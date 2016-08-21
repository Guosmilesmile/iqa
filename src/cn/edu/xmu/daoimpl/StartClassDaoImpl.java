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

import cn.edu.xmu.dao.StartClassDao;
import cn.edu.xmu.entity.MajorTeacherStructureTemp;
import cn.edu.xmu.entity.StartClass;
import cn.edu.xmu.table.ExternalTeacherTable;
import cn.edu.xmu.table.ForeignExchangeTable;
import cn.edu.xmu.table.FullTimeTeacherInfoTable;
import cn.edu.xmu.table.MajorTeachTable;
import cn.edu.xmu.table.OtherTeacherInfoTable;
import cn.edu.xmu.table.StartClassTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;

/**
 * 5-1-1-1 开课情况表
 * @author chunfeng
 *
 */
public class StartClassDaoImpl extends BaseDaoImpl<StartClass>
		implements StartClassDao {

	@Override
	public List<StartClass> getStartClass(int start, int end,
			String sortStr, String orderStr, Map<String, String> params, Date deadline) {

		String sql = " select tmp.* from ( " + " select * from "
				+ StartClassTable.TABLE_NAME + " where 1=1 ";
		if (deadline != null) {
			sql += String.format("and "+StartClassTable.SC_DEADLINE+" like  '%s%%' ", deadline);
		}
		if (!(params == null || params.keySet().size() == 0)) {
			for (Object object : params.keySet()) {

				String key = object.toString();
				String value = params.get(key).toString();
				sql += String.format("and %s like  '%%%s%%' ", key, value);
			}
		}
		
		if (sortStr == "nope") {
			
		}else{
			sql += " order by " + sortStr + " " + orderStr + " ) tmp limit "
					+ start + " ," + end;
		}

		

		System.out.println(sql);

		List<StartClass> startClasses = new ArrayList<StartClass>();
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
				int sc_id = resultSet.getInt(StartClassTable.SC_ID);
			    
				String sc_number = resultSet.getString(StartClassTable.SC_NUMBER); //开课号
				String sc_coursenum = resultSet.getString(StartClassTable.SC_COURSENUM); //课程号
				String sc_coursecategory = resultSet.getString(StartClassTable.SC_COURSECATEGORY); //课程类别
				String sc_campus = resultSet.getString(StartClassTable.SC_CAMPUS); //校区
				Integer sc_totalhour = resultSet.getInt(StartClassTable.SC_TOTALHOUR); //总学时
				if(sc_totalhour == -1) sc_totalhour = null;
				Float sc_totalcredit = resultSet.getFloat(StartClassTable.SC_TOTALCREDIT); //学分
				if(sc_totalcredit == -1.0) sc_totalcredit = null;
				String sc_evaluationmode = resultSet.getString(StartClassTable.SC_EVALUATIONMODE); //考核方式
				String sc_teachobject = resultSet.getString(StartClassTable.SC_TEACHOBJECT); //授课对象
				String sc_arrange = resultSet.getString(StartClassTable.SC_ARRANGE); //安排情况
				String sc_yearandsemester = resultSet.getString(StartClassTable.SC_YEARANDSEMESTER); //学年学期
				String sc_collegename = resultSet.getString(StartClassTable.SC_COLLEGENAME); //授课院
				String sc_coursename = resultSet.getString(StartClassTable.SC_COURSENAME); //课程名称
				String sc_teacher = resultSet.getString(StartClassTable.SC_TEACHER); //授课教师
				String sc_isoutsideteacher = resultSet.getString(StartClassTable.SC_ISOUTSIDETEACHER); //是否校外专家
				String sc_teachernumber = resultSet.getString(StartClassTable.SC_TEACHERNUMBER); //授课教师工号
				String sc_teachertitle = resultSet.getString(StartClassTable.SC_TEACHERTITLE); //职称
				Integer sc_studentnum = resultSet.getInt(StartClassTable.SC_STUDENTNUM); //本科学生数
				if(sc_studentnum == -1) sc_studentnum = null;
				String sc_isenglish = resultSet.getString(StartClassTable.SC_ISENGLISH); //英语授课情况
				String sc_website = resultSet.getString(StartClassTable.SC_WEBSITE); //网络教学平台网站
				String sc_teachmaterial = resultSet.getString(StartClassTable.SC_TEACHMATERIAL); //教材使用情况
				Integer sc_materialspecies = resultSet.getInt(StartClassTable.SC_MATERIALSPECIES); //使用教材种数
				if(sc_materialspecies == -1) sc_materialspecies = null;
				String sc_ismagong = resultSet.getString(StartClassTable.SC_ISMAGONG); //是否使用马工教材
				String sc_isstandard = resultSet.getString(StartClassTable.SC_ISSTANDARD); //是否使用规划教材
				String sc_foreignmaterial = resultSet.getString(StartClassTable.SC_FOREIGNMATERIAL); //境外教材使用情况
				String sc_m_name = resultSet.getString(StartClassTable.SC_M_NAME); //教材名称
				String sc_m_auther = resultSet.getString(StartClassTable.SC_M_AUTHER);//作者
				String sc_m_publisher = resultSet.getString(StartClassTable.SC_M_PUBLISHER); //出版社
			    String sc_m_country = resultSet.getString(StartClassTable.SC_M_COUNTRY); //所属国家
				Integer sc_m_publishyear = resultSet.getInt(StartClassTable.SC_M_PUBLISHYEAR); //出版年
				if(sc_m_publishyear == -1) sc_m_publishyear = null;
				    
				String sc_college = resultSet.getString(StartClassTable.SC_COLLEGE); 
			    int sc_serialnumber = resultSet.getInt(StartClassTable.SC_SERIALNUMBER);//序列号
			  	Date sc_deadline = resultSet.getDate(StartClassTable.SC_DEADLINE);//截止日期
			  	String sc_comments = resultSet.getString(StartClassTable.SC_COMMENTS);//审核意见
				if(null == sc_comments){
					sc_comments = "";
				}
				int sc_isnull = resultSet.getInt(StartClassTable.SC_ISNULL);
				StartClass startClass = new StartClass(
						sc_id, sc_number, sc_coursenum, sc_coursecategory, sc_campus, sc_totalhour, sc_totalcredit, sc_evaluationmode, 
						sc_teachobject, sc_arrange, sc_yearandsemester, sc_collegename, sc_coursename, sc_teacher, sc_isoutsideteacher, sc_teachernumber,
				        sc_teachertitle, sc_studentnum, sc_isenglish, sc_website, sc_teachmaterial, sc_materialspecies, sc_ismagong, sc_isstandard, 
				        sc_foreignmaterial, sc_m_name, sc_m_auther, sc_m_publisher, sc_m_country, sc_m_publishyear, sc_college, sc_deadline, sc_serialnumber, sc_comments, sc_isnull);

				startClasses.add(startClass);
			}
			return startClasses;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}

	}

	@Override
	public int getStartClassCount(Map queryParams) {
		int count = 0;
		String sql = "select count(*) from " + StartClassTable.TABLE_NAME
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
	public boolean batchDelete(String[] scids) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();

		for (String scid : scids) {
			String sql = "delete from " + StartClassTable.TABLE_NAME
					+ " where " + StartClassTable.SC_ID + " = '" + scid
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
	public int addStartClass(StartClass StartClass) {
		int result = 0;

		String sql2 = "update " + StartClassTable.TABLE_NAME + " set "
				+ StartClassTable.SC_SERIALNUMBER + " = "
				+ StartClassTable.SC_SERIALNUMBER + " +1 where "
				+ StartClassTable.SC_SERIALNUMBER + ">="
				+ StartClass.getSc_serialnumber();
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

		String sql = "insert into " + StartClassTable.TABLE_NAME + "("
				+ StartClassTable.SC_NUMBER + "," //开课号
				+ StartClassTable.SC_COURSENUM + "," //课程号
				+ StartClassTable.SC_COURSECATEGORY + "," //课程类别
				+ StartClassTable.SC_CAMPUS + "," //校区
				+ StartClassTable.SC_TOTALHOUR + "," //总学时
				+ StartClassTable.SC_TOTALCREDIT + "," //学分
				+ StartClassTable.SC_EVALUATIONMODE + "," //考核方式
				+ StartClassTable.SC_TEACHOBJECT + "," //授课对象
				+ StartClassTable.SC_ARRANGE + "," //安排情况
				+ StartClassTable.SC_YEARANDSEMESTER + "," //学年学期
				+ StartClassTable.SC_COLLEGENAME + "," //授课院
				+ StartClassTable.SC_COURSENAME + "," //课程名称
				+ StartClassTable.SC_TEACHER + "," //授课教师
				+ StartClassTable.SC_ISOUTSIDETEACHER + "," //是否校外专家
				+ StartClassTable.SC_TEACHERNUMBER + "," //授课教师工号
				+ StartClassTable.SC_TEACHERTITLE + "," //职称
				+ StartClassTable.SC_STUDENTNUM + "," //本科学生数
				+ StartClassTable.SC_ISENGLISH + "," //英语授课情况
				+ StartClassTable.SC_WEBSITE + "," //网络教学平台网站
				+ StartClassTable.SC_TEACHMATERIAL + "," //教材使用情况
				+ StartClassTable.SC_MATERIALSPECIES + "," //使用教材种数
				+ StartClassTable.SC_ISMAGONG + "," //是否使用马工教材
				+ StartClassTable.SC_ISSTANDARD + "," //是否使用规划教材
				+ StartClassTable.SC_FOREIGNMATERIAL + "," //境外教材使用情况
				+ StartClassTable.SC_M_NAME + "," //教材名称
				+ StartClassTable.SC_M_AUTHER + ","//作者
				+ StartClassTable.SC_M_PUBLISHER + "," //出版社
				+ StartClassTable.SC_M_COUNTRY + "," //所属国家
				+ StartClassTable.SC_M_PUBLISHYEAR + "," //出版年
				+ StartClassTable.SC_COLLEGE + "," 
				+ StartClassTable.SC_SERIALNUMBER + ","
				+ StartClassTable.SC_ISNULL +")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

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
			pstmt.setString(1, StartClass.getSc_number());
			pstmt.setString(2, StartClass.getSc_coursenum());
			pstmt.setString(3, StartClass.getSc_coursecategory());
			pstmt.setString(4, StartClass.getSc_campus());
			pstmt.setInt(5, StartClass.getSc_totalhour());
			pstmt.setFloat(6, StartClass.getSc_totalcredit());
			pstmt.setString(7, StartClass.getSc_evaluationmode());
			pstmt.setString(8, StartClass.getSc_teachobject());
			pstmt.setString(9, StartClass.getSc_arrange());
			pstmt.setString(10, StartClass.getSc_yearandsemester());
			pstmt.setString(11, StartClass.getSc_collegename());
			pstmt.setString(12, StartClass.getSc_coursename());
			pstmt.setString(13, StartClass.getSc_teacher());
			pstmt.setString(14, StartClass.getSc_isoutsideteacher());
			pstmt.setString(15, StartClass.getSc_teachernumber());
			pstmt.setString(16, StartClass.getSc_teachertitle());
			pstmt.setInt(17, StartClass.getSc_studentnum());
			pstmt.setString(18, StartClass.getSc_isenglish());
			pstmt.setString(19, StartClass.getSc_website());
			pstmt.setString(20, StartClass.getSc_teachmaterial());
			pstmt.setInt(21, StartClass.getSc_materialspecies());
			pstmt.setString(22, StartClass.getSc_ismagong());
			pstmt.setString(23, StartClass.getSc_isstandard());
			pstmt.setString(24, StartClass.getSc_foreignmaterial());
			pstmt.setString(25, StartClass.getSc_m_name());
			pstmt.setString(26, StartClass.getSc_m_auther());
			pstmt.setString(27, StartClass.getSc_m_publisher());
			pstmt.setString(28, StartClass.getSc_m_country());
			pstmt.setInt(29, StartClass.getSc_m_publishyear());
			pstmt.setString(30, StartClass.getSc_college());
			pstmt.setInt(31, StartClass.getSc_serialnumber());
			pstmt.setInt(32, StartClass.getSc_isnull());
			
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
	public int alterStartClass(Map<String, String> valueMap, String id) {
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(StartClassTable.SC_ID, id);
		int result = updateRecord(StartClassTable.TABLE_NAME, valueMap,
				condition);
		return result;
	}

	@Override
	public List<StartClass> getAllStartClasss() {
		String sql = " select * from " + StartClassTable.TABLE_NAME
				+ " where 1=1 " + " order by " + StartClassTable.SC_ID;
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
			List<StartClass> startClassList = new ArrayList<StartClass>();
			while (resultSet.next()) {
                int sc_id = resultSet.getInt(StartClassTable.SC_ID);
			    
				String sc_number = resultSet.getString(StartClassTable.SC_NUMBER); //开课号
				String sc_coursenum = resultSet.getString(StartClassTable.SC_COURSENUM); //课程号
				String sc_coursecategory = resultSet.getString(StartClassTable.SC_COURSECATEGORY); //课程类别
				String sc_campus = resultSet.getString(StartClassTable.SC_CAMPUS); //校区
				Integer sc_totalhour = resultSet.getInt(StartClassTable.SC_TOTALHOUR); //总学时
				if(sc_totalhour == -1) sc_totalhour = null;
				Float sc_totalcredit = resultSet.getFloat(StartClassTable.SC_TOTALCREDIT); //学分
				if(sc_totalcredit == -1.0) sc_totalcredit = null;
				String sc_evaluationmode = resultSet.getString(StartClassTable.SC_EVALUATIONMODE); //考核方式
				String sc_teachobject = resultSet.getString(StartClassTable.SC_TEACHOBJECT); //授课对象
				String sc_arrange = resultSet.getString(StartClassTable.SC_ARRANGE); //安排情况
				String sc_yearandsemester = resultSet.getString(StartClassTable.SC_YEARANDSEMESTER); //学年学期
				String sc_collegename = resultSet.getString(StartClassTable.SC_COLLEGENAME); //授课院
				String sc_coursename = resultSet.getString(StartClassTable.SC_COURSENAME); //课程名称
				String sc_teacher = resultSet.getString(StartClassTable.SC_TEACHER); //授课教师
				String sc_isoutsideteacher = resultSet.getString(StartClassTable.SC_ISOUTSIDETEACHER); //是否校外专家
				String sc_teachernumber = resultSet.getString(StartClassTable.SC_TEACHERNUMBER); //授课教师工号
				String sc_teachertitle = resultSet.getString(StartClassTable.SC_TEACHERTITLE); //职称
				Integer sc_studentnum = resultSet.getInt(StartClassTable.SC_STUDENTNUM); //本科学生数
				if(sc_studentnum == -1) sc_studentnum = null;
				String sc_isenglish = resultSet.getString(StartClassTable.SC_ISENGLISH); //英语授课情况
				String sc_website = resultSet.getString(StartClassTable.SC_WEBSITE); //网络教学平台网站
				String sc_teachmaterial = resultSet.getString(StartClassTable.SC_TEACHMATERIAL); //教材使用情况
				Integer sc_materialspecies = resultSet.getInt(StartClassTable.SC_MATERIALSPECIES); //使用教材种数
				if(sc_materialspecies == -1) sc_materialspecies = null;
				String sc_ismagong = resultSet.getString(StartClassTable.SC_ISMAGONG); //是否使用马工教材
				String sc_isstandard = resultSet.getString(StartClassTable.SC_ISSTANDARD); //是否使用规划教材
				String sc_foreignmaterial = resultSet.getString(StartClassTable.SC_FOREIGNMATERIAL); //境外教材使用情况
				String sc_m_name = resultSet.getString(StartClassTable.SC_M_NAME); //教材名称
				String sc_m_auther = resultSet.getString(StartClassTable.SC_M_AUTHER);//作者
				String sc_m_publisher = resultSet.getString(StartClassTable.SC_M_PUBLISHER); //出版社
			    String sc_m_country = resultSet.getString(StartClassTable.SC_M_COUNTRY); //所属国家
				Integer sc_m_publishyear = resultSet.getInt(StartClassTable.SC_M_PUBLISHYEAR); //出版年
				if(sc_m_publishyear == -1) sc_m_publishyear = null;
				    
				String sc_college = resultSet.getString(StartClassTable.SC_COLLEGE);
			    int sc_serialnumber = resultSet.getInt(StartClassTable.SC_SERIALNUMBER);//序列号
			  	Date sc_deadline = resultSet.getDate(StartClassTable.SC_DEADLINE);//截止日期
			  	String sc_comments = resultSet.getString(StartClassTable.SC_COMMENTS);//审核意见
				if(null == sc_comments){
					sc_comments = "";
				}
				int sc_isnull = resultSet.getInt(StartClassTable.SC_ISNULL);
				StartClass startClass = new StartClass(
						sc_id, sc_number, sc_coursenum, sc_coursecategory, sc_campus, sc_totalhour, sc_totalcredit, sc_evaluationmode, 
						sc_teachobject, sc_arrange, sc_yearandsemester, sc_collegename, sc_coursename, sc_teacher, sc_isoutsideteacher, sc_teachernumber,
				        sc_teachertitle, sc_studentnum, sc_isenglish, sc_website, sc_teachmaterial, sc_materialspecies, sc_ismagong, sc_isstandard, 
				        sc_foreignmaterial, sc_m_name, sc_m_auther, sc_m_publisher, sc_m_country, sc_m_publishyear, sc_college,sc_deadline, sc_serialnumber, sc_comments, sc_isnull);


				startClassList.add(startClass);
			}
			return startClassList;
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

		String sql = "delete from " + StartClassTable.TABLE_NAME;
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
		String sql = "delete from " + StartClassTable.TABLE_NAME
				+ " where " + StartClassTable.SC_COLLEGE + " = '" + college + "'" +" and sc_deadline is null ";
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
	public int getClassCountDistinct(Map queryParams){
		int count = 0;
		String sql = "select count(DISTINCT "+StartClassTable.SC_COURSENUM +") from " + StartClassTable.TABLE_NAME
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
	public int getClassTeacherCount(Map queryParams) {
		int count = 0;
		String sql = "select count(DISTINCT "+StartClassTable.SC_TEACHERNUMBER +") from " + StartClassTable.TABLE_NAME
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
	public List<MajorTeacherStructureTemp> getMajorTeacherStructureFormFTTIAll(Map queryParamsforMT, Map queryParamsforSC, Map queryParamsforFTTI) {
		/*SELECT mt_majornameinschool, sc_teachertitle, ftti_degree, count(*) FROM (SELECT mt_majornameinschool, sc_teachertitle, 
		sc_teachernumber FROM sc_startclass INNER JOIN mt_majorteach ON sc_startclass.sc_coursenum = mt_majorteach.mt_coursecode 
				WHERE 1=1 (此处接两个表的参数)GROUP BY mt_majornameinschool, sc_teachertitle, sc_teachernumber)tmp INNER JOIN ftti_fulltimeteacherinfo 
				WHERE tmp.sc_teachernumber = ftti_fulltimeteacherinfo.ftti_worknumber (此处接剩余一个表的参数) GROUP BY mt_majornameinschool, sc_teachertitle, ftti_degree*/
		//获得MajorTeacherStructureFTTI不包括低年级教师的数据
		//group teachernumber用于筛选一个老师开了多门同一个专业的课程
		//要注意一个老师有可能开多个专业的多门课
		//注意deadline is null
		String sql = "select "+MajorTeachTable.MT_MAJORNAMEINSCHOOL+", "+StartClassTable.SC_TEACHERTITLE+", "+FullTimeTeacherInfoTable.FTTI_DEGREE+", count(*) from (select "+
				MajorTeachTable.MT_MAJORNAMEINSCHOOL+", "+StartClassTable.SC_TEACHERTITLE+", "+StartClassTable.SC_TEACHERNUMBER+" from "+StartClassTable.TABLE_NAME+" INNER JOIN "+
				MajorTeachTable.TABLE_NAME+" on "+StartClassTable.TABLE_NAME+"."+StartClassTable.SC_COURSENUM+" = "+MajorTeachTable.TABLE_NAME+"."+MajorTeachTable.MT_COURSECODE+
				" where 1=1 ";
		//接mt和sc表的参数,如deadline和college
		if (queryParamsforMT != null && queryParamsforMT.keySet().size() != 0)
		{
			for (Object object : queryParamsforMT.keySet())
			{
				String key = object.toString();
				String value = queryParamsforMT.get(key).toString();
				sql += String.format(" and %s like  '%%%s%%'", key, value);
			}
		}
		
		if (queryParamsforSC != null && queryParamsforSC.keySet().size() != 0)
		{
			for (Object object : queryParamsforSC.keySet())
			{
				String key = object.toString();
				String value = queryParamsforSC.get(key).toString();
				sql += String.format(" and %s like  '%%%s%%'", key, value);
			}
		}
		sql+=" group by "+MajorTeachTable.MT_MAJORNAMEINSCHOOL+", "+StartClassTable.SC_TEACHERTITLE+", "+StartClassTable.SC_TEACHERNUMBER+")tmp inner join "+FullTimeTeacherInfoTable.TABLE_NAME+
				" where tmp."+StartClassTable.SC_TEACHERNUMBER+" = "+FullTimeTeacherInfoTable.TABLE_NAME+"."+FullTimeTeacherInfoTable.FTTI_WORKNUMBER;
		
		if (queryParamsforFTTI != null && queryParamsforFTTI.keySet().size() != 0)
		{
			for (Object object : queryParamsforFTTI.keySet())
			{
				String key = object.toString();
				String value = queryParamsforFTTI.get(key).toString();
				sql += String.format(" and %s like  '%%%s%%'", key, value);
			}
		}
		sql+=" group by "+MajorTeachTable.MT_MAJORNAMEINSCHOOL+", "+StartClassTable.SC_TEACHERTITLE+", "+FullTimeTeacherInfoTable.FTTI_DEGREE;
		
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

		List<MajorTeacherStructureTemp>resultList = new ArrayList<MajorTeacherStructureTemp>();
		try {
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			while (resultSet.next())
			{
				String subject = resultSet.getString(MajorTeachTable.MT_MAJORNAMEINSCHOOL);
				String title = resultSet.getString(StartClassTable.SC_TEACHERTITLE);
				String degree = resultSet.getString(FullTimeTeacherInfoTable.FTTI_DEGREE);
				int count = resultSet.getInt(4);
				MajorTeacherStructureTemp majorTeacherStructureTemp = new MajorTeacherStructureTemp(subject, title, degree, count);
				resultList.add(majorTeacherStructureTemp);
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
	public Map getMajorLowCountFormFTTI(Map queryParamsforMT, Map queryParamsforSC, Map queryParamsforFTTI)
	{
		/*SELECT mt_majornameinschool, ftti_degree, count(*) FROM (SELECT mt_majornameinschool, sc_teachernumber FROM sc_startclass INNER JOIN mt_majorteach ON sc_startclass.sc_coursenum = 
		 * mt_majorteach.mt_coursecode WHERE sc_teachertitle = '教授' AND (sc_teachobject like '%2013%' or sc_teachobject like '%2014%' ) GROUP BY mt_majornameinschool, sc_teachernumber)tmp 
		 * INNER JOIN ftti_fulltimeteacherinfo WHERE tmp.sc_teachernumber = ftti_fulltimeteacherinfo.ftti_worknumber GROUP BY mt_majornameinschool, ftti_degree*/
		//获得MajorTeacherStructure其中每个专业有的低年级教授!的数据
		String sql = "select "+MajorTeachTable.MT_MAJORNAMEINSCHOOL+", count(*) from (select "+MajorTeachTable.MT_MAJORNAMEINSCHOOL+", "+StartClassTable.SC_TEACHERNUMBER+
				" from "+StartClassTable.TABLE_NAME+" inner join "+MajorTeachTable.TABLE_NAME+" on "+StartClassTable.TABLE_NAME+"."+StartClassTable.SC_COURSENUM+" = "+MajorTeachTable.TABLE_NAME+"."+MajorTeachTable.MT_COURSECODE+
				" where "+StartClassTable.SC_TEACHERTITLE+" = '教授'";
		String degree1 = "";
		String degree2 = "";
		if (queryParamsforSC != null && queryParamsforSC.keySet().size() != 0)
		{
			for (Object object : queryParamsforSC.keySet())
			{
				String key = object.toString();
				if(key.equals("2nddegree"))
				{
					degree1 = queryParamsforSC.get(key).toString();
				}
				else if(key.equals("1stdegree"))
				{
					degree2 = queryParamsforSC.get(key).toString();
				}
				else {
					String value = queryParamsforSC.get(key).toString();
					sql += String.format(" and %s like  '%%%s%%'", key, value);
				}
			}
		}
		
		if (queryParamsforMT != null && queryParamsforMT.keySet().size() != 0)
		{
			for (Object object : queryParamsforMT.keySet())
			{
				String key = object.toString();
				String value = queryParamsforMT.get(key).toString();
				sql += String.format(" and %s like  '%%%s%%'", key, value);
			}
		}
		
		sql+=" and ("+StartClassTable.SC_TEACHOBJECT+" like '%"+degree1+"%' or "+StartClassTable.SC_TEACHOBJECT+" like '%"+degree2+"%' )";
		
		sql+=" group by "+MajorTeachTable.MT_MAJORNAMEINSCHOOL+", "+StartClassTable.SC_TEACHERNUMBER+")tmp inner join "+FullTimeTeacherInfoTable.TABLE_NAME+" where tmp."+StartClassTable.SC_TEACHERNUMBER+" = "+
				FullTimeTeacherInfoTable.TABLE_NAME+"."+FullTimeTeacherInfoTable.FTTI_WORKNUMBER;
		
		if (queryParamsforFTTI != null && queryParamsforFTTI.keySet().size() != 0)
		{
			for (Object object : queryParamsforFTTI.keySet())
			{
				String key = object.toString();
				String value = queryParamsforFTTI.get(key).toString();
				sql += String.format(" and %s like  '%%%s%%'", key, value);
			}
		}
		sql+=" group by "+MajorTeachTable.MT_MAJORNAMEINSCHOOL;
		
		System.out.println(sql);
		Map resultMap = new HashMap();
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
		try {
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			while (resultSet.next())
			{
				resultMap.put(resultSet.getString(MajorTeachTable.MT_MAJORNAMEINSCHOOL), resultSet.getInt(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return resultMap;
	}
	
	//***********************et
	@Override
	public List<MajorTeacherStructureTemp> getMajorTeacherStructureFormETAll(Map queryParamsforMT, Map queryParamsforSC, Map queryParamsforET) {
		String sql = "select "+MajorTeachTable.MT_MAJORNAMEINSCHOOL+", "+StartClassTable.SC_TEACHERTITLE+", "+ExternalTeacherTable.ET_TOPEDUCATION+", count(*) from (select "+
				MajorTeachTable.MT_MAJORNAMEINSCHOOL+", "+StartClassTable.SC_TEACHERTITLE+", "+StartClassTable.SC_TEACHERNUMBER+" from "+StartClassTable.TABLE_NAME+" INNER JOIN "+
				MajorTeachTable.TABLE_NAME+" on "+StartClassTable.TABLE_NAME+"."+StartClassTable.SC_COURSENUM+" = "+MajorTeachTable.TABLE_NAME+"."+MajorTeachTable.MT_COURSECODE+
				" where 1=1 ";
		//接mt和sc表的参数,如deadline和college
		if (queryParamsforMT != null && queryParamsforMT.keySet().size() != 0)
		{
			for (Object object : queryParamsforMT.keySet())
			{
				String key = object.toString();
				String value = queryParamsforMT.get(key).toString();
				sql += String.format(" and %s like  '%%%s%%'", key, value);
			}
		}
		
		if (queryParamsforSC != null && queryParamsforSC.keySet().size() != 0)
		{
			for (Object object : queryParamsforSC.keySet())
			{
				String key = object.toString();
				String value = queryParamsforSC.get(key).toString();
				sql += String.format(" and %s like  '%%%s%%'", key, value);
			}
		}
		sql+=" group by "+MajorTeachTable.MT_MAJORNAMEINSCHOOL+", "+StartClassTable.SC_TEACHERTITLE+", "+StartClassTable.SC_TEACHERNUMBER+")tmp inner join "+ExternalTeacherTable.TABLE_NAME+
				" where tmp."+StartClassTable.SC_TEACHERNUMBER+" = "+ExternalTeacherTable.TABLE_NAME+"."+ExternalTeacherTable.ET_WORKNUMBER;
		
		if (queryParamsforET != null && queryParamsforET.keySet().size() != 0)
		{
			for (Object object : queryParamsforET.keySet())
			{
				String key = object.toString();
				String value = queryParamsforET.get(key).toString();
				sql += String.format(" and %s like  '%%%s%%'", key, value);
			}
		}
		sql+=" group by "+MajorTeachTable.MT_MAJORNAMEINSCHOOL+", "+StartClassTable.SC_TEACHERTITLE+", "+ExternalTeacherTable.ET_TOPEDUCATION;
		
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

		List<MajorTeacherStructureTemp>resultList = new ArrayList<MajorTeacherStructureTemp>();
		try {
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			while (resultSet.next())
			{
				String subject = resultSet.getString(MajorTeachTable.MT_MAJORNAMEINSCHOOL);
				String title = resultSet.getString(StartClassTable.SC_TEACHERTITLE);
				String degree = resultSet.getString(ExternalTeacherTable.ET_TOPEDUCATION);
				int count = resultSet.getInt(4);
				MajorTeacherStructureTemp majorTeacherStructureTemp = new MajorTeacherStructureTemp(subject, title, degree, count);
				resultList.add(majorTeacherStructureTemp);
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
	public Map getMajorLowCountFormET(Map queryParamsforMT, Map queryParamsforSC, Map queryParamsforET)
	{
		String sql = "select "+MajorTeachTable.MT_MAJORNAMEINSCHOOL+", count(*) from (select "+MajorTeachTable.MT_MAJORNAMEINSCHOOL+", "+StartClassTable.SC_TEACHERNUMBER+
				" from "+StartClassTable.TABLE_NAME+" inner join "+MajorTeachTable.TABLE_NAME+" on "+StartClassTable.TABLE_NAME+"."+StartClassTable.SC_COURSENUM+" = "+MajorTeachTable.TABLE_NAME+"."+MajorTeachTable.MT_COURSECODE+
				" where "+StartClassTable.SC_TEACHERTITLE+" = '教授'";
		String degree1 = "";
		String degree2 = "";
		if (queryParamsforSC != null && queryParamsforSC.keySet().size() != 0)
		{
			for (Object object : queryParamsforSC.keySet())
			{
				String key = object.toString();
				if(key.equals("2nddegree"))
				{
					degree1 = queryParamsforSC.get(key).toString();
				}
				else if(key.equals("1stdegree"))
				{
					degree2 = queryParamsforSC.get(key).toString();
				}
				else {
					String value = queryParamsforSC.get(key).toString();
					sql += String.format(" and %s like  '%%%s%%'", key, value);
				}
			}
		}
		
		if (queryParamsforMT != null && queryParamsforMT.keySet().size() != 0)
		{
			for (Object object : queryParamsforMT.keySet())
			{
				String key = object.toString();
				String value = queryParamsforMT.get(key).toString();
				sql += String.format(" and %s like  '%%%s%%'", key, value);
			}
		}
		
		sql+=" and ("+StartClassTable.SC_TEACHOBJECT+" like '%"+degree1+"%' or "+StartClassTable.SC_TEACHOBJECT+" like '%"+degree2+"%' )";
		
		sql+=" group by "+MajorTeachTable.MT_MAJORNAMEINSCHOOL+", "+StartClassTable.SC_TEACHERNUMBER+")tmp inner join "+ExternalTeacherTable.TABLE_NAME+" where tmp."+StartClassTable.SC_TEACHERNUMBER+" = "+
				ExternalTeacherTable.TABLE_NAME+"."+ExternalTeacherTable.ET_WORKNUMBER;
		
		if (queryParamsforET != null && queryParamsforET.keySet().size() != 0)
		{
			for (Object object : queryParamsforET.keySet())
			{
				String key = object.toString();
				String value = queryParamsforET.get(key).toString();
				sql += String.format(" and %s like  '%%%s%%'", key, value);
			}
		}
		sql+=" group by "+MajorTeachTable.MT_MAJORNAMEINSCHOOL;
		
		System.out.println(sql);
		Map resultMap = new HashMap();
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
		try {
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			while (resultSet.next())
			{
				resultMap.put(resultSet.getString(MajorTeachTable.MT_MAJORNAMEINSCHOOL), resultSet.getInt(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return resultMap;
	}
	
	//***********************OTI
	@Override
	public List<MajorTeacherStructureTemp> getMajorTeacherStructureFormOTIAll(Map queryParamsforMT, Map queryParamsforSC, Map queryParamsforOTI) {
		String sql = "select "+MajorTeachTable.MT_MAJORNAMEINSCHOOL+", "+StartClassTable.SC_TEACHERTITLE+", "+OtherTeacherInfoTable.OTI_DEGREE+", count(*) from (select "+
				MajorTeachTable.MT_MAJORNAMEINSCHOOL+", "+StartClassTable.SC_TEACHERTITLE+", "+StartClassTable.SC_TEACHERNUMBER+" from "+StartClassTable.TABLE_NAME+" INNER JOIN "+
				MajorTeachTable.TABLE_NAME+" on "+StartClassTable.TABLE_NAME+"."+StartClassTable.SC_COURSENUM+" = "+MajorTeachTable.TABLE_NAME+"."+MajorTeachTable.MT_COURSECODE+
				" where 1=1 ";
		//接mt和sc表的参数,如deadline和college
		if (queryParamsforMT != null && queryParamsforMT.keySet().size() != 0)
		{
			for (Object object : queryParamsforMT.keySet())
			{
				String key = object.toString();
				String value = queryParamsforMT.get(key).toString();
				sql += String.format(" and %s like  '%%%s%%'", key, value);
			}
		}
		
		if (queryParamsforSC != null && queryParamsforSC.keySet().size() != 0)
		{
			for (Object object : queryParamsforSC.keySet())
			{
				String key = object.toString();
				String value = queryParamsforSC.get(key).toString();
				sql += String.format(" and %s like  '%%%s%%'", key, value);
			}
		}
		sql+=" group by "+MajorTeachTable.MT_MAJORNAMEINSCHOOL+", "+StartClassTable.SC_TEACHERTITLE+", "+StartClassTable.SC_TEACHERNUMBER+")tmp inner join "+OtherTeacherInfoTable.TABLE_NAME+
				" where tmp."+StartClassTable.SC_TEACHERNUMBER+" = "+OtherTeacherInfoTable.TABLE_NAME+"."+OtherTeacherInfoTable.OTI_WORKNUMBER;
		
		if (queryParamsforOTI != null && queryParamsforOTI.keySet().size() != 0)
		{
			for (Object object : queryParamsforOTI.keySet())
			{
				String key = object.toString();
				String value = queryParamsforOTI.get(key).toString();
				sql += String.format(" and %s like  '%%%s%%'", key, value);
			}
		}
		sql+=" group by "+MajorTeachTable.MT_MAJORNAMEINSCHOOL+", "+StartClassTable.SC_TEACHERTITLE+", "+OtherTeacherInfoTable.OTI_DEGREE;
		
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

		List<MajorTeacherStructureTemp>resultList = new ArrayList<MajorTeacherStructureTemp>();
		try {
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			while (resultSet.next())
			{
				String subject = resultSet.getString(MajorTeachTable.MT_MAJORNAMEINSCHOOL);
				String title = resultSet.getString(StartClassTable.SC_TEACHERTITLE);
				String degree = resultSet.getString(OtherTeacherInfoTable.OTI_DEGREE);
				int count = resultSet.getInt(4);
				MajorTeacherStructureTemp majorTeacherStructureTemp = new MajorTeacherStructureTemp(subject, title, degree, count);
				resultList.add(majorTeacherStructureTemp);
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
	public Map getMajorLowCountFormOTI(Map queryParamsforMT, Map queryParamsforSC, Map queryParamsforOTI)
	{
		String sql = "select "+MajorTeachTable.MT_MAJORNAMEINSCHOOL+", count(*) from (select "+MajorTeachTable.MT_MAJORNAMEINSCHOOL+", "+StartClassTable.SC_TEACHERNUMBER+
				" from "+StartClassTable.TABLE_NAME+" inner join "+MajorTeachTable.TABLE_NAME+" on "+StartClassTable.TABLE_NAME+"."+StartClassTable.SC_COURSENUM+" = "+MajorTeachTable.TABLE_NAME+"."+MajorTeachTable.MT_COURSECODE+
				" where "+StartClassTable.SC_TEACHERTITLE+" = '教授'";
		String degree1 = "";
		String degree2 = "";
		if (queryParamsforSC != null && queryParamsforSC.keySet().size() != 0)
		{
			for (Object object : queryParamsforSC.keySet())
			{
				String key = object.toString();
				if(key.equals("2nddegree"))
				{
					degree1 = queryParamsforSC.get(key).toString();
				}
				else if(key.equals("1stdegree"))
				{
					degree2 = queryParamsforSC.get(key).toString();
				}
				else {
					String value = queryParamsforSC.get(key).toString();
					sql += String.format(" and %s like  '%%%s%%'", key, value);
				}
			}
		}
		
		if (queryParamsforMT != null && queryParamsforMT.keySet().size() != 0)
		{
			for (Object object : queryParamsforMT.keySet())
			{
				String key = object.toString();
				String value = queryParamsforMT.get(key).toString();
				sql += String.format(" and %s like  '%%%s%%'", key, value);
			}
		}
		
		sql+=" and ("+StartClassTable.SC_TEACHOBJECT+" like '%"+degree1+"%' or "+StartClassTable.SC_TEACHOBJECT+" like '%"+degree2+"%' )";
		
		sql+=" group by "+MajorTeachTable.MT_MAJORNAMEINSCHOOL+", "+StartClassTable.SC_TEACHERNUMBER+")tmp inner join "+OtherTeacherInfoTable.TABLE_NAME+" where tmp."+StartClassTable.SC_TEACHERNUMBER+" = "+
				OtherTeacherInfoTable.TABLE_NAME+"."+OtherTeacherInfoTable.OTI_WORKNUMBER;
		
		if (queryParamsforOTI != null && queryParamsforOTI.keySet().size() != 0)
		{
			for (Object object : queryParamsforOTI.keySet())
			{
				String key = object.toString();
				String value = queryParamsforOTI.get(key).toString();
				sql += String.format(" and %s like  '%%%s%%'", key, value);
			}
		}
		sql+=" group by "+MajorTeachTable.MT_MAJORNAMEINSCHOOL;
		
		System.out.println(sql);
		Map resultMap = new HashMap();
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
		try {
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			while (resultSet.next())
			{
				resultMap.put(resultSet.getString(MajorTeachTable.MT_MAJORNAMEINSCHOOL), resultSet.getInt(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return resultMap;
	}

	@Override
	public List<StartClass> getStartClassByTeacherNum(String teacherNum) {
		// TODO Auto-generated method stub
		String sql = " select * from "
				+ StartClassTable.TABLE_NAME + " where " + StartClassTable.SC_TEACHERNUMBER +" = "+teacherNum;
		
		List<StartClass> startClasses = new ArrayList<StartClass>();
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
				int sc_id = resultSet.getInt(StartClassTable.SC_ID);
			    
				String sc_number = resultSet.getString(StartClassTable.SC_NUMBER); //开课号
				String sc_coursenum = resultSet.getString(StartClassTable.SC_COURSENUM); //课程号
				String sc_coursecategory = resultSet.getString(StartClassTable.SC_COURSECATEGORY); //课程类别
				String sc_campus = resultSet.getString(StartClassTable.SC_CAMPUS); //校区
				Integer sc_totalhour = resultSet.getInt(StartClassTable.SC_TOTALHOUR); //总学时
				if(sc_totalhour == -1) sc_totalhour = null;
				Float sc_totalcredit = resultSet.getFloat(StartClassTable.SC_TOTALCREDIT); //学分
				if(sc_totalcredit == -1.0) sc_totalcredit = null;
				String sc_evaluationmode = resultSet.getString(StartClassTable.SC_EVALUATIONMODE); //考核方式
				String sc_teachobject = resultSet.getString(StartClassTable.SC_TEACHOBJECT); //授课对象
				String sc_arrange = resultSet.getString(StartClassTable.SC_ARRANGE); //安排情况
				String sc_yearandsemester = resultSet.getString(StartClassTable.SC_YEARANDSEMESTER); //学年学期
				String sc_collegename = resultSet.getString(StartClassTable.SC_COLLEGENAME); //授课院
				String sc_coursename = resultSet.getString(StartClassTable.SC_COURSENAME); //课程名称
				String sc_teacher = resultSet.getString(StartClassTable.SC_TEACHER); //授课教师
				String sc_isoutsideteacher = resultSet.getString(StartClassTable.SC_ISOUTSIDETEACHER); //是否校外专家
				String sc_teachernumber = resultSet.getString(StartClassTable.SC_TEACHERNUMBER); //授课教师工号
				String sc_teachertitle = resultSet.getString(StartClassTable.SC_TEACHERTITLE); //职称
				Integer sc_studentnum = resultSet.getInt(StartClassTable.SC_STUDENTNUM); //本科学生数
				if(sc_studentnum == -1) sc_studentnum = null;
				String sc_isenglish = resultSet.getString(StartClassTable.SC_ISENGLISH); //英语授课情况
				String sc_website = resultSet.getString(StartClassTable.SC_WEBSITE); //网络教学平台网站
				String sc_teachmaterial = resultSet.getString(StartClassTable.SC_TEACHMATERIAL); //教材使用情况
				Integer sc_materialspecies = resultSet.getInt(StartClassTable.SC_MATERIALSPECIES); //使用教材种数
				if(sc_materialspecies == -1) sc_materialspecies = null;
				String sc_ismagong = resultSet.getString(StartClassTable.SC_ISMAGONG); //是否使用马工教材
				String sc_isstandard = resultSet.getString(StartClassTable.SC_ISSTANDARD); //是否使用规划教材
				String sc_foreignmaterial = resultSet.getString(StartClassTable.SC_FOREIGNMATERIAL); //境外教材使用情况
				String sc_m_name = resultSet.getString(StartClassTable.SC_M_NAME); //教材名称
				String sc_m_auther = resultSet.getString(StartClassTable.SC_M_AUTHER);//作者
				String sc_m_publisher = resultSet.getString(StartClassTable.SC_M_PUBLISHER); //出版社
			    String sc_m_country = resultSet.getString(StartClassTable.SC_M_COUNTRY); //所属国家
				Integer sc_m_publishyear = resultSet.getInt(StartClassTable.SC_M_PUBLISHYEAR); //出版年
				if(sc_m_publishyear == -1) sc_m_publishyear = null;
				    
				String sc_college = resultSet.getString(StartClassTable.SC_COLLEGE); 
			    int sc_serialnumber = resultSet.getInt(StartClassTable.SC_SERIALNUMBER);//序列号
			  	Date sc_deadline = resultSet.getDate(StartClassTable.SC_DEADLINE);//截止日期
			  	String sc_comments = resultSet.getString(StartClassTable.SC_COMMENTS);//审核意见
				if(null == sc_comments){
					sc_comments = "";
				}
				int sc_isnull = resultSet.getInt(StartClassTable.SC_ISNULL);
				StartClass startClass = new StartClass(
						sc_id, sc_number, sc_coursenum, sc_coursecategory, sc_campus, sc_totalhour, sc_totalcredit, sc_evaluationmode, 
						sc_teachobject, sc_arrange, sc_yearandsemester, sc_collegename, sc_coursename, sc_teacher, sc_isoutsideteacher, sc_teachernumber,
				        sc_teachertitle, sc_studentnum, sc_isenglish, sc_website, sc_teachmaterial, sc_materialspecies, sc_ismagong, sc_isstandard, 
				        sc_foreignmaterial, sc_m_name, sc_m_auther, sc_m_publisher, sc_m_country, sc_m_publishyear, sc_college, sc_deadline, sc_serialnumber, sc_comments, sc_isnull);

				startClasses.add(startClass);
			}
			return startClasses;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}

	}
}
