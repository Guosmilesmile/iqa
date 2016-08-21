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

import cn.edu.xmu.dao.PublishProductDao;
import cn.edu.xmu.entity.PublishProduct;
import cn.edu.xmu.table.PublishProductTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;

/**
 * 附表6-2-1-5 2014-2015学年本科生发表作品情况（非学术论文）
 * 
 * @author Gy
 */

public class PublishProductDaoImpl extends BaseDaoImpl<PublishProduct>
		implements PublishProductDao {

	@Override
	public int addRecord(PublishProduct pp) {

		int result = 0;

		String t_sql = "update " + PublishProductTable.TABLE_NAME + " set "
				+ PublishProductTable.PP_SERIALNUMBER + " = "
				+ PublishProductTable.PP_SERIALNUMBER + " +1 where "
				+ PublishProductTable.PP_SERIALNUMBER + ">=?";

		Connection connection2 = null;
		try {
			// 连接池取得对象连接
			connection2 = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		PreparedStatement t_pstmt = null;
		try {
			// 获取操作对象
			t_pstmt = connection2.prepareStatement(t_sql);
			t_pstmt.setInt(1, pp.getPp_serialnumber());

			// 执行插入操作并更新
			result = t_pstmt.executeUpdate();

		} catch (SQLException e) {
			// 事务回滚，不做插入操作
			e.printStackTrace();
			return 0;
		} finally {
			JdbcUtils_DBCP.release(connection2, t_pstmt, null);
		}

		// 执行插入操作的语句
		String sql = "insert into " + PublishProductTable.TABLE_NAME + "("
				+ PublishProductTable.PP_COLLEGES + ","
				+ PublishProductTable.PP_PRODUCTNAME + ","
				+ PublishProductTable.PP_GRADE + ","
				+ PublishProductTable.PP_NAME + ","
				+ PublishProductTable.PP_MAJOR + ","
				+ PublishProductTable.PP_PUBLICATION + ","
				+ PublishProductTable.PP_TIME + ","
				+ PublishProductTable.PP_REMARK + ","
				+ PublishProductTable.PP_SERIALNUMBER + ","
				+ PublishProductTable.PP_COLLEGE + ","
				+ PublishProductTable.PP_COMMENTS + ",isnull"
				+ ")values(?,?,?,?,?,?,?,?,?,?,?,?)";

		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, pp.getPp_colleges());
			pstmt.setString(2, pp.getPp_productname());
			pstmt.setString(3, pp.getPp_grade());
			pstmt.setString(4, pp.getPp_name());
			pstmt.setString(5, pp.getPp_major());
			pstmt.setString(6, pp.getPp_publication());
			pstmt.setDate(7, pp.getPp_time());
			pstmt.setString(8, pp.getPp_remark());
			pstmt.setInt(9, pp.getPp_serialnumber());
			pstmt.setString(10, pp.getPp_college());
			pstmt.setString(11, pp.getPp_comments());
			pstmt.setInt(12, pp.getIsnull());
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			// 事务回滚。不做插入操作
			e.printStackTrace();
			result = -1;

		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, null);
		}
		return result;

	}

	@Override
	public int getPublishProductCount(Map queryParams) {

		int count = 0;
		// 获取条件的语句
		String sql = "select count(*) from " + PublishProductTable.TABLE_NAME
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
			sql += String.format(" and  %s like  '%s%%' ", key, value);
		}
		sql += String
				.format(" or %s ='%s'", PublishProductTable.PP_COLLEGE, "");

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
	public List<PublishProduct> getAllPublishProduct(int start, int end,
			String sortStr, String orderStr, Map queryParams) {

		String sql = " select tmp.* from ( " + " select * from "
				+ PublishProductTable.TABLE_NAME + " where 1=1 ";

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

		List<PublishProduct> aps = new ArrayList<PublishProduct>();
		try {

			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			Date temp = Date.valueOf("1800-1-1");
			while (resultSet.next()) {
				int pp_id = resultSet.getInt(PublishProductTable.PP_ID);

				String pp_colleges = resultSet
						.getString(PublishProductTable.PP_COLLEGES);
				String pp_productname = resultSet
						.getString(PublishProductTable.PP_PRODUCTNAME);
				String pp_grade = resultSet
						.getString(PublishProductTable.PP_GRADE);
				String pp_name = resultSet
						.getString(PublishProductTable.PP_NAME);
				String pp_major = resultSet
						.getString(PublishProductTable.PP_MAJOR);
				String pp_publication = resultSet
						.getString(PublishProductTable.PP_PUBLICATION);
				Date pp_time = resultSet.getDate(PublishProductTable.PP_TIME);
				if(temp.equals(pp_time))
					pp_time = null;
				String pp_remark = resultSet
						.getString(PublishProductTable.PP_REMARK);
				int pp_serialnumber = resultSet
						.getInt(PublishProductTable.PP_SERIALNUMBER);
				String pp_college = resultSet
						.getString(PublishProductTable.PP_COLLEGE);
				String pp_comments = resultSet
						.getString(PublishProductTable.PP_COMMENTS);
				Date pp_deadline = resultSet
						.getDate(PublishProductTable.PP_DEADLINE);
				if (null == pp_time
						|| pp_time.equals(Date.valueOf("1800-01-01")))
					pp_time = null;
				PublishProduct ap = new PublishProduct(pp_id, pp_colleges,
						pp_productname, pp_grade, pp_name, pp_major,
						pp_publication, pp_time, pp_remark, pp_serialnumber,
						pp_deadline, pp_college, pp_comments);
				aps.add(ap);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return aps;
	}

	@Override
	public List<PublishProduct> getAllPublishProduct() {

		String sql = " select * from " + PublishProductTable.TABLE_NAME
				+ " where 1=1 " + " order by " + PublishProductTable.PP_ID;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		List<PublishProduct> aps = new ArrayList<PublishProduct>();
		try {
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			Date temp = Date.valueOf("1800-1-1");
			while (resultSet.next()) {
				int pp_id = resultSet.getInt(PublishProductTable.PP_ID);

				String pp_colleges = resultSet
						.getString(PublishProductTable.PP_COLLEGES);
				String pp_productname = resultSet
						.getString(PublishProductTable.PP_PRODUCTNAME);
				String pp_grade = resultSet
						.getString(PublishProductTable.PP_GRADE);
				String pp_name = resultSet
						.getString(PublishProductTable.PP_NAME);
				String pp_major = resultSet
						.getString(PublishProductTable.PP_MAJOR);
				String pp_publication = resultSet
						.getString(PublishProductTable.PP_PUBLICATION);
				Date pp_time = resultSet.getDate(PublishProductTable.PP_TIME);
				if(temp.equals(pp_time))
					pp_time = null;
				String pp_remark = resultSet
						.getString(PublishProductTable.PP_REMARK);
				int pp_serialnumber = resultSet
						.getInt(PublishProductTable.PP_SERIALNUMBER);
				String pp_college = resultSet
						.getString(PublishProductTable.PP_COLLEGE);
				String pp_comments = resultSet
						.getString(PublishProductTable.PP_COMMENTS);
				if (null == pp_time
						|| pp_time.equals(Date.valueOf("1800-01-01")))
					pp_time = null;
				PublishProduct ap = new PublishProduct(pp_id, pp_colleges,
						pp_productname, pp_grade, pp_name, pp_major,
						pp_publication, pp_time, pp_remark, pp_serialnumber,
						pp_college, pp_comments);
				aps.add(ap);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return aps;
	}

	@Override
	public int alterPublishProduct(Map<String, String> valueMap, String id) {

		Map<String, String> condition = new HashMap<String, String>();
		condition.put(PublishProductTable.PP_ID, id);
		int result = updateRecord(PublishProductTable.TABLE_NAME, valueMap,
				condition);
		return result;
	}

	@Override
	public boolean batchDelete(String[] seuids) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();

		for (String seuid : seuids) {
			String sql = "delete from " + PublishProductTable.TABLE_NAME
					+ " where " + PublishProductTable.PP_ID + " = '" + seuid
					+ "'";
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
	public void deleteByCollegeandDeadline(String college, Date deadline)
			throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();
		String sql = "delete from " + PublishProductTable.TABLE_NAME
				+ " where " + PublishProductTable.PP_COLLEGE + " = '" + college
				+ "'" + " and pp_deadline is null ";
		System.out.println(sql);
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, stmt, null);
		}

	}

}
