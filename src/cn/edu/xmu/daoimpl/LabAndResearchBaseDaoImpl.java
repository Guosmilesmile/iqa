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

import cn.edu.xmu.dao.LabAndResearchBaseDao;
import cn.edu.xmu.entity.LabAndResearchBase;
import cn.edu.xmu.table.LabAndResearchBaseTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;

public class LabAndResearchBaseDaoImpl extends BaseDaoImpl<LabAndResearchBase> implements LabAndResearchBaseDao{

	@Override
	public List<LabAndResearchBase> getAllLabAndResearchBase(Map queryParams) {
		String sql = " select * from " + LabAndResearchBaseTable.TABLE_NAME
				+ " where 1=1 ";
		if (queryParams != null) {
			for (Object object : queryParams.keySet()) {
				String key = object.toString();
				String value = queryParams.get(key).toString();
				sql += String.format(" and  %s like '%%%s%%' ", key, value);
			}
			sql += String.format(" or %s ='%s'", LabAndResearchBaseTable.LR_COLLEGE, "");
		}
		sql += " order by " + LabAndResearchBaseTable.LR_ID;
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
			List<LabAndResearchBase> labAndResearchBases = new ArrayList<LabAndResearchBase>();

			while (resultSet.next()) {
				int lr_id = resultSet.getInt(LabAndResearchBaseTable.LR_ID);
				String lr_name = resultSet.getString(LabAndResearchBaseTable.LR_NAME);
				String lr_type = resultSet
						.getString(LabAndResearchBaseTable.LR_TYPE);
				String lr_ifbuildtogether = resultSet.getString(LabAndResearchBaseTable.LR_IFBUILDTOGETHER);
				String lr_ifopentonongraduate = resultSet
						.getString(LabAndResearchBaseTable.LR_IFOPENTONONGRADUATE);
				String college = resultSet
						.getString(LabAndResearchBaseTable.LR_COLLEGE);
				int lr_serialnumber = resultSet
						.getInt(LabAndResearchBaseTable.LR_SERIALNUMBER);
				Date lr_deadline = resultSet
						.getDate(LabAndResearchBaseTable.LR_DEADLINE);
				String lr_comments = resultSet
						.getString(LabAndResearchBaseTable.LR_COMMENTS);

				int isnull = resultSet.getInt(LabAndResearchBaseTable.ISNULL);
				if(lr_comments==null)
					lr_comments="";
				LabAndResearchBase labAndResearchBase = new LabAndResearchBase(lr_id, lr_name, lr_type, lr_ifbuildtogether, lr_ifopentonongraduate, lr_serialnumber, lr_deadline, college, lr_comments,isnull);

				labAndResearchBases.add(labAndResearchBase);
			}
			return labAndResearchBases;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
	}

	@Override
	public List<LabAndResearchBase> getAllLabAndResearchBase() {
		String sql = " select * from " + LabAndResearchBaseTable.TABLE_NAME
				+ " where 1=1 " + " order by " + LabAndResearchBaseTable.LR_ID;
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
			List<LabAndResearchBase> labAndResearchBases = new ArrayList<LabAndResearchBase>();

			while (resultSet.next()) {
				int lr_id = resultSet.getInt(LabAndResearchBaseTable.LR_ID);
				String lr_name = resultSet.getString(LabAndResearchBaseTable.LR_NAME);
				String lr_type = resultSet
						.getString(LabAndResearchBaseTable.LR_TYPE);
				String lr_ifbuildtogether = resultSet.getString(LabAndResearchBaseTable.LR_IFBUILDTOGETHER);
				String lr_ifopentonongraduate = resultSet
						.getString(LabAndResearchBaseTable.LR_IFOPENTONONGRADUATE);
				String college = resultSet
						.getString(LabAndResearchBaseTable.LR_COLLEGE);
				int lr_serialnumber = resultSet
						.getInt(LabAndResearchBaseTable.LR_SERIALNUMBER);
				Date lr_deadline = resultSet
						.getDate(LabAndResearchBaseTable.LR_DEADLINE);
				String lr_comments = resultSet
						.getString(LabAndResearchBaseTable.LR_COMMENTS);

				int isnull = resultSet.getInt(LabAndResearchBaseTable.ISNULL);
				if(lr_comments==null)
					lr_comments="";
				LabAndResearchBase labAndResearchBase = new LabAndResearchBase(lr_id, lr_name, lr_type, lr_ifbuildtogether, lr_ifopentonongraduate, lr_serialnumber, lr_deadline, college, lr_comments,isnull);

				labAndResearchBases.add(labAndResearchBase);
			}
			return labAndResearchBases;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
	}

