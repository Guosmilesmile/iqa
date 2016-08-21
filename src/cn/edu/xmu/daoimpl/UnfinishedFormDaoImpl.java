package cn.edu.xmu.daoimpl;



import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.dao.UnfinishedFormDao;
import cn.edu.xmu.entity.UnfinishedForm;
import cn.edu.xmu.table.UnfinishedFormTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;

/**
 * 附表 未填表格说明
 * @author chunfeng
 *
 */
public class UnfinishedFormDaoImpl  extends BaseDaoImpl<UnfinishedForm> implements UnfinishedFormDao {

	@Override
	public List<UnfinishedForm> getUnfinishedForm(int start, int end,
			String sortStr, String orderStr, Map<String, String> params,Date deadline) {
		
		String sql = " select tmp.* from ( " + " select * from "
				+ UnfinishedFormTable.TABLE_NAME + " where 1=1 ";
		if (deadline != null) {
			sql += String.format("and "+UnfinishedFormTable.UF_DEADLINE+" like  '%s%%' ", deadline);
		}
		if (!(params == null || params.keySet().size() == 0)) {
			for (Object object : params.keySet()) {

				String key = object.toString();
				String value = params.get(key).toString();
				sql += String.format("and %s like  '%%%s%%' ", key, value);
			}
		}
		
		sql += " order by " + sortStr + " " + orderStr + " ) tmp limit "
					+ start + " ," + end;
		
		System.out.println(sql);

		List<UnfinishedForm> unfinishedForms = new ArrayList<UnfinishedForm>();
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		try {
			System.out.println(sql);
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				int uf_id = resultSet.getInt(UnfinishedFormTable.UF_ID);
 
				String uf_formname = resultSet.getString(UnfinishedFormTable.UF_FORMNAME);  
				String uf_accountfor  = resultSet.getString(UnfinishedFormTable.UF_ACCOUNTFOR); 
				
				String uf_college  = resultSet.getString(UnfinishedFormTable.UF_COLLEGE); 
				int uf_serialnumber = resultSet.getInt(UnfinishedFormTable.UF_SERIALNUMBER);
				Date uf_deadline = resultSet.getDate(UnfinishedFormTable.UF_DEADLINE); 
				String uf_comments =  resultSet.getString(UnfinishedFormTable.UF_COMMENTS);
				if(null == uf_comments){
					uf_comments = "";
				}
				int uf_isnull = resultSet.getInt(UnfinishedFormTable.UF_ISNULL);
				
				UnfinishedForm unfinishedForm = new UnfinishedForm(uf_id, uf_formname,
						uf_accountfor, uf_college, uf_serialnumber, uf_deadline, uf_comments, uf_isnull);

				unfinishedForms.add(unfinishedForm);
			}
			return unfinishedForms;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
	}

	@Override
	public int getUnfinishedFormCount(Map queryParams) {
		// TODO Auto-generated method stub
		int count = 0;
		String sql = "select count(*) from " + UnfinishedFormTable.TABLE_NAME
				+ " where 1 = 1 ";
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (queryParams != null)
		{
			for (Object object : queryParams.keySet())
			{
				String key = object.toString();
				String value = queryParams.get(key).toString();
				sql += String.format(" and  %s like  '%%%s%%' ", key, value);
			}
			// sql += String.format(" or %s ='%s'", "college","");
		}
		
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		try {
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			resultSet.next();
			count = resultSet.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} finally{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}

		System.out.println(count);
		return count;
	}

	@Override
	public boolean batchDelete(String[] ufids) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();

