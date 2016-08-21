package cn.edu.xmu.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.xmu.entity.TableName;
import cn.edu.xmu.util.JdbcUtils_DBCP;

/**
 * 获得需填表格名称列表
 * @author chunfeng
 *
 */
public class TableNameDaoImpl {

	public List<TableName> getAllTablename() {
		
	String sql = "SELECT t_tablename from t_tableList";
	Connection connection = null;
	PreparedStatement pstmt = null;
	ResultSet resultSet = null;
	
	List<TableName> tableNames = new ArrayList<TableName>();
	try {
		System.out.println(sql);
		connection = JdbcUtils_DBCP.getConnection();
		pstmt = connection.prepareStatement(sql);
		resultSet = pstmt.executeQuery();

		while (resultSet.next()) {			
			TableName tablename = new TableName(resultSet.getString("t_tablename"));			
			tableNames.add(tablename);
		}

	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		JdbcUtils_DBCP.release(connection, pstmt, resultSet);
	}
	return tableNames;
}
}