	@Override
	public List<LabAndResearchBase> getAllLabAndResearchBase(int start,
			int end, String sortStr, String orderStr, Map queryParams) {
		String sql = " select tmp.* from ( " + " select * from "
				+ LabAndResearchBaseTable.TABLE_NAME + " where 1=1 ";
		if (queryParams != null) {
			for (Object object : queryParams.keySet()) {
				String key = object.toString();
				String value = queryParams.get(key).toString();
				sql += String.format(" and %s like  '%%%s%%'", key, value);
			}
		}
		
		sql += String.format(" or %s ='%s'", LabAndResearchBaseTable.LR_COLLEGE, "");
		sql += " order by " + sortStr + " " + orderStr + " ) tmp limit "
				+ start + " ," + end;

		System.out.println(sql);
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		List<LabAndResearchBase> labAndResearchBases = new ArrayList<LabAndResearchBase>();
		try {
			System.out.println(sql);
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int lr_id = resultSet.getInt(LabAndResearchBaseTable.LR_ID);
				String lr_name = resultSet.getString(LabAndResearchBaseTable.LR_NAME);
				String lr_type = resultSet
						.getString(LabAndResearchBaseTable.LR_TYPE);
				String lr_ifbuildtogether = resultSet.getString(LabAndResearchBaseTable.LR_IFBUILDTOGETHER);
				String lr_ifopentonongraduate = resultSet
						.getString(LabAndResearchBaseTable.LR_IFOPENTONONGRADUATE);
				String college = resultSet
						.getString(LabAndResearchBaseTable.LR_COLLEGE);
				int lr_serialnumber = resultSet
						.getInt(LabAndResearchBaseTable.LR_SERIALNUMBER);
				Date lr_deadline = resultSet
						.getDate(LabAndResearchBaseTable.LR_DEADLINE);
				String lr_comments = resultSet
						.getString(LabAndResearchBaseTable.LR_COMMENTS);

				int isnull = resultSet.getInt(LabAndResearchBaseTable.ISNULL);
				if(lr_comments==null)
					lr_comments="";
				LabAndResearchBase labAndResearchBase = new LabAndResearchBase(lr_id, lr_name, lr_type, lr_ifbuildtogether, lr_ifopentonongraduate, lr_serialnumber, lr_deadline, college, lr_comments,isnull);

				labAndResearchBases.add(labAndResearchBase);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return labAndResearchBases;
	}

	@Override
	public int addLabAndResearchBase(LabAndResearchBase labAndResearchBase) {
		int result = 0;

		String sql2 = "update " + LabAndResearchBaseTable.TABLE_NAME + " set "
				+ LabAndResearchBaseTable.LR_SERIALNUMBER + " = "
				+ LabAndResearchBaseTable.LR_SERIALNUMBER + " +1 where "
				+ LabAndResearchBaseTable.LR_SERIALNUMBER + ">=" + labAndResearchBase.getLr_serialnumber();

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
		} finally{
			JdbcUtils_DBCP.release(connection2, pstmt2, null);
		}
		

		String sql = "insert into " + LabAndResearchBaseTable.TABLE_NAME + "("
				+ LabAndResearchBaseTable.LR_NAME + ","
				+ LabAndResearchBaseTable.LR_TYPE + ","
				+ LabAndResearchBaseTable.LR_IFBUILDTOGETHER + ","
				+ LabAndResearchBaseTable.LR_IFOPENTONONGRADUATE + ","
				+ LabAndResearchBaseTable.LR_SERIALNUMBER + ","
				+ LabAndResearchBaseTable.LR_DEADLINE + ","
				+ LabAndResearchBaseTable.LR_COLLEGE + ","
				+ LabAndResearchBaseTable.LR_COMMENTS +","
				+ LabAndResearchBaseTable.ISNULL
				+ ")values(?,?,?,?,?,?,?,?,?)";

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
			pstmt.setString(1, labAndResearchBase.getLr_name());
			pstmt.setString(2, labAndResearchBase.getLr_type());
			pstmt.setString(3, labAndResearchBase.getLr_ifbuildtogether());
			pstmt.setString(4, labAndResearchBase.getLr_ifopentonongraduate());
			pstmt.setInt(5, labAndResearchBase.getLr_serialnumber());
			pstmt.setDate(6, labAndResearchBase.getLr_deadline());
			pstmt.setString(7, labAndResearchBase.getLr_college());
			pstmt.setString(8, labAndResearchBase.getLr_comments());
			pstmt.setInt(9, labAndResearchBase.getIsnull());

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
	public int alterLabAndResearchBase(Map<String, String> valueMap, String id) {
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(LabAndResearchBaseTable.LR_ID, id);
		int result = updateRecord(LabAndResearchBaseTable.TABLE_NAME, valueMap,
				condition);
		return result;
	}

	@Override
	public int deleteLabAndResearchBaseById(String id, String serialnumber) {
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(LabAndResearchBaseTable.LR_ID, id);
		int result = deleteRecord(LabAndResearchBaseTable.TABLE_NAME, condition);

		String sql2 = "update " + LabAndResearchBaseTable.TABLE_NAME + " set "
				+ LabAndResearchBaseTable.LR_SERIALNUMBER + " = "
				+ LabAndResearchBaseTable.LR_SERIALNUMBER + " -1 where "
				+ LabAndResearchBaseTable.LR_SERIALNUMBER + ">=" + serialnumber;

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
	public boolean batchDelete(String[] smids) throws SQLException {
		int deleteOne;
		int deleteCount = 0;
		for (String smid : smids) {
			deleteOne = deleteLabAndResearchBaseById(smid, Integer.toString(getSerialNumberById(smid)));
			deleteCount += deleteOne;
		}
		
		if (deleteCount == smids.length) {
			return true;
		}else{
			return false;
		}
	}

	@Override
	public int getMaxSerialNum() {
		ResultSet resultSet = null;
		int result = 1;
		String sql = "select max(" + LabAndResearchBaseTable.LR_SERIALNUMBER
				+ ") from " + LabAndResearchBaseTable.TABLE_NAME;

		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
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
	public int getLabAndResearchBaseCount(Map queryParams) {
		int count = 0;
		String sql = "select count(*) from " + LabAndResearchBaseTable.TABLE_NAME
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
		sql += String.format(" or %s ='%s'", LabAndResearchBaseTable.LR_COLLEGE, "");

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
	public List<LabAndResearchBase> findForPage(int start, int end,
			String sortStr, String orderStr, Map queryParams) {
		// TODO Auto-generated method stub
		return null;
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

		String sql = "delete from " + LabAndResearchBaseTable.TABLE_NAME;
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JdbcUtils_DBCP.release(connection, stmt, null);
	}

	@Override
	public int getSerialNumberById(String id) {
		String sql = " select "+LabAndResearchBaseTable.LR_SERIALNUMBER+" from " + LabAndResearchBaseTable.TABLE_NAME
				+ " where 1=1 ";
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
			resultSet.next();
			int lr_serialnumber = resultSet
						.getInt(LabAndResearchBaseTable.LR_SERIALNUMBER);
				
			return lr_serialnumber;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
	}

	@Override
	public void deleteByCollegeandDeadline(String college) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();
		String sql = "delete from " + LabAndResearchBaseTable.TABLE_NAME
				+ " where " + LabAndResearchBaseTable.LR_COLLEGE + " = '" + college + "'" +" and "
				+LabAndResearchBaseTable.LR_DEADLINE+" is null ";
		
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
