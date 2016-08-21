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

import cn.edu.xmu.dao.UndergraduateAwardLoanDao;
import cn.edu.xmu.entity.UndergraduateAwardLoan;
import cn.edu.xmu.table.UndergraduateAwardLoanTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;


/**
 * @author zhantu
 * 本科生奖贷补（自然年）  实体类功能 ——接口实现
 * date 2015-07-05
 */

public class UndergraduateAwardLoanDaoImpl extends BaseDaoImpl<UndergraduateAwardLoan>implements UndergraduateAwardLoanDao{

	@Override
	public int addRecord(UndergraduateAwardLoan ual) {
		
		int result = 0;

		String t_sql = "update " + UndergraduateAwardLoanTable.TABLE_NAME + " set "
				+ UndergraduateAwardLoanTable.UAL_SERIALNUMBER + " = "
				+ UndergraduateAwardLoanTable.UAL_SERIALNUMBER + " +1 where "
				+ UndergraduateAwardLoanTable.UAL_SERIALNUMBER + ">=?";

		
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
			t_pstmt.setInt(1, ual.getUal_serialnumber());
			
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
		String sql = "insert into " + UndergraduateAwardLoanTable.TABLE_NAME + "("
				+ UndergraduateAwardLoanTable.UAL_SUMCOST+ "," 
				+ UndergraduateAwardLoanTable.UAL_GOVCOST + ","
				+ UndergraduateAwardLoanTable.UAL_SOCIETYCOST + ","
				+ UndergraduateAwardLoanTable.UAL_SCHOOLCOST + ","
				+ UndergraduateAwardLoanTable.UAL_COUNTRYCOST + ","
				+ UndergraduateAwardLoanTable.UAL_WORKSTUDYCOST + ","
				+ UndergraduateAwardLoanTable.UAL_DERATECOST + ","
				+ UndergraduateAwardLoanTable.UAL_TROUBLEAIDCOST + ","
				
				+ UndergraduateAwardLoanTable.UAL_SUMCOUNT+ "," 
				+ UndergraduateAwardLoanTable.UAL_GOVCOUNT + ","
				+ UndergraduateAwardLoanTable.UAL_SOCIETYCOUNT + ","
				+ UndergraduateAwardLoanTable.UAL_SCHOOLCOUNT + ","
				+ UndergraduateAwardLoanTable.UAL_COUNTRYCOUNT + ","
				+ UndergraduateAwardLoanTable.UAL_WORKSTUDYCOUNT + ","
				+ UndergraduateAwardLoanTable.UAL_DERATECOUNT + ","
				+ UndergraduateAwardLoanTable.UAL_TROUBLEAIDCOUNT + ","
				
				+ UndergraduateAwardLoanTable.UAL_SERIALNUMBER + ","
				+ UndergraduateAwardLoanTable.UAL_COLLEGE + ","
				+ UndergraduateAwardLoanTable.UAL_COMMENTS + ","
				+ UndergraduateAwardLoanTable.ISNULL
				+ ")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setFloat(1, ual.getUal_sumcost());
			pstmt.setFloat(2, ual.getUal_govcost());
			pstmt.setFloat(3, ual.getUal_societycost());
			pstmt.setFloat(4, ual.getUal_schoolcost());
			pstmt.setFloat(5, ual.getUal_countrycost());
			pstmt.setFloat(6, ual.getUal_workstudycost());
			pstmt.setFloat(7, ual.getUal_deratecost());
			pstmt.setFloat(8, ual.getUal_troubleaidcost());
			
			pstmt.setInt(9, ual.getUal_sumcount());
			pstmt.setInt(10, ual.getUal_govcount());
			pstmt.setInt(11, ual.getUal_societycount());
			pstmt.setInt(12, ual.getUal_schoolcount());
			pstmt.setInt(13, ual.getUal_countrycount());
			pstmt.setInt(14, ual.getUal_workstudycount());
			pstmt.setInt(15, ual.getUal_deratecount());
			pstmt.setInt(16, ual.getUal_troubleaidcount());
			
			pstmt.setInt(17, ual.getUal_serialnumber());
			pstmt.setString(18, ual.getUal_college());
			pstmt.setString(19, ual.getUal_comments());
			pstmt.setInt(20, ual.getIsnull());
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
	public int getUndergraduateAwardLoanCount(Map queryParams) {
		
		int count = 0;
		//获取条件的语句
		String sql = "select count(*) from " + UndergraduateAwardLoanTable.TABLE_NAME
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
		sql += String.format(" or %s ='%s'", UndergraduateAwardLoanTable.UAL_COLLEGE, "");
		
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
	public List<UndergraduateAwardLoan> getAllUndergraduateAwardLoan(int start, int end,
			String sortStr, String orderStr, Map queryParams) {
		
		String sql = " select tmp.* from ( " + " select * from "
				+ UndergraduateAwardLoanTable.TABLE_NAME + " where 1=1 ";
		
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
		
		List<UndergraduateAwardLoan> uals = new ArrayList<UndergraduateAwardLoan>();
		try {
			
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int ual_id = resultSet.getInt(UndergraduateAwardLoanTable.UAL_ID);

				Float ual_sumcost = resultSet.getFloat(UndergraduateAwardLoanTable.UAL_SUMCOST);
				if(ual_sumcost==-999)
					ual_sumcost = null;
				Float ual_govcost = resultSet.getFloat(UndergraduateAwardLoanTable.UAL_GOVCOST);
				if(ual_govcost==-999)
					ual_govcost = null;
				Float ual_societycost = resultSet.getFloat(UndergraduateAwardLoanTable.UAL_SOCIETYCOST);
				if(ual_societycost==-999)
					ual_societycost = null;
				Float ual_schoolcost = resultSet.getFloat(UndergraduateAwardLoanTable.UAL_SCHOOLCOST);
				if(ual_schoolcost==-999)
					ual_schoolcost = null;
				Float ual_countrycost = resultSet.getFloat(UndergraduateAwardLoanTable.UAL_COUNTRYCOST);
				if(ual_countrycost==-999)
					ual_countrycost = null;
				Float ual_workstudycost = resultSet.getFloat(UndergraduateAwardLoanTable.UAL_WORKSTUDYCOST);
				if(ual_workstudycost==-999)
					ual_workstudycost = null;
				Float ual_deratecost = resultSet.getFloat(UndergraduateAwardLoanTable.UAL_DERATECOST);
				if(ual_deratecost==-999)
					ual_deratecost = null;
				Float ual_troubleaidcost = resultSet.getFloat(UndergraduateAwardLoanTable.UAL_TROUBLEAIDCOST);
				if(ual_troubleaidcost==-999)
					ual_troubleaidcost = null;
				
				Integer ual_sumcount = resultSet.getInt(UndergraduateAwardLoanTable.UAL_SUMCOUNT);
				if(ual_sumcount==-999)
					ual_sumcount = null;
				Integer ual_govcount = resultSet.getInt(UndergraduateAwardLoanTable.UAL_GOVCOUNT);
				if(ual_govcount==-999)
					ual_govcount = null;
				Integer ual_societycount = resultSet.getInt(UndergraduateAwardLoanTable.UAL_SOCIETYCOUNT);
				if(ual_societycount==-999)
					ual_societycount = null;
				Integer ual_schoolcount = resultSet.getInt(UndergraduateAwardLoanTable.UAL_SCHOOLCOUNT);
				if(ual_schoolcount==-999)
					ual_schoolcount = null;
				Integer ual_countrycount = resultSet.getInt(UndergraduateAwardLoanTable.UAL_COUNTRYCOUNT);
				if(ual_countrycount==-999)
					ual_countrycount = null;
				Integer ual_workstudycount = resultSet.getInt(UndergraduateAwardLoanTable.UAL_WORKSTUDYCOUNT);
				if(ual_workstudycount==-999)
					ual_workstudycount = null;
				Integer ual_deratecount = resultSet.getInt(UndergraduateAwardLoanTable.UAL_DERATECOUNT);
				if(ual_deratecount==-999)
					ual_deratecount = null;
				Integer ual_troubleaidcount = resultSet.getInt(UndergraduateAwardLoanTable.UAL_TROUBLEAIDCOUNT);
				if(ual_troubleaidcount==-999)
					ual_troubleaidcount = null;
				
				int ual_serialnumber = resultSet.getInt(UndergraduateAwardLoanTable.UAL_SERIALNUMBER);
				Date ual_deadline = resultSet.getDate(UndergraduateAwardLoanTable.UAL_DEADLINE);
				String ual_comments = resultSet.getString(UndergraduateAwardLoanTable.UAL_COMMENTS);
				String ual_college = resultSet.getString(UndergraduateAwardLoanTable.UAL_COLLEGE);
				int isnull = resultSet.getInt(UndergraduateAwardLoanTable.ISNULL);
				if(ual_comments==null)
					ual_comments = "";
				
				UndergraduateAwardLoan ual = new UndergraduateAwardLoan(ual_id, ual_sumcost,
						ual_govcost, ual_societycost, ual_schoolcost,
						ual_countrycost, ual_workstudycost,
						ual_deratecost, ual_troubleaidcost, ual_sumcount,
						ual_govcount, ual_societycount, ual_schoolcount,
						ual_countrycount, ual_workstudycount, ual_deratecount,
						ual_troubleaidcount, ual_serialnumber, ual_deadline,
						ual_college, ual_comments, isnull);
				
				uals.add(ual);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return uals;
	}

	@Override
	public int alterUndergraduateAwardLoan(Map<String, String> valueMap, String id) {
		
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(UndergraduateAwardLoanTable.UAL_ID, id);
		int result = updateRecord(UndergraduateAwardLoanTable.TABLE_NAME, valueMap,
				condition);
		return result;
	}

	@Override
	public boolean batchDelete(String[] ualids) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();

		for (String ualid : ualids) {
			String sql = "delete from " + UndergraduateAwardLoanTable.TABLE_NAME
					+ " where " + UndergraduateAwardLoanTable.UAL_ID + " = '" + ualid + "'";
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
	public List<UndergraduateAwardLoan> getUndergraduateAwardLoan(int start, int end,
			String sortStr, String orderStr, Map<String, String> params,
			Date deadline) {
		String sql = " select tmp.* from ( " + " select * from "
				+ UndergraduateAwardLoanTable.TABLE_NAME + " where 1=1 ";
		if (deadline != null) {

			sql += String.format("and "+UndergraduateAwardLoanTable.UAL_DEADLINE+" like  '%s%%' ", deadline);
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

		List<UndergraduateAwardLoan> undergraduateAwardLoans = new ArrayList<UndergraduateAwardLoan>();
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
				int ual_id = resultSet.getInt(UndergraduateAwardLoanTable.UAL_ID);

				Float ual_sumcost = resultSet.getFloat(UndergraduateAwardLoanTable.UAL_SUMCOST);
				if(ual_sumcost==-999)
					ual_sumcost = null;
				Float ual_govcost = resultSet.getFloat(UndergraduateAwardLoanTable.UAL_GOVCOST);
				if(ual_govcost==-999)
					ual_govcost = null;
				Float ual_societycost = resultSet.getFloat(UndergraduateAwardLoanTable.UAL_SOCIETYCOST);
				if(ual_societycost==-999)
					ual_societycost = null;
				Float ual_schoolcost = resultSet.getFloat(UndergraduateAwardLoanTable.UAL_SCHOOLCOST);
				if(ual_schoolcost==-999)
					ual_schoolcost = null;
				Float ual_countrycost = resultSet.getFloat(UndergraduateAwardLoanTable.UAL_COUNTRYCOST);
				if(ual_countrycost==-999)
					ual_countrycost = null;
				Float ual_workstudycost = resultSet.getFloat(UndergraduateAwardLoanTable.UAL_WORKSTUDYCOST);
				if(ual_workstudycost==-999)
					ual_workstudycost = null;
				Float ual_deratecost = resultSet.getFloat(UndergraduateAwardLoanTable.UAL_DERATECOST);
				if(ual_deratecost==-999)
					ual_deratecost = null;
				Float ual_troubleaidcost = resultSet.getFloat(UndergraduateAwardLoanTable.UAL_TROUBLEAIDCOST);
				if(ual_troubleaidcost==-999)
					ual_troubleaidcost = null;
				
				Integer ual_sumcount = resultSet.getInt(UndergraduateAwardLoanTable.UAL_SUMCOUNT);
				if(ual_sumcount==-999)
					ual_sumcount = null;
				Integer ual_govcount = resultSet.getInt(UndergraduateAwardLoanTable.UAL_GOVCOUNT);
				if(ual_govcount==-999)
					ual_govcount = null;
				Integer ual_societycount = resultSet.getInt(UndergraduateAwardLoanTable.UAL_SOCIETYCOUNT);
				if(ual_societycount==-999)
					ual_societycount = null;
				Integer ual_schoolcount = resultSet.getInt(UndergraduateAwardLoanTable.UAL_SCHOOLCOUNT);
				if(ual_schoolcount==-999)
					ual_schoolcount = null;
				Integer ual_countrycount = resultSet.getInt(UndergraduateAwardLoanTable.UAL_COUNTRYCOUNT);
				if(ual_countrycount==-999)
					ual_countrycount = null;
				Integer ual_workstudycount = resultSet.getInt(UndergraduateAwardLoanTable.UAL_WORKSTUDYCOUNT);
				if(ual_workstudycount==-999)
					ual_workstudycount = null;
				Integer ual_deratecount = resultSet.getInt(UndergraduateAwardLoanTable.UAL_DERATECOUNT);
				if(ual_deratecount==-999)
					ual_deratecount = null;
				Integer ual_troubleaidcount = resultSet.getInt(UndergraduateAwardLoanTable.UAL_TROUBLEAIDCOUNT);
				if(ual_troubleaidcount==-999)
					ual_troubleaidcount = null;
				
				int ual_serialnumber = resultSet.getInt(UndergraduateAwardLoanTable.UAL_SERIALNUMBER);
				Date ual_deadline = resultSet.getDate(UndergraduateAwardLoanTable.UAL_DEADLINE);
				String ual_comments = resultSet.getString(UndergraduateAwardLoanTable.UAL_COMMENTS);
				String ual_college = resultSet.getString(UndergraduateAwardLoanTable.UAL_COLLEGE);
				int isnull = resultSet.getInt(UndergraduateAwardLoanTable.ISNULL);
				
				UndergraduateAwardLoan ual = new UndergraduateAwardLoan(ual_id, ual_sumcost,
						ual_govcost, ual_societycost, ual_schoolcost,
						ual_countrycost, ual_workstudycost,
						ual_deratecost, ual_troubleaidcost, ual_sumcount,
						ual_govcount, ual_societycount, ual_schoolcount,
						ual_countrycount, ual_workstudycount, ual_deratecount,
						ual_troubleaidcount, ual_serialnumber, ual_deadline,
						ual_college, ual_comments, isnull);

				undergraduateAwardLoans.add(ual);
			}
			return undergraduateAwardLoans;
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
		String sql = "delete from " + UndergraduateAwardLoanTable.TABLE_NAME
				+ " where " + UndergraduateAwardLoanTable.UAL_COLLEGE + " = '" + college + "'" +" and ual_deadline is null ";
		System.out.println(sql);
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils_DBCP.release(connection, stmt, null);
		}
		
	}

	// 得到所有单位
	@Override
	public List<UndergraduateAwardLoan> getAllUndergraduateAwardLoan() {
		String sql = " select * from " + UndergraduateAwardLoanTable.TABLE_NAME
				+ " where 1=1 " + " order by " + UndergraduateAwardLoanTable.UAL_ID;
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
			List<UndergraduateAwardLoan> undergraduateAwardLoanList = new ArrayList<UndergraduateAwardLoan>();
			while (resultSet.next()) {
				int ual_id = resultSet.getInt(UndergraduateAwardLoanTable.UAL_ID);

				Float ual_sumcost = resultSet.getFloat(UndergraduateAwardLoanTable.UAL_SUMCOST);
				if(ual_sumcost==-999)
					ual_sumcost = null;
				Float ual_govcost = resultSet.getFloat(UndergraduateAwardLoanTable.UAL_GOVCOST);
				if(ual_govcost==-999)
					ual_govcost = null;
				Float ual_societycost = resultSet.getFloat(UndergraduateAwardLoanTable.UAL_SOCIETYCOST);
				if(ual_societycost==-999)
					ual_societycost = null;
				Float ual_schoolcost = resultSet.getFloat(UndergraduateAwardLoanTable.UAL_SCHOOLCOST);
				if(ual_schoolcost==-999)
					ual_schoolcost = null;
				Float ual_countrycost = resultSet.getFloat(UndergraduateAwardLoanTable.UAL_COUNTRYCOST);
				if(ual_countrycost==-999)
					ual_countrycost = null;
				Float ual_workstudycost = resultSet.getFloat(UndergraduateAwardLoanTable.UAL_WORKSTUDYCOST);
				if(ual_workstudycost==-999)
					ual_workstudycost = null;
				Float ual_deratecost = resultSet.getFloat(UndergraduateAwardLoanTable.UAL_DERATECOST);
				if(ual_deratecost==-999)
					ual_deratecost = null;
				Float ual_troubleaidcost = resultSet.getFloat(UndergraduateAwardLoanTable.UAL_TROUBLEAIDCOST);
				if(ual_troubleaidcost==-999)
					ual_troubleaidcost = null;
				
				Integer ual_sumcount = resultSet.getInt(UndergraduateAwardLoanTable.UAL_SUMCOUNT);
				if(ual_sumcount==-999)
					ual_sumcount = null;
				Integer ual_govcount = resultSet.getInt(UndergraduateAwardLoanTable.UAL_GOVCOUNT);
				if(ual_govcount==-999)
					ual_govcount = null;
				Integer ual_societycount = resultSet.getInt(UndergraduateAwardLoanTable.UAL_SOCIETYCOUNT);
				if(ual_societycount==-999)
					ual_societycount = null;
				Integer ual_schoolcount = resultSet.getInt(UndergraduateAwardLoanTable.UAL_SCHOOLCOUNT);
				if(ual_schoolcount==-999)
					ual_schoolcount = null;
				Integer ual_countrycount = resultSet.getInt(UndergraduateAwardLoanTable.UAL_COUNTRYCOUNT);
				if(ual_countrycount==-999)
					ual_countrycount = null;
				Integer ual_workstudycount = resultSet.getInt(UndergraduateAwardLoanTable.UAL_WORKSTUDYCOUNT);
				if(ual_workstudycount==-999)
					ual_workstudycount = null;
				Integer ual_deratecount = resultSet.getInt(UndergraduateAwardLoanTable.UAL_DERATECOUNT);
				if(ual_deratecount==-999)
					ual_deratecount = null;
				Integer ual_troubleaidcount = resultSet.getInt(UndergraduateAwardLoanTable.UAL_TROUBLEAIDCOUNT);
				if(ual_troubleaidcount==-999)
					ual_troubleaidcount = null;
				
				int ual_serialnumber = resultSet.getInt(UndergraduateAwardLoanTable.UAL_SERIALNUMBER);
				Date ual_deadline = resultSet.getDate(UndergraduateAwardLoanTable.UAL_DEADLINE);
				String ual_comments = resultSet.getString(UndergraduateAwardLoanTable.UAL_COMMENTS);
				String ual_college = resultSet.getString(UndergraduateAwardLoanTable.UAL_COLLEGE);
				int isnull = resultSet.getInt(UndergraduateAwardLoanTable.ISNULL);
				
				UndergraduateAwardLoan ual = new UndergraduateAwardLoan(ual_id, ual_sumcost,
						ual_govcost, ual_societycost, ual_schoolcost,
						ual_countrycost, ual_workstudycost,
						ual_deratecost, ual_troubleaidcost, ual_sumcount,
						ual_govcount, ual_societycount, ual_schoolcount,
						ual_countrycount, ual_workstudycount, ual_deratecount,
						ual_troubleaidcount, ual_serialnumber, ual_deadline,
						ual_college, ual_comments, isnull);
				
				undergraduateAwardLoanList.add(ual);
			}
			return undergraduateAwardLoanList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
	}
}
