package cn.edu.xmu.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.StudentCenter;

/**
 * 2-4-2  运动场、学生活动中心
 * @author chunfeng
 *
 */
public interface StudentCenterDao {
	public List<StudentCenter> getStudentCenter(int start, int end,
			String sortStr, String orderStr,Map<String, String> params, Date deadline);
	
	public int getStudentCenterCount(Map queryParams);
	
	boolean batchDelete(String[] scids) throws SQLException;
	
	public int addStudentCenter(StudentCenter studentCenter);
	
	public int alterStudentCenter(Map<String, String> valueMap, String id);
	
	public List<StudentCenter> getAllStudentCenter();
	
	public void deleteAll();
	
	public void deleteByCollegeandDeadline(String college,Date deadline) throws SQLException;
	
}
