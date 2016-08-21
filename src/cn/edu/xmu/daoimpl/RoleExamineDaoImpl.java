package cn.edu.xmu.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.xmu.dao.RoleExamineDao;
import cn.edu.xmu.entity.RoleExamine;
import cn.edu.xmu.util.JdbcUtils_DBCP;

public class RoleExamineDaoImpl extends BaseDaoImpl<RoleExamine> implements RoleExamineDao{

	@Override
	public List<RoleExamine> getRoleExamines() {
		List<RoleExamine> roleExamines = new ArrayList<RoleExamine>();
		String sql="select re_id,r_role.r_roleid,r_role.r_rolename,re_roleexamine.re_tableid,t_tablename from r_role,re_roleexamine,t_tableList WHERE re_roleid=r_roleid and t_tableid=re_tableid";
		Connection connection = null;
		PreparedStatement preparedStatement =null;
		ResultSet resultSet = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			resultSet =preparedStatement.executeQuery();
			while(resultSet.next()){
				int id = resultSet.getInt("re_id");
				int roleid = resultSet.getInt("r_roleid");
				String rolename =resultSet.getString("r_rolename");
				int tableid = resultSet.getInt("re_tableid");
				String tablename = resultSet.getString("t_tablename");
				RoleExamine roleExamine = new RoleExamine(id, roleid, rolename, tableid,tablename);
				roleExamines.add(roleExamine);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils_DBCP.release(connection, preparedStatement, resultSet);
		}
		return roleExamines;
	}

}
