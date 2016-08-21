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

import cn.edu.xmu.dao.TeacherObtainPatentDao;
import cn.edu.xmu.entity.TeacherObtainPatent;
import cn.edu.xmu.table.TeacherObtainPatentTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;


/**
 * 3-6-6  教师获准专利数（自然年）
 * @author chunfeng
 *
 */
public class TeacherObtainPatentDaoImpl extends BaseDaoImpl<TeacherObtainPatent> implements TeacherObtainPatentDao{

	@Override
	public List<TeacherObtainPatent> getTeacherObtainPatent(
			int start, int end, String sortStr, String orderStr,
			Map<String, String> params,Date deadline) {
		// TODO Auto-generated method stub
		String sql = " select tmp.* from ( " + " select * from "
				+ TeacherObtainPatentTable.TABLE_NAME + " where 1=1 ";
		if (deadline != null) {
			sql += String.format("and "+TeacherObtainPatentTable.TOP_DEADLINE+" like  '%s%%' ", deadline);
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

		List<TeacherObtainPatent> teacherObtainPatents = new ArrayList<TeacherObtainPatent>();
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
				int top_id = resultSet.getInt(TeacherObtainPatentTable.TOP_ID);
				Integer top_total = resultSet.getInt(TeacherObtainPatentTable.TOP_TOTAL);  
				if(top_total == -1) top_total = null;
				Integer top_invention = resultSet.getInt(TeacherObtainPatentTable.TOP_INVENTION); 
				if(top_invention == -1) top_invention = null;
				Integer top_utilitymodel = resultSet.getInt(TeacherObtainPatentTable.TOP_UTILITYMODEL); 
				if(top_utilitymodel == -1) top_utilitymodel = null;
				
				String top_college =  resultSet.getString(TeacherObtainPatentTable.TOP_COLLEGE);
				int top_serialnumber = resultSet.getInt(TeacherObtainPatentTable.TOP_SERIALNUMBER);
				Date top_deadline = resultSet.getDate(TeacherObtainPatentTable.TOP_DEADLINE); 
				String top_comments =  resultSet.getString(TeacherObtainPatentTable.TOP_COMMENTS);
				if(null == top_comments){
					top_comments = "";
				}
				int top_isnull = resultSet.getInt(TeacherObtainPatentTable.TOP_ISNULL);
				TeacherObtainPatent teacherObtainPatent = new TeacherObtainPatent(top_id,
				   top_total, top_invention, top_utilitymodel, top_college, top_deadline, 
				   top_serialnumber, top_comments, top_isnull);

				teacherObtainPatents.add(teacherObtainPatent);
			}
			return teacherObtainPatents;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
	}

	@Override
	public int getTeacherObtainPatentCount(Map queryParams) {
		// TODO Auto-generated method stub
		int count = 0;
		String sql = "select count(*) from " + TeacherObtainPatentTable.TABLE_NAME
				+ " where 1 = 1 ";
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (queryParams != null)
		{
			for (Object object : queryParams.keySet())
			{
				String key = object.toString();
				String value = queryParams.get(key).toString();
				sql += String.format(" and  %s like  '%%%s%%' ", key, value);
			}
			// sql += String.format(" or %s ='%s'", "college","");
		}
		
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
		} finally{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}

