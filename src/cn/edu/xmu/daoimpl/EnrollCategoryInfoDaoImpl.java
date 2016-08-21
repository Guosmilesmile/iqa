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

import org.apache.jasper.tagplugins.jstl.core.If;

import cn.edu.xmu.dao.EnrollCategoryInfoDao;
import cn.edu.xmu.entity.EnrollCategoryInfo;
import cn.edu.xmu.table.EnrollCategoryInfoTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;

public class EnrollCategoryInfoDaoImpl extends BaseDaoImpl<EnrollCategoryInfo> implements EnrollCategoryInfoDao{

	@Override
	public List<EnrollCategoryInfo> getEnrollCategoryInfo(int start, int end, String sortStr, String orderStr,
			Map<String, String> params, Date deadline) {
		String sql = " select tmp.* from ( " + " select * from "
				+ EnrollCategoryInfoTable.TABLE_NAME + " where 1=1 ";
		if (deadline != null) {

			sql += String.format("and "+EnrollCategoryInfoTable.ECI_DEADLINE+" like  '%s%%' ", deadline);
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

		List<EnrollCategoryInfo> ecis = new ArrayList<EnrollCategoryInfo>();
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
			   
			while(resultSet.next()){
				
				
				int eci_id = resultSet.getInt(EnrollCategoryInfoTable.ECI_ID);
				Integer eci_plannumber = resultSet.getInt(EnrollCategoryInfoTable.ECI_PLANNUMBER);
			    if (eci_plannumber == -999 )
			    	eci_plannumber = null;
			    Integer eci_enrollnumber = resultSet.getInt(EnrollCategoryInfoTable.ECI_ENROLLNUMBER);
			    if (eci_enrollnumber == -999 )
			    	eci_enrollnumber = null;
			    Integer eci_registernumber =  resultSet.getInt(EnrollCategoryInfoTable.ECI_REGISTERNUMBER); 	    
			    if (eci_registernumber == -999 )
			    	eci_registernumber = null;
			    Integer eci_indrecruitmentnumber = resultSet.getInt(EnrollCategoryInfoTable.ECI_INDRECRUITMENTNUMBER); 
			    if (eci_indrecruitmentnumber == -999 )
			    	eci_indrecruitmentnumber = null;
			    Integer eci_specialnumber = resultSet.getInt(EnrollCategoryInfoTable.ECI_SPECIALNUMBER);
			    if (eci_specialnumber == -999 )
			    	eci_specialnumber = null;
			    Integer eci_provincenumber = resultSet.getInt(EnrollCategoryInfoTable.ECI_PROVINCENUMBER);
			    if (eci_provincenumber == -999 )
			    	eci_provincenumber = null;
			    Integer eci_newspecialitynumber = resultSet.getInt(EnrollCategoryInfoTable.ECI_NEWSPECIALITYNUMBER);
			    if (eci_newspecialitynumber == -999 )
			    	eci_newspecialitynumber = null;
			    
			    String eci_college = resultSet.getString(EnrollCategoryInfoTable.ECI_COLLEGE);//填报学院
			    Date eci_deadline = resultSet.getDate(EnrollCategoryInfoTable.ECI_DEADLINE);//截止时间
			    int eci_serialnumber = resultSet.getInt(EnrollCategoryInfoTable.ECI_SERIALNUMBER);//序列号
			    String eci_comments = resultSet.getString(EnrollCategoryInfoTable.ECI_COMMENTS);//备注,用于填写审核意见
			    int isnull = resultSet.getInt(EnrollCategoryInfoTable.ISNULL);
	
				EnrollCategoryInfo eci = new EnrollCategoryInfo(eci_id, eci_plannumber, eci_enrollnumber, 
						eci_registernumber, eci_indrecruitmentnumber, eci_specialnumber, eci_provincenumber, 
						eci_newspecialitynumber, eci_serialnumber, eci_deadline, eci_college, eci_comments,
						isnull);
				ecis.add(eci);
			}
			return ecis;
		   } catch (SQLException e) {
			e.printStackTrace();
			return null;
		   }finally{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		   }
	}

	@Override
	public int getEnrollCategoryInfoCount(Map queryParams) {
		int count  = 0;
		String sql = "select count(*) from " + EnrollCategoryInfoTable.TABLE_NAME +" where 1 = 1"; 
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
		}finally{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		System.err.println(count);
		return count;
	}

