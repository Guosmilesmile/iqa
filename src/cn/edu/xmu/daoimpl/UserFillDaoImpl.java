package cn.edu.xmu.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.xmu.dao.RoleDao;
import cn.edu.xmu.dao.UserFillDao;
import cn.edu.xmu.entity.UserFill;
import cn.edu.xmu.util.JdbcUtils_DBCP;

public class UserFillDaoImpl extends BaseDaoImpl<UserFill>implements UserFillDao
{

	@Override
	public List<UserFill> getUserFills(String userid)
	{
		List<UserFill> userFills = new ArrayList<UserFill>();
		Connection connection = null;
		try
		{
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1)
		{
			e1.printStackTrace();
		}
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "select rf_id,t_tableid,t_tablename,rf_fillsituation,rf_reviewsituation,rf_reviewcontent,t_sonid from rf_fillreview,t_tableList  where "
				+ " rf_tableid = t_tableid and  rf_fillroleid=(select ru_roleid from "
				+ " ru_userrole where ru_userid=?) and" + " t_tableid in "
				+ " (select t_tableid from t_tableList,rp_roletablepower WHERE"
				+ " t_tableid=rp_tableid and rp_powerid=2 and t_publish=1 and rp_roleid IN"
				+ " (select ru_roleid from ru_userrole where ru_userid=? and ru_roleid in"
				+ " (select r_roleid from r_role where r_islive=1))) order by t_tableid";
		System.out.println("fill=====" + sql);
		try
		{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userid);
			preparedStatement.setString(2, userid);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next())
			{
				int id = resultSet.getInt("rf_id");
				int tableid = resultSet.getInt("t_tableid");
				String tablename = resultSet.getString("t_tablename");
				int fillsituation = resultSet.getInt("rf_fillsituation");
				int reviewsituation = resultSet.getInt("rf_reviewsituation");
				String reviewcontent = resultSet.getString("rf_reviewcontent");
				int sonid = resultSet.getInt("t_sonid");
				UserFill userFill = new UserFill(id, tableid, tablename, fillsituation, reviewsituation, reviewcontent,
						sonid);
				userFills.add(userFill);
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			JdbcUtils_DBCP.release(connection, preparedStatement, resultSet);
		}

