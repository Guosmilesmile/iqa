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

import cn.edu.xmu.dao.TeachingAwardDao;
import cn.edu.xmu.entity.TeachingAward;
import cn.edu.xmu.table.ForeignExchangeTable;
import cn.edu.xmu.table.FullTimeTeacherInfoTable;
import cn.edu.xmu.table.MajorInfoTable;
import cn.edu.xmu.table.ManagerInfoTable;
import cn.edu.xmu.table.TeachingAwardTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;


/**
 * 7-3-2  教学成果奖
 * @author chunfeng
 *
 */
public class TeachingAwardDaoImpl extends BaseDaoImpl<TeachingAward> implements TeachingAwardDao {

	@Override
	public List<TeachingAward> getTeachingAward(int start, int end,
			String sortStr, String orderStr, Map<String, String> params, Date deadline) {
		
		String sql = " select tmp.* from ( " + " select * from "
				+ TeachingAwardTable.TABLE_NAME + " where 1=1 ";
		if (deadline != null) {
			sql += String.format("and "+TeachingAwardTable.TA_DEADLINE+" like  '%s%%' ", deadline);
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

		List<TeachingAward> teachingAwards = new ArrayList<TeachingAward>();
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
				int ta_id = resultSet.getInt(TeachingAwardTable.TA_ID);				
				String ta_projectname = resultSet.getString(TeachingAwardTable.TA_PROJECTNAME);   
				String ta_compere  = resultSet.getString(TeachingAwardTable.TA_COMPERE); 
				String ta_comperenumber = resultSet.getString(TeachingAwardTable.TA_COMPERENUMBER); 
			 	String ta_level = resultSet.getString(TeachingAwardTable.TA_LEVEL);  
				String ta_windate = resultSet.getString(TeachingAwardTable.TA_WINDATE); 
				String ta_grantunit = resultSet.getString(TeachingAwardTable.TA_GRANTUNIT);  				
					
			    String ta_college = resultSet.getString(TeachingAwardTable.TA_COLLEGE);
			    Date ta_deadline = resultSet.getDate(TeachingAwardTable.TA_DEADLINE); 
				int ta_serialnumber = resultSet.getInt(TeachingAwardTable.TA_SERIALNUMBER);				
				String ta_comments =  resultSet.getString(TeachingAwardTable.TA_COMMENTS);
				if(null == ta_comments){
					ta_comments = "";
				}
				int ta_isnull = resultSet.getInt(TeachingAwardTable.TA_ISNULL);		
				TeachingAward teachingAward = new TeachingAward(ta_id, ta_projectname,ta_compere,ta_comperenumber,ta_level,
						ta_windate,ta_grantunit, ta_college,ta_deadline,ta_serialnumber,ta_comments,ta_isnull);

				teachingAwards.add(teachingAward);
			}
			return teachingAwards;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
	}

