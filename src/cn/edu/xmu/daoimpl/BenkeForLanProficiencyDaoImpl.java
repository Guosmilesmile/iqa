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

import cn.edu.xmu.dao.BenkeForLanProficiencyDao;
import cn.edu.xmu.entity.BenkeForLanProficiency;
import cn.edu.xmu.table.BenkeForLanProficiencyTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;


/**
 * 
 * @author Lee
 * 本科生受处分情况  实体类功能 ——接口实现
 * date 2015-07-13
 */

public class BenkeForLanProficiencyDaoImpl extends BaseDaoImpl<BenkeForLanProficiency>implements BenkeForLanProficiencyDao{

	@Override
	public int addRecord(BenkeForLanProficiency bflp) {
		
		int result = 0;

		String t_sql = "update " + BenkeForLanProficiencyTable.TABLE_NAME + " set "
				+ BenkeForLanProficiencyTable.BFLP_SERIALNUMBER + " = "
				+ BenkeForLanProficiencyTable.BFLP_SERIALNUMBER + " +1 where "
				+ BenkeForLanProficiencyTable.BFLP_SERIALNUMBER + ">=?";

		
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
			t_pstmt.setInt(1, bflp.getBflp_serialnumber());
			
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
		String sql = "insert into " + BenkeForLanProficiencyTable.TABLE_NAME + "("
				+ BenkeForLanProficiencyTable.BFLP_STUNUM + ","
				+ BenkeForLanProficiencyTable.BFLP_COLLEGE1 + ","
				+ BenkeForLanProficiencyTable.BFLP_NAME + ","
				+ BenkeForLanProficiencyTable.BFLP_MAJOR + ","
				+ BenkeForLanProficiencyTable.BFLP_GRADE + ","
				+ BenkeForLanProficiencyTable.BFLP_DEGREE + ","
				+ BenkeForLanProficiencyTable.BFLP_LEVEL + ","
				+ BenkeForLanProficiencyTable.BFLP_GPA + ","
				+ BenkeForLanProficiencyTable.BFLP_RANK + ","
				+ BenkeForLanProficiencyTable.BFLP_ISPUSH + ","
				+ BenkeForLanProficiencyTable.BFLP_SERIALNUMBER+","
				+ BenkeForLanProficiencyTable.BFLP_COLLEGE+","
				+ BenkeForLanProficiencyTable.ISNULL
				+ ")values(?,?,?,?,?,?,?,?,?,?,?,?,?)";

		System.out.println(sql);
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, bflp.getBflp_stunum());
			pstmt.setString(2, bflp.getBflp_college1());
			pstmt.setString(3, bflp.getBflp_name());
			pstmt.setString(4, bflp.getBflp_major());
			pstmt.setString(5, bflp.getBflp_grade());
			pstmt.setString(6, bflp.getBflp_degree());
			pstmt.setString(7, bflp.getBflp_level());
			pstmt.setString(8, bflp.getBflp_gpa());
			pstmt.setString(9, bflp.getBflp_rank());
			pstmt.setString(10, bflp.getBflp_ispush());
			
