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

import cn.edu.xmu.dao.TeachingResearchAndReformProjectDao;
import cn.edu.xmu.entity.TeachingResearchAndReformProject;
import cn.edu.xmu.table.ForeignExchangeTable;
import cn.edu.xmu.table.ManagerInfoTable;
import cn.edu.xmu.table.TeachingResearchAndReformProjectTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;


/**
 * 7-3-1  教育教学研究与改革项目
 * @author chunfeng
 *
 */
public class TeachingResearchAndReformProjectDaoImpl extends BaseDaoImpl<TeachingResearchAndReformProject> implements TeachingResearchAndReformProjectDao {

	@Override
	public List<TeachingResearchAndReformProject> getTeachingResearchAndReformProject(int start, int end,
			String sortStr, String orderStr, Map<String, String> params,Date deadline) {
		
		String sql = " select tmp.* from ( " + " select * from "
				+ TeachingResearchAndReformProjectTable.TABLE_NAME + " where 1=1 ";
		if (deadline != null) {
			sql += String.format("and "+TeachingResearchAndReformProjectTable.TRARP_DEADLINE+" like  '%s%%' ", deadline);
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

		List<TeachingResearchAndReformProject> teachingResearchAndReformProjects = new ArrayList<TeachingResearchAndReformProject>();
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
				int trarp_id = resultSet.getInt(TeachingResearchAndReformProjectTable.TRARP_ID);				
				String trarp_projectname = resultSet.getString(TeachingResearchAndReformProjectTable.TRARP_PROJECTNAME);   
				String trarp_compere  = resultSet.getString(TeachingResearchAndReformProjectTable.TRARP_COMPERE); 
				String trarp_comperenumber = resultSet.getString(TeachingResearchAndReformProjectTable.TRARP_COMPERENUMBER); 
			 	String trarp_level = resultSet.getString(TeachingResearchAndReformProjectTable.TRARP_LEVEL);  
				String trarp_setupdate = resultSet.getString(TeachingResearchAndReformProjectTable.TRARP_SETUPDATE); 
				String trarp_checkdate = resultSet.getString(TeachingResearchAndReformProjectTable.TRARP_CHECKDATE);  
				Float trarp_funds = resultSet.getFloat(TeachingResearchAndReformProjectTable.TRARP_FUNDS);
				if(trarp_funds == -1.0) trarp_funds = null;
			    Integer trarp_jointeachers = resultSet.getInt(TeachingResearchAndReformProjectTable.TRARP_JOINTEACHERS);
			    if(trarp_jointeachers == -1) trarp_jointeachers = null;
					
			    String trarp_college = resultSet.getString(TeachingResearchAndReformProjectTable.TRARP_COLLEGE);
			    Date trarp_deadline = resultSet.getDate(TeachingResearchAndReformProjectTable.TRARP_DEADLINE); 
				int trarp_serialnumber = resultSet.getInt(TeachingResearchAndReformProjectTable.TRARP_SERIALNUMBER);				
				String trarp_comments =  resultSet.getString(TeachingResearchAndReformProjectTable.TRARP_COMMENTS);
				if(null == trarp_comments){
					trarp_comments = "";
				}
				int trarp_isnull = resultSet.getInt(TeachingResearchAndReformProjectTable.TRARP_ISNULL);	
				TeachingResearchAndReformProject teachingResearchAndReformProject = new TeachingResearchAndReformProject(trarp_id, trarp_projectname,trarp_compere,trarp_comperenumber,trarp_level,
						trarp_setupdate,trarp_checkdate,trarp_funds,trarp_jointeachers,trarp_college,trarp_deadline,trarp_serialnumber,trarp_comments, trarp_isnull);

				teachingResearchAndReformProjects.add(teachingResearchAndReformProject);
			}
			return teachingResearchAndReformProjects;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
	}

