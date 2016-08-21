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

import cn.edu.xmu.dao.TeacherPublishThesisDao;
import cn.edu.xmu.entity.TeacherPublishThesis;
import cn.edu.xmu.table.ForeignExchangeTable;
import cn.edu.xmu.table.TeacherPublishThesisTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;


/**
 * 表3-6-4  教师发表论文数 (自然年)
 * @author chunfeng
 *
 */
public class TeacherPublishThesisDaoImpl extends BaseDaoImpl<TeacherPublishThesis> implements TeacherPublishThesisDao{

	@Override
	public List<TeacherPublishThesis> getTeacherPublishThesis(int start,
			int end, String sortStr, String orderStr,
			Map<String, String> params, Date deadline) {
		// TODO Auto-generated method stub
		String sql = " select tmp.* from ( " + " select * from "
				+ TeacherPublishThesisTable.TABLE_NAME + " where 1=1 ";
		if (deadline != null) {
			sql += String.format("and "+TeacherPublishThesisTable.TPT_DEADLINE+" like  '%s%%' ", deadline);
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

		List<TeacherPublishThesis> teacherPublishThesises = new ArrayList<TeacherPublishThesis>();
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
				int tpt_id = resultSet.getInt(TeacherPublishThesisTable.TPT_ID);
				Integer tpt_total = resultSet.getInt(TeacherPublishThesisTable.TPT_TOTAL);  
				if(tpt_total == -1) tpt_total = null;
				Integer tpt_sci = resultSet.getInt(TeacherPublishThesisTable.TPT_SCI);
				if(tpt_sci == -1) tpt_sci = null;
				Integer tpt_ssci = resultSet.getInt(TeacherPublishThesisTable.TPT_SSCI);
				if(tpt_ssci == -1) tpt_ssci = null;
				Integer tpt_ei = resultSet.getInt(TeacherPublishThesisTable.TPT_EI); 
				if(tpt_ei == -1) tpt_ei = null;
				Integer tpt_istp = resultSet.getInt(TeacherPublishThesisTable.TPT_ISTP); 
				if(tpt_istp == -1) tpt_istp = null;
				Integer tpt_domesic = resultSet.getInt(TeacherPublishThesisTable.TPT_DOMESIC); 
				if(tpt_domesic == -1) tpt_domesic = null;
				
				String tpt_college =  resultSet.getString(TeacherPublishThesisTable.TPT_COLLEGE);
				int tpt_serialnumber = resultSet.getInt(TeacherPublishThesisTable.TPT_SERIALNUMBER);
				Date tpt_deadline = resultSet.getDate(TeacherPublishThesisTable.TPT_DEADLINE); 
				String tpt_comments =  resultSet.getString(TeacherPublishThesisTable.TPT_COMMENTS);
				if(null == tpt_comments){
					tpt_comments = "";
				}
				Integer tpt_isnull = resultSet.getInt(TeacherPublishThesisTable.TPT_ISNULL);
				TeacherPublishThesis teacherPublishThesis = new TeacherPublishThesis(tpt_id,
				   tpt_total, tpt_sci, tpt_ssci, tpt_ei, tpt_istp, tpt_domesic, tpt_college, tpt_deadline, tpt_serialnumber, tpt_comments, tpt_isnull);

				teacherPublishThesises.add(teacherPublishThesis);
			}
			return teacherPublishThesises;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
	}

