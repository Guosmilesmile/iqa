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

import cn.edu.xmu.dao.ImportantStudyDao;
import cn.edu.xmu.entity.ImportantStudy;
import cn.edu.xmu.entity.MajorInfo;
import cn.edu.xmu.table.AcademicPaperTable;
import cn.edu.xmu.table.ImportantStudyTable;
import cn.edu.xmu.table.MajorInfoTable;
import cn.edu.xmu.table.SchoolExecutiveUnitTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;

public class ImportantStudyDaoImpl extends BaseDaoImpl<ImportantStudy>
		implements ImportantStudyDao {

	@Override
	public int getImportantStudyCount() {
		int count = 0;
		String sql = "select count(*) from " + ImportantStudyTable.TABLE_NAME
				+ " where 1 = 1";
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
			return -1;
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
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);

		}
		System.err.println(count);
		return count;
	}

	@Override
	public List<ImportantStudy> getAllImportantStudy(int start, int end,
			String sortStr, String orderStr,Map queryParams) {
		String sql = " select tmp.* from ( " + " select * from "
				+ ImportantStudyTable.TABLE_NAME + " where 1=1 ";

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

		try {
			System.out.println(sql);
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			List<ImportantStudy> importantStudies = new ArrayList<ImportantStudy>();

			while (resultSet.next()) {

				String ip_id = resultSet.getString(ImportantStudyTable.IP_ID);
				int ip_studynumber = resultSet
						.getInt(ImportantStudyTable.IP_STUDYNUMBER);
				String ip_studyname = resultSet
						.getString(ImportantStudyTable.IP_STUDYNAME);
				String ip_departmentnumber = resultSet
						.getString(ImportantStudyTable.IP_DEPARTMENTNUMBER);
				String ip_departmentname = resultSet
						.getString(ImportantStudyTable.IP_DEPARTMENTNAME);
				String ip_category = resultSet
						.getString(ImportantStudyTable.IP_CATEGORY);
				String ip_level = resultSet
						.getString(ImportantStudyTable.IP_LEVEL);
				int ip_serialnumber = resultSet
						.getInt(ImportantStudyTable.IP_SERIALNUMBER);
				Date ip_deadline = resultSet
						.getDate(ImportantStudyTable.IP_DEADLINE);
				String ip_college = resultSet
						.getString(ImportantStudyTable.IP_COLLEGE);
				String ip_comments = resultSet
						.getString(ImportantStudyTable.IP_COMMENTS);
				ImportantStudy e = new ImportantStudy(ip_id, ip_studynumber,
						ip_studyname, ip_departmentnumber, ip_departmentname,
						ip_category, ip_level, ip_serialnumber, ip_deadline,
						ip_college, ip_comments);
				importantStudies.add(e);
			}
			return importantStudies;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}

	}

	@Override
	public int addImportantStudy(ImportantStudy importantStudy) {
		int result = 0;

		String sql2 = "update " + ImportantStudyTable.TABLE_NAME + " set "
				+ ImportantStudyTable.IP_SERIALNUMBER + " = "
				+ ImportantStudyTable.IP_SERIALNUMBER + " +1 where "
				+ ImportantStudyTable.IP_SERIALNUMBER + ">="
				+ importantStudy.getIp_serialnumber();
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
			JdbcUtils_DBCP.release(connection2, pstmt2, null);
		}

		String sql = "insert into " + ImportantStudyTable.TABLE_NAME + "("
				+ ImportantStudyTable.IP_STUDYNUMBER + ","
				+ ImportantStudyTable.IP_STUDYNAME + ","
				+ ImportantStudyTable.IP_DEPARTMENTNUMBER + ","
				+ ImportantStudyTable.IP_DEPARTMENTNAME + ","
				+ ImportantStudyTable.IP_CATEGORY + ","
				+ ImportantStudyTable.IP_LEVEL + ","
				+ ImportantStudyTable.IP_SERIALNUMBER + ","
				+ ImportantStudyTable.IP_COLLEGE + ","
				+ ImportantStudyTable.IP_COMMENTS + ",isnull"
				+ ")values(?,?,?,?,?,?,?,?,'',?)";

		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		PreparedStatement pstmt = null;

		try {
			pstmt = connection.prepareStatement(sql);
			System.out.println(sql);
			pstmt.setInt(1, importantStudy.getIp_studynumber());
			pstmt.setString(2, importantStudy.getIp_studyname());
			pstmt.setString(3, importantStudy.getIp_departmentnumber());
			pstmt.setString(4, importantStudy.getIp_departmentname());
			pstmt.setString(5, importantStudy.getIp_category());
			pstmt.setString(6, importantStudy.getIp_level());
			pstmt.setInt(7, importantStudy.getIp_serialnumber());
			pstmt.setString(8, importantStudy.getIp_college());
			pstmt.setInt(9, importantStudy.getIsnull());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			result = -1;
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, null);
		}

		return result;
	}

	@Override
	public int alterImportantStudy(Map<String, String> valueMap, String id) {
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(ImportantStudyTable.IP_ID, id);
		int result = updateRecord(ImportantStudyTable.TABLE_NAME, valueMap,
				condition);
		return result;
	}

	@Override
	public boolean batchDelete(String[] miids) throws SQLException {
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		}
		Statement stmt = connection.createStatement();

		for (String miid : miids) {
			String sql = "delete from " + ImportantStudyTable.TABLE_NAME
					+ " where " + ImportantStudyTable.IP_ID + " = '" + miid
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
	public int getImportantStudyCountCount(Map queryParams) {
		int count = 0;
		// 获取条件的语句
		String sql = "select count(*) from " + ImportantStudyTable.TABLE_NAME
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
		sql += String
				.format(" or %s ='%s'", ImportantStudyTable.IP_COLLEGE, "");

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
	public int getImportantStudyCountByName(Map queryParams) {
		int count = 0;
		// 获取条件的语句
		String sql = "select count(*) from " + ImportantStudyTable.TABLE_NAME
				+ " where 1 = 1 and " + ImportantStudyTable.IP_STUDYNAME
				+ " is not null and " + ImportantStudyTable.IP_STUDYNAME
				+ " != ''";
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		if (queryParams != null && queryParams.size() != 0) {
			for (Object object : queryParams.keySet()) {
				String key = object.toString();
				String value = queryParams.get(key).toString();
				sql += String.format(" and  %s like  '%s%%' ", key, value);
			}
		}

		sql += String
				.format(" or %s ='%s'", ImportantStudyTable.IP_COLLEGE, "");

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
	public List<ImportantStudy> getAllImportantStudy() {

		String sql = " select * from " + ImportantStudyTable.TABLE_NAME
				+ " where 1=1 " + " order by " + ImportantStudyTable.IP_ID;
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
			return null;
		}
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		try {
			System.out.println(sql);
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			List<ImportantStudy> importantStudies = new ArrayList<ImportantStudy>();

			while (resultSet.next()) {

				String ip_id = resultSet.getString(ImportantStudyTable.IP_ID);
				int ip_studynumber = resultSet
						.getInt(ImportantStudyTable.IP_STUDYNUMBER);
				String ip_studyname = resultSet
						.getString(ImportantStudyTable.IP_STUDYNAME);
				String ip_departmentnumber = resultSet
						.getString(ImportantStudyTable.IP_DEPARTMENTNUMBER);
				String ip_departmentname = resultSet
						.getString(ImportantStudyTable.IP_DEPARTMENTNAME);
				String ip_category = resultSet
						.getString(ImportantStudyTable.IP_CATEGORY);
				String ip_level = resultSet
						.getString(ImportantStudyTable.IP_LEVEL);
				int ip_serialnumber = resultSet
						.getInt(ImportantStudyTable.IP_SERIALNUMBER);
				Date ip_deadline = resultSet
						.getDate(ImportantStudyTable.IP_DEADLINE);
				String ip_college = resultSet
						.getString(ImportantStudyTable.IP_COLLEGE);
				String ip_comments = resultSet
						.getString(ImportantStudyTable.IP_COMMENTS);

				ImportantStudy e = new ImportantStudy(ip_id, ip_studynumber,
						ip_studyname, ip_departmentnumber, ip_departmentname,
						ip_category, ip_level, ip_serialnumber, ip_deadline,
						ip_college, ip_comments);
				importantStudies.add(e);
			}
			return importantStudies;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}

	}

	@Override
	public void deleteByCollegeandDeadline(String college, Date deadline)
			throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();
		String sql = "delete from " + ImportantStudyTable.TABLE_NAME
				+ " where " + ImportantStudyTable.IP_COLLEGE + " = '" + college
				+ "'" + " and ip_deadline is null ";
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
