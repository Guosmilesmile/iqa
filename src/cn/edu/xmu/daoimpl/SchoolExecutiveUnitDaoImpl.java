package cn.edu.xmu.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.dao.SchoolExecutiveUnitDao;
import cn.edu.xmu.entity.SchoolExecutiveUnit;
import cn.edu.xmu.table.SchoolExecutiveUnitTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;


/**
 * 
 * @author Lee
 * 学校相关行政单位  实体类功能 ——接口实现
 * date 2015-06-29
 */

public class SchoolExecutiveUnitDaoImpl extends BaseDaoImpl<SchoolExecutiveUnit>implements SchoolExecutiveUnitDao{

	@Override
	public int addRecord(SchoolExecutiveUnit seu) {
		
		int result = 0;

		String t_sql = "update " + SchoolExecutiveUnitTable.TABLE_NAME + " set "
				+ SchoolExecutiveUnitTable.SEU_SERIALNUMBER + " = "
				+ SchoolExecutiveUnitTable.SEU_SERIALNUMBER + " +1 where "
				+ SchoolExecutiveUnitTable.SEU_SERIALNUMBER + ">=?";

		
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
			t_pstmt.setInt(1, seu.getSeu_serialnumber());
			
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
		String sql = "insert into " + SchoolExecutiveUnitTable.TABLE_NAME + "("
				+ SchoolExecutiveUnitTable.SEU_DEPARTMENTNAME
				+ "," + SchoolExecutiveUnitTable.SEU_DEPARTMENTNUMBER + ","
				+ SchoolExecutiveUnitTable.SEU_FUNCTION + ","
				+ SchoolExecutiveUnitTable.SEU_BOSSHEAD + ","
				+ SchoolExecutiveUnitTable.SEU_SERIALNUMBER + ","
				+ SchoolExecutiveUnitTable.SEU_COLLEGE + ","
				+ SchoolExecutiveUnitTable.SEU_COMMENTS
				+ ")values(?,?,?,?,?,?,?)";

		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, seu.getSeu_departmentname());
			pstmt.setString(2, seu.getSeu_departmentnumber());
			pstmt.setString(3, seu.getSeu_function());
			pstmt.setString(4, seu.getSeu_bosshead());
			pstmt.setInt(5, seu.getSeu_serialnumber());
			pstmt.setString(6, seu.getSeu_college());
			pstmt.setString(7, seu.getSeu_comments());
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
	public int getSchoolExecutiveUnitCount(Map queryParams) {
		
		int count = 0;
		//获取条件的语句
		String sql = "select count(*) from " + SchoolExecutiveUnitTable.TABLE_NAME
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
		sql += String.format(" or %s ='%s'", SchoolExecutiveUnitTable.SEU_COLLEGE, "");
		
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
	public List<SchoolExecutiveUnit> getAllSchoolExecutiveUnit(int start, int end,
			String sortStr, String orderStr, Map queryParams) {
		
		String sql = " select tmp.* from ( " + " select * from "
				+ SchoolExecutiveUnitTable.TABLE_NAME + " where 1=1 ";
		
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
		
		List<SchoolExecutiveUnit> seus = new ArrayList<SchoolExecutiveUnit>();
		try {
			
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int seu_id = resultSet.getInt(SchoolExecutiveUnitTable.SEU_ID);
				String seu_departmentname = resultSet.getString(SchoolExecutiveUnitTable.SEU_DEPARTMENTNAME);
				String seu_departmentnumber = resultSet
						.getString(SchoolExecutiveUnitTable.SEU_DEPARTMENTNUMBER);
				
				String seu_function = resultSet.getString(SchoolExecutiveUnitTable.SEU_FUNCTION);
				String seu_bosshead = resultSet.getString(SchoolExecutiveUnitTable.SEU_BOSSHEAD);
				
				int seu_serialnumber = resultSet.getInt(SchoolExecutiveUnitTable.SEU_SERIALNUMBER);
				
				String seu_comments = resultSet
						.getString(SchoolExecutiveUnitTable.SEU_COMMENTS);
				int isnull = resultSet.getInt(SchoolExecutiveUnitTable.ISNULL);
				
				SchoolExecutiveUnit seu = new SchoolExecutiveUnit(seu_id, seu_departmentname,
						seu_departmentnumber, seu_function, seu_bosshead, seu_serialnumber,seu_comments,isnull);
				
				seus.add(seu);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return seus;
	}

	@Override
	public int alterSchoolExecutiveUnit(Map<String, String> valueMap, String id) {
		
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(SchoolExecutiveUnitTable.SEU_ID, id);
		int result = updateRecord(SchoolExecutiveUnitTable.TABLE_NAME, valueMap,
				condition);
		return result;
	}

	@Override
	public boolean batchDelete(String[] seuids) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();

		for (String seuid : seuids) {
			String sql = "delete from " + SchoolExecutiveUnitTable.TABLE_NAME
					+ " where " + SchoolExecutiveUnitTable.SEU_ID + " = '" + seuid + "'";
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
	public void deleteByCollegeandDeadline(String college) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();
		String sql = "delete from " + SchoolExecutiveUnitTable.TABLE_NAME
				+ " where " + SchoolExecutiveUnitTable.SEU_COLLEGE + " = '" + college + "'" +" and "
				+SchoolExecutiveUnitTable.SEU_DEADLINE+" is null ";
		
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
	public List<SchoolExecutiveUnit> getAllSchoolExecutiveUnit() {
		
		
		String sql = " select * from " + SchoolExecutiveUnitTable.TABLE_NAME
				+ " where 1=1 " + " order by " + SchoolExecutiveUnitTable.SEU_ID;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		List<SchoolExecutiveUnit> seus = new ArrayList<SchoolExecutiveUnit>();
		try {
			
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int seu_id = resultSet.getInt(SchoolExecutiveUnitTable.SEU_ID);
				String seu_departmentname = resultSet.getString(SchoolExecutiveUnitTable.SEU_DEPARTMENTNAME);
				String seu_departmentnumber = resultSet
						.getString(SchoolExecutiveUnitTable.SEU_DEPARTMENTNUMBER);
				
				String seu_function = resultSet.getString(SchoolExecutiveUnitTable.SEU_FUNCTION);
				String seu_bosshead = resultSet.getString(SchoolExecutiveUnitTable.SEU_BOSSHEAD);
				
				int seu_serialnumber = resultSet.getInt(SchoolExecutiveUnitTable.SEU_SERIALNUMBER);
				
				String seu_comments = resultSet
						.getString(SchoolExecutiveUnitTable.SEU_COMMENTS);
				int isnull = resultSet.getInt(SchoolExecutiveUnitTable.ISNULL);
				
				SchoolExecutiveUnit seu = new SchoolExecutiveUnit(seu_id, seu_departmentname,
						seu_departmentnumber, seu_function, seu_bosshead, seu_serialnumber,seu_comments,isnull);
				
				seus.add(seu);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return seus;
	}
}
