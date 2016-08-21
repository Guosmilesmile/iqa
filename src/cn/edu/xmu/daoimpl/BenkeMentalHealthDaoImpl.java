package cn.edu.xmu.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.dao.BenkeMentalHealthDao;
import cn.edu.xmu.entity.BenkeMentalHealth;
import cn.edu.xmu.table.BenkeMentalHealthTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;


/**
 * 
 * @author Lee
 * 本科生受处分情况  实体类功能 ——接口实现
 * date 2015-07-13
 */

public class BenkeMentalHealthDaoImpl extends BaseDaoImpl<BenkeMentalHealth>implements BenkeMentalHealthDao{

	@Override
	public int addRecord(BenkeMentalHealth bmh) {
		
		int result = 0;

		String t_sql = "update " + BenkeMentalHealthTable.TABLE_NAME + " set "
				+ BenkeMentalHealthTable.BMH_SERIALNUMBER + " = "
				+ BenkeMentalHealthTable.BMH_SERIALNUMBER + " +1 where "
				+ BenkeMentalHealthTable.BMH_SERIALNUMBER + ">=?";

		
		Connection connection2 = null;
		try {
			//连接池取得对象连接
			connection2 = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		PreparedStatement t_pstmt = null;
		try {
			//获取操作对象
			t_pstmt = connection2.prepareStatement(t_sql);
			t_pstmt.setInt(1, bmh.getBmh_serialnumber());
			
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
		String sql = "insert into " + BenkeMentalHealthTable.TABLE_NAME + "("
				+ BenkeMentalHealthTable.BMH_COLLEGE1 + ","
				+ BenkeMentalHealthTable.BMH_INSCHOOLAMOUNT + ","
				+ BenkeMentalHealthTable.BMH_HEALTH + ","
				+ BenkeMentalHealthTable.BMH_SERIALNUMBER+","
				+ BenkeMentalHealthTable.BMH_COLLEGE+","
				+ BenkeMentalHealthTable.ISNULL
				+ ")values(?,?,?,?,?,?)";

		System.out.println(sql);
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, bmh.getBmh_college1());
			pstmt.setInt(2, bmh.getBmh_inschoolamount());
			pstmt.setString(3, bmh.getBmh_health());
			pstmt.setInt(4, bmh.getBmh_serialnumber());
			pstmt.setString(5, bmh.getBmh_college());
			pstmt.setInt(6, bmh.getIsnull());
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
	public int getBenkeMentalHealthCount(Map queryParams) {
		
		int count = 0;
		//获取条件的语句
		String sql = "select count(*) from " + BenkeMentalHealthTable.TABLE_NAME
				+ " where 1 = 1";
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		for (Object object : queryParams.keySet()) {
			String key = object.toString();
			String value = queryParams.get(key).toString();
			sql += String.format(" and  %s like  '%%%s%%' ", key, value);
		}
		sql += String.format(" or %s ='%s'", BenkeMentalHealthTable.BMH_COLLEGE, "");
		
		
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
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		
		return count;
	}

	@Override
	public List<BenkeMentalHealth> getAllBenkeMentalHealth(int start, int end,
			String sortStr, String orderStr, Map queryParams) {
		
		String sql = " select tmp.* from ( " + " select * from "
				+ BenkeMentalHealthTable.TABLE_NAME + " where 1=1 ";
		
		for (Object object : queryParams.keySet()) {
			String key = object.toString();
			String value = queryParams.get(key).toString();
			sql += String.format(" and %s like  '%%%s%%'", key, value);
		}
		sql += " order by " + sortStr + " " + orderStr + " ) tmp limit "
				+ start + " ," + end;

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		List<BenkeMentalHealth> bmhs = new ArrayList<BenkeMentalHealth>();
		try {
			
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int bmh_id = resultSet.getInt(BenkeMentalHealthTable.BMH_ID);
				String bmh_college1 = resultSet.getString(BenkeMentalHealthTable.BMH_COLLEGE1);
				int bmh_inschoolamount = resultSet.getInt(BenkeMentalHealthTable.BMH_INSCHOOLAMOUNT);
				String bmh_health = resultSet.getString(BenkeMentalHealthTable.BMH_HEALTH);
				
				int bmh_serialnumber = resultSet.getInt(BenkeMentalHealthTable.BMH_SERIALNUMBER);
				
				String bmh_comments = resultSet.getString(BenkeMentalHealthTable.BMH_COMMENTS);
				if(null==bmh_comments){
					bmh_comments="";
				}
				String bmh_college = resultSet.getString(BenkeMentalHealthTable.BMH_COLLEGE);
				int isnull = resultSet.getInt(BenkeMentalHealthTable.ISNULL);
				
				BenkeMentalHealth bmh = new BenkeMentalHealth(bmh_id, bmh_college1, bmh_inschoolamount, bmh_health,
						bmh_serialnumber, bmh_comments, bmh_college, isnull);
				
				bmhs.add(bmh);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return bmhs;
	}

	@Override
	public int alterBenkeMentalHealth(Map<String, String> valueMap, String id) {
		
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(BenkeMentalHealthTable.BMH_ID, id);
		int result = updateRecord(BenkeMentalHealthTable.TABLE_NAME, valueMap,condition);
		return result;
	}
	
	
	

	@Override
	public boolean batchDelete(String[] bmhids) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();

		for (String bmhid : bmhids) {
			String sql = "delete from " + BenkeMentalHealthTable.TABLE_NAME
					+ " where " + BenkeMentalHealthTable.BMH_ID + " = '" + bmhid + "'";
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
	public void deleteByCollegeandDeadline(String college) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();
		String sql = "delete from " + BenkeMentalHealthTable.TABLE_NAME
				+ " where " + BenkeMentalHealthTable.BMH_COLLEGE + " = '" + college + "'" +" and "
				+BenkeMentalHealthTable.BMH_DEADLINE+" is null ";
		
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
	public List<BenkeMentalHealth> getAllBenkeMentalHealth() {
		String sql = " select * from " + BenkeMentalHealthTable.TABLE_NAME
				+ " where 1=1 " + " order by " + BenkeMentalHealthTable.BMH_ID;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		List<BenkeMentalHealth> bmhs = new ArrayList<BenkeMentalHealth>();
		try {
			
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int bmh_id = resultSet.getInt(BenkeMentalHealthTable.BMH_ID);
				String bmh_college1 = resultSet.getString(BenkeMentalHealthTable.BMH_COLLEGE1);
				int bmh_inschoolamount = resultSet.getInt(BenkeMentalHealthTable.BMH_INSCHOOLAMOUNT);
				String bmh_health = resultSet.getString(BenkeMentalHealthTable.BMH_HEALTH);
				
				int bmh_serialnumber = resultSet.getInt(BenkeMentalHealthTable.BMH_SERIALNUMBER);
				
				String bmh_comments = resultSet.getString(BenkeMentalHealthTable.BMH_COMMENTS);
				if(null==bmh_comments){
					bmh_comments="";
				}
				String bmh_college = resultSet.getString(BenkeMentalHealthTable.BMH_COLLEGE);
				int isnull = resultSet.getInt(BenkeMentalHealthTable.ISNULL);
				
				BenkeMentalHealth bmh = new BenkeMentalHealth(bmh_id, bmh_college1, bmh_inschoolamount, bmh_health,
						bmh_serialnumber, bmh_comments, bmh_college, isnull);
				
				bmhs.add(bmh);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return bmhs;
	}
}
