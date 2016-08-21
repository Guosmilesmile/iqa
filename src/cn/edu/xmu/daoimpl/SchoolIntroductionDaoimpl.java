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

import cn.edu.xmu.dao.SchoolIntroductionDao;
import cn.edu.xmu.entity.SchoolIntroduction;
import cn.edu.xmu.table.SchoolIntroductionTable;
import cn.edu.xmu.table.TeachScientificTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;
/**
 * 
 * @author Luo
 * 学校概况  实体类功能 ——接口实现
 * date 2015-06-30
 */
public class SchoolIntroductionDaoimpl extends BaseDaoImpl<SchoolIntroduction>implements SchoolIntroductionDao {

	@Override
	public int addRecord(SchoolIntroduction si) {
		int result = 0;

		String t_sql = "update " + SchoolIntroductionTable.TABLE_NAME + " set "
				+ SchoolIntroductionTable.SI_SERIALNUMBER + " = "
				+ SchoolIntroductionTable.SI_SERIALNUMBER + " +1 where "
				+ SchoolIntroductionTable.SI_SERIALNUMBER + ">=?";

		
		Connection connection2 = null;
		try {
			//连接池取得对象连接
			connection2 = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		PreparedStatement t_pstmt = null;
		try {
			//获取操作对象
			t_pstmt = connection2.prepareStatement(t_sql);
			t_pstmt.setInt(1, si.getSi_serialnumber());
			
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
		String sql = "insert into " + SchoolIntroductionTable.TABLE_NAME + "("
				+ SchoolIntroductionTable.SI_SCHOOLNAME
				+ "," + SchoolIntroductionTable.SI_SCHOOLCODE + ","
				+ SchoolIntroductionTable.SI_ENGLISHNAME + ","
				+ SchoolIntroductionTable.SI_CAMPUSTYPE + ","
				+ SchoolIntroductionTable.SI_CAMPUSNATURE + ","
				+ SchoolIntroductionTable.SI_HOST + ","
				+ SchoolIntroductionTable.SI_DEPARTMENT + ","
				+ SchoolIntroductionTable.SI_WEBSITE + ","
				+ SchoolIntroductionTable.SI_ADMISSIONSBATCHES + ","
				+ SchoolIntroductionTable.SI_EDUCATIONSTARTYEAR + ","
				+ SchoolIntroductionTable.SI_SERIALNUMBER + ","
				+ SchoolIntroductionTable.SI_COLLEGE + ","
				+ SchoolIntroductionTable.SI_COMMENTS+","
				+ SchoolIntroductionTable.ISNULL
				+ ")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, si.getSi_schoolname());
			pstmt.setString(2, si.getSi_schoolcode());
			pstmt.setString(3, si.getSi_Englishname());
			pstmt.setString(4, si.getSi_campustype());
			pstmt.setString(5, si.getSi_campusnature());
			pstmt.setString(6, si.getSi_host());
			pstmt.setString(7, si.getSi_department());
			pstmt.setString(8, si.getSi_website());
			pstmt.setString(9, si.getSi_admissionsbatches());
			pstmt.setString(10, si.getSi_educationstartyear());
			pstmt.setInt(11, si.getSi_serialnumber());
			pstmt.setString(12, si.getSi_college());
			pstmt.setString(13, si.getSi_comments());
			pstmt.setInt(14, si.getIsnull());
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
	public boolean batchDelete(String[] siuids) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();

		for (String siuid : siuids) {
			String sql = "delete from " + SchoolIntroductionTable.TABLE_NAME
					+ " where " + SchoolIntroductionTable.SI_ID + " = '" + siuid + "'";
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
	public int alterSchoolIntroduction(Map<String, String> valueMap, String id) {
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(SchoolIntroductionTable.SI_ID, id);
		int result = updateRecord(SchoolIntroductionTable.TABLE_NAME, valueMap,
				condition);
		return result;
	}

	@Override
	public int getSchoolIntroductionCount(Map queryParams) {
		int count = 0;
		//获取条件的语句
		String sql = "select count(*) from " + SchoolIntroductionTable.TABLE_NAME
				+ " where 1 = 1";
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		for (Object object : queryParams.keySet()) {
			String key = object.toString();
			String value = queryParams.get(key).toString();
			sql += String.format(" and  %s like  '%s%%' ", key, value);
		}
		sql += String.format(" or %s ='%s'", SchoolIntroductionTable.SI_COLLEGE, "");
		
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
		
		return count;
	}

	@Override
	public List<SchoolIntroduction> getAllSchoolIntroduction(int start,
			int end, String sortStr, String orderStr, Map queryParams) {
		String sql = " select tmp.* from ( " + " select * from "
				+ SchoolIntroductionTable.TABLE_NAME + " where 1=1 ";
		
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
		
		List<SchoolIntroduction> sis = new ArrayList<SchoolIntroduction>();
		try {
			
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int si_id = resultSet.getInt(SchoolIntroductionTable.SI_ID);
				String si_schoolname = resultSet.getString(SchoolIntroductionTable.SI_SCHOOLNAME);
				String si_schoolcode = resultSet.getString(SchoolIntroductionTable.SI_SCHOOLCODE);
				String si_Englishname = resultSet.getString(SchoolIntroductionTable.SI_ENGLISHNAME);
				String si_campustype = resultSet.getString(SchoolIntroductionTable.SI_CAMPUSTYPE);
				String si_campusnature = resultSet.getString(SchoolIntroductionTable.SI_CAMPUSNATURE);
				String si_host = resultSet.getString(SchoolIntroductionTable.SI_HOST);
				String si_department = resultSet.getString(SchoolIntroductionTable.SI_DEPARTMENT);
				String si_website = resultSet.getString(SchoolIntroductionTable.SI_WEBSITE);
				String si_admissionsbatches = resultSet.getString(SchoolIntroductionTable.SI_ADMISSIONSBATCHES);
				String si_educationstartyear = resultSet.getString(SchoolIntroductionTable.SI_EDUCATIONSTARTYEAR);
				int si_serialnumber = resultSet.getInt(SchoolIntroductionTable.SI_SERIALNUMBER);
				String si_comments = resultSet.getString(SchoolIntroductionTable.SI_COMMENTS);
				String si_college = resultSet.getString(SchoolIntroductionTable.SI_COLLEGE);
				
				int isnull = resultSet.getInt(SchoolIntroductionTable.ISNULL);
				if(si_comments==null)
					si_comments="";
				
				SchoolIntroduction si = new SchoolIntroduction(si_id, si_schoolname,
						si_schoolcode, si_Englishname, si_campustype, si_campusnature,si_host,si_department,si_website,si_admissionsbatches,si_educationstartyear,si_serialnumber,si_comments,isnull,si_college);
				
				sis.add(si);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return sis;
	}

	@Override
	public List<SchoolIntroduction> getAllSchoolIntroduction() {
		String sql = " select * from "
				+ SchoolIntroductionTable.TABLE_NAME + " where 1=1 ";
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		List<SchoolIntroduction> sis = new ArrayList<SchoolIntroduction>();
		try {
			
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int si_id = resultSet.getInt(SchoolIntroductionTable.SI_ID);
				String si_schoolname = resultSet.getString(SchoolIntroductionTable.SI_SCHOOLNAME);
				String si_schoolcode = resultSet.getString(SchoolIntroductionTable.SI_SCHOOLCODE);
				String si_Englishname = resultSet.getString(SchoolIntroductionTable.SI_ENGLISHNAME);
				String si_campustype = resultSet.getString(SchoolIntroductionTable.SI_CAMPUSTYPE);
				String si_campusnature = resultSet.getString(SchoolIntroductionTable.SI_CAMPUSNATURE);
				String si_host = resultSet.getString(SchoolIntroductionTable.SI_HOST);
				String si_department = resultSet.getString(SchoolIntroductionTable.SI_DEPARTMENT);
				String si_website = resultSet.getString(SchoolIntroductionTable.SI_WEBSITE);
				String si_admissionsbatches = resultSet.getString(SchoolIntroductionTable.SI_ADMISSIONSBATCHES);
				String si_educationstartyear = resultSet.getString(SchoolIntroductionTable.SI_EDUCATIONSTARTYEAR);
				int si_serialnumber = resultSet.getInt(SchoolIntroductionTable.SI_SERIALNUMBER);
				String si_comments = resultSet.getString(SchoolIntroductionTable.SI_COMMENTS);
				String si_college = resultSet.getString(SchoolIntroductionTable.SI_COLLEGE);
				int isnull = resultSet.getInt(SchoolIntroductionTable.ISNULL);
				if(si_comments==null)
					si_comments="";
				
				SchoolIntroduction si = new SchoolIntroduction(si_id, si_schoolname,
						si_schoolcode, si_Englishname, si_campustype, si_campusnature,si_host,si_department,si_website,si_admissionsbatches,si_educationstartyear,si_serialnumber,si_comments,isnull,si_college);
				
				sis.add(si);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return sis;
	}

	@Override
	public void deleteByCollegeandDeadline(String college, Date deadline)
			throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();
		String sql = "delete from " + SchoolIntroductionTable.TABLE_NAME
				+ " where " + SchoolIntroductionTable.SI_COLLEGE + " = '" + college + "'" +" and si_deadline is null ";
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
