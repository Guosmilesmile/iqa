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

import cn.edu.xmu.dao.UndergraduateArtAndSportCompetitionDao;
import cn.edu.xmu.entity.UndergraduateArtAndSportCompetition;
import cn.edu.xmu.table.ForeignExchangeTable;
import cn.edu.xmu.table.UndergraduateArtAndSportCompetitionTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;


/**
 * 附表6-2-1-3   2014-2015学年本科生参加文艺、体育竞赛情况（省部级及以上）
 * @author chunfeng 
 *
 */
public class UndergraduateArtAndSportCompetitionDaoImpl extends BaseDaoImpl<UndergraduateArtAndSportCompetition>
		implements UndergraduateArtAndSportCompetitionDao {

	@Override
	public List<UndergraduateArtAndSportCompetition> getUndergraduateArtAndSportCompetitions(int start, int end,
			String sortStr, String orderStr, Map<String, String> params, Date deadline) {

		String sql = " select tmp.* from ( " + " select * from "
				+ UndergraduateArtAndSportCompetitionTable.TABLE_NAME + " where 1=1 ";
		if (deadline != null) {
			sql += String.format("and "+UndergraduateArtAndSportCompetitionTable.UAASC_DEADLINE+" like  '%s%%' ", deadline);
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

		List<UndergraduateArtAndSportCompetition> undergraduateArtAndSportCompetitions = new ArrayList<UndergraduateArtAndSportCompetition>();
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
				int uaasc_id = resultSet.getInt(UndergraduateArtAndSportCompetitionTable.UAASC_ID);
				String uaasc_collegename = resultSet.getString(UndergraduateArtAndSportCompetitionTable.UAASC_COLLEGENAME);
				String uaasc_competitionname = resultSet.getString(UndergraduateArtAndSportCompetitionTable.UAASC_COMPETITIONNAME);
				String uaasc_competitioncategory = resultSet.getString(UndergraduateArtAndSportCompetitionTable.UAASC_COMPETITIONCATEGORY);
				String uaasc_awardgrade = resultSet.getString(UndergraduateArtAndSportCompetitionTable.UAASC_AWARDGRADE);
				String uaasc_prizelevel  =resultSet.getString(UndergraduateArtAndSportCompetitionTable.UAASC_PRIZELEVEL);
				String uaasc_personalorteam = resultSet.getString(UndergraduateArtAndSportCompetitionTable.UAASC_PERSONALORTEAM);
				String uaasc_studentname  = resultSet.getString(UndergraduateArtAndSportCompetitionTable.UAASC_STUDENTNAME);
				Integer uaasc_studentnum = resultSet.getInt(UndergraduateArtAndSportCompetitionTable.UAASC_STUDENTNUM);
				if(uaasc_studentnum == -1) uaasc_studentnum = null;
				String uaasc_windate = resultSet.getString(UndergraduateArtAndSportCompetitionTable.UAASC_WINDATE);  
			
				String uaasc_college = resultSet.getString(UndergraduateArtAndSportCompetitionTable.UAASC_COLLEGE);
				int uaasc_serialnumber = resultSet.getInt(UndergraduateArtAndSportCompetitionTable.UAASC_SERIALNUMBER);
				Date uaasc_deadline = resultSet.getDate(UndergraduateArtAndSportCompetitionTable.UAASC_DEADLINE); 
				String uaasc_comments =  resultSet.getString(UndergraduateArtAndSportCompetitionTable.UAASC_COMMENTS);
				if(null == uaasc_comments){
					uaasc_comments = "";
				}
				int uaasc_isnull = resultSet.getInt(UndergraduateArtAndSportCompetitionTable.UAASC_ISNULL);
				
				UndergraduateArtAndSportCompetition undergraduateArtAndSportCompetition = new UndergraduateArtAndSportCompetition(
						uaasc_id, uaasc_collegename, uaasc_competitionname, uaasc_competitioncategory, uaasc_awardgrade, uaasc_prizelevel, uaasc_personalorteam,
					    uaasc_studentname, uaasc_studentnum, uaasc_windate, uaasc_college, uaasc_deadline, uaasc_serialnumber, uaasc_comments, uaasc_isnull);

				undergraduateArtAndSportCompetitions.add(undergraduateArtAndSportCompetition);
			}
			return undergraduateArtAndSportCompetitions;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}

	}

	@Override
	public int getUndergraduateArtAndSportCompetitionCount(Map queryParams) {
		int count = 0;
		String sql = "select count(*) from " + UndergraduateArtAndSportCompetitionTable.TABLE_NAME
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
	public boolean batchDelete(String[] uaascids) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();

		for (String uaascid : uaascids) {
			String sql = "delete from " + UndergraduateArtAndSportCompetitionTable.TABLE_NAME
					+ " where " + UndergraduateArtAndSportCompetitionTable.UAASC_ID + " = '" + uaascid
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
	public int addUndergraduateArtAndSportCompetition(UndergraduateArtAndSportCompetition UndergraduateArtAndSportCompetition) {
		int result = 0;

		String sql2 = "update " + UndergraduateArtAndSportCompetitionTable.TABLE_NAME + " set "
				+ UndergraduateArtAndSportCompetitionTable.UAASC_SERIALNUMBER + " = "
				+ UndergraduateArtAndSportCompetitionTable.UAASC_SERIALNUMBER + " +1 where "
				+ UndergraduateArtAndSportCompetitionTable.UAASC_SERIALNUMBER + ">="
				+ UndergraduateArtAndSportCompetition.getUaasc_serialnumber();
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
			try {
				JdbcUtils_DBCP.release(connection2, pstmt2, null);
			} catch (Exception e2) {
				return 0;
			}
		}

		String sql = "insert into " + UndergraduateArtAndSportCompetitionTable.TABLE_NAME + "("
				+ UndergraduateArtAndSportCompetitionTable.UAASC_COLLEGENAME + ","
				+ UndergraduateArtAndSportCompetitionTable.UAASC_COMPETITIONNAME + ","
				+ UndergraduateArtAndSportCompetitionTable.UAASC_COMPETITIONCATEGORY + ","
				+ UndergraduateArtAndSportCompetitionTable.UAASC_AWARDGRADE + ","
				+ UndergraduateArtAndSportCompetitionTable.UAASC_PRIZELEVEL + ","
				+ UndergraduateArtAndSportCompetitionTable.UAASC_PERSONALORTEAM + ","
				+ UndergraduateArtAndSportCompetitionTable.UAASC_STUDENTNAME + ","
				+ UndergraduateArtAndSportCompetitionTable.UAASC_STUDENTNUM + ","
				+ UndergraduateArtAndSportCompetitionTable.UAASC_WINDATE + ","  
				+ UndergraduateArtAndSportCompetitionTable.UAASC_COLLEGE + ","
				+ UndergraduateArtAndSportCompetitionTable.UAASC_SERIALNUMBER + ","  
				+ UndergraduateArtAndSportCompetitionTable.UAASC_ISNULL + ")values(?,?,?,?,?,?,?,?,?,?,?,?)";

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
			pstmt.setString(1, UndergraduateArtAndSportCompetition.getUaasc_collegename());
			pstmt.setString(2, UndergraduateArtAndSportCompetition.getUaasc_competitionname());
			pstmt.setString(3, UndergraduateArtAndSportCompetition.getUaasc_competitioncategory());
			pstmt.setString(4, UndergraduateArtAndSportCompetition.getUaasc_awardgrade());
			pstmt.setString(5, UndergraduateArtAndSportCompetition.getUaasc_prizelevel());
			pstmt.setString(6, UndergraduateArtAndSportCompetition.getUaasc_personalorteam());
			pstmt.setString(7, UndergraduateArtAndSportCompetition.getUaasc_studentname());
			pstmt.setInt(8, UndergraduateArtAndSportCompetition.getUaasc_studentnum());
			pstmt.setString(9, UndergraduateArtAndSportCompetition.getUaasc_windate());
			pstmt.setString(10, UndergraduateArtAndSportCompetition.getUaasc_college());
			pstmt.setInt(11, UndergraduateArtAndSportCompetition.getUaasc_serialnumber());
			pstmt.setInt(12, UndergraduateArtAndSportCompetition.getUaasc_isnull());
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
	public int alterUndergraduateArtAndSportCompetition(Map<String, String> valueMap, String id) {
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(UndergraduateArtAndSportCompetitionTable.UAASC_ID, id);
		int result = updateRecord(UndergraduateArtAndSportCompetitionTable.TABLE_NAME, valueMap,
				condition);
		return result;
	}

	@Override
	public List<UndergraduateArtAndSportCompetition> getAllUndergraduateArtAndSportCompetitions() {
		String sql = " select * from " + UndergraduateArtAndSportCompetitionTable.TABLE_NAME
				+ " where 1=1 " + " order by " + UndergraduateArtAndSportCompetitionTable.UAASC_ID;
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
			List<UndergraduateArtAndSportCompetition> undergraduateArtAndSportCompetitionList = new ArrayList<UndergraduateArtAndSportCompetition>();
			while (resultSet.next()) {
				int uaasc_id = resultSet.getInt(UndergraduateArtAndSportCompetitionTable.UAASC_ID);
				String uaasc_collegename = resultSet.getString(UndergraduateArtAndSportCompetitionTable.UAASC_COLLEGENAME);
				String uaasc_competitionname = resultSet.getString(UndergraduateArtAndSportCompetitionTable.UAASC_COMPETITIONNAME);
				String uaasc_competitioncategory = resultSet.getString(UndergraduateArtAndSportCompetitionTable.UAASC_COMPETITIONCATEGORY);
				String uaasc_awardgrade = resultSet.getString(UndergraduateArtAndSportCompetitionTable.UAASC_AWARDGRADE);
				String uaasc_prizelevel  =resultSet.getString(UndergraduateArtAndSportCompetitionTable.UAASC_PRIZELEVEL);
				String uaasc_personalorteam = resultSet.getString(UndergraduateArtAndSportCompetitionTable.UAASC_PERSONALORTEAM);
				String uaasc_studentname  = resultSet.getString(UndergraduateArtAndSportCompetitionTable.UAASC_STUDENTNAME);
				Integer uaasc_studentnum = resultSet.getInt(UndergraduateArtAndSportCompetitionTable.UAASC_STUDENTNUM);
				if(uaasc_studentnum == -1) uaasc_studentnum = null;
				String uaasc_windate = resultSet.getString(UndergraduateArtAndSportCompetitionTable.UAASC_WINDATE);  
			
				String uaasc_college = resultSet.getString(UndergraduateArtAndSportCompetitionTable.UAASC_COLLEGE);
				int uaasc_serialnumber = resultSet.getInt(UndergraduateArtAndSportCompetitionTable.UAASC_SERIALNUMBER);
				Date uaasc_deadline = resultSet.getDate(UndergraduateArtAndSportCompetitionTable.UAASC_DEADLINE); 
				String uaasc_comments =  resultSet.getString(UndergraduateArtAndSportCompetitionTable.UAASC_COMMENTS);
				if(null == uaasc_comments){
					uaasc_comments = "";
				}
				int uaasc_isnull = resultSet.getInt(UndergraduateArtAndSportCompetitionTable.UAASC_ISNULL);
				
				UndergraduateArtAndSportCompetition undergraduateArtAndSportCompetition = new UndergraduateArtAndSportCompetition(
						uaasc_id, uaasc_collegename, uaasc_competitionname, uaasc_competitioncategory, uaasc_awardgrade, uaasc_prizelevel, uaasc_personalorteam,
					    uaasc_studentname, uaasc_studentnum, uaasc_windate, uaasc_college, uaasc_deadline, uaasc_serialnumber, uaasc_comments, uaasc_isnull);

				undergraduateArtAndSportCompetitionList.add(undergraduateArtAndSportCompetition);
			}
			return undergraduateArtAndSportCompetitionList;
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

		String sql = "delete from " + UndergraduateArtAndSportCompetitionTable.TABLE_NAME;
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils_DBCP.release(connection, stmt, null);
		}

	}


	@Override
	public void deleteByCollegeandDeadline(String college, Date deadline) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();
		String sql = "delete from " + UndergraduateArtAndSportCompetitionTable.TABLE_NAME
				+ " where " + UndergraduateArtAndSportCompetitionTable.UAASC_COLLEGE + " = '" + college + "'" +" and uaasc_deadline is null ";
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
