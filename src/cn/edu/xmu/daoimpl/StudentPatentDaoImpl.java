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

import cn.edu.xmu.dao.StudentPatentDao;
import cn.edu.xmu.entity.StudentPatent;
import cn.edu.xmu.table.StudentPatentTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;

/**
 * 附表6-2-1-6 2014-2015学年本科生获得专利情况
 * 
 * @author Gy
 */

public class StudentPatentDaoImpl extends BaseDaoImpl<StudentPatent> implements
		StudentPatentDao {

	@Override
	public int addRecord(StudentPatent sp) {

		int result = 0;

		String t_sql = "update " + StudentPatentTable.TABLE_NAME + " set "
				+ StudentPatentTable.SP_SERIALNUMBER + " = "
				+ StudentPatentTable.SP_SERIALNUMBER + " +1 where "
				+ StudentPatentTable.SP_SERIALNUMBER + ">=?";

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
			t_pstmt.setInt(1, sp.getSp_serialnumber());

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
		String sql = "insert into " + StudentPatentTable.TABLE_NAME + "("
				+ StudentPatentTable.SP_COLLEGES + ","
				+ StudentPatentTable.SP_PATENTNAME + ","
				+ StudentPatentTable.SP_NUMBER + ","
				+ StudentPatentTable.SP_GRADE + ","
				+ StudentPatentTable.SP_AUTHORS + ","
				+ StudentPatentTable.SP_SERIAL + ","
				+ StudentPatentTable.SP_MAJOR + ","
				+ StudentPatentTable.SP_TIME + ","
				+ StudentPatentTable.SP_REMARK + ","
				+ StudentPatentTable.SP_SERIALNUMBER + ","
				+ StudentPatentTable.SP_COLLEGE + ","
				+ StudentPatentTable.SP_COMMENTS + ",isnull"
				+ ")values(?,?,?,?,?,?,?,?,?,?,?,?,?)";

		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, sp.getSp_colleges());
			pstmt.setString(2, sp.getSp_patentname());
			pstmt.setString(3, sp.getSp_number());
			pstmt.setString(4, sp.getSp_grade());
			pstmt.setString(5, sp.getSp_authors());
			pstmt.setString(6, sp.getSp_serial());
			pstmt.setString(7, sp.getSp_major());
			pstmt.setDate(8, sp.getSp_time());
			pstmt.setString(9, sp.getSp_remark());
			pstmt.setInt(10, sp.getSp_serialnumber());
			pstmt.setString(11, sp.getSp_college());
			pstmt.setString(12, "");
			pstmt.setInt(13, sp.getIsnull());
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
	public int getStudentPatentCount(Map queryParams) {

		int count = 0;
		// 获取条件的语句
		String sql = "select count(*) from " + StudentPatentTable.TABLE_NAME
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
		sql += String.format(" or %s ='%s'", StudentPatentTable.SP_COLLEGE, "");

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
	public List<StudentPatent> getAllStudentPatent(int start, int end,
			String sortStr, String orderStr, Map queryParams) {

		String sql = " select tmp.* from ( " + " select * from "
				+ StudentPatentTable.TABLE_NAME + " where 1=1 ";

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

		List<StudentPatent> aps = new ArrayList<StudentPatent>();
		try {

			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			Date temp = Date.valueOf("1800-1-1");
			while (resultSet.next()) {
				int sp_id = resultSet.getInt(StudentPatentTable.SP_ID);

				String sp_colleges = resultSet
						.getString(StudentPatentTable.SP_COLLEGES);
				String sp_patentname = resultSet
						.getString(StudentPatentTable.SP_PATENTNAME);
				String sp_number = resultSet
						.getString(StudentPatentTable.SP_NUMBER);
				String sp_grade = resultSet
						.getString(StudentPatentTable.SP_GRADE);
				String sp_authors = resultSet
						.getString(StudentPatentTable.SP_AUTHORS);
				String sp_serial = resultSet
						.getString(StudentPatentTable.SP_SERIAL);
				String sp_major = resultSet
						.getString(StudentPatentTable.SP_MAJOR);
				Date sp_time = resultSet.getDate(StudentPatentTable.SP_TIME);
				if(temp.equals(sp_time))
					sp_time = null;
				String sp_remark = resultSet
						.getString(StudentPatentTable.SP_REMARK);
				int sp_serialnumber = resultSet
						.getInt(StudentPatentTable.SP_SERIALNUMBER);
				String sp_college = resultSet
						.getString(StudentPatentTable.SP_COLLEGE);
				String sp_comments = resultSet
						.getString(StudentPatentTable.SP_COMMENTS);
				Date sp_deadline = resultSet
						.getDate(StudentPatentTable.SP_DEADLINE);
				if (null == sp_time
						|| sp_time.equals(Date.valueOf("1800-01-01")))
					sp_time = null;
				StudentPatent ap = new StudentPatent(sp_id, sp_colleges,
						sp_patentname, sp_number, sp_grade, sp_authors,
						sp_serial, sp_major, sp_time, sp_remark,
						sp_serialnumber, sp_deadline, sp_college, sp_comments);
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
	public List<StudentPatent> getAllStudentPatent() {

		String sql = " select * from " + StudentPatentTable.TABLE_NAME
				+ " where 1=1 " + " order by " + StudentPatentTable.SP_ID;
		Connection connection = null;

		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		List<StudentPatent> aps = new ArrayList<StudentPatent>();
		try {

			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			Date temp = Date.valueOf("1800-1-1");
			while (resultSet.next()) {
				int sp_id = resultSet.getInt(StudentPatentTable.SP_ID);

				String sp_colleges = resultSet
						.getString(StudentPatentTable.SP_COLLEGES);
				String sp_patentname = resultSet
						.getString(StudentPatentTable.SP_PATENTNAME);
				String sp_number = resultSet
						.getString(StudentPatentTable.SP_NUMBER);
				String sp_grade = resultSet
						.getString(StudentPatentTable.SP_GRADE);
				String sp_authors = resultSet
						.getString(StudentPatentTable.SP_AUTHORS);
				String sp_serial = resultSet
						.getString(StudentPatentTable.SP_SERIAL);
				String sp_major = resultSet
						.getString(StudentPatentTable.SP_MAJOR);
				Date sp_time = resultSet.getDate(StudentPatentTable.SP_TIME);
				if(temp.equals(sp_time))
					sp_time = null;
				String sp_remark = resultSet
						.getString(StudentPatentTable.SP_REMARK);
				int sp_serialnumber = resultSet
						.getInt(StudentPatentTable.SP_SERIALNUMBER);
				String sp_college = resultSet
						.getString(StudentPatentTable.SP_COLLEGE);
				String sp_comments = resultSet
						.getString(StudentPatentTable.SP_COMMENTS);

				StudentPatent ap = new StudentPatent(sp_id, sp_colleges,
						sp_patentname, sp_number, sp_grade, sp_authors,
						sp_serial, sp_major, sp_time, sp_remark,
						sp_serialnumber, sp_college, sp_comments);
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
	public int alterStudentPatent(Map<String, String> valueMap, String id) {

		Map<String, String> condition = new HashMap<String, String>();
		condition.put(StudentPatentTable.SP_ID, id);
		int result = updateRecord(StudentPatentTable.TABLE_NAME, valueMap,
				condition);
		return result;
	}

	@Override
	public boolean batchDelete(String[] seuids) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();

		for (String seuid : seuids) {
			String sql = "delete from " + StudentPatentTable.TABLE_NAME
					+ " where " + StudentPatentTable.SP_ID + " = '" + seuid
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
		String sql = "delete from " + StudentPatentTable.TABLE_NAME + " where "
				+ StudentPatentTable.SP_COLLEGE + " = '" + college + "'"
				+ " and sp_deadline is null ";
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
