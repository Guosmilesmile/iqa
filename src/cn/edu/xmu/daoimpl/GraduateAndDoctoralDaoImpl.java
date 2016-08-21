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

import cn.edu.xmu.dao.GraduateAndDoctoralDao;
import cn.edu.xmu.entity.GraduateAndDoctoral;
import cn.edu.xmu.table.GraduateAndDoctoralTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;

public class GraduateAndDoctoralDaoImpl extends BaseDaoImpl<GraduateAndDoctoral> implements GraduateAndDoctoralDao{

	@Override
	public int addGraduateAndDoctoralRecord(GraduateAndDoctoral gd) {
		int result = 0;
		String t_sql = "update "+GraduateAndDoctoralTable.TABLE_NAME+" set "
				+ GraduateAndDoctoralTable.GD_SERIALNUMBER + " = "
				+ GraduateAndDoctoralTable.GD_SERIALNUMBER + " +1 where "
				+ GraduateAndDoctoralTable.GD_SERIALNUMBER + " >=?";
		Connection connection2 = null;
		try{
			connection2 = JdbcUtils_DBCP.getConnection();
		}catch(SQLException e1){
			e1.printStackTrace();
			}
		PreparedStatement t_pstmt = null;
		try{
			t_pstmt = connection2.prepareStatement(t_sql);
			t_pstmt.setInt(1, gd.getGd_serialnumber());
			
			result = t_pstmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
			return 0;
		}finally{
			JdbcUtils_DBCP.release(connection2, t_pstmt, null);
		}
		
		String sql = "insert into " + GraduateAndDoctoralTable.TABLE_NAME+" ( "
				+ GraduateAndDoctoralTable.GD_NAME + ","
				+ GraduateAndDoctoralTable.GD_CODE + ","
				+ GraduateAndDoctoralTable.GD_DEPARTMENTNAME + ","
				+ GraduateAndDoctoralTable.GD_DEPARTMENTNUMBER + ","
				+ GraduateAndDoctoralTable.GD_TYPE + ","
				+ GraduateAndDoctoralTable.GD_SERIALNUMBER + ","
				+ GraduateAndDoctoralTable.GD_COLLEGE + ","
				+ GraduateAndDoctoralTable.GD_COMMENTS + ","
				+ GraduateAndDoctoralTable.ISNULL
				+" )values(?,?,?,?,?,?,?,?,?)";
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, gd.getGd_name());
			pstmt.setString(2, gd.getGd_code());
			pstmt .setString(3,gd.getGd_departmentname());
			pstmt.setString(4,gd.getGd_departmentnumber());
			pstmt.setString(5, gd.getGd_type());
			pstmt.setInt(6, gd.getGd_serialnumber());
			pstmt.setString(7, gd.getGd_college());
			pstmt.setString(8, gd.getGd_comments());
			pstmt.setInt(9, gd.getIsnull());
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
	public boolean batchDelete(String[] gdids) throws SQLException {
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
		
		for (String gdid : gdids) {
			String sql = "delete from "+GraduateAndDoctoralTable.TABLE_NAME+ " where "+GraduateAndDoctoralTable.GD_ID + " = '"+ gdid+ "'";
			try {
				stmt.executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
//		stmt.close();
//		connection.close();
		JdbcUtils_DBCP.release(connection, stmt, null);
		return true;
	}

	@Override
	public int alterGraduateAndDoctoral(Map<String, String> valueMap, String id) {
		Map<String,String> condition = new HashMap<String,String>();
		condition.put(GraduateAndDoctoralTable.GD_ID, id);
		int result = updateRecord(GraduateAndDoctoralTable.TABLE_NAME, valueMap, condition);
		return result;
	}

	@Override
	public int getGraduateAndDoctoralCount(Map queryParams) {
		int count  = 0;
		String sql = "select count(*) from " + GraduateAndDoctoralTable.TABLE_NAME +" where 1 = 1"; 
		Connection connection = null;
		try
		{
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1)
		{
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
		
		try {
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			resultSet.next();
			count = resultSet.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}finally{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		System.err.println(count);
		return count;
	}

	@Override
	public List<GraduateAndDoctoral> getGraduateAndDoctoral(int start, int end, String sortStr, String orderStr,
			Map<String, String> params, Date deadline) {
		String sql = " select tmp.* from ( " + " select * from "
				+ GraduateAndDoctoralTable.TABLE_NAME + " where 1=1 ";
		if (deadline != null) {
			sql += String.format("and "+GraduateAndDoctoralTable.GD_DEADLINE+" like  '%s%%' ", deadline);
		}
		if (!(params == null || params.keySet().size() == 0)) {
			for (Object object : params.keySet()) {

				String key = object.toString();
				String value = params.get(key).toString();
				sql += String.format(" and %s like  '%%%s%%' ", key, value);
			}
		}

		sql += " order by " + sortStr + " " + orderStr + " ) tmp limit "
				+ start + " ," + end;

		System.out.println(sql);

		List<GraduateAndDoctoral> gds = new ArrayList<GraduateAndDoctoral>();
		Connection connection = null;
		try
		{
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1)
		{
			e1.printStackTrace();
	
		}
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		try {
			System.out.println(sql);
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			String temp = resultSet.toString();
			System.out.println(temp);
			while(resultSet.next()){
				
				
				int gd_id = resultSet.getInt(GraduateAndDoctoralTable.GD_ID);
				String gd_name = resultSet.getString(GraduateAndDoctoralTable.GD_NAME); //名称
			    String gd_code = resultSet.getString(GraduateAndDoctoralTable.GD_CODE);  //代码		   
			    String gd_departmentname = resultSet.getString(GraduateAndDoctoralTable.GD_DEPARTMENTNAME);; //单位名称			    
			    String gd_departmentnumber =  resultSet.getString(GraduateAndDoctoralTable.GD_DEPARTMENTNUMBER); //单位号		    
			    String gd_type = resultSet.getString(GraduateAndDoctoralTable.GD_TYPE); //类型
			    String gd_college = resultSet.getString(GraduateAndDoctoralTable.GD_COLLEGE);//填报学院
			    Date gd_deadline = resultSet.getDate(GraduateAndDoctoralTable.GD_DEADLINE);//截止时间
			    int gd_serialnumber = resultSet.getInt(GraduateAndDoctoralTable.GD_SERIALNUMBER);//序列号
			    String gd_comments = resultSet.getString(GraduateAndDoctoralTable.GD_COMMENTS);//备注,用于填写审核意见
			    int isnull = resultSet.getInt(GraduateAndDoctoralTable.ISNULL);
			    GraduateAndDoctoral gd = new GraduateAndDoctoral(gd_id, gd_name, gd_code, gd_departmentname, 
			    		gd_departmentnumber, gd_type, gd_serialnumber, gd_deadline, gd_college, gd_comments,isnull);		
			    gds.add(gd);
				}
				return gds;
		   } catch (SQLException e) {
			e.printStackTrace();
			return null;
		   }finally{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		   }
	}

	@Override
	public void deleteByCollegeandDeadline(String college, Date deadline) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();
		String sql = "delete from " + GraduateAndDoctoralTable.TABLE_NAME
				+ " where " + GraduateAndDoctoralTable.GD_COLLEGE + " = '" + college + "'" +" and gd_deadline is null ";
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
	public List<GraduateAndDoctoral> getAllGraduateAndDoctoral() {
		String sql = " select * from " + GraduateAndDoctoralTable.TABLE_NAME
				+ " where 1=1 " + " order by " + GraduateAndDoctoralTable.GD_ID;
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
			List<GraduateAndDoctoral> gds = new ArrayList<GraduateAndDoctoral>();
			while (resultSet.next()) {
				int gd_id = resultSet.getInt(GraduateAndDoctoralTable.GD_ID);
				String gd_name = resultSet.getString(GraduateAndDoctoralTable.GD_NAME); //名称
			    String gd_code = resultSet.getString(GraduateAndDoctoralTable.GD_CODE);  //代码		   
			    String gd_departmentname = resultSet.getString(GraduateAndDoctoralTable.GD_DEPARTMENTNAME);; //单位名称			    
			    String gd_departmentnumber =  resultSet.getString(GraduateAndDoctoralTable.GD_DEPARTMENTNUMBER); //单位号		    
			    String gd_type = resultSet.getString(GraduateAndDoctoralTable.GD_TYPE); //类型
			    String gd_college = resultSet.getString(GraduateAndDoctoralTable.GD_COLLEGE);//填报学院
			    Date gd_deadline = resultSet.getDate(GraduateAndDoctoralTable.GD_DEADLINE);//截止时间
			    int gd_serialnumber = resultSet.getInt(GraduateAndDoctoralTable.GD_SERIALNUMBER);//序列号
			    String gd_comments = resultSet.getString(GraduateAndDoctoralTable.GD_COMMENTS);//备注,用于填写审核意见
			    int isnull = resultSet.getInt(GraduateAndDoctoralTable.ISNULL);
			    GraduateAndDoctoral gd = new GraduateAndDoctoral(gd_id, gd_name, gd_code, gd_departmentname,
			    		gd_departmentnumber, gd_type, gd_serialnumber, gd_deadline, gd_college, gd_comments,isnull);		
			    gds.add(gd);
				}
				return gds;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
	}

}
