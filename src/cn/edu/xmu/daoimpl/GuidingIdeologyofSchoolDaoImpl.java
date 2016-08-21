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

import cn.edu.xmu.dao.GuidingIdeologyofSchoolDao;
import cn.edu.xmu.entity.GuidingIdeologyofSchool;
import cn.edu.xmu.table.GuidingIdeologyofSchoolTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;


/**
 * @author zhantu
 * 图书当年新增情况  实体类功能 ——接口实现
 * date 2015-06-30
 */

public class GuidingIdeologyofSchoolDaoImpl extends BaseDaoImpl<GuidingIdeologyofSchool>implements GuidingIdeologyofSchoolDao{

	@Override
	public int addRecord(GuidingIdeologyofSchool gis) {
		
		int result = 0;

		String t_sql = "update " + GuidingIdeologyofSchoolTable.TABLE_NAME + " set "
				+ GuidingIdeologyofSchoolTable.GIS_SERIALNUMBER + " = "
				+ GuidingIdeologyofSchoolTable.GIS_SERIALNUMBER + " +1 where "
				+ GuidingIdeologyofSchoolTable.GIS_SERIALNUMBER + ">=?";

		
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
			t_pstmt.setInt(1, gis.getGis_serialnumber());
			
			//执行插入操作并更新
			result = t_pstmt.executeUpdate();
			
		} catch (SQLException e) {
			//事务回滚，不做插入操作
			e.printStackTrace();
			return 0;
		} finally {
			JdbcUtils_DBCP.release(connection2, t_pstmt, null);
		}

		//执行插入操作的语句
		String sql = "insert into " + GuidingIdeologyofSchoolTable.TABLE_NAME + "("
				+ GuidingIdeologyofSchoolTable.GIS_MOTTOCONTENT + ","
				+ GuidingIdeologyofSchoolTable.GIS_MOTTOREMARK + ","
				+ GuidingIdeologyofSchoolTable.GIS_POSITIONGOALCONTENT + ","
				+ GuidingIdeologyofSchoolTable.GIS_POSITIONGOALREMARK + ","
				+ GuidingIdeologyofSchoolTable.GIS_STRATEGY + ","
				+ GuidingIdeologyofSchoolTable.GIS_DISCIPLINE + ","
				+ GuidingIdeologyofSchoolTable.GIS_PROFESSIONAL + ","
				+ GuidingIdeologyofSchoolTable.GIS_TEACHER + ","
				
