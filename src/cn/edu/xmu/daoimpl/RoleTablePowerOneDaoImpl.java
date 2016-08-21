package cn.edu.xmu.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.xmu.dao.RoleTablePowerOneDao;
import cn.edu.xmu.entity.RoleTablePowerOne;
import cn.edu.xmu.util.JdbcUtils_DBCP;

public class RoleTablePowerOneDaoImpl extends BaseDaoImpl<RoleTablePowerOne> implements
		RoleTablePowerOneDao {

	@Override
	public List<RoleTablePowerOne> getRoleFills() {
		List<RoleTablePowerOne> roleFills = new ArrayList<RoleTablePowerOne>();
		String sql = "select r_role.r_roleid,r_role.r_rolename,rp_roletablepower.rp_id,t_tableList.t_tableid,t_tableList.t_tablename,rp_roletablepower.rp_powerid"
				+ " from r_role,rp_roletablepower,t_tableList "
				+ "WHERE r_role.r_roleid = rp_roletablepower.rp_roleid "
				+ "and r_role.r_islive=1 "
				+ "and rp_roletablepower.rp_tableid=t_tableList.t_tableid order by r_role.r_roleid desc";
		System.out.println(sql);
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("rp_id");
				int roleid = resultSet.getInt("r_roleid");
				String rolename = resultSet.getString("r_rolename");
				int tableid = resultSet.getInt("t_tableid");
				String tablename = resultSet.getString("t_tablename");
				int powerid = resultSet.getInt("rp_powerid");
				RoleTablePowerOne roleFill = new RoleTablePowerOne(id, roleid, rolename, tableid, tablename, powerid);
				roleFills.add(roleFill);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, preparedStatement, resultSet);
		}
		return roleFills;
	}

}
