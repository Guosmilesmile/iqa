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

import cn.edu.xmu.dao.PublishedMaterialsDao;
import cn.edu.xmu.entity.PublishedMaterials;
import cn.edu.xmu.entity.SchoolNet;
import cn.edu.xmu.table.PublishedMaterialsTable;
import cn.edu.xmu.table.SchoolNetTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;

/**
 * 
 * @author Luo 附表5-2-1出版教材情况（自然年） 实体类功能接口 date 2015-07-08
 */
public class PublishedMaterialsDaoImpl extends BaseDaoImpl<PublishedMaterials>
		implements PublishedMaterialsDao {

	@Override
	public int addRecord(PublishedMaterials pm) {
		int result = 0;

		String t_sql = "update " + PublishedMaterialsTable.TABLE_NAME + " set "
				+ PublishedMaterialsTable.PM_SERIALNUMBER + " = "
				+ PublishedMaterialsTable.PM_SERIALNUMBER + " +1 where "
				+ PublishedMaterialsTable.PM_SERIALNUMBER + ">=?";

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
			t_pstmt.setInt(1, pm.getPm_serialnumber());

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
		String sql = "insert into " + PublishedMaterialsTable.TABLE_NAME + "("
				+ PublishedMaterialsTable.PM_IMPORTCOLLEGE + ","
				+ PublishedMaterialsTable.PM_MATERIALSNAME + ","
				+ PublishedMaterialsTable.PM_AUTHOR + ","
				+ PublishedMaterialsTable.PM_TYPE + ","
				+ PublishedMaterialsTable.PM_PUBLISHER + ","
				+ PublishedMaterialsTable.PM_PUBLISHYEAR + ","
				+ PublishedMaterialsTable.PM_ENGINEERINGMATERIALS + ","
				+ PublishedMaterialsTable.PM_FORTEACHING + ","
				+ PublishedMaterialsTable.PM_SERIALNUMBER + ","
				+ PublishedMaterialsTable.PM_COLLEGE + ","
				+ PublishedMaterialsTable.PM_COMMENTS+","
				+ PublishedMaterialsTable.ISNULL
				+ ")values(?,?,?,?,?,?,?,?,?,?,?,?)";

		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, pm.getPm_importcollege());
			pstmt.setString(2, pm.getPm_materialsname());
			pstmt.setString(3, pm.getPm_author());
			pstmt.setString(4, pm.getPm_type());
			pstmt.setString(5, pm.getPm_publisher());
			pstmt.setString(6, pm.getPm_publishyear());
			pstmt.setString(7, pm.getPm_engineeringmaterials());
			pstmt.setString(8, pm.getPm_forteaching());
			pstmt.setInt(9, pm.getPm_serialnumber());
			pstmt.setString(10, pm.getPm_college());
			pstmt.setString(11, pm.getPm_comments());
			pstmt.setInt(12, pm.getIsnull());
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
	public boolean batchDelete(String[] pmids) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();

		for (String pmid : pmids) {
			String sql = "delete from " + PublishedMaterialsTable.TABLE_NAME
					+ " where " + PublishedMaterialsTable.PM_ID + " = '" + pmid
					+ "'";
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
	public int alterPublishedMaterials(Map<String, String> valueMap, String id) {
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(PublishedMaterialsTable.PM_ID, id);
		int result = updateRecord(PublishedMaterialsTable.TABLE_NAME, valueMap,
				condition);
		return result;
	}

	@Override
	public int getPublishedMaterialsCount(Map queryParams) {
		int count = 0;
		// 获取条件的语句
		String sql = "select count(*) from "
				+ PublishedMaterialsTable.TABLE_NAME + " where 1 = 1";
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
		sql += String.format(" or %s ='%s'",
				PublishedMaterialsTable.PM_COLLEGE, "");

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
	public List<PublishedMaterials> getAllPublishedMaterials(int start,
			int end, String sortStr, String orderStr, Map queryParams) {
		String sql = " select tmp.* from ( " + " select * from "
				+ PublishedMaterialsTable.TABLE_NAME + " where 1=1 ";

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

		List<PublishedMaterials> pms = new ArrayList<PublishedMaterials>();
		try {

			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int pm_id = resultSet.getInt(PublishedMaterialsTable.PM_ID);
				String pm_importcollege = resultSet
						.getString(PublishedMaterialsTable.PM_IMPORTCOLLEGE);
				String pm_materialsname = resultSet
						.getString(PublishedMaterialsTable.PM_MATERIALSNAME);
				String pm_author = resultSet
						.getString(PublishedMaterialsTable.PM_AUTHOR);
				String pm_type = resultSet
						.getString(PublishedMaterialsTable.PM_TYPE);
				String pm_publisher = resultSet
						.getString(PublishedMaterialsTable.PM_PUBLISHER);
				String pm_publishyear = resultSet
						.getString(PublishedMaterialsTable.PM_PUBLISHYEAR);
				String pm_engineeringmaterials = resultSet
						.getString(PublishedMaterialsTable.PM_ENGINEERINGMATERIALS);
				String pm_forteaching = resultSet
						.getString(PublishedMaterialsTable.PM_FORTEACHING);
				int pm_serialnumber = resultSet
						.getInt(PublishedMaterialsTable.PM_SERIALNUMBER);
				String pm_comments = resultSet
						.getString(PublishedMaterialsTable.PM_COMMENTS);
				String pm_college = resultSet
						.getString(PublishedMaterialsTable.PM_COLLEGE);

				int isnull = resultSet.getInt(PublishedMaterialsTable.ISNULL);
				if(pm_comments==null)
					pm_comments="";
				
				PublishedMaterials pm = new PublishedMaterials(pm_id,
						pm_importcollege, pm_materialsname, pm_author, pm_type,
						pm_publisher, pm_publishyear, pm_engineeringmaterials,
						pm_forteaching, pm_serialnumber, pm_comments,isnull,pm_college);

				pms.add(pm);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return pms;
	}

	@Override
	public List<PublishedMaterials> getAllPublishedMaterials() {
		String sql = " select * from "
				+ PublishedMaterialsTable.TABLE_NAME + " where 1=1 ";

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		List<PublishedMaterials> pms = new ArrayList<PublishedMaterials>();
		try {

			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int pm_id = resultSet.getInt(PublishedMaterialsTable.PM_ID);
				String pm_importcollege = resultSet
						.getString(PublishedMaterialsTable.PM_IMPORTCOLLEGE);
				String pm_materialsname = resultSet
						.getString(PublishedMaterialsTable.PM_MATERIALSNAME);
				String pm_author = resultSet
						.getString(PublishedMaterialsTable.PM_AUTHOR);
				String pm_type = resultSet
						.getString(PublishedMaterialsTable.PM_TYPE);
				String pm_publisher = resultSet
						.getString(PublishedMaterialsTable.PM_PUBLISHER);
				String pm_publishyear = resultSet
						.getString(PublishedMaterialsTable.PM_PUBLISHYEAR);
				String pm_engineeringmaterials = resultSet
						.getString(PublishedMaterialsTable.PM_ENGINEERINGMATERIALS);
				String pm_forteaching = resultSet
						.getString(PublishedMaterialsTable.PM_FORTEACHING);
				int pm_serialnumber = resultSet
						.getInt(PublishedMaterialsTable.PM_SERIALNUMBER);
				String pm_comments = resultSet
						.getString(PublishedMaterialsTable.PM_COMMENTS);
				String pm_college = resultSet
						.getString(PublishedMaterialsTable.PM_COLLEGE);

				int isnull = resultSet.getInt(PublishedMaterialsTable.ISNULL);
				if(pm_comments==null)
					pm_comments="";
				
				PublishedMaterials pm = new PublishedMaterials(pm_id,
						pm_importcollege, pm_materialsname, pm_author, pm_type,
						pm_publisher, pm_publishyear, pm_engineeringmaterials,
						pm_forteaching, pm_serialnumber, pm_comments,isnull,pm_college);

				pms.add(pm);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return pms;
	}

	@Override
	public void deleteByCollegeandDeadline(String college, Date deadline)
			throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();
		String sql = "delete from " + PublishedMaterialsTable.TABLE_NAME
				+ " where " + PublishedMaterialsTable.PM_COLLEGE + " = '" + college + "'" +" and pm_deadline is null ";
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
