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

import cn.edu.xmu.dao.MajorTeachDao;
import cn.edu.xmu.entity.MajorTeach;
import cn.edu.xmu.table.MajorTeachTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;
/**
 * 
 * @author Luo
 * 专业教学实施情况 实体类功能 ——接口实现
 * date 2015-07-05
 */
public class MajorTeachDaoImpl extends BaseDaoImpl<MajorTeach> implements MajorTeachDao {

	@Override
	public int addRecord(MajorTeach mt) {
		int result = 0;

		String t_sql = "update " + MajorTeachTable.TABLE_NAME + " set "
				+ MajorTeachTable.MT_SERIALNUMBER + " = "
				+ MajorTeachTable.MT_SERIALNUMBER + " +1 where "
				+ MajorTeachTable.MT_SERIALNUMBER + ">=?";

		
		Connection connection2 = null;
		try {
			//连接池取得对象连接
			connection2 = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		PreparedStatement t_pstmt = null;
		try {
			//获取操作对象
			t_pstmt = connection2.prepareStatement(t_sql);
			t_pstmt.setInt(1, mt.getMt_serialnumber());
			
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
		String sql = "insert into " + MajorTeachTable.TABLE_NAME + "("
				+ MajorTeachTable.MT_MAJORNAMEINSCHOOL
				+ "," + MajorTeachTable.MT_MAJORCODEINSCHOOL+ ","
				+ MajorTeachTable.MT_COURSECODE + ","
				+ MajorTeachTable.MT_COURSENATURE + ","
				+ MajorTeachTable.MT_CREDITS + ","
				+ MajorTeachTable.MT_GRADE + ","
				+ MajorTeachTable.MT_SERIALNUMBER + ","
				+ MajorTeachTable.MT_COLLEGE + ","
				+ MajorTeachTable.MT_COMMENTS+","
				+ MajorTeachTable.ISNULL
				+ ")values(?,?,?,?,?,?,?,?,?,?)";

		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, mt.getMt_majornameinschool());
			pstmt.setString(2, mt.getMt_majorcodeinschool());
			pstmt.setString(3, mt.getMt_coursecode());
			pstmt.setString(4, mt.getMt_coursenature());
			pstmt.setFloat(5, mt.getMt_credits());
			pstmt.setString(6, mt.getMt_grade());
			pstmt.setInt(7, mt.getMt_serialnumber());
			pstmt.setString(8, mt.getMt_college());
			pstmt.setString(9, mt.getMt_comments());
			pstmt.setInt(10, mt.getIsnull());
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
	public boolean batchDelete(String[] mtids) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();

		for (String mtid : mtids) {
			String sql = "delete from " + MajorTeachTable.TABLE_NAME
					+ " where " + MajorTeachTable.MT_ID + " = '" + mtid + "'";
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
	public int alterMajorTeach(Map<String, String> valueMap, String id) {
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(MajorTeachTable.MT_ID, id);
		int result = updateRecord(MajorTeachTable.TABLE_NAME, valueMap,
				condition);
		return result;
	}

	@Override
	public int getMajorTeachCount(Map queryParams) {
		int count = 0;
		//获取条件的语句
		String sql = "select count(*) from " + MajorTeachTable.TABLE_NAME
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
			sql += String.format(" and  %s like  '%s%%' ", key, value);
		}
		sql += String.format(" or %s ='%s'", MajorTeachTable.MT_COLLEGE, "");
		
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
	public List<MajorTeach> getAllMajorTeach(int start, int end,
			String sortStr, String orderStr, Map queryParams) {
		String sql = " select tmp.* from ( " + " select * from "
				+ MajorTeachTable.TABLE_NAME + " where 1=1 ";
		
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
		
		List<MajorTeach> mts = new ArrayList<MajorTeach>();
		try {
			
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int mt_id = resultSet.getInt(MajorTeachTable.MT_ID);
				String mt_majornameinschool = resultSet.getString(MajorTeachTable.MT_MAJORNAMEINSCHOOL);
				String mt_majorcodeinschool = resultSet.getString(MajorTeachTable.MT_MAJORCODEINSCHOOL);
				String mt_coursecode = resultSet.getString(MajorTeachTable.MT_COURSECODE);
				String mt_coursenature = resultSet.getString(MajorTeachTable.MT_COURSENATURE);
				Float mt_credits = resultSet.getFloat(MajorTeachTable.MT_CREDITS);
				if(mt_credits==-999)
					mt_credits = null;
				
				String mt_grade = resultSet.getString(MajorTeachTable.MT_GRADE);
				int mt_serialnumber = resultSet.getInt(MajorTeachTable.MT_SERIALNUMBER);
				String mt_comments = resultSet.getString(MajorTeachTable.MT_COMMENTS);
				String mt_college = resultSet.getString(MajorTeachTable.MT_COLLEGE);
				
				int isnull = resultSet.getInt(MajorTeachTable.ISNULL);
				if(mt_comments==null)
					mt_comments="";
				
				MajorTeach mt = new MajorTeach(mt_id, mt_majornameinschool,
						mt_majorcodeinschool, mt_coursecode,mt_coursenature, mt_credits,mt_grade,mt_serialnumber, mt_comments,isnull,mt_college);
				
				mts.add(mt);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return mts;
	}

	@Override
	public List<MajorTeach> getAllMajorTeach() {
		String sql = " select * from "
				+ MajorTeachTable.TABLE_NAME + " where 1=1 ";

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		List<MajorTeach> mts = new ArrayList<MajorTeach>();
		try {
			
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int mt_id = resultSet.getInt(MajorTeachTable.MT_ID);
				String mt_majornameinschool = resultSet.getString(MajorTeachTable.MT_MAJORNAMEINSCHOOL);
				String mt_majorcodeinschool = resultSet.getString(MajorTeachTable.MT_MAJORCODEINSCHOOL);
				String mt_coursecode = resultSet.getString(MajorTeachTable.MT_COURSECODE);
				String mt_coursenature = resultSet.getString(MajorTeachTable.MT_COURSENATURE);
				Float mt_credits = resultSet.getFloat(MajorTeachTable.MT_CREDITS);
				if(mt_credits==-999)
					mt_credits = null;
				
				String mt_grade = resultSet.getString(MajorTeachTable.MT_GRADE);
				int mt_serialnumber = resultSet.getInt(MajorTeachTable.MT_SERIALNUMBER);
				String mt_comments = resultSet.getString(MajorTeachTable.MT_COMMENTS);
				String mt_college = resultSet.getString(MajorTeachTable.MT_COLLEGE);
				int isnull = resultSet.getInt(MajorTeachTable.ISNULL);
				if(mt_comments==null)
					mt_comments="";
				
				MajorTeach mt = new MajorTeach(mt_id, mt_majornameinschool,
						mt_majorcodeinschool, mt_coursecode,mt_coursenature, mt_credits,mt_grade,mt_serialnumber, mt_comments,isnull,mt_college);
				
				mts.add(mt);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return mts;
	}

	@Override
	public void deleteByCollegeandDeadline(String college, Date deadline)
			throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();
		String sql = "delete from " + MajorTeachTable.TABLE_NAME
				+ " where " + MajorTeachTable.MT_COLLEGE + " = '" + college + "'" +" and mt_deadline is null ";
		System.out.println(sql);
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils_DBCP.release(connection, stmt, null);
		}
		
	}

}
