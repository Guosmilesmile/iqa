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

import cn.edu.xmu.dao.ListenSituationDao;
import cn.edu.xmu.entity.ListenSituation;
import cn.edu.xmu.entity.SchoolNet;
import cn.edu.xmu.table.ExternalTeacherTable;
import cn.edu.xmu.table.ListenSituationTable;
import cn.edu.xmu.table.SchoolNetTable;
import cn.edu.xmu.table.TeachingQualityTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;

/**
 * 
 * @author Luo
 * 党政干部听课情况 实体类功能接口  实体类功能 ——接口实现
 * date 2015-07-1
 */
public class ListenSituationDaoImpl extends BaseDaoImpl<ListenSituation> implements ListenSituationDao {

	@Override
	public int addRecord(ListenSituation ls) {
		int result = 0;

		String t_sql = "update " + ListenSituationTable.TABLE_NAME + " set "
				+ ListenSituationTable.LS_SERIALNUMBER + " = "
				+ ListenSituationTable.LS_SERIALNUMBER + " +1 where "
				+ ListenSituationTable.LS_SERIALNUMBER + ">=?";

		
		Connection connection2 = null;
		try {
			//连接池取得对象连接
			connection2 = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		PreparedStatement t_pstmt = null;
		try {
			//获取操作对象
			t_pstmt = connection2.prepareStatement(t_sql);
			t_pstmt.setInt(1, ls.getLs_serialnumber());
			
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
		String sql = "insert into " + ListenSituationTable.TABLE_NAME + "("
				+ ListenSituationTable.LS_IMPORTCOLLEGE
				+ "," + ListenSituationTable.LS_PERSONNUMBER+ ","
				+ ListenSituationTable.LS_LESSONNUMBER + ","
				+ ListenSituationTable.LS_PERSONNUMBER2 + ","
				+ ListenSituationTable.LS_LESSONNUMBER2 + ","
				+ ListenSituationTable.LS_EXCELLENT + ","
				+ ListenSituationTable.LS_GOOD + ","
				+ ListenSituationTable.LS_MEDIUM + ","
				+ ListenSituationTable.LS_BAD + ","
				+ ListenSituationTable.LS_SERIALNUMBER + ","
				+ ListenSituationTable.LS_COLLEGE + ","
				+ ListenSituationTable.LS_COMMENTS+ ","
				+ "isnull"
				+ ")values(?,?,?,?,?,?,?,?,?,?,?,?,?)";

		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, ls.getLs_importcollege());
			pstmt.setInt(2, ls.getLs_personnumber());
			pstmt.setInt(3, ls.getLs_lessonnumber());
			pstmt.setInt(4, ls.getLs_personnumber2());
			pstmt.setInt(5, ls.getLs_lessonnumber2());
			pstmt.setInt(6, ls.getLs_excellent());
			pstmt.setInt(7, ls.getLs_good());
			pstmt.setInt(8, ls.getLs_medium());
			pstmt.setInt(9, ls.getLs_bad());
			pstmt.setInt(10, ls.getLs_serialnumber());
			pstmt.setString(11, ls.getLs_college());
			pstmt.setString(12, ls.getLs_comments());
			pstmt.setInt(13, ls.getIsnull());
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
	public boolean batchDelete(String[] lsids) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();

		for (String lsid : lsids) {
			String sql = "delete from " + ListenSituationTable.TABLE_NAME
					+ " where " + ListenSituationTable.LS_ID + " = '" + lsid + "'";
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
	public int alterListenSituation(Map<String, String> valueMap, String id) {
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(ListenSituationTable.LS_ID, id);
		int result = updateRecord(ListenSituationTable.TABLE_NAME, valueMap,
				condition);
		return result;
	}

	@Override
	public int getListenSituationCount(Map queryParams) {
		int count = 0;
		//获取条件的语句
		String sql = "select count(*) from " + ListenSituationTable.TABLE_NAME
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
		sql += String.format(" or %s ='%s'", ListenSituationTable.LS_COLLEGE, "");
		
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
	public List<ListenSituation> getAllListenSituation(int start, int end,
			String sortStr, String orderStr, Map queryParams) {
		String sql = " select tmp.* from ( " + " select * from "
				+ ListenSituationTable.TABLE_NAME + " where 1=1 ";
		
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
		
		List<ListenSituation> lss = new ArrayList<ListenSituation>();
		try {
			
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int ls_id = resultSet.getInt(ListenSituationTable.LS_ID);
				String ls_importcollege = resultSet.getString(ListenSituationTable.LS_IMPORTCOLLEGE);
				Integer ls_personnumber = resultSet.getInt(ListenSituationTable.LS_PERSONNUMBER);
				if(ls_personnumber==-999)
					ls_personnumber = null;
				Integer ls_lessonnumber = resultSet.getInt(ListenSituationTable.LS_LESSONNUMBER);
				if(ls_lessonnumber==-999)
					ls_lessonnumber = null;
				Integer ls_personnumber2 = resultSet.getInt(ListenSituationTable.LS_PERSONNUMBER2);
				if(ls_personnumber2==-999)
					ls_personnumber2 = null;
				Integer ls_lessonnumber2 = resultSet.getInt(ListenSituationTable.LS_LESSONNUMBER2);
				if(ls_lessonnumber2==-999)
					ls_lessonnumber2 = null;
				Integer ls_excellent = resultSet.getInt(ListenSituationTable.LS_EXCELLENT);
				if(ls_excellent==-999)
					ls_excellent = null;
				Integer ls_good = resultSet.getInt(ListenSituationTable.LS_GOOD);
				if(ls_good==-999)
					ls_good = null;
				Integer ls_medium = resultSet.getInt(ListenSituationTable.LS_MEDIUM);
				if(ls_medium==-999)
					ls_medium = null;
				Integer ls_bad = resultSet.getInt(ListenSituationTable.LS_BAD);
				if(ls_bad==-999)
					ls_bad = null;
				
				int ls_serialnumber = resultSet.getInt(ListenSituationTable.LS_SERIALNUMBER);

				String ls_comments = resultSet.getString(ListenSituationTable.LS_COMMENTS);
				String ls_college = resultSet.getString(ListenSituationTable.LS_COLLEGE);
				
				int isnull = resultSet.getInt(ListenSituationTable.ISNULL);
				if(ls_comments==null)
					ls_comments="";
				
				ListenSituation ls = new ListenSituation(ls_id, ls_importcollege,
						ls_personnumber, ls_lessonnumber,ls_personnumber2,ls_lessonnumber2,ls_excellent,
						ls_good,ls_medium,ls_bad,ls_serialnumber, ls_comments,isnull,ls_college);
				
				lss.add(ls);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return lss;
	}

	@Override
	public List<ListenSituation> getAllListenSituation() {
		String sql = " select * from "
				+ ListenSituationTable.TABLE_NAME + " where 1=1 ";
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		List<ListenSituation> lss = new ArrayList<ListenSituation>();
		try {
			
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int ls_id = resultSet.getInt(ListenSituationTable.LS_ID);
				String ls_importcollege = resultSet.getString(ListenSituationTable.LS_IMPORTCOLLEGE);
				Integer ls_personnumber = resultSet.getInt(ListenSituationTable.LS_PERSONNUMBER);
				if(ls_personnumber==-999)
					ls_personnumber = null;
				Integer ls_lessonnumber = resultSet.getInt(ListenSituationTable.LS_LESSONNUMBER);
				if(ls_lessonnumber==-999)
					ls_lessonnumber = null;
				Integer ls_personnumber2 = resultSet.getInt(ListenSituationTable.LS_PERSONNUMBER2);
				if(ls_personnumber2==-999)
					ls_personnumber2 = null;
				Integer ls_lessonnumber2 = resultSet.getInt(ListenSituationTable.LS_LESSONNUMBER2);
				if(ls_lessonnumber2==-999)
					ls_lessonnumber2 = null;
				Integer ls_excellent = resultSet.getInt(ListenSituationTable.LS_EXCELLENT);
				if(ls_excellent==-999)
					ls_excellent = null;
				Integer ls_good = resultSet.getInt(ListenSituationTable.LS_GOOD);
				if(ls_good==-999)
					ls_good = null;
				Integer ls_medium = resultSet.getInt(ListenSituationTable.LS_MEDIUM);
				if(ls_medium==-999)
					ls_medium = null;
				Integer ls_bad = resultSet.getInt(ListenSituationTable.LS_BAD);
				if(ls_bad==-999)
					ls_bad = null;
				
				int ls_serialnumber = resultSet.getInt(ListenSituationTable.LS_SERIALNUMBER);

				String ls_comments = resultSet.getString(ListenSituationTable.LS_COMMENTS);
				int isnull = resultSet.getInt(ListenSituationTable.ISNULL);
				String ls_college = resultSet.getString(ListenSituationTable.LS_COLLEGE);
				
				if(ls_comments==null)
					ls_comments="";
				
				ListenSituation ls = new ListenSituation(ls_id, ls_importcollege,
						ls_personnumber, ls_lessonnumber,ls_personnumber2,ls_lessonnumber2,ls_excellent,
						ls_good,ls_medium,ls_bad,ls_serialnumber, ls_comments,isnull,ls_college);
				
				lss.add(ls);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return lss;
	}

	@Override
	public void deleteByCollegeandDeadline(String college, Date deadline)
			throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();
		String sql = "delete from " + ListenSituationTable.TABLE_NAME
				+ " where " + ListenSituationTable.LS_COLLEGE + " = '" + college + "'" +" and ls_deadline is null ";
		System.out.println(sql);
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils_DBCP.release(connection, stmt, null);
		}
		
	}

}