	@Override
	public int getTeachingAwardCount(Map queryParams) {
		// TODO Auto-generated method stub
		int count = 0;
		String sql = "select count(*) from " + TeachingAwardTable.TABLE_NAME
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
	public boolean batchDelete(String[] taids) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();

		for (String taid : taids) {
			String sql = "delete from " + TeachingAwardTable.TABLE_NAME
					+ " where " + TeachingAwardTable.TA_ID + " = '" + taid
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
	public int addTeachingAward(TeachingAward TeachingAward) {
		// TODO Auto-generated method stub
		int result = 0;

		String sql2 = "update " + TeachingAwardTable.TABLE_NAME + " set "
				+ TeachingAwardTable.TA_SERIALNUMBER + " = "
				+ TeachingAwardTable.TA_SERIALNUMBER + " +1 where "
				+ TeachingAwardTable.TA_SERIALNUMBER + ">="
				+ TeachingAward.getTa_serialnumber();
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

	
		
		String sql = "insert into " + TeachingAwardTable.TABLE_NAME + "("
				+TeachingAwardTable.TA_PROJECTNAME+","  
		        +TeachingAwardTable.TA_COMPERE+","
		        +TeachingAwardTable.TA_COMPERENUMBER+"," 
	 	        +TeachingAwardTable.TA_LEVEL+","  
		        +TeachingAwardTable.TA_WINDATE+"," 
		        +TeachingAwardTable.TA_GRANTUNIT+","  
	            +TeachingAwardTable.TA_COLLEGE+","
	            +TeachingAwardTable.TA_SERIALNUMBER+ ","
	            +TeachingAwardTable.TA_ISNULL+")values(?,?,?,?,?,?,?,?,?)";

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
			pstmt.setString(1, TeachingAward.getTa_projectname());
			pstmt.setString(2, TeachingAward.getTa_compere());
			pstmt.setString(3, TeachingAward.getTa_comperenumber());
			pstmt.setString(4, TeachingAward.getTa_level());
			pstmt.setString(5, TeachingAward.getTa_windate());
			pstmt.setString(6, TeachingAward.getTa_grantunit());		
			pstmt.setString(7, TeachingAward.getTa_college());		
			pstmt.setInt(8, TeachingAward.getTa_serialnumber());
			pstmt.setInt(9, TeachingAward.getTa_isnull());
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
	public int alterTeachingAward(Map<String, String> valueMap, String id) {
		// TODO Auto-generated method stub
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(TeachingAwardTable.TA_ID, id);
		int result = updateRecord(TeachingAwardTable.TABLE_NAME, valueMap,
				condition);
		return result;
	}

	@Override
	public List<TeachingAward> getAllTeachingAward() {
		// TODO Auto-generated method stub
		String sql = " select * from " + TeachingAwardTable.TABLE_NAME
				+ " where 1=1 " + " order by " + TeachingAwardTable.TA_ID;
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
			List<TeachingAward> teachingAwardList = new ArrayList<TeachingAward>();
			while (resultSet.next()) {
				int ta_id = resultSet.getInt(TeachingAwardTable.TA_ID);				
				String ta_projectname = resultSet.getString(TeachingAwardTable.TA_PROJECTNAME);   
				String ta_compere  = resultSet.getString(TeachingAwardTable.TA_COMPERE); 
				String ta_comperenumber = resultSet.getString(TeachingAwardTable.TA_COMPERENUMBER); 
			 	String ta_level = resultSet.getString(TeachingAwardTable.TA_LEVEL);  
				String ta_windate = resultSet.getString(TeachingAwardTable.TA_WINDATE); 
				String ta_grantunit = resultSet.getString(TeachingAwardTable.TA_GRANTUNIT);  
				
			    String ta_college = resultSet.getString(TeachingAwardTable.TA_COLLEGE);
			    Date ta_deadline = resultSet.getDate(TeachingAwardTable.TA_DEADLINE); 
				int ta_serialnumber = resultSet.getInt(TeachingAwardTable.TA_SERIALNUMBER);				
				String ta_comments =  resultSet.getString(TeachingAwardTable.TA_COMMENTS);
				if(null == ta_comments){
					ta_comments = "";
				}
				int ta_isnull = resultSet.getInt(TeachingAwardTable.TA_ISNULL);		
				TeachingAward teachingAward = new TeachingAward(ta_id, ta_projectname,ta_compere,ta_comperenumber,ta_level,
						ta_windate, ta_grantunit, ta_college,ta_deadline,ta_serialnumber,ta_comments,ta_isnull);

				
				teachingAwardList.add(teachingAward);
			}
			return teachingAwardList;
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

		String sql = "delete from " + TeachingAwardTable.TABLE_NAME;
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
		String sql = "delete from " + TeachingAwardTable.TABLE_NAME
				+ " where " + TeachingAwardTable.TA_COLLEGE + " = '" + college + "'" +" and ta_deadline is null ";
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
	public int getTeachingAwardCountInManagerInfo(Map queryParams) {
		int count = 0;
		String sql = "select count(*) from " + TeachingAwardTable.TABLE_NAME
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
			 
		}
		sql += String.format(" or %s ='%s'", TeachingAwardTable.TA_COLLEGE,"");
		
		
		sql += " and "+TeachingAwardTable.TA_COMPERENUMBER+" in (select distinct "+ManagerInfoTable.MI_WORKNUMBER+" from "+ManagerInfoTable.TABLE_NAME+" where 1 = 1)";
		
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

}
