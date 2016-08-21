package cn.edu.xmu.serviceimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.dao.StartClassDao;
import cn.edu.xmu.daoimpl.StartClassDaoImpl;
import cn.edu.xmu.entity.StartClass;
import cn.edu.xmu.entity.StartClassInfo;
import cn.edu.xmu.service.StartClassInfoService;
import cn.edu.xmu.table.StartClassTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;

/**
 * 3.8 全校课程开设情况
 * @author yue
 *
 */
public class StartClassInfoServiceImpl implements StartClassInfoService{

	StartClassDao scDao = new StartClassDaoImpl();
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<StartClassInfo> getStartClassInfo(Map params) {
		
		 String courseCategory = "";//课程类别
		 int courseCount = 0;//课程门数
		 int courseAmount = 0;//课程门次数
		 int bilingualCount = 0;//双语课程门数
		 float avgHour = 0;//平均学时数
		 float avgStudent = 0;//平均班规模(人)
		String college = "";
		Map paramsStartClass = new HashMap<>();
		if(params == null)
		{
			params = new HashMap<>();
		}
		else if(params.keySet().size() != 0){
			for(Object object:params.keySet()){
				String key = object.toString();
				String value = (String) params.get(key);
				if(key.equals("college"))
				{
					college = value;
					params.remove("college");
					paramsStartClass.put(StartClassTable.SC_COLLEGE, value);
				}
				if(key.equals("deadline"))
				{
					params.remove("deadline");
					paramsStartClass.put(StartClassTable.SC_DEADLINE, value);
				}
			}
		}
		
		List<StartClass> scs = new ArrayList<StartClass>();
		List<StartClassInfo> scis = new ArrayList<StartClassInfo>();
		 
		courseCategory = "专业课";
		paramsStartClass.put(StartClassTable.SC_COURSECATEGORY, "专业");
		courseCount = getCourseAmountDistinct(paramsStartClass);
		courseAmount = getCourseAmount(paramsStartClass);
		
		scs = scDao.getStartClass(0, courseAmount, StartClassTable.SC_ID, "asc", paramsStartClass, null);
		paramsStartClass.put(StartClassTable.SC_ISENGLISH, "双语授课");
		bilingualCount = scDao.getClassTeacherCount(paramsStartClass);
		paramsStartClass.remove(StartClassTable.SC_ISENGLISH);
		
		int totalHour = 0;
		int totalStudent = 0;
		for(StartClass sc:scs)
		{ 
			if(sc.getSc_totalhour() != null || !"".equals(sc.getSc_totalhour()))
				totalHour += sc.getSc_totalhour();
			if(sc.getSc_studentnum() != null || !"".equals(sc.getSc_studentnum()))
				totalStudent += sc.getSc_studentnum();
		}
		if(courseAmount != 0)
		{
			avgHour = (float)totalHour/courseAmount;
			avgStudent = (float)totalStudent/courseAmount;
		}
		
		StartClassInfo sci = new StartClassInfo(courseCategory, courseCount, courseAmount, bilingualCount, avgHour, avgStudent,college);
		scis.add(sci);
		paramsStartClass.remove(StartClassTable.SC_COURSECATEGORY);
		
		
		
		
		courseCategory = "公共必修课";
		courseCount = 0;//课程门数
		courseAmount = 0;//课程门次数
		bilingualCount = 0;//双语课程门数
		avgHour = 0;//平均学时数
		avgStudent = 0;//平均班规模(人)
		paramsStartClass.put(StartClassTable.SC_COURSECATEGORY, "公共必修课");
		courseCount = getCourseAmountDistinct(paramsStartClass);
		courseAmount = getCourseAmount(paramsStartClass);
		
		
		scs = scDao.getStartClass(0, courseAmount, StartClassTable.SC_ID, "asc", paramsStartClass, null);
		paramsStartClass.put(StartClassTable.SC_ISENGLISH, "双语授课");
		bilingualCount = scDao.getClassTeacherCount(paramsStartClass);
		paramsStartClass.remove(StartClassTable.SC_ISENGLISH);
		
		totalHour = 0;
		totalStudent = 0;
		for(StartClass sc:scs)
		{
			if(sc.getSc_totalhour() != null || !"".equals(sc.getSc_totalhour()))
				totalHour += sc.getSc_totalhour();
			if(sc.getSc_studentnum() != null || !"".equals(sc.getSc_studentnum()))
				totalStudent += sc.getSc_studentnum();
		}
		if(courseAmount != 0)
		{
			avgHour = (float)totalHour/courseAmount;
			avgStudent = (float)totalStudent/courseAmount;
		}
		
		sci = new StartClassInfo(courseCategory, courseCount, courseAmount, bilingualCount, avgHour, avgStudent,college);
		scis.add(sci);
		paramsStartClass.remove(StartClassTable.SC_COURSECATEGORY);
		
		
		
		courseCategory = "公共选修课";
		courseCount = 0;//课程门数
		courseAmount = 0;//课程门次数
		bilingualCount = 0;//双语课程门数
		avgHour = 0;//平均学时数
		avgStudent = 0;//平均班规模(人)
		paramsStartClass.put(StartClassTable.SC_COURSECATEGORY, "公共选修课");
		courseCount = getCourseAmountDistinct(paramsStartClass);
		courseAmount = getCourseAmount(paramsStartClass);		
		
		scs = scDao.getStartClass(0, courseAmount, StartClassTable.SC_ID, "asc", paramsStartClass, null);
		paramsStartClass.put(StartClassTable.SC_ISENGLISH, "双语授课");
		bilingualCount = scDao.getClassTeacherCount(paramsStartClass);
		paramsStartClass.remove(StartClassTable.SC_ISENGLISH);
		
		totalHour = 0;
		totalStudent = 0;
		for(StartClass sc:scs)
		{
			if(sc.getSc_totalhour() != null || !"".equals(sc.getSc_totalhour()))
				totalHour += sc.getSc_totalhour();
			if(sc.getSc_studentnum() != null || !"".equals(sc.getSc_studentnum()))
				totalStudent += sc.getSc_studentnum();
		}
		if(courseAmount != 0)
		{
			avgHour = (float)totalHour/courseAmount;
			avgStudent = (float)totalStudent/courseAmount;
		}
		
		sci = new StartClassInfo(courseCategory, courseCount, courseAmount, bilingualCount, avgHour, avgStudent,college);
		scis.add(sci);
		paramsStartClass.remove(StartClassTable.SC_COURSECATEGORY);
		
		return scis;
		
	}

