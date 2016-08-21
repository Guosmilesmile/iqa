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

import cn.edu.xmu.dao.AdvancedIndividualDao;
import cn.edu.xmu.entity.AdvancedIndividual;
import cn.edu.xmu.table.AdvancedIndividualTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;

public class AdvancedIndividualDaoImpl extends BaseDaoImpl<AdvancedIndividual> implements AdvancedIndividualDao {

	@Override
	public int addRecord(AdvancedIndividual ai) {
		int result = 0;

		String t_sql = "update " + AdvancedIndividualTable.TABLE_NAME + " set "
				+ AdvancedIndividualTable.AI_SERIALNUMBER + " = "
				+ AdvancedIndividualTable.AI_SERIALNUMBER + " +1 where "
				+ AdvancedIndividualTable.AI_SERIALNUMBER + ">=?";

		
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
			t_pstmt.setInt(1, ai.getAi_serialnumber());
			
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
		String sql = "insert into " + AdvancedIndividualTable.TABLE_NAME + "("
				+ AdvancedIndividualTable.AI_ORDER
				+ "," + AdvancedIndividualTable.AI_IMPORTCOLLEGE+ ","
				+ AdvancedIndividualTable.AI_NAME + ","
				+ AdvancedIndividualTable.AI_HONORYEAR + ","
				+ AdvancedIndividualTable.AI_SERIALNUMBER + ","
				+ AdvancedIndividualTable.AI_COLLEGE + ","
				+ AdvancedIndividualTable.AI_COMMENTS+","
				+ AdvancedIndividualTable.ISNULL
				+ ")values(?,?,?,?,?,?,?,?)";

		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, ai.getAi_order());
			pstmt.setString(2, ai.getAi_importcollege());
			pstmt.setString(3, ai.getAi_name());
			pstmt.setString(4, ai.getAi_honoryear());
			pstmt.setInt(5, ai.getAi_serialnumber());
			pstmt.setString(6, ai.getAi_college());
			pstmt.setString(7, ai.getAi_comments());
			pstmt.setInt(8, ai.getIsnull());
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
	public boolean batchDelete(String[] snids) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();

		for (String snid : snids) {
			String sql = "delete from " + AdvancedIndividualTable.TABLE_NAME
					+ " where " + AdvancedIndividualTable.AI_ID + " = '" + snid + "'";
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
	public int alterAdvancedIndividual(Map<String, String> valueMap, String id) {
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(AdvancedIndividualTable.AI_ID, id);
		int result = updateRecord(AdvancedIndividualTable.TABLE_NAME, valueMap,
				condition);
		return result;
	}

	@Override
	public int getAdvancedIndividualCount(Map queryParams) {
		int count = 0;
		//获取条件的语句
		String sql = "select count(*) from " + AdvancedIndividualTable.TABLE_NAME
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
		sql += String.format(" or %s ='%s'", AdvancedIndividualTable.AI_COLLEGE, "");
		
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
	public List<AdvancedIndividual> getAllAdvancedIndividual(int start,
			int end, String sortStr, String orderStr, Map queryParams) {
		String sql = " select tmp.* from ( " + " select * from "
				+ AdvancedIndividualTable.TABLE_NAME + " where 1=1 ";
		
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
		
		List<AdvancedIndividual> ais = new ArrayList<AdvancedIndividual>();
		try {
			
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int ai_id = resultSet.getInt(AdvancedIndividualTable.AI_ID);
				Integer ai_order = resultSet.getInt(AdvancedIndividualTable.AI_ORDER);
				if(ai_order==-999)
					ai_order = null;
				
				String ai_importcollege = resultSet.getString(AdvancedIndividualTable.AI_IMPORTCOLLEGE);
				String ai_name = resultSet.getString(AdvancedIndividualTable.AI_NAME);
				String ai_honoryear = resultSet.getString(AdvancedIndividualTable.AI_HONORYEAR);
				int ai_serialnumber = resultSet.getInt(AdvancedIndividualTable.AI_SERIALNUMBER);
				String ai_comments = resultSet.getString(AdvancedIndividualTable.AI_COMMENTS);
				String ai_college = resultSet.getString(AdvancedIndividualTable.AI_COLLEGE);
				
				int isnull = resultSet.getInt(AdvancedIndividualTable.ISNULL);
				if(ai_comments==null)
					ai_comments="";
				
				AdvancedIndividual ai = new AdvancedIndividual(ai_id, ai_order,
						ai_importcollege, ai_name,ai_honoryear, ai_serialnumber, ai_comments,isnull,ai_college);
				
				ais.add(ai);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return ais;
	}

	@Override
	public List<AdvancedIndividual> getAllAdvancedIndividual() {
		String sql = " select * from "
				+ AdvancedIndividualTable.TABLE_NAME + " where 1=1 ";

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		List<AdvancedIndividual> ais = new ArrayList<AdvancedIndividual>();
		try {
			
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int ai_id = resultSet.getInt(AdvancedIndividualTable.AI_ID);
				Integer ai_order = resultSet.getInt(AdvancedIndividualTable.AI_ORDER);
				if(ai_order==-999)
					ai_order = null;
				
				String ai_importcollege = resultSet.getString(AdvancedIndividualTable.AI_IMPORTCOLLEGE);
				String ai_name = resultSet.getString(AdvancedIndividualTable.AI_NAME);
				String ai_honoryear = resultSet.getString(AdvancedIndividualTable.AI_HONORYEAR);
				int ai_serialnumber = resultSet.getInt(AdvancedIndividualTable.AI_SERIALNUMBER);
				String ai_comments = resultSet.getString(AdvancedIndividualTable.AI_COMMENTS);
				String ai_college = resultSet.getString(AdvancedIndividualTable.AI_COLLEGE);
				
				int isnull = resultSet.getInt(AdvancedIndividualTable.ISNULL);
				if(ai_comments==null)
					ai_comments="";
				
				AdvancedIndividual ai = new AdvancedIndividual(ai_id, ai_order,
						ai_importcollege, ai_name,ai_honoryear, ai_serialnumber, ai_comments,isnull,ai_college);
				
				ais.add(ai);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return ais;
	}

	@Override
	public void deleteByCollegeandDeadline(String college, Date deadline)
			throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();
		String sql = "delete from " + AdvancedIndividualTable.TABLE_NAME
				+ " where " + AdvancedIndividualTable.AI_COLLEGE + " = '" + college + "'" +" and ai_deadline is null ";
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
