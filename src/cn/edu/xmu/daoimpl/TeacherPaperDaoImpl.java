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

import cn.edu.xmu.dao.TeacherPaperDao;
import cn.edu.xmu.entity.TeacherPaper;
import cn.edu.xmu.table.TeacherPaperTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;

/**
 * 
 * @author Gy
 */

public class TeacherPaperDaoImpl extends BaseDaoImpl<TeacherPaper> implements
		TeacherPaperDao {

	@Override
	public int addRecord(TeacherPaper ap) {

		int result = 0;

		String t_sql = "update " + TeacherPaperTable.TABLE_NAME + " set "
				+ TeacherPaperTable.TP_SERIALNUMBER + " = "
				+ TeacherPaperTable.TP_SERIALNUMBER + " +1 where "
				+ TeacherPaperTable.TP_SERIALNUMBER + ">=?";

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
			t_pstmt.setInt(1, ap.getTp_serialnumber());

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
		String sql = "insert into " + TeacherPaperTable.TABLE_NAME + "("
				+ TeacherPaperTable.TP_COLLEGES + ","
				+ TeacherPaperTable.TP_PAPERTITLE + ","
				+ TeacherPaperTable.TP_PAPERCLASSES + ","
				+ TeacherPaperTable.TP_AUTHORS + ","
				+ TeacherPaperTable.TP_SERIAL + ","
				+ TeacherPaperTable.TP_AUTHORCLASSES + ","
				+ TeacherPaperTable.TP_PUBLICATION + ","
				+ TeacherPaperTable.TP_YEAR + "," + TeacherPaperTable.TP_MONTH
				+ "," + TeacherPaperTable.TP_CLASSES + ","
				+ TeacherPaperTable.TP_SERIALNUMBER + ","
				+ TeacherPaperTable.TP_COLLEGE + ","
				+ TeacherPaperTable.TP_COMMENTS + ",isnull"
				+ ")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, ap.getTp_colleges());
			pstmt.setString(2, ap.getTp_papertitle());
			pstmt.setString(3, ap.getTp_paperclasses());
			pstmt.setString(4, ap.getTp_authors());
			pstmt.setString(5, ap.getTp_serial());
			pstmt.setString(6, ap.getTp_authorclasses());
			pstmt.setString(7, ap.getTp_publication());
			pstmt.setString(8, ap.getTp_year());
			pstmt.setString(9, ap.getTp_month());
			pstmt.setString(10, ap.getTp_classes());
			pstmt.setInt(11, ap.getTp_serialnumber());
			pstmt.setString(12, ap.getTp_college());
			pstmt.setString(13, "");
			pstmt.setInt(14, ap.getIsnull());

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
	public int getTeacherPaperCount(Map queryParams) {

		int count = 0;
		// 获取条件的语句
		String sql = "select count(*) from " + TeacherPaperTable.TABLE_NAME
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
		sql += String.format(" or %s ='%s'", TeacherPaperTable.TP_COLLEGE, "");

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
	public List<TeacherPaper> getAllTeacherPaper(int start, int end,
			String sortStr, String orderStr, Map queryParams) {

		String sql = " select tmp.* from ( " + " select * from "
				+ TeacherPaperTable.TABLE_NAME + " where 1=1 ";

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

		List<TeacherPaper> aps = new ArrayList<TeacherPaper>();
		try {

			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int tp_id = resultSet.getInt(TeacherPaperTable.TP_ID);

				String tp_colleges = resultSet
						.getString(TeacherPaperTable.TP_COLLEGES);
				String tp_papertitle = resultSet
						.getString(TeacherPaperTable.TP_PAPERTITLE);
				String tp_paperclasses = resultSet
						.getString(TeacherPaperTable.TP_PAPERCLASSES);
				String tp_authors = resultSet
						.getString(TeacherPaperTable.TP_AUTHORS);
				String tp_serial = resultSet
						.getString(TeacherPaperTable.TP_SERIAL);
				String tp_authorclasses = resultSet
						.getString(TeacherPaperTable.TP_AUTHORCLASSES);
				String tp_publication = resultSet
						.getString(TeacherPaperTable.TP_PUBLICATION);
				String tp_year = resultSet.getString(TeacherPaperTable.TP_YEAR);
				String tp_month = resultSet
						.getString(TeacherPaperTable.TP_MONTH);
				String tp_classes = resultSet
						.getString(TeacherPaperTable.TP_CLASSES);
				int tp_serialnumber = resultSet
						.getInt(TeacherPaperTable.TP_SERIALNUMBER);
				String tp_college = resultSet
						.getString(TeacherPaperTable.TP_COLLEGE);
				String tp_comments = resultSet
						.getString(TeacherPaperTable.TP_COMMENTS);
				Date tp_deadline = resultSet
						.getDate(TeacherPaperTable.TP_DEADLINE);
				TeacherPaper ap = new TeacherPaper(tp_id, tp_colleges,
						tp_papertitle, tp_paperclasses, tp_authors, tp_serial,
						tp_authorclasses, tp_publication, tp_year, tp_month,
						tp_classes, tp_serialnumber, tp_deadline, tp_college,
						tp_comments);
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
	public List<TeacherPaper> getAllTeacherPaper() {

		String sql = " select * from " + TeacherPaperTable.TABLE_NAME
				+ " where 1=1 " + " order by " + TeacherPaperTable.TP_ID;
		Connection connection = null;

		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		List<TeacherPaper> aps = new ArrayList<TeacherPaper>();
		try {

			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int tp_id = resultSet.getInt(TeacherPaperTable.TP_ID);

				String tp_colleges = resultSet
						.getString(TeacherPaperTable.TP_COLLEGES);
				String tp_papertitle = resultSet
						.getString(TeacherPaperTable.TP_PAPERTITLE);
				String tp_paperclasses = resultSet
						.getString(TeacherPaperTable.TP_PAPERCLASSES);
				String tp_authors = resultSet
						.getString(TeacherPaperTable.TP_AUTHORS);
				String tp_serial = resultSet
						.getString(TeacherPaperTable.TP_SERIAL);
				String tp_authorclasses = resultSet
						.getString(TeacherPaperTable.TP_AUTHORCLASSES);
				String tp_publication = resultSet
						.getString(TeacherPaperTable.TP_PUBLICATION);
				String tp_year = resultSet.getString(TeacherPaperTable.TP_YEAR);
				String tp_month = resultSet
						.getString(TeacherPaperTable.TP_MONTH);
				String tp_classes = resultSet
						.getString(TeacherPaperTable.TP_CLASSES);
				int tp_serialnumber = resultSet
						.getInt(TeacherPaperTable.TP_SERIALNUMBER);
				String tp_college = resultSet
						.getString(TeacherPaperTable.TP_COLLEGE);
				String tp_comments = resultSet
						.getString(TeacherPaperTable.TP_COMMENTS);

				TeacherPaper ap = new TeacherPaper(tp_id, tp_colleges,
						tp_papertitle, tp_paperclasses, tp_authors, tp_serial,
						tp_authorclasses, tp_publication, tp_year, tp_month,
						tp_classes, tp_serialnumber, null, tp_college,
						tp_comments);
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
	public int alterTeacherPaper(Map<String, String> valueMap, String id) {

		Map<String, String> condition = new HashMap<String, String>();
		condition.put(TeacherPaperTable.TP_ID, id);
		int result = updateRecord(TeacherPaperTable.TABLE_NAME, valueMap,
				condition);
		return result;
	}

	@Override
	public boolean batchDelete(String[] seuids) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();

		for (String seuid : seuids) {
			String sql = "delete from " + TeacherPaperTable.TABLE_NAME
					+ " where " + TeacherPaperTable.TP_ID + " = '" + seuid
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
	public void deleteByCollegeandDeadline(String college, Date deadline)
			throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();
		String sql = "delete from " + TeacherPaperTable.TABLE_NAME + " where "
				+ TeacherPaperTable.TP_COLLEGE + " = '" + college + "'"
				+ " and tp_deadline is null ";
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
