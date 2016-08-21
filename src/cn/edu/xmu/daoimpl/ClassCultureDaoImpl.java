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













import cn.edu.xmu.dao.ClassCultureDao;
import cn.edu.xmu.entity.ClassCulture;
import cn.edu.xmu.entity.PlayGround;
import cn.edu.xmu.table.ClassCultureTable;
import cn.edu.xmu.table.PlayGroundTable;
import cn.edu.xmu.table.SchoolAddressTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;

public class ClassCultureDaoImpl extends BaseDaoImpl<ClassCulture> implements
		ClassCultureDao {

	@Override
	public List<ClassCulture> getAllClassCultures(int start, int end,
			String sortStr, String orderStr) {
		String sql = " select tmp.* from ( " + " select * from "
				+ ClassCultureTable.TABLE_NAME + " where 1=1 ";
		sql += " order by " + sortStr + " " + orderStr + " ) tmp limit "
				+ start + " ," + end;

		System.out.println(sql);

		List<ClassCulture> classCultures = new ArrayList<ClassCulture>();
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		try {
			System.out.println(sql);
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				int c_id = resultSet.getInt(ClassCultureTable.C_ID);
				String c_classname = resultSet.getString(ClassCultureTable.C_CLASSNAME);
				String c_classnumber = resultSet.getString(ClassCultureTable.C_CLASSNUMBER);
				/*int c_shunttime = 0;
				String c_departmentnumber = null;
				String c_majorname = null;
				String c_majornumber = null;
				String c_college = null;
				String c_comments = null;
				int c_serialnumber = 0;
				java.util.Date c_deadline = null;*/
				int c_shunttime = resultSet.getInt(ClassCultureTable.C_SHUNTTIME);
				String c_departmentname = resultSet.getString(ClassCultureTable.C_DEPARTMENAME);
				String c_departmentnumber = resultSet.getString(ClassCultureTable.C_DEPARTMENTNUMBER);
				String c_majornumber = resultSet.getString(ClassCultureTable.C_MAJORNUMBER);
				String c_majorname = resultSet.getString(ClassCultureTable.C_MAJORNAME);
				int c_serialnumber = resultSet.getInt(ClassCultureTable.C_SERIALNUMBER);
				Date c_deadline = resultSet.getDate(ClassCultureTable.C_DEADLINE);
				String c_college = resultSet.getString(ClassCultureTable.C_COLLEGE);
				String c_comments = resultSet.getString(ClassCultureTable.C_COMMENTS);
				if(null==c_comments)
					c_comments ="";
				ClassCulture classCulture = new ClassCulture(c_id, c_classname,
						c_classnumber , c_shunttime,c_departmentname, c_departmentnumber ,
						c_majornumber , c_majorname, c_serialnumber , c_deadline ,
						c_college, c_comments );
				classCultures.add(classCulture);
			}
			return classCultures;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
	}

	@Override
	public int getClassCultureCounts(Map queryParams) {
		int count = 0;
		//获取条件的语句
		String sql = "select count(*) from " + ClassCultureTable.TABLE_NAME
				+ " where 1 = 1";
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		for (Object object : queryParams.keySet()) {
			String key = object.toString();
			String value = queryParams.get(key).toString();
			sql += String.format(" and  %s like  '%s%%' ", key, value);
		}
		sql += String.format(" or %s ='%s'", ClassCultureTable.C_COLLEGE, "");
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
		
		return count;
	}

	@Override
	public boolean batchDelete(String[] smids) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();

		for (String smid : smids) {
			String sql = "delete from " + ClassCultureTable.TABLE_NAME
					+ " where " + ClassCultureTable.C_ID + " = '" + smid + "'";
			try {
				stmt.executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		JdbcUtils_DBCP.release(connection, stmt, null);
		return true;
	}

	@Override
	public int addClassCulture(ClassCulture classCulture) {
		int result = 0;

		String sql2 = "update " + ClassCultureTable.TABLE_NAME + " set "
				+ ClassCultureTable.C_SERIALNUMBER + " = "
				+ ClassCultureTable.C_SERIALNUMBER + " +1 where "
				+ ClassCultureTable.C_SERIALNUMBER + ">="
				+ classCulture.getC_serialnumber();
		Connection connection2 = null;
		try {
			connection2 = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		PreparedStatement pstmt2 = null;
		try {
			pstmt2 = connection2.prepareStatement(sql2);
			result = pstmt2.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			try {
				JdbcUtils_DBCP.release(connection2, pstmt2, null);
			} catch (Exception e2) {
				return 0;
			}
		}

		String sql = "insert into " + ClassCultureTable.TABLE_NAME + "("
				+ ClassCultureTable.C_CLASSNAME + ","
				+ ClassCultureTable.C_CLASSNUMBER + ","
				+ ClassCultureTable.C_SHUNTTIME + ","
				+ ClassCultureTable.C_DEPARTMENAME + ","
				+ ClassCultureTable.C_DEPARTMENTNUMBER + ","
				+ ClassCultureTable.C_MAJORNUMBER + ","
				+ ClassCultureTable.C_MAJORNAME + ","
				+ ClassCultureTable.C_SERIALNUMBER + ","
				+ ClassCultureTable.C_DEADLINE + ","
				+ ClassCultureTable.C_COLLEGE + ","
				+ ClassCultureTable.C_COMMENTS + ", isnull)values(?,?,?,?,?,?,?,?,?,?,'',?)";

		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		PreparedStatement pstmt = null;

		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, classCulture.getC_classname());
			pstmt.setString(2, classCulture.getC_classnumber());
			pstmt.setInt(3, classCulture.getC_shunttime());
			pstmt.setString(4, classCulture.getC_departmentname());
			pstmt.setString(5, classCulture.getC_departmentnumber());
			pstmt.setString(6, classCulture.getC_majornumber());
			pstmt.setString(7, classCulture.getC_majorname());
			pstmt.setInt(8, classCulture.getC_serialnumber());
			pstmt.setDate(9, (Date) classCulture.getC_deadline());
			pstmt.setString(10,classCulture.getC_college());
			pstmt.setInt(11,classCulture.getIsnull());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			result = -1;
		} finally {
			try {
				JdbcUtils_DBCP.release(connection, pstmt, null);
			} catch (Exception e2) {
				result = -1;
			}
		}

		return result;

	}

	@Override
	public int alterClassCulture(Map<String, String> valueMap, String id) {
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(ClassCultureTable.C_ID, id);
		int result = updateRecord(ClassCultureTable.TABLE_NAME, valueMap,
				condition);
		return result;
	}

	@Override
	public List<ClassCulture> getAllClassCultures() {
		String sql = " select * from " + ClassCultureTable.TABLE_NAME
				+ " where 1=1 " + " order by " + ClassCultureTable.C_ID;
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			System.out.println(sql);
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			List<ClassCulture> classCultureList = new ArrayList<ClassCulture>();
			while (resultSet.next()) {
				int c_id = resultSet.getInt(ClassCultureTable.C_ID);
				String c_classname = resultSet.getString(ClassCultureTable.C_CLASSNAME);
				String c_classnumber = resultSet.getString(ClassCultureTable.C_CLASSNUMBER);
				int c_shunttime = resultSet.getInt(ClassCultureTable.C_SHUNTTIME);
				String c_departmentname = resultSet.getString(ClassCultureTable.C_DEPARTMENAME);
				String c_departmentnumber = resultSet.getString(ClassCultureTable.C_DEPARTMENTNUMBER);
				String c_majornumber = resultSet.getString(ClassCultureTable.C_MAJORNUMBER);
				String c_majorname = resultSet.getString(ClassCultureTable.C_MAJORNAME);
				int c_serialnumber = resultSet.getInt(ClassCultureTable.C_SERIALNUMBER);
				Date c_deadline = resultSet.getDate(ClassCultureTable.C_DEADLINE);
				String c_college = resultSet.getString(ClassCultureTable.C_COLLEGE);
				String c_comments = resultSet.getString(ClassCultureTable.C_COMMENTS);
				ClassCulture classCulture = new ClassCulture(c_id, c_classname,
						c_classnumber , c_shunttime,c_departmentname, c_departmentnumber ,
						c_majornumber , c_majorname, c_serialnumber , c_deadline ,
						c_college, c_comments );
				classCultureList.add(classCulture);
			}
			return classCultureList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				JdbcUtils_DBCP.release(connection, pstmt, resultSet);
			} catch (Exception e2) {
				return null;
			}
		}
	}

	@Override
	public void deleteAll() {
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		Statement stmt = null;
		try {
			stmt = connection.createStatement();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		String sql = "delete from " + ClassCultureTable.TABLE_NAME;
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JdbcUtils_DBCP.release(connection, stmt, null);
	}

	@Override
	public int addClassCultureDaoRecord(ClassCulture classCulture) {
		int result = 0;

		String sql = "insert into " + ClassCultureTable.TABLE_NAME + "("
				+ ClassCultureTable.C_CLASSNAME + ","
				+ ClassCultureTable.C_CLASSNUMBER + ","
				+ ClassCultureTable.C_SHUNTTIME + ","
				+ ClassCultureTable.TABLE_NAME + ","
				+ ClassCultureTable.C_DEPARTMENTNUMBER + ","
				+ ClassCultureTable.C_MAJORNUMBER + ","
				+ ClassCultureTable.C_MAJORNAME + ","
				+ ClassCultureTable.C_SERIALNUMBER + ","
				+ ClassCultureTable.C_DEADLINE + ","
				+ ClassCultureTable.C_COLLEGE + ","
				+ ClassCultureTable.C_COMMENTS + ")values(?,?,?,?,?,?,?,?,?,?,?)";


		System.out.println("添加纪录" + sql);
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		PreparedStatement pstmt = null;

		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, classCulture.getC_classname());
			pstmt.setString(2, classCulture.getC_classnumber());
			pstmt.setInt(3, classCulture.getC_shunttime());
			pstmt.setString(4, classCulture.getC_departmentname());
			pstmt.setString(5, classCulture.getC_departmentnumber());
			pstmt.setString(6, classCulture.getC_majornumber());
			pstmt.setString(7, classCulture.getC_majorname());
			pstmt.setInt(8, classCulture.getC_serialnumber());
			pstmt.setDate(9, (Date) classCulture.getC_deadline());
			pstmt.setString(10,classCulture.getC_college());
			pstmt.setString(11,classCulture.getC_comments());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			result = -1;
		} finally {
			try {
				JdbcUtils_DBCP.release(connection, pstmt, null);
			} catch (Exception e2) {
				result = -1;
			}
		}
		return result;
	}

	@Override
	public int getClassCultureCountByMajor(Map params) {
		int count = 0;
		String sql = "select count(*) from " + ClassCultureTable.TABLE_NAME
				+ " where 1 = 1 and "+ClassCultureTable.C_MAJORNAME+" is not null and "+ClassCultureTable.C_MAJORNAME+" != ''";
		if (params != null && params.size() != 0) {
			for (Object object : params.keySet()) {
				String key = object.toString();
				String value = params.get(key).toString();
				sql += String.format(" and  %s like  '%s%%' ", key, value);
			}
		}
		System.out.println(sql);
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
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
		System.out.println(count);
		return count;
	}

	@Override
	public void deleteByCollegeandDeadline(String college, Date deadline)
			throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();
		String sql = "delete from " + ClassCultureTable.TABLE_NAME
				+ " where " + ClassCultureTable.C_COLLEGE + " = '" + college + "'" +" and c_deadline is null ";
		System.out.println(sql);
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils_DBCP.release(connection, stmt, null);
		}
		
	}

	@Override
	public List<ClassCulture> getAllClassCultures(int start, int end,
			String sortStr, String orderStr, Map queryParams) {
		String sql = " select tmp.* from ( " + " select * from "
				+ ClassCultureTable.TABLE_NAME + " where 1=1 ";
		
		for (Object object : queryParams.keySet()) {
			String key = object.toString();
			String value = queryParams.get(key).toString();
			sql += String.format(" and %s like  '%%%s%%'", key, value);
		}
		
		if (sortStr == "nope") {
			
		}
		else {
			sql += " order by " + sortStr + " " + orderStr + " ) tmp limit "
					+ start + " ," + end;
		}
	

		List<ClassCulture> classCultures = new ArrayList<ClassCulture>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			System.out.println(sql);
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				int c_id = resultSet.getInt(ClassCultureTable.C_ID);
				String c_classname = resultSet.getString(ClassCultureTable.C_CLASSNAME);
				String c_classnumber = resultSet.getString(ClassCultureTable.C_CLASSNUMBER);
				/*int c_shunttime = 0;
				String c_departmentnumber = null;
				String c_majorname = null;
				String c_majornumber = null;
				String c_college = null;
				String c_comments = null;
				int c_serialnumber = 0;
				java.util.Date c_deadline = null;*/
				int c_shunttime = resultSet.getInt(ClassCultureTable.C_SHUNTTIME);
				String c_departmentname = resultSet.getString(ClassCultureTable.C_DEPARTMENAME);
				String c_departmentnumber = resultSet.getString(ClassCultureTable.C_DEPARTMENTNUMBER);
				String c_majornumber = resultSet.getString(ClassCultureTable.C_MAJORNUMBER);
				String c_majorname = resultSet.getString(ClassCultureTable.C_MAJORNAME);
				int c_serialnumber = resultSet.getInt(ClassCultureTable.C_SERIALNUMBER);
				Date c_deadline = resultSet.getDate(ClassCultureTable.C_DEADLINE);
				String c_college = resultSet.getString(ClassCultureTable.C_COLLEGE);
				String c_comments = resultSet.getString(ClassCultureTable.C_COMMENTS);
				if(null==c_comments)
					c_comments ="";
				ClassCulture classCulture = new ClassCulture(c_id, c_classname,
						c_classnumber , c_shunttime,c_departmentname, c_departmentnumber ,
						c_majornumber , c_majorname, c_serialnumber , c_deadline ,
						c_college, c_comments );
				classCultures.add(classCulture);
			}
			return classCultures;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
	}


	
}
