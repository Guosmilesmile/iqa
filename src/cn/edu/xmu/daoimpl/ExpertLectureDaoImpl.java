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

import cn.edu.xmu.dao.ExpertLectureDao;
import cn.edu.xmu.entity.ExpertLecture;
import cn.edu.xmu.table.ExpertLectureTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;


/**
 * @author zhantu
 * 校内外专家开设文化、学术讲座情况（学年）  实体类功能 ——接口实现
 * date 2015-07-09
 */

public class ExpertLectureDaoImpl extends BaseDaoImpl<ExpertLecture>implements ExpertLectureDao{

	@Override
	public int addRecord(ExpertLecture el) {
		
		int result = 0;

		String t_sql = "update " + ExpertLectureTable.TABLE_NAME + " set "
				+ ExpertLectureTable.EL_SERIALNUMBER + " = "
				+ ExpertLectureTable.EL_SERIALNUMBER + " +1 where "
				+ ExpertLectureTable.EL_SERIALNUMBER + ">=?";

		
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
			t_pstmt.setInt(1, el.getEl_serialnumber());
			
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
		String sql = "insert into " + ExpertLectureTable.TABLE_NAME + "("
				+ ExpertLectureTable.EL_COLLEGENAME + ","
				+ ExpertLectureTable.EL_TITLE + ","
				+ ExpertLectureTable.EL_GRADE + ","
				+ ExpertLectureTable.EL_TERM + ","
				+ ExpertLectureTable.EL_TYPE + ","
				
				+ ExpertLectureTable.EL_NAME + ","
				+ ExpertLectureTable.EL_PRORANK + ","
				+ ExpertLectureTable.EL_UNIT + ","
				+ ExpertLectureTable.EL_AREA + ","
				+ ExpertLectureTable.EL_REMARK + ","
				
				+ ExpertLectureTable.EL_SERIALNUMBER + ","
				+ ExpertLectureTable.EL_COLLEGE + ","
				+ ExpertLectureTable.EL_COMMENTS + ","
				+ ExpertLectureTable.ISNULL
				+ ")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, el.getEl_collegename());
			pstmt.setString(2, el.getEl_title());
			pstmt.setString(3, el.getEl_grade());
			pstmt.setString(4, el.getEl_term());
			pstmt.setString(5, el.getEl_type());
			pstmt.setString(6, el.getEl_name());
			pstmt.setString(7, el.getEl_prorank());
			pstmt.setString(8, el.getEl_unit());
			pstmt.setString(9, el.getEl_area());
			pstmt.setString(10, el.getEl_remark());
			pstmt.setInt(11, el.getEl_serialnumber());
			pstmt.setString(12, el.getEl_college());
			pstmt.setString(13, el.getEl_comments());
			pstmt.setInt(14, el.getIsnull());
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
	public int getExpertLectureCount(Map queryParams) {
		
		int count = 0;
		//获取条件的语句
		String sql = "select count(*) from " + ExpertLectureTable.TABLE_NAME
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
			sql += String.format(" and  %s like  '%%%s%%' ", key, value);
		}
		sql += String.format(" or %s ='%s'", ExpertLectureTable.EL_COLLEGE, "");
		
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
	public List<ExpertLecture> getAllExpertLecture(int start, int end,
			String sortStr, String orderStr, Map queryParams) {
		
		String sql = " select tmp.* from ( " + " select * from "
				+ ExpertLectureTable.TABLE_NAME + " where 1=1 ";
		
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
		
		List<ExpertLecture> els = new ArrayList<ExpertLecture>();
		try {
			
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int el_id = resultSet.getInt(ExpertLectureTable.EL_ID);

				String el_collegename = resultSet.getString(ExpertLectureTable.EL_COLLEGENAME);
				String el_title = resultSet.getString(ExpertLectureTable.EL_TITLE);
				String el_grade = resultSet.getString(ExpertLectureTable.EL_GRADE);
				String el_term = resultSet.getString(ExpertLectureTable.EL_TERM);
				String el_type = resultSet.getString(ExpertLectureTable.EL_TYPE);

				String el_name = resultSet.getString(ExpertLectureTable.EL_NAME);
				String el_prorank = resultSet.getString(ExpertLectureTable.EL_PRORANK);
				String el_unit = resultSet.getString(ExpertLectureTable.EL_UNIT);
				String el_area = resultSet.getString(ExpertLectureTable.EL_AREA);
				String el_remark = resultSet.getString(ExpertLectureTable.EL_REMARK);
				
				int el_serialnumber = resultSet.getInt(ExpertLectureTable.EL_SERIALNUMBER);
				Date el_deadline = resultSet.getDate(ExpertLectureTable.EL_DEADLINE);
				String el_comments = resultSet.getString(ExpertLectureTable.EL_COMMENTS);
				String el_college = resultSet.getString(ExpertLectureTable.EL_COLLEGE);
				if(el_comments==null)
					el_comments = "";
				int isnull = resultSet.getInt(ExpertLectureTable.ISNULL);
				
				ExpertLecture el = new ExpertLecture(el_id, el_collegename, el_title,
						el_grade, el_term, el_type, el_name,
						el_prorank, el_unit, el_area,
						el_remark, el_serialnumber, el_deadline,
						el_college, el_comments, isnull);
				
				els.add(el);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return els;
	}

	@Override
	public int alterExpertLecture(Map<String, String> valueMap, String id) {
		
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(ExpertLectureTable.EL_ID, id);
		int result = updateRecord(ExpertLectureTable.TABLE_NAME, valueMap,
				condition);
		return result;
	}

	@Override
	public boolean batchDelete(String[] elids) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();

		for (String elid : elids) {
			String sql = "delete from " + ExpertLectureTable.TABLE_NAME
					+ " where " + ExpertLectureTable.EL_ID + " = '" + elid + "'";
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
	public List<ExpertLecture> getExpertLecture(int start, int end,
			String sortStr, String orderStr, Map<String, String> params,
			Date deadline) {
		String sql = " select tmp.* from ( " + " select * from "
				+ ExpertLectureTable.TABLE_NAME + " where 1=1 ";
		if (deadline != null) {

			sql += String.format("and "+ExpertLectureTable.EL_DEADLINE+" like  '%s%%' ", deadline);
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

		List<ExpertLecture> expertLectures = new ArrayList<ExpertLecture>();
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
				int el_id = resultSet.getInt(ExpertLectureTable.EL_ID);

				String el_collegename = resultSet.getString(ExpertLectureTable.EL_COLLEGENAME);
				String el_title = resultSet.getString(ExpertLectureTable.EL_TITLE);
				String el_grade = resultSet.getString(ExpertLectureTable.EL_GRADE);
				String el_term = resultSet.getString(ExpertLectureTable.EL_TERM);
				String el_type = resultSet.getString(ExpertLectureTable.EL_TYPE);

				String el_name = resultSet.getString(ExpertLectureTable.EL_NAME);
				String el_prorank = resultSet.getString(ExpertLectureTable.EL_PRORANK);
				String el_unit = resultSet.getString(ExpertLectureTable.EL_UNIT);
				String el_area = resultSet.getString(ExpertLectureTable.EL_AREA);
				String el_remark = resultSet.getString(ExpertLectureTable.EL_REMARK);
				
				int el_serialnumber = resultSet.getInt(ExpertLectureTable.EL_SERIALNUMBER);
				Date el_deadline = resultSet.getDate(ExpertLectureTable.EL_DEADLINE);
				String el_comments = resultSet.getString(ExpertLectureTable.EL_COMMENTS);
				String el_college = resultSet.getString(ExpertLectureTable.EL_COLLEGE);
				int isnull = resultSet.getInt(ExpertLectureTable.ISNULL);
				
				ExpertLecture el = new ExpertLecture(el_id, el_collegename, el_title,
						el_grade, el_term, el_type, el_name,
						el_prorank, el_unit, el_area,
						el_remark, el_serialnumber, el_deadline,
						el_college, el_comments, isnull);

				expertLectures.add(el);
			}
			return expertLectures;
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
		String sql = "delete from " + ExpertLectureTable.TABLE_NAME
				+ " where " + ExpertLectureTable.EL_COLLEGE + " = '" + college + "'" +" and el_deadline is null ";
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
	public List<ExpertLecture> getAllExpertLecture() {
		String sql = " select * from " + ExpertLectureTable.TABLE_NAME
				+ " where 1=1 " + " order by " + ExpertLectureTable.EL_ID;
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
			List<ExpertLecture> expertLectureList = new ArrayList<ExpertLecture>();
			while (resultSet.next()) {
				int el_id = resultSet.getInt(ExpertLectureTable.EL_ID);

				String el_collegename = resultSet.getString(ExpertLectureTable.EL_COLLEGENAME);
				String el_title = resultSet.getString(ExpertLectureTable.EL_TITLE);
				String el_grade = resultSet.getString(ExpertLectureTable.EL_GRADE);
				String el_term = resultSet.getString(ExpertLectureTable.EL_TERM);
				String el_type = resultSet.getString(ExpertLectureTable.EL_TYPE);

				String el_name = resultSet.getString(ExpertLectureTable.EL_NAME);
				String el_prorank = resultSet.getString(ExpertLectureTable.EL_PRORANK);
				String el_unit = resultSet.getString(ExpertLectureTable.EL_UNIT);
				String el_area = resultSet.getString(ExpertLectureTable.EL_AREA);
				String el_remark = resultSet.getString(ExpertLectureTable.EL_REMARK);
				
				int el_serialnumber = resultSet.getInt(ExpertLectureTable.EL_SERIALNUMBER);
				Date el_deadline = resultSet.getDate(ExpertLectureTable.EL_DEADLINE);
				String el_comments = resultSet.getString(ExpertLectureTable.EL_COMMENTS);
				String el_college = resultSet.getString(ExpertLectureTable.EL_COLLEGE);
				int isnull = resultSet.getInt(ExpertLectureTable.ISNULL);
				
				ExpertLecture el = new ExpertLecture(el_id, el_collegename, el_title,
						el_grade, el_term, el_type, el_name,
						el_prorank, el_unit, el_area,
						el_remark, el_serialnumber, el_deadline,
						el_college, el_comments, isnull);
				
				expertLectureList.add(el);
			}
			return expertLectureList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
	}
}
