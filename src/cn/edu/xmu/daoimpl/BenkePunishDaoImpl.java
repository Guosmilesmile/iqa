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

import cn.edu.xmu.dao.BenkePunishDao;
import cn.edu.xmu.entity.BenkePunish;
import cn.edu.xmu.table.BenkePunishTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;


/**
 * 
 * @author Lee
 * 本科生受处分情况  实体类功能 ——接口实现
 * date 2015-07-13
 */

public class BenkePunishDaoImpl extends BaseDaoImpl<BenkePunish>implements BenkePunishDao{

	@Override
	public int addRecord(BenkePunish bp) {
		
		int result = 0;

		String t_sql = "update " + BenkePunishTable.TABLE_NAME + " set "
				+ BenkePunishTable.BP_SERIALNUMBER + " = "
				+ BenkePunishTable.BP_SERIALNUMBER + " +1 where "
				+ BenkePunishTable.BP_SERIALNUMBER + ">=?";

		
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
			t_pstmt.setInt(1, bp.getBp_serialnumber());
			
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
		String sql = "insert into " + BenkePunishTable.TABLE_NAME + "("
				+ BenkePunishTable.BP_COLLEGE1 + ","
				+ BenkePunishTable.BP_WARNING + ","
				+ BenkePunishTable.BP_DEMERIT + ","
				+ BenkePunishTable.BP_PROBATION + ","
				+ BenkePunishTable.BP_EXPULSION + ","
				+ BenkePunishTable.BP_TOTALMOUNT+","
				+ BenkePunishTable.BP_SERIALNUMBER+","
				+ BenkePunishTable.BP_COLLEGE+","
				+ BenkePunishTable.ISNULL
				+ ")values(?,?,?,?,?,?,?,?,?)";

		System.out.println(sql);
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, bp.getBp_college1());
			pstmt.setString(2, bp.getBp_warning());
			pstmt.setString(3, bp.getBp_demerit());
			pstmt.setString(4, bp.getBp_probation());
			pstmt.setString(5, bp.getBp_expulsion());
			pstmt.setInt(6, bp.getBp_totalmount());
			pstmt.setInt(7, bp.getBp_serialnumber());
			pstmt.setString(8, bp.getBp_college());
			pstmt.setInt(9, bp.getIsnull());
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
	public int getBenkePunishCount(Map queryParams) {
		
		int count = 0;
		//获取条件的语句
		String sql = "select count(*) from " + BenkePunishTable.TABLE_NAME
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
			sql += String.format(" and  %s like  '%s%%' ", key, value);
		}
		sql += String.format(" or %s ='%s'", BenkePunishTable.BP_COLLEGE, "");
		
		
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
	public List<BenkePunish> getAllBenkePunish(int start, int end,
			String sortStr, String orderStr, Map queryParams) {
		
		String sql = " select tmp.* from ( " + " select * from "
				+ BenkePunishTable.TABLE_NAME + " where 1=1 ";
		
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
		
		List<BenkePunish> bps = new ArrayList<BenkePunish>();
		try {
			
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int bp_id = resultSet.getInt(BenkePunishTable.BP_ID);
				String bp_college1 = resultSet.getString(BenkePunishTable.BP_COLLEGE1);
				String bp_warning = resultSet.getString(BenkePunishTable.BP_WARNING);
				String bp_demerit = resultSet.getString(BenkePunishTable.BP_DEMERIT);
				String bp_probation = resultSet.getString(BenkePunishTable.BP_PROBATION);
				String bp_expulsion = resultSet.getString(BenkePunishTable.BP_EXPULSION);
				
				int bp_totalmount = resultSet.getInt(BenkePunishTable.BP_TOTALMOUNT);
				
				int bp_serialnumber = resultSet.getInt(BenkePunishTable.BP_SERIALNUMBER);
				
				String bp_comments = resultSet.getString(BenkePunishTable.BP_COMMENTS);
				if(null==bp_comments){
					bp_comments="";
				}
				String bp_college = resultSet.getString(BenkePunishTable.BP_COLLEGE);
				int isnull = resultSet.getInt(BenkePunishTable.ISNULL);
				
				BenkePunish bp = new BenkePunish(bp_id, bp_college1, bp_warning, bp_demerit,
						bp_probation, bp_expulsion, bp_totalmount, bp_serialnumber,
						bp_comments, bp_college, isnull);
				
				bps.add(bp);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return bps;
	}

	@Override
	public int alterBenkePunish(Map<String, String> valueMap, String id) {
		
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(BenkePunishTable.BP_ID, id);
		int result = updateRecord(BenkePunishTable.TABLE_NAME, valueMap,condition);
		return result;
	}
	
	
	

	@Override
	public boolean batchDelete(String[] bpids) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();

		for (String bpid : bpids) {
			String sql = "delete from " + BenkePunishTable.TABLE_NAME
					+ " where " + BenkePunishTable.BP_ID + " = '" + bpid + "'";
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
		String sql = "delete from " + BenkePunishTable.TABLE_NAME
				+ " where " + BenkePunishTable.BP_COLLEGE + " = '" + college + "'" +" and "
				+BenkePunishTable.BP_DEADLINE+" is null ";
		
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
	public List<BenkePunish> getAllBenkePunish() {
		String sql = " select * from " + BenkePunishTable.TABLE_NAME
				+ " where 1=1 " + " order by " + BenkePunishTable.BP_ID;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		List<BenkePunish> bps = new ArrayList<BenkePunish>();
		try {
			
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int bp_id = resultSet.getInt(BenkePunishTable.BP_ID);
				String bp_college1 = resultSet.getString(BenkePunishTable.BP_COLLEGE1);
				String bp_warning = resultSet.getString(BenkePunishTable.BP_WARNING);
				String bp_demerit = resultSet.getString(BenkePunishTable.BP_DEMERIT);
				String bp_probation = resultSet.getString(BenkePunishTable.BP_PROBATION);
				String bp_expulsion = resultSet.getString(BenkePunishTable.BP_EXPULSION);
				
				int bp_totalmount = resultSet.getInt(BenkePunishTable.BP_TOTALMOUNT);
				
				int bp_serialnumber = resultSet.getInt(BenkePunishTable.BP_SERIALNUMBER);
				
				String bp_comments = resultSet.getString(BenkePunishTable.BP_COMMENTS);
				if(null==bp_comments){
					bp_comments="";
				}
				String bp_college = resultSet.getString(BenkePunishTable.BP_COLLEGE);
				int isnull = resultSet.getInt(BenkePunishTable.ISNULL);
				
				BenkePunish bp = new BenkePunish(bp_id, bp_college1, bp_warning, bp_demerit,
						bp_probation, bp_expulsion, bp_totalmount, bp_serialnumber,
						bp_comments, bp_college, isnull);
				
				bps.add(bp);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return bps;
	}
}
