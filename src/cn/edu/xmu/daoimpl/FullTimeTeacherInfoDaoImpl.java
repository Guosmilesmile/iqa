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

import org.apache.poi.hssf.record.formula.functions.Sum;

import cn.edu.xmu.dao.FullTimeTeacherInfoDao;
import cn.edu.xmu.entity.FullTimeTeacherInfo;
import cn.edu.xmu.entity.MajorTeacherStructureTemp;
import cn.edu.xmu.entity.TeachResearchReform;
import cn.edu.xmu.table.ExternalTeacherTable;
import cn.edu.xmu.table.FullTimeTeacherInfoTable;
import cn.edu.xmu.table.MajorInfoTable;
import cn.edu.xmu.table.MajorTeachTable;
import cn.edu.xmu.table.StartClassTable;
import cn.edu.xmu.table.TeachScientificTable;
import cn.edu.xmu.table.TeachingAwardTable;
import cn.edu.xmu.table.TeachingResearchAndReformProjectTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;

/**
 * 
 * @author xiaoping 专任教师基本信息 实体类功能接口 date 2015-06-29
 */
public class FullTimeTeacherInfoDaoImpl extends BaseDaoImpl<FullTimeTeacherInfo>implements FullTimeTeacherInfoDao
{

	@Override
	public List<FullTimeTeacherInfo> getFullTimeTeachers(int start, int end, String sortStr, String orderStr,
			Map queryParams, Date deadline)
	{
		String sql = " select tmp.* from ( " + " select * from " + FullTimeTeacherInfoTable.TABLE_NAME + " where 1=1 ";
		if (deadline != null) {
			sql += String.format("and "+FullTimeTeacherInfoTable.FTTI_DEADLINE+" like  '%s%%' ", deadline);
		}
		if (queryParams != null && queryParams.keySet().size() != 0)
		{
			for (Object object : queryParams.keySet())
			{
				String key = object.toString();
				String value = queryParams.get(key).toString();
				sql += String.format(" and %s like  '%%%s%%'", key, value);
			}
		}

		sql += " order by " + sortStr + " " + orderStr + " ) tmp limit " + start + " ," + end;

		System.out.println(sql);
		Connection connection = null;
		try
		{
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		}
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		try
		{
			System.out.println(sql);
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			List<FullTimeTeacherInfo> fullTimeTeacherInfos = new ArrayList<FullTimeTeacherInfo>();

			while (resultSet.next())
			{
				Date temp = Date.valueOf("1800-1-1");//用于date类型的比较，等于“1800-1-1”的date字段为null
				int ftti_id = resultSet.getInt(FullTimeTeacherInfoTable.FTTI_ID);
				String ftti_name = resultSet.getString(FullTimeTeacherInfoTable.FTTI_NAME);
				String ftti_worknumber = resultSet.getString(FullTimeTeacherInfoTable.FTTI_WORKNUMBER);
				String ftti_gender = resultSet.getString(FullTimeTeacherInfoTable.FTTI_GENDER);
				Date ftti_birthday = resultSet.getDate(FullTimeTeacherInfoTable.FTTI_BIRTHDAY);
				if(temp.equals(ftti_birthday))
					ftti_birthday = null;
				Date ftti_inschooldate = resultSet.getDate(FullTimeTeacherInfoTable.FTTI_INSCHOOLDATE);
				if(temp.equals(ftti_inschooldate))
					ftti_inschooldate = null;
				String ftti_workstate =resultSet.getString(FullTimeTeacherInfoTable.FTTI_WORKSTATE);

				String ftti_departmentnumber = resultSet.getString(FullTimeTeacherInfoTable.FTTI_DEPARTMENTNUMBER);

				String ftti_departmentname = resultSet.getString(FullTimeTeacherInfoTable.FTTI_DEPARTMENTNAME);
				String ftti_education =resultSet.getString(FullTimeTeacherInfoTable.FTTI_EDUCATION);
				String ftti_degree = resultSet.getString(FullTimeTeacherInfoTable.FTTI_DEGREE);
				String ftti_educationsource = resultSet.getString(FullTimeTeacherInfoTable.FTTI_EDUCATIONSOURCE);
				String ftti_professionaltitle = resultSet.getString(FullTimeTeacherInfoTable.FTTI_PROFESSIONALTITLE);
				String ftti_subjectcategory = resultSet.getString(FullTimeTeacherInfoTable.FTTI_SUBJECTCATEGORY);
				String ftti_ifdoublequalifiedteacher = resultSet.getString(FullTimeTeacherInfoTable.FTTI_IFDOUBLEQUALIFIEDTEACHER);
				String ftti_ifengineeringbackground =resultSet.getString(FullTimeTeacherInfoTable.FTTI_IFENGINEERINBACKGROUND);
				String ftti_ifindustrybackground = resultSet.getString(FullTimeTeacherInfoTable.FTTI_IFINDUSTRYBACKGROUND);
				String ftti_tutortype = resultSet.getString(FullTimeTeacherInfoTable.FTTI_TUTORTYPE);
				int ftti_serialnumber = resultSet.getInt(FullTimeTeacherInfoTable.FTTI_SERIALNUMBER);
				Date ftti_deadline = resultSet.getDate(FullTimeTeacherInfoTable.FTTI_DEADLINE);
				String ftti_college = resultSet.getString(FullTimeTeacherInfoTable.FTTI_COLLEGE);
				String ftti_comments = resultSet.getString(FullTimeTeacherInfoTable.FTTI_COMMENTS);
				int ftti_isnull = resultSet.getInt(FullTimeTeacherInfoTable.FTTI_ISNULL);
				FullTimeTeacherInfo fullTimeTeacherInfo = new FullTimeTeacherInfo(ftti_id, ftti_name, ftti_worknumber,
						ftti_gender, ftti_birthday, ftti_inschooldate, ftti_workstate, ftti_departmentnumber,
						ftti_departmentname, ftti_education, ftti_degree, ftti_educationsource, ftti_professionaltitle,
						ftti_subjectcategory, ftti_ifdoublequalifiedteacher, ftti_ifengineeringbackground,
						ftti_ifindustrybackground, ftti_tutortype, ftti_serialnumber, ftti_deadline, ftti_college,
						ftti_comments, ftti_isnull);
				fullTimeTeacherInfos.add(fullTimeTeacherInfo);
			}
			return fullTimeTeacherInfos;
		} catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		} finally
		{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
	}

