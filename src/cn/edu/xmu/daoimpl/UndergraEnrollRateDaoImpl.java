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

import cn.edu.xmu.dao.UndergraEnrollRateDao;
import cn.edu.xmu.entity.UndergraEnrollRate;
import cn.edu.xmu.table.UndergraEnrollRateTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;

/**
 * 附表6-1-5-3本科生招生志愿满足率（时点）
 * @author yue
 *
 */
public class UndergraEnrollRateDaoImpl extends BaseDaoImpl<UndergraEnrollRate> implements UndergraEnrollRateDao{

	@Override
	public int addUndergraEnrollRateRecord(UndergraEnrollRate uer) {
		int result = 0;
		String t_sql = "update " + UndergraEnrollRateTable.TABLE_NAME + " set "
				+UndergraEnrollRateTable.UER_SERIALNUMBER + " = "
				+UndergraEnrollRateTable.UER_SERIALNUMBER + " +1 where "
				+UndergraEnrollRateTable.UER_SERIALNUMBER + " >=?";
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
			t_pstmt.setInt(1, uer.getUer_serialnumber());
			
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
		String sql = "insert into " + UndergraEnrollRateTable.TABLE_NAME + "("
				+ UndergraEnrollRateTable.UER_INSTITUTE + ","
				+ UndergraEnrollRateTable.UER_ADMISSION + ","
				+ UndergraEnrollRateTable.UER_FIRSTMAJORRATE + ","
				+ UndergraEnrollRateTable.UER_UNFIRSTMAJORRATE + ","
				+ UndergraEnrollRateTable.UER_ADJUSTRATE + ","
				+ UndergraEnrollRateTable.UER_SERIALNUMBER + ","
				+ UndergraEnrollRateTable.UER_COLLEGEG + ","
				+ UndergraEnrollRateTable.UER_COMMENTS + ","
				+ UndergraEnrollRateTable.ISNULL
				+ ")values(?,?,?,?,?,?,?,?,?)";

		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, uer.getUer_institute());
			pstmt.setString(2, uer.getUer_admission());
			pstmt.setDouble(3, uer.getUer_firstmajorrate());
			pstmt.setDouble(4, uer.getUer_unfirstmajorrate());
			pstmt.setDouble(5, uer.getUer_adjustrate());
			pstmt.setInt(6, uer.getUer_serialnumber());
			pstmt.setString(7, uer.getUer_college());
			pstmt.setString(8, uer.getUer_comments());
			pstmt.setInt(9, uer.getIsnull());
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
	public boolean batchDelete(String[] uerids) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();
		
