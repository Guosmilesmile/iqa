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

import cn.edu.xmu.dao.RegUndergraProfesStuNumDao;
import cn.edu.xmu.entity.RegUndergraProfesStuNum;
import cn.edu.xmu.table.RegUndergraProfesStuNumTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;

/**
 * 
 * @author xiaoping 数据报表6-1-2 普通本科分专业（大类）学生数（时点） date 2015-7-5
 *
 */
public class RegUndergraProfesStuNumDaoImpl extends BaseDaoImpl<RegUndergraProfesStuNum>
		implements RegUndergraProfesStuNumDao
{

	@Override
	public List<RegUndergraProfesStuNum> getRegUndergraProfesStuNums(int start, int end, String sortStr,
			String orderStr, Map queryParams, Date deadline)
	{
		String sql = " select tmp.* from ( " + " select * from " + RegUndergraProfesStuNumTable.TABLE_NAME
				+ " where 1=1 ";
		if (deadline != null)
		{
			sql += String.format("and " + RegUndergraProfesStuNumTable.RUPSN_DEADLINE + " like  '%s%%' ", deadline);
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
			List<RegUndergraProfesStuNum> regUndergraProfesStuNums = new ArrayList<RegUndergraProfesStuNum>();

			while (resultSet.next())
			{
				int rupsn_id = resultSet.getInt(RegUndergraProfesStuNumTable.RUPSN_ID);
				String rupsn_schprofescode = resultSet.getString(RegUndergraProfesStuNumTable.RUPSN_SCHPROFESCODE);
				String rupsn_schprofesname = resultSet.getString(RegUndergraProfesStuNumTable.RUPSN_SCHPROFESNAME);
				Integer rupsn_edusystem = resultSet.getInt(RegUndergraProfesStuNumTable.RUPSN_EDUSYSTEM);
				if(rupsn_edusystem == -9)
					rupsn_edusystem = null;
				Integer rupsn_gradeone = resultSet.getInt(RegUndergraProfesStuNumTable.RUPSN_GRADEONE);
				if(rupsn_gradeone == -9)
					rupsn_gradeone = null;
				Integer rupsn_gradetwo = resultSet.getInt(RegUndergraProfesStuNumTable.RUPSN_GRADETWO);
				if(rupsn_gradetwo == -9)
					rupsn_gradetwo = null;
				Integer rupsn_gradethree = resultSet.getInt(RegUndergraProfesStuNumTable.RUPSN_GRADETHREE);
				if(rupsn_gradethree == -9)
					rupsn_gradethree = null;
				Integer rupsn_gradefour = resultSet.getInt(RegUndergraProfesStuNumTable.RUPSN_GRADEFOUR);
				if(rupsn_gradefour == -9)
					rupsn_gradefour = null;
				Integer rupsn_abgradefive = resultSet.getInt(RegUndergraProfesStuNumTable.RUPSN_ABGRADEFIVE);
				if(rupsn_abgradefive == -9)
					rupsn_abgradefive = null;
				Integer rupsn_atschtotal = resultSet.getInt(RegUndergraProfesStuNumTable.RUPSN_ATSCHTOTAL);
				if(rupsn_atschtotal < 0)
					rupsn_atschtotal = null;
				Integer rupsn_minor = resultSet.getInt(RegUndergraProfesStuNumTable.RUPSN_MINOR);
				if(rupsn_minor == -9)
					rupsn_minor = null;
				Integer rupsn_doubledegree = resultSet.getInt(RegUndergraProfesStuNumTable.RUPSN_DOUBLEDEGREE);
				if(rupsn_doubledegree == -9)
					rupsn_doubledegree = null;
				Integer rupsn_intonumber = resultSet.getInt(RegUndergraProfesStuNumTable.RUPSN_INTONUMBER);
				if(rupsn_intonumber == -9)
					rupsn_intonumber = null;
				Integer rupsn_outnumber = resultSet.getInt(RegUndergraProfesStuNumTable.RUPSN_OUTNUMBER);
				if(rupsn_outnumber == -9)
					rupsn_outnumber = null;
				int rupsn_serialnumber = resultSet.getInt(RegUndergraProfesStuNumTable.RUPSN_SERIALNUMBER);
				Date rupsn_deadline = resultSet.getDate(RegUndergraProfesStuNumTable.RUPSN_DEADLINE);
				String rupsn_college = resultSet.getString(RegUndergraProfesStuNumTable.RUPSN_COLLEGE);
				String rupsn_comments = resultSet.getString(RegUndergraProfesStuNumTable.RUPSN_COMMENTS);
				int rupsn_isnull = resultSet.getInt(RegUndergraProfesStuNumTable.RUPSN_ISNULL);
				RegUndergraProfesStuNum regUndergraProfesStuNum = new RegUndergraProfesStuNum(rupsn_id,
						rupsn_schprofescode, rupsn_schprofesname, rupsn_edusystem, rupsn_atschtotal, rupsn_gradeone,
						rupsn_gradetwo, rupsn_gradethree, rupsn_gradefour, rupsn_abgradefive, rupsn_minor,
						rupsn_doubledegree, rupsn_intonumber, rupsn_outnumber, rupsn_serialnumber, rupsn_deadline,
						rupsn_college, rupsn_comments, rupsn_isnull);
				regUndergraProfesStuNums.add(regUndergraProfesStuNum);
			}
			return regUndergraProfesStuNums;
		} catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		} finally
		{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
	}

	public RegUndergraProfesStuNum getByMajorCode(String majorCode){
		String sql = " select * from " + RegUndergraProfesStuNumTable.TABLE_NAME
				+ " where " + RegUndergraProfesStuNumTable.RUPSN_SCHPROFESCODE + " = "+majorCode;
		
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
			List<RegUndergraProfesStuNum> regUndergraProfesStuNums = new ArrayList<RegUndergraProfesStuNum>();

			while (resultSet.next())
			{
				int rupsn_id = resultSet.getInt(RegUndergraProfesStuNumTable.RUPSN_ID);
				String rupsn_schprofescode = resultSet.getString(RegUndergraProfesStuNumTable.RUPSN_SCHPROFESCODE);
				String rupsn_schprofesname = resultSet.getString(RegUndergraProfesStuNumTable.RUPSN_SCHPROFESNAME);
				Integer rupsn_edusystem = resultSet.getInt(RegUndergraProfesStuNumTable.RUPSN_EDUSYSTEM);
				if(rupsn_edusystem == -9)
					rupsn_edusystem = null;
				Integer rupsn_gradeone = resultSet.getInt(RegUndergraProfesStuNumTable.RUPSN_GRADEONE);
				if(rupsn_gradeone == -9)
					rupsn_gradeone = null;
				Integer rupsn_gradetwo = resultSet.getInt(RegUndergraProfesStuNumTable.RUPSN_GRADETWO);
				if(rupsn_gradetwo == -9)
					rupsn_gradetwo = null;
				Integer rupsn_gradethree = resultSet.getInt(RegUndergraProfesStuNumTable.RUPSN_GRADETHREE);
				if(rupsn_gradethree == -9)
					rupsn_gradethree = null;
				Integer rupsn_gradefour = resultSet.getInt(RegUndergraProfesStuNumTable.RUPSN_GRADEFOUR);
				if(rupsn_gradefour == -9)
					rupsn_gradefour = null;
				Integer rupsn_abgradefive = resultSet.getInt(RegUndergraProfesStuNumTable.RUPSN_ABGRADEFIVE);
				if(rupsn_abgradefive == -9)
					rupsn_abgradefive = null;
				Integer rupsn_atschtotal = resultSet.getInt(RegUndergraProfesStuNumTable.RUPSN_ATSCHTOTAL);
				if(rupsn_atschtotal < 0)
					rupsn_atschtotal = null;
				Integer rupsn_minor = resultSet.getInt(RegUndergraProfesStuNumTable.RUPSN_MINOR);
				if(rupsn_minor == -9)
					rupsn_minor = null;
				Integer rupsn_doubledegree = resultSet.getInt(RegUndergraProfesStuNumTable.RUPSN_DOUBLEDEGREE);
				if(rupsn_doubledegree == -9)
					rupsn_doubledegree = null;
				Integer rupsn_intonumber = resultSet.getInt(RegUndergraProfesStuNumTable.RUPSN_INTONUMBER);
				if(rupsn_intonumber == -9)
					rupsn_intonumber = null;
				Integer rupsn_outnumber = resultSet.getInt(RegUndergraProfesStuNumTable.RUPSN_OUTNUMBER);
				if(rupsn_outnumber == -9)
					rupsn_outnumber = null;
				int rupsn_serialnumber = resultSet.getInt(RegUndergraProfesStuNumTable.RUPSN_SERIALNUMBER);
				Date rupsn_deadline = resultSet.getDate(RegUndergraProfesStuNumTable.RUPSN_DEADLINE);
				String rupsn_college = resultSet.getString(RegUndergraProfesStuNumTable.RUPSN_COLLEGE);
				String rupsn_comments = resultSet.getString(RegUndergraProfesStuNumTable.RUPSN_COMMENTS);
				int rupsn_isnull = resultSet.getInt(RegUndergraProfesStuNumTable.RUPSN_ISNULL);
				RegUndergraProfesStuNum regUndergraProfesStuNum = new RegUndergraProfesStuNum(rupsn_id,
						rupsn_schprofescode, rupsn_schprofesname, rupsn_edusystem, rupsn_atschtotal, rupsn_gradeone,
						rupsn_gradetwo, rupsn_gradethree, rupsn_gradefour, rupsn_abgradefive, rupsn_minor,
						rupsn_doubledegree, rupsn_intonumber, rupsn_outnumber, rupsn_serialnumber, rupsn_deadline,
						rupsn_college, rupsn_comments, rupsn_isnull);
				regUndergraProfesStuNums.add(regUndergraProfesStuNum);
			}
			if(regUndergraProfesStuNums.size() > 0)
			   return regUndergraProfesStuNums.get(0);
			else {
				return null;
			}
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
	public int getRegUndergraProfesStuNumCount(Map queryParams)
	{
		int count = 0;
		String sql = "select count(*) from " + RegUndergraProfesStuNumTable.TABLE_NAME + " where 1 = 1";
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
		String sql = "select max(" + RegUndergraProfesStuNumTable.RUPSN_SERIALNUMBER + ") from "
				+ RegUndergraProfesStuNumTable.TABLE_NAME;

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
	public boolean batchDelete(String[] rupsnids) throws SQLException
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

		for (String rupsnid : rupsnids)
		{
			String sql = "delete from " + RegUndergraProfesStuNumTable.TABLE_NAME + " where rupsn_id = '" + rupsnid
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
	public int addRegUndergraProfesStuNum(RegUndergraProfesStuNum regUndergraProfesStuNum)
	{
		int result = 0;

		String t_sql = "update " + RegUndergraProfesStuNumTable.TABLE_NAME + " set "
				+ RegUndergraProfesStuNumTable.RUPSN_SERIALNUMBER + " = "
				+ RegUndergraProfesStuNumTable.RUPSN_SERIALNUMBER + " +1 where "
				+ RegUndergraProfesStuNumTable.RUPSN_SERIALNUMBER + ">=?";
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
			t_pstmt.setInt(1, regUndergraProfesStuNum.getRupsn_serialnumber());
			
			//执行插入操作并更新
			result = t_pstmt.executeUpdate();
			
		} catch (SQLException e) {
			//事务回滚，不做插入操作
			e.printStackTrace();
			return 0;
		} finally {
			JdbcUtils_DBCP.release(connection2, t_pstmt, null);
		}
		String sql = "insert into " + RegUndergraProfesStuNumTable.TABLE_NAME + "("
				+ RegUndergraProfesStuNumTable.RUPSN_SCHPROFESCODE + ","
				+ RegUndergraProfesStuNumTable.RUPSN_SCHPROFESNAME + ","
				+ RegUndergraProfesStuNumTable.RUPSN_EDUSYSTEM + ","
				+ RegUndergraProfesStuNumTable.RUPSN_ATSCHTOTAL + ","
				+ RegUndergraProfesStuNumTable.RUPSN_GRADEONE + ","
				+ RegUndergraProfesStuNumTable.RUPSN_GRADETWO + ","
				+ RegUndergraProfesStuNumTable.RUPSN_GRADETHREE + ","
				+ RegUndergraProfesStuNumTable.RUPSN_GRADEFOUR + ","
				+ RegUndergraProfesStuNumTable.RUPSN_ABGRADEFIVE + ","
				+ RegUndergraProfesStuNumTable.RUPSN_MINOR + ","
				+ RegUndergraProfesStuNumTable.RUPSN_DOUBLEDEGREE + ","
				+ RegUndergraProfesStuNumTable.RUPSN_INTONUMBER + ","
				+ RegUndergraProfesStuNumTable.RUPSN_OUTNUMBER + ","
				+ RegUndergraProfesStuNumTable.RUPSN_SERIALNUMBER + "," + RegUndergraProfesStuNumTable.RUPSN_DEADLINE
				+ "," + RegUndergraProfesStuNumTable.RUPSN_COLLEGE + "," + RegUndergraProfesStuNumTable.RUPSN_COMMENTS+ "," + RegUndergraProfesStuNumTable.RUPSN_ISNULL
				+ ")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

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
			pstmt.setString(1, regUndergraProfesStuNum.getRupsn_schprofescode());
			pstmt.setString(2, regUndergraProfesStuNum.getRupsn_schprofesname());
			pstmt.setInt(3, regUndergraProfesStuNum.getRupsn_edusystem());
			pstmt.setInt(4, regUndergraProfesStuNum.getRupsn_atschtotal());
			pstmt.setInt(5, regUndergraProfesStuNum.getRupsn_gradeone());
			pstmt.setInt(6, regUndergraProfesStuNum.getRupsn_gradetwo());
			pstmt.setInt(7, regUndergraProfesStuNum.getRupsn_gradethree());
			pstmt.setInt(8, regUndergraProfesStuNum.getRupsn_gradefour());
			pstmt.setInt(9, regUndergraProfesStuNum.getRupsn_abgradefive());
			pstmt.setInt(10, regUndergraProfesStuNum.getRupsn_minor());
			pstmt.setInt(11, regUndergraProfesStuNum.getRupsn_doubledegree());
			pstmt.setInt(12, regUndergraProfesStuNum.getRupsn_intonumber());
			pstmt.setInt(13, regUndergraProfesStuNum.getRupsn_outnumber());
			pstmt.setInt(14, regUndergraProfesStuNum.getRupsn_serialnumber());
			pstmt.setDate(15, regUndergraProfesStuNum.getRupsn_deadline());
			pstmt.setString(16, regUndergraProfesStuNum.getRupsn_college());
			pstmt.setString(17, regUndergraProfesStuNum.getRupsn_comments());
			pstmt.setInt(18, regUndergraProfesStuNum.getRupsn_isnull());
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
	public int alterRegUndergraProfesStuNum(Map<String, String> valueMap, String id)
	{
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(RegUndergraProfesStuNumTable.RUPSN_ID, id);
		int result = updateRecord(RegUndergraProfesStuNumTable.TABLE_NAME, valueMap, condition);
		return result;
	}

	@Override
	public List<RegUndergraProfesStuNum> getAll()
	{
		String sql = " select * from " + RegUndergraProfesStuNumTable.TABLE_NAME + " where 1=1 " + " order by "
				+ RegUndergraProfesStuNumTable.RUPSN_ID;
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
			List<RegUndergraProfesStuNum> rupsnList = new ArrayList<RegUndergraProfesStuNum>();
			while (resultSet.next())
			{
				int rupsn_id = resultSet.getInt(RegUndergraProfesStuNumTable.RUPSN_ID);
				String rupsn_schprofescode = resultSet.getString(RegUndergraProfesStuNumTable.RUPSN_SCHPROFESCODE);
				String rupsn_schprofesname = resultSet.getString(RegUndergraProfesStuNumTable.RUPSN_SCHPROFESNAME);
				Integer rupsn_edusystem = resultSet.getInt(RegUndergraProfesStuNumTable.RUPSN_EDUSYSTEM);
				if(rupsn_edusystem == -9)
					rupsn_edusystem = null;
				Integer rupsn_gradeone = resultSet.getInt(RegUndergraProfesStuNumTable.RUPSN_GRADEONE);
				if(rupsn_gradeone == -9)
					rupsn_gradeone = null;
				Integer rupsn_gradetwo = resultSet.getInt(RegUndergraProfesStuNumTable.RUPSN_GRADETWO);
				if(rupsn_gradetwo == -9)
					rupsn_gradetwo = null;
				Integer rupsn_gradethree = resultSet.getInt(RegUndergraProfesStuNumTable.RUPSN_GRADETHREE);
				if(rupsn_gradethree == -9)
					rupsn_gradethree = null;
				Integer rupsn_gradefour = resultSet.getInt(RegUndergraProfesStuNumTable.RUPSN_GRADEFOUR);
				if(rupsn_gradefour == -9)
					rupsn_gradefour = null;
				Integer rupsn_abgradefive = resultSet.getInt(RegUndergraProfesStuNumTable.RUPSN_ABGRADEFIVE);
				if(rupsn_abgradefive == -9)
					rupsn_abgradefive = null;
				Integer rupsn_atschtotal = resultSet.getInt(RegUndergraProfesStuNumTable.RUPSN_ATSCHTOTAL);
				if(rupsn_atschtotal < 0)
					rupsn_atschtotal = null;
				Integer rupsn_minor = resultSet.getInt(RegUndergraProfesStuNumTable.RUPSN_MINOR);
				if(rupsn_minor == -9)
					rupsn_minor = null;
				Integer rupsn_doubledegree = resultSet.getInt(RegUndergraProfesStuNumTable.RUPSN_DOUBLEDEGREE);
				if(rupsn_doubledegree == -9)
					rupsn_doubledegree = null;
				Integer rupsn_intonumber = resultSet.getInt(RegUndergraProfesStuNumTable.RUPSN_INTONUMBER);
				if(rupsn_intonumber == -9)
					rupsn_intonumber = null;
				Integer rupsn_outnumber = resultSet.getInt(RegUndergraProfesStuNumTable.RUPSN_OUTNUMBER);
				if(rupsn_outnumber == -9)
					rupsn_outnumber = null;
				int rupsn_serialnumber = resultSet.getInt(RegUndergraProfesStuNumTable.RUPSN_SERIALNUMBER);
				Date rupsn_deadline = resultSet.getDate(RegUndergraProfesStuNumTable.RUPSN_DEADLINE);
				String rupsn_college = resultSet.getString(RegUndergraProfesStuNumTable.RUPSN_COLLEGE);
				String rupsn_comments = resultSet.getString(RegUndergraProfesStuNumTable.RUPSN_COMMENTS);
				int rupsn_isnull = resultSet.getInt(RegUndergraProfesStuNumTable.RUPSN_ISNULL);
				RegUndergraProfesStuNum regUndergraProfesStuNum = new RegUndergraProfesStuNum(rupsn_id,
						rupsn_schprofescode, rupsn_schprofesname, rupsn_edusystem, rupsn_atschtotal, rupsn_gradeone,
						rupsn_gradetwo, rupsn_gradethree, rupsn_gradefour, rupsn_abgradefive, rupsn_minor,
						rupsn_doubledegree, rupsn_intonumber, rupsn_outnumber, rupsn_serialnumber, rupsn_deadline,
						rupsn_college, rupsn_comments, rupsn_isnull);
				rupsnList.add(regUndergraProfesStuNum);
			}
			return rupsnList;
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
		String sql = "delete from " + RegUndergraProfesStuNumTable.TABLE_NAME + " where "
				+ RegUndergraProfesStuNumTable.RUPSN_COLLEGE + " = '" + college + "'" + " and rupsn_deadline is null ";
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
