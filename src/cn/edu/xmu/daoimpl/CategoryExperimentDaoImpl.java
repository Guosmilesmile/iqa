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

import cn.edu.xmu.dao.CategoryExperimentDao;
import cn.edu.xmu.entity.CategoryExperiment;
import cn.edu.xmu.table.CategoryExperimentTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;

/**
 * 
 * @author Gy 表5-3-3 2014-2015学年分专业（大类）实验情况 date 2015-07-5
 */

public class CategoryExperimentDaoImpl extends BaseDaoImpl<CategoryExperiment> implements
		CategoryExperimentDao {

	@Override
	public int addRecord(CategoryExperiment ce) {

		int result = 0;

		String t_sql = "update " + CategoryExperimentTable.TABLE_NAME + " set "
				+ CategoryExperimentTable.CE_SERIALNUMBER + " = "
				+ CategoryExperimentTable.CE_SERIALNUMBER + " +1 where "
				+ CategoryExperimentTable.CE_SERIALNUMBER + ">=?";

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
			t_pstmt.setInt(1, ce.getCe_serialnumber());

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
		String sql = "insert into " + CategoryExperimentTable.TABLE_NAME + "("
				+ CategoryExperimentTable.CE_COLLEGES + "," + CategoryExperimentTable.CE_MAJORNAME
				+ "," + CategoryExperimentTable.CE_CONTAINEXPERIMENT + ","
				+ CategoryExperimentTable.CE_SINLGEEXPERIMENT + ","
				+ CategoryExperimentTable.CE_NOSINGELEEXPERIMENT + ","
				+ CategoryExperimentTable.CE_DESIGNEXPERIMENT + ","
				+ CategoryExperimentTable.CE_PERCENTAGE + ","
				+ CategoryExperimentTable.CE_MAJORNUMBER + ","
				+ CategoryExperimentTable.CE_SERIALNUMBER + ","
				+ CategoryExperimentTable.CE_COLLEGE + "," + CategoryExperimentTable.CE_COMMENTS
				+ ",isnull" + ")values(?,?,?,?,?,?,?,?,?,?,'',?)";

		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, ce.getCe_colleges());
			pstmt.setString(2, ce.getCe_majorname());
			pstmt.setInt(3, ce.getCe_containexperiment());
			pstmt.setInt(4, ce.getCe_singleexperiment());
			pstmt.setInt(5, ce.getCe_nosingleexperiment());
			pstmt.setInt(6, ce.getCe_designexperiment());
			pstmt.setFloat(7, ce.getCe_percentage());
			pstmt.setString(8, ce.getCe_majornumber());
			pstmt.setInt(9, ce.getCe_serialnumber());
			pstmt.setString(10, ce.getCe_college());
			pstmt.setInt(11, ce.getIsnull());
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
	public int getCategoryExperimentCount(Map queryParams) {

		int count = 0;
		// 获取条件的语句
		String sql = "select count(*) from " + CategoryExperimentTable.TABLE_NAME + " where 1 = 1";
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
		sql += String.format(" or %s ='%s'", CategoryExperimentTable.CE_COLLEGE, "");

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
	public List<CategoryExperiment> getAllCategoryExperiment(int start, int end, String sortStr,
			String orderStr, Map queryParams) {

		String sql = " select tmp.* from ( " + " select * from "
				+ CategoryExperimentTable.TABLE_NAME + " where 1=1 ";

		for (Object object : queryParams.keySet()) {
			String key = object.toString();
			String value = queryParams.get(key).toString();
			sql += String.format(" and %s like  '%%%s%%'", key, value);
		}
		sql += " order by " + sortStr + " " + orderStr + " ) tmp limit " + start + " ," + end;

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		List<CategoryExperiment> ces = new ArrayList<CategoryExperiment>();
		try {

			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int ce_id = resultSet.getInt(CategoryExperimentTable.CE_ID);
				String ce_colleges = resultSet.getString(CategoryExperimentTable.CE_COLLEGES);
				String ce_majorname = resultSet.getString(CategoryExperimentTable.CE_MAJORNAME);
				Integer ce_containexperiment = resultSet
						.getInt(CategoryExperimentTable.CE_CONTAINEXPERIMENT);
				if (ce_containexperiment == -999)
					ce_containexperiment = null;
				Integer ce_singleexperiment = resultSet
						.getInt(CategoryExperimentTable.CE_SINLGEEXPERIMENT);
				if (ce_singleexperiment == -999)
					ce_singleexperiment = null;
				Integer ce_nosingleexperiment = resultSet
						.getInt(CategoryExperimentTable.CE_NOSINGELEEXPERIMENT);
				if (ce_nosingleexperiment == -999)
					ce_nosingleexperiment = null;
				Integer ce_designexperiment = resultSet
						.getInt(CategoryExperimentTable.CE_DESIGNEXPERIMENT);
				if (ce_designexperiment == -999)
					ce_designexperiment = null;
				Float ce_percentage = resultSet.getFloat(CategoryExperimentTable.CE_PERCENTAGE);
				if (ce_percentage == -999)
					ce_percentage = null;
				String ce_majornumber = resultSet.getString(CategoryExperimentTable.CE_MAJORNUMBER);
				int ce_serialnumber = resultSet.getInt(CategoryExperimentTable.CE_SERIALNUMBER);
				String ce_college = resultSet.getString(CategoryExperimentTable.CE_COLLEGE);
				String ce_comments = resultSet.getString(CategoryExperimentTable.CE_COMMENTS);
				Date ce_deadline = resultSet.getDate(CategoryExperimentTable.CE_DEADLINE);
				CategoryExperiment ce = new CategoryExperiment(ce_id, ce_colleges, ce_majorname,
						ce_containexperiment, ce_singleexperiment, ce_nosingleexperiment,
						ce_designexperiment, ce_percentage, ce_majornumber, ce_serialnumber,
						ce_deadline, ce_college, ce_comments);
				ces.add(ce);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return ces;
	}

	@Override
	public List<CategoryExperiment> getAllCategoryExperiment() {

		String sql = " select * from " + CategoryExperimentTable.TABLE_NAME + " where 1=1 "
				+ " order by " + CategoryExperimentTable.CE_ID;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		List<CategoryExperiment> ces = new ArrayList<CategoryExperiment>();
		try {

			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int ce_id = resultSet.getInt(CategoryExperimentTable.CE_ID);
				String ce_colleges = resultSet.getString(CategoryExperimentTable.CE_COLLEGES);
				String ce_majorname = resultSet.getString(CategoryExperimentTable.CE_MAJORNAME);
				Integer ce_containexperiment = resultSet
						.getInt(CategoryExperimentTable.CE_CONTAINEXPERIMENT);
				if (ce_containexperiment == -999)
					ce_containexperiment = null;
				Integer ce_singleexperiment = resultSet
						.getInt(CategoryExperimentTable.CE_SINLGEEXPERIMENT);
				if (ce_singleexperiment == -999)
					ce_singleexperiment = null;
				Integer ce_nosingleexperiment = resultSet
						.getInt(CategoryExperimentTable.CE_NOSINGELEEXPERIMENT);
				if (ce_nosingleexperiment == -999)
					ce_nosingleexperiment = null;
				Integer ce_designexperiment = resultSet
						.getInt(CategoryExperimentTable.CE_DESIGNEXPERIMENT);
				if (ce_designexperiment == -999)
					ce_designexperiment = null;
				Float ce_percentage = resultSet.getFloat(CategoryExperimentTable.CE_PERCENTAGE);
				if (ce_percentage == -999)
					ce_percentage = null;
				String ce_majornumber = resultSet.getString(CategoryExperimentTable.CE_MAJORNUMBER);
				int ce_serialnumber = resultSet.getInt(CategoryExperimentTable.CE_SERIALNUMBER);
				String ce_college = resultSet.getString(CategoryExperimentTable.CE_COLLEGE);
				String ce_comments = resultSet.getString(CategoryExperimentTable.CE_COMMENTS);
				Date ce_deadline = resultSet.getDate(CategoryExperimentTable.CE_DEADLINE);
				CategoryExperiment ce = new CategoryExperiment(ce_id, ce_colleges, ce_majorname,
						ce_containexperiment, ce_singleexperiment, ce_nosingleexperiment,
						ce_designexperiment, ce_percentage, ce_majornumber, ce_serialnumber,
						ce_deadline, ce_college, ce_comments);
				ces.add(ce);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return ces;
	}

	@Override
	public int alterCategoryExperiment(Map<String, String> valueMap, String id) {

		Map<String, String> condition = new HashMap<String, String>();
		condition.put(CategoryExperimentTable.CE_ID, id);
		int result = updateRecord(CategoryExperimentTable.TABLE_NAME, valueMap, condition);
		return result;
	}

	@Override
	public boolean batchDelete(String[] seuids) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();

		for (String seuid : seuids) {
			String sql = "delete from " + CategoryExperimentTable.TABLE_NAME + " where "
					+ CategoryExperimentTable.CE_ID + " = '" + seuid + "'";
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
	public void deleteByCollegeandDeadline(String college, Date deadline) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();
		String sql = "delete from " + CategoryExperimentTable.TABLE_NAME + " where "
				+ CategoryExperimentTable.CE_COLLEGE + " = '" + college + "'"
				+ " and ce_deadline is null ";
		System.out.println(sql);
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, stmt, null);
		}

	}

}
