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

import cn.edu.xmu.dao.StuPhysicalHealthInfoDao;
import cn.edu.xmu.entity.StuPhysicalHealthInfo;
import cn.edu.xmu.table.StuPhysicalHealthInfoTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;

/**
 * 附表6-2-1-8厦门大学学生体质健康情况（学年）
 * @author yue
 *
 */
public class StuPhysicalHealthInfoDaoImpl extends BaseDaoImpl<StuPhysicalHealthInfo> implements StuPhysicalHealthInfoDao{

	@Override
	public int addStuPhysicalHealthInfoRecord(StuPhysicalHealthInfo sphi) {
		int result = 0;
		String t_sql = "update " + StuPhysicalHealthInfoTable.TABLE_NAME + " set "
				+StuPhysicalHealthInfoTable.SPHI_SERIALNUMBER + " = "
				+StuPhysicalHealthInfoTable.SPHI_SERIALNUMBER + " +1 where "
				+StuPhysicalHealthInfoTable.SPHI_SERIALNUMBER + " >=?";
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
			t_pstmt.setInt(1, sphi.getSphi_serialnumber());
			
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
		String sql = "insert into " + StuPhysicalHealthInfoTable.TABLE_NAME + "("
				+ StuPhysicalHealthInfoTable.SPHI_GRADE +  ","
				+ StuPhysicalHealthInfoTable.SPHI_TOTALNUMBER + ","
				+ StuPhysicalHealthInfoTable.SPHI_FREETESTNUMBER + ","
				+ StuPhysicalHealthInfoTable.SPHI_TESTNUMBER + ","
				+ StuPhysicalHealthInfoTable.SPHI_PASSNUMBER + ","
				+ StuPhysicalHealthInfoTable.SPHI_GOODNUMBER + ","
				+ StuPhysicalHealthInfoTable.SPHI_EXCELLENTNUMBER + ","
				+ StuPhysicalHealthInfoTable.SPHI_SERIALNUMBER + ","
				+ StuPhysicalHealthInfoTable.SPHI_COLLEGE + ","
				+ StuPhysicalHealthInfoTable.SPHI_COMMENTS + ","
				+ StuPhysicalHealthInfoTable.ISNULL
				+ ")values(?,?,?,?,?,?,?,?,?,?,?)";

		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, sphi.getSphi_grade());
			pstmt.setInt(2, sphi.getSphi_totalnumber());
			pstmt.setInt(3, sphi.getSphi_freetestnumber());
			pstmt.setInt(4, sphi.getSphi_testnumber());
			pstmt.setInt(5, sphi.getSphi_passnumber());
			pstmt.setInt(6, sphi.getSphi_goodnumber());
			pstmt.setInt(7, sphi.getSphi_excellentnumber());
			pstmt.setInt(8, sphi.getSphi_serialnumber());
			pstmt.setString(9, sphi.getSphi_college());
			pstmt.setString(10, sphi.getSphi_comments());
			pstmt.setInt(11, sphi.getIsnull());
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
	public boolean batchDelete(String[] sphiids) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();
		
