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

import cn.edu.xmu.dao.MajorEnrollInfoDao;
import cn.edu.xmu.entity.MajorEnrollInfo;
import cn.edu.xmu.table.MajorEnrollInfoTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;

/**
 * 各专业（大类）招生情况（时点）
 * @author yue
 *
 */
public class MajorEnrollInfoDaoImpl extends BaseDaoImpl<MajorEnrollInfo> implements MajorEnrollInfoDao{

	@Override
	public int addMajorEnrollInfoRecord(MajorEnrollInfo mei) {
		int result = 0;
		String t_sql = "update " + MajorEnrollInfoTable.TABLE_NAME + " set "
				+MajorEnrollInfoTable.MEI_SERIALNUMBER + " = "
				+MajorEnrollInfoTable.MEI_SERIALNUMBER + " +1 where "
				+MajorEnrollInfoTable.MEI_SERIALNUMBER + " >=?";
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
			t_pstmt.setInt(1, mei.getMei_serialnumber());
			
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
		String sql = "insert into " + MajorEnrollInfoTable.TABLE_NAME + "("
				+ MajorEnrollInfoTable.MEI_MAJORCODE +  ","
				+ MajorEnrollInfoTable.MEI_MAJORNAME + ","
				+ MajorEnrollInfoTable.MEI_PLANNUMBER + ","
				+ MajorEnrollInfoTable.MEI_ENROLLNUMBER + ","
				+ MajorEnrollInfoTable.MEI_SERIALNUMBER + ","
				+ MajorEnrollInfoTable.MEI_COLLEGE + ","
				+ MajorEnrollInfoTable.MEI_COMMENTS + ","
				+ MajorEnrollInfoTable.ISNULL
				+ ")values(?,?,?,?,?,?,?,?)";

		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, mei.getMei_majorcode());
			pstmt.setString(2, mei.getMei_majorname());
			pstmt.setInt(3, mei.getMei_plannumber());
			pstmt.setInt(4, mei.getMei_enrollnumber());
			pstmt.setInt(5, mei.getMei_serialnumber());
			pstmt.setString(6, mei.getMei_college());
			pstmt.setString(7, mei.getMei_comments());
			pstmt.setInt(8, mei.getIsnull());
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
	public boolean batchDelete(String[] meiids) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();
		
