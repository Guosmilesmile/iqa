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

import cn.edu.xmu.dao.LibraryYearDao;
import cn.edu.xmu.entity.LibraryYear;
import cn.edu.xmu.table.LibraryYearTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;

public class LibraryYearDaoImpl extends BaseDaoImpl<LibraryYear> implements LibraryYearDao {

	
	@Override
	public int addRecord(LibraryYear ly){
		
		int result = 0;
		
		String t_sql = "update " + LibraryYearTable.TABLE_NAME + " set "
				+ LibraryYearTable.LY_SERIALNUMBER + " = "
				+ LibraryYearTable.LY_SERIALNUMBER + " +1 where "
				+ LibraryYearTable.LY_SERIALNUMBER + ">=?";
		
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
			t_pstmt.setInt(1, ly.getLy_serialnumber());
			
			//执行插入操作并更新
			result = t_pstmt.executeUpdate();
			
		} catch (SQLException e) {
			//事务回滚，不做插入操作
			e.printStackTrace();
			return 0;
		} finally {
			JdbcUtils_DBCP.release(connection2, t_pstmt, null);
		}
		
		String sql = "insert into " + LibraryYearTable.TABLE_NAME + "("
				+ LibraryYearTable.LY_NUMBER+ "," 
				+ LibraryYearTable.LY_SEATNUMBER+","
				+ LibraryYearTable.LY_PAPERBOOKNUMBER + ","
				+ LibraryYearTable.LY_PAPERJOURNALNUMBER + ","
				+ LibraryYearTable.LY_PAPERJOURNALTYPE + ","
				+ LibraryYearTable.LY_EBOOKNUMBER + ","
				+ LibraryYearTable.LY_EBOOKCHNNUMBER + ","
				+ LibraryYearTable.LY_EBOOKFOREIGNNUMBER + ","
				+ LibraryYearTable.LY_EJOURNALTYPE + ","
				+ LibraryYearTable.LY_DATABASENUMBER + ","
				+ LibraryYearTable.LY_SERIALNUMBER + ","
				+ LibraryYearTable.LY_COLLEGE + ","
				+ LibraryYearTable.LY_COMMENTS + ","
				+ LibraryYearTable.ISNULL
				+ ")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, ly.getLy_number());
			pstmt.setInt(2, ly.getLy_seatnumber());
			pstmt.setInt(3, ly.getLy_paperbooknumber());
			pstmt.setInt(4, ly.getLy_paperjournalnumber());
			pstmt.setInt(5, ly.getLy_paperjournaltype());
			pstmt.setInt(6, ly.getLy_ebooknumber());
			pstmt.setInt(7, ly.getLy_ebookchnnumber());
			pstmt.setInt(8, ly.getLy_ebookforeignnumber());
			pstmt.setInt(9, ly.getLy_ejournaltype());
			pstmt.setInt(10, ly.getLy_databasenumber());
			pstmt.setInt(11, ly.getLy_serialnumber());
			pstmt.setString(12, ly.getLy_college());
			pstmt.setString(13, ly.getLy_comments());
			pstmt.setInt(14, ly.getIsnull());
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
	public int getLibraryYearCount(Map queryParams){
		int count = 0;
		String sql = "select count(*) from " + LibraryYearTable.TABLE_NAME
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
		sql += String.format(" or %s ='%s'", LibraryYearTable.LY_COLLEGE, "");
		
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
	public List<LibraryYear> getLibraryYear(int start, int end, String sortStr, 
			String orderStr, Map queryParams){
		
		String sql = " select tmp.* from ( " + " select * from "
				+ LibraryYearTable.TABLE_NAME + " where 1=1 ";
		
		for (Object object : queryParams.keySet()) {
			String key = object.toString();
			String value = queryParams.get(key).toString();
			sql += String.format(" and %s like  '%%%s%%'", key, value);
		}
		
		if (sortStr == "nope") {
			
		}
		else {
			sql += " order by " + sortStr + " " + orderStr + " ) tmp limit "
					+ start + " ," + end;
		}
	

		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		List<LibraryYear> seus = new ArrayList<LibraryYear>();
		try {
			
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int ly_id = resultSet.getInt(LibraryYearTable.LY_ID);
				Integer ly_number = resultSet.getInt(LibraryYearTable.LY_NUMBER);
				if (ly_number == -9)
					ly_number = null;
				Integer ly_seatnumber = resultSet.getInt(LibraryYearTable.LY_SEATNUMBER);
				if (ly_seatnumber == -9)
					ly_seatnumber = null;
				Integer ly_paperbooknumber = resultSet.getInt(LibraryYearTable.LY_PAPERBOOKNUMBER);
				if (ly_paperbooknumber == -9)
					ly_paperbooknumber = null;
				Integer ly_paperjournalnumber = resultSet.getInt(LibraryYearTable.LY_PAPERJOURNALNUMBER);
				if (ly_paperjournalnumber == -9)
					ly_paperjournalnumber = null;
				Integer ly_paperjournaltype = resultSet.getInt(LibraryYearTable.LY_PAPERJOURNALTYPE);
				if (ly_paperjournaltype == -9)
					ly_paperjournaltype = null;
				Integer ly_ebooknumber = resultSet.getInt(LibraryYearTable.LY_EBOOKNUMBER);
				if (ly_ebooknumber == -9)
					ly_ebooknumber = null;
				Integer ly_ebookchnnumber = resultSet.getInt(LibraryYearTable.LY_EBOOKCHNNUMBER);
				if (ly_ebookchnnumber == -9)
					ly_ebookchnnumber = null;
				Integer ly_ebookforeignnumber = resultSet.getInt(LibraryYearTable.LY_EBOOKFOREIGNNUMBER);
				if (ly_ebookforeignnumber == -9)
					ly_ebookforeignnumber = null;
				Integer ly_ejournaltype = resultSet.getInt(LibraryYearTable.LY_EJOURNALTYPE);
				if (ly_ejournaltype == -9)
					ly_ejournaltype = null;
				Integer ly_databasenumber = resultSet.getInt(LibraryYearTable.LY_DATABASENUMBER);
				if (ly_databasenumber == -9)
					ly_databasenumber = null;
				int ly_serialnumber = resultSet.getInt(LibraryYearTable.LY_SERIALNUMBER);
				String ly_comments = resultSet.getString(LibraryYearTable.LY_COMMENTS);
				String ly_deadline = resultSet.getString(LibraryYearTable.LY_DEADLINE);
				String ly_college = resultSet.getString(LibraryYearTable.LY_COLLEGE);
				int isnull = resultSet.getInt(LibraryYearTable.ISNULL);
				
				LibraryYear seu = new LibraryYear(ly_id, ly_number, ly_seatnumber,
						ly_paperbooknumber, ly_paperjournalnumber, ly_paperjournaltype,
						ly_ebooknumber,ly_ebookchnnumber, ly_ebookforeignnumber, ly_ejournaltype, ly_databasenumber, 
						ly_serialnumber, ly_college, ly_deadline,ly_comments, isnull);
				System.out.println(ly_paperbooknumber);
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
	public int alterLibraryYear(Map<String, String> valueMap, String id){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(LibraryYearTable.LY_ID, id);
		int result = updateRecord(LibraryYearTable.TABLE_NAME, valueMap,
				condition);
		return result;
	}
	
	@Override
	public boolean batchDelete(String[] lyids) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();

		for (String lyid : lyids) {
			String sql = "delete from " + LibraryYearTable.TABLE_NAME
					+ " where " + LibraryYearTable.LY_ID + " = '" + lyid + "'";
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
	public List<LibraryYear> getAll(){
		String sql = " select tmp.* from ( " + " select * from "
				+ LibraryYearTable.TABLE_NAME + " where 1=1 " + " order by " + LibraryYearTable.LY_ID;
		
	

		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		List<LibraryYear> seus = new ArrayList<LibraryYear>();
		try {
			
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int ly_id = resultSet.getInt(LibraryYearTable.LY_ID);
				Integer ly_number = resultSet.getInt(LibraryYearTable.LY_NUMBER);
				if (ly_number == -9)
					ly_number = null;
				Integer ly_seatnumber = resultSet.getInt(LibraryYearTable.LY_SEATNUMBER);
				if (ly_seatnumber == -9)
					ly_seatnumber = null;
				Integer ly_paperbooknumber = resultSet.getInt(LibraryYearTable.LY_PAPERBOOKNUMBER);
				if (ly_paperbooknumber == -9)
					ly_paperbooknumber = null;
				Integer ly_paperjournalnumber = resultSet.getInt(LibraryYearTable.LY_PAPERJOURNALNUMBER);
				if (ly_paperjournalnumber == -9)
					ly_paperjournalnumber = null;
				Integer ly_paperjournaltype = resultSet.getInt(LibraryYearTable.LY_PAPERJOURNALTYPE);
				if (ly_paperjournaltype == -9)
					ly_paperjournaltype = null;
				Integer ly_ebooknumber = resultSet.getInt(LibraryYearTable.LY_EBOOKNUMBER);
				if (ly_ebooknumber == -9)
					ly_ebooknumber = null;
				Integer ly_ebookchnnumber = resultSet.getInt(LibraryYearTable.LY_EBOOKCHNNUMBER);
				if (ly_ebookchnnumber == -9)
					ly_ebookchnnumber = null;
				Integer ly_ebookforeignnumber = resultSet.getInt(LibraryYearTable.LY_EBOOKFOREIGNNUMBER);
				if (ly_ebookforeignnumber == -9)
					ly_ebookforeignnumber = null;
				Integer ly_ejournaltype = resultSet.getInt(LibraryYearTable.LY_EJOURNALTYPE);
				if (ly_ejournaltype == -9)
					ly_ejournaltype = null;
				Integer ly_databasenumber = resultSet.getInt(LibraryYearTable.LY_DATABASENUMBER);
				if (ly_databasenumber == -9)
					ly_databasenumber = null;
				int ly_serialnumber = resultSet.getInt(LibraryYearTable.LY_SERIALNUMBER);
				String ly_comments = resultSet.getString(LibraryYearTable.LY_COMMENTS);
				String ly_deadline = resultSet.getString(LibraryYearTable.LY_DEADLINE);
				String ly_college = resultSet.getString(LibraryYearTable.LY_COLLEGE);
				int isnull = resultSet.getInt(LibraryYearTable.ISNULL);
				
				LibraryYear seu = new LibraryYear(ly_id, ly_number, ly_seatnumber,
						ly_paperbooknumber, ly_paperjournalnumber, ly_paperjournaltype,
						ly_ebooknumber,ly_ebookchnnumber, ly_ebookforeignnumber, ly_ejournaltype, ly_databasenumber, 
						ly_serialnumber, ly_college, ly_deadline,ly_comments, isnull);
				System.out.println(ly_paperbooknumber);
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
	public void deleteByCollegeandDeadline(String college, Date deadline)
			throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();
		String sql = "delete from " + LibraryYearTable.TABLE_NAME
				+ " where " + LibraryYearTable.LY_COLLEGE + " = '" + college + "'" +" and ly_deadline is null ";
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
