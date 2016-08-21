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

import cn.edu.xmu.dao.UndergraInResearchLabDao;
import cn.edu.xmu.entity.UndergraInResearchLab;
import cn.edu.xmu.table.UndergraInResearchLabTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;

/**
 * 
 * @author xiaoping 附表5-4-4 本科生进入科研实验室情况 date 2015-7-11
 *
 */
public class UndergraInResearchLabDaoImpl extends BaseDaoImpl<UndergraInResearchLab>implements UndergraInResearchLabDao
{

	@Override
	public List<UndergraInResearchLab> getUndergraInResearchLabs(int start, int end, String sortStr, String orderStr,
			Map queryParams, Date deadline)
	{
		String sql = " select tmp.* from ( " + " select * from " + UndergraInResearchLabTable.TABLE_NAME
				+ " where 1=1 ";
		if (deadline != null)
		{
			sql += String.format("and " + UndergraInResearchLabTable.UIRL_DEADLINE + " like  '%s%%' ", deadline);
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
			List<UndergraInResearchLab> undergraInResearchLabs = new ArrayList<UndergraInResearchLab>();

			while (resultSet.next())
			{
				int uirl_id = resultSet.getInt(UndergraInResearchLabTable.UIRL_ID);
				String uirl_institute = resultSet.getString(UndergraInResearchLabTable.UIRL_INSTITUTE);
				String uirl_major = resultSet.getString(UndergraInResearchLabTable.UIRL_MAJOR);
				String uirl_grade = resultSet.getString(UndergraInResearchLabTable.UIRL_GRADE);
				String uirl_name = resultSet.getString(UndergraInResearchLabTable.UIRL_NAME);
				Integer uirl_days = resultSet.getInt(UndergraInResearchLabTable.UIRL_DAYS);
				if (uirl_days == -9)
					uirl_days = null;
				Integer uirl_times = resultSet.getInt(UndergraInResearchLabTable.UIRL_TIMES);
				if (uirl_times == -9)
					uirl_times = null;
				Float uirl_totalhours = resultSet.getFloat(UndergraInResearchLabTable.UIRL_TOTALHOURS);
				if (uirl_totalhours == -9)
					uirl_totalhours = null;
				String uirl_laboratoryname = resultSet.getString(UndergraInResearchLabTable.UIRL_LABORATORYNAME);
				String uirl_laboratorydirector = resultSet
						.getString(UndergraInResearchLabTable.UIRL_LABORATORYDIRECTOR);
				String uirl_tutor = resultSet.getString(UndergraInResearchLabTable.UIRL_TUTOR);
				String uirl_tutortitle = resultSet.getString(UndergraInResearchLabTable.UIRL_TUTORTITLE);
				String uirl_ispartiresearchproject = resultSet
						.getString(UndergraInResearchLabTable.UIRL_ISPARTIRESEARCHPROJECT);
				String uirl_researchprojectname = resultSet
						.getString(UndergraInResearchLabTable.UIRL_RESEARCHPROJECTNAME);
				String uirl_researchprojectlevel = resultSet
						.getString(UndergraInResearchLabTable.UIRL_RESEARCHPROJECTLEVEL);
				String uirl_createprojectname = resultSet.getString(UndergraInResearchLabTable.UIRL_CREATEPROJECTNAME);
				String uirl_createprojecttype = resultSet.getString(UndergraInResearchLabTable.UIRL_CREATEPROJECTTYPE);
				String uirl_createprojectlevel = resultSet
						.getString(UndergraInResearchLabTable.UIRL_CREATEPROJECTLEVEL);
				String uirl_paper = resultSet.getString(UndergraInResearchLabTable.UIRL_PAPER);
				String uirl_patent = resultSet.getString(UndergraInResearchLabTable.UIRL_PATENT);
				String uirl_note = resultSet.getString(UndergraInResearchLabTable.UIRL_NOTE);
				int uirl_serialnumber = resultSet.getInt(UndergraInResearchLabTable.UIRL_SERIALNUMBER);
				Date uirl_deadline = resultSet.getDate(UndergraInResearchLabTable.UIRL_DEADLINE);
				String uirl_college = resultSet.getString(UndergraInResearchLabTable.UIRL_COLLEGE);
				String uirl_comments = resultSet.getString(UndergraInResearchLabTable.UIRL_COMMENTS);
				int uirl_isnull = resultSet.getInt(UndergraInResearchLabTable.UIRL_ISNULL);
				UndergraInResearchLab undergraInResearchLab = new UndergraInResearchLab(uirl_id, uirl_institute,
						uirl_major, uirl_grade, uirl_name, uirl_days, uirl_times, uirl_totalhours, uirl_laboratoryname,
						uirl_laboratorydirector, uirl_tutor, uirl_tutortitle, uirl_ispartiresearchproject,
						uirl_researchprojectname, uirl_researchprojectlevel, uirl_createprojectname,
						uirl_createprojecttype, uirl_createprojectlevel, uirl_paper, uirl_patent, uirl_note,
						uirl_serialnumber, uirl_deadline, uirl_college, uirl_comments, uirl_isnull);
				undergraInResearchLabs.add(undergraInResearchLab);
			}
			return undergraInResearchLabs;
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
	public int getUndergraInResearchLabCount(Map queryParams)
	{
		int count = 0;
		String sql = "select count(*) from " + UndergraInResearchLabTable.TABLE_NAME + " where 1 = 1";
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
		String sql = "select max(" + UndergraInResearchLabTable.UIRL_SERIALNUMBER + ") from "
				+ UndergraInResearchLabTable.TABLE_NAME;

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
	public boolean batchDelete(String[] uirlids) throws SQLException
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

		for (String uirlid : uirlids)
		{
			String sql = "delete from " + UndergraInResearchLabTable.TABLE_NAME + " where uirl_id = '" + uirlid + "'";
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
	public int addUndergraInResearchLab(UndergraInResearchLab undergraInResearchLab)
	{
		int result = 0;

		String t_sql = "update " + UndergraInResearchLabTable.TABLE_NAME + " set "
				+ UndergraInResearchLabTable.UIRL_SERIALNUMBER + " = " + UndergraInResearchLabTable.UIRL_SERIALNUMBER
				+ " +1 where " + UndergraInResearchLabTable.UIRL_SERIALNUMBER + ">=?";
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
			t_pstmt.setInt(1, undergraInResearchLab.getUirl_serialnumber());

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
		String sql = "insert into " + UndergraInResearchLabTable.TABLE_NAME + "("
				+ UndergraInResearchLabTable.UIRL_INSTITUTE + "," + UndergraInResearchLabTable.UIRL_MAJOR + ","
				+ UndergraInResearchLabTable.UIRL_GRADE + "," + UndergraInResearchLabTable.UIRL_NAME + ","
				+ UndergraInResearchLabTable.UIRL_DAYS + "," + UndergraInResearchLabTable.UIRL_TIMES + ","
				+ UndergraInResearchLabTable.UIRL_TOTALHOURS + "," + UndergraInResearchLabTable.UIRL_LABORATORYNAME
				+ "," + UndergraInResearchLabTable.UIRL_LABORATORYDIRECTOR + "," + UndergraInResearchLabTable.UIRL_TUTOR
				+ "," + UndergraInResearchLabTable.UIRL_TUTORTITLE + ","
				+ UndergraInResearchLabTable.UIRL_ISPARTIRESEARCHPROJECT + ","
				+ UndergraInResearchLabTable.UIRL_RESEARCHPROJECTNAME + ","
				+ UndergraInResearchLabTable.UIRL_RESEARCHPROJECTLEVEL + ","
				+ UndergraInResearchLabTable.UIRL_CREATEPROJECTNAME + ","
				+ UndergraInResearchLabTable.UIRL_CREATEPROJECTTYPE + ","
				+ UndergraInResearchLabTable.UIRL_CREATEPROJECTLEVEL + "," + UndergraInResearchLabTable.UIRL_PAPER + ","
				+ UndergraInResearchLabTable.UIRL_PATENT + "," + UndergraInResearchLabTable.UIRL_NOTE + ","
				+ UndergraInResearchLabTable.UIRL_SERIALNUMBER + "," + UndergraInResearchLabTable.UIRL_DEADLINE + ","
				+ UndergraInResearchLabTable.UIRL_COLLEGE + "," + UndergraInResearchLabTable.UIRL_COMMENTS+ "," + UndergraInResearchLabTable.UIRL_ISNULL
				+ ")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

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
			pstmt.setString(1, undergraInResearchLab.getUirl_institute());
			pstmt.setString(2, undergraInResearchLab.getUirl_major());
			pstmt.setString(3, undergraInResearchLab.getUirl_grade());
			pstmt.setString(4, undergraInResearchLab.getUirl_name());
			pstmt.setInt(5, undergraInResearchLab.getUirl_days());
			pstmt.setInt(6, undergraInResearchLab.getUirl_times());
			pstmt.setFloat(7, undergraInResearchLab.getUirl_totalhours());
			pstmt.setString(8, undergraInResearchLab.getUirl_laboratoryname());
			pstmt.setString(9, undergraInResearchLab.getUirl_laboratorydirector());
			pstmt.setString(10, undergraInResearchLab.getUirl_tutor());
			pstmt.setString(11, undergraInResearchLab.getUirl_tutortitle());
			pstmt.setString(12, undergraInResearchLab.getUirl_ispartiresearchproject());
			pstmt.setString(13, undergraInResearchLab.getUirl_researchprojectname());
			pstmt.setString(14, undergraInResearchLab.getUirl_researchprojectlevel());
			pstmt.setString(15, undergraInResearchLab.getUirl_createprojectname());
			pstmt.setString(16, undergraInResearchLab.getUirl_createprojecttype());
			pstmt.setString(17, undergraInResearchLab.getUirl_createprojectlevel());
			pstmt.setString(18, undergraInResearchLab.getUirl_paper());
			pstmt.setString(19, undergraInResearchLab.getUirl_patent());
			pstmt.setString(20, undergraInResearchLab.getUirl_note());
			pstmt.setInt(21, undergraInResearchLab.getUirl_serialnumber());
			pstmt.setDate(22, undergraInResearchLab.getUirl_deadline());
			pstmt.setString(23, undergraInResearchLab.getUirl_college());
			pstmt.setString(24, undergraInResearchLab.getUirl_comments());
			pstmt.setInt(25, undergraInResearchLab.getUirl_isnull());
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
	public int alterUndergraInResearchLab(Map<String, String> valueMap, String id)
	{
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(UndergraInResearchLabTable.UIRL_ID, id);
		int result = updateRecord(UndergraInResearchLabTable.TABLE_NAME, valueMap, condition);
		return result;
	}

	@Override
	public List<UndergraInResearchLab> getAll()
	{
		String sql = " select * from " + UndergraInResearchLabTable.TABLE_NAME + " where 1=1 " + " order by "
				+ UndergraInResearchLabTable.UIRL_ID;
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
			List<UndergraInResearchLab> uirlList = new ArrayList<UndergraInResearchLab>();
			while (resultSet.next())
			{
				int uirl_id = resultSet.getInt(UndergraInResearchLabTable.UIRL_ID);
				String uirl_institute = resultSet.getString(UndergraInResearchLabTable.UIRL_INSTITUTE);
				String uirl_major = resultSet.getString(UndergraInResearchLabTable.UIRL_MAJOR);
				String uirl_grade = resultSet.getString(UndergraInResearchLabTable.UIRL_GRADE);
				String uirl_name = resultSet.getString(UndergraInResearchLabTable.UIRL_NAME);
				Integer uirl_days = resultSet.getInt(UndergraInResearchLabTable.UIRL_DAYS);
				if (uirl_days == -9)
					uirl_days = null;
				Integer uirl_times = resultSet.getInt(UndergraInResearchLabTable.UIRL_TIMES);
				if (uirl_times == -9)
					uirl_times = null;
				Float uirl_totalhours = resultSet.getFloat(UndergraInResearchLabTable.UIRL_TOTALHOURS);
				if (uirl_totalhours == -9)
					uirl_totalhours = null;
				String uirl_laboratoryname = resultSet.getString(UndergraInResearchLabTable.UIRL_LABORATORYNAME);
				String uirl_laboratorydirector = resultSet
						.getString(UndergraInResearchLabTable.UIRL_LABORATORYDIRECTOR);
				String uirl_tutor = resultSet.getString(UndergraInResearchLabTable.UIRL_TUTOR);
				String uirl_tutortitle = resultSet.getString(UndergraInResearchLabTable.UIRL_TUTORTITLE);
				String uirl_ispartiresearchproject = resultSet
						.getString(UndergraInResearchLabTable.UIRL_ISPARTIRESEARCHPROJECT);
				String uirl_researchprojectname = resultSet
						.getString(UndergraInResearchLabTable.UIRL_RESEARCHPROJECTNAME);
				String uirl_researchprojectlevel = resultSet
						.getString(UndergraInResearchLabTable.UIRL_RESEARCHPROJECTLEVEL);
				String uirl_createprojectname = resultSet.getString(UndergraInResearchLabTable.UIRL_CREATEPROJECTNAME);
				String uirl_createprojecttype = resultSet.getString(UndergraInResearchLabTable.UIRL_CREATEPROJECTTYPE);
				String uirl_createprojectlevel = resultSet
						.getString(UndergraInResearchLabTable.UIRL_CREATEPROJECTLEVEL);
				String uirl_paper = resultSet.getString(UndergraInResearchLabTable.UIRL_PAPER);
				String uirl_patent = resultSet.getString(UndergraInResearchLabTable.UIRL_PATENT);
				String uirl_note = resultSet.getString(UndergraInResearchLabTable.UIRL_NOTE);
				int uirl_serialnumber = resultSet.getInt(UndergraInResearchLabTable.UIRL_SERIALNUMBER);
				Date uirl_deadline = resultSet.getDate(UndergraInResearchLabTable.UIRL_DEADLINE);
				String uirl_college = resultSet.getString(UndergraInResearchLabTable.UIRL_COLLEGE);
				String uirl_comments = resultSet.getString(UndergraInResearchLabTable.UIRL_COMMENTS);
				int uirl_isnull = resultSet.getInt(UndergraInResearchLabTable.UIRL_ISNULL);
				UndergraInResearchLab undergraInResearchLab = new UndergraInResearchLab(uirl_id, uirl_institute,
						uirl_major, uirl_grade, uirl_name, uirl_days, uirl_times, uirl_totalhours, uirl_laboratoryname,
						uirl_laboratorydirector, uirl_tutor, uirl_tutortitle, uirl_ispartiresearchproject,
						uirl_researchprojectname, uirl_researchprojectlevel, uirl_createprojectname,
						uirl_createprojecttype, uirl_createprojectlevel, uirl_paper, uirl_patent, uirl_note,
						uirl_serialnumber, uirl_deadline, uirl_college, uirl_comments, uirl_isnull);

				uirlList.add(undergraInResearchLab);
			}
			return uirlList;
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
		String sql = "delete from " + UndergraInResearchLabTable.TABLE_NAME + " where "
				+ UndergraInResearchLabTable.UIRL_COLLEGE + " = '" + college + "'" + " and uirl_deadline is null ";
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
