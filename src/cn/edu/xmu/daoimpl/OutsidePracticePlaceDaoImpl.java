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

import cn.edu.xmu.dao.OutsidePracticePlaceDao;
import cn.edu.xmu.entity.OutsidePracticePlace;
import cn.edu.xmu.table.ForeignExchangeTable;
import cn.edu.xmu.table.OutsidePracticePlaceTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;


/**
 * 2-6-1 本科实验实习实训场所
 * @author chunfeng
 *
 */
public class OutsidePracticePlaceDaoImpl extends BaseDaoImpl<OutsidePracticePlace> implements OutsidePracticePlaceDao {

	@Override
	public List<OutsidePracticePlace> getOutsidePracticePlace(int start, int end,
			String sortStr, String orderStr, Map<String, String> params, Date deadline) {
		
		String sql = " select tmp.* from ( " + " select * from "
				+ OutsidePracticePlaceTable.TABLE_NAME + " where 1=1 ";
		if (deadline != null) {
			sql += String.format("and "+OutsidePracticePlaceTable.OPP_DEADLINE+" like  '%s%%' ", deadline);
		}
		if (!(params == null || params.keySet().size() == 0)) {
			for (Object object : params.keySet()) {

				String key = object.toString();
				String value = params.get(key).toString();
				sql += String.format("and %s like  '%%%s%%' ", key, value);
			}
		}
		
		if (sortStr == "nope") {
			
		}else {
			sql += " order by " + sortStr + " " + orderStr + " ) tmp limit "
					+ start + " ," + end;
		}



		System.out.println(sql);

		List<OutsidePracticePlace> outsidePracticePlaces = new ArrayList<OutsidePracticePlace>();
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
				int opp_id = resultSet.getInt(OutsidePracticePlaceTable.OPP_ID);
 
				String opp_placename = resultSet.getString(OutsidePracticePlaceTable.OPP_PLACENAME);   
				String opp_collegename  = resultSet.getString(OutsidePracticePlaceTable.OPP_COLLEGENAME); 
				String opp_address = resultSet.getString(OutsidePracticePlaceTable.OPP_ADDRESS); 
			 	String opp_majors = resultSet.getString(OutsidePracticePlaceTable.OPP_MAJORS);  
				Integer opp_studentspertime = resultSet.getInt(OutsidePracticePlaceTable.OPP_STUDENTSPERTIME); 
				if(opp_studentspertime == -1) opp_studentspertime = null;
				Integer opp_studentsthisyear = resultSet.getInt(OutsidePracticePlaceTable.OPP_STUDENTSTHISYEAR); 
				if(opp_studentsthisyear == -1) opp_studentsthisyear = null;
				String opp_month = resultSet.getString(OutsidePracticePlaceTable.OPP_MONTH); 
				String opp_cooperator = resultSet.getString(OutsidePracticePlaceTable.OPP_COOPERATOR);  
				String opp_setupdate = resultSet.getString(OutsidePracticePlaceTable.OPP_SETUPDATE); 
				String opp_level = resultSet.getString(OutsidePracticePlaceTable.OPP_LEVEL);
				Integer opp_accumulatedstudents = resultSet.getInt(OutsidePracticePlaceTable.OPP_ACCUMULATEDSTUDENTS);
				if(opp_accumulatedstudents == -1) opp_accumulatedstudents = null;
						
				String opp_college  = resultSet.getString(OutsidePracticePlaceTable.OPP_COLLEGE); 
				int opp_serialnumber = resultSet.getInt(OutsidePracticePlaceTable.OPP_SERIALNUMBER);
				Date opp_deadline = resultSet.getDate(OutsidePracticePlaceTable.OPP_DEADLINE); 
				String opp_comments =  resultSet.getString(OutsidePracticePlaceTable.OPP_COMMENTS);
				if(null == opp_comments){
					opp_comments = "";
				}
				int opp_isnull = resultSet.getInt(OutsidePracticePlaceTable.OPP_ISNULL);
				OutsidePracticePlace outsidePracticePlace = new OutsidePracticePlace(opp_id, opp_placename, opp_collegename, opp_address, opp_majors, 
			            opp_studentspertime, opp_studentsthisyear, opp_month, opp_cooperator, opp_setupdate, opp_level, 
			            opp_accumulatedstudents,opp_college, opp_deadline, opp_serialnumber, opp_comments, opp_isnull);

				outsidePracticePlaces.add(outsidePracticePlace);
			}
			return outsidePracticePlaces;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
	}

	@Override
	public int getOutsidePracticePlaceCount(Map queryParams) {
		// TODO Auto-generated method stub
		int count = 0;
		String sql = "select count(*) from " + OutsidePracticePlaceTable.TABLE_NAME
				+ " where 1 = 1 ";
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
		} finally{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}

		System.out.println(count);
		return count;
	}

	@Override
	public boolean batchDelete(String[] oppids) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();

		for (String oppid : oppids) {
			String sql = "delete from " + OutsidePracticePlaceTable.TABLE_NAME
					+ " where " + OutsidePracticePlaceTable.OPP_ID + " = '" + oppid
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
	public int addOutsidePracticePlace(OutsidePracticePlace OutsidePracticePlace) {
		// TODO Auto-generated method stub
		int result = 0;

		String sql2 = "update " + OutsidePracticePlaceTable.TABLE_NAME + " set "
				+ OutsidePracticePlaceTable.OPP_SERIALNUMBER + " = "
				+ OutsidePracticePlaceTable.OPP_SERIALNUMBER + " +1 where "
				+ OutsidePracticePlaceTable.OPP_SERIALNUMBER + ">="
				+ OutsidePracticePlace.getOpp_serialnumber();
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

	
		
		String sql = "insert into " + OutsidePracticePlaceTable.TABLE_NAME + "("
				+ OutsidePracticePlaceTable.OPP_PLACENAME + ","
				+ OutsidePracticePlaceTable.OPP_COLLEGENAME + ","
				+ OutsidePracticePlaceTable.OPP_ADDRESS + ","
				+ OutsidePracticePlaceTable.OPP_MAJORS + ","
				+ OutsidePracticePlaceTable.OPP_STUDENTSPERTIME + ","
				+ OutsidePracticePlaceTable.OPP_STUDENTSTHISYEAR + ","
				+ OutsidePracticePlaceTable.OPP_MONTH + ","
				+ OutsidePracticePlaceTable.OPP_COOPERATOR + ","
				+ OutsidePracticePlaceTable.OPP_SETUPDATE + ","
				+ OutsidePracticePlaceTable.OPP_LEVEL + ","
				+ OutsidePracticePlaceTable.OPP_ACCUMULATEDSTUDENTS + ","
				+ OutsidePracticePlaceTable.OPP_COLLEGE + ","
				+ OutsidePracticePlaceTable.OPP_SERIALNUMBER + ","
				+ OutsidePracticePlaceTable.OPP_ISNULL + ")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

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
			pstmt.setString(1, OutsidePracticePlace.getOpp_placename());
			pstmt.setString(2, OutsidePracticePlace.getOpp_collegename());
			pstmt.setString(3, OutsidePracticePlace.getOpp_address());
			pstmt.setString(4, OutsidePracticePlace.getOpp_majors());
			pstmt.setInt(5, OutsidePracticePlace.getOpp_studentspertime());
			pstmt.setInt(6, OutsidePracticePlace.getOpp_studentsthisyear());
			pstmt.setString(7, OutsidePracticePlace.getOpp_month());
			pstmt.setString(8, OutsidePracticePlace.getOpp_cooperator());
			pstmt.setString(9, OutsidePracticePlace.getOpp_setupdate());
			pstmt.setString(10, OutsidePracticePlace.getOpp_level());
			pstmt.setInt(11, OutsidePracticePlace.getOpp_accumulatedstudents());
			pstmt.setString(12, OutsidePracticePlace.getOpp_college());
			pstmt.setInt(13, OutsidePracticePlace.getOpp_serialnumber());
			pstmt.setInt(14, OutsidePracticePlace.getOpp_isnull());
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
	public int alterOutsidePracticePlace(Map<String, String> valueMap, String id) {
		// TODO Auto-generated method stub
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(OutsidePracticePlaceTable.OPP_ID, id);
		int result = updateRecord(OutsidePracticePlaceTable.TABLE_NAME, valueMap,
				condition);
		return result;
	}

	@Override
	public List<OutsidePracticePlace> getAllOutsidePracticePlace() {
		// TODO Auto-generated method stub
		String sql = " select * from " + OutsidePracticePlaceTable.TABLE_NAME
				+ " where 1=1 " + " order by " + OutsidePracticePlaceTable.OPP_ID;
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
			List<OutsidePracticePlace> outsidePracticePlaceList = new ArrayList<OutsidePracticePlace>();
			while (resultSet.next()) {
				int opp_id = resultSet.getInt(OutsidePracticePlaceTable.OPP_ID);
				 
				String opp_placename = resultSet.getString(OutsidePracticePlaceTable.OPP_PLACENAME);   
				String opp_collegename  = resultSet.getString(OutsidePracticePlaceTable.OPP_COLLEGENAME); 
				String opp_address = resultSet.getString(OutsidePracticePlaceTable.OPP_ADDRESS); 
			 	String opp_majors = resultSet.getString(OutsidePracticePlaceTable.OPP_MAJORS);  
			 	Integer opp_studentspertime = resultSet.getInt(OutsidePracticePlaceTable.OPP_STUDENTSPERTIME); 
				if(opp_studentspertime == -1) opp_studentspertime = null;
				Integer opp_studentsthisyear = resultSet.getInt(OutsidePracticePlaceTable.OPP_STUDENTSTHISYEAR); 
				if(opp_studentsthisyear == -1) opp_studentsthisyear = null;
				String opp_month = resultSet.getString(OutsidePracticePlaceTable.OPP_MONTH); 
				String opp_cooperator = resultSet.getString(OutsidePracticePlaceTable.OPP_COOPERATOR);  
				String opp_setupdate = resultSet.getString(OutsidePracticePlaceTable.OPP_SETUPDATE); 
				String opp_level = resultSet.getString(OutsidePracticePlaceTable.OPP_LEVEL);
				Integer opp_accumulatedstudents = resultSet.getInt(OutsidePracticePlaceTable.OPP_ACCUMULATEDSTUDENTS);
				if(opp_accumulatedstudents == -1) opp_accumulatedstudents = null;
						
				String opp_college  = resultSet.getString(OutsidePracticePlaceTable.OPP_COLLEGE); 
				int opp_serialnumber = resultSet.getInt(OutsidePracticePlaceTable.OPP_SERIALNUMBER);
				Date opp_deadline = resultSet.getDate(OutsidePracticePlaceTable.OPP_DEADLINE); 
				String opp_comments =  resultSet.getString(OutsidePracticePlaceTable.OPP_COMMENTS);
				if(null == opp_comments){
					opp_comments = "";
				}
				int opp_isnull = resultSet.getInt(OutsidePracticePlaceTable.OPP_ISNULL);
				OutsidePracticePlace outsidePracticePlace = new OutsidePracticePlace(opp_id, opp_placename, opp_collegename, opp_address, opp_majors, 
			            opp_studentspertime, opp_studentsthisyear, opp_month, opp_cooperator, opp_setupdate, opp_level, 
			            opp_accumulatedstudents, opp_college,opp_deadline, opp_serialnumber, opp_comments, opp_isnull);
				
				outsidePracticePlaceList.add(outsidePracticePlace);
			}
			return outsidePracticePlaceList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}

	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
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

		String sql = "delete from " + OutsidePracticePlaceTable.TABLE_NAME;
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils_DBCP.release(connection, stmt, null);
		}
	}

	@Override
	public void deleteByCollegeandDeadline(String college, Date deadline)
			throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();
		String sql = "delete from " + OutsidePracticePlaceTable.TABLE_NAME
				+ " where " + OutsidePracticePlaceTable.OPP_COLLEGE + " = '" + college + "'" +" and opp_deadline is null ";
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
