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

import cn.edu.xmu.dao.EducationMoneyDao;
import cn.edu.xmu.entity.EducationMoney;
import cn.edu.xmu.table.EducationMoneyTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;

/**
 * 
 * @author Gy 附表2-10-2-1教育经费收支情况
 */

public class EducationMoneyDaoImpl extends BaseDaoImpl<EducationMoney> implements EducationMoneyDao {

	@Override
	public int addRecord(EducationMoney em) {

		int result = 0;

		String t_sql = "update " + EducationMoneyTable.TABLE_NAME + " set "
				+ EducationMoneyTable.EM_SERIALNUMBER + " = " + EducationMoneyTable.EM_SERIALNUMBER
				+ " +1 where " + EducationMoneyTable.EM_SERIALNUMBER + ">=?";

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
			t_pstmt.setInt(1, em.getEm_serialnumber());

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
		String sql = "insert into " + EducationMoneyTable.TABLE_NAME + "("
				+ EducationMoneyTable.EM_COLLEGES + "," + EducationMoneyTable.EM_INTOTAL + ","
				+ EducationMoneyTable.EM_INSCHOOLFUNDS + "," + EducationMoneyTable.EM_INCHANGE
				+ "," + EducationMoneyTable.EM_INSTUDENTACTIVITY + ","
				+ EducationMoneyTable.EM_INBUY + "," + EducationMoneyTable.EM_INSELFRAISE + ","
				+ EducationMoneyTable.EM_INDONATIONS + "," + EducationMoneyTable.EM_OUTTOTAL + ","
				+ EducationMoneyTable.EM_OUTDAILY + "," + EducationMoneyTable.EM_OUTCHANGE + ","
				+ EducationMoneyTable.EM_OUTBUILD + "," + EducationMoneyTable.EM_OUTPRACTICE + ","
				+ EducationMoneyTable.EM_OUTPRACTICEEXPERIMENT + ","
				+ EducationMoneyTable.EM_OUTPRACTICEINTER + "," + EducationMoneyTable.EM_OUTOTHER
				+ "," + EducationMoneyTable.EM_OUTSTUDENTACTIVITY + ","
				+ EducationMoneyTable.EM_OUTTEACHER + "," + EducationMoneyTable.EM_SERIALNUMBER
				+ "," + EducationMoneyTable.EM_COLLEGE + "," + EducationMoneyTable.EM_COMMENTS
				+ ",isnull" + ")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,'',?)";
		System.out.println(sql);
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, em.getEm_colleges());
			pstmt.setFloat(2, em.getEm_intotal());
			pstmt.setFloat(3, em.getEm_inschoolfunds());
			pstmt.setFloat(4, em.getEm_inchange());
			pstmt.setFloat(5, em.getEm_instudentactivity());
			pstmt.setFloat(6, em.getEm_inbuy());
			pstmt.setFloat(7, em.getEm_inselfraise());
			pstmt.setFloat(8, em.getEm_indonations());
			pstmt.setFloat(9, em.getEm_outtotal());
			pstmt.setFloat(10, em.getEm_outdaily());
			pstmt.setFloat(11, em.getEm_outchange());
			pstmt.setFloat(12, em.getEm_outbuild());
			pstmt.setFloat(13, em.getEm_outpractice());
			pstmt.setFloat(14, em.getEm_outpracticeexperiment());
			pstmt.setFloat(15, em.getEm_outpracticeinter());
			pstmt.setFloat(16, em.getEm_outother());
			pstmt.setFloat(17, em.getEm_outstudentactivity());
			pstmt.setFloat(18, em.getEm_outteacher());
			pstmt.setInt(19, em.getEm_serialnumber());
			pstmt.setString(20, em.getEm_college());
			pstmt.setInt(21, em.getIsnull());
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
	public int getEducationMoneyCount(Map queryParams) {

		int count = 0;
		// 获取条件的语句
		String sql = "select count(*) from " + EducationMoneyTable.TABLE_NAME + " where 1 = 1";
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
		sql += String.format(" or %s ='%s'", EducationMoneyTable.EM_COLLEGE, "");

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
	public List<EducationMoney> getAllEducationMoney(int start, int end, String sortStr,
			String orderStr, Map queryParams) {

		String sql = " select tmp.* from ( " + " select * from " + EducationMoneyTable.TABLE_NAME
				+ " where 1=1 ";

		for (Object object : queryParams.keySet()) {
			String key = object.toString();
			String value = queryParams.get(key).toString();
			sql += String.format(" and %s like  '%%%s%%'", key, value);
		}

		if (sortStr == "nope") {

		} else {
			sql += " order by " + sortStr + " " + orderStr + " ) tmp limit " + start + " ," + end;
		}

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		List<EducationMoney> ems = new ArrayList<EducationMoney>();
		try {

			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {

				int em_id = resultSet.getInt(EducationMoneyTable.EM_ID);
				String em_colleges = resultSet.getString(EducationMoneyTable.EM_COLLEGES);
				Float em_inchange = resultSet.getFloat(EducationMoneyTable.EM_INCHANGE);
				if (em_inchange == -999)
					em_inchange = null;
				Float em_intotal = resultSet.getFloat(EducationMoneyTable.EM_INTOTAL);
				if (em_intotal == -999)
					em_intotal = null;
				Float em_inschoolfunds = resultSet.getFloat(EducationMoneyTable.EM_INSCHOOLFUNDS);
				if (em_inschoolfunds == -999)
					em_inschoolfunds = null;
				Float em_instudentactivity = resultSet
						.getFloat(EducationMoneyTable.EM_INSTUDENTACTIVITY);
				if (em_instudentactivity == -999)
					em_instudentactivity = null;
				Float em_inbuy = resultSet.getFloat(EducationMoneyTable.EM_INBUY);
				if (em_inbuy == -999)
					em_inbuy = null;
				Float em_inselfraise = resultSet.getFloat(EducationMoneyTable.EM_INSELFRAISE);
				if (em_inselfraise == -999)
					em_inselfraise = null;
				Float em_indonations = resultSet.getFloat(EducationMoneyTable.EM_INDONATIONS);
				if (em_indonations == -999)
					em_indonations = null;
				Float em_outtotal = resultSet.getFloat(EducationMoneyTable.EM_OUTTOTAL);
				if (em_outtotal == -999)
					em_outtotal = null;
				Float em_outdaily = resultSet.getFloat(EducationMoneyTable.EM_OUTDAILY);
				if (em_outdaily == -999)
					em_outdaily = null;
				Float em_outchange = resultSet.getFloat(EducationMoneyTable.EM_OUTCHANGE);
				if (em_outchange == -999)
					em_outchange = null;
				Float em_outbuild = resultSet.getFloat(EducationMoneyTable.EM_OUTBUILD);
				if (em_outbuild == -999)
					em_outbuild = null;
				Float em_outpractice = resultSet.getFloat(EducationMoneyTable.EM_OUTPRACTICE);
				if (em_outpractice == -999)
					em_outpractice = null;
				Float em_outpracticeexperiment = resultSet
						.getFloat(EducationMoneyTable.EM_OUTPRACTICEEXPERIMENT);
				if (em_outpracticeexperiment == -999)
					em_outpracticeexperiment = null;
				Float em_outpracticeinter = resultSet
						.getFloat(EducationMoneyTable.EM_OUTPRACTICEINTER);
				if (em_outpracticeinter == -999)
					em_outpracticeinter = null;
				Float em_outother = resultSet.getFloat(EducationMoneyTable.EM_OUTOTHER);
				if (em_outother == -999)
					em_outother = null;
				Float em_outstudentactivity = resultSet
						.getFloat(EducationMoneyTable.EM_OUTSTUDENTACTIVITY);
				if (em_outstudentactivity == -999)
					em_outstudentactivity = null;
				Float em_outteacher = resultSet.getFloat(EducationMoneyTable.EM_OUTTEACHER);
				if (em_outteacher == -999)
					em_outteacher = null;
				int em_serialnumber = resultSet.getInt(EducationMoneyTable.EM_SERIALNUMBER);
				String em_college = resultSet.getString(EducationMoneyTable.EM_COLLEGE);
				String em_comments = resultSet.getString(EducationMoneyTable.EM_COMMENTS);
				Date em_deadline = resultSet.getDate(EducationMoneyTable.EM_DEADLINE);
				EducationMoney em = new EducationMoney(em_id, em_colleges, em_intotal,
						em_inschoolfunds, em_inchange, em_instudentactivity, em_inbuy,
						em_inselfraise, em_indonations, em_outtotal, em_outdaily, em_outchange,
						em_outbuild, em_outpractice, em_outpracticeexperiment, em_outpracticeinter,
						em_outother, em_outstudentactivity, em_outteacher, em_serialnumber,
						em_deadline, em_college, em_comments);
				ems.add(em);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return ems;
	}

	@Override
	public int alterEducationMoney(Map<String, String> valueMap, String id) {

		Map<String, String> condition = new HashMap<String, String>();
		condition.put(EducationMoneyTable.EM_ID, id);
		int result = updateRecord(EducationMoneyTable.TABLE_NAME, valueMap, condition);
		return result;
	}

	@Override
	public boolean batchDelete(String[] seuids) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();

		for (String seuid : seuids) {
			String sql = "delete from " + EducationMoneyTable.TABLE_NAME + " where "
					+ EducationMoneyTable.EM_ID + " = '" + seuid + "'";
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
	public List<EducationMoney> getAllEducationMoney() {
		String sql = " select * from " + EducationMoneyTable.TABLE_NAME + " where 1=1 "
				+ " order by " + EducationMoneyTable.EM_ID;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		List<EducationMoney> ems = new ArrayList<EducationMoney>();
		try {

			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {

				int em_id = resultSet.getInt(EducationMoneyTable.EM_ID);
				String em_colleges = resultSet.getString(EducationMoneyTable.EM_COLLEGES);
				Float em_inchange = resultSet.getFloat(EducationMoneyTable.EM_INCHANGE);
				if (em_inchange == -999)
					em_inchange = null;
				Float em_intotal = resultSet.getFloat(EducationMoneyTable.EM_INTOTAL);
				if (em_intotal == -999)
					em_intotal = null;
				Float em_inschoolfunds = resultSet.getFloat(EducationMoneyTable.EM_INSCHOOLFUNDS);
				if (em_inschoolfunds == -999)
					em_inschoolfunds = null;
				Float em_instudentactivity = resultSet
						.getFloat(EducationMoneyTable.EM_INSTUDENTACTIVITY);
				if (em_instudentactivity == -999)
					em_instudentactivity = null;
				Float em_inbuy = resultSet.getFloat(EducationMoneyTable.EM_INBUY);
				if (em_inbuy == -999)
					em_inbuy = null;
				Float em_inselfraise = resultSet.getFloat(EducationMoneyTable.EM_INSELFRAISE);
				if (em_inselfraise == -999)
					em_inselfraise = null;
				Float em_indonations = resultSet.getFloat(EducationMoneyTable.EM_INDONATIONS);
				if (em_indonations == -999)
					em_indonations = null;
				Float em_outtotal = resultSet.getFloat(EducationMoneyTable.EM_OUTTOTAL);
				if (em_outtotal == -999)
					em_outtotal = null;
				Float em_outdaily = resultSet.getFloat(EducationMoneyTable.EM_OUTDAILY);
				if (em_outdaily == -999)
					em_outdaily = null;
				Float em_outchange = resultSet.getFloat(EducationMoneyTable.EM_OUTCHANGE);
				if (em_outchange == -999)
					em_outchange = null;
				Float em_outbuild = resultSet.getFloat(EducationMoneyTable.EM_OUTBUILD);
				if (em_outbuild == -999)
					em_outbuild = null;
				Float em_outpractice = resultSet.getFloat(EducationMoneyTable.EM_OUTPRACTICE);
				if (em_outpractice == -999)
					em_outpractice = null;
				Float em_outpracticeexperiment = resultSet
						.getFloat(EducationMoneyTable.EM_OUTPRACTICEEXPERIMENT);
				if (em_outpracticeexperiment == -999)
					em_outpracticeexperiment = null;
				Float em_outpracticeinter = resultSet
						.getFloat(EducationMoneyTable.EM_OUTPRACTICEINTER);
				if (em_outpracticeinter == -999)
					em_outpracticeinter = null;
				Float em_outother = resultSet.getFloat(EducationMoneyTable.EM_OUTOTHER);
				if (em_outother == -999)
					em_outother = null;
				Float em_outstudentactivity = resultSet
						.getFloat(EducationMoneyTable.EM_OUTSTUDENTACTIVITY);
				if (em_outstudentactivity == -999)
					em_outstudentactivity = null;
				Float em_outteacher = resultSet.getFloat(EducationMoneyTable.EM_OUTTEACHER);
				if (em_outteacher == -999)
					em_outteacher = null;
				int em_serialnumber = resultSet.getInt(EducationMoneyTable.EM_SERIALNUMBER);
				String em_college = resultSet.getString(EducationMoneyTable.EM_COLLEGE);
				String em_comments = resultSet.getString(EducationMoneyTable.EM_COMMENTS);
				Date em_deadline = resultSet.getDate(EducationMoneyTable.EM_DEADLINE);
				EducationMoney em = new EducationMoney(em_id, em_colleges, em_intotal,
						em_inschoolfunds, em_inchange, em_instudentactivity, em_inbuy,
						em_inselfraise, em_indonations, em_outtotal, em_outdaily, em_outchange,
						em_outbuild, em_outpractice, em_outpracticeexperiment, em_outpracticeinter,
						em_outother, em_outstudentactivity, em_outteacher, em_serialnumber,
						em_deadline, em_college, em_comments);
				ems.add(em);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return ems;
	}

	@Override
	public void deleteByCollegeandDeadline(String college, Date deadline) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();
		String sql = "delete from " + EducationMoneyTable.TABLE_NAME + " where "
				+ EducationMoneyTable.EM_COLLEGE + " = '" + college + "'"
				+ " and em_deadline is null ";
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