	@Override
	public int getTeacherPublishThesisCount(Map queryParams) {
		// TODO Auto-generated method stub
		int count = 0;
		String sql = "select count(*) from " + TeacherPublishThesisTable.TABLE_NAME
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
	public boolean batchDelete(String[] tptids) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();

		for (String tptid : tptids) {
			String sql = "delete from " + TeacherPublishThesisTable.TABLE_NAME
					+ " where " + TeacherPublishThesisTable.TPT_ID + " = '" + tptid
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
	public int addTeacherPublishThesis(TeacherPublishThesis teacherPublishThesis) {
		// TODO Auto-generated method stub
		int result = 0;

		String sql2 = "update " + TeacherPublishThesisTable.TABLE_NAME + " set "
				+ TeacherPublishThesisTable.TPT_SERIALNUMBER + " = "
				+ TeacherPublishThesisTable.TPT_SERIALNUMBER + " +1 where "
				+ TeacherPublishThesisTable.TPT_SERIALNUMBER + ">="
				+ teacherPublishThesis.getTpt_serialnumber();
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

		String sql = "insert into " + TeacherPublishThesisTable.TABLE_NAME + "("
				+ TeacherPublishThesisTable.TPT_TOTAL + ","			
				+ TeacherPublishThesisTable.TPT_SCI + ","
				+ TeacherPublishThesisTable.TPT_SSCI + ","
				+ TeacherPublishThesisTable.TPT_EI + ","
				+ TeacherPublishThesisTable.TPT_ISTP + ","
				+ TeacherPublishThesisTable.TPT_DOMESIC + ","			
				+ TeacherPublishThesisTable.TPT_COLLEGE + ","
				+ TeacherPublishThesisTable.TPT_SERIALNUMBER + ","
				+ TeacherPublishThesisTable.TPT_ISNULL+ ")values(?,?,?,?,?,?,?,?,?)";

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
			pstmt.setInt(1, teacherPublishThesis.getTpt_total());
			pstmt.setInt(2, teacherPublishThesis.getTpt_sci());
			pstmt.setInt(3, teacherPublishThesis.getTpt_ssci());
			pstmt.setInt(4, teacherPublishThesis.getTpt_ei());
			pstmt.setInt(5, teacherPublishThesis.getTpt_istp());
			pstmt.setInt(6, teacherPublishThesis.getTpt_domesic());
			pstmt.setString(7, teacherPublishThesis.getTpt_college());
			pstmt.setInt(8, teacherPublishThesis.getTpt_serialnumber());
			pstmt.setInt(9, teacherPublishThesis.getTpt_isnull());
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
	public int alterTeacherPublishThesis(Map<String, String> valueMap, String id) {
		// TODO Auto-generated method stub
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(TeacherPublishThesisTable.TPT_ID, id);
		int result = updateRecord(TeacherPublishThesisTable.TABLE_NAME, valueMap,
				condition);
		return result;
	}

	@Override
	public List<TeacherPublishThesis> getAllTeacherPublishThesis() {
		// TODO Auto-generated method stub
		String sql = " select * from " + TeacherPublishThesisTable.TABLE_NAME
				+ " where 1=1 " + " order by " + TeacherPublishThesisTable.TPT_ID;
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
			List<TeacherPublishThesis> teacherPublishThesisList = new ArrayList<TeacherPublishThesis>();
			while (resultSet.next()) {
				int tpt_id = resultSet.getInt(TeacherPublishThesisTable.TPT_ID);
				Integer tpt_total = resultSet.getInt(TeacherPublishThesisTable.TPT_TOTAL);  
				if(tpt_total == -1) tpt_total = null;
				Integer tpt_sci = resultSet.getInt(TeacherPublishThesisTable.TPT_SCI);
				if(tpt_sci == -1) tpt_sci = null;
				Integer tpt_ssci = resultSet.getInt(TeacherPublishThesisTable.TPT_SSCI);
				if(tpt_ssci == -1) tpt_ssci = null;
				Integer tpt_ei = resultSet.getInt(TeacherPublishThesisTable.TPT_EI); 
				if(tpt_ei == -1) tpt_ei = null;
				Integer tpt_istp = resultSet.getInt(TeacherPublishThesisTable.TPT_ISTP); 
				if(tpt_istp == -1) tpt_istp = null;
				Integer tpt_domesic = resultSet.getInt(TeacherPublishThesisTable.TPT_DOMESIC); 
				if(tpt_domesic == -1) tpt_domesic = null;
				
				String tpt_college =  resultSet.getString(TeacherPublishThesisTable.TPT_COLLEGE);
				int tpt_serialnumber = resultSet.getInt(TeacherPublishThesisTable.TPT_SERIALNUMBER);
				Date tpt_deadline = resultSet.getDate(TeacherPublishThesisTable.TPT_DEADLINE); 
				String tpt_comments =  resultSet.getString(TeacherPublishThesisTable.TPT_COMMENTS);
				if(null == tpt_comments){
					tpt_comments = "";
				}
				Integer tpt_isnull = resultSet.getInt(TeacherPublishThesisTable.TPT_ISNULL);
				TeacherPublishThesis teacherPublishThesis = new TeacherPublishThesis(tpt_id,
				   tpt_total, tpt_sci, tpt_ssci, tpt_ei, tpt_istp, tpt_domesic, tpt_college, tpt_deadline, tpt_serialnumber, tpt_comments, tpt_isnull);

				teacherPublishThesisList.add(teacherPublishThesis);
			}
			return teacherPublishThesisList;
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

		String sql = "delete from " + TeacherPublishThesisTable.TABLE_NAME;
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
		String sql = "delete from " + TeacherPublishThesisTable.TABLE_NAME + " where "
				+ TeacherPublishThesisTable.TPT_COLLEGE + " = '" + college + "'" + " and "+TeacherPublishThesisTable.TPT_DEADLINE +" is null ";
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
