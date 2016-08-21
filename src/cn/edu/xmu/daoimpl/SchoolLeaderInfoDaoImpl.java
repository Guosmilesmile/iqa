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

import cn.edu.xmu.dao.SchoolLeaderInfoDao;
import cn.edu.xmu.entity.SchoolLeaderInfo;
import cn.edu.xmu.table.SchoolLeaderInfoTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;

/**
 * 
 * @author xiaoping 表3-2 校领导基本信息(时点) date 2015-7-3
 *
 */
public class SchoolLeaderInfoDaoImpl extends BaseDaoImpl<SchoolLeaderInfo>implements SchoolLeaderInfoDao
{

	@Override
	public List<SchoolLeaderInfo> getSchoolLeaderInfos(int start, int end, String sortStr, String orderStr,
			Map queryParams, Date deadline)
	{
		String sql = " select tmp.* from ( " + " select * from " + SchoolLeaderInfoTable.TABLE_NAME + " where 1=1 ";
		if (deadline != null)
		{
			sql += String.format("and " + SchoolLeaderInfoTable.SLI_DEADLINE + " like  '%s%%' ", deadline);
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
			List<SchoolLeaderInfo> schoolLeaderInfos = new ArrayList<SchoolLeaderInfo>();

			while (resultSet.next())
			{
				Date temp = Date.valueOf("1800-1-1");
				int sli_id = resultSet.getInt(SchoolLeaderInfoTable.SLI_ID);
				Integer sli_sequencenumber = resultSet.getInt(SchoolLeaderInfoTable.SLI_SEQUENCENUMBER);
				if(sli_sequencenumber == -9)
					sli_sequencenumber = null;
				String sli_name = resultSet.getString(SchoolLeaderInfoTable.SLI_NAME);
				String sli_worknumber = resultSet.getString(SchoolLeaderInfoTable.SLI_WORKNUMBER);
				String sli_position = resultSet.getString(SchoolLeaderInfoTable.SLI_POSITION);
				String sli_gender = resultSet.getString(SchoolLeaderInfoTable.SLI_GENDER);
				Date sli_birthday = resultSet.getDate(SchoolLeaderInfoTable.SLI_BIRTHDAY);
				if(temp.equals(sli_birthday))
					sli_birthday = null;
				Date sli_inschooldate = resultSet.getDate(SchoolLeaderInfoTable.SLI_INSCHOOLDATE);
				if(temp.equals(sli_inschooldate))
					sli_inschooldate = null;
				String sli_education = resultSet.getString(SchoolLeaderInfoTable.SLI_EDUCATION);
				String sli_degree = resultSet.getString(SchoolLeaderInfoTable.SLI_DEGREE);
				String sli_professionaltitle = resultSet.getString(SchoolLeaderInfoTable.SLI_PROFESSIONALTITLE);
				String sli_responsibility = resultSet.getString(SchoolLeaderInfoTable.SLI_RESPONSIBILITY);
				String sli_studyworkresume = resultSet.getString(SchoolLeaderInfoTable.SLI_STUDYWORKRESUME);
				int sli_serialnumber = resultSet.getInt(SchoolLeaderInfoTable.SLI_SERIALNUMBER);
				Date sli_deadline = resultSet.getDate(SchoolLeaderInfoTable.SLI_DEADLINE);
				String sli_college = resultSet.getString(SchoolLeaderInfoTable.SLI_COLLEGE);
				String sli_comments = resultSet.getString(SchoolLeaderInfoTable.SLI_COMMENTS);
				int sli_isnull = resultSet.getInt(SchoolLeaderInfoTable.SLI_ISNULL);
				SchoolLeaderInfo schoolLeaderInfo = new SchoolLeaderInfo(sli_id, sli_sequencenumber, sli_name,
						sli_worknumber, sli_position, sli_gender, sli_birthday, sli_inschooldate, sli_education,
						sli_degree, sli_professionaltitle, sli_responsibility, sli_studyworkresume, sli_serialnumber,
						sli_deadline, sli_college, sli_comments, sli_isnull);

				schoolLeaderInfos.add(schoolLeaderInfo);
			}
			return schoolLeaderInfos;
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
	public int getSchoolLeaderInfoCount(Map queryParams)
	{
		int count = 0;
		String sql = "select count(*) from " + SchoolLeaderInfoTable.TABLE_NAME + " where 1 = 1";
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
		String sql = "select max(" + SchoolLeaderInfoTable.SLI_SERIALNUMBER + ") from "
				+ SchoolLeaderInfoTable.TABLE_NAME;

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
	public boolean batchDelete(String[] sliids) throws SQLException
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

		for (String sliid : sliids)
		{
			String sql = "delete from " + SchoolLeaderInfoTable.TABLE_NAME + " where sli_id = '" + sliid + "'";
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
	public int addSchoolLeaderInfo(SchoolLeaderInfo schoolLeaderInfo)
	{
		int result = 0;

		String t_sql = "update " + SchoolLeaderInfoTable.TABLE_NAME + " set "
				+ SchoolLeaderInfoTable.SLI_SERIALNUMBER + " = "
				+ SchoolLeaderInfoTable.SLI_SERIALNUMBER + " +1 where "
				+ SchoolLeaderInfoTable.SLI_SERIALNUMBER + ">=?";
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
			t_pstmt.setInt(1, schoolLeaderInfo.getSli_serialnumber());
			
			//执行插入操作并更新
			result = t_pstmt.executeUpdate();
			
		} catch (SQLException e) {
			//事务回滚，不做插入操作
			e.printStackTrace();
			return 0;
		} finally {
			JdbcUtils_DBCP.release(connection2, t_pstmt, null);
		}
		String sql = "insert into " + SchoolLeaderInfoTable.TABLE_NAME + "(" + SchoolLeaderInfoTable.SLI_SEQUENCENUMBER
				+ "," + SchoolLeaderInfoTable.SLI_NAME + "," + SchoolLeaderInfoTable.SLI_WORKNUMBER + ","
				+ SchoolLeaderInfoTable.SLI_POSITION + "," + SchoolLeaderInfoTable.SLI_GENDER + ","
				+ SchoolLeaderInfoTable.SLI_BIRTHDAY + "," + SchoolLeaderInfoTable.SLI_INSCHOOLDATE + ","
				+ SchoolLeaderInfoTable.SLI_EDUCATION + "," + SchoolLeaderInfoTable.SLI_DEGREE + ","
				+ SchoolLeaderInfoTable.SLI_PROFESSIONALTITLE + "," + SchoolLeaderInfoTable.SLI_RESPONSIBILITY + ","
				+ SchoolLeaderInfoTable.SLI_STUDYWORKRESUME + "," + SchoolLeaderInfoTable.SLI_SERIALNUMBER + ","
				+ SchoolLeaderInfoTable.SLI_DEADLINE + "," + SchoolLeaderInfoTable.SLI_COLLEGE + ","
				+ SchoolLeaderInfoTable.SLI_COMMENTS + ","+SchoolLeaderInfoTable.SLI_ISNULL+")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

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
			pstmt.setInt(1, schoolLeaderInfo.getSli_sequencenumber());
			pstmt.setString(2, schoolLeaderInfo.getSli_name());
			pstmt.setString(3, schoolLeaderInfo.getSli_worknumber());
			pstmt.setString(4, schoolLeaderInfo.getSli_position());
			pstmt.setString(5, schoolLeaderInfo.getSli_gender());
			pstmt.setDate(6, schoolLeaderInfo.getSli_birthday());
			pstmt.setDate(7, schoolLeaderInfo.getSli_inschooldate());
			pstmt.setString(8, schoolLeaderInfo.getSli_education());
			pstmt.setString(9, schoolLeaderInfo.getSli_degree());
			pstmt.setString(10, schoolLeaderInfo.getSli_professionaltitle());
			pstmt.setString(11, schoolLeaderInfo.getSli_responsibility());
			pstmt.setString(12, schoolLeaderInfo.getSli_studyworkresume());
			pstmt.setInt(13, schoolLeaderInfo.getSli_serialnumber());
			pstmt.setDate(14, schoolLeaderInfo.getSli_deadline());
			pstmt.setString(15, schoolLeaderInfo.getSli_college());
			pstmt.setString(16, schoolLeaderInfo.getSli_comments());
			pstmt.setInt(17, schoolLeaderInfo.getSli_isnull());
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
	public int alterSchoolLeaderInfo(Map<String, String> valueMap, String id)
	{
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(SchoolLeaderInfoTable.SLI_ID, id);
		int result = updateRecord(SchoolLeaderInfoTable.TABLE_NAME, valueMap, condition);
		return result;
	}

	@Override
	public int getCountByRange(String param, Date start, Date end, Map queryParams)
	{
		int count = 0;
		String sql = "select count(*) from " + SchoolLeaderInfoTable.TABLE_NAME + String.format(" where unix_timestamp( %s ) between unix_timestamp( '%s') and unix_timestamp( '%s' ) ",param,start, end);
		if (queryParams != null && queryParams.keySet().size() != 0)
		{
			for (Object object : queryParams.keySet())
			{
				String key = object.toString();
				String value = queryParams.get(key).toString();
				sql += String.format(" and %s like  '%%%s%%'", key, value);
			}
		}
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
			return -1;
		}
		System.out.println(sql);
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		try {
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			resultSet.next();
			count = resultSet.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		System.err.println(count);
		return count;
	}

	@Override
	public List<SchoolLeaderInfo> getAll()
	{
		String sql = " select * from " + SchoolLeaderInfoTable.TABLE_NAME + " where 1=1 " + " order by "
				+ SchoolLeaderInfoTable.SLI_ID;
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
			List<SchoolLeaderInfo> sliList = new ArrayList<SchoolLeaderInfo>();
			while (resultSet.next())
			{
				Date temp = Date.valueOf("1800-1-1");
				int sli_id = resultSet.getInt(SchoolLeaderInfoTable.SLI_ID);
				Integer sli_sequencenumber = resultSet.getInt(SchoolLeaderInfoTable.SLI_SEQUENCENUMBER);
				if(sli_sequencenumber == -999999)
					sli_sequencenumber = null;
				String sli_name = resultSet.getString(SchoolLeaderInfoTable.SLI_NAME);
				String sli_worknumber = resultSet.getString(SchoolLeaderInfoTable.SLI_WORKNUMBER);
				String sli_position = resultSet.getString(SchoolLeaderInfoTable.SLI_POSITION);
				String sli_gender = resultSet.getString(SchoolLeaderInfoTable.SLI_GENDER);
				Date sli_birthday = resultSet.getDate(SchoolLeaderInfoTable.SLI_BIRTHDAY);
				if(temp.equals(sli_birthday))
					sli_birthday = null;
				Date sli_inschooldate = resultSet.getDate(SchoolLeaderInfoTable.SLI_INSCHOOLDATE);
				if(temp.equals(sli_inschooldate))
					sli_inschooldate = null;
				String sli_education = resultSet.getString(SchoolLeaderInfoTable.SLI_EDUCATION);
				String sli_degree = resultSet.getString(SchoolLeaderInfoTable.SLI_DEGREE);
				String sli_professionaltitle = resultSet.getString(SchoolLeaderInfoTable.SLI_PROFESSIONALTITLE);
				String sli_responsibility = resultSet.getString(SchoolLeaderInfoTable.SLI_RESPONSIBILITY);
				String sli_studyworkresume = resultSet.getString(SchoolLeaderInfoTable.SLI_STUDYWORKRESUME);
				int sli_serialnumber = resultSet.getInt(SchoolLeaderInfoTable.SLI_SERIALNUMBER);
				Date sli_deadline = resultSet.getDate(SchoolLeaderInfoTable.SLI_DEADLINE);
				String sli_college = resultSet.getString(SchoolLeaderInfoTable.SLI_COLLEGE);
				String sli_comments = resultSet.getString(SchoolLeaderInfoTable.SLI_COMMENTS);
				int sli_isnull = resultSet.getInt(SchoolLeaderInfoTable.SLI_ISNULL);
				SchoolLeaderInfo schoolLeaderInfo = new SchoolLeaderInfo(sli_id, sli_sequencenumber, sli_name,
						sli_worknumber, sli_position, sli_gender, sli_birthday, sli_inschooldate, sli_education,
						sli_degree, sli_professionaltitle, sli_responsibility, sli_studyworkresume, sli_serialnumber,
						sli_deadline, sli_college, sli_comments, sli_isnull);

				sliList.add(schoolLeaderInfo);
			}
			return sliList;
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
		String sql = "delete from " + SchoolLeaderInfoTable.TABLE_NAME + " where "
				+ SchoolLeaderInfoTable.SLI_COLLEGE + " = '" + college + "'" + " and sli_deadline is null ";
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
