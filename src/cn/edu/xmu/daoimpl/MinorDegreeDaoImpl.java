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

import cn.edu.xmu.dao.MinorDegreeDao;
import cn.edu.xmu.entity.MinorDegree;
import cn.edu.xmu.entity.SchoolNet;
import cn.edu.xmu.table.MinorDegreeTable;
import cn.edu.xmu.table.SchoolNetTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;

public class MinorDegreeDaoImpl extends BaseDaoImpl<MinorDegree> implements
		MinorDegreeDao {

	@Override
	public int addRecord(MinorDegree md) {
		int result = 0;

		String t_sql = "update " + MinorDegreeTable.TABLE_NAME + " set "
				+ MinorDegreeTable.MD_SERIALNUMBER + " = "
				+ MinorDegreeTable.MD_SERIALNUMBER + " +1 where "
				+ MinorDegreeTable.MD_SERIALNUMBER + ">=?";

		Connection connection2 = null;
		try {
			// 连接池取得对象连接
			connection2 = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		PreparedStatement t_pstmt = null;
		try {
			// 获取操作对象
			t_pstmt = connection2.prepareStatement(t_sql);
			t_pstmt.setInt(1, md.getMd_serialnumber());

			// 执行插入操作并更新
			result = t_pstmt.executeUpdate();

		} catch (SQLException e) {
			// 事务回滚，不做插入操作
			e.printStackTrace();
			return 0;
		} finally {
			JdbcUtils_DBCP.release(connection2, t_pstmt, null);
		}

		// 执行插入操作的语句
		String sql = "insert into " + MinorDegreeTable.TABLE_NAME + "("
				+ MinorDegreeTable.MD_ORDER + ","
				+ MinorDegreeTable.MD_IMPORTCOLLEGE + ","
				+ MinorDegreeTable.MD_MAJOR + ","
				+ MinorDegreeTable.MD_MINORDEGREEECOUNT + ","
				+ MinorDegreeTable.MD_MINORCERTIFICATECOUNT + ","
				+ MinorDegreeTable.MD_SERIALNUMBER + ","
				+ MinorDegreeTable.MD_COLLEGE + ","
				+ MinorDegreeTable.MD_COMMENTS + ","
				+ MinorDegreeTable.ISNULL + 
				")values(?,?,?,?,?,?,?,?,?)";

		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, md.getMd_order());
			pstmt.setString(2, md.getMd_importcollege());
			pstmt.setString(3, md.getMd_major());
			pstmt.setInt(4, md.getMd_minordegreecount());
			pstmt.setInt(5, md.getMd_minorcertificatecount());
			pstmt.setInt(6, md.getMd_serialnumber());
			pstmt.setString(7, md.getMd_college());
			pstmt.setString(8, md.getMd_comments());
			pstmt.setInt(9, md.getIsnull());
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			// 事务回滚。不做插入操作
			e.printStackTrace();
			result = -1;

		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, null);
		}
		return result;
	}

	@Override
	public boolean batchDelete(String[] mdids) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();

		for (String mdid : mdids) {
			String sql = "delete from " + MinorDegreeTable.TABLE_NAME
					+ " where " + MinorDegreeTable.MD_ID + " = '" + mdid + "'";
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
	public int alterMinorDegree(Map<String, String> valueMap, String id) {
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(MinorDegreeTable.MD_ID, id);
		int result = updateRecord(MinorDegreeTable.TABLE_NAME, valueMap,
				condition);
		return result;
	}

	@Override
	public int getMinorDegreeCount(Map queryParams) {
		int count = 0;
		// 获取条件的语句
		String sql = "select count(*) from " + MinorDegreeTable.TABLE_NAME
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
		sql += String.format(" or %s ='%s'", MinorDegreeTable.MD_COLLEGE, "");

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
	public List<MinorDegree> getAllMinorDegree(int start, int end,
			String sortStr, String orderStr, Map queryParams) {
		String sql = " select tmp.* from ( " + " select * from "
				+ MinorDegreeTable.TABLE_NAME + " where 1=1 ";

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

		List<MinorDegree> mds = new ArrayList<MinorDegree>();
		try {

			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int md_id = resultSet.getInt(MinorDegreeTable.MD_ID);
				Integer md_order = resultSet.getInt(MinorDegreeTable.MD_ORDER);
				if(md_order==-999)
					md_order = null;
				
				String md_importcollege = resultSet
						.getString(MinorDegreeTable.MD_IMPORTCOLLEGE);
				String md_major = resultSet
						.getString(MinorDegreeTable.MD_MAJOR);
				Integer md_minordegreecount = resultSet
						.getInt(MinorDegreeTable.MD_MINORDEGREEECOUNT);
				if(md_minordegreecount==-999)
					md_minordegreecount = null;
				
				Integer md_minorcertificatecount = resultSet
						.getInt(MinorDegreeTable.MD_MINORCERTIFICATECOUNT);
				if(md_minorcertificatecount==-999)
					md_minorcertificatecount = null;
				
				int md_serialnumber = resultSet
						.getInt(MinorDegreeTable.MD_SERIALNUMBER);

				
				String md_comments = resultSet
						.getString(MinorDegreeTable.MD_COMMENTS);
				
				String md_college = resultSet
						.getString(MinorDegreeTable.MD_COLLEGE);

				int isnull = resultSet.getInt(MinorDegreeTable.ISNULL);
				if(md_comments==null)
					md_comments="";
				
				MinorDegree md = new MinorDegree(md_id, md_order,
						md_importcollege, md_major, md_minordegreecount,
						md_minorcertificatecount, md_serialnumber, md_comments,isnull,md_college);

				mds.add(md);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return mds;
	}

	@Override
	public List<MinorDegree> getAllMinorDegree() {
		String sql =  " select * from "
				+ MinorDegreeTable.TABLE_NAME + " where 1=1 ";

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		List<MinorDegree> mds = new ArrayList<MinorDegree>();
		try {

			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int md_id = resultSet.getInt(MinorDegreeTable.MD_ID);
				Integer md_order = resultSet.getInt(MinorDegreeTable.MD_ORDER);
				if(md_order==-999)
					md_order = null;
				
				String md_importcollege = resultSet
						.getString(MinorDegreeTable.MD_IMPORTCOLLEGE);
				String md_major = resultSet
						.getString(MinorDegreeTable.MD_MAJOR);
				Integer md_minordegreecount = resultSet
						.getInt(MinorDegreeTable.MD_MINORDEGREEECOUNT);
				if(md_minordegreecount==-999)
					md_minordegreecount = null;
				
				Integer md_minorcertificatecount = resultSet
						.getInt(MinorDegreeTable.MD_MINORCERTIFICATECOUNT);
				if(md_minorcertificatecount==-999)
					md_minorcertificatecount = null;
				
				int md_serialnumber = resultSet
						.getInt(MinorDegreeTable.MD_SERIALNUMBER);

				
				String md_comments = resultSet
						.getString(MinorDegreeTable.MD_COMMENTS);

				String md_college = resultSet
						.getString(MinorDegreeTable.MD_COLLEGE);
				
				int isnull = resultSet.getInt(MinorDegreeTable.ISNULL);
				if(md_comments==null)
					md_comments="";
				
				MinorDegree md = new MinorDegree(md_id, md_order,
						md_importcollege, md_major, md_minordegreecount,
						md_minorcertificatecount, md_serialnumber, md_comments,isnull,md_college);

				mds.add(md);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return mds;
	}

	@Override
	public void deleteByCollegeandDeadline(String college, Date deadline)
			throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();
		String sql = "delete from " + MinorDegreeTable.TABLE_NAME
				+ " where " + MinorDegreeTable.MD_COLLEGE + " = '" + college + "'" +" and md_deadline is null ";
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
