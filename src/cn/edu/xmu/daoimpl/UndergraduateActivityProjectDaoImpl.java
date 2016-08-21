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

import cn.edu.xmu.dao.UndergraduateActivityProjectDao;
import cn.edu.xmu.entity.UndergraduateActivityProject;
import cn.edu.xmu.table.UndergraduateActivityProjectTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;


/**
 * @author zhantu
 * 图书当年新增情况  实体类功能 ——接口实现
 * date 2015-06-30
 */

public class UndergraduateActivityProjectDaoImpl extends BaseDaoImpl<UndergraduateActivityProject>implements UndergraduateActivityProjectDao{

	@Override
	public int addRecord(UndergraduateActivityProject uap) {
		
		int result = 0;

		String t_sql = "update " + UndergraduateActivityProjectTable.TABLE_NAME + " set "
				+ UndergraduateActivityProjectTable.UAP_SERIALNUMBER + " = "
				+ UndergraduateActivityProjectTable.UAP_SERIALNUMBER + " +1 where "
				+ UndergraduateActivityProjectTable.UAP_SERIALNUMBER + ">=?";

		
		Connection connection2 = null;
		try {
			//连接池取得对象连接
			connection2 = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
			return 0;
		}
		PreparedStatement t_pstmt = null;
		try {
			//获取操作对象
			t_pstmt = connection2.prepareStatement(t_sql);
			t_pstmt.setInt(1, uap.getUap_serialnumber());
			
			//执行插入操作并更新
			result = t_pstmt.executeUpdate();
			
		} catch (SQLException e) {
			//事务回滚，不做插入操作
			e.printStackTrace();
			return 0;
		} finally {
			JdbcUtils_DBCP.release(connection2, t_pstmt, null);
		}

		//执行插入操作的语句
		String sql = "insert into " + UndergraduateActivityProjectTable.TABLE_NAME + "("
				+ UndergraduateActivityProjectTable.UAP_PROJECTNAME + ","
				+ UndergraduateActivityProjectTable.UAP_UNIT + ","
				+ UndergraduateActivityProjectTable.UAP_PROJECTTYPE + ","
				+ UndergraduateActivityProjectTable.UAP_PERSONNUMBER + ","
				+ UndergraduateActivityProjectTable.UAP_MAJORNUMBER + ","
				
				+ UndergraduateActivityProjectTable.UAP_PROJECTNUMBER + ","
				+ UndergraduateActivityProjectTable.UAP_AWARDGRADE + ","
				+ UndergraduateActivityProjectTable.UAP_AWARDTYPE + ","
				+ UndergraduateActivityProjectTable.UAP_AWARDDATE + ","
				
				+ UndergraduateActivityProjectTable.UAP_SERIALNUMBER + ","
				+ UndergraduateActivityProjectTable.UAP_COLLEGE + ","
				+ UndergraduateActivityProjectTable.UAP_COMMENTS + ","
				+ UndergraduateActivityProjectTable.ISNULL
				+ ")values(?,?,?,?,?,?,?,?,?,?,?,?,?)";

		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, uap.getUap_projectname());
			pstmt.setString(2, uap.getUap_unit());
			pstmt.setString(3, uap.getUap_projecttype());
			pstmt.setInt(4, uap.getUap_personnumber());
			pstmt.setInt(5, uap.getUap_majornumber());
			pstmt.setInt(6, uap.getUap_projectnumber());
			pstmt.setString(7, uap.getUap_awardgrade());
			pstmt.setString(8, uap.getUap_awardtype());
			pstmt.setDate(9, uap.getUap_awarddate());
			pstmt.setInt(10, uap.getUap_serialnumber());
			pstmt.setString(11, uap.getUap_college());
			pstmt.setString(12, uap.getUap_comments());
			pstmt.setInt(13, uap.getIsnull());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			//事务回滚。不做插入操作
			e.printStackTrace();
			result = -1;
			
		} finally {
			JdbcUtils_DBCP.release(connection,pstmt,null);
		}
		return result;
		
	}

	@Override
	public int getUndergraduateActivityProjectCount(Map queryParams) {
		
		int count = 0;
		//获取条件的语句
		String sql = "select count(*) from " + UndergraduateActivityProjectTable.TABLE_NAME
				+ " where 1 = 1";
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		for (Object object : queryParams.keySet()) {
			String key = object.toString();
			String value = queryParams.get(key).toString();
			sql += String.format(" and  %s like  '%%%s%%' ", key, value);
		}
		sql += String.format(" or %s ='%s'", UndergraduateActivityProjectTable.UAP_COLLEGE, "");
		
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
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		
		return count;
	}

	@Override
	public List<UndergraduateActivityProject> getAllUndergraduateActivityProject(int start, int end,
			String sortStr, String orderStr, Map queryParams) {
		
		String sql = " select tmp.* from ( " + " select * from "
				+ UndergraduateActivityProjectTable.TABLE_NAME + " where 1=1 ";
		
		for (Object object : queryParams.keySet()) {
			String key = object.toString();
			String value = queryParams.get(key).toString();
			sql += String.format(" and %s like  '%%%s%%'", key, value);
		}
		sql += " order by " + sortStr + " " + orderStr + " ) tmp limit "
				+ start + " ," + end;

		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Date temp = Date.valueOf("1800-1-1");
		
		List<UndergraduateActivityProject> uaps = new ArrayList<UndergraduateActivityProject>();
		try {
			
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int uap_id = resultSet.getInt(UndergraduateActivityProjectTable.UAP_ID);
				
				String uap_projectname = resultSet.getString(UndergraduateActivityProjectTable.UAP_PROJECTNAME);
				String uap_unit = resultSet.getString(UndergraduateActivityProjectTable.UAP_UNIT);
				String uap_projecttype = resultSet.getString(UndergraduateActivityProjectTable.UAP_PROJECTTYPE);
				Integer uap_personnumber = resultSet.getInt(UndergraduateActivityProjectTable.UAP_PERSONNUMBER);
				if(uap_personnumber==-999)
					uap_personnumber = null;
				Integer uap_majornumber = resultSet.getInt(UndergraduateActivityProjectTable.UAP_MAJORNUMBER);
				if(uap_majornumber==-999)
					uap_majornumber = null;
				Integer uap_projectnumber = resultSet.getInt(UndergraduateActivityProjectTable.UAP_PROJECTNUMBER);
				if(uap_projectnumber==-999)
					uap_projectnumber = null;
				String uap_awardgrade = resultSet.getString(UndergraduateActivityProjectTable.UAP_AWARDGRADE);
				String uap_awardtype = resultSet.getString(UndergraduateActivityProjectTable.UAP_AWARDTYPE);
				Date uap_awarddate = resultSet.getDate(UndergraduateActivityProjectTable.UAP_AWARDDATE);
				if(uap_awarddate.equals(temp))
					uap_awarddate = null;
				int uap_serialnumber = resultSet.getInt(UndergraduateActivityProjectTable.UAP_SERIALNUMBER);
				Date uap_deadline = resultSet.getDate(UndergraduateActivityProjectTable.UAP_DEADLINE);
				String uap_comments = resultSet.getString(UndergraduateActivityProjectTable.UAP_COMMENTS);
				String uap_college = resultSet.getString(UndergraduateActivityProjectTable.UAP_COLLEGE);
				int isnull = resultSet.getInt(UndergraduateActivityProjectTable.ISNULL);
				if(uap_comments==null)
					uap_comments = "";
				
				UndergraduateActivityProject uap = new UndergraduateActivityProject(uap_id, uap_projectname,
						uap_unit, uap_projecttype, uap_personnumber,
						uap_majornumber, uap_projectnumber, uap_awardgrade,
						uap_awardtype, uap_awarddate, uap_serialnumber,
						uap_deadline, uap_college, uap_comments, isnull);
				
				uaps.add(uap);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return uaps;
	}

	@Override
	public int alterUndergraduateActivityProject(Map<String, String> valueMap, String id) {
		
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(UndergraduateActivityProjectTable.UAP_ID, id);
		int result = updateRecord(UndergraduateActivityProjectTable.TABLE_NAME, valueMap,
				condition);
		return result;
	}

	@Override
	public boolean batchDelete(String[] uapids) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();

		for (String uapid : uapids) {
			String sql = "delete from " + UndergraduateActivityProjectTable.TABLE_NAME
					+ " where " + UndergraduateActivityProjectTable.UAP_ID + " = '" + uapid + "'";
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
	public List<UndergraduateActivityProject> getUndergraduateActivityProject(int start, int end,
			String sortStr, String orderStr, Map<String, String> params,
			Date deadline) {
		String sql = " select tmp.* from ( " + " select * from "
				+ UndergraduateActivityProjectTable.TABLE_NAME + " where 1=1 ";
		if (deadline != null) {

			sql += String.format("and "+UndergraduateActivityProjectTable.UAP_DEADLINE+" like  '%s%%' ", deadline);
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

		List<UndergraduateActivityProject> undergraduateActivityProjects = new ArrayList<UndergraduateActivityProject>();
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
			Date temp = Date.valueOf("1800-1-1");
			while (resultSet.next()) {
				int uap_id = resultSet.getInt(UndergraduateActivityProjectTable.UAP_ID);
				
				String uap_projectname = resultSet.getString(UndergraduateActivityProjectTable.UAP_PROJECTNAME);
				String uap_unit = resultSet.getString(UndergraduateActivityProjectTable.UAP_UNIT);
				String uap_projecttype = resultSet.getString(UndergraduateActivityProjectTable.UAP_PROJECTTYPE);
				Integer uap_personnumber = resultSet.getInt(UndergraduateActivityProjectTable.UAP_PERSONNUMBER);
				if(uap_personnumber==-999)
					uap_personnumber = null;
				Integer uap_majornumber = resultSet.getInt(UndergraduateActivityProjectTable.UAP_MAJORNUMBER);
				if(uap_majornumber==-999)
					uap_majornumber = null;
				Integer uap_projectnumber = resultSet.getInt(UndergraduateActivityProjectTable.UAP_PROJECTNUMBER);
				if(uap_projectnumber==-999)
					uap_projectnumber = null;
				String uap_awardgrade = resultSet.getString(UndergraduateActivityProjectTable.UAP_AWARDGRADE);
				String uap_awardtype = resultSet.getString(UndergraduateActivityProjectTable.UAP_AWARDTYPE);
				Date uap_awarddate = resultSet.getDate(UndergraduateActivityProjectTable.UAP_AWARDDATE);
				if(uap_awarddate.equals(temp))
					uap_awarddate = null;
				int uap_serialnumber = resultSet.getInt(UndergraduateActivityProjectTable.UAP_SERIALNUMBER);
				Date uap_deadline = resultSet.getDate(UndergraduateActivityProjectTable.UAP_DEADLINE);
				String uap_comments = resultSet.getString(UndergraduateActivityProjectTable.UAP_COMMENTS);
				String uap_college = resultSet.getString(UndergraduateActivityProjectTable.UAP_COLLEGE);
				int isnull = resultSet.getInt(UndergraduateActivityProjectTable.ISNULL);
				
				UndergraduateActivityProject uap = new UndergraduateActivityProject(uap_id, uap_projectname,
						uap_unit, uap_projecttype, uap_personnumber,
						uap_majornumber, uap_projectnumber, uap_awardgrade,
						uap_awardtype, uap_awarddate, uap_serialnumber,
						uap_deadline, uap_college, uap_comments, isnull);

				undergraduateActivityProjects.add(uap);
			}
			return undergraduateActivityProjects;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
	}
	
	@Override
	public void deleteByCollegeandDeadline(String college, Date deadline)
			throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();
		String sql = "delete from " + UndergraduateActivityProjectTable.TABLE_NAME
				+ " where " + UndergraduateActivityProjectTable.UAP_COLLEGE + " = '" + college + "'" +" and uap_deadline is null ";
		System.out.println(sql);
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils_DBCP.release(connection, stmt, null);
		}
		
	}

	@Override
	public List<UndergraduateActivityProject> getAllUndergraduateActivityProject() {
		String sql = " select * from " + UndergraduateActivityProjectTable.TABLE_NAME
				+ " where 1=1 " + " order by " + UndergraduateActivityProjectTable.UAP_ID;
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			System.out.println(sql);
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			Date temp = Date.valueOf("1800-1-1");
			List<UndergraduateActivityProject> undergraduateActivityProjectList = new ArrayList<UndergraduateActivityProject>();
			while (resultSet.next()) {
				int uap_id = resultSet.getInt(UndergraduateActivityProjectTable.UAP_ID);
				
				String uap_projectname = resultSet.getString(UndergraduateActivityProjectTable.UAP_PROJECTNAME);
				String uap_unit = resultSet.getString(UndergraduateActivityProjectTable.UAP_UNIT);
				String uap_projecttype = resultSet.getString(UndergraduateActivityProjectTable.UAP_PROJECTTYPE);
				Integer uap_personnumber = resultSet.getInt(UndergraduateActivityProjectTable.UAP_PERSONNUMBER);
				if(uap_personnumber==-999)
					uap_personnumber = null;
				Integer uap_majornumber = resultSet.getInt(UndergraduateActivityProjectTable.UAP_MAJORNUMBER);
				if(uap_majornumber==-999)
					uap_majornumber = null;
				Integer uap_projectnumber = resultSet.getInt(UndergraduateActivityProjectTable.UAP_PROJECTNUMBER);
				if(uap_projectnumber==-999)
					uap_projectnumber = null;
				String uap_awardgrade = resultSet.getString(UndergraduateActivityProjectTable.UAP_AWARDGRADE);
				String uap_awardtype = resultSet.getString(UndergraduateActivityProjectTable.UAP_AWARDTYPE);
				Date uap_awarddate = resultSet.getDate(UndergraduateActivityProjectTable.UAP_AWARDDATE);
				if(uap_awarddate.equals(temp))
					uap_awarddate = null;
				int uap_serialnumber = resultSet.getInt(UndergraduateActivityProjectTable.UAP_SERIALNUMBER);
				Date uap_deadline = resultSet.getDate(UndergraduateActivityProjectTable.UAP_DEADLINE);
				String uap_comments = resultSet.getString(UndergraduateActivityProjectTable.UAP_COMMENTS);
				String uap_college = resultSet.getString(UndergraduateActivityProjectTable.UAP_COLLEGE);
				int isnull = resultSet.getInt(UndergraduateActivityProjectTable.ISNULL);
				
				UndergraduateActivityProject uap = new UndergraduateActivityProject(uap_id, uap_projectname,
						uap_unit, uap_projecttype, uap_personnumber,
						uap_majornumber, uap_projectnumber, uap_awardgrade,
						uap_awardtype, uap_awarddate, uap_serialnumber,
						uap_deadline, uap_college, uap_comments, isnull);
				
				undergraduateActivityProjectList.add(uap);
			}
			return undergraduateActivityProjectList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
	}
}
