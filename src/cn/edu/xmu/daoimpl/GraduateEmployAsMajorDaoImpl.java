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

import cn.edu.xmu.dao.GraduateEmployAsMajorDao;
import cn.edu.xmu.entity.GraduateEmployAsMajor;
import cn.edu.xmu.table.ForeignExchangeTable;
import cn.edu.xmu.table.GraduateEmployAsMajorTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;


/**
 * 表6-1-9-3 应届本科毕业生分专业就业情况（时点）
 * @author chunfeng 
 *
 */
public class GraduateEmployAsMajorDaoImpl extends BaseDaoImpl<GraduateEmployAsMajor>
		implements GraduateEmployAsMajorDao {

	@Override
	public List<GraduateEmployAsMajor> getGraduateEmployAsMajors(int start, int end,
			String sortStr, String orderStr, Map<String, String> params, Date deadline) {

		String sql = " select tmp.* from ( " + " select * from "
				+ GraduateEmployAsMajorTable.TABLE_NAME + " where 1=1 ";
		if (deadline != null) {
			sql += String.format("and "+GraduateEmployAsMajorTable.GEAM_DEADLINE+" like  '%s%%' ", deadline);
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

		List<GraduateEmployAsMajor> graduateEmployAsMajors = new ArrayList<GraduateEmployAsMajor>();
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
				int geam_id = resultSet.getInt(GraduateEmployAsMajorTable.GEAM_ID);				
				String geam_majorcodeinschool = resultSet.getString(GraduateEmployAsMajorTable.GEAM_MAJORCODEINSCHOOL);
				String geam_majornameinschool = resultSet.getString(GraduateEmployAsMajorTable.GEAM_MAJORNAMEINSCHOOL);				
				Integer geam_graduatenum = resultSet.getInt(GraduateEmployAsMajorTable.GEAM_GRADUATENUM);
				if(geam_graduatenum == -1) geam_graduatenum = null;
				
				String geam_college = resultSet.getString(GraduateEmployAsMajorTable.GEAM_COLLEGE);
				int geam_serialnumber = resultSet.getInt(GraduateEmployAsMajorTable.GEAM_SERIALNUMBER);
				Date geam_deadline = resultSet.getDate(GraduateEmployAsMajorTable.GEAM_DEADLINE); 
				String geam_comments =  resultSet.getString(GraduateEmployAsMajorTable.GEAM_COMMENTS);
				if(null == geam_comments){
					geam_comments = "";
				}
				int geam_isnull = resultSet.getInt(GraduateEmployAsMajorTable.GEAM_ISNULL);
				
				GraduateEmployAsMajor graduateEmployAsMajor = new GraduateEmployAsMajor(
						geam_id, geam_majorcodeinschool, geam_majornameinschool, geam_graduatenum, geam_college, 
						geam_deadline, geam_serialnumber, geam_comments, geam_isnull);

				graduateEmployAsMajors.add(graduateEmployAsMajor);
			}
			return graduateEmployAsMajors;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}

	}

	@Override
	public int getGraduateEmployAsMajorCount(Map queryParams) {
		int count = 0;
		String sql = "select count(*) from " + GraduateEmployAsMajorTable.TABLE_NAME
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
	public boolean batchDelete(String[] geamids) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();

		for (String geamid : geamids) {
			String sql = "delete from " + GraduateEmployAsMajorTable.TABLE_NAME
					+ " where " + GraduateEmployAsMajorTable.GEAM_ID + " = '" + geamid
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
	public int addGraduateEmployAsMajor(GraduateEmployAsMajor GraduateEmployAsMajor) {
		int result = 0;

		String sql2 = "update " + GraduateEmployAsMajorTable.TABLE_NAME + " set "
				+ GraduateEmployAsMajorTable.GEAM_SERIALNUMBER + " = "
				+ GraduateEmployAsMajorTable.GEAM_SERIALNUMBER + " +1 where "
				+ GraduateEmployAsMajorTable.GEAM_SERIALNUMBER + ">="
				+ GraduateEmployAsMajor.getGeam_serialnumber();
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

		String sql = "insert into " + GraduateEmployAsMajorTable.TABLE_NAME + "("
				+ GraduateEmployAsMajorTable.GEAM_MAJORCODEINSCHOOL + ","
				+ GraduateEmployAsMajorTable.GEAM_MAJORNAMEINSCHOOL + ","
				+ GraduateEmployAsMajorTable.GEAM_GRADUATENUM + ","				
				+ GraduateEmployAsMajorTable.GEAM_COLLEGE + ","  
				+ GraduateEmployAsMajorTable.GEAM_SERIALNUMBER + ","
				+ GraduateEmployAsMajorTable.GEAM_ISNULL + ")values(?,?,?,?,?,?)";

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
			pstmt.setString(1, GraduateEmployAsMajor.getGeam_majorcodeinschool());
			pstmt.setString(2, GraduateEmployAsMajor.getGeam_majornameinschool());
			pstmt.setInt(3, GraduateEmployAsMajor.getGeam_graduatenum());
			pstmt.setString(4, GraduateEmployAsMajor.getGeam_college());
			pstmt.setInt(5, GraduateEmployAsMajor.getGeam_serialnumber());
			pstmt.setInt(6, GraduateEmployAsMajor.getGeam_isnull());
			
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
	public int alterGraduateEmployAsMajor(Map<String, String> valueMap, String id) {
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(GraduateEmployAsMajorTable.GEAM_ID, id);
		int result = updateRecord(GraduateEmployAsMajorTable.TABLE_NAME, valueMap,
				condition);
		return result;
	}

	@Override
	public List<GraduateEmployAsMajor> getAllGraduateEmployAsMajors() {
		String sql = " select * from " + GraduateEmployAsMajorTable.TABLE_NAME
				+ " where 1=1 " + " order by " + GraduateEmployAsMajorTable.GEAM_ID;
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
			List<GraduateEmployAsMajor> graduateEmployAsMajorList = new ArrayList<GraduateEmployAsMajor>();
			while (resultSet.next()) {
				int geam_id = resultSet.getInt(GraduateEmployAsMajorTable.GEAM_ID);				
				String geam_majorcodeinschool = resultSet.getString(GraduateEmployAsMajorTable.GEAM_MAJORCODEINSCHOOL);
				String geam_majornameinschool = resultSet.getString(GraduateEmployAsMajorTable.GEAM_MAJORNAMEINSCHOOL);				
				Integer geam_graduatenum = resultSet.getInt(GraduateEmployAsMajorTable.GEAM_GRADUATENUM);
				if(geam_graduatenum == -1) geam_graduatenum = null;
				
				String geam_college = resultSet.getString(GraduateEmployAsMajorTable.GEAM_COLLEGE);
				int geam_serialnumber = resultSet.getInt(GraduateEmployAsMajorTable.GEAM_SERIALNUMBER);
				Date geam_deadline = resultSet.getDate(GraduateEmployAsMajorTable.GEAM_DEADLINE); 
				String geam_comments =  resultSet.getString(GraduateEmployAsMajorTable.GEAM_COMMENTS);
				if(null == geam_comments){
					geam_comments = "";
				}
				int geam_isnull = resultSet.getInt(GraduateEmployAsMajorTable.GEAM_ISNULL);
				
				GraduateEmployAsMajor graduateEmployAsMajor = new GraduateEmployAsMajor(
						geam_id, geam_majorcodeinschool, geam_majornameinschool, geam_graduatenum, geam_college, 
						geam_deadline, geam_serialnumber, geam_comments, geam_isnull);
				graduateEmployAsMajorList.add(graduateEmployAsMajor);
			}
			return graduateEmployAsMajorList;
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

		String sql = "delete from " + GraduateEmployAsMajorTable.TABLE_NAME;
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
		String sql = "delete from " + GraduateEmployAsMajorTable.TABLE_NAME
				+ " where " + GraduateEmployAsMajorTable.GEAM_COLLEGE + " = '" + college + "'" +" and geam_deadline is null ";
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

	@Override
	public GraduateEmployAsMajor getGraduateEmployAsMajorsByMajor(
			String majorCode) {
		// TODO Auto-generated method stub
		String sql = " select * from "
				+ GraduateEmployAsMajorTable.TABLE_NAME + " where " + GraduateEmployAsMajorTable.GEAM_MAJORCODEINSCHOOL + " = "+ majorCode;
		
		List<GraduateEmployAsMajor> graduateEmployAsMajors = new ArrayList<GraduateEmployAsMajor>();
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
				int geam_id = resultSet.getInt(GraduateEmployAsMajorTable.GEAM_ID);				
				String geam_majorcodeinschool = resultSet.getString(GraduateEmployAsMajorTable.GEAM_MAJORCODEINSCHOOL);
				String geam_majornameinschool = resultSet.getString(GraduateEmployAsMajorTable.GEAM_MAJORNAMEINSCHOOL);				
				Integer geam_graduatenum = resultSet.getInt(GraduateEmployAsMajorTable.GEAM_GRADUATENUM);
				if(geam_graduatenum == -1) geam_graduatenum = null;
				
				String geam_college = resultSet.getString(GraduateEmployAsMajorTable.GEAM_COLLEGE);
				int geam_serialnumber = resultSet.getInt(GraduateEmployAsMajorTable.GEAM_SERIALNUMBER);
				Date geam_deadline = resultSet.getDate(GraduateEmployAsMajorTable.GEAM_DEADLINE); 
				String geam_comments =  resultSet.getString(GraduateEmployAsMajorTable.GEAM_COMMENTS);
				if(null == geam_comments){
					geam_comments = "";
				}
				int geam_isnull = resultSet.getInt(GraduateEmployAsMajorTable.GEAM_ISNULL);
				
				GraduateEmployAsMajor graduateEmployAsMajor = new GraduateEmployAsMajor(
						geam_id, geam_majorcodeinschool, geam_majornameinschool, geam_graduatenum, geam_college, 
						geam_deadline, geam_serialnumber, geam_comments, geam_isnull);

				graduateEmployAsMajors.add(graduateEmployAsMajor);
			}
			return graduateEmployAsMajors.get(0);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}

	}
}
