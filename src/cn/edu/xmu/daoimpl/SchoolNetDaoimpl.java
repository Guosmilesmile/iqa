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

import cn.edu.xmu.dao.SchoolNetDao;
import cn.edu.xmu.entity.SchoolIntroduction;
import cn.edu.xmu.entity.SchoolNet;
import cn.edu.xmu.table.SchoolIntroductionTable;
import cn.edu.xmu.table.SchoolNetTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;
/**
 * 
 * @author Luo
 * 校园网  实体类功能 ——接口实现
 * date 2015-07-03
 */
public class SchoolNetDaoimpl extends BaseDaoImpl<SchoolNet> implements SchoolNetDao {

	@Override
	public int addRecord(SchoolNet sn) {
		int result = 0;

		String t_sql = "update " + SchoolNetTable.TABLE_NAME + " set "
				+ SchoolNetTable.SN_SERIALNUMBER + " = "
				+ SchoolNetTable.SN_SERIALNUMBER + " +1 where "
				+ SchoolNetTable.SN_SERIALNUMBER + ">=?";

		
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
			t_pstmt.setInt(1, sn.getSn_serialnumber());
			
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
		String sql = "insert into " + SchoolNetTable.TABLE_NAME + "("
				+ SchoolNetTable.SN_BACKBONEBANDWIDTH
				+ "," + SchoolNetTable.SN_EXPORTBANDWIDTH+ ","
				+ SchoolNetTable.SN_INFORMATIONCOUNT + ","
				+ SchoolNetTable.SN_SERIALNUMBER + ","
				+ SchoolNetTable.SN_COLLEGE + ","
				+ SchoolNetTable.SN_COMMENTS+","
				+ SchoolNetTable.ISNULL
				+ ")values(?,?,?,?,?,?,?)";

		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, sn.getSn_backbonebandwidth());
			pstmt.setInt(2, sn.getSn_exportbandwidth());
			pstmt.setInt(3, sn.getSn_informationcount());
			pstmt.setInt(4, sn.getSn_serialnumber());
			pstmt.setString(5, sn.getSn_college());
			pstmt.setString(6, sn.getSn_comments());
			pstmt.setInt(7, sn.getIsnull());
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
	public boolean batchDelete(String[] snids) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();

		for (String snid : snids) {
			String sql = "delete from " + SchoolNetTable.TABLE_NAME
					+ " where " + SchoolNetTable.SN_ID + " = '" + snid + "'";
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
	public int alterSchoolNet(Map<String, String> valueMap, String id) {
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(SchoolNetTable.SN_ID, id);
		int result = updateRecord(SchoolNetTable.TABLE_NAME, valueMap,
				condition);
		return result;
	}

	@Override
	public int getSchoolNetCount(Map queryParams) {
		int count = 0;
		//获取条件的语句
		String sql = "select count(*) from " + SchoolNetTable.TABLE_NAME
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
		sql += String.format(" or %s ='%s'", SchoolNetTable.SN_COLLEGE, "");
		
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
	public List<SchoolNet> getAllSchoolNet(int start, int end, String sortStr,
			String orderStr, Map queryParams) {
		String sql = " select tmp.* from ( " + " select * from "
				+ SchoolNetTable.TABLE_NAME + " where 1=1 ";
		
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

		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		List<SchoolNet> sns = new ArrayList<SchoolNet>();
		try {
			
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int sn_id = resultSet.getInt(SchoolNetTable.SN_ID);
				Integer sn_backbonebandwidth = resultSet.getInt(SchoolNetTable.SN_BACKBONEBANDWIDTH);
				if(sn_backbonebandwidth==-999)
					sn_backbonebandwidth = null;
				
				Integer sn_exportbandwidth = resultSet.getInt(SchoolNetTable.SN_EXPORTBANDWIDTH);
				if(sn_exportbandwidth==-999)
					sn_exportbandwidth = null;
				
				Integer sn_informationcount = resultSet.getInt(SchoolNetTable.SN_INFORMATIONCOUNT);
				if(sn_informationcount==-999)
					sn_informationcount = null;
				
				int sn_serialnumber = resultSet.getInt(SchoolNetTable.SN_SERIALNUMBER);
				String sn_comments = resultSet.getString(SchoolNetTable.SN_COMMENTS);
				String sn_college = resultSet.getString(SchoolNetTable.SN_COLLEGE);
				int isnull = resultSet.getInt(SchoolNetTable.ISNULL);
				if(sn_comments==null)
					sn_comments="";
				
				
				SchoolNet sn = new SchoolNet(sn_id, sn_backbonebandwidth,
						sn_exportbandwidth, sn_informationcount, sn_serialnumber, sn_comments,isnull,sn_college);
				
				sns.add(sn);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return sns;
	}

	@Override
	public List<SchoolNet> getAllSchoolNet() {
		String sql = "select * from "
				+ SchoolNetTable.TABLE_NAME + " where 1=1 ";

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		List<SchoolNet> sns = new ArrayList<SchoolNet>();
		try {
			
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int sn_id = resultSet.getInt(SchoolNetTable.SN_ID);
				Integer sn_backbonebandwidth = resultSet.getInt(SchoolNetTable.SN_BACKBONEBANDWIDTH);
				if(sn_backbonebandwidth==-999)
					sn_backbonebandwidth = null;
				
				Integer sn_exportbandwidth = resultSet.getInt(SchoolNetTable.SN_EXPORTBANDWIDTH);
				if(sn_exportbandwidth==-999)
					sn_exportbandwidth = null;
				
				Integer sn_informationcount = resultSet.getInt(SchoolNetTable.SN_INFORMATIONCOUNT);
				if(sn_informationcount==-999)
					sn_informationcount = null;
				
				int sn_serialnumber = resultSet.getInt(SchoolNetTable.SN_SERIALNUMBER);
				String sn_comments = resultSet.getString(SchoolNetTable.SN_COMMENTS);
				String sn_college = resultSet.getString(SchoolNetTable.SN_COLLEGE);
				int isnull = resultSet.getInt(SchoolNetTable.ISNULL);
				if(sn_comments==null)
					sn_comments="";
				
				
				SchoolNet sn = new SchoolNet(sn_id, sn_backbonebandwidth,
						sn_exportbandwidth, sn_informationcount, sn_serialnumber, sn_comments,isnull,sn_college);
				
				sns.add(sn);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return sns;
	}

	@Override
	public void deleteByCollegeandDeadline(String college, Date deadline)
			throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();
		String sql = "delete from " + SchoolNetTable.TABLE_NAME
				+ " where " + SchoolNetTable.SN_COLLEGE + " = '" + college + "'" +" and sn_deadline is null ";
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
