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

import cn.edu.xmu.dao.ForeignExchangeDao;
import cn.edu.xmu.entity.ForeignExchange;
import cn.edu.xmu.table.ForeignExchangeTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;

public class ForeignExchangeDaoImpl extends BaseDaoImpl<ForeignExchange>
		implements ForeignExchangeDao {

	@Override
	public List<ForeignExchange> getForeignExchanges(int start, int end,
			String sortStr, String orderStr, Map<String, String> params,Date deadline) {

		String sql = " select tmp.* from ( " + " select * from "
				+ ForeignExchangeTable.TABLE_NAME + " where 1=1 ";
		if (deadline != null) {
			sql += String.format("and "+ForeignExchangeTable.FE_DEADLINE+" like  '%s%%' ", deadline);
		}
		if (!(params == null || params.keySet().size() == 0)) {
			for (Object object : params.keySet()) {

				String key = object.toString();
				String value = params.get(key).toString();
				sql += String.format("and %s like  '%%%s%%' ", key, value);
			}
		}

		sql += " order by " + sortStr + " " + orderStr + " ) tmp limit "
				+ start + " ," + end;

		System.out.println(sql);

		List<ForeignExchange> foreignExchanges = new ArrayList<ForeignExchange>();
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		try {
			System.out.println(sql);
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				int fe_id = resultSet.getInt(ForeignExchangeTable.FE_ID);
 
				String fe_collegename = resultSet.getString(ForeignExchangeTable.FE_COLLEGENAME);
				String fe_projectname = resultSet.getString(ForeignExchangeTable.FE_PROJECTNAME);
				String fe_iscsc = resultSet.getString(ForeignExchangeTable.FE_ISCSC);
				String fe_country  =resultSet.getString(ForeignExchangeTable.FE_COUNTRY);
				String fe_school = resultSet.getString(ForeignExchangeTable.FE_SCHOOL);
				String fe_level  = resultSet.getString(ForeignExchangeTable.FE_LEVEL);
				String fe_time = resultSet.getString(ForeignExchangeTable.FE_TIME);
				Integer fe_selftoforeign = resultSet.getInt(ForeignExchangeTable.FE_SELFTOFOREIGN); 
				if(fe_selftoforeign == -1)
					fe_selftoforeign = null;
				Integer fe_foreigntoself = resultSet.getInt(ForeignExchangeTable.FE_FOREIGNTOSELF);
				if(fe_foreigntoself == -1)
				{
					fe_foreigntoself = null;
				}
				
				String fe_college  = resultSet.getString(ForeignExchangeTable.FE_COLLEGE);
				int fe_serialnumber = resultSet.getInt(ForeignExchangeTable.FE_SERIALNUMBER);
				Date fe_deadline = resultSet.getDate(ForeignExchangeTable.FE_DEADLINE); 
				String fe_comments =  resultSet.getString(ForeignExchangeTable.FE_COMMENTS);
				if(null == fe_comments){
					fe_comments = "";
				}
				Integer fe_isnull = resultSet.getInt(ForeignExchangeTable.FE_ISNULL);
				
				ForeignExchange foreignExchange = new ForeignExchange(fe_id,fe_collegename,fe_projectname, fe_iscsc, fe_country, fe_school, fe_level, fe_time,fe_selftoforeign,fe_foreigntoself,fe_college, fe_deadline, fe_serialnumber,fe_comments,fe_isnull);

				foreignExchanges.add(foreignExchange);
			}
			return foreignExchanges;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}

	}

	@Override
	public int getForeignExchangeCount(Map queryParams) {
		int count = 0;
		String sql = "select count(*) from " + ForeignExchangeTable.TABLE_NAME
				+ " where 1 = 1";
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		if (queryParams != null)
		{
			for (Object object : queryParams.keySet())
			{
				String key = object.toString();
				String value = queryParams.get(key).toString();
				sql += String.format(" and  %s like  '%%%s%%' ", key, value);
			}
			// sql += String.format(" or %s ='%s'", "college","");
		}
		
		try {
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			resultSet.next();
			count = resultSet.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} finally{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}

		System.out.println(count);
		return count;
	}

	@Override
	public boolean batchDelete(String[] smids) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();

		for (String smid : smids) {
			String sql = "delete from " + ForeignExchangeTable.TABLE_NAME
					+ " where " + ForeignExchangeTable.FE_ID + " = '" + smid
					+ "'";
			System.out.println(sql);
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
	public int addForeignExchange(ForeignExchange foreignExchange) {
		int result = 0;

		String sql2 = "update " + ForeignExchangeTable.TABLE_NAME + " set "
				+ ForeignExchangeTable.FE_SERIALNUMBER + " = "
				+ ForeignExchangeTable.FE_SERIALNUMBER + " +1 where "
				+ ForeignExchangeTable.FE_SERIALNUMBER + ">="
				+ foreignExchange.getFe_serialnumber();
		Connection connection2 = null;
		try {
			connection2 = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		PreparedStatement pstmt2 = null;
		try {
			pstmt2 = connection2.prepareStatement(sql2);
			//pstmt2.setInt(1, foreignExchange.getFe_serialnumber());
			
			result = pstmt2.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			try {
				JdbcUtils_DBCP.release(connection2, pstmt2, null);
			} catch (Exception e2) {
				return 0;
			}
		}

		String sql = "insert into " + ForeignExchangeTable.TABLE_NAME + "("
				+ ForeignExchangeTable.FE_COLLEGENAME + ","
				+ ForeignExchangeTable.FE_PROJECTNAME + ","
				+ ForeignExchangeTable.FE_ISCSC + ","
				+ ForeignExchangeTable.FE_COUNTRY + ","
				+ ForeignExchangeTable.FE_SCHOOL + ","
				+ ForeignExchangeTable.FE_LEVEL + ","
				+ ForeignExchangeTable.FE_TIME + ","
				+ ForeignExchangeTable.FE_SELFTOFOREIGN + ","
				+ ForeignExchangeTable.FE_FOREIGNTOSELF + ","
				+ ForeignExchangeTable.FE_COLLEGE + ","
				+ ForeignExchangeTable.FE_SERIALNUMBER + ","
				+ ForeignExchangeTable.FE_ISNULL + ")values(?,?,?,?,?,?,?,?,?,?,?,?)";

		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PreparedStatement pstmt = null;

		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, foreignExchange.getFe_collegename());
			pstmt.setString(2, foreignExchange.getFe_projectname());
			pstmt.setString(3, foreignExchange.getFe_iscsc());
			pstmt.setString(4, foreignExchange.getFe_country());
			pstmt.setString(5, foreignExchange.getFe_school());
			pstmt.setString(6, foreignExchange.getFe_level());
			pstmt.setString(7, foreignExchange.getFe_time());
			pstmt.setInt(8, foreignExchange.getFe_selftoforeign());
			pstmt.setInt(9, foreignExchange.getFe_foreigntoself());
			pstmt.setString(10, foreignExchange.getFe_college());
			pstmt.setInt(11, foreignExchange.getFe_serialnumber());
			pstmt.setInt(12, foreignExchange.getFe_isnull());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			result = -1;
		} finally{
			JdbcUtils_DBCP.release(connection, pstmt, null);
		}


		return result;
	}

	@Override
	public int alterForeignExchange(Map<String, String> valueMap, String id) {
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(ForeignExchangeTable.FE_ID, id);
		int result = updateRecord(ForeignExchangeTable.TABLE_NAME, valueMap,
				condition);
		return result;
	}

	@Override
	public List<ForeignExchange> getAllForeignExchanges() {
		String sql = " select * from " + ForeignExchangeTable.TABLE_NAME
				+ " where 1=1 " + " order by " + ForeignExchangeTable.FE_ID;
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			System.out.println(sql);
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			List<ForeignExchange> foreignExchangeList = new ArrayList<ForeignExchange>();
			while (resultSet.next()) {
				int fe_id = resultSet.getInt(ForeignExchangeTable.FE_ID);
				String fe_collegename = resultSet.getString(ForeignExchangeTable.FE_COLLEGENAME);
				String fe_projectname = resultSet.getString(ForeignExchangeTable.FE_PROJECTNAME);
				String fe_iscsc = resultSet.getString(ForeignExchangeTable.FE_ISCSC);
				String fe_country  =resultSet.getString(ForeignExchangeTable.FE_COUNTRY);
				String fe_school = resultSet.getString(ForeignExchangeTable.FE_SCHOOL);
				String fe_level  = resultSet.getString(ForeignExchangeTable.FE_LEVEL);
				String fe_time = resultSet.getString(ForeignExchangeTable.FE_TIME);
				Integer fe_selftoforeign = resultSet.getInt(ForeignExchangeTable.FE_SELFTOFOREIGN); 
				if(fe_selftoforeign == -1)
					fe_selftoforeign = null;
				Integer fe_foreigntoself = resultSet.getInt(ForeignExchangeTable.FE_FOREIGNTOSELF);
				if(fe_selftoforeign == -1)
					fe_selftoforeign = null;
				
				String fe_college = resultSet.getString(ForeignExchangeTable.FE_COLLEGE);
				int fe_serialnumber = resultSet.getInt(ForeignExchangeTable.FE_SERIALNUMBER);
				Date fe_deadline = resultSet.getDate(ForeignExchangeTable.FE_DEADLINE); 
				String fe_comments =  resultSet.getString(ForeignExchangeTable.FE_COMMENTS);
				if(null == fe_comments){
					fe_comments = "";
				}
				Integer fe_isnull = resultSet.getInt(ForeignExchangeTable.FE_ISNULL);
				
				ForeignExchange foreignExchange = new ForeignExchange(fe_id,fe_collegename,fe_projectname, fe_iscsc, fe_country, fe_school, fe_level, fe_time,fe_selftoforeign,fe_foreigntoself,fe_college,fe_deadline, fe_serialnumber,fe_comments,fe_isnull);
                foreignExchangeList.add(foreignExchange);
			}
			return foreignExchangeList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}

	}

	@Override
	public void deleteAll() {
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		Statement stmt = null;
		try {
			stmt = connection.createStatement();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		String sql = "delete from " + ForeignExchangeTable.TABLE_NAME;
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils_DBCP.release(connection, stmt, null);
		}

	}


	@Override
	public Map<String, Integer> getNumByAttribute(String attribute,
			Map<String, String> params) {
		Map<String, Integer> numByAttributeMap = new HashMap<String, Integer>();

		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		String sql = "SELECT " + attribute + ", COUNT(*) FROM "
				+ ForeignExchangeTable.TABLE_NAME + " GROUP BY " + attribute;
		if (!(params == null || params.keySet().size() == 0)) {
			for (Object object : params.keySet()) {
				String key = object.toString();
				String value = params.get(key).toString();
				sql += String.format(" %s like  '%s%%' ", key, value);
			}
		}

		System.out.println("SQL:" + sql);
		try {
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				numByAttributeMap.put(resultSet.getString(1),
						resultSet.getInt(2));
			}

			// total += resultSet.getRow();
			System.out.println(resultSet.getRow());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcUtils_DBCP.release(connection, pstmt, null);
		}


		return numByAttributeMap;
	}

	@Override
	public void deleteByCollegeandDeadline(String college, Date deadline) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();
		String sql = "delete from " + ForeignExchangeTable.TABLE_NAME
				+ " where " + ForeignExchangeTable.FE_COLLEGE + " = '" + college + "'" +" and fe_deadline is null ";
	//	sql += String.format(" and fe_deadline = ", null);
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
