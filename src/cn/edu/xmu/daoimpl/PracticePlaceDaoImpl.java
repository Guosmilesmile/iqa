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

import cn.edu.xmu.dao.PracticePlaceDao;
import cn.edu.xmu.entity.PracticePlace;
import cn.edu.xmu.table.PracticePlaceTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;


/**
 * 2-6-1 本科实验实习实训场所
 * @author chunfeng
 *
 */
public class PracticePlaceDaoImpl extends BaseDaoImpl<PracticePlace> implements PracticePlaceDao {

	@Override
	public List<PracticePlace> getPracticePlace(int start, int end,
			String sortStr, String orderStr, Map<String, String> params,Date deadline) {
		
		String sql = " select tmp.* from ( " + " select * from "
				+ PracticePlaceTable.TABLE_NAME + " where 1=1 ";
		if (deadline != null) {
			sql += String.format("and "+PracticePlaceTable.PP_DEADLINE+" like  '%s%%' ", deadline);
		}
		if (!(params == null || params.keySet().size() == 0)) {
			for (Object object : params.keySet()) {

				String key = object.toString();
				String value = params.get(key).toString();
				sql += String.format("and %s like  '%%%s%%' ", key, value);
			}
		}

		if (sortStr == "nope") {
			
		}
		else {
			sql += " order by " + sortStr + " " + orderStr + " ) tmp limit "
					+ start + " ," + end;
		}


		System.out.println(sql);

		List<PracticePlace> practicePlaces = new ArrayList<PracticePlace>();
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
				int pp_id = resultSet.getInt(PracticePlaceTable.PP_ID);
 
				String pp_placename = resultSet.getString(PracticePlaceTable.PP_PLACENAME);   //名称
				String pp_collegename  = resultSet.getString(PracticePlaceTable.PP_COLLEGENAME); //院系名称
				Float pp_area = resultSet.getFloat(PracticePlaceTable.PP_AREA);  //面积
				if(pp_area == -1.0)
					pp_area = null;
				String pp_property = resultSet.getString(PracticePlaceTable.PP_PROPERTY); //实验室性质
			 	String pp_majors = resultSet.getString(PracticePlaceTable.PP_MAJORS);  //面向专业
				Integer pp_hours = resultSet.getInt(PracticePlaceTable.PP_HOURS);  //学年度承担的实验教学人时数
				if(pp_hours == -1)
					pp_hours = null;
				Integer pp_times = resultSet.getInt(PracticePlaceTable.PP_TIMES); //学年度承担的实验教学人次数
				if(pp_times == -1)
					pp_times = null;
				Integer pp_projectnum = resultSet.getInt(PracticePlaceTable.PP_PROJECTNUM); //本科生实验实习实训项目数
				if(pp_projectnum == -1)
					pp_projectnum = null;
				Integer pp_largeststudents = resultSet.getInt(PracticePlaceTable.PP_LARGESTSTUDENTS);  //最大可容纳学生数
				if(pp_largeststudents == -1)
					pp_largeststudents = null;
				
				String pp_college  = resultSet.getString(PracticePlaceTable.PP_COLLEGE); 
				int pp_serialnumber = resultSet.getInt(PracticePlaceTable.PP_SERIALNUMBER);
				Date pp_deadline = resultSet.getDate(PracticePlaceTable.PP_DEADLINE); 
				String pp_comments =  resultSet.getString(PracticePlaceTable.PP_COMMENTS);
				if(null == pp_comments){
					pp_comments = "";
				}
				int pp_isnull = resultSet.getInt(PracticePlaceTable.PP_ISNULL);
				
				PracticePlace practicePlace = new PracticePlace(pp_id, pp_placename, pp_collegename, pp_area, pp_property, pp_majors, 
						pp_hours, pp_times, pp_projectnum, pp_largeststudents, pp_college, pp_deadline, pp_serialnumber, pp_comments, pp_isnull);

				practicePlaces.add(practicePlace);
			}
			return practicePlaces;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
	}

	@Override
	public int getPracticePlaceCount(Map queryParams) {
		// TODO Auto-generated method stub
		int count = 0;
		String sql = "select count(*) from " + PracticePlaceTable.TABLE_NAME
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
	public boolean batchDelete(String[] ppids) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();

		for (String ppid : ppids) {
			String sql = "delete from " + PracticePlaceTable.TABLE_NAME
					+ " where " + PracticePlaceTable.PP_ID + " = '" + ppid
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
	public int addPracticePlace(PracticePlace practicePlace) {
		// TODO Auto-generated method stub
		int result = 0;

		String sql2 = "update " + PracticePlaceTable.TABLE_NAME + " set "
				+ PracticePlaceTable.PP_SERIALNUMBER + " = "
				+ PracticePlaceTable.PP_SERIALNUMBER + " +1 where "
				+ PracticePlaceTable.PP_SERIALNUMBER + ">="
				+ practicePlace.getPp_serialnumber();
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

		String sql = "insert into " + PracticePlaceTable.TABLE_NAME + "("
				+ PracticePlaceTable.PP_PLACENAME + ","
				+ PracticePlaceTable.PP_COLLEGENAME + ","
				+ PracticePlaceTable.PP_AREA + ","
				+ PracticePlaceTable.PP_PROPERTY + ","
				+ PracticePlaceTable.PP_MAJORS + ","
				+ PracticePlaceTable.PP_HOURS + ","
				+ PracticePlaceTable.PP_TIMES + ","
				+ PracticePlaceTable.PP_PROJECTNUM + ","
				+ PracticePlaceTable.PP_LARGESTSTUDENTS + ","
				+ PracticePlaceTable.PP_COLLEGE + ","
				+ PracticePlaceTable.PP_SERIALNUMBER + ","
				+ PracticePlaceTable.PP_ISNULL +")values(?,?,?,?,?,?,?,?,?,?,?,?)";

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
			pstmt.setString(1, practicePlace.getPp_placename());
			pstmt.setString(2, practicePlace.getPp_collegename());
			pstmt.setFloat(3, practicePlace.getPp_area());
			pstmt.setString(4, practicePlace.getPp_property());
			pstmt.setString(5, practicePlace.getPp_majors());
			pstmt.setInt(6, practicePlace.getPp_hours());
			pstmt.setInt(7, practicePlace.getPp_times());
			pstmt.setInt(8, practicePlace.getPp_projectnum());
			pstmt.setInt(9, practicePlace.getPp_largeststudents());
			pstmt.setString(10, practicePlace.getPp_college());
			pstmt.setInt(11, practicePlace.getPp_serialnumber());
			pstmt.setInt(12, practicePlace.getPp_isnull());
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
	public int alterPracticePlace(Map<String, String> valueMap, String id) {
		// TODO Auto-generated method stub
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(PracticePlaceTable.PP_ID, id);
		int result = updateRecord(PracticePlaceTable.TABLE_NAME, valueMap,
				condition);
		return result;
	}

	@Override
	public List<PracticePlace> getAllPracticePlace() {
		// TODO Auto-generated method stub
		String sql = " select * from " + PracticePlaceTable.TABLE_NAME
				+ " where 1=1 " + " order by " + PracticePlaceTable.PP_ID;
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
			List<PracticePlace> practicePlaceList = new ArrayList<PracticePlace>();
			while (resultSet.next()) {
				int pp_id = resultSet.getInt(PracticePlaceTable.PP_ID);
				 
				String pp_placename = resultSet.getString(PracticePlaceTable.PP_PLACENAME);   //名称
				String pp_collegename  = resultSet.getString(PracticePlaceTable.PP_COLLEGENAME); //院系名称
				Float pp_area = resultSet.getFloat(PracticePlaceTable.PP_AREA);  //面积
				if(pp_area == -1.0)
					pp_area = null;
				String pp_property = resultSet.getString(PracticePlaceTable.PP_PROPERTY); //实验室性质
			 	String pp_majors = resultSet.getString(PracticePlaceTable.PP_MAJORS);  //面向专业
				Integer pp_hours = resultSet.getInt(PracticePlaceTable.PP_HOURS);  //学年度承担的实验教学人时数
				if(pp_hours == -1)
					pp_hours = null;
				Integer pp_times = resultSet.getInt(PracticePlaceTable.PP_TIMES); //学年度承担的实验教学人次数
				if(pp_times == -1)
					pp_times = null;
				Integer pp_projectnum = resultSet.getInt(PracticePlaceTable.PP_PROJECTNUM); //本科生实验实习实训项目数
				if(pp_projectnum == -1)
					pp_projectnum = null;
				Integer pp_largeststudents = resultSet.getInt(PracticePlaceTable.PP_LARGESTSTUDENTS);  //最大可容纳学生数
				if(pp_largeststudents == -1)
					pp_largeststudents = null;
				
				String pp_college  = resultSet.getString(PracticePlaceTable.PP_COLLEGE);
				int pp_serialnumber = resultSet.getInt(PracticePlaceTable.PP_SERIALNUMBER);
				Date pp_deadline = resultSet.getDate(PracticePlaceTable.PP_DEADLINE); 
				String pp_comments =  resultSet.getString(PracticePlaceTable.PP_COMMENTS);
				if(null == pp_comments){
					pp_comments = "";
				}
				int pp_isnull = resultSet.getInt(PracticePlaceTable.PP_ISNULL);
				
				PracticePlace practicePlace = new PracticePlace(pp_id, pp_placename, pp_collegename, pp_area, pp_property, pp_majors, 
						pp_hours, pp_times, pp_projectnum, pp_largeststudents, pp_college,  pp_deadline, pp_serialnumber, pp_comments, pp_isnull);

				practicePlaceList.add(practicePlace);
			}
			return practicePlaceList;
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

		String sql = "delete from " + PracticePlaceTable.TABLE_NAME;
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
		String sql = "delete from " + PracticePlaceTable.TABLE_NAME
				+ " where " + PracticePlaceTable.PP_COLLEGE + " = '" + college + "'" +" and pp_deadline is null ";
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
