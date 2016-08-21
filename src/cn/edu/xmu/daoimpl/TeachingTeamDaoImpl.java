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

import cn.edu.xmu.dao.TeachingTeamDao;
import cn.edu.xmu.entity.TeachingTeam;
import cn.edu.xmu.table.TeachingTeamTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;


/**
 * @author zhantu
 * 教学团队（时点）  实体类功能 ——接口实现
 * date 2015-07-08
 */

public class TeachingTeamDaoImpl extends BaseDaoImpl<TeachingTeam>implements TeachingTeamDao{

	@Override
	public int addRecord(TeachingTeam tt) {
		
		int result = 0;

		String t_sql = "update " + TeachingTeamTable.TABLE_NAME + " set "
				+ TeachingTeamTable.TT_SERIALNUMBER + " = "
				+ TeachingTeamTable.TT_SERIALNUMBER + " +1 where "
				+ TeachingTeamTable.TT_SERIALNUMBER + ">=?";

		
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
			t_pstmt.setInt(1, tt.getTt_serialnumber());
			
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
		String sql = "insert into " + TeachingTeamTable.TABLE_NAME + "("
				+ TeachingTeamTable.TT_SEQUENCENUMBER + ","
				+ TeachingTeamTable.TT_COLLEGENAME + ","
				+ TeachingTeamTable.TT_NAME + ","
				+ TeachingTeamTable.TT_LEADER + ","
				+ TeachingTeamTable.TT_COUNTRY + ","
				+ TeachingTeamTable.TT_PROVINCE + ","
				+ TeachingTeamTable.TT_SERIALNUMBER + ","
				+ TeachingTeamTable.TT_COLLEGE + ","
				+ TeachingTeamTable.TT_COMMENTS + ","
				+ TeachingTeamTable.ISNULL
				+ ")values(?,?,?,?,?,?,?,?,?,?)";

		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, tt.getTt_sequencenumber());
			pstmt.setString(2, tt.getTt_collegename());
			pstmt.setString(3, tt.getTt_name());
			pstmt.setString(4, tt.getTt_leader());
			pstmt.setInt(5, tt.getTt_country());
			pstmt.setInt(6, tt.getTt_province());
			pstmt.setInt(7, tt.getTt_serialnumber());
			pstmt.setString(8, tt.getTt_college());
			pstmt.setString(9, tt.getTt_comments());
			pstmt.setInt(10, tt.getIsnull());
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
	public int getTeachingTeamCount(Map queryParams) {
		
		int count = 0;
		//获取条件的语句
		String sql = "select count(*) from " + TeachingTeamTable.TABLE_NAME
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
		sql += String.format(" or %s ='%s'", TeachingTeamTable.TT_COLLEGE, "");
		
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
	public List<TeachingTeam> getAllTeachingTeam(int start, int end,
			String sortStr, String orderStr, Map queryParams) {
		
		String sql = " select tmp.* from ( " + " select * from "
				+ TeachingTeamTable.TABLE_NAME + " where 1=1 ";
		
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
		
		List<TeachingTeam> tts = new ArrayList<TeachingTeam>();
		try {
			
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int tt_id = resultSet.getInt(TeachingTeamTable.TT_ID);
				Integer tt_sequencenumber = resultSet.getInt(TeachingTeamTable.TT_SEQUENCENUMBER);
				if(tt_sequencenumber==-999)
					tt_sequencenumber = null;
				String tt_collegename = resultSet.getString(TeachingTeamTable.TT_COLLEGENAME);
				String tt_name = resultSet.getString(TeachingTeamTable.TT_NAME);
				String tt_leader = resultSet.getString(TeachingTeamTable.TT_LEADER);
				Integer tt_country = resultSet.getInt(TeachingTeamTable.TT_COUNTRY);
				if(tt_country==-999)
					tt_country = null;
				Integer tt_province = resultSet.getInt(TeachingTeamTable.TT_PROVINCE);
				if(tt_province==-999)
					tt_province = null;
				int tt_serialnumber = resultSet.getInt(TeachingTeamTable.TT_SERIALNUMBER);
				Date tt_deadline = resultSet.getDate(TeachingTeamTable.TT_DEADLINE);
				String tt_comments = resultSet.getString(TeachingTeamTable.TT_COMMENTS);
				String tt_college = resultSet.getString(TeachingTeamTable.TT_COLLEGE);
				int isnull = resultSet.getInt(TeachingTeamTable.ISNULL);
				if(tt_comments == null)
					tt_comments = "";
				
				TeachingTeam tt = new TeachingTeam(tt_id, tt_sequencenumber, tt_collegename, tt_name,
						tt_leader, tt_country, tt_province,
						tt_serialnumber, tt_deadline, tt_college,
						tt_comments, isnull);
				
				tts.add(tt);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return tts;
	}

	@Override
	public int alterTeachingTeam(Map<String, String> valueMap, String id) {
		
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(TeachingTeamTable.TT_ID, id);
		int result = updateRecord(TeachingTeamTable.TABLE_NAME, valueMap,
				condition);
		return result;
	}

	@Override
	public boolean batchDelete(String[] ttids) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();

		for (String ttid : ttids) {
			String sql = "delete from " + TeachingTeamTable.TABLE_NAME
					+ " where " + TeachingTeamTable.TT_ID + " = '" + ttid + "'";
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
	public List<TeachingTeam> getTeachingTeam(int start, int end,
			String sortStr, String orderStr, Map<String, String> params,
			Date deadline) {
		String sql = " select tmp.* from ( " + " select * from "
				+ TeachingTeamTable.TABLE_NAME + " where 1=1 ";
		if (deadline != null) {

			sql += String.format("and "+TeachingTeamTable.TT_DEADLINE+" like  '%s%%' ", deadline);
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

		List<TeachingTeam> teachingTeams = new ArrayList<TeachingTeam>();
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
				int tt_id = resultSet.getInt(TeachingTeamTable.TT_ID);
				Integer tt_sequencenumber = resultSet.getInt(TeachingTeamTable.TT_SEQUENCENUMBER);
				if(tt_sequencenumber==-999)
					tt_sequencenumber = null;
				String tt_collegename = resultSet.getString(TeachingTeamTable.TT_COLLEGENAME);
				String tt_name = resultSet.getString(TeachingTeamTable.TT_NAME);
				String tt_leader = resultSet.getString(TeachingTeamTable.TT_LEADER);
				Integer tt_country = resultSet.getInt(TeachingTeamTable.TT_COUNTRY);
				if(tt_country==-999)
					tt_country = null;
				Integer tt_province = resultSet.getInt(TeachingTeamTable.TT_PROVINCE);
				if(tt_province==-999)
					tt_province = null;
				int tt_serialnumber = resultSet.getInt(TeachingTeamTable.TT_SERIALNUMBER);
				Date tt_deadline = resultSet.getDate(TeachingTeamTable.TT_DEADLINE);
				String tt_comments = resultSet.getString(TeachingTeamTable.TT_COMMENTS);
				String tt_college = resultSet.getString(TeachingTeamTable.TT_COLLEGE);
				int isnull = resultSet.getInt(TeachingTeamTable.ISNULL);
				
				TeachingTeam tt = new TeachingTeam(tt_id, tt_sequencenumber, tt_collegename, tt_name,
						tt_leader, tt_country, tt_province,
						tt_serialnumber, tt_deadline, tt_college,
						tt_comments, isnull);

				teachingTeams.add(tt);
			}
			return teachingTeams;
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
		String sql = "delete from " + TeachingTeamTable.TABLE_NAME
				+ " where " + TeachingTeamTable.TT_COLLEGE + " = '" + college + "'" +" and tt_deadline is null ";
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
	public List<TeachingTeam> getAllTeachingTeam() {
		String sql = " select * from " + TeachingTeamTable.TABLE_NAME
				+ " where 1=1 " + " order by " + TeachingTeamTable.TT_ID;
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
			List<TeachingTeam> teachingTeamList = new ArrayList<TeachingTeam>();
			while (resultSet.next()) {
				int tt_id = resultSet.getInt(TeachingTeamTable.TT_ID);
				Integer tt_sequencenumber = resultSet.getInt(TeachingTeamTable.TT_SEQUENCENUMBER);
				if(tt_sequencenumber==-999)
					tt_sequencenumber = null;
				String tt_collegename = resultSet.getString(TeachingTeamTable.TT_COLLEGENAME);
				String tt_name = resultSet.getString(TeachingTeamTable.TT_NAME);
				String tt_leader = resultSet.getString(TeachingTeamTable.TT_LEADER);
				Integer tt_country = resultSet.getInt(TeachingTeamTable.TT_COUNTRY);
				if(tt_country==-999)
					tt_country = null;
				Integer tt_province = resultSet.getInt(TeachingTeamTable.TT_PROVINCE);
				if(tt_province==-999)
					tt_province = null;
				int tt_serialnumber = resultSet.getInt(TeachingTeamTable.TT_SERIALNUMBER);
				Date tt_deadline = resultSet.getDate(TeachingTeamTable.TT_DEADLINE);
				String tt_comments = resultSet.getString(TeachingTeamTable.TT_COMMENTS);
				String tt_college = resultSet.getString(TeachingTeamTable.TT_COLLEGE);
				int isnull = resultSet.getInt(TeachingTeamTable.ISNULL);
				
				TeachingTeam tt = new TeachingTeam(tt_id, tt_sequencenumber, tt_collegename, tt_name,
						tt_leader, tt_country, tt_province,
						tt_serialnumber, tt_deadline, tt_college,
						tt_comments, isnull);
				
				teachingTeamList.add(tt);
			}
			return teachingTeamList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
	}
}