			pstmt.setInt(11, bflp.getBflp_serialnumber());
			pstmt.setString(12, bflp.getBflp_college());
			pstmt.setInt(13, bflp.getIsnull());
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
	public int getBenkeForLanProficiencyCount(Map queryParams) {
		
		int count = 0;
		//获取条件的语句
		String sql = "select count(*) from " + BenkeForLanProficiencyTable.TABLE_NAME
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
		sql += String.format(" or %s ='%s'", BenkeForLanProficiencyTable.BFLP_COLLEGE, "");
		
		
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
	public List<BenkeForLanProficiency> getAllBenkeForLanProficiency(int start, int end,
			String sortStr, String orderStr, Map queryParams) {
		
		String sql = " select tmp.* from ( " + " select * from "
				+ BenkeForLanProficiencyTable.TABLE_NAME + " where 1=1 ";
		
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
		
		List<BenkeForLanProficiency> bflps = new ArrayList<BenkeForLanProficiency>();
		try {
			
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int bflp_id = resultSet.getInt(BenkeForLanProficiencyTable.BFLP_ID);
				String bflp_stunum = resultSet.getString(BenkeForLanProficiencyTable.BFLP_STUNUM);
				String bflp_college1 = resultSet.getString(BenkeForLanProficiencyTable.BFLP_COLLEGE1);
				String bflp_name = resultSet.getString(BenkeForLanProficiencyTable.BFLP_NAME);
				String bflp_major = resultSet.getString(BenkeForLanProficiencyTable.BFLP_MAJOR);
				String bflp_grade = resultSet.getString(BenkeForLanProficiencyTable.BFLP_GRADE);
				String bflp_degree = resultSet.getString(BenkeForLanProficiencyTable.BFLP_DEGREE);
				String bflp_level = resultSet.getString(BenkeForLanProficiencyTable.BFLP_LEVEL);
				String bflp_gpa = resultSet.getString(BenkeForLanProficiencyTable.BFLP_GPA);
				String bflp_rank = resultSet.getString(BenkeForLanProficiencyTable.BFLP_RANK);
				String bflp_ispush = resultSet.getString(BenkeForLanProficiencyTable.BFLP_ISPUSH);
				
				
				int bflp_serialnumber = resultSet.getInt(BenkeForLanProficiencyTable.BFLP_SERIALNUMBER);
				
				String bflp_comments = resultSet.getString(BenkeForLanProficiencyTable.BFLP_COMMENTS);
				if(null==bflp_comments){
					bflp_comments="";
				}
				String bflp_college = resultSet.getString(BenkeForLanProficiencyTable.BFLP_COLLEGE);
				int isnull = resultSet.getInt(BenkeForLanProficiencyTable.ISNULL);
				
				BenkeForLanProficiency bflp = new BenkeForLanProficiency(bflp_id, bflp_stunum, bflp_college1,
						bflp_name, bflp_major, bflp_grade, bflp_degree, bflp_level, bflp_gpa, bflp_rank, bflp_ispush, 
						bflp_serialnumber, bflp_comments, bflp_college, isnull);
				
				bflps.add(bflp);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return bflps;
	}

	@Override
	public int alterBenkeForLanProficiency(Map<String, String> valueMap, String id) {
		
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(BenkeForLanProficiencyTable.BFLP_ID, id);
		int result = updateRecord(BenkeForLanProficiencyTable.TABLE_NAME, valueMap,condition);
		return result;
	}
	
	
	

	@Override
	public boolean batchDelete(String[] bflpids) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();

		for (String bflpid : bflpids) {
			String sql = "delete from " + BenkeForLanProficiencyTable.TABLE_NAME
					+ " where " + BenkeForLanProficiencyTable.BFLP_ID + " = '" + bflpid + "'";
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
		String sql = "delete from " + BenkeForLanProficiencyTable.TABLE_NAME
				+ " where " + BenkeForLanProficiencyTable.BFLP_COLLEGE + " = '" + college + "'" +" and "
				+BenkeForLanProficiencyTable.BFLP_DEADLINE+" is null ";
		
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
	public List<BenkeForLanProficiency> getAllBenkeForLanProficiency() {
		String sql = " select * from " + BenkeForLanProficiencyTable.TABLE_NAME
				+ " where 1=1 " + " order by " + BenkeForLanProficiencyTable.BFLP_ID;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		List<BenkeForLanProficiency> bflps = new ArrayList<BenkeForLanProficiency>();
		try {
			
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int bflp_id = resultSet.getInt(BenkeForLanProficiencyTable.BFLP_ID);
				String bflp_stunum = resultSet.getString(BenkeForLanProficiencyTable.BFLP_STUNUM);
				String bflp_college1 = resultSet.getString(BenkeForLanProficiencyTable.BFLP_COLLEGE1);
				String bflp_name = resultSet.getString(BenkeForLanProficiencyTable.BFLP_NAME);
				String bflp_major = resultSet.getString(BenkeForLanProficiencyTable.BFLP_MAJOR);
				String bflp_grade = resultSet.getString(BenkeForLanProficiencyTable.BFLP_GRADE);
				String bflp_degree = resultSet.getString(BenkeForLanProficiencyTable.BFLP_DEGREE);
				String bflp_level = resultSet.getString(BenkeForLanProficiencyTable.BFLP_LEVEL);
				String bflp_gpa = resultSet.getString(BenkeForLanProficiencyTable.BFLP_GPA);
				String bflp_rank = resultSet.getString(BenkeForLanProficiencyTable.BFLP_RANK);
				String bflp_ispush = resultSet.getString(BenkeForLanProficiencyTable.BFLP_ISPUSH);
				
				
				int bflp_serialnumber = resultSet.getInt(BenkeForLanProficiencyTable.BFLP_SERIALNUMBER);
				
				String bflp_comments = resultSet.getString(BenkeForLanProficiencyTable.BFLP_COMMENTS);
				if(null==bflp_comments){
					bflp_comments="";
				}
				String bflp_college = resultSet.getString(BenkeForLanProficiencyTable.BFLP_COLLEGE);
				int isnull = resultSet.getInt(BenkeForLanProficiencyTable.ISNULL);
				
				BenkeForLanProficiency bflp = new BenkeForLanProficiency(bflp_id, bflp_stunum, bflp_college1,
						bflp_name, bflp_major, bflp_grade, bflp_degree, bflp_level, bflp_gpa, bflp_rank, bflp_ispush, 
						bflp_serialnumber, bflp_comments, bflp_college, isnull);
				
				bflps.add(bflp);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return bflps;
	}
}