	/**
	 * 课程门数 = 表5-1-1中此类课程编号数（查重）
	 * @param queryParams
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private int getCourseAmountDistinct(Map queryParams)
	{
		int result = 0;
		String sql1 = "select count(DISTINCT  "+StartClassTable.SC_COURSENUM +") from " + StartClassTable.TABLE_NAME
				+ " where 1 = 1";
		Connection connection1 = null;
		try {
			connection1 = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PreparedStatement pstmt1 = null;
		ResultSet resultSet1 = null;
		if (queryParams != null)
		{
			for (Object object : queryParams.keySet())
			{
				String key = object.toString();
				String value = queryParams.get(key).toString();
				sql1 += String.format(" and  %s like  '%%%s%%' ", key, value);
			}
			// sql += String.format(" or %s ='%s'", "college","");
		}
		
		try {
			pstmt1 = connection1.prepareStatement(sql1);
			resultSet1 = pstmt1.executeQuery();
			resultSet1.next();
			result = resultSet1.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} finally{
			JdbcUtils_DBCP.release(connection1, pstmt1, resultSet1);
		}
		return result;
	}
	/**
	 * 课程门次数 =表5-1-1中此类课程编号数（不查重）
	 * @param queryParams
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private int getCourseAmount(Map queryParams)
	{
		int result = 0;
		String sql1 = "select count( "+StartClassTable.SC_COURSENUM +") from " + StartClassTable.TABLE_NAME
				+ " where 1 = 1";
		Connection connection1 = null;
		try {
			connection1 = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PreparedStatement pstmt1 = null;
		ResultSet resultSet1 = null;
		if (queryParams != null)
		{
			for (Object object : queryParams.keySet())
			{
				String key = object.toString();
				String value = queryParams.get(key).toString();
				sql1 += String.format(" and  %s like  '%%%s%%' ", key, value);
			}
			// sql += String.format(" or %s ='%s'", "college","");
		}
		
		try {
			pstmt1 = connection1.prepareStatement(sql1);
			resultSet1 = pstmt1.executeQuery();
			resultSet1.next();
			result = resultSet1.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} finally{
			JdbcUtils_DBCP.release(connection1, pstmt1, resultSet1);
		}
		return result;
	}
}
