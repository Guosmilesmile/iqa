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


import cn.edu.xmu.dao.AlumnusAndSocialCoopDao;
import cn.edu.xmu.entity.AlumnusAndSocialCoop;
import cn.edu.xmu.table.AlumnusAndSocialCoppTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;

/**
 * 表1-7  校友会与社会合作
 * @author yue
 *
 */
public class AlumnusAndSocialCoopDaoImpl extends BaseDaoImpl<AlumnusAndSocialCoop> implements AlumnusAndSocialCoopDao{

	@Override
	public int addAlumnusAndSocialCoopRecord(AlumnusAndSocialCoop as) {
		int result = 0;

		String t_sql = "update " + AlumnusAndSocialCoppTable.TABLE_NAME + " set "
				+ AlumnusAndSocialCoppTable.AS_SERIALNUMBER + " = "
				+ AlumnusAndSocialCoppTable.AS_SERIALNUMBER + " +1 where "
				+ AlumnusAndSocialCoppTable.AS_SERIALNUMBER + ">=?";

		
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
			t_pstmt.setInt(1, as.getAs_serialnumber());
			
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
		String sql = "insert into " + AlumnusAndSocialCoppTable.TABLE_NAME + "("
				+ AlumnusAndSocialCoppTable.AS_ALUMNUSAMOUNT +  ","
				+ AlumnusAndSocialCoppTable.AS_DOMESTICALUMNUS + ","
				+ AlumnusAndSocialCoppTable.AS_OVERSEAALUMNUS + ","
				+ AlumnusAndSocialCoppTable.AS_AGENCYAMOUNT+ ","
				+ AlumnusAndSocialCoppTable.AS_ACADEMIC + ","
				+ AlumnusAndSocialCoppTable.AS_INDUSTRY + ","
				+ AlumnusAndSocialCoppTable.AS_GOVERNMENT + ","
				+ AlumnusAndSocialCoppTable.AS_SERIALNUMBER + ","
				+ AlumnusAndSocialCoppTable.AS_COLLEGE + ","
				+ AlumnusAndSocialCoppTable.AS_COMMENTS + ","
				+ AlumnusAndSocialCoppTable.ISNULL
				+ ")values(?,?,?,?,?,?,?,?,?,?,?)";
		
		System.out.println("添加纪录" + sql);
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		PreparedStatement pstmt = null;

		try {
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, as.getAs_alumnusamount());
			pstmt.setInt(2, as.getAs_domesticalumnus());
			pstmt.setInt(3, as.getAs_overseaalumnus());
			pstmt.setInt(4, as.getAs_agencyamount());
			pstmt.setInt(5, as.getAs_academic());
			pstmt.setInt(6, as.getAs_industry());
			pstmt.setInt(7, as.getAs_government());
			pstmt.setInt(8, as.getAs_serialnumber());
			pstmt.setString(9, as.getAs_college());
			pstmt.setString(10, as.getAs_comments());
			pstmt.setInt(11, as.getIsnull());
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
	public boolean batchDelete(String[] asids) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();
		
		for(String asid:asids)
		{
			String sql = "delete from " + AlumnusAndSocialCoppTable.TABLE_NAME
					+ " where " + AlumnusAndSocialCoppTable.AS_ID + " = '" +asid + "'";
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
	public int alterAlumnusAndSocialCoop(Map<String, String> valueMap, String id) {
		Map<String, String> condition = new HashMap<String,String>();
		condition.put(AlumnusAndSocialCoppTable.AS_ID, id);
		int result = updateRecord(AlumnusAndSocialCoppTable.TABLE_NAME, valueMap, condition);
		return result;
	}

	@Override
	public int getAlumnusAndSocialCoopCount(Map queryParams) {
		int count = 0;
		//获取条件的语句
		String sql = "select count(*) from " + AlumnusAndSocialCoppTable.TABLE_NAME
				+" where 1 = 1";
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
	public List<AlumnusAndSocialCoop> getAlumnusAndSocialCoop(int start, int end, String sortStr, String orderStr,
			Map<String, String> params,Date deadline) {
		String sql = " select tmp.* from ( " + " select * from "
				+ AlumnusAndSocialCoppTable.TABLE_NAME + " where 1=1 ";
		if (deadline != null) {

			sql += String.format("and "+AlumnusAndSocialCoppTable.AS_DEADLINE+" like  '%s%%' ", deadline);
		}
		if (!(params == null || params.keySet().size() == 0)) {
			for (Object object : params.keySet()) {

				String key = object.toString();
				String value = params.get(key).toString();
				sql += String.format(" and %s like  '%%%s%%'  ", key, value);
			}
		}

		if (sortStr == "nope") {
			
		}else {
			sql += " order by " + sortStr + " " + orderStr + " ) tmp limit "
					+ start + " ," + end;
		}


		System.out.println(sql);
		
		List<AlumnusAndSocialCoop> ass = new ArrayList<AlumnusAndSocialCoop>();
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
				int as_id = resultSet.getInt(AlumnusAndSocialCoppTable.AS_ID);
				Integer as_alumnusamount = resultSet.getInt(AlumnusAndSocialCoppTable.AS_ALUMNUSAMOUNT);
				if(as_alumnusamount == -999)
					as_alumnusamount =  null;
				Integer as_domesticalumnus = resultSet.getInt(AlumnusAndSocialCoppTable.AS_DOMESTICALUMNUS);
				if(as_domesticalumnus == -999)
					as_domesticalumnus =  null;
				Integer as_overseaalumnus = resultSet.getInt(AlumnusAndSocialCoppTable.AS_OVERSEAALUMNUS);
				if(as_overseaalumnus == -999)
					as_overseaalumnus =  null;
				Integer as_agencyamount = resultSet.getInt(AlumnusAndSocialCoppTable.AS_AGENCYAMOUNT);
				if(as_agencyamount == -999)
					as_agencyamount =  null;
				Integer as_academic = resultSet.getInt(AlumnusAndSocialCoppTable.AS_ACADEMIC);
				if(as_academic == -999)
					as_academic =  null;
				Integer as_industry = resultSet.getInt(AlumnusAndSocialCoppTable.AS_INDUSTRY);
				if(as_industry == -999)
					as_industry =  null;
				Integer as_government = resultSet.getInt(AlumnusAndSocialCoppTable.AS_GOVERNMENT);			
				if(as_government == -999)
					as_government =  null;
				int as_serialnumber = resultSet.getInt(AlumnusAndSocialCoppTable.AS_SERIALNUMBER);
				String as_college = resultSet.getString(AlumnusAndSocialCoppTable.AS_COLLEGE);//填报学院
			    Date as_deadline = resultSet.getDate(AlumnusAndSocialCoppTable.AS_DEADLINE);//截止时间    
				String as_comments = resultSet.getString(AlumnusAndSocialCoppTable.AS_COMMENTS);
				int isnull = resultSet.getInt(AlumnusAndSocialCoppTable.ISNULL);
				
				AlumnusAndSocialCoop as = new AlumnusAndSocialCoop(as_id, as_alumnusamount, as_domesticalumnus, as_overseaalumnus, 
						as_agencyamount, as_academic, as_industry, as_government, as_serialnumber,as_deadline,as_college, 
						as_comments,isnull);
				ass.add(as);
			}
			return ass;
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
		String sql = "delete from " + AlumnusAndSocialCoppTable.TABLE_NAME
				+ " where " + AlumnusAndSocialCoppTable.AS_COLLEGE + " = '" + college + "'" +" and as_deadline is null ";
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
	public List<AlumnusAndSocialCoop> getAllAlumnusAndSocialCoop() {
		String sql = " select * from " + AlumnusAndSocialCoppTable.TABLE_NAME
				+ " where 1=1 " + " order by " + AlumnusAndSocialCoppTable.AS_ID;
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
			List<AlumnusAndSocialCoop> ass = new ArrayList<AlumnusAndSocialCoop>();
			while (resultSet.next()) {
				int as_id = resultSet.getInt(AlumnusAndSocialCoppTable.AS_ID);
				Integer as_alumnusamount = resultSet.getInt(AlumnusAndSocialCoppTable.AS_ALUMNUSAMOUNT);
				if(as_alumnusamount == -999)
					as_alumnusamount =  null;
				Integer as_domesticalumnus = resultSet.getInt(AlumnusAndSocialCoppTable.AS_DOMESTICALUMNUS);
				if(as_domesticalumnus == -999)
					as_domesticalumnus =  null;
				Integer as_overseaalumnus = resultSet.getInt(AlumnusAndSocialCoppTable.AS_OVERSEAALUMNUS);
				if(as_overseaalumnus == -999)
					as_overseaalumnus =  null;
				Integer as_agencyamount = resultSet.getInt(AlumnusAndSocialCoppTable.AS_AGENCYAMOUNT);
				if(as_agencyamount == -999)
					as_agencyamount =  null;
				Integer as_academic = resultSet.getInt(AlumnusAndSocialCoppTable.AS_ACADEMIC);
				if(as_academic == -999)
					as_academic =  null;
				Integer as_industry = resultSet.getInt(AlumnusAndSocialCoppTable.AS_INDUSTRY);
				if(as_industry == -999)
					as_industry =  null;
				Integer as_government = resultSet.getInt(AlumnusAndSocialCoppTable.AS_GOVERNMENT);			
				if(as_government == -999)
					as_government =  null;
				int as_serialnumber = resultSet.getInt(AlumnusAndSocialCoppTable.AS_SERIALNUMBER);
				String as_college = resultSet.getString(AlumnusAndSocialCoppTable.AS_COLLEGE);//填报学院
			    Date as_deadline = resultSet.getDate(AlumnusAndSocialCoppTable.AS_DEADLINE);//截止时间    
				String as_comments = resultSet.getString(AlumnusAndSocialCoppTable.AS_COMMENTS);
				int isnull = resultSet.getInt(AlumnusAndSocialCoppTable.ISNULL);
				
				AlumnusAndSocialCoop as = new AlumnusAndSocialCoop(as_id, as_alumnusamount, as_domesticalumnus, as_overseaalumnus, 
						as_agencyamount, as_academic, as_industry, as_government, as_serialnumber,as_deadline,as_college, 
						as_comments,isnull);
				ass.add(as);
			}
			return ass;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
	}

	


}
