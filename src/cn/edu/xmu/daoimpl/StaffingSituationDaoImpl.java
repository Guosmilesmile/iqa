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

import cn.edu.xmu.dao.StaffingSituationDao;
import cn.edu.xmu.entity.SchoolNet;
import cn.edu.xmu.entity.StaffingSituation;
import cn.edu.xmu.table.SchoolNetTable;
import cn.edu.xmu.table.StaffingSituationTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;

public class StaffingSituationDaoImpl extends BaseDaoImpl<StaffingSituation>implements StaffingSituationDao {

	@Override
	public int addRecord(StaffingSituation ss) {
		int result = 0;

		String t_sql = "update " + StaffingSituationTable.TABLE_NAME + " set "
				+ StaffingSituationTable.SS_SERIALNUMBER + " = "
				+ StaffingSituationTable.SS_SERIALNUMBER + " +1 where "
				+ StaffingSituationTable.SS_SERIALNUMBER + ">=?";

		
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
			t_pstmt.setInt(1, ss.getSs_serialnumber());
			
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
		String sql = "insert into " + StaffingSituationTable.TABLE_NAME + "("
				+ StaffingSituationTable.SS_CONDITION1+","
				+ StaffingSituationTable.SS_CONDITION2+","
				+ StaffingSituationTable.SS_TEACHERCOUNT+ ","
				+ StaffingSituationTable.SS_FULLTIMESTAFFCOUNT+ ","
				+ StaffingSituationTable.SS_FACULTYCOUNT + ","
				+ StaffingSituationTable.SS_SERIALNUMBER + ","
				+ StaffingSituationTable.SS_COLLEGE + ","
				+ StaffingSituationTable.SS_COMMENTS+","
				+ StaffingSituationTable.ISNULL
				+ ")values(?,?,?,?,?,?,?,?,?)";

		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, ss.getSs_condition1());
			pstmt.setString(2, ss.getSs_condition2());
			pstmt.setInt(3, ss.getSs_teachercount());
			pstmt.setInt(4, ss.getSs_fulltimestaffcount());
			pstmt.setInt(5, ss.getSs_facultycount());
			pstmt.setInt(6, ss.getSs_serialnumber());
			pstmt.setString(7, ss.getSs_college());
			pstmt.setString(8, ss.getSs_comments());
			pstmt.setInt(9, ss.getIsnull());
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
	public boolean batchDelete(String[] ssids) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();

		for (String ssid : ssids) {
			String sql = "delete from " + StaffingSituationTable.TABLE_NAME
					+ " where " + StaffingSituationTable.SS_ID + " = '" + ssid + "'";
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
	public int alterStaffingSituation(Map<String, String> valueMap, String id) {
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(StaffingSituationTable.SS_ID, id);
		int result = updateRecord(StaffingSituationTable.TABLE_NAME, valueMap,
				condition);
		return result;
	}

	@Override
	public int getStaffingSituationCount(Map queryParams) {
		int count = 0;
		//获取条件的语句
		String sql = "select count(*) from " + StaffingSituationTable.TABLE_NAME
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
		sql += String.format(" or %s ='%s'", StaffingSituationTable.SS_COLLEGE, "");
		
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
	public List<StaffingSituation> getAllStaffingSituation(int start, int end,
			String sortStr, String orderStr, Map queryParams) {
		String sql = " select tmp.* from ( " + " select * from "
				+ StaffingSituationTable.TABLE_NAME + " where 1=1 ";
		
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
		
		List<StaffingSituation> sss = new ArrayList<StaffingSituation>();
		try {
			
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int ss_id = resultSet.getInt(StaffingSituationTable.SS_ID);
				String ss_condition1 = resultSet.getString(StaffingSituationTable.SS_CONDITION1);
				String ss_condition2 = resultSet.getString(StaffingSituationTable.SS_CONDITION2);
				Integer ss_teachercount = resultSet.getInt(StaffingSituationTable.SS_TEACHERCOUNT);
				if(ss_teachercount==-999)
					ss_teachercount = null;
				
				Integer ss_fulltimestaffcount = resultSet.getInt(StaffingSituationTable.SS_FULLTIMESTAFFCOUNT);
				if(ss_fulltimestaffcount==-999)
					ss_fulltimestaffcount = null;
				
				Integer ss_facultycount = resultSet.getInt(StaffingSituationTable.SS_FACULTYCOUNT);
				if(ss_facultycount==-999)
					ss_facultycount = null;
				
				int ss_serialnumber = resultSet.getInt(StaffingSituationTable.SS_SERIALNUMBER);
				String ss_comments = resultSet.getString(StaffingSituationTable.SS_COMMENTS);
				String ss_college = resultSet.getString(StaffingSituationTable.SS_COLLEGE);
				int isnull = resultSet.getInt(StaffingSituationTable.ISNULL);
				if(ss_comments==null)
					ss_comments="";
				
				StaffingSituation ss = new StaffingSituation(ss_id,ss_condition1,ss_condition2, ss_teachercount,
						ss_fulltimestaffcount, ss_facultycount, ss_serialnumber, ss_comments,isnull,ss_college);
				
				sss.add(ss);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return sss;
	}

	@Override
	public List<StaffingSituation> getAllStaffingSituation() {
		String sql = " select * from "
				+ StaffingSituationTable.TABLE_NAME + " where 1=1 ";
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		List<StaffingSituation> sss = new ArrayList<StaffingSituation>();
		try {
			
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int ss_id = resultSet.getInt(StaffingSituationTable.SS_ID);
				String ss_condition1 = resultSet.getString(StaffingSituationTable.SS_CONDITION1);
				String ss_condition2 = resultSet.getString(StaffingSituationTable.SS_CONDITION2);
				Integer ss_teachercount = resultSet.getInt(StaffingSituationTable.SS_TEACHERCOUNT);
				if(ss_teachercount==-999)
					ss_teachercount = null;
				
				Integer ss_fulltimestaffcount = resultSet.getInt(StaffingSituationTable.SS_FULLTIMESTAFFCOUNT);
				if(ss_fulltimestaffcount==-999)
					ss_fulltimestaffcount = null;
				
				Integer ss_facultycount = resultSet.getInt(StaffingSituationTable.SS_FACULTYCOUNT);
				if(ss_facultycount==-999)
					ss_facultycount = null;
				
				int ss_serialnumber = resultSet.getInt(StaffingSituationTable.SS_SERIALNUMBER);
				String ss_comments = resultSet.getString(StaffingSituationTable.SS_COMMENTS);
				int isnull = resultSet.getInt(StaffingSituationTable.ISNULL);
				String ss_college = resultSet.getString(StaffingSituationTable.SS_COLLEGE);
				if(ss_comments==null)
					ss_comments="";
				
				StaffingSituation ss = new StaffingSituation(ss_id,ss_condition1,ss_condition2, ss_teachercount,
						ss_fulltimestaffcount, ss_facultycount, ss_serialnumber, ss_comments,isnull,ss_college);
				
				sss.add(ss);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return sss;
	}

	@Override
	public void deleteByCollegeandDeadline(String college, Date deadline)
			throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();
		String sql = "delete from " + StaffingSituationTable.TABLE_NAME
				+ " where " + StaffingSituationTable.SS_COLLEGE + " = '" + college + "'" +" and ss_deadline is null ";
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
