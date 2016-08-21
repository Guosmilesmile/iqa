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

import cn.edu.xmu.dao.StaffTeachingAchieveDao;
import cn.edu.xmu.entity.StaffTeachingAchieve;
import cn.edu.xmu.table.NewBooksthatYearTable;
import cn.edu.xmu.table.StaffTeachingAchieveTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;


/**
 * @author zhantu
 * 教学管理人员获教学成果奖情况（时点）  实体类功能 ——接口实现
 * date 2015-07-09
 */

public class StaffTeachingAchieveDaoImpl extends BaseDaoImpl<StaffTeachingAchieve>implements StaffTeachingAchieveDao{

	@Override
	public int addRecord(StaffTeachingAchieve sta) {
		
		int result = 0;

		String t_sql = "update " + StaffTeachingAchieveTable.TABLE_NAME + " set "
				+ StaffTeachingAchieveTable.STA_SERIALNUMBER + " = "
				+ StaffTeachingAchieveTable.STA_SERIALNUMBER + " +1 where "
				+ StaffTeachingAchieveTable.STA_SERIALNUMBER + ">=?";

		
		Connection connection2 = null;
		try {
			//连接池取得对象连接
			connection2 = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
			return 0;
		}
		PreparedStatement t_pstmt = null;
		try {
			//获取操作对象
			t_pstmt = connection2.prepareStatement(t_sql);
			t_pstmt.setInt(1, sta.getSta_serialnumber());
			
			//执行插入操作并更新
			result = t_pstmt.executeUpdate();
			
		} catch (SQLException e) {
			//事务回滚，不做插入操作
			e.printStackTrace();
			return 0;
		} finally {
			JdbcUtils_DBCP.release(connection2, t_pstmt, null);
		}

		//执行插入操作的语句
		String sql = "insert into " + StaffTeachingAchieveTable.TABLE_NAME + "("
				+ StaffTeachingAchieveTable.STA_YEAR + ","
				+ StaffTeachingAchieveTable.STA_SESSION + ","
				+ StaffTeachingAchieveTable.STA_COLLEGENAME + ","
				+ StaffTeachingAchieveTable.STA_PROJECTNAME + ","
				+ StaffTeachingAchieveTable.STA_PERSONS + ","
				
				+ StaffTeachingAchieveTable.STA_COUNTRY + ","
				+ StaffTeachingAchieveTable.STA_PROVINCE + ","
				+ StaffTeachingAchieveTable.STA_SCHOOL + ","
				+ StaffTeachingAchieveTable.STA_IFSTAFFATTEND + ","
				
				+ StaffTeachingAchieveTable.STA_SERIALNUMBER + ","
				+ StaffTeachingAchieveTable.STA_COLLEGE + ","
				+ StaffTeachingAchieveTable.STA_COMMENTS + ","
				+ StaffTeachingAchieveTable.ISNULL
				+ ")values(?,?,?,?,?,?,?,?,?,?,?,?,?)";

		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, sta.getSta_year());
			pstmt.setString(2, sta.getSta_session());
			pstmt.setString(3, sta.getSta_collegename());
			pstmt.setString(4, sta.getSta_projectname());
			pstmt.setString(5, sta.getSta_persons());
			pstmt.setString(6, sta.getSta_country());
			pstmt.setString(7, sta.getSta_province());
			pstmt.setString(8, sta.getSta_school());
			pstmt.setString(9, sta.getSta_ifstaffattend());
			pstmt.setInt(10, sta.getSta_serialnumber());
			pstmt.setString(11, sta.getSta_college());
			pstmt.setString(12, sta.getSta_comments());
			pstmt.setInt(13, sta.getIsnull());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			//事务回滚。不做插入操作
			e.printStackTrace();
			result = -1;
			
		} finally {
			JdbcUtils_DBCP.release(connection,pstmt,null);
		}
		return result;
		
	}

	@Override
	public int getStaffTeachingAchieveCount(Map queryParams) {
		
		int count = 0;
		//获取条件的语句
		String sql = "select count(*) from " + StaffTeachingAchieveTable.TABLE_NAME
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
		sql += String.format(" or %s ='%s'", StaffTeachingAchieveTable.STA_COLLEGE, "");
		
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
	public List<StaffTeachingAchieve> getAllStaffTeachingAchieve(int start, int end,
			String sortStr, String orderStr, Map queryParams) {
		
		String sql = " select tmp.* from ( " + " select * from "
				+ StaffTeachingAchieveTable.TABLE_NAME + " where 1=1 ";
		
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
		
		List<StaffTeachingAchieve> stas = new ArrayList<StaffTeachingAchieve>();
		try {
			
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int sta_id = resultSet.getInt(StaffTeachingAchieveTable.STA_ID);

				Integer sta_year = resultSet.getInt(StaffTeachingAchieveTable.STA_YEAR);
				if(sta_year==-999)
					sta_year = null;
				String sta_session = resultSet.getString(StaffTeachingAchieveTable.STA_SESSION);
				String sta_collegename = resultSet.getString(StaffTeachingAchieveTable.STA_COLLEGENAME);
				String sta_projectname = resultSet.getString(StaffTeachingAchieveTable.STA_PROJECTNAME);
				String sta_persons = resultSet.getString(StaffTeachingAchieveTable.STA_PERSONS);

				String sta_country = resultSet.getString(StaffTeachingAchieveTable.STA_COUNTRY);
				String sta_province = resultSet.getString(StaffTeachingAchieveTable.STA_PROVINCE);
				String sta_school = resultSet.getString(StaffTeachingAchieveTable.STA_SCHOOL);
				String sta_ifstaffattend = resultSet.getString(StaffTeachingAchieveTable.STA_IFSTAFFATTEND);
				
				int sta_serialnumber = resultSet.getInt(StaffTeachingAchieveTable.STA_SERIALNUMBER);
				Date sta_deadline = resultSet.getDate(StaffTeachingAchieveTable.STA_DEADLINE);
				String sta_comments = resultSet.getString(StaffTeachingAchieveTable.STA_COMMENTS);
				String sta_college = resultSet.getString(StaffTeachingAchieveTable.STA_COLLEGE);
				int isnull = resultSet.getInt(StaffTeachingAchieveTable.ISNULL);
				if(sta_comments==null)
					sta_comments = "";
				
				StaffTeachingAchieve sta = new StaffTeachingAchieve(sta_id, sta_year, sta_session,
						sta_collegename, sta_projectname, sta_persons,
						sta_country, sta_province, sta_school,
						sta_ifstaffattend, sta_serialnumber, sta_deadline,
						sta_college, sta_comments, isnull);
				
				stas.add(sta);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return stas;
	}

	@Override
	public int alterStaffTeachingAchieve(Map<String, String> valueMap, String id) {
		
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(StaffTeachingAchieveTable.STA_ID, id);
		int result = updateRecord(StaffTeachingAchieveTable.TABLE_NAME, valueMap,
				condition);
		return result;
	}

	@Override
	public boolean batchDelete(String[] staids) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();

		for (String staid : staids) {
			String sql = "delete from " + StaffTeachingAchieveTable.TABLE_NAME
					+ " where " + StaffTeachingAchieveTable.STA_ID + " = '" + staid + "'";
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
	public List<StaffTeachingAchieve> getStaffTeachingAchieve(int start, int end,
			String sortStr, String orderStr, Map<String, String> params,
			Date deadline) {
		String sql = " select tmp.* from ( " + " select * from "
				+ StaffTeachingAchieveTable.TABLE_NAME + " where 1=1 ";
		if (deadline != null) {

			sql += String.format("and "+StaffTeachingAchieveTable.STA_DEADLINE+" like  '%s%%' ", deadline);
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

		List<StaffTeachingAchieve> staffTeachingAchieves = new ArrayList<StaffTeachingAchieve>();
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
				int sta_id = resultSet.getInt(StaffTeachingAchieveTable.STA_ID);

				Integer sta_year = resultSet.getInt(StaffTeachingAchieveTable.STA_YEAR);
				if(sta_year==-999)
					sta_year = null;
				String sta_session = resultSet.getString(StaffTeachingAchieveTable.STA_SESSION);
				String sta_collegename = resultSet.getString(StaffTeachingAchieveTable.STA_COLLEGENAME);
				String sta_projectname = resultSet.getString(StaffTeachingAchieveTable.STA_PROJECTNAME);
				String sta_persons = resultSet.getString(StaffTeachingAchieveTable.STA_PERSONS);

				String sta_country = resultSet.getString(StaffTeachingAchieveTable.STA_COUNTRY);
				String sta_province = resultSet.getString(StaffTeachingAchieveTable.STA_PROVINCE);
				String sta_school = resultSet.getString(StaffTeachingAchieveTable.STA_SCHOOL);
				String sta_ifstaffattend = resultSet.getString(StaffTeachingAchieveTable.STA_IFSTAFFATTEND);
				
				int sta_serialnumber = resultSet.getInt(StaffTeachingAchieveTable.STA_SERIALNUMBER);
				Date sta_deadline = resultSet.getDate(StaffTeachingAchieveTable.STA_DEADLINE);
				String sta_comments = resultSet.getString(StaffTeachingAchieveTable.STA_COMMENTS);
				String sta_college = resultSet.getString(StaffTeachingAchieveTable.STA_COLLEGE);
				int isnull = resultSet.getInt(StaffTeachingAchieveTable.ISNULL);
				StaffTeachingAchieve sta = new StaffTeachingAchieve(sta_id, sta_year, sta_session,
						sta_collegename, sta_projectname, sta_persons,
						sta_country, sta_province, sta_school,
						sta_ifstaffattend, sta_serialnumber, sta_deadline,
						sta_college, sta_comments, isnull);

				staffTeachingAchieves.add(sta);
			}
			return staffTeachingAchieves;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
	}
	
	@Override
	public void deleteByCollegeandDeadline(String college, Date deadline)
			throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();
		String sql = "delete from " + StaffTeachingAchieveTable.TABLE_NAME
				+ " where " + StaffTeachingAchieveTable.STA_COLLEGE + " = '" + college + "'" +" and sta_deadline is null ";
		System.out.println(sql);
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils_DBCP.release(connection, stmt, null);
		}
		
	}

	@Override
	public List<StaffTeachingAchieve> getAllStaffTeachingAchieve() {
		String sql = " select * from " + StaffTeachingAchieveTable.TABLE_NAME
				+ " where 1=1 " + " order by " + StaffTeachingAchieveTable.STA_ID;
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
			List<StaffTeachingAchieve> staffTeachingAchieveList = new ArrayList<StaffTeachingAchieve>();
			while (resultSet.next()) {
				int sta_id = resultSet.getInt(StaffTeachingAchieveTable.STA_ID);

				Integer sta_year = resultSet.getInt(StaffTeachingAchieveTable.STA_YEAR);
				if(sta_year==-999)
					sta_year = null;
				String sta_session = resultSet.getString(StaffTeachingAchieveTable.STA_SESSION);
				String sta_collegename = resultSet.getString(StaffTeachingAchieveTable.STA_COLLEGENAME);
				String sta_projectname = resultSet.getString(StaffTeachingAchieveTable.STA_PROJECTNAME);
				String sta_persons = resultSet.getString(StaffTeachingAchieveTable.STA_PERSONS);

				String sta_country = resultSet.getString(StaffTeachingAchieveTable.STA_COUNTRY);
				String sta_province = resultSet.getString(StaffTeachingAchieveTable.STA_PROVINCE);
				String sta_school = resultSet.getString(StaffTeachingAchieveTable.STA_SCHOOL);
				String sta_ifstaffattend = resultSet.getString(StaffTeachingAchieveTable.STA_IFSTAFFATTEND);
				
				int sta_serialnumber = resultSet.getInt(StaffTeachingAchieveTable.STA_SERIALNUMBER);
				Date sta_deadline = resultSet.getDate(StaffTeachingAchieveTable.STA_DEADLINE);
				String sta_comments = resultSet.getString(StaffTeachingAchieveTable.STA_COMMENTS);
				String sta_college = resultSet.getString(StaffTeachingAchieveTable.STA_COLLEGE);
				int isnull = resultSet.getInt(StaffTeachingAchieveTable.ISNULL);
				StaffTeachingAchieve sta = new StaffTeachingAchieve(sta_id, sta_year, sta_session,
						sta_collegename, sta_projectname, sta_persons,
						sta_country, sta_province, sta_school,
						sta_ifstaffattend, sta_serialnumber, sta_deadline,
						sta_college, sta_comments, isnull);

				staffTeachingAchieveList.add(sta);
			}
			return staffTeachingAchieveList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
	}
}
