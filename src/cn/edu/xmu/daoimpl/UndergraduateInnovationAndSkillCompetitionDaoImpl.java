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

import cn.edu.xmu.dao.UndergraduateInnovationAndSkillCompetitionDao;
import cn.edu.xmu.entity.UndergraduateInnovationAndSkillCompetition;
import cn.edu.xmu.table.ForeignExchangeTable;
import cn.edu.xmu.table.UndergraduateInnovationAndSkillCompetitionTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;


/**
 * 附表6-2-1-2  2014-2015学年本科生参加本科生创新活动、技能竞赛情况（省部级及以上）
 * @author chunfeng 
 *
 */
public class UndergraduateInnovationAndSkillCompetitionDaoImpl extends BaseDaoImpl<UndergraduateInnovationAndSkillCompetition>
		implements UndergraduateInnovationAndSkillCompetitionDao {

	@Override
	public List<UndergraduateInnovationAndSkillCompetition> getUndergraduateInnovationAndSkillCompetitions(int start, int end,
			String sortStr, String orderStr, Map<String, String> params, Date deadline) {

		String sql = " select tmp.* from ( " + " select * from "
				+ UndergraduateInnovationAndSkillCompetitionTable.TABLE_NAME + " where 1=1 ";
		if (deadline != null) {
			sql += String.format("and "+UndergraduateInnovationAndSkillCompetitionTable.UIASC_DEADLINE+" like  '%s%%' ", deadline);
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

		List<UndergraduateInnovationAndSkillCompetition> undergraduateInnovationAndSkillCompetitions = new ArrayList<UndergraduateInnovationAndSkillCompetition>();
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
				int uiasc_id = resultSet.getInt(UndergraduateInnovationAndSkillCompetitionTable.UIASC_ID);
				String uiasc_collegename = resultSet.getString(UndergraduateInnovationAndSkillCompetitionTable.UIASC_COLLEGENAME);
				String uiasc_competitionname = resultSet.getString(UndergraduateInnovationAndSkillCompetitionTable.UIASC_COMPETITIONNAME);
				String uiasc_awardgrade = resultSet.getString(UndergraduateInnovationAndSkillCompetitionTable.UIASC_AWARDGRADE);
				String uiasc_prizelevel  =resultSet.getString(UndergraduateInnovationAndSkillCompetitionTable.UIASC_PRIZELEVEL);
				String uiasc_personalorteam = resultSet.getString(UndergraduateInnovationAndSkillCompetitionTable.UIASC_PERSONALORTEAM);
				String uiasc_studentname  = resultSet.getString(UndergraduateInnovationAndSkillCompetitionTable.UIASC_STUDENTNAME);
				Integer uiasc_studentnum = resultSet.getInt(UndergraduateInnovationAndSkillCompetitionTable.UIASC_STUDENTNUM);
				if(uiasc_studentnum == -1) uiasc_studentnum = null;
				String uiasc_windate = resultSet.getString(UndergraduateInnovationAndSkillCompetitionTable.UIASC_WINDATE);  
			
				String uiasc_college = resultSet.getString(UndergraduateInnovationAndSkillCompetitionTable.UIASC_COLLEGE);
				int uiasc_serialnumber = resultSet.getInt(UndergraduateInnovationAndSkillCompetitionTable.UIASC_SERIALNUMBER);
				Date uiasc_deadline = resultSet.getDate(UndergraduateInnovationAndSkillCompetitionTable.UIASC_DEADLINE); 
				String uiasc_comments =  resultSet.getString(UndergraduateInnovationAndSkillCompetitionTable.UIASC_COMMENTS);
				if(null == uiasc_comments){
					uiasc_comments = "";
				}
				int uiasc_isnull = resultSet.getInt(UndergraduateInnovationAndSkillCompetitionTable.UIASC_ISNULL);
				
				UndergraduateInnovationAndSkillCompetition undergraduateInnovationAndSkillCompetition = new UndergraduateInnovationAndSkillCompetition(
						uiasc_id, uiasc_collegename, uiasc_competitionname, uiasc_awardgrade, uiasc_prizelevel, uiasc_personalorteam,
					    uiasc_studentname, uiasc_studentnum, uiasc_windate,uiasc_college,  uiasc_deadline, uiasc_serialnumber, uiasc_comments, uiasc_isnull);

				undergraduateInnovationAndSkillCompetitions.add(undergraduateInnovationAndSkillCompetition);
			}
			return undergraduateInnovationAndSkillCompetitions;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}

	}

	@Override
	public int getUndergraduateInnovationAndSkillCompetitionCount(Map queryParams) {
		int count = 0;
		String sql = "select count(*) from " + UndergraduateInnovationAndSkillCompetitionTable.TABLE_NAME
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
	public boolean batchDelete(String[] uiascids) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();

		for (String uiascid : uiascids) {
			String sql = "delete from " + UndergraduateInnovationAndSkillCompetitionTable.TABLE_NAME
					+ " where " + UndergraduateInnovationAndSkillCompetitionTable.UIASC_ID + " = '" + uiascid
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
	public int addUndergraduateInnovationAndSkillCompetition(UndergraduateInnovationAndSkillCompetition UndergraduateInnovationAndSkillCompetition) {
		int result = 0;

		String sql2 = "update " + UndergraduateInnovationAndSkillCompetitionTable.TABLE_NAME + " set "
				+ UndergraduateInnovationAndSkillCompetitionTable.UIASC_SERIALNUMBER + " = "
				+ UndergraduateInnovationAndSkillCompetitionTable.UIASC_SERIALNUMBER + " +1 where "
				+ UndergraduateInnovationAndSkillCompetitionTable.UIASC_SERIALNUMBER + ">="
				+ UndergraduateInnovationAndSkillCompetition.getUiasc_serialnumber();
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

		String sql = "insert into " + UndergraduateInnovationAndSkillCompetitionTable.TABLE_NAME + "("
				+ UndergraduateInnovationAndSkillCompetitionTable.UIASC_COLLEGENAME + ","
				+ UndergraduateInnovationAndSkillCompetitionTable.UIASC_COMPETITIONNAME + ","
				+ UndergraduateInnovationAndSkillCompetitionTable.UIASC_AWARDGRADE + ","
				+ UndergraduateInnovationAndSkillCompetitionTable.UIASC_PRIZELEVEL + ","
				+ UndergraduateInnovationAndSkillCompetitionTable.UIASC_PERSONALORTEAM + ","
				+ UndergraduateInnovationAndSkillCompetitionTable.UIASC_STUDENTNAME + ","
				+ UndergraduateInnovationAndSkillCompetitionTable.UIASC_STUDENTNUM + ","
				+ UndergraduateInnovationAndSkillCompetitionTable.UIASC_WINDATE + ","  
				+ UndergraduateInnovationAndSkillCompetitionTable.UIASC_COLLEGE + ","
				+ UndergraduateInnovationAndSkillCompetitionTable.UIASC_SERIALNUMBER + ","  
				+ UndergraduateInnovationAndSkillCompetitionTable.UIASC_ISNULL + ")values(?,?,?,?,?,?,?,?,?,?,?)";

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
			pstmt.setString(1, UndergraduateInnovationAndSkillCompetition.getUiasc_collegename());
			pstmt.setString(2, UndergraduateInnovationAndSkillCompetition.getUiasc_competitionname());
			pstmt.setString(3, UndergraduateInnovationAndSkillCompetition.getUiasc_awardgrade());
			pstmt.setString(4, UndergraduateInnovationAndSkillCompetition.getUiasc_prizelevel());
			pstmt.setString(5, UndergraduateInnovationAndSkillCompetition.getUiasc_personalorteam());
			pstmt.setString(6, UndergraduateInnovationAndSkillCompetition.getUiasc_studentname());
			pstmt.setInt(7, UndergraduateInnovationAndSkillCompetition.getUiasc_studentnum());
			pstmt.setString(8, UndergraduateInnovationAndSkillCompetition.getUiasc_windate());
			pstmt.setString(9, UndergraduateInnovationAndSkillCompetition.getUiasc_college());
			pstmt.setInt(10, UndergraduateInnovationAndSkillCompetition.getUiasc_serialnumber());
			pstmt.setInt(11, UndergraduateInnovationAndSkillCompetition.getUiasc_isnull());
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
	public int alterUndergraduateInnovationAndSkillCompetition(Map<String, String> valueMap, String id) {
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(UndergraduateInnovationAndSkillCompetitionTable.UIASC_ID, id);
		int result = updateRecord(UndergraduateInnovationAndSkillCompetitionTable.TABLE_NAME, valueMap,
				condition);
		return result;
	}

	@Override
	public List<UndergraduateInnovationAndSkillCompetition> getAllUndergraduateInnovationAndSkillCompetitions() {
		String sql = " select * from " + UndergraduateInnovationAndSkillCompetitionTable.TABLE_NAME
				+ " where 1=1 " + " order by " + UndergraduateInnovationAndSkillCompetitionTable.UIASC_ID;
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
			List<UndergraduateInnovationAndSkillCompetition> undergraduateInnovationAndSkillCompetitionList = new ArrayList<UndergraduateInnovationAndSkillCompetition>();
			while (resultSet.next()) {
				int uiasc_id = resultSet.getInt(UndergraduateInnovationAndSkillCompetitionTable.UIASC_ID);
				String uiasc_collegename = resultSet.getString(UndergraduateInnovationAndSkillCompetitionTable.UIASC_COLLEGENAME);
				String uiasc_competitionname = resultSet.getString(UndergraduateInnovationAndSkillCompetitionTable.UIASC_COMPETITIONNAME);
				String uiasc_awardgrade = resultSet.getString(UndergraduateInnovationAndSkillCompetitionTable.UIASC_AWARDGRADE);
				String uiasc_prizelevel  =resultSet.getString(UndergraduateInnovationAndSkillCompetitionTable.UIASC_PRIZELEVEL);
				String uiasc_personalorteam = resultSet.getString(UndergraduateInnovationAndSkillCompetitionTable.UIASC_PERSONALORTEAM);
				String uiasc_studentname  = resultSet.getString(UndergraduateInnovationAndSkillCompetitionTable.UIASC_STUDENTNAME);
				Integer uiasc_studentnum = resultSet.getInt(UndergraduateInnovationAndSkillCompetitionTable.UIASC_STUDENTNUM);
				if(uiasc_studentnum == -1) uiasc_studentnum = null;
				String uiasc_windate = resultSet.getString(UndergraduateInnovationAndSkillCompetitionTable.UIASC_WINDATE);  
			
				String uiasc_college = resultSet.getString(UndergraduateInnovationAndSkillCompetitionTable.UIASC_COLLEGE);
				int uiasc_serialnumber = resultSet.getInt(UndergraduateInnovationAndSkillCompetitionTable.UIASC_SERIALNUMBER);
				Date uiasc_deadline = resultSet.getDate(UndergraduateInnovationAndSkillCompetitionTable.UIASC_DEADLINE); 
				String uiasc_comments =  resultSet.getString(UndergraduateInnovationAndSkillCompetitionTable.UIASC_COMMENTS);
				if(null == uiasc_comments){
					uiasc_comments = "";
				}
				int uiasc_isnull = resultSet.getInt(UndergraduateInnovationAndSkillCompetitionTable.UIASC_ISNULL);
				
				UndergraduateInnovationAndSkillCompetition undergraduateInnovationAndSkillCompetition = new UndergraduateInnovationAndSkillCompetition(
						uiasc_id, uiasc_collegename, uiasc_competitionname, uiasc_awardgrade, uiasc_prizelevel, uiasc_personalorteam,
					    uiasc_studentname, uiasc_studentnum, uiasc_windate,uiasc_college,  uiasc_deadline, uiasc_serialnumber, uiasc_comments, uiasc_isnull);

				undergraduateInnovationAndSkillCompetitionList.add(undergraduateInnovationAndSkillCompetition);
			}
			return undergraduateInnovationAndSkillCompetitionList;
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

		String sql = "delete from " + UndergraduateInnovationAndSkillCompetitionTable.TABLE_NAME;
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
		String sql = "delete from " + UndergraduateInnovationAndSkillCompetitionTable.TABLE_NAME
				+ " where " + UndergraduateInnovationAndSkillCompetitionTable.UIASC_COLLEGE + " = '" + college + "'" +" and uiasc_deadline is null ";
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
