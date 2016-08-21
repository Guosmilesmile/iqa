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

import cn.edu.xmu.dao.ManagerInfoDao;
import cn.edu.xmu.dao.ManagerInfoDaoBack;
import cn.edu.xmu.entity.ManagerInfo;
import cn.edu.xmu.entity.ManagerInfoBack;
import cn.edu.xmu.table.ManagerInfoTable;
import cn.edu.xmu.table.ManagerInfoTableBack;
import cn.edu.xmu.util.JdbcUtils_DBCP;

//chunfeng
public class ManagerInfoDaoImplBack extends BaseDaoImpl<ManagerInfoBack> implements ManagerInfoDaoBack{

	@Override
	public int getManagerInfoCount() {
		// TODO Auto-generated method stub
		int count  = 0;
		String sql = "select count(*) from " + ManagerInfoTable.TABLE_NAME +" where 1 = 1"; 
		Connection connection = null;
		try
		{
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1)
		{
			e1.printStackTrace();
			return -1;
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
		}finally{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		System.err.println(count);
		return count;
	}

	@Override
	public List<ManagerInfoBack> getManagerInfo(int start, int end, String sortStr,
			String orderStr) {
		// TODO Auto-generated method stub
		String sql =
				" select * from "+ManagerInfoTable.TABLE_NAME +" where 1=1 " 
				+" order by " + ManagerInfoTable.MI_SERIALNUMBER;
		Connection connection = null;
		try
		{
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1)
		{
			e1.printStackTrace();
			return null;
		}
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		try {
			System.out.println(sql);
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			List<ManagerInfoBack> managerInfos = new ArrayList<ManagerInfoBack>();
			   
			while(resultSet.next()){
				int ti_id = resultSet.getInt(ManagerInfoTableBack.MI_ID);
				String ti_name = resultSet.getString(ManagerInfoTableBack.MI_NAME); //姓名
			    String ti_teacherid = resultSet.getString(ManagerInfoTableBack.MI_MANAGERID);  //工号
			    String ti_sex = resultSet.getString(ManagerInfoTableBack.MI_SEX); //性别
			    String ti_birthday = resultSet.getString(ManagerInfoTableBack.MI_BIRTHDAY);  //出生年月
			    Date ti_inschooldate = resultSet.getDate(ManagerInfoTableBack.MI_INSCHOOLDATE);  //入校时间
			    String ti_class = resultSet.getString(ManagerInfoTableBack.MI_CLASS); //师资类别
			    String ti_unitnum = resultSet.getString(ManagerInfoTableBack.MI_UNITNUM);  //单位号
			    String ti_unitname = resultSet.getString(ManagerInfoTableBack.MI_UNITNAME);  //单位名称
			    String ti_edubackground = resultSet.getString(ManagerInfoTableBack.MI_EDUBACKGROUND);  //学历
			    String ti_highestoffering = resultSet.getString(ManagerInfoTableBack.MI_HIGHESTOFFERING);  //最高学位
			    String ti_professiontitle = resultSet.getString(ManagerInfoTableBack.MI_PROFESSIONTITLE);  //专业技术职称
			    String ti_duty = resultSet.getString(ManagerInfoTableBack.MI_DUTY);
			    int ti_serialnumber = resultSet.getInt(ManagerInfoTable.MI_SERIALNUMBER);
			    
				ManagerInfoBack managerInfoback = new ManagerInfoBack(ti_id, ti_name, ti_teacherid, ti_sex, ti_birthday, ti_inschooldate,
						                            ti_class, ti_unitnum, ti_unitname, ti_edubackground, ti_highestoffering,
						                            ti_professiontitle, ti_duty,ti_serialnumber);
				
				managerInfos.add(managerInfoback);
			}
			return managerInfos;
		   } catch (SQLException e) {
			e.printStackTrace();
			return null;
		   }finally{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		   }
	}

	@Override
	public int addManagerInfo(Map<String, String> valueMap) {
		// TODO Auto-generated method stub
		 int result = 0;
			
	        String sql2 = "update "+ ManagerInfoTable.TABLE_NAME+" set "+ManagerInfoTable.MI_SERIALNUMBER+" = "+ManagerInfoTable.MI_SERIALNUMBER+
	        		      " +1 where "+ManagerInfoTable.MI_SERIALNUMBER+">="+valueMap.get("mi_serialnumber").toString();
			
			Connection connection2 = null;
			try
			{
				connection2 = JdbcUtils_DBCP.getConnection();
			} catch (Exception e)
			{
				e.printStackTrace();
				return 0;
			}
			PreparedStatement pstmt2 = null;
			try {
				pstmt2 = connection2.prepareStatement(sql2);
				result = pstmt2.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				return 0;
			} finally {
				JdbcUtils_DBCP.release(connection2, pstmt2, null);
			}
			
			String sql = "insert into "+ ManagerInfoTable.TABLE_NAME+"(";
			
			String sql1 = "values(";
			for (Object object : valueMap.keySet()) {
				String key = object.toString();			
				sql += String.format(" %s ,", key);
				sql1 += "?,";
			}
			sql = sql.substring(0,sql.length()-1);
			sql1 = sql1.substring(0,sql1.length()-1);		
			sql += ")"+sql1+")";
			System.out.println(sql);
			
			Connection connection = null;
			try
			{
				connection = JdbcUtils_DBCP.getConnection();
			} catch (SQLException e1)
			{
				e1.printStackTrace();
				return -1;
			}
			PreparedStatement pstmt = null;
			
			try {
				pstmt = connection.prepareStatement(sql);
				
				int i=1;
				for (Object object : valueMap.keySet()) {
					  String key = object.toString();
					  String value = valueMap.get(key).toString();				
					  pstmt.setString(i++, value);				
				}
										
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				result = -1;
			}finally{
				JdbcUtils_DBCP.release(connection, pstmt, null);
			}
			
			return result;
	}

	@Override
	public int alterManagerInfo(Map<String, String> valueMap, String id) {
		// TODO Auto-generated method stub
		Map<String,String> condition = new HashMap<String,String>();
		condition.put(ManagerInfoTable.MI_ID, id);
		int result = updateRecord(ManagerInfoTable.TABLE_NAME, valueMap, condition);
		return result;
	}

	@Override
	public boolean batchDelete(String[] miids) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		try
		{
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1)
		{
			e1.printStackTrace();
			return false;
		}
		Statement stmt = connection.createStatement(); 
		
		for (String miid : miids) {
			String sql = "delete from "+ManagerInfoTable.TABLE_NAME+ " where "+ManagerInfoTable.MI_ID + " = '"+ miid+ "'";
			try {
				stmt.executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
//		stmt.close();
//		connection.close();
		JdbcUtils_DBCP.release(connection, stmt, null);
		return true;
	}

}
