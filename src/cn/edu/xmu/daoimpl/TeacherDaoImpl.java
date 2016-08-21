package cn.edu.xmu.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.xmu.entity.Teacher;
import cn.edu.xmu.util.JdbcUtils_DBCP;


/**
 * 
 * @author Lee
 * 
 * date 2015-07-13
 */

public class TeacherDaoImpl{


	public List<Teacher> getAllTeacher() {
		String sql1 = "  select ftti_name as teachername,ftti_worknumber as teachernumber from ftti_fulltimeteacherinfo "
				+ "union select et_name,et_worknumber from et_externalteacher "
				+ "union select oti_name,oti_worknumber from oti_otherteacherinfo" ;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		List<Teacher> teachers = new ArrayList<Teacher>();
		try {
			
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql1);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				String teachername = resultSet.getString("teachername");
				String teachernumber = resultSet.getString("teachernumber");
				Teacher tea = new Teacher(teachername+"#"+teachernumber);
				teachers.add(tea);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return teachers;
	}
}
