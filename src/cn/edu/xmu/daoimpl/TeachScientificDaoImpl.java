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

import cn.edu.xmu.dao.TeachScientificDao;
import cn.edu.xmu.entity.TeachScientific;
import cn.edu.xmu.table.ForeignExchangeTable;
import cn.edu.xmu.table.SchoolExecutiveUnitTable;
import cn.edu.xmu.table.SchoolNetTable;
import cn.edu.xmu.table.TeachResearchUnitTable;
import cn.edu.xmu.table.TeachScientificTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;

public class TeachScientificDaoImpl extends BaseDaoImpl<TeachScientific>
		implements TeachScientificDao {

	// 获取总数量
	@Override
	public int getTeachScientificCount(Map queryParams) {
		int count = 0;
		String sql = "select count(*) from " + TeachScientificTable.TABLE_NAME
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
		sql += String.format(" or %s ='%s'", TeachScientificTable.TS_COLLEGE, "");
		
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
		System.err.println(count);
		return count;
	}

	// 获取所有数据
	@Override
	public List<TeachScientific> getAllTeachScientific(int start, int end,
			String sortStr, String orderStr, Map queryParams) {
		String sql = " select tmp.* from ( " + " select * from "
				+ TeachScientificTable.TABLE_NAME + " where 1=1 ";
		
		for (Object object : queryParams.keySet()) {
			String key = object.toString();
			String value = queryParams.get(key).toString();
			sql += String.format(" and %s like  '%%%s%%'", key, value);
		}
		
		sql += " order by " + sortStr + " " + orderStr + " ) tmp limit "
				+ start + " ," + end;
		System.out.println(sql);
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
			List<TeachScientific> TeachScientifics = new ArrayList<TeachScientific>();

			while (resultSet.next()) {
				int ts_id = resultSet.getInt(TeachScientificTable.TS_ID);
				String ts_name = resultSet
						.getString(TeachScientificTable.TS_NAME);
				String ts_number = resultSet
						.getString(TeachScientificTable.TS_NUMBER);
				String ts_head = resultSet
						.getString(TeachScientificTable.TS_HEAD);
				int ts_serialnumber = resultSet
						.getInt(TeachScientificTable.TS_SERIALNUMBER);
				Date ts_deadline = resultSet
						.getDate(TeachScientificTable.TS_DEADLINE);
				String ts_college = resultSet
						.getString(TeachScientificTable.TS_COLLEGE);
				String ts_comments = resultSet
						.getString(TeachScientificTable.TS_COMMENTS);

				int isnull = resultSet.getInt(TeachScientificTable.ISNULL);
				if(ts_comments==null)
					ts_comments="";
				
				TeachScientific teachScientific = new TeachScientific(ts_id,
						ts_name, ts_number, ts_head, ts_serialnumber,
						ts_deadline, ts_college, ts_comments,isnull);

				TeachScientifics.add(teachScientific);
			}
			return TeachScientifics;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
	}

	// 添加
	@Override
	public int addTeachScientific(String name, String number,
			String head, int serialnumber) {
	       int result = 0;
			
	        String sql2 = "update "+ TeachScientificTable.TABLE_NAME+" set "+TeachScientificTable.TS_SERIALNUMBER+
	        		" = "+TeachScientificTable.TS_SERIALNUMBER+" +1 where "+TeachScientificTable.TS_SERIALNUMBER+">="+serialnumber;
			
			Connection connection2 = null;
			try {
				connection2 = JdbcUtils_DBCP.getConnection();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			PreparedStatement pstmt2 = null;
			try {
				pstmt2 = connection2.prepareStatement(sql2);
				result = pstmt2.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				return 0;
			} finally {
				JdbcUtils_DBCP.release(connection2, pstmt2, null);
			}
			
			String sql = "insert into "+ TeachScientificTable.TABLE_NAME+"("+TeachScientificTable.TS_NAME+","+TeachScientificTable.TS_NUMBER+","
			        +TeachScientificTable.TS_HEAD+","+TeachScientificTable.TS_SERIALNUMBER+")values(?,?,?,?)";
			
			
			Connection connection = null;
			try {
				connection = JdbcUtils_DBCP.getConnection();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			PreparedStatement pstmt = null;
			
			try {
				pstmt = connection.prepareStatement(sql);
				pstmt.setString(1, name);
				pstmt.setString(2, number);
				pstmt.setString(3, head);
				pstmt.setInt(4, serialnumber);
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				result = -1;
			}finally{
				JdbcUtils_DBCP.release(connection, pstmt, null);
			}
			
			return result;
	}

	// 修改
	@Override
	public int alterTeachScientific(Map<String, String> valueMap, String id) {
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(TeachScientificTable.TS_ID, id);
		int result = updateRecord(TeachScientificTable.TABLE_NAME, valueMap,
				condition);
		return result;
	}

	// 批量删除
	@Override
	public boolean batchDelete(String[] trids) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();

		for (String trid : trids) {
			String sql = "delete from " + TeachScientificTable.TABLE_NAME
					+ " where " + TeachScientificTable.TS_ID + " = '" + trid
					+ "'";
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
	public List<TeachScientific> findForPage(int start, int end,
			String sortStr, String orderStr, Map queryParams) {
		// TODO Auto-generated method stub
		return null;
	}

	// 得到所有单位
	@Override
	public List<TeachScientific> getAllTeachScientific() {
		String sql =  " select * from "
				+ TeachScientificTable.TABLE_NAME + " where 1=1 ";
		
		System.out.println(sql);
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
			List<TeachScientific> TeachScientifics = new ArrayList<TeachScientific>();

			while (resultSet.next()) {
				int ts_id = resultSet.getInt(TeachScientificTable.TS_ID);
				String ts_name = resultSet
						.getString(TeachScientificTable.TS_NAME);
				String ts_number = resultSet
						.getString(TeachScientificTable.TS_NUMBER);
				String ts_head = resultSet
						.getString(TeachScientificTable.TS_HEAD);
				int ts_serialnumber = resultSet
						.getInt(TeachScientificTable.TS_SERIALNUMBER);
				Date ts_deadline = resultSet
						.getDate(TeachScientificTable.TS_DEADLINE);
				String ts_college = resultSet
						.getString(TeachScientificTable.TS_COLLEGE);
				String ts_comments = resultSet
						.getString(TeachScientificTable.TS_COMMENTS);

				int isnull = resultSet.getInt(TeachScientificTable.ISNULL);
				if(ts_comments==null)
					ts_comments="";
				
				TeachScientific teachScientific = new TeachScientific(ts_id,
						ts_name, ts_number, ts_head, ts_serialnumber,
						ts_deadline, ts_college, ts_comments,isnull);

				TeachScientifics.add(teachScientific);
			}
			return TeachScientifics;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public int addTeachScientificRecord(TeachScientific teachScientific) {
		int result = 0;
		String t_sql = "update " + TeachScientificTable.TABLE_NAME + " set "
				+ TeachScientificTable.TS_SERIALNUMBER + " = "
				+ TeachScientificTable.TS_SERIALNUMBER + " +1 where "
				+ TeachScientificTable.TS_SERIALNUMBER + ">=?";
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
			t_pstmt.setInt(1, teachScientific.getTs_serialnumber());
			
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
		String sql = "insert into " + TeachScientificTable.TABLE_NAME + "("
				+ TeachScientificTable.TS_NAME + ","
				+ TeachScientificTable.TS_NUMBER + ","
				+ TeachScientificTable.TS_HEAD + ","
				+ TeachScientificTable.TS_SERIALNUMBER + ","
				+ TeachScientificTable.TS_DEADLINE + ","
				+ TeachScientificTable.TS_COLLEGE + ","
				+ TeachScientificTable.TS_COMMENTS+"," 
				+ TeachScientificTable.ISNULL
				+ ")values(?,?,?,?,?,?,?,?)";

		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, teachScientific.getTs_name());
			pstmt.setString(2, teachScientific.getTs_number());
			pstmt.setString(3, teachScientific.getTs_head());
			pstmt.setInt(4, teachScientific.getTs_serialnumber());
			pstmt.setDate(5, (Date)teachScientific.getTs_deadline());
			pstmt.setString(6, teachScientific.getTs_college());
			pstmt.setString(7, teachScientific.getTs_comments());
			pstmt.setInt(8, teachScientific.getIsnull());
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

	/**
	 * 返回所有的单位号给Sec_GetAllDepartmentNumber制作1-3+1-4+"000"的下拉框
	 * @return
	 */
	public int[] getDepartmentNumber()
	{
		int []departmentNumber = null;
		//获取条件的语句
		String sql = "select Ts_number from " + TeachScientificTable.TABLE_NAME
				+ " where 1 = 1";
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		try {
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			departmentNumber = (int[])resultSet.getArray(0).getArray();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		
		return departmentNumber;
	}

	@Override
	public List<String> getAllUnits(Map params) {
		String sql = " select distinct "+TeachScientificTable.TS_NAME+" from " + TeachScientificTable.TABLE_NAME
				+ " where 1=1 " ;
		
		if (!(params == null || params.keySet().size() == 0)) {
			for (Object object : params.keySet()) {

				String key = object.toString();
				String value = params.get(key).toString();
				sql += String.format("and %s like  '%s%%' ", key, value);
			}
		}
		sql += " order by " + TeachScientificTable.TS_ID;
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
			List<String> teachingUnitNames = new ArrayList<String>();
			while (resultSet.next()) {
				String name = resultSet.getString(TeachScientificTable.TS_NAME);
				teachingUnitNames.add(name);
			}
			return teachingUnitNames;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
	}

	@Override
	public void deleteByCollegeandDeadline(String college, Date deadline)
			throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();
		String sql = "delete from " + TeachScientificTable.TABLE_NAME
				+ " where " + TeachScientificTable.TS_COLLEGE + " = '" + college + "'" +" and ts_deadline is null ";
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
