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

import cn.edu.xmu.dao.TeacherResearchNumberDao;
import cn.edu.xmu.entity.TeacherResearchNumber;
import cn.edu.xmu.table.TeacherResearchNumberTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;


/**
 * @author zhantu
 * 教师科研项目数  (自然年)  实体类功能 ——接口实现
 * date 2015-07-03
 */

public class TeacherResearchNumberDaoImpl extends BaseDaoImpl<TeacherResearchNumber>implements TeacherResearchNumberDao{

	@Override
	public int addRecord(TeacherResearchNumber trn) {
		
		int result = 0;

		String t_sql = "update " + TeacherResearchNumberTable.TABLE_NAME + " set "
				+ TeacherResearchNumberTable.TRN_SERIALNUMBER + " = "
				+ TeacherResearchNumberTable.TRN_SERIALNUMBER + " +1 where "
				+ TeacherResearchNumberTable.TRN_SERIALNUMBER + ">=?";

		
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
			t_pstmt.setInt(1, trn.getTrn_serialnumber());
			
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
		String sql = "insert into " + TeacherResearchNumberTable.TABLE_NAME + "("
				+ TeacherResearchNumberTable.TRN_PROJECTNUM+ "," 
				+ TeacherResearchNumberTable.TRN_HRZNPROJECTNUM + ","
				+ TeacherResearchNumberTable.TRN_HRZNHUMANITIESNUM + ","
				+ TeacherResearchNumberTable.TRN_VTCLPROJECTNUM + ","
				+ TeacherResearchNumberTable.TRN_VTCLHUMANITIESNUM + ","
				
				+ TeacherResearchNumberTable.TRN_PROJECTCOST + ","
				+ TeacherResearchNumberTable.TRN_HRZNPROJECTCOST + ","
				+ TeacherResearchNumberTable.TRN_HRZNHUMANITIESCOST + ","
				+ TeacherResearchNumberTable.TRN_VTCLPROJECTCOST + ","
				+ TeacherResearchNumberTable.TRN_VTCLHUMANITIESCOST + ","
				
				+ TeacherResearchNumberTable.TRN_SERIALNUMBER + ","
				+ TeacherResearchNumberTable.TRN_COLLEGE + ","
				+ TeacherResearchNumberTable.TRN_COMMENTS + ","
				+ TeacherResearchNumberTable.ISNULL
				+ ")values(?,?,?,?,?, ?,?,?,?,?, ?,?,?,?)";

		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, trn.getTrn_projectnum());
			pstmt.setInt(2, trn.getTrn_hrznprojectnum());
			pstmt.setInt(3, trn.getTrn_hrznhumanitiesnum());
			pstmt.setInt(4, trn.getTrn_vtclprojectnum());
			pstmt.setInt(5, trn.getTrn_vtclhumanitiesnum());

			pstmt.setFloat(6, trn.getTrn_projectcost());
			pstmt.setFloat(7, trn.getTrn_hrznprojectcost());
			pstmt.setFloat(8, trn.getTrn_hrznhumanitiescost());
			pstmt.setFloat(9, trn.getTrn_vtclprojectcost());
			pstmt.setFloat(10, trn.getTrn_vtclhumanitiescost());
			
