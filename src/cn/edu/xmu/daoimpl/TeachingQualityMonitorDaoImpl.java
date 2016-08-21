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

import cn.edu.xmu.dao.TeachingQualityMonitorDao;
import cn.edu.xmu.entity.TeachingQualityMonitor;
import cn.edu.xmu.table.TeachingQualityMonitorTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;

/**
 * 表7-4  教学质量管理与监控（时点）
 * @author chunfeng
 *
 */
public class TeachingQualityMonitorDaoImpl extends BaseDaoImpl<TeachingQualityMonitor> implements TeachingQualityMonitorDao {

	@Override
	public List<TeachingQualityMonitor> getTeachingQualityMonitor(int start, int end,
			String sortStr, String orderStr, Map<String, String> params,Date deadline) {
		
		String sql = " select tmp.* from ( " + " select * from "
				+ TeachingQualityMonitorTable.TABLE_NAME + " where 1=1 ";
		if (deadline != null) {
			sql += String.format("and "+TeachingQualityMonitorTable.TQM_DEADLINE+" like  '%s%%' ", deadline);
		}
		if (!(params == null || params.keySet().size() == 0)) {
			for (Object object : params.keySet()) {

				String key = object.toString();
				String value = params.get(key).toString();
				sql += String.format("and %s like  '%%%s%%' ", key, value);
			}
		}
		
		sql += " order by " + sortStr + " " + orderStr + " ) tmp limit "
					+ start + " ," + end;
		
		System.out.println(sql);

		List<TeachingQualityMonitor> teachingQualityMonitors = new ArrayList<TeachingQualityMonitor>();
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		try {
			System.out.println(sql);
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				int tqm_id = resultSet.getInt(TeachingQualityMonitorTable.TQM_ID);
 
				String tqm_managesyscontent = resultSet.getString(TeachingQualityMonitorTable.TQM_MANAGESYSCONTENT);  
				String tqm_assessexe  = resultSet.getString(TeachingQualityMonitorTable.TQM_ASSESSEXE); 
				String tqm_annualreport = resultSet.getString(TeachingQualityMonitorTable.TQM_ANNUALREPORT); 
			 	
				String tqm_college  = resultSet.getString(TeachingQualityMonitorTable.TQM_COLLEGE); 
				int tqm_serialnumber = resultSet.getInt(TeachingQualityMonitorTable.TQM_SERIALNUMBER);
				Date tqm_deadline = resultSet.getDate(TeachingQualityMonitorTable.TQM_DEADLINE); 
				String tqm_comments =  resultSet.getString(TeachingQualityMonitorTable.TQM_COMMENTS);
				if(null == tqm_comments){
					tqm_comments = "";
				}
				int tqm_isnull = resultSet.getInt(TeachingQualityMonitorTable.TQM_ISNULL);
				
				TeachingQualityMonitor teachingQualityMonitor = new TeachingQualityMonitor(tqm_id, tqm_managesyscontent,
						tqm_assessexe, tqm_annualreport, tqm_college, tqm_serialnumber, tqm_deadline, tqm_comments, tqm_isnull);

				teachingQualityMonitors.add(teachingQualityMonitor);
			}
			return teachingQualityMonitors;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
	}

	@Override
	public int getTeachingQualityMonitorCount(Map queryParams) {
		// TODO Auto-generated method stub
		int count = 0;
		String sql = "select count(*) from " + TeachingQualityMonitorTable.TABLE_NAME
				+ " where 1 = 1 ";
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (queryParams != null)
		{
			for (Object object : queryParams.keySet())
			{
				String key = object.toString();
				String value = queryParams.get(key).toString();
				sql += String.format(" and  %s like  '%%%s%%' ", key, value);
			}
			// sql += String.format(" or %s ='%s'", "college","");
		}
		
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
		} finally{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}

