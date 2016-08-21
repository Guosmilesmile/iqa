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

import cn.edu.xmu.dao.MajorRegisterInfoDao;
import cn.edu.xmu.entity.MajorRegisterInfo;
import cn.edu.xmu.table.MajorRegisterInfoTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;

/**
 * 表6-1-6-2 各专业（大类）报到情况（时点）
 * @author yue
 *
 */
public class MajorRegisterInfoDaoImpl extends BaseDaoImpl<MajorRegisterInfo> implements MajorRegisterInfoDao{

	@Override
	public int addMajorRegisterInfoRecord(MajorRegisterInfo mri) {
		int result = 0;
		String t_sql = "update " + MajorRegisterInfoTable.TABLE_NAME + " set "
				+MajorRegisterInfoTable.MRI_SERIALNUMBER + " = "
				+MajorRegisterInfoTable.MRI_SERIALNUMBER + " +1 where "
				+MajorRegisterInfoTable.MRI_SERIALNUMBER + " >=?";
		Connection connection2 = null;
		try{
			//连接池获取对象连接
			connection2 = JdbcUtils_DBCP.getConnection();
			
		}catch(SQLException e1){
			e1.printStackTrace();
		}
		PreparedStatement t_pstmt = null;
		try {
			//获取操作对象
			t_pstmt = connection2.prepareStatement(t_sql);
			t_pstmt.setInt(1, mri.getMri_serialnumber());
			
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
		String sql = "insert into " + MajorRegisterInfoTable.TABLE_NAME + "("
				+ MajorRegisterInfoTable.MRI_MAJORCODE +  ","
				+ MajorRegisterInfoTable.MRI_MAJORNAME + ","
				+ MajorRegisterInfoTable.MRI_REGISTERNUMBER + ","
				+ MajorRegisterInfoTable.MRI_SERIALNUMBER + ","
				+ MajorRegisterInfoTable.MRI_COLLEGE + ","
				+ MajorRegisterInfoTable.MRI_COMMETNS + ","
				+ MajorRegisterInfoTable.ISNULL
				+ ")values(?,?,?,?,?,?,?)";

		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, mri.getMri_majorcode());
			pstmt.setString(2, mri.getMri_majorname());
			pstmt.setInt(3, mri.getMri_registernumber());
			pstmt.setInt(4, mri.getMri_serialnumber());
			pstmt.setString(5, mri.getMri_college());
			pstmt.setString(6, mri.getMri_comments());
			pstmt.setInt(7, mri.getIsnull());
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
	public boolean batchDelete(String[] mriids) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();
		
		for(String mriid:mriids)
		{
			String sql = "delete from " + MajorRegisterInfoTable.TABLE_NAME
					+ " where " + MajorRegisterInfoTable.MRI_ID + " = '" +mriid + "'";
			try{
				stmt.executeUpdate(sql);
			}catch(SQLException e){
				e.printStackTrace();
				return false;
			}
		}
		JdbcUtils_DBCP.release(connection, stmt, null);
		return true;
	}

	@Override
	public int alterMajorRegisterInfo(Map<String, String> valueMap, String id) {
		Map<String, String> condition = new HashMap<String,String>();
		condition.put(MajorRegisterInfoTable.MRI_ID, id);
		int result = updateRecord(MajorRegisterInfoTable.TABLE_NAME, valueMap, condition);
		return result;
	}

	@Override
	public int getMajorRegisterInfoCount(Map queryParams) {
		int count = 0;
		//获取条件的语句
		String sql = "select count(*) from " + MajorRegisterInfoTable.TABLE_NAME
				+" where 1 = 1";
		System.out.println(sql);
		Connection connection = null;
		try{
			connection = JdbcUtils_DBCP.getConnection();
			
		}catch(SQLException e1){
			e1.printStackTrace();
			return -1;
		}
		if (queryParams != null)
		{
			for (Object object : queryParams.keySet())
			{
				String key = object.toString();
				String value = queryParams.get(key).toString();
				sql += String.format(" and  %s like  '%s%%' ", key, value);
			}
			// sql += String.format(" or %s ='%s'", "college","");
		}
		System.out.println(sql);
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
		}finally{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return count;
	}

