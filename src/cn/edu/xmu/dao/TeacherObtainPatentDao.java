
package cn.edu.xmu.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.TeacherObtainPatent;


/**
 * 3-6-6  教师获准专利数（自然年）
 * @author chunfeng
 *
 */
public interface TeacherObtainPatentDao {
	public List<TeacherObtainPatent> getTeacherObtainPatent(int start, int end,
			String sortStr, String orderStr,Map<String, String> params,Date deadline);
	
	public int getTeacherObtainPatentCount(Map queryParams);
	
	boolean batchDelete(String[] topids) throws SQLException;
	
	public int addTeacherObtainPatent(TeacherObtainPatent teacherObtainPatent);
	
	public int alterTeacherObtainPatent(Map<String, String> valueMap, String id);
	
	public List<TeacherObtainPatent> getAllTeacherObtainPatent();
	
	public void deleteAll();
	public boolean deleteByCollegeandDeadline(String college, Date deadline) throws SQLException;

}

