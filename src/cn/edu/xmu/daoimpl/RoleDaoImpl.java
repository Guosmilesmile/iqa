package cn.edu.xmu.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.xmu.dao.RoleDao;
import cn.edu.xmu.entity.Role;
import cn.edu.xmu.table.RoleTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;

public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao {
	@Override
	public int AddRole(String rolename) {
		String sql = "select " + RoleTable.R_ROLEID + " from "
				+ RoleTable.TABLE_NAME + " where " + RoleTable.R_ROLENAME + "="
				+ "'" + rolename + "'";
		System.out.println(sql);
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String name = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				name = resultSet.getString(1);
			}

			if (name == null) {

				sql = "insert into " + RoleTable.TABLE_NAME + " ("
						+ RoleTable.R_ROLENAME + ")" + " values(" + "'"
						+ rolename + "'" + ")";
				System.out.println(sql);
				preparedStatement = connection.prepareStatement(sql);
				boolean flag = preparedStatement.execute();
				return 1;
			} else {
				return 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, preparedStatement, resultSet);
		}
		return 0;

	}

	@Override
	public List<Role> getallrole() {
		List<Role> listRoles = new ArrayList<Role>();
		String sql = "select * from r_role";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int roleid = resultSet.getInt("r_roleid");
				System.out.println(roleid);
				String rolename = resultSet.getString("r_rolename");
				int islive = resultSet.getInt("r_islive");
				Role role = new Role(roleid, rolename, islive);
				listRoles.add(role);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils_DBCP.release(connection, preparedStatement, resultSet);
			
		}

		return listRoles;
	}

	@Override
	public int deleterole(String roleid) {
		String sql = "delete from r_role where r_roleid =" + roleid;
		System.out.println(sql);
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		boolean flag = true;
		try {
			connection = JdbcUtils_DBCP.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			int i = preparedStatement.executeUpdate();
			if(i==0)
				flag=false;
			if (flag)
				return 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils_DBCP.release(connection, preparedStatement, null);
		}
		return 0;
	}

	@Override
	public String GetRoleNameByRoleId(int roleid) {
		String sql = "select r_rolename from r_role where r_roleid=" + roleid;
		System.out.println(sql);
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet=null;
		String rolename="";
		try {
			connection = JdbcUtils_DBCP.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				rolename =  resultSet.getString("r_rolename");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils_DBCP.release(connection, preparedStatement, resultSet);
		}
		return rolename;
	}

	

}
