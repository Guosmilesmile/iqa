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

import cn.edu.xmu.dao.UndergraStuInlandCommuDao;
import cn.edu.xmu.entity.UndergraStuInlandCommu;
import cn.edu.xmu.table.UndergraStuInlandCommuTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;

/**
 * 
 * @author xiaoping 附表6-2-2-3本科生境内交流情况（学年） date 2015-7-9
 *
 */
public class UndergraStuInlandCommuDaoImpl extends BaseDaoImpl<UndergraStuInlandCommu>
		implements UndergraStuInlandCommuDao
{

	@Override
	public List<UndergraStuInlandCommu> getUndergraStuInlandCommus(int start, int end, String sortStr, String orderStr,
			Map queryParams, Date deadline)
	{
		String sql = " select tmp.* from ( " + " select * from " + UndergraStuInlandCommuTable.TABLE_NAME
				+ " where 1=1 ";
		if (deadline != null)
		{
			sql += String.format("and " + UndergraStuInlandCommuTable.USIC_DEADLINE + " like  '%s%%' ", deadline);
		}
		if (queryParams != null && queryParams.keySet().size() != 0)
		{
			for (Object object : queryParams.keySet())
			{
				String key = object.toString();
				String value = queryParams.get(key).toString();
				sql += String.format(" and %s like  '%%%s%%'", key, value);
			}
		}

		sql += " order by " + sortStr + " " + orderStr + " ) tmp limit " + start + " ," + end;

		System.out.println(sql);
		Connection connection = null;
		try
		{
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		}
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		try
		{
			System.out.println(sql);
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			List<UndergraStuInlandCommu> undergraStuInlandCommus = new ArrayList<UndergraStuInlandCommu>();

			while (resultSet.next())
			{
				int usic_id = resultSet.getInt(UndergraStuInlandCommuTable.USIC_ID);
				String usic_institute = resultSet.getString(UndergraStuInlandCommuTable.USIC_INSTITUTE);
				Integer usic_outnumber = resultSet.getInt(UndergraStuInlandCommuTable.USIC_OUTNUMBER);
				if(usic_outnumber == -9)
					usic_outnumber = null;
				Integer usic_innumber = resultSet.getInt(UndergraStuInlandCommuTable.USIC_INNUMBER);
				if(usic_innumber == -9)
					usic_innumber = null;
				int usic_serialnumber = resultSet.getInt(UndergraStuInlandCommuTable.USIC_SERIALNUMBER);
				Date usic_deadline = resultSet.getDate(UndergraStuInlandCommuTable.USIC_DEADLINE);
				String usic_college = resultSet.getString(UndergraStuInlandCommuTable.USIC_COLLEGE);
				String usic_comments = resultSet.getString(UndergraStuInlandCommuTable.USIC_COMMENTS);
				int usic_isnull = resultSet.getInt(UndergraStuInlandCommuTable.USIC_ISNULL);
				UndergraStuInlandCommu undergraStuInlandCommu = new UndergraStuInlandCommu(usic_id, usic_institute,
						usic_outnumber, usic_innumber, usic_serialnumber, usic_deadline, usic_college, usic_comments,usic_isnull);
				if(usic_institute.equals("合计"))
				{
					undergraStuInlandCommus.add(0, undergraStuInlandCommu);
					continue;
				}
				undergraStuInlandCommus.add(undergraStuInlandCommu);
			}
			return undergraStuInlandCommus;
		} catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		} finally
		{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
	}

	@Override
	public int getUndergraStuInlandCommuCount(Map queryParams)
	{
		int count = 0;
		String sql = "select count(*) from " + UndergraStuInlandCommuTable.TABLE_NAME + " where 1 = 1";
		Connection connection = null;
		try
		{
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return -1;
		}
		if (queryParams != null)
		{
			for (Object object : queryParams.keySet())
			{
				String key = object.toString();
				String value = queryParams.get(key).toString();
				sql += String.format(" and  %s like  '%%%s%%' ", key, value);
			}
			// sql += String.format(" or %s ='%s'", "college","");
		}

		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		try
		{
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			resultSet.next();
			count = resultSet.getInt(1);
		} catch (SQLException e)
		{
			e.printStackTrace();
			return -1;
		} finally
		{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		System.err.println(count);
		return count;
	}

	@Override
	public int getMaxSerialNum()
	{
		ResultSet resultSet = null;
		int result = 1;
		String sql = "select max(" + UndergraStuInlandCommuTable.USIC_SERIALNUMBER + ") from "
				+ UndergraStuInlandCommuTable.TABLE_NAME;

		Connection connection = null;
		PreparedStatement pstmt = null;
		try
		{
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next())
			{
				result = resultSet.getInt(1);
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
			return 0;
		} finally
		{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}

		return result;
	}

	@Override
	public boolean batchDelete(String[] usicids) throws SQLException
	{
		Connection connection = null;
		Statement stmt = null;
		try
		{
			connection = JdbcUtils_DBCP.getConnection();
			stmt = connection.createStatement();
		} catch (SQLException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}

		for (String usicid : usicids)
		{
			String sql = "delete from " + UndergraStuInlandCommuTable.TABLE_NAME + " where usic_id = '" + usicid + "'";
			System.out.println(sql);
			try
			{
				stmt.executeUpdate(sql);
			} catch (SQLException e)
			{
				e.printStackTrace();
				return false;
			}
		}
		JdbcUtils_DBCP.release(connection, stmt, null);
		return true;
	}

	@Override
	public int addUndergraStuInlandCommu(UndergraStuInlandCommu undergraStuInlandCommu)
	{
		int result = 0;

		String t_sql = "update " + UndergraStuInlandCommuTable.TABLE_NAME + " set "
				+ UndergraStuInlandCommuTable.USIC_SERIALNUMBER + " = " + UndergraStuInlandCommuTable.USIC_SERIALNUMBER
				+ " +1 where " + UndergraStuInlandCommuTable.USIC_SERIALNUMBER + ">=?";
		Connection connection2 = null;
		try
		{
			// 连接池取得对象连接
			connection2 = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1)
		{
			e1.printStackTrace();
			return 0;
		}
		PreparedStatement t_pstmt = null;
		try
		{
			// 获取操作对象
			t_pstmt = connection2.prepareStatement(t_sql);
			t_pstmt.setInt(1, undergraStuInlandCommu.getUsic_serialnumber());

			// 执行插入操作并更新
			result = t_pstmt.executeUpdate();

		} catch (SQLException e)
		{
			// 事务回滚，不做插入操作
			e.printStackTrace();
			return 0;
		} finally
		{
			JdbcUtils_DBCP.release(connection2, t_pstmt, null);
		}
		String sql = "insert into " + UndergraStuInlandCommuTable.TABLE_NAME + "("
				+ UndergraStuInlandCommuTable.USIC_INSTITUTE + "," + UndergraStuInlandCommuTable.USIC_OUTNUMBER + ","
				+ UndergraStuInlandCommuTable.USIC_INNUMBER + "," + UndergraStuInlandCommuTable.USIC_SERIALNUMBER + ","
				+ UndergraStuInlandCommuTable.USIC_DEADLINE + "," + UndergraStuInlandCommuTable.USIC_COLLEGE + ","
				+ UndergraStuInlandCommuTable.USIC_COMMENTS+ ","
						+ UndergraStuInlandCommuTable.USIC_ISNULL  + ")values(?,?,?,?,?,?,?,?)";

		System.out.println("添加纪录" + sql);
		Connection connection = null;
		try
		{
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1)
		{
			e1.printStackTrace();
			return -1;
		}
		PreparedStatement pstmt = null;

		try
		{
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, undergraStuInlandCommu.getUsic_institute());
			pstmt.setInt(2, undergraStuInlandCommu.getUsic_outnumber());
			pstmt.setInt(3, undergraStuInlandCommu.getUsic_innumber());
			pstmt.setInt(4, undergraStuInlandCommu.getUsic_serialnumber());
			pstmt.setDate(5, undergraStuInlandCommu.getUsic_deadline());
			pstmt.setString(6, undergraStuInlandCommu.getUsic_college());
			pstmt.setString(7, undergraStuInlandCommu.getUsic_comments());
			pstmt.setInt(8, undergraStuInlandCommu.getUsic_isnull());
			result = pstmt.executeUpdate();
		} catch (SQLException e)
		{
			e.printStackTrace();
			result = -1;
		} finally
		{
			JdbcUtils_DBCP.release(connection, pstmt, null);
		}
		return result;
	}

	@Override
	public int alterUndergraStuInlandCommu(Map<String, String> valueMap, String id)
	{
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(UndergraStuInlandCommuTable.USIC_ID, id);
		int result = updateRecord(UndergraStuInlandCommuTable.TABLE_NAME, valueMap, condition);
		return result;
	}
	
	@Override
	public List<UndergraStuInlandCommu> getEqualUndergraStuInlandCommu(Map<String, String> equalParams, Map<String, String> notEqualParams)
	{
		String sql = " select * from " + UndergraStuInlandCommuTable.TABLE_NAME + " where 1=1";
		if (equalParams != null && equalParams.keySet().size() != 0)
		{
			for (Object object : equalParams.keySet())
			{
				String key = object.toString();
				String value = equalParams.get(key).toString();
				sql += String.format(" and %s = '%s'", key, value);
			}
		}
		if (notEqualParams != null && notEqualParams.keySet().size() != 0)
		{
			for (Object object : notEqualParams.keySet())
			{
				String key = object.toString();
				String value = notEqualParams.get(key).toString();
				sql += String.format(" and %s != '%s'", key, value);
			}
		}
		sql += " and " + UndergraStuInlandCommuTable.USIC_DEADLINE + " is null";
		System.out.println(sql);
		Connection connection = null;
		try
		{
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		}
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		try
		{
			System.out.println(sql);
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			List<UndergraStuInlandCommu> undergraStuInlandCommus = new ArrayList<UndergraStuInlandCommu>();

			while (resultSet.next())
			{
				int usic_id = resultSet.getInt(UndergraStuInlandCommuTable.USIC_ID);
				String usic_institute = resultSet.getString(UndergraStuInlandCommuTable.USIC_INSTITUTE);
				int usic_outnumber = resultSet.getInt(UndergraStuInlandCommuTable.USIC_OUTNUMBER);
				int usic_innumber = resultSet.getInt(UndergraStuInlandCommuTable.USIC_INNUMBER);
				int usic_serialnumber = resultSet.getInt(UndergraStuInlandCommuTable.USIC_SERIALNUMBER);
				Date usic_deadline = resultSet.getDate(UndergraStuInlandCommuTable.USIC_DEADLINE);
				String usic_college = resultSet.getString(UndergraStuInlandCommuTable.USIC_COLLEGE);
				String usic_comments = resultSet.getString(UndergraStuInlandCommuTable.USIC_COMMENTS);
				int usic_isnull = resultSet.getInt(UndergraStuInlandCommuTable.USIC_ISNULL);
			 	UndergraStuInlandCommu undergraStuInlandCommu = new UndergraStuInlandCommu(usic_id, usic_institute, usic_outnumber, usic_innumber, usic_serialnumber, usic_deadline, usic_college, usic_comments, usic_isnull);
				undergraStuInlandCommus.add(undergraStuInlandCommu);
			}
			return undergraStuInlandCommus;
		} catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		} finally
		{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
	}
	
	@Override
	public List<UndergraStuInlandCommu> getAll()
	{
		String sql = " select * from " + UndergraStuInlandCommuTable.TABLE_NAME
				+ " where 1=1 " + " order by " + UndergraStuInlandCommuTable.USIC_ID;
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
			List<UndergraStuInlandCommu> usicList = new ArrayList<UndergraStuInlandCommu>();
			while (resultSet.next()) {
				int usic_id = resultSet.getInt(UndergraStuInlandCommuTable.USIC_ID);
				String usic_institute = resultSet.getString(UndergraStuInlandCommuTable.USIC_INSTITUTE);
				Integer usic_outnumber = resultSet.getInt(UndergraStuInlandCommuTable.USIC_OUTNUMBER);
				if(usic_outnumber == -9)
					usic_outnumber = null;
				Integer usic_innumber = resultSet.getInt(UndergraStuInlandCommuTable.USIC_INNUMBER);
				if(usic_innumber == -9)
					usic_innumber = null;
				int usic_serialnumber = resultSet.getInt(UndergraStuInlandCommuTable.USIC_SERIALNUMBER);
				Date usic_deadline = resultSet.getDate(UndergraStuInlandCommuTable.USIC_DEADLINE);
				String usic_college = resultSet.getString(UndergraStuInlandCommuTable.USIC_COLLEGE);
				String usic_comments = resultSet.getString(UndergraStuInlandCommuTable.USIC_COMMENTS);
				int usic_isnull = resultSet.getInt(UndergraStuInlandCommuTable.USIC_ISNULL);
				UndergraStuInlandCommu undergraStuInlandCommu = new UndergraStuInlandCommu(usic_id, usic_institute,
						usic_outnumber, usic_innumber, usic_serialnumber, usic_deadline, usic_college, usic_comments,usic_isnull);
				if(usic_institute.equals("合计"))
				{
					usicList.add(0, undergraStuInlandCommu);
					continue;
				}
				usicList.add(undergraStuInlandCommu);
			}
			return usicList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
	}
	
	@Override
	public boolean deleteByCollegeandDeadline(String college, Date deadline)
			throws SQLException {
		Connection connection = null;
		Statement stmt = null;
		try
		{
			connection = JdbcUtils_DBCP.getConnection();
			stmt = connection.createStatement();
		} catch (SQLException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
		String sql = "delete from " + UndergraStuInlandCommuTable.TABLE_NAME
				+ " where " + UndergraStuInlandCommuTable.USIC_COLLEGE + " = '" + college + "'" +" and usic_deadline is null ";
		System.out.println(sql);
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally{
			JdbcUtils_DBCP.release(connection, stmt, null);
		}
		return true;
	}
}
