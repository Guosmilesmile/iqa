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

import cn.edu.xmu.dao.StudentAssociationDao;
import cn.edu.xmu.entity.StudentAssociation;
import cn.edu.xmu.table.StudentAssociationTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;


/**
 * @author zhantu
 * 学生社团（时点）  实体类功能 ——接口实现
 * date 2015-07-07
 */

public class StudentAssociationDaoImpl extends BaseDaoImpl<StudentAssociation>implements StudentAssociationDao{

	@Override
	public int addRecord(StudentAssociation sa) {
		
		int result = 0;

		String t_sql = "update " + StudentAssociationTable.TABLE_NAME + " set "
				+ StudentAssociationTable.SA_SERIALNUMBER + " = "
				+ StudentAssociationTable.SA_SERIALNUMBER + " +1 where "
				+ StudentAssociationTable.SA_SERIALNUMBER + ">=?";

		
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
			t_pstmt.setInt(1, sa.getSa_serialnumber());
			
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
		String sql = "insert into " + StudentAssociationTable.TABLE_NAME + "(";

		Integer temp = null,i = 3;
		temp = sa.getSa_sumcount();
		if(temp!=null)
		{
			i++;
			sql+=(StudentAssociationTable.SA_SUMCOUNT + ",");
		}
			
		temp = sa.getSa_sciencecount();
		if(temp!=null)
		{
			i++;
			sql+=(StudentAssociationTable.SA_SCIENCECOUNT + ",");
		}
		temp = sa.getSa_humanisticcount();
		if(temp!=null)
		{
			i++;
			sql+=(StudentAssociationTable.SA_HUMANISTICCOUNT + ",");
		}
		temp = sa.getSa_sportscount();
		if(temp!=null)
		{
			i++;
			sql+=(StudentAssociationTable.SA_SPORTSCOUNT + ",");
		}
		temp = sa.getSa_literatureartcount();
		if(temp!=null)
		{
			i++;
			sql+=(StudentAssociationTable.SA_LITERATUREARTCOUNT + ",");
		}
		temp = sa.getSa_othercount();
		if(temp!=null)
		{
			i++;
			sql+=(StudentAssociationTable.SA_OTHERCOUNT + ",");
		}
		
		temp = sa.getSa_sumperson();
		if(temp!=null)
		{
			i++;
			sql+=(StudentAssociationTable.SA_SUMPERSON + ",");
		}
		temp = sa.getSa_scienceperson();
		if(temp!=null)
		{
			i++;
			sql+=(StudentAssociationTable.SA_SCIENCEPERSON + ",");
		}
		temp = sa.getSa_humanisticperson();
		if(temp!=null)
		{
			i++;
			sql+=(StudentAssociationTable.SA_HUMANISTICPERSON + ",");
		}
		temp = sa.getSa_sportsperson();
		if(temp!=null)
		{
			i++;
			sql+=(StudentAssociationTable.SA_SPORTSPERSON + ",");
		}
		temp = sa.getSa_literatureartperson();
		if(temp!=null)
		{
			i++;
			sql+=(StudentAssociationTable.SA_LITERATUREARTPERSON + ",");
		}
		temp = sa.getSa_otherperson();
		if(temp!=null)
		{
			i++;
			sql+=(StudentAssociationTable.SA_OTHERPERSON + ",");
		}
		
		sql+=(StudentAssociationTable.SA_SERIALNUMBER + ","
			+ StudentAssociationTable.SA_COLLEGE + ","
			+ StudentAssociationTable.SA_COMMENTS + ","
			+ StudentAssociationTable.ISNULL
			+ ")values(");
		for(int x = 1; x<i;x++)
			sql+="?,";
		sql+="?,?)";
		System.out.println("mysql:"+sql);

		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			
			i = 0;
			temp = sa.getSa_sumcount();
			if(temp!=null)
				pstmt.setInt(++i, temp);
				
			temp = sa.getSa_sciencecount();
			if(temp!=null)
				pstmt.setInt(++i, temp);
			
			temp = sa.getSa_humanisticcount();
			if(temp!=null)
				pstmt.setInt(++i, temp);
			
			temp = sa.getSa_sportscount();
			if(temp!=null)
				pstmt.setInt(++i, temp);

			temp = sa.getSa_literatureartcount();
			if(temp!=null)
				pstmt.setInt(++i, temp);

			temp = sa.getSa_othercount();
			if(temp!=null)
				pstmt.setInt(++i, temp);
			
			temp = sa.getSa_sumperson();
			if(temp!=null)
				pstmt.setInt(++i, temp);

			temp = sa.getSa_scienceperson();
			if(temp!=null)
				pstmt.setInt(++i, temp);

			temp = sa.getSa_humanisticperson();
			if(temp!=null)
				pstmt.setInt(++i, temp);

			temp = sa.getSa_sportsperson();
			if(temp!=null)
				pstmt.setInt(++i, temp);

			temp = sa.getSa_literatureartperson();
			if(temp!=null)
				pstmt.setInt(++i, temp);

			temp = sa.getSa_otherperson();
			if(temp!=null)
				pstmt.setInt(++i, temp);

			
			pstmt.setInt(++i, sa.getSa_serialnumber());
			pstmt.setString(++i, sa.getSa_college());
			pstmt.setString(++i, sa.getSa_comments());
			pstmt.setInt(++i, sa.getIsnull());
			
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
	public int getStudentAssociationCount(Map queryParams) {
		
		int count = 0;
		//获取条件的语句
		String sql = "select count(*) from " + StudentAssociationTable.TABLE_NAME
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
		sql += String.format(" or %s ='%s'", StudentAssociationTable.SA_COLLEGE, "");
		
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
	public List<StudentAssociation> getAllStudentAssociation(int start, int end,
			String sortStr, String orderStr, Map queryParams) {
		
		String sql = " select tmp.* from ( " + " select * from "
				+ StudentAssociationTable.TABLE_NAME + " where 1=1 ";
		
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
		
		List<StudentAssociation> sas = new ArrayList<StudentAssociation>();
		try {
			
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int sa_id = resultSet.getInt(StudentAssociationTable.SA_ID);
				
				Integer sa_sumcount = resultSet.getInt(StudentAssociationTable.SA_SUMCOUNT);
				Integer sa_sciencecount = resultSet.getInt(StudentAssociationTable.SA_SCIENCECOUNT);
				Integer sa_humanisticcount = resultSet.getInt(StudentAssociationTable.SA_HUMANISTICCOUNT);
				Integer sa_sportscount = resultSet.getInt(StudentAssociationTable.SA_SPORTSCOUNT);
				Integer sa_literatureartcount = resultSet.getInt(StudentAssociationTable.SA_LITERATUREARTCOUNT);
				Integer sa_othercount = resultSet.getInt(StudentAssociationTable.SA_OTHERCOUNT);
				
				Integer sa_sumperson = resultSet.getInt(StudentAssociationTable.SA_SUMPERSON);
				Integer sa_scienceperson = resultSet.getInt(StudentAssociationTable.SA_SCIENCEPERSON);
				Integer sa_humanisticperson = resultSet.getInt(StudentAssociationTable.SA_HUMANISTICPERSON);
				Integer sa_sportsperson = resultSet.getInt(StudentAssociationTable.SA_SPORTSPERSON);
				Integer sa_literatureartperson = resultSet.getInt(StudentAssociationTable.SA_LITERATUREARTPERSON);
				Integer sa_otherperson = resultSet.getInt(StudentAssociationTable.SA_OTHERPERSON);
				
				int sa_serialnumber = resultSet.getInt(StudentAssociationTable.SA_SERIALNUMBER);
				Date sa_deadline = resultSet.getDate(StudentAssociationTable.SA_DEADLINE);
				String sa_comments = resultSet.getString(StudentAssociationTable.SA_COMMENTS);
				String sa_college = resultSet.getString(StudentAssociationTable.SA_COLLEGE);
				int isnull = resultSet.getInt(StudentAssociationTable.ISNULL);
				
				StudentAssociation sa = new StudentAssociation(
						sa_id, sa_sumcount, sa_sciencecount,
						sa_humanisticcount, sa_sportscount,
						sa_literatureartcount, sa_othercount, sa_sumperson,
						sa_scienceperson, sa_humanisticperson, sa_sportsperson,
						sa_literatureartperson, sa_otherperson,
						sa_serialnumber, sa_deadline, sa_college,
						sa_comments, isnull);
				
				sas.add(sa);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return sas;
	}

	@Override
	public int alterStudentAssociation(Map<String, String> valueMap, String id) {
		
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(StudentAssociationTable.SA_ID, id);
		int result = updateRecord(StudentAssociationTable.TABLE_NAME, valueMap,
				condition);
		return result;
	}

	@Override
	public boolean batchDelete(String[] saids) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();

		for (String said : saids) {
			String sql = "delete from " + StudentAssociationTable.TABLE_NAME
					+ " where " + StudentAssociationTable.SA_ID + " = '" + said + "'";
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
	public List<StudentAssociation> getStudentAssociation(int start, int end,
			String sortStr, String orderStr, Map<String, String> params,
			Date deadline) {
		String sql = " select tmp.* from ( " + " select * from "
				+ StudentAssociationTable.TABLE_NAME + " where 1=1 ";
		if (deadline != null) {

			sql += String.format("and "+StudentAssociationTable.SA_DEADLINE+" like  '%s%%' ", deadline);
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

		List<StudentAssociation> studentAssociations = new ArrayList<StudentAssociation>();
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
				Integer sa_id = resultSet.getInt(StudentAssociationTable.SA_ID);
				
				Integer sa_sumcount = null;
				if (resultSet.getObject(StudentAssociationTable.SA_SUMCOUNT) != null) {
					sa_sumcount = resultSet.getInt(StudentAssociationTable.SA_SUMCOUNT);
                }
				Integer sa_sciencecount = null;
				if (resultSet.getObject(StudentAssociationTable.SA_SCIENCECOUNT) != null) {
					sa_sciencecount = resultSet.getInt(StudentAssociationTable.SA_SCIENCECOUNT);
                }
				Integer sa_humanisticcount = null;
				if (resultSet.getObject(StudentAssociationTable.SA_HUMANISTICCOUNT) != null) {
					sa_humanisticcount = resultSet.getInt(StudentAssociationTable.SA_HUMANISTICCOUNT);
                }
				Integer sa_sportscount = null;
				if (resultSet.getObject(StudentAssociationTable.SA_SPORTSCOUNT) != null) {
					sa_sportscount = resultSet.getInt(StudentAssociationTable.SA_SPORTSCOUNT);
                }
				Integer sa_literatureartcount = null;
				if (resultSet.getObject(StudentAssociationTable.SA_LITERATUREARTCOUNT) != null) {
					sa_literatureartcount = resultSet.getInt(StudentAssociationTable.SA_LITERATUREARTCOUNT);
                }
				Integer sa_othercount = null;
				if (resultSet.getObject(StudentAssociationTable.SA_OTHERCOUNT) != null) {
					sa_othercount = resultSet.getInt(StudentAssociationTable.SA_OTHERCOUNT);
                }
				
				
				Integer sa_sumperson = null;
				if (resultSet.getObject(StudentAssociationTable.SA_SUMPERSON) != null) {
					sa_sumperson = resultSet.getInt(StudentAssociationTable.SA_SUMPERSON);
                }
				Integer sa_scienceperson = null;
				if (resultSet.getObject(StudentAssociationTable.SA_SCIENCEPERSON) != null) {
					sa_scienceperson = resultSet.getInt(StudentAssociationTable.SA_SCIENCEPERSON);
                }
				Integer sa_humanisticperson = null;
				if (resultSet.getObject(StudentAssociationTable.SA_HUMANISTICPERSON) != null) {
					sa_humanisticperson = resultSet.getInt(StudentAssociationTable.SA_HUMANISTICPERSON);
                }
				Integer sa_sportsperson = null;
				if (resultSet.getObject(StudentAssociationTable.SA_SPORTSPERSON) != null) {
					sa_sportsperson = resultSet.getInt(StudentAssociationTable.SA_SPORTSPERSON);
                }
				Integer sa_literatureartperson = null;
				if (resultSet.getObject(StudentAssociationTable.SA_LITERATUREARTPERSON) != null) {
					sa_literatureartperson = resultSet.getInt(StudentAssociationTable.SA_LITERATUREARTPERSON);
                }
				Integer sa_otherperson = null;
				if (resultSet.getObject(StudentAssociationTable.SA_OTHERPERSON) != null) {
					sa_otherperson = resultSet.getInt(StudentAssociationTable.SA_OTHERPERSON);
                }
				
				int sa_serialnumber = resultSet.getInt(StudentAssociationTable.SA_SERIALNUMBER);
				Date sa_deadline = resultSet.getDate(StudentAssociationTable.SA_DEADLINE);
				String sa_comments = resultSet.getString(StudentAssociationTable.SA_COMMENTS);
				if(sa_comments==null)
					sa_comments="";
				String sa_college = resultSet.getString(StudentAssociationTable.SA_COLLEGE);
				int isnull = resultSet.getInt(StudentAssociationTable.ISNULL);
				StudentAssociation sa = new StudentAssociation(
						sa_id, sa_sumcount, sa_sciencecount,
						sa_humanisticcount, sa_sportscount,
						sa_literatureartcount, sa_othercount, sa_sumperson,
						sa_scienceperson, sa_humanisticperson, sa_sportsperson,
						sa_literatureartperson, sa_otherperson,
						sa_serialnumber, sa_deadline, sa_college,
						sa_comments, isnull);

				studentAssociations.add(sa);
			}
			return studentAssociations;
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
		String sql = "delete from " + StudentAssociationTable.TABLE_NAME
				+ " where " + StudentAssociationTable.SA_COLLEGE + " = '" + college + "'" +" and sa_deadline is null ";
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
	public List<StudentAssociation> getAllStudentAssociation() {
		String sql = " select * from " + StudentAssociationTable.TABLE_NAME
				+ " where 1=1 " + " order by " + StudentAssociationTable.SA_ID;
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
			List<StudentAssociation> studentAssociationList = new ArrayList<StudentAssociation>();
			while (resultSet.next()) {
				Integer sa_id = resultSet.getInt(StudentAssociationTable.SA_ID);
				
				Integer sa_sumcount = resultSet.getInt(StudentAssociationTable.SA_SUMCOUNT);
				Integer sa_sciencecount = resultSet.getInt(StudentAssociationTable.SA_SCIENCECOUNT);
				Integer sa_humanisticcount = resultSet.getInt(StudentAssociationTable.SA_HUMANISTICCOUNT);
				Integer sa_sportscount = resultSet.getInt(StudentAssociationTable.SA_SPORTSCOUNT);
				Integer sa_literatureartcount = resultSet.getInt(StudentAssociationTable.SA_LITERATUREARTCOUNT);
				Integer sa_othercount = resultSet.getInt(StudentAssociationTable.SA_OTHERCOUNT);
				
				Integer sa_sumperson = resultSet.getInt(StudentAssociationTable.SA_SUMPERSON);
				Integer sa_scienceperson = resultSet.getInt(StudentAssociationTable.SA_SCIENCEPERSON);
				Integer sa_humanisticperson = resultSet.getInt(StudentAssociationTable.SA_HUMANISTICPERSON);
				Integer sa_sportsperson = resultSet.getInt(StudentAssociationTable.SA_SPORTSPERSON);
				Integer sa_literatureartperson = resultSet.getInt(StudentAssociationTable.SA_LITERATUREARTPERSON);
				Integer sa_otherperson = resultSet.getInt(StudentAssociationTable.SA_OTHERPERSON);
				
				int sa_serialnumber = resultSet.getInt(StudentAssociationTable.SA_SERIALNUMBER);
				Date sa_deadline = resultSet.getDate(StudentAssociationTable.SA_DEADLINE);
				String sa_comments = resultSet.getString(StudentAssociationTable.SA_COMMENTS);
				String sa_college = resultSet.getString(StudentAssociationTable.SA_COLLEGE);
				int isnull = resultSet.getInt(StudentAssociationTable.ISNULL);
				
				StudentAssociation sa = new StudentAssociation(
						sa_id, sa_sumcount, sa_sciencecount,
						sa_humanisticcount, sa_sportscount,
						sa_literatureartcount, sa_othercount, sa_sumperson,
						sa_scienceperson, sa_humanisticperson, sa_sportsperson,
						sa_literatureartperson, sa_otherperson,
						sa_serialnumber, sa_deadline, sa_college,
						sa_comments, isnull);
				
				studentAssociationList.add(sa);
			}
			return studentAssociationList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
	}
}
