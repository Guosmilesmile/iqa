package cn.edu.xmu.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.dao.GovermentalUnitDao;
import cn.edu.xmu.entity.ForeignExchange;
import cn.edu.xmu.entity.GovermentalUnit;
import cn.edu.xmu.entity.SuperMajor;
import cn.edu.xmu.table.ForeignExchangeTable;
import cn.edu.xmu.table.GovermentalUnitTable;
import cn.edu.xmu.table.SuperMajorTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;

public class GovermentalUnitDaoImpl extends BaseDaoImpl<GovermentalUnit> implements GovermentalUnitDao {

	//获得行政单位总数目
	@Override
	public int getGovermentalUnitCount() {
		// TODO Auto-generated method stub
		int count  = 0;
		String sql = "select count(*) from " + GovermentalUnitTable.TABLE_NAME +" where 1 = 1"; 
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		/*for ( Object object : queryParams.keySet()) {
			String key = object.toString();
			String value = queryParams.get(key).toString();
			sql += String.format(" and  %s like  '%s%%' ", key,value);
		}*/
		//sql += String.format(" or %s ='%s'", "college","");
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

	//
	@Override
	public List<GovermentalUnit> getAllGovermentalUnit(int start, int end,
			String sortStr, String orderStr) {
		// TODO Auto-generated method stub
		String sql = " select tmp.* from ( " +
				" select * from "+GovermentalUnitTable.TABLE_NAME +" where 1=1 " ;
		/*for ( Object object : queryParams.keySet()) {
			String key = object.toString();
			String value = queryParams.get(key).toString();
			sql += String.format(" and %s like  '%%%s%%'", key,value);
		}*/
		//sql += String.format(" or %s ='%s'", "college","");
		sql += " order by " + sortStr +" "+orderStr+
				" ) tmp limit "+ start +
				" ," + end;
		
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
		System.out.println(sql);
		pstmt = connection.prepareStatement(sql);
		resultSet = pstmt.executeQuery();
		List<GovermentalUnit> govermentalUnits = new ArrayList<GovermentalUnit>();
		   
		while(resultSet.next()){
			int gu_id = resultSet.getInt(GovermentalUnitTable.GU_ID);
			String gu_name = resultSet.getString(GovermentalUnitTable.GU_NAME);
			String gu_number = resultSet.getString(GovermentalUnitTable.GU_NUMBER);
			String gu_responsibility = resultSet.getString(GovermentalUnitTable.GU_RESPONSIBILITY);
			String respon_person = resultSet.getString(GovermentalUnitTable.GU_RESPONPERSON);
			//String college = resultSet.getString(GovermentalUnitTable.COLLEGE);
			int serialnumber = resultSet.getInt(GovermentalUnitTable.GU_SERIALNUMBER);
			
			GovermentalUnit govermentalUnit = new GovermentalUnit(gu_id,gu_name,gu_number,gu_responsibility,respon_person,serialnumber);
			
			govermentalUnits.add(govermentalUnit);
		}
		return govermentalUnits;
	} catch (SQLException e) {
		e.printStackTrace();
		return null;
	}finally{
		JdbcUtils_DBCP.release(connection, pstmt, resultSet);
	}
	}

	//添加
	@Override
	public int addGovermentalUnit(String name, String number,
			String responsibility, String responperson, int serialnumber) {
		// TODO Auto-generated method stub
        int result = 0;
		
        String sql2 = "update "+ GovermentalUnitTable.TABLE_NAME+" set "+GovermentalUnitTable.GU_SERIALNUMBER+
        		" = "+GovermentalUnitTable.GU_SERIALNUMBER+" +1 where "+GovermentalUnitTable.GU_SERIALNUMBER+">="+serialnumber;
		
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
			JdbcUtils_DBCP.release(connection2, pstmt2, null);
		}
		
