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

import cn.edu.xmu.dao.AdvantageMajorInfoDao;
import cn.edu.xmu.entity.AdvantageMajorInfo;
import cn.edu.xmu.table.AdvantageMajorInfoTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;

/**
 * 附表4-2-3-1优势专业情况（时点）
 * @author yue
 *
 */
public class AdvantageMajorInfoDaoImpl extends BaseDaoImpl<AdvantageMajorInfo> implements AdvantageMajorInfoDao{

	@Override
	public int addAdvantageMajorInfoRecord(AdvantageMajorInfo ami) {
		int result = 0;
		String t_sql = "update " + AdvantageMajorInfoTable.TABLE_NAME + " set "
				+AdvantageMajorInfoTable.AMI_SERIALNUMBER + " = "
				+AdvantageMajorInfoTable.AMI_SERIALNUMBER + " +1 where "
				+AdvantageMajorInfoTable.AMI_SERIALNUMBER + " >=?";
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
			t_pstmt.setInt(1, ami.getAmi_serialnumber());
			
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
		String sql = "insert into " + AdvantageMajorInfoTable.TABLE_NAME + "("
				+ AdvantageMajorInfoTable.AMI_INSTITUTE + ","
				+ AdvantageMajorInfoTable.AMI_NAME + ","
				+ AdvantageMajorInfoTable.AMI_TYPE + ","
				+ AdvantageMajorInfoTable.AMI_NATIONALLEVEL + ","
				+ AdvantageMajorInfoTable.AMI_PROVINCIALLEVEL + ","
				+ AdvantageMajorInfoTable.AMI_REMARK + ","
				+ AdvantageMajorInfoTable.AMI_SERIALNUMBER + ","
				+ AdvantageMajorInfoTable.AMI_COLLEGE + ","
				+ AdvantageMajorInfoTable.AMI_COMMETNS + ","
				+ AdvantageMajorInfoTable.ISNULL
				+ ")values(?,?,?,?,?,?,?,?,?,?)";

		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, ami.getAmi_institute());
			pstmt.setString(2, ami.getAmi_name());
			pstmt.setString(3, ami.getAmi_type());
			pstmt.setInt(4, ami.getAmi_nationallevel());
			pstmt.setInt(5, ami.getAmi_provinciallevel());
			pstmt.setString(6, ami.getAmi_remark());
			pstmt.setInt(7, ami.getAmi_serialnumber());
			pstmt.setString(8, ami.getAmi_college());
			pstmt.setString(9, ami.getAmi_comments());
			pstmt.setInt(10, ami.getIsnull());
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
	public boolean batchDelete(String[] amiids) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();
		
