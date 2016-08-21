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

import cn.edu.xmu.dao.TeachingPlanImplDao;
import cn.edu.xmu.entity.TeachingPlanImpl;
import cn.edu.xmu.table.TeachingPlanImplTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;

/**
 * 
 * @author xiaoping 数据报表 附表4-2-2-2教学计划执行情况 date 2015-7-8
 *
 */
public class TeachingPlanImplDaoImpl extends BaseDaoImpl<TeachingPlanImpl>implements TeachingPlanImplDao
{

	@Override
	public List<TeachingPlanImpl> getTeachingPlanImpls(int start, int end, String sortStr, String orderStr,
			Map queryParams, Date deadline)
	{
		String sql = " select tmp.* from ( " + " select * from " + TeachingPlanImplTable.TABLE_NAME + " where 1=1 ";
		if (deadline != null)
		{
			sql += String.format("and " + TeachingPlanImplTable.TPI_DEADLINE + " like  '%s%%' ", deadline);
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
			List<TeachingPlanImpl> teachingPlanImpls = new ArrayList<TeachingPlanImpl>();

			while (resultSet.next())
			{
				int tpi_id = resultSet.getInt(TeachingPlanImplTable.TPI_ID);
				String tpi_institute = resultSet.getString(TeachingPlanImplTable.TPI_INSTITUTE);
				String tpi_major = resultSet.getString(TeachingPlanImplTable.TPI_MAJOR);
				String tpi_grade = resultSet.getString(TeachingPlanImplTable.TPI_GRADE);
				Integer tpi_plancoursenum = resultSet.getInt(TeachingPlanImplTable.TPI_PLANCOURSENUM);
				if(tpi_plancoursenum == -9)
					tpi_plancoursenum = null;
				Float tpi_plancoursecredit = resultSet.getFloat(TeachingPlanImplTable.TPI_PLANCOURSECREDIT);
				if(tpi_plancoursecredit == -9)
					tpi_plancoursecredit = null;
				Integer tpi_actualcoursenum = resultSet.getInt(TeachingPlanImplTable.TPI_ACTUALCOURSENUM);
				if(tpi_actualcoursenum == -9)
					tpi_actualcoursenum = null;
				Float tpi_actualcoursecredit = resultSet.getFloat(TeachingPlanImplTable.TPI_ACTUALCOURSECREDIT);
				if(tpi_actualcoursecredit == -9)
					tpi_actualcoursecredit = null;
				Integer tpi_newcoursenum = resultSet.getInt(TeachingPlanImplTable.TPI_NEWCOURSENUM);
				if(tpi_newcoursenum == -9)
					tpi_newcoursenum = null;
				Float tpi_newcoursecredit = resultSet.getFloat(TeachingPlanImplTable.TPI_NEWCOURSECREDIT);
				if(tpi_newcoursecredit == -9)
					tpi_newcoursecredit = null;
				Integer tpi_stopcoursenum = resultSet.getInt(TeachingPlanImplTable.TPI_STOPCOURSENUM);
				if(tpi_stopcoursenum == -9)
					tpi_stopcoursenum = null;
				Float tpi_stopcoursecredit = resultSet.getFloat(TeachingPlanImplTable.TPI_STOPCOURSECREDIT);
				if(tpi_stopcoursecredit == -9)
					tpi_stopcoursecredit = null;
				String tpi_advancelatercoursenum = resultSet.getString(TeachingPlanImplTable.TPI_ADVANCELATERCOURSENUM);
				Float tpi_advancelatercoursecredit = resultSet
						.getFloat(TeachingPlanImplTable.TPI_ADVANCELATERCOURSECREDIT);
				if(tpi_advancelatercoursecredit == -9)
					tpi_advancelatercoursecredit = null;
				int tpi_serialnumber = resultSet.getInt(TeachingPlanImplTable.TPI_SERIALNUMBER);
				Date tpi_deadline = resultSet.getDate(TeachingPlanImplTable.TPI_DEADLINE);
				String tpi_college = resultSet.getString(TeachingPlanImplTable.TPI_COLLEGE);
				String tpi_comments = resultSet.getString(TeachingPlanImplTable.TPI_COMMENTS);
				int tpi_isnull = resultSet.getInt(TeachingPlanImplTable.TPI_ISNULL);
				TeachingPlanImpl teachingPlanImpl = new TeachingPlanImpl(tpi_id, tpi_institute, tpi_major, tpi_grade,
						tpi_plancoursenum, tpi_plancoursecredit, tpi_actualcoursenum, tpi_actualcoursecredit,
						tpi_newcoursenum, tpi_newcoursecredit, tpi_stopcoursenum, tpi_stopcoursecredit,
						tpi_advancelatercoursenum, tpi_advancelatercoursecredit, tpi_serialnumber, tpi_deadline,
						tpi_college, tpi_comments, tpi_isnull);

				teachingPlanImpls.add(teachingPlanImpl);
			}
			return teachingPlanImpls;
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
	public int getTeachingPlanImplCount(Map queryParams)
	{
		int count = 0;
		String sql = "select count(*) from " + TeachingPlanImplTable.TABLE_NAME + " where 1 = 1";
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
		String sql = "select max(" + TeachingPlanImplTable.TPI_SERIALNUMBER + ") from "
				+ TeachingPlanImplTable.TABLE_NAME;

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
	public boolean batchDelete(String[] tpiids) throws SQLException
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

		for (String tpiid : tpiids)
		{
			String sql = "delete from " + TeachingPlanImplTable.TABLE_NAME + " where tpi_id = '" + tpiid + "'";
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
	public int addTeachingPlanImpl(TeachingPlanImpl teachingPlanImpl)
	{
		int result = 0;

		String t_sql = "update " + TeachingPlanImplTable.TABLE_NAME + " set " + TeachingPlanImplTable.TPI_SERIALNUMBER
				+ " = " + TeachingPlanImplTable.TPI_SERIALNUMBER + " +1 where " + TeachingPlanImplTable.TPI_SERIALNUMBER
				+ ">=?";
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
			t_pstmt.setInt(1, teachingPlanImpl.getTpi_serialnumber());

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
		String sql = "insert into " + TeachingPlanImplTable.TABLE_NAME + "(" + TeachingPlanImplTable.TPI_INSTITUTE + ","
				+ TeachingPlanImplTable.TPI_MAJOR + "," + TeachingPlanImplTable.TPI_GRADE + ","
				+ TeachingPlanImplTable.TPI_PLANCOURSENUM + "," + TeachingPlanImplTable.TPI_PLANCOURSECREDIT + ","
				+ TeachingPlanImplTable.TPI_ACTUALCOURSENUM + "," + TeachingPlanImplTable.TPI_ACTUALCOURSECREDIT + ","
				+ TeachingPlanImplTable.TPI_NEWCOURSENUM + "," + TeachingPlanImplTable.TPI_NEWCOURSECREDIT + ","
				+ TeachingPlanImplTable.TPI_STOPCOURSENUM + "," + TeachingPlanImplTable.TPI_STOPCOURSECREDIT + ","
				+ TeachingPlanImplTable.TPI_ADVANCELATERCOURSENUM + ","
				+ TeachingPlanImplTable.TPI_ADVANCELATERCOURSECREDIT + "," + TeachingPlanImplTable.TPI_SERIALNUMBER
				+ "," + TeachingPlanImplTable.TPI_DEADLINE + "," + TeachingPlanImplTable.TPI_COLLEGE + ","
				+ TeachingPlanImplTable.TPI_COMMENTS+ "," + TeachingPlanImplTable.TPI_ISNULL + ")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

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
			pstmt.setString(1, teachingPlanImpl.getTpi_institute());
			pstmt.setString(2, teachingPlanImpl.getTpi_major());
			pstmt.setString(3, teachingPlanImpl.getTpi_grade());
			pstmt.setInt(4, teachingPlanImpl.getTpi_plancoursenum());
			pstmt.setFloat(5, teachingPlanImpl.getTpi_plancoursecredit());
			pstmt.setInt(6, teachingPlanImpl.getTpi_actualcoursenum());
			pstmt.setFloat(7, teachingPlanImpl.getTpi_actualcoursecredit());
			pstmt.setInt(8, teachingPlanImpl.getTpi_newcoursenum());
			pstmt.setFloat(9, teachingPlanImpl.getTpi_newcoursecredit());
			pstmt.setInt(10, teachingPlanImpl.getTpi_stopcoursenum());
			pstmt.setFloat(11, teachingPlanImpl.getTpi_stopcoursecredit());
			pstmt.setString(12, teachingPlanImpl.getTpi_advancelatercoursenum());
			pstmt.setFloat(13, teachingPlanImpl.getTpi_advancelatercoursecredit());
			pstmt.setInt(14, teachingPlanImpl.getTpi_serialnumber());
			pstmt.setDate(15, teachingPlanImpl.getTpi_deadline());
			pstmt.setString(16, teachingPlanImpl.getTpi_college());
			pstmt.setString(17, teachingPlanImpl.getTpi_comments());
			pstmt.setInt(18, teachingPlanImpl.getTpi_isnull());
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
	public int alterTeachingPlanImpl(Map<String, String> valueMap, String id)
	{
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(TeachingPlanImplTable.TPI_ID, id);
		int result = updateRecord(TeachingPlanImplTable.TABLE_NAME, valueMap, condition);
		return result;
	}

	@Override
	public List<TeachingPlanImpl> getAll()
	{
		String sql = " select * from " + TeachingPlanImplTable.TABLE_NAME + " where 1=1 " + " order by "
				+ TeachingPlanImplTable.TPI_ID;
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
			List<TeachingPlanImpl> tpiList = new ArrayList<TeachingPlanImpl>();
			while (resultSet.next())
			{
				int tpi_id = resultSet.getInt(TeachingPlanImplTable.TPI_ID);
				String tpi_institute = resultSet.getString(TeachingPlanImplTable.TPI_INSTITUTE);
				String tpi_major = resultSet.getString(TeachingPlanImplTable.TPI_MAJOR);
				String tpi_grade = resultSet.getString(TeachingPlanImplTable.TPI_GRADE);
				Integer tpi_plancoursenum = resultSet.getInt(TeachingPlanImplTable.TPI_PLANCOURSENUM);
				if(tpi_plancoursenum == -9)
					tpi_plancoursenum = null;
				Float tpi_plancoursecredit = resultSet.getFloat(TeachingPlanImplTable.TPI_PLANCOURSECREDIT);
				if(tpi_plancoursecredit == -9)
					tpi_plancoursecredit = null;
				Integer tpi_actualcoursenum = resultSet.getInt(TeachingPlanImplTable.TPI_ACTUALCOURSENUM);
				if(tpi_actualcoursenum == -9)
					tpi_actualcoursenum = null;
				Float tpi_actualcoursecredit = resultSet.getFloat(TeachingPlanImplTable.TPI_ACTUALCOURSECREDIT);
				if(tpi_actualcoursecredit == -9)
					tpi_actualcoursecredit = null;
				Integer tpi_newcoursenum = resultSet.getInt(TeachingPlanImplTable.TPI_NEWCOURSENUM);
				if(tpi_newcoursenum == -9)
					tpi_newcoursenum = null;
				Float tpi_newcoursecredit = resultSet.getFloat(TeachingPlanImplTable.TPI_NEWCOURSECREDIT);
				if(tpi_newcoursecredit == -9)
					tpi_newcoursecredit = null;
				Integer tpi_stopcoursenum = resultSet.getInt(TeachingPlanImplTable.TPI_STOPCOURSENUM);
				if(tpi_stopcoursenum == -9)
					tpi_stopcoursenum = null;
				Float tpi_stopcoursecredit = resultSet.getFloat(TeachingPlanImplTable.TPI_STOPCOURSECREDIT);
				if(tpi_stopcoursecredit == -9)
					tpi_stopcoursecredit = null;
				String tpi_advancelatercoursenum = resultSet.getString(TeachingPlanImplTable.TPI_ADVANCELATERCOURSENUM);
				Float tpi_advancelatercoursecredit = resultSet
						.getFloat(TeachingPlanImplTable.TPI_ADVANCELATERCOURSECREDIT);
				if(tpi_advancelatercoursecredit == -9)
					tpi_advancelatercoursecredit = null;
				int tpi_serialnumber = resultSet.getInt(TeachingPlanImplTable.TPI_SERIALNUMBER);
				Date tpi_deadline = resultSet.getDate(TeachingPlanImplTable.TPI_DEADLINE);
				String tpi_college = resultSet.getString(TeachingPlanImplTable.TPI_COLLEGE);
				String tpi_comments = resultSet.getString(TeachingPlanImplTable.TPI_COMMENTS);
				int tpi_isnull = resultSet.getInt(TeachingPlanImplTable.TPI_ISNULL);
				TeachingPlanImpl teachingPlanImpl = new TeachingPlanImpl(tpi_id, tpi_institute, tpi_major, tpi_grade,
						tpi_plancoursenum, tpi_plancoursecredit, tpi_actualcoursenum, tpi_actualcoursecredit,
						tpi_newcoursenum, tpi_newcoursecredit, tpi_stopcoursenum, tpi_stopcoursecredit,
						tpi_advancelatercoursenum, tpi_advancelatercoursecredit, tpi_serialnumber, tpi_deadline,
						tpi_college, tpi_comments, tpi_isnull);

				tpiList.add(teachingPlanImpl);
			}
			return tpiList;
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
		String sql = "delete from " + TeachingPlanImplTable.TABLE_NAME + " where "
				+ TeachingPlanImplTable.TPI_COLLEGE + " = '" + college + "'" + " and tpi_deadline is null ";
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
