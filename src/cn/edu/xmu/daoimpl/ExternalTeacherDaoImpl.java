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

import cn.edu.xmu.dao.ExternalTeacherDao;
import cn.edu.xmu.entity.ExternalTeacher;
import cn.edu.xmu.table.ExternalTeacherTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;

/**
 * 
 * @author zhantu
 *
 */
public class ExternalTeacherDaoImpl extends BaseDaoImpl<ExternalTeacher> implements
ExternalTeacherDao{

	@Override
	public List<ExternalTeacher> getAllExternalTeacher(int start, int end,
			String sortStr, String orderStr, Map queryParams) {
		
		String sql = " select tmp.* from ( " + " select * from "
				+ ExternalTeacherTable.TABLE_NAME + " where 1=1 ";
		
		for (Object object : queryParams.keySet()) {
			String key = object.toString();
			String value = queryParams.get(key).toString();
			sql += String.format(" and %s like  '%%%s%%'", key, value);
		}
		sql += " order by " + sortStr + " " + orderStr + " ) tmp limit "
				+ start + " ," + end;

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		List<ExternalTeacher> externalTeachers = new ArrayList<ExternalTeacher>();
		try {
			
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			Date temp = Date.valueOf("1800-1-1");
			while (resultSet.next()) {
				int et_id = resultSet.getInt(ExternalTeacherTable.ET_ID); 
				String et_name = resultSet.getString(ExternalTeacherTable.ET_NAME);
				String et_worknumber = resultSet.getString(ExternalTeacherTable.ET_WORKNUMBER);
				String et_sex = resultSet.getString(ExternalTeacherTable.ET_SEX);
				Date et_birth = resultSet.getDate(ExternalTeacherTable.ET_BIRTH);
				if(et_birth.equals(temp))
					et_birth = null;
				Date et_appointment = resultSet.getDate(ExternalTeacherTable.ET_APPOINTMENT);
				if(et_appointment.equals(temp))
					et_appointment = null;
				String et_situation = resultSet.getString(ExternalTeacherTable.ET_SITUATION);
				Integer et_term = resultSet.getInt(ExternalTeacherTable.ET_TERM);
				if(et_term==-999)
					et_term = null;
				String et_departmentnumber = resultSet.getString(ExternalTeacherTable.ET_DEPARTMENTNUMBER);
				String et_education = resultSet.getString(ExternalTeacherTable.ET_EDUCATION);
				String et_topeducation = resultSet.getString(ExternalTeacherTable.ET_TOPEDUCATION);
				String et_professional = resultSet.getString(ExternalTeacherTable.ET_PROFESSIONAL);
				String et_subject = resultSet.getString(ExternalTeacherTable.ET_SUBJECT);
				String et_job = resultSet.getString(ExternalTeacherTable.ET_JOB);
				String et_teacher = resultSet.getString(ExternalTeacherTable.ET_TEACHER);
				String et_area = resultSet.getString(ExternalTeacherTable.ET_AREA);
				int et_serialnumber = resultSet.getInt(ExternalTeacherTable.ET_SERIALNUMBER);
				Date et_deadline = resultSet.getDate(ExternalTeacherTable.ET_DEADLINE);
				String et_comments = resultSet.getString(ExternalTeacherTable.ET_COMMENTS);
				String et_college = resultSet.getString(ExternalTeacherTable.ET_COLLEGE);
				String et_documentnumber = resultSet.getString(ExternalTeacherTable.ET_DOCUMENTNUMBER);
				String et_departmentname = resultSet.getString(ExternalTeacherTable.ET_DEPARTMENTNAME);
				int isnull = resultSet.getInt(ExternalTeacherTable.ISNULL);
				if(et_comments==null)
					et_comments="";
				
				ExternalTeacher externalTeacher = new ExternalTeacher(et_id, et_name, et_worknumber,
						 et_sex, et_birth, et_appointment, et_situation, et_term, et_departmentnumber,
						 et_education, et_topeducation, et_professional, et_subject, et_job,  et_teacher, 
						 et_area, et_serialnumber, et_deadline, et_comments, et_college, et_documentnumber, et_departmentname, isnull);

				externalTeachers.add(externalTeacher);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return externalTeachers;
	}

	@Override
	public int alterExternalTeacher(Map<String, String> valueMap, String id) {
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(ExternalTeacherTable.ET_ID, id);
		int result = updateRecord(ExternalTeacherTable.TABLE_NAME, valueMap,
				condition);
		return result;
	}

	@Override
	public boolean batchDelete(String[] etids) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();

		for (String etid : etids) {
			String sql = "delete from " + ExternalTeacherTable.TABLE_NAME
					+ " where " + ExternalTeacherTable.ET_ID + " = '" + etid + "'";
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
	public int getExternalTeacherCount(Map queryParams) {
		int count = 0;
		String sql = "select count(*) from " + ExternalTeacherTable.TABLE_NAME
				+ " where 1 = 1";
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
			return -1;
		}
		if(queryParams!=null)
			for (Object object : queryParams.keySet()) {
				String key = object.toString();
				String value = queryParams.get(key).toString();
				sql += String.format(" and  %s like  '%%%s%%' ", key, value);
			}
		sql += String.format(" or %s ='%s'", ExternalTeacherTable.ET_COLLEGE, "");

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
	public int addRecord(ExternalTeacher et) {
		int result = 0;

		String t_sql = "update " + ExternalTeacherTable.TABLE_NAME + " set "
				+ ExternalTeacherTable.ET_SERIALNUMBER + " = "
				+ ExternalTeacherTable.ET_SERIALNUMBER + " +1 where "
				+ ExternalTeacherTable.ET_SERIALNUMBER + ">=?";

		
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
			t_pstmt.setInt(1, et.getEt_serialnumber());
			
			//执行插入操作并更新
			result = t_pstmt.executeUpdate();
			
		} catch (SQLException e) {
			//事务回滚，不做插入操作
			e.printStackTrace();
			return 0;
		} finally {
			JdbcUtils_DBCP.release(connection2, t_pstmt, null);
		}

		//执行插入操作的语句无id和deadline
		String sql = "insert into " + ExternalTeacherTable.TABLE_NAME + "("
				+ ExternalTeacherTable.ET_NAME + "," 
				+ ExternalTeacherTable.ET_WORKNUMBER+ "," 
				+ ExternalTeacherTable.ET_SEX+ "," 
				+ ExternalTeacherTable.ET_BIRTH + ","
				+ ExternalTeacherTable.ET_APPOINTMENT + ","
				+ ExternalTeacherTable.ET_SITUATION + ","
				+ ExternalTeacherTable.ET_TERM + ","
				+ ExternalTeacherTable.ET_DEPARTMENTNUMBER + ","
				+ ExternalTeacherTable.ET_EDUCATION + ","
				+ ExternalTeacherTable.ET_TOPEDUCATION + ","
				+ ExternalTeacherTable.ET_PROFESSIONAL + ","
				+ ExternalTeacherTable.ET_SUBJECT + ","
				+ ExternalTeacherTable.ET_JOB + ","
				+ ExternalTeacherTable.ET_TEACHER + ","
				+ ExternalTeacherTable.ET_AREA + ","
				+ ExternalTeacherTable.ET_SERIALNUMBER + ","
				+ ExternalTeacherTable.ET_COMMENTS + ","
				+ ExternalTeacherTable.ET_COLLEGE + ","
				+ ExternalTeacherTable.ET_DOCUMENTNUMBER + ","
				+ ExternalTeacherTable.ET_DEPARTMENTNAME + ","
				+ ExternalTeacherTable.ISNULL
				+ ")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, et.getEt_name());
			pstmt.setString(2, et.getEt_worknumber());
			pstmt.setString(3, et.getEt_sex());
			pstmt.setString(4, et.getEt_birth().toString());
			pstmt.setString(5, et.getEt_appointment().toString());
			pstmt.setString(6, et.getEt_situation());
			pstmt.setInt(7, et.getEt_term());
			pstmt.setString(8, et.getEt_departmentnumber());
			pstmt.setString(9, et.getEt_education());
			pstmt.setString(10, et.getEt_topeducation());
			pstmt.setString(11, et.getEt_professional());
			pstmt.setString(12, et.getEt_subject());
			pstmt.setString(13, et.getEt_job());
			pstmt.setString(14, et.getEt_teacher());
			pstmt.setString(15, et.getEt_area());
			pstmt.setInt(16, et.getEt_serialnumber());
			pstmt.setString(17, et.getEt_comments());
			pstmt.setString(18, et.getEt_college());
			pstmt.setString(19, et.getEt_documentnumber());
			pstmt.setString(20, et.getEt_departmentname());
			pstmt.setInt(21, et.getIsnull());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			//事务回滚。不做插入操作
			e.printStackTrace();
			result = -1;
			
		} finally {
			JdbcUtils_DBCP.release(connection,pstmt,null);
		}
		return result;
	}

	@Override
	public List<ExternalTeacher> getExternalTeacher(int start, int end, String sortStr,
			String orderStr, Map<String, String> params, Date deadline) {
		String sql = " select tmp.* from ( " + " select * from "
				+ ExternalTeacherTable.TABLE_NAME + " where 1=1 ";
		if (deadline != null) {

			sql += String.format("and "+ExternalTeacherTable.ET_DEADLINE+" like  '%s%%' ", deadline);
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

		List<ExternalTeacher> externalTeachers = new ArrayList<ExternalTeacher>();
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
			Date temp = Date.valueOf("1800-1-1");
			while (resultSet.next()) {
				int et_id = resultSet.getInt(ExternalTeacherTable.ET_ID); 
				String et_name = resultSet.getString(ExternalTeacherTable.ET_NAME);
				String et_worknumber = resultSet.getString(ExternalTeacherTable.ET_WORKNUMBER);
				String et_sex = resultSet.getString(ExternalTeacherTable.ET_SEX);
				Date et_birth = resultSet.getDate(ExternalTeacherTable.ET_BIRTH);
				if(et_birth.equals(temp))
					et_birth = null;
				Date et_appointment = resultSet.getDate(ExternalTeacherTable.ET_APPOINTMENT);
				if(et_appointment.equals(temp))
					et_appointment = null;
				String et_situation = resultSet.getString(ExternalTeacherTable.ET_SITUATION);
				Integer et_term = resultSet.getInt(ExternalTeacherTable.ET_TERM);
				if(et_term==-999)
					et_term = null;
				String et_departmentnumber = resultSet.getString(ExternalTeacherTable.ET_DEPARTMENTNUMBER);
				String et_education = resultSet.getString(ExternalTeacherTable.ET_EDUCATION);
				String et_topeducation = resultSet.getString(ExternalTeacherTable.ET_TOPEDUCATION);
				String et_professional = resultSet.getString(ExternalTeacherTable.ET_PROFESSIONAL);
				String et_subject = resultSet.getString(ExternalTeacherTable.ET_SUBJECT);
				String et_job = resultSet.getString(ExternalTeacherTable.ET_JOB);
				String et_teacher = resultSet.getString(ExternalTeacherTable.ET_TEACHER);
				String et_area = resultSet.getString(ExternalTeacherTable.ET_AREA);
				int et_serialnumber = resultSet.getInt(ExternalTeacherTable.ET_SERIALNUMBER);
				Date et_deadline = resultSet.getDate(ExternalTeacherTable.ET_DEADLINE);
				String et_comments = resultSet.getString(ExternalTeacherTable.ET_COMMENTS);
				String et_college = resultSet.getString(ExternalTeacherTable.ET_COLLEGE);
				String et_documentnumber = resultSet.getString(ExternalTeacherTable.ET_DOCUMENTNUMBER);
				String et_departmentname = resultSet.getString(ExternalTeacherTable.ET_DEPARTMENTNAME);
				int isnull = resultSet.getInt(ExternalTeacherTable.ISNULL);
				
				ExternalTeacher externalTeacher = new ExternalTeacher(et_id, et_name, et_worknumber,
						 et_sex, et_birth, et_appointment, et_situation, et_term, et_departmentnumber,
						 et_education, et_topeducation, et_professional, et_subject, et_job,  et_teacher, 
						 et_area, et_serialnumber, et_deadline, et_comments, et_college, et_documentnumber, et_departmentname, isnull);

				externalTeachers.add(externalTeacher);
			}
			return externalTeachers;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
	}
	
	@Override
	public int getCountByRange(String param, Date start, Date end)
	{
		int count = 0;
		String sql = "select count(*) from " + ExternalTeacherTable.TABLE_NAME + String.format(" where unix_timestamp( %s ) between unix_timestamp( '%s') and unix_timestamp( '%s' ) ",param,start, end);
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
	public void deleteByCollegeandDeadline(String college, Date deadline)
			throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();
		String sql = "delete from " + ExternalTeacherTable.TABLE_NAME
				+ " where " + ExternalTeacherTable.ET_COLLEGE + " = '" + college + "'" +" and et_deadline is null ";
		System.out.println(sql);
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils_DBCP.release(connection, stmt, null);
		}
		
	}
	
	// 得到所有单位
	@Override
	public List<ExternalTeacher> getAllExternalTeacher() {
		String sql = " select * from " + ExternalTeacherTable.TABLE_NAME
				+ " where 1=1 " + " order by " + ExternalTeacherTable.ET_ID;
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
			List<ExternalTeacher> externalTeacherList = new ArrayList<ExternalTeacher>();
			Date temp = Date.valueOf("1800-1-1");
			while (resultSet.next()) {
				int et_id = resultSet.getInt(ExternalTeacherTable.ET_ID); 
				String et_name = resultSet.getString(ExternalTeacherTable.ET_NAME);
				String et_worknumber = resultSet.getString(ExternalTeacherTable.ET_WORKNUMBER);
				String et_sex = resultSet.getString(ExternalTeacherTable.ET_SEX);
				Date et_birth = resultSet.getDate(ExternalTeacherTable.ET_BIRTH);
				if(et_birth.equals(temp))
					et_birth = null;
				Date et_appointment = resultSet.getDate(ExternalTeacherTable.ET_APPOINTMENT);
				if(et_appointment.equals(temp))
					et_appointment = null;
				String et_situation = resultSet.getString(ExternalTeacherTable.ET_SITUATION);
				Integer et_term = resultSet.getInt(ExternalTeacherTable.ET_TERM);
				if(et_term==-999)
					et_term = null;
				String et_departmentnumber = resultSet.getString(ExternalTeacherTable.ET_DEPARTMENTNUMBER);
				String et_education = resultSet.getString(ExternalTeacherTable.ET_EDUCATION);
				String et_topeducation = resultSet.getString(ExternalTeacherTable.ET_TOPEDUCATION);
				String et_professional = resultSet.getString(ExternalTeacherTable.ET_PROFESSIONAL);
				String et_subject = resultSet.getString(ExternalTeacherTable.ET_SUBJECT);
				String et_job = resultSet.getString(ExternalTeacherTable.ET_JOB);
				String et_teacher = resultSet.getString(ExternalTeacherTable.ET_TEACHER);
				String et_area = resultSet.getString(ExternalTeacherTable.ET_AREA);
				int et_serialnumber = resultSet.getInt(ExternalTeacherTable.ET_SERIALNUMBER);
				Date et_deadline = resultSet.getDate(ExternalTeacherTable.ET_DEADLINE);
				String et_comments = resultSet.getString(ExternalTeacherTable.ET_COMMENTS);
				String et_college = resultSet.getString(ExternalTeacherTable.ET_COLLEGE);
				String et_documentnumber = resultSet.getString(ExternalTeacherTable.ET_DOCUMENTNUMBER);
				String et_departmentname = resultSet.getString(ExternalTeacherTable.ET_DEPARTMENTNAME);
				int isnull = resultSet.getInt(ExternalTeacherTable.ISNULL);
				
				ExternalTeacher externalTeacher = new ExternalTeacher(et_id, et_name, et_worknumber,
						 et_sex, et_birth, et_appointment, et_situation, et_term, et_departmentnumber,
						 et_education, et_topeducation, et_professional, et_subject, et_job,  et_teacher, 
						 et_area, et_serialnumber, et_deadline, et_comments, et_college, et_documentnumber, et_departmentname, isnull);
				
				externalTeacherList.add(externalTeacher);
			}
			return externalTeacherList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
	}
}