		for(String meiid:meiids)
		{
			String sql = "delete from " + MajorEnrollInfoTable.TABLE_NAME
					+ " where " + MajorEnrollInfoTable.MEI_ID + " = '" +meiid + "'";
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
	public int alterMajorEnrollInfo(Map<String, String> valueMap, String id) {
		Map<String, String> condition = new HashMap<String,String>();
		condition.put(MajorEnrollInfoTable.MEI_ID, id);
		int result = updateRecord(MajorEnrollInfoTable.TABLE_NAME, valueMap, condition);
		return result;
	}

	@Override
	public int getMajorEnrollInfoCount(Map queryParams) {
		int count = 0;
		//获取条件的语句
		String sql = "select count(*) from " + MajorEnrollInfoTable.TABLE_NAME
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
	public List<MajorEnrollInfo> getMajorEnrollInfo(int start, int end, String sortStr, String orderStr,
			Map<String, String> params, Date deadline) {
		String sql = " select tmp.* from ( " + " select * from "
				+ MajorEnrollInfoTable.TABLE_NAME + " where 1=1 ";
		if (deadline != null) {

			sql += String.format("and "+MajorEnrollInfoTable.MEI_DEADLINE+" like  '%s%%' ", deadline);
		}
		if (!(params == null || params.keySet().size() == 0)) {
			for (Object object : params.keySet()) {

				String key = object.toString();
				String value = params.get(key).toString();
				sql += String.format("  and %s like  '%%%s%%' ", key, value);
			}
		}

		sql += " order by " + sortStr + " " + orderStr + " ) tmp limit "
				+ start + " ," + end;

		System.out.println(sql);
		
		List<MajorEnrollInfo> meis = new ArrayList<MajorEnrollInfo>();
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

			while (resultSet.next()) {
				int mei_id = resultSet.getInt(MajorEnrollInfoTable.MEI_ID);
				String mei_majorcode = resultSet.getString(MajorEnrollInfoTable.MEI_MAJORCODE);
				String mei_majorname = resultSet.getString(MajorEnrollInfoTable.MEI_MAJORNAME);
				Integer mei_plannumber = resultSet.getInt(MajorEnrollInfoTable.MEI_PLANNUMBER);
				if(mei_plannumber == -999)
					mei_plannumber = null;
				Integer mei_enrollnumber = resultSet.getInt(MajorEnrollInfoTable.MEI_ENROLLNUMBER);
				if(mei_enrollnumber == -999)
					mei_enrollnumber = null;
				Integer mei_serialnumber = resultSet.getInt(MajorEnrollInfoTable.MEI_SERIALNUMBER);
				if(mei_serialnumber == -999)
					mei_serialnumber = null;
				String mei_college = resultSet.getString(MajorEnrollInfoTable.MEI_COLLEGE);//填报学院
			    Date mei_deadline = resultSet.getDate(MajorEnrollInfoTable.MEI_DEADLINE);//截止时间    
				String mei_comments = resultSet.getString(MajorEnrollInfoTable.MEI_COMMENTS);
				int isnull = resultSet.getInt(MajorEnrollInfoTable.ISNULL);
				
				MajorEnrollInfo mei = new MajorEnrollInfo(mei_id, mei_majorcode, mei_majorname, 
						mei_plannumber, mei_enrollnumber, mei_serialnumber, mei_deadline, 
						mei_college, mei_comments,isnull);
				meis.add(mei);
			}
			return meis;
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
		String sql = "delete from " + MajorEnrollInfoTable.TABLE_NAME
				+ " where " + MajorEnrollInfoTable.MEI_COLLEGE + " = '" + college + "'" +" and mei_deadline is null ";
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
	public List<MajorEnrollInfo> getAllMajorEnrollInfo() {
		String sql = " select * from " + MajorEnrollInfoTable.TABLE_NAME
				+ " where 1=1 " + " order by " + MajorEnrollInfoTable.MEI_ID;
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
			List<MajorEnrollInfo> meis = new ArrayList<MajorEnrollInfo>();
			while (resultSet.next()) {
				int mei_id = resultSet.getInt(MajorEnrollInfoTable.MEI_ID);
				String mei_majorcode = resultSet.getString(MajorEnrollInfoTable.MEI_MAJORCODE);
				String mei_majorname = resultSet.getString(MajorEnrollInfoTable.MEI_MAJORNAME);
				Integer mei_plannumber = resultSet.getInt(MajorEnrollInfoTable.MEI_PLANNUMBER);
				if(mei_plannumber == -999)
					mei_plannumber = null;
				Integer mei_enrollnumber = resultSet.getInt(MajorEnrollInfoTable.MEI_ENROLLNUMBER);
				if(mei_enrollnumber == -999)
					mei_enrollnumber = null;
				Integer mei_serialnumber = resultSet.getInt(MajorEnrollInfoTable.MEI_SERIALNUMBER);
				if(mei_serialnumber == -999)
					mei_serialnumber = null;
				String mei_college = resultSet.getString(MajorEnrollInfoTable.MEI_COLLEGE);//填报学院
			    Date mei_deadline = resultSet.getDate(MajorEnrollInfoTable.MEI_DEADLINE);//截止时间    
				String mei_comments = resultSet.getString(MajorEnrollInfoTable.MEI_COMMENTS);
				int isnull = resultSet.getInt(MajorEnrollInfoTable.ISNULL);
				
				MajorEnrollInfo mei = new MajorEnrollInfo(mei_id, mei_majorcode, mei_majorname, 
						mei_plannumber, mei_enrollnumber, mei_serialnumber, mei_deadline, 
						mei_college, mei_comments,isnull);
				meis.add(mei);
			}
			return meis;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
	}

}
