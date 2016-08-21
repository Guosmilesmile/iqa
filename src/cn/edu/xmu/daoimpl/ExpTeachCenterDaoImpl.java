package cn.edu.xmu.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.dao.ExpTeachCenterDao;
import cn.edu.xmu.entity.ExpTeachCenter;
import cn.edu.xmu.entity.ExpTeachCenter;
import cn.edu.xmu.table.ExpTeachCenterTable;
import cn.edu.xmu.table.ExpTeachCenterTable;
import cn.edu.xmu.table.ExpTeachCenterTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;


/**
 * 
 * @author Lee
 * 学校相关行政单位  实体类功能 ——接口实现
 * date 2015-06-29
 */

public class ExpTeachCenterDaoImpl extends BaseDaoImpl<ExpTeachCenter>implements ExpTeachCenterDao{

	@Override
	public int addRecord(ExpTeachCenter etc) {
		
		int result = 0;

		String t_sql = "update " + ExpTeachCenterTable.TABLE_NAME + " set "
				+ ExpTeachCenterTable.ETC_SERIALNUMBER + " = "
				+ ExpTeachCenterTable.ETC_SERIALNUMBER + " +1 where "
				+ ExpTeachCenterTable.ETC_SERIALNUMBER + ">=?";

		
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
			t_pstmt.setInt(1, etc.getEtc_serialnumber());
			
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
		String sql = "insert into " + ExpTeachCenterTable.TABLE_NAME + "("
				+ ExpTeachCenterTable.ETC_EXPTEACHCENTERNAME + ","
				+ ExpTeachCenterTable.ETC_SUBJECTNAME + ","
				+ ExpTeachCenterTable.ETC_SUBJECTCODE + ","
				+ ExpTeachCenterTable.ETC_LEVELNUM + ","
				+ ExpTeachCenterTable.ETC_LEVELNAME + ","
				+ ExpTeachCenterTable.ETC_STARTTIME+","
				+ ExpTeachCenterTable.ETC_SERIALNUMBER+","
				+ ExpTeachCenterTable.ETC_COLLEGE+","
				+ ExpTeachCenterTable.ISNULL
				+ ")values(?,?,?,?,?,?,?,?,?)";

		System.out.println(sql);
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, etc.getEtc_expteachcentername());
			pstmt.setString(2, etc.getEtc_subjectname());
			pstmt.setString(3, etc.getEtc_subjectcode());
			pstmt.setInt(4, etc.getEtc_levelnum());
			pstmt.setString(5, etc.getEtc_levelname());
			pstmt.setInt(6, etc.getEtc_starttime());
			pstmt.setInt(7, etc.getEtc_serialnumber());
			pstmt.setString(8, etc.getEtc_college());
			pstmt.setInt(9, etc.getIsnull());
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
	public int getExpTeachCenterCount(Map queryParams) {
		
		int count = 0;
		//获取条件的语句
		String sql = "select count(*) from " + ExpTeachCenterTable.TABLE_NAME
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
		sql += String.format(" or %s ='%s'", ExpTeachCenterTable.ETC_COLLEGE, "");
		
		
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
		
		return count;
	}

