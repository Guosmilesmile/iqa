package cn.edu.xmu.daoimpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.dao.MajorInfoDao;
import cn.edu.xmu.entity.MajorInfo;
import cn.edu.xmu.table.MajorInfoTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;

/**
 * 
 * @author zsj
 * 4-2-2-1 专业基本情况
 * 
 */
public class MajorInfoDaoImpl extends BaseDaoImpl<MajorInfo> implements MajorInfoDao{

	//获得总数量
	@Override
	public int getMajorInfoCount() {
		int count  = 0;
		String sql = "select count(*) from " + MajorInfoTable.TABLE_NAME +" where 1 = 1"; 
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
			return -1;
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

	//获得所有专业信息
	@Override
	public List<MajorInfo> getAllMajorInfo(int start, int end, String sortStr,
			String orderStr) {
		String sql = " select tmp.* from ( " +
				" select * from "+MajorInfoTable.TABLE_NAME +" where 1=1 " ;
		
		sql += " order by " + sortStr +" "+orderStr+
				" ) tmp limit "+ start +
				" ," + end;
		
		System.out.println(sql);
	   Connection connection = null;
	   try {
		   connection = JdbcUtils_DBCP.getConnection();
		   }
	   catch (SQLException e1) {
		   e1.printStackTrace();
		   return null;
		   }
	   PreparedStatement pstmt = null;
	   ResultSet resultSet = null;
	
	try {
		System.out.println(sql);
		pstmt = connection.prepareStatement(sql);
		resultSet = pstmt.executeQuery();
		List<MajorInfo> majorInfos = new ArrayList<MajorInfo>();
		   
		while(resultSet.next()){
			int mi_id = resultSet.getInt(MajorInfoTable.MI_ID);
			String mi_majorcodeinschool = resultSet.getString(MajorInfoTable.MI_MAJORCODEINSCHOOL);
			String mi_majornameinschool = resultSet.getString(MajorInfoTable.MI_MAJORNAMEINSCHOOL);
			String mi_majorname = resultSet.getString(MajorInfoTable.MI_MAJORNAME);
			String mi_majorcode = resultSet.getString(MajorInfoTable.MI_MAJORCODE);
			String mi_codeversion = resultSet.getString(MajorInfoTable.MI_CODEVERSION);
			String mi_majorfieldnum = resultSet.getString(MajorInfoTable.MI_MAJORFIELDNUM);
			String mi_majorfieldname = resultSet.getString(MajorInfoTable.MI_MAJORFIELDNAME);
			String mi_departmentnumber = resultSet.getString(MajorInfoTable.MI_DEPARTMENTNUMBER);
			String mi_departmentname = resultSet.getString(MajorInfoTable.MI_DEPARTMENTNAME);
			String mi_leaderid = resultSet.getString(MajorInfoTable.MI_LEADERID);
			String mi_leadername = resultSet.getString(MajorInfoTable.MI_LEADERNAME);
			String mi_admissionstate = resultSet.getString(MajorInfoTable.MI_ADMISSIONSTATE);
			String mi_majorspecialty = resultSet.getString(MajorInfoTable.MI_MAJORSPECIALTY);
			String mi_traininggoal = resultSet.getString(MajorInfoTable.MI_TRAININGGOAL);
			Integer mi_schoolingyear = resultSet.getInt(MajorInfoTable.MI_SCHOOLINGYEAR);
			if(mi_schoolingyear==-999)
				mi_schoolingyear = null;
			String mi_setupyear = resultSet.getString(MajorInfoTable.MI_SETUPYEAR);
			
			String mi_isnew = resultSet.getString(MajorInfoTable.MI_ISNEW);

			Integer mi_allhours = resultSet.getInt(MajorInfoTable.MI_ALLHOURS);
			if(mi_allhours==-999)
				mi_allhours = null;
			Integer mi_musthours = resultSet.getInt(MajorInfoTable.MI_MUSTHOURS);
			if(mi_musthours==-999)
				mi_musthours = null;
			Integer mi_selectedhours = resultSet.getInt(MajorInfoTable.MI_SELECTEDHOURS);
			if(mi_selectedhours==-999)
				mi_selectedhours = null;
			Integer mi_inclasshours = resultSet.getInt(MajorInfoTable.MI_INCLASSHOURS);
			if(mi_inclasshours==-999)
				mi_inclasshours = null;
			Integer mi_experimenthours = resultSet.getInt(MajorInfoTable.MI_EXPERIMENTHOURS);
			if(mi_experimenthours==-999)
				mi_experimenthours = null;
			Float mi_allcredits = resultSet.getFloat(MajorInfoTable.MI_ALLCREDITS);
			if(mi_allcredits==-999)
				mi_allcredits = null;
			Float mi_mustcredits = resultSet.getFloat(MajorInfoTable.MI_MUSTCREDITS);
			if(mi_mustcredits==-999)
				mi_mustcredits = null;
			Float mi_selectedcredits = resultSet.getFloat(MajorInfoTable.MI_SELECTEDCREDITS);
			if(mi_selectedcredits==-999)
				mi_selectedcredits = null;
			Float mi_concentratedpracticecredits = resultSet.getFloat(MajorInfoTable.MI_CONCENTRATEDPRACTICECREDITS);
			if(mi_concentratedpracticecredits==-999)
				mi_concentratedpracticecredits = null;
			Float mi_inclasscredits = resultSet.getFloat(MajorInfoTable.MI_INCLASSCREDITS);
			if(mi_inclasscredits==-999)
				mi_inclasscredits = null;
			Float mi_experimentcredits = resultSet.getFloat(MajorInfoTable.MI_EXPERIMENTCREDITS);
			if(mi_experimentcredits==-999)
				mi_experimentcredits = null;
			Float mi_outclassactivitycredits = resultSet.getFloat(MajorInfoTable.MI_OUTCLASSACTIVITYCREDITS);
			if(mi_outclassactivitycredits==-999)
				mi_outclassactivitycredits = null;
			
			int mi_serialnumber = resultSet.getInt(MajorInfoTable.MI_SERIALNUMBER);
			Date mi_deadline = resultSet.getDate(MajorInfoTable.MI_DEADLINE);
			String mi_college = resultSet.getString(MajorInfoTable.MI_COLLEGE);
			String mi_comments = resultSet.getString(MajorInfoTable.MI_COMMENTS);
			int isnull = resultSet.getInt(MajorInfoTable.ISNULL);
			if(mi_comments==null)
				mi_comments="";
			MajorInfo majorInfo = new MajorInfo(mi_id,mi_majorcodeinschool, mi_majornameinschool, mi_codeversion, mi_majorcode, mi_majorname, mi_majorfieldnum, mi_majorfieldname, mi_departmentnumber, mi_departmentname,mi_leaderid,mi_leadername, mi_admissionstate, mi_majorspecialty, mi_traininggoal, mi_schoolingyear, mi_setupyear, mi_isnew, mi_allhours, mi_musthours, mi_selectedhours, mi_inclasshours, mi_experimenthours, mi_allcredits, mi_mustcredits, mi_selectedcredits, mi_concentratedpracticecredits, mi_inclasscredits, mi_experimentcredits, mi_outclassactivitycredits, mi_serialnumber, mi_deadline, mi_college, mi_comments,isnull);
			
			majorInfos.add(majorInfo);
		}
		return majorInfos;
	} catch (SQLException e) {
		e.printStackTrace();
		return null;
	}finally{
		JdbcUtils_DBCP.release(connection, pstmt, resultSet);
	  }
		
	}

	
	
	@Override
	public int addMajorInfo(MajorInfo majorInfo) {
		int result = 0;

		String sql2 = "update " + MajorInfoTable.TABLE_NAME + " set "
				+ MajorInfoTable.MI_SERIALNUMBER + " = "
				+ MajorInfoTable.MI_SERIALNUMBER + " +1 where "
				+ MajorInfoTable.MI_SERIALNUMBER + ">=" + majorInfo.getMi_serialnumber();
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
		} finally{
			JdbcUtils_DBCP.release(connection2, pstmt2, null);
		}
		
		String sql = "insert into " + MajorInfoTable.TABLE_NAME + "("
		
		+ MajorInfoTable.MI_MAJORCODEINSCHOOL+ "," 
		+ MajorInfoTable.MI_MAJORNAMEINSCHOOL+ "," 
		+ MajorInfoTable.MI_CODEVERSION+ "," 
		+ MajorInfoTable.MI_MAJORCODE+ "," 
		+ MajorInfoTable.MI_MAJORNAME + "," 
		+ MajorInfoTable.MI_MAJORFIELDNUM + "," 
		+ MajorInfoTable.MI_MAJORFIELDNAME+ "," 
		+ MajorInfoTable.MI_DEPARTMENTNUMBER + "," 
		+ MajorInfoTable.MI_DEPARTMENTNAME + "," 
		+ MajorInfoTable.MI_LEADERID+ "," 
		+ MajorInfoTable.MI_LEADERNAME+ "," 
		+ MajorInfoTable.MI_ADMISSIONSTATE + "," 
		+ MajorInfoTable.MI_MAJORSPECIALTY+ "," 
		+ MajorInfoTable.MI_TRAININGGOAL+ "," 
		+ MajorInfoTable.MI_SCHOOLINGYEAR + "," 
		+ MajorInfoTable.MI_SETUPYEAR + "," 
		+ MajorInfoTable.MI_ISNEW + "," 
		+ MajorInfoTable.MI_ALLHOURS + "," 
		+ MajorInfoTable.MI_MUSTHOURS+ "," 
		+ MajorInfoTable.MI_SELECTEDHOURS + "," 
		+ MajorInfoTable.MI_INCLASSHOURS+ "," 
		+ MajorInfoTable.MI_EXPERIMENTHOURS+ "," 
		+ MajorInfoTable.MI_ALLCREDITS + "," 
		+ MajorInfoTable.MI_MUSTCREDITS+ "," 
		+ MajorInfoTable.MI_SELECTEDCREDITS + "," 
		+ MajorInfoTable.MI_CONCENTRATEDPRACTICECREDITS + "," 
		+ MajorInfoTable.MI_INCLASSCREDITS+ "," 
		+ MajorInfoTable.MI_EXPERIMENTCREDITS+ "," 
		+ MajorInfoTable.MI_OUTCLASSACTIVITYCREDITS + "," 
		+ MajorInfoTable.MI_SERIALNUMBER + ","  
		+ MajorInfoTable.MI_DEADLINE + ","  
		+ MajorInfoTable.MI_COLLEGE + "," 
		+ MajorInfoTable.MI_COMMENTS +","
		+ MajorInfoTable.ISNULL
		+ ")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		PreparedStatement pstmt = null;
		
		try {
			pstmt = connection.prepareStatement(sql);
			System.out.println(sql);
			pstmt.setString(1, majorInfo.getMi_majorcodeinschool());
			pstmt.setString(2, majorInfo.getMi_majornameinschool());
			pstmt.setString(3, majorInfo.getMi_codeversion());
			pstmt.setString(4, majorInfo.getMi_majorcode());
			pstmt.setString(5, majorInfo.getMi_majorname());
			pstmt.setString(6, majorInfo.getMi_majorfieldnum());
			pstmt.setString(7, majorInfo.getMi_majorfieldname());
			pstmt.setString(8, majorInfo.getMi_departmentnumber());
			pstmt.setString(9, majorInfo.getMi_departmentname());
			pstmt.setString(10, majorInfo.getMi_leaderid());
			pstmt.setString(11, majorInfo.getMi_leadername());
			pstmt.setString(12, majorInfo.getMi_admissionstate());
			pstmt.setString(13, majorInfo.getMi_majorspecialty());
			pstmt.setString(14, majorInfo.getMi_traininggoal());
			pstmt.setInt(15, majorInfo.getMi_schoolingyear());
			pstmt.setString(16, majorInfo.getMi_setupyear());
			pstmt.setString(17, majorInfo.getMi_isnew());
			pstmt.setInt(18, majorInfo.getMi_allhours());
			pstmt.setInt(19, majorInfo.getMi_musthours());
			pstmt.setInt(20, majorInfo.getMi_selectedhours());
			pstmt.setInt(21, majorInfo.getMi_inclasshours());
			pstmt.setInt(22, majorInfo.getMi_experimenthours());
			pstmt.setFloat(23, majorInfo.getMi_allcredits());
			pstmt.setFloat(24, majorInfo.getMi_mustcredits());
			pstmt.setFloat(25, majorInfo.getMi_selectedcredits());
			pstmt.setFloat(26, majorInfo.getMi_concentratedpracticecredits());
			pstmt.setFloat(27, majorInfo.getMi_inclasscredits());
			pstmt.setFloat(28, majorInfo.getMi_experimentcredits());
			pstmt.setFloat(29, majorInfo.getMi_outclassactivitycredits());
			pstmt.setInt(30, majorInfo.getMi_serialnumber());
			pstmt.setDate(31, majorInfo.getMi_deadline());
			pstmt.setString(32, majorInfo.getMi_college());
			pstmt.setString(33, majorInfo.getMi_comments());
			pstmt.setInt(34, majorInfo.getIsnull());
			
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			result = -1;
		} finally{
			JdbcUtils_DBCP.release(connection, pstmt, null);
		}

		return result;
	}
	