		System.out.println(count);
		return count;
	}

	@Override
	public boolean batchDelete(String[] ppids) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();

		for (String ppid : ppids) {
			String sql = "delete from " + TeachingQualityMonitorTable.TABLE_NAME
					+ " where " + TeachingQualityMonitorTable.TQM_ID + " = '" + ppid
					+ "'";
			System.out.println(sql);
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
	public int addTeachingQualityMonitor(TeachingQualityMonitor teachingQualityMonitor) {
		// TODO Auto-generated method stub
		int result = 0;

		String sql2 = "update " + TeachingQualityMonitorTable.TABLE_NAME + " set "
				+ TeachingQualityMonitorTable.TQM_SERIALNUMBER + " = "
				+ TeachingQualityMonitorTable.TQM_SERIALNUMBER + " +1 where "
				+ TeachingQualityMonitorTable.TQM_SERIALNUMBER + ">="
				+ teachingQualityMonitor.getTqm_serialnumber();
		Connection connection2 = null;
		try {
			connection2 = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		PreparedStatement pstmt2 = null;
		try {
			pstmt2 = connection2.prepareStatement(sql2);
			result = pstmt2.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			try {
				JdbcUtils_DBCP.release(connection2, pstmt2, null);
			} catch (Exception e2) {
				return 0;
			}
		}

		String sql = "insert into " + TeachingQualityMonitorTable.TABLE_NAME + "("
				+ TeachingQualityMonitorTable.TQM_MANAGESYSCONTENT + ","
				+ TeachingQualityMonitorTable.TQM_ASSESSEXE + ","
				+ TeachingQualityMonitorTable.TQM_ANNUALREPORT + ","
				+ TeachingQualityMonitorTable.TQM_COLLEGE + ","
				+ TeachingQualityMonitorTable.TQM_SERIALNUMBER + ","
				+ TeachingQualityMonitorTable.TQM_ISNULL +")values(?,?,?,?,?,?)";

		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PreparedStatement pstmt = null;

		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, teachingQualityMonitor.getTqm_managesyscontent());
			pstmt.setString(2, teachingQualityMonitor.getTqm_assessexe());
			pstmt.setString(3, teachingQualityMonitor.getTqm_annualreport());
			pstmt.setString(4, teachingQualityMonitor.getTqm_college());
			pstmt.setInt(5, teachingQualityMonitor.getTqm_serialnumber());
			pstmt.setInt(6, teachingQualityMonitor.getTqm_isnull());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			result = -1;
		} finally{
			JdbcUtils_DBCP.release(connection, pstmt, null);
		}


		return result;
	}

	@Override
	public int alterTeachingQualityMonitor(Map<String, String> valueMap, String id) {
		// TODO Auto-generated method stub
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(TeachingQualityMonitorTable.TQM_ID, id);
		int result = updateRecord(TeachingQualityMonitorTable.TABLE_NAME, valueMap,
				condition);
		return result;
	}

	@Override
	public List<TeachingQualityMonitor> getAllTeachingQualityMonitor() {
		// TODO Auto-generated method stub
		String sql = " select * from " + TeachingQualityMonitorTable.TABLE_NAME
				+ " where 1=1 " + " order by " + TeachingQualityMonitorTable.TQM_ID;
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			System.out.println(sql);
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			List<TeachingQualityMonitor> teachingQualityMonitorList = new ArrayList<TeachingQualityMonitor>();
			while (resultSet.next()) {
				int tqm_id = resultSet.getInt(TeachingQualityMonitorTable.TQM_ID);
				 
				String tqm_managesyscontent = resultSet.getString(TeachingQualityMonitorTable.TQM_MANAGESYSCONTENT);  
				String tqm_assessexe  = resultSet.getString(TeachingQualityMonitorTable.TQM_ASSESSEXE); 
				String tqm_annualreport = resultSet.getString(TeachingQualityMonitorTable.TQM_ANNUALREPORT); 
			 	
				String tqm_college  = resultSet.getString(TeachingQualityMonitorTable.TQM_COLLEGE); 
				int tqm_serialnumber = resultSet.getInt(TeachingQualityMonitorTable.TQM_SERIALNUMBER);
				Date tqm_deadline = resultSet.getDate(TeachingQualityMonitorTable.TQM_DEADLINE); 
				String tqm_comments =  resultSet.getString(TeachingQualityMonitorTable.TQM_COMMENTS);
				if(null == tqm_comments){
					tqm_comments = "";
				}
				int tqm_isnull = resultSet.getInt(TeachingQualityMonitorTable.TQM_ISNULL);
				
				TeachingQualityMonitor teachingQualityMonitor = new TeachingQualityMonitor(tqm_id, tqm_managesyscontent,
						tqm_assessexe, tqm_annualreport, tqm_college, tqm_serialnumber, tqm_deadline, tqm_comments, tqm_isnull);

				teachingQualityMonitorList.add(teachingQualityMonitor);
			}
			return teachingQualityMonitorList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}

	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		Statement stmt = null;
		try {
			stmt = connection.createStatement();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		String sql = "delete from " + TeachingQualityMonitorTable.TABLE_NAME;
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils_DBCP.release(connection, stmt, null);
		}
	}

	@Override
	public void deleteByCollegeandDeadline(String college, Date deadline)
			throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();
		String sql = "delete from " + TeachingQualityMonitorTable.TABLE_NAME
				+ " where " + TeachingQualityMonitorTable.TQM_COLLEGE + " = '" + college + "'" +" and tqm_deadline is null ";
	//	sql += String.format(" and fe_deadline = ", null);
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
