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

import cn.edu.xmu.dao.GoInterConferDao;
import cn.edu.xmu.entity.GoInterConfer;
import cn.edu.xmu.table.GoInterConferTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;


/**
 * 
 * @author Lee
 * 学生参加国际会议情况 实体类功能 ——接口实现
 * date 2015-07-13
 */

public class GoInterConferDaoImpl extends BaseDaoImpl<GoInterConfer>implements GoInterConferDao{

	@Override
	public int addRecord(GoInterConfer gic) {
		
		int result = 0;

		String t_sql = "update " + GoInterConferTable.TABLE_NAME + " set "
				+ GoInterConferTable.GIC_SERIALNUMBER + " = "
				+ GoInterConferTable.GIC_SERIALNUMBER + " +1 where "
				+ GoInterConferTable.GIC_SERIALNUMBER + ">=?";

		
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
			t_pstmt.setInt(1, gic.getGic_serialnumber());
			
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
		String sql = "insert into " + GoInterConferTable.TABLE_NAME + "("
				+ GoInterConferTable.GIC_COLLEGE1 + ","
				+ GoInterConferTable.GIC_MAJOR + ","
				+ GoInterConferTable.GIC_GRADE + ","
				+ GoInterConferTable.GIC_STUNUM + ","
				+ GoInterConferTable.GIC_NAME + ","
				+ GoInterConferTable.GIC_INTERNAME+","
				+ GoInterConferTable.GIC_PAPERORTITLE+","
				+ GoInterConferTable.GIC_SERIALNUMBER+","
				+ GoInterConferTable.GIC_COLLEGE+","
				+ GoInterConferTable.ISNULL
				+ ")values(?,?,?,?,?,?,?,?,?,?)";

		System.out.println(sql);
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, gic.getGic_college1());
			pstmt.setString(2, gic.getGic_major());
			pstmt.setString(3, gic.getGic_grade());
			pstmt.setString(4, gic.getGic_stunum());
			pstmt.setString(5, gic.getGic_name());
			pstmt.setString(6, gic.getGic_intername());
			pstmt.setString(7, gic.getGic_paperortitle());
			pstmt.setInt(8, gic.getGic_serialnumber());
			pstmt.setString(9, gic.getGic_college());
			pstmt.setInt(10, gic.getIsnull());
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
	public int getGoInterConferCount(Map queryParams) {
		
		int count = 0;
		//获取条件的语句
		String sql = "select count(*) from " + GoInterConferTable.TABLE_NAME
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
		sql += String.format(" or %s ='%s'", GoInterConferTable.GIC_COLLEGE, "");
		
		
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
	public List<GoInterConfer> getAllGoInterConfer(int start, int end,
			String sortStr, String orderStr, Map queryParams) {
		
		String sql = " select tmp.* from ( " + " select * from "
				+ GoInterConferTable.TABLE_NAME + " where 1=1 ";
		
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
		
		List<GoInterConfer> gics = new ArrayList<GoInterConfer>();
		try {
			
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int gic_id = resultSet.getInt(GoInterConferTable.GIC_ID);
				String gic_college1 = resultSet.getString(GoInterConferTable.GIC_COLLEGE1);
				String gic_major = resultSet.getString(GoInterConferTable.GIC_MAJOR);
				String gic_grade = resultSet.getString(GoInterConferTable.GIC_GRADE);
				String gic_stunum = resultSet.getString(GoInterConferTable.GIC_STUNUM);
				String gic_name = resultSet.getString(GoInterConferTable.GIC_NAME);
				
				String gic_intername = resultSet.getString(GoInterConferTable.GIC_INTERNAME);
				String gic_paperortitle = resultSet.getString(GoInterConferTable.GIC_PAPERORTITLE);
				
				int gic_serialnumber = resultSet.getInt(GoInterConferTable.GIC_SERIALNUMBER);
				
				String gic_comments = resultSet.getString(GoInterConferTable.GIC_COMMENTS);
				if(null==gic_comments){
					gic_comments="";
				}
				String gic_college = resultSet.getString(GoInterConferTable.GIC_COLLEGE);
				int isnull = resultSet.getInt(GoInterConferTable.ISNULL);
				
				GoInterConfer gic = new GoInterConfer(gic_id, gic_college1, gic_major, gic_grade,
						gic_stunum, gic_name, gic_intername, gic_paperortitle, gic_serialnumber,
						gic_comments, gic_college, isnull);
				
				gics.add(gic);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return gics;
	}

	@Override
	public int alterGoInterConfer(Map<String, String> valueMap, String id) {
		
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(GoInterConferTable.GIC_ID, id);
		int result = updateRecord(GoInterConferTable.TABLE_NAME, valueMap,condition);
		return result;
	}
	
	
	

	@Override
	public boolean batchDelete(String[] gicids) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();

		for (String gicid : gicids) {
			String sql = "delete from " + GoInterConferTable.TABLE_NAME
					+ " where " + GoInterConferTable.GIC_ID + " = '" + gicid + "'";
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
		String sql = "delete from " + GoInterConferTable.TABLE_NAME
				+ " where " + GoInterConferTable.GIC_COLLEGE + " = '" + college + "'" +" and "
				+GoInterConferTable.GIC_DEADLINE+" is null ";
		
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
	public List<GoInterConfer> getAllGoInterConfer() {
		String sql = " select * from " + GoInterConferTable.TABLE_NAME
				+ " where 1=1 " + " order by " + GoInterConferTable.GIC_ID;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		List<GoInterConfer> gics = new ArrayList<GoInterConfer>();
		try {
			
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int gic_id = resultSet.getInt(GoInterConferTable.GIC_ID);
				String gic_college1 = resultSet.getString(GoInterConferTable.GIC_COLLEGE1);
				String gic_major = resultSet.getString(GoInterConferTable.GIC_MAJOR);
				String gic_grade = resultSet.getString(GoInterConferTable.GIC_GRADE);
				String gic_stunum = resultSet.getString(GoInterConferTable.GIC_STUNUM);
				String gic_name = resultSet.getString(GoInterConferTable.GIC_NAME);
				
				String gic_intername = resultSet.getString(GoInterConferTable.GIC_INTERNAME);
				String gic_paperortitle = resultSet.getString(GoInterConferTable.GIC_PAPERORTITLE);
				
				int gic_serialnumber = resultSet.getInt(GoInterConferTable.GIC_SERIALNUMBER);
				
				String gic_comments = resultSet.getString(GoInterConferTable.GIC_COMMENTS);
				if(null==gic_comments){
					gic_comments="";
				}
				String gic_college = resultSet.getString(GoInterConferTable.GIC_COLLEGE);
				int isnull = resultSet.getInt(GoInterConferTable.ISNULL);
				
				GoInterConfer gic = new GoInterConfer(gic_id, gic_college1, gic_major, gic_grade,
						gic_stunum, gic_name, gic_intername, gic_paperortitle, gic_serialnumber,
						gic_comments, gic_college, isnull);
				
				gics.add(gic);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return gics;
	}
}