		for(String sphiid:sphiids)
		{
			String sql = "delete from " + StuPhysicalHealthInfoTable.TABLE_NAME
					+ " where " + StuPhysicalHealthInfoTable.SPHI_ID + " = '" +sphiid + "'";
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
	public int alterStuPhysicalHealthInfoInfo(Map<String, String> valueMap, String id) {
		Map<String, String> condition = new HashMap<String,String>();
		condition.put(StuPhysicalHealthInfoTable.SPHI_ID, id);
		int result = updateRecord(StuPhysicalHealthInfoTable.TABLE_NAME, valueMap, condition);
		return result;
	}

	@Override
	public int getStuPhysicalHealthInfoCount(Map queryParams) {
		int count = 0;
		//获取条件的语句
		String sql = "select count(*) from " + StuPhysicalHealthInfoTable.TABLE_NAME
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
	public List<StuPhysicalHealthInfo> getStuPhysicalHealthInfo(int start, int end, String sortStr, String orderStr,
			Map<String, String> params, Date deadline) {
		String sql = " select tmp.* from ( " + " select * from "
				+ StuPhysicalHealthInfoTable.TABLE_NAME + " where 1=1 ";
		if (deadline != null) {

			sql += String.format("and "+StuPhysicalHealthInfoTable.SPHI_DEADLINE+" like  '%s%%' ", deadline);
		}
		if (!(params == null || params.keySet().size() == 0)) {
			for (Object object : params.keySet()) {

				String key = object.toString();
				String value = params.get(key).toString();
				sql += String.format(" and %s like  '%%%s%%' ", key, value);
			}
		}

		sql += " order by " + sortStr + " " + orderStr + " ) tmp limit "
				+ start + " ," + end;

		System.out.println(sql);
		
		List<StuPhysicalHealthInfo> sphis = new ArrayList<StuPhysicalHealthInfo>();
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
				int sphi_id = resultSet.getInt(StuPhysicalHealthInfoTable.SPHI_ID);
				String sphi_grade = resultSet.getString(StuPhysicalHealthInfoTable.SPHI_GRADE);
				Integer sphi_totalnumber = resultSet.getInt(StuPhysicalHealthInfoTable.SPHI_TOTALNUMBER);
				if(sphi_totalnumber == -999)
					sphi_totalnumber = null;
				Integer sphi_freetestnumber = resultSet.getInt(StuPhysicalHealthInfoTable.SPHI_FREETESTNUMBER);
				if(sphi_freetestnumber == -999)
					sphi_freetestnumber = null;
				Integer sphi_testnumber = resultSet.getInt(StuPhysicalHealthInfoTable.SPHI_TESTNUMBER);
				if(sphi_testnumber == -999)
					sphi_testnumber = null;
				Integer sphi_passnumber = resultSet.getInt(StuPhysicalHealthInfoTable.SPHI_PASSNUMBER);
				if(sphi_passnumber == -999)
					sphi_passnumber = null;
				Integer sphi_goodnumber = resultSet.getInt(StuPhysicalHealthInfoTable.SPHI_GOODNUMBER);
				if(sphi_goodnumber == -999)
					sphi_goodnumber = null;
				Integer sphi_excellentnumber = resultSet.getInt(StuPhysicalHealthInfoTable.SPHI_EXCELLENTNUMBER);
				if(sphi_excellentnumber == -999)
					sphi_excellentnumber = null;
				int sphi_serialnumber = resultSet.getInt(StuPhysicalHealthInfoTable.SPHI_SERIALNUMBER);
				String sphi_college = resultSet.getString(StuPhysicalHealthInfoTable.SPHI_COLLEGE);//填报学院
			    Date sphi_deadline = resultSet.getDate(StuPhysicalHealthInfoTable.SPHI_DEADLINE);//截止时间    
				String sphi_comments = resultSet.getString(StuPhysicalHealthInfoTable.SPHI_COMMENTS);
				int isnull = resultSet.getInt(StuPhysicalHealthInfoTable.ISNULL);
				
				StuPhysicalHealthInfo sphi = new StuPhysicalHealthInfo(sphi_id, sphi_grade, sphi_totalnumber,
						sphi_freetestnumber, sphi_testnumber, sphi_passnumber, sphi_goodnumber, 
						sphi_excellentnumber, sphi_serialnumber, sphi_deadline, sphi_college, 
						sphi_comments,isnull);

				sphis.add(sphi);
			}
			return sphis;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		
	}

	@Override
	public List<StuPhysicalHealthInfo> getSPHISumorNoSum(String college, boolean flag) {
		String sqlString;
		if(flag)
		{
			sqlString = "select * from "+StuPhysicalHealthInfoTable.TABLE_NAME+" where "+
					StuPhysicalHealthInfoTable.SPHI_GRADE+" = \'合计\' and "+
					StuPhysicalHealthInfoTable.SPHI_COLLEGE+" = \'"+college+"\' and "+ StuPhysicalHealthInfoTable.SPHI_DEADLINE+
					" is null";
		}
		else {
			sqlString = "select * from "+StuPhysicalHealthInfoTable.TABLE_NAME+" where "+
					StuPhysicalHealthInfoTable.SPHI_GRADE+" != \'合计\' and "+
					StuPhysicalHealthInfoTable.SPHI_COLLEGE+" = \'"+college+"\' and "+StuPhysicalHealthInfoTable.SPHI_DEADLINE+
					" is null";
		}
		
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		List<StuPhysicalHealthInfo> sphis = new ArrayList<StuPhysicalHealthInfo>();
		try {
			System.out.println(sqlString);
			pstmt = connection.prepareStatement(sqlString);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				int sphi_id = resultSet.getInt(StuPhysicalHealthInfoTable.SPHI_ID);
				String sphi_grade = resultSet.getString(StuPhysicalHealthInfoTable.SPHI_GRADE);
				Integer sphi_totalnumber = resultSet.getInt(StuPhysicalHealthInfoTable.SPHI_TOTALNUMBER);
				if(sphi_totalnumber == -999)
					sphi_totalnumber = null;
				Integer sphi_freetestnumber = resultSet.getInt(StuPhysicalHealthInfoTable.SPHI_FREETESTNUMBER);
				if(sphi_freetestnumber == -999)
					sphi_freetestnumber = null;
				Integer sphi_testnumber = resultSet.getInt(StuPhysicalHealthInfoTable.SPHI_TESTNUMBER);
				if(sphi_testnumber == -999)
					sphi_testnumber = null;
				Integer sphi_passnumber = resultSet.getInt(StuPhysicalHealthInfoTable.SPHI_PASSNUMBER);
				if(sphi_passnumber == -999)
					sphi_passnumber = null;
				Integer sphi_goodnumber = resultSet.getInt(StuPhysicalHealthInfoTable.SPHI_GOODNUMBER);
				if(sphi_goodnumber == -999)
					sphi_goodnumber = null;
				Integer sphi_excellentnumber = resultSet.getInt(StuPhysicalHealthInfoTable.SPHI_EXCELLENTNUMBER);
				if(sphi_excellentnumber == -999)
					sphi_excellentnumber = null;
				int sphi_serialnumber = resultSet.getInt(StuPhysicalHealthInfoTable.SPHI_SERIALNUMBER);
				String sphi_college = resultSet.getString(StuPhysicalHealthInfoTable.SPHI_COLLEGE);//填报学院
			    Date sphi_deadline = resultSet.getDate(StuPhysicalHealthInfoTable.SPHI_DEADLINE);//截止时间    
				String sphi_comments = resultSet.getString(StuPhysicalHealthInfoTable.SPHI_COMMENTS);
				int isnull = resultSet.getInt(StuPhysicalHealthInfoTable.ISNULL);
				
				StuPhysicalHealthInfo sphi = new StuPhysicalHealthInfo(sphi_id, sphi_grade, sphi_totalnumber,
						sphi_freetestnumber, sphi_testnumber, sphi_passnumber, sphi_goodnumber, 
						sphi_excellentnumber, sphi_serialnumber, sphi_deadline, sphi_college, 
						sphi_comments,isnull);

				sphis.add(sphi);
			}
			return sphis;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
	}

	@Override
	public void deleteByCollegeandDeadline(String college, Date deadline) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();
		String sql = "delete from " + StuPhysicalHealthInfoTable.TABLE_NAME
				+ " where " + StuPhysicalHealthInfoTable.SPHI_COLLEGE + " = '" + college + "'" +" and sphi_deadline is null ";
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
	public List<StuPhysicalHealthInfo> getAllStuPhysicalHealthInfo() {
		String sql = " select * from " + StuPhysicalHealthInfoTable.TABLE_NAME
				+ " where 1=1 " + " order by " + StuPhysicalHealthInfoTable.SPHI_SERIALNUMBER+", "+StuPhysicalHealthInfoTable.SPHI_ID;
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
			List<StuPhysicalHealthInfo> sphis = new ArrayList<StuPhysicalHealthInfo>();
			while (resultSet.next()) {
				int sphi_id = resultSet.getInt(StuPhysicalHealthInfoTable.SPHI_ID);
				String sphi_grade = resultSet.getString(StuPhysicalHealthInfoTable.SPHI_GRADE);
				Integer sphi_totalnumber = resultSet.getInt(StuPhysicalHealthInfoTable.SPHI_TOTALNUMBER);
				if(sphi_totalnumber == -999)
					sphi_totalnumber = null;
				Integer sphi_freetestnumber = resultSet.getInt(StuPhysicalHealthInfoTable.SPHI_FREETESTNUMBER);
				if(sphi_freetestnumber == -999)
					sphi_freetestnumber = null;
				Integer sphi_testnumber = resultSet.getInt(StuPhysicalHealthInfoTable.SPHI_TESTNUMBER);
				if(sphi_testnumber == -999)
					sphi_testnumber = null;
				Integer sphi_passnumber = resultSet.getInt(StuPhysicalHealthInfoTable.SPHI_PASSNUMBER);
				if(sphi_passnumber == -999)
					sphi_passnumber = null;
				Integer sphi_goodnumber = resultSet.getInt(StuPhysicalHealthInfoTable.SPHI_GOODNUMBER);
				if(sphi_goodnumber == -999)
					sphi_goodnumber = null;
				Integer sphi_excellentnumber = resultSet.getInt(StuPhysicalHealthInfoTable.SPHI_EXCELLENTNUMBER);
				if(sphi_excellentnumber == -999)
					sphi_excellentnumber = null;
				int sphi_serialnumber = resultSet.getInt(StuPhysicalHealthInfoTable.SPHI_SERIALNUMBER);
				String sphi_college = resultSet.getString(StuPhysicalHealthInfoTable.SPHI_COLLEGE);//填报学院
			    Date sphi_deadline = resultSet.getDate(StuPhysicalHealthInfoTable.SPHI_DEADLINE);//截止时间    
				String sphi_comments = resultSet.getString(StuPhysicalHealthInfoTable.SPHI_COMMENTS);
				int isnull = resultSet.getInt(StuPhysicalHealthInfoTable.ISNULL);
				
				StuPhysicalHealthInfo sphi = new StuPhysicalHealthInfo(sphi_id, sphi_grade, sphi_totalnumber,
						sphi_freetestnumber, sphi_testnumber, sphi_passnumber, sphi_goodnumber, 
						sphi_excellentnumber, sphi_serialnumber, sphi_deadline, sphi_college, 
						sphi_comments,isnull);

				sphis.add(sphi);
			}
			return sphis;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
	}


}
