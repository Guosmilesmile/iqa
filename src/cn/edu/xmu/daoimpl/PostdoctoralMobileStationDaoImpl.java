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


import cn.edu.xmu.dao.PostdoctoralMobileStationDao;
import cn.edu.xmu.entity.PostdoctoralMobileStation;
import cn.edu.xmu.table.PostdoctoralMobileStationTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;

/**
 * 表4-1-2  博士后流动站   (时点)
 * @author yue
 *
 */
public class PostdoctoralMobileStationDaoImpl extends BaseDaoImpl<PostdoctoralMobileStation> implements PostdoctoralMobileStationDao {

	@Override
	public int addPostdoctoralMobileStationRecord(PostdoctoralMobileStation pms) {
		int result = 0;
		String t_sql = "update "+PostdoctoralMobileStationTable.TABLE_NAME+" set "
				+ PostdoctoralMobileStationTable.PMS_SERIALNUMBER + " = "
				+ PostdoctoralMobileStationTable.PMS_SERIALNUMBER + " +1 where "
				+ PostdoctoralMobileStationTable.PMS_SERIALNUMBER + " >=?";
		Connection connection2 = null;
		try{
			connection2 = JdbcUtils_DBCP.getConnection();
		}catch(SQLException e1){
			e1.printStackTrace();
			}
		PreparedStatement t_pstmt = null;
		try{
			t_pstmt = connection2.prepareStatement(t_sql);
			t_pstmt.setInt(1, pms.getPms_serialnumber());
			
			result = t_pstmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
			return 0;
		}finally{
			JdbcUtils_DBCP.release(connection2, t_pstmt, null);
		}
		
		String sql = "insert into " + PostdoctoralMobileStationTable.TABLE_NAME+" ( "
				+ PostdoctoralMobileStationTable.PMS_DEPARTMENTNUMBER + ","
				+ PostdoctoralMobileStationTable.PMS_DEPARTMENTNAME + ","
				+ PostdoctoralMobileStationTable.PMS_STATIONNAME + ","
				+ PostdoctoralMobileStationTable.PMS_SERIALNUMBER + ","
				+ PostdoctoralMobileStationTable.PMS_COLLEGE + ","
				+ PostdoctoralMobileStationTable.PMS_COMMENTS + ","
				+ PostdoctoralMobileStationTable.ISNULL
				+" )values(?,?,?,?,?,?,?)";
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, pms.getPms_departmentnumber());
			pstmt .setString(2, pms.getPms_departmentname());
			pstmt.setString(3, pms.getPms_stationname());
			pstmt.setInt(4, pms.getPms_serialnumber());
			pstmt.setString(5, pms.getPms_college());
			pstmt.setString(6, pms.getPms_comments());
			pstmt.setInt(7, pms.getIsnull());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			result = -1;
			}finally{
				JdbcUtils_DBCP.release(connection, pstmt, null);
			}
		return result;
	}

	@Override
	public boolean batchDelete(String[] pmsids) throws SQLException {
		Connection connection = null;
		try
		{
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1)
		{
			e1.printStackTrace();
			return false;
		}
		Statement stmt = connection.createStatement(); 
		
		for(String pmsid:pmsids)
		{
			String sql = "delete from "+PostdoctoralMobileStationTable.TABLE_NAME
					+" where "+ PostdoctoralMobileStationTable.PMS_ID + " = '" + pmsid + "'";
			try{
				stmt.executeUpdate(sql);
			}catch(SQLException e){
				e.printStackTrace();
				return false;
			}
		}
		JdbcUtils_DBCP.release(connection, stmt, null);
		return true;
	}

	@Override
	public int alterPostdoctoralMobileStation(Map<String, String> valueMap, String id) {
		Map<String, String> connection = new HashMap<String,String>();
		connection.put(PostdoctoralMobileStationTable.PMS_ID, id);
		int result = updateRecord(PostdoctoralMobileStationTable.TABLE_NAME,valueMap, connection);
		
		return result;
	}

	@Override
	public int getPostdoctoralMobileStationCount(Map queryParams) {
		int count = 0;
		String sql = "select count(*) from " + PostdoctoralMobileStationTable.TABLE_NAME 
				+ "  where  1 = 1";
		Connection connection = null;
		try{
			connection = JdbcUtils_DBCP.getConnection();
		}catch(SQLException e1){
			e1.printStackTrace();
			return -1;
		}
		if (queryParams != null)
		{
			for (Object object : queryParams.keySet())
			{
				String key = object.toString();
				String value = queryParams.get(key).toString();
				sql += String.format(" and  %s like  '%s%%' ", key, value);
			}
			// sql += String.format(" or %s ='%s'", "college","");
		}

		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try{
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			resultSet.next();
			count = resultSet.getInt(1);
		}catch(SQLException e1){
			e1.printStackTrace();
			return -1;
		}finally{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return count;
	}

	@Override
	public List<PostdoctoralMobileStation> getPostdoctoralMobileStation(int start, int end, String sortStr,
			String orderStr, Map<String, String> params, Date deadline) {
		String sql = "select tmp.* from ( " + " select * from "
			+ PostdoctoralMobileStationTable.TABLE_NAME + " where 1=1 ";
		if(deadline != null){
			sql += String.format("and"+PostdoctoralMobileStationTable.PMS_DEADLINE+" like  '%s%%", deadline);		
		}
		if(!(params == null || params.keySet().size() == 0 )){
			for (Object object : params.keySet()) {

				String key = object.toString();
				String value = params.get(key).toString();
				sql += String.format("  and %s like  '%%%s%%' ", key, value);
			}
		}
		sql += " order by " + sortStr + " " + orderStr + " ) tmp limit "
				+ start + " ," + end;
		System.out.println(sql);
		List<PostdoctoralMobileStation> pmss = new ArrayList<PostdoctoralMobileStation>();
		Connection connection = null;
		try{
			connection = JdbcUtils_DBCP.getConnection();
		}catch(SQLException e1)
		{
			e1.printStackTrace();
		}
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try{
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			while(resultSet.next()){
				int pms_id = resultSet.getInt(PostdoctoralMobileStationTable.PMS_ID);
				String pms_departmentnumber  = resultSet.getString(PostdoctoralMobileStationTable.PMS_DEPARTMENTNUMBER);
				String pms_departmentname = resultSet.getString(PostdoctoralMobileStationTable.PMS_DEPARTMENTNAME);
				String pms_stationname = resultSet.getString(PostdoctoralMobileStationTable.PMS_STATIONNAME);
				int pms_serialnumber = resultSet.getInt(PostdoctoralMobileStationTable.PMS_SERIALNUMBER);
				Date pms_deadline = resultSet.getDate(PostdoctoralMobileStationTable.PMS_DEADLINE);
				String pms_college = resultSet.getString(PostdoctoralMobileStationTable.PMS_COLLEGE);
				String pms_comments = resultSet.getString(PostdoctoralMobileStationTable.PMS_COMMENTS);
				int isnull = resultSet.getInt(PostdoctoralMobileStationTable.ISNULL);
				
				PostdoctoralMobileStation pms = new PostdoctoralMobileStation(pms_id, pms_departmentnumber, pms_departmentname,
						pms_stationname, pms_serialnumber, pms_deadline, pms_college, pms_comments,isnull);
				pmss.add(pms);
			}
			return pmss;
		}catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}finally{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
	}

	@Override
	public int getPostdoctoralMobileStationCountByStation(Map queryParams) {
		int count = 0;
		String sql = "select count(*) from " +PostdoctoralMobileStationTable.TABLE_NAME 
				+ " where 1 = 1 and "+PostdoctoralMobileStationTable.PMS_STATIONNAME+" is not null and "+PostdoctoralMobileStationTable.PMS_STATIONNAME+" != ''";
		Connection connection = null;
		try{
			connection = JdbcUtils_DBCP.getConnection();
		}catch(SQLException e1){
			e1.printStackTrace();
			return -1;
		}
		if (queryParams != null)
		{
			for (Object object : queryParams.keySet())
			{
				String key = object.toString();
				String value = queryParams.get(key).toString();
				sql += String.format(" and  %s like  '%s%%' ", key, value);
			}
			// sql += String.format(" or %s ='%s'", "college","");
		}

		System.out.println(sql);
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try{
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			resultSet.next();
			count = resultSet.getInt(1);
		}catch(SQLException e1){
			e1.printStackTrace();
			return -1;
		}finally{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return count;
	}

	@Override
	public void deleteByCollegeandDeadline(String college, Date deadline) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();
		String sql = "delete from " + PostdoctoralMobileStationTable.TABLE_NAME
				+ " where " + PostdoctoralMobileStationTable.PMS_COLLEGE + " = '" + college + "'" +" and pms_deadline is null ";
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
	public List<PostdoctoralMobileStation> getAllPostdoctoralMobileStation() {
		String sql = " select * from " + PostdoctoralMobileStationTable.TABLE_NAME
				+ " where 1=1 " + " order by " + PostdoctoralMobileStationTable.PMS_ID;
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
			List<PostdoctoralMobileStation> pmss = new ArrayList<PostdoctoralMobileStation>();
			while (resultSet.next()) {
				int pms_id = resultSet.getInt(PostdoctoralMobileStationTable.PMS_ID);
				String pms_departmentnumber  = resultSet.getString(PostdoctoralMobileStationTable.PMS_DEPARTMENTNUMBER);
				String pms_departmentname = resultSet.getString(PostdoctoralMobileStationTable.PMS_DEPARTMENTNAME);
				String pms_stationname = resultSet.getString(PostdoctoralMobileStationTable.PMS_STATIONNAME);
				int pms_serialnumber = resultSet.getInt(PostdoctoralMobileStationTable.PMS_SERIALNUMBER);
				Date pms_deadline = resultSet.getDate(PostdoctoralMobileStationTable.PMS_DEADLINE);
				String pms_college = resultSet.getString(PostdoctoralMobileStationTable.PMS_COLLEGE);
				String pms_comments = resultSet.getString(PostdoctoralMobileStationTable.PMS_COMMENTS);
				int isnull = resultSet.getInt(PostdoctoralMobileStationTable.ISNULL);
				
				PostdoctoralMobileStation pms = new PostdoctoralMobileStation(pms_id, pms_departmentnumber, pms_departmentname,
						pms_stationname, pms_serialnumber, pms_deadline, pms_college, pms_comments,isnull);
				pmss.add(pms);
			}
			return pmss;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
	}


}