	@Override
	public List<ExpTeachCenter> getAllExpTeachCenter(int start, int end,
			String sortStr, String orderStr, Map queryParams) {
		
		String sql = " select tmp.* from ( " + " select * from "
				+ ExpTeachCenterTable.TABLE_NAME + " where 1=1 ";
		
		for (Object object : queryParams.keySet()) {
			String key = object.toString();
			String value = queryParams.get(key).toString();
			sql += String.format(" and %s like  '%%%s%%'", key, value);
		}
		sql += " order by " + sortStr + " " + orderStr + " ) tmp limit "
				+ start + " ," + end;

		System.out.println(sql+"===");
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		List<ExpTeachCenter> etcs = new ArrayList<ExpTeachCenter>();
		try {
			
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int etc_id = resultSet.getInt(ExpTeachCenterTable.ETC_ID);
				String etc_expteachcentername = resultSet.getString(ExpTeachCenterTable.ETC_EXPTEACHCENTERNAME);
				String etc_subjectname = resultSet.getString(ExpTeachCenterTable.ETC_SUBJECTNAME);
				String etc_subjectcode = resultSet.getString(ExpTeachCenterTable.ETC_SUBJECTCODE);
				int etc_levelnum = resultSet.getInt(ExpTeachCenterTable.ETC_LEVELNUM);
				String etc_levelname = resultSet.getString(ExpTeachCenterTable.ETC_LEVELNAME);
				
				int etc_starttime = resultSet.getInt(ExpTeachCenterTable.ETC_STARTTIME);
				int etc_serialnumber = resultSet.getInt(ExpTeachCenterTable.ETC_SERIALNUMBER);
				Date etc_deadline = resultSet.getDate(ExpTeachCenterTable.ETC_DEADLINE);
				
				String etc_comments = resultSet.getString(ExpTeachCenterTable.ETC_COMMENTS);
				if(null==etc_comments){
					etc_comments="";
				}
				String etc_college = resultSet.getString(ExpTeachCenterTable.ETC_COLLEGE);
				int isnull = resultSet.getInt(ExpTeachCenterTable.ISNULL);
				
				ExpTeachCenter etc = new ExpTeachCenter(etc_id, etc_expteachcentername, etc_subjectname,
						etc_subjectcode, etc_levelnum, etc_levelname, etc_starttime, etc_serialnumber, 
						etc_deadline, etc_comments, etc_college, isnull);
				etcs.add(etc);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return etcs;
	}

	@Override
	public int alterExpTeachCenter(Map<String, String> valueMap, String id) {
		
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(ExpTeachCenterTable.ETC_ID, id);
		int result = updateRecord(ExpTeachCenterTable.TABLE_NAME, valueMap,condition);
		return result;
	}
	
	
	

	@Override
	public boolean batchDelete(String[] etcids) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();

		for (String etcid : etcids) {
			String sql = "delete from " + ExpTeachCenterTable.TABLE_NAME
					+ " where " + ExpTeachCenterTable.ETC_ID + " = '" + etcid + "'";
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
	public List<ExpTeachCenter> getExpTeachCenter(Map queryParams) {
		// TODO Auto-generated method stub
		String sql = " select tmp.* from ( " + " select * from "
				+ ExpTeachCenterTable.TABLE_NAME + " where 1=1 ";
		
		for (Object object : queryParams.keySet()) {
			String key = object.toString();
			String value = queryParams.get(key).toString();
			sql += String.format(" and %s like  '%%%s%%'", key, value);
		}
		sql += " order by " + ExpTeachCenterTable.ETC_ID + " ) tmp ";

		System.out.println(sql+"===");
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		List<ExpTeachCenter> etcs = new ArrayList<ExpTeachCenter>();
		try {
			
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int etc_id = resultSet.getInt(ExpTeachCenterTable.ETC_ID);
				String etc_expteachcentername = resultSet.getString(ExpTeachCenterTable.ETC_EXPTEACHCENTERNAME);
				String etc_subjectname = resultSet.getString(ExpTeachCenterTable.ETC_SUBJECTNAME);
				String etc_subjectcode = resultSet.getString(ExpTeachCenterTable.ETC_SUBJECTCODE);
				int etc_levelnum = resultSet.getInt(ExpTeachCenterTable.ETC_LEVELNUM);
				String etc_levelname = resultSet.getString(ExpTeachCenterTable.ETC_LEVELNAME);
				
				int etc_starttime = resultSet.getInt(ExpTeachCenterTable.ETC_STARTTIME);
				int etc_serialnumber = resultSet.getInt(ExpTeachCenterTable.ETC_SERIALNUMBER);
				Date etc_deadline = resultSet.getDate(ExpTeachCenterTable.ETC_DEADLINE);
				
				String etc_comments = resultSet.getString(ExpTeachCenterTable.ETC_COMMENTS);
				if(null==etc_comments){
					etc_comments="";
				}
				String etc_college = resultSet.getString(ExpTeachCenterTable.ETC_COLLEGE);
				int isnull = resultSet.getInt(ExpTeachCenterTable.ISNULL);
				
				ExpTeachCenter etc = new ExpTeachCenter(etc_id, etc_expteachcentername, etc_subjectname,
						etc_subjectcode, etc_levelnum, etc_levelname, etc_starttime, etc_serialnumber, 
						etc_deadline, etc_comments, etc_college, isnull);
				
				
				etcs.add(etc);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return etcs;
	}
	
	@Override
	public void deleteByCollegeandDeadline(String college)
			throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();
		String sql = "delete from " + ExpTeachCenterTable.TABLE_NAME
				+ " where " + ExpTeachCenterTable.ETC_COLLEGE + " = '" + college + "'" +" and "
				+ExpTeachCenterTable.ETC_DEADLINE+" is null ";
		
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
	public List<ExpTeachCenter> getAllExpTeachCenter() {
		
		return null;
	}

}
