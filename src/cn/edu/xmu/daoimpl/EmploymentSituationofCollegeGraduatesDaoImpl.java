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

import cn.edu.xmu.dao.EmploymentSituationofCollegeGraduatesDao;
import cn.edu.xmu.entity.EmploymentSituationofCollegeGraduates;
import cn.edu.xmu.table.EmploymentSituationofCollegeGraduatesTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;


/**
 * @author zhantu
 * 应届本科毕业生就业情况（时点）  实体类功能 ——接口实现
 * date 2015-07-05
 */

public class EmploymentSituationofCollegeGraduatesDaoImpl extends BaseDaoImpl<EmploymentSituationofCollegeGraduates>implements EmploymentSituationofCollegeGraduatesDao{

	@Override
	public int addRecord(EmploymentSituationofCollegeGraduates escg) {
		
		int result = 0;

		String t_sql = "update " + EmploymentSituationofCollegeGraduatesTable.TABLE_NAME + " set "
				+ EmploymentSituationofCollegeGraduatesTable.ESCG_SERIALNUMBER + " = "
				+ EmploymentSituationofCollegeGraduatesTable.ESCG_SERIALNUMBER + " +1 where "
				+ EmploymentSituationofCollegeGraduatesTable.ESCG_SERIALNUMBER + ">=?";

		
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
			t_pstmt.setInt(1, escg.getEscg_serialnumber());
			
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
		String sql = "insert into " + EmploymentSituationofCollegeGraduatesTable.TABLE_NAME + "("
				+ EmploymentSituationofCollegeGraduatesTable.ESCG_RECOMMENDGRADUATE+ "," 
				+ EmploymentSituationofCollegeGraduatesTable.ESCG_POSTGRADUATEEXAMSUM + ","
				+ EmploymentSituationofCollegeGraduatesTable.ESCG_POSTGRADUATEEXAMIN + ","
				+ EmploymentSituationofCollegeGraduatesTable.ESCG_POSTGRADUATEEXAMOUT + ","
				+ EmploymentSituationofCollegeGraduatesTable.ESCG_STUDYABROAD + ","
				+ EmploymentSituationofCollegeGraduatesTable.ESCG_EMPLOYMENTSUM + ","
				+ EmploymentSituationofCollegeGraduatesTable.ESCG_GOV + ","
				+ EmploymentSituationofCollegeGraduatesTable.ESCG_INSTITUTION + ","
				+ EmploymentSituationofCollegeGraduatesTable.ESCG_ENTERPRISE + ","
				+ EmploymentSituationofCollegeGraduatesTable.ESCG_TROOPS + ","
				+ EmploymentSituationofCollegeGraduatesTable.ESCG_FLEXIBLEEMPLOYMENT + ","
				+ EmploymentSituationofCollegeGraduatesTable.ESCE_ENTRANCE + ","
				+ EmploymentSituationofCollegeGraduatesTable.ESCG_NATIONALLOCALPROJECTEMPLOYMENT + ","
				+ EmploymentSituationofCollegeGraduatesTable.ESCG_OTHERS + ","
				
				+ EmploymentSituationofCollegeGraduatesTable.ESCG_SERIALNUMBER + ","
				+ EmploymentSituationofCollegeGraduatesTable.ESCG_COLLEGE + ","
				+ EmploymentSituationofCollegeGraduatesTable.ESCG_COMMENTS + ","
				+ EmploymentSituationofCollegeGraduatesTable.ISNULL
				+ ")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, escg.getEscg_recommendgraduate());
			pstmt.setInt(2, escg.getEscg_postgraduateexamsum());
			pstmt.setInt(3, escg.getEscg_postgraduateexamin());
			pstmt.setInt(4, escg.getEscg_postgraduateexamout());
			pstmt.setInt(5, escg.getEscg_studyabroad());
			pstmt.setInt(6, escg.getEscg_employmentsum());
			pstmt.setInt(7, escg.getEscg_gov());
			pstmt.setInt(8, escg.getEscg_institution());
			pstmt.setInt(9, escg.getEscg_enterprise());
			pstmt.setInt(10, escg.getEscg_troops());
			
			pstmt.setInt(11, escg.getEscg_flexibleemployment());
			pstmt.setInt(12, escg.getEsce_entrance());
			pstmt.setInt(13, escg.getEscg_nationallocalprojectemployment());
			pstmt.setInt(14, escg.getEscg_others());
			pstmt.setInt(15, escg.getEscg_serialnumber());
			pstmt.setString(16, escg.getEscg_college());
			pstmt.setString(17, escg.getEscg_comments());
			pstmt.setInt(18, escg.getIsnull());
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
	public int getEmploymentSituationofCollegeGraduatesCount(Map queryParams) {
		
		int count = 0;
		//获取条件的语句
		String sql = "select count(*) from " + EmploymentSituationofCollegeGraduatesTable.TABLE_NAME
				+ " where 1 = 1";
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}


		if (queryParams != null) {
			for (Object object : queryParams.keySet()) {
				String key = object.toString();
				String value = queryParams.get(key).toString();
				sql += String.format(" and  %s like  '%s%%' ", key, value);
			}
		}
		
		sql += String.format(" or %s ='%s'", EmploymentSituationofCollegeGraduatesTable.ESCG_COLLEGE, "");
		
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
	public List<EmploymentSituationofCollegeGraduates> getAllEmploymentSituationofCollegeGraduates(int start, int end,
			String sortStr, String orderStr, Map queryParams) {
		
		String sql = " select tmp.* from ( " + " select * from "
				+ EmploymentSituationofCollegeGraduatesTable.TABLE_NAME + " where 1=1 ";
		if (queryParams != null) {
			for (Object object : queryParams.keySet()) {
				String key = object.toString();
				String value = queryParams.get(key).toString();
				sql += String.format(" and %s like  '%%%s%%'", key, value);
			}
		}
		
		sql += " order by " + sortStr + " " + orderStr + " ) tmp limit "
				+ start + " ," + end;

		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		List<EmploymentSituationofCollegeGraduates> escgs = new ArrayList<EmploymentSituationofCollegeGraduates>();
		try {
			
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int escg_id = resultSet.getInt(EmploymentSituationofCollegeGraduatesTable.ESCG_ID);
				Integer escg_recommendgraduate = resultSet.getInt(EmploymentSituationofCollegeGraduatesTable.ESCG_RECOMMENDGRADUATE);
				if(escg_recommendgraduate==-999)
					escg_recommendgraduate = null;
				Integer escg_postgraduateexamsum = resultSet.getInt(EmploymentSituationofCollegeGraduatesTable.ESCG_POSTGRADUATEEXAMSUM);
				if(escg_postgraduateexamsum==-999)
					escg_postgraduateexamsum = null;
				Integer escg_postgraduateexamin = resultSet.getInt(EmploymentSituationofCollegeGraduatesTable.ESCG_POSTGRADUATEEXAMIN);
				if(escg_postgraduateexamin==-999)
					escg_postgraduateexamin = null;
				Integer escg_postgraduateexamout = resultSet.getInt(EmploymentSituationofCollegeGraduatesTable.ESCG_POSTGRADUATEEXAMOUT);
				if(escg_postgraduateexamout==-999)
					escg_postgraduateexamout = null;
				Integer escg_studyabroad = resultSet.getInt(EmploymentSituationofCollegeGraduatesTable.ESCG_STUDYABROAD);
				if(escg_studyabroad==-999)
					escg_studyabroad = null;
				Integer escg_employmentsum = resultSet.getInt(EmploymentSituationofCollegeGraduatesTable.ESCG_EMPLOYMENTSUM);
				if(escg_employmentsum==-999)
					escg_employmentsum = null;
				Integer escg_gov = resultSet.getInt(EmploymentSituationofCollegeGraduatesTable.ESCG_GOV);
				if(escg_gov==-999)
					escg_gov = null;
				Integer escg_institution = resultSet.getInt(EmploymentSituationofCollegeGraduatesTable.ESCG_INSTITUTION);
				if(escg_institution==-999)
					escg_institution = null;
				Integer escg_enterprise = resultSet.getInt(EmploymentSituationofCollegeGraduatesTable.ESCG_ENTERPRISE);
				if(escg_enterprise==-999)
					escg_enterprise = null;
				Integer escg_troops = resultSet.getInt(EmploymentSituationofCollegeGraduatesTable.ESCG_TROOPS);
				if(escg_troops==-999)
					escg_troops = null;
				Integer escg_flexibleemployment = resultSet.getInt(EmploymentSituationofCollegeGraduatesTable.ESCG_FLEXIBLEEMPLOYMENT);
				if(escg_flexibleemployment==-999)
					escg_flexibleemployment = null;
				Integer esce_entrance = resultSet.getInt(EmploymentSituationofCollegeGraduatesTable.ESCE_ENTRANCE);
				if(esce_entrance==-999)
					esce_entrance = null;
				Integer escg_nationallocalprojectemployment = resultSet.getInt(EmploymentSituationofCollegeGraduatesTable.ESCG_NATIONALLOCALPROJECTEMPLOYMENT);
				if(escg_nationallocalprojectemployment==-999)
					escg_nationallocalprojectemployment = null;
				Integer escg_others = resultSet.getInt(EmploymentSituationofCollegeGraduatesTable.ESCG_OTHERS);
				if(escg_others==-999)
					escg_others = null;
				int escg_serialnumber = resultSet.getInt(EmploymentSituationofCollegeGraduatesTable.ESCG_SERIALNUMBER);
				Date escg_deadline = resultSet.getDate(EmploymentSituationofCollegeGraduatesTable.ESCG_DEADLINE);
				String escg_college = resultSet.getString(EmploymentSituationofCollegeGraduatesTable.ESCG_COLLEGE);
				String escg_comments = resultSet.getString(EmploymentSituationofCollegeGraduatesTable.ESCG_COMMENTS);
				int isnull = resultSet.getInt(EmploymentSituationofCollegeGraduatesTable.ISNULL);
				if(escg_comments==null)
					escg_comments="";
				
				EmploymentSituationofCollegeGraduates escg = new EmploymentSituationofCollegeGraduates(escg_id,
						escg_recommendgraduate, escg_postgraduateexamsum,
						escg_postgraduateexamin, escg_postgraduateexamout,
						escg_studyabroad, escg_employmentsum, escg_gov,
						escg_institution, escg_enterprise, escg_troops,
						escg_flexibleemployment, esce_entrance,
						escg_nationallocalprojectemployment, escg_others,
						escg_serialnumber, escg_deadline, escg_college,
						escg_comments, isnull);
				
				escgs.add(escg);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return escgs;
	}

	@Override
	public int alterEmploymentSituationofCollegeGraduates(Map<String, String> valueMap, String id) {
		
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(EmploymentSituationofCollegeGraduatesTable.ESCG_ID, id);
		int result = updateRecord(EmploymentSituationofCollegeGraduatesTable.TABLE_NAME, valueMap,
				condition);
		return result;
	}

	@Override
	public boolean batchDelete(String[] escgids) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();

		for (String escgid : escgids) {
			String sql = "delete from " + EmploymentSituationofCollegeGraduatesTable.TABLE_NAME
					+ " where " + EmploymentSituationofCollegeGraduatesTable.ESCG_ID + " = '" + escgid + "'";
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
	public List<EmploymentSituationofCollegeGraduates> getEmploymentSituationofCollegeGraduates(int start, int end,
			String sortStr, String orderStr, Map<String, String> params,
			Date deadline) {
		String sql = " select tmp.* from ( " + " select * from "
				+ EmploymentSituationofCollegeGraduatesTable.TABLE_NAME + " where 1=1 ";
		if (deadline != null) {

			sql += String.format("and "+EmploymentSituationofCollegeGraduatesTable.ESCG_DEADLINE+" like  '%s%%' ", deadline);
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

		List<EmploymentSituationofCollegeGraduates> employmentSituationofCollegeGraduatess = new ArrayList<EmploymentSituationofCollegeGraduates>();
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
				int escg_id = resultSet.getInt(EmploymentSituationofCollegeGraduatesTable.ESCG_ID);
				Integer escg_recommendgraduate = resultSet.getInt(EmploymentSituationofCollegeGraduatesTable.ESCG_RECOMMENDGRADUATE);
				if(escg_recommendgraduate==-999)
					escg_recommendgraduate = null;
				Integer escg_postgraduateexamsum = resultSet.getInt(EmploymentSituationofCollegeGraduatesTable.ESCG_POSTGRADUATEEXAMSUM);
				if(escg_postgraduateexamsum==-999)
					escg_postgraduateexamsum = null;
				Integer escg_postgraduateexamin = resultSet.getInt(EmploymentSituationofCollegeGraduatesTable.ESCG_POSTGRADUATEEXAMIN);
				if(escg_postgraduateexamin==-999)
					escg_postgraduateexamin = null;
				Integer escg_postgraduateexamout = resultSet.getInt(EmploymentSituationofCollegeGraduatesTable.ESCG_POSTGRADUATEEXAMOUT);
				if(escg_postgraduateexamout==-999)
					escg_postgraduateexamout = null;
				Integer escg_studyabroad = resultSet.getInt(EmploymentSituationofCollegeGraduatesTable.ESCG_STUDYABROAD);
				if(escg_studyabroad==-999)
					escg_studyabroad = null;
				Integer escg_employmentsum = resultSet.getInt(EmploymentSituationofCollegeGraduatesTable.ESCG_EMPLOYMENTSUM);
				if(escg_employmentsum==-999)
					escg_employmentsum = null;
				Integer escg_gov = resultSet.getInt(EmploymentSituationofCollegeGraduatesTable.ESCG_GOV);
				if(escg_gov==-999)
					escg_gov = null;
				Integer escg_institution = resultSet.getInt(EmploymentSituationofCollegeGraduatesTable.ESCG_INSTITUTION);
				if(escg_institution==-999)
					escg_institution = null;
				Integer escg_enterprise = resultSet.getInt(EmploymentSituationofCollegeGraduatesTable.ESCG_ENTERPRISE);
				if(escg_enterprise==-999)
					escg_enterprise = null;
				Integer escg_troops = resultSet.getInt(EmploymentSituationofCollegeGraduatesTable.ESCG_TROOPS);
				if(escg_troops==-999)
					escg_troops = null;
				Integer escg_flexibleemployment = resultSet.getInt(EmploymentSituationofCollegeGraduatesTable.ESCG_FLEXIBLEEMPLOYMENT);
				if(escg_flexibleemployment==-999)
					escg_flexibleemployment = null;
				Integer esce_entrance = resultSet.getInt(EmploymentSituationofCollegeGraduatesTable.ESCE_ENTRANCE);
				if(esce_entrance==-999)
					esce_entrance = null;
				Integer escg_nationallocalprojectemployment = resultSet.getInt(EmploymentSituationofCollegeGraduatesTable.ESCG_NATIONALLOCALPROJECTEMPLOYMENT);
				if(escg_nationallocalprojectemployment==-999)
					escg_nationallocalprojectemployment = null;
				Integer escg_others = resultSet.getInt(EmploymentSituationofCollegeGraduatesTable.ESCG_OTHERS);
				if(escg_others==-999)
					escg_others = null;
				int escg_serialnumber = resultSet.getInt(EmploymentSituationofCollegeGraduatesTable.ESCG_SERIALNUMBER);
				Date escg_deadline = resultSet.getDate(EmploymentSituationofCollegeGraduatesTable.ESCG_DEADLINE);
				String escg_college = resultSet.getString(EmploymentSituationofCollegeGraduatesTable.ESCG_COLLEGE);
				String escg_comments = resultSet.getString(EmploymentSituationofCollegeGraduatesTable.ESCG_COMMENTS);
				int isnull = resultSet.getInt(EmploymentSituationofCollegeGraduatesTable.ISNULL);
				
				EmploymentSituationofCollegeGraduates escg = new EmploymentSituationofCollegeGraduates(escg_id,
						escg_recommendgraduate, escg_postgraduateexamsum,
						escg_postgraduateexamin, escg_postgraduateexamout,
						escg_studyabroad, escg_employmentsum, escg_gov,
						escg_institution, escg_enterprise, escg_troops,
						escg_flexibleemployment, esce_entrance,
						escg_nationallocalprojectemployment, escg_others,
						escg_serialnumber, escg_deadline, escg_college,
						escg_comments, isnull);

				employmentSituationofCollegeGraduatess.add(escg);
			}
			return employmentSituationofCollegeGraduatess;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
	}
	
	@Override
	public void deleteByCollegeandDeadline(String college, Date deadline)
			throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();
		String sql = "delete from " + EmploymentSituationofCollegeGraduatesTable.TABLE_NAME
				+ " where " + EmploymentSituationofCollegeGraduatesTable.ESCG_COLLEGE + " = '" + college + "'" +" and escg_deadline is null ";
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
	public List<EmploymentSituationofCollegeGraduates> getAllEmploymentSituationofCollegeGraduates() {
		String sql = " select * from " + EmploymentSituationofCollegeGraduatesTable.TABLE_NAME
				+ " where 1=1 " + " order by " + EmploymentSituationofCollegeGraduatesTable.ESCG_ID;
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
			List<EmploymentSituationofCollegeGraduates> employmentSituationofCollegeGraduatesList = new ArrayList<EmploymentSituationofCollegeGraduates>();
			while (resultSet.next()) {
				int escg_id = resultSet.getInt(EmploymentSituationofCollegeGraduatesTable.ESCG_ID);
				Integer escg_recommendgraduate = resultSet.getInt(EmploymentSituationofCollegeGraduatesTable.ESCG_RECOMMENDGRADUATE);
				if(escg_recommendgraduate==-999)
					escg_recommendgraduate = null;
				Integer escg_postgraduateexamsum = resultSet.getInt(EmploymentSituationofCollegeGraduatesTable.ESCG_POSTGRADUATEEXAMSUM);
				if(escg_postgraduateexamsum==-999)
					escg_postgraduateexamsum = null;
				Integer escg_postgraduateexamin = resultSet.getInt(EmploymentSituationofCollegeGraduatesTable.ESCG_POSTGRADUATEEXAMIN);
				if(escg_postgraduateexamin==-999)
					escg_postgraduateexamin = null;
				Integer escg_postgraduateexamout = resultSet.getInt(EmploymentSituationofCollegeGraduatesTable.ESCG_POSTGRADUATEEXAMOUT);
				if(escg_postgraduateexamout==-999)
					escg_postgraduateexamout = null;
				Integer escg_studyabroad = resultSet.getInt(EmploymentSituationofCollegeGraduatesTable.ESCG_STUDYABROAD);
				if(escg_studyabroad==-999)
					escg_studyabroad = null;
				Integer escg_employmentsum = resultSet.getInt(EmploymentSituationofCollegeGraduatesTable.ESCG_EMPLOYMENTSUM);
				if(escg_employmentsum==-999)
					escg_employmentsum = null;
				Integer escg_gov = resultSet.getInt(EmploymentSituationofCollegeGraduatesTable.ESCG_GOV);
				if(escg_gov==-999)
					escg_gov = null;
				Integer escg_institution = resultSet.getInt(EmploymentSituationofCollegeGraduatesTable.ESCG_INSTITUTION);
				if(escg_institution==-999)
					escg_institution = null;
				Integer escg_enterprise = resultSet.getInt(EmploymentSituationofCollegeGraduatesTable.ESCG_ENTERPRISE);
				if(escg_enterprise==-999)
					escg_enterprise = null;
				Integer escg_troops = resultSet.getInt(EmploymentSituationofCollegeGraduatesTable.ESCG_TROOPS);
				if(escg_troops==-999)
					escg_troops = null;
				Integer escg_flexibleemployment = resultSet.getInt(EmploymentSituationofCollegeGraduatesTable.ESCG_FLEXIBLEEMPLOYMENT);
				if(escg_flexibleemployment==-999)
					escg_flexibleemployment = null;
				Integer esce_entrance = resultSet.getInt(EmploymentSituationofCollegeGraduatesTable.ESCE_ENTRANCE);
				if(esce_entrance==-999)
					esce_entrance = null;
				Integer escg_nationallocalprojectemployment = resultSet.getInt(EmploymentSituationofCollegeGraduatesTable.ESCG_NATIONALLOCALPROJECTEMPLOYMENT);
				if(escg_nationallocalprojectemployment==-999)
					escg_nationallocalprojectemployment = null;
				Integer escg_others = resultSet.getInt(EmploymentSituationofCollegeGraduatesTable.ESCG_OTHERS);
				if(escg_others==-999)
					escg_others = null;
				int escg_serialnumber = resultSet.getInt(EmploymentSituationofCollegeGraduatesTable.ESCG_SERIALNUMBER);
				Date escg_deadline = resultSet.getDate(EmploymentSituationofCollegeGraduatesTable.ESCG_DEADLINE);
				String escg_college = resultSet.getString(EmploymentSituationofCollegeGraduatesTable.ESCG_COLLEGE);
				String escg_comments = resultSet.getString(EmploymentSituationofCollegeGraduatesTable.ESCG_COMMENTS);
				int isnull = resultSet.getInt(EmploymentSituationofCollegeGraduatesTable.ISNULL);
				
				EmploymentSituationofCollegeGraduates escg = new EmploymentSituationofCollegeGraduates(escg_id,
						escg_recommendgraduate, escg_postgraduateexamsum,
						escg_postgraduateexamin, escg_postgraduateexamout,
						escg_studyabroad, escg_employmentsum, escg_gov,
						escg_institution, escg_enterprise, escg_troops,
						escg_flexibleemployment, esce_entrance,
						escg_nationallocalprojectemployment, escg_others,
						escg_serialnumber, escg_deadline, escg_college,
						escg_comments,isnull);
				
				employmentSituationofCollegeGraduatesList.add(escg);
			}
			return employmentSituationofCollegeGraduatesList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
	}
}
