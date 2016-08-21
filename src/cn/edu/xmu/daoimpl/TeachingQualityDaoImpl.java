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

import cn.edu.xmu.dao.TeachingQualityDao;
import cn.edu.xmu.entity.SchoolNet;
import cn.edu.xmu.entity.TeachingQuality;
import cn.edu.xmu.table.SchoolNetTable;
import cn.edu.xmu.table.TeachingQualityTable;
import cn.edu.xmu.table.TextbookAwardTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;

public class TeachingQualityDaoImpl extends BaseDaoImpl<TeachingQuality>implements TeachingQualityDao {

	@Override
	public int addRecord(TeachingQuality tq) {
		int result = 0;

		String t_sql = "update " + TeachingQualityTable.TABLE_NAME + " set "
				+ TeachingQualityTable.TQ_SERIALNUMBER + " = "
				+ TeachingQualityTable.TQ_SERIALNUMBER + " +1 where "
				+ TeachingQualityTable.TQ_SERIALNUMBER + ">=?";

		
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
			t_pstmt.setInt(1, tq.getTq_serialnumber());
			
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
		String sql = "insert into " + TeachingQualityTable.TABLE_NAME + "("
				+ TeachingQualityTable.TQ_PROJECT
				+ "," + TeachingQualityTable.TQ_COVERPERCENT+ ","
				+ TeachingQualityTable.TQ_EXCELLENT + ","
				+ TeachingQualityTable.TQ_GOOD + ","
				+ TeachingQualityTable.TQ_MEDIUN + ","
				+ TeachingQualityTable.TQ_POOR + ","
				+ TeachingQualityTable.TQ_SERIALNUMBER + ","
				+ TeachingQualityTable.TQ_COLLEGE + ","
				+ TeachingQualityTable.TQ_COMMENTS+","
				+ TeachingQualityTable.ISNULL
				+ ")values(?,?,?,?,?,?,?,?,?,?)";

		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, tq.getTq_project());
			pstmt.setFloat(2, tq.getTq_coverpercent());
			pstmt.setFloat(3, tq.getTq_excellent());
			pstmt.setFloat(4, tq.getTq_good());
			pstmt.setFloat(5, tq.getTq_medium());
			pstmt.setFloat(6, tq.getTq_poor());
			pstmt.setInt(7, tq.getTq_serialnumber());
			pstmt.setString(8, tq.getTq_college());
			pstmt.setString(9, tq.getTq_comments());
			pstmt.setInt(10, tq.getIsnull());
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
	public boolean batchDelete(String[] tqids) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();

