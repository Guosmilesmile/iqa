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

import cn.edu.xmu.dao.TeacherPublishWritingDao;
import cn.edu.xmu.entity.TeacherPublishWriting;
import cn.edu.xmu.table.ForeignExchangeTable;
import cn.edu.xmu.table.TeacherPublishWritingTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;


/**
 * 表3-6-5  教师出版著作（自然年）
 * @author chunfeng
 *
 */
public class TeacherPublishWritingDaoImpl extends BaseDaoImpl<TeacherPublishWriting> implements TeacherPublishWritingDao{

	@Override
	public List<TeacherPublishWriting> getTeacherPublishWriting(int start,
			int end, String sortStr, String orderStr,
			Map<String, String> params,Date deadline) {
		// TODO Auto-generated method stub
		String sql = " select tmp.* from ( " + " select * from "
				+ TeacherPublishWritingTable.TABLE_NAME + " where 1=1 ";
		if (deadline != null) {
			sql += String.format("and "+TeacherPublishWritingTable.TPW_DEADLINE+" like  '%s%%' ", deadline);
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

		List<TeacherPublishWriting> teacherPublishWritings = new ArrayList<TeacherPublishWriting>();
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
				int tpw_id = resultSet.getInt(TeacherPublishWritingTable.TPW_ID);
				Integer tpw_total = resultSet.getInt(TeacherPublishWritingTable.TPW_TOTAL); 
				if(tpw_total == -1) tpw_total = null; 
				Integer tpw_monograph = resultSet.getInt(TeacherPublishWritingTable.TPW_MONOGRAPH); 
				if(tpw_monograph == -1) tpw_monograph = null; 
				Integer tpw_translation = resultSet.getInt(TeacherPublishWritingTable.TPW_TRANSLATION); 
				if(tpw_translation == -1) tpw_translation = null; 
				Integer tpw_compile = resultSet.getInt(TeacherPublishWritingTable.TPW_COMPILE); 
				if(tpw_compile == -1) tpw_compile = null; 
				Integer tpw_other = resultSet.getInt(TeacherPublishWritingTable.TPW_OTHER); 
				if(tpw_other == -1) tpw_other = null; 
				
				String tpw_college =  resultSet.getString(TeacherPublishWritingTable.TPW_COLLEGE);
				int tpw_serialnumber = resultSet.getInt(TeacherPublishWritingTable.TPW_SERIALNUMBER);
				Date tpw_deadline = resultSet.getDate(TeacherPublishWritingTable.TPW_DEADLINE); 
				String tpw_comments =  resultSet.getString(TeacherPublishWritingTable.TPW_COMMENTS);
				if(null == tpw_comments){
					tpw_comments = "";
				}
				Integer tpw_isnull = resultSet.getInt(TeacherPublishWritingTable.TPW_ISNULL);
				TeacherPublishWriting teacherPublishWriting = new TeacherPublishWriting(tpw_id,
				   tpw_total, tpw_monograph, tpw_translation, tpw_compile, tpw_other, tpw_college, 
				   tpw_deadline, tpw_serialnumber, tpw_comments, tpw_isnull);

				teacherPublishWritings.add(teacherPublishWriting);
			}
			return teacherPublishWritings;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
	}

