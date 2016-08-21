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

import cn.edu.xmu.dao.ManagerInfoDao;
import cn.edu.xmu.entity.ManagerInfo;
import cn.edu.xmu.table.ManagerInfoTable;
import cn.edu.xmu.table.SchoolExecutiveUnitTable;
import cn.edu.xmu.table.TeachScientificTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;

public class ManagerInfoDaoImpl extends BaseDaoImpl<ManagerInfo> implements ManagerInfoDao{

	@Override
	public int getManagerInfoCount(Map queryParams) {
		int count  = 0;
		String sql = "select count(*) from " + ManagerInfoTable.TABLE_NAME +" where 1 = 1"; 
		Connection connection = null;
		try
		{
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1)
		{
			e1.printStackTrace();
			return -1;
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
		}finally{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		System.err.println(count);
		return count;
	}

	@Override
	public List<ManagerInfo> getManagerInfo(int start, int end, String sortStr,
			String orderStr,Map<String, String> params,
			Date deadline) {
		// TODO Auto-generated method stub
		String sql = " select tmp.* from ( " + " select * from "
				+ ManagerInfoTable.TABLE_NAME + " where 1=1 ";
		if (deadline != null) {

			sql += String.format("and "+ManagerInfoTable.MI_DEADLINE+" like  '%s%%' ", deadline);
		}
		if (!(params == null || params.keySet().size() == 0)) {
			for (Object object : params.keySet()) {

				String key = object.toString();
				String value = params.get(key).toString();
				sql += String.format(" and %s like  '%%%s%%' ", key, value);
			}
		}

		sql += " order by " + sortStr + " " + orderStr + " ) tmp limit "
				+ start + " ," + end;

		System.out.println(sql);

		List<ManagerInfo> mis = new ArrayList<ManagerInfo>();
		Connection connection = null;
		try
		{
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1)
		{
			e1.printStackTrace();
	
		}
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		try {
			System.out.println(sql);
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			Date temp = Date.valueOf("1800-1-1");
			
			while(resultSet.next()){
				
				
				int mi_id = resultSet.getInt(ManagerInfoTable.MI_ID);
				String mi_name = resultSet.getString(ManagerInfoTable.MI_NAME); //姓名
			    String mi_worknumber = resultSet.getString(ManagerInfoTable.MI_WORKNUMBER);  //工号		   
			    String mi_sex = resultSet.getString(ManagerInfoTable.MI_SEX);; //性别			    
			    Date mi_birthday =  resultSet.getDate(ManagerInfoTable.MI_BIRTHDAY); //出生年月		    
			    if(mi_birthday.equals(temp))
			    	mi_birthday = null;
			    Date mi_inschooldate = resultSet.getDate(ManagerInfoTable.MI_INSCHOOLDATE); //入校时间
			    if(mi_inschooldate.equals(temp))
			    	mi_inschooldate = null;
			    String mi_managertype = resultSet.getString(ManagerInfoTable.MI_MANAGERTYPE);; //师资类别
			    String mi_departmentnumber = resultSet.getString(ManagerInfoTable.MI_DEPARTMENTNUMBER);  //单位号
			    String mi_departmentname = resultSet.getString(ManagerInfoTable.MI_DEPARTMENTNAME);  //单位名称
			    String mi_education = resultSet.getString(ManagerInfoTable.MI_EDUCATION);  //学历
			    String mi_degrees = resultSet.getString(ManagerInfoTable.MI_DEGREES);  //最高学位
			    String mi_professionaltitle =  resultSet.getString(ManagerInfoTable.MI_PROFESSIONALTITLE); //专业技术职称
			    String mi_duty = resultSet.getString(ManagerInfoTable.MI_DUTY);//职务
			    String mi_college = resultSet.getString(ManagerInfoTable.MI_COLLEGE);//填报学院
			    Date mi_deadline = resultSet.getDate(ManagerInfoTable.MI_DEADLINE);//截止时间
			    int mi_serialnumber = resultSet.getInt(ManagerInfoTable.MI_SERIALNUMBER);//序列号
			    String mi_comments = resultSet.getString(ManagerInfoTable.MI_COMMENTS);//备注,用于填写审核意见
			    int isnull = resultSet.getInt(ManagerInfoTable.ISNULL);
	
				ManagerInfo managerInfo = new ManagerInfo(mi_id, mi_name, mi_worknumber, mi_sex, mi_birthday, mi_inschooldate, 
						mi_managertype, mi_departmentnumber, mi_departmentname, mi_education, mi_degrees, mi_professionaltitle, 
						mi_duty, mi_college, mi_deadline, mi_serialnumber, mi_comments,isnull);
				
			    mis.add(managerInfo);
			}
			return mis;
		   } catch (SQLException e) {
			e.printStackTrace();
			return null;
		   }finally{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		   }
	}


	@Override
	public int alterManagerInfo(Map<String, String> valueMap, String id) {
		// TODO Auto-generated method stub
		Map<String,String> condition = new HashMap<String,String>();
		condition.put(ManagerInfoTable.MI_ID, id);
		int result = updateRecord(ManagerInfoTable.TABLE_NAME, valueMap, condition);
		return result;
	}

	@Override
	public boolean batchDelete(String[] miids) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		try
		{
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1)
		{
			e1.printStackTrace();
			return false;
		}
		Statement stmt = connection.createStatement(); 
		
		for (String miid : miids) {
			String sql = "delete from "+ManagerInfoTable.TABLE_NAME+ " where "+ManagerInfoTable.MI_ID + " = '"+ miid+ "'";
			try {
				stmt.executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
//		stmt.close();
//		connection.close();
		JdbcUtils_DBCP.release(connection, stmt, null);
		return true;
	}



	@Override
	public int addManagerInfoRecord(ManagerInfo managerInfo) {
		int result = 0;

		String t_sql = "update " + ManagerInfoTable.TABLE_NAME + " set "
				+ ManagerInfoTable.MI_SERIALNUMBER + " = "
				+ ManagerInfoTable.MI_SERIALNUMBER + " +1 where "
				+ ManagerInfoTable.MI_SERIALNUMBER + ">=?";

		
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
			t_pstmt.setInt(1, managerInfo.getMi_serialnumber());
			
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
		String sql = "insert into " + ManagerInfoTable.TABLE_NAME + "("
				+ ManagerInfoTable.MI_NAME + "," 
				+ ManagerInfoTable.MI_WORKNUMBER + "," 
				+ ManagerInfoTable.MI_SEX + ","
				+ ManagerInfoTable.MI_BIRTHDAY + ","
				+ ManagerInfoTable.MI_INSCHOOLDATE + ","
				+ ManagerInfoTable.MI_MANAGERTYPE + ","
				+ ManagerInfoTable.MI_DEPARTMENTNUMBER + ","
				+ ManagerInfoTable.MI_DEPARTMENTNAME + ","
				+ ManagerInfoTable.MI_EDUCATION + ","
				+ ManagerInfoTable.MI_DEGREES + ","
				+ ManagerInfoTable.MI_PROFESSIONALTITLE + ","
				+ ManagerInfoTable.MI_DUTY + ","
				+ ManagerInfoTable.MI_COLLEGE + ","
				+ ManagerInfoTable.MI_SERIALNUMBER + ","
				+ ManagerInfoTable.MI_COMMENTS  + ","
				+ ManagerInfoTable.ISNULL
				+")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		System.out.println("添加纪录" + sql);
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		PreparedStatement pstmt = null;

		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, managerInfo.getMi_name());
			pstmt.setString(2, managerInfo.getMi_worknumber());
			pstmt.setString(3, managerInfo.getMi_sex());
			pstmt.setDate(4, managerInfo.getMi_birthday());
			pstmt.setDate(5, managerInfo.getMi_inschooldate());
			pstmt.setString(6, managerInfo.getMi_managertype());
			pstmt.setString(7, managerInfo.getMi_departmentnumber());
			pstmt.setString(8, managerInfo.getMi_departmentname());
			pstmt.setString(9, managerInfo.getMi_education());
			pstmt.setString(10, managerInfo.getMi_degrees());
			pstmt.setString(11, managerInfo.getMi_professionaltitle());
			pstmt.setString(12, managerInfo.getMi_duty());
			pstmt.setString(13, managerInfo.getMi_college());
			pstmt.setInt(14,	managerInfo.getMi_serialnumber());
			pstmt.setString(15, managerInfo.getMi_comments());
			pstmt.setInt(16, managerInfo.getIsnull());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			result = -1;
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, null);
		}
		return result;
	}


	@Override
	public int getCountByRange(String param, Date start, Date end,Map params) {
		int count = 0;
		String sql = "select count(*) from " + ManagerInfoTable.TABLE_NAME + String.format(" where unix_timestamp( %s ) between unix_timestamp( '%s') and unix_timestamp( '%s' ) ",param,start, end);
		
		if (!(params == null || params.keySet().size() == 0)) {
			for (Object object : params.keySet()) {

				String key = object.toString();
				String value = params.get(key).toString();
				sql += String.format(" and %s like  '%%%s%%' ", key, value);
			}
		}
		
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
			return -1;
		}
		System.out.println(sql);
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
		System.err.println(count);
		return count;
	}

	@Override
	public void deleteByCollegeandDeadline(String college, Date deadline) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();
		String sql = "delete from " + ManagerInfoTable.TABLE_NAME
				+ " where " + ManagerInfoTable.MI_COLLEGE + " = '" + college + "'" +" and mi_deadline is null ";
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
	public List<ManagerInfo> getAllManagerInfo() {
		String sql = " select * from " + ManagerInfoTable.TABLE_NAME
				+ " where 1=1 " + " order by " + ManagerInfoTable.MI_ID;
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
			List<ManagerInfo> mis = new ArrayList<ManagerInfo>();
			Date temp = Date.valueOf("1800-1-1");
			
			while(resultSet.next()){
				
				
				int mi_id = resultSet.getInt(ManagerInfoTable.MI_ID);
				String mi_name = resultSet.getString(ManagerInfoTable.MI_NAME); //姓名
			    String mi_worknumber = resultSet.getString(ManagerInfoTable.MI_WORKNUMBER);  //工号		   
			    String mi_sex = resultSet.getString(ManagerInfoTable.MI_SEX);; //性别			    
			    Date mi_birthday =  resultSet.getDate(ManagerInfoTable.MI_BIRTHDAY); //出生年月		    
			    if(mi_birthday.equals(temp))
			    	mi_birthday = null;
			    Date mi_inschooldate = resultSet.getDate(ManagerInfoTable.MI_INSCHOOLDATE); //入校时间
			    if(mi_inschooldate.equals(temp))
			    	mi_inschooldate = null;
			    String mi_managertype = resultSet.getString(ManagerInfoTable.MI_MANAGERTYPE);; //师资类别
			    String mi_departmentnumber = resultSet.getString(ManagerInfoTable.MI_DEPARTMENTNUMBER);  //单位号
			    String mi_departmentname = resultSet.getString(ManagerInfoTable.MI_DEPARTMENTNAME);  //单位名称
			    String mi_education = resultSet.getString(ManagerInfoTable.MI_EDUCATION);  //学历
			    String mi_degrees = resultSet.getString(ManagerInfoTable.MI_DEGREES);  //最高学位
			    String mi_professionaltitle =  resultSet.getString(ManagerInfoTable.MI_PROFESSIONALTITLE); //专业技术职称
			    String mi_duty = resultSet.getString(ManagerInfoTable.MI_DUTY);//职务
			    String mi_college = resultSet.getString(ManagerInfoTable.MI_COLLEGE);//填报学院
			    Date mi_deadline = resultSet.getDate(ManagerInfoTable.MI_DEADLINE);//截止时间
			    int mi_serialnumber = resultSet.getInt(ManagerInfoTable.MI_SERIALNUMBER);//序列号
			    String mi_comments = resultSet.getString(ManagerInfoTable.MI_COMMENTS);//备注,用于填写审核意见
			    int isnull = resultSet.getInt(ManagerInfoTable.ISNULL);
	
				ManagerInfo managerInfo = new ManagerInfo(mi_id, mi_name, mi_worknumber, mi_sex, mi_birthday, mi_inschooldate, 
						mi_managertype, mi_departmentnumber, mi_departmentname, mi_education, mi_degrees, mi_professionaltitle, 
						mi_duty, mi_college, mi_deadline, mi_serialnumber, mi_comments,isnull);
				
			    mis.add(managerInfo);
			}
			return mis;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
	}
	@Override
	public int getManagerInfoCountInTeachingScientific(Map queryParams) {
		int count  = 0;
		String sql = "select count(*) from " + ManagerInfoTable.TABLE_NAME +" where 1 = 1"; 
		Connection connection = null;
		try
		{
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1)
		{
			e1.printStackTrace();
			return -1;
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
	
		
		sql += " and "+ManagerInfoTable.MI_DEPARTMENTNAME+" in (select distinct "+TeachScientificTable.TS_NAME+" from "+TeachScientificTable.TABLE_NAME+" where 1 = 1)";
		
		System.out.println(sql);
		
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
		}finally{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		System.err.println(count);
		return count;
	}

	@Override
	public int getManagerInfoCountInExecutiveUnit(Map queryParams) {
		int count  = 0;
		String sql = "select count(*) from " + ManagerInfoTable.TABLE_NAME +" where 1 = 1"; 
		Connection connection = null;
		try
		{
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1)
		{
			e1.printStackTrace();
			return -1;
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
	
		
		sql += " and "+ManagerInfoTable.MI_DEPARTMENTNAME+" in (select distinct "+SchoolExecutiveUnitTable.SEU_DEPARTMENTNAME+" from "+SchoolExecutiveUnitTable.TABLE_NAME+" where 1 = 1)";
		
		System.out.println(sql);
		
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
		}finally{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		System.err.println(count);
		return count;
	}

	@Override
	public int getCountByRangeInTeachingScientific(String param, Date start,
			Date end,Map params) {
		int count = 0;
		String sql = "select count(*) from " + ManagerInfoTable.TABLE_NAME + String.format(" where unix_timestamp( %s ) between unix_timestamp( '%s') and unix_timestamp( '%s' ) ",param,start, end);
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
			return -1;
		}
		
		if (params != null) {
			for (Object object : params.keySet()) {
				String key = object.toString();
				String value = params.get(key).toString();
				sql += String.format(" and  %s like  '%%%s%%' ", key, value);
			}
		}
		sql += " and "+ManagerInfoTable.MI_DEPARTMENTNAME+" in (select distinct "+TeachScientificTable.TS_NAME+" from "+TeachScientificTable.TABLE_NAME+" where 1 = 1)";
		
		System.out.println(sql);
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
		System.err.println(count);
		return count;
	}

	
	@Override
	public int getCountByRangeInExecutiveUnit(String param, Date start, Date end,Map params) {
		int count = 0;
		String sql = "select count(*) from " + ManagerInfoTable.TABLE_NAME + String.format(" where unix_timestamp( %s ) between unix_timestamp( '%s') and unix_timestamp( '%s' ) ",param,start, end);
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
			return -1;
		}
		
		if (params != null) {
			for (Object object : params.keySet()) {
				String key = object.toString();
				String value = params.get(key).toString();
				sql += String.format(" and  %s like  '%%%s%%' ", key, value);
			}
		}
		sql += " and "+ManagerInfoTable.MI_DEPARTMENTNAME+" in (select distinct "
		+ SchoolExecutiveUnitTable.SEU_DEPARTMENTNAME+" from "+SchoolExecutiveUnitTable.TABLE_NAME+" where 1 = 1)";
		
		System.out.println(sql);
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
		System.err.println(count);
		return count;
	}

	@Override
	public int getCountByWorkNumber(String workNumber, String id) {
		int count = 0;
		String sql = "select count(*) from " + ManagerInfoTable.TABLE_NAME 
				+ String.format(" where %s = '%s'", ManagerInfoTable.MI_WORKNUMBER,workNumber);
		if(id != null && !"".equals(id))
			sql += String.format(" and %s != '%s'", ManagerInfoTable.MI_ID,id);
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
			return -1;
		}
		System.out.println(sql);
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
		System.err.println(count);
		return count;
	}
	

}