		for (String tqid : tqids) {
			String sql = "delete from " + TeachingQualityTable.TABLE_NAME
					+ " where " + TeachingQualityTable.TQ_ID + " = '" + tqid + "'";
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
	public int alterTeachingQuality(Map<String, String> valueMap, String id) {
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(TeachingQualityTable.TQ_ID, id);
		int result = updateRecord(TeachingQualityTable.TABLE_NAME, valueMap,
				condition);
		return result;
	}

	@Override
	public int getTeachingQualityCount(Map queryParams) {
		int count = 0;
		//获取条件的语句
		String sql = "select count(*) from " + TeachingQualityTable.TABLE_NAME
				+ " where 1 = 1";
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		if (queryParams != null) {
			for (Object object : queryParams.keySet()) {
				String key = object.toString();
				String value = queryParams.get(key).toString();
				sql += String.format(" and  %s like  '%s%%' ", key, value);
			}
		}
		
		sql += String.format(" or %s ='%s'", TeachingQualityTable.TQ_COLLEGE, "");
		
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
	public List<TeachingQuality> getAllTeachingQuality(int start, int end,
			String sortStr, String orderStr, Map queryParams) {
		String sql = " select tmp.* from ( " + " select * from "
				+ TeachingQualityTable.TABLE_NAME + " where 1=1 ";
		
		if (queryParams != null) {
			for (Object object : queryParams.keySet()) {
				String key = object.toString();
				String value = queryParams.get(key).toString();
				sql += String.format(" and %s like  '%%%s%%'", key, value);
			}
		}
		
		sql += " order by " + sortStr + " " + orderStr + " ) tmp limit "
				+ start + " ," + end;

		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		List<TeachingQuality> tqs = new ArrayList<TeachingQuality>();
		try {
			
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int tq_id = resultSet.getInt(TeachingQualityTable.TQ_ID);
				String tq_project = resultSet.getString(TeachingQualityTable.TQ_PROJECT);
				Float tq_coverpercent = resultSet.getFloat(TeachingQualityTable.TQ_COVERPERCENT);
				if(tq_coverpercent==-999)
					tq_coverpercent = null;
				
				Float tq_excellent = resultSet.getFloat(TeachingQualityTable.TQ_EXCELLENT);
				if(tq_excellent==-999)
					tq_excellent = null;
				
				Float tq_good = resultSet.getFloat(TeachingQualityTable.TQ_GOOD);
				if(tq_good==-999)
					tq_good = null;
				
				Float tq_medium = resultSet.getFloat(TeachingQualityTable.TQ_MEDIUN);
				if(tq_medium==-999)
					tq_medium = null;
				
				Float tq_poor = resultSet.getFloat(TeachingQualityTable.TQ_POOR);
				if(tq_poor==-999)
					tq_poor = null;
				
				int tq_serialnumber = resultSet.getInt(TeachingQualityTable.TQ_SERIALNUMBER);
				String tq_comments = resultSet.getString(TeachingQualityTable.TQ_COMMENTS);
				int isnull = resultSet.getInt(TeachingQualityTable.ISNULL);
				String tq_college = resultSet.getString(TeachingQualityTable.TQ_COLLEGE);
				
				if(tq_comments==null)
					tq_comments="";
				
				TeachingQuality tq = new TeachingQuality(tq_id, tq_project,
						tq_coverpercent, tq_excellent,tq_good,tq_medium, tq_poor,tq_serialnumber, tq_comments,isnull,tq_college);
				
				tqs.add(tq);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return tqs;
	}

	@Override
	public List<TeachingQuality> getAllTeachingQuality() {
		String sql = " select * from "
				+ TeachingQualityTable.TABLE_NAME + " where 1=1 ";

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		List<TeachingQuality> tqs = new ArrayList<TeachingQuality>();
		try {
			
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int tq_id = resultSet.getInt(TeachingQualityTable.TQ_ID);
				String tq_project = resultSet.getString(TeachingQualityTable.TQ_PROJECT);
				Float tq_coverpercent = resultSet.getFloat(TeachingQualityTable.TQ_COVERPERCENT);
				if(tq_coverpercent==-999)
					tq_coverpercent = null;
				
				Float tq_excellent = resultSet.getFloat(TeachingQualityTable.TQ_EXCELLENT);
				if(tq_excellent==-999)
					tq_excellent = null;
				
				Float tq_good = resultSet.getFloat(TeachingQualityTable.TQ_GOOD);
				if(tq_good==-999)
					tq_good = null;
				
				Float tq_medium = resultSet.getFloat(TeachingQualityTable.TQ_MEDIUN);
				if(tq_medium==-999)
					tq_medium = null;
				
				Float tq_poor = resultSet.getFloat(TeachingQualityTable.TQ_POOR);
				if(tq_poor==-999)
					tq_poor = null;
				
				int tq_serialnumber = resultSet.getInt(TeachingQualityTable.TQ_SERIALNUMBER);
				String tq_comments = resultSet.getString(TeachingQualityTable.TQ_COMMENTS);
				int isnull = resultSet.getInt(TeachingQualityTable.ISNULL);
				String tq_college = resultSet.getString(TeachingQualityTable.TQ_COLLEGE);
				
				if(tq_comments==null)
					tq_comments="";
				
				TeachingQuality tq = new TeachingQuality(tq_id, tq_project,
						tq_coverpercent, tq_excellent,tq_good,tq_medium, tq_poor,tq_serialnumber, tq_comments,isnull,tq_college);
				
				tqs.add(tq);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return tqs;
	}

	@Override
	public void deleteByCollegeandDeadline(String college, Date deadline)
			throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();
		String sql = "delete from " + TeachingQualityTable.TABLE_NAME
				+ " where " + TeachingQualityTable.TQ_COLLEGE + " = '" + college + "'" +" and tq_deadline is null ";
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