		return userFills;
	}
	
	@Override
	public List<UserFill> getTableDetail(String tid){
		
		String sql = "SELECT t_tableList.t_tableid, t_tableList.t_tablename, r_role.r_roleid,r_role.r_rolename, rf_fillreview.rf_fillsituation, rf_fillreview.rf_reviewsituation "
				+ "FROM t_tableList, r_role, rf_fillreview "
				+ "WHERE t_tableList.t_tableid = rf_tableid AND rf_fillroleid = r_roleid AND t_tableid = "+ tid;
		List<UserFill> userFills = new ArrayList<UserFill>();
		Connection connection = null;
		try
		{
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1)
		{
			e1.printStackTrace();
		}
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try
		{
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next())
			{
				int tableid = resultSet.getInt("t_tableid");
				String tablename = resultSet.getString("t_tablename");
				int roleid = resultSet.getInt("r_roleid");
				String rolename = resultSet.getString("r_rolename");
				int fillsituation = resultSet.getInt("rf_fillsituation");
				int reviewsituation = resultSet.getInt("rf_reviewsituation");
				UserFill userFill = new UserFill(tableid, tablename, roleid, rolename, fillsituation, reviewsituation);
				userFills.add(userFill);
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			JdbcUtils_DBCP.release(connection, preparedStatement, resultSet);
		}
		
		return userFills;
	}
	

	@Override
	public List<UserFill> getUserExam(String userid, String college)
	{

		RoleDao roleDao = new RoleDaoImpl();
		List<UserFill> userFills = new ArrayList<UserFill>();
		Connection connection = null;
		try
		{
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1)
		{
			e1.printStackTrace();
		}
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "select distinct (t_tableid),t_tablename from t_tableList,rp_roletablepower"
				+ " where t_tableid=rp_tableid and (rp_powerid=3 or rp_powerid=4) and rp_roleid ="
				+ " (select ru_roleid from ru_userrole,r_role where r_islive=1 and ru_roleid=r_roleid and ru_userid=?)"
				+ " order by t_tableid ";
		System.out.println("examtableid------" + sql);
		try
		{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userid);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next())
			{
				int tableid = resultSet.getInt("t_tableid");
				String tablename = resultSet.getString("t_tablename");
				UserFill userFill = new UserFill(tableid, tablename, "", 0, 0, "");
				userFills.add(userFill);
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			JdbcUtils_DBCP.release(connection, preparedStatement, resultSet);
		}
		List<UserFill> userFillList = new ArrayList<UserFill>();
		for (int i = 0; i < userFills.size(); i++)
		{
			int flag = 0;
			UserFill userFill = userFills.get(i);
			sql = "select rf_fillsituation,rf_reviewsituation,rf_reviewcontent,rf_fillroleid from rf_fillreview"
					+ " where rf_tableid=" + userFill.getTableid();
			if (college!=null && !college.equals("\"\""))
				sql += String.format(
						" and rf_fillroleid in(select r_roleid from r_role where r_rolename like  '%%%s%%') ", college);
			System.out.println(sql);
			try
			{
				connection = JdbcUtils_DBCP.getConnection();
				preparedStatement = connection.prepareStatement(sql);
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next())
				{
					flag = 1;
					UserFill userFill2 = new UserFill(userFill.getTableid(), userFill.getTablename(), "", 0, 0, "");
					int fillsituation = resultSet.getInt("rf_fillsituation");
					int reviewsituation = resultSet.getInt("rf_reviewsituation");
					String reviewcontent = resultSet.getString("rf_reviewcontent");
					int fillroleid = resultSet.getInt("rf_fillroleid");
					String rolename = roleDao.GetRoleNameByRoleId(fillroleid);
					userFill2.setRoleid(fillroleid);
					userFill2.setRf_fillsituation(fillsituation);
					userFill2.setRf_reviewsituation(reviewsituation);
					userFill2.setRf_reviewcontent(reviewcontent);
					userFill2.setRolename(rolename);
					userFillList.add(userFill2);
				}
				if (flag == 0)
				{
					userFillList.add(userFill);
				}

			} catch (SQLException e)
			{
				e.printStackTrace();
				System.out.println("22===================college=" + college);
			} finally
			{
				JdbcUtils_DBCP.release(connection, preparedStatement, resultSet);
			}
		}
		return userFillList;
	}

	@Override
	public int UpdateReview(String userid, int tableid, int flag, int roleid, int reviewsituation)
	{
		int bool = 0;
		Connection connection = null;
		try
		{
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1)
		{
			e1.printStackTrace();
		}
		PreparedStatement preparedStatement = null;

		String sql = "update rf_fillreview set rf_reviewsituation=? where rf_tableid=? and rf_fillroleid = ? ";
		System.out.println(sql);
		System.out.println("reviewsituation:" + reviewsituation);
		System.out.println("tableid:" + tableid);
		System.out.println("roleid:" + roleid);
		try
		{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, reviewsituation);
			preparedStatement.setInt(2, tableid);
			preparedStatement.setInt(3, roleid);
			boolean b = preparedStatement.execute();
			if (b)
			{
				bool = 1;
				return bool;
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			JdbcUtils_DBCP.release(connection, preparedStatement, null);
		}

		/*
		 * if(flag==2){//不通过 }else{//通过 String sql=
		 * "update rf_fillreview set rf_reviewsituation=(select rp_powerid-1 from rp_roletablepower where rp_tableid=? and rp_roleid=(select ru_roleid from ru_userrole where ru_userid =?)) where rf_tableid=? and rf_fillroleid = ? "
		 * ; System.out.println("pass-----"+sql); try { preparedStatement =
		 * connection.prepareStatement(sql); preparedStatement.setInt(1,
		 * tableid); preparedStatement.setString(2, userid);
		 * preparedStatement.setInt(3, tableid); preparedStatement.setInt(4,
		 * roleid); boolean b = preparedStatement.execute(); if(b){ bool=1;
		 * return bool; } } catch (SQLException e) { e.printStackTrace();
		 * }finally { try { preparedStatement.close(); connection.close(); }
		 * catch (SQLException e) { e.printStackTrace(); } }
		 * 
		 * }
		 */
		return bool;
	}
	
	@Override
	public int UpdateReview(String userid, int tableid, int flag, List<Integer> roleid, int reviewsituation)
	{
		int bool = 0;
		Connection connection = null;
		try
		{
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1)
		{
			e1.printStackTrace();
		}
		PreparedStatement preparedStatement = null;

		String sql = "update rf_fillreview set rf_reviewsituation=%s where rf_tableid=%s ";
		System.out.println(sql);
		System.out.println("reviewsituation:" + reviewsituation);
		System.out.println("tableid:" + tableid);
		System.out.println("roleid:" + roleid);
		try
		{
			sql = String.format(sql, reviewsituation,tableid);
			if(roleid != null && roleid.size() > 0)
			{
				sql+=String.format(" and (rf_fillroleid=%s", roleid.get(0));
				for (int i = 1; i < roleid.size(); i++) {
					sql+=String.format(" or rf_fillroleid=%s", roleid.get(i));
				}
				sql+=")";
			}
			System.out.println("============="+sql);
			preparedStatement = connection.prepareStatement(sql);
			boolean b = preparedStatement.execute();
			if (b)
			{
				bool = 1;
				return bool;
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			JdbcUtils_DBCP.release(connection, preparedStatement, null);
		}

		/*
		 * if(flag==2){//不通过 }else{//通过 String sql=
		 * "update rf_fillreview set rf_reviewsituation=(select rp_powerid-1 from rp_roletablepower where rp_tableid=? and rp_roleid=(select ru_roleid from ru_userrole where ru_userid =?)) where rf_tableid=? and rf_fillroleid = ? "
		 * ; System.out.println("pass-----"+sql); try { preparedStatement =
		 * connection.prepareStatement(sql); preparedStatement.setInt(1,
		 * tableid); preparedStatement.setString(2, userid);
		 * preparedStatement.setInt(3, tableid); preparedStatement.setInt(4,
		 * roleid); boolean b = preparedStatement.execute(); if(b){ bool=1;
		 * return bool; } } catch (SQLException e) { e.printStackTrace();
		 * }finally { try { preparedStatement.close(); connection.close(); }
		 * catch (SQLException e) { e.printStackTrace(); } }
		 * 
		 * }
		 */
		return bool;
	}
}
