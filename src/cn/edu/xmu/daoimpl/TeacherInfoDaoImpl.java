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

import cn.edu.xmu.dao.TeacherInfoDao;
import cn.edu.xmu.entity.TeacherInfo;
import cn.edu.xmu.table.TeacherInfoTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;


/**
 * 5-1-1-1 教师资源表
 * @author chunfeng
 *
 */
public class TeacherInfoDaoImpl extends BaseDaoImpl<TeacherInfo> implements TeacherInfoDao{

	@Override
	public List<TeacherInfo> getTeacherInfo(
			int start, int end, String sortStr, String orderStr,
			Map<String, String> params,Date deadline) {
		// TODO Auto-generated method stub
		String sql = " select tmp.* from ( " + " select * from "
				+ TeacherInfoTable.TABLE_NAME + " where 1=1 ";
		if (deadline != null) {
			sql += String.format("and "+TeacherInfoTable.TI_DEADLINE+" like  '%s%%' ", deadline);
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

		List<TeacherInfo> teacherInfos = new ArrayList<TeacherInfo>();
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
				int ti_id = resultSet.getInt(TeacherInfoTable.TI_ID);
				String ti_collegename = resultSet.getString(TeacherInfoTable.TI_COLLEGENAME);  				
				String ti_name = resultSet.getString(TeacherInfoTable.TI_NAME); 
				String ti_number = resultSet.getString(TeacherInfoTable.TI_NUMBER); 
				String ti_professiontitle = resultSet.getString(TeacherInfoTable.TI_PROFESSIONTITLE);  	
				
				String ti_college =  resultSet.getString(TeacherInfoTable.TI_COLLEGE);
				int ti_serialnumber = resultSet.getInt(TeacherInfoTable.TI_SERIALNUMBER);
				Date ti_deadline = resultSet.getDate(TeacherInfoTable.TI_DEADLINE); 
				String ti_comments =  resultSet.getString(TeacherInfoTable.TI_COMMENTS);
				if(null == ti_comments){
					ti_comments = "";
				}
				int ti_isnull = resultSet.getInt(TeacherInfoTable.TI_ISNULL);
				TeacherInfo teacherInfo = new TeacherInfo(ti_id, ti_collegename, ti_name, ti_number, ti_professiontitle,
			             ti_college, ti_deadline, ti_serialnumber, ti_comments, ti_isnull);

				teacherInfos.add(teacherInfo);
			}
			return teacherInfos;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
	}

