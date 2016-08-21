package cn.edu.xmu.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.edu.xmu.dao.GetValueUtilDao;
import cn.edu.xmu.util.JdbcUtils_DBCP;

public class GetValueUtilDaoImpl implements GetValueUtilDao{

	@Override
	public String getDeptxiByRoleId(String roleId) {
		String sql = "select u_deptxi from u_user, ru_userrole where ru_userid = u_userid and ru_roleid = ?";
		
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		String result="";
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, roleId);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				result = resultSet.getString("u_deptxi");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			result = null;
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return result;
	}

}
