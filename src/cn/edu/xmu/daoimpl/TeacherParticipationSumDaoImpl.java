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

import cn.edu.xmu.dao.TeacherParticipationSumDao;
import cn.edu.xmu.entity.TeacherParticipationSum;
import cn.edu.xmu.table.TeacherParticipationSumTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;

/**
 * 
 * @author xiaoping 数据报表 附表3-5-1-3 教师参加院级及以上教学竞赛情况汇总表（自然年） date 2015-7-8
 *
 */
public class TeacherParticipationSumDaoImpl extends BaseDaoImpl<TeacherParticipationSum>
		implements TeacherParticipationSumDao
{

	@Override
	public List<TeacherParticipationSum> getTeacherParticipationSums(int start, int end, String sortStr,
			String orderStr, Map queryParams, Date deadline)
	{
		String sql = " select tmp.* from ( " + " select * from " + TeacherParticipationSumTable.TABLE_NAME
				+ " where 1=1 ";
		if (deadline != null)
		{
			sql += String.format("and " + TeacherParticipationSumTable.TPS_DEADLINE + " like  '%s%%' ", deadline);
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
			List<TeacherParticipationSum> teacherParticipationSums = new ArrayList<TeacherParticipationSum>();

			while (resultSet.next())
			{
				int tps_id = resultSet.getInt(TeacherParticipationSumTable.TPS_ID);
				String tps_particollege = resultSet.getString(TeacherParticipationSumTable.TPS_PARTICOLLEGE);
				Integer tps_schskillcompecourtyardnum = resultSet
						.getInt(TeacherParticipationSumTable.TPS_SCHSKILLCOMPECOURTYARDNUM);
				if(tps_schskillcompecourtyardnum == -9)
					tps_schskillcompecourtyardnum = null;
				Integer tps_schskillcompeschoolnum = resultSet
						.getInt(TeacherParticipationSumTable.TPS_SCHSKILLCOMPESCHOOLNUM);
				if(tps_schskillcompeschoolnum == -9)
					tps_schskillcompeschoolnum = null;
				Integer tps_schskillcompespecialnum = resultSet
						.getInt(TeacherParticipationSumTable.TPS_SCHSKILLCOMPESPECIALNUM);
				if(tps_schskillcompespecialnum == -9)
					tps_schskillcompespecialnum = null;
				Integer tps_schskillcompefirstnum = resultSet
						.getInt(TeacherParticipationSumTable.TPS_SCHSKILLCOMPEFIRSTNUM);
				if(tps_schskillcompefirstnum == -9)
					tps_schskillcompefirstnum = null;
				Integer tps_schskillcompesecnum = resultSet.getInt(TeacherParticipationSumTable.TPS_SCHSKILLCOMPESECNUM);
				if(tps_schskillcompesecnum == -9)
					tps_schskillcompesecnum = null;
				Integer tps_provinskillcompepartinum = resultSet
						.getInt(TeacherParticipationSumTable.TPS_PROVINSKILLCOMPEPARTINUM);
				if(tps_provinskillcompepartinum == -9)
					tps_provinskillcompepartinum = null;
				Integer tps_provinskillcompespecialnum = resultSet
						.getInt(TeacherParticipationSumTable.TPS_PROVINSKILLCOMPESPECIALNUM);
				if(tps_provinskillcompespecialnum == -9)
					tps_provinskillcompespecialnum = null;
				Integer tps_provinskillcompefirstnum = resultSet
						.getInt(TeacherParticipationSumTable.TPS_PROVINSKILLCOMPEFIRSTNUM);
				if(tps_provinskillcompefirstnum == -9)
					tps_provinskillcompefirstnum = null;
				Integer tps_provinskillcompesecnum = resultSet
						.getInt(TeacherParticipationSumTable.TPS_PROVINSKILLCOMPESECNUM);
				if(tps_provinskillcompesecnum == -9)
					tps_provinskillcompesecnum = null;
				Integer tps_countryskillcompepartinum = resultSet
						.getInt(TeacherParticipationSumTable.TPS_COUNTRYSKILLCOMPEPARTINUM);
				if(tps_countryskillcompepartinum == -9)
					tps_countryskillcompepartinum = null;
				Integer tps_countryskillcompespecialnum = resultSet
						.getInt(TeacherParticipationSumTable.TPS_COUNTRYSKILLCOMPESPECIALNUM);
				if(tps_countryskillcompespecialnum == -9)
					tps_countryskillcompespecialnum = null;
				Integer tps_countryskillcompefirstnum = resultSet
						.getInt(TeacherParticipationSumTable.TPS_COUNTRYSKILLCOMPEFIRSTNUM);
				if(tps_countryskillcompefirstnum == -9)
					tps_countryskillcompefirstnum = null;
				Integer tps_countryskillcompesecnum = resultSet
						.getInt(TeacherParticipationSumTable.TPS_COUNTRYSKILLCOMPESECNUM);
				if(tps_countryskillcompesecnum == -9)
					tps_countryskillcompesecnum = null;
				Integer tps_countrymicrocompepartinum = resultSet
						.getInt(TeacherParticipationSumTable.TPS_COUNTRYMICROCOMPEPARTINUM);
				if(tps_countrymicrocompepartinum == -9)
					tps_countrymicrocompepartinum = null;
				Integer tps_countrymicrocompespecialnum = resultSet
						.getInt(TeacherParticipationSumTable.TPS_COUNTRYMICROCOMPESPECIALNUM);
				if(tps_countrymicrocompespecialnum == -9)
					tps_countrymicrocompespecialnum = null;
				Integer tps_countrymicrocompefirstnum = resultSet
						.getInt(TeacherParticipationSumTable.TPS_COUNTRYMICROCOMPEFIRSTNUM);
				if(tps_countrymicrocompefirstnum == -9)
					tps_countrymicrocompefirstnum = null;
				Integer tps_countrymicrocompesecnum = resultSet
						.getInt(TeacherParticipationSumTable.TPS_COUNTRYMICROCOMPESECNUM);
				if(tps_countrymicrocompesecnum == -9)
					tps_countrymicrocompesecnum = null;
				String tps_preparer = resultSet.getString(TeacherParticipationSumTable.TPS_PREPARER);
				int tps_serialnumber = resultSet.getInt(TeacherParticipationSumTable.TPS_SERIALNUMBER);
				Date tps_deadline = resultSet.getDate(TeacherParticipationSumTable.TPS_DEADLINE);
				String tps_college = resultSet.getString(TeacherParticipationSumTable.TPS_COLLEGE);
				String tps_comments = resultSet.getString(TeacherParticipationSumTable.TPS_COMMENTS);
				int tps_isnull = resultSet.getInt(TeacherParticipationSumTable.TPS_ISNULL);
				TeacherParticipationSum teacherParticipationSum = new TeacherParticipationSum(tps_id, tps_particollege,
						tps_schskillcompecourtyardnum, tps_schskillcompeschoolnum, tps_schskillcompespecialnum,
						tps_schskillcompefirstnum, tps_schskillcompesecnum, tps_provinskillcompepartinum,
						tps_provinskillcompespecialnum, tps_provinskillcompefirstnum, tps_provinskillcompesecnum,
						tps_countryskillcompepartinum, tps_countryskillcompespecialnum, tps_countryskillcompefirstnum,
						tps_countryskillcompesecnum, tps_countrymicrocompepartinum, tps_countrymicrocompespecialnum,
						tps_countrymicrocompefirstnum, tps_countrymicrocompesecnum, tps_preparer, tps_serialnumber,
						tps_deadline, tps_college, tps_comments, tps_isnull);
				teacherParticipationSums.add(teacherParticipationSum);
			}
			return teacherParticipationSums;
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
	public int getTeacherParticipationSumCount(Map queryParams)
	{
		int count = 0;
		String sql = "select count(*) from " + TeacherParticipationSumTable.TABLE_NAME + " where 1 = 1";
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
		String sql = "select max(" + TeacherParticipationSumTable.TPS_SERIALNUMBER + ") from "
				+ TeacherParticipationSumTable.TABLE_NAME;

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
	public boolean batchDelete(String[] tpsids) throws SQLException
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

		for (String tpsid : tpsids)
		{
			String sql = "delete from " + TeacherParticipationSumTable.TABLE_NAME + " where tps_id = '" + tpsid + "'";
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
	public int addTeacherParticipationSum(TeacherParticipationSum teacherParticipationSum)
	{
		int result = 0;

		String t_sql = "update " + TeacherParticipationSumTable.TABLE_NAME + " set "
				+ TeacherParticipationSumTable.TPS_SERIALNUMBER + " = " + TeacherParticipationSumTable.TPS_SERIALNUMBER
				+ " +1 where " + TeacherParticipationSumTable.TPS_SERIALNUMBER + ">=?";
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
			t_pstmt.setInt(1, teacherParticipationSum.getTps_serialnumber());

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
		String sql = "insert into " + TeacherParticipationSumTable.TABLE_NAME + "("
				+ TeacherParticipationSumTable.TPS_PARTICOLLEGE + ","
				+ TeacherParticipationSumTable.TPS_SCHSKILLCOMPECOURTYARDNUM + ","
				+ TeacherParticipationSumTable.TPS_SCHSKILLCOMPESCHOOLNUM + ","
				+ TeacherParticipationSumTable.TPS_SCHSKILLCOMPESPECIALNUM + ","
				+ TeacherParticipationSumTable.TPS_SCHSKILLCOMPEFIRSTNUM + ","
				+ TeacherParticipationSumTable.TPS_SCHSKILLCOMPESECNUM + ","
				+ TeacherParticipationSumTable.TPS_PROVINSKILLCOMPEPARTINUM + ","
				+ TeacherParticipationSumTable.TPS_PROVINSKILLCOMPESPECIALNUM + ","
				+ TeacherParticipationSumTable.TPS_PROVINSKILLCOMPEFIRSTNUM + ","
				+ TeacherParticipationSumTable.TPS_PROVINSKILLCOMPESECNUM + ","
				+ TeacherParticipationSumTable.TPS_COUNTRYSKILLCOMPEPARTINUM + ","
				+ TeacherParticipationSumTable.TPS_COUNTRYSKILLCOMPESPECIALNUM + ","
				+ TeacherParticipationSumTable.TPS_COUNTRYSKILLCOMPEFIRSTNUM + ","
				+ TeacherParticipationSumTable.TPS_COUNTRYSKILLCOMPESECNUM + ","
				+ TeacherParticipationSumTable.TPS_COUNTRYMICROCOMPEPARTINUM + ","
				+ TeacherParticipationSumTable.TPS_COUNTRYMICROCOMPESPECIALNUM + ","
				+ TeacherParticipationSumTable.TPS_COUNTRYMICROCOMPEFIRSTNUM + ","
				+ TeacherParticipationSumTable.TPS_COUNTRYMICROCOMPESECNUM + ","
				+ TeacherParticipationSumTable.TPS_PREPARER + "," + TeacherParticipationSumTable.TPS_SERIALNUMBER + ","
				+ TeacherParticipationSumTable.TPS_DEADLINE + "," + TeacherParticipationSumTable.TPS_COLLEGE + ","
				+ TeacherParticipationSumTable.TPS_COMMENTS +"," + TeacherParticipationSumTable.TPS_ISNULL+ ")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

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
			pstmt.setString(1, teacherParticipationSum.getTps_particollege());
			pstmt.setInt(2, teacherParticipationSum.getTps_schskillcompecourtyardnum());
			pstmt.setInt(3, teacherParticipationSum.getTps_schskillcompeschoolnum());
			pstmt.setInt(4, teacherParticipationSum.getTps_schskillcompespecialnum());
			pstmt.setInt(5, teacherParticipationSum.getTps_schskillcompefirstnum());
			pstmt.setInt(6, teacherParticipationSum.getTps_schskillcompesecnum());
			pstmt.setInt(7, teacherParticipationSum.getTps_provinskillcompepartinum());
			pstmt.setInt(8, teacherParticipationSum.getTps_provinskillcompespecialnum());
			pstmt.setInt(9, teacherParticipationSum.getTps_provinskillcompefirstnum());
			pstmt.setInt(10, teacherParticipationSum.getTps_provinskillcompesecnum());
			pstmt.setInt(11, teacherParticipationSum.getTps_countryskillcompepartinum());
			pstmt.setInt(12, teacherParticipationSum.getTps_countryskillcompespecialnum());
			pstmt.setInt(13, teacherParticipationSum.getTps_countryskillcompefirstnum());
			pstmt.setInt(14, teacherParticipationSum.getTps_countryskillcompesecnum());
			pstmt.setInt(15, teacherParticipationSum.getTps_countrymicrocompepartinum());
			pstmt.setInt(16, teacherParticipationSum.getTps_countrymicrocompespecialnum());
			pstmt.setInt(17, teacherParticipationSum.getTps_countrymicrocompefirstnum());
			pstmt.setInt(18, teacherParticipationSum.getTps_countrymicrocompesecnum());
			pstmt.setString(19, teacherParticipationSum.getTps_preparer());
			pstmt.setInt(20, teacherParticipationSum.getTps_serialnumber());
			pstmt.setDate(21, teacherParticipationSum.getTps_deadline());
			pstmt.setString(22, teacherParticipationSum.getTps_college());
			pstmt.setString(23, teacherParticipationSum.getTps_comments());
			pstmt.setInt(24, teacherParticipationSum.getTps_isnull());
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
	public int alterTeacherParticipationSum(Map<String, String> valueMap, String id)
	{
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(TeacherParticipationSumTable.TPS_ID, id);
		int result = updateRecord(TeacherParticipationSumTable.TABLE_NAME, valueMap, condition);
		return result;
	}

	@Override
	public List<TeacherParticipationSum> getAll()
	{
		String sql = " select * from " + TeacherParticipationSumTable.TABLE_NAME + " where 1=1 " + " order by "
				+ TeacherParticipationSumTable.TPS_ID;
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
			List<TeacherParticipationSum> tpsList = new ArrayList<TeacherParticipationSum>();
			while (resultSet.next())
			{
				int tps_id = resultSet.getInt(TeacherParticipationSumTable.TPS_ID);
				String tps_particollege = resultSet.getString(TeacherParticipationSumTable.TPS_PARTICOLLEGE);
				Integer tps_schskillcompecourtyardnum = resultSet
						.getInt(TeacherParticipationSumTable.TPS_SCHSKILLCOMPECOURTYARDNUM);
				if(tps_schskillcompecourtyardnum == -9)
					tps_schskillcompecourtyardnum = null;
				Integer tps_schskillcompeschoolnum = resultSet
						.getInt(TeacherParticipationSumTable.TPS_SCHSKILLCOMPESCHOOLNUM);
				if(tps_schskillcompeschoolnum == -9)
					tps_schskillcompeschoolnum = null;
				Integer tps_schskillcompespecialnum = resultSet
						.getInt(TeacherParticipationSumTable.TPS_SCHSKILLCOMPESPECIALNUM);
				if(tps_schskillcompespecialnum == -9)
					tps_schskillcompespecialnum = null;
				Integer tps_schskillcompefirstnum = resultSet
						.getInt(TeacherParticipationSumTable.TPS_SCHSKILLCOMPEFIRSTNUM);
				if(tps_schskillcompefirstnum == -9)
					tps_schskillcompefirstnum = null;
				Integer tps_schskillcompesecnum = resultSet.getInt(TeacherParticipationSumTable.TPS_SCHSKILLCOMPESECNUM);
				if(tps_schskillcompesecnum == -9)
					tps_schskillcompesecnum = null;
				Integer tps_provinskillcompepartinum = resultSet
						.getInt(TeacherParticipationSumTable.TPS_PROVINSKILLCOMPEPARTINUM);
				if(tps_provinskillcompepartinum == -9)
					tps_provinskillcompepartinum = null;
				Integer tps_provinskillcompespecialnum = resultSet
						.getInt(TeacherParticipationSumTable.TPS_PROVINSKILLCOMPESPECIALNUM);
				if(tps_provinskillcompespecialnum == -9)
					tps_provinskillcompespecialnum = null;
				Integer tps_provinskillcompefirstnum = resultSet
						.getInt(TeacherParticipationSumTable.TPS_PROVINSKILLCOMPEFIRSTNUM);
				if(tps_provinskillcompefirstnum == -9)
					tps_provinskillcompefirstnum = null;
				Integer tps_provinskillcompesecnum = resultSet
						.getInt(TeacherParticipationSumTable.TPS_PROVINSKILLCOMPESECNUM);
				if(tps_provinskillcompesecnum == -9)
					tps_provinskillcompesecnum = null;
				Integer tps_countryskillcompepartinum = resultSet
						.getInt(TeacherParticipationSumTable.TPS_COUNTRYSKILLCOMPEPARTINUM);
				if(tps_countryskillcompepartinum == -9)
					tps_countryskillcompepartinum = null;
				Integer tps_countryskillcompespecialnum = resultSet
						.getInt(TeacherParticipationSumTable.TPS_COUNTRYSKILLCOMPESPECIALNUM);
				if(tps_countryskillcompespecialnum == -9)
					tps_countryskillcompespecialnum = null;
				Integer tps_countryskillcompefirstnum = resultSet
						.getInt(TeacherParticipationSumTable.TPS_COUNTRYSKILLCOMPEFIRSTNUM);
				if(tps_countryskillcompefirstnum == -9)
					tps_countryskillcompefirstnum = null;
				Integer tps_countryskillcompesecnum = resultSet
						.getInt(TeacherParticipationSumTable.TPS_COUNTRYSKILLCOMPESECNUM);
				if(tps_countryskillcompesecnum == -9)
					tps_countryskillcompesecnum = null;
				Integer tps_countrymicrocompepartinum = resultSet
						.getInt(TeacherParticipationSumTable.TPS_COUNTRYMICROCOMPEPARTINUM);
				if(tps_countrymicrocompepartinum == -9)
					tps_countrymicrocompepartinum = null;
				Integer tps_countrymicrocompespecialnum = resultSet
						.getInt(TeacherParticipationSumTable.TPS_COUNTRYMICROCOMPESPECIALNUM);
				if(tps_countrymicrocompespecialnum == -9)
					tps_countrymicrocompespecialnum = null;
				Integer tps_countrymicrocompefirstnum = resultSet
						.getInt(TeacherParticipationSumTable.TPS_COUNTRYMICROCOMPEFIRSTNUM);
				if(tps_countrymicrocompefirstnum == -9)
					tps_countrymicrocompefirstnum = null;
				Integer tps_countrymicrocompesecnum = resultSet
						.getInt(TeacherParticipationSumTable.TPS_COUNTRYMICROCOMPESECNUM);
				if(tps_countrymicrocompesecnum == -9)
					tps_countrymicrocompesecnum = null;
				String tps_preparer = resultSet.getString(TeacherParticipationSumTable.TPS_PREPARER);
				int tps_serialnumber = resultSet.getInt(TeacherParticipationSumTable.TPS_SERIALNUMBER);
				Date tps_deadline = resultSet.getDate(TeacherParticipationSumTable.TPS_DEADLINE);
				String tps_college = resultSet.getString(TeacherParticipationSumTable.TPS_COLLEGE);
				String tps_comments = resultSet.getString(TeacherParticipationSumTable.TPS_COMMENTS);
				int tps_isnull = resultSet.getInt(TeacherParticipationSumTable.TPS_ISNULL);
				TeacherParticipationSum teacherParticipationSum = new TeacherParticipationSum(tps_id, tps_particollege,
						tps_schskillcompecourtyardnum, tps_schskillcompeschoolnum, tps_schskillcompespecialnum,
						tps_schskillcompefirstnum, tps_schskillcompesecnum, tps_provinskillcompepartinum,
						tps_provinskillcompespecialnum, tps_provinskillcompefirstnum, tps_provinskillcompesecnum,
						tps_countryskillcompepartinum, tps_countryskillcompespecialnum, tps_countryskillcompefirstnum,
						tps_countryskillcompesecnum, tps_countrymicrocompepartinum, tps_countrymicrocompespecialnum,
						tps_countrymicrocompefirstnum, tps_countrymicrocompesecnum, tps_preparer, tps_serialnumber,
						tps_deadline, tps_college, tps_comments, tps_isnull);

				tpsList.add(teacherParticipationSum);
			}
			return tpsList;
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
		String sql = "delete from " + TeacherParticipationSumTable.TABLE_NAME + " where "
				+ TeacherParticipationSumTable.TPS_COLLEGE + " = '" + college + "'" + " and tps_deadline is null ";
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
