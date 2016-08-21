package cn.edu.xmu.daoimpl;

import java.nio.DoubleBuffer;
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

import cn.edu.xmu.dao.AdmissionCriteriaAndNumberDao;
import cn.edu.xmu.entity.AdmissionCriteriaAndNumber;
import cn.edu.xmu.table.AdmissionCriteriaAndNumberTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;

/**
 * 附表6-1-5-4  近一届本科生录取标准及人数（时点）
 * @author yue
 *
 */
public class AdmissionCriteriaAndNumberDaoImpl extends BaseDaoImpl<AdmissionCriteriaAndNumber> implements AdmissionCriteriaAndNumberDao{

	@Override
	public int addAdmissionCriteriaAndNumberRecord(AdmissionCriteriaAndNumber acn) {
		int result = 0;
		String t_sql = "update " + AdmissionCriteriaAndNumberTable.TABLE_NAME + " set "
				+AdmissionCriteriaAndNumberTable.ACN_SERIALNUMBER + " = "
				+AdmissionCriteriaAndNumberTable.ACN_SERIALNUMBER + " +1 where "
				+AdmissionCriteriaAndNumberTable.ACN_SERIALNUMBER + " >=?";
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
			t_pstmt.setInt(1, acn.getAcn_serialnumber());
			
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
		String sql = "insert into " + AdmissionCriteriaAndNumberTable.TABLE_NAME + "("
				+ AdmissionCriteriaAndNumberTable.ACN_PROVINCE +  ","
				+ AdmissionCriteriaAndNumberTable.ACN_BATCH + ","
				+ AdmissionCriteriaAndNumberTable.ACN_ARTSADMISSION + ","
				+ AdmissionCriteriaAndNumberTable.ACN_SCIENCEADMISSION + ","
				+ AdmissionCriteriaAndNumberTable.ACN_ARTSMINCTRLINE + ","
				+ AdmissionCriteriaAndNumberTable.ACN_SCIENCEMINCTRLINE + ","
				+ AdmissionCriteriaAndNumberTable.ACN_ARTSAVGSCORE + ","
				+ AdmissionCriteriaAndNumberTable.ACN_SCIENCEAVGSCORE + ","
				+ AdmissionCriteriaAndNumberTable.ACN_INSTRUCTION + ","
				+ AdmissionCriteriaAndNumberTable.ACN_SERIALNUMBER + ","
				+ AdmissionCriteriaAndNumberTable.ACN_COLLEGE + ","
				+ AdmissionCriteriaAndNumberTable.ANC_COMMENTS + ","
				+ AdmissionCriteriaAndNumberTable.ISNULL
				+ ")values(?,?,?,?,?,?,?,?,?,?,?,?,?)";

		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, acn.getAcn_province());
			pstmt.setString(2, acn.getAcn_batch());
			pstmt.setInt(3, acn.getAcn_artsadmission());
			pstmt.setInt(4, acn.getAcn_scienceadmission());
			pstmt.setDouble(5, acn.getAcn_artsminctrline());
			pstmt.setDouble(6, acn.getAcn_scienceminctrline());
			pstmt.setDouble(7, acn.getAcn_artsavgscore());
			pstmt.setDouble(8, acn.getAcn_scienceavgscore());
			pstmt.setString(9, acn.getAcn_instruction());
			pstmt.setInt(10, acn.getAcn_serialnumber());
			pstmt.setString(11, acn.getAcn_college());
			pstmt.setString(12, acn.getAcn_comments());
			pstmt.setInt(13, acn.getIsnull());
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
	public boolean batchDelete(String[] acnids) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();
		
