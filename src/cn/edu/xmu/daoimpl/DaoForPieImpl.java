package cn.edu.xmu.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import cn.edu.xmu.dao.DaoForPie;
import cn.edu.xmu.util.JdbcUtils_DBCP;

public class DaoForPieImpl implements DaoForPie{

	@Override
	public Map<String, Integer> getNumByAttribute(String tableName,String attribute,
			Map<String, String> params) {
		Map<String, Integer> numByAttributeMap = new HashMap<String, Integer>();		
		 
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		String sql = "SELECT "+attribute+", COUNT(*) FROM "+tableName+" where 1=1 "; 
		if (params!=null) {
			for ( Object object : params.keySet()) {
				String key = object.toString();
				String value = params.get(key).toString();
				sql += String.format(" and %s like  '%%%s%%'", key,value);
			}
			sql += String.format(" or %s ='%s'", "college","");
		}
		sql += " GROUP BY "+attribute;
		System.out.println("SQL:" + sql);
		try {
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				numByAttributeMap.put(resultSet.getString(1),resultSet.getInt(2));
			}
			
			//total += resultSet.getRow();
			System.out.println(resultSet.getRow());
		} catch (SQLException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		
		return numByAttributeMap;
	}

}