	@Override
	public int getTeachingResearchAndReformProjectCount(Map queryParams) {
		// TODO Auto-generated method stub
		int count = 0;
		String sql = "select count(*) from " + TeachingResearchAndReformProjectTable.TABLE_NAME
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
	public boolean batchDelete(String[] trarpids) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();

		for (String trarpid : trarpids) {
			String sql = "delete from " + TeachingResearchAndReformProjectTable.TABLE_NAME
					+ " where " + TeachingResearchAndReformProjectTable.TRARP_ID + " = '" + trarpid
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
	public int addTeachingResearchAndReformProject(TeachingResearchAndReformProject TeachingResearchAndReformProject) {
		// TODO Auto-generated method stub
		int result = 0;

		String sql2 = "update " + TeachingResearchAndReformProjectTable.TABLE_NAME + " set "
				+ TeachingResearchAndReformProjectTable.TRARP_SERIALNUMBER + " = "
				+ TeachingResearchAndReformProjectTable.TRARP_SERIALNUMBER + " +1 where "
				+ TeachingResearchAndReformProjectTable.TRARP_SERIALNUMBER + ">="
				+ TeachingResearchAndReformProject.getTrarp_serialnumber();
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

	
		
		String sql = "insert into " + TeachingResearchAndReformProjectTable.TABLE_NAME + "("
				+TeachingResearchAndReformProjectTable.TRARP_PROJECTNAME+","  
		        +TeachingResearchAndReformProjectTable.TRARP_COMPERE+","
		        +TeachingResearchAndReformProjectTable.TRARP_COMPERENUMBER+"," 
	 	        +TeachingResearchAndReformProjectTable.TRARP_LEVEL+","  
		        +TeachingResearchAndReformProjectTable.TRARP_SETUPDATE+"," 
		        +TeachingResearchAndReformProjectTable.TRARP_CHECKDATE+","  
		        +TeachingResearchAndReformProjectTable.TRARP_FUNDS+","
	            +TeachingResearchAndReformProjectTable.TRARP_JOINTEACHERS+","		
	            +TeachingResearchAndReformProjectTable.TRARP_COLLEGE+","
	            +TeachingResearchAndReformProjectTable.TRARP_SERIALNUMBER+"," 
	            +TeachingResearchAndReformProjectTable.TRARP_ISNULL + ")values(?,?,?,?,?,?,?,?,?,?,?)";

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
			pstmt.setString(1, TeachingResearchAndReformProject.getTrarp_projectname());
			pstmt.setString(2, TeachingResearchAndReformProject.getTrarp_compere());
			pstmt.setString(3, TeachingResearchAndReformProject.getTrarp_comperenumber());
			pstmt.setString(4, TeachingResearchAndReformProject.getTrarp_level());
			pstmt.setString(5, TeachingResearchAndReformProject.getTrarp_setupdate());
			pstmt.setString(6, TeachingResearchAndReformProject.getTrarp_checkdate());
			pstmt.setFloat(7, TeachingResearchAndReformProject.getTrarp_funds());
			pstmt.setInt(8, TeachingResearchAndReformProject.getTrarp_jointeachers());
			pstmt.setString(9, TeachingResearchAndReformProject.getTrarp_college());		
			pstmt.setInt(10, TeachingResearchAndReformProject.getTrarp_serialnumber());
			pstmt.setInt(11, TeachingResearchAndReformProject.getTrarp_isnull());
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
	public int alterTeachingResearchAndReformProject(Map<String, String> valueMap, String id) {
		// TODO Auto-generated method stub
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(TeachingResearchAndReformProjectTable.TRARP_ID, id);
		int result = updateRecord(TeachingResearchAndReformProjectTable.TABLE_NAME, valueMap,
				condition);
		return result;
	}

	@Override
	public List<TeachingResearchAndReformProject> getAllTeachingResearchAndReformProject() {
		// TODO Auto-generated method stub
		String sql = " select * from " + TeachingResearchAndReformProjectTable.TABLE_NAME
				+ " where 1=1 " + " order by " + TeachingResearchAndReformProjectTable.TRARP_ID;
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
			List<TeachingResearchAndReformProject> teachingResearchAndReformProjectList = new ArrayList<TeachingResearchAndReformProject>();
			while (resultSet.next()) {
				int trarp_id = resultSet.getInt(TeachingResearchAndReformProjectTable.TRARP_ID);				
				String trarp_projectname = resultSet.getString(TeachingResearchAndReformProjectTable.TRARP_PROJECTNAME);   
				String trarp_compere  = resultSet.getString(TeachingResearchAndReformProjectTable.TRARP_COMPERE); 
				String trarp_comperenumber = resultSet.getString(TeachingResearchAndReformProjectTable.TRARP_COMPERENUMBER); 
			 	String trarp_level = resultSet.getString(TeachingResearchAndReformProjectTable.TRARP_LEVEL);  
				String trarp_setupdate = resultSet.getString(TeachingResearchAndReformProjectTable.TRARP_SETUPDATE); 
				String trarp_checkdate = resultSet.getString(TeachingResearchAndReformProjectTable.TRARP_CHECKDATE);  
				Float trarp_funds = resultSet.getFloat(TeachingResearchAndReformProjectTable.TRARP_FUNDS);
				if(trarp_funds == -1.0) trarp_funds = null;
			    Integer trarp_jointeachers = resultSet.getInt(TeachingResearchAndReformProjectTable.TRARP_JOINTEACHERS);
			    if(trarp_jointeachers == -1) trarp_jointeachers = null;
					
			    String trarp_college = resultSet.getString(TeachingResearchAndReformProjectTable.TRARP_COLLEGE);
			    Date trarp_deadline = resultSet.getDate(TeachingResearchAndReformProjectTable.TRARP_DEADLINE); 
				int trarp_serialnumber = resultSet.getInt(TeachingResearchAndReformProjectTable.TRARP_SERIALNUMBER);				
				String trarp_comments =  resultSet.getString(TeachingResearchAndReformProjectTable.TRARP_COMMENTS);
				if(null == trarp_comments){
					trarp_comments = "";
				}
				int trarp_isnull = resultSet.getInt(TeachingResearchAndReformProjectTable.TRARP_ISNULL);	
				TeachingResearchAndReformProject teachingResearchAndReformProject = new TeachingResearchAndReformProject(trarp_id, trarp_projectname,trarp_compere,trarp_comperenumber,trarp_level,
						trarp_setupdate,trarp_checkdate,trarp_funds,trarp_jointeachers,trarp_college,trarp_deadline,trarp_serialnumber,trarp_comments, trarp_isnull);

				
				teachingResearchAndReformProjectList.add(teachingResearchAndReformProject);
			}
			return teachingResearchAndReformProjectList;
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

		String sql = "delete from " + TeachingResearchAndReformProjectTable.TABLE_NAME;
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
		String sql = "delete from " + TeachingResearchAndReformProjectTable.TABLE_NAME
				+ " where " + TeachingResearchAndReformProjectTable.TRARP_COLLEGE + " = '" + college + "'" +" and trarp_deadline is null ";
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
	public float getSumByParam(String param) {
		// TODO Auto-generated method stub
		float sum=0;
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Statement stmt = null;
		try {
			stmt = connection.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String sql = "select sum("+param+") from " + TeachingResearchAndReformProjectTable.TABLE_NAME;
	
		System.out.println(sql);
		try {
			sum = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils_DBCP.release(connection, stmt, null);
		}
		
		return sum;
	}

	@Override
	public List<TeachingResearchAndReformProject> getTeachingResearchAndReformProjectInManagerInfo(
			int start, int end, String sortStr, String orderStr,
			Map<String, String> params) {
		String sql = " select tmp.* from ( " + " select * from "
				+ TeachingResearchAndReformProjectTable.TABLE_NAME + " where 1=1 ";
		
		if (!(params == null || params.keySet().size() == 0)) {
			for (Object object : params.keySet()) {

				String key = object.toString();
				String value = params.get(key).toString();
				sql += String.format("and %s like  '%%%s%%' ", key, value);
			}
		}
		sql += String.format(" or %s ='%s'", TeachingResearchAndReformProjectTable.TRARP_COLLEGE,"");
		
		sql += " and "+TeachingResearchAndReformProjectTable.TRARP_COMPERENUMBER+" in (select distinct "+ManagerInfoTable.MI_WORKNUMBER+" from "+ManagerInfoTable.TABLE_NAME+" where 1 = 1)";
		
		
		sql += " order by " + sortStr + " " + orderStr + " ) tmp limit "
				+ start + " ," + end;

		System.out.println(sql);

		List<TeachingResearchAndReformProject> teachingResearchAndReformProjects = new ArrayList<TeachingResearchAndReformProject>();
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
				int trarp_id = resultSet.getInt(TeachingResearchAndReformProjectTable.TRARP_ID);				
				String trarp_projectname = resultSet.getString(TeachingResearchAndReformProjectTable.TRARP_PROJECTNAME);   
				String trarp_compere  = resultSet.getString(TeachingResearchAndReformProjectTable.TRARP_COMPERE); 
				String trarp_comperenumber = resultSet.getString(TeachingResearchAndReformProjectTable.TRARP_COMPERENUMBER); 
			 	String trarp_level = resultSet.getString(TeachingResearchAndReformProjectTable.TRARP_LEVEL);  
				String trarp_setupdate = resultSet.getString(TeachingResearchAndReformProjectTable.TRARP_SETUPDATE); 
				String trarp_checkdate = resultSet.getString(TeachingResearchAndReformProjectTable.TRARP_CHECKDATE);  
				float trarp_funds = resultSet.getFloat(TeachingResearchAndReformProjectTable.TRARP_FUNDS);
			    int trarp_jointeachers = resultSet.getInt(TeachingResearchAndReformProjectTable.TRARP_JOINTEACHERS);
					
			    String trarp_college = resultSet.getString(TeachingResearchAndReformProjectTable.TRARP_COLLEGE);
			    Date trarp_deadline = resultSet.getDate(TeachingResearchAndReformProjectTable.TRARP_DEADLINE); 
				int trarp_serialnumber = resultSet.getInt(TeachingResearchAndReformProjectTable.TRARP_SERIALNUMBER);				
				String trarp_comments =  resultSet.getString(TeachingResearchAndReformProjectTable.TRARP_COMMENTS);
				if(null == trarp_comments){
					trarp_comments = "";
				}
				int trarp_isnull = resultSet.getInt(TeachingResearchAndReformProjectTable.TRARP_ISNULL);	
				TeachingResearchAndReformProject teachingResearchAndReformProject = new TeachingResearchAndReformProject(trarp_id, trarp_projectname,trarp_compere,trarp_comperenumber,trarp_level,
						trarp_setupdate,trarp_checkdate,trarp_funds,trarp_jointeachers,trarp_college,trarp_deadline,trarp_serialnumber,trarp_comments, trarp_isnull);

				teachingResearchAndReformProjects.add(teachingResearchAndReformProject);
			}
			return teachingResearchAndReformProjects;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
	}

	@Override
	public int getTeachingResearchAndReformProjectCountInManagerInfo(
			Map queryParams) {
		int count = 0;
		String sql = "select count(*) from " + TeachingResearchAndReformProjectTable.TABLE_NAME
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
		sql += String.format(" or %s ='%s'", TeachingResearchAndReformProjectTable.TRARP_COLLEGE,"");
		
		sql += " and "+TeachingResearchAndReformProjectTable.TRARP_COMPERENUMBER+" in (select distinct "+ManagerInfoTable.MI_WORKNUMBER+" from "+ManagerInfoTable.TABLE_NAME+" where 1 = 1)";
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
