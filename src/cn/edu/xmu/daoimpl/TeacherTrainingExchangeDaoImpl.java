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

import cn.edu.xmu.dao.TeacherTrainingExchangeDao;
import cn.edu.xmu.entity.TeacherTrainingExchange;
import cn.edu.xmu.table.TeacherTrainingExchangeTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;

public class TeacherTrainingExchangeDaoImpl extends BaseDaoImpl<TeacherTrainingExchange>implements TeacherTrainingExchangeDao{
	@Override
	public int addRecord(TeacherTrainingExchange tte) {
		
		int result = 0;

		String t_sql = "update " + TeacherTrainingExchangeTable.TABLE_NAME + " set "
				+ TeacherTrainingExchangeTable.TTE_SERIALNUMBER + " = "
				+ TeacherTrainingExchangeTable.TTE_SERIALNUMBER + " +1 where "
				+ TeacherTrainingExchangeTable.TTE_SERIALNUMBER + ">=?";

		
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
			t_pstmt.setInt(1, tte.getTte_serialnumber());
			
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
		//无id和deadline
		String sql = "insert into " + TeacherTrainingExchangeTable.TABLE_NAME + "("
				+ TeacherTrainingExchangeTable.TTE_DEPARTMENTNAME+ "," 
				+ TeacherTrainingExchangeTable.TTE_DEPARTMENTNUMBER + ","
				+ TeacherTrainingExchangeTable.TTE_TRAINCHURCHYARD + ","
				+ TeacherTrainingExchangeTable.TTE_TRAINOVERSEASSUM + ","
				+ TeacherTrainingExchangeTable.TTE_TRAINOVERSEASOVER3 + ","
				+ TeacherTrainingExchangeTable.TTE_TRAINTRADE + ","
				
				
				+ TeacherTrainingExchangeTable.TTE_TRAINFORDEGREESUM+ "," 
				+ TeacherTrainingExchangeTable.TTE_TRAINFORDOCTOR + ","
				+ TeacherTrainingExchangeTable.TTE_TRAINFORMASTER + ","
				+ TeacherTrainingExchangeTable.TTE_EXCHANGECOMECHURCHYARD + ","
				+ TeacherTrainingExchangeTable.TTE_EXCHANGECOMEOVERSEA + ","
				+ TeacherTrainingExchangeTable.TTE_EXCHANGEVISITCHURCHYARD + ","
				
				
				+ TeacherTrainingExchangeTable.TTE_EXCHANGEVISITOVERSEA+ "," 
				+ TeacherTrainingExchangeTable.TTE_SERIALNUMBER + ","
				+ TeacherTrainingExchangeTable.TTE_COLLEGE + ","
				+ TeacherTrainingExchangeTable.TTE_COMMENTS + ","
				+ TeacherTrainingExchangeTable.ISNULL
				+ ")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, tte.getTte_departmentname());
			pstmt.setString(2, tte.getTte_departmentnumber());
			pstmt.setInt(3, tte.getTte_trainchurchyard());
			pstmt.setInt(4, tte.getTte_trainoverseassum());
			pstmt.setInt(5, tte.getTte_trainoverseasover3());
			pstmt.setInt(6, tte.getTte_traintrade());
			pstmt.setInt(7, tte.getTte_trainfordegreesum());
			pstmt.setInt(8, tte.getTte_trainfordoctor());
			pstmt.setInt(9, tte.getTte_trainformaster());

			pstmt.setInt(10, tte.getTte_exchangecomechurchyard());
			pstmt.setInt(11, tte.getTte_exchangecomeoversea());
			pstmt.setInt(12, tte.getTte_exchangevisitchurchyard());
			pstmt.setInt(13, tte.getTte_exchangevisitoversea());
			pstmt.setInt(14, tte.getTte_serialnumber());
			pstmt.setString(15, tte.getTte_college());
			pstmt.setString(16, tte.getTte_comments());
			pstmt.setInt(17, tte.getIsnull());
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
	public int getTeacherTrainingExchangeCount(Map queryParams) {
		
		int count = 0;
		//获取条件的语句
		String sql = "select count(*) from " + TeacherTrainingExchangeTable.TABLE_NAME
				+ " where 1 = 1";
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		if (queryParams != null && queryParams.size() >0) {
			for (Object object : queryParams.keySet()) {
				String key = object.toString();
				String value = queryParams.get(key).toString();
				sql += String.format(" and  %s like  '%%%s%%' ", key, value);
			}
		}
		
		sql += String.format(" or %s ='%s'", TeacherTrainingExchangeTable.TTE_COLLEGE, "");
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
	public List<TeacherTrainingExchange> getAllTeacherTrainingExchange(int start, int end,
			String sortStr, String orderStr, Map queryParams) {
		
		String sql = " select tmp.* from ( " + " select * from "
				+ TeacherTrainingExchangeTable.TABLE_NAME + " where 1=1 ";
		if (queryParams != null && queryParams.size() > 0) {
			for (Object object : queryParams.keySet()) {
				String key = object.toString();
				String value = queryParams.get(key).toString();
				sql += String.format(" and %s like  '%%%s%%'", key, value);
			}
		}
		
		sql += " order by " + sortStr + " " + orderStr + " ) tmp limit "
				+ start + " ," + end;

		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		List<TeacherTrainingExchange> ttes = new ArrayList<TeacherTrainingExchange>();
		try {
			
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int tte_id = resultSet.getInt(TeacherTrainingExchangeTable.TTE_ID);
				String tte_departmentname = resultSet.getString(TeacherTrainingExchangeTable.TTE_DEPARTMENTNAME);
				String tte_departmentnumber = resultSet.getString(TeacherTrainingExchangeTable.TTE_DEPARTMENTNUMBER);
				Integer tte_trainchurchyard = resultSet.getInt(TeacherTrainingExchangeTable.TTE_TRAINCHURCHYARD);
				if(tte_trainchurchyard==-999)
					tte_trainchurchyard= null;
				Integer tte_trainoverseassum = resultSet.getInt(TeacherTrainingExchangeTable.TTE_TRAINOVERSEASSUM);
				if(tte_trainoverseassum==-999)
					tte_trainoverseassum = null;
				Integer tte_trainoverseasover3 = resultSet.getInt(TeacherTrainingExchangeTable.TTE_TRAINOVERSEASOVER3);
				if(tte_trainoverseasover3==-999)
					tte_trainoverseasover3 = null;
				Integer tte_traintrade = resultSet.getInt(TeacherTrainingExchangeTable.TTE_TRAINTRADE);
				if(tte_traintrade==-999)
					tte_traintrade = null;
				Integer tte_trainfordegreesum = resultSet.getInt(TeacherTrainingExchangeTable.TTE_TRAINFORDEGREESUM);
				if(tte_trainfordegreesum==-999)
					tte_trainfordegreesum = null;
				Integer tte_trainfordoctor = resultSet.getInt(TeacherTrainingExchangeTable.TTE_TRAINFORDOCTOR);
				if(tte_trainfordoctor==-999)
					tte_trainfordoctor = null;
				Integer tte_trainformaster = resultSet.getInt(TeacherTrainingExchangeTable.TTE_TRAINFORMASTER);
				if(tte_trainformaster==-999)
					tte_trainformaster = null;
				Integer tte_exchangecomechurchyard = resultSet.getInt(TeacherTrainingExchangeTable.TTE_EXCHANGECOMECHURCHYARD);
				if(tte_exchangecomechurchyard==-999)
					tte_exchangecomechurchyard = null;
				Integer tte_exchangecomeoversea = resultSet.getInt(TeacherTrainingExchangeTable.TTE_EXCHANGECOMEOVERSEA);
				if(tte_exchangecomeoversea==-999)
					tte_exchangecomeoversea = null;
				Integer tte_exchangevisitchurchyard = resultSet.getInt(TeacherTrainingExchangeTable.TTE_EXCHANGEVISITCHURCHYARD);
				if(tte_exchangevisitchurchyard==-999)
					tte_exchangevisitchurchyard = null;
				Integer tte_exchangevisitoversea = resultSet.getInt(TeacherTrainingExchangeTable.TTE_EXCHANGEVISITOVERSEA);
				if(tte_exchangevisitoversea==-999)
					tte_exchangevisitoversea = null;
				int tte_serialnumber = resultSet.getInt(TeacherTrainingExchangeTable.TTE_SERIALNUMBER);
				Date tte_deadline = resultSet.getDate(TeacherTrainingExchangeTable.TTE_DEADLINE);
				String tte_college = resultSet.getString(TeacherTrainingExchangeTable.TTE_COLLEGE);
				String tte_comments = resultSet.getString(TeacherTrainingExchangeTable.TTE_COMMENTS);
				int isnull = resultSet.getInt(TeacherTrainingExchangeTable.ISNULL);
				if(tte_comments==null)
					tte_comments="";
				
				TeacherTrainingExchange tte = new TeacherTrainingExchange(tte_id, tte_departmentname,
						tte_departmentnumber, tte_trainchurchyard,
						tte_trainoverseassum, tte_trainoverseasover3,
						tte_traintrade, tte_trainfordegreesum,
						tte_trainfordoctor, tte_trainformaster,
						tte_exchangecomechurchyard, tte_exchangecomeoversea,
						tte_exchangevisitchurchyard, tte_exchangevisitoversea,
						tte_serialnumber, tte_deadline, tte_college,
						tte_comments,isnull);
				
				ttes.add(tte);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return ttes;
	}

	@Override
	public int alterTeacherTrainingExchange(Map<String, String> valueMap, String id) {
		
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(TeacherTrainingExchangeTable.TTE_ID, id);
		int result = updateRecord(TeacherTrainingExchangeTable.TABLE_NAME, valueMap,
				condition);
		return result;
	}

	@Override
	public boolean batchDelete(String[] tteids) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();

		for (String tteid : tteids) {
			String sql = "delete from " + TeacherTrainingExchangeTable.TABLE_NAME
					+ " where " + TeacherTrainingExchangeTable.TTE_ID + " = '" + tteid + "'";
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
	public List<TeacherTrainingExchange> getTeacherTrainingExchange(int start, int end,
			String sortStr, String orderStr, Map<String, String> params,
			Date deadline) {
		String sql = " select tmp.* from ( " + " select * from "
				+ TeacherTrainingExchangeTable.TABLE_NAME + " where 1=1 ";
		if (deadline != null) {

			sql += String.format("and "+TeacherTrainingExchangeTable.TTE_DEADLINE+" like  '%s%%' ", deadline);
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

		List<TeacherTrainingExchange> teachertrainingexchanges = new ArrayList<TeacherTrainingExchange>();
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
				int tte_id = resultSet.getInt(TeacherTrainingExchangeTable.TTE_ID);
				String tte_departmentname = resultSet.getString(TeacherTrainingExchangeTable.TTE_DEPARTMENTNAME);
				String tte_departmentnumber = resultSet.getString(TeacherTrainingExchangeTable.TTE_DEPARTMENTNUMBER);
				Integer tte_trainchurchyard = resultSet.getInt(TeacherTrainingExchangeTable.TTE_TRAINCHURCHYARD);
				if(tte_trainchurchyard==-999)
					tte_trainchurchyard= null;
				Integer tte_trainoverseassum = resultSet.getInt(TeacherTrainingExchangeTable.TTE_TRAINOVERSEASSUM);
				if(tte_trainoverseassum==-999)
					tte_trainoverseassum = null;
				Integer tte_trainoverseasover3 = resultSet.getInt(TeacherTrainingExchangeTable.TTE_TRAINOVERSEASOVER3);
				if(tte_trainoverseasover3==-999)
					tte_trainoverseasover3 = null;
				Integer tte_traintrade = resultSet.getInt(TeacherTrainingExchangeTable.TTE_TRAINTRADE);
				if(tte_traintrade==-999)
					tte_traintrade = null;
				Integer tte_trainfordegreesum = resultSet.getInt(TeacherTrainingExchangeTable.TTE_TRAINFORDEGREESUM);
				if(tte_trainfordegreesum==-999)
					tte_trainfordegreesum = null;
				Integer tte_trainfordoctor = resultSet.getInt(TeacherTrainingExchangeTable.TTE_TRAINFORDOCTOR);
				if(tte_trainfordoctor==-999)
					tte_trainfordoctor = null;
				Integer tte_trainformaster = resultSet.getInt(TeacherTrainingExchangeTable.TTE_TRAINFORMASTER);
				if(tte_trainformaster==-999)
					tte_trainformaster = null;
				Integer tte_exchangecomechurchyard = resultSet.getInt(TeacherTrainingExchangeTable.TTE_EXCHANGECOMECHURCHYARD);
				if(tte_exchangecomechurchyard==-999)
					tte_exchangecomechurchyard = null;
				Integer tte_exchangecomeoversea = resultSet.getInt(TeacherTrainingExchangeTable.TTE_EXCHANGECOMEOVERSEA);
				if(tte_exchangecomeoversea==-999)
					tte_exchangecomeoversea = null;
				Integer tte_exchangevisitchurchyard = resultSet.getInt(TeacherTrainingExchangeTable.TTE_EXCHANGEVISITCHURCHYARD);
				if(tte_exchangevisitchurchyard==-999)
					tte_exchangevisitchurchyard = null;
				Integer tte_exchangevisitoversea = resultSet.getInt(TeacherTrainingExchangeTable.TTE_EXCHANGEVISITOVERSEA);
				if(tte_exchangevisitoversea==-999)
					tte_exchangevisitoversea = null;
				int tte_serialnumber = resultSet.getInt(TeacherTrainingExchangeTable.TTE_SERIALNUMBER);
				Date tte_deadline = resultSet.getDate(TeacherTrainingExchangeTable.TTE_DEADLINE);
				String tte_college = resultSet.getString(TeacherTrainingExchangeTable.TTE_COLLEGE);
				String tte_comments = resultSet.getString(TeacherTrainingExchangeTable.TTE_COMMENTS);
				int isnull = resultSet.getInt(TeacherTrainingExchangeTable.ISNULL);
				
				TeacherTrainingExchange tte = new TeacherTrainingExchange(tte_id, tte_departmentname,
						tte_departmentnumber, tte_trainchurchyard,
						tte_trainoverseassum, tte_trainoverseasover3,
						tte_traintrade, tte_trainfordegreesum,
						tte_trainfordoctor, tte_trainformaster,
						tte_exchangecomechurchyard, tte_exchangecomeoversea,
						tte_exchangevisitchurchyard, tte_exchangevisitoversea,
						tte_serialnumber, tte_deadline, tte_college,
						tte_comments, isnull);

				teachertrainingexchanges.add(tte);
			}
			return teachertrainingexchanges;
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
		String sql = "delete from " + TeacherTrainingExchangeTable.TABLE_NAME
				+ " where " + TeacherTrainingExchangeTable.TTE_COLLEGE + " = '" + college + "'" +" and tte_deadline is null ";
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
	public List<TeacherTrainingExchange> getAllTeacherTrainingExchange() {
		String sql = " select * from " + TeacherTrainingExchangeTable.TABLE_NAME
				+ " where 1=1 " + " order by " + TeacherTrainingExchangeTable.TTE_ID;
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
			List<TeacherTrainingExchange> teacherTrainingExchangeList = new ArrayList<TeacherTrainingExchange>();
			while (resultSet.next()) {
				int tte_id = resultSet.getInt(TeacherTrainingExchangeTable.TTE_ID);
				String tte_departmentname = resultSet.getString(TeacherTrainingExchangeTable.TTE_DEPARTMENTNAME);
				String tte_departmentnumber = resultSet.getString(TeacherTrainingExchangeTable.TTE_DEPARTMENTNUMBER);
				Integer tte_trainchurchyard = resultSet.getInt(TeacherTrainingExchangeTable.TTE_TRAINCHURCHYARD);
				if(tte_trainchurchyard==-999)
					tte_trainchurchyard= null;
				Integer tte_trainoverseassum = resultSet.getInt(TeacherTrainingExchangeTable.TTE_TRAINOVERSEASSUM);
				if(tte_trainoverseassum==-999)
					tte_trainoverseassum = null;
				Integer tte_trainoverseasover3 = resultSet.getInt(TeacherTrainingExchangeTable.TTE_TRAINOVERSEASOVER3);
				if(tte_trainoverseasover3==-999)
					tte_trainoverseasover3 = null;
				Integer tte_traintrade = resultSet.getInt(TeacherTrainingExchangeTable.TTE_TRAINTRADE);
				if(tte_traintrade==-999)
					tte_traintrade = null;
				Integer tte_trainfordegreesum = resultSet.getInt(TeacherTrainingExchangeTable.TTE_TRAINFORDEGREESUM);
				if(tte_trainfordegreesum==-999)
					tte_trainfordegreesum = null;
				Integer tte_trainfordoctor = resultSet.getInt(TeacherTrainingExchangeTable.TTE_TRAINFORDOCTOR);
				if(tte_trainfordoctor==-999)
					tte_trainfordoctor = null;
				Integer tte_trainformaster = resultSet.getInt(TeacherTrainingExchangeTable.TTE_TRAINFORMASTER);
				if(tte_trainformaster==-999)
					tte_trainformaster = null;
				Integer tte_exchangecomechurchyard = resultSet.getInt(TeacherTrainingExchangeTable.TTE_EXCHANGECOMECHURCHYARD);
				if(tte_exchangecomechurchyard==-999)
					tte_exchangecomechurchyard = null;
				Integer tte_exchangecomeoversea = resultSet.getInt(TeacherTrainingExchangeTable.TTE_EXCHANGECOMEOVERSEA);
				if(tte_exchangecomeoversea==-999)
					tte_exchangecomeoversea = null;
				Integer tte_exchangevisitchurchyard = resultSet.getInt(TeacherTrainingExchangeTable.TTE_EXCHANGEVISITCHURCHYARD);
				if(tte_exchangevisitchurchyard==-999)
					tte_exchangevisitchurchyard = null;
				Integer tte_exchangevisitoversea = resultSet.getInt(TeacherTrainingExchangeTable.TTE_EXCHANGEVISITOVERSEA);
				if(tte_exchangevisitoversea==-999)
					tte_exchangevisitoversea = null;
				int tte_serialnumber = resultSet.getInt(TeacherTrainingExchangeTable.TTE_SERIALNUMBER);
				Date tte_deadline = resultSet.getDate(TeacherTrainingExchangeTable.TTE_DEADLINE);
				String tte_college = resultSet.getString(TeacherTrainingExchangeTable.TTE_COLLEGE);
				String tte_comments = resultSet.getString(TeacherTrainingExchangeTable.TTE_COMMENTS);
				int isnull = resultSet.getInt(TeacherTrainingExchangeTable.ISNULL);
				
				TeacherTrainingExchange tte = new TeacherTrainingExchange(tte_id, tte_departmentname,
						tte_departmentnumber, tte_trainchurchyard,
						tte_trainoverseassum, tte_trainoverseasover3,
						tte_traintrade, tte_trainfordegreesum,
						tte_trainfordoctor, tte_trainformaster,
						tte_exchangecomechurchyard, tte_exchangecomeoversea,
						tte_exchangevisitchurchyard, tte_exchangevisitoversea,
						tte_serialnumber, tte_deadline, tte_college,
						tte_comments, isnull);
				teacherTrainingExchangeList.add(tte);
			}
			return teacherTrainingExchangeList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
	}
}
