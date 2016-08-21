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

import cn.edu.xmu.dao.RoomAreaofTeachingAdministrationDao;
import cn.edu.xmu.entity.RoomAreaofTeachingAdministration;
import cn.edu.xmu.table.RoomAreaofTeachingAdministrationTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;


/**
 * @author zhantu
 * 各单位教学行政用房面积  实体类功能 ——接口实现
 * date 2015-07-07
 */

public class RoomAreaofTeachingAdministrationDaoImpl extends BaseDaoImpl<RoomAreaofTeachingAdministration>implements RoomAreaofTeachingAdministrationDao{

	@Override
	public int addRecord(RoomAreaofTeachingAdministration rata) {
		
		int result = 0;

		String t_sql = "update " + RoomAreaofTeachingAdministrationTable.TABLE_NAME + " set "
				+ RoomAreaofTeachingAdministrationTable.RATA_SERIALNUMBER + " = "
				+ RoomAreaofTeachingAdministrationTable.RATA_SERIALNUMBER + " +1 where "
				+ RoomAreaofTeachingAdministrationTable.RATA_SERIALNUMBER + ">=?";

		
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
			t_pstmt.setInt(1, rata.getRata_serialnumber());
			
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
		String sql = "insert into " + RoomAreaofTeachingAdministrationTable.TABLE_NAME + "("
				+ RoomAreaofTeachingAdministrationTable.RATA_COLLEGENAME + ","
				+ RoomAreaofTeachingAdministrationTable.RATA_SUM + ","
				+ RoomAreaofTeachingAdministrationTable.RATA_TEACHRESEARCHAUXILIARY + ","
				+ RoomAreaofTeachingAdministrationTable.RATA_CLASSROOM + ","
				+ RoomAreaofTeachingAdministrationTable.RATA_LIBRARY + ","
				+ RoomAreaofTeachingAdministrationTable.RATA_LAB + ","
				
				+ RoomAreaofTeachingAdministrationTable.RATA_PRIVATESCIENRESEARCH + ","
				+ RoomAreaofTeachingAdministrationTable.RATA_GYM + ","
				+ RoomAreaofTeachingAdministrationTable.RATA_HALL + ","
				+ RoomAreaofTeachingAdministrationTable.RATA_ADMINISTRATIONOFFICE + ","
				
				+ RoomAreaofTeachingAdministrationTable.RATA_SERIALNUMBER + ","
				+ RoomAreaofTeachingAdministrationTable.RATA_COLLEGE + ","
				+ RoomAreaofTeachingAdministrationTable.RATA_COMMENTS + ","
				+ RoomAreaofTeachingAdministrationTable.ISNULL
				+ ")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, rata.getRata_collegename());
			pstmt.setFloat(2, rata.getRata_sum());
			pstmt.setFloat(3, rata.getRata_teachresearchauxiliary());
			pstmt.setFloat(4, rata.getRata_classroom());
			pstmt.setFloat(5, rata.getRata_library());
			pstmt.setFloat(6, rata.getRata_lab());
			pstmt.setFloat(7, rata.getRata_privatescienresearch());
			pstmt.setFloat(8, rata.getRata_gym());
			pstmt.setFloat(9, rata.getRata_hall());
			pstmt.setFloat(10, rata.getRata_administrationoffice());
			pstmt.setInt(11, rata.getRata_serialnumber());
			pstmt.setString(12, rata.getRata_college());
			pstmt.setString(13, rata.getRata_comments());
			pstmt.setInt(14, rata.getIsnull());
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
	public int getRoomAreaofTeachingAdministrationCount(Map queryParams) {
		
		int count = 0;
		//获取条件的语句
		String sql = "select count(*) from " + RoomAreaofTeachingAdministrationTable.TABLE_NAME
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
		sql += String.format(" or %s ='%s'", RoomAreaofTeachingAdministrationTable.RATA_COLLEGE, "");
		
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
	public List<RoomAreaofTeachingAdministration> getAllRoomAreaofTeachingAdministration(int start, int end,
			String sortStr, String orderStr, Map queryParams) {
		
		String sql = " select tmp.* from ( " + " select * from "
				+ RoomAreaofTeachingAdministrationTable.TABLE_NAME + " where 1=1 ";
		
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
		
		List<RoomAreaofTeachingAdministration> ratas = new ArrayList<RoomAreaofTeachingAdministration>();
		try {
			
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int rata_id = resultSet.getInt(RoomAreaofTeachingAdministrationTable.RATA_ID);
				String rata_collegename = resultSet.getString(RoomAreaofTeachingAdministrationTable.RATA_COLLEGENAME);
				Float rata_sum = resultSet.getFloat(RoomAreaofTeachingAdministrationTable.RATA_SUM);
				if(rata_sum==-999)
					rata_sum = null;
				Float rata_teachresearchauxiliary = resultSet.getFloat(RoomAreaofTeachingAdministrationTable.RATA_TEACHRESEARCHAUXILIARY);
				if(rata_teachresearchauxiliary==-999)
					rata_teachresearchauxiliary = null;
				Float rata_classroom = resultSet.getFloat(RoomAreaofTeachingAdministrationTable.RATA_CLASSROOM);
				if(rata_classroom==-999)
					rata_classroom = null;
				Float rata_library = resultSet.getFloat(RoomAreaofTeachingAdministrationTable.RATA_LIBRARY);
				if(rata_library==-999)
					rata_library = null;
				Float rata_lab = resultSet.getFloat(RoomAreaofTeachingAdministrationTable.RATA_LAB);
				if(rata_lab==-999)
					rata_lab = null;
				
				Float rata_privatescienresearch = resultSet.getFloat(RoomAreaofTeachingAdministrationTable.RATA_PRIVATESCIENRESEARCH);
				if(rata_privatescienresearch==-999)
					rata_privatescienresearch = null;
				Float rata_gym = resultSet.getFloat(RoomAreaofTeachingAdministrationTable.RATA_GYM);
				if(rata_gym==-999)
					rata_gym = null;
				Float rata_hall = resultSet.getFloat(RoomAreaofTeachingAdministrationTable.RATA_HALL);
				if(rata_hall==-999)
					rata_hall = null;
				Float rata_administrationoffice = resultSet.getFloat(RoomAreaofTeachingAdministrationTable.RATA_ADMINISTRATIONOFFICE);
				if(rata_administrationoffice==-999)
					rata_administrationoffice = null;
				
				int rata_serialnumber = resultSet.getInt(RoomAreaofTeachingAdministrationTable.RATA_SERIALNUMBER);
				Date rata_deadline = resultSet.getDate(RoomAreaofTeachingAdministrationTable.RATA_DEADLINE);
				String rata_comments = resultSet.getString(RoomAreaofTeachingAdministrationTable.RATA_COMMENTS);
				String rata_college = resultSet.getString(RoomAreaofTeachingAdministrationTable.RATA_COLLEGE);
				int isnull = resultSet.getInt(RoomAreaofTeachingAdministrationTable.ISNULL);
				if(rata_comments == null)
					rata_comments = "";
				RoomAreaofTeachingAdministration rata = new RoomAreaofTeachingAdministration(rata_id, rata_collegename, rata_sum,
						rata_teachresearchauxiliary, rata_classroom,
						rata_library, rata_lab,
						rata_privatescienresearch, rata_gym, rata_hall,
						rata_administrationoffice, rata_serialnumber,
						rata_deadline, rata_college, rata_comments, isnull);
				
				ratas.add(rata);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return ratas;
	}

	@Override
	public int alterRoomAreaofTeachingAdministration(Map<String, String> valueMap, String id) {
		
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(RoomAreaofTeachingAdministrationTable.RATA_ID, id);
		int result = updateRecord(RoomAreaofTeachingAdministrationTable.TABLE_NAME, valueMap,
				condition);
		return result;
	}

	@Override
	public boolean batchDelete(String[] rataids) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();

		for (String rataid : rataids) {
			String sql = "delete from " + RoomAreaofTeachingAdministrationTable.TABLE_NAME
					+ " where " + RoomAreaofTeachingAdministrationTable.RATA_ID + " = '" + rataid + "'";
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
	public List<RoomAreaofTeachingAdministration> getRoomAreaofTeachingAdministration(int start, int end,
			String sortStr, String orderStr, Map<String, String> params,
			Date deadline) {
		String sql = " select tmp.* from ( " + " select * from "
				+ RoomAreaofTeachingAdministrationTable.TABLE_NAME + " where 1=1 ";
		if (deadline != null) {

			sql += String.format("and "+RoomAreaofTeachingAdministrationTable.RATA_DEADLINE+" like  '%s%%' ", deadline);
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

		List<RoomAreaofTeachingAdministration> roomAreaofTeachingAdministrations = new ArrayList<RoomAreaofTeachingAdministration>();
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
				int rata_id = resultSet.getInt(RoomAreaofTeachingAdministrationTable.RATA_ID);
				String rata_collegename = resultSet.getString(RoomAreaofTeachingAdministrationTable.RATA_COLLEGENAME);
				Float rata_sum = resultSet.getFloat(RoomAreaofTeachingAdministrationTable.RATA_SUM);
				if(rata_sum==-999)
					rata_sum = null;
				Float rata_teachresearchauxiliary = resultSet.getFloat(RoomAreaofTeachingAdministrationTable.RATA_TEACHRESEARCHAUXILIARY);
				if(rata_teachresearchauxiliary==-999)
					rata_teachresearchauxiliary = null;
				Float rata_classroom = resultSet.getFloat(RoomAreaofTeachingAdministrationTable.RATA_CLASSROOM);
				if(rata_classroom==-999)
					rata_classroom = null;
				Float rata_library = resultSet.getFloat(RoomAreaofTeachingAdministrationTable.RATA_LIBRARY);
				if(rata_library==-999)
					rata_library = null;
				Float rata_lab = resultSet.getFloat(RoomAreaofTeachingAdministrationTable.RATA_LAB);
				if(rata_lab==-999)
					rata_lab = null;
				
				Float rata_privatescienresearch = resultSet.getFloat(RoomAreaofTeachingAdministrationTable.RATA_PRIVATESCIENRESEARCH);
				if(rata_privatescienresearch==-999)
					rata_privatescienresearch = null;
				Float rata_gym = resultSet.getFloat(RoomAreaofTeachingAdministrationTable.RATA_GYM);
				if(rata_gym==-999)
					rata_gym = null;
				Float rata_hall = resultSet.getFloat(RoomAreaofTeachingAdministrationTable.RATA_HALL);
				if(rata_hall==-999)
					rata_hall = null;
				Float rata_administrationoffice = resultSet.getFloat(RoomAreaofTeachingAdministrationTable.RATA_ADMINISTRATIONOFFICE);
				if(rata_administrationoffice==-999)
					rata_administrationoffice = null;
				
				int rata_serialnumber = resultSet.getInt(RoomAreaofTeachingAdministrationTable.RATA_SERIALNUMBER);
				Date rata_deadline = resultSet.getDate(RoomAreaofTeachingAdministrationTable.RATA_DEADLINE);
				String rata_comments = resultSet.getString(RoomAreaofTeachingAdministrationTable.RATA_COMMENTS);
				String rata_college = resultSet.getString(RoomAreaofTeachingAdministrationTable.RATA_COLLEGE);
				int isnull = resultSet.getInt(RoomAreaofTeachingAdministrationTable.ISNULL);
				
				RoomAreaofTeachingAdministration rata = new RoomAreaofTeachingAdministration(rata_id,rata_collegename, rata_sum,
						rata_teachresearchauxiliary, rata_classroom,
						rata_library, rata_lab,
						rata_privatescienresearch, rata_gym, rata_hall,
						rata_administrationoffice, rata_serialnumber,
						rata_deadline, rata_college, rata_comments, isnull);

				roomAreaofTeachingAdministrations.add(rata);
			}
			return roomAreaofTeachingAdministrations;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
	}
	
	//flag = true取总计 否则 取不是总计的
	public List<RoomAreaofTeachingAdministration> getRATASumorNoSum(String college,boolean flag)
	{
		String sqlString;
		if(flag)
		{
			sqlString = "select * from "+RoomAreaofTeachingAdministrationTable.TABLE_NAME+" where "+
					RoomAreaofTeachingAdministrationTable.RATA_COLLEGENAME+" = \'总计\' and "+
					RoomAreaofTeachingAdministrationTable.RATA_COLLEGE+" = \'"+college+"\' and "+RoomAreaofTeachingAdministrationTable.RATA_DEADLINE+
					" is null";
		}
		else {
			sqlString = "select * from "+RoomAreaofTeachingAdministrationTable.TABLE_NAME+" where "+
					RoomAreaofTeachingAdministrationTable.RATA_COLLEGENAME+" != \'总计\' and "+
					RoomAreaofTeachingAdministrationTable.RATA_COLLEGE+" = \'"+college+"\' and "+RoomAreaofTeachingAdministrationTable.RATA_DEADLINE+
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
		List<RoomAreaofTeachingAdministration> roomAreaofTeachingAdministrations = new ArrayList<RoomAreaofTeachingAdministration>();
		try {
			System.out.println(sqlString);
			pstmt = connection.prepareStatement(sqlString);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				int rata_id = resultSet.getInt(RoomAreaofTeachingAdministrationTable.RATA_ID);
				String rata_collegename = resultSet.getString(RoomAreaofTeachingAdministrationTable.RATA_COLLEGENAME);
				Float rata_sum = resultSet.getFloat(RoomAreaofTeachingAdministrationTable.RATA_SUM);
				if(rata_sum==-999)
					rata_sum = null;
				Float rata_teachresearchauxiliary = resultSet.getFloat(RoomAreaofTeachingAdministrationTable.RATA_TEACHRESEARCHAUXILIARY);
				if(rata_teachresearchauxiliary==-999)
					rata_teachresearchauxiliary = null;
				Float rata_classroom = resultSet.getFloat(RoomAreaofTeachingAdministrationTable.RATA_CLASSROOM);
				if(rata_classroom==-999)
					rata_classroom = null;
				Float rata_library = resultSet.getFloat(RoomAreaofTeachingAdministrationTable.RATA_LIBRARY);
				if(rata_library==-999)
					rata_library = null;
				Float rata_lab = resultSet.getFloat(RoomAreaofTeachingAdministrationTable.RATA_LAB);
				if(rata_lab==-999)
					rata_lab = null;
				
				Float rata_privatescienresearch = resultSet.getFloat(RoomAreaofTeachingAdministrationTable.RATA_PRIVATESCIENRESEARCH);
				if(rata_privatescienresearch==-999)
					rata_privatescienresearch = null;
				Float rata_gym = resultSet.getFloat(RoomAreaofTeachingAdministrationTable.RATA_GYM);
				if(rata_gym==-999)
					rata_gym = null;
				Float rata_hall = resultSet.getFloat(RoomAreaofTeachingAdministrationTable.RATA_HALL);
				if(rata_hall==-999)
					rata_hall = null;
				Float rata_administrationoffice = resultSet.getFloat(RoomAreaofTeachingAdministrationTable.RATA_ADMINISTRATIONOFFICE);
				if(rata_administrationoffice==-999)
					rata_administrationoffice = null;
				
				int rata_serialnumber = resultSet.getInt(RoomAreaofTeachingAdministrationTable.RATA_SERIALNUMBER);
				Date rata_deadline = resultSet.getDate(RoomAreaofTeachingAdministrationTable.RATA_DEADLINE);
				String rata_comments = resultSet.getString(RoomAreaofTeachingAdministrationTable.RATA_COMMENTS);
				String rata_college = resultSet.getString(RoomAreaofTeachingAdministrationTable.RATA_COLLEGE);
				int isnull = resultSet.getInt(RoomAreaofTeachingAdministrationTable.ISNULL);
				
				RoomAreaofTeachingAdministration rata = new RoomAreaofTeachingAdministration(rata_id,rata_collegename, rata_sum,
						rata_teachresearchauxiliary, rata_classroom,
						rata_library, rata_lab,
						rata_privatescienresearch, rata_gym, rata_hall,
						rata_administrationoffice, rata_serialnumber,
						rata_deadline, rata_college, rata_comments, isnull);

				roomAreaofTeachingAdministrations.add(rata);
			}
			return roomAreaofTeachingAdministrations;
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
		String sql = "delete from " + RoomAreaofTeachingAdministrationTable.TABLE_NAME
				+ " where " + RoomAreaofTeachingAdministrationTable.RATA_COLLEGE + " = '" + college + "'" +" and rata_deadline is null ";
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
	public List<RoomAreaofTeachingAdministration> getAllRoomAreaofTeachingAdministration() {
		String sql = " select * from " + RoomAreaofTeachingAdministrationTable.TABLE_NAME
				+ " where 1=1 " + " order by " + RoomAreaofTeachingAdministrationTable.RATA_SERIALNUMBER+", "+RoomAreaofTeachingAdministrationTable.RATA_ID;
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
			List<RoomAreaofTeachingAdministration> roomAreaofTeachingAdministrationList = new ArrayList<RoomAreaofTeachingAdministration>();
			while (resultSet.next()) {
				int rata_id = resultSet.getInt(RoomAreaofTeachingAdministrationTable.RATA_ID);
				String rata_collegename = resultSet.getString(RoomAreaofTeachingAdministrationTable.RATA_COLLEGENAME);
				Float rata_sum = resultSet.getFloat(RoomAreaofTeachingAdministrationTable.RATA_SUM);
				if(rata_sum==-999)
					rata_sum = null;
				Float rata_teachresearchauxiliary = resultSet.getFloat(RoomAreaofTeachingAdministrationTable.RATA_TEACHRESEARCHAUXILIARY);
				if(rata_teachresearchauxiliary==-999)
					rata_teachresearchauxiliary = null;
				Float rata_classroom = resultSet.getFloat(RoomAreaofTeachingAdministrationTable.RATA_CLASSROOM);
				if(rata_classroom==-999)
					rata_classroom = null;
				Float rata_library = resultSet.getFloat(RoomAreaofTeachingAdministrationTable.RATA_LIBRARY);
				if(rata_library==-999)
					rata_library = null;
				Float rata_lab = resultSet.getFloat(RoomAreaofTeachingAdministrationTable.RATA_LAB);
				if(rata_lab==-999)
					rata_lab = null;
				
				Float rata_privatescienresearch = resultSet.getFloat(RoomAreaofTeachingAdministrationTable.RATA_PRIVATESCIENRESEARCH);
				if(rata_privatescienresearch==-999)
					rata_privatescienresearch = null;
				Float rata_gym = resultSet.getFloat(RoomAreaofTeachingAdministrationTable.RATA_GYM);
				if(rata_gym==-999)
					rata_gym = null;
				Float rata_hall = resultSet.getFloat(RoomAreaofTeachingAdministrationTable.RATA_HALL);
				if(rata_hall==-999)
					rata_hall = null;
				Float rata_administrationoffice = resultSet.getFloat(RoomAreaofTeachingAdministrationTable.RATA_ADMINISTRATIONOFFICE);
				if(rata_administrationoffice==-999)
					rata_administrationoffice = null;
				
				int rata_serialnumber = resultSet.getInt(RoomAreaofTeachingAdministrationTable.RATA_SERIALNUMBER);
				Date rata_deadline = resultSet.getDate(RoomAreaofTeachingAdministrationTable.RATA_DEADLINE);
				String rata_comments = resultSet.getString(RoomAreaofTeachingAdministrationTable.RATA_COMMENTS);
				String rata_college = resultSet.getString(RoomAreaofTeachingAdministrationTable.RATA_COLLEGE);
				int isnull = resultSet.getInt(RoomAreaofTeachingAdministrationTable.ISNULL);
				
				RoomAreaofTeachingAdministration rata = new RoomAreaofTeachingAdministration(rata_id,rata_collegename, rata_sum,
						rata_teachresearchauxiliary, rata_classroom,
						rata_library, rata_lab,
						rata_privatescienresearch, rata_gym, rata_hall,
						rata_administrationoffice, rata_serialnumber,
						rata_deadline, rata_college, rata_comments, isnull);
				
				roomAreaofTeachingAdministrationList.add(rata);
			}
			return roomAreaofTeachingAdministrationList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
	}
}
