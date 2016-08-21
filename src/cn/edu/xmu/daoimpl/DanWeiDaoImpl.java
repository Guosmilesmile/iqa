package cn.edu.xmu.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.xmu.entity.DanWei;
import cn.edu.xmu.servlet.AddClassCultureServlet;
import cn.edu.xmu.util.JdbcUtils_DBCP;


/**
 * 
 * @author Lee
 * 
 * date 2015-07-13
 */

public class DanWeiDaoImpl{


	public List<DanWei> getAllDanWei() {
		String sql1 = "  select seu_departmentname as danweiname,seu_departmentnumber as danweinumber "
				+ "from seu_schoolexecutiveunit union select ts_name,ts_number from ts_teachscientific;" ;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		List<DanWei> dws = new ArrayList<DanWei>();
		try {
			
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql1);
			resultSet = pstmt.executeQuery();

			//dws.add(new DanWei(" #000"));
			while (resultSet.next()) {
				String danweiname = resultSet.getString("danweiname");
				String danweinumber = resultSet.getString("danweinumber");
				DanWei dw = new DanWei(danweiname+"#"+danweinumber);
				dws.add(dw);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return dws;
	}
	
}
