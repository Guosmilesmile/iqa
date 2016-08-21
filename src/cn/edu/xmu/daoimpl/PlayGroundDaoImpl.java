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

import cn.edu.xmu.dao.PlayGroundDao;
import cn.edu.xmu.entity.PlayGround;
import cn.edu.xmu.table.PlayGroundTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;


/**
 * 
 * @author Lee
 * 运动场 实体类功能 ——接口实现
 * date 2015-07-13
 */

public class PlayGroundDaoImpl extends BaseDaoImpl<PlayGround>implements PlayGroundDao{

	@Override
	public int addRecord(PlayGround pg) {
		
		int result = 0;

		String t_sql = "update " + PlayGroundTable.TABLE_NAME + " set "
				+ PlayGroundTable.PG_SERIALNUMBER + " = "
				+ PlayGroundTable.PG_SERIALNUMBER + " +1 where "
				+ PlayGroundTable.PG_SERIALNUMBER + ">=?";

		
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
			t_pstmt.setInt(1, pg.getPg_serialnumber());
			
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
		String sql = "insert into " + PlayGroundTable.TABLE_NAME + "("
				+ PlayGroundTable.PG_CAMPUS + ","
				+ PlayGroundTable.PG_GROUNDNAME + ","
				+ PlayGroundTable.PG_AMOUNT + ","
				+ PlayGroundTable.PG_INDOORAREA + ","
				+ PlayGroundTable.PG_OUTDOORAREA + ","
				+ PlayGroundTable.PG_TOTALAREA+","
				+ PlayGroundTable.PG_SERIALNUMBER+","
				+ PlayGroundTable.PG_COLLEGE+","
				+ PlayGroundTable.ISNULL
				+ ")values(?,?,?,?,?,?,?,?,?)";

		System.out.println(sql);
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, pg.getPg_campus());
			pstmt.setString(2, pg.getPg_groundname());
			pstmt.setInt(3, pg.getPg_amount());
			pstmt.setString(4, pg.getPg_indoorarea());
			pstmt.setString(5, pg.getPg_outdoorarea());
			pstmt.setString(6, pg.getPg_totalarea());
			pstmt.setInt(7, pg.getPg_serialnumber());
			pstmt.setString(8, pg.getPg_college());
			pstmt.setInt(9, pg.getIsnull());
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
	public int getPlayGroundCount(Map queryParams) {
		
		int count = 0;
		//获取条件的语句
		String sql = "select count(*) from " + PlayGroundTable.TABLE_NAME
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
		sql += String.format(" or %s ='%s'", PlayGroundTable.PG_COLLEGE, "");
		
		
		System.out.println("========"+sql);
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
	public List<PlayGround> getAllPlayGround(int start, int end,
			String sortStr, String orderStr, Map queryParams) {
		
		String sql = " select tmp.* from ( " + " select * from "
				+ PlayGroundTable.TABLE_NAME + " where 1=1 ";
		
		for (Object object : queryParams.keySet()) {
			String key = object.toString();
			String value = queryParams.get(key).toString();
			sql += String.format(" and %s like  '%%%s%%'", key, value);
		}
		
		if (sortStr == "nope") {
			
		}else {
			sql += " order by " + sortStr + " " + orderStr + " ) tmp limit "
					+ start + " ," + end;
		}


		System.out.println("==="+sql);
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		List<PlayGround> pgs = new ArrayList<PlayGround>();
		try {
			
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int pg_id = resultSet.getInt(PlayGroundTable.PG_ID);
				String pg_campus = resultSet.getString(PlayGroundTable.PG_CAMPUS);
				String pg_groundname = resultSet.getString(PlayGroundTable.PG_GROUNDNAME);
				int pg_amount = resultSet.getInt(PlayGroundTable.PG_AMOUNT);
				String pg_indoorarea = resultSet.getString(PlayGroundTable.PG_INDOORAREA);
				String pg_outdoorarea = resultSet.getString(PlayGroundTable.PG_OUTDOORAREA);
				
				String pg_totalarea = resultSet.getString(PlayGroundTable.PG_TOTALAREA);
				int pg_serialnumber = resultSet.getInt(PlayGroundTable.PG_SERIALNUMBER);
				
				String pg_comments = resultSet.getString(PlayGroundTable.PG_COMMENTS);
				if(null==pg_comments){
					pg_comments="";
				}
				String pg_college = resultSet.getString(PlayGroundTable.PG_COLLEGE);
				int isnull = resultSet.getInt(PlayGroundTable.ISNULL);
				
				PlayGround pg = new PlayGround(pg_id, pg_campus, pg_groundname, pg_amount,
						pg_indoorarea, pg_outdoorarea, pg_totalarea, pg_serialnumber,pg_comments, pg_college, isnull);
				
				
				pgs.add(pg);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return pgs;
	}

	@Override
	public int alterPlayGround(Map<String, String> valueMap, String id) {
		
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(PlayGroundTable.PG_ID, id);
		int result = updateRecord(PlayGroundTable.TABLE_NAME, valueMap,condition);
		return result;
	}
	
	
	

	@Override
	public boolean batchDelete(String[] pgids) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();

		for (String pgid : pgids) {
			String sql = "delete from " + PlayGroundTable.TABLE_NAME
					+ " where " + PlayGroundTable.PG_ID + " = '" + pgid + "'";
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
	public List<PlayGround> getAllPlayGround() {
		String sql = " select * from " + PlayGroundTable.TABLE_NAME
				+ " where 1=1 " + " order by " + PlayGroundTable.PG_ID;
		Connection connection = null;

		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		List<PlayGround> pgs = new ArrayList<PlayGround>();
		try {
			
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int pg_id = resultSet.getInt(PlayGroundTable.PG_ID);
				String pg_campus = resultSet.getString(PlayGroundTable.PG_CAMPUS);
				String pg_groundname = resultSet.getString(PlayGroundTable.PG_GROUNDNAME);
				int pg_amount = resultSet.getInt(PlayGroundTable.PG_AMOUNT);
				String pg_indoorarea = resultSet.getString(PlayGroundTable.PG_INDOORAREA);
				String pg_outdoorarea = resultSet.getString(PlayGroundTable.PG_OUTDOORAREA);
				
				String pg_totalarea = resultSet.getString(PlayGroundTable.PG_TOTALAREA);
				int pg_serialnumber = resultSet.getInt(PlayGroundTable.PG_SERIALNUMBER);
				
				String pg_comments = resultSet.getString(PlayGroundTable.PG_COMMENTS);
				if(null==pg_comments){
					pg_comments="";
				}
				String pg_college = resultSet.getString(PlayGroundTable.PG_COLLEGE);
				int isnull = resultSet.getInt(PlayGroundTable.ISNULL);
				
				PlayGround pg = new PlayGround(pg_id, pg_campus, pg_groundname, pg_amount,
						pg_indoorarea, pg_outdoorarea, pg_totalarea, pg_serialnumber,pg_comments, pg_college, isnull);
				
				
				pgs.add(pg);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return pgs;
	}

	@Override
	public void deleteByCollegeandDeadline(String college)
			throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();
		String sql = "delete from " + PlayGroundTable.TABLE_NAME
				+ " where " + PlayGroundTable.PG_COLLEGE + " = '" + college + "'" +" and "
				+PlayGroundTable.PG_DEADLINE+" is null ";
		
		System.out.println(sql);
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils_DBCP.release(connection, stmt, null);
		}
	}
}
