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

import cn.edu.xmu.dao.StudentNumberInfoDao;
import cn.edu.xmu.entity.StudentNumberInfo;
import cn.edu.xmu.table.StudentNumberInfoTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;

/**
 * 
 * @author xiaoping 数据报表6-1-1 学生数量基本情况（时点）date 2015-7-5
 *
 */
public class StudentNumberInfoDaoImpl extends BaseDaoImpl<StudentNumberInfo>implements StudentNumberInfoDao
{

	@Override
	public List<StudentNumberInfo> getStudentNumberInfos(int start, int end, String sortStr, String orderStr,
			Map queryParams, Date deadline)
	{
		String sql = " select tmp.* from ( " + " select * from " + StudentNumberInfoTable.TABLE_NAME + " where 1=1 ";
		if (deadline != null)
		{
			sql += String.format("and " + StudentNumberInfoTable.SNI_DEADLINE + " like  '%s%%' ", deadline);
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
		
		if (sortStr == "nope") {
			
		}
		else {
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
			List<StudentNumberInfo> studentNumberInfos = new ArrayList<StudentNumberInfo>();

			while (resultSet.next())
			{
				int sni_id = resultSet.getInt(StudentNumberInfoTable.SNI_ID);
				String sni_stuinfobaselink = resultSet.getString(StudentNumberInfoTable.SNI_STUINFOBASELINK);
				Integer sni_ordiundergrastu = resultSet.getInt(StudentNumberInfoTable.SNI_ORDIUNDERGRASTU);
				if (sni_ordiundergrastu == -9)
					sni_ordiundergrastu = null;
				Integer sni_countryoverseastu = resultSet.getInt(StudentNumberInfoTable.SNI_COUNTRYOVERSEASTU);
				if (sni_countryoverseastu == -9)
					sni_countryoverseastu = null;
				Integer sni_highervocationstu = resultSet.getInt(StudentNumberInfoTable.SNI_HIGHERVOCATIONSTU);
				if (sni_highervocationstu == -9)
					sni_highervocationstu = null;
				Integer sni_postgraduate = resultSet.getInt(StudentNumberInfoTable.SNI_POSTGRADUATE);
				if (sni_postgraduate == -9)
					sni_postgraduate = null;
				Integer sni_fulltimepost = resultSet.getInt(StudentNumberInfoTable.SNI_FULLTIMEPOST);
				if (sni_fulltimepost == -9)
					sni_fulltimepost = null;
				Integer sni_nofulltimepost = resultSet.getInt(StudentNumberInfoTable.SNI_NOFULLTIMEPOST);
				if (sni_nofulltimepost == -9)
					sni_nofulltimepost = null;
				Integer sni_doctorcandidate = resultSet.getInt(StudentNumberInfoTable.SNI_DOCTORCANDIDATE);
				if (sni_doctorcandidate == -9)
					sni_doctorcandidate = null;
				Integer sni_fulltimedoct = resultSet.getInt(StudentNumberInfoTable.SNI_FULLTIMEDOCT);
				if (sni_fulltimedoct == -9)
					sni_fulltimedoct = null;
				Integer sni_nofulltimedoct = resultSet.getInt(StudentNumberInfoTable.SNI_NOFULLTIMEDOCT);
				if (sni_nofulltimedoct == -9)
					sni_nofulltimedoct = null;
				Integer sni_abroadstu = resultSet.getInt(StudentNumberInfoTable.SNI_ABROADSTU);
				if (sni_abroadstu == -9)
					sni_abroadstu = null;
				Integer sni_ordipreppie = resultSet.getInt(StudentNumberInfoTable.SNI_ORDIPREPPIE);
				if (sni_ordipreppie == -9)
					sni_ordipreppie = null;
				Integer sni_advancedstu = resultSet.getInt(StudentNumberInfoTable.SNI_ADVANCEDSTU);
				if (sni_advancedstu == -9)
					sni_advancedstu = null;
				Integer sni_adultfulltimestu = resultSet.getInt(StudentNumberInfoTable.SNI_ADULTFULLTIMESTU);
				if (sni_adultfulltimestu == -9)
					sni_adultfulltimestu = null;
				Integer sni_parttimestu = resultSet.getInt(StudentNumberInfoTable.SNI_PARTTIMESTU);
				if (sni_parttimestu == -9)
					sni_parttimestu = null;
				Integer sni_correspondencestu = resultSet.getInt(StudentNumberInfoTable.SNI_CORRESPONDENCESTU);
				if (sni_correspondencestu == -9)
					sni_correspondencestu = null;
				Integer sni_networkstu = resultSet.getInt(StudentNumberInfoTable.SNI_NETWORKSTU);
				if (sni_networkstu == -9)
					sni_networkstu = null;
				Integer sni_selftaughtstu = resultSet.getInt(StudentNumberInfoTable.SNI_SELFTAUGHTSTU);
				if (sni_selftaughtstu == -9)
					sni_selftaughtstu = null;
				Integer sni_serialnumber = resultSet.getInt(StudentNumberInfoTable.SNI_SERIALNUMBER);
				Date sni_deadline = resultSet.getDate(StudentNumberInfoTable.SNI_DEADLINE);
				String sni_college = resultSet.getString(StudentNumberInfoTable.SNI_COLLEGE);
				String sni_comments = resultSet.getString(StudentNumberInfoTable.SNI_COMMENTS);
				int sni_isnull = resultSet.getInt(StudentNumberInfoTable.SNI_ISNULL);
				StudentNumberInfo studentNumberInfo = new StudentNumberInfo(sni_id, sni_stuinfobaselink,
						sni_ordiundergrastu, sni_countryoverseastu, sni_highervocationstu, sni_postgraduate,
						sni_fulltimepost, sni_nofulltimepost, sni_doctorcandidate, sni_fulltimedoct, sni_nofulltimedoct,
						sni_abroadstu, sni_ordipreppie, sni_advancedstu, sni_adultfulltimestu, sni_parttimestu,
						sni_correspondencestu, sni_networkstu, sni_selftaughtstu, sni_serialnumber, sni_deadline,
						sni_college, sni_comments, sni_isnull);

				studentNumberInfos.add(studentNumberInfo);
			}
			return studentNumberInfos;
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
	public int getStudentNumberInfoCount(Map queryParams)
	{
		int count = 0;
		String sql = "select count(*) from " + StudentNumberInfoTable.TABLE_NAME + " where 1 = 1";
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
		String sql = "select max(" + StudentNumberInfoTable.SNI_SERIALNUMBER + ") from "
				+ StudentNumberInfoTable.TABLE_NAME;

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
	public boolean batchDelete(String[] sniids) throws SQLException
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

		for (String sniid : sniids)
		{
			String sql = "delete from " + StudentNumberInfoTable.TABLE_NAME + " where sni_id = '" + sniid + "'";
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
	public int addStudentNumberInfo(StudentNumberInfo studentNumberInfo)
	{
		int result = 0;

		String t_sql = "update " + StudentNumberInfoTable.TABLE_NAME + " set " + StudentNumberInfoTable.SNI_SERIALNUMBER
				+ " = " + StudentNumberInfoTable.SNI_SERIALNUMBER + " +1 where "
				+ StudentNumberInfoTable.SNI_SERIALNUMBER + ">=?";
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
			t_pstmt.setInt(1, studentNumberInfo.getSni_serialnumber());

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
		String sql = "insert into " + StudentNumberInfoTable.TABLE_NAME + "("
				+ StudentNumberInfoTable.SNI_STUINFOBASELINK + "," + StudentNumberInfoTable.SNI_ORDIUNDERGRASTU + ","
				+ StudentNumberInfoTable.SNI_COUNTRYOVERSEASTU + "," + StudentNumberInfoTable.SNI_HIGHERVOCATIONSTU
				+ "," + StudentNumberInfoTable.SNI_POSTGRADUATE + "," + StudentNumberInfoTable.SNI_FULLTIMEPOST + ","
				+ StudentNumberInfoTable.SNI_NOFULLTIMEPOST + "," + StudentNumberInfoTable.SNI_DOCTORCANDIDATE + ","
				+ StudentNumberInfoTable.SNI_FULLTIMEDOCT + "," + StudentNumberInfoTable.SNI_NOFULLTIMEDOCT + ","
				+ StudentNumberInfoTable.SNI_ABROADSTU + "," + StudentNumberInfoTable.SNI_ORDIPREPPIE + ","
				+ StudentNumberInfoTable.SNI_ADVANCEDSTU + "," + StudentNumberInfoTable.SNI_ADULTFULLTIMESTU + ","
				+ StudentNumberInfoTable.SNI_PARTTIMESTU + "," + StudentNumberInfoTable.SNI_CORRESPONDENCESTU + ","
				+ StudentNumberInfoTable.SNI_NETWORKSTU + "," + StudentNumberInfoTable.SNI_SELFTAUGHTSTU + ","
				+ StudentNumberInfoTable.SNI_SERIALNUMBER + "," + StudentNumberInfoTable.SNI_DEADLINE + ","
				+ StudentNumberInfoTable.SNI_COLLEGE + "," + StudentNumberInfoTable.SNI_COMMENTS + ","
				+ StudentNumberInfoTable.SNI_ISNULL +")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

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
			pstmt.setString(1, studentNumberInfo.getSni_stuinfobaselink());
			pstmt.setInt(2, studentNumberInfo.getSni_ordiundergrastu());
			pstmt.setInt(3, studentNumberInfo.getSni_countryoverseastu());
			pstmt.setInt(4, studentNumberInfo.getSni_highervocationstu());
			pstmt.setInt(5, studentNumberInfo.getSni_postgraduate());
			pstmt.setInt(6, studentNumberInfo.getSni_fulltimepost());
			pstmt.setInt(7, studentNumberInfo.getSni_nofulltimepost());
			pstmt.setInt(8, studentNumberInfo.getSni_doctorcandidate());
			pstmt.setInt(9, studentNumberInfo.getSni_fulltimedoct());
			pstmt.setInt(10, studentNumberInfo.getSni_nofulltimedoct());
			pstmt.setInt(11, studentNumberInfo.getSni_abroadstu());
			pstmt.setInt(12, studentNumberInfo.getSni_ordipreppie());
			pstmt.setInt(13, studentNumberInfo.getSni_advancedstu());
			pstmt.setInt(14, studentNumberInfo.getSni_adultfulltimestu());
			pstmt.setInt(15, studentNumberInfo.getSni_parttimestu());
			pstmt.setInt(16, studentNumberInfo.getSni_correspondencestu());
			pstmt.setInt(17, studentNumberInfo.getSni_networkstu());
			pstmt.setInt(18, studentNumberInfo.getSni_selftaughtstu());
			pstmt.setInt(19, studentNumberInfo.getSni_serialnumber());
			pstmt.setDate(20, studentNumberInfo.getSni_deadline());
			pstmt.setString(21, studentNumberInfo.getSni_college());
			pstmt.setString(22, studentNumberInfo.getSni_comments());
			pstmt.setInt(23, studentNumberInfo.getSni_isnull());
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
	public int alterStudentNumberInfo(Map<String, String> valueMap, String id)
	{
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(StudentNumberInfoTable.SNI_ID, id);
		int result = updateRecord(StudentNumberInfoTable.TABLE_NAME, valueMap, condition);
		return result;
	}

	@Override
	public List<StudentNumberInfo> getAll()
	{
		String sql = " select * from " + StudentNumberInfoTable.TABLE_NAME + " where 1=1 " + " order by "
				+ StudentNumberInfoTable.SNI_ID;
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
			List<StudentNumberInfo> sniList = new ArrayList<StudentNumberInfo>();
			while (resultSet.next())
			{
				int sni_id = resultSet.getInt(StudentNumberInfoTable.SNI_ID);
				String sni_stuinfobaselink = resultSet.getString(StudentNumberInfoTable.SNI_STUINFOBASELINK);
				Integer sni_ordiundergrastu = resultSet.getInt(StudentNumberInfoTable.SNI_ORDIUNDERGRASTU);
				if (sni_ordiundergrastu == -9)
					sni_ordiundergrastu = null;
				Integer sni_countryoverseastu = resultSet.getInt(StudentNumberInfoTable.SNI_COUNTRYOVERSEASTU);
				if (sni_countryoverseastu == -9)
					sni_countryoverseastu = null;
				Integer sni_highervocationstu = resultSet.getInt(StudentNumberInfoTable.SNI_HIGHERVOCATIONSTU);
				if (sni_highervocationstu == -9)
					sni_highervocationstu = null;
				Integer sni_postgraduate = resultSet.getInt(StudentNumberInfoTable.SNI_POSTGRADUATE);
				if (sni_postgraduate == -9)
					sni_postgraduate = null;
				Integer sni_fulltimepost = resultSet.getInt(StudentNumberInfoTable.SNI_FULLTIMEPOST);
				if (sni_fulltimepost == -9)
					sni_fulltimepost = null;
				Integer sni_nofulltimepost = resultSet.getInt(StudentNumberInfoTable.SNI_NOFULLTIMEPOST);
				if (sni_nofulltimepost == -9)
					sni_nofulltimepost = null;
				Integer sni_doctorcandidate = resultSet.getInt(StudentNumberInfoTable.SNI_DOCTORCANDIDATE);
				if (sni_doctorcandidate == -9)
					sni_doctorcandidate = null;
				Integer sni_fulltimedoct = resultSet.getInt(StudentNumberInfoTable.SNI_FULLTIMEDOCT);
				if (sni_fulltimedoct == -9)
					sni_fulltimedoct = null;
				Integer sni_nofulltimedoct = resultSet.getInt(StudentNumberInfoTable.SNI_NOFULLTIMEDOCT);
				if (sni_nofulltimedoct == -9)
					sni_nofulltimedoct = null;
				Integer sni_abroadstu = resultSet.getInt(StudentNumberInfoTable.SNI_ABROADSTU);
				if (sni_abroadstu == -9)
					sni_abroadstu = null;
				Integer sni_ordipreppie = resultSet.getInt(StudentNumberInfoTable.SNI_ORDIPREPPIE);
				if (sni_ordipreppie == -9)
					sni_ordipreppie = null;
				Integer sni_advancedstu = resultSet.getInt(StudentNumberInfoTable.SNI_ADVANCEDSTU);
				if (sni_advancedstu == -9)
					sni_advancedstu = null;
				Integer sni_adultfulltimestu = resultSet.getInt(StudentNumberInfoTable.SNI_ADULTFULLTIMESTU);
				if (sni_adultfulltimestu == -9)
					sni_adultfulltimestu = null;
				Integer sni_parttimestu = resultSet.getInt(StudentNumberInfoTable.SNI_PARTTIMESTU);
				if (sni_parttimestu == -9)
					sni_parttimestu = null;
				Integer sni_correspondencestu = resultSet.getInt(StudentNumberInfoTable.SNI_CORRESPONDENCESTU);
				if (sni_correspondencestu == -9)
					sni_correspondencestu = null;
				Integer sni_networkstu = resultSet.getInt(StudentNumberInfoTable.SNI_NETWORKSTU);
				if (sni_networkstu == -9)
					sni_networkstu = null;
				Integer sni_selftaughtstu = resultSet.getInt(StudentNumberInfoTable.SNI_SELFTAUGHTSTU);
				if (sni_selftaughtstu == -9)
					sni_selftaughtstu = null;
				Integer sni_serialnumber = resultSet.getInt(StudentNumberInfoTable.SNI_SERIALNUMBER);
				Date sni_deadline = resultSet.getDate(StudentNumberInfoTable.SNI_DEADLINE);
				String sni_college = resultSet.getString(StudentNumberInfoTable.SNI_COLLEGE);
				String sni_comments = resultSet.getString(StudentNumberInfoTable.SNI_COMMENTS);
				int sni_isnull = resultSet.getInt(StudentNumberInfoTable.SNI_ISNULL);
				StudentNumberInfo studentNumberInfo = new StudentNumberInfo(sni_id, sni_stuinfobaselink,
						sni_ordiundergrastu, sni_countryoverseastu, sni_highervocationstu, sni_postgraduate,
						sni_fulltimepost, sni_nofulltimepost, sni_doctorcandidate, sni_fulltimedoct, sni_nofulltimedoct,
						sni_abroadstu, sni_ordipreppie, sni_advancedstu, sni_adultfulltimestu, sni_parttimestu,
						sni_correspondencestu, sni_networkstu, sni_selftaughtstu, sni_serialnumber, sni_deadline,
						sni_college, sni_comments, sni_isnull);

				sniList.add(studentNumberInfo);
			}
			return sniList;
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
		String sql = "delete from " + StudentNumberInfoTable.TABLE_NAME + " where " + StudentNumberInfoTable.SNI_COLLEGE
				+ " = '" + college + "'" + " and sni_deadline is null ";
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
