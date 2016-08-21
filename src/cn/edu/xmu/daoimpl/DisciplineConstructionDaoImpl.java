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

import cn.edu.xmu.dao.DisciplineConstructionDao;
import cn.edu.xmu.entity.DisciplineConstruction;
import cn.edu.xmu.entity.SchoolExecutiveUnit;
import cn.edu.xmu.table.ClassCultureTable;
import cn.edu.xmu.table.DisciplineConstructionTable;
import cn.edu.xmu.table.SchoolExecutiveUnitTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;

/**
 * 
 * @author Gy 表4-1-1 学科建设 (时点)
 * 
 */

public class DisciplineConstructionDaoImpl extends BaseDaoImpl<DisciplineConstruction>
		implements DisciplineConstructionDao
{

	@Override
	public int addRecord(DisciplineConstruction dc)
	{

		int result = 0;

		String t_sql = "update " + DisciplineConstructionTable.TABLE_NAME + " set "
				+ DisciplineConstructionTable.DC_SERIALNUMBER + " = " + DisciplineConstructionTable.DC_SERIALNUMBER
				+ " +1 where " + DisciplineConstructionTable.DC_SERIALNUMBER + ">=?";

		Connection connection2 = null;
		try
		{
			// 连接池取得对象连接
			connection2 = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1)
		{
			e1.printStackTrace();
		}
		PreparedStatement t_pstmt = null;
		try
		{
			// 获取操作对象
			t_pstmt = connection2.prepareStatement(t_sql);
			t_pstmt.setInt(1, dc.getDc_serialnumber());

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

		// 执行插入操作的语句
		String sql = "insert into " + DisciplineConstructionTable.TABLE_NAME + "("
				+ DisciplineConstructionTable.DC_DOCTORSTATION + "," + DisciplineConstructionTable.DC_DOCGRANTONE + ","
				+ DisciplineConstructionTable.DC_DOCGRANTTWO + "," + DisciplineConstructionTable.DC_MASGRANTONE + ","
				+ DisciplineConstructionTable.DC_MASGRANTTWO + "," + DisciplineConstructionTable.DC_UNDERTOTAL + ","
				+ DisciplineConstructionTable.DC_UNDERNEW + "," + DisciplineConstructionTable.DC_JUNIOR + ","
				+ DisciplineConstructionTable.DC_SERIALNUMBER + "," + DisciplineConstructionTable.DC_COLLEGE + ","
				+ DisciplineConstructionTable.DC_COMMENTS + ",isnull" + ")values(?,?,?,?,?,?,?,?,?,?,'',?)";

		Connection connection = null;
		PreparedStatement pstmt = null;
		try
		{
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, dc.getDc_doctorstation());
			pstmt.setInt(2, dc.getDc_docgrantone());
			pstmt.setInt(3, dc.getDc_docgranttwo());
			pstmt.setInt(4, dc.getDc_masgranttwo());
			pstmt.setInt(5, dc.getDc_masgrantone());
			pstmt.setInt(6, dc.getDc_undertotal());
			pstmt.setInt(7, dc.getDc_undernew());
			pstmt.setInt(8, dc.getDc_junior());
			pstmt.setInt(9, dc.getDc_serialnumber());
			pstmt.setString(10, dc.getDc_college());
			pstmt.setInt(11, dc.getIsnull());
			result = pstmt.executeUpdate();

		} catch (SQLException e)
		{
			// 事务回滚。不做插入操作
			e.printStackTrace();
			result = -1;

		} finally
		{
			JdbcUtils_DBCP.release(connection, pstmt, null);
		}
		return result;

	}

	@Override
	public int getDisciplineConstructionCount(Map queryParams)
	{
		int count = 0;
		// 获取条件的语句
		String sql = "select count(*) from " + DisciplineConstructionTable.TABLE_NAME + " where 1 = 1";
		Connection connection = null;
		try
		{
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1)
		{
			e1.printStackTrace();
		}

		for (Object object : queryParams.keySet())
		{
			String key = object.toString();
			String value = queryParams.get(key).toString();
			sql += String.format(" and  %s like  '%s%%' ", key, value);
		}
		sql += String.format(" or %s ='%s'", DisciplineConstructionTable.DC_COLLEGE, "");

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
		return count;
	}

	@Override
	public List<DisciplineConstruction> getAllDisciplineConstruction(int start, int end, String sortStr,
			String orderStr, Map queryParams)
	{

		String sql = " select tmp.* from ( " + " select * from " + DisciplineConstructionTable.TABLE_NAME
				+ " where 1=1 ";

		for (Object object : queryParams.keySet())
		{
			String key = object.toString();
			String value = queryParams.get(key).toString();
			sql += String.format(" and %s like  '%%%s%%'", key, value);
		}
		sql += " order by " + sortStr + " " + orderStr + " ) tmp limit " + start + " ," + end;

		System.out.println(sql);
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		List<DisciplineConstruction> dcs = new ArrayList<DisciplineConstruction>();
		try
		{

			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next())
			{
				int dc_id = resultSet.getInt(DisciplineConstructionTable.DC_ID);
				int dc_doctorstation = resultSet.getInt(DisciplineConstructionTable.DC_DOCTORSTATION);
				int dc_docgrantone = resultSet.getInt(DisciplineConstructionTable.DC_DOCGRANTONE);
				int dc_docgranttwo = resultSet.getInt(DisciplineConstructionTable.DC_DOCGRANTTWO);
				int dc_masgrantone = resultSet.getInt(DisciplineConstructionTable.DC_MASGRANTONE);
				int dc_masgranttwo = resultSet.getInt(DisciplineConstructionTable.DC_MASGRANTTWO);
				int dc_undertotal = resultSet.getInt(DisciplineConstructionTable.DC_UNDERTOTAL);
				int dc_undernew = resultSet.getInt(DisciplineConstructionTable.DC_UNDERNEW);
				int dc_junior = resultSet.getInt(DisciplineConstructionTable.DC_JUNIOR);
				int dc_serialnumber = resultSet.getInt(DisciplineConstructionTable.DC_SERIALNUMBER);
				String dc_comments = resultSet.getString(DisciplineConstructionTable.DC_COMMENTS);
				Date dc_deadline = resultSet.getDate(DisciplineConstructionTable.DC_DEADLINE);
				String dc_college = resultSet.getString(DisciplineConstructionTable.DC_COLLEGE);
				DisciplineConstruction dc = new DisciplineConstruction(dc_id, dc_doctorstation, dc_docgrantone,
						dc_docgranttwo, dc_masgrantone, dc_masgranttwo, dc_undertotal, dc_undernew, dc_junior,
						dc_serialnumber, dc_deadline, dc_college, dc_comments);

				dcs.add(dc);
			}

		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return dcs;
	}

	@Override
	public int alterDisciplineConstruction(Map<String, String> valueMap, String id)
	{

		Map<String, String> condition = new HashMap<String, String>();
		condition.put(DisciplineConstructionTable.DC_ID, id);
		int result = updateRecord(DisciplineConstructionTable.TABLE_NAME, valueMap, condition);
		return result;
	}

	@Override
	public boolean batchDelete(String[] seuids) throws SQLException
	{
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();

		for (String seuid : seuids)
		{
			String sql = "delete from " + DisciplineConstructionTable.TABLE_NAME + " where "
					+ DisciplineConstructionTable.DC_ID + " = '" + seuid + "'";
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
	public List<DisciplineConstruction> getAllDisciplineConstruction()
	{

		String sql = " select * from " + DisciplineConstructionTable.TABLE_NAME + " where 1=1 " + " order by "
				+ DisciplineConstructionTable.DC_ID;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		List<DisciplineConstruction> dcs = new ArrayList<DisciplineConstruction>();
		try
		{

			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next())
			{
				int dc_id = resultSet.getInt(DisciplineConstructionTable.DC_ID);
				int dc_doctorstation = resultSet.getInt(DisciplineConstructionTable.DC_DOCTORSTATION);
				int dc_docgrantone = resultSet.getInt(DisciplineConstructionTable.DC_DOCGRANTONE);
				int dc_docgranttwo = resultSet.getInt(DisciplineConstructionTable.DC_DOCGRANTTWO);
				int dc_masgrantone = resultSet.getInt(DisciplineConstructionTable.DC_MASGRANTONE);
				int dc_masgranttwo = resultSet.getInt(DisciplineConstructionTable.DC_MASGRANTTWO);
				int dc_undertotal = resultSet.getInt(DisciplineConstructionTable.DC_UNDERTOTAL);
				int dc_undernew = resultSet.getInt(DisciplineConstructionTable.DC_UNDERNEW);
				int dc_junior = resultSet.getInt(DisciplineConstructionTable.DC_JUNIOR);
				int dc_serialnumber = resultSet.getInt(DisciplineConstructionTable.DC_SERIALNUMBER);
				String dc_comments = resultSet.getString(DisciplineConstructionTable.DC_COMMENTS);
				DisciplineConstruction dc = new DisciplineConstruction(dc_id, dc_doctorstation, dc_docgrantone,
						dc_docgranttwo, dc_masgrantone, dc_masgranttwo, dc_undertotal, dc_undernew, dc_junior,
						dc_serialnumber, dc_comments);

				dcs.add(dc);
			}

		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return dcs;
	}

	@Override
	public void deleteByCollegeandDeadline(String college, Date deadline) throws SQLException
	{
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();
		String sql = "delete from " + DisciplineConstructionTable.TABLE_NAME + " where "
				+ DisciplineConstructionTable.DC_COLLEGE + " = '" + college + "'" + " and  dc_deadline is null ";
		System.out.println(sql);
		try
		{
			stmt.executeUpdate(sql);
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			JdbcUtils_DBCP.release(connection, stmt, null);
		}

	}
}