		for(String acnid:acnids)
		{
			String sql = "delete from " + AdmissionCriteriaAndNumberTable.TABLE_NAME
					+ " where " + AdmissionCriteriaAndNumberTable.ACN_ID + " = '" + acnid + "'";
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
	public int alterAdmissionCriteriaAndNumber(Map<String, String> valueMap, String id) {
		Map<String, String> condition = new HashMap<String,String>();
		condition.put(AdmissionCriteriaAndNumberTable.ACN_ID, id);
		int result = updateRecord(AdmissionCriteriaAndNumberTable.TABLE_NAME, valueMap, condition);
		return result;
	}

	@Override
	public int getAdmissionCriteriaAndNumberCount(Map queryParams) {
		int count = 0;
		//获取条件的语句
		String sql = "select count(*) from " + AdmissionCriteriaAndNumberTable.TABLE_NAME
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
	public List<AdmissionCriteriaAndNumber> getAdmissionCriteriaAndNumber(int start, int end, String sortStr,
			String orderStr, Map<String, String> params, Date deadline) {
		String sql = " select tmp.* from ( " + " select * from "
				+ AdmissionCriteriaAndNumberTable.TABLE_NAME + " where 1=1 ";
		if (deadline != null) {

			sql += String.format("and "+AdmissionCriteriaAndNumberTable.ACN_DEADLINE+" like  '%s%%' ", deadline);
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
		
		List<AdmissionCriteriaAndNumber> acns = new ArrayList<AdmissionCriteriaAndNumber>();
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
			
			Double tempDouble = Double.valueOf("-999");//为空默认-999
			while (resultSet.next()) {
				int acn_id = resultSet.getInt(AdmissionCriteriaAndNumberTable.ACN_ID);
				String acn_province = resultSet.getString(AdmissionCriteriaAndNumberTable.ACN_PROVINCE);
				String acn_batch = resultSet.getString(AdmissionCriteriaAndNumberTable.ACN_BATCH);
				Integer acn_artsadmission = resultSet.getInt(AdmissionCriteriaAndNumberTable.ACN_ARTSADMISSION);
				if(acn_artsadmission == -999)
					acn_artsadmission = null;
				Integer acn_scienceadmission = resultSet.getInt(AdmissionCriteriaAndNumberTable.ACN_SCIENCEADMISSION);
				if(acn_scienceadmission == -999)
					acn_scienceadmission = null;
				Double acn_artsminctrline = resultSet.getDouble(AdmissionCriteriaAndNumberTable.ACN_ARTSMINCTRLINE);
				if(acn_artsminctrline.equals(tempDouble))
					acn_artsminctrline = null;
				Double acn_scienceminctrline = resultSet.getDouble(AdmissionCriteriaAndNumberTable.ACN_SCIENCEMINCTRLINE);
				if(acn_scienceminctrline.equals(tempDouble))
					acn_scienceminctrline = null;
				Double acn_artsavgscore = resultSet.getDouble(AdmissionCriteriaAndNumberTable.ACN_ARTSAVGSCORE);
				if(acn_artsavgscore.equals(tempDouble))
					acn_artsavgscore = null;
				Double acn_scienceavgscore = resultSet.getDouble(AdmissionCriteriaAndNumberTable.ACN_SCIENCEAVGSCORE);
				if(acn_scienceavgscore.equals(tempDouble))
					acn_scienceavgscore = null;
				String acn_instruction = resultSet.getString(AdmissionCriteriaAndNumberTable.ACN_INSTRUCTION);
				int acn_serialnumber = resultSet.getInt(AdmissionCriteriaAndNumberTable.ACN_SERIALNUMBER);
				String acn_college = resultSet.getString(AdmissionCriteriaAndNumberTable.ACN_COLLEGE);//填报学院
			    Date acn_deadline = resultSet.getDate(AdmissionCriteriaAndNumberTable.ACN_DEADLINE);//截止时间    
				String acn_comments = resultSet.getString(AdmissionCriteriaAndNumberTable.ANC_COMMENTS);
				int isnull = resultSet.getInt(AdmissionCriteriaAndNumberTable.ISNULL);
				
				AdmissionCriteriaAndNumber acn = new AdmissionCriteriaAndNumber(acn_id, acn_province, acn_batch,
						acn_artsadmission, acn_scienceadmission, acn_artsminctrline, acn_scienceminctrline, 
						acn_artsavgscore, acn_scienceavgscore, acn_instruction, acn_serialnumber, acn_deadline, 
						acn_college, acn_comments,isnull);
				acns.add(acn);
			}
			return acns;
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
		String sql = "delete from " + AdmissionCriteriaAndNumberTable.TABLE_NAME
				+ " where " + AdmissionCriteriaAndNumberTable.ACN_COLLEGE + " = '" + college + "'" +" and acn_deadline is null ";
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
	public List<AdmissionCriteriaAndNumber> getAllAdmissionCriteriaAndNumber() {
		String sql = " select * from " + AdmissionCriteriaAndNumberTable.TABLE_NAME
				+ " where 1=1 " + " order by " + AdmissionCriteriaAndNumberTable.ACN_ID;
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
			List<AdmissionCriteriaAndNumber> acns = new ArrayList<AdmissionCriteriaAndNumber>();
			Double tempDouble = Double.valueOf("-999");//为空默认-999
			while (resultSet.next()) {
				int acn_id = resultSet.getInt(AdmissionCriteriaAndNumberTable.ACN_ID);
				String acn_province = resultSet.getString(AdmissionCriteriaAndNumberTable.ACN_PROVINCE);
				String acn_batch = resultSet.getString(AdmissionCriteriaAndNumberTable.ACN_BATCH);
				Integer acn_artsadmission = resultSet.getInt(AdmissionCriteriaAndNumberTable.ACN_ARTSADMISSION);
				if(acn_artsadmission == -999)
					acn_artsadmission = null;
				Integer acn_scienceadmission = resultSet.getInt(AdmissionCriteriaAndNumberTable.ACN_SCIENCEADMISSION);
				if(acn_scienceadmission == -999)
					acn_scienceadmission = null;
				Double acn_artsminctrline = resultSet.getDouble(AdmissionCriteriaAndNumberTable.ACN_ARTSMINCTRLINE);
				if(acn_artsminctrline.equals(tempDouble))
					acn_artsminctrline = null;
				Double acn_scienceminctrline = resultSet.getDouble(AdmissionCriteriaAndNumberTable.ACN_SCIENCEMINCTRLINE);
				if(acn_scienceminctrline.equals(tempDouble))
					acn_scienceminctrline = null;
				Double acn_artsavgscore = resultSet.getDouble(AdmissionCriteriaAndNumberTable.ACN_ARTSAVGSCORE);
				if(acn_artsavgscore.equals(tempDouble))
					acn_artsavgscore = null;
				Double acn_scienceavgscore = resultSet.getDouble(AdmissionCriteriaAndNumberTable.ACN_SCIENCEAVGSCORE);
				if(acn_scienceavgscore.equals(tempDouble))
					acn_scienceavgscore = null;
				String acn_instruction = resultSet.getString(AdmissionCriteriaAndNumberTable.ACN_INSTRUCTION);
				int acn_serialnumber = resultSet.getInt(AdmissionCriteriaAndNumberTable.ACN_SERIALNUMBER);
				String acn_college = resultSet.getString(AdmissionCriteriaAndNumberTable.ACN_COLLEGE);//填报学院
			    Date acn_deadline = resultSet.getDate(AdmissionCriteriaAndNumberTable.ACN_DEADLINE);//截止时间    
				String acn_comments = resultSet.getString(AdmissionCriteriaAndNumberTable.ANC_COMMENTS);
				int isnull = resultSet.getInt(AdmissionCriteriaAndNumberTable.ISNULL);
				
				AdmissionCriteriaAndNumber acn = new AdmissionCriteriaAndNumber(acn_id, acn_province, acn_batch,
						acn_artsadmission, acn_scienceadmission, acn_artsminctrline, acn_scienceminctrline, 
						acn_artsavgscore, acn_scienceavgscore, acn_instruction, acn_serialnumber, acn_deadline, 
						acn_college, acn_comments,isnull);
				acns.add(acn);
			}
			return acns;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
	}

}
