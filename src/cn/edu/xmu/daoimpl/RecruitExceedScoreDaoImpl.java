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

import cn.edu.xmu.dao.RecruitExceedScoreDao;
import cn.edu.xmu.entity.RecruitExceedScore;
import cn.edu.xmu.table.RecruitExceedScoreTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;

/**
 * 
 * @author xiaoping 附表6-1-5-1厦门大学在全国各省（市、自治区）招生出档分数情况（时点） date 2015-7-11
 *
 */
public class RecruitExceedScoreDaoImpl extends BaseDaoImpl<RecruitExceedScore>implements RecruitExceedScoreDao
{

	@Override
	public List<RecruitExceedScore> getRecruitExceedScores(int start, int end, String sortStr, String orderStr,
			Map queryParams, Date deadline)
	{
		String sql = " select tmp.* from ( " + " select * from " + RecruitExceedScoreTable.TABLE_NAME + " where 1=1 ";
		if (deadline != null)
		{
			sql += String.format("and " + RecruitExceedScoreTable.RES_DEADLINE + " like  '%s%%' ", deadline);
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
			List<RecruitExceedScore> recruitExceedScores = new ArrayList<RecruitExceedScore>();

			while (resultSet.next())
			{
				int res_id = resultSet.getInt(RecruitExceedScoreTable.RES_ID);
				String res_year = resultSet.getString(RecruitExceedScoreTable.RES_YEAR);
				Float res_libexctwentyproportion = resultSet
						.getFloat(RecruitExceedScoreTable.RES_LIBEXCTWENTYPROPORTION);
				if (res_libexctwentyproportion == -9)
					res_libexctwentyproportion = null;
				Float res_libexcthirtyproportion = resultSet
						.getFloat(RecruitExceedScoreTable.RES_LIBEXCTHIRTYPROPORTION);
				if (res_libexcthirtyproportion == -9)
					res_libexcthirtyproportion = null;
				Float res_libexclineave = resultSet.getFloat(RecruitExceedScoreTable.RES_LIBEXCLINEAVE);
				if (res_libexclineave == -9)
					res_libexclineave = null;
				Float res_sciexcthirtyproportion = resultSet
						.getFloat(RecruitExceedScoreTable.RES_SCIEXCTHIRTYPROPORTION);
				if (res_sciexcthirtyproportion == -9)
					res_sciexcthirtyproportion = null;
				Float res_sciexcfortyproportion = resultSet.getFloat(RecruitExceedScoreTable.RES_SCIEXCFORTYPROPORTION);
				if (res_sciexcfortyproportion == -9)
					res_sciexcfortyproportion = null;
				Float res_sciexclineave = resultSet.getFloat(RecruitExceedScoreTable.RES_SCIEXCLINEAVE);
				if (res_sciexclineave == -9)
					res_sciexclineave = null;
				int res_serialnumber = resultSet.getInt(RecruitExceedScoreTable.RES_SERIALNUMBER);
				Date res_deadline = resultSet.getDate(RecruitExceedScoreTable.RES_DEADLINE);
				String res_college = resultSet.getString(RecruitExceedScoreTable.RES_COLLEGE);
				String res_comments = resultSet.getString(RecruitExceedScoreTable.RES_COMMENTS);
				int res_isnull = resultSet.getInt(RecruitExceedScoreTable.RES_ISNULL);
				RecruitExceedScore recruitExceedScore = new RecruitExceedScore(res_id, res_year,
						res_libexctwentyproportion, res_libexcthirtyproportion, res_libexclineave,
						res_sciexcthirtyproportion, res_sciexcfortyproportion, res_sciexclineave, res_serialnumber,
						res_deadline, res_college, res_comments, res_isnull);
				recruitExceedScores.add(recruitExceedScore);
			}
			return recruitExceedScores;
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
	public int getRecruitExceedScoreCount(Map queryParams)
	{
		int count = 0;
		String sql = "select count(*) from " + RecruitExceedScoreTable.TABLE_NAME + " where 1 = 1";
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
		String sql = "select max(" + RecruitExceedScoreTable.RES_SERIALNUMBER + ") from "
				+ RecruitExceedScoreTable.TABLE_NAME;

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
	public boolean batchDelete(String[] resids) throws SQLException
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

		for (String resid : resids)
		{
			String sql = "delete from " + RecruitExceedScoreTable.TABLE_NAME + " where res_id = '" + resid + "'";
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
	public int addRecruitExceedScore(RecruitExceedScore recruitExceedScore)
	{
		int result = 0;

		String t_sql = "update " + RecruitExceedScoreTable.TABLE_NAME + " set "
				+ RecruitExceedScoreTable.RES_SERIALNUMBER + " = " + RecruitExceedScoreTable.RES_SERIALNUMBER
				+ " +1 where " + RecruitExceedScoreTable.RES_SERIALNUMBER + ">=?";
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
			t_pstmt.setInt(1, recruitExceedScore.getRes_serialnumber());

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
		String sql = "insert into " + RecruitExceedScoreTable.TABLE_NAME + "(" + RecruitExceedScoreTable.RES_YEAR + ","
				+ RecruitExceedScoreTable.RES_LIBEXCTWENTYPROPORTION + ","
				+ RecruitExceedScoreTable.RES_LIBEXCTHIRTYPROPORTION + "," + RecruitExceedScoreTable.RES_LIBEXCLINEAVE
				+ "," + RecruitExceedScoreTable.RES_SCIEXCTHIRTYPROPORTION + ","
				+ RecruitExceedScoreTable.RES_SCIEXCFORTYPROPORTION + "," + RecruitExceedScoreTable.RES_SCIEXCLINEAVE
				+ "," + RecruitExceedScoreTable.RES_SERIALNUMBER + "," + RecruitExceedScoreTable.RES_DEADLINE + ","
				+ RecruitExceedScoreTable.RES_COLLEGE + "," + RecruitExceedScoreTable.RES_COMMENTS+ "," + RecruitExceedScoreTable.RES_ISNULL
				+ ")values(?,?,?,?,?,?,?,?,?,?,?,?)";

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
			pstmt.setString(1, recruitExceedScore.getRes_year());
			pstmt.setFloat(2, recruitExceedScore.getRes_libexctwentyproportion());
			pstmt.setFloat(3, recruitExceedScore.getRes_libexcthirtyproportion());
			pstmt.setFloat(4, recruitExceedScore.getRes_libexclineave());
			pstmt.setFloat(5, recruitExceedScore.getRes_sciexcthirtyproportion());
			pstmt.setFloat(6, recruitExceedScore.getRes_sciexcfortyproportion());
			pstmt.setFloat(7, recruitExceedScore.getRes_sciexclineave());
			pstmt.setInt(8, recruitExceedScore.getRes_serialnumber());
			pstmt.setDate(9, recruitExceedScore.getRes_deadline());
			pstmt.setString(10, recruitExceedScore.getRes_college());
			pstmt.setString(11, recruitExceedScore.getRes_comments());
			pstmt.setInt(12, recruitExceedScore.getRes_isnull());
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
	public int alterRecruitExceedScore(Map<String, String> valueMap, String id)
	{
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(RecruitExceedScoreTable.RES_ID, id);
		int result = updateRecord(RecruitExceedScoreTable.TABLE_NAME, valueMap, condition);
		return result;
	}

	@Override
	public List<RecruitExceedScore> getAll()
	{
		String sql = " select * from " + RecruitExceedScoreTable.TABLE_NAME + " where 1=1 " + " order by "
				+ RecruitExceedScoreTable.RES_ID;
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
			List<RecruitExceedScore> resList = new ArrayList<RecruitExceedScore>();
			while (resultSet.next())
			{
				int res_id = resultSet.getInt(RecruitExceedScoreTable.RES_ID);
				String res_year = resultSet.getString(RecruitExceedScoreTable.RES_YEAR);
				Float res_libexctwentyproportion = resultSet
						.getFloat(RecruitExceedScoreTable.RES_LIBEXCTWENTYPROPORTION);
				if (res_libexctwentyproportion == -9)
					res_libexctwentyproportion = null;
				Float res_libexcthirtyproportion = resultSet
						.getFloat(RecruitExceedScoreTable.RES_LIBEXCTHIRTYPROPORTION);
				if (res_libexcthirtyproportion == -9)
					res_libexcthirtyproportion = null;
				Float res_libexclineave = resultSet.getFloat(RecruitExceedScoreTable.RES_LIBEXCLINEAVE);
				if (res_libexclineave == -9)
					res_libexclineave = null;
				Float res_sciexcthirtyproportion = resultSet
						.getFloat(RecruitExceedScoreTable.RES_SCIEXCTHIRTYPROPORTION);
				if (res_sciexcthirtyproportion == -9)
					res_sciexcthirtyproportion = null;
				Float res_sciexcfortyproportion = resultSet.getFloat(RecruitExceedScoreTable.RES_SCIEXCFORTYPROPORTION);
				if (res_sciexcfortyproportion == -9)
					res_sciexcfortyproportion = null;
				Float res_sciexclineave = resultSet.getFloat(RecruitExceedScoreTable.RES_SCIEXCLINEAVE);
				if (res_sciexclineave == -9)
					res_sciexclineave = null;
				int res_serialnumber = resultSet.getInt(RecruitExceedScoreTable.RES_SERIALNUMBER);
				Date res_deadline = resultSet.getDate(RecruitExceedScoreTable.RES_DEADLINE);
				String res_college = resultSet.getString(RecruitExceedScoreTable.RES_COLLEGE);
				String res_comments = resultSet.getString(RecruitExceedScoreTable.RES_COMMENTS);
				int res_isnull = resultSet.getInt(RecruitExceedScoreTable.RES_ISNULL);
				RecruitExceedScore recruitExceedScore = new RecruitExceedScore(res_id, res_year,
						res_libexctwentyproportion, res_libexcthirtyproportion, res_libexclineave,
						res_sciexcthirtyproportion, res_sciexcfortyproportion, res_sciexclineave, res_serialnumber,
						res_deadline, res_college, res_comments, res_isnull);

				resList.add(recruitExceedScore);
			}
			return resList;
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
		String sql = "delete from " + RecruitExceedScoreTable.TABLE_NAME + " where "
				+ RecruitExceedScoreTable.RES_COLLEGE + " = '" + college + "'" + " and res_deadline is null ";
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
