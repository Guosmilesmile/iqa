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

import cn.edu.xmu.dao.FundingProfileDao;
import cn.edu.xmu.entity.FundingProfile;
import cn.edu.xmu.entity.SchoolNet;
import cn.edu.xmu.table.FundingProfileTable;
import cn.edu.xmu.table.SchoolNetTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;

public class FundingProfileDaoimpl extends BaseDaoImpl<FundingProfile>implements FundingProfileDao {

	@Override
	public int addRecord(FundingProfile fp) {
		int result = 0;

		String t_sql = "update " + FundingProfileTable.TABLE_NAME + " set "
				+ FundingProfileTable.FP_SERIALNUMBER + " = "
				+ FundingProfileTable.FP_SERIALNUMBER + " +1 where "
				+ FundingProfileTable.FP_SERIALNUMBER + ">=?";

		
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
			t_pstmt.setInt(1, fp.getFp_serialnumber());
			
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
		String sql = "insert into " + FundingProfileTable.TABLE_NAME + "("
				+ FundingProfileTable.FP_EDUCATIONFUND
				+ "," + FundingProfileTable.FP_TEACHFUND+ ","
				+ FundingProfileTable.FP_REFORMFUND + ","
				+ FundingProfileTable.FP_SERIALNUMBER + ","
				+ FundingProfileTable.FP_COLLEGE + ","
				+ FundingProfileTable.FP_COMMENTS+","
				+ FundingProfileTable.ISNULL
				+ ")values(?,?,?,?,?,?,?)";

		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setFloat(1, fp.getFp_educationfund());
			pstmt.setFloat(2, fp.getFp_teachfund());
			pstmt.setFloat(3, fp.getFp_reformfund());
			pstmt.setInt(4, fp.getFp_serialnumber());
			pstmt.setString(5, fp.getFp_college());
			pstmt.setString(6, fp.getFp_comments());
			pstmt.setInt(7, fp.getIsnull());
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
	public boolean batchDelete(String[] fpids) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();

		for (String fpid : fpids) {
			String sql = "delete from " + FundingProfileTable.TABLE_NAME
					+ " where " + FundingProfileTable.FP_ID + " = '" + fpid + "'";
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
	public int alterFundingProfile(Map<String, String> valueMap, String id) {
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(FundingProfileTable.FP_ID, id);
		int result = updateRecord(FundingProfileTable.TABLE_NAME, valueMap,
				condition);
		return result;
	}

	@Override
	public int getFundingProfileCount(Map queryParams) {
		int count = 0;
		//获取条件的语句
		String sql = "select count(*) from " + FundingProfileTable.TABLE_NAME
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
		sql += String.format(" or %s ='%s'", FundingProfileTable.FP_COLLEGE, "");
		
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
	public List<FundingProfile> getAllFundingProfile(int start, int end,
			String sortStr, String orderStr, Map queryParams) {
		String sql = " select tmp.* from ( " + " select * from "
				+ FundingProfileTable.TABLE_NAME + " where 1=1 ";
		
		for (Object object : queryParams.keySet()) {
			String key = object.toString();
			String value = queryParams.get(key).toString();
			sql += String.format(" and %s like  '%%%s%%'", key, value);
		}
		
		if (start ==0 && end ==0 && sortStr == "nope" &&  orderStr == "nope") {
			
		}else{
			sql += " order by " + sortStr + " " + orderStr + " ) tmp limit "
					+ start + " ," + end;
		}
	

		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		List<FundingProfile> fps = new ArrayList<FundingProfile>();
		try {
			
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int fp_id = resultSet.getInt(FundingProfileTable.FP_ID);
				Float fp_educationfund = resultSet.getFloat(FundingProfileTable.FP_EDUCATIONFUND);
				if(fp_educationfund==-999)
					fp_educationfund = null;
				
				Float fp_teachfund = resultSet.getFloat(FundingProfileTable.FP_TEACHFUND);
				if(fp_teachfund==-999)
					fp_teachfund = null;
				
				Float fp_reformfund = resultSet.getFloat(FundingProfileTable.FP_REFORMFUND);
				if(fp_reformfund==-999)
					fp_reformfund = null;
				
				int fp_serialnumber = resultSet.getInt(FundingProfileTable.FP_SERIALNUMBER);
				String fp_comments = resultSet.getString(FundingProfileTable.FP_COMMENTS);
				String fp_college = resultSet.getString(FundingProfileTable.FP_COLLEGE);
				int isnull = resultSet.getInt(FundingProfileTable.ISNULL);
				if(fp_comments==null)
					fp_comments="";
				
				
				FundingProfile fp = new FundingProfile(fp_id, fp_educationfund,
						fp_teachfund, fp_reformfund, fp_serialnumber, fp_comments,isnull,fp_college);
				
				fps.add(fp);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return fps;
	}

	@Override
	public List<FundingProfile> getAllFundingProfile() {
		String sql = " select * from "
				+ FundingProfileTable.TABLE_NAME + " where 1=1 ";
		
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		List<FundingProfile> fps = new ArrayList<FundingProfile>();
		try {
			
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int fp_id = resultSet.getInt(FundingProfileTable.FP_ID);
				Float fp_educationfund = resultSet.getFloat(FundingProfileTable.FP_EDUCATIONFUND);
				if(fp_educationfund==-999)
					fp_educationfund = null;
				
				Float fp_teachfund = resultSet.getFloat(FundingProfileTable.FP_TEACHFUND);
				if(fp_teachfund==-999)
					fp_teachfund = null;
				
				Float fp_reformfund = resultSet.getFloat(FundingProfileTable.FP_REFORMFUND);
				if(fp_reformfund==-999)
					fp_reformfund = null;
				
				int fp_serialnumber = resultSet.getInt(FundingProfileTable.FP_SERIALNUMBER);
				String fp_comments = resultSet.getString(FundingProfileTable.FP_COMMENTS);
				int isnull = resultSet.getInt(FundingProfileTable.ISNULL);
				String fp_college = resultSet.getString(FundingProfileTable.FP_COLLEGE);
				if(fp_comments==null)
					fp_comments="";
				
				
				FundingProfile fp = new FundingProfile(fp_id, fp_educationfund,
						fp_teachfund, fp_reformfund, fp_serialnumber, fp_comments,isnull,fp_college);
				
				fps.add(fp);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return fps;
	}

	@Override
	public void deleteByCollegeandDeadline(String college, Date deadline)
			throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();
		String sql = "delete from " + FundingProfileTable.TABLE_NAME
				+ " where " + FundingProfileTable.FP_COLLEGE + " = '" + college + "'" +" and fp_deadline is null ";
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
