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

import cn.edu.xmu.dao.CourseBuildInfoDao;
import cn.edu.xmu.entity.CourseBuildInfo;
import cn.edu.xmu.table.CourseBuildInfoTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;

/**
 * 附表5-2-3课程建设情况（时点）
 * @author yue
 *
 */
public class CourseBuildInfoDaoImpl extends BaseDaoImpl<CourseBuildInfo> implements CourseBuildInfoDao{

	@Override
	public int addCourseBuildInfoRecord(CourseBuildInfo cbi) {
		int result = 0;
		String t_sql = "update " + CourseBuildInfoTable.TABLE_NAME + " set "
				+CourseBuildInfoTable.CBI_SERIALNUMBER + " = "
				+CourseBuildInfoTable.CBI_SERIALNUMBER + " +1 where "
				+CourseBuildInfoTable.CBI_SERIALNUMBER + " >=?";
		Connection connection2 = null;
		try{
			//连接池获取对象连接
			connection2 = JdbcUtils_DBCP.getConnection();
			
		}catch(SQLException e1){
			e1.printStackTrace();
		}
		PreparedStatement t_pstmt = null;
		try {
			//获取操作对象
			t_pstmt = connection2.prepareStatement(t_sql);
			t_pstmt.setInt(1, cbi.getCbi_serialnumber());
			
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
		String sql = "insert into " + CourseBuildInfoTable.TABLE_NAME + "("
				+ CourseBuildInfoTable.CBI_INSTITUTE + ","
				+ CourseBuildInfoTable.CBI_NAME + ","
				+ CourseBuildInfoTable.CBI_CHARGEPERSON + ","
				+ CourseBuildInfoTable.CBI_TYPE + ","
				+ CourseBuildInfoTable.CBI_GRADE + ","
				+ CourseBuildInfoTable.CBI_APPROVALTIME + ","
				+ CourseBuildInfoTable.CBI_SERIALNUMBER + ","
				+ CourseBuildInfoTable.CBI_COLLEGE + ","
				+ CourseBuildInfoTable.CBI_COMMENTS + ","
				+ CourseBuildInfoTable.ISNULL
				+ ")values(?,?,?,?,?,?,?,?,?,?)";

		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, cbi.getCbi_institute());
			pstmt.setString(2, cbi.getCbi_name());
			pstmt.setString(3, cbi.getCbi_chargeperson());
			pstmt.setString(4, cbi.getCbi_type());
			pstmt.setString(5, cbi.getCbi_grade());
			pstmt.setDate(6, cbi.getCbi_approvaltime());
			pstmt.setInt(7, cbi.getCbi_serialnumber());
			pstmt.setString(8,cbi.getCbi_college());
			pstmt.setString(9, cbi.getCbi_comments());
			pstmt.setInt(10, cbi.getIsnull());
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
	public boolean batchDelete(String[] cbiids) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();
		
		for(String cbiid:cbiids)
		{
			String sql = "delete from " + CourseBuildInfoTable.TABLE_NAME
					+ " where " + CourseBuildInfoTable.CBI_ID + " = '" +cbiid + "'";
			try{
				stmt.executeUpdate(sql);
			}catch(SQLException e){
				e.printStackTrace();
				return false;
			}
		}
		JdbcUtils_DBCP.release(connection, stmt, null);
		return true;
	}

	@Override
	public int alterCourseBuildInfo(Map<String, String> valueMap, String id) {
		Map<String, String> condition = new HashMap<String,String>();
		condition.put(CourseBuildInfoTable.CBI_ID, id);
		int result = updateRecord(CourseBuildInfoTable.TABLE_NAME, valueMap, condition);
		return result;
	}