	@Override
	public List<MajorRegisterInfo> getMajorRegisterInfo(int start, int end, String sortStr, String orderStr,
			Map<String, String> params, Date deadline) {
		String sql = " select tmp.* from ( " + " select * from "
				+ MajorRegisterInfoTable.TABLE_NAME + " where 1=1 ";
		if (deadline != null) {

			sql += String.format("and "+MajorRegisterInfoTable.MRI_DEADLINE+" like  '%s%%' ", deadline);
		}
		if (!(params == null || params.keySet().size() == 0)) {
			for (Object object : params.keySet()) {

				String key = object.toString();
				String value = params.get(key).toString();
				sql += String.format(" and %s like  '%%%s%%' ", key, value);
			}
		}

		sql += " order by " + sortStr + " " + orderStr + " ) tmp limit "
				+ start + " ," + end;

		System.out.println(sql);
		
		List<MajorRegisterInfo> mris = new ArrayList<MajorRegisterInfo>();
		Connection connection = null;
		try
		{
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1)
		{
			e1.printStackTrace();
	
		}
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
				
		try {
			
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int mri_id = resultSet.getInt(MajorRegisterInfoTable.MRI_ID);
				String mri_majorcode = resultSet.getString(MajorRegisterInfoTable.MRI_MAJORCODE);
				String mri_majorname = resultSet.getString(MajorRegisterInfoTable.MRI_MAJORNAME);
				Integer mri_registernumber = resultSet.getInt(MajorRegisterInfoTable.MRI_REGISTERNUMBER);
				if(mri_registernumber == -999)
					mri_registernumber = null;
				int mri_serialnumber = resultSet.getInt(MajorRegisterInfoTable.MRI_SERIALNUMBER);
				String mri_college = resultSet.getString(MajorRegisterInfoTable.MRI_COLLEGE);//填报学院
			    Date mri_deadline = resultSet.getDate(MajorRegisterInfoTable.MRI_DEADLINE);//截止时间    
				String mri_comments = resultSet.getString(MajorRegisterInfoTable.MRI_COMMETNS);
				int isnull = resultSet.getInt(MajorRegisterInfoTable.ISNULL);
				MajorRegisterInfo mri = new MajorRegisterInfo(mri_id, mri_majorcode, mri_majorname,
						mri_registernumber, mri_serialnumber, mri_deadline, mri_college,
						mri_comments,isnull);
				mris.add(mri);
			}
			return mris;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
	}

	@Override
	public void deleteByCollegeandDeadline(String college, Date deadline) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();
		String sql = "delete from " + MajorRegisterInfoTable.TABLE_NAME
				+ " where " + MajorRegisterInfoTable.MRI_COLLEGE + " = '" + college + "'" +" and mri_deadline is null ";
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
	public List<MajorRegisterInfo> getAllMajorRegisterInfo() {
		String sql = " select * from " + MajorRegisterInfoTable.TABLE_NAME
				+ " where 1=1 " + " order by " + MajorRegisterInfoTable.MRI_ID;
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
			List<MajorRegisterInfo> mris = new ArrayList<MajorRegisterInfo>();
			while (resultSet.next()) {
				int mri_id = resultSet.getInt(MajorRegisterInfoTable.MRI_ID);
				String mri_majorcode = resultSet.getString(MajorRegisterInfoTable.MRI_MAJORCODE);
				String mri_majorname = resultSet.getString(MajorRegisterInfoTable.MRI_MAJORNAME);
				Integer mri_registernumber = resultSet.getInt(MajorRegisterInfoTable.MRI_REGISTERNUMBER);
				if(mri_registernumber == -999)
					mri_registernumber = null;
				int mri_serialnumber = resultSet.getInt(MajorRegisterInfoTable.MRI_SERIALNUMBER);
				String mri_college = resultSet.getString(MajorRegisterInfoTable.MRI_COLLEGE);//填报学院
			    Date mri_deadline = resultSet.getDate(MajorRegisterInfoTable.MRI_DEADLINE);//截止时间    
				String mri_comments = resultSet.getString(MajorRegisterInfoTable.MRI_COMMETNS);
				int isnull = resultSet.getInt(MajorRegisterInfoTable.ISNULL);
				MajorRegisterInfo mri = new MajorRegisterInfo(mri_id, mri_majorcode, mri_majorname,
						mri_registernumber, mri_serialnumber, mri_deadline, mri_college,
						mri_comments,isnull);
				mris.add(mri);
			}
			return mris;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
	}

}
