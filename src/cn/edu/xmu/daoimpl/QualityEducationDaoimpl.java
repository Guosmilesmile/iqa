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

import cn.edu.xmu.dao.QualityEducationDao;
import cn.edu.xmu.entity.QualityEducation;
import cn.edu.xmu.table.QualityEducationTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;

public class QualityEducationDaoimpl extends BaseDaoImpl<QualityEducation>implements QualityEducationDao {

	@Override
	public int addRecord(QualityEducation qe) {
		int result = 0;

		String t_sql = "update " + QualityEducationTable.TABLE_NAME + " set "
				+ QualityEducationTable.QE_SERIALNUMBER + " = "
				+ QualityEducationTable.QE_SERIALNUMBER + " +1 where "
				+ QualityEducationTable.QE_SERIALNUMBER + ">=?";

		
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
			t_pstmt.setInt(1, qe.getQe_serialnumber());
			
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
		String sql = "insert into " + QualityEducationTable.TABLE_NAME + "("
				+ QualityEducationTable.QE_DIATHESISDEVELOPING
				+ "," + QualityEducationTable.QE_QUALIFICATIONTRAINING+ ","
				+ QualityEducationTable.QE_COURSE + ","
				+ QualityEducationTable.QE_BASECOUNT + ","
				+ QualityEducationTable.QE_SERIALNUMBER + ","
				+ QualityEducationTable.QE_COLLEGE + ","
				+ QualityEducationTable.QE_COMMENTS+","
				+ QualityEducationTable.ISNULL
				+ ")values(?,?,?,?,?,?,?,?)";

		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, qe.getQe_diathesisdeveloping());
			pstmt.setInt(2, qe.getQe_qualificationtraining());
			pstmt.setInt(3, qe.getQe_course());
			pstmt.setInt(4, qe.getQe_basecount());
			pstmt.setInt(5, qe.getQe_serialnumber());
			pstmt.setString(6, qe.getQe_college());
			pstmt.setString(7, qe.getQe_comments());
			pstmt.setInt(8, qe.getIsnull());
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
	public boolean batchDelete(String[] qeids) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();

		for (String qeid : qeids) {
			String sql = "delete from " + QualityEducationTable.TABLE_NAME
					+ " where " + QualityEducationTable.QE_ID + " = '" + qeid + "'";
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
	public int alterQualityEducation(Map<String, String> valueMap, String id) {
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(QualityEducationTable.QE_ID, id);
		int result = updateRecord(QualityEducationTable.TABLE_NAME, valueMap,
				condition);
		return result;
	}

	@Override
	public int getQualityEducationCount(Map queryParams) {
		int count = 0;
		//获取条件的语句
		String sql = "select count(*) from " + QualityEducationTable.TABLE_NAME
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
		sql += String.format(" or %s ='%s'", QualityEducationTable.QE_COLLEGE, "");
		
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
	public List<QualityEducation> getAllQualityEducation(int start, int end,
			String sortStr, String orderStr, Map queryParams) {
		String sql = " select tmp.* from ( " + " select * from "
				+ QualityEducationTable.TABLE_NAME + " where 1=1 ";
		
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
		
		List<QualityEducation> qes = new ArrayList<QualityEducation>();
		try {
			
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int qe_id = resultSet.getInt(QualityEducationTable.QE_ID);
				Integer qe_diathesisdeveloping = resultSet.getInt(QualityEducationTable.QE_DIATHESISDEVELOPING);
				if(qe_diathesisdeveloping==-999)
					qe_diathesisdeveloping = null;
				
				Integer qe_qualificationtraining = resultSet.getInt(QualityEducationTable.QE_QUALIFICATIONTRAINING);
				if(qe_qualificationtraining==-999)
					qe_qualificationtraining = null;
				
				Integer qe_course = resultSet.getInt(QualityEducationTable.QE_COURSE);
				if(qe_course==-999)
					qe_course = null;
				
				Integer qe_basecount = resultSet.getInt(QualityEducationTable.QE_BASECOUNT);
				if(qe_basecount==-999)
					qe_basecount = null;
				
				int qe_serialnumber = resultSet.getInt(QualityEducationTable.QE_SERIALNUMBER);
				String qe_comments = resultSet.getString(QualityEducationTable.QE_COMMENTS);
				String qe_college = resultSet.getString(QualityEducationTable.QE_COLLEGE);
				int isnull = resultSet.getInt(QualityEducationTable.ISNULL);
				if(qe_comments==null)
					qe_comments="";
				
				
				QualityEducation qe = new QualityEducation(qe_id, qe_diathesisdeveloping,
						qe_qualificationtraining, qe_course,qe_basecount,qe_serialnumber, qe_comments,isnull,qe_college);
				
				qes.add(qe);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return qes;
	}

	@Override
	public List<QualityEducation> getAllQualityEducation() {
		String sql = " select * from "
				+ QualityEducationTable.TABLE_NAME + " where 1=1 ";
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		List<QualityEducation> qes = new ArrayList<QualityEducation>();
		try {
			
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int qe_id = resultSet.getInt(QualityEducationTable.QE_ID);
				Integer qe_diathesisdeveloping = resultSet.getInt(QualityEducationTable.QE_DIATHESISDEVELOPING);
				if(qe_diathesisdeveloping==-999)
					qe_diathesisdeveloping = null;
				
				Integer qe_qualificationtraining = resultSet.getInt(QualityEducationTable.QE_QUALIFICATIONTRAINING);
				if(qe_qualificationtraining==-999)
					qe_qualificationtraining = null;
				
				Integer qe_course = resultSet.getInt(QualityEducationTable.QE_COURSE);
				if(qe_course==-999)
					qe_course = null;
				
				Integer qe_basecount = resultSet.getInt(QualityEducationTable.QE_BASECOUNT);
				if(qe_basecount==-999)
					qe_basecount = null;
				
				int qe_serialnumber = resultSet.getInt(QualityEducationTable.QE_SERIALNUMBER);
				String qe_comments = resultSet.getString(QualityEducationTable.QE_COMMENTS);
				String qe_college = resultSet.getString(QualityEducationTable.QE_COLLEGE);
				int isnull = resultSet.getInt(QualityEducationTable.ISNULL);
				if(qe_comments==null)
					qe_comments="";
				
				
				QualityEducation qe = new QualityEducation(qe_id, qe_diathesisdeveloping,
						qe_qualificationtraining, qe_course,qe_basecount,qe_serialnumber, qe_comments,isnull,qe_college);
				
				qes.add(qe);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return qes;
	}

	@Override
	public void deleteByCollegeandDeadline(String college, Date deadline)
			throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();
		String sql = "delete from " + QualityEducationTable.TABLE_NAME
				+ " where " + QualityEducationTable.QE_COLLEGE + " = '" + college + "'" +" and qe_deadline is null ";
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
