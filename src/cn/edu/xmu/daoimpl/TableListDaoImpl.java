package cn.edu.xmu.daoimpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.dao.TableListDao;
import cn.edu.xmu.entity.TableList;
import cn.edu.xmu.util.JdbcUtils_DBCP;

public class TableListDaoImpl extends BaseDaoImpl<TableList> implements
		TableListDao {

	@Override
	public List<TableList> getTableLists() {
		List<TableList> tableLists = new ArrayList<TableList>();
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
			return null;
		}
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "SELECT * from t_tableList";
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int tableid = resultSet.getInt("t_tableid");
				String tablename = resultSet.getString("t_tablename");
				int publish = resultSet.getInt("t_publish");
				Date date = resultSet.getDate("t_deadline");
				TableList tableList = new TableList(tableid, tablename, publish,date);
				tableLists.add(tableList);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, preparedStatement, resultSet);
		}
		return tableLists;
	}

	@SuppressWarnings("resource")
	@Override
	public List<TableList> getWatchTable(String userid) {
		Map<Integer, TableList> resultMap = new HashMap<Integer, TableList>();
		List<TableList> result = new ArrayList<>();
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
			return null;
		}
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		
		String sql = "select t_tableid,tmp2.t_tablename, tmp2.filltotal, count(*) as committotal "
				+ "FROM (select t_tableid,tmp.t_tablename, count(*) as filltotal FROM(select t_tableid,t_tableList.t_tablename from  "
				+ "t_tableList  WHERE t_tableid in  (select distinct  t_tableid   from  t_tableList,rp_roletablepower,ru_userrole,r_role  WHERE  rp_roleid = ru_roleid and ru_roleid=r_roleid and r_islive=1 "
				+ "and  ru_userid =? and  t_tableList.t_tableid = rp_tableid order by t_tableid ))tmp INNER JOIN rp_roletablepower WHERE rp_roletablepower.rp_tableid = "
				+ "tmp.t_tableid and rp_roletablepower.rp_powerid = 2 GROUP BY rp_roletablepower.rp_tableid)tmp2 INNER JOIN rf_fillreview "
				+ " WHERE tmp2.t_tableid = rf_fillreview.rf_tableid and (rf_reviewsituation = 2 or rf_reviewsituation = 3) GROUP BY rf_fillreview.rf_tableid ";
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userid);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int tableid = resultSet.getInt("t_tableid");
				String tablename = resultSet.getString("t_tablename");
				String filltotal = resultSet.getString("filltotal");
				String committotal = resultSet.getString("committotal");
				TableList tableList = new TableList(tableid, tablename,committotal+"/"+filltotal);
				resultMap.put(tableid, tableList);
			}
			
			String sql2 = "select t_tableid,tmp.t_tablename, count(*) as filltotal FROM(select t_tableid,t_tableList.t_tablename from  t_tableList  WHERE t_tableid in  (select distinct  t_tableid   "
					+ "from  t_tableList,rp_roletablepower,ru_userrole,r_role    WHERE  rp_roleid = ru_roleid and ru_roleid=r_roleid and r_islive=1 and  ru_userid =? and  t_tableList.t_tableid = rp_tableid order by t_tableid ))tmp "
					+ "INNER JOIN rp_roletablepower WHERE rp_roletablepower.rp_tableid = tmp.t_tableid and rp_roletablepower.rp_powerid = 2 GROUP BY rp_roletablepower.rp_tableid";
			
			preparedStatement = connection.prepareStatement(sql2);
			preparedStatement.setString(1, userid);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int tableid = resultSet.getInt("t_tableid");
				if(!resultMap.containsKey(tableid))
				{
					String tablename = resultSet.getString("t_tablename");
					String filltotal = resultSet.getString("filltotal");
					TableList tableList = new TableList(tableid, tablename,"0/"+filltotal);
					resultMap.put(tableid, tableList);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, preparedStatement, resultSet);
		}
		

		for (Map.Entry<Integer, TableList> entry : resultMap.entrySet()) {  
			result.add(entry.getValue());
        } 
		Collections.sort(result);
		
		return result;
	}

	@Override
	public String getTableNameById(String tableId) {

		String result;

		String sql = "select t_tablename_db from t_tableList where t_tableid = "
				+ tableId;

		System.out.println("select语句" + sql);

		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
			return null;
		}
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			result = resultSet.getString("t_tablename_db");
		} catch (SQLException e) {
			e.printStackTrace();
			result = null;
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return result;
	}

	@Override
	public List<TableList> getTableListsLike(String like) {
		List<TableList> tableLists = new ArrayList<TableList>();
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
			return null;
		}
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "SELECT * from t_tableList where t_tablename LIKE '%"
				+ like + "%'";
		System.out.println(sql);
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int tableid = resultSet.getInt("t_tableid");
				String tablename = resultSet.getString("t_tablename");
				int publish = resultSet.getInt("t_publish");
				TableList tableList = new TableList(tableid, tablename, publish);
				tableLists.add(tableList);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, preparedStatement, resultSet);
		}
		return tableLists;
	}

	
	@Override
	public String getTablename(String tableid) {
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
			return null;
		}
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String tablename = null;
		String sql = "SELECT t_tableList.t_tablename from t_tableList WHERE t_tableList.t_tableid="
				+ tableid;
		System.out.println(sql);
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				tablename = resultSet.getString("t_tablename");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, preparedStatement, resultSet);
		}
		return tablename;
	}

	@Override
	public boolean changePublishAll(String flag,String tableid,Date datetime) {
		String sql = "";
		if(flag.equals("2")){
		   sql = "update t_tableList set t_publish= 0 , t_deadline=?";
		}
		else{
		   sql = "update t_tableList set t_publish= 1 , t_deadline=?";
		}
		System.out.println(sql);
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		}
		PreparedStatement preparedStatement = null;
		boolean result = false;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setDate(1, datetime);
			result = preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils_DBCP.release(connection, preparedStatement, null);
		}
		return result;
	}

	@Override
	public boolean changePublish(String flag, String tableid,Date datetime) {		
		String sql = "update t_tableList set t_publish= "+ flag+" , t_deadline=? "
				+ " WHERE t_tableid = " + tableid;
		System.out.println(sql);
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		}
		PreparedStatement preparedStatement = null;
		boolean result = false;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setDate(1, datetime);
			result = preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils_DBCP.release(connection, preparedStatement, null);
		}
		return result;
	}

	@Override
	public boolean changePublishPage(String flag,String tableid){
		String tableids[] = tableid.split(",");
		boolean result = false;
		
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		}
		PreparedStatement preparedStatement = null;
		
		for (String tid : tableids) {
			String sql = "update t_tableList set t_publish=1 "
					+ " WHERE t_tableid = " + tid;
			System.out.println(sql);
		
		   try {
			   preparedStatement = connection.prepareStatement(sql);
			   result = preparedStatement.execute();
		   } catch (SQLException e) {
			   e.printStackTrace();
			   return false;
		   }
		}
		JdbcUtils_DBCP.release(connection, preparedStatement, null);
		
		return result;
	}
	
	@Override
	public Date getTableDate(String tableid) {
		String sql = "select t_deadline from t_tableList where t_tableid="+tableid;
		System.out.println(sql);
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
			return null;
		}
		PreparedStatement preparedStatement = null;
		Date date = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				date = resultSet.getDate("t_deadline");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils_DBCP.release(connection, preparedStatement, resultSet);
		}
		return date;
	}

	
}