	@Override
	public int getFullTimeTeacherCount(Map queryParams)
	{
		int count = 0;
		String sql = "select count(*) from " + FullTimeTeacherInfoTable.TABLE_NAME + " where 1 = 1";
		Connection connection = null;
		try
		{
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1)
		{
			// TODO Auto-generated catch block
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

		System.out.println(sql);
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		try
		{
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			resultSet.next();
			count = resultSet.getInt(1);
		} catch (SQLException e)
		{
			e.printStackTrace();
			return -1;
		} finally
		{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		System.err.println(count);
		return count;
	}

	@Override
	public boolean batchDelete(String[] fttiids)  throws SQLException
	{
		Connection connection = null;
		Statement stmt = null;
		try
		{
			connection = JdbcUtils_DBCP.getConnection();
			stmt = connection.createStatement();
		} catch (SQLException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}

		for (String fttid : fttiids)
		{
			String sql = "delete from " + FullTimeTeacherInfoTable.TABLE_NAME + " where ftti_id = '" + fttid + "'";
			System.out.println(sql);
			try
			{
				stmt.executeUpdate(sql);
			} catch (SQLException e)
			{
				e.printStackTrace();
				return false;
			}
		}
		JdbcUtils_DBCP.release(connection, stmt, null);
		return true;
	}

	@Override
	public int addFullTimeTeacher(FullTimeTeacherInfo fullTimeTeacherInfo)
	{
		int result = 0;

		String t_sql = "update " + FullTimeTeacherInfoTable.TABLE_NAME + " set "
				+ FullTimeTeacherInfoTable.FTTI_SERIALNUMBER + " = "
				+ FullTimeTeacherInfoTable.FTTI_SERIALNUMBER + " +1 where "
				+ FullTimeTeacherInfoTable.FTTI_SERIALNUMBER + ">=?";
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
			t_pstmt.setInt(1, fullTimeTeacherInfo.getFtti_serialnumber());
			
			//执行插入操作并更新
			result = t_pstmt.executeUpdate();
			
		} catch (SQLException e) {
			//事务回滚，不做插入操作
			e.printStackTrace();
			return 0;
		} finally {
			JdbcUtils_DBCP.release(connection2, t_pstmt, null);
		}
		String sql = "insert into " + FullTimeTeacherInfoTable.TABLE_NAME + "(" + FullTimeTeacherInfoTable.FTTI_NAME
				+ "," + FullTimeTeacherInfoTable.FTTI_WORKNUMBER + "," + FullTimeTeacherInfoTable.FTTI_GENDER + ","
				+ FullTimeTeacherInfoTable.FTTI_BIRTHDAY + "," + FullTimeTeacherInfoTable.FTTI_INSCHOOLDATE + ","
				+ FullTimeTeacherInfoTable.FTTI_WORKSTATE + "," + FullTimeTeacherInfoTable.FTTI_DEPARTMENTNUMBER + ","
				+ FullTimeTeacherInfoTable.FTTI_DEPARTMENTNAME + "," + FullTimeTeacherInfoTable.FTTI_EDUCATION + ","
				+ FullTimeTeacherInfoTable.FTTI_DEGREE + "," + FullTimeTeacherInfoTable.FTTI_EDUCATIONSOURCE + ","
				+ FullTimeTeacherInfoTable.FTTI_PROFESSIONALTITLE + "," + FullTimeTeacherInfoTable.FTTI_SUBJECTCATEGORY
				+ "," + FullTimeTeacherInfoTable.FTTI_IFDOUBLEQUALIFIEDTEACHER + ","
				+ FullTimeTeacherInfoTable.FTTI_IFENGINEERINBACKGROUND + ","
				+ FullTimeTeacherInfoTable.FTTI_IFINDUSTRYBACKGROUND + "," + FullTimeTeacherInfoTable.FTTI_TUTORTYPE
				+ "," + FullTimeTeacherInfoTable.FTTI_SERIALNUMBER + "," + FullTimeTeacherInfoTable.FTTI_DEADLINE + ","
				+ FullTimeTeacherInfoTable.FTTI_COLLEGE + "," + FullTimeTeacherInfoTable.FTTI_COMMENTS + "," + FullTimeTeacherInfoTable.FTTI_ISNULL
				+ ")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		System.out.println("添加纪录" + sql);
		Connection connection = null;
		try
		{
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1)
		{
			e1.printStackTrace();
			return -1;
		}
		PreparedStatement pstmt = null;

		try
		{
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, fullTimeTeacherInfo.getFtti_name());
			pstmt.setString(2, fullTimeTeacherInfo.getFtti_worknumber());
			pstmt.setString(3, fullTimeTeacherInfo.getFtti_gender().toString());
			pstmt.setDate(4, fullTimeTeacherInfo.getFtti_birthday());
			pstmt.setDate(5, fullTimeTeacherInfo.getFtti_inschooldate());
			pstmt.setString(6, fullTimeTeacherInfo.getFtti_workstate().toString());
			pstmt.setString(7, fullTimeTeacherInfo.getFtti_departmentnumber().toString());
			pstmt.setString(8, fullTimeTeacherInfo.getFtti_departmentname().toString());
			pstmt.setString(9, fullTimeTeacherInfo.getFtti_education().toString());
			pstmt.setString(10, fullTimeTeacherInfo.getFtti_degree().toString());
			pstmt.setString(11, fullTimeTeacherInfo.getFtti_educationsource().toString());
			pstmt.setString(12, fullTimeTeacherInfo.getFtti_professionaltitle().toString());
			pstmt.setString(13, fullTimeTeacherInfo.getFtti_subjectcategory().toString());
			pstmt.setString(14, fullTimeTeacherInfo.getFtti_ifdoublequalifiedteacher().toString());
			pstmt.setString(15, fullTimeTeacherInfo.getFtti_ifengineeringbackground().toString());
			pstmt.setString(16, fullTimeTeacherInfo.getFtti_ifindustrybackground().toString());
			pstmt.setString(17, fullTimeTeacherInfo.getFtti_tutortype().toString());
			pstmt.setInt(18, fullTimeTeacherInfo.getFtti_serialnumber());
			pstmt.setDate(19, fullTimeTeacherInfo.getFtti_deadline());
			pstmt.setString(20, fullTimeTeacherInfo.getFtti_college());
			pstmt.setString(21, fullTimeTeacherInfo.getFtti_comments());
			pstmt.setInt(22, fullTimeTeacherInfo.getFtti_isnull());
			result = pstmt.executeUpdate();
		} catch (SQLException e)
		{
			e.printStackTrace();
			result = -1;
		} finally
		{
			JdbcUtils_DBCP.release(connection, pstmt, null);
		}
		return result;
	}

