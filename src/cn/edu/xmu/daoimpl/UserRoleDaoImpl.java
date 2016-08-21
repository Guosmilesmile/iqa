package cn.edu.xmu.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.xmu.dao.UserRoleDao;
import cn.edu.xmu.entity.RolePower;
import cn.edu.xmu.entity.UserRole;
import cn.edu.xmu.table.UserRoleTable;
import cn.edu.xmu.util.FastJsonTool;
import cn.edu.xmu.util.JdbcUtils_DBCP;

public class UserRoleDaoImpl extends BaseDaoImpl<UserRole> implements UserRoleDao{

	@Override
	public List<UserRole> getalluserrole() {
		List<UserRole> list = new ArrayList<UserRole>();
		String sql="SELECT * FROM r_role,ru_userrole WHERE  r_role.r_roleid=ru_userrole.ru_roleid";
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		PreparedStatement preparedStatement = null;
		ResultSet resultSet =null;
		try {
			preparedStatement =connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				String id = resultSet.getInt("ru_id")+"";
				String userid = resultSet.getString("ru_userid");
				String rolename = resultSet.getString("r_rolename");
				UserRole userRole = new UserRole(id,userid, rolename);
				list.add(userRole);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils_DBCP.release(connection, preparedStatement, resultSet);
			
		}
		return list;
	}

	@Override
	public int getRoleidByUserid(String userid) {
		int roleid=0;
		String sql="select ru_roleid from ru_userrole where ru_userid ='"+userid+"'";
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		PreparedStatement preparedStatement = null;
		ResultSet resultSet =null;
		try {
			preparedStatement =connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				 roleid = resultSet.getInt("ru_roleid");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils_DBCP.release(connection, preparedStatement, resultSet);
			
		}
		return roleid;
	}

	@Override
	public String getUseridByRoleid(String roleid) {
		String userid="";
		String sql="select ru_userid from ru_userrole where ru_roleid ='"+roleid+"'";
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		PreparedStatement preparedStatement = null;
		ResultSet resultSet =null;
		try {
			preparedStatement =connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				 userid = resultSet.getString(UserRoleTable.RU_USERID);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils_DBCP.release(connection, preparedStatement, resultSet);
			
		}
		return userid;
	}

	@Override
	public List<Integer> getUseridsByRoleid(String roleid) {
		// TODO Auto-generated method stub
		return null;
	}

}