	@Override
	public int getCourseBuildInfoCount(Map queryParams) {
		int count = 0;
		//获取条件的语句
		String sql = "select count(*) from " + CourseBuildInfoTable.TABLE_NAME
				+" where 1 = 1";
		System.out.println(sql);
		Connection connection = null;
		try{
			connection = JdbcUtils_DBCP.getConnection();
			
		}catch(SQLException e1){
			e1.printStackTrace();
			return -1;
		}
		if (queryParams != null)
		{
			for (Object object : queryParams.keySet())
			{
				String key = object.toString();
				String value = queryParams.get(key).toString();
				sql += String.format(" and  %s like  '%s%%' ", key, value);
			}
			// sql += String.format(" or %s ='%s'", "college","");
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
		}finally{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return count;
	}

	@Override
	public List<CourseBuildInfo> getCourseBuildInfo(int start, int end, String sortStr, String orderStr,
			Map<String, String> params, Date deadline) {
		String sql = " select tmp.* from ( " + " select * from "
				+ CourseBuildInfoTable.TABLE_NAME + " where 1=1 ";
		if (deadline != null) {

			sql += String.format("and "+CourseBuildInfoTable.CBI_DEADLINE+" like  '%s%%' ", deadline);
		}
		if (!(params == null || params.keySet().size() == 0)) {
			for (Object object : params.keySet()) {

				String key = object.toString();
				String value = params.get(key).toString();
				sql += String.format(" and %s like  '%%%s%%'  ", key, value);
			}
		}

		sql += " order by " + sortStr + " " + orderStr + " ) tmp limit "
				+ start + " ," + end;

		System.out.println(sql);
		
		List<CourseBuildInfo> cbis = new ArrayList<CourseBuildInfo>();
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
				
		try {
			
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			Date tempDate = Date.valueOf("1800-1-1");
			
			while (resultSet.next()) {
				int cbi_id = resultSet.getInt(CourseBuildInfoTable.CBI_ID);
				String cbi_institute = resultSet.getString(CourseBuildInfoTable.CBI_INSTITUTE);
				String cbi_name = resultSet.getString(CourseBuildInfoTable.CBI_NAME);
				String cbi_chargeperson = resultSet.getString(CourseBuildInfoTable.CBI_CHARGEPERSON);
				String cbi_type = resultSet.getString(CourseBuildInfoTable.CBI_TYPE);
				String cbi_grade = resultSet.getString(CourseBuildInfoTable.CBI_GRADE);
				Date cbi_approvaltime = resultSet.getDate(CourseBuildInfoTable.CBI_APPROVALTIME); 
				if(cbi_approvaltime.equals(tempDate))
					cbi_approvaltime = null;
				int cbi_serialnumber = resultSet.getInt(CourseBuildInfoTable.CBI_SERIALNUMBER);
				String cbi_college = resultSet.getString(CourseBuildInfoTable.CBI_COLLEGE);//填报学院
			    Date cbi_deadline = resultSet.getDate(CourseBuildInfoTable.CBI_DEADLINE);//截止时间    
				String cbi_comments = resultSet.getString(CourseBuildInfoTable.CBI_COMMENTS);
				int isnull = resultSet.getInt(CourseBuildInfoTable.ISNULL);
				
				CourseBuildInfo cbi = new CourseBuildInfo(cbi_id, cbi_institute, cbi_name, 
						cbi_chargeperson, cbi_type, cbi_grade, cbi_approvaltime, cbi_serialnumber,
						cbi_deadline, cbi_college, cbi_comments,isnull);
				cbis.add(cbi);
			}
			return cbis;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
	}

	@Override
	public void deleteByCollegeandDeadline(String college, Date deadline) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();
		String sql = "delete from " + CourseBuildInfoTable.TABLE_NAME
				+ " where " + CourseBuildInfoTable.CBI_COLLEGE + " = '" + college + "'" +" and cbi_deadline is null ";
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
	public List<CourseBuildInfo> getAllCourseBuildInfo() {
		String sql = " select * from " + CourseBuildInfoTable.TABLE_NAME
				+ " where 1=1 " + " order by " + CourseBuildInfoTable.CBI_ID;
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
			Date tempDate = Date.valueOf("1800-1-1");
			List<CourseBuildInfo> cbis = new ArrayList<CourseBuildInfo>();
			while (resultSet.next()) {
				int cbi_id = resultSet.getInt(CourseBuildInfoTable.CBI_ID);
				String cbi_institute = resultSet.getString(CourseBuildInfoTable.CBI_INSTITUTE);
				String cbi_name = resultSet.getString(CourseBuildInfoTable.CBI_NAME);
				String cbi_chargeperson = resultSet.getString(CourseBuildInfoTable.CBI_CHARGEPERSON);
				String cbi_type = resultSet.getString(CourseBuildInfoTable.CBI_TYPE);
				String cbi_grade = resultSet.getString(CourseBuildInfoTable.CBI_GRADE);
				Date cbi_approvaltime = resultSet.getDate(CourseBuildInfoTable.CBI_APPROVALTIME); 
				if(cbi_approvaltime.equals(tempDate))
					cbi_approvaltime = null;
				int cbi_serialnumber = resultSet.getInt(CourseBuildInfoTable.CBI_SERIALNUMBER);
				String cbi_college = resultSet.getString(CourseBuildInfoTable.CBI_COLLEGE);//填报学院
			    Date cbi_deadline = resultSet.getDate(CourseBuildInfoTable.CBI_DEADLINE);//截止时间    
				String cbi_comments = resultSet.getString(CourseBuildInfoTable.CBI_COMMENTS);
				int isnull = resultSet.getInt(CourseBuildInfoTable.ISNULL);
				
				CourseBuildInfo cbi = new CourseBuildInfo(cbi_id, cbi_institute, cbi_name, 
						cbi_chargeperson, cbi_type, cbi_grade, cbi_approvaltime, cbi_serialnumber,
						cbi_deadline, cbi_college, cbi_comments,isnull);
				cbis.add(cbi);
			}
			return cbis;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
	}

}
