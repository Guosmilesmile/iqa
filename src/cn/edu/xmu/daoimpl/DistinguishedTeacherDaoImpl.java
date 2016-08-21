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

import cn.edu.xmu.dao.DistinguishedTeacherDao;
import cn.edu.xmu.entity.DistinguishedTeacher;
import cn.edu.xmu.table.DistinguishedTeacherTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;


/**
 * @author zhantu
 * 教学名师  实体类功能 ——接口实现
 * date 2015-07-08
 */

public class DistinguishedTeacherDaoImpl extends BaseDaoImpl<DistinguishedTeacher>implements DistinguishedTeacherDao{

	@Override
	public int addRecord(DistinguishedTeacher dt) {
		
		int result = 0;

		String t_sql = "update " + DistinguishedTeacherTable.TABLE_NAME + " set "
				+ DistinguishedTeacherTable.DT_SERIALNUMBER + " = "
				+ DistinguishedTeacherTable.DT_SERIALNUMBER + " +1 where "
				+ DistinguishedTeacherTable.DT_SERIALNUMBER + ">=?";

		
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
			t_pstmt.setInt(1, dt.getDt_serialnumber());
			
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
		String sql = "insert into " + DistinguishedTeacherTable.TABLE_NAME + "("
				+ DistinguishedTeacherTable.DT_SEQUENCENUMBER + ","
				+ DistinguishedTeacherTable.DT_NAME + ","
				+ DistinguishedTeacherTable.DT_COLLEGENAME + ","
				+ DistinguishedTeacherTable.DT_COUNTRY + ","
				+ DistinguishedTeacherTable.DT_PROVINCE + ","
				+ DistinguishedTeacherTable.DT_SCHOOL + ","
				+ DistinguishedTeacherTable.DT_SERIALNUMBER + ","
				+ DistinguishedTeacherTable.DT_COLLEGE + ","
				+ DistinguishedTeacherTable.DT_COMMENTS + ","
				+ DistinguishedTeacherTable.ISNULL
				+ ")values(?,?,?,?,?,?,?,?,?,?)";

		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, dt.getDt_sequencenumber());
			pstmt.setString(2, dt.getDt_name());
			pstmt.setString(3, dt.getDt_collegename());
			pstmt.setInt(4, dt.getDt_country());
			pstmt.setInt(5, dt.getDt_province());
			pstmt.setInt(6, dt.getDt_school());
			pstmt.setInt(7, dt.getDt_serialnumber());
			pstmt.setString(8, dt.getDt_college());
			pstmt.setString(9, dt.getDt_comments());
			pstmt.setInt(10, dt.getIsnull());
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
	public int getDistinguishedTeacherCount(Map queryParams) {
		
		int count = 0;
		//获取条件的语句
		String sql = "select count(*) from " + DistinguishedTeacherTable.TABLE_NAME
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
		sql += String.format(" or %s ='%s'", DistinguishedTeacherTable.DT_COLLEGE, "");
		
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
	public List<DistinguishedTeacher> getAllDistinguishedTeacher(int start, int end,
			String sortStr, String orderStr, Map queryParams) {
		
		String sql = " select tmp.* from ( " + " select * from "
				+ DistinguishedTeacherTable.TABLE_NAME + " where 1=1 ";
		
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
		
		List<DistinguishedTeacher> dts = new ArrayList<DistinguishedTeacher>();
		try {
			
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int dt_id = resultSet.getInt(DistinguishedTeacherTable.DT_ID);
				Integer dt_sequencenumber = resultSet.getInt(DistinguishedTeacherTable.DT_SEQUENCENUMBER);
				if(dt_sequencenumber==-999)
					dt_sequencenumber = null;
				String dt_name = resultSet.getString(DistinguishedTeacherTable.DT_NAME);
				String dt_collegename = resultSet.getString(DistinguishedTeacherTable.DT_COLLEGENAME);
				Integer dt_country = resultSet.getInt(DistinguishedTeacherTable.DT_COUNTRY);
				if(dt_country==-999)
					dt_country = null;
				Integer dt_province = resultSet.getInt(DistinguishedTeacherTable.DT_PROVINCE);
				if(dt_province==-999)
					dt_province = null;
				Integer dt_school = resultSet.getInt(DistinguishedTeacherTable.DT_SCHOOL);
				if(dt_school==-999)
					dt_school = null;
				int dt_serialnumber = resultSet.getInt(DistinguishedTeacherTable.DT_SERIALNUMBER);
				Date dt_deadline = resultSet.getDate(DistinguishedTeacherTable.DT_DEADLINE);
				String dt_comments = resultSet.getString(DistinguishedTeacherTable.DT_COMMENTS);
				String dt_college = resultSet.getString(DistinguishedTeacherTable.DT_COLLEGE);
				int isnull = resultSet.getInt(DistinguishedTeacherTable.ISNULL);
				
				if(dt_comments==null)
					dt_comments = "";
				DistinguishedTeacher dt = new DistinguishedTeacher(dt_id, dt_sequencenumber, dt_name,
						dt_collegename, dt_country, dt_province,
						dt_school, dt_serialnumber, dt_deadline,
						dt_college, dt_comments, isnull);
				
				dts.add(dt);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return dts;
	}

	@Override
	public int alterDistinguishedTeacher(Map<String, String> valueMap, String id) {
		
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(DistinguishedTeacherTable.DT_ID, id);
		int result = updateRecord(DistinguishedTeacherTable.TABLE_NAME, valueMap,
				condition);
		return result;
	}

	@Override
	public boolean batchDelete(String[] dtids) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();

		for (String dtid : dtids) {
			String sql = "delete from " + DistinguishedTeacherTable.TABLE_NAME
					+ " where " + DistinguishedTeacherTable.DT_ID + " = '" + dtid + "'";
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
	public List<DistinguishedTeacher> getDistinguishedTeacher(int start, int end,
			String sortStr, String orderStr, Map<String, String> params,
			Date deadline) {
		String sql = " select tmp.* from ( " + " select * from "
				+ DistinguishedTeacherTable.TABLE_NAME + " where 1=1 ";
		if (deadline != null) {

			sql += String.format("and "+DistinguishedTeacherTable.DT_DEADLINE+" like  '%s%%' ", deadline);
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

		List<DistinguishedTeacher> distinguishedTeachers = new ArrayList<DistinguishedTeacher>();
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
				int dt_id = resultSet.getInt(DistinguishedTeacherTable.DT_ID);
				Integer dt_sequencenumber = resultSet.getInt(DistinguishedTeacherTable.DT_SEQUENCENUMBER);
				if(dt_sequencenumber==-999)
					dt_sequencenumber = null;
				String dt_name = resultSet.getString(DistinguishedTeacherTable.DT_NAME);
				String dt_collegename = resultSet.getString(DistinguishedTeacherTable.DT_COLLEGENAME);
				Integer dt_country = resultSet.getInt(DistinguishedTeacherTable.DT_COUNTRY);
				if(dt_country==-999)
					dt_country = null;
				Integer dt_province = resultSet.getInt(DistinguishedTeacherTable.DT_PROVINCE);
				if(dt_province==-999)
					dt_province = null;
				Integer dt_school = resultSet.getInt(DistinguishedTeacherTable.DT_SCHOOL);
				if(dt_school==-999)
					dt_school = null;
				int dt_serialnumber = resultSet.getInt(DistinguishedTeacherTable.DT_SERIALNUMBER);
				Date dt_deadline = resultSet.getDate(DistinguishedTeacherTable.DT_DEADLINE);
				String dt_comments = resultSet.getString(DistinguishedTeacherTable.DT_COMMENTS);
				String dt_college = resultSet.getString(DistinguishedTeacherTable.DT_COLLEGE);
				int isnull = resultSet.getInt(DistinguishedTeacherTable.ISNULL);
				
				DistinguishedTeacher dt = new DistinguishedTeacher(dt_id, dt_sequencenumber, dt_name,
						dt_collegename, dt_country, dt_province,
						dt_school, dt_serialnumber, dt_deadline,
						dt_college, dt_comments, isnull);

				distinguishedTeachers.add(dt);
			}
			return distinguishedTeachers;
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
		String sql = "delete from " + DistinguishedTeacherTable.TABLE_NAME
				+ " where " + DistinguishedTeacherTable.DT_COLLEGE + " = '" + college + "'" +" and dt_deadline is null ";
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
	public List<DistinguishedTeacher> getAllDistinguishedTeacher() {
		String sql = " select * from " + DistinguishedTeacherTable.TABLE_NAME
				+ " where 1=1 " + " order by " + DistinguishedTeacherTable.DT_ID;
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
			List<DistinguishedTeacher> distinguishedTeacherList = new ArrayList<DistinguishedTeacher>();
			while (resultSet.next()) {
				int dt_id = resultSet.getInt(DistinguishedTeacherTable.DT_ID);
				Integer dt_sequencenumber = resultSet.getInt(DistinguishedTeacherTable.DT_SEQUENCENUMBER);
				if(dt_sequencenumber==-999)
					dt_sequencenumber = null;
				String dt_name = resultSet.getString(DistinguishedTeacherTable.DT_NAME);
				String dt_collegename = resultSet.getString(DistinguishedTeacherTable.DT_COLLEGENAME);
				Integer dt_country = resultSet.getInt(DistinguishedTeacherTable.DT_COUNTRY);
				if(dt_country==-999)
					dt_country = null;
				Integer dt_province = resultSet.getInt(DistinguishedTeacherTable.DT_PROVINCE);
				if(dt_province==-999)
					dt_province = null;
				Integer dt_school = resultSet.getInt(DistinguishedTeacherTable.DT_SCHOOL);
				if(dt_school==-999)
					dt_school = null;
				int dt_serialnumber = resultSet.getInt(DistinguishedTeacherTable.DT_SERIALNUMBER);
				Date dt_deadline = resultSet.getDate(DistinguishedTeacherTable.DT_DEADLINE);
				String dt_comments = resultSet.getString(DistinguishedTeacherTable.DT_COMMENTS);
				String dt_college = resultSet.getString(DistinguishedTeacherTable.DT_COLLEGE);
				int isnull = resultSet.getInt(DistinguishedTeacherTable.ISNULL);
				
				DistinguishedTeacher dt = new DistinguishedTeacher(dt_id, dt_sequencenumber, dt_name,
						dt_collegename, dt_country, dt_province,
						dt_school, dt_serialnumber, dt_deadline,
						dt_college, dt_comments, isnull);
				
				distinguishedTeacherList.add(dt);
			}
			return distinguishedTeacherList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
	}
}
