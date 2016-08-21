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

import cn.edu.xmu.dao.SuperMajorDao;
import cn.edu.xmu.entity.SuperMajor;
import cn.edu.xmu.table.SuperMajorTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;

public class SuperMajorDaoImpl extends BaseDaoImpl<SuperMajor> implements
		SuperMajorDao {

	@Override
	public List<SuperMajor> getAllSuperMajor(int start, int end,
			String sortStr, String orderStr, Map queryParams) {
		String sql = " select tmp.* from ( " + " select * from "
				+ SuperMajorTable.TABLE_NAME + " where 1=1 ";
		for (Object object : queryParams.keySet()) {
			String key = object.toString();
			String value = queryParams.get(key).toString();
			sql += String.format(" and %s like  '%%%s%%'", key, value);
		}
		// sql += String.format(" or %s ='%s'", "college","");
		sql += " order by " + sortStr + " " + orderStr + " ) tmp limit "
				+ start + " ," + end;

		System.out.println(sql);
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		List<SuperMajor> superMajors = new ArrayList<SuperMajor>();
		try {
			System.out.println(sql);
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int sm_id = resultSet.getInt(SuperMajorTable.SM_ID);
				String sm_name = resultSet.getString(SuperMajorTable.SM_NAME);
				String sm_number = resultSet
						.getString(SuperMajorTable.SM_NUMBER);
				String sm_class = resultSet.getString(SuperMajorTable.SM_CLASS);
				String c_startyear = resultSet
						.getString(SuperMajorTable.C_STARTYEAR);
				String p_startyear = resultSet
						.getString(SuperMajorTable.P_STARTYEAR);
				String s_startyear = resultSet
						.getString(SuperMajorTable.S_STARTYEAR);
				String respon_person = resultSet
						.getString(SuperMajorTable.RESPON_PERSON);
				String sm_college = resultSet
						.getString(SuperMajorTable.SM_COLLEGE);
				int sm_serialnumber = resultSet
						.getInt(SuperMajorTable.SM_SERIALNUMBER);
				Date sm_deadline = resultSet
						.getDate(SuperMajorTable.SM_DEADLINE);

				SuperMajor superMajor = new SuperMajor(sm_id, sm_name,
						sm_number, sm_class, c_startyear, p_startyear,
						s_startyear, respon_person, sm_college,
						sm_serialnumber, sm_deadline);

				superMajors.add(superMajor);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return superMajors;
	}

	@Override
	public int addSuperMajor(String name, String number, String m_class,
			String c_start, String p_start, String s_start, String respon_p,
			String college, int sm_serialnumber) {
		int result = 0;

		String sql2 = "update " + SuperMajorTable.TABLE_NAME + " set "
				+ SuperMajorTable.SM_SERIALNUMBER + " = "
				+ SuperMajorTable.SM_SERIALNUMBER + " +1 where "
				+ SuperMajorTable.SM_SERIALNUMBER + ">=" + sm_serialnumber;

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

		String sql = "insert into " + SuperMajorTable.TABLE_NAME + "("
				+ SuperMajorTable.SM_NAME + "," + SuperMajorTable.SM_NUMBER
				+ "," + SuperMajorTable.SM_CLASS + ","
				+ SuperMajorTable.C_STARTYEAR + ","
				+ SuperMajorTable.P_STARTYEAR + ","
				+ SuperMajorTable.S_STARTYEAR + ","
				+ SuperMajorTable.RESPON_PERSON + ","
				+ SuperMajorTable.SM_COLLEGE + ","
				+ SuperMajorTable.SM_SERIALNUMBER
				+ ")values(?,?,?,?,?,?,?,?,?)";

		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, number);
			pstmt.setString(3, m_class);
			pstmt.setString(4, c_start);
			pstmt.setString(5, p_start);
			pstmt.setString(6, s_start);
			pstmt.setString(7, respon_p);
			pstmt.setString(8, college);
			pstmt.setInt(9, sm_serialnumber);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			result = -1;
		} finally {
			JdbcUtils_DBCP.release(connection,pstmt,null);
		}

		return result;
	}

	@Override
	public int alterSuperMajor(Map<String, String> valueMap, String id) {
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(SuperMajorTable.SM_ID, id);
		int result = updateRecord(SuperMajorTable.TABLE_NAME, valueMap,
				condition);
		return result;
	}

	@Override
	public int deleteSMajorById(String id, String serialnumber) {
		// TODO Auto-generated method stub
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(SuperMajorTable.SM_ID, id);
		int result = deleteRecord(SuperMajorTable.TABLE_NAME, condition);

		String sql2 = "update " + SuperMajorTable.TABLE_NAME + " set "
				+ SuperMajorTable.SM_SERIALNUMBER + " = "
				+ SuperMajorTable.SM_SERIALNUMBER + " -1 where "
				+ SuperMajorTable.SM_SERIALNUMBER + ">=" + serialnumber;

		Connection connection2 = null;
		PreparedStatement pstmt2 = null;
		try {
			connection2 = JdbcUtils_DBCP.getConnection();
			pstmt2 = connection2.prepareStatement(sql2);
			pstmt2.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			JdbcUtils_DBCP.release(connection2, pstmt2, null);
		}

		return result;
	}

	@Override
	public int getMaxSerialNum() {
		ResultSet resultSet = null;
		int result = 1;
		String sql = "select max(" + SuperMajorTable.SM_SERIALNUMBER
				+ ") from " + SuperMajorTable.TABLE_NAME;

		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
			// pstmt = connection.prepareStatement(sql);
			// resultSet = pstmt.executeQuery();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				result = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}

		return result;
	}

	@Override
	public int getSuperMajorCount(Map queryParams) {
		int count = 0;
		String sql = "select count(*) from " + SuperMajorTable.TABLE_NAME
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
		sql += String.format(" or %s ='%s'", SuperMajorTable.SM_COLLEGE, "");

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
		System.err.println(count);
		return count;
	}

	@Override
	public List<SuperMajor> findForPage(int start, int end, String sortStr,
			String orderStr, Map queryParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SuperMajor> getAllSuperMajor() {
		String sql = " select * from " + SuperMajorTable.TABLE_NAME
				+ " where 1=1 " + " order by " + SuperMajorTable.SM_ID;
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		try {
			System.out.println(sql);
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			List<SuperMajor> superMajors = new ArrayList<SuperMajor>();

			while (resultSet.next()) {
				String sm_name = resultSet.getString(SuperMajorTable.SM_NAME);
				String sm_number = resultSet
						.getString(SuperMajorTable.SM_NUMBER);
				String sm_class = resultSet.getString(SuperMajorTable.SM_CLASS);
				String c_startyear = resultSet
						.getString(SuperMajorTable.C_STARTYEAR);
				String p_startyear = resultSet
						.getString(SuperMajorTable.P_STARTYEAR);
				String s_startyear = resultSet
						.getString(SuperMajorTable.S_STARTYEAR);
				String respon_person = resultSet
						.getString(SuperMajorTable.RESPON_PERSON);
				String college = resultSet
						.getString(SuperMajorTable.SM_COLLEGE);
				int sm_serialnumber = resultSet
						.getInt(SuperMajorTable.SM_SERIALNUMBER);
				Date sm_deadline = resultSet
						.getDate(SuperMajorTable.SM_DEADLINE);
				SuperMajor superMajor = new SuperMajor(sm_name, sm_number,
						sm_class, c_startyear, p_startyear, s_startyear,
						respon_person, college, sm_serialnumber, sm_deadline);
				superMajors.add(superMajor);
			}
			return superMajors;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
	}

	@Override
	public boolean batchDelete(String[] smids) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();

		for (String smid : smids) {
			String sql = "delete from " + SuperMajorTable.TABLE_NAME
					+ " where " + SuperMajorTable.SM_ID + " = '" + smid + "'";
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
	public int addSuperMajorRecord(SuperMajor superMajor) {
		int result = 0;

		String sql = "insert into " + SuperMajorTable.TABLE_NAME + "("
				+ SuperMajorTable.SM_NAME + "," + SuperMajorTable.SM_NUMBER
				+ "," + SuperMajorTable.SM_CLASS + ","
				+ SuperMajorTable.C_STARTYEAR + ","
				+ SuperMajorTable.P_STARTYEAR + ","
				+ SuperMajorTable.S_STARTYEAR + ","
				+ SuperMajorTable.RESPON_PERSON + ","
				+ SuperMajorTable.SM_COLLEGE + ","
				+ SuperMajorTable.SM_SERIALNUMBER + ","
				+ SuperMajorTable.SM_DEADLINE + ")values(?,?,?,?,?,?,?,?,?,?)";

		System.out.println("添加纪录" + sql);
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		PreparedStatement pstmt = null;

		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, superMajor.getSm_name());
			pstmt.setString(2, superMajor.getSm_number());
			pstmt.setString(3, superMajor.getSm_class());
			pstmt.setString(4, superMajor.getC_startyear());
			pstmt.setString(5, superMajor.getP_startyear());
			pstmt.setString(6, superMajor.getS_startyear());
			pstmt.setString(7, superMajor.getRespon_person());
			pstmt.setString(8, superMajor.getSm_college());
			pstmt.setInt(9, superMajor.getSm_serialnumber());
			pstmt.setDate(10, superMajor.getSm_deadline());
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
	public void deleteAll() {
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		Statement stmt = null;
		try {
			stmt = connection.createStatement();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		String sql = "delete from " + SuperMajorTable.TABLE_NAME;
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JdbcUtils_DBCP.release(connection, stmt, null);
	}

	@Override
	public List<SuperMajor> getAllSuperMajor(Map queryParams) {
		String sql = " select * from " + SuperMajorTable.TABLE_NAME
				+ " where 1=1 ";
		if (queryParams != null) {
			for (Object object : queryParams.keySet()) {
				String key = object.toString();
				String value = queryParams.get(key).toString();
				sql += String.format(" and  %s like '%s%%' ", key, value);
			}
			sql += String.format(" or %s ='%s'", "college", "");
		}
		sql += " order by " + SuperMajorTable.SM_ID;
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		try {
			System.out.println(sql);
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			List<SuperMajor> superMajors = new ArrayList<SuperMajor>();

			while (resultSet.next()) {
				int sm_id = resultSet.getInt(SuperMajorTable.SM_ID);
				String sm_name = resultSet.getString(SuperMajorTable.SM_NAME);
				String sm_number = resultSet
						.getString(SuperMajorTable.SM_NUMBER);
				String sm_class = resultSet.getString(SuperMajorTable.SM_CLASS);
				String c_startyear = resultSet
						.getString(SuperMajorTable.C_STARTYEAR);
				String p_startyear = resultSet
						.getString(SuperMajorTable.P_STARTYEAR);
				String s_startyear = resultSet
						.getString(SuperMajorTable.S_STARTYEAR);
				String respon_person = resultSet
						.getString(SuperMajorTable.RESPON_PERSON);
				String college = resultSet
						.getString(SuperMajorTable.SM_COLLEGE);
				int sm_serialnumber = resultSet
						.getInt(SuperMajorTable.SM_SERIALNUMBER);
				Date sm_deadline = resultSet
						.getDate(SuperMajorTable.SM_DEADLINE);

				SuperMajor superMajor = new SuperMajor(sm_id, sm_name,
						sm_number, sm_class, c_startyear, p_startyear,
						s_startyear, respon_person, college, sm_serialnumber,
						sm_deadline);

				superMajors.add(superMajor);
			}
			return superMajors;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
	}

}
