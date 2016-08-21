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

import cn.edu.xmu.dao.UndergraduateAcademicCompetitionDao;
import cn.edu.xmu.entity.UndergraduateAcademicCompetition;
import cn.edu.xmu.table.ForeignExchangeTable;
import cn.edu.xmu.table.UndergraduateAcademicCompetitionTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;


/**
 * 附表6-2-1-1   2014-2015学年本科生参加学科竞赛情况（省部级及以上）
 * @author chunfeng 
 *
 */
public class UndergraduateAcademicCompetitionDaoImpl extends BaseDaoImpl<UndergraduateAcademicCompetition>
		implements UndergraduateAcademicCompetitionDao {

	@Override
	public List<UndergraduateAcademicCompetition> getUndergraduateAcademicCompetitions(int start, int end,
			String sortStr, String orderStr, Map<String, String> params, Date deadline) {

		String sql = " select tmp.* from ( " + " select * from "
				+ UndergraduateAcademicCompetitionTable.TABLE_NAME + " where 1=1 ";
		if (deadline != null) {
			sql += String.format("and "+UndergraduateAcademicCompetitionTable.UAC_DEADLINE+" like  '%s%%' ", deadline);
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

		List<UndergraduateAcademicCompetition> undergraduateAcademicCompetitions = new ArrayList<UndergraduateAcademicCompetition>();
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
				int uac_id = resultSet.getInt(UndergraduateAcademicCompetitionTable.UAC_ID);
				String uac_collegename = resultSet.getString(UndergraduateAcademicCompetitionTable.UAC_COLLEGENAME);
				String uac_competitionname = resultSet.getString(UndergraduateAcademicCompetitionTable.UAC_COMPETITIONNAME);
				String uac_awardgrade = resultSet.getString(UndergraduateAcademicCompetitionTable.UAC_AWARDGRADE);
				String uac_prizelevel  =resultSet.getString(UndergraduateAcademicCompetitionTable.UAC_PRIZELEVEL);
				String uac_personalorteam = resultSet.getString(UndergraduateAcademicCompetitionTable.UAC_PERSONALORTEAM);
				String uac_studentname  = resultSet.getString(UndergraduateAcademicCompetitionTable.UAC_STUDENTNAME);
				Integer uac_studentnum = resultSet.getInt(UndergraduateAcademicCompetitionTable.UAC_STUDENTNUM);
				if(uac_studentnum == -1) uac_studentnum = null;
				String uac_windate = resultSet.getString(UndergraduateAcademicCompetitionTable.UAC_WINDATE);  
			
				String uac_college = resultSet.getString(UndergraduateAcademicCompetitionTable.UAC_COLLEGE);
				int uac_serialnumber = resultSet.getInt(UndergraduateAcademicCompetitionTable.UAC_SERIALNUMBER);
				Date uac_deadline = resultSet.getDate(UndergraduateAcademicCompetitionTable.UAC_DEADLINE); 
				String uac_comments =  resultSet.getString(UndergraduateAcademicCompetitionTable.UAC_COMMENTS);
				if(null == uac_comments){
					uac_comments = "";
				}
				int uac_isnull = resultSet.getInt(UndergraduateAcademicCompetitionTable.UAC_ISNULL);
				
				UndergraduateAcademicCompetition undergraduateAcademicCompetition = new UndergraduateAcademicCompetition(
						uac_id, uac_collegename, uac_competitionname, uac_awardgrade, uac_prizelevel, uac_personalorteam,
					    uac_studentname, uac_studentnum, uac_windate, uac_college, uac_deadline, uac_serialnumber, uac_comments, uac_isnull);

				undergraduateAcademicCompetitions.add(undergraduateAcademicCompetition);
			}
			return undergraduateAcademicCompetitions;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}

	}

	@Override
	public int getUndergraduateAcademicCompetitionCount(Map queryParams) {
		int count = 0;
		String sql = "select count(*) from " + UndergraduateAcademicCompetitionTable.TABLE_NAME
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
	public boolean batchDelete(String[] uacids) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();

		for (String uacid : uacids) {
			String sql = "delete from " + UndergraduateAcademicCompetitionTable.TABLE_NAME
					+ " where " + UndergraduateAcademicCompetitionTable.UAC_ID + " = '" + uacid
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
	public int addUndergraduateAcademicCompetition(UndergraduateAcademicCompetition UndergraduateAcademicCompetition) {
		int result = 0;

		String sql2 = "update " + UndergraduateAcademicCompetitionTable.TABLE_NAME + " set "
				+ UndergraduateAcademicCompetitionTable.UAC_SERIALNUMBER + " = "
				+ UndergraduateAcademicCompetitionTable.UAC_SERIALNUMBER + " +1 where "
				+ UndergraduateAcademicCompetitionTable.UAC_SERIALNUMBER + ">="
				+ UndergraduateAcademicCompetition.getUac_serialnumber();
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

		String sql = "insert into " + UndergraduateAcademicCompetitionTable.TABLE_NAME + "("
				+ UndergraduateAcademicCompetitionTable.UAC_COLLEGENAME + ","
				+ UndergraduateAcademicCompetitionTable.UAC_COMPETITIONNAME + ","
				+ UndergraduateAcademicCompetitionTable.UAC_AWARDGRADE + ","
				+ UndergraduateAcademicCompetitionTable.UAC_PRIZELEVEL + ","
				+ UndergraduateAcademicCompetitionTable.UAC_PERSONALORTEAM + ","
				+ UndergraduateAcademicCompetitionTable.UAC_STUDENTNAME + ","
				+ UndergraduateAcademicCompetitionTable.UAC_STUDENTNUM + ","
				+ UndergraduateAcademicCompetitionTable.UAC_WINDATE + ","  
				+ UndergraduateAcademicCompetitionTable.UAC_COLLEGE + ","
				+ UndergraduateAcademicCompetitionTable.UAC_SERIALNUMBER + ","  
				+ UndergraduateAcademicCompetitionTable.UAC_ISNULL + ")values(?,?,?,?,?,?,?,?,?,?,?)";

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
			pstmt.setString(1, UndergraduateAcademicCompetition.getUac_collegename());
			pstmt.setString(2, UndergraduateAcademicCompetition.getUac_competitionname());
			pstmt.setString(3, UndergraduateAcademicCompetition.getUac_awardgrade());
			pstmt.setString(4, UndergraduateAcademicCompetition.getUac_prizelevel());
			pstmt.setString(5, UndergraduateAcademicCompetition.getUac_personalorteam());
			pstmt.setString(6, UndergraduateAcademicCompetition.getUac_studentname());
			pstmt.setInt(7, UndergraduateAcademicCompetition.getUac_studentnum());
			pstmt.setString(8, UndergraduateAcademicCompetition.getUac_windate());
			pstmt.setString(9, UndergraduateAcademicCompetition.getUac_college());
			pstmt.setInt(10, UndergraduateAcademicCompetition.getUac_serialnumber());
			pstmt.setInt(11, UndergraduateAcademicCompetition.getUac_isnull());
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
	public int alterUndergraduateAcademicCompetition(Map<String, String> valueMap, String id) {
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(UndergraduateAcademicCompetitionTable.UAC_ID, id);
		int result = updateRecord(UndergraduateAcademicCompetitionTable.TABLE_NAME, valueMap,
				condition);
		return result;
	}

	@Override
	public List<UndergraduateAcademicCompetition> getAllUndergraduateAcademicCompetitions() {
		String sql = " select * from " + UndergraduateAcademicCompetitionTable.TABLE_NAME
				+ " where 1=1 " + " order by " + UndergraduateAcademicCompetitionTable.UAC_ID;
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
			List<UndergraduateAcademicCompetition> undergraduateAcademicCompetitionList = new ArrayList<UndergraduateAcademicCompetition>();
			while (resultSet.next()) {
				int uac_id = resultSet.getInt(UndergraduateAcademicCompetitionTable.UAC_ID);
				String uac_collegename = resultSet.getString(UndergraduateAcademicCompetitionTable.UAC_COLLEGENAME);
				String uac_competitionname = resultSet.getString(UndergraduateAcademicCompetitionTable.UAC_COMPETITIONNAME);
				String uac_awardgrade = resultSet.getString(UndergraduateAcademicCompetitionTable.UAC_AWARDGRADE);
				String uac_prizelevel  =resultSet.getString(UndergraduateAcademicCompetitionTable.UAC_PRIZELEVEL);
				String uac_personalorteam = resultSet.getString(UndergraduateAcademicCompetitionTable.UAC_PERSONALORTEAM);
				String uac_studentname  = resultSet.getString(UndergraduateAcademicCompetitionTable.UAC_STUDENTNAME);
				Integer uac_studentnum = resultSet.getInt(UndergraduateAcademicCompetitionTable.UAC_STUDENTNUM);
				if(uac_studentnum == -1) uac_studentnum = null;
				String uac_windate = resultSet.getString(UndergraduateAcademicCompetitionTable.UAC_WINDATE);  
			
				String uac_college = resultSet.getString(UndergraduateAcademicCompetitionTable.UAC_COLLEGE);
				int uac_serialnumber = resultSet.getInt(UndergraduateAcademicCompetitionTable.UAC_SERIALNUMBER);
				Date uac_deadline = resultSet.getDate(UndergraduateAcademicCompetitionTable.UAC_DEADLINE); 
				String uac_comments =  resultSet.getString(UndergraduateAcademicCompetitionTable.UAC_COMMENTS);
				if(null == uac_comments){
					uac_comments = "";
				}
				int uac_isnull = resultSet.getInt(UndergraduateAcademicCompetitionTable.UAC_ISNULL);
				
				UndergraduateAcademicCompetition undergraduateAcademicCompetition = new UndergraduateAcademicCompetition(
						uac_id, uac_collegename, uac_competitionname, uac_awardgrade, uac_prizelevel, uac_personalorteam,
					    uac_studentname, uac_studentnum, uac_windate, uac_college, uac_deadline, uac_serialnumber, uac_comments, uac_isnull);

				undergraduateAcademicCompetitionList.add(undergraduateAcademicCompetition);
			}
			return undergraduateAcademicCompetitionList;
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

		String sql = "delete from " + UndergraduateAcademicCompetitionTable.TABLE_NAME;
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
		String sql = "delete from " + UndergraduateAcademicCompetitionTable.TABLE_NAME
				+ " where " + UndergraduateAcademicCompetitionTable.UAC_COLLEGE + " = '" + college + "'" +" and uac_deadline is null ";
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