		System.out.println(count);
		return count;
	}

	@Override
	public boolean batchDelete(String[] topids) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();

		for (String topid : topids) {
			String sql = "delete from " + TeacherObtainPatentTable.TABLE_NAME
					+ " where " + TeacherObtainPatentTable.TOP_ID + " = '" + topid
					+ "'";
			System.out.println(sql);
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
	public int addTeacherObtainPatent(
			TeacherObtainPatent teacherObtainPatent) {
		// TODO Auto-generated method stub
		int result = 0;

		String sql2 = "update " + TeacherObtainPatentTable.TABLE_NAME + " set "
				+ TeacherObtainPatentTable.TOP_SERIALNUMBER + " = "
				+ TeacherObtainPatentTable.TOP_SERIALNUMBER + " +1 where "
				+ TeacherObtainPatentTable.TOP_SERIALNUMBER + ">="
				+ teacherObtainPatent.getTop_serialnumber();
		Connection connection2 = null;
		try {
			connection2 = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		PreparedStatement pstmt2 = null;
		try {
			pstmt2 = connection2.prepareStatement(sql2);
			result = pstmt2.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			try {
				JdbcUtils_DBCP.release(connection2, pstmt2, null);
			} catch (Exception e2) {
				return 0;
			}
		}

		String sql = "insert into " + TeacherObtainPatentTable.TABLE_NAME + "("
				+ TeacherObtainPatentTable.TOP_TOTAL + ","			
				+ TeacherObtainPatentTable.TOP_INVENTION + ","
				+ TeacherObtainPatentTable.TOP_UTILITYMODEL + ","
				+ TeacherObtainPatentTable.TOP_COLLEGE + ","
				+ TeacherObtainPatentTable.TOP_SERIALNUMBER +","
				+ TeacherObtainPatentTable.TOP_ISNULL + ")values(?,?,?,?,?,?)";

		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PreparedStatement pstmt = null;

		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, teacherObtainPatent.getTop_total());
			pstmt.setInt(2, teacherObtainPatent.getTop_invention());
			pstmt.setInt(3, teacherObtainPatent.getTop_utilitymodel());
			pstmt.setString(4, teacherObtainPatent.getTop_college());
			pstmt.setInt(5, teacherObtainPatent.getTop_serialnumber());
			pstmt.setInt(6, teacherObtainPatent.getTop_isnull());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			result = -1;
		} finally{
			JdbcUtils_DBCP.release(connection, pstmt, null);
		}


		return result;
	}

	@Override
	public int alterTeacherObtainPatent(
			Map<String, String> valueMap, String id) {
		// TODO Auto-generated method stub
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(TeacherObtainPatentTable.TOP_ID, id);
		int result = updateRecord(TeacherObtainPatentTable.TABLE_NAME, valueMap,
				condition);
		return result;
	}

	@Override
	public List<TeacherObtainPatent> getAllTeacherObtainPatent() {
		// TODO Auto-generated method stub
		String sql = " select * from " + TeacherObtainPatentTable.TABLE_NAME
				+ " where 1=1 " + " order by " + TeacherObtainPatentTable.TOP_ID;
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
			List<TeacherObtainPatent> teacherObtainPatentList = new ArrayList<TeacherObtainPatent>();
			while (resultSet.next()) {
				int top_id = resultSet.getInt(TeacherObtainPatentTable.TOP_ID);
				Integer top_total = resultSet.getInt(TeacherObtainPatentTable.TOP_TOTAL);  
				if(top_total == -1) top_total = null;
				Integer top_invention = resultSet.getInt(TeacherObtainPatentTable.TOP_INVENTION); 
				if(top_invention == -1) top_invention = null;
				Integer top_utilitymodel = resultSet.getInt(TeacherObtainPatentTable.TOP_UTILITYMODEL); 
				if(top_utilitymodel == -1) top_utilitymodel = null;
				
				String top_college =  resultSet.getString(TeacherObtainPatentTable.TOP_COLLEGE);
				int top_serialnumber = resultSet.getInt(TeacherObtainPatentTable.TOP_SERIALNUMBER);
				Date top_deadline = resultSet.getDate(TeacherObtainPatentTable.TOP_DEADLINE); 
				String top_comments =  resultSet.getString(TeacherObtainPatentTable.TOP_COMMENTS);
				if(null == top_comments){
					top_comments = "";
				}
				int top_isnull = resultSet.getInt(TeacherObtainPatentTable.TOP_ISNULL);
				TeacherObtainPatent teacherObtainPatent = new TeacherObtainPatent(top_id,
				   top_total, top_invention, top_utilitymodel, top_college, top_deadline, 
				   top_serialnumber, top_comments, top_isnull);
				teacherObtainPatentList.add(teacherObtainPatent);
			}
			return teacherObtainPatentList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}

	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		Statement stmt = null;
		try {
			stmt = connection.createStatement();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		String sql = "delete from " + TeacherObtainPatentTable.TABLE_NAME;
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils_DBCP.release(connection, stmt, null);
		}
	}
	
	@Override
	public boolean deleteByCollegeandDeadline(String college, Date deadline) throws SQLException
	{
		Connection connection = null;
		Statement stmt = null;
		try
		{
			connection = JdbcUtils_DBCP.getConnection();
			stmt = connection.createStatement();
		} catch (SQLException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
		String sql = "delete from " + TeacherObtainPatentTable.TABLE_NAME + " where "
				+ TeacherObtainPatentTable.TOP_COLLEGE + " = '" + college + "'" + " and "+TeacherObtainPatentTable.TOP_DEADLINE +" is null ";
		System.out.println(sql);
		try
		{
			stmt.executeUpdate(sql);
		} catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		} finally
		{
			JdbcUtils_DBCP.release(connection, stmt, null);
		}
		return true;
	}
}

