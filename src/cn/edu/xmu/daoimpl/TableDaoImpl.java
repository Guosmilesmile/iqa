package cn.edu.xmu.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import cn.edu.xmu.dao.TableDao;
import cn.edu.xmu.util.JdbcUtils_DBCP;

public class TableDaoImpl implements TableDao{

	@Override
	public ArrayList<String> getAttibutes(String tableName) {
		ArrayList<String> result = new ArrayList<String>();
		 
		String sql="select * from " + tableName + " limit 1"; 	
		//String sql = "select COLUMN_NAME from information_schema.COLUMNS where table_name = "+tableName;
	
		System.out.println("select语句" + sql);

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
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			
			ResultSetMetaData rsmd = resultSet.getMetaData(); 
			//获取字段名 
			int columnCount  = resultSet.getMetaData().getColumnCount();
			for (int i = 0; i < columnCount; i++) {
				//System.out.println("***************"+i);
				String sName = rsmd.getColumnName(i+1); //从1开始
				result.add(sName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			result = null;
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return result;
	}

}
