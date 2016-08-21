
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

import cn.edu.xmu.dao.StudentCenterDao;
import cn.edu.xmu.entity.StudentCenter;
import cn.edu.xmu.table.OutsidePracticePlaceTable;
import cn.edu.xmu.table.StudentCenterTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;


/**
 * 2-4-2  运动场、学生活动中心
 * @author chunfeng
 *
 */
public class StudentCenterDaoImpl extends BaseDaoImpl<StudentCenter> implements StudentCenterDao{

	@Override
	public List<StudentCenter> getStudentCenter(
			int start, int end, String sortStr, String orderStr,
			Map<String, String> params, Date deadline) {
		// TODO Auto-generated method stub
		String sql = " select tmp.* from ( " + " select * from "
				+ StudentCenterTable.TABLE_NAME + " where 1=1 ";
		if (deadline != null) {
			sql += String.format("and "+StudentCenterTable.SC_DEADLINE+" like  '%s%%' ", deadline);
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

		List<StudentCenter> studentCenters = new ArrayList<StudentCenter>();
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
				int sc_id = resultSet.getInt(StudentCenterTable.SC_ID);
				String sc_project = resultSet.getString(StudentCenterTable.SC_PROJECT);  
				Integer sc_quantity = resultSet.getInt(StudentCenterTable.SC_QUANTITY); 
				if(sc_quantity == -1) sc_quantity = null;
				Float sc_area = resultSet.getFloat(StudentCenterTable.SC_AREA); 
				if(sc_area == -1) sc_area = null;
				
				String sc_college =  resultSet.getString(StudentCenterTable.SC_COLLEGE);
				int sc_serialnumber = resultSet.getInt(StudentCenterTable.SC_SERIALNUMBER);
				Date sc_deadline = resultSet.getDate(StudentCenterTable.SC_DEADLINE); 
				String sc_comments =  resultSet.getString(StudentCenterTable.SC_COMMENTS);
				if(null == sc_comments){
					sc_comments = "";
				}
				int sc_isnull = resultSet.getInt(StudentCenterTable.SC_ISNULL);
				StudentCenter studentCenter = new StudentCenter(sc_id,
				   sc_project, sc_quantity, sc_area, sc_college, sc_deadline, sc_serialnumber, sc_comments, sc_isnull);

				studentCenters.add(studentCenter);
			}
			return studentCenters;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
	}

	@Override
	public int getStudentCenterCount(Map queryParams) {
		// TODO Auto-generated method stub
		int count = 0;
		String sql = "select count(*) from " + StudentCenterTable.TABLE_NAME
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
	public boolean batchDelete(String[] scids) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();

		for (String scid : scids) {
			String sql = "delete from " + StudentCenterTable.TABLE_NAME
					+ " where " + StudentCenterTable.SC_ID + " = '" + scid
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
	public int addStudentCenter(StudentCenter studentCenter) {
		// TODO Auto-generated method stub
		int result = 0;

		String sql2 = "update " + StudentCenterTable.TABLE_NAME + " set "
				+ StudentCenterTable.SC_SERIALNUMBER + " = "
				+ StudentCenterTable.SC_SERIALNUMBER + " +1 where "
				+ StudentCenterTable.SC_SERIALNUMBER + ">="
				+ studentCenter.getSc_serialnumber();
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

		String sql = "insert into " + StudentCenterTable.TABLE_NAME + "("
				+ StudentCenterTable.SC_PROJECT + ","			
				+ StudentCenterTable.SC_QUANTITY + ","
				+ StudentCenterTable.SC_AREA + ","
				+ StudentCenterTable.SC_COLLEGE + ","
				+ StudentCenterTable.SC_SERIALNUMBER + ","
				+ StudentCenterTable.SC_ISNULL + ")values(?,?,?,?,?,?)";

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
			pstmt.setString(1, studentCenter.getSc_project());
			pstmt.setInt(2, studentCenter.getSc_quantity());
			pstmt.setFloat(3, studentCenter.getSc_area());
			pstmt.setString(4, studentCenter.getSc_college());
			pstmt.setInt(5, studentCenter.getSc_serialnumber());
			pstmt.setInt(6, studentCenter.getSc_isnull());
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
	public int alterStudentCenter(
			Map<String, String> valueMap, String id) {
		// TODO Auto-generated method stub
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(StudentCenterTable.SC_ID, id);
		int result = updateRecord(StudentCenterTable.TABLE_NAME, valueMap,
				condition);
		return result;
	}

	@Override
	public List<StudentCenter> getAllStudentCenter() {
		// TODO Auto-generated method stub
		String sql = " select * from " + StudentCenterTable.TABLE_NAME
				+ " where 1=1 " + " order by " + StudentCenterTable.SC_ID;
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
			List<StudentCenter> studentCenterList = new ArrayList<StudentCenter>();
			while (resultSet.next()) {
				int sc_id = resultSet.getInt(StudentCenterTable.SC_ID);
				String sc_project = resultSet.getString(StudentCenterTable.SC_PROJECT);  
				Integer sc_quantity = resultSet.getInt(StudentCenterTable.SC_QUANTITY); 
				if(sc_quantity == -1) sc_quantity = null;
				Float sc_area = resultSet.getFloat(StudentCenterTable.SC_AREA); 
				if(sc_area == -1) sc_area = null;
				
				String sc_college =  resultSet.getString(StudentCenterTable.SC_COLLEGE);
				int sc_serialnumber = resultSet.getInt(StudentCenterTable.SC_SERIALNUMBER);
				Date sc_deadline = resultSet.getDate(StudentCenterTable.SC_DEADLINE); 
				String sc_comments =  resultSet.getString(StudentCenterTable.SC_COMMENTS);
				if(null == sc_comments){
					sc_comments = "";
				}
				int sc_isnull = resultSet.getInt(StudentCenterTable.SC_ISNULL);
				StudentCenter studentCenter = new StudentCenter(sc_id,
				   sc_project, sc_quantity, sc_area, sc_college, sc_deadline, sc_serialnumber, sc_comments, sc_isnull);

				studentCenterList.add(studentCenter);
			}
			return studentCenterList;
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

		String sql = "delete from " + StudentCenterTable.TABLE_NAME;
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
		String sql = "delete from " + StudentCenterTable.TABLE_NAME
				+ " where " + StudentCenterTable.SC_COLLEGE + " = '" + college + "'" +" and sc_deadline is null ";
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