		String sql = "insert into "+ GovermentalUnitTable.TABLE_NAME+"("+GovermentalUnitTable.GU_NAME+","+GovermentalUnitTable.GU_NUMBER+","
		        +GovermentalUnitTable.GU_RESPONSIBILITY+","+GovermentalUnitTable.GU_RESPONPERSON+","+GovermentalUnitTable.GU_SERIALNUMBER+
		        ")values(?,?,?,?,?)";
		
		
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		PreparedStatement pstmt = null;
		
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, number);
			pstmt.setString(3, responsibility);
			pstmt.setString(4, responperson);
			pstmt.setInt(5, serialnumber);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			result = -1;
		}finally{
			JdbcUtils_DBCP.release(connection, pstmt, null);
		}
		
		return result;
	}

	//修改
	@Override
	public int alterGovermentalUnit(Map<String, String> valueMap, String id) {
		Map<String,String> condition = new HashMap<String,String>();
		condition.put(GovermentalUnitTable.GU_ID, id);
		int result = updateRecord(GovermentalUnitTable.TABLE_NAME, valueMap, condition);
		return result;
	}

	//批量删除
	@Override
	public boolean batchDelete(String[] guids) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement(); 
		
		for (String guid : guids) {
			String sql = "delete from "+GovermentalUnitTable.TABLE_NAME+ " where "+GovermentalUnitTable.GU_ID + " = '"+ guid+ "'";
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
	public List<GovermentalUnit> findForPage(int start, int end,
			String sortStr, String orderStr, Map queryParams) {
		return null;
	}

	@Override
	public List<GovermentalUnit> getAllGovermentalUnit() {
		String sql =
				" select * from "+GovermentalUnitTable.TABLE_NAME +" where 1=1 " 
				+" order by " + GovermentalUnitTable.GU_ID;
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
		List<GovermentalUnit> govermentalUnitList = new ArrayList<GovermentalUnit>();
		while(resultSet.next()){
			int gu_id = resultSet.getInt(GovermentalUnitTable.GU_ID);
			String gu_name = resultSet.getString(GovermentalUnitTable.GU_NAME);
			String gu_number = resultSet.getString(GovermentalUnitTable.GU_NUMBER);
			String gu_responsibility = resultSet.getString(GovermentalUnitTable.GU_RESPONSIBILITY);
			String gu_responperson = resultSet.getString(GovermentalUnitTable.GU_RESPONPERSON);
			GovermentalUnit govermentalUnit =new GovermentalUnit(gu_id,gu_name,gu_number,gu_responsibility,gu_responperson);
			govermentalUnitList.add(govermentalUnit);
		}
		return govermentalUnitList;
	} catch (SQLException e) {
		e.printStackTrace();
		return null;
	}finally{
		JdbcUtils_DBCP.release(connection, pstmt, resultSet);
	}
	}

	@Override
	public void deleteAll() {
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		Statement stmt = null;
		try {
			stmt = connection.createStatement();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} 
		
	 String sql = "delete from "+GovermentalUnitTable.TABLE_NAME;
			try {
				stmt.executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			JdbcUtils_DBCP.release(connection, stmt, null);
	}
	
	@Override
	public int addGovermentalUnitRecord(GovermentalUnit govermentalUnit) {
		int result = 0;
		String sql = "insert into " + GovermentalUnitTable.TABLE_NAME + "("
				+ GovermentalUnitTable.GU_NAME + "," + GovermentalUnitTable.GU_NUMBER + ","
				+ GovermentalUnitTable.GU_RESPONSIBILITY + "," + GovermentalUnitTable.GU_RESPONPERSON +  ")values(?,?,?,?)";

		System.out.println("添加纪录" + sql);
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		PreparedStatement pstmt = null;

		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, govermentalUnit.getGu_name());
			pstmt.setString(2, govermentalUnit.getGu_number());
			pstmt.setString(3, govermentalUnit.getGu_responsibility());
			pstmt.setString(4, govermentalUnit.getGu_responperson());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			result = -1;
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, null);
		}
		return result;
	}
	

}
