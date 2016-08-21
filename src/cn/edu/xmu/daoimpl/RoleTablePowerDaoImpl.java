package cn.edu.xmu.daoimpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.dao.RoleTablePowerDao;
import cn.edu.xmu.entity.RoleTablePower;
import cn.edu.xmu.entity.SuperMajor;
import cn.edu.xmu.table.SuperMajorTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;

public class RoleTablePowerDaoImpl extends BaseDaoImpl<RoleTablePower>
		implements RoleTablePowerDao {

	@Override
	public void AddRoleTablePowers(int roleid, String watchs, String fills,
			String examones, String examtwos) {
		String[] watchlist = watchs.split(",");
		String[] filllist = fills.split(",");
		String[] examonelist = examones.split(",");
		String[] examtwolist = examtwos.split(",");
		boolean b = false;
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		List<String> watchList = new ArrayList<String>();
		for (int i = 0; i < watchlist.length; i++) {
			if (!"".equals(watchlist[i])) {
				watchList.add(watchlist[i]);
			}

		}
		List<String> fillList = new ArrayList<String>();
		for (int i = 0; i < filllist.length; i++) {
			if (!"".equals(filllist[i]))
				fillList.add(filllist[i]);
		}
		List<String> examoneList = new ArrayList<String>();
		for (int i = 0; i < examonelist.length; i++) {
			if (!"".equals(examonelist[i]))
				examoneList.add(examonelist[i]);
		}
		List<String> examtwoList = new ArrayList<String>();
		for (int i = 0; i < examtwolist.length; i++) {
			if (!"".equals(examtwolist[i]))
				examtwoList.add(examtwolist[i]);
		}

		for (int i = 0; i < watchList.size(); i++) {
			String tableid = watchList.get(i);
			String sql = "select count(*) from rp_roletablepower where rp_tableid="
					+ tableid + " and rp_powerid=1 and rp_roleid=" + roleid;
			int count = 0;
			try {
				preparedStatement = connection.prepareStatement(sql);
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					count = resultSet.getInt(1);
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			if (count == 0) {
				sql = "insert into rp_roletablepower (rp_roleid,rp_tableid,rp_powerid) values("
						+ roleid + "," + tableid + ",1)";
				try {
					preparedStatement = connection.prepareStatement(sql);
					b = preparedStatement.execute();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		for (int i = 0; i < fillList.size(); i++) {
			String tableid = fillList.get(i);
			String sql = "select count(*) from rp_roletablepower where rp_tableid="
					+ tableid + " and rp_powerid=2 and rp_roleid=" + roleid;
			int count = 0;
			try {
				preparedStatement = connection.prepareStatement(sql);
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					count = resultSet.getInt(1);
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			String sql1 = "select count(*) from rp_roletablepower where rp_tableid="
					+ tableid + " and rp_powerid=3 and rp_roleid=" + roleid;
			int count1 = 0;
			try {
				preparedStatement = connection.prepareStatement(sql1);
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					count1 = resultSet.getInt(1);
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			String sql2 = "select count(*) from rp_roletablepower where rp_tableid="
					+ tableid + " and rp_powerid=4 and rp_roleid=" + roleid;
			int count2 = 0;
			try {
				preparedStatement = connection.prepareStatement(sql2);
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					count2 = resultSet.getInt(1);
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			if (count == 0 && count1 == 0 && count2 == 0) {
				sql = "insert into rp_roletablepower (rp_roleid,rp_tableid,rp_powerid) values("
						+ roleid + "," + tableid + ",2)";
				try {
					preparedStatement = connection.prepareStatement(sql);
					b = preparedStatement.execute();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		for (int i = 0; i < examoneList.size(); i++) {
			String tableid = examoneList.get(i);
			String sql = "select count(*) from rp_roletablepower where rp_tableid="
					+ tableid + " and rp_powerid=3 and rp_roleid=" + roleid;
			int count = 0;
			try {
				preparedStatement = connection.prepareStatement(sql);
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					count = resultSet.getInt(1);
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			String sql1 = "select count(*) from rp_roletablepower where rp_tableid="
					+ tableid + " and rp_powerid=2 and rp_roleid=" + roleid;
			System.out.println("exam----selectroletablepower====" + sql);
			int count1 = 0;
			try {
				preparedStatement = connection.prepareStatement(sql1);
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					count1 = resultSet.getInt(1);
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			System.out.println("examone----" + count1);
			if (count == 0 && count1 == 0) {
				sql = "insert into rp_roletablepower (rp_roleid,rp_tableid,rp_powerid) values("
						+ roleid + "," + tableid + ",3)";
				try {
					preparedStatement = connection.prepareStatement(sql);
					b = preparedStatement.execute();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		for (int i = 0; i < examtwoList.size(); i++) {
			String tableid = examtwoList.get(i);
			String sql = "select count(*) from rp_roletablepower where rp_tableid="
					+ tableid + " and rp_powerid=4 and rp_roleid=" + roleid;
			int count = 0;
			try {
				preparedStatement = connection.prepareStatement(sql);
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					count = resultSet.getInt(1);
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			String sql1 = "select count(*) from rp_roletablepower where rp_tableid="
					+ tableid + " and rp_powerid=2 and rp_roleid=" + roleid;
			int count1 = 0;
			try {
				preparedStatement = connection.prepareStatement(sql1);
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					count1 = resultSet.getInt(1);
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			if (count == 0 && count1 == 0) {
				sql = "insert into rp_roletablepower (rp_roleid,rp_tableid,rp_powerid) values("
						+ roleid + "," + tableid + ",4)";
				try {
					preparedStatement = connection.prepareStatement(sql);
					b = preparedStatement.execute();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		JdbcUtils_DBCP.release(connection, preparedStatement, resultSet);
	}

	@Override
	public int getPoweridByRoleidAndTableid(int roleid, int tableid) {
		int powerid = 0;
		String sql = " select rp_powerid from rp_roletablepower where 1=1 and  rp_roleid ="
				+ roleid + " and rp_tableid=" + tableid;

		System.out.println(sql);
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {

				powerid = resultSet.getInt("rp_powerid");

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return powerid;
	}

	@Override
	public int getPowerByUserid(String userid,String tableid) {
		int powerid = 0;
		String sql = "select rp_powerid from rp_roletablepower,ru_userrole where  ru_userid='"+userid+"' and ru_roleid=rp_roleid  and rp_tableid="+tableid;
		System.out.println(sql);
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {

				powerid = resultSet.getInt("rp_powerid");

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return powerid;
	}

	@Override
	public List<Integer> getFillRoleIdByColleges(String[] colleges) {
		List<Integer> roleIds = new ArrayList<>();
		String sql = "select ru_roleid from u_user,ru_userrole,(select DISTINCT rp_roleid from rp_roletablepower where rp_powerid = 2)tmp where (";
		sql+=String.format("u_deptxi like '%%%s%%'", colleges[0]);
		for (int i = 1; i < colleges.length; i++) {
				sql+=String.format(" or u_deptxi like '%%%s%%'", colleges[i]);
		}
		sql+=") and u_userid=ru_userid and rp_roleid = ru_roleid";
		System.out.println(sql);
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				roleIds.add(resultSet.getInt("ru_roleid"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return roleIds;
	}

}
