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

import cn.edu.xmu.dao.ForeignStudentInfoDao;
import cn.edu.xmu.entity.ForeignStudentInfo;
import cn.edu.xmu.table.ForeignStudentInfoTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;

public class ForeignStudentInfoDaoImpl extends BaseDaoImpl<ForeignStudentInfo> implements ForeignStudentInfoDao{

	@Override
	public int addForeignStudentInfoRecord(ForeignStudentInfo fsi) {
		int result = 0;
		String t_sql = "update " + ForeignStudentInfoTable.TABLE_NAME + " set "
				+ForeignStudentInfoTable.FSI_SERIALNUMBER + " = "
				+ForeignStudentInfoTable.FSI_SERIALNUMBER + " +1 where "
				+ForeignStudentInfoTable.FSI_SERIALNUMBER + " >=?";
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
			t_pstmt.setInt(1, fsi.getFsi_serialnumber());
			
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
		String sql = "insert into " + ForeignStudentInfoTable.TABLE_NAME + "("
				+ ForeignStudentInfoTable.FSI_ALLGRADUATENUMBER +  ","
				+ ForeignStudentInfoTable.FSI_ALLDEGREENUMBER + ","
				+ ForeignStudentInfoTable.FSI_ALLENROLLNUMBER + ","
				+ ForeignStudentInfoTable.FSI_ALLCURRENTSTUDENTNUMBER + ","
				+ ForeignStudentInfoTable.FSI_FOREIGNGRADUATENUMBER +  ","
				+ ForeignStudentInfoTable.FSI_FOREIGNDEGREENUMBER + ","
				+ ForeignStudentInfoTable.FSI_FOREIGNENROLLNUMBER + ","
				+ ForeignStudentInfoTable.FSI_FOREIGNCURRENTSTUDENTNUMBER + ","
				+ ForeignStudentInfoTable.FSI_HKGRADUATENUMBER +  ","
				+ ForeignStudentInfoTable.FSI_HKDEGREENUMBER + ","
				+ ForeignStudentInfoTable.FSI_HKENROLLNUMBER + ","
				+ ForeignStudentInfoTable.FSI_HKCURRENTSTUDENTNUMBER + ","
				+ ForeignStudentInfoTable.FSI_MACGRADUATENUMBER +  ","
				+ ForeignStudentInfoTable.FSI_MACDEGREENUMBER + ","
				+ ForeignStudentInfoTable.FSI_MACENROLLNUMBER + ","
				+ ForeignStudentInfoTable.FSI_MACCURRENTSTUDENTNUMBER + ","
				+ ForeignStudentInfoTable.FSI_TWNGRADUATENUMBER +  ","
				+ ForeignStudentInfoTable.FSI_TWNDEGREENUMBER + ","
				+ ForeignStudentInfoTable.FSI_TWNENROLLNUMBER + ","
				+ ForeignStudentInfoTable.FSI_TWNCURRENTSTUDENTNUMBER + ","
				+ ForeignStudentInfoTable.FSI_SERIALNUMBER + ","
				+ ForeignStudentInfoTable.FSI_COLLEGE + ","
				+ ForeignStudentInfoTable.FSI_COMMENTS + ","
				+ ForeignStudentInfoTable.ISNULL
				+ ")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, fsi.getFsi_allgraduatenumber());
			pstmt.setInt(2, fsi.getFsi_alldegreenumber());
			pstmt.setInt(3, fsi.getFsi_allenrollnumber());
			pstmt.setInt(4, fsi.getFsi_allcurrentstudentnumber());
			pstmt.setInt(5, fsi.getFsi_foreigngraduatenumber());
			pstmt.setInt(6, fsi.getFsi_foreigndegreenumber());
			pstmt.setInt(7, fsi.getFsi_foreignenrollnumber());
			pstmt.setInt(8, fsi.getFsi_foreigncurrentstudentnumber());
			pstmt.setInt(9, fsi.getFsi_hkgraduatenumber());
			pstmt.setInt(10, fsi.getFsi_hkdegreenumber());
			pstmt.setInt(11, fsi.getFsi_hkenrollnumber());
			pstmt.setInt(12, fsi.getFsi_hkcurrentstudentnumber());
			pstmt.setInt(13, fsi.getFsi_macgraduatenumber());
			pstmt.setInt(14, fsi.getFsi_macdegreenumber());
			pstmt.setInt(15, fsi.getFsi_macenrollnumber());
			pstmt.setInt(16, fsi.getFsi_maccurrentstudentnumber());
			pstmt.setInt(17, fsi.getFsi_twngraduatenumber());
			pstmt.setInt(18, fsi.getFsi_twndegreenumber());
			pstmt.setInt(19, fsi.getFsi_twnenrollnumber());
			pstmt.setInt(20, fsi.getFsi_twncurrentstudentnumber());
			pstmt.setInt(21, fsi.getFsi_serialnumber());
			pstmt.setString(22, fsi.getFsi_college());
			pstmt.setString(23, fsi.getFsi_comments());
			pstmt.setInt(24, fsi.getIsnull());
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
	public boolean batchDelete(String[] fsiids) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();
		
		for(String fsiid:fsiids)
		{
			String sql = "delete from " + ForeignStudentInfoTable.TABLE_NAME
					+ " where " + ForeignStudentInfoTable.FSI_ID + " = '" +fsiid + "'";
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
	public int alterForeignStudentInfo(Map<String, String> valueMap, String id) {
		Map<String, String> condition = new HashMap<String,String>();
		condition.put(ForeignStudentInfoTable.FSI_ID, id);
		int result = updateRecord(ForeignStudentInfoTable.TABLE_NAME, valueMap, condition);
		return result;
	}

	@Override
	public int getForeignStudentInfoCount(Map queryParams) {
		int count = 0;
		//获取条件的语句
		String sql = "select count(*) from " + ForeignStudentInfoTable.TABLE_NAME
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
	public List<ForeignStudentInfo> getForeignStudentInfo(int start, int end, String sortStr, String orderStr,
			Map<String, String> params, Date deadline) {
		String sql = " select tmp.* from ( " + " select * from "
				+ ForeignStudentInfoTable.TABLE_NAME + " where 1=1 ";
		if (deadline != null) {

			sql += String.format("and "+ForeignStudentInfoTable.FSI_DEADLINE+" like  '%s%%' ", deadline);
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
		
		List<ForeignStudentInfo> fsis = new ArrayList<ForeignStudentInfo>();
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
				int fsi_id = resultSet.getInt(ForeignStudentInfoTable.FSI_ID);
				Integer fsi_allgraduatenumber = resultSet.getInt(ForeignStudentInfoTable.FSI_ALLGRADUATENUMBER);
				if(fsi_allgraduatenumber == -999)
					fsi_allgraduatenumber = null;
				Integer fsi_alldegreenumber = resultSet.getInt(ForeignStudentInfoTable.FSI_ALLDEGREENUMBER);
				if(fsi_alldegreenumber == -999)
					fsi_alldegreenumber = null;
				Integer fsi_allenrollnumber = resultSet.getInt(ForeignStudentInfoTable.FSI_ALLENROLLNUMBER);
				if(fsi_allenrollnumber == -999)
					fsi_allenrollnumber = null;
				Integer fsi_allcurrentstudentnumber = resultSet.getInt(ForeignStudentInfoTable.FSI_ALLCURRENTSTUDENTNUMBER);
				if(fsi_allcurrentstudentnumber == -999)
					fsi_allcurrentstudentnumber = null;
				Integer fsi_foreigngraduatenumber = resultSet.getInt(ForeignStudentInfoTable.FSI_FOREIGNGRADUATENUMBER);
				if(fsi_foreigngraduatenumber == -999)
					fsi_foreigngraduatenumber = null;
				Integer fsi_foreigndegreenumber = resultSet.getInt(ForeignStudentInfoTable.FSI_FOREIGNDEGREENUMBER);
				if(fsi_foreigndegreenumber == -999)
					fsi_foreigndegreenumber = null;
				Integer fsi_foreignenrollnumber = resultSet.getInt(ForeignStudentInfoTable.FSI_FOREIGNENROLLNUMBER);
				if(fsi_foreignenrollnumber == -999)
					fsi_foreignenrollnumber = null;
				Integer fsi_foreigncurrentstudentnumber = resultSet.getInt(ForeignStudentInfoTable.FSI_FOREIGNCURRENTSTUDENTNUMBER);
				if(fsi_foreigncurrentstudentnumber == -999)
					fsi_foreigncurrentstudentnumber = null;
				Integer fsi_hkgraduatenumber = resultSet.getInt(ForeignStudentInfoTable.FSI_HKGRADUATENUMBER);
				if(fsi_hkgraduatenumber == -999)
					fsi_hkgraduatenumber = null;
				Integer fsi_hkdegreenumber = resultSet.getInt(ForeignStudentInfoTable.FSI_HKDEGREENUMBER);
				if(fsi_hkdegreenumber == -999)
					fsi_hkdegreenumber = null;
				Integer fsi_hkenrollnumber = resultSet.getInt(ForeignStudentInfoTable.FSI_HKENROLLNUMBER);
				if(fsi_hkenrollnumber == -999)
					fsi_hkenrollnumber = null;
				Integer fsi_hkcurrentstudentnumber = resultSet.getInt(ForeignStudentInfoTable.FSI_HKCURRENTSTUDENTNUMBER);
				if(fsi_hkcurrentstudentnumber == -999)
					fsi_hkcurrentstudentnumber = null;
				Integer fsi_macgraduatenumber = resultSet.getInt(ForeignStudentInfoTable.FSI_MACGRADUATENUMBER);
				if(fsi_macgraduatenumber == -999)
					fsi_macgraduatenumber = null;
				Integer fsi_macdegreenumber = resultSet.getInt(ForeignStudentInfoTable.FSI_MACDEGREENUMBER);
				if(fsi_macdegreenumber == -999)
					fsi_macdegreenumber = null;
				Integer fsi_macenrollnumber = resultSet.getInt(ForeignStudentInfoTable.FSI_MACENROLLNUMBER);
				if(fsi_macenrollnumber == -999)
					fsi_macenrollnumber = null;
				Integer fsi_maccurrentstudentnumber = resultSet.getInt(ForeignStudentInfoTable.FSI_MACCURRENTSTUDENTNUMBER);
				if(fsi_maccurrentstudentnumber == -999)
					fsi_maccurrentstudentnumber = null;
				Integer fsi_twngraduatenumber = resultSet.getInt(ForeignStudentInfoTable.FSI_TWNGRADUATENUMBER);
				if(fsi_twngraduatenumber == -999)
					fsi_twngraduatenumber = null;
				Integer fsi_twndegreenumber = resultSet.getInt(ForeignStudentInfoTable.FSI_TWNDEGREENUMBER);
				if(fsi_twndegreenumber == -999)
					fsi_twndegreenumber = null;
				Integer fsi_twnenrollnumber = resultSet.getInt(ForeignStudentInfoTable.FSI_TWNENROLLNUMBER);
				if(fsi_twnenrollnumber == -999)
					fsi_twnenrollnumber = null;
				Integer fsi_twncurrentstudentnumber = resultSet.getInt(ForeignStudentInfoTable.FSI_TWNCURRENTSTUDENTNUMBER);
				if(fsi_twncurrentstudentnumber == -999)
					fsi_twncurrentstudentnumber = null;
				
				int fsi_serialnumber = resultSet.getInt(ForeignStudentInfoTable.FSI_SERIALNUMBER);
				String fsi_college = resultSet.getString(ForeignStudentInfoTable.FSI_COLLEGE);//填报学院
			    Date fsi_deadline = resultSet.getDate(ForeignStudentInfoTable.FSI_DEADLINE);//截止时间    
				String fsi_comments = resultSet.getString(ForeignStudentInfoTable.FSI_COMMENTS);
				int isnull = resultSet.getInt(ForeignStudentInfoTable.ISNULL);
				
				ForeignStudentInfo fsi = new ForeignStudentInfo(fsi_id, fsi_allgraduatenumber, fsi_alldegreenumber, fsi_allenrollnumber, 
						fsi_allcurrentstudentnumber, fsi_foreigngraduatenumber, fsi_foreigndegreenumber, fsi_foreignenrollnumber, 
						fsi_foreigncurrentstudentnumber, fsi_hkgraduatenumber, fsi_hkdegreenumber, fsi_hkenrollnumber, 
						fsi_hkcurrentstudentnumber, fsi_macgraduatenumber, fsi_macdegreenumber, fsi_macenrollnumber, 
						fsi_maccurrentstudentnumber, fsi_twngraduatenumber, fsi_twndegreenumber, fsi_twnenrollnumber, 
						fsi_twncurrentstudentnumber, fsi_serialnumber, fsi_deadline, fsi_college, fsi_comments,isnull);
				fsis.add(fsi);
			}
			return fsis;
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
		String sql = "delete from " + ForeignStudentInfoTable.TABLE_NAME
				+ " where " + ForeignStudentInfoTable.FSI_COLLEGE + " = '" + college + "'" +" and fsi_deadline is null ";
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
	public List<ForeignStudentInfo> getAllForeignStudentInfo() {
		String sql = " select * from " + ForeignStudentInfoTable.TABLE_NAME
				+ " where 1=1 " + " order by " + ForeignStudentInfoTable.FSI_ID;
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
			List<ForeignStudentInfo> fsis = new ArrayList<ForeignStudentInfo>();
			while (resultSet.next()) {
				int fsi_id = resultSet.getInt(ForeignStudentInfoTable.FSI_ID);
				Integer fsi_allgraduatenumber = resultSet.getInt(ForeignStudentInfoTable.FSI_ALLGRADUATENUMBER);
				if(fsi_allgraduatenumber == -999)
					fsi_allgraduatenumber = null;
				Integer fsi_alldegreenumber = resultSet.getInt(ForeignStudentInfoTable.FSI_ALLDEGREENUMBER);
				if(fsi_alldegreenumber == -999)
					fsi_alldegreenumber = null;
				Integer fsi_allenrollnumber = resultSet.getInt(ForeignStudentInfoTable.FSI_ALLENROLLNUMBER);
				if(fsi_allenrollnumber == -999)
					fsi_allenrollnumber = null;
				Integer fsi_allcurrentstudentnumber = resultSet.getInt(ForeignStudentInfoTable.FSI_ALLCURRENTSTUDENTNUMBER);
				if(fsi_allcurrentstudentnumber == -999)
					fsi_allcurrentstudentnumber = null;
				Integer fsi_foreigngraduatenumber = resultSet.getInt(ForeignStudentInfoTable.FSI_FOREIGNGRADUATENUMBER);
				if(fsi_foreigngraduatenumber == -999)
					fsi_foreigngraduatenumber = null;
				Integer fsi_foreigndegreenumber = resultSet.getInt(ForeignStudentInfoTable.FSI_FOREIGNDEGREENUMBER);
				if(fsi_foreigndegreenumber == -999)
					fsi_foreigndegreenumber = null;
				Integer fsi_foreignenrollnumber = resultSet.getInt(ForeignStudentInfoTable.FSI_FOREIGNENROLLNUMBER);
				if(fsi_foreignenrollnumber == -999)
					fsi_foreignenrollnumber = null;
				Integer fsi_foreigncurrentstudentnumber = resultSet.getInt(ForeignStudentInfoTable.FSI_FOREIGNCURRENTSTUDENTNUMBER);
				if(fsi_foreigncurrentstudentnumber == -999)
					fsi_foreigncurrentstudentnumber = null;
				Integer fsi_hkgraduatenumber = resultSet.getInt(ForeignStudentInfoTable.FSI_HKGRADUATENUMBER);
				if(fsi_hkgraduatenumber == -999)
					fsi_hkgraduatenumber = null;
				Integer fsi_hkdegreenumber = resultSet.getInt(ForeignStudentInfoTable.FSI_HKDEGREENUMBER);
				if(fsi_hkdegreenumber == -999)
					fsi_hkdegreenumber = null;
				Integer fsi_hkenrollnumber = resultSet.getInt(ForeignStudentInfoTable.FSI_HKENROLLNUMBER);
				if(fsi_hkenrollnumber == -999)
					fsi_hkenrollnumber = null;
				Integer fsi_hkcurrentstudentnumber = resultSet.getInt(ForeignStudentInfoTable.FSI_HKCURRENTSTUDENTNUMBER);
				if(fsi_hkcurrentstudentnumber == -999)
					fsi_hkcurrentstudentnumber = null;
				Integer fsi_macgraduatenumber = resultSet.getInt(ForeignStudentInfoTable.FSI_MACGRADUATENUMBER);
				if(fsi_macgraduatenumber == -999)
					fsi_macgraduatenumber = null;
				Integer fsi_macdegreenumber = resultSet.getInt(ForeignStudentInfoTable.FSI_MACDEGREENUMBER);
				if(fsi_macdegreenumber == -999)
					fsi_macdegreenumber = null;
				Integer fsi_macenrollnumber = resultSet.getInt(ForeignStudentInfoTable.FSI_MACENROLLNUMBER);
				if(fsi_macenrollnumber == -999)
					fsi_macenrollnumber = null;
				Integer fsi_maccurrentstudentnumber = resultSet.getInt(ForeignStudentInfoTable.FSI_MACCURRENTSTUDENTNUMBER);
				if(fsi_maccurrentstudentnumber == -999)
					fsi_maccurrentstudentnumber = null;
				Integer fsi_twngraduatenumber = resultSet.getInt(ForeignStudentInfoTable.FSI_TWNGRADUATENUMBER);
				if(fsi_twngraduatenumber == -999)
					fsi_twngraduatenumber = null;
				Integer fsi_twndegreenumber = resultSet.getInt(ForeignStudentInfoTable.FSI_TWNDEGREENUMBER);
				if(fsi_twndegreenumber == -999)
					fsi_twndegreenumber = null;
				Integer fsi_twnenrollnumber = resultSet.getInt(ForeignStudentInfoTable.FSI_TWNENROLLNUMBER);
				if(fsi_twnenrollnumber == -999)
					fsi_twnenrollnumber = null;
				Integer fsi_twncurrentstudentnumber = resultSet.getInt(ForeignStudentInfoTable.FSI_TWNCURRENTSTUDENTNUMBER);
				if(fsi_twncurrentstudentnumber == -999)
					fsi_twncurrentstudentnumber = null;
				
				int fsi_serialnumber = resultSet.getInt(ForeignStudentInfoTable.FSI_SERIALNUMBER);
				String fsi_college = resultSet.getString(ForeignStudentInfoTable.FSI_COLLEGE);//填报学院
			    Date fsi_deadline = resultSet.getDate(ForeignStudentInfoTable.FSI_DEADLINE);//截止时间    
				String fsi_comments = resultSet.getString(ForeignStudentInfoTable.FSI_COMMENTS);
				int isnull = resultSet.getInt(ForeignStudentInfoTable.ISNULL);
				
				ForeignStudentInfo fsi = new ForeignStudentInfo(fsi_id, fsi_allgraduatenumber, fsi_alldegreenumber, fsi_allenrollnumber, 
						fsi_allcurrentstudentnumber, fsi_foreigngraduatenumber, fsi_foreigndegreenumber, fsi_foreignenrollnumber, 
						fsi_foreigncurrentstudentnumber, fsi_hkgraduatenumber, fsi_hkdegreenumber, fsi_hkenrollnumber, 
						fsi_hkcurrentstudentnumber, fsi_macgraduatenumber, fsi_macdegreenumber, fsi_macenrollnumber, 
						fsi_maccurrentstudentnumber, fsi_twngraduatenumber, fsi_twndegreenumber, fsi_twnenrollnumber, 
						fsi_twncurrentstudentnumber, fsi_serialnumber, fsi_deadline, fsi_college, fsi_comments,isnull);
				fsis.add(fsi);
			}
			return fsis;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
	}




}