		for(String amiid:amiids)
		{
			String sql = "delete from " + AdvantageMajorInfoTable.TABLE_NAME
					+ " where " + AdvantageMajorInfoTable.AMI_ID + " = '" +amiid + "'";
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
	public int alterAdvantageMajorInfo(Map<String, String> valueMap, String id) {
		Map<String, String> condition = new HashMap<String,String>();
		condition.put(AdvantageMajorInfoTable.AMI_ID, id);
		int result = updateRecord(AdvantageMajorInfoTable.TABLE_NAME, valueMap, condition);
		return result;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public int getAdvantageMajorInfoCount(Map queryParams) {
		int count = 0;
		//获取条件的语句
		String sql = "select count(*) from " + AdvantageMajorInfoTable.TABLE_NAME
				+" where 1 = 1";
		System.out.println(sql);
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
		return count;
	}

	@Override
	public List<AdvantageMajorInfo> getAdvantageMajorInfo(int start, int end, String sortStr, String orderStr,
			Map<String, String> params, Date deadline) {
		String sql = " select tmp.* from ( " + " select * from "
				+ AdvantageMajorInfoTable.TABLE_NAME + " where 1=1 ";
		if (deadline != null) {

			sql += String.format("and "+AdvantageMajorInfoTable.AMI_DEADLINE+" like  '%s%%' ", deadline);
		}
		if (!(params == null || params.keySet().size() == 0)) {
			for (Object object : params.keySet()) {

				String key = object.toString();
				String value = params.get(key).toString();
				sql += String.format("  and %s like  '%%%s%%' ", key, value);
			}
		}

		sql += " order by " + sortStr + " " + orderStr + " ) tmp limit "
				+ start + " ," + end;

		System.out.println(sql);
		
		List<AdvantageMajorInfo> amis = new ArrayList<AdvantageMajorInfo>();
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
			
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int ami_id = resultSet.getInt(AdvantageMajorInfoTable.AMI_ID);
				String ami_institute = resultSet.getString(AdvantageMajorInfoTable.AMI_INSTITUTE);
				String ami_name = resultSet.getString(AdvantageMajorInfoTable.AMI_NAME);
				String ami_type = resultSet.getString(AdvantageMajorInfoTable.AMI_TYPE);
				Integer ami_nationallevel = resultSet.getInt(AdvantageMajorInfoTable.AMI_NATIONALLEVEL);
				if(ami_nationallevel == -999)
					ami_nationallevel = null;
				Integer ami_provinciallevel = resultSet.getInt(AdvantageMajorInfoTable.AMI_PROVINCIALLEVEL);
				if(ami_provinciallevel == -999)
					ami_provinciallevel = null;
				String ami_remark = resultSet.getString(AdvantageMajorInfoTable.AMI_REMARK);
				int ami_serialnumber = resultSet.getInt(AdvantageMajorInfoTable.AMI_SERIALNUMBER);
				String ami_college = resultSet.getString(AdvantageMajorInfoTable.AMI_COLLEGE);//填报学院
			    Date ami_deadline = resultSet.getDate(AdvantageMajorInfoTable.AMI_DEADLINE);//截止时间    
				String ami_comments = resultSet.getString(AdvantageMajorInfoTable.AMI_COMMETNS);
				int isnull = resultSet.getInt(AdvantageMajorInfoTable.ISNULL);
				AdvantageMajorInfo ami = new AdvantageMajorInfo(ami_id, ami_institute, ami_name, 
						ami_type, ami_nationallevel, ami_provinciallevel, ami_remark, ami_serialnumber,
						ami_deadline, ami_college, ami_comments,isnull);
				amis.add(ami);
			}
			return amis;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
	}

	@Override
	public List<AdvantageMajorInfo> getAMISumorNoSum(String college, boolean flag) {
		String sqlString;
		if(flag)
		{
			sqlString = "select * from "+AdvantageMajorInfoTable.TABLE_NAME+" where "+
					AdvantageMajorInfoTable.AMI_INSTITUTE+" = \'全校\' and "+
				  AdvantageMajorInfoTable.AMI_COLLEGE+" = \'"+college+"\' and "+AdvantageMajorInfoTable.AMI_DEADLINE+
					" is null";
		}
		else {
			sqlString = "select * from "+AdvantageMajorInfoTable.TABLE_NAME+" where "+
					AdvantageMajorInfoTable.AMI_INSTITUTE+" != \'全校\' and "+
					AdvantageMajorInfoTable.AMI_COLLEGE+" = \'"+college+"\' and "+AdvantageMajorInfoTable.AMI_DEADLINE+
					" is null";
		}
		
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		List<AdvantageMajorInfo> amis = new ArrayList<AdvantageMajorInfo>();
		try {
			System.out.println(sqlString);
			pstmt = connection.prepareStatement(sqlString);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				int ami_id = resultSet.getInt(AdvantageMajorInfoTable.AMI_ID);
				String ami_institute = resultSet.getString(AdvantageMajorInfoTable.AMI_INSTITUTE);
				String ami_name = resultSet.getString(AdvantageMajorInfoTable.AMI_NAME);
				String ami_type = resultSet.getString(AdvantageMajorInfoTable.AMI_TYPE);
				Integer ami_nationallevel = resultSet.getInt(AdvantageMajorInfoTable.AMI_NATIONALLEVEL);
				if(ami_nationallevel == -999)
					ami_nationallevel = null;
				Integer ami_provinciallevel = resultSet.getInt(AdvantageMajorInfoTable.AMI_PROVINCIALLEVEL);
				if(ami_provinciallevel == -999)
					ami_provinciallevel = null;
				String ami_remark = resultSet.getString(AdvantageMajorInfoTable.AMI_REMARK);
				int ami_serialnumber = resultSet.getInt(AdvantageMajorInfoTable.AMI_SERIALNUMBER);
				String ami_college = resultSet.getString(AdvantageMajorInfoTable.AMI_COLLEGE);//填报学院
			    Date ami_deadline = resultSet.getDate(AdvantageMajorInfoTable.AMI_DEADLINE);//截止时间    
				String ami_comments = resultSet.getString(AdvantageMajorInfoTable.AMI_COMMETNS);
				int isnull = resultSet.getInt(AdvantageMajorInfoTable.ISNULL);
				AdvantageMajorInfo ami = new AdvantageMajorInfo(ami_id, ami_institute, ami_name, 
						ami_type, ami_nationallevel, ami_provinciallevel, ami_remark, ami_serialnumber,
						ami_deadline, ami_college, ami_comments,isnull);
				amis.add(ami);
			}
			return amis;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
	}

	@Override
	public void deleteByCollegeandDeadline(String college, Date deadline) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();
		String sql = "delete from " + AdvantageMajorInfoTable.TABLE_NAME
				+ " where " + AdvantageMajorInfoTable.AMI_COLLEGE + " = '" + college + "'" +" and ami_deadline is null ";
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
	public List<AdvantageMajorInfo> getAllAdvantageMajorInfo() {
		String sql = " select * from " + AdvantageMajorInfoTable.TABLE_NAME
				+ " where 1=1 " + " order by " + AdvantageMajorInfoTable.AMI_SERIALNUMBER+", "+AdvantageMajorInfoTable.AMI_ID;
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
			List<AdvantageMajorInfo> amis = new ArrayList<AdvantageMajorInfo>();
			while (resultSet.next()) {
				int ami_id = resultSet.getInt(AdvantageMajorInfoTable.AMI_ID);
				String ami_institute = resultSet.getString(AdvantageMajorInfoTable.AMI_INSTITUTE);
				String ami_name = resultSet.getString(AdvantageMajorInfoTable.AMI_NAME);
				String ami_type = resultSet.getString(AdvantageMajorInfoTable.AMI_TYPE);
				Integer ami_nationallevel = resultSet.getInt(AdvantageMajorInfoTable.AMI_NATIONALLEVEL);
				if(ami_nationallevel == -999)
					ami_nationallevel = null;
				Integer ami_provinciallevel = resultSet.getInt(AdvantageMajorInfoTable.AMI_PROVINCIALLEVEL);
				if(ami_provinciallevel == -999)
					ami_provinciallevel = null;
				String ami_remark = resultSet.getString(AdvantageMajorInfoTable.AMI_REMARK);
				int ami_serialnumber = resultSet.getInt(AdvantageMajorInfoTable.AMI_SERIALNUMBER);
				String ami_college = resultSet.getString(AdvantageMajorInfoTable.AMI_COLLEGE);//填报学院
			    Date ami_deadline = resultSet.getDate(AdvantageMajorInfoTable.AMI_DEADLINE);//截止时间    
				String ami_comments = resultSet.getString(AdvantageMajorInfoTable.AMI_COMMETNS);
				int isnull = resultSet.getInt(AdvantageMajorInfoTable.ISNULL);
				AdvantageMajorInfo ami = new AdvantageMajorInfo(ami_id, ami_institute, ami_name, 
						ami_type, ami_nationallevel, ami_provinciallevel, ami_remark, ami_serialnumber,
						ami_deadline, ami_college, ami_comments,isnull);
				amis.add(ami);
			}
			return amis;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
	}

}
