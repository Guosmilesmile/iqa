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

import cn.edu.xmu.dao.AcademicPaperDao;
import cn.edu.xmu.entity.AcademicPaper;
import cn.edu.xmu.table.AcademicPaperTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;

/**
 * 附表6-2-1-4 2014-2015学年本科生发表学术论文情况
 * 
 * @author Gy
 */

public class AcademicPaperDaoImpl extends BaseDaoImpl<AcademicPaper> implements
		AcademicPaperDao {

	@Override
	public int addRecord(AcademicPaper ap) {

		int result = 0;

		String t_sql = "update " + AcademicPaperTable.TABLE_NAME + " set "
				+ AcademicPaperTable.AP_SERIALNUMBER + " = "
				+ AcademicPaperTable.AP_SERIALNUMBER + " +1 where "
				+ AcademicPaperTable.AP_SERIALNUMBER + ">=?";

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
			t_pstmt.setInt(1, ap.getAp_serialnumber());

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
		String sql = "insert into " + AcademicPaperTable.TABLE_NAME + "("
				+ AcademicPaperTable.AP_COLLEGES + ","
				+ AcademicPaperTable.AP_PAPERTITLE + ","
				+ AcademicPaperTable.AP_GRADE + ","
				+ AcademicPaperTable.AP_NAME + ","
				+ AcademicPaperTable.AP_MAJOR + ","
				+ AcademicPaperTable.AP_PUBLICATION + ","
				+ AcademicPaperTable.AP_TIME + ","
				+ AcademicPaperTable.AP_CLASSES + ","
				+ AcademicPaperTable.AP_REMARK + ","
				+ AcademicPaperTable.AP_SERIALNUMBER + ","
				+ AcademicPaperTable.AP_COLLEGE + ","
				+ AcademicPaperTable.AP_COMMENTS + ",isnull"
				+ ")values(?,?,?,?,?,?,?,?,?,?,?,?,?)";

		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, ap.getAp_colleges());
			pstmt.setString(2, ap.getAp_papertitle());
			pstmt.setString(3, ap.getAp_grade());
			pstmt.setString(4, ap.getAp_name());
			pstmt.setString(5, ap.getAp_major());
			pstmt.setString(6, ap.getAp_publication());
			pstmt.setDate(7, ap.getAp_time());
			pstmt.setString(8, ap.getAp_classes());
			pstmt.setString(9, ap.getAp_remark());
			pstmt.setInt(10, ap.getAp_serialnumber());
			pstmt.setString(11, ap.getAp_college());
			pstmt.setString(12, "");
			pstmt.setInt(13, ap.getIsnull());
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
	public int getAcademicPaperCount(Map queryParams) {

		int count = 0;
		// 获取条件的语句
		String sql = "select count(*) from " + AcademicPaperTable.TABLE_NAME
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
		sql += String.format(" or %s ='%s'", AcademicPaperTable.AP_COLLEGE, "");

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
	public List<AcademicPaper> getAllAcademicPaper(int start, int end,
			String sortStr, String orderStr, Map queryParams) {

		String sql = " select tmp.* from ( " + " select * from "
				+ AcademicPaperTable.TABLE_NAME + " where 1=1 ";

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

		List<AcademicPaper> aps = new ArrayList<AcademicPaper>();
		try {

			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			Date temp = Date.valueOf("1800-1-1");
			while (resultSet.next()) {
				int ap_id = resultSet.getInt(AcademicPaperTable.AP_ID);

				String ap_colleges = resultSet
						.getString(AcademicPaperTable.AP_COLLEGES);
				String ap_papertitle = resultSet
						.getString(AcademicPaperTable.AP_PAPERTITLE);
				String ap_grade = resultSet
						.getString(AcademicPaperTable.AP_GRADE);
				String ap_name = resultSet
						.getString(AcademicPaperTable.AP_NAME);
				String ap_major = resultSet
						.getString(AcademicPaperTable.AP_MAJOR);
				String ap_publication = resultSet
						.getString(AcademicPaperTable.AP_PUBLICATION);
				Date ap_time = resultSet.getDate(AcademicPaperTable.AP_TIME);
				if(temp.equals(ap_time))
					ap_time = null;
				String ap_classes = resultSet
						.getString(AcademicPaperTable.AP_CLASSES);
				String ap_remark = resultSet
						.getString(AcademicPaperTable.AP_REMARK);
				int ap_serialnumber = resultSet
						.getInt(AcademicPaperTable.AP_SERIALNUMBER);
				String ap_college = resultSet
						.getString(AcademicPaperTable.AP_COLLEGE);
				String ap_comments = resultSet
						.getString(AcademicPaperTable.AP_COMMENTS);
				Date ap_deadline = resultSet
						.getDate(AcademicPaperTable.AP_DEADLINE);
				if (null == ap_time
						|| ap_time.equals(Date.valueOf("1800-01-01")))
					ap_time = null;
				AcademicPaper ap = new AcademicPaper(ap_id, ap_colleges,
						ap_papertitle, ap_grade, ap_name, ap_major,
						ap_publication, ap_time, ap_classes, ap_remark,
						ap_serialnumber, ap_deadline, ap_college, ap_comments);
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
	public int alterAcademicPaper(Map<String, String> valueMap, String id) {

		Map<String, String> condition = new HashMap<String, String>();
		condition.put(AcademicPaperTable.AP_ID, id);
		int result = updateRecord(AcademicPaperTable.TABLE_NAME, valueMap,
				condition);
		return result;
	}

	@Override
	public boolean batchDelete(String[] seuids) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();

		for (String seuid : seuids) {
			String sql = "delete from " + AcademicPaperTable.TABLE_NAME
					+ " where " + AcademicPaperTable.AP_ID + " = '" + seuid
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
	public List<AcademicPaper> getAllAcademicPaper() {

		String sql = " select * from " + AcademicPaperTable.TABLE_NAME
				+ " where 1=1 " + " order by " + AcademicPaperTable.AP_ID;
		Connection connection = null;

		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		List<AcademicPaper> aps = new ArrayList<AcademicPaper>();
		try {

			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int seu_id = resultSet.getInt(AcademicPaperTable.AP_ID);

				String ap_colleges = resultSet
						.getString(AcademicPaperTable.AP_COLLEGES);
				String ap_papertitle = resultSet
						.getString(AcademicPaperTable.AP_PAPERTITLE);
				String ap_grade = resultSet
						.getString(AcademicPaperTable.AP_GRADE);
				String ap_name = resultSet
						.getString(AcademicPaperTable.AP_NAME);
				String ap_major = resultSet
						.getString(AcademicPaperTable.AP_MAJOR);
				String ap_publication = resultSet
						.getString(AcademicPaperTable.AP_PUBLICATION);
				Date ap_time = resultSet.getDate(AcademicPaperTable.AP_TIME);
				System.out.println(ap_time.toString());
				String ap_classes = resultSet
						.getString(AcademicPaperTable.AP_CLASSES);
				String ap_remark = resultSet
						.getString(AcademicPaperTable.AP_REMARK);
				int ap_serialnumber = resultSet
						.getInt(AcademicPaperTable.AP_SERIALNUMBER);
				String ap_college = resultSet
						.getString(AcademicPaperTable.AP_COLLEGE);
				String ap_comments = resultSet
						.getString(AcademicPaperTable.AP_COMMENTS);
				AcademicPaper ap = new AcademicPaper(ap_colleges,
						ap_papertitle, ap_grade, ap_name, ap_major,
						ap_publication, ap_time, ap_classes, ap_remark,
						ap_serialnumber, ap_college, ap_comments);
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
	public void deleteByCollegeandDeadline(String college, Date deadline)
			throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();
		String sql = "delete from " + AcademicPaperTable.TABLE_NAME + " where "
				+ AcademicPaperTable.AP_COLLEGE + " = '" + college + "'"
				+ " and ap_deadline is null ";
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
