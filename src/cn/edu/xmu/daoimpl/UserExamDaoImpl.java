package cn.edu.xmu.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.edu.xmu.dao.UserExamDao;
import cn.edu.xmu.entity.UserExam;
import cn.edu.xmu.table.ReviewTableTable;
import cn.edu.xmu.util.FastJsonTool;
import cn.edu.xmu.util.JdbcUtils_DBCP;

public class UserExamDaoImpl extends BaseDaoImpl<UserExam> implements
		UserExamDao {

	@Override
	public List<UserExam> getUserExams(String userid) {
		List<UserExam> userExams = new ArrayList<UserExam>();
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "select rt_reviewtable.rt_id,t_tableList.t_tableid,t_tableList.t_tablename,uf_userfilltable.uf_fillid,uf_userfilltable.uf_time,rt_reviewtable.rt_situation "
				+ "from rt_reviewtable,t_tableList,uf_userfilltable where "
				+ "rt_reviewtable.rt_ufid = uf_userfilltable.uf_id "
				+ "and uf_userfilltable.uf_tableid = t_tableList.t_tableid "
				+ "  and t_tableid in "
				+ "(select rp_roletablepower.rp_tableid from "
				+ "ru_userrole,rp_roletablepower,r_role "
				+ " WHERE r_roleid=ru_roleid and r_islive=1 and "
				+ " ru_userrole.ru_roleid = rp_roletablepower.rp_roleid and "
				+ "rp_roletablepower.rp_powerid=3 and "
				+ "ru_userrole.ru_userid = ?) order by rt_reviewtable.rt_id desc";
		System.out.println("exam==========="+sql);
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userid);
			//preparedStatement.setString(2, userid);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("rt_id");
				String tablename = resultSet.getString("t_tablename");
				String filluserid = resultSet.getString("uf_fillid");
				Date filldate = resultSet.getDate("uf_time");
				int situation = resultSet.getInt("rt_situation");
				int tableid = resultSet.getInt("t_tableid");
				UserExam userExam = new UserExam(id, tablename, filluserid,
						filldate, situation,tableid);
				System.out.println(FastJsonTool.createJsonString(userExam));
				userExams.add(userExam);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils_DBCP.release(connection, preparedStatement, resultSet);
		}
		return userExams;
	}

	@Override
	public boolean ChangeExamSituation(int id, int flag,String userid) {
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		PreparedStatement preparedStatement = null;
		String sql ="update "+ReviewTableTable.TABLE_NAME+" set "+ReviewTableTable.RT_SITUATION+"=?,"+ReviewTableTable.RT_REVIEWID+"=? where rt_id=?";
		System.out.println("updateexam-----"+sql);
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, flag);
			preparedStatement.setString(2, userid);
			preparedStatement.setInt(3, id);
			boolean b = preparedStatement.execute();
			return b;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils_DBCP.release(connection, preparedStatement, null);
		}
		return false;
	}
}
