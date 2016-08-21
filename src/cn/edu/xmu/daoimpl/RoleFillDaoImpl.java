package cn.edu.xmu.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.xmu.dao.BaseDao;
import cn.edu.xmu.dao.RoleFillDao;
import cn.edu.xmu.entity.RoleFill;
import cn.edu.xmu.util.JdbcUtils_DBCP;

public class RoleFillDaoImpl extends BaseDaoImpl<RoleFill> implements RoleFillDao{

	@Override
	public List<RoleFill> getRoleFills() {
		List<RoleFill> roleFills = new ArrayList<RoleFill>();
		String sql="select rr_rolefill.rr_id,r_role.r_roleid,r_role.r_rolename,rr_rolefill.rr_tableid,t_tableList.t_tablename from r_role,rr_rolefill,t_tableList WHERE t_tableList.t_tableid=rr_rolefill.rr_tableid and rr_roleid=r_roleid";
		Connection connection = null;
		PreparedStatement preparedStatement =null;
		ResultSet resultSet = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			resultSet =preparedStatement.executeQuery();
			while(resultSet.next()){
				int id = resultSet.getInt("rr_id");
				int roleid = resultSet.getInt("r_roleid");
				String rolename =resultSet.getString("r_rolename");
				int tableid = resultSet.getInt("rr_tableid");
				String tablename  = resultSet.getString("t_tablename");
				RoleFill roleFill = new RoleFill(id, roleid, rolename, tableid,tablename);
				roleFills.add(roleFill);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils_DBCP.release(connection, preparedStatement, resultSet);
		}
		return roleFills;
	}

}
