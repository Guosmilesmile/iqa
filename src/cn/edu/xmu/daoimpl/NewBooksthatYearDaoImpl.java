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

import cn.edu.xmu.dao.NewBooksthatYearDao;
import cn.edu.xmu.entity.NewBooksthatYear;
import cn.edu.xmu.table.NewBooksthatYearTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;


/**
 * @author zhantu
 * 图书当年新增情况  实体类功能 ——接口实现
 * date 2015-06-30
 */

public class NewBooksthatYearDaoImpl extends BaseDaoImpl<NewBooksthatYear>implements NewBooksthatYearDao{

	@Override
	public int addRecord(NewBooksthatYear nby) {
		
		int result = 0;

		String t_sql = "update " + NewBooksthatYearTable.TABLE_NAME + " set "
				+ NewBooksthatYearTable.NBY_SERIALNUMBER + " = "
				+ NewBooksthatYearTable.NBY_SERIALNUMBER + " +1 where "
				+ NewBooksthatYearTable.NBY_SERIALNUMBER + ">=?";

		
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
			t_pstmt.setInt(1, nby.getNby_serialnumber());
			
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
		String sql = "insert into " + NewBooksthatYearTable.TABLE_NAME + "("
				+ NewBooksthatYearTable.NBY_PAPERBOOKSNUMBER + ","
				+ NewBooksthatYearTable.NBY_EBOOKSNUMBER + ","
				+ NewBooksthatYearTable.NBY_DOCUMENTACQUISITIONCOST + ","
				+ NewBooksthatYearTable.NBY_BOOKCIRCULATION + ","
				+ NewBooksthatYearTable.NBY_ELECTRONICRESOURCEACCESS + ","
				+ NewBooksthatYearTable.NBY_SERIALNUMBER + ","
				+ NewBooksthatYearTable.NBY_COLLEGE + ","
				+ NewBooksthatYearTable.NBY_COMMENTS + ","
				+ NewBooksthatYearTable.ISNULL
				+ ")values(?,?,?,?,?,?,?,?,?)";

		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, nby.getNby_paperbooksnumber());
			pstmt.setInt(2, nby.getNby_ebooksnumber());
			pstmt.setFloat(3, nby.getNby_documentacquisitioncost());
			pstmt.setInt(4, nby.getNby_bookcirculation());
			pstmt.setInt(5, nby.getNby_electronicresourceaccess());
			pstmt.setInt(6, nby.getNby_serialnumber());
			pstmt.setString(7, nby.getNby_college());
			pstmt.setString(8, nby.getNby_comments());
			pstmt.setInt(9, nby.getIsnull());
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
	public int getNewBooksthatYearCount(Map queryParams) {
		
		int count = 0;
		//获取条件的语句
		String sql = "select count(*) from " + NewBooksthatYearTable.TABLE_NAME
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
		sql += String.format(" or %s ='%s'", NewBooksthatYearTable.NBY_COLLEGE, "");
		
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
	public List<NewBooksthatYear> getAllNewBooksthatYear(int start, int end,
			String sortStr, String orderStr, Map queryParams) {
		
		String sql = " select tmp.* from ( " + " select * from "
				+ NewBooksthatYearTable.TABLE_NAME + " where 1=1 ";
		
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
		
		List<NewBooksthatYear> nbys = new ArrayList<NewBooksthatYear>();
		try {
			
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int nby_id = resultSet.getInt(NewBooksthatYearTable.NBY_ID);
				Integer nby_paperbooksnumber = resultSet.getInt(NewBooksthatYearTable.NBY_PAPERBOOKSNUMBER);
				if(nby_paperbooksnumber==-999)
					nby_paperbooksnumber = null;
				
				Integer nby_ebooksnumber = resultSet.getInt(NewBooksthatYearTable.NBY_EBOOKSNUMBER);
				if(nby_ebooksnumber==-999)
					nby_ebooksnumber = null;
				
				Float nby_documentacquisitioncost = resultSet.getFloat(NewBooksthatYearTable.NBY_DOCUMENTACQUISITIONCOST);
				if(nby_documentacquisitioncost==-999)
					nby_documentacquisitioncost = null;
				
				Integer nby_bookcirculation = resultSet.getInt(NewBooksthatYearTable.NBY_BOOKCIRCULATION);
				if(nby_bookcirculation==-999)
					nby_bookcirculation = null;
				
				Integer nby_electronicresourceaccess = resultSet.getInt(NewBooksthatYearTable.NBY_ELECTRONICRESOURCEACCESS);
				if(nby_electronicresourceaccess==-999)
					nby_electronicresourceaccess = null;
				
				int nby_serialnumber = resultSet.getInt(NewBooksthatYearTable.NBY_SERIALNUMBER);
				Date nby_deadline = resultSet.getDate(NewBooksthatYearTable.NBY_DEADLINE);
				String nby_comments = resultSet.getString(NewBooksthatYearTable.NBY_COMMENTS);
				if(nby_comments==null)
					nby_comments="";
				String nby_college = resultSet.getString(NewBooksthatYearTable.NBY_COLLEGE);
				int isnull = resultSet.getInt(NewBooksthatYearTable.ISNULL);
				
				NewBooksthatYear nby = new NewBooksthatYear(nby_id, nby_paperbooksnumber,
						nby_ebooksnumber, nby_documentacquisitioncost, nby_bookcirculation,
						nby_electronicresourceaccess, nby_serialnumber, nby_deadline, nby_college, nby_comments, isnull);
				
				nbys.add(nby);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return nbys;
	}

	@Override
	public int alterNewBooksthatYear(Map<String, String> valueMap, String id) {
		
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(NewBooksthatYearTable.NBY_ID, id);
		int result = updateRecord(NewBooksthatYearTable.TABLE_NAME, valueMap,
				condition);
		return result;
	}

	@Override
	public boolean batchDelete(String[] nbyids) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();

		for (String nbyid : nbyids) {
			String sql = "delete from " + NewBooksthatYearTable.TABLE_NAME
					+ " where " + NewBooksthatYearTable.NBY_ID + " = '" + nbyid + "'";
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
	public List<NewBooksthatYear> getNewBooksthatYear(int start, int end,
			String sortStr, String orderStr, Map<String, String> params,
			Date deadline) {
		String sql = " select tmp.* from ( " + " select * from "
				+ NewBooksthatYearTable.TABLE_NAME + " where 1=1 ";
		if (deadline != null) {

			sql += String.format("and "+NewBooksthatYearTable.NBY_DEADLINE+" like  '%s%%' ", deadline);
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

		List<NewBooksthatYear> newBooksthatYears = new ArrayList<NewBooksthatYear>();
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
				int nby_id = resultSet.getInt(NewBooksthatYearTable.NBY_ID);
				Integer nby_paperbooksnumber = resultSet.getInt(NewBooksthatYearTable.NBY_PAPERBOOKSNUMBER);
				if(nby_paperbooksnumber==-999)
					nby_paperbooksnumber = null;
				
				Integer nby_ebooksnumber = resultSet.getInt(NewBooksthatYearTable.NBY_EBOOKSNUMBER);
				if(nby_ebooksnumber==-999)
					nby_ebooksnumber = null;
				
				Float nby_documentacquisitioncost = resultSet.getFloat(NewBooksthatYearTable.NBY_DOCUMENTACQUISITIONCOST);
				if(nby_documentacquisitioncost==-999)
					nby_documentacquisitioncost = null;
				
				Integer nby_bookcirculation = resultSet.getInt(NewBooksthatYearTable.NBY_BOOKCIRCULATION);
				if(nby_bookcirculation==-999)
					nby_bookcirculation = null;
				
				Integer nby_electronicresourceaccess = resultSet.getInt(NewBooksthatYearTable.NBY_ELECTRONICRESOURCEACCESS);
				if(nby_electronicresourceaccess==-999)
					nby_electronicresourceaccess = null;
				
				int nby_serialnumber = resultSet.getInt(NewBooksthatYearTable.NBY_SERIALNUMBER);
				Date nby_deadline = resultSet.getDate(NewBooksthatYearTable.NBY_DEADLINE);
				String nby_comments = resultSet.getString(NewBooksthatYearTable.NBY_COMMENTS);
				String nby_college = resultSet.getString(NewBooksthatYearTable.NBY_COLLEGE);
				int isnull = resultSet.getInt(NewBooksthatYearTable.ISNULL);
				
				NewBooksthatYear nby = new NewBooksthatYear(nby_id, nby_paperbooksnumber,
						nby_ebooksnumber, nby_documentacquisitioncost, nby_bookcirculation,
						nby_electronicresourceaccess, nby_serialnumber, nby_deadline, nby_college, nby_comments, isnull);

				newBooksthatYears.add(nby);
			}
			return newBooksthatYears;
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
		String sql = "delete from " + NewBooksthatYearTable.TABLE_NAME
				+ " where " + NewBooksthatYearTable.NBY_COLLEGE + " = '" + college + "'" +" and nby_deadline is null ";
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
	public List<NewBooksthatYear> getAllNewBooksthatYear() {
		String sql = " select * from " + NewBooksthatYearTable.TABLE_NAME
				+ " where 1=1 " + " order by " + NewBooksthatYearTable.NBY_ID;
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
			List<NewBooksthatYear> newBooksthatYearList = new ArrayList<NewBooksthatYear>();
			while (resultSet.next()) {
				int nby_id = resultSet.getInt(NewBooksthatYearTable.NBY_ID);
				Integer nby_paperbooksnumber = resultSet.getInt(NewBooksthatYearTable.NBY_PAPERBOOKSNUMBER);
				if(nby_paperbooksnumber==-999)
					nby_paperbooksnumber = null;
				
				Integer nby_ebooksnumber = resultSet.getInt(NewBooksthatYearTable.NBY_EBOOKSNUMBER);
				if(nby_ebooksnumber==-999)
					nby_ebooksnumber = null;
				
				Float nby_documentacquisitioncost = resultSet.getFloat(NewBooksthatYearTable.NBY_DOCUMENTACQUISITIONCOST);
				if(nby_documentacquisitioncost==-999)
					nby_documentacquisitioncost = null;
				
				Integer nby_bookcirculation = resultSet.getInt(NewBooksthatYearTable.NBY_BOOKCIRCULATION);
				if(nby_bookcirculation==-999)
					nby_bookcirculation = null;
				
				Integer nby_electronicresourceaccess = resultSet.getInt(NewBooksthatYearTable.NBY_ELECTRONICRESOURCEACCESS);
				if(nby_electronicresourceaccess==-999)
					nby_electronicresourceaccess = null;
				
				int nby_serialnumber = resultSet.getInt(NewBooksthatYearTable.NBY_SERIALNUMBER);
				Date nby_deadline = resultSet.getDate(NewBooksthatYearTable.NBY_DEADLINE);
				String nby_comments = resultSet.getString(NewBooksthatYearTable.NBY_COMMENTS);
				String nby_college = resultSet.getString(NewBooksthatYearTable.NBY_COLLEGE);
				int isnull = resultSet.getInt(NewBooksthatYearTable.ISNULL);
				
				NewBooksthatYear nby = new NewBooksthatYear(nby_id, nby_paperbooksnumber,
						nby_ebooksnumber, nby_documentacquisitioncost, nby_bookcirculation,
						nby_electronicresourceaccess, nby_serialnumber, nby_deadline, nby_college, nby_comments, isnull);
				
				newBooksthatYearList.add(nby);
			}
			return newBooksthatYearList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
	}
}
