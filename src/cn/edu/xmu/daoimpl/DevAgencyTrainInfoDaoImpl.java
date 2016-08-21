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

import cn.edu.xmu.dao.DevAgencyTrainInfoDao;
import cn.edu.xmu.daoimpl.BaseDaoImpl;
import cn.edu.xmu.entity.DevAgencyTrainInfo;
import cn.edu.xmu.table.DevAgencyTrainInfoTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;

/**
 * 附表3-5-1-1教师教学发展机构培训情况（学年）
 * @author yue
 *
 */
public class DevAgencyTrainInfoDaoImpl extends BaseDaoImpl<DevAgencyTrainInfo> implements DevAgencyTrainInfoDao{

	@Override
	public int addDevAgencyTrainInfoRecord(DevAgencyTrainInfo dati) {
		int result = 0;
		String t_sql = "update " + DevAgencyTrainInfoTable.TABLE_NAME + " set "
				+DevAgencyTrainInfoTable.DATI_SERIALNUMBER + " = "
				+DevAgencyTrainInfoTable.DATI_SERIALNUMBER + " +1 where "
				+DevAgencyTrainInfoTable.DATI_SERIALNUMBER + " >=?";
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
			t_pstmt.setInt(1, dati.getDati_serialnumber());
			
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
		String sql = "insert into " + DevAgencyTrainInfoTable.TABLE_NAME + "("
				+ DevAgencyTrainInfoTable.DATI_NAME +  ","
				+ DevAgencyTrainInfoTable.DATI_DEPARTMENTNAME + ","
				+ DevAgencyTrainInfoTable.DATI_DEPARTMENTNUMBER + ","
				+ DevAgencyTrainInfoTable.DATI_WORKPLAN + ","
				+ DevAgencyTrainInfoTable.DATI_TRAINTIMES + ","
				+ DevAgencyTrainInfoTable.DATI_TRAINTRIPS + ","
				+ DevAgencyTrainInfoTable.DATI_SERIALNUMBER + ","
				+ DevAgencyTrainInfoTable.DATI_COLLEGE + ","
				+ DevAgencyTrainInfoTable.DATI_COMMENTS + ","
				+ DevAgencyTrainInfoTable.ISNULL
				+ ")values(?,?,?,?,?,?,?,?,?,?)";

		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, dati.getDati_name());
			pstmt.setString(2, dati.getDati_departmentname());
			pstmt.setString(3, dati.getDati_departmentnumber());
			pstmt.setString(4, dati.getDati_workplan());
			pstmt.setInt(5, dati.getDati_traintimes());
			pstmt.setInt(6, dati.getDati_traintrips());
			pstmt.setInt(7, dati.getDati_serialnumber());
			pstmt.setString(8, dati.getDati_college());
			pstmt.setString(9, dati.getDati_comments());
			pstmt.setInt(10, dati.getIsnull());
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
	public boolean batchDelete(String[] datiids) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();
		
