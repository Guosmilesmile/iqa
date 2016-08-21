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

import cn.edu.xmu.dao.StudentHomeDao;
import cn.edu.xmu.entity.SchoolNet;
import cn.edu.xmu.entity.StudentHome;
import cn.edu.xmu.table.SchoolNetTable;
import cn.edu.xmu.table.StudentHomeTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;

public class StudentHomeDaoImpl extends BaseDaoImpl<StudentHome>implements StudentHomeDao {

	@Override
	public int addRecord(StudentHome sh) {
		int result = 0;

		String t_sql = "update " + StudentHomeTable.TABLE_NAME + " set "
				+ StudentHomeTable.SH_SERIALNUMBER + " = "
				+ StudentHomeTable.SH_SERIALNUMBER + " +1 where "
				+ StudentHomeTable.SH_SERIALNUMBER + ">=?";

		
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
			t_pstmt.setInt(1, sh.getSh_serialnumber());
			
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
		String sql = "insert into " + StudentHomeTable.TABLE_NAME + "("
				+ StudentHomeTable.SH_DININGROOMAREA
				+ "," + StudentHomeTable.SH_DININGROONCOUNT+ ","
				+ StudentHomeTable.SH_DORMITORYAREA + ","
				+ StudentHomeTable.SH_DORMITORYCOUNT + ","
				+ StudentHomeTable.SH_SERIALNUMBER + ","
				+ StudentHomeTable.SH_COLLEGE + ","
				+ StudentHomeTable.SH_COMMENTS+","
				+ StudentHomeTable.ISNULL
				+ ")values(?,?,?,?,?,?,?,?)";

		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setFloat(1, sh.getSh_diningroomarea());
			pstmt.setInt(2, sh.getSh_diningrooncount());
			pstmt.setFloat(3, sh.getSh_dormitoryarea());
			pstmt.setInt(4, sh.getSh_dormitorycount());
			pstmt.setInt(5, sh.getSh_serialnumber());
			pstmt.setString(6, sh.getSh_college());
			pstmt.setString(7, sh.getSh_comments());
			pstmt.setInt(8, sh.getIsnull());
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
	public boolean batchDelete(String[] shids) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();

		for (String shid : shids) {
			String sql = "delete from " + StudentHomeTable.TABLE_NAME
					+ " where " + StudentHomeTable.SH_ID + " = '" + shid + "'";
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
	public int alterStudentHome(Map<String, String> valueMap, String id) {
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(StudentHomeTable.SH_ID, id);
		int result = updateRecord(StudentHomeTable.TABLE_NAME, valueMap,
				condition);
		return result;
	}

	@Override
	public int getStudentHomeCount(Map queryParams) {
		int count = 0;
		//获取条件的语句
		String sql = "select count(*) from " + StudentHomeTable.TABLE_NAME
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
		sql += String.format(" or %s ='%s'", StudentHomeTable.SH_COLLEGE, "");
		
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
	public List<StudentHome> getAllStudentHome(int start, int end,
			String sortStr, String orderStr, Map queryParams) {
		String sql = " select tmp.* from ( " + " select * from "
				+ StudentHomeTable.TABLE_NAME + " where 1=1 ";
		
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
		
		List<StudentHome> shs = new ArrayList<StudentHome>();
		try {
			
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int sh_id = resultSet.getInt(StudentHomeTable.SH_ID);
				Float sh_diningroomarea = resultSet.getFloat(StudentHomeTable.SH_DININGROOMAREA);
				if(sh_diningroomarea==-999)
					sh_diningroomarea = null;
				
				Integer sh_diningrooncount = resultSet.getInt(StudentHomeTable.SH_DININGROONCOUNT);
				if(sh_diningrooncount==-999)
					sh_diningrooncount = null;
				
				Float sh_dormitoryarea = resultSet.getFloat(StudentHomeTable.SH_DORMITORYAREA);
				if(sh_dormitoryarea==-999)
					sh_dormitoryarea = null;
				
				Integer sh_dormitorycount = resultSet.getInt(StudentHomeTable.SH_DORMITORYCOUNT);
				if(sh_dormitorycount==-999)
					sh_dormitorycount = null;
				
				int sh_serialnumber = resultSet.getInt(StudentHomeTable.SH_SERIALNUMBER);
				String sh_comments = resultSet.getString(StudentHomeTable.SH_COMMENTS);
				String sh_college = resultSet.getString(StudentHomeTable.SH_COLLEGE);
				int isnull = resultSet.getInt(StudentHomeTable.ISNULL);
				if(sh_comments==null)
					sh_comments="";
				
				StudentHome sh = new StudentHome(sh_id, sh_diningroomarea,
						sh_diningrooncount, sh_dormitoryarea,sh_dormitorycount, sh_serialnumber, sh_comments,isnull,sh_college);
				
				shs.add(sh);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return shs;
	}

	@Override
	public List<StudentHome> getAllStudentHome() {
		String sql = " select * from "
				+ StudentHomeTable.TABLE_NAME + " where 1=1 ";

		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		List<StudentHome> shs = new ArrayList<StudentHome>();
		try {
			
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int sh_id = resultSet.getInt(StudentHomeTable.SH_ID);
				Float sh_diningroomarea = resultSet.getFloat(StudentHomeTable.SH_DININGROOMAREA);
				if(sh_diningroomarea==-999)
					sh_diningroomarea = null;
				
				Integer sh_diningrooncount = resultSet.getInt(StudentHomeTable.SH_DININGROONCOUNT);
				if(sh_diningrooncount==-999)
					sh_diningrooncount = null;
				
				Float sh_dormitoryarea = resultSet.getFloat(StudentHomeTable.SH_DORMITORYAREA);
				if(sh_dormitoryarea==-999)
					sh_dormitoryarea = null;
				
				Integer sh_dormitorycount = resultSet.getInt(StudentHomeTable.SH_DORMITORYCOUNT);
				if(sh_dormitorycount==-999)
					sh_dormitorycount = null;
				
				int sh_serialnumber = resultSet.getInt(StudentHomeTable.SH_SERIALNUMBER);
				String sh_comments = resultSet.getString(StudentHomeTable.SH_COMMENTS);
				String sh_college = resultSet.getString(StudentHomeTable.SH_COLLEGE);
				int isnull = resultSet.getInt(StudentHomeTable.ISNULL);
				if(sh_comments==null)
					sh_comments="";
				
				StudentHome sh = new StudentHome(sh_id, sh_diningroomarea,
						sh_diningrooncount, sh_dormitoryarea,sh_dormitorycount, sh_serialnumber, sh_comments,isnull,sh_college);
				
				shs.add(sh);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return shs;
	}

	@Override
	public void deleteByCollegeandDeadline(String college, Date deadline)
			throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();
		String sql = "delete from " + StudentHomeTable.TABLE_NAME
				+ " where " + StudentHomeTable.SH_COLLEGE + " = '" + college + "'" +" and sh_deadline is null ";
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
