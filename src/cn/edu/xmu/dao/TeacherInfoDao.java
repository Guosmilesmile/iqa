package cn.edu.xmu.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.TeacherInfo;


/**
 * 5-1-1-1 教师资源表
 * @author chunfeng
 *
 */
public interface TeacherInfoDao {
	public List<TeacherInfo> getTeacherInfo(int start, int end,
			String sortStr, String orderStr,Map<String, String> params,Date deadline);
	
	public List<TeacherInfo> getTeacherInfoByCollege(String collegeName);
	
	public int getTeacherInfoCount(Map queryParams);
	
	boolean batchDelete(String[] tiids) throws SQLException;
	
	public int addTeacherInfo(TeacherInfo teacherInfo);
	
	public int alterTeacherInfo(Map<String, String> valueMap, String id);
	
	public List<TeacherInfo> getAllTeacherInfo();
	
	public void deleteAll();
	public boolean deleteByCollegeandDeadline(String college, Date deadline) throws SQLException;

}

