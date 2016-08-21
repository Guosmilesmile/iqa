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

import cn.edu.xmu.dao.SchActUseClassroomDao;
import cn.edu.xmu.entity.SchActUseClassroom;
import cn.edu.xmu.table.SchActUseClassroomTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;

/**
 * 
 * @author xiaoping 数据报表2-3-1全校性实际使用的教室（时点） date 2015-7-6
 *
 */
public class SchActUseClassroomDaoImpl extends BaseDaoImpl<SchActUseClassroom>implements SchActUseClassroomDao
{

	@Override
	public List<SchActUseClassroom> getSchActUseClassrooms(int start, int end, String sortStr, String orderStr,
			Map queryParams, Date deadline)
	{
		String sql = " select tmp.* from ( " + " select * from " + SchActUseClassroomTable.TABLE_NAME + " where 1=1 ";
		if (deadline != null)
		{
			sql += String.format("and " + SchActUseClassroomTable.SAUC_DEADLINE + " like  '%s%%' ", deadline);
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
		
		if (sortStr =="nope") {
			
		}else {
			sql += " order by " + sortStr + " " + orderStr + " ) tmp limit " + start + " ," + end;

		}


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
			List<SchActUseClassroom> schActUseClassrooms = new ArrayList<SchActUseClassroom>();

			while (resultSet.next())
			{
				int sauc_id = resultSet.getInt(SchActUseClassroomTable.SAUC_ID);
				String sauc_region = resultSet.getString(SchActUseClassroomTable.SAUC_REGION);
				String sauc_site = resultSet.getString(SchActUseClassroomTable.SAUC_SITE);
				Integer sauc_subtotalroom = resultSet.getInt(SchActUseClassroomTable.SAUC_SUBTOTALROOM);
				Integer sauc_subtotalseat = resultSet.getInt(SchActUseClassroomTable.SAUC_SUBTOTALSEAT);
				Integer sauc_multiroom = resultSet.getInt(SchActUseClassroomTable.SAUC_MULTIROOM);
				Integer sauc_multiseat = resultSet.getInt(SchActUseClassroomTable.SAUC_MULTISEAT);
				Integer sauc_regularroom = resultSet.getInt(SchActUseClassroomTable.SAUC_REGULARROOM);
				Integer sauc_regularseat = resultSet.getInt(SchActUseClassroomTable.SAUC_REGULARSEAT);
				Integer sauc_computerroom = resultSet.getInt(SchActUseClassroomTable.SAUC_COMPUTERROOM);
				Integer sauc_computerseat = resultSet.getInt(SchActUseClassroomTable.SAUC_COMPUTERSEAT);
				Integer sauc_voiceroom = resultSet.getInt(SchActUseClassroomTable.SAUC_VOICEROOM);
				Integer sauc_voiceseat = resultSet.getInt(SchActUseClassroomTable.SAUC_VOICESEAT);
				Integer sauc_otherroom = resultSet.getInt(SchActUseClassroomTable.SAUC_OTHERROOM);
				Integer sauc_otherseat = resultSet.getInt(SchActUseClassroomTable.SAUC_OTHERSEAT);
				if (sauc_subtotalroom < 0)
					sauc_subtotalroom = null;
				if (sauc_subtotalseat < 0)
					sauc_subtotalseat = null;
				if (sauc_multiroom == -9)
					sauc_multiroom = null;
				if (sauc_multiseat == -9)
					sauc_multiseat = null;
				if (sauc_regularroom == -9)
					sauc_regularroom = null;
				if (sauc_regularseat == -9)
					sauc_regularseat = null;
				if (sauc_computerroom == -9)
					sauc_computerroom = null;
				if (sauc_computerseat == -9)
					sauc_computerseat = null;
				if (sauc_voiceroom == -9)
					sauc_voiceroom = null;
				if (sauc_voiceseat == -9)
					sauc_voiceseat = null;
				if (sauc_otherroom == -9)
					sauc_otherroom = null;
				if (sauc_otherseat == -9)
					sauc_otherseat = null;
				int sauc_serialnumber = resultSet.getInt(SchActUseClassroomTable.SAUC_SERIALNUMBER);
				Date sauc_deadline = resultSet.getDate(SchActUseClassroomTable.SAUC_DEADLINE);
				String sauc_college = resultSet.getString(SchActUseClassroomTable.SAUC_COLLEGE);
				String sauc_comments = resultSet.getString(SchActUseClassroomTable.SAUC_COMMENTS);
				int sauc_isnull = resultSet.getInt(SchActUseClassroomTable.SAUC_ISNULL);
				SchActUseClassroom schActUseClassroom = new SchActUseClassroom(sauc_id, sauc_region, sauc_site,
						sauc_subtotalroom, sauc_subtotalseat, sauc_multiroom, sauc_multiseat, sauc_regularroom,
						sauc_regularseat, sauc_computerroom, sauc_computerseat, sauc_voiceroom, sauc_voiceseat,
						sauc_otherroom, sauc_otherseat, sauc_serialnumber, sauc_deadline, sauc_college, sauc_comments,
						sauc_isnull);
				if (sauc_region.equals("合计"))
				{
					schActUseClassrooms.add(0, schActUseClassroom);
					continue;
				}
				schActUseClassrooms.add(schActUseClassroom);
			}
			return schActUseClassrooms;
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
	public int getSchActUseClassroomCount(Map queryParams)
	{
		int count = 0;
		String sql = "select count(*) from " + SchActUseClassroomTable.TABLE_NAME + " where 1 = 1";
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
		String sql = "select max(" + SchActUseClassroomTable.SAUC_SERIALNUMBER + ") from "
				+ SchActUseClassroomTable.TABLE_NAME;

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
	public boolean batchDelete(String[] saucids) throws SQLException
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

		for (String saucid : saucids)
		{
			String sql = "delete from " + SchActUseClassroomTable.TABLE_NAME + " where sauc_id = '" + saucid + "'";
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
	public int addSchActUseClassroom(SchActUseClassroom schActUseClassroom)
	{
		int result = 0;

		String t_sql = "update " + SchActUseClassroomTable.TABLE_NAME + " set "
				+ SchActUseClassroomTable.SAUC_SERIALNUMBER + " = " + SchActUseClassroomTable.SAUC_SERIALNUMBER
				+ " +1 where " + SchActUseClassroomTable.SAUC_SERIALNUMBER + ">=?";
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
			t_pstmt.setInt(1, schActUseClassroom.getSauc_serialnumber());

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
		String sql = "insert into " + SchActUseClassroomTable.TABLE_NAME + "(" + SchActUseClassroomTable.SAUC_REGION
				+ "," + SchActUseClassroomTable.SAUC_SITE + "," + SchActUseClassroomTable.SAUC_SUBTOTALROOM + ","
				+ SchActUseClassroomTable.SAUC_SUBTOTALSEAT + "," + SchActUseClassroomTable.SAUC_MULTIROOM + ","
				+ SchActUseClassroomTable.SAUC_MULTISEAT + "," + SchActUseClassroomTable.SAUC_REGULARROOM + ","
				+ SchActUseClassroomTable.SAUC_REGULARSEAT + "," + SchActUseClassroomTable.SAUC_COMPUTERROOM + ","
				+ SchActUseClassroomTable.SAUC_COMPUTERSEAT + "," + SchActUseClassroomTable.SAUC_VOICEROOM + ","
				+ SchActUseClassroomTable.SAUC_VOICESEAT + "," + SchActUseClassroomTable.SAUC_OTHERROOM + ","
				+ SchActUseClassroomTable.SAUC_OTHERSEAT + "," + SchActUseClassroomTable.SAUC_SERIALNUMBER + ","
				+ SchActUseClassroomTable.SAUC_DEADLINE + "," + SchActUseClassroomTable.SAUC_COLLEGE + ","
				+ SchActUseClassroomTable.SAUC_COMMENTS+"," + SchActUseClassroomTable.SAUC_ISNULL + ")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

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
			pstmt.setString(1, schActUseClassroom.getSauc_region());
			pstmt.setString(2, schActUseClassroom.getSauc_site());
			pstmt.setInt(3, schActUseClassroom.getSauc_subtotalroom());
			pstmt.setInt(4, schActUseClassroom.getSauc_subtotalseat());
			pstmt.setInt(5, schActUseClassroom.getSauc_multiroom());
			pstmt.setInt(6, schActUseClassroom.getSauc_multiseat());
			pstmt.setInt(7, schActUseClassroom.getSauc_regularroom());
			pstmt.setInt(8, schActUseClassroom.getSauc_regularseat());
			pstmt.setInt(9, schActUseClassroom.getSauc_computerroom());
			pstmt.setInt(10, schActUseClassroom.getSauc_computerseat());
			pstmt.setInt(11, schActUseClassroom.getSauc_voiceroom());
			pstmt.setInt(12, schActUseClassroom.getSauc_voiceseat());
			pstmt.setInt(13, schActUseClassroom.getSauc_otherroom());
			pstmt.setInt(14, schActUseClassroom.getSauc_otherseat());
			pstmt.setInt(15, schActUseClassroom.getSauc_serialnumber());
			pstmt.setDate(16, schActUseClassroom.getSauc_deadline());
			pstmt.setString(17, schActUseClassroom.getSauc_college());
			pstmt.setString(18, schActUseClassroom.getSauc_comments());
			pstmt.setInt(19, schActUseClassroom.getSauc_isnull());
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
	public int alterSchActUseClassroom(Map<String, String> valueMap, String id)
	{
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(SchActUseClassroomTable.SAUC_ID, id);
		int result = updateRecord(SchActUseClassroomTable.TABLE_NAME, valueMap, condition);
		return result;
	}

	@Override
	public List<SchActUseClassroom> getEqualSchActUseClassroom(Map<String, String> equalParams,
			Map<String, String> notEqualParams)
	{
		String sql = " select * from " + SchActUseClassroomTable.TABLE_NAME + " where 1=1";
		if (equalParams != null && equalParams.keySet().size() != 0)
		{
			for (Object object : equalParams.keySet())
			{
				String key = object.toString();
				String value = equalParams.get(key).toString();
				sql += String.format(" and %s = '%s'", key, value);
			}
		}
		if (notEqualParams != null && notEqualParams.keySet().size() != 0)
		{
			for (Object object : notEqualParams.keySet())
			{
				String key = object.toString();
				String value = notEqualParams.get(key).toString();
				sql += String.format(" and %s != '%s'", key, value);
			}
		}
		sql += " and " + SchActUseClassroomTable.SAUC_DEADLINE + " is null";
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
			List<SchActUseClassroom> schActUseClassrooms = new ArrayList<SchActUseClassroom>();

			while (resultSet.next())
			{
				int sauc_id = resultSet.getInt(SchActUseClassroomTable.SAUC_ID);
				String sauc_region = resultSet.getString(SchActUseClassroomTable.SAUC_REGION);
				String sauc_site = resultSet.getString(SchActUseClassroomTable.SAUC_SITE);
				int sauc_subtotalroom = resultSet.getInt(SchActUseClassroomTable.SAUC_SUBTOTALROOM);
				int sauc_subtotalseat = resultSet.getInt(SchActUseClassroomTable.SAUC_SUBTOTALSEAT);
				int sauc_multiroom = resultSet.getInt(SchActUseClassroomTable.SAUC_MULTIROOM);
				int sauc_multiseat = resultSet.getInt(SchActUseClassroomTable.SAUC_MULTISEAT);
				int sauc_regularroom = resultSet.getInt(SchActUseClassroomTable.SAUC_REGULARROOM);
				int sauc_regularseat = resultSet.getInt(SchActUseClassroomTable.SAUC_REGULARSEAT);
				int sauc_computerroom = resultSet.getInt(SchActUseClassroomTable.SAUC_COMPUTERROOM);
				int sauc_computerseat = resultSet.getInt(SchActUseClassroomTable.SAUC_COMPUTERSEAT);
				int sauc_voiceroom = resultSet.getInt(SchActUseClassroomTable.SAUC_VOICEROOM);
				int sauc_voiceseat = resultSet.getInt(SchActUseClassroomTable.SAUC_VOICESEAT);
				int sauc_otherroom = resultSet.getInt(SchActUseClassroomTable.SAUC_OTHERROOM);
				int sauc_otherseat = resultSet.getInt(SchActUseClassroomTable.SAUC_OTHERSEAT);
				int sauc_serialnumber = resultSet.getInt(SchActUseClassroomTable.SAUC_SERIALNUMBER);
				Date sauc_deadline = resultSet.getDate(SchActUseClassroomTable.SAUC_DEADLINE);
				String sauc_college = resultSet.getString(SchActUseClassroomTable.SAUC_COLLEGE);
				String sauc_comments = resultSet.getString(SchActUseClassroomTable.SAUC_COMMENTS);
				int sauc_isnull = resultSet.getInt(SchActUseClassroomTable.SAUC_ISNULL);
				SchActUseClassroom schActUseClassroom = new SchActUseClassroom(sauc_id, sauc_region, sauc_site,
						sauc_subtotalroom, sauc_subtotalseat, sauc_multiroom, sauc_multiseat, sauc_regularroom,
						sauc_regularseat, sauc_computerroom, sauc_computerseat, sauc_voiceroom, sauc_voiceseat,
						sauc_otherroom, sauc_otherseat, sauc_serialnumber, sauc_deadline, sauc_college, sauc_comments,sauc_isnull);
				schActUseClassrooms.add(schActUseClassroom);
			}
			return schActUseClassrooms;
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
	public List<SchActUseClassroom> getAll()
	{
		String sql = " select * from " + SchActUseClassroomTable.TABLE_NAME + " where 1=1 " + " order by "
				+ SchActUseClassroomTable.SAUC_REGION + "," + SchActUseClassroomTable.SAUC_ID;
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
			List<SchActUseClassroom> saucList = new ArrayList<SchActUseClassroom>();
			String region = "";
			int subtotalI = -1;// 暂存"小计"的位置
			int tempI = -1;//
			int i = -1;// 当前saucList的位置
			while (resultSet.next())
			{
				int sauc_id = resultSet.getInt(SchActUseClassroomTable.SAUC_ID);
				String sauc_region = resultSet.getString(SchActUseClassroomTable.SAUC_REGION);
				String sauc_site = resultSet.getString(SchActUseClassroomTable.SAUC_SITE);
				Integer sauc_subtotalroom = resultSet.getInt(SchActUseClassroomTable.SAUC_SUBTOTALROOM);
				Integer sauc_subtotalseat = resultSet.getInt(SchActUseClassroomTable.SAUC_SUBTOTALSEAT);
				Integer sauc_multiroom = resultSet.getInt(SchActUseClassroomTable.SAUC_MULTIROOM);
				Integer sauc_multiseat = resultSet.getInt(SchActUseClassroomTable.SAUC_MULTISEAT);
				Integer sauc_regularroom = resultSet.getInt(SchActUseClassroomTable.SAUC_REGULARROOM);
				Integer sauc_regularseat = resultSet.getInt(SchActUseClassroomTable.SAUC_REGULARSEAT);
				Integer sauc_computerroom = resultSet.getInt(SchActUseClassroomTable.SAUC_COMPUTERROOM);
				Integer sauc_computerseat = resultSet.getInt(SchActUseClassroomTable.SAUC_COMPUTERSEAT);
				Integer sauc_voiceroom = resultSet.getInt(SchActUseClassroomTable.SAUC_VOICEROOM);
				Integer sauc_voiceseat = resultSet.getInt(SchActUseClassroomTable.SAUC_VOICESEAT);
				Integer sauc_otherroom = resultSet.getInt(SchActUseClassroomTable.SAUC_OTHERROOM);
				Integer sauc_otherseat = resultSet.getInt(SchActUseClassroomTable.SAUC_OTHERSEAT);
				if (sauc_subtotalroom < 0)
					sauc_subtotalroom = null;
				if (sauc_subtotalseat < 0)
					sauc_subtotalseat = null;
				if (sauc_multiroom == -9)
					sauc_multiroom = null;
				if (sauc_multiseat == -9)
					sauc_multiseat = null;
				if (sauc_regularroom == -9)
					sauc_regularroom = null;
				if (sauc_regularseat == -9)
					sauc_regularseat = null;
				if (sauc_computerroom == -9)
					sauc_computerroom = null;
				if (sauc_computerseat == -9)
					sauc_computerseat = null;
				if (sauc_voiceroom == -9)
					sauc_voiceroom = null;
				if (sauc_voiceseat == -9)
					sauc_voiceseat = null;
				if (sauc_otherroom == -9)
					sauc_otherroom = null;
				if (sauc_otherseat == -9)
					sauc_otherseat = null;
				int sauc_serialnumber = resultSet.getInt(SchActUseClassroomTable.SAUC_SERIALNUMBER);
				Date sauc_deadline = resultSet.getDate(SchActUseClassroomTable.SAUC_DEADLINE);
				String sauc_college = resultSet.getString(SchActUseClassroomTable.SAUC_COLLEGE);
				String sauc_comments = resultSet.getString(SchActUseClassroomTable.SAUC_COMMENTS);
				int sauc_isnull = resultSet.getInt(SchActUseClassroomTable.SAUC_ISNULL);
				SchActUseClassroom schActUseClassroom = new SchActUseClassroom(sauc_id, sauc_region, sauc_site,
						sauc_subtotalroom, sauc_subtotalseat, sauc_multiroom, sauc_multiseat, sauc_regularroom,
						sauc_regularseat, sauc_computerroom, sauc_computerseat, sauc_voiceroom, sauc_voiceseat,
						sauc_otherroom, sauc_otherseat, sauc_serialnumber, sauc_deadline, sauc_college, sauc_comments,
						sauc_isnull);
				i++;
				// 将"合计"移到第一个
				if (sauc_region.equals("合计"))
				{
					saucList.add(0, schActUseClassroom);
					continue;
				}
				if (sauc_site.equals("小计"))
				{
					subtotalI = i;
				}
				// 将小计移到校区的最后一个
				if (subtotalI != -1 && subtotalI != i && !"".equals(region) && !region.equals(sauc_region))
				{
					SchActUseClassroom subtotal = saucList.get(subtotalI);
					saucList.remove(subtotalI);
					saucList.add(subtotal);
				}
				saucList.add(schActUseClassroom);
				region = sauc_region;
			}
			// 将小计移到校区的最后一个
			if (subtotalI != -1 && subtotalI != i)
			{
				SchActUseClassroom subtotal = saucList.get(subtotalI);
				saucList.remove(subtotalI);
				saucList.add(subtotal);
			}
			return saucList;
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
		String sql = "delete from " + SchActUseClassroomTable.TABLE_NAME + " where "
				+ SchActUseClassroomTable.SAUC_COLLEGE + " = '" + college + "'" + " and sauc_deadline is null ";
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
