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

import cn.edu.xmu.dao.HighLevelTalentDao;
import cn.edu.xmu.entity.HighLevelTalent;
import cn.edu.xmu.table.FullTimeTeacherInfoTable;
import cn.edu.xmu.table.HighLevelTalentTable;
import cn.edu.xmu.table.SchActUseClassroomTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;

/**
 * 
 * @author xiaoping 表3-4-1 高层次人才(时点) date 2015-7-3
 *
 */
public class HighLevelTalentDaoImpl extends BaseDaoImpl<HighLevelTalent>implements HighLevelTalentDao
{

	@Override
	public List<HighLevelTalent> getHighLevelTalents(int start, int end, String sortStr, String orderStr,
			Map queryParams, Date deadline)
	{
		String sql = " select tmp.* from ( " + " select * from " + HighLevelTalentTable.TABLE_NAME + " where 1=1 ";
		if (deadline != null)
		{
			sql += String.format("and " + HighLevelTalentTable.HLT_DEADLINE + " like  '%s%%' ", deadline);
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
			List<HighLevelTalent> highLevelTalents = new ArrayList<HighLevelTalent>();

			while (resultSet.next())
			{
				Date temp = Date.valueOf("1800-1-1");
				int hlt_id = resultSet.getInt(HighLevelTalentTable.HLT_ID);
				String hlt_name = resultSet.getString(HighLevelTalentTable.HLT_NAME);
				String hlt_worknumber = resultSet.getString(HighLevelTalentTable.HLT_WORKNUMBER);
				String hlt_type = resultSet.getString(HighLevelTalentTable.HLT_TYPE);
				String hlt_researchdirection = resultSet.getString(HighLevelTalentTable.HLT_RESEARCHDIRECTION);
				Date hlt_acquisitiondate = resultSet.getDate(HighLevelTalentTable.HLT_ACQUISITIONDATE);
				if(temp.equals(hlt_acquisitiondate))
					hlt_acquisitiondate = null;
				int hlt_serialnumber = resultSet.getInt(HighLevelTalentTable.HLT_SERIALNUMBER);
				Date hlt_deadline = resultSet.getDate(HighLevelTalentTable.HLT_DEADLINE);
				String hlt_college = resultSet.getString(HighLevelTalentTable.HLT_COLLEGE);
				String hlt_comments = resultSet.getString(HighLevelTalentTable.HLT_COMMENTS);
				int hlt_isnull = resultSet.getInt(HighLevelTalentTable.HLT_ISNULL);
				HighLevelTalent highLevelTalent = new HighLevelTalent(hlt_id, hlt_name, hlt_worknumber, hlt_type,
						hlt_researchdirection, hlt_acquisitiondate, hlt_serialnumber, hlt_deadline, hlt_college,
						hlt_comments, hlt_isnull);

				highLevelTalents.add(highLevelTalent);
			}
			return highLevelTalents;
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
	public int getHighLevelTalentCount(Map queryParams)
	{
		int count = 0;
		String sql = "select count(*) from " + HighLevelTalentTable.TABLE_NAME + " where 1 = 1";
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
		String sql = "select max(" + HighLevelTalentTable.HLT_SERIALNUMBER + ") from "
				+ HighLevelTalentTable.TABLE_NAME;

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
	public boolean batchDelete(String[] hltids) throws SQLException
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

		for (String hltid : hltids)
		{
			String sql = "delete from " + HighLevelTalentTable.TABLE_NAME + " where hlt_id = '" + hltid + "'";
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
	public int addHighLevelTalent(HighLevelTalent highLevelTalent)
	{
		int result = 0;

		String t_sql = "update " + HighLevelTalentTable.TABLE_NAME + " set "
				+ HighLevelTalentTable.HLT_SERIALNUMBER + " = "
				+ HighLevelTalentTable.HLT_SERIALNUMBER + " +1 where "
				+ HighLevelTalentTable.HLT_SERIALNUMBER + ">=?";
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
			t_pstmt.setInt(1, highLevelTalent.getHlt_serialnumber());
			
			//执行插入操作并更新
			result = t_pstmt.executeUpdate();
			
		} catch (SQLException e) {
			//事务回滚，不做插入操作
			e.printStackTrace();
			return 0;
		} finally {
			JdbcUtils_DBCP.release(connection2, t_pstmt, null);
		}
		String sql = "insert into " + HighLevelTalentTable.TABLE_NAME + "(" + HighLevelTalentTable.HLT_NAME + ","
				+ HighLevelTalentTable.HLT_WORKNUMBER + "," + HighLevelTalentTable.HLT_TYPE + ","
				+ HighLevelTalentTable.HLT_RESEARCHDIRECTION + "," + HighLevelTalentTable.HLT_ACQUISITIONDATE + ","
				+ HighLevelTalentTable.HLT_SERIALNUMBER + "," + HighLevelTalentTable.HLT_DEADLINE + ","
				+ HighLevelTalentTable.HLT_COLLEGE + "," + HighLevelTalentTable.HLT_COMMENTS +","+HighLevelTalentTable.HLT_ISNULL
				+ ")values(?,?,?,?,?,?,?,?,?,?)";

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
			pstmt.setString(1, highLevelTalent.getHlt_name());
			pstmt.setString(2, highLevelTalent.getHlt_worknumber());
			pstmt.setString(3, highLevelTalent.getHlt_type());
			pstmt.setString(4, highLevelTalent.getHlt_researchdirection());
			pstmt.setDate(5, highLevelTalent.getHlt_acquisitiondate());
			pstmt.setInt(6, highLevelTalent.getHlt_serialnumber());
			pstmt.setDate(7, highLevelTalent.getHlt_deadline());
			pstmt.setString(8, highLevelTalent.getHlt_college());
			pstmt.setString(9, highLevelTalent.getHlt_comments());
			pstmt.setInt(10, highLevelTalent.getHlt_isnull());
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
	public int alterHighLevelTalent(Map<String, String> valueMap, String id)
	{
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(HighLevelTalentTable.HLT_ID, id);
		int result = updateRecord(HighLevelTalentTable.TABLE_NAME, valueMap, condition);
		return result;
	}

	@Override
	public List<HighLevelTalent> getAll()
	{
		String sql = " select * from " + HighLevelTalentTable.TABLE_NAME + " where 1=1 " + " order by "
				+ HighLevelTalentTable.HLT_ID;
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
			List<HighLevelTalent> hltList = new ArrayList<HighLevelTalent>();
			while (resultSet.next())
			{
				Date temp = Date.valueOf("1800-1-1");
				int hlt_id = resultSet.getInt(HighLevelTalentTable.HLT_ID);
				String hlt_name = resultSet.getString(HighLevelTalentTable.HLT_NAME);
				String hlt_worknumber = resultSet.getString(HighLevelTalentTable.HLT_WORKNUMBER);
				String hlt_type = resultSet.getString(HighLevelTalentTable.HLT_TYPE);
				String hlt_researchdirection = resultSet.getString(HighLevelTalentTable.HLT_RESEARCHDIRECTION);
				Date hlt_acquisitiondate = resultSet.getDate(HighLevelTalentTable.HLT_ACQUISITIONDATE);
				if(temp.equals(hlt_acquisitiondate))
					hlt_acquisitiondate = null;
				int hlt_serialnumber = resultSet.getInt(HighLevelTalentTable.HLT_SERIALNUMBER);
				Date hlt_deadline = resultSet.getDate(HighLevelTalentTable.HLT_DEADLINE);
				String hlt_college = resultSet.getString(HighLevelTalentTable.HLT_COLLEGE);
				String hlt_comments = resultSet.getString(HighLevelTalentTable.HLT_COMMENTS);
				int hlt_isnull = resultSet.getInt(HighLevelTalentTable.HLT_ISNULL);
				HighLevelTalent highLevelTalent = new HighLevelTalent(hlt_id, hlt_name, hlt_worknumber, hlt_type,
						hlt_researchdirection, hlt_acquisitiondate, hlt_serialnumber, hlt_deadline, hlt_college,
						hlt_comments, hlt_isnull);

				hltList.add(highLevelTalent);
			}
			return hltList;
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
		String sql = "delete from " + HighLevelTalentTable.TABLE_NAME + " where "
				+ HighLevelTalentTable.HLT_COLLEGE + " = '" + college + "'" + " and hlt_deadline is null ";
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