	//修改
	@Override
	public int alterMajorInfo(Map<String, String> valueMap, String id) {
		Map<String,String> condition = new HashMap<String,String>();
		condition.put(MajorInfoTable.MI_ID, id);
		int result = updateRecord(MajorInfoTable.TABLE_NAME, valueMap, condition);
		return result;
	}

	//批量删除
	@Override
	public boolean batchDelete(String[] miids) throws SQLException {
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
		Statement stmt = connection.createStatement(); 
		
		for (String miid : miids) {
			String sql = "delete from "+MajorInfoTable.TABLE_NAME+ " where "+MajorInfoTable.MI_ID + " = '"+ miid+ "'";
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
	public List<MajorInfo> getMajorInfo(int start, int end, String sortStr,
			String orderStr, Map<String, String> params) {
		String sql = " select tmp.* from ( " + " select * from "
				+ MajorInfoTable.TABLE_NAME + " where 1=1 ";
		
		if (!(params == null || params.keySet().size() == 0)) {
			for (Object object : params.keySet()) {

				String key = object.toString();
				String value = params.get(key).toString();
				sql += String.format(" and %s like  '%%%s%%' ", key, value);
			}
		}
		sql += String.format(" or %s ='%s'", MajorInfoTable.MI_COLLEGE, "");
		sql += " order by " + sortStr + " " + orderStr + " ) tmp limit "
				+ start + " ," + end;

		System.out.println(sql);

		List<MajorInfo> majorInfos = new ArrayList<MajorInfo>();
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
				int mi_id = resultSet.getInt(MajorInfoTable.MI_ID);
				String mi_majorcodeinschool = resultSet.getString(MajorInfoTable.MI_MAJORCODEINSCHOOL);
				String mi_majornameinschool = resultSet.getString(MajorInfoTable.MI_MAJORNAMEINSCHOOL);
				String mi_majorname = resultSet.getString(MajorInfoTable.MI_MAJORNAME);
				String mi_majorcode = resultSet.getString(MajorInfoTable.MI_MAJORCODE);
				String mi_codeversion = resultSet.getString(MajorInfoTable.MI_CODEVERSION);
				String mi_majorfieldnum = resultSet.getString(MajorInfoTable.MI_MAJORFIELDNUM);
				String mi_majorfieldname = resultSet.getString(MajorInfoTable.MI_MAJORFIELDNAME);
				String mi_departmentnumber = resultSet.getString(MajorInfoTable.MI_DEPARTMENTNUMBER);
				String mi_departmentname = resultSet.getString(MajorInfoTable.MI_DEPARTMENTNAME);
				String mi_leaderid = resultSet.getString(MajorInfoTable.MI_LEADERID);
				String mi_leadername = resultSet.getString(MajorInfoTable.MI_LEADERNAME);
				String mi_admissionstate = resultSet.getString(MajorInfoTable.MI_ADMISSIONSTATE);
				String mi_majorspecialty = resultSet.getString(MajorInfoTable.MI_MAJORSPECIALTY);
				String mi_traininggoal = resultSet.getString(MajorInfoTable.MI_TRAININGGOAL);
				Integer mi_schoolingyear = resultSet.getInt(MajorInfoTable.MI_SCHOOLINGYEAR);
				if(mi_schoolingyear==-999)
					mi_schoolingyear = null;
				String mi_setupyear = resultSet.getString(MajorInfoTable.MI_SETUPYEAR);
				
				String mi_isnew = resultSet.getString(MajorInfoTable.MI_ISNEW);

				Integer mi_allhours = resultSet.getInt(MajorInfoTable.MI_ALLHOURS);
				if(mi_allhours==-999)
					mi_allhours = null;
				Integer mi_musthours = resultSet.getInt(MajorInfoTable.MI_MUSTHOURS);
				if(mi_musthours==-999)
					mi_musthours = null;
				Integer mi_selectedhours = resultSet.getInt(MajorInfoTable.MI_SELECTEDHOURS);
				if(mi_selectedhours==-999)
					mi_selectedhours = null;
				Integer mi_inclasshours = resultSet.getInt(MajorInfoTable.MI_INCLASSHOURS);
				if(mi_inclasshours==-999)
					mi_inclasshours = null;
				Integer mi_experimenthours = resultSet.getInt(MajorInfoTable.MI_EXPERIMENTHOURS);
				if(mi_experimenthours==-999)
					mi_experimenthours = null;
				Float mi_allcredits = resultSet.getFloat(MajorInfoTable.MI_ALLCREDITS);
				if(mi_allcredits==-999)
					mi_allcredits = null;
				Float mi_mustcredits = resultSet.getFloat(MajorInfoTable.MI_MUSTCREDITS);
				if(mi_mustcredits==-999)
					mi_mustcredits = null;
				Float mi_selectedcredits = resultSet.getFloat(MajorInfoTable.MI_SELECTEDCREDITS);
				if(mi_selectedcredits==-999)
					mi_selectedcredits = null;
				Float mi_concentratedpracticecredits = resultSet.getFloat(MajorInfoTable.MI_CONCENTRATEDPRACTICECREDITS);
				if(mi_concentratedpracticecredits==-999)
					mi_concentratedpracticecredits = null;
				Float mi_inclasscredits = resultSet.getFloat(MajorInfoTable.MI_INCLASSCREDITS);
				if(mi_inclasscredits==-999)
					mi_inclasscredits = null;
				Float mi_experimentcredits = resultSet.getFloat(MajorInfoTable.MI_EXPERIMENTCREDITS);
				if(mi_experimentcredits==-999)
					mi_experimentcredits = null;
				Float mi_outclassactivitycredits = resultSet.getFloat(MajorInfoTable.MI_OUTCLASSACTIVITYCREDITS);
				if(mi_outclassactivitycredits==-999)
					mi_outclassactivitycredits = null;
				
				int mi_serialnumber = resultSet.getInt(MajorInfoTable.MI_SERIALNUMBER);
				Date mi_deadline = resultSet.getDate(MajorInfoTable.MI_DEADLINE);
				String mi_college = resultSet.getString(MajorInfoTable.MI_COLLEGE);
				String mi_comments = resultSet.getString(MajorInfoTable.MI_COMMENTS);
				int isnull = resultSet.getInt(MajorInfoTable.ISNULL);
				if(mi_comments==null)
					mi_comments="";
				MajorInfo majorInfo = new MajorInfo(mi_id,mi_majorcodeinschool, mi_majornameinschool, mi_codeversion, mi_majorcode, mi_majorname, mi_majorfieldnum, mi_majorfieldname, mi_departmentnumber, mi_departmentname,mi_leaderid, mi_leadername,mi_admissionstate, mi_majorspecialty, mi_traininggoal, mi_schoolingyear, mi_setupyear, mi_isnew, mi_allhours, mi_musthours, mi_selectedhours, mi_inclasshours, mi_experimenthours, mi_allcredits, mi_mustcredits, mi_selectedcredits, mi_concentratedpracticecredits, mi_inclasscredits, mi_experimentcredits, mi_outclassactivitycredits, mi_serialnumber, mi_deadline, mi_college, mi_comments,isnull);
				
				majorInfos.add(majorInfo);
			}
			return majorInfos;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
	}

	@Override
	public int getCount(Map params) {
		int count  = 0;
		String sql = "select count(*) from " + MajorInfoTable.TABLE_NAME +" where 1 = 1"; 
		
		if (params != null && params.keySet().size() > 0) {
			for (Object object : params.keySet()) {

				String key = object.toString();
				System.out.println("key--------------------------"+key);
				String value = params.get(key).toString();
				sql += String.format(" and %s like  '%%%s%%' ", key, value);
			}
		}
		sql += String.format(" or %s ='%s'", MajorInfoTable.MI_COLLEGE, "");
		System.out.println(sql);
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
			return -1;
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
	public void deleteByCollegeandDeadline(String college) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();
		String sql = "delete from " + MajorInfoTable.TABLE_NAME
				+ " where " + MajorInfoTable.MI_COLLEGE + " = '" + college + "'" +" and "
				+MajorInfoTable.MI_DEADLINE+" is null ";
		
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
	public List<MajorInfo> getMajorInfoByYear() {
		Calendar a=Calendar.getInstance();
	//	int year = a.get(Calendar.YEAR) - 4;
		String sql = " select * from "+MajorInfoTable.TABLE_NAME +" where "+ MajorInfoTable.MI_SETUPYEAR +" <= "+ (a.get(Calendar.YEAR) - 4) ;
		
		System.out.println(sql);
	   Connection connection = null;
	   try {
		   connection = JdbcUtils_DBCP.getConnection();
		   }
	   catch (SQLException e1) {
		   e1.printStackTrace();
		   return null;
		   }
	   PreparedStatement pstmt = null;
	   ResultSet resultSet = null;
	
	try {
		System.out.println(sql);
		pstmt = connection.prepareStatement(sql);
		resultSet = pstmt.executeQuery();
		List<MajorInfo> majorInfos = new ArrayList<MajorInfo>();
		   
		while(resultSet.next()){
			int mi_id = resultSet.getInt(MajorInfoTable.MI_ID);
			String mi_majorcodeinschool = resultSet.getString(MajorInfoTable.MI_MAJORCODEINSCHOOL);
			String mi_majornameinschool = resultSet.getString(MajorInfoTable.MI_MAJORNAMEINSCHOOL);
			String mi_majorname = resultSet.getString(MajorInfoTable.MI_MAJORNAME);
			String mi_majorcode = resultSet.getString(MajorInfoTable.MI_MAJORCODE);
			String mi_codeversion = resultSet.getString(MajorInfoTable.MI_CODEVERSION);
			String mi_majorfieldnum = resultSet.getString(MajorInfoTable.MI_MAJORFIELDNUM);
			String mi_majorfieldname = resultSet.getString(MajorInfoTable.MI_MAJORFIELDNAME);
			String mi_departmentnumber = resultSet.getString(MajorInfoTable.MI_DEPARTMENTNUMBER);
			String mi_departmentname = resultSet.getString(MajorInfoTable.MI_DEPARTMENTNAME);
			String mi_leaderid = resultSet.getString(MajorInfoTable.MI_LEADERID);
			String mi_leadername = resultSet.getString(MajorInfoTable.MI_LEADERNAME);
			String mi_admissionstate = resultSet.getString(MajorInfoTable.MI_ADMISSIONSTATE);
			String mi_majorspecialty = resultSet.getString(MajorInfoTable.MI_MAJORSPECIALTY);
			String mi_traininggoal = resultSet.getString(MajorInfoTable.MI_TRAININGGOAL);
			Integer mi_schoolingyear = resultSet.getInt(MajorInfoTable.MI_SCHOOLINGYEAR);
			if(mi_schoolingyear==-999)
				mi_schoolingyear = null;
			String mi_setupyear = resultSet.getString(MajorInfoTable.MI_SETUPYEAR);
			
			String mi_isnew = resultSet.getString(MajorInfoTable.MI_ISNEW);

			Integer mi_allhours = resultSet.getInt(MajorInfoTable.MI_ALLHOURS);
			if(mi_allhours==-999)
				mi_allhours = null;
			Integer mi_musthours = resultSet.getInt(MajorInfoTable.MI_MUSTHOURS);
			if(mi_musthours==-999)
				mi_musthours = null;
			Integer mi_selectedhours = resultSet.getInt(MajorInfoTable.MI_SELECTEDHOURS);
			if(mi_selectedhours==-999)
				mi_selectedhours = null;
			Integer mi_inclasshours = resultSet.getInt(MajorInfoTable.MI_INCLASSHOURS);
			if(mi_inclasshours==-999)
				mi_inclasshours = null;
			Integer mi_experimenthours = resultSet.getInt(MajorInfoTable.MI_EXPERIMENTHOURS);
			if(mi_experimenthours==-999)
				mi_experimenthours = null;
			Float mi_allcredits = resultSet.getFloat(MajorInfoTable.MI_ALLCREDITS);
			if(mi_allcredits==-999)
				mi_allcredits = null;
			Float mi_mustcredits = resultSet.getFloat(MajorInfoTable.MI_MUSTCREDITS);
			if(mi_mustcredits==-999)
				mi_mustcredits = null;
			Float mi_selectedcredits = resultSet.getFloat(MajorInfoTable.MI_SELECTEDCREDITS);
			if(mi_selectedcredits==-999)
				mi_selectedcredits = null;
			Float mi_concentratedpracticecredits = resultSet.getFloat(MajorInfoTable.MI_CONCENTRATEDPRACTICECREDITS);
			if(mi_concentratedpracticecredits==-999)
				mi_concentratedpracticecredits = null;
			Float mi_inclasscredits = resultSet.getFloat(MajorInfoTable.MI_INCLASSCREDITS);
			if(mi_inclasscredits==-999)
				mi_inclasscredits = null;
			Float mi_experimentcredits = resultSet.getFloat(MajorInfoTable.MI_EXPERIMENTCREDITS);
			if(mi_experimentcredits==-999)
				mi_experimentcredits = null;
			Float mi_outclassactivitycredits = resultSet.getFloat(MajorInfoTable.MI_OUTCLASSACTIVITYCREDITS);
			if(mi_outclassactivitycredits==-999)
				mi_outclassactivitycredits = null;
			
			int mi_serialnumber = resultSet.getInt(MajorInfoTable.MI_SERIALNUMBER);
			Date mi_deadline = resultSet.getDate(MajorInfoTable.MI_DEADLINE);
			String mi_college = resultSet.getString(MajorInfoTable.MI_COLLEGE);
			String mi_comments = resultSet.getString(MajorInfoTable.MI_COMMENTS);
			int isnull = resultSet.getInt(MajorInfoTable.ISNULL);
			if(mi_comments==null)
				mi_comments="";
			MajorInfo majorInfo = new MajorInfo(mi_id,mi_majorcodeinschool, mi_majornameinschool, mi_codeversion, mi_majorcode, mi_majorname, mi_majorfieldnum, mi_majorfieldname, mi_departmentnumber, mi_departmentname,mi_leaderid,mi_leadername, mi_admissionstate, mi_majorspecialty, mi_traininggoal, mi_schoolingyear, mi_setupyear, mi_isnew, mi_allhours, mi_musthours, mi_selectedhours, mi_inclasshours, mi_experimenthours, mi_allcredits, mi_mustcredits, mi_selectedcredits, mi_concentratedpracticecredits, mi_inclasscredits, mi_experimentcredits, mi_outclassactivitycredits, mi_serialnumber, mi_deadline, mi_college, mi_comments,isnull);
			
			majorInfos.add(majorInfo);
		}
		return majorInfos;
	} catch (SQLException e) {
		e.printStackTrace();
		return null;
	}finally{
		JdbcUtils_DBCP.release(connection, pstmt, resultSet);
	  }
		
	}

	@Override
	public MajorInfo getMajorInfoByName(String majorName) {
		// TODO Auto-generated method stub
       if(majorName == null) return null;
       
		 String sql = " select * from "+MajorInfoTable.TABLE_NAME +" where "+ MajorInfoTable.MI_MAJORNAME +" = '"+ majorName+"'";
		
	   Connection connection = null;
	   try {
		   connection = JdbcUtils_DBCP.getConnection();
		   }
	   catch (SQLException e1) {
		   e1.printStackTrace();
		   return null;
		   }
	   PreparedStatement pstmt = null;
	   ResultSet resultSet = null;
	
	try {
		System.out.println(sql);
		pstmt = connection.prepareStatement(sql);
		resultSet = pstmt.executeQuery();
		List<MajorInfo> majorInfos = new ArrayList<MajorInfo>();
		   
		while(resultSet.next()){
			int mi_id = resultSet.getInt(MajorInfoTable.MI_ID);
			String mi_majorcodeinschool = resultSet.getString(MajorInfoTable.MI_MAJORCODEINSCHOOL);
			String mi_majornameinschool = resultSet.getString(MajorInfoTable.MI_MAJORNAMEINSCHOOL);
			String mi_majorname = resultSet.getString(MajorInfoTable.MI_MAJORNAME);
			String mi_majorcode = resultSet.getString(MajorInfoTable.MI_MAJORCODE);
			String mi_codeversion = resultSet.getString(MajorInfoTable.MI_CODEVERSION);
			String mi_majorfieldnum = resultSet.getString(MajorInfoTable.MI_MAJORFIELDNUM);
			String mi_majorfieldname = resultSet.getString(MajorInfoTable.MI_MAJORFIELDNAME);
			String mi_departmentnumber = resultSet.getString(MajorInfoTable.MI_DEPARTMENTNUMBER);
			String mi_departmentname = resultSet.getString(MajorInfoTable.MI_DEPARTMENTNAME);
			String mi_leaderid = resultSet.getString(MajorInfoTable.MI_LEADERID);
			String mi_leadername = resultSet.getString(MajorInfoTable.MI_LEADERNAME);
			String mi_admissionstate = resultSet.getString(MajorInfoTable.MI_ADMISSIONSTATE);
			String mi_majorspecialty = resultSet.getString(MajorInfoTable.MI_MAJORSPECIALTY);
			String mi_traininggoal = resultSet.getString(MajorInfoTable.MI_TRAININGGOAL);
			Integer mi_schoolingyear = resultSet.getInt(MajorInfoTable.MI_SCHOOLINGYEAR);
			if(mi_schoolingyear==-999)
				mi_schoolingyear = null;
			String mi_setupyear = resultSet.getString(MajorInfoTable.MI_SETUPYEAR);
			
			String mi_isnew = resultSet.getString(MajorInfoTable.MI_ISNEW);

			Integer mi_allhours = resultSet.getInt(MajorInfoTable.MI_ALLHOURS);
			if(mi_allhours==-999)
				mi_allhours = null;
			Integer mi_musthours = resultSet.getInt(MajorInfoTable.MI_MUSTHOURS);
			if(mi_musthours==-999)
				mi_musthours = null;
			Integer mi_selectedhours = resultSet.getInt(MajorInfoTable.MI_SELECTEDHOURS);
			if(mi_selectedhours==-999)
				mi_selectedhours = null;
			Integer mi_inclasshours = resultSet.getInt(MajorInfoTable.MI_INCLASSHOURS);
			if(mi_inclasshours==-999)
				mi_inclasshours = null;
			Integer mi_experimenthours = resultSet.getInt(MajorInfoTable.MI_EXPERIMENTHOURS);
			if(mi_experimenthours==-999)
				mi_experimenthours = null;
			Float mi_allcredits = resultSet.getFloat(MajorInfoTable.MI_ALLCREDITS);
			if(mi_allcredits==-999)
				mi_allcredits = null;
			Float mi_mustcredits = resultSet.getFloat(MajorInfoTable.MI_MUSTCREDITS);
			if(mi_mustcredits==-999)
				mi_mustcredits = null;
			Float mi_selectedcredits = resultSet.getFloat(MajorInfoTable.MI_SELECTEDCREDITS);
			if(mi_selectedcredits==-999)
				mi_selectedcredits = null;
			Float mi_concentratedpracticecredits = resultSet.getFloat(MajorInfoTable.MI_CONCENTRATEDPRACTICECREDITS);
			if(mi_concentratedpracticecredits==-999)
				mi_concentratedpracticecredits = null;
			Float mi_inclasscredits = resultSet.getFloat(MajorInfoTable.MI_INCLASSCREDITS);
			if(mi_inclasscredits==-999)
				mi_inclasscredits = null;
			Float mi_experimentcredits = resultSet.getFloat(MajorInfoTable.MI_EXPERIMENTCREDITS);
			if(mi_experimentcredits==-999)
				mi_experimentcredits = null;
			Float mi_outclassactivitycredits = resultSet.getFloat(MajorInfoTable.MI_OUTCLASSACTIVITYCREDITS);
			if(mi_outclassactivitycredits==-999)
				mi_outclassactivitycredits = null;
			
			int mi_serialnumber = resultSet.getInt(MajorInfoTable.MI_SERIALNUMBER);
			Date mi_deadline = resultSet.getDate(MajorInfoTable.MI_DEADLINE);
			String mi_college = resultSet.getString(MajorInfoTable.MI_COLLEGE);
			String mi_comments = resultSet.getString(MajorInfoTable.MI_COMMENTS);
			int isnull = resultSet.getInt(MajorInfoTable.ISNULL);
			if(mi_comments==null)
				mi_comments="";
			MajorInfo majorInfo = new MajorInfo(mi_id,mi_majorcodeinschool, mi_majornameinschool, mi_codeversion, mi_majorcode, mi_majorname, mi_majorfieldnum, mi_majorfieldname, mi_departmentnumber, mi_departmentname,mi_leaderid,mi_leadername, mi_admissionstate, mi_majorspecialty, mi_traininggoal, mi_schoolingyear, mi_setupyear, mi_isnew, mi_allhours, mi_musthours, mi_selectedhours, mi_inclasshours, mi_experimenthours, mi_allcredits, mi_mustcredits, mi_selectedcredits, mi_concentratedpracticecredits, mi_inclasscredits, mi_experimentcredits, mi_outclassactivitycredits, mi_serialnumber, mi_deadline, mi_college, mi_comments,isnull);
			
			majorInfos.add(majorInfo);
		}
		if(majorInfos.size() > 0) return majorInfos.get(0);
		else return  null;
	} catch (SQLException e) {
		e.printStackTrace();
		return null;
	}finally{
		JdbcUtils_DBCP.release(connection, pstmt, resultSet);
	  }
	}

	@Override
	public List<MajorInfo> getNewMajor() {
		// TODO Auto-generated method stub
		String sql = " select * from "+MajorInfoTable.TABLE_NAME +" where "+ MajorInfoTable.MI_ISNEW +" = '是' ";
		
		   Connection connection = null;
		   try {
			   connection = JdbcUtils_DBCP.getConnection();
			   }
		   catch (SQLException e1) {
			   e1.printStackTrace();
			   return null;
			   }
		   PreparedStatement pstmt = null;
		   ResultSet resultSet = null;
		
		try {
			System.out.println(sql);
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			List<MajorInfo> majorInfos = new ArrayList<MajorInfo>();
			   
			while(resultSet.next()){
				int mi_id = resultSet.getInt(MajorInfoTable.MI_ID);
				String mi_majorcodeinschool = resultSet.getString(MajorInfoTable.MI_MAJORCODEINSCHOOL);
				String mi_majornameinschool = resultSet.getString(MajorInfoTable.MI_MAJORNAMEINSCHOOL);
				String mi_majorname = resultSet.getString(MajorInfoTable.MI_MAJORNAME);
				String mi_majorcode = resultSet.getString(MajorInfoTable.MI_MAJORCODE);
				String mi_codeversion = resultSet.getString(MajorInfoTable.MI_CODEVERSION);
				String mi_majorfieldnum = resultSet.getString(MajorInfoTable.MI_MAJORFIELDNUM);
				String mi_majorfieldname = resultSet.getString(MajorInfoTable.MI_MAJORFIELDNAME);
				String mi_departmentnumber = resultSet.getString(MajorInfoTable.MI_DEPARTMENTNUMBER);
				String mi_departmentname = resultSet.getString(MajorInfoTable.MI_DEPARTMENTNAME);
				String mi_leaderid = resultSet.getString(MajorInfoTable.MI_LEADERID);
				String mi_leadername = resultSet.getString(MajorInfoTable.MI_LEADERNAME);
				String mi_admissionstate = resultSet.getString(MajorInfoTable.MI_ADMISSIONSTATE);
				String mi_majorspecialty = resultSet.getString(MajorInfoTable.MI_MAJORSPECIALTY);
				String mi_traininggoal = resultSet.getString(MajorInfoTable.MI_TRAININGGOAL);
				Integer mi_schoolingyear = resultSet.getInt(MajorInfoTable.MI_SCHOOLINGYEAR);
				if(mi_schoolingyear==-999)
					mi_schoolingyear = null;
				String mi_setupyear = resultSet.getString(MajorInfoTable.MI_SETUPYEAR);
				
				String mi_isnew = resultSet.getString(MajorInfoTable.MI_ISNEW);

				Integer mi_allhours = resultSet.getInt(MajorInfoTable.MI_ALLHOURS);
				if(mi_allhours==-999)
					mi_allhours = null;
				Integer mi_musthours = resultSet.getInt(MajorInfoTable.MI_MUSTHOURS);
				if(mi_musthours==-999)
					mi_musthours = null;
				Integer mi_selectedhours = resultSet.getInt(MajorInfoTable.MI_SELECTEDHOURS);
				if(mi_selectedhours==-999)
					mi_selectedhours = null;
				Integer mi_inclasshours = resultSet.getInt(MajorInfoTable.MI_INCLASSHOURS);
				if(mi_inclasshours==-999)
					mi_inclasshours = null;
				Integer mi_experimenthours = resultSet.getInt(MajorInfoTable.MI_EXPERIMENTHOURS);
				if(mi_experimenthours==-999)
					mi_experimenthours = null;
				Float mi_allcredits = resultSet.getFloat(MajorInfoTable.MI_ALLCREDITS);
				if(mi_allcredits==-999)
					mi_allcredits = null;
				Float mi_mustcredits = resultSet.getFloat(MajorInfoTable.MI_MUSTCREDITS);
				if(mi_mustcredits==-999)
					mi_mustcredits = null;
				Float mi_selectedcredits = resultSet.getFloat(MajorInfoTable.MI_SELECTEDCREDITS);
				if(mi_selectedcredits==-999)
					mi_selectedcredits = null;
				Float mi_concentratedpracticecredits = resultSet.getFloat(MajorInfoTable.MI_CONCENTRATEDPRACTICECREDITS);
				if(mi_concentratedpracticecredits==-999)
					mi_concentratedpracticecredits = null;
				Float mi_inclasscredits = resultSet.getFloat(MajorInfoTable.MI_INCLASSCREDITS);
				if(mi_inclasscredits==-999)
					mi_inclasscredits = null;
				Float mi_experimentcredits = resultSet.getFloat(MajorInfoTable.MI_EXPERIMENTCREDITS);
				if(mi_experimentcredits==-999)
					mi_experimentcredits = null;
				Float mi_outclassactivitycredits = resultSet.getFloat(MajorInfoTable.MI_OUTCLASSACTIVITYCREDITS);
				if(mi_outclassactivitycredits==-999)
					mi_outclassactivitycredits = null;
				
				int mi_serialnumber = resultSet.getInt(MajorInfoTable.MI_SERIALNUMBER);
				Date mi_deadline = resultSet.getDate(MajorInfoTable.MI_DEADLINE);
				String mi_college = resultSet.getString(MajorInfoTable.MI_COLLEGE);
				String mi_comments = resultSet.getString(MajorInfoTable.MI_COMMENTS);
				int isnull = resultSet.getInt(MajorInfoTable.ISNULL);
				if(mi_comments==null)
					mi_comments="";
				MajorInfo majorInfo = new MajorInfo(mi_id,mi_majorcodeinschool, mi_majornameinschool, mi_codeversion, mi_majorcode, mi_majorname, mi_majorfieldnum, mi_majorfieldname, mi_departmentnumber, mi_departmentname,mi_leaderid,mi_leadername, mi_admissionstate, mi_majorspecialty, mi_traininggoal, mi_schoolingyear, mi_setupyear, mi_isnew, mi_allhours, mi_musthours, mi_selectedhours, mi_inclasshours, mi_experimenthours, mi_allcredits, mi_mustcredits, mi_selectedcredits, mi_concentratedpracticecredits, mi_inclasscredits, mi_experimentcredits, mi_outclassactivitycredits, mi_serialnumber, mi_deadline, mi_college, mi_comments,isnull);
				
				majorInfos.add(majorInfo);
			}
			return majorInfos;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		  }
	}
}
