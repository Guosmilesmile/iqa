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

import cn.edu.xmu.dao.SchoolAddressDao;
import cn.edu.xmu.dao.SchoolExecutiveUnitDao;
import cn.edu.xmu.entity.SchoolAddress;
import cn.edu.xmu.entity.SchoolExecutiveUnit;
import cn.edu.xmu.table.BenkeMentalHealthTable;
import cn.edu.xmu.table.ClassCultureTable;
import cn.edu.xmu.table.SchoolAddressTable;
import cn.edu.xmu.table.SchoolExecutiveUnitTable;
import cn.edu.xmu.table.SuperMajorTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;

/**
 * 
 * @author Lee 学校相关行政单位 实体类功能 ——接口实现 date 2015-06-29
 */

public class SchoolAddressDaoImpl extends BaseDaoImpl<SchoolAddress> implements
		SchoolAddressDao {

	@Override
	public int addRecord(cn.edu.xmu.entity.SchoolAddress sa) {

		int result = 0;

		String t_sql = "update " + SchoolAddressTable.TABLE_NAME + " set "
				+ SchoolAddressTable.SA_SERIALNUMBER + " = "
				+ SchoolAddressTable.SA_SERIALNUMBER + " +1 where "
				+ SchoolAddressTable.SA_SERIALNUMBER + ">=?";

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
			t_pstmt.setInt(1, sa.getSa_serialnumber());

			// 执行插入操作并更新
			result = t_pstmt.executeUpdate();

		} catch (SQLException e) {
			// 事务回滚，不做插入操作
			e.printStackTrace();
			return 0;
		} finally {
			JdbcUtils_DBCP.release(connection2, t_pstmt, null);
		}

		if ("".equals(sa.getSa_name()) && "".equals(sa.getSa_address()))
			return -1;
		// 执行插入操作的语句
		String sql = "insert into " + SchoolAddressTable.TABLE_NAME + "("
				+ SchoolAddressTable.SA_NAME + ","
				+ SchoolAddressTable.SA_ADDRESS + ","
				+ SchoolAddressTable.SA_SERIALNUMBER + ","
				+ SchoolAddressTable.SA_COLLEGE + ","
				+ SchoolAddressTable.SA_COMMENTS + ", isnull"
				+ ")values(?,?,?,?,'',?)";

		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, sa.getSa_name());
			pstmt.setString(2, sa.getSa_address());
			pstmt.setString(4, sa.getSa_college());
			pstmt.setInt(3, sa.getSa_serialnumber());
			pstmt.setInt(5, sa.getIsnull());
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
	public int getSchoolAddressCount(Map queryParams) {

		int count = 0;
		// 获取条件的语句
		String sql = "select count(*) from " + SchoolAddressTable.TABLE_NAME
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
		sql += String.format(" or %s ='%s'", SchoolAddressTable.SA_COLLEGE, "");
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
	public List<SchoolAddress> getAllSchoolAddress(int start, int end,
			String sortStr, String orderStr, Map queryParams) {

		String sql = " select tmp.* from ( " + " select * from "
				+ SchoolAddressTable.TABLE_NAME + " where 1=1 ";

		for (Object object : queryParams.keySet()) {
			String key = object.toString();
			String value = queryParams.get(key).toString();
			sql += String.format(" and %s like  '%%%s%%'", key, value);
		}
		sql += String.format(" or %s ='%s'", SchoolAddressTable.SA_COLLEGE, "");
		sql += " order by " + sortStr + " " + orderStr + " ) tmp limit "
				+ start + " ," + end;

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		List<SchoolAddress> sas = new ArrayList<SchoolAddress>();
		try {

			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int sa_id = resultSet.getInt(SchoolAddressTable.SA_ID);
				String sa_name = resultSet
						.getString(SchoolAddressTable.SA_NAME);
				String sa_address = resultSet
						.getString(SchoolAddressTable.SA_ADDRESS);
				int sa_serialnumber = resultSet
						.getInt(SchoolAddressTable.SA_SERIALNUMBER);
				String sa_college = resultSet
						.getString(SchoolAddressTable.SA_COLLEGE);
				String sa_comments = resultSet
						.getString(SchoolAddressTable.SA_COMMENTS);
				Date sa_deadline = resultSet
						.getDate(SchoolAddressTable.SA_DEADLINE);
				SchoolAddress schoolAddress = new SchoolAddress(sa_id, sa_name,
						sa_address, sa_serialnumber, sa_deadline, sa_college,
						sa_comments);
				sas.add(schoolAddress);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return sas;
	}

	@Override
	public int alterSchoolAddress(Map<String, String> valueMap, String id) {

		Map<String, String> condition = new HashMap<String, String>();
		condition.put(SchoolAddressTable.SA_ID, id);
		int result = updateRecord(SchoolAddressTable.TABLE_NAME, valueMap,
				condition);
		return result;
	}

	@Override
	public boolean batchDelete(String[] seuids) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();

		for (String seuid : seuids) {
			String sql = "delete from " + SchoolAddressTable.TABLE_NAME
					+ " where " + SchoolAddressTable.SA_ID + " = '" + seuid
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
	public List<SchoolAddress> getAllSchoolAddress() {

		String sql = " select * from " + SchoolAddressTable.TABLE_NAME
				+ " where 1=1 " + " order by " + SchoolAddressTable.SA_ID;

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		List<SchoolAddress> sas = new ArrayList<SchoolAddress>();
		try {

			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int sa_id = resultSet.getInt(SchoolAddressTable.SA_ID);
				String sa_name = resultSet
						.getString(SchoolAddressTable.SA_NAME);
				String sa_address = resultSet
						.getString(SchoolAddressTable.SA_ADDRESS);
				int sa_serialnumber = resultSet
						.getInt(SchoolAddressTable.SA_SERIALNUMBER);
				String sa_college = resultSet
						.getString(SchoolAddressTable.SA_COLLEGE);
				String sa_comments = resultSet
						.getString(SchoolAddressTable.SA_COMMENTS);
				SchoolAddress schoolAddress = new SchoolAddress(sa_id, sa_name,
						sa_address, sa_serialnumber, null, sa_college,
						sa_comments);

				sas.add(schoolAddress);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return sas;
	}

	@Override
	public void deleteByCollegeandDeadline(String college, Date deadline)
			throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();
		String sql = "delete from " + SchoolAddressTable.TABLE_NAME + " where "
				+ SchoolAddressTable.SA_COLLEGE + " = '" + college + "'"
				+ " and sa_deadline is null ";
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
