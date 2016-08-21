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

import cn.edu.xmu.dao.GraduationSituationDao;
import cn.edu.xmu.entity.GraduationSituation;
import cn.edu.xmu.table.GraduationSituationTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;

/**
 * 
 * @author Luo 附表6-1-9-1XXXX届本科毕业生分专业毕业情况 实体类功能 ——接口实现 date 2015-07-10
 */
public class GraduationSituationDaoImpl extends
		BaseDaoImpl<GraduationSituation> implements GraduationSituationDao {

	@Override
	public int addRecord(GraduationSituation gs) {
		int result = 0;

		String t_sql = "update " + GraduationSituationTable.TABLE_NAME
				+ " set " + GraduationSituationTable.GS_SERIALNUMBER + " = "
				+ GraduationSituationTable.GS_SERIALNUMBER + " +1 where "
				+ GraduationSituationTable.GS_SERIALNUMBER + ">=?";

		Connection connection2 = null;
		try {
			// 连接池取得对象连接
			connection2 = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		PreparedStatement t_pstmt = null;
		try {
			// 获取操作对象
			t_pstmt = connection2.prepareStatement(t_sql);
			t_pstmt.setInt(1, gs.getGs_serialnumber());

			// 执行插入操作并更新
			result = t_pstmt.executeUpdate();

		} catch (SQLException e) {
			// 事务回滚，不做插入操作
			e.printStackTrace();
			return 0;
		} finally {
			JdbcUtils_DBCP.release(connection2, t_pstmt, null);
		}

		// 执行插入操作的语句
		String sql = "insert into " + GraduationSituationTable.TABLE_NAME + "("
				+ GraduationSituationTable.GS_IMPORTCOLLEGE + ","
				+ GraduationSituationTable.GS_MAJOR + ","
				+ GraduationSituationTable.GS_MAJORCODE + ","
				+ GraduationSituationTable.GS_EDUCTIONALSYSTME + ","
				+ GraduationSituationTable.GS_DEGREECATEGORY + ","
				+ GraduationSituationTable.GS_GRADUATIONNUMBER + ","
				+ GraduationSituationTable.GS_TOTAL + ","
				+ GraduationSituationTable.GS_DEGREEYES + ","
				+ GraduationSituationTable.GS_DEGREENO + ","
				+ GraduationSituationTable.GS_NUMBEROFCOMPLETION + ","
				+ GraduationSituationTable.GS_NUMBEROFINCOMPLETE + ","
				+ GraduationSituationTable.GS_MINORCERTIFICATECOUNT + ","
				+ GraduationSituationTable.GS_MINORDEGREECOUNT + ","
				+ GraduationSituationTable.GS_SERIALNUMBER + ","
				+ GraduationSituationTable.GS_COLLEGE + ","
				+ GraduationSituationTable.GS_COMMENTS+","
				+ GraduationSituationTable.ISNULL
				+ ")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, gs.getGs_importcollege());
			pstmt.setString(2, gs.getGs_major());
			pstmt.setString(3, gs.getGs_majorcode());
			pstmt.setInt(4, gs.getGs_eductionalsystme());
			pstmt.setString(5, gs.getGs_degreecategory());
			pstmt.setInt(6, gs.getGs_graduationnumber());
			pstmt.setInt(7, gs.getGs_total());
			pstmt.setInt(8, gs.getGs_degreeyes());
			pstmt.setInt(9, gs.getGs_degreeno());
			pstmt.setInt(10, gs.getGs_numberofcompletion());
			pstmt.setInt(11, gs.getGs_numberofincomplete());
			pstmt.setInt(12, gs.getGs_minorcertificatecount());
			pstmt.setInt(13, gs.getGs_minordegreecount());
			pstmt.setInt(14, gs.getGs_serialnumber());
			pstmt.setString(15, gs.getGs_college());
			pstmt.setString(16, gs.getGs_comments());
			pstmt.setInt(17, gs.getIsnull());
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			// 事务回滚。不做插入操作
			e.printStackTrace();
			result = -1;

		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, null);
		}
		return result;
	}

	@Override
	public boolean batchDelete(String[] gsids) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();

		for (String gsid : gsids) {
			String sql = "delete from " + GraduationSituationTable.TABLE_NAME
					+ " where " + GraduationSituationTable.GS_ID + " = '"
					+ gsid + "'";
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
	public int alterGraduationSituation(Map<String, String> valueMap, String id) {
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(GraduationSituationTable.GS_ID, id);
		int result = updateRecord(GraduationSituationTable.TABLE_NAME,
				valueMap, condition);
		return result;
	}

	@Override
	public int getGraduationSituationCount(Map queryParams) {
		int count = 0;
		// 获取条件的语句
		String sql = "select count(*) from "
				+ GraduationSituationTable.TABLE_NAME + " where 1 = 1";
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
		sql += String.format(" or %s ='%s'",
				GraduationSituationTable.GS_COLLEGE, "");

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
	public List<GraduationSituation> getAllGraduationSituation(int start,
			int end, String sortStr, String orderStr, Map queryParams) {
		String sql = " select tmp.* from ( " + " select * from "
				+ GraduationSituationTable.TABLE_NAME + " where 1=1 ";

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

		List<GraduationSituation> gss = new ArrayList<GraduationSituation>();
		try {

			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int gs_id = resultSet.getInt(GraduationSituationTable.GS_ID);
				String gs_importcollege = resultSet
						.getString(GraduationSituationTable.GS_IMPORTCOLLEGE);
				String gs_major = resultSet
						.getString(GraduationSituationTable.GS_MAJOR);
				String gs_majorcode = resultSet
						.getString(GraduationSituationTable.GS_MAJORCODE);
				Integer gs_eductionalsystme = resultSet
						.getInt(GraduationSituationTable.GS_EDUCTIONALSYSTME);
				if(gs_eductionalsystme==-999)
					gs_eductionalsystme = null;
				
				String gs_degreecategory = resultSet
						.getString(GraduationSituationTable.GS_DEGREECATEGORY);
				Integer gs_graduationnumber = resultSet
						.getInt(GraduationSituationTable.GS_GRADUATIONNUMBER);
				if(gs_graduationnumber==-999)
					gs_graduationnumber = null;
				
				Integer gs_total = resultSet
						.getInt(GraduationSituationTable.GS_TOTAL);
				if(gs_total==-999)
					gs_total = null;
				
				Integer gs_degreeyes = resultSet
						.getInt(GraduationSituationTable.GS_DEGREEYES);
				if(gs_degreeyes==-999)
					gs_degreeyes = null;
				
				Integer gs_degreeno = resultSet
						.getInt(GraduationSituationTable.GS_DEGREENO);
				if(gs_degreeno==-999)
					gs_degreeno = null;
				
				Integer gs_numberofcompletion = resultSet
						.getInt(GraduationSituationTable.GS_NUMBEROFCOMPLETION);
				if(gs_numberofcompletion==-999)
					gs_numberofcompletion = null;
				
				Integer gs_numberofincomplete = resultSet
						.getInt(GraduationSituationTable.GS_NUMBEROFINCOMPLETE);
				if(gs_numberofincomplete==-999)
					gs_numberofincomplete = null;
				
				Integer gs_minorcertificatecount = resultSet
						.getInt(GraduationSituationTable.GS_MINORCERTIFICATECOUNT);
				if(gs_minorcertificatecount==-999)
					gs_minorcertificatecount = null;
				
				Integer gs_minordegreecount = resultSet
						.getInt(GraduationSituationTable.GS_MINORDEGREECOUNT);
				if(gs_minordegreecount==-999)
					gs_minordegreecount = null;
				
				int gs_serialnumber = resultSet
						.getInt(GraduationSituationTable.GS_SERIALNUMBER);
				String gs_comments = resultSet
						.getString(GraduationSituationTable.GS_COMMENTS);
				int isnull = resultSet.getInt(GraduationSituationTable.ISNULL);
				if(gs_comments==null)
					gs_comments="";

				GraduationSituation gs = new GraduationSituation(gs_id,
						gs_importcollege, gs_major, gs_majorcode,
						gs_eductionalsystme, gs_degreecategory,
						gs_graduationnumber, gs_total, gs_degreeyes,
						gs_degreeno, gs_numberofcompletion,
						gs_numberofincomplete, gs_minorcertificatecount,
						gs_minordegreecount, gs_serialnumber, gs_comments,isnull);

				gss.add(gs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return gss;
	}

	@Override
	public List<GraduationSituation> getAllGraduationSituation() {
		String sql = " select * from "
				+ GraduationSituationTable.TABLE_NAME + " where 1=1 ";


		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		List<GraduationSituation> gss = new ArrayList<GraduationSituation>();
		try {

			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int gs_id = resultSet.getInt(GraduationSituationTable.GS_ID);
				String gs_importcollege = resultSet
						.getString(GraduationSituationTable.GS_IMPORTCOLLEGE);
				String gs_major = resultSet
						.getString(GraduationSituationTable.GS_MAJOR);
				String gs_majorcode = resultSet
						.getString(GraduationSituationTable.GS_MAJORCODE);
				Integer gs_eductionalsystme = resultSet
						.getInt(GraduationSituationTable.GS_EDUCTIONALSYSTME);
				if(gs_eductionalsystme==-999)
					gs_eductionalsystme = null;
				
				String gs_degreecategory = resultSet
						.getString(GraduationSituationTable.GS_DEGREECATEGORY);
				Integer gs_graduationnumber = resultSet
						.getInt(GraduationSituationTable.GS_GRADUATIONNUMBER);
				if(gs_graduationnumber==-999)
					gs_graduationnumber = null;
				
				Integer gs_total = resultSet
						.getInt(GraduationSituationTable.GS_TOTAL);
				if(gs_total==-999)
					gs_total = null;
				
				Integer gs_degreeyes = resultSet
						.getInt(GraduationSituationTable.GS_DEGREEYES);
				if(gs_degreeyes==-999)
					gs_degreeyes = null;
				
				Integer gs_degreeno = resultSet
						.getInt(GraduationSituationTable.GS_DEGREENO);
				if(gs_eductionalsystme==-999)
					gs_eductionalsystme = null;
				
				Integer gs_numberofcompletion = resultSet
						.getInt(GraduationSituationTable.GS_NUMBEROFCOMPLETION);
				if(gs_numberofcompletion==-999)
					gs_numberofcompletion = null;
				
				Integer gs_numberofincomplete = resultSet
						.getInt(GraduationSituationTable.GS_NUMBEROFINCOMPLETE);
				if(gs_numberofincomplete==-999)
					gs_numberofincomplete = null;
				
				Integer gs_minorcertificatecount = resultSet
						.getInt(GraduationSituationTable.GS_MINORCERTIFICATECOUNT);
				if(gs_minorcertificatecount==-999)
					gs_minorcertificatecount = null;
				
				Integer gs_minordegreecount = resultSet
						.getInt(GraduationSituationTable.GS_MINORDEGREECOUNT);
				if(gs_eductionalsystme==-999)
					gs_eductionalsystme = null;
				
				int gs_serialnumber = resultSet
						.getInt(GraduationSituationTable.GS_SERIALNUMBER);
				String gs_comments = resultSet
						.getString(GraduationSituationTable.GS_COMMENTS);
				int isnull = resultSet.getInt(GraduationSituationTable.ISNULL);
				if(gs_comments==null)
					gs_comments="";

				GraduationSituation gs = new GraduationSituation(gs_id,
						gs_importcollege, gs_major, gs_majorcode,
						gs_eductionalsystme, gs_degreecategory,
						gs_graduationnumber, gs_total, gs_degreeyes,
						gs_degreeno, gs_numberofcompletion,
						gs_numberofincomplete, gs_minorcertificatecount,
						gs_minordegreecount, gs_serialnumber, gs_comments,isnull);

				gss.add(gs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return gss;
	}

	@Override
	public void deleteByCollegeandDeadline(String college, Date deadline)
			throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();
		String sql = "delete from " + GraduationSituationTable.TABLE_NAME
				+ " where " + GraduationSituationTable.GS_COLLEGE + " = '" + college + "'" +" and gs_deadline is null ";
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
	public GraduationSituation getGraduationSituationByMajor(String majorCode) {
		// TODO Auto-generated method stub
		String sql = " select * from "
				+ GraduationSituationTable.TABLE_NAME + " where " +  GraduationSituationTable.GS_MAJORCODE +" = "+ majorCode;

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		List<GraduationSituation> gss = new ArrayList<GraduationSituation>();
		try {

			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int gs_id = resultSet.getInt(GraduationSituationTable.GS_ID);
				String gs_importcollege = resultSet
						.getString(GraduationSituationTable.GS_IMPORTCOLLEGE);
				String gs_major = resultSet
						.getString(GraduationSituationTable.GS_MAJOR);
				String gs_majorcode = resultSet
						.getString(GraduationSituationTable.GS_MAJORCODE);
				Integer gs_eductionalsystme = resultSet
						.getInt(GraduationSituationTable.GS_EDUCTIONALSYSTME);
				if(gs_eductionalsystme==-999)
					gs_eductionalsystme = null;
				
				String gs_degreecategory = resultSet
						.getString(GraduationSituationTable.GS_DEGREECATEGORY);
				Integer gs_graduationnumber = resultSet
						.getInt(GraduationSituationTable.GS_GRADUATIONNUMBER);
				if(gs_graduationnumber==-999)
					gs_graduationnumber = null;
				
				Integer gs_total = resultSet
						.getInt(GraduationSituationTable.GS_TOTAL);
				if(gs_total==-999)
					gs_total = null;
				
				Integer gs_degreeyes = resultSet
						.getInt(GraduationSituationTable.GS_DEGREEYES);
				if(gs_degreeyes==-999)
					gs_degreeyes = null;
				
				Integer gs_degreeno = resultSet
						.getInt(GraduationSituationTable.GS_DEGREENO);
				if(gs_degreeno==-999)
					gs_degreeno = null;
				
				Integer gs_numberofcompletion = resultSet
						.getInt(GraduationSituationTable.GS_NUMBEROFCOMPLETION);
				if(gs_numberofcompletion==-999)
					gs_numberofcompletion = null;
				
				Integer gs_numberofincomplete = resultSet
						.getInt(GraduationSituationTable.GS_NUMBEROFINCOMPLETE);
				if(gs_numberofincomplete==-999)
					gs_numberofincomplete = null;
				
				Integer gs_minorcertificatecount = resultSet
						.getInt(GraduationSituationTable.GS_MINORCERTIFICATECOUNT);
				if(gs_minorcertificatecount==-999)
					gs_minorcertificatecount = null;
				
				Integer gs_minordegreecount = resultSet
						.getInt(GraduationSituationTable.GS_MINORDEGREECOUNT);
				if(gs_minordegreecount==-999)
					gs_minordegreecount = null;
				
				int gs_serialnumber = resultSet
						.getInt(GraduationSituationTable.GS_SERIALNUMBER);
				String gs_comments = resultSet
						.getString(GraduationSituationTable.GS_COMMENTS);
				int isnull = resultSet.getInt(GraduationSituationTable.ISNULL);
				if(gs_comments==null)
					gs_comments="";

				GraduationSituation gs = new GraduationSituation(gs_id,
						gs_importcollege, gs_major, gs_majorcode,
						gs_eductionalsystme, gs_degreecategory,
						gs_graduationnumber, gs_total, gs_degreeyes,
						gs_degreeno, gs_numberofcompletion,
						gs_numberofincomplete, gs_minorcertificatecount,
						gs_minordegreecount, gs_serialnumber, gs_comments,isnull);

				gss.add(gs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		if(gss.size() != 0)
		   return gss.get(0);
		else return null;
	}

}
