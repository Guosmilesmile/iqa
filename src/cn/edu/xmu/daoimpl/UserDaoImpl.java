package cn.edu.xmu.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.xmu.dao.UserDao;
import cn.edu.xmu.entity.User;
import cn.edu.xmu.table.UserTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao{

	@Override
	public int getUser() {
		int count = 0;
		String sql = "select count(*) from u_user where 1=1";
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils_DBCP.release(connection, pstmt, rs);
		}
		System.out.println(count);
		return count;
	}


	@Override
	public User getAllInfo(String userid) {
		User user = null ;
		String sql ="select * from "+UserTable.TABLE_NAME + " where "
					+UserTable.U_USERID+"=?";
		System.out.println(sql);
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			System.out.println(sql);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userid);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				String u_userid = resultSet.getString(UserTable.U_USERID);
				String u_username = resultSet.getString(UserTable.U_USERNAME);
				String u_password = resultSet.getString(UserTable.U_PASSWORD);
				int u_islive = resultSet.getInt(UserTable.U_ISLIVE);
				String u_deptxi = resultSet.getString(UserTable.U_DEPTX);
				user = new User(u_userid, u_username, u_password, u_islive,u_deptxi);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils_DBCP.release(connection, preparedStatement, resultSet);
			
		}
		
		return user;
	}

	@Override
	public int getHighestPower(String userid) {
		String sql ="SELECT MAX(rp_roletablepower.rp_powerid) FROM ru_userrole, rp_roletablepower WHERE ru_roleid = rp_roleid AND ru_userid = ?";
		System.out.println(sql);
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int highestPower = 0;
		try {
			System.out.println(sql);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userid);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				highestPower = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils_DBCP.release(connection, preparedStatement, resultSet);
			
		}
		
		return highestPower;
	}
	
	@Override
	public int[] getAllPower(String userid) {
		int power[] = {0,0,0,0};
		String  sql="SELECT rr_managerolepower.rr_rolepowerid,ru_userrole.ru_userid FROM rr_managerolepower,ru_userrole WHERE ru_userid =? and ru_userrole.ru_roleid = rr_managerolepower.rr_roleid";
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		PreparedStatement preparedStatement =null;
		ResultSet resultSet =null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userid);
			resultSet = preparedStatement.executeQuery();
			int i=0;
			while(resultSet.next()){
				int number = resultSet.getInt("rr_rolepowerid");
				power[number-1]++;
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils_DBCP.release(connection, preparedStatement, resultSet);
			
		}
		for(int i=0;i<2;i++){
			System.out.println(power[i]);
		}
		return power;
	}


	@Override
	public List<User> getallUsers() {
		List<User> list = new ArrayList<User>();
		
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		PreparedStatement preparedStatement = null;
		String sql="select * from "+UserTable.TABLE_NAME;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				String userid = resultSet.getString(UserTable.U_USERID);
				String username = resultSet.getString(UserTable.U_USERNAME);
				String password = resultSet.getString(UserTable.U_PASSWORD);
				int islive = resultSet.getInt(UserTable.U_ISLIVE);
				User user = new User(userid, username, password, islive);
				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils_DBCP.release(connection, preparedStatement, resultSet);
			
		}
		return list;
	}


	@Override
	public int isLogin(String userid, String password) {
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		PreparedStatement preparedStatement = null;
		String sql="select count(*) from "+UserTable.TABLE_NAME +" WHERE "+UserTable.U_USERID +"= ? and " +UserTable.U_PASSWORD +"=?";
		System.out.println(sql);
		ResultSet resultSet = null;
		int result=0;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userid);
			preparedStatement.setString(2, password);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				result = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils_DBCP.release(connection, preparedStatement, resultSet);
			
		}
		return result;
	}


	@Override
	public String getDepxiByUserid(String userid) {
	String deptxi = "";
		String sql ="select "+UserTable.U_DEPTX+" from "+UserTable.TABLE_NAME + " where "
				+UserTable.U_USERID+"=?";
	System.out.println(sql);
	Connection connection = null;
	try {
		connection = JdbcUtils_DBCP.getConnection();
	} catch (SQLException e1) {
		e1.printStackTrace();
	}
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	try {
		System.out.println(sql);
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, userid);
		resultSet = preparedStatement.executeQuery();
		while(resultSet.next()){
			deptxi = resultSet.getString(UserTable.U_DEPTX);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}finally{
		JdbcUtils_DBCP.release(connection, preparedStatement, resultSet);
		
	}
	return deptxi;
	}
	
}