				+ GuidingIdeologyofSchoolTable.GIS_SERIALNUMBER + ","
				+ GuidingIdeologyofSchoolTable.GIS_COLLEGE + ","
				+ GuidingIdeologyofSchoolTable.GIS_COMMENTS + ","
				+ GuidingIdeologyofSchoolTable.ISNULL
				+ ")values(?,?,?,?,?,?,?,?,?,?,?,?)";

		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, gis.getGis_mottocontent());
			pstmt.setString(2, gis.getGis_mottoremark());
			pstmt.setString(3, gis.getGis_positiongoalcontent());
			pstmt.setString(4, gis.getGis_positiongoalremark());
			pstmt.setString(5, gis.getGis_strategy());
			pstmt.setString(6, gis.getGis_discipline());
			pstmt.setString(7, gis.getGis_professional());
			pstmt.setString(8, gis.getGis_teacher());
			pstmt.setInt(9, gis.getGis_serialnumber());
			pstmt.setString(10, gis.getGis_college());
			pstmt.setString(11, gis.getGis_comments());
			pstmt.setInt(12, gis.getIsnull());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			//事务回滚。不做插入操作
			e.printStackTrace();
			result = -1;
			
		} finally {
			JdbcUtils_DBCP.release(connection,pstmt,null);
		}
		return result;
		
	}

	@Override
	public int getGuidingIdeologyofSchoolCount(Map queryParams) {
		
		int count = 0;
		//获取条件的语句
		String sql = "select count(*) from " + GuidingIdeologyofSchoolTable.TABLE_NAME
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
			sql += String.format(" and  %s like  '%%%s%%' ", key, value);
		}
		sql += String.format(" or %s ='%s'", GuidingIdeologyofSchoolTable.GIS_COLLEGE, "");
		
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
	public List<GuidingIdeologyofSchool> getAllGuidingIdeologyofSchool(int start, int end,
			String sortStr, String orderStr, Map queryParams) {
		
		String sql = " select tmp.* from ( " + " select * from "
				+ GuidingIdeologyofSchoolTable.TABLE_NAME + " where 1=1 ";
		
		for (Object object : queryParams.keySet()) {
			String key = object.toString();
			String value = queryParams.get(key).toString();
			sql += String.format(" and %s like  '%%%s%%'", key, value);
		}
		sql += " order by " + sortStr + " " + orderStr + " ) tmp limit "
				+ start + " ," + end;

		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		List<GuidingIdeologyofSchool> giss = new ArrayList<GuidingIdeologyofSchool>();
		try {
			
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int gis_id = resultSet.getInt(GuidingIdeologyofSchoolTable.GIS_ID);
				String gis_mottocontent = resultSet.getString(GuidingIdeologyofSchoolTable.GIS_MOTTOCONTENT);
				String gis_mottoremark = resultSet.getString(GuidingIdeologyofSchoolTable.GIS_MOTTOREMARK);
				String gis_positiongoalcontent = resultSet.getString(GuidingIdeologyofSchoolTable.GIS_POSITIONGOALCONTENT);
				String gis_positiongoalremark = resultSet.getString(GuidingIdeologyofSchoolTable.GIS_POSITIONGOALREMARK);
				String gis_strategy = resultSet.getString(GuidingIdeologyofSchoolTable.GIS_STRATEGY);
				String gis_discipline = resultSet.getString(GuidingIdeologyofSchoolTable.GIS_DISCIPLINE);
				String gis_professional = resultSet.getString(GuidingIdeologyofSchoolTable.GIS_PROFESSIONAL);
				String gis_teacher = resultSet.getString(GuidingIdeologyofSchoolTable.GIS_TEACHER);

				int gis_serialnumber = resultSet.getInt(GuidingIdeologyofSchoolTable.GIS_SERIALNUMBER);
				Date gis_deadline = resultSet.getDate(GuidingIdeologyofSchoolTable.GIS_DEADLINE);
				String gis_comments = resultSet.getString(GuidingIdeologyofSchoolTable.GIS_COMMENTS);
				String gis_college = resultSet.getString(GuidingIdeologyofSchoolTable.GIS_COLLEGE);
				int isnull = resultSet.getInt(GuidingIdeologyofSchoolTable.ISNULL);
				if(gis_comments==null)
					gis_comments = "";
				GuidingIdeologyofSchool gis = new GuidingIdeologyofSchool(gis_id, gis_mottocontent,
						gis_mottoremark, gis_positiongoalcontent,
						gis_positiongoalremark, gis_strategy,
						gis_discipline, gis_professional, gis_teacher,
						gis_serialnumber, gis_deadline, gis_college,
						gis_comments, isnull);
				
				giss.add(gis);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return giss;
	}

	@Override
	public int alterGuidingIdeologyofSchool(Map<String, String> valueMap, String id) {
		
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(GuidingIdeologyofSchoolTable.GIS_ID, id);
		int result = updateRecord(GuidingIdeologyofSchoolTable.TABLE_NAME, valueMap,
				condition);
		return result;
	}

	@Override
	public boolean batchDelete(String[] gisids) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();

		for (String gisid : gisids) {
			String sql = "delete from " + GuidingIdeologyofSchoolTable.TABLE_NAME
					+ " where " + GuidingIdeologyofSchoolTable.GIS_ID + " = '" + gisid + "'";
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
	public List<GuidingIdeologyofSchool> getGuidingIdeologyofSchool(int start, int end,
			String sortStr, String orderStr, Map<String, String> params,
			Date deadline) {
		String sql = " select tmp.* from ( " + " select * from "
				+ GuidingIdeologyofSchoolTable.TABLE_NAME + " where 1=1 ";
		if (deadline != null) {

			sql += String.format("and "+GuidingIdeologyofSchoolTable.GIS_DEADLINE+" like  '%s%%' ", deadline);
		}
		if (!(params == null || params.keySet().size() == 0)) {
			for (Object object : params.keySet()) {

				String key = object.toString();
				String value = params.get(key).toString();
				sql += String.format("and %s like  '%%%s%%' ", key, value);
			}
		}

		sql += " order by " + sortStr + " " + orderStr + " ) tmp limit "
				+ start + " ," + end;

		System.out.println(sql);

		List<GuidingIdeologyofSchool> guidingIdeologyofSchools = new ArrayList<GuidingIdeologyofSchool>();
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		try {
			System.out.println(sql);
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				int gis_id = resultSet.getInt(GuidingIdeologyofSchoolTable.GIS_ID);
				String gis_mottocontent = resultSet.getString(GuidingIdeologyofSchoolTable.GIS_MOTTOCONTENT);
				String gis_mottoremark = resultSet.getString(GuidingIdeologyofSchoolTable.GIS_MOTTOREMARK);
				String gis_positiongoalcontent = resultSet.getString(GuidingIdeologyofSchoolTable.GIS_POSITIONGOALCONTENT);
				String gis_positiongoalremark = resultSet.getString(GuidingIdeologyofSchoolTable.GIS_POSITIONGOALREMARK);
				String gis_strategy = resultSet.getString(GuidingIdeologyofSchoolTable.GIS_STRATEGY);
				String gis_discipline = resultSet.getString(GuidingIdeologyofSchoolTable.GIS_DISCIPLINE);
				String gis_professional = resultSet.getString(GuidingIdeologyofSchoolTable.GIS_PROFESSIONAL);
				String gis_teacher = resultSet.getString(GuidingIdeologyofSchoolTable.GIS_TEACHER);

				int gis_serialnumber = resultSet.getInt(GuidingIdeologyofSchoolTable.GIS_SERIALNUMBER);
				Date gis_deadline = resultSet.getDate(GuidingIdeologyofSchoolTable.GIS_DEADLINE);
				String gis_comments = resultSet.getString(GuidingIdeologyofSchoolTable.GIS_COMMENTS);
				String gis_college = resultSet.getString(GuidingIdeologyofSchoolTable.GIS_COLLEGE);
				int isnull = resultSet.getInt(GuidingIdeologyofSchoolTable.ISNULL);
				GuidingIdeologyofSchool gis = new GuidingIdeologyofSchool(gis_id, gis_mottocontent,
						gis_mottoremark, gis_positiongoalcontent,
						gis_positiongoalremark, gis_strategy,
						gis_discipline, gis_professional, gis_teacher,
						gis_serialnumber, gis_deadline, gis_college,
						gis_comments, isnull);

				guidingIdeologyofSchools.add(gis);
			}
			return guidingIdeologyofSchools;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
	}
	
	@Override
	public void deleteByCollegeandDeadline(String college, Date deadline)
			throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();
		String sql = "delete from " + GuidingIdeologyofSchoolTable.TABLE_NAME
				+ " where " + GuidingIdeologyofSchoolTable.GIS_COLLEGE + " = '" + college + "'" +" and gis_deadline is null ";
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
	public List<GuidingIdeologyofSchool> getAllGuidingIdeologyofSchool() {
		String sql = " select * from " + GuidingIdeologyofSchoolTable.TABLE_NAME
				+ " where 1=1 " + " order by " + GuidingIdeologyofSchoolTable.GIS_ID;
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
			List<GuidingIdeologyofSchool> guidingIdeologyofSchoolList = new ArrayList<GuidingIdeologyofSchool>();
			while (resultSet.next()) {
				int gis_id = resultSet.getInt(GuidingIdeologyofSchoolTable.GIS_ID);
				String gis_mottocontent = resultSet.getString(GuidingIdeologyofSchoolTable.GIS_MOTTOCONTENT);
				String gis_mottoremark = resultSet.getString(GuidingIdeologyofSchoolTable.GIS_MOTTOREMARK);
				String gis_positiongoalcontent = resultSet.getString(GuidingIdeologyofSchoolTable.GIS_POSITIONGOALCONTENT);
				String gis_positiongoalremark = resultSet.getString(GuidingIdeologyofSchoolTable.GIS_POSITIONGOALREMARK);
				String gis_strategy = resultSet.getString(GuidingIdeologyofSchoolTable.GIS_STRATEGY);
				String gis_discipline = resultSet.getString(GuidingIdeologyofSchoolTable.GIS_DISCIPLINE);
				String gis_professional = resultSet.getString(GuidingIdeologyofSchoolTable.GIS_PROFESSIONAL);
				String gis_teacher = resultSet.getString(GuidingIdeologyofSchoolTable.GIS_TEACHER);

				int gis_serialnumber = resultSet.getInt(GuidingIdeologyofSchoolTable.GIS_SERIALNUMBER);
				Date gis_deadline = resultSet.getDate(GuidingIdeologyofSchoolTable.GIS_DEADLINE);
				String gis_comments = resultSet.getString(GuidingIdeologyofSchoolTable.GIS_COMMENTS);
				String gis_college = resultSet.getString(GuidingIdeologyofSchoolTable.GIS_COLLEGE);
				int isnull = resultSet.getInt(GuidingIdeologyofSchoolTable.ISNULL);
				GuidingIdeologyofSchool gis = new GuidingIdeologyofSchool(gis_id, gis_mottocontent,
						gis_mottoremark, gis_positiongoalcontent,
						gis_positiongoalremark, gis_strategy,
						gis_discipline, gis_professional, gis_teacher,
						gis_serialnumber, gis_deadline, gis_college,
						gis_comments, isnull);
				
				guidingIdeologyofSchoolList.add(gis);
			}
			return guidingIdeologyofSchoolList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
	}
}
