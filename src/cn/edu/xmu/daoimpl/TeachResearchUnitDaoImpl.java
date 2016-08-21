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

import cn.edu.xmu.dao.TeachResearchUnitDao;
import cn.edu.xmu.entity.GovermentalUnit;
import cn.edu.xmu.entity.TeachResearchUnit;
import cn.edu.xmu.table.GovermentalUnitTable;
import cn.edu.xmu.table.TeachResearchUnitTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;

/**
 * 
 * @author luo
 * 学校教学科研单位 实体类功能--接口实现
 * date 2015-06-29
 */

public class TeachResearchUnitDaoImpl extends BaseDaoImpl<TeachResearchUnit> implements TeachResearchUnitDao{
  
	//获得总数量
	@Override
	public int getTeachResearchUnitCount() {
		int count  = 0;
		String sql = "select count(*) from " + TeachResearchUnitTable.TABLE_NAME +" where 1 = 1"; 
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
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

	//获取数据
	@Override
	public List<TeachResearchUnit> getAllTeachResearchUnit(int start, int end,
			String sortStr, String orderStr) {
		String sql = " select tmp.* from ( " +
				" select * from "+TeachResearchUnitTable.TABLE_NAME +" where 1=1 " ;
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
		List<TeachResearchUnit> teachResearchUnits = new ArrayList<TeachResearchUnit>();
		   
		while(resultSet.next()){
			int tr_id = resultSet.getInt(TeachResearchUnitTable.TR_ID);
			String tr_name = resultSet.getString(TeachResearchUnitTable.TR_NAME);
			String tr_number = resultSet.getString(TeachResearchUnitTable.TR_NUMBER);
			String respon_person = resultSet.getString(TeachResearchUnitTable.TR_RESPONPERSON);
			int serialnumber = resultSet.getInt(TeachResearchUnitTable.TR_SERIALNUMBER);
			
			TeachResearchUnit teachResearchUnit = new TeachResearchUnit(tr_id,tr_name,tr_number,respon_person,serialnumber);
			
			teachResearchUnits.add(teachResearchUnit);
		}
		return teachResearchUnits;
	} catch (SQLException e) {
		e.printStackTrace();
		return null;
	}finally{
		JdbcUtils_DBCP.release(connection, pstmt, resultSet);
	}
	}

	//添加
	@Override
	public int addTeachResearchUnit(String name, String number,
			String responperson, int serialnumber,int isnull) {
        int result = 0;
		
        String sql2 = "update "+ TeachResearchUnitTable.TABLE_NAME+" set "+TeachResearchUnitTable.TR_SERIALNUMBER+
        		" = "+TeachResearchUnitTable.TR_SERIALNUMBER+" +1 where "+TeachResearchUnitTable.TR_SERIALNUMBER+">="+serialnumber;
		
		Connection connection2 = null;
		try {
			connection2 = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e2) {
			e2.printStackTrace();
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
		
		String sql = "insert into "+ TeachResearchUnitTable.TABLE_NAME+"("+TeachResearchUnitTable.TR_NAME+","+TeachResearchUnitTable.TR_NUMBER+","
		        +TeachResearchUnitTable.TR_RESPONPERSON+","+TeachResearchUnitTable.TR_SERIALNUMBER+","+"isnull"+
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
			pstmt.setString(3, responperson);
			pstmt.setInt(4, serialnumber);
			pstmt.setInt(5, isnull);
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
	public int alterTeachResearchUnit(Map<String, String> valueMap, String id) {
		Map<String,String> condition = new HashMap<String,String>();
		condition.put(TeachResearchUnitTable.TR_ID, id);
		int result = updateRecord(TeachResearchUnitTable.TABLE_NAME, valueMap, condition);
		return result;
	}

	//批量删除
	@Override
	public boolean batchDelete(String[] trids) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement(); 
		
		for (String trid : trids) {
			String sql = "delete from "+TeachResearchUnitTable.TABLE_NAME+ " where "+TeachResearchUnitTable.TR_ID + " = '"+ trid+ "'";
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
	public List<TeachResearchUnit> findForPage(int start, int end,
			String sortStr, String orderStr, Map queryParams) {
		return null;
	}

	//得到所有单位
	@Override
	public List<TeachResearchUnit> getAllTeachResearchUnits() {
		String sql =
				" select * from "+TeachResearchUnitTable.TABLE_NAME +" where 1=1 " 
				+" order by " + TeachResearchUnitTable.TR_ID;
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
		List<TeachResearchUnit> teachResearchUnitList = new ArrayList<TeachResearchUnit>();
		while(resultSet.next()){
			int tr_id = resultSet.getInt(TeachResearchUnitTable.TR_ID);
			String tr_name = resultSet.getString(TeachResearchUnitTable.TR_NAME);
			String tr_number = resultSet.getString(TeachResearchUnitTable.TR_NUMBER);
			String tr_responperson = resultSet.getString(TeachResearchUnitTable.TR_RESPONPERSON);
			TeachResearchUnit teachResearchUnit =new TeachResearchUnit(tr_id,tr_name,tr_number,tr_responperson);
			teachResearchUnitList.add(teachResearchUnit);
		}
		return teachResearchUnitList;
	} catch (SQLException e) {
		e.printStackTrace();
		return null;
	}finally{
		JdbcUtils_DBCP.release(connection, pstmt, resultSet);
	}
	}

	//删除全部单位
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
		
	 String sql = "delete from "+TeachResearchUnitTable.TABLE_NAME;
			try {
				stmt.executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			JdbcUtils_DBCP.release(connection, stmt, null);
	}

	//添加一条记录
	@Override
	public int addTeachResearchUnitRecord(TeachResearchUnit teachResearchUnit) {
		int result = 0;
		String sql = "insert into " + TeachResearchUnitTable.TABLE_NAME + "("
				+ TeachResearchUnitTable.TR_NAME + "," + TeachResearchUnitTable.TR_NUMBER + ","
				+ TeachResearchUnitTable.TR_RESPONPERSON + ")values(?,?,?)";
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
			pstmt.setString(1, teachResearchUnit.getTr_name());
			pstmt.setString(2, teachResearchUnit.getTr_number());
			pstmt.setString(3, teachResearchUnit.getTr_responperson());
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
