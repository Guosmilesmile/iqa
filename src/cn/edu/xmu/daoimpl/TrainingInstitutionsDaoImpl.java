package cn.edu.xmu.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.dao.TrainingInstitutionsDao;
import cn.edu.xmu.entity.TrainingInstitutions;
import cn.edu.xmu.table.TrainingInstitutionsTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;

/**
 * 
 * @author Gy 附表3-5-1-2教师教学发展机构培训情况（学年）
 */

public class TrainingInstitutionsDaoImpl extends
		BaseDaoImpl<TrainingInstitutions> implements TrainingInstitutionsDao {

	@Override
	public int addRecord(TrainingInstitutions ti) {

		int result = 0;

		String t_sql = "update " + TrainingInstitutionsTable.TABLE_NAME
				+ " set " + TrainingInstitutionsTable.TI_SERIALNUMBER + " = "
				+ TrainingInstitutionsTable.TI_SERIALNUMBER + " +1 where "
				+ TrainingInstitutionsTable.TI_SERIALNUMBER + ">=?";

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
			t_pstmt.setInt(1, ti.getTi_serialnumber());

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
		String sql = "insert into " + TrainingInstitutionsTable.TABLE_NAME
				+ "(" + TrainingInstitutionsTable.TI_NAME + ","
				+ TrainingInstitutionsTable.TI_DEPAERTMENTNUMBER + ","
				+ TrainingInstitutionsTable.TI_DEPAERTMENTNAME + ","
				+ TrainingInstitutionsTable.TI_PROJECTNAME + ","
				+ TrainingInstitutionsTable.TI_OBJECT + ","
				+ TrainingInstitutionsTable.TI_PEOPLECOUNT + ","
				+ TrainingInstitutionsTable.TI_TIME + ","
				+ TrainingInstitutionsTable.TI_SERIALNUMBER + ","
				+ TrainingInstitutionsTable.TI_COLLEGE + ","
				+ TrainingInstitutionsTable.TI_COMMENTS+",isnull"
				+ ")values(?,?,?,?,?,?,?,?,?,'',?)";

		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, ti.getTi_name());
			pstmt.setString(2, ti.getTi_departmentnumber());
			pstmt.setString(3, ti.getTi_departmentname());
			pstmt.setString(4, ti.getTi_projectname());
			pstmt.setString(5, ti.getTi_object());
			pstmt.setInt(6, ti.getTi_peoplecount());
			pstmt.setDate(7, (java.sql.Date) ti.getTi_time());
			pstmt.setInt(8, ti.getTi_serialnumber());
			pstmt.setString(9, ti.getTi_college());
			pstmt.setInt(10, ti.getIsnull());
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
	public int getTrainingInstitutionsCount(Map queryParams) {

		int count = 0;
		// 获取条件的语句
		String sql = "select count(*) from "
				+ TrainingInstitutionsTable.TABLE_NAME + " where 1 = 1";
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
		sql += String.format(" or %s ='%s'",
				TrainingInstitutionsTable.TI_COLLEGE, "");

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
	public List<TrainingInstitutions> getAllTrainingInstitutions(int start,
			int end, String sortStr, String orderStr, Map queryParams) {

		String sql = " select tmp.* from ( " + " select * from "
				+ TrainingInstitutionsTable.TABLE_NAME + " where 1=1 ";

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

		List<TrainingInstitutions> tis = new ArrayList<TrainingInstitutions>();
		try {

			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {

				int ti_id = resultSet.getInt(TrainingInstitutionsTable.TI_ID);
				String ti_name = resultSet
						.getString(TrainingInstitutionsTable.TI_NAME);
				String ti_departmentnumber = resultSet
						.getString(TrainingInstitutionsTable.TI_DEPAERTMENTNUMBER);
				String ti_departmentname = resultSet
						.getString(TrainingInstitutionsTable.TI_DEPAERTMENTNAME);
				String ti_projectname = resultSet
						.getString(TrainingInstitutionsTable.TI_PROJECTNAME);
				String ti_object = resultSet
						.getString(TrainingInstitutionsTable.TI_OBJECT);
				Date ti_time = resultSet.getDate(TrainingInstitutionsTable.TI_TIME);
				Date temp=null;
				try {
					temp = new SimpleDateFormat("yyyy-MM-dd").parse("1800-01-01");
				} catch (ParseException e) {
					e.printStackTrace();
				}
				if(ti_time.equals(temp))
					ti_time=null;
				int ti_peoplecount = resultSet
						.getInt(TrainingInstitutionsTable.TI_PEOPLECOUNT);
				int ti_serialnumber = resultSet
						.getInt(TrainingInstitutionsTable.TI_SERIALNUMBER);
				Date ti_deadline = resultSet
						.getDate(TrainingInstitutionsTable.TI_DEADLINE);
				String ti_college = resultSet
						.getString(TrainingInstitutionsTable.TI_COLLEGE);
				String ti_comments = resultSet
						.getString(TrainingInstitutionsTable.TI_COMMENTS);
				TrainingInstitutions ti = new TrainingInstitutions(ti_id,
						ti_name, ti_departmentnumber, ti_departmentname,
						ti_projectname, ti_object,ti_time, ti_peoplecount,
						ti_serialnumber, ti_deadline, ti_college, ti_comments);
				tis.add(ti);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return tis;
	}

	@Override
	public int alterTrainingInstitutions(Map<String, String> valueMap, String id) {

		Map<String, String> condition = new HashMap<String, String>();
		condition.put(TrainingInstitutionsTable.TI_ID, id);
		int result = updateRecord(TrainingInstitutionsTable.TABLE_NAME,
				valueMap, condition);
		return result;
	}

	@Override
	public boolean batchDelete(String[] seuids) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();

		for (String seuid : seuids) {
			String sql = "delete from " + TrainingInstitutionsTable.TABLE_NAME
					+ " where " + TrainingInstitutionsTable.TI_ID + " = '"
					+ seuid + "'";
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
	public List<TrainingInstitutions> getAllTrainingInstitutions() {
		String sql = " select * from " + TrainingInstitutionsTable.TABLE_NAME
				+ " where 1=1 " + " order by " + TrainingInstitutionsTable.TI_ID;
		Connection connection = null;

		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		List<TrainingInstitutions> tis = new ArrayList<TrainingInstitutions>();
		try {

			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {

				int ti_id = resultSet.getInt(TrainingInstitutionsTable.TI_ID);
				String ti_name = resultSet
						.getString(TrainingInstitutionsTable.TI_NAME);
				String ti_departmentnumber = resultSet
						.getString(TrainingInstitutionsTable.TI_DEPAERTMENTNUMBER);
				String ti_departmentname = resultSet
						.getString(TrainingInstitutionsTable.TI_DEPAERTMENTNUMBER);
				String ti_projectname = resultSet
						.getString(TrainingInstitutionsTable.TI_PROJECTNAME);
				String ti_object = resultSet
						.getString(TrainingInstitutionsTable.TI_OBJECT);
				Date ti_time = resultSet.getDate(TrainingInstitutionsTable.TI_TIME);
				int ti_peoplecount = resultSet
						.getInt(TrainingInstitutionsTable.TI_PEOPLECOUNT);
				int ti_serialnumber = resultSet
						.getInt(TrainingInstitutionsTable.TI_SERIALNUMBER);
				Date ti_deadline = resultSet
						.getDate(TrainingInstitutionsTable.TI_DEADLINE);
				String ti_college = resultSet
						.getString(TrainingInstitutionsTable.TI_COLLEGE);
				String ti_comments = resultSet
						.getString(TrainingInstitutionsTable.TI_COMMENTS);
				TrainingInstitutions ti = new TrainingInstitutions(ti_id,
						ti_name, ti_departmentnumber, ti_departmentname,
						ti_projectname, ti_object,ti_time, ti_peoplecount,
						ti_serialnumber, ti_deadline, ti_college, ti_comments);
				tis.add(ti);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return tis;
	}
	
	@Override
	public void deleteByCollegeandDeadline(String college, Date deadline)
			throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();
		String sql = "delete from " + TrainingInstitutionsTable.TABLE_NAME
				+ " where " + TrainingInstitutionsTable.TI_COLLEGE + " = '" + college + "'" +" and ti_deadline is null ";
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
