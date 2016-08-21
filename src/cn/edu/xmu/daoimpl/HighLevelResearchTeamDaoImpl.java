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

import cn.edu.xmu.dao.HighLevelResearchTeamDao;
import cn.edu.xmu.entity.HighLevelResearchTeam;
import cn.edu.xmu.table.HighLevelResearchTeamTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;

/**
 * 
 * @author xiaoping 表3-4-2 高层次研究团队 (时点) date 2015-7-10
 *
 */
public class HighLevelResearchTeamDaoImpl extends BaseDaoImpl<HighLevelResearchTeam>implements HighLevelResearchTeamDao
{

	@Override
	public List<HighLevelResearchTeam> getHighLevelResearchTeams(int start, int end, String sortStr, String orderStr,
			Map queryParams, Date deadline)
	{
		String sql = " select tmp.* from ( " + " select * from " + HighLevelResearchTeamTable.TABLE_NAME
				+ " where 1=1 ";
		if (deadline != null)
		{
			sql += String.format("and " + HighLevelResearchTeamTable.HLRT_DEADLINE + " like  '%s%%' ", deadline);
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
			List<HighLevelResearchTeam> highLevelResearchTeams = new ArrayList<HighLevelResearchTeam>();

			while (resultSet.next())
			{
				int hlrt_id = resultSet.getInt(HighLevelResearchTeamTable.HLRT_ID);
				String hlrt_researchdirection = resultSet.getString(HighLevelResearchTeamTable.HLRT_RESEARCHDIRECTION);
				String hlrt_head = resultSet.getString(HighLevelResearchTeamTable.HLRT_HEAD);
				String hlrt_headnumber = resultSet.getString(HighLevelResearchTeamTable.HLRT_HEADNUMBER);
				String hlrt_type = resultSet.getString(HighLevelResearchTeamTable.HLRT_TYPE);
				Date hlrt_acquisitiondate = resultSet.getDate(HighLevelResearchTeamTable.HLRT_ACQUISITIONDATE);
				Date temp = Date.valueOf("1800-1-1");
				if(temp.equals(hlrt_acquisitiondate))
					hlrt_acquisitiondate = null;
				int hlrt_serialnumber = resultSet.getInt(HighLevelResearchTeamTable.HLRT_SERIALNUMBER);
				Date hlrt_deadline = resultSet.getDate(HighLevelResearchTeamTable.HLRT_DEADLINE);
				String hlrt_college = resultSet.getString(HighLevelResearchTeamTable.HLRT_COLLEGE);
				String hlrt_comments = resultSet.getString(HighLevelResearchTeamTable.HLRT_COMMENTS);
				int hlrt_isnull = resultSet.getInt(HighLevelResearchTeamTable.HLRT_ISNULL);
				HighLevelResearchTeam highLevelResearchTeam = new HighLevelResearchTeam(hlrt_id, hlrt_researchdirection,
						hlrt_head, hlrt_headnumber, hlrt_type, hlrt_acquisitiondate, hlrt_serialnumber, hlrt_deadline,
						hlrt_college, hlrt_comments, hlrt_isnull);

				highLevelResearchTeams.add(highLevelResearchTeam);
			}
			return highLevelResearchTeams;
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
	public int getHighLevelResearchTeamCount(Map queryParams)
	{
		int count = 0;
		String sql = "select count(*) from " + HighLevelResearchTeamTable.TABLE_NAME + " where 1 = 1";
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
		String sql = "select max(" + HighLevelResearchTeamTable.HLRT_SERIALNUMBER + ") from "
				+ HighLevelResearchTeamTable.TABLE_NAME;

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
	public boolean batchDelete(String[] hlrtids) throws SQLException
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

		for (String hlrtid : hlrtids)
		{
			String sql = "delete from " + HighLevelResearchTeamTable.TABLE_NAME + " where hlrt_id = '" + hlrtid + "'";
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
	public int addHighLevelResearchTeam(HighLevelResearchTeam highLevelResearchTeam)
	{
		int result = 0;

		String t_sql = "update " + HighLevelResearchTeamTable.TABLE_NAME + " set "
				+ HighLevelResearchTeamTable.HLRT_SERIALNUMBER + " = " + HighLevelResearchTeamTable.HLRT_SERIALNUMBER
				+ " +1 where " + HighLevelResearchTeamTable.HLRT_SERIALNUMBER + ">=?";
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
			t_pstmt.setInt(1, highLevelResearchTeam.getHlrt_serialnumber());

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
		String sql = "insert into " + HighLevelResearchTeamTable.TABLE_NAME + "("
				+ HighLevelResearchTeamTable.HLRT_RESEARCHDIRECTION + "," + HighLevelResearchTeamTable.HLRT_HEAD + ","
				+ HighLevelResearchTeamTable.HLRT_HEADNUMBER + "," + HighLevelResearchTeamTable.HLRT_TYPE + ","
				+ HighLevelResearchTeamTable.HLRT_ACQUISITIONDATE + "," + HighLevelResearchTeamTable.HLRT_SERIALNUMBER
				+ "," + HighLevelResearchTeamTable.HLRT_DEADLINE + "," + HighLevelResearchTeamTable.HLRT_COLLEGE + ","
				+ HighLevelResearchTeamTable.HLRT_COMMENTS+ "," + HighLevelResearchTeamTable.HLRT_ISNULL + ")values(?,?,?,?,?,?,?,?,?,?)";

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
			pstmt.setString(1, highLevelResearchTeam.getHlrt_researchdirection());
			pstmt.setString(2, highLevelResearchTeam.getHlrt_head());
			pstmt.setString(3, highLevelResearchTeam.getHlrt_headnumber());
			pstmt.setString(4, highLevelResearchTeam.getHlrt_type());
			pstmt.setDate(5, highLevelResearchTeam.getHlrt_acquisitiondate());
			pstmt.setInt(6, highLevelResearchTeam.getHlrt_serialnumber());
			pstmt.setDate(7, highLevelResearchTeam.getHlrt_deadline());
			pstmt.setString(8, highLevelResearchTeam.getHlrt_college());
			pstmt.setString(9, highLevelResearchTeam.getHlrt_comments());
			pstmt.setInt(10, highLevelResearchTeam.getHlrt_isnull());
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
	public int alterHighLevelResearchTeam(Map<String, String> valueMap, String id)
	{
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(HighLevelResearchTeamTable.HLRT_ID, id);
		int result = updateRecord(HighLevelResearchTeamTable.TABLE_NAME, valueMap, condition);
		return result;
	}

	@Override
	public List<HighLevelResearchTeam> getAll()
	{
		String sql = " select * from " + HighLevelResearchTeamTable.TABLE_NAME + " where 1=1 " + " order by "
				+ HighLevelResearchTeamTable.HLRT_ID;
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
			List<HighLevelResearchTeam> hlrtList = new ArrayList<HighLevelResearchTeam>();
			while (resultSet.next())
			{
				int hlrt_id = resultSet.getInt(HighLevelResearchTeamTable.HLRT_ID);
				String hlrt_researchdirection = resultSet.getString(HighLevelResearchTeamTable.HLRT_RESEARCHDIRECTION);
				String hlrt_head = resultSet.getString(HighLevelResearchTeamTable.HLRT_HEAD);
				String hlrt_headnumber = resultSet.getString(HighLevelResearchTeamTable.HLRT_HEADNUMBER);
				String hlrt_type = resultSet.getString(HighLevelResearchTeamTable.HLRT_TYPE);
				Date hlrt_acquisitiondate = resultSet.getDate(HighLevelResearchTeamTable.HLRT_ACQUISITIONDATE);
				Date temp = Date.valueOf("1800-1-1");
				if(temp.equals(hlrt_acquisitiondate))
					hlrt_acquisitiondate = null;
				int hlrt_serialnumber = resultSet.getInt(HighLevelResearchTeamTable.HLRT_SERIALNUMBER);
				Date hlrt_deadline = resultSet.getDate(HighLevelResearchTeamTable.HLRT_DEADLINE);
				String hlrt_college = resultSet.getString(HighLevelResearchTeamTable.HLRT_COLLEGE);
				String hlrt_comments = resultSet.getString(HighLevelResearchTeamTable.HLRT_COMMENTS);
				int hlrt_isnull = resultSet.getInt(HighLevelResearchTeamTable.HLRT_ISNULL);
				HighLevelResearchTeam highLevelResearchTeam = new HighLevelResearchTeam(hlrt_id, hlrt_researchdirection,
						hlrt_head, hlrt_headnumber, hlrt_type, hlrt_acquisitiondate, hlrt_serialnumber, hlrt_deadline,
						hlrt_college, hlrt_comments, hlrt_isnull);

				hlrtList.add(highLevelResearchTeam);
			}
			return hlrtList;
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
		String sql = "delete from " + HighLevelResearchTeamTable.TABLE_NAME + " where "
				+ HighLevelResearchTeamTable.HLRT_COLLEGE + " = '" + college + "'" + " and hlrt_deadline is null ";
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

}