	@Override
	public int alterFullTimeTeacher(Map<String, String> valueMap, String id)
	{
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(FullTimeTeacherInfoTable.FTTI_ID, id);
		int result = updateRecord(FullTimeTeacherInfoTable.TABLE_NAME, valueMap, condition);
		return result;
	}

	@Override
	public int getMaxSerialNum()
	{
		ResultSet resultSet = null;
		int result = 1;
		String sql = "select max(" + FullTimeTeacherInfoTable.FTTI_SERIALNUMBER + ") from "
				+ FullTimeTeacherInfoTable.TABLE_NAME;

		Connection connection = null;
		PreparedStatement pstmt = null;
		try
		{
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next())
			{
				result = resultSet.getInt(1);
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
			return 0;
		} finally
		{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}

		return result;
	}

	@Override
	public int getCountByWorkNumber(String workNumber, String id)
	{
		int count =  0;
		String sql = "select count(*) from " + FullTimeTeacherInfoTable.TABLE_NAME  + String.format(" where %s='%s'",FullTimeTeacherInfoTable.FTTI_WORKNUMBER,workNumber);
		if(id != null && !"".equals(id))
			sql+=String.format(" and %s!='%s'", FullTimeTeacherInfoTable.FTTI_ID, id);
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
	public int getCountByRange(String param, Date start, Date end, Map queryParams)
	{
		int count = 0;
		String sql = "select count(*) from " + FullTimeTeacherInfoTable.TABLE_NAME + String.format(" where unix_timestamp( %s ) between unix_timestamp( '%s') and unix_timestamp( '%s' ) ",param,start, end);
		if (queryParams != null && queryParams.keySet().size() != 0)
		{
			for (Object object : queryParams.keySet())
			{
				String key = object.toString();
				String value = queryParams.get(key).toString();
				sql += String.format(" and %s like  '%%%s%%'", key, value);
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
	public List<FullTimeTeacherInfo> getAll()
	{
		String sql = " select * from " + FullTimeTeacherInfoTable.TABLE_NAME + " where 1=1 " + " order by "
				+ FullTimeTeacherInfoTable.FTTI_ID;
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
		try
		{
			System.out.println(sql);
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			List<FullTimeTeacherInfo> fttiList = new ArrayList<FullTimeTeacherInfo>();
			while (resultSet.next())
			{
				int ftti_id = resultSet.getInt(FullTimeTeacherInfoTable.FTTI_ID);
				String ftti_name = resultSet.getString(FullTimeTeacherInfoTable.FTTI_NAME);
				String ftti_worknumber = resultSet.getString(FullTimeTeacherInfoTable.FTTI_WORKNUMBER);
				String ftti_gender = resultSet.getString(FullTimeTeacherInfoTable.FTTI_GENDER);
				Date temp = Date.valueOf("1800-1-1");//用于date类型的比较，等于“1800-1-1”的date字段为null
				Date ftti_birthday = resultSet.getDate(FullTimeTeacherInfoTable.FTTI_BIRTHDAY);
				if (temp.equals(ftti_birthday))
				{
					ftti_birthday = null;
				}
				Date ftti_inschooldate = resultSet.getDate(FullTimeTeacherInfoTable.FTTI_INSCHOOLDATE);
				if (temp.equals(ftti_inschooldate))
				{
					ftti_inschooldate = null;
				}
				String ftti_workstate =resultSet.getString(FullTimeTeacherInfoTable.FTTI_WORKSTATE);

				String ftti_departmentnumber = resultSet.getString(FullTimeTeacherInfoTable.FTTI_DEPARTMENTNUMBER);

				String ftti_departmentname = resultSet.getString(FullTimeTeacherInfoTable.FTTI_DEPARTMENTNAME);
				String ftti_education =resultSet.getString(FullTimeTeacherInfoTable.FTTI_EDUCATION);
				String ftti_degree = resultSet.getString(FullTimeTeacherInfoTable.FTTI_DEGREE);
				String ftti_educationsource = resultSet.getString(FullTimeTeacherInfoTable.FTTI_EDUCATIONSOURCE);
				String ftti_professionaltitle = resultSet.getString(FullTimeTeacherInfoTable.FTTI_PROFESSIONALTITLE);
				String ftti_subjectcategory = resultSet.getString(FullTimeTeacherInfoTable.FTTI_SUBJECTCATEGORY);
				String ftti_ifdoublequalifiedteacher = resultSet.getString(FullTimeTeacherInfoTable.FTTI_IFDOUBLEQUALIFIEDTEACHER);
				String ftti_ifengineeringbackground =resultSet.getString(FullTimeTeacherInfoTable.FTTI_IFENGINEERINBACKGROUND);
				String ftti_ifindustrybackground = resultSet.getString(FullTimeTeacherInfoTable.FTTI_IFINDUSTRYBACKGROUND);
				String ftti_tutortype = resultSet.getString(FullTimeTeacherInfoTable.FTTI_TUTORTYPE);
				int ftti_serialnumber = resultSet.getInt(FullTimeTeacherInfoTable.FTTI_SERIALNUMBER);
				Date ftti_deadline = resultSet.getDate(FullTimeTeacherInfoTable.FTTI_DEADLINE);
				String ftti_college = resultSet.getString(FullTimeTeacherInfoTable.FTTI_COLLEGE);
				String ftti_comments = resultSet.getString(FullTimeTeacherInfoTable.FTTI_COMMENTS);
				int ftti_isnull = resultSet.getInt(FullTimeTeacherInfoTable.FTTI_ISNULL);
				FullTimeTeacherInfo fullTimeTeacherInfo = new FullTimeTeacherInfo(ftti_id, ftti_name, ftti_worknumber,
						ftti_gender, ftti_birthday, ftti_inschooldate, ftti_workstate, ftti_departmentnumber,
						ftti_departmentname, ftti_education, ftti_degree, ftti_educationsource, ftti_professionaltitle,
						ftti_subjectcategory, ftti_ifdoublequalifiedteacher, ftti_ifengineeringbackground,
						ftti_ifindustrybackground, ftti_tutortype, ftti_serialnumber, ftti_deadline, ftti_college,
						ftti_comments, ftti_isnull);
				fttiList.add(fullTimeTeacherInfo);
			}
			return fttiList;
		} catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		} finally
		{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
	}

	@Override
	public boolean deleteByCollegeandDeadline(String college, Date deadline) throws SQLException
	{
		Connection connection = null;
		Statement stmt = null;
		try
		{
			connection = JdbcUtils_DBCP.getConnection();
			stmt = connection.createStatement();
		} catch (SQLException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
		String sql = "delete from " + FullTimeTeacherInfoTable.TABLE_NAME + " where "
				+ FullTimeTeacherInfoTable.FTTI_COLLEGE + " = '" + college + "'" + " and ftti_deadline is null ";
		System.out.println(sql);
		try
		{
			stmt.executeUpdate(sql);
		} catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		} finally
		{
			JdbcUtils_DBCP.release(connection, stmt, null);
		}
		return true;
	}

	@Override
	public int getFullTimeTeacherCountInMajorHeader(Map queryParams,Map basicMap) {
		int count = 0;
		String sql = "select count(*) from " + FullTimeTeacherInfoTable.TABLE_NAME + " where 1 = 1";
		Connection connection = null;
		try
		{
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1)
		{
			// TODO Auto-generated catch block
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
		sql += String.format(" or %s ='%s'", FullTimeTeacherInfoTable.FTTI_COLLEGE,"");
		
		sql += " and "+FullTimeTeacherInfoTable.FTTI_WORKNUMBER+" in (select distinct "+MajorInfoTable.MI_LEADERID+" from "+MajorInfoTable.TABLE_NAME+" where 1 = 1";
		if (basicMap != null)
		{
		System.out.println(basicMap);
			for (Object object : basicMap.keySet())
			{
				String key = object.toString();
				String value = basicMap.get(key).toString();
				sql += String.format(" and  %s like  '%%%s%%' ", key, value);
			}
		}
		sql+=")";
		
		System.out.println(sql);
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		try
		{
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			resultSet.next();
			count = resultSet.getInt(1);
		} catch (SQLException e)
		{
			e.printStackTrace();
			return -1;
		} finally
		{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		System.err.println(count);
		return count;
	}

	@Override
	public int getCountByRangeInMajorHeader(String param, Date start, Date end,
			Map queryParams,Map basicMap) {
		int count = 0;
		String sql = "select count(*) from " + FullTimeTeacherInfoTable.TABLE_NAME + String.format(" where unix_timestamp( %s ) between unix_timestamp( '%s') and unix_timestamp( '%s' ) ",param,start, end);
		if (queryParams != null && queryParams.keySet().size() != 0)
		{
			for (Object object : queryParams.keySet())
			{
				String key = object.toString();
				String value = queryParams.get(key).toString();
				sql += String.format(" and %s like  '%%%s%%'", key, value);
			}
		}
		sql += String.format(" or %s ='%s'", FullTimeTeacherInfoTable.FTTI_COLLEGE,"");
		sql += " and "+FullTimeTeacherInfoTable.FTTI_WORKNUMBER+" in (select distinct "+MajorInfoTable.MI_LEADERID+" from "+MajorInfoTable.TABLE_NAME+" where 1 = 1";
		if (basicMap != null)
		{
			for (Object object : basicMap.keySet())
			{
				String key = object.toString();
				String value = basicMap.get(key).toString();
				sql += String.format(" and  %s like  '%%%s%%' ", key, value);
			}
		}
		sql+=")";
		System.out.println(sql);
		
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
	public List<TeachResearchReform> getReformAndFulltime(
			Map queryParamsforReform, Map queryParamsforFulltime) {
		String sql = "select "+FullTimeTeacherInfoTable.FTTI_DEPARTMENTNUMBER+", "+
				TeachingResearchAndReformProjectTable.TRARP_LEVEL+", "+"sum("+TeachingResearchAndReformProjectTable.TRARP_FUNDS+")"+", "+FullTimeTeacherInfoTable.FTTI_DEPARTMENTNAME+", count(*) from "+TeachingResearchAndReformProjectTable.TABLE_NAME+" INNER JOIN "+
				FullTimeTeacherInfoTable.TABLE_NAME+" on "+TeachingResearchAndReformProjectTable.TABLE_NAME+"."+TeachingResearchAndReformProjectTable.TRARP_COMPERENUMBER+" = "+FullTimeTeacherInfoTable.TABLE_NAME+"."+FullTimeTeacherInfoTable.FTTI_WORKNUMBER+
				" where 1=1 ";
		//接参数,如deadline和college
		if (queryParamsforReform != null && queryParamsforReform.keySet().size() != 0)
		{
			for (Object object : queryParamsforReform.keySet())
			{
				String key = object.toString();
				String value = queryParamsforReform.get(key).toString();
				sql += String.format(" and %s like  '%%%s%%'", key, value);
			}
		}
		
		if (queryParamsforFulltime != null && queryParamsforFulltime.keySet().size() != 0)
		{
			for (Object object : queryParamsforFulltime.keySet())
			{
				String key = object.toString();
				String value = queryParamsforFulltime.get(key).toString();
				sql += String.format(" and %s like  '%%%s%%'", key, value);
			}
		}
		
		sql+=" group by "+FullTimeTeacherInfoTable.FTTI_DEPARTMENTNUMBER+", "+TeachingResearchAndReformProjectTable.TRARP_LEVEL+", "+FullTimeTeacherInfoTable.FTTI_DEPARTMENTNAME;
		
		
		
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
			return null;
		}
		System.out.println(sql);
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		List<TeachResearchReform>resultList = new ArrayList<TeachResearchReform>();
		try {
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			while (resultSet.next())
			{
				int departmentnumber = resultSet.getInt(FullTimeTeacherInfoTable.FTTI_DEPARTMENTNUMBER);
				String level = resultSet.getString(TeachingResearchAndReformProjectTable.TRARP_LEVEL);
				int projectTotalCount = resultSet.getInt(5);
				int countryCount = 0;
				int provinceCount = 0;
				if(level.equals("国家级"))
					countryCount+=projectTotalCount;
				if(level.equals("省部级"))
					provinceCount+=projectTotalCount;
				double funding = resultSet.getDouble(3);
				String unitName = resultSet.getString(FullTimeTeacherInfoTable.FTTI_DEPARTMENTNAME);
				TeachResearchReform teachResearchReform = new TeachResearchReform(departmentnumber, unitName, projectTotalCount, countryCount,provinceCount,funding,0,0,0);
				resultList.add(teachResearchReform);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return resultList;
	}

	@Override
	public List<TeachResearchReform> getAwardAndFulltime(
			Map queryParamsforAward, Map queryParamsforFulltime) {
		String sql = "select "+FullTimeTeacherInfoTable.FTTI_DEPARTMENTNUMBER+", "+
				TeachingAwardTable.TA_LEVEL+", "+FullTimeTeacherInfoTable.FTTI_DEPARTMENTNAME+", count(*) from "+TeachingAwardTable.TABLE_NAME+" INNER JOIN "+
				FullTimeTeacherInfoTable.TABLE_NAME+" on "+TeachingAwardTable.TABLE_NAME+"."+TeachingAwardTable.TA_COMPERENUMBER+" = "+FullTimeTeacherInfoTable.TABLE_NAME+"."+FullTimeTeacherInfoTable.FTTI_WORKNUMBER+
				" where 1=1 ";
		//接参数,如deadline和college
		if (queryParamsforAward != null && queryParamsforAward.keySet().size() != 0)
		{
			for (Object object : queryParamsforAward.keySet())
			{
				String key = object.toString();
				String value = queryParamsforAward.get(key).toString();
				sql += String.format(" and %s like  '%%%s%%'", key, value);
			}
		}
		
		if (queryParamsforFulltime != null && queryParamsforFulltime.keySet().size() != 0)
		{
			for (Object object : queryParamsforFulltime.keySet())
			{
				String key = object.toString();
				String value = queryParamsforFulltime.get(key).toString();
				sql += String.format(" and %s like  '%%%s%%'", key, value);
			}
		}
		
		sql+=" group by "+FullTimeTeacherInfoTable.FTTI_DEPARTMENTNUMBER+", "+TeachingAwardTable.TA_LEVEL+", "+FullTimeTeacherInfoTable.FTTI_DEPARTMENTNAME;
		
		
		
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
			return null;
		}
		System.out.println(sql);
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		List<TeachResearchReform>resultList = new ArrayList<TeachResearchReform>();
		try {
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			while (resultSet.next())
			{
				int departmentnumber = resultSet.getInt(FullTimeTeacherInfoTable.FTTI_DEPARTMENTNUMBER);
				String level = resultSet.getString(TeachingAwardTable.TA_LEVEL);
				int priceCount = resultSet.getInt(4);
				int countryPriceCount = 0;
				int provincePriceCount = 0;
				if(level.equals("国家级"))
					countryPriceCount+=priceCount;
				if(level.equals("省部级"))
					provincePriceCount+=priceCount;
				String unitName = resultSet.getString(FullTimeTeacherInfoTable.FTTI_DEPARTMENTNAME);
				TeachResearchReform teachResearchReform = new TeachResearchReform(departmentnumber, unitName, 0, 0,0,0,priceCount, countryPriceCount,provincePriceCount);
				resultList.add(teachResearchReform);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return resultList;
	}

	//根据单位号获得教师信息
	@Override
	public List<FullTimeTeacherInfo> getFullTimeTeachersByDepartNum(
			String departNum) {
		// TODO Auto-generated method stub
		String sql = " select * from " + FullTimeTeacherInfoTable.TABLE_NAME + " where " 
		            + FullTimeTeacherInfoTable.FTTI_DEPARTMENTNUMBER +" = "+departNum;
		
		Connection connection = null;
		try
		{
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		}
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		try
		{
			System.out.println(sql);
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			List<FullTimeTeacherInfo> fullTimeTeacherInfos = new ArrayList<FullTimeTeacherInfo>();

			while (resultSet.next())
			{
				Date temp = Date.valueOf("1800-1-1");//用于date类型的比较，等于“1800-1-1”的date字段为null
				int ftti_id = resultSet.getInt(FullTimeTeacherInfoTable.FTTI_ID);
				String ftti_name = resultSet.getString(FullTimeTeacherInfoTable.FTTI_NAME);
				String ftti_worknumber = resultSet.getString(FullTimeTeacherInfoTable.FTTI_WORKNUMBER);
				String ftti_gender = resultSet.getString(FullTimeTeacherInfoTable.FTTI_GENDER);
				Date ftti_birthday = resultSet.getDate(FullTimeTeacherInfoTable.FTTI_BIRTHDAY);
				if(temp.equals(ftti_birthday))
					ftti_birthday = null;
				Date ftti_inschooldate = resultSet.getDate(FullTimeTeacherInfoTable.FTTI_INSCHOOLDATE);
				if(temp.equals(ftti_inschooldate))
					ftti_inschooldate = null;
				String ftti_workstate =resultSet.getString(FullTimeTeacherInfoTable.FTTI_WORKSTATE);

				String ftti_departmentnumber = resultSet.getString(FullTimeTeacherInfoTable.FTTI_DEPARTMENTNUMBER);

				String ftti_departmentname = resultSet.getString(FullTimeTeacherInfoTable.FTTI_DEPARTMENTNAME);
				String ftti_education =resultSet.getString(FullTimeTeacherInfoTable.FTTI_EDUCATION);
				String ftti_degree = resultSet.getString(FullTimeTeacherInfoTable.FTTI_DEGREE);
				String ftti_educationsource = resultSet.getString(FullTimeTeacherInfoTable.FTTI_EDUCATIONSOURCE);
				String ftti_professionaltitle = resultSet.getString(FullTimeTeacherInfoTable.FTTI_PROFESSIONALTITLE);
				String ftti_subjectcategory = resultSet.getString(FullTimeTeacherInfoTable.FTTI_SUBJECTCATEGORY);
				String ftti_ifdoublequalifiedteacher = resultSet.getString(FullTimeTeacherInfoTable.FTTI_IFDOUBLEQUALIFIEDTEACHER);
				String ftti_ifengineeringbackground =resultSet.getString(FullTimeTeacherInfoTable.FTTI_IFENGINEERINBACKGROUND);
				String ftti_ifindustrybackground = resultSet.getString(FullTimeTeacherInfoTable.FTTI_IFINDUSTRYBACKGROUND);
				String ftti_tutortype = resultSet.getString(FullTimeTeacherInfoTable.FTTI_TUTORTYPE);
				int ftti_serialnumber = resultSet.getInt(FullTimeTeacherInfoTable.FTTI_SERIALNUMBER);
				Date ftti_deadline = resultSet.getDate(FullTimeTeacherInfoTable.FTTI_DEADLINE);
				String ftti_college = resultSet.getString(FullTimeTeacherInfoTable.FTTI_COLLEGE);
				String ftti_comments = resultSet.getString(FullTimeTeacherInfoTable.FTTI_COMMENTS);
				int ftti_isnull = resultSet.getInt(FullTimeTeacherInfoTable.FTTI_ISNULL);
				FullTimeTeacherInfo fullTimeTeacherInfo = new FullTimeTeacherInfo(ftti_id, ftti_name, ftti_worknumber,
						ftti_gender, ftti_birthday, ftti_inschooldate, ftti_workstate, ftti_departmentnumber,
						ftti_departmentname, ftti_education, ftti_degree, ftti_educationsource, ftti_professionaltitle,
						ftti_subjectcategory, ftti_ifdoublequalifiedteacher, ftti_ifengineeringbackground,
						ftti_ifindustrybackground, ftti_tutortype, ftti_serialnumber, ftti_deadline, ftti_college,
						ftti_comments, ftti_isnull);
				fullTimeTeacherInfos.add(fullTimeTeacherInfo);
			}
			return fullTimeTeacherInfos;
		} catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		} finally
		{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
	}
}
