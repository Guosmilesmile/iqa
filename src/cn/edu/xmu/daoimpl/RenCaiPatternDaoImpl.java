package cn.edu.xmu.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.dao.RenCaiPatternDao;
import cn.edu.xmu.entity.RenCaiPattern;
import cn.edu.xmu.table.RenCaiPatternTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;


/**
 * 
 * @author Lee
 * 学校相关行政单位  实体类功能 ——接口实现
 * date 2015-06-29
 */

public class RenCaiPatternDaoImpl extends BaseDaoImpl<RenCaiPattern>implements RenCaiPatternDao{

	@Override
	public int addRecord(RenCaiPattern rcp) {
		
		int result = 0;

		String t_sql = "update " + RenCaiPatternTable.TABLE_NAME + " set "
				+ RenCaiPatternTable.RCP_SERIALNUMBER + " = "
				+ RenCaiPatternTable.RCP_SERIALNUMBER + " +1 where "
				+ RenCaiPatternTable.RCP_SERIALNUMBER + ">=?";

		
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
			t_pstmt.setInt(1, rcp.getRcp_serialnumber());
			
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
		String sql = "insert into " + RenCaiPatternTable.TABLE_NAME + "("
				+ RenCaiPatternTable.RCP_COLLEGE1 + ","
				+ RenCaiPatternTable.RCP_PROJECT + ","
				+ RenCaiPatternTable.RCP_HEAD + ","
				+ RenCaiPatternTable.RCP_TYPE + ","
				+ RenCaiPatternTable.RCP_LEVEL + ","
				+ RenCaiPatternTable.RCP_STARTTIME+","
				+ RenCaiPatternTable.RCP_SERIALNUMBER+","
				+ RenCaiPatternTable.RCP_COLLEGE+","
				+ RenCaiPatternTable.ISNULL
				+ ")values(?,?,?,?,?,?,?,?,?)";

		System.out.println(sql);
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, rcp.getRcp_college1());
			pstmt.setString(2, rcp.getRcp_project());
			pstmt.setString(3, rcp.getRcp_head());
			pstmt.setString(4, rcp.getRcp_type());
			pstmt.setString(5, rcp.getRcp_level());
			pstmt.setDate(6, (java.sql.Date) rcp.getRcp_starttime());
			pstmt.setInt(7, rcp.getRcp_serialnumber());
			pstmt.setString(8, rcp.getRcp_college());
			pstmt.setInt(9, rcp.getRcp_isnull());
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
	public int getRenCaiPatternCount(Map queryParams) {
		
		int count = 0;
		//获取条件的语句
		String sql = "select count(*) from " + RenCaiPatternTable.TABLE_NAME
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
		sql += String.format(" or %s ='%s'", RenCaiPatternTable.RCP_COLLEGE, "");
		
		
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
	public List<RenCaiPattern> getAllRenCaiPattern(int start, int end,
			String sortStr, String orderStr, Map queryParams) {
		
		String sql = " select tmp.* from ( " + " select * from "
				+ RenCaiPatternTable.TABLE_NAME + " where 1=1 ";
		
		for (Object object : queryParams.keySet()) {
			String key = object.toString();
			String value = queryParams.get(key).toString();
			sql += String.format(" and %s like  '%%%s%%'", key, value);
		}
		sql += " order by " + sortStr + " " + orderStr + " ) tmp limit "
				+ start + " ," + end;

		System.out.println(sql+"===");
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		List<RenCaiPattern> rcps = new ArrayList<RenCaiPattern>();
		try {
			
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int rcp_id = resultSet.getInt(RenCaiPatternTable.RCP_ID);
				String rcp_college1 = resultSet.getString(RenCaiPatternTable.RCP_COLLEGE1);
				String rcp_project = resultSet.getString(RenCaiPatternTable.RCP_PROJECT);
				String rcp_head = resultSet.getString(RenCaiPatternTable.RCP_HEAD);
				String rcp_type = resultSet.getString(RenCaiPatternTable.RCP_TYPE);
				String rcp_level = resultSet.getString(RenCaiPatternTable.RCP_LEVEL);
				
				Date rcp_starttime = resultSet.getDate(RenCaiPatternTable.RCP_STARTTIME);
				Date temp = Date.valueOf("1800-1-1");
				if(rcp_starttime.equals(temp)){
					rcp_starttime = null;
				}
				int rcp_serialnumber = resultSet.getInt(RenCaiPatternTable.RCP_SERIALNUMBER);
				Date rcp_deadline = resultSet.getDate(RenCaiPatternTable.RCP_DEADLINE);
				
				String rcp_comments = resultSet.getString(RenCaiPatternTable.RCP_COMMENTS);
				if(null==rcp_comments){
					rcp_comments="";
				}
				String rcp_college = resultSet.getString(RenCaiPatternTable.RCP_COLLEGE);
				int rcp_isnull = resultSet.getInt(RenCaiPatternTable.ISNULL);
				
				RenCaiPattern rcp = new RenCaiPattern(rcp_id, rcp_college1, rcp_project, rcp_head,
						rcp_type, rcp_level, rcp_starttime, rcp_serialnumber, rcp_deadline,
						rcp_comments, rcp_college, rcp_isnull);
				
				
				rcps.add(rcp);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return rcps;
	}

	@Override
	public int alterRenCaiPattern(Map<String, String> valueMap, String id) {
		
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(RenCaiPatternTable.RCP_ID, id);
		int result = updateRecord(RenCaiPatternTable.TABLE_NAME, valueMap,condition);
		return result;
	}
	
	
	

	@Override
	public boolean batchDelete(String[] rcpids) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();

		for (String rcpid : rcpids) {
			String sql = "delete from " + RenCaiPatternTable.TABLE_NAME
					+ " where " + RenCaiPatternTable.RCP_ID + " = '" + rcpid + "'";
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
	public List<RenCaiPattern> getRenCaiPattern(Map queryParams) {
		String sql = " select tmp.* from ( " + " select * from "
				+ RenCaiPatternTable.TABLE_NAME + " where 1=1 ";
		
		for (Object object : queryParams.keySet()) {
			String key = object.toString();
			String value = queryParams.get(key).toString();
			sql += String.format(" and %s like  '%%%s%%'", key, value);
		}
		sql += " order by " + RenCaiPatternTable.RCP_ID + " ) tmp ";

		System.out.println(sql+"===");
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		List<RenCaiPattern> rcps = new ArrayList<RenCaiPattern>();
		try {
			
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int rcp_id = resultSet.getInt(RenCaiPatternTable.RCP_ID);
				String rcp_college1 = resultSet.getString(RenCaiPatternTable.RCP_COLLEGE1);
				String rcp_project = resultSet.getString(RenCaiPatternTable.RCP_PROJECT);
				String rcp_head = resultSet.getString(RenCaiPatternTable.RCP_HEAD);
				String rcp_type = resultSet.getString(RenCaiPatternTable.RCP_TYPE);
				String rcp_level = resultSet.getString(RenCaiPatternTable.RCP_LEVEL);
				
				Date rcp_starttime = resultSet.getDate(RenCaiPatternTable.RCP_STARTTIME);
				int rcp_serialnumber = resultSet.getInt(RenCaiPatternTable.RCP_SERIALNUMBER);
				Date rcp_deadline = resultSet.getDate(RenCaiPatternTable.RCP_DEADLINE);
				
				String rcp_comments = resultSet.getString(RenCaiPatternTable.RCP_COMMENTS);
				if(null==rcp_comments){
					rcp_comments="";
				}
				String rcp_college = resultSet.getString(RenCaiPatternTable.RCP_COLLEGE);
				int rcp_isnull = resultSet.getInt(RenCaiPatternTable.ISNULL);
				
				RenCaiPattern rcp = new RenCaiPattern(rcp_id, rcp_college1, rcp_project, rcp_head,
						rcp_type, rcp_level, rcp_starttime, rcp_serialnumber, rcp_deadline,
						rcp_comments, rcp_college, rcp_isnull);
				
				
				rcps.add(rcp);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return rcps;
	}
}