	@Override
	public boolean batchDelete(String[] eciids) throws SQLException {
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
		
		for (String eciid : eciids) {
			String sql = "delete from "+EnrollCategoryInfoTable.TABLE_NAME+ " where "+EnrollCategoryInfoTable.ECI_ID + " = '"+ eciid+ "'";
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
	public int addEnrollCategoryInfoRecord(EnrollCategoryInfo eci) {
		int result = 0;
		
		String t_sql = "update " + EnrollCategoryInfoTable.TABLE_NAME + " set "
				+EnrollCategoryInfoTable.ECI_SERIALNUMBER + " = "
				+EnrollCategoryInfoTable.ECI_SERIALNUMBER + " +1 where "
				+EnrollCategoryInfoTable.ECI_SERIALNUMBER + " >=?";
		Connection connection2 = null;
		try{
			//连接池获取对象连接
			connection2 = JdbcUtils_DBCP.getConnection();
			
		}catch(SQLException e1){
			e1.printStackTrace();
		}
		PreparedStatement t_pstmt = null;
		try {
			//获取操作对象
			t_pstmt = connection2.prepareStatement(t_sql);
			t_pstmt.setInt(1, eci.getEci_serialnumber());
			
			//执行插入操作并更新
			result = t_pstmt.executeUpdate();
			
		} catch (SQLException e) {
			//事务回滚，不做插入操作
			e.printStackTrace();
			return 0;
		} finally {
			JdbcUtils_DBCP.release(connection2, t_pstmt, null);
		}

		String sql = "insert into " + EnrollCategoryInfoTable.TABLE_NAME + "("
				+ EnrollCategoryInfoTable.ECI_PLANNUMBER + "," 
				+ EnrollCategoryInfoTable.ECI_ENROLLNUMBER + "," 
				+ EnrollCategoryInfoTable.ECI_REGISTERNUMBER + ","
				+ EnrollCategoryInfoTable.ECI_INDRECRUITMENTNUMBER + ","
				+ EnrollCategoryInfoTable.ECI_SPECIALNUMBER + ","
				+ EnrollCategoryInfoTable.ECI_PROVINCENUMBER + ","
				+ EnrollCategoryInfoTable.ECI_NEWSPECIALITYNUMBER + ","
				+ EnrollCategoryInfoTable.ECI_COLLEGE + ","
				+ EnrollCategoryInfoTable.ECI_SERIALNUMBER + ","
				+ EnrollCategoryInfoTable.ECI_COMMENTS + ","
				+ EnrollCategoryInfoTable.ISNULL
				+")values(?,?,?,?,?,?,?,?,?,?,?)";

		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, eci.getEci_plannumber());
			pstmt.setInt(2, eci.getEci_enrollnumber());
			pstmt.setInt(3, eci.getEci_registernumber());
			pstmt.setInt(4, eci.getEci_indrecruitmentnumber());
			pstmt.setInt(5, eci.getEci_specialnumber());
			pstmt.setInt(6, eci.getEci_provincenumber());
			pstmt.setInt(7, eci.getEci_newspecialitynumber());
			pstmt.setString(8, eci.getEci_college());
			pstmt.setInt(9,	eci.getEci_serialnumber());
			pstmt.setString(10, eci.getEci_comments());
			pstmt.setInt(11, eci.getIsnull());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			result = -1;
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, null);
		}
		return result;
	}

	@Override
	public int alterEnrollCategoryInfo(Map<String, String> valueMap, String id) {
		Map<String, String> condition = new HashMap<String,String>();
		condition.put(EnrollCategoryInfoTable.ECI_ID, id);
		int result = updateRecord(EnrollCategoryInfoTable.TABLE_NAME, valueMap, condition);
		return result;
	}

	@Override
	public void deleteByCollegeandDeadline(String college, Date deadline) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();
		String sql = "delete from " + EnrollCategoryInfoTable.TABLE_NAME
				+ " where " + EnrollCategoryInfoTable.ECI_COLLEGE + " = '" + college + "'" +" and eci_deadline is null ";
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
	public List<EnrollCategoryInfo> getAllEnrollCategoryInfo() {
		String sql = " select * from " + EnrollCategoryInfoTable.TABLE_NAME
				+ " where 1=1 " + " order by " + EnrollCategoryInfoTable.ECI_ID;
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
			List<EnrollCategoryInfo> ecis = new ArrayList<EnrollCategoryInfo>();
			while (resultSet.next()) {
				int eci_id = resultSet.getInt(EnrollCategoryInfoTable.ECI_ID);
				Integer eci_plannumber = resultSet.getInt(EnrollCategoryInfoTable.ECI_PLANNUMBER);
			    if (eci_plannumber == -999 )
			    	eci_plannumber = null;
			    Integer eci_enrollnumber = resultSet.getInt(EnrollCategoryInfoTable.ECI_ENROLLNUMBER);
			    if (eci_enrollnumber == -999 )
			    	eci_enrollnumber = null;
			    Integer eci_registernumber =  resultSet.getInt(EnrollCategoryInfoTable.ECI_REGISTERNUMBER); 	    
			    if (eci_registernumber == -999 )
			    	eci_registernumber = null;
			    Integer eci_indrecruitmentnumber = resultSet.getInt(EnrollCategoryInfoTable.ECI_INDRECRUITMENTNUMBER); 
			    if (eci_indrecruitmentnumber == -999 )
			    	eci_indrecruitmentnumber = null;
			    Integer eci_specialnumber = resultSet.getInt(EnrollCategoryInfoTable.ECI_SPECIALNUMBER);
			    if (eci_specialnumber == -999 )
			    	eci_specialnumber = null;
			    Integer eci_provincenumber = resultSet.getInt(EnrollCategoryInfoTable.ECI_PROVINCENUMBER);
			    if (eci_provincenumber == -999 )
			    	eci_provincenumber = null;
			    Integer eci_newspecialitynumber = resultSet.getInt(EnrollCategoryInfoTable.ECI_NEWSPECIALITYNUMBER);
			    if (eci_newspecialitynumber == -999 )
			    	eci_newspecialitynumber = null;
			    
			    String eci_college = resultSet.getString(EnrollCategoryInfoTable.ECI_COLLEGE);//填报学院
			    Date eci_deadline = resultSet.getDate(EnrollCategoryInfoTable.ECI_DEADLINE);//截止时间
			    int eci_serialnumber = resultSet.getInt(EnrollCategoryInfoTable.ECI_SERIALNUMBER);//序列号
			    String eci_comments = resultSet.getString(EnrollCategoryInfoTable.ECI_COMMENTS);//备注,用于填写审核意见
			    int isnull = resultSet.getInt(EnrollCategoryInfoTable.ISNULL);
	
				EnrollCategoryInfo eci = new EnrollCategoryInfo(eci_id, eci_plannumber, eci_enrollnumber, 
						eci_registernumber, eci_indrecruitmentnumber, eci_specialnumber, eci_provincenumber, 
						eci_newspecialitynumber, eci_serialnumber, eci_deadline, eci_college, eci_comments,
						isnull);
				ecis.add(eci);
			}
			return ecis;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
	}

}
