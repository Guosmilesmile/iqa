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

import cn.edu.xmu.dao.FixedAssetsDao;
import cn.edu.xmu.entity.FixedAssets;
import cn.edu.xmu.entity.SchoolNet;
import cn.edu.xmu.table.FixedAssetsTable;
import cn.edu.xmu.table.SchoolNetTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;

public class FixedAssetsDaoImpl extends BaseDaoImpl<FixedAssets>implements FixedAssetsDao {

	@Override
	public int addRecord(FixedAssets fa) {
		int result = 0;

		String t_sql = "update " + FixedAssetsTable.TABLE_NAME + " set "
				+ FixedAssetsTable.FA_SERIALNUMBER + " = "
				+ FixedAssetsTable.FA_SERIALNUMBER + " +1 where "
				+ FixedAssetsTable.FA_SERIALNUMBER + ">=?";

		
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
			t_pstmt.setInt(1, fa.getFa_serialnumber());
			
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
		String sql = "insert into " + FixedAssetsTable.TABLE_NAME + "("
				+ FixedAssetsTable.FA_IMPORTCOLLEGE
				+ "," + FixedAssetsTable.FA_FIXEDASSETSSUM+ ","
				+ FixedAssetsTable.FA_EQUIPMENTASSETSSUM + ","
				+ FixedAssetsTable.FA_NEWASSETS + ","
				+ FixedAssetsTable.FA_SERIALNUMBER + ","
				+ FixedAssetsTable.FA_COLLEGE + ","
				+ FixedAssetsTable.FA_COMMENTS+","
				+ FixedAssetsTable.ISNULL
				+ ")values(?,?,?,?,?,?,?,?)";

		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, fa.getFa_importcollege());
			pstmt.setFloat(2, fa.getFa_fixedassetssum());
			pstmt.setFloat(3, fa.getFa_equipmentassetssum());
			pstmt.setFloat(4, fa.getFa_newassets());
			pstmt.setInt(5, fa.getFa_serialnumber());
			pstmt.setString(6, fa.getFa_college());
			pstmt.setString(7, fa.getFa_comments());
			pstmt.setInt(8, fa.getIsnull());
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
	public boolean batchDelete(String[] faids) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();

		for (String faid : faids) {
			String sql = "delete from " + FixedAssetsTable.TABLE_NAME
					+ " where " + FixedAssetsTable.FA_ID + " = '" + faid + "'";
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
	public int alterFixedAssets(Map<String, String> valueMap, String id) {
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(FixedAssetsTable.FA_ID, id);
		int result = updateRecord(FixedAssetsTable.TABLE_NAME, valueMap,
				condition);
		return result;
	}

	@Override
	public int getFixedAssetsCount(Map queryParams) {
		int count = 0;
		//获取条件的语句
		String sql = "select count(*) from " + FixedAssetsTable.TABLE_NAME
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
		sql += String.format(" or %s ='%s'", FixedAssetsTable.FA_COLLEGE, "");
		
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
	public List<FixedAssets> getAllFixedAssets(int start, int end,
			String sortStr, String orderStr, Map queryParams) {
		String sql = " select tmp.* from ( " + " select * from "
				+ FixedAssetsTable.TABLE_NAME + " where 1=1 ";
		
		for (Object object : queryParams.keySet()) {
			String key = object.toString();
			String value = queryParams.get(key).toString();
			sql += String.format(" and %s like  '%%%s%%'", key, value);
		}
		
		if (sortStr  == "nope") {
			
		}else {
			sql += " order by " + sortStr + " " + orderStr + " ) tmp limit "
					+ start + " ," + end;
		}


		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		List<FixedAssets> fas = new ArrayList<FixedAssets>();
		try {
			
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int fa_id = resultSet.getInt(FixedAssetsTable.FA_ID);
				String fa_importcollege = resultSet.getString(FixedAssetsTable.FA_IMPORTCOLLEGE);
				Float fa_fixedassetssum = resultSet.getFloat(FixedAssetsTable.FA_FIXEDASSETSSUM);
				if(fa_fixedassetssum==-999)
					fa_fixedassetssum = null;
				
				Float fa_equipmentassetssum = resultSet.getFloat(FixedAssetsTable.FA_EQUIPMENTASSETSSUM);
				if(fa_equipmentassetssum==-999)
					fa_equipmentassetssum = null;
				
				Float fa_newassets = resultSet.getFloat(FixedAssetsTable.FA_NEWASSETS);
				if(fa_newassets==-999)
					fa_newassets = null;
				
				int fa_serialnumber = resultSet.getInt(FixedAssetsTable.FA_SERIALNUMBER);
				
				String fa_comments = resultSet.getString(FixedAssetsTable.FA_COMMENTS);
				String fa_college = resultSet.getString(FixedAssetsTable.FA_COLLEGE);
				int isnull = resultSet.getInt(FixedAssetsTable.ISNULL);
				if(fa_comments==null)
					fa_comments="";
				
				FixedAssets fa = new FixedAssets(fa_id, fa_importcollege,
						fa_fixedassetssum, fa_equipmentassetssum,fa_newassets, fa_serialnumber, fa_comments,isnull,fa_college);
				
				fas.add(fa);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return fas;
	}

	@Override
	public List<FixedAssets> getAllFixedAssets() {
		String sql = " select * from "
				+ FixedAssetsTable.TABLE_NAME + " where 1=1 ";
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		List<FixedAssets> fas = new ArrayList<FixedAssets>();
		try {
			
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int fa_id = resultSet.getInt(FixedAssetsTable.FA_ID);
				String fa_importcollege = resultSet.getString(FixedAssetsTable.FA_IMPORTCOLLEGE);
				Float fa_fixedassetssum = resultSet.getFloat(FixedAssetsTable.FA_FIXEDASSETSSUM);
				if(fa_fixedassetssum==-999)
					fa_fixedassetssum = null;
				
				Float fa_equipmentassetssum = resultSet.getFloat(FixedAssetsTable.FA_EQUIPMENTASSETSSUM);
				if(fa_equipmentassetssum==-999)
					fa_equipmentassetssum = null;
				
				Float fa_newassets = resultSet.getFloat(FixedAssetsTable.FA_NEWASSETS);
				if(fa_newassets==-999)
					fa_newassets = null;
				
				int fa_serialnumber = resultSet.getInt(FixedAssetsTable.FA_SERIALNUMBER);
				
				String fa_comments = resultSet.getString(FixedAssetsTable.FA_COMMENTS);
				String fa_college = resultSet.getString(FixedAssetsTable.FA_COLLEGE);
				int isnull = resultSet.getInt(FixedAssetsTable.ISNULL);
				if(fa_comments==null)
					fa_comments="";
				
				FixedAssets fa = new FixedAssets(fa_id, fa_importcollege,
						fa_fixedassetssum, fa_equipmentassetssum,fa_newassets, fa_serialnumber, fa_comments,isnull,fa_college);
				
				fas.add(fa);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return fas;
	}

	@Override
	public void deleteByCollegeandDeadline(String college, Date deadline)
			throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();
		String sql = "delete from " + FixedAssetsTable.TABLE_NAME
				+ " where " + FixedAssetsTable.FA_COLLEGE + " = '" + college + "'" +" and fa_deadline is null ";
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
