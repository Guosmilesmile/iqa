package cn.edu.xmu.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.xmu.dao.RolePowerDao;
import cn.edu.xmu.entity.RolePower;
import cn.edu.xmu.util.JdbcUtils_DBCP;

public class RolePowerDapImpl extends BaseDaoImpl<RolePower> implements
		RolePowerDao {

	@Override
	public List<RolePower> getRolePowers() {
		List<RolePower> list = new ArrayList<RolePower>();
		String sql = "SELECT r_roleid,r_rolename,r_rolepowerid,r_rolepower from r_role,rr_managerolepower,r_rolepower WHERE r_role.r_roleid=rr_managerolepower.rr_roleid and r_rolepower.r_rolepowerid=rr_managerolepower.rr_rolepowerid";
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int roleid = resultSet.getInt("r_roleid");
				System.out.println(roleid);
				String rolename = resultSet.getString("r_rolename");
				int rolepowerid = resultSet.getInt("r_rolepowerid");
				String rolepower = resultSet.getString("r_rolepower");
				RolePower rePower = new RolePower(roleid, rolename,
						rolepowerid, rolepower);
				list.add(rePower);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils_DBCP.release(connection, preparedStatement, resultSet);
			
		}

		return list;
	}

}
