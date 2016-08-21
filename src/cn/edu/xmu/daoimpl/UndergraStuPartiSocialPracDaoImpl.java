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

import cn.edu.xmu.dao.UndergraStuPartiSocialPracDao;
import cn.edu.xmu.entity.UndergraStuPartiSocialPrac;
import cn.edu.xmu.table.UndergraStuPartiSocialPracTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;

/**
 * 
 * @author xiaoping 附表5-4-3 本科生参与暑期社会实践情况 date 2015-7-10
 *
 */
public class UndergraStuPartiSocialPracDaoImpl extends BaseDaoImpl<UndergraStuPartiSocialPrac>
		implements UndergraStuPartiSocialPracDao
{

	@Override
	public List<UndergraStuPartiSocialPrac> getUndergraStuPartiSocialPracs(int start, int end, String sortStr,
			String orderStr, Map queryParams, Date deadline)
	{
		String sql = " select tmp.* from ( " + " select * from " + UndergraStuPartiSocialPracTable.TABLE_NAME
				+ " where 1=1 ";
		if (deadline != null)
		{
			sql += String.format("and " + UndergraStuPartiSocialPracTable.USPSP_DEADLINE + " like  '%s%%' ", deadline);
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
			List<UndergraStuPartiSocialPrac> undergraStuPartiSocialPracs = new ArrayList<UndergraStuPartiSocialPrac>();

			while (resultSet.next())
			{
				int uspsp_id = resultSet.getInt(UndergraStuPartiSocialPracTable.USPSP_ID);
				String uspsp_department = resultSet.getString(UndergraStuPartiSocialPracTable.USPSP_DEPARTMENT);
				Integer uspsp_focuspracnum = resultSet.getInt(UndergraStuPartiSocialPracTable.USPSP_FOCUSPRACNUM);
				if (uspsp_focuspracnum == -9)
					uspsp_focuspracnum = null;
				Integer uspsp_scatterpracnum = resultSet.getInt(UndergraStuPartiSocialPracTable.USPSP_SCATTERPRACNUM);
				if (uspsp_scatterpracnum == -9)
					uspsp_scatterpracnum = null;
				Integer uspsp_subtotal = resultSet.getInt(UndergraStuPartiSocialPracTable.USPSP_SUBTOTAL);
				if (uspsp_subtotal < 0)
					uspsp_subtotal = null;
				int uspsp_serialnumber = resultSet.getInt(UndergraStuPartiSocialPracTable.USPSP_SERIALNUMBER);
				Date uspsp_deadline = resultSet.getDate(UndergraStuPartiSocialPracTable.USPSP_DEADLINE);
				String uspsp_college = resultSet.getString(UndergraStuPartiSocialPracTable.USPSP_COLLEGE);
				String uspsp_comments = resultSet.getString(UndergraStuPartiSocialPracTable.USPSP_COMMENTS);
				int uspsp_isnull = resultSet.getInt(UndergraStuPartiSocialPracTable.USPSP_ISNULL);
				UndergraStuPartiSocialPrac undergraStuPartiSocialPrac = new UndergraStuPartiSocialPrac(uspsp_id,
						uspsp_department, uspsp_focuspracnum, uspsp_scatterpracnum, uspsp_subtotal, uspsp_serialnumber,
						uspsp_deadline, uspsp_college, uspsp_comments, uspsp_isnull);
				if (uspsp_department.equals("合计"))
				{
					undergraStuPartiSocialPracs.add(0, undergraStuPartiSocialPrac);
					continue;
				}
				undergraStuPartiSocialPracs.add(undergraStuPartiSocialPrac);
			}
			return undergraStuPartiSocialPracs;
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
	public int getUndergraStuPartiSocialPracCount(Map queryParams)
	{
		int count = 0;
		String sql = "select count(*) from " + UndergraStuPartiSocialPracTable.TABLE_NAME + " where 1 = 1";
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
		String sql = "select max(" + UndergraStuPartiSocialPracTable.USPSP_SERIALNUMBER + ") from "
				+ UndergraStuPartiSocialPracTable.TABLE_NAME;

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
	public boolean batchDelete(String[] uspspids) throws SQLException
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

		for (String uspspid : uspspids)
		{
			String sql = "delete from " + UndergraStuPartiSocialPracTable.TABLE_NAME + " where uspsp_id = '" + uspspid
					+ "'";
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
	public int addUndergraStuPartiSocialPrac(UndergraStuPartiSocialPrac undergraStuPartiSocialPrac)
	{
		int result = 0;

		String t_sql = "update " + UndergraStuPartiSocialPracTable.TABLE_NAME + " set "
				+ UndergraStuPartiSocialPracTable.USPSP_SERIALNUMBER + " = "
				+ UndergraStuPartiSocialPracTable.USPSP_SERIALNUMBER + " +1 where "
				+ UndergraStuPartiSocialPracTable.USPSP_SERIALNUMBER + ">=?";
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
			t_pstmt.setInt(1, undergraStuPartiSocialPrac.getUspsp_serialnumber());

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
		String sql = "insert into " + UndergraStuPartiSocialPracTable.TABLE_NAME + "("
				+ UndergraStuPartiSocialPracTable.USPSP_DEPARTMENT + ","
				+ UndergraStuPartiSocialPracTable.USPSP_FOCUSPRACNUM + ","
				+ UndergraStuPartiSocialPracTable.USPSP_SCATTERPRACNUM + ","
				+ UndergraStuPartiSocialPracTable.USPSP_SUBTOTAL + ","
				+ UndergraStuPartiSocialPracTable.USPSP_SERIALNUMBER + ","
				+ UndergraStuPartiSocialPracTable.USPSP_DEADLINE + "," + UndergraStuPartiSocialPracTable.USPSP_COLLEGE
				+ "," + UndergraStuPartiSocialPracTable.USPSP_COMMENTS + ","
				+ UndergraStuPartiSocialPracTable.USPSP_ISNULL + ")values(?,?,?,?,?,?,?,?,?)";

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
			pstmt.setString(1, undergraStuPartiSocialPrac.getUspsp_department());
			pstmt.setInt(2, undergraStuPartiSocialPrac.getUspsp_focuspracnum());
			pstmt.setInt(3, undergraStuPartiSocialPrac.getUspsp_scatterpracnum());
			pstmt.setInt(4, undergraStuPartiSocialPrac.getUspsp_subtotal());
			pstmt.setInt(5, undergraStuPartiSocialPrac.getUspsp_serialnumber());
			pstmt.setDate(6, undergraStuPartiSocialPrac.getUspsp_deadline());
			pstmt.setString(7, undergraStuPartiSocialPrac.getUspsp_college());
			pstmt.setString(8, undergraStuPartiSocialPrac.getUspsp_comments());
			pstmt.setInt(9, undergraStuPartiSocialPrac.getUspsp_isnull());
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
	public int alterUndergraStuPartiSocialPrac(Map<String, String> valueMap, String id)
	{
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(UndergraStuPartiSocialPracTable.USPSP_ID, id);
		int result = updateRecord(UndergraStuPartiSocialPracTable.TABLE_NAME, valueMap, condition);
		return result;
	}

	@Override
	public List<UndergraStuPartiSocialPrac> getAll()
	{
		String sql = " select * from " + UndergraStuPartiSocialPracTable.TABLE_NAME + " where 1=1 " + " order by "
				+ UndergraStuPartiSocialPracTable.USPSP_ID;
		Connection connection = null;
		try
		{
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1)
		{
			e1.printStackTrace();
		}
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try
		{
			System.out.println(sql);
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			List<UndergraStuPartiSocialPrac> uspspList = new ArrayList<UndergraStuPartiSocialPrac>();
			while (resultSet.next())
			{
				int uspsp_id = resultSet.getInt(UndergraStuPartiSocialPracTable.USPSP_ID);
				String uspsp_department = resultSet.getString(UndergraStuPartiSocialPracTable.USPSP_DEPARTMENT);
				Integer uspsp_focuspracnum = resultSet.getInt(UndergraStuPartiSocialPracTable.USPSP_FOCUSPRACNUM);
				if (uspsp_focuspracnum == -9)
					uspsp_focuspracnum = null;
				Integer uspsp_scatterpracnum = resultSet.getInt(UndergraStuPartiSocialPracTable.USPSP_SCATTERPRACNUM);
				if (uspsp_scatterpracnum == -9)
					uspsp_scatterpracnum = null;
				Integer uspsp_subtotal = resultSet.getInt(UndergraStuPartiSocialPracTable.USPSP_SUBTOTAL);
				if (uspsp_subtotal < 0)
					uspsp_subtotal = null;
				int uspsp_serialnumber = resultSet.getInt(UndergraStuPartiSocialPracTable.USPSP_SERIALNUMBER);
				Date uspsp_deadline = resultSet.getDate(UndergraStuPartiSocialPracTable.USPSP_DEADLINE);
				String uspsp_college = resultSet.getString(UndergraStuPartiSocialPracTable.USPSP_COLLEGE);
				String uspsp_comments = resultSet.getString(UndergraStuPartiSocialPracTable.USPSP_COMMENTS);
				int uspsp_isnull = resultSet.getInt(UndergraStuPartiSocialPracTable.USPSP_ISNULL);
				UndergraStuPartiSocialPrac undergraStuPartiSocialPrac = new UndergraStuPartiSocialPrac(uspsp_id,
						uspsp_department, uspsp_focuspracnum, uspsp_scatterpracnum, uspsp_subtotal, uspsp_serialnumber,
						uspsp_deadline, uspsp_college, uspsp_comments, uspsp_isnull);

				if (uspsp_department.equals("合计"))
				{
					uspspList.add(0, undergraStuPartiSocialPrac);
					continue;
				}
				uspspList.add(undergraStuPartiSocialPrac);
			}
			return uspspList;
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
	public boolean deleteByCollegeandDeadline(String college, Date deadline) throws SQLException
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
		String sql = "delete from " + UndergraStuPartiSocialPracTable.TABLE_NAME + " where "
				+ UndergraStuPartiSocialPracTable.USPSP_COLLEGE + " = '" + college + "'"
				+ " and uspsp_deadline is null ";
		System.out.println(sql);
		try
		{
			stmt.executeUpdate(sql);
		} catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		} finally
		{
			JdbcUtils_DBCP.release(connection, stmt, null);
		}
		return true;
	}

	@Override
	public List<UndergraStuPartiSocialPrac> getEqualUndergraStuPartiSocialPrac(Map<String, String> equalParams,
			Map<String, String> notEqualParams)
	{
		String sql = " select * from " + UndergraStuPartiSocialPracTable.TABLE_NAME + " where 1=1";
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
		sql += " and " + UndergraStuPartiSocialPracTable.USPSP_DEADLINE + " is null";
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
			List<UndergraStuPartiSocialPrac> undergraStuPartiSocialPracs = new ArrayList<UndergraStuPartiSocialPrac>();

			while (resultSet.next())
			{
				int uspsp_id = resultSet.getInt(UndergraStuPartiSocialPracTable.USPSP_ID);
				String uspsp_department = resultSet.getString(UndergraStuPartiSocialPracTable.USPSP_DEPARTMENT);
				int uspsp_focuspracnum = resultSet.getInt(UndergraStuPartiSocialPracTable.USPSP_FOCUSPRACNUM);
				int uspsp_scatterpracnum = resultSet.getInt(UndergraStuPartiSocialPracTable.USPSP_SCATTERPRACNUM);
				int uspsp_subtotal = resultSet.getInt(UndergraStuPartiSocialPracTable.USPSP_SUBTOTAL);
				int uspsp_serialnumber = resultSet.getInt(UndergraStuPartiSocialPracTable.USPSP_SERIALNUMBER);
				Date uspsp_deadline = resultSet.getDate(UndergraStuPartiSocialPracTable.USPSP_DEADLINE);
				String uspsp_college = resultSet.getString(UndergraStuPartiSocialPracTable.USPSP_COLLEGE);
				String uspsp_comments = resultSet.getString(UndergraStuPartiSocialPracTable.USPSP_COMMENTS);
				int uspsp_isnull = resultSet.getInt(UndergraStuPartiSocialPracTable.USPSP_ISNULL);
				UndergraStuPartiSocialPrac undergraStuPartiSocialPrac = new UndergraStuPartiSocialPrac(uspsp_id,
						uspsp_department, uspsp_focuspracnum, uspsp_scatterpracnum, uspsp_subtotal, uspsp_serialnumber,
						uspsp_deadline, uspsp_college, uspsp_comments, uspsp_isnull);
				undergraStuPartiSocialPracs.add(undergraStuPartiSocialPrac);
			}
			return undergraStuPartiSocialPracs;
		} catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		} finally
		{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
	}
}
