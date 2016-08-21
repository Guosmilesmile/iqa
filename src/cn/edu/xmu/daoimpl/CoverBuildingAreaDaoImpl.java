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

import cn.edu.xmu.dao.CoverBuildingAreaDao;
import cn.edu.xmu.entity.CoverBuildingArea;
import cn.edu.xmu.table.CoverBuildingAreaTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;

public class CoverBuildingAreaDaoImpl extends BaseDaoImpl<CoverBuildingArea>implements CoverBuildingAreaDao
{

	@Override
	public List<CoverBuildingArea> getCoverBuildingAreaList(int start, int end, String sortStr, String orderStr,
			Map queryParams,Date deadline)
	{
		String sql = " select tmp.* from ( " + " select * from " + CoverBuildingAreaTable.TABLE_NAME + " where 1=1 ";
		if (deadline != null) {
			sql += String.format("and "+CoverBuildingAreaTable.CBA_DEADLINE+" like  '%s%%' ", deadline);
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
			List<CoverBuildingArea> cbas = new ArrayList<CoverBuildingArea>();
			Date temp = Date.valueOf("1800-1-1");
			while (resultSet.next())
			{
				int cba_id = resultSet.getInt(CoverBuildingAreaTable.CBA_ID);
				Date cba_statisticaltime = resultSet.getDate(CoverBuildingAreaTable.CBA_STATISTICALTIME);
				if(temp.equals(cba_statisticaltime))
					cba_statisticaltime = null;
				String cba_fillschool = resultSet.getString(CoverBuildingAreaTable.CBA_FILLSCHOOL);
				Float cba_totalcoverarea = resultSet.getFloat(CoverBuildingAreaTable.CBA_TOTALCOVERAREA);
				if(cba_totalcoverarea == -9)
					cba_totalcoverarea = null;
				Float cba_propertycov = resultSet.getFloat(CoverBuildingAreaTable.CBA_PROPERTYCOV);
				if(cba_propertycov == -9)
					cba_propertycov = null;
				Float cba_propertyafforestcov = resultSet.getFloat(CoverBuildingAreaTable.CBA_PROPERTYAFFORESTCOV);
				if(cba_propertyafforestcov == -9)
					cba_propertyafforestcov = null;
				Float cba_nonpropertycov = resultSet.getFloat(CoverBuildingAreaTable.CBA_NONPROPERTYCOV);
				if(cba_nonpropertycov == -9)
					cba_nonpropertycov = null;
				Float cba_nonpropertyafforestcov = resultSet
						.getFloat(CoverBuildingAreaTable.CBA_NONPROPERTYAFFORESTCOV);
				if(cba_nonpropertyafforestcov == -9)
					cba_nonpropertyafforestcov = null;
				Float cba_nonproindepusecov = resultSet.getFloat(CoverBuildingAreaTable.CBA_NONPROINDEPUSECOV);
				if(cba_nonproindepusecov == -9)
					cba_nonproindepusecov = null;
				Float cba_nonprocommonusecov = resultSet.getFloat(CoverBuildingAreaTable.CBA_NONPROCOMMONUSECOV);
				if(cba_nonprocommonusecov  == -9)
					cba_nonprocommonusecov = null;
				Float cba_totalbuildingarea = resultSet.getFloat(CoverBuildingAreaTable.CBA_TOTALBUILDINGAREA);
				if(cba_totalbuildingarea  == -9)
					cba_totalbuildingarea = null;
				Float cba_propertybui = resultSet.getFloat(CoverBuildingAreaTable.CBA_PROPERTYBUI);
				if(cba_propertybui  == -9)
					cba_propertybui = null;
				Float cba_nonpropertybui = resultSet.getFloat(CoverBuildingAreaTable.CBA_NONPROPERTYBUI);
				if(cba_nonpropertybui  == -9)
					cba_nonpropertybui = null;
				Float cba_nonproindepusebui = resultSet.getFloat(CoverBuildingAreaTable.CBA_NONPROINDEPUSEBUI);
				if(cba_nonproindepusebui  == -9)
					cba_nonproindepusebui = null;
				Float cba_nonprocommonusebui = resultSet.getFloat(CoverBuildingAreaTable.CBA_NONPROCOMMONUSEBUI);
				if(cba_nonprocommonusebui  == -9)
					cba_nonprocommonusebui = null;
				int cba_serialnumber = resultSet.getInt(CoverBuildingAreaTable.CBA_SERIALNUMBER);
				Date cba_deadline = resultSet.getDate(CoverBuildingAreaTable.CBA_DEADLINE);
				String cba_college = resultSet.getString(CoverBuildingAreaTable.CBA_COLLEGE);
				String cba_comments = resultSet.getString(CoverBuildingAreaTable.CBA_COMMENTS);
				int cba_isnull = resultSet.getInt(CoverBuildingAreaTable.CBA_ISNULL);
				CoverBuildingArea coverBuildingArea = new CoverBuildingArea(cba_id, cba_statisticaltime, cba_fillschool,
						cba_totalcoverarea, cba_propertycov, cba_propertyafforestcov, cba_nonpropertycov,
						cba_nonpropertyafforestcov, cba_nonproindepusecov, cba_nonprocommonusecov,
						cba_totalbuildingarea, cba_propertybui, cba_nonpropertybui, cba_nonproindepusebui,
						cba_nonprocommonusebui, cba_serialnumber, cba_deadline, cba_college, cba_comments, cba_isnull);
				cbas.add(coverBuildingArea);
			}
			return cbas;
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
	public int getCoverBuildingAreaCount(Map queryParams)
	{
		int count = 0;
		String sql = "select count(*) from " + CoverBuildingAreaTable.TABLE_NAME + " where 1 = 1";
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
		String sql = "select max(" + CoverBuildingAreaTable.CBA_SERIALNUMBER + ") from "
				+ CoverBuildingAreaTable.TABLE_NAME;

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
	public boolean batchDelete(String[] cbaids) throws SQLException
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

		for (String cba_id : cbaids)
		{
			String sql = "delete from " + CoverBuildingAreaTable.TABLE_NAME + " where cba_id = '" + cba_id + "'";
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
	public int addCoverBuildingArea(CoverBuildingArea coverBuildingArea)
	{
		int result = 0;

		String t_sql = "update " + CoverBuildingAreaTable.TABLE_NAME + " set "
				+ CoverBuildingAreaTable.CBA_SERIALNUMBER + " = "
				+ CoverBuildingAreaTable.CBA_SERIALNUMBER + " +1 where "
				+ CoverBuildingAreaTable.CBA_SERIALNUMBER + ">=?";
		Connection connection2 = null;
		try {
			//连接池取得对象连接
			connection2 = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
			return 0;
		}
		PreparedStatement t_pstmt = null;
		try {
			//获取操作对象
			t_pstmt = connection2.prepareStatement(t_sql);
			t_pstmt.setInt(1, coverBuildingArea.getCba_serialnumber());
			
			//执行插入操作并更新
			result = t_pstmt.executeUpdate();
			
		} catch (SQLException e) {
			//事务回滚，不做插入操作
			e.printStackTrace();
			return 0;
		} finally {
			JdbcUtils_DBCP.release(connection2, t_pstmt, null);
		}
		String sql = "insert into " + CoverBuildingAreaTable.TABLE_NAME + "("
				+ CoverBuildingAreaTable.CBA_STATISTICALTIME + "," + CoverBuildingAreaTable.CBA_FILLSCHOOL + ","
				+ CoverBuildingAreaTable.CBA_TOTALCOVERAREA + "," + CoverBuildingAreaTable.CBA_PROPERTYCOV + ","
				+ CoverBuildingAreaTable.CBA_PROPERTYAFFORESTCOV + "," + CoverBuildingAreaTable.CBA_NONPROPERTYCOV + ","
				+ CoverBuildingAreaTable.CBA_NONPROPERTYAFFORESTCOV + "," + CoverBuildingAreaTable.CBA_NONPROINDEPUSECOV
				+ "," + CoverBuildingAreaTable.CBA_NONPROCOMMONUSECOV + ","
				+ CoverBuildingAreaTable.CBA_TOTALBUILDINGAREA + "," + CoverBuildingAreaTable.CBA_PROPERTYBUI + ","
				+ CoverBuildingAreaTable.CBA_NONPROPERTYBUI + "," + CoverBuildingAreaTable.CBA_NONPROINDEPUSEBUI + ","
				+ CoverBuildingAreaTable.CBA_NONPROCOMMONUSEBUI + "," + CoverBuildingAreaTable.CBA_SERIALNUMBER + ","
				+ CoverBuildingAreaTable.CBA_DEADLINE + "," + CoverBuildingAreaTable.CBA_COLLEGE + ","
				+ CoverBuildingAreaTable.CBA_COMMENTS + ","+CoverBuildingAreaTable.CBA_ISNULL + ")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

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
			pstmt.setDate(1, coverBuildingArea.getCba_statisticaltime());
			pstmt.setString(2, coverBuildingArea.getCba_fillschool());
			pstmt.setFloat(3, coverBuildingArea.getCba_totalcoverarea());
			pstmt.setFloat(4, coverBuildingArea.getCba_propertycov());
			pstmt.setFloat(5, coverBuildingArea.getCba_propertyafforestcov());
			pstmt.setFloat(6, coverBuildingArea.getCba_nonpropertycov());
			pstmt.setFloat(7, coverBuildingArea.getCba_nonpropertyafforestcov());
			pstmt.setFloat(8, coverBuildingArea.getCba_nonproindepusecov());
			pstmt.setFloat(9, coverBuildingArea.getCba_nonprocommonusecov());
			pstmt.setFloat(10, coverBuildingArea.getCba_totalbuildingarea());
			pstmt.setFloat(11, coverBuildingArea.getCba_propertybui());
			pstmt.setFloat(12, coverBuildingArea.getCba_nonpropertybui());
			pstmt.setFloat(13, coverBuildingArea.getCba_nonproindepusebui());
			pstmt.setFloat(14, coverBuildingArea.getCba_nonprocommonusebui());
			pstmt.setInt(15, coverBuildingArea.getCba_serialnumber());
			pstmt.setDate(16, coverBuildingArea.getCba_deadline());
			pstmt.setString(17, coverBuildingArea.getCba_college());
			pstmt.setString(18, coverBuildingArea.getCba_comments());
			pstmt.setInt(19, coverBuildingArea.getCba_isnull());
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
	public int alterCoverBuildingArea(Map<String, String> valueMap, String id)
	{
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(CoverBuildingAreaTable.CBA_ID, id);
		int result = updateRecord(CoverBuildingAreaTable.TABLE_NAME, valueMap, condition);
		return result;
	}

	@Override
	public List<CoverBuildingArea> getAll()
	{
		String sql = " select * from " + CoverBuildingAreaTable.TABLE_NAME + " where 1=1 " + " order by "
				+ CoverBuildingAreaTable.CBA_ID;
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
			List<CoverBuildingArea> cbaList = new ArrayList<CoverBuildingArea>();
			while (resultSet.next())
			{
				int cba_id = resultSet.getInt(CoverBuildingAreaTable.CBA_ID);
				Date cba_statisticaltime = resultSet.getDate(CoverBuildingAreaTable.CBA_STATISTICALTIME);
				Date temp = Date.valueOf("1800-1-1");
				if(temp.equals(cba_statisticaltime))
					cba_statisticaltime = null;
				String cba_fillschool = resultSet.getString(CoverBuildingAreaTable.CBA_FILLSCHOOL);
				Float cba_totalcoverarea = resultSet.getFloat(CoverBuildingAreaTable.CBA_TOTALCOVERAREA);
				if(cba_totalcoverarea == -9)
					cba_totalcoverarea = null;
				Float cba_propertycov = resultSet.getFloat(CoverBuildingAreaTable.CBA_PROPERTYCOV);
				if(cba_propertycov == -9)
					cba_propertycov = null;
				Float cba_propertyafforestcov = resultSet.getFloat(CoverBuildingAreaTable.CBA_PROPERTYAFFORESTCOV);
				if(cba_propertyafforestcov == -9)
					cba_propertyafforestcov = null;
				Float cba_nonpropertycov = resultSet.getFloat(CoverBuildingAreaTable.CBA_NONPROPERTYCOV);
				if(cba_nonpropertycov == -9)
					cba_nonpropertycov = null;
				Float cba_nonpropertyafforestcov = resultSet
						.getFloat(CoverBuildingAreaTable.CBA_NONPROPERTYAFFORESTCOV);
				if(cba_nonpropertyafforestcov == -9)
					cba_nonpropertyafforestcov = null;
				Float cba_nonproindepusecov = resultSet.getFloat(CoverBuildingAreaTable.CBA_NONPROINDEPUSECOV);
				if(cba_nonproindepusecov == -9)
					cba_nonproindepusecov = null;
				Float cba_nonprocommonusecov = resultSet.getFloat(CoverBuildingAreaTable.CBA_NONPROCOMMONUSECOV);
				if(cba_nonprocommonusecov  == -9)
					cba_nonprocommonusecov = null;
				Float cba_totalbuildingarea = resultSet.getFloat(CoverBuildingAreaTable.CBA_TOTALBUILDINGAREA);
				if(cba_totalbuildingarea  == -9)
					cba_totalbuildingarea = null;
				Float cba_propertybui = resultSet.getFloat(CoverBuildingAreaTable.CBA_PROPERTYBUI);
				if(cba_propertybui  == -9)
					cba_propertybui = null;
				Float cba_nonpropertybui = resultSet.getFloat(CoverBuildingAreaTable.CBA_NONPROPERTYBUI);
				if(cba_nonpropertybui  == -9)
					cba_nonpropertybui = null;
				Float cba_nonproindepusebui = resultSet.getFloat(CoverBuildingAreaTable.CBA_NONPROINDEPUSEBUI);
				if(cba_nonproindepusebui  == -9)
					cba_nonproindepusebui = null;
				Float cba_nonprocommonusebui = resultSet.getFloat(CoverBuildingAreaTable.CBA_NONPROCOMMONUSEBUI);
				if(cba_nonprocommonusebui  == -9)
					cba_nonprocommonusebui = null;
				int cba_serialnumber = resultSet.getInt(CoverBuildingAreaTable.CBA_SERIALNUMBER);
				Date cba_deadline = resultSet.getDate(CoverBuildingAreaTable.CBA_DEADLINE);
				String cba_college = resultSet.getString(CoverBuildingAreaTable.CBA_COLLEGE);
				String cba_comments = resultSet.getString(CoverBuildingAreaTable.CBA_COMMENTS);
				int cba_isnull = resultSet.getInt(CoverBuildingAreaTable.CBA_ISNULL);
				CoverBuildingArea coverBuildingArea = new CoverBuildingArea(cba_id, cba_statisticaltime, cba_fillschool,
						cba_totalcoverarea, cba_propertycov, cba_propertyafforestcov, cba_nonpropertycov,
						cba_nonpropertyafforestcov, cba_nonproindepusecov, cba_nonprocommonusecov,
						cba_totalbuildingarea, cba_propertybui, cba_nonpropertybui, cba_nonproindepusebui,
						cba_nonprocommonusebui, cba_serialnumber, cba_deadline, cba_college, cba_comments, cba_isnull);

				cbaList.add(coverBuildingArea);
			}
			return cbaList;
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
		String sql = "delete from " + CoverBuildingAreaTable.TABLE_NAME + " where "
				+ CoverBuildingAreaTable.CBA_COLLEGE + " = '" + college + "'" + " and cba_deadline is null ";
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
