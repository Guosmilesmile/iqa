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

import cn.edu.xmu.dao.TeachersScientificAchievementsDao;
import cn.edu.xmu.entity.TeachersScientificAchievements;
import cn.edu.xmu.table.ForeignExchangeTable;
import cn.edu.xmu.table.TeachersScientificAchievementsTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;


/**
 * 3-6-3  教师最近一届科研成果奖数
 * @author chunfeng
 *
 */
public class TeachersScientificAchievementsDaoImpl extends BaseDaoImpl<TeachersScientificAchievements> 
                                                                         implements TeachersScientificAchievementsDao{

	@Override
	public List<TeachersScientificAchievements> getTeachersScientificAchievements(
			int start, int end, String sortStr, String orderStr,
			Map<String, String> params,Date deadline) {
		// TODO Auto-generated method stub
		String sql = " select tmp.* from ( " + " select * from "
				+ TeachersScientificAchievementsTable.TABLE_NAME + " where 1=1 ";
		if (deadline != null) {
			sql += String.format("and "+TeachersScientificAchievementsTable.TSA_DEADLINE+" like  '%s%%' ", deadline);
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

		List<TeachersScientificAchievements> teachersScientificAchievements = new ArrayList<TeachersScientificAchievements>();
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
				int tsa_id = resultSet.getInt(TeachersScientificAchievementsTable.TSA_ID);				
				Integer tsa_total = resultSet.getInt(TeachersScientificAchievementsTable.TSA_TOTAL);  
				if(tsa_total == -1) tsa_total = null;
				Integer tsa_nationallevel = resultSet.getInt(TeachersScientificAchievementsTable.TSA_NATIONALLEVEL); 
				if(tsa_nationallevel == -1) tsa_nationallevel = null;
				Integer tsa_provinciallevel = resultSet.getInt(TeachersScientificAchievementsTable.TSA_PROVINCIALLEVEL); 
				if(tsa_provinciallevel == -1) tsa_provinciallevel = null;
				
				String tsa_college =  resultSet.getString(TeachersScientificAchievementsTable.TSA_COLLEGE);
				int tsa_serialnumber = resultSet.getInt(TeachersScientificAchievementsTable.TSA_SERIALNUMBER);
				Date tsa_deadline = resultSet.getDate(TeachersScientificAchievementsTable.TSA_DEADLINE); 
				String tsa_comments =  resultSet.getString(TeachersScientificAchievementsTable.TSA_COMMENTS);
				if(null == tsa_comments){
					tsa_comments = "";
				}
				int tsa_isnull = resultSet.getInt(TeachersScientificAchievementsTable.TSA_ISNULL);
				TeachersScientificAchievements teachersScientificAchievement = new TeachersScientificAchievements(tsa_id,
				   tsa_total, tsa_nationallevel, tsa_provinciallevel, tsa_college, tsa_deadline, tsa_serialnumber, tsa_comments, tsa_isnull);

				teachersScientificAchievements.add(teachersScientificAchievement);
			}
			return teachersScientificAchievements;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
	}

	@Override
	public int getTeachersScientificAchievementsCount(Map queryParams) {
		// TODO Auto-generated method stub
		int count = 0;
		String sql = "select count(*) from " + TeachersScientificAchievementsTable.TABLE_NAME
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
	public boolean batchDelete(String[] tsaids) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();

		for (String tsaid : tsaids) {
			String sql = "delete from " + TeachersScientificAchievementsTable.TABLE_NAME
					+ " where " + TeachersScientificAchievementsTable.TSA_ID + " = '" + tsaid
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
	public int addTeachersScientificAchievements(
			TeachersScientificAchievements teachersScientificAchievements) {
		// TODO Auto-generated method stub
		int result = 0;

		String sql2 = "update " + TeachersScientificAchievementsTable.TABLE_NAME + " set "
				+ TeachersScientificAchievementsTable.TSA_SERIALNUMBER + " = "
				+ TeachersScientificAchievementsTable.TSA_SERIALNUMBER + " +1 where "
				+ TeachersScientificAchievementsTable.TSA_SERIALNUMBER + ">="
				+ teachersScientificAchievements.getTsa_serialnumber();
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

		String sql = "insert into " + TeachersScientificAchievementsTable.TABLE_NAME + "("
				+ TeachersScientificAchievementsTable.TSA_TOTAL + ","			
				+ TeachersScientificAchievementsTable.TSA_NATIONALLEVEL + ","
				+ TeachersScientificAchievementsTable.TSA_PROVINCIALLEVEL + ","
				+ TeachersScientificAchievementsTable.TSA_COLLEGE + ","
				+ TeachersScientificAchievementsTable.TSA_SERIALNUMBER + ","
				+ TeachersScientificAchievementsTable.TSA_ISNULL+ ")values(?,?,?,?,?,?)";

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
			pstmt.setInt(1, teachersScientificAchievements.getTsa_total());
			pstmt.setInt(2, teachersScientificAchievements.getTsa_nationallevel());
			pstmt.setInt(3, teachersScientificAchievements.getTsa_provinciallevel());
			pstmt.setString(4, teachersScientificAchievements.getTsa_college());
			pstmt.setInt(5, teachersScientificAchievements.getTsa_serialnumber());
			pstmt.setInt(6, teachersScientificAchievements.getTsa_isnull());
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
	public int alterTeachersScientificAchievements(
			Map<String, String> valueMap, String id) {
		// TODO Auto-generated method stub
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(TeachersScientificAchievementsTable.TSA_ID, id);
		int result = updateRecord(TeachersScientificAchievementsTable.TABLE_NAME, valueMap,
				condition);
		return result;
	}

	@Override
	public List<TeachersScientificAchievements> getAllTeachersScientificAchievements() {
		// TODO Auto-generated method stub
		String sql = " select * from " + TeachersScientificAchievementsTable.TABLE_NAME
				+ " where 1=1 " + " order by " + TeachersScientificAchievementsTable.TSA_ID;
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
			List<TeachersScientificAchievements> teachersScientificAchievementsList = new ArrayList<TeachersScientificAchievements>();
			while (resultSet.next()) {
				int tsa_id = resultSet.getInt(TeachersScientificAchievementsTable.TSA_ID);
				Integer tsa_total = resultSet.getInt(TeachersScientificAchievementsTable.TSA_TOTAL);  
				if(tsa_total == -1) tsa_total = null;
				Integer tsa_nationallevel = resultSet.getInt(TeachersScientificAchievementsTable.TSA_NATIONALLEVEL); 
				if(tsa_nationallevel == -1) tsa_nationallevel = null;
				Integer tsa_provinciallevel = resultSet.getInt(TeachersScientificAchievementsTable.TSA_PROVINCIALLEVEL); 
				if(tsa_provinciallevel == -1) tsa_provinciallevel = null;
				
				String tsa_college =  resultSet.getString(TeachersScientificAchievementsTable.TSA_COLLEGE);
				int tsa_serialnumber = resultSet.getInt(TeachersScientificAchievementsTable.TSA_SERIALNUMBER);
				Date tsa_deadline = resultSet.getDate(TeachersScientificAchievementsTable.TSA_DEADLINE); 
				String tsa_comments =  resultSet.getString(TeachersScientificAchievementsTable.TSA_COMMENTS);
				if(null == tsa_comments){
					tsa_comments = "";
				}
				int tsa_isnull = resultSet.getInt(TeachersScientificAchievementsTable.TSA_ISNULL);
				TeachersScientificAchievements teachersScientificAchievement = new TeachersScientificAchievements(tsa_id,
				   tsa_total, tsa_nationallevel, tsa_provinciallevel, tsa_college, tsa_deadline, tsa_serialnumber, tsa_comments, tsa_isnull);

				teachersScientificAchievementsList.add(teachersScientificAchievement);
			}
			return teachersScientificAchievementsList;
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

		String sql = "delete from " + TeachersScientificAchievementsTable.TABLE_NAME;
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
		String sql = "delete from " + TeachersScientificAchievementsTable.TABLE_NAME + " where "
				+ TeachersScientificAchievementsTable.TSA_COLLEGE + " = '" + college + "'" + " and "+TeachersScientificAchievementsTable.TSA_DEADLINE +" is null ";
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