		for (String ufid : ufids) {
			String sql = "delete from " + UnfinishedFormTable.TABLE_NAME
					+ " where " + UnfinishedFormTable.UF_ID + " = '" + ufid
					+ "'";
			System.out.println(sql);
			try {
				stmt.executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		JdbcUtils_DBCP.release(connection, stmt, null);

		return true;
	}

	@Override
	public int addUnfinishedForm(UnfinishedForm unfinishedForm) {
		// TODO Auto-generated method stub
		int result = 0;

		String sql2 = "update " + UnfinishedFormTable.TABLE_NAME + " set "
				+ UnfinishedFormTable.UF_SERIALNUMBER + " = "
				+ UnfinishedFormTable.UF_SERIALNUMBER + " +1 where "
				+ UnfinishedFormTable.UF_SERIALNUMBER + ">="
				+ unfinishedForm.getUf_serialnumber();
		Connection connection2 = null;
		try {
			connection2 = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		PreparedStatement pstmt2 = null;
		try {
			pstmt2 = connection2.prepareStatement(sql2);
			result = pstmt2.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			try {
				JdbcUtils_DBCP.release(connection2, pstmt2, null);
			} catch (Exception e2) {
				return 0;
			}
		}

		String sql = "insert into " + UnfinishedFormTable.TABLE_NAME + "("
				+ UnfinishedFormTable.UF_FORMNAME + ","
				+ UnfinishedFormTable.UF_ACCOUNTFOR + ","
				+ UnfinishedFormTable.UF_COLLEGE + ","
				+ UnfinishedFormTable.UF_SERIALNUMBER + ","
				+ UnfinishedFormTable.UF_ISNULL +")values(?,?,?,?,?)";

		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PreparedStatement pstmt = null;

		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, unfinishedForm.getUf_formname());
			pstmt.setString(2, unfinishedForm.getUf_accountfor());
			pstmt.setString(3, unfinishedForm.getUf_college());
			pstmt.setInt(4, unfinishedForm.getUf_serialnumber());
			pstmt.setInt(5, unfinishedForm.getUf_isnull());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			result = -1;
		} finally{
			JdbcUtils_DBCP.release(connection, pstmt, null);
		}


		return result;
	}

	@Override
	public int alterUnfinishedForm(Map<String, String> valueMap, String id) {
		// TODO Auto-generated method stub
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(UnfinishedFormTable.UF_ID, id);
		int result = updateRecord(UnfinishedFormTable.TABLE_NAME, valueMap,
				condition);
		return result;
	}

	@Override
	public List<UnfinishedForm> getAllUnfinishedForm() {
		// TODO Auto-generated method stub
		String sql = " select * from " + UnfinishedFormTable.TABLE_NAME
				+ " where 1=1 " + " order by " + UnfinishedFormTable.UF_ID;
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			System.out.println(sql);
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			List<UnfinishedForm> unfinishedFormList = new ArrayList<UnfinishedForm>();
			while (resultSet.next()) {
				int uf_id = resultSet.getInt(UnfinishedFormTable.UF_ID);
				 
				String uf_formname = resultSet.getString(UnfinishedFormTable.UF_FORMNAME);  
				String uf_accountfor  = resultSet.getString(UnfinishedFormTable.UF_ACCOUNTFOR); 
				
				String uf_college  = resultSet.getString(UnfinishedFormTable.UF_COLLEGE); 
				int uf_serialnumber = resultSet.getInt(UnfinishedFormTable.UF_SERIALNUMBER);
				Date uf_deadline = resultSet.getDate(UnfinishedFormTable.UF_DEADLINE); 
				String uf_comments =  resultSet.getString(UnfinishedFormTable.UF_COMMENTS);
				if(null == uf_comments){
					uf_comments = "";
				}
				int uf_isnull = resultSet.getInt(UnfinishedFormTable.UF_ISNULL);
				
				UnfinishedForm unfinishedForm = new UnfinishedForm(uf_id, uf_formname,
						uf_accountfor, uf_college, uf_serialnumber, uf_deadline, uf_comments, uf_isnull);

				unfinishedFormList.add(unfinishedForm);
			}
			return unfinishedFormList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}

	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		Statement stmt = null;
		try {
			stmt = connection.createStatement();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		String sql = "delete from " + UnfinishedFormTable.TABLE_NAME;
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils_DBCP.release(connection, stmt, null);
		}
	}

	@Override
	public void deleteByCollegeandDeadline(String college, Date deadline)
			throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();
		String sql = "delete from " + UnfinishedFormTable.TABLE_NAME
				+ " where " + UnfinishedFormTable.UF_COLLEGE + " = '" + college + "'" +" and uf_deadline is null ";
	//	sql += String.format(" and fe_deadline = ", null);
		System.out.println(sql);
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils_DBCP.release(connection, stmt, null);
		}

	}

}