	@Override
	public int getTeacherInfoCount(Map queryParams) {
		// TODO Auto-generated method stub
		int count = 0;
		String sql = "select count(*) from " + TeacherInfoTable.TABLE_NAME
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
	public boolean batchDelete(String[] tiids) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();

		for (String tiid : tiids) {
			String sql = "delete from " + TeacherInfoTable.TABLE_NAME
					+ " where " + TeacherInfoTable.TI_ID + " = '" + tiid
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
	public int addTeacherInfo(
			TeacherInfo teacherInfo) {
		// TODO Auto-generated method stub
		int result = 0;

		String sql2 = "update " + TeacherInfoTable.TABLE_NAME + " set "
				+ TeacherInfoTable.TI_SERIALNUMBER + " = "
				+ TeacherInfoTable.TI_SERIALNUMBER + " +1 where "
				+ TeacherInfoTable.TI_SERIALNUMBER + ">="
				+ teacherInfo.getTi_serialnumber();
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

		String sql = "insert into " + TeacherInfoTable.TABLE_NAME + "("
				+ TeacherInfoTable.TI_COLLEGENAME + ","			
				+ TeacherInfoTable.TI_NAME + ","
				+ TeacherInfoTable.TI_NUMBER + ","
				+ TeacherInfoTable.TI_PROFESSIONTITLE + ","
				+ TeacherInfoTable.TI_COLLEGE + ","
				+ TeacherInfoTable.TI_SERIALNUMBER +","
				+ TeacherInfoTable.TI_ISNULL + ")values(?,?,?,?,?,?,?)";

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
			pstmt.setString(1, teacherInfo.getTi_collegename());
			pstmt.setString(2, teacherInfo.getTi_name());
			pstmt.setString(3, teacherInfo.getTi_number());
			pstmt.setString(4, teacherInfo.getTi_professiontitle());
			pstmt.setString(5, teacherInfo.getTi_college());
			pstmt.setInt(6, teacherInfo.getTi_serialnumber());
			pstmt.setInt(7, teacherInfo.getTi_isnull());
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
	public int alterTeacherInfo(
			Map<String, String> valueMap, String id) {
		// TODO Auto-generated method stub
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(TeacherInfoTable.TI_ID, id);
		int result = updateRecord(TeacherInfoTable.TABLE_NAME, valueMap,
				condition);
		return result;
	}

	@Override
	public List<TeacherInfo> getAllTeacherInfo() {
		// TODO Auto-generated method stub
		String sql = " select * from " + TeacherInfoTable.TABLE_NAME
				+ " where 1=1 " + " order by " + TeacherInfoTable.TI_ID;
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
			List<TeacherInfo> teacherInfos = new ArrayList<TeacherInfo>();
			while (resultSet.next()) {
				int ti_id = resultSet.getInt(TeacherInfoTable.TI_ID);
				String ti_collegename = resultSet.getString(TeacherInfoTable.TI_COLLEGENAME);  				
				String ti_name = resultSet.getString(TeacherInfoTable.TI_NAME); 
				String ti_number = resultSet.getString(TeacherInfoTable.TI_NUMBER); 
				String ti_professiontitle = resultSet.getString(TeacherInfoTable.TI_PROFESSIONTITLE);  	
				
				String ti_college =  resultSet.getString(TeacherInfoTable.TI_COLLEGE);
				int ti_serialnumber = resultSet.getInt(TeacherInfoTable.TI_SERIALNUMBER);
				Date ti_deadline = resultSet.getDate(TeacherInfoTable.TI_DEADLINE); 
				String ti_comments =  resultSet.getString(TeacherInfoTable.TI_COMMENTS);
				if(null == ti_comments){
					ti_comments = "";
				}
				int ti_isnull = resultSet.getInt(TeacherInfoTable.TI_ISNULL);
				TeacherInfo teacherInfo = new TeacherInfo(ti_id, ti_collegename, ti_name, ti_number, ti_professiontitle,
			             ti_college, ti_deadline, ti_serialnumber, ti_comments, ti_isnull);

				teacherInfos.add(teacherInfo);
			}
			return teacherInfos;
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

		String sql = "delete from " + TeacherInfoTable.TABLE_NAME;
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
		String sql = "delete from " + TeacherInfoTable.TABLE_NAME + " where "
				+ TeacherInfoTable.TI_COLLEGE + " = '" + college + "'" + " and "+TeacherInfoTable.TI_DEADLINE +" is null ";
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

	@Override
	public List<TeacherInfo> getTeacherInfoByCollege(String collegeName) {
		// TODO Auto-generated method stub
		String sql = " select * from "
				+ TeacherInfoTable.TABLE_NAME + " where "+ TeacherInfoTable.TI_COLLEGENAME +" = '"+collegeName+"' ";
		
		System.out.println(sql);

		List<TeacherInfo> teacherInfos = new ArrayList<TeacherInfo>();
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
				int ti_id = resultSet.getInt(TeacherInfoTable.TI_ID);
				String ti_collegename = resultSet.getString(TeacherInfoTable.TI_COLLEGENAME);  				
				String ti_name = resultSet.getString(TeacherInfoTable.TI_NAME); 
				String ti_number = resultSet.getString(TeacherInfoTable.TI_NUMBER); 
				String ti_professiontitle = resultSet.getString(TeacherInfoTable.TI_PROFESSIONTITLE);  	
				
				String ti_college =  resultSet.getString(TeacherInfoTable.TI_COLLEGE);
				int ti_serialnumber = resultSet.getInt(TeacherInfoTable.TI_SERIALNUMBER);
				Date ti_deadline = resultSet.getDate(TeacherInfoTable.TI_DEADLINE); 
				String ti_comments =  resultSet.getString(TeacherInfoTable.TI_COMMENTS);
				if(null == ti_comments){
					ti_comments = "";
				}
				int ti_isnull = resultSet.getInt(TeacherInfoTable.TI_ISNULL);
				TeacherInfo teacherInfo = new TeacherInfo(ti_id, ti_collegename, ti_name, ti_number, ti_professiontitle,
			             ti_college, ti_deadline, ti_serialnumber, ti_comments, ti_isnull);

				teacherInfos.add(teacherInfo);
			}
			return teacherInfos;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
	}
}