			pstmt.setInt(11, trn.getTrn_serialnumber());
			pstmt.setString(12, trn.getTrn_college());
			pstmt.setString(13, trn.getTrn_comments());
			pstmt.setInt(14, trn.getIsnull());
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
	public int getTeacherResearchNumberCount(Map queryParams) {
		
		int count = 0;
		//获取条件的语句
		String sql = "select count(*) from " + TeacherResearchNumberTable.TABLE_NAME
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
		sql += String.format(" or %s ='%s'", TeacherResearchNumberTable.TRN_COLLEGE, "");
		
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
	public List<TeacherResearchNumber> getAllTeacherResearchNumber(int start, int end,
			String sortStr, String orderStr, Map queryParams) {
		
		String sql = " select tmp.* from ( " + " select * from "
				+ TeacherResearchNumberTable.TABLE_NAME + " where 1=1 ";
		
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
		
		List<TeacherResearchNumber> trns = new ArrayList<TeacherResearchNumber>();
		try {
			
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int trn_id = resultSet.getInt(TeacherResearchNumberTable.TRN_ID);
				Integer trn_projectnum = resultSet.getInt(TeacherResearchNumberTable.TRN_PROJECTNUM);
				if(trn_projectnum==-999)
					trn_projectnum = null;
				Integer trn_hrznprojectnum = resultSet.getInt(TeacherResearchNumberTable.TRN_HRZNPROJECTNUM);
				if(trn_hrznprojectnum==-999)
					trn_hrznprojectnum = null;
				Integer trn_hrznhumanitiesnum = resultSet.getInt(TeacherResearchNumberTable.TRN_HRZNHUMANITIESNUM);
				if(trn_hrznhumanitiesnum==-999)
					trn_hrznhumanitiesnum = null;
				Integer trn_vtclprojectnum = resultSet.getInt(TeacherResearchNumberTable.TRN_VTCLPROJECTNUM);
				if(trn_vtclprojectnum==-999)
					trn_vtclprojectnum = null;
				Integer trn_vtclhumanitiesnum = resultSet.getInt(TeacherResearchNumberTable.TRN_VTCLHUMANITIESNUM);
				if(trn_vtclhumanitiesnum==-999)
					trn_vtclhumanitiesnum = null;
				Float trn_projectcost = resultSet.getFloat(TeacherResearchNumberTable.TRN_PROJECTCOST);
				if(trn_projectcost==-999)
					trn_projectcost = null;
				Float trn_hrznprojectcost = resultSet.getFloat(TeacherResearchNumberTable.TRN_HRZNPROJECTCOST);
				if(trn_hrznprojectcost==-999)
					trn_hrznprojectcost = null;
				Float trn_hrznhumanitiescost = resultSet.getFloat(TeacherResearchNumberTable.TRN_HRZNHUMANITIESCOST);
				if(trn_hrznhumanitiescost==-999)
					trn_hrznhumanitiescost = null;
				Float trn_vtclprojectcost = resultSet.getFloat(TeacherResearchNumberTable.TRN_VTCLPROJECTCOST);
				if(trn_vtclprojectcost==-999)
					trn_vtclprojectcost = null;
				Float trn_vtclhumanitiescost = resultSet.getFloat(TeacherResearchNumberTable.TRN_VTCLHUMANITIESCOST);
				if(trn_vtclhumanitiescost==-999)
					trn_vtclhumanitiescost = null;
				int trn_serialnumber = resultSet.getInt(TeacherResearchNumberTable.TRN_SERIALNUMBER);
				Date trn_deadline = resultSet.getDate(TeacherResearchNumberTable.TRN_DEADLINE);
				String trn_college = resultSet.getString(TeacherResearchNumberTable.TRN_COLLEGE);
				String trn_comments = resultSet.getString(TeacherResearchNumberTable.TRN_COMMENTS);
				if(trn_comments==null)
					trn_comments = "";
				int isnull = resultSet.getInt(TeacherResearchNumberTable.ISNULL);
				
				TeacherResearchNumber trn = new TeacherResearchNumber(trn_id, trn_projectnum,
						trn_hrznprojectnum, trn_hrznhumanitiesnum,
						trn_vtclprojectnum, trn_vtclhumanitiesnum,
						trn_projectcost, trn_hrznprojectcost,
						trn_hrznhumanitiescost, trn_vtclprojectcost,
						trn_vtclhumanitiescost, trn_serialnumber,
						trn_deadline, trn_college, trn_comments,isnull);
				
				trns.add(trn);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return trns;
	}

	@Override
	public int alterTeacherResearchNumber(Map<String, String> valueMap, String id) {
		
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(TeacherResearchNumberTable.TRN_ID, id);
		int result = updateRecord(TeacherResearchNumberTable.TABLE_NAME, valueMap,
				condition);
		return result;
	}

	@Override
	public boolean batchDelete(String[] trnids) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();

		for (String trnid : trnids) {
			String sql = "delete from " + TeacherResearchNumberTable.TABLE_NAME
					+ " where " + TeacherResearchNumberTable.TRN_ID + " = '" + trnid + "'";
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
	public List<TeacherResearchNumber> getTeacherResearchNumber(int start, int end,
			String sortStr, String orderStr, Map<String, String> params,
			Date deadline) {
		String sql = " select tmp.* from ( " + " select * from "
				+ TeacherResearchNumberTable.TABLE_NAME + " where 1=1 ";
		if (deadline != null) {

			sql += String.format("and "+TeacherResearchNumberTable.TRN_DEADLINE+" like  '%s%%' ", deadline);
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

		List<TeacherResearchNumber> teacherResearchNumbers = new ArrayList<TeacherResearchNumber>();
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
				int trn_id = resultSet.getInt(TeacherResearchNumberTable.TRN_ID);
				Integer trn_projectnum = resultSet.getInt(TeacherResearchNumberTable.TRN_PROJECTNUM);
				if(trn_projectnum==-999)
					trn_projectnum = null;
				Integer trn_hrznprojectnum = resultSet.getInt(TeacherResearchNumberTable.TRN_HRZNPROJECTNUM);
				if(trn_hrznprojectnum==-999)
					trn_hrznprojectnum = null;
				Integer trn_hrznhumanitiesnum = resultSet.getInt(TeacherResearchNumberTable.TRN_HRZNHUMANITIESNUM);
				if(trn_hrznhumanitiesnum==-999)
					trn_hrznhumanitiesnum = null;
				Integer trn_vtclprojectnum = resultSet.getInt(TeacherResearchNumberTable.TRN_VTCLPROJECTNUM);
				if(trn_vtclprojectnum==-999)
					trn_vtclprojectnum = null;
				Integer trn_vtclhumanitiesnum = resultSet.getInt(TeacherResearchNumberTable.TRN_VTCLHUMANITIESNUM);
				if(trn_vtclhumanitiesnum==-999)
					trn_vtclhumanitiesnum = null;
				Float trn_projectcost = resultSet.getFloat(TeacherResearchNumberTable.TRN_PROJECTCOST);
				if(trn_projectcost==-999)
					trn_projectcost = null;
				Float trn_hrznprojectcost = resultSet.getFloat(TeacherResearchNumberTable.TRN_HRZNPROJECTCOST);
				if(trn_hrznprojectcost==-999)
					trn_hrznprojectcost = null;
				Float trn_hrznhumanitiescost = resultSet.getFloat(TeacherResearchNumberTable.TRN_HRZNHUMANITIESCOST);
				if(trn_hrznhumanitiescost==-999)
					trn_hrznhumanitiescost = null;
				Float trn_vtclprojectcost = resultSet.getFloat(TeacherResearchNumberTable.TRN_VTCLPROJECTCOST);
				if(trn_vtclprojectcost==-999)
					trn_vtclprojectcost = null;
				Float trn_vtclhumanitiescost = resultSet.getFloat(TeacherResearchNumberTable.TRN_VTCLHUMANITIESCOST);
				if(trn_vtclhumanitiescost==-999)
					trn_vtclhumanitiescost = null;
				int trn_serialnumber = resultSet.getInt(TeacherResearchNumberTable.TRN_SERIALNUMBER);
				Date trn_deadline = resultSet.getDate(TeacherResearchNumberTable.TRN_DEADLINE);
				String trn_college = resultSet.getString(TeacherResearchNumberTable.TRN_COLLEGE);
				String trn_comments = resultSet.getString(TeacherResearchNumberTable.TRN_COMMENTS);
				int isnull = resultSet.getInt(TeacherResearchNumberTable.ISNULL);
				
				TeacherResearchNumber trn = new TeacherResearchNumber(trn_id, trn_projectnum,
						trn_hrznprojectnum, trn_hrznhumanitiesnum,
						trn_vtclprojectnum, trn_vtclhumanitiesnum,
						trn_projectcost, trn_hrznprojectcost,
						trn_hrznhumanitiescost, trn_vtclprojectcost,
						trn_vtclhumanitiescost, trn_serialnumber,
						trn_deadline, trn_college, trn_comments, isnull);

				teacherResearchNumbers.add(trn);
			}
			return teacherResearchNumbers;
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
		String sql = "delete from " + TeacherResearchNumberTable.TABLE_NAME
				+ " where " + TeacherResearchNumberTable.TRN_COLLEGE + " = '" + college + "'" +" and trn_deadline is null ";
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
	public List<TeacherResearchNumber> getAllTeacherResearchNumber() {
		String sql = " select * from " + TeacherResearchNumberTable.TABLE_NAME
				+ " where 1=1 " + " order by " + TeacherResearchNumberTable.TRN_ID;
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
			List<TeacherResearchNumber> teacherResearchNumberList = new ArrayList<TeacherResearchNumber>();
			while (resultSet.next()) {
				int trn_id = resultSet.getInt(TeacherResearchNumberTable.TRN_ID);
				Integer trn_projectnum = resultSet.getInt(TeacherResearchNumberTable.TRN_PROJECTNUM);
				if(trn_projectnum==-999)
					trn_projectnum = null;
				Integer trn_hrznprojectnum = resultSet.getInt(TeacherResearchNumberTable.TRN_HRZNPROJECTNUM);
				if(trn_hrznprojectnum==-999)
					trn_hrznprojectnum = null;
				Integer trn_hrznhumanitiesnum = resultSet.getInt(TeacherResearchNumberTable.TRN_HRZNHUMANITIESNUM);
				if(trn_hrznhumanitiesnum==-999)
					trn_hrznhumanitiesnum = null;
				Integer trn_vtclprojectnum = resultSet.getInt(TeacherResearchNumberTable.TRN_VTCLPROJECTNUM);
				if(trn_vtclprojectnum==-999)
					trn_vtclprojectnum = null;
				Integer trn_vtclhumanitiesnum = resultSet.getInt(TeacherResearchNumberTable.TRN_VTCLHUMANITIESNUM);
				if(trn_vtclhumanitiesnum==-999)
					trn_vtclhumanitiesnum = null;
				Float trn_projectcost = resultSet.getFloat(TeacherResearchNumberTable.TRN_PROJECTCOST);
				if(trn_projectcost==-999)
					trn_projectcost = null;
				Float trn_hrznprojectcost = resultSet.getFloat(TeacherResearchNumberTable.TRN_HRZNPROJECTCOST);
				if(trn_hrznprojectcost==-999)
					trn_hrznprojectcost = null;
				Float trn_hrznhumanitiescost = resultSet.getFloat(TeacherResearchNumberTable.TRN_HRZNHUMANITIESCOST);
				if(trn_hrznhumanitiescost==-999)
					trn_hrznhumanitiescost = null;
				Float trn_vtclprojectcost = resultSet.getFloat(TeacherResearchNumberTable.TRN_VTCLPROJECTCOST);
				if(trn_vtclprojectcost==-999)
					trn_vtclprojectcost = null;
				Float trn_vtclhumanitiescost = resultSet.getFloat(TeacherResearchNumberTable.TRN_VTCLHUMANITIESCOST);
				if(trn_vtclhumanitiescost==-999)
					trn_vtclhumanitiescost = null;
				int trn_serialnumber = resultSet.getInt(TeacherResearchNumberTable.TRN_SERIALNUMBER);
				Date trn_deadline = resultSet.getDate(TeacherResearchNumberTable.TRN_DEADLINE);
				String trn_college = resultSet.getString(TeacherResearchNumberTable.TRN_COLLEGE);
				String trn_comments = resultSet.getString(TeacherResearchNumberTable.TRN_COMMENTS);
				int isnull = resultSet.getInt(TeacherResearchNumberTable.ISNULL);
				
				TeacherResearchNumber trn = new TeacherResearchNumber(trn_id, trn_projectnum,
						trn_hrznprojectnum, trn_hrznhumanitiesnum,
						trn_vtclprojectnum, trn_vtclhumanitiesnum,
						trn_projectcost, trn_hrznprojectcost,
						trn_hrznhumanitiescost, trn_vtclprojectcost,
						trn_vtclhumanitiescost, trn_serialnumber,
						trn_deadline, trn_college, trn_comments, isnull);
				
				teacherResearchNumberList.add(trn);
			}
			return teacherResearchNumberList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
	}
}
