package cn.edu.xmu.daoimpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.dao.TextbookAwardDao;
import cn.edu.xmu.entity.TextbookAward;
import cn.edu.xmu.table.NewBooksthatYearTable;
import cn.edu.xmu.table.TextbookAwardTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;


/**
 * @author zhantu
 * 教材获奖情况（自然年）  实体类功能 ——接口实现
 * date 2015-07-08
 */

public class TextbookAwardDaoImpl extends BaseDaoImpl<TextbookAward>implements TextbookAwardDao{

	@Override
	public int addRecord(TextbookAward ta) {
		
		int result = 0;

		String t_sql = "update " + TextbookAwardTable.TABLE_NAME + " set "
				+ TextbookAwardTable.TA_SERIALNUMBER + " = "
				+ TextbookAwardTable.TA_SERIALNUMBER + " +1 where "
				+ TextbookAwardTable.TA_SERIALNUMBER + ">=?";

		
		Connection connection2 = null;
		try {
			//连接池取得对象连接
			connection2 = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
			return 0;
		}
		PreparedStatement t_pstmt = null;
		try {
			//获取操作对象
			t_pstmt = connection2.prepareStatement(t_sql);
			t_pstmt.setInt(1, ta.getTa_serialnumber());
			
			//执行插入操作并更新
			result = t_pstmt.executeUpdate();
			
		} catch (SQLException e) {
			//事务回滚，不做插入操作
			e.printStackTrace();
			return 0;
		} finally {
			JdbcUtils_DBCP.release(connection2, t_pstmt, null);
		}

		//执行插入操作的语句
		String sql = "insert into " + TextbookAwardTable.TABLE_NAME + "("
				+ TextbookAwardTable.TA_COLLEGENAME + ","
				+ TextbookAwardTable.TA_NAME + ","
				+ TextbookAwardTable.TA_AUTHOR + ","
				+ TextbookAwardTable.TA_PUBLISHER + ","
				+ TextbookAwardTable.TA_PRIZENAME + ","
				+ TextbookAwardTable.TA_GRADE + ","
				+ TextbookAwardTable.TA_TYPE + ","
				+ TextbookAwardTable.TA_SERIALNUMBER + ","
				+ TextbookAwardTable.TA_COLLEGE + ","
				+ TextbookAwardTable.TA_COMMENTS + ","
				+ TextbookAwardTable.ISNULL
				+ ")values(?,?,?,?,?,?,?,?,?,?,?)";

		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, ta.getTa_collegename());
			pstmt.setString(2, ta.getTa_name());
			pstmt.setString(3, ta.getTa_author());
			pstmt.setString(4, ta.getTa_publisher());
			pstmt.setString(5, ta.getTa_prizename());
			pstmt.setString(6, ta.getTa_grade());
			pstmt.setString(7, ta.getTa_type());
			pstmt.setInt(8, ta.getTa_serialnumber());
			pstmt.setString(9, ta.getTa_college());
			pstmt.setString(10, ta.getTa_comments());
			pstmt.setInt(11, ta.getIsnull());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			//事务回滚。不做插入操作
			e.printStackTrace();
			result = -1;
			
		} finally {
			JdbcUtils_DBCP.release(connection,pstmt,null);
		}
		return result;
		
	}

	@Override
	public int getTextbookAwardCount(Map queryParams) {
		
		int count = 0;
		//获取条件的语句
		String sql = "select count(*) from " + TextbookAwardTable.TABLE_NAME
				+ " where 1 = 1";
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		for (Object object : queryParams.keySet()) {
			String key = object.toString();
			String value = queryParams.get(key).toString();
			sql += String.format(" and  %s like  '%%%s%%' ", key, value);
		}
		sql += String.format(" or %s ='%s'", TextbookAwardTable.TA_COLLEGE, "");
		
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		try {
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			resultSet.next();
			count = resultSet.getInt(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		
		return count;
	}

	@Override
	public List<TextbookAward> getAllTextbookAward(int start, int end,
			String sortStr, String orderStr, Map queryParams) {
		
		String sql = " select tmp.* from ( " + " select * from "
				+ TextbookAwardTable.TABLE_NAME + " where 1=1 ";
		
		for (Object object : queryParams.keySet()) {
			String key = object.toString();
			String value = queryParams.get(key).toString();
			sql += String.format(" and %s like  '%%%s%%'", key, value);
		}
		sql += " order by " + sortStr + " " + orderStr + " ) tmp limit "
				+ start + " ," + end;

		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		List<TextbookAward> tas = new ArrayList<TextbookAward>();
		try {
			
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int ta_id = resultSet.getInt(TextbookAwardTable.TA_ID);
				
				String ta_collegename= resultSet.getString(TextbookAwardTable.TA_COLLEGENAME);
				String ta_name= resultSet.getString(TextbookAwardTable.TA_NAME);
				String ta_author= resultSet.getString(TextbookAwardTable.TA_AUTHOR);
				String ta_publisher= resultSet.getString(TextbookAwardTable.TA_PUBLISHER);
				String ta_prizename= resultSet.getString(TextbookAwardTable.TA_PRIZENAME);
				String ta_grade= resultSet.getString(TextbookAwardTable.TA_GRADE);
				String ta_type= resultSet.getString(TextbookAwardTable.TA_TYPE);

				
				int ta_serialnumber = resultSet.getInt(TextbookAwardTable.TA_SERIALNUMBER);
				Date ta_deadline = resultSet.getDate(TextbookAwardTable.TA_DEADLINE);
				String ta_comments = resultSet.getString(TextbookAwardTable.TA_COMMENTS);
				String ta_college = resultSet.getString(TextbookAwardTable.TA_COLLEGE);
				if(ta_comments==null)
					ta_comments = "";
				int isnull = resultSet.getInt(NewBooksthatYearTable.ISNULL);
				TextbookAward ta = new TextbookAward(ta_id, ta_collegename, ta_name,
						ta_author, ta_publisher, ta_prizename,
						ta_grade, ta_type, ta_serialnumber,
						ta_deadline, ta_college, ta_comments,isnull);
				
				tas.add(ta);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return tas;
	}

	@Override
	public int alterTextbookAward(Map<String, String> valueMap, String id) {
		
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(TextbookAwardTable.TA_ID, id);
		int result = updateRecord(TextbookAwardTable.TABLE_NAME, valueMap,
				condition);
		return result;
	}

	@Override
	public boolean batchDelete(String[] taids) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();

		for (String taid : taids) {
			String sql = "delete from " + TextbookAwardTable.TABLE_NAME
					+ " where " + TextbookAwardTable.TA_ID + " = '" + taid + "'";
			try {
				stmt.executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		JdbcUtils_DBCP.release(connection, stmt, null);
		return true;
	}

	@Override
	public List<TextbookAward> getTextbookAward(int start, int end,
			String sortStr, String orderStr, Map<String, String> params,
			Date deadline) {
		String sql = " select tmp.* from ( " + " select * from "
				+ TextbookAwardTable.TABLE_NAME + " where 1=1 ";
		if (deadline != null) {

			sql += String.format("and "+TextbookAwardTable.TA_DEADLINE+" like  '%s%%' ", deadline);
		}
		if (!(params == null || params.keySet().size() == 0)) {
			for (Object object : params.keySet()) {

				String key = object.toString();
				String value = params.get(key).toString();
				sql += String.format("and %s like  '%%%s%%' ", key, value);
			}
		}

		sql += " order by " + sortStr + " " + orderStr + " ) tmp limit "
				+ start + " ," + end;

		System.out.println(sql);

		List<TextbookAward> textbookAwards = new ArrayList<TextbookAward>();
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		try {
			System.out.println(sql);
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				int ta_id = resultSet.getInt(TextbookAwardTable.TA_ID);
				
				String ta_collegename= resultSet.getString(TextbookAwardTable.TA_COLLEGENAME);
				String ta_name= resultSet.getString(TextbookAwardTable.TA_NAME);
				String ta_author= resultSet.getString(TextbookAwardTable.TA_AUTHOR);
				String ta_publisher= resultSet.getString(TextbookAwardTable.TA_PUBLISHER);
				String ta_prizename= resultSet.getString(TextbookAwardTable.TA_PRIZENAME);
				String ta_grade= resultSet.getString(TextbookAwardTable.TA_GRADE);
				String ta_type= resultSet.getString(TextbookAwardTable.TA_TYPE);

				
				int ta_serialnumber = resultSet.getInt(TextbookAwardTable.TA_SERIALNUMBER);
				Date ta_deadline = resultSet.getDate(TextbookAwardTable.TA_DEADLINE);
				String ta_comments = resultSet.getString(TextbookAwardTable.TA_COMMENTS);
				String ta_college = resultSet.getString(TextbookAwardTable.TA_COLLEGE);
				int isnull = resultSet.getInt(NewBooksthatYearTable.ISNULL);
				
				TextbookAward ta = new TextbookAward(ta_id, ta_collegename, ta_name,
						ta_author, ta_publisher, ta_prizename,
						ta_grade, ta_type, ta_serialnumber,
						ta_deadline, ta_college, ta_comments, isnull);

				textbookAwards.add(ta);
			}
			return textbookAwards;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
	}
	
	@Override
	public void deleteByCollegeandDeadline(String college, Date deadline)
			throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();
		String sql = "delete from " + TextbookAwardTable.TABLE_NAME
				+ " where " + TextbookAwardTable.TA_COLLEGE + " = '" + college + "'" +" and ta_deadline is null ";
		System.out.println(sql);
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils_DBCP.release(connection, stmt, null);
		}
		
	}

	@Override
	public List<TextbookAward> getAllTextbookAward() {
		String sql = " select * from " + TextbookAwardTable.TABLE_NAME
				+ " where 1=1 " + " order by " + TextbookAwardTable.TA_ID;
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			System.out.println(sql);
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			List<TextbookAward> textbookAwardList = new ArrayList<TextbookAward>();
			while (resultSet.next()) {
int ta_id = resultSet.getInt(TextbookAwardTable.TA_ID);
				
				String ta_collegename= resultSet.getString(TextbookAwardTable.TA_COLLEGENAME);
				String ta_name= resultSet.getString(TextbookAwardTable.TA_NAME);
				String ta_author= resultSet.getString(TextbookAwardTable.TA_AUTHOR);
				String ta_publisher= resultSet.getString(TextbookAwardTable.TA_PUBLISHER);
				String ta_prizename= resultSet.getString(TextbookAwardTable.TA_PRIZENAME);
				String ta_grade= resultSet.getString(TextbookAwardTable.TA_GRADE);
				String ta_type= resultSet.getString(TextbookAwardTable.TA_TYPE);

				
				int ta_serialnumber = resultSet.getInt(TextbookAwardTable.TA_SERIALNUMBER);
				Date ta_deadline = resultSet.getDate(TextbookAwardTable.TA_DEADLINE);
				String ta_comments = resultSet.getString(TextbookAwardTable.TA_COMMENTS);
				String ta_college = resultSet.getString(TextbookAwardTable.TA_COLLEGE);
				int isnull = resultSet.getInt(NewBooksthatYearTable.ISNULL);
				
				TextbookAward ta = new TextbookAward(ta_id, ta_collegename, ta_name,
						ta_author, ta_publisher, ta_prizename,
						ta_grade, ta_type, ta_serialnumber,
						ta_deadline, ta_college, ta_comments, isnull);
				
				textbookAwardList.add(ta);
			}
			return textbookAwardList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
	}
}