	@Override
	public int getTeacherPublishWritingCount(Map queryParams) {
		// TODO Auto-generated method stub
		int count = 0;
		String sql = "select count(*) from " + TeacherPublishWritingTable.TABLE_NAME
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
	public boolean batchDelete(String[] tpwids) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();

		for (String tpwid : tpwids) {
			String sql = "delete from " + TeacherPublishWritingTable.TABLE_NAME
					+ " where " + TeacherPublishWritingTable.TPW_ID + " = '" + tpwid
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
	public int addTeacherPublishWriting(TeacherPublishWriting teacherPublishWriting) {
		// TODO Auto-generated method stub
		int result = 0;

		String sql2 = "update " + TeacherPublishWritingTable.TABLE_NAME + " set "
				+ TeacherPublishWritingTable.TPW_SERIALNUMBER + " = "
				+ TeacherPublishWritingTable.TPW_SERIALNUMBER + " +1 where "
				+ TeacherPublishWritingTable.TPW_SERIALNUMBER + ">="
				+ teacherPublishWriting.getTpw_serialnumber();
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

		String sql = "insert into " + TeacherPublishWritingTable.TABLE_NAME + "("
				+ TeacherPublishWritingTable.TPW_TOTAL + ","			
				+ TeacherPublishWritingTable.TPW_MONOGRAPH + ","
				+ TeacherPublishWritingTable.TPW_TRANSLATION + ","
				+ TeacherPublishWritingTable.TPW_COMPILE + ","
				+ TeacherPublishWritingTable.TPW_OTHER + ","
				+ TeacherPublishWritingTable.TPW_COLLEGE + ","
				+ TeacherPublishWritingTable.TPW_SERIALNUMBER +","
				+ TeacherPublishWritingTable.TPW_ISNULL + ")values(?,?,?,?,?,?,?,?)";

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
			pstmt.setInt(1, teacherPublishWriting.getTpw_total());
			pstmt.setInt(2, teacherPublishWriting.getTpw_monograph());
			pstmt.setInt(3, teacherPublishWriting.getTpw_translation());
			pstmt.setInt(4, teacherPublishWriting.getTpw_compile());
			pstmt.setInt(5, teacherPublishWriting.getTpw_other());
			pstmt.setString(6, teacherPublishWriting.getTpw_college());
			pstmt.setInt(7, teacherPublishWriting.getTpw_serialnumber());
			pstmt.setInt(8, teacherPublishWriting.getTpw_isnull());
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
	public int alterTeacherPublishWriting(Map<String, String> valueMap, String id) {
		// TODO Auto-generated method stub
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(TeacherPublishWritingTable.TPW_ID, id);
		int result = updateRecord(TeacherPublishWritingTable.TABLE_NAME, valueMap,
				condition);
		return result;
	}

	@Override
	public List<TeacherPublishWriting> getAllTeacherPublishWriting() {
		// TODO Auto-generated method stub
		String sql = " select * from " + TeacherPublishWritingTable.TABLE_NAME
				+ " where 1=1 " + " order by " + TeacherPublishWritingTable.TPW_ID;
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
			List<TeacherPublishWriting> teacherPublishWritingList = new ArrayList<TeacherPublishWriting>();
			while (resultSet.next()) {
				int tpw_id = resultSet.getInt(TeacherPublishWritingTable.TPW_ID);
				Integer tpw_total = resultSet.getInt(TeacherPublishWritingTable.TPW_TOTAL); 
				if(tpw_total == -1) tpw_total = null; 
				Integer tpw_monograph = resultSet.getInt(TeacherPublishWritingTable.TPW_MONOGRAPH); 
				if(tpw_monograph == -1) tpw_monograph = null; 
				Integer tpw_translation = resultSet.getInt(TeacherPublishWritingTable.TPW_TRANSLATION); 
				if(tpw_translation == -1) tpw_translation = null; 
				Integer tpw_compile = resultSet.getInt(TeacherPublishWritingTable.TPW_COMPILE); 
				if(tpw_compile == -1) tpw_compile = null; 
				Integer tpw_other = resultSet.getInt(TeacherPublishWritingTable.TPW_OTHER); 
				if(tpw_other == -1) tpw_other = null; 
				
				String tpw_college =  resultSet.getString(TeacherPublishWritingTable.TPW_COLLEGE);
				int tpw_serialnumber = resultSet.getInt(TeacherPublishWritingTable.TPW_SERIALNUMBER);
				Date tpw_deadline = resultSet.getDate(TeacherPublishWritingTable.TPW_DEADLINE); 
				String tpw_comments =  resultSet.getString(TeacherPublishWritingTable.TPW_COMMENTS);
				if(null == tpw_comments){
					tpw_comments = "";
				}
				Integer tpw_isnull = resultSet.getInt(TeacherPublishWritingTable.TPW_ISNULL);
				TeacherPublishWriting teacherPublishWriting = new TeacherPublishWriting(tpw_id,
				   tpw_total, tpw_monograph, tpw_translation, tpw_compile, tpw_other, tpw_college, 
				   tpw_deadline, tpw_serialnumber, tpw_comments, tpw_isnull);
				teacherPublishWritingList.add(teacherPublishWriting);
			}
			return teacherPublishWritingList;
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

		String sql = "delete from " + TeacherPublishWritingTable.TABLE_NAME;
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
		String sql = "delete from " + TeacherPublishWritingTable.TABLE_NAME + " where "
				+ TeacherPublishWritingTable.TPW_COLLEGE + " = '" + college + "'" + " and "+TeacherPublishWritingTable.TPW_DEADLINE +" is null ";
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
