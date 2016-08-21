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

import cn.edu.xmu.dao.FourSixDao;
import cn.edu.xmu.entity.FourSix;
import cn.edu.xmu.table.FourSixTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;

/**
 * 附表6-2-1-7 本科毕业生大学英语四六级考试累计通过率（学年）
 * 
 * @author Gy
 */

public class FourSixDaoImpl extends BaseDaoImpl<FourSix> implements FourSixDao {

	@Override
	public int addRecord(FourSix fx) {

		int result = 0;

		String t_sql = "update " + FourSixTable.TABLE_NAME + " set "
				+ FourSixTable.FX_SERIALNUMBER + " = "
				+ FourSixTable.FX_SERIALNUMBER + " +1 where "
				+ FourSixTable.FX_SERIALNUMBER + ">=?";

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
			t_pstmt.setInt(1, fx.getFx_serialnumber());

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
		String sql = "insert into " + FourSixTable.TABLE_NAME + "("
				+ FourSixTable.FX_COLLEGES + "," + FourSixTable.FX_DEAPERMENT
				+ "," + FourSixTable.FX_MAJOR + "," + FourSixTable.FX_GRADE
				+ "," + FourSixTable.FX_LEVEL + "," + FourSixTable.FX_TOTAL
				+ "," + FourSixTable.FX_ATTEND + ","
				+ FourSixTable.FX_ATTENDCOUNT + "," + FourSixTable.FX_PASS
				+ "," + FourSixTable.FX_PASSPERCENT + ","
				+ FourSixTable.FX_GOOD + "," + FourSixTable.FX_GOODPERCENT
				+ "," + FourSixTable.FX_SERIALNUMBER + ","
				+ FourSixTable.FX_COLLEGE + "," + FourSixTable.FX_COMMENTS
				+ ",isnull" + ")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,'',?)";
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, fx.getFx_colleges());
			pstmt.setString(2, fx.getFx_department());
			pstmt.setString(3, fx.getFx_major());
			pstmt.setString(4, fx.getFx_grade());
			pstmt.setString(5, fx.getFx_level());
			pstmt.setInt(6, fx.getFx_total());
			pstmt.setInt(7, fx.getFx_attend());
			pstmt.setInt(8, fx.getFx_attendcount());
			pstmt.setInt(9, fx.getFx_pass());
			pstmt.setDouble(10, fx.getFx_passpercent());
			pstmt.setInt(11, fx.getFx_good());
			pstmt.setDouble(12, fx.getFx_goodpercent());
			pstmt.setInt(13, fx.getFx_serialnumber());
			pstmt.setString(14, fx.getFx_college());
			pstmt.setInt(15, fx.getIsnull());
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
	public int getFourSixCount(Map queryParams) {

		int count = 0;
		// 获取条件的语句
		String sql = "select count(*) from " + FourSixTable.TABLE_NAME
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
		sql += String.format(" or %s ='%s'", FourSixTable.FX_COLLEGE, "");

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
	public List<FourSix> getAllFourSix(int start, int end, String sortStr,
			String orderStr, Map queryParams) {

		String sql = " select tmp.* from ( " + " select * from "
				+ FourSixTable.TABLE_NAME + " where 1=1 ";

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

		List<FourSix> aps = new ArrayList<FourSix>();
		try {

			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int fx_id = resultSet.getInt(FourSixTable.FX_ID);
				String fx_colleges = resultSet
						.getString(FourSixTable.FX_COLLEGES);
				String fx_department = resultSet
						.getString(FourSixTable.FX_DEAPERMENT);
				String fx_major = resultSet.getString(FourSixTable.FX_MAJOR);
				String fx_grade = resultSet.getString(FourSixTable.FX_GRADE);
				String fx_level = resultSet.getString(FourSixTable.FX_LEVEL);
				int fx_total = resultSet.getInt(FourSixTable.FX_TOTAL);
				int fx_attend = resultSet.getInt(FourSixTable.FX_ATTEND);
				int fx_attendcount = resultSet
						.getInt(FourSixTable.FX_ATTENDCOUNT);
				int fx_pass = resultSet.getInt(FourSixTable.FX_PASS);
				double fx_passpercent = resultSet
						.getDouble(FourSixTable.FX_PASSPERCENT);
				int fx_good = resultSet.getInt(FourSixTable.FX_GOOD);
				double fx_goodpercent = resultSet
						.getDouble(FourSixTable.FX_GOODPERCENT);

				int fx_serialnumber = resultSet
						.getInt(FourSixTable.FX_SERIALNUMBER);
				String fx_college = resultSet
						.getString(FourSixTable.FX_COLLEGE);
				String fx_comments = resultSet
						.getString(FourSixTable.FX_COMMENTS);
				Date fx_deadline = resultSet.getDate(FourSixTable.FX_DEADLINE);
				FourSix ap = new FourSix(fx_id, fx_colleges, fx_department,
						fx_major, fx_grade, fx_level, fx_total, fx_attend,
						fx_attendcount, fx_pass, fx_passpercent, fx_good,
						fx_goodpercent, fx_serialnumber, fx_deadline,
						fx_college, fx_comments);
				aps.add(ap);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return aps;
	}

	@Override
	public List<FourSix> getAllFourSix() {

		String sql = " select * from " + FourSixTable.TABLE_NAME
				+ " where 1=1 " + " order by " + FourSixTable.FX_ID;
		Connection connection = null;

		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		List<FourSix> aps = new ArrayList<FourSix>();
		try {

			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int fx_id = resultSet.getInt(FourSixTable.FX_ID);
				String fx_colleges = resultSet
						.getString(FourSixTable.FX_COLLEGES);
				String fx_department = resultSet
						.getString(FourSixTable.FX_DEAPERMENT);
				String fx_major = resultSet.getString(FourSixTable.FX_MAJOR);
				String fx_grade = resultSet.getString(FourSixTable.FX_GRADE);
				String fx_level = resultSet.getString(FourSixTable.FX_LEVEL);
				int fx_total = resultSet.getInt(FourSixTable.FX_TOTAL);
				int fx_attend = resultSet.getInt(FourSixTable.FX_ATTEND);
				int fx_attendcount = resultSet
						.getInt(FourSixTable.FX_ATTENDCOUNT);
				int fx_pass = resultSet.getInt(FourSixTable.FX_PASS);
				Double fx_passpercent = resultSet
						.getDouble(FourSixTable.FX_PASSPERCENT);
				int fx_good = resultSet.getInt(FourSixTable.FX_GOOD);
				Double fx_goodpercent = resultSet
						.getDouble(FourSixTable.FX_GOODPERCENT);

				int fx_serialnumber = resultSet
						.getInt(FourSixTable.FX_SERIALNUMBER);
				String fx_college = resultSet
						.getString(FourSixTable.FX_COLLEGE);
				String fx_comments = resultSet
						.getString(FourSixTable.FX_COMMENTS);

				FourSix ap = new FourSix(fx_id, fx_colleges, fx_department,
						fx_major, fx_grade, fx_level, fx_total, fx_attend,
						fx_attendcount, fx_pass, fx_passpercent, fx_good,
						fx_goodpercent, fx_serialnumber, null, fx_college,
						fx_comments);
				aps.add(ap);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return aps;
	}

	@Override
	public int alterFourSix(Map<String, String> valueMap, String id) {

		Map<String, String> condition = new HashMap<String, String>();
		condition.put(FourSixTable.FX_ID, id);
		int result = updateRecord(FourSixTable.TABLE_NAME, valueMap, condition);
		return result;
	}

	@Override
	public boolean batchDelete(String[] seuids) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();

		for (String seuid : seuids) {
			String sql = "delete from " + FourSixTable.TABLE_NAME + " where "
					+ FourSixTable.FX_ID + " = '" + seuid + "'";
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
	public void deleteByCollegeandDeadline(String college, Date deadline)
			throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();
		String sql = "delete from " + FourSixTable.TABLE_NAME + " where "
				+ FourSixTable.FX_COLLEGE + " = '" + college + "'"
				+ " and fx_deadline is null ";
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
