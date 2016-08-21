package cn.edu.xmu.daoimpl;
/**
 * @author Sihan
 */

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

import cn.edu.xmu.dao.OtherTeacherInfoDao;
import cn.edu.xmu.entity.OtherTeacherInfo;
import cn.edu.xmu.table.FullTimeTeacherInfoTable;
import cn.edu.xmu.table.MajorInfoTable;
import cn.edu.xmu.table.OtherTeacherInfoTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;

public class OtherTeacherInfoDaoImpl extends BaseDaoImpl<OtherTeacherInfo>implements OtherTeacherInfoDao
{

	@Override
	public List<OtherTeacherInfo> getAllOtherTeacherInfo(int start, int end, String sortStr, String orderStr,
			Map queryParams)
	{
		String sql = " select tmp.* from ( " + " select * from " + OtherTeacherInfoTable.TABLE_NAME + " where 1=1 ";
		if (queryParams != null && queryParams.size() > 0)
		{
			for (Object object : queryParams.keySet())
			{
				String key = object.toString();
				String value = queryParams.get(key).toString();
				sql += String.format(" and %s like  '%%%s%%'", key, value);
			}
		}
		sql += " order by " + sortStr + " " + orderStr + " ) tmp limit " + start + " ," + end;

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		List<OtherTeacherInfo> otherTeacherInfos = new ArrayList<OtherTeacherInfo>();
		try
		{
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			String tempString = "";

			while (resultSet.next())
			{
				// String tempStr = new String();
				Date temp = Date.valueOf("1800-1-1");//用于date类型的比较，等于“1800-1-1”的date字段为null
				int oti_id = resultSet.getInt(OtherTeacherInfoTable.OTI_ID);
				String oti_name = resultSet.getString(OtherTeacherInfoTable.OTI_NAME); // 姓名
				String oti_worknumber = resultSet.getString(OtherTeacherInfoTable.OTI_WORKNUMBER); // 工号
				String oti_sex = resultSet.getString(OtherTeacherInfoTable.OTI_SEX);

				// tempStr = resultSet.getString(OtherTeacherInfoTable.OTI_SEX);
				// XingBie oti_sex = XingBie.valueOf(tempStr);//性别

				Date oti_birthday = resultSet.getDate(OtherTeacherInfoTable.OTI_BIRTHDAY); // 出生年月
				if(temp.equals(oti_birthday))
					oti_birthday = null;
				Date oti_inschooldate = resultSet.getDate(OtherTeacherInfoTable.OTI_INSCHOOLDATE); // 入校时间
				if(temp.equals(oti_inschooldate))
					oti_inschooldate = null;
				String oti_workstate = resultSet.getString(OtherTeacherInfoTable.OTI_WORKSTATE);
				// tempStr=
				// resultSet.getString(OtherTeacherInfoTable.OTI_WORKSTATE);
				// WorkingState oti_workstate =
				// WorkingState.valueOf(tempStr);//任职状态

				String oti_teachertype = resultSet.getString(OtherTeacherInfoTable.OTI_TEACHERTYPE);
				// tempStr=
				// resultSet.getString(OtherTeacherInfoTable.OTI_TEACHERTYPE);
				// ShiZiLeiBie oti_teachertype =
				// ShiZiLeiBie.valueOf(tempStr);//师资类别

				String oti_departmentnumber = resultSet.getString(OtherTeacherInfoTable.OTI_DEPARTMENTNUMBER); // 单位号
				String oti_departmentname = resultSet.getString(OtherTeacherInfoTable.OTI_DEPARTMENTNAME); // 单位名称

				String oti_education = resultSet.getString(OtherTeacherInfoTable.OTI_EDUCATION);
				// tempStr =
				// resultSet.getString(OtherTeacherInfoTable.OTI_EDUCATION);
				// XueLi oti_education = XueLi.valueOf(tempStr);//学历

				String oti_degree = resultSet.getString(OtherTeacherInfoTable.OTI_DEGREE);
				// tempStr =
				// resultSet.getString(OtherTeacherInfoTable.OTI_DEGREE);
				// ZuiGaoXueWei oti_degree = ZuiGaoXueWei.valueOf(tempStr);
				// //最高学位

				String oti_professionaltitle = resultSet.getString(OtherTeacherInfoTable.OTI_PROFESSIONALTITLE);
				// tempStr =
				// resultSet.getString(OtherTeacherInfoTable.OTI_PROFESSIONALTITLE);
				// ZhuanYeJiShuZhiCheng oti_professionaltitle =
				// ZhuanYeJiShuZhiCheng.valueOf(tempStr); //专业技术职称

				String oti_subjectcategory = resultSet.getString(OtherTeacherInfoTable.OTI_SUBJECTCATEGORY);
				// tempStr =
				// resultSet.getString(OtherTeacherInfoTable.OTI_SUBJECTCATEGORY);
				// XueKeLeiBie oti_subjectcategory =
				// XueKeLeiBie.valueOf(tempStr); //学科类别

				String oti_tutorcategory = resultSet.getString(OtherTeacherInfoTable.OTI_TUTORCATEGORY);
				// tempStr =
				// resultSet.getString(OtherTeacherInfoTable.OTI_TUTORCATEGORY);
				// DaoShiLeiBie oti_tutorcategory =
				// DaoShiLeiBie.valueOf(tempStr); //导师类别
				String oti_college = resultSet.getString(OtherTeacherInfoTable.OTI_COLLEGE);
				Date oti_deadline = resultSet.getDate(OtherTeacherInfoTable.OTI_DEADLINE);

				int oti_serialnumber = resultSet.getInt(OtherTeacherInfoTable.OTI_SERIALNUMBER);
				String oti_comments = resultSet.getString(OtherTeacherInfoTable.OTI_COMMENTS);

				int oti_isnull = resultSet.getInt(OtherTeacherInfoTable.OTI_ISNULL);
				OtherTeacherInfo otherTeacherInfo = new OtherTeacherInfo(oti_id, oti_name, oti_worknumber, oti_sex,
						oti_birthday, oti_inschooldate, oti_workstate, oti_teachertype, oti_departmentnumber,
						oti_departmentname, oti_education, oti_degree, oti_professionaltitle, oti_subjectcategory,
						oti_tutorcategory, oti_college, oti_deadline, oti_serialnumber, oti_comments, oti_isnull);
				otherTeacherInfos.add(otherTeacherInfo);

			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return otherTeacherInfos;
	}

	@Override
	public int alterOtherTeacherInfo(Map<String, String> valueMap, String id)
	{
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(OtherTeacherInfoTable.OTI_ID, id);
		int result = updateRecord(OtherTeacherInfoTable.TABLE_NAME, valueMap, condition);
		return result;
	}

	@Override
	public boolean batchDelete(String[] otiids) throws SQLException
	{
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();
		for (String otiid : otiids)
		{
			String sql = "delete from " + OtherTeacherInfoTable.TABLE_NAME + " where " + OtherTeacherInfoTable.OTI_ID
					+ " = '" + otiid + "'";

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
	public int getOtherTeacherInfoCountByWorknumber(String worknumber, String id)
	{

		int count = 0;
		String sql = "select count(*) from " + OtherTeacherInfoTable.TABLE_NAME
				+ String.format(" where %s='%s' ", OtherTeacherInfoTable.OTI_WORKNUMBER, worknumber);
		if (id != null && !"".equals(id))
			sql += String.format(" and %s!='%s'", OtherTeacherInfoTable.OTI_ID, id);
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

	};

	@Override
	public int getOtherTeacherInfoCount(Map queryParams)
	{
		int count = 0;
		String sql = "select count(*) from " + OtherTeacherInfoTable.TABLE_NAME + " where 1 = 1";
		Connection connection = null;
		try
		{
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1)
		{
			e1.printStackTrace();
			return -1;
		}

		if (queryParams != null && queryParams.size() > 0)
		{
			for (Object object : queryParams.keySet())
			{
				String key = object.toString();
				String value = queryParams.get(key).toString();
				sql += String.format(" and  %s like  '%s%%' ", key, value);
			}
		}

		sql += String.format(" or %s ='%s'", OtherTeacherInfoTable.OTI_COLLEGE, "");
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		System.out.println(sql);
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
	public int addRecord(OtherTeacherInfo oti)
	{
		int result = 0;
		String t_sql = "update " + OtherTeacherInfoTable.TABLE_NAME + " set " + OtherTeacherInfoTable.OTI_SERIALNUMBER
				+ " = " + OtherTeacherInfoTable.OTI_SERIALNUMBER + " +1 where " + OtherTeacherInfoTable.OTI_SERIALNUMBER
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
			t_pstmt.setInt(1, oti.getOti_serialnumber());

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

		String sql = "insert into " + OtherTeacherInfoTable.TABLE_NAME + "(" + OtherTeacherInfoTable.OTI_NAME + ","
				+ OtherTeacherInfoTable.OTI_WORKNUMBER + "," + OtherTeacherInfoTable.OTI_SEX + ","
				+ OtherTeacherInfoTable.OTI_BIRTHDAY + "," + OtherTeacherInfoTable.OTI_INSCHOOLDATE + ","
				+ OtherTeacherInfoTable.OTI_WORKSTATE + "," + OtherTeacherInfoTable.OTI_TEACHERTYPE + ","
				+ OtherTeacherInfoTable.OTI_DEPARTMENTNUMBER + "," + OtherTeacherInfoTable.OTI_DEPARTMENTNAME + ","
				+ OtherTeacherInfoTable.OTI_EDUCATION + "," + OtherTeacherInfoTable.OTI_DEGREE + ","
				+ OtherTeacherInfoTable.OTI_PROFESSIONALTITLE + "," + OtherTeacherInfoTable.OTI_SUBJECTCATEGORY + ","
				+ OtherTeacherInfoTable.OTI_TUTORCATEGORY + "," + OtherTeacherInfoTable.OTI_SERIALNUMBER + ","
				+ OtherTeacherInfoTable.OTI_COMMENTS + "," + OtherTeacherInfoTable.OTI_COLLEGE + ","
				+ OtherTeacherInfoTable.OTI_ISNULL + ")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		Connection connection = null;
		PreparedStatement pstmt = null;
		try
		{
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, oti.getOti_name());
			pstmt.setString(2, oti.getOti_worknumber());
			pstmt.setString(3, oti.getOti_sex().toString());
			pstmt.setString(4, oti.getOti_birthday().toString());
			pstmt.setString(5, oti.getOti_inschooldate().toString());
			pstmt.setString(6, oti.getOti_workstate().toString());
			pstmt.setString(7, oti.getOti_teachertype().toString());
			pstmt.setString(8, oti.getOti_departmentnumber().toString());
			pstmt.setString(9, oti.getOti_departmentname().toString());
			pstmt.setString(10, oti.getOti_education().toString());
			pstmt.setString(11, oti.getOti_degree().toString());
			pstmt.setString(12, oti.getOti_professionaltitle().toString());
			pstmt.setString(13, oti.getOti_subjectcategory().toString());
			pstmt.setString(14, oti.getOti_tutorcategory().toString());
			pstmt.setInt(15, oti.getOti_serialnumber());
			pstmt.setString(16, oti.getOti_comments());
			pstmt.setString(17, oti.getOti_college());
			pstmt.setInt(18, oti.getOti_isnull());

			System.out.println(oti.getOti_tutorcategory());

			result = pstmt.executeUpdate();

		} catch (SQLException e)
		{
			e.printStackTrace();
			return -1;
		} finally
		{
			JdbcUtils_DBCP.release(connection, pstmt, null);
		}

		return result;

	}

	@Override
	public int getCountByRange(String param, Date start, Date end, Map queryMap)
	{
		int count = 0;
		String sql = "select count(*) from " + OtherTeacherInfoTable.TABLE_NAME
				+ String.format(" where unix_timestamp( %s ) between unix_timestamp( '%s') and unix_timestamp( '%s' ) ",
						param, start, end);

		if (queryMap != null && queryMap.keySet().size() != 0)
		{
			for (Object object : queryMap.keySet())
			{
				String key = object.toString();
				String value = queryMap.get(key).toString();
				sql += String.format(" and %s like  '%%%s%%'", key, value);
			}
		}

		System.out.println(sql);

		Connection connection = null;
		try
		{
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1)
		{
			e1.printStackTrace();
			return -1;
		}
		System.out.println(sql);
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
	public int getCountByRangeInMajorHeader(String param, Date start, Date end, Map queryParams, Map basicMap)
	{
		int count = 0;
		String sql = "select count(*) from " + OtherTeacherInfoTable.TABLE_NAME
				+ String.format(" where unix_timestamp( %s ) between unix_timestamp( '%s') and unix_timestamp( '%s' ) ",
						param, start, end);

		if (queryParams != null && queryParams.keySet().size() != 0)
		{
			for (Object object : queryParams.keySet())
			{
				String key = object.toString();
				String value = queryParams.get(key).toString();
				sql += String.format(" and %s like  '%%%s%%'", key, value);
			}
		}

		sql += " and " + OtherTeacherInfoTable.OTI_WORKNUMBER + " in (select distinct " + MajorInfoTable.MI_LEADERID
				+ " from " + MajorInfoTable.TABLE_NAME + " where 1 = 1";
		if (basicMap != null)
		{
			for (Object object : basicMap.keySet())
			{
				String key = object.toString();
				String value = basicMap.get(key).toString();
				sql += String.format(" and  %s like  '%s%%' ", key, value);
			}
		}
		sql += ")";
		System.out.println(sql);

		Connection connection = null;
		try
		{
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1)
		{
			e1.printStackTrace();
			return -1;
		}
		System.out.println(sql);
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
	public int getOtherTeacherCountInMajorHeader(Map queryParams, Map basicMap)
	{

		int count = 0;
		String sql = "select count(*) from " + OtherTeacherInfoTable.TABLE_NAME + " where 1 = 1";
		Connection connection = null;
		try
		{
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1)
		{
			e1.printStackTrace();
			return -1;
		}

		if (queryParams != null && queryParams.size() > 0)
		{
			for (Object object : queryParams.keySet())
			{
				String key = object.toString();
				String value = queryParams.get(key).toString();
				sql += String.format(" and  %s like  '%%%s%%' ", key, value);
			}
		}
		sql += String.format(" or %s ='%s'", OtherTeacherInfoTable.OTI_COLLEGE, "");

		sql += " and " + OtherTeacherInfoTable.OTI_WORKNUMBER + " in (select distinct " + MajorInfoTable.MI_LEADERID
				+ " from " + MajorInfoTable.TABLE_NAME + " where 1 = 1";
		if (basicMap != null)
		{
			for (Object object : basicMap.keySet())
			{
				String key = object.toString();
				String value = basicMap.get(key).toString();
				sql += String.format(" and  %s like  '%s%%' ", key, value);
			}
		}
		sql += ")";

		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		System.out.println(sql);
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
	public List<OtherTeacherInfo> getAll()
	{
		String sql = " select tmp.* from ( " + " select * from " + OtherTeacherInfoTable.TABLE_NAME + " where 1=1 "
				+ " order by " + OtherTeacherInfoTable.OTI_ID;

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		List<OtherTeacherInfo> otherTeacherInfos = new ArrayList<OtherTeacherInfo>();
		try
		{
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next())
			{
				// String tempStr = new String();

				int oti_id = resultSet.getInt(OtherTeacherInfoTable.OTI_ID);
				String oti_name = resultSet.getString(OtherTeacherInfoTable.OTI_NAME); // 姓名
				String oti_worknumber = resultSet.getString(OtherTeacherInfoTable.OTI_WORKNUMBER); // 工号
				String oti_sex = resultSet.getString(OtherTeacherInfoTable.OTI_SEX);

				// tempStr = resultSet.getString(OtherTeacherInfoTable.OTI_SEX);
				// XingBie oti_sex = XingBie.valueOf(tempStr);//性别

				Date oti_birthday = resultSet.getDate(OtherTeacherInfoTable.OTI_BIRTHDAY); // 出生年月
				Date oti_inschooldate = resultSet.getDate(OtherTeacherInfoTable.OTI_INSCHOOLDATE); // 入校时间

				String oti_workstate = resultSet.getString(OtherTeacherInfoTable.OTI_WORKSTATE);
				// tempStr=
				// resultSet.getString(OtherTeacherInfoTable.OTI_WORKSTATE);
				// WorkingState oti_workstate =
				// WorkingState.valueOf(tempStr);//任职状态

				String oti_teachertype = resultSet.getString(OtherTeacherInfoTable.OTI_TEACHERTYPE);
				// tempStr=
				// resultSet.getString(OtherTeacherInfoTable.OTI_TEACHERTYPE);
				// ShiZiLeiBie oti_teachertype =
				// ShiZiLeiBie.valueOf(tempStr);//师资类别

				String oti_departmentnumber = resultSet.getString(OtherTeacherInfoTable.OTI_DEPARTMENTNUMBER); // 单位号
				String oti_departmentname = resultSet.getString(OtherTeacherInfoTable.OTI_DEPARTMENTNAME); // 单位名称

				String oti_education = resultSet.getString(OtherTeacherInfoTable.OTI_EDUCATION);
				// tempStr =
				// resultSet.getString(OtherTeacherInfoTable.OTI_EDUCATION);
				// XueLi oti_education = XueLi.valueOf(tempStr);//学历

				String oti_degree = resultSet.getString(OtherTeacherInfoTable.OTI_DEGREE);
				// tempStr =
				// resultSet.getString(OtherTeacherInfoTable.OTI_DEGREE);
				// ZuiGaoXueWei oti_degree = ZuiGaoXueWei.valueOf(tempStr);
				// //最高学位

				String oti_professionaltitle = resultSet.getString(OtherTeacherInfoTable.OTI_PROFESSIONALTITLE);
				// tempStr =
				// resultSet.getString(OtherTeacherInfoTable.OTI_PROFESSIONALTITLE);
				// ZhuanYeJiShuZhiCheng oti_professionaltitle =
				// ZhuanYeJiShuZhiCheng.valueOf(tempStr); //专业技术职称

				String oti_subjectcategory = resultSet.getString(OtherTeacherInfoTable.OTI_SUBJECTCATEGORY);
				// tempStr =
				// resultSet.getString(OtherTeacherInfoTable.OTI_SUBJECTCATEGORY);
				// XueKeLeiBie oti_subjectcategory =
				// XueKeLeiBie.valueOf(tempStr); //学科类别

				String oti_tutorcategory = resultSet.getString(OtherTeacherInfoTable.OTI_TUTORCATEGORY);
				// tempStr =
				// resultSet.getString(OtherTeacherInfoTable.OTI_TUTORCATEGORY);
				// DaoShiLeiBie oti_tutorcategory =
				// DaoShiLeiBie.valueOf(tempStr); //导师类别

				int oti_serialnumber = resultSet.getInt(OtherTeacherInfoTable.OTI_SERIALNUMBER);
				String oti_comments = resultSet.getString(OtherTeacherInfoTable.OTI_COMMENTS);
				String oti_college = resultSet.getString(OtherTeacherInfoTable.OTI_COLLEGE);
				int oti_isnull = resultSet.getInt(OtherTeacherInfoTable.OTI_ISNULL);

				OtherTeacherInfo otherTeacherInfo = new OtherTeacherInfo(oti_name, oti_worknumber, oti_sex,
						oti_birthday, oti_inschooldate, oti_workstate, oti_teachertype, oti_departmentnumber,
						oti_departmentname, oti_education, oti_degree, oti_professionaltitle, oti_subjectcategory,
						oti_tutorcategory, oti_college, oti_serialnumber, oti_comments, oti_isnull);
				otherTeacherInfos.add(otherTeacherInfo);

			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return otherTeacherInfos;
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
		String sql = "delete from " + OtherTeacherInfoTable.TABLE_NAME + " where " + OtherTeacherInfoTable.OTI_COLLEGE
				+ " = '" + college + "'" + " and oti_deadline is null ";
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

	//根据单位号获得教师信息
	@Override
	public List<OtherTeacherInfo> getOtherTeacherInfoByDepartNum(
			String departNum) {
		// TODO Auto-generated method stub
		String sql = " select * from " + OtherTeacherInfoTable.TABLE_NAME + " where "+ 
				OtherTeacherInfoTable.OTI_DEPARTMENTNUMBER + " = "+departNum;
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		List<OtherTeacherInfo> otherTeacherInfos = new ArrayList<OtherTeacherInfo>();
		try
		{
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			String tempString = "";

			while (resultSet.next())
			{
				
				Date temp = Date.valueOf("1800-1-1");//用于date类型的比较，等于“1800-1-1”的date字段为null
				int oti_id = resultSet.getInt(OtherTeacherInfoTable.OTI_ID);
				String oti_name = resultSet.getString(OtherTeacherInfoTable.OTI_NAME); // 姓名
				String oti_worknumber = resultSet.getString(OtherTeacherInfoTable.OTI_WORKNUMBER); // 工号
				String oti_sex = resultSet.getString(OtherTeacherInfoTable.OTI_SEX);

				Date oti_birthday = resultSet.getDate(OtherTeacherInfoTable.OTI_BIRTHDAY); // 出生年月
				if(temp.equals(oti_birthday))
					oti_birthday = null;
				Date oti_inschooldate = resultSet.getDate(OtherTeacherInfoTable.OTI_INSCHOOLDATE); // 入校时间
				if(temp.equals(oti_inschooldate))
					oti_inschooldate = null;
				String oti_workstate = resultSet.getString(OtherTeacherInfoTable.OTI_WORKSTATE);
				
				String oti_teachertype = resultSet.getString(OtherTeacherInfoTable.OTI_TEACHERTYPE);
				
				String oti_departmentnumber = resultSet.getString(OtherTeacherInfoTable.OTI_DEPARTMENTNUMBER); // 单位号
				String oti_departmentname = resultSet.getString(OtherTeacherInfoTable.OTI_DEPARTMENTNAME); // 单位名称

				String oti_education = resultSet.getString(OtherTeacherInfoTable.OTI_EDUCATION);
				
				String oti_degree = resultSet.getString(OtherTeacherInfoTable.OTI_DEGREE);
				
				String oti_professionaltitle = resultSet.getString(OtherTeacherInfoTable.OTI_PROFESSIONALTITLE);
				
				String oti_subjectcategory = resultSet.getString(OtherTeacherInfoTable.OTI_SUBJECTCATEGORY);
				
				String oti_tutorcategory = resultSet.getString(OtherTeacherInfoTable.OTI_TUTORCATEGORY);
				
				String oti_college = resultSet.getString(OtherTeacherInfoTable.OTI_COLLEGE);
				Date oti_deadline = resultSet.getDate(OtherTeacherInfoTable.OTI_DEADLINE);

				int oti_serialnumber = resultSet.getInt(OtherTeacherInfoTable.OTI_SERIALNUMBER);
				String oti_comments = resultSet.getString(OtherTeacherInfoTable.OTI_COMMENTS);

				int oti_isnull = resultSet.getInt(OtherTeacherInfoTable.OTI_ISNULL);
				OtherTeacherInfo otherTeacherInfo = new OtherTeacherInfo(oti_id, oti_name, oti_worknumber, oti_sex,
						oti_birthday, oti_inschooldate, oti_workstate, oti_teachertype, oti_departmentnumber,
						oti_departmentname, oti_education, oti_degree, oti_professionaltitle, oti_subjectcategory,
						oti_tutorcategory, oti_college, oti_deadline, oti_serialnumber, oti_comments, oti_isnull);
				otherTeacherInfos.add(otherTeacherInfo);

			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return otherTeacherInfos;
	}

}