		for(String uerid:uerids)
		{
			String sql = "delete from " + UndergraEnrollRateTable.TABLE_NAME
					+ " where " + UndergraEnrollRateTable.UER_ID + " = '" +uerid + "'";
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
	public int alterUndergraEnrollRate(Map<String, String> valueMap, String id) {
		Map<String, String> condition = new HashMap<String,String>();
		condition.put(UndergraEnrollRateTable.UER_ID, id);
		int result = updateRecord(UndergraEnrollRateTable.TABLE_NAME, valueMap, condition);
		return result;
	}

	@Override
	public int getUndergraEnrollRateCount(Map queryParams) {
		int count = 0;
		//获取条件的语句
		String sql = "select count(*) from " + UndergraEnrollRateTable.TABLE_NAME
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
	public List<UndergraEnrollRate> getUndergraEnrollRate(int start, int end, String sortStr, String orderStr,
			Map<String, String> params, Date deadline) {
		String sql = " select tmp.* from ( " + " select * from "
				+ UndergraEnrollRateTable.TABLE_NAME + " where 1=1 ";
		if (deadline != null) {

			sql += String.format("and "+UndergraEnrollRateTable.UER_DEADLINE+" like  '%s%%' ", deadline);
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
		
		List<UndergraEnrollRate> uers = new ArrayList<UndergraEnrollRate>();
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
			Double tempDouble = Double.valueOf("-999");//
			while (resultSet.next()) {
				int uer_id = resultSet.getInt(UndergraEnrollRateTable.UER_ID);
				String uer_institute = resultSet.getString(UndergraEnrollRateTable.UER_INSTITUTE);
				String uer_admission = resultSet.getString(UndergraEnrollRateTable.UER_ADMISSION);
				Double uer_firstmajorrate = resultSet.getDouble(UndergraEnrollRateTable.UER_FIRSTMAJORRATE);
				if(uer_firstmajorrate.equals(tempDouble))
					uer_firstmajorrate = null;
				Double uer_unfirstmajorrate = resultSet.getDouble(UndergraEnrollRateTable.UER_UNFIRSTMAJORRATE);
				if(uer_unfirstmajorrate.equals(tempDouble))
					uer_unfirstmajorrate = null;
				Double uer_adjustrate = resultSet.getDouble(UndergraEnrollRateTable.UER_ADJUSTRATE);
				if(uer_adjustrate.equals(tempDouble))
					uer_adjustrate = null;
				int uer_serialnumber = resultSet.getInt(UndergraEnrollRateTable.UER_SERIALNUMBER);
				String uer_college = resultSet.getString(UndergraEnrollRateTable.UER_COLLEGEG);//填报学院
			    Date uer_deadline = resultSet.getDate(UndergraEnrollRateTable.UER_DEADLINE);//截止时间    
				String uer_comments = resultSet.getString(UndergraEnrollRateTable.UER_COMMENTS);
				int isnull = resultSet.getInt(UndergraEnrollRateTable.ISNULL);
				
				UndergraEnrollRate uer = new UndergraEnrollRate(uer_id, uer_institute, uer_admission, 
						uer_firstmajorrate, uer_unfirstmajorrate, uer_adjustrate, uer_serialnumber,
						uer_deadline, uer_college, uer_comments,isnull);
				uers.add(uer);
			}
			return uers;
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
		String sql = "delete from " + UndergraEnrollRateTable.TABLE_NAME
				+ " where " + UndergraEnrollRateTable.UER_COLLEGEG + " = '" + college + "'" +" and uer_deadline is null ";
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
	public List<UndergraEnrollRate> getAllUndergraEnrollRate() {
		String sql = " select * from " + UndergraEnrollRateTable.TABLE_NAME
				+ " where 1=1 " + " order by " + UndergraEnrollRateTable.UER_ID;
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
			List<UndergraEnrollRate> uers = new ArrayList<UndergraEnrollRate>();
			Double tempDouble = Double.valueOf("-999");//
			while (resultSet.next()) {
				int uer_id = resultSet.getInt(UndergraEnrollRateTable.UER_ID);
				String uer_institute = resultSet.getString(UndergraEnrollRateTable.UER_INSTITUTE);
				String uer_admission = resultSet.getString(UndergraEnrollRateTable.UER_ADMISSION);
				Double uer_firstmajorrate = resultSet.getDouble(UndergraEnrollRateTable.UER_FIRSTMAJORRATE);
				if(uer_firstmajorrate.equals(tempDouble))
					uer_firstmajorrate = null;
				Double uer_unfirstmajorrate = resultSet.getDouble(UndergraEnrollRateTable.UER_UNFIRSTMAJORRATE);
				if(uer_unfirstmajorrate.equals(tempDouble))
					uer_unfirstmajorrate = null;
				Double uer_adjustrate = resultSet.getDouble(UndergraEnrollRateTable.UER_ADJUSTRATE);
				if(uer_adjustrate.equals(tempDouble))
					uer_adjustrate = null;
				int uer_serialnumber = resultSet.getInt(UndergraEnrollRateTable.UER_SERIALNUMBER);
				String uer_college = resultSet.getString(UndergraEnrollRateTable.UER_COLLEGEG);//填报学院
			    Date uer_deadline = resultSet.getDate(UndergraEnrollRateTable.UER_DEADLINE);//截止时间    
				String uer_comments = resultSet.getString(UndergraEnrollRateTable.UER_COMMENTS);
				int isnull = resultSet.getInt(UndergraEnrollRateTable.ISNULL);
				
				UndergraEnrollRate uer = new UndergraEnrollRate(uer_id, uer_institute, uer_admission, 
						uer_firstmajorrate, uer_unfirstmajorrate, uer_adjustrate, uer_serialnumber,
						uer_deadline, uer_college, uer_comments,isnull);
				uers.add(uer);
			
			}
			return uers;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
	}

}