		for(String datiid:datiids)
		{
			String sql = "delete from " + DevAgencyTrainInfoTable.TABLE_NAME
					+ " where " + DevAgencyTrainInfoTable.DATI_ID + " = '" +datiid + "'";
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
	public int alterDevAgencyTrainInfo(Map<String, String> valueMap, String id) {
		Map<String, String> condition = new HashMap<String,String>();
		condition.put(DevAgencyTrainInfoTable.DATI_ID, id);
		int result = updateRecord(DevAgencyTrainInfoTable.TABLE_NAME, valueMap, condition);
		return result;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public int getDevAgencyTrainInfoCount(Map queryParams) {
		int count = 0;
		//获取条件的语句
		String sql = "select count(*) from " + DevAgencyTrainInfoTable.TABLE_NAME
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
	public List<DevAgencyTrainInfo> getDevAgencyTrainInfo(int start, int end, String sortStr, String orderStr,
			Map<String, String> params, Date deadline) {
		String sql = " select tmp.* from ( " + " select * from "
				+ DevAgencyTrainInfoTable.TABLE_NAME + " where 1=1 ";
		if (deadline != null) {

			sql += String.format("and "+DevAgencyTrainInfoTable.DATI_DEADLINE+" like  '%s%%' ", deadline);
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
		
		List<DevAgencyTrainInfo> datis = new ArrayList<DevAgencyTrainInfo>();
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
				int dati_id = resultSet.getInt(DevAgencyTrainInfoTable.DATI_ID);
				String dati_name = resultSet.getString(DevAgencyTrainInfoTable.DATI_NAME);
				String dati_departmentname = resultSet.getString(DevAgencyTrainInfoTable.DATI_DEPARTMENTNAME);
				String dati_departmentnumber = resultSet.getString(DevAgencyTrainInfoTable.DATI_DEPARTMENTNUMBER);
				String dati_workplan = resultSet.getString(DevAgencyTrainInfoTable.DATI_WORKPLAN);

				Integer dati_traintimes = resultSet.getInt(DevAgencyTrainInfoTable.DATI_TRAINTIMES);
				if(dati_traintimes == -999)
					dati_traintimes = null;
				Integer dati_traintrips = resultSet.getInt(DevAgencyTrainInfoTable.DATI_TRAINTRIPS);
				if(dati_traintrips == -999)
					dati_traintrips = null;
				int dati_serialnumber = resultSet.getInt(DevAgencyTrainInfoTable.DATI_SERIALNUMBER);
				String dati_college = resultSet.getString(DevAgencyTrainInfoTable.DATI_COLLEGE);//填报学院
			    Date dati_deadline = resultSet.getDate(DevAgencyTrainInfoTable.DATI_DEADLINE);//截止时间    
				String dati_comments = resultSet.getString(DevAgencyTrainInfoTable.DATI_COMMENTS);
				int isnull = resultSet.getInt(DevAgencyTrainInfoTable.ISNULL);
				
				DevAgencyTrainInfo dati = new DevAgencyTrainInfo(dati_id, dati_name, dati_departmentname, dati_departmentnumber,
						dati_workplan, dati_traintimes, dati_traintrips, dati_serialnumber, dati_deadline, 
						dati_college, dati_comments,isnull);
				datis.add(dati);
			}
			return datis;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
	}

	@Override
	public void deleteByCollegeandDeadline(String college, Date deadline) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();
		String sql = "delete from " + DevAgencyTrainInfoTable.TABLE_NAME
				+ " where " + DevAgencyTrainInfoTable.DATI_COLLEGE + " = '" + college + "'" +" and dati_deadline is null ";
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
	public List<DevAgencyTrainInfo> getAllDevAgencyTrainInfo() {
		String sql = " select * from " + DevAgencyTrainInfoTable.TABLE_NAME
				+ " where 1=1 " + " order by " + DevAgencyTrainInfoTable.DATI_ID;
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
			List<DevAgencyTrainInfo> datis = new ArrayList<DevAgencyTrainInfo>();
			while (resultSet.next()) {
				int dati_id = resultSet.getInt(DevAgencyTrainInfoTable.DATI_ID);
				String dati_name = resultSet.getString(DevAgencyTrainInfoTable.DATI_NAME);
				String dati_departmentname = resultSet.getString(DevAgencyTrainInfoTable.DATI_DEPARTMENTNAME);
				String dati_departmentnumber = resultSet.getString(DevAgencyTrainInfoTable.DATI_DEPARTMENTNUMBER);
				String dati_workplan = resultSet.getString(DevAgencyTrainInfoTable.DATI_WORKPLAN);

				Integer dati_traintimes = resultSet.getInt(DevAgencyTrainInfoTable.DATI_TRAINTIMES);
				if(dati_traintimes == -999)
					dati_traintimes = null;
				Integer dati_traintrips = resultSet.getInt(DevAgencyTrainInfoTable.DATI_TRAINTRIPS);
				if(dati_traintrips == -999)
					dati_traintrips = null;
				int dati_serialnumber = resultSet.getInt(DevAgencyTrainInfoTable.DATI_SERIALNUMBER);
				String dati_college = resultSet.getString(DevAgencyTrainInfoTable.DATI_COLLEGE);//填报学院
			    Date dati_deadline = resultSet.getDate(DevAgencyTrainInfoTable.DATI_DEADLINE);//截止时间    
				String dati_comments = resultSet.getString(DevAgencyTrainInfoTable.DATI_COMMENTS);
				int isnull = resultSet.getInt(DevAgencyTrainInfoTable.ISNULL);
				
				DevAgencyTrainInfo dati = new DevAgencyTrainInfo(dati_id, dati_name, dati_departmentname, dati_departmentnumber,
						dati_workplan, dati_traintimes, dati_traintrips, dati_serialnumber, dati_deadline, 
						dati_college, dati_comments,isnull);
				datis.add(dati);
			}
			return datis;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
	}


}
