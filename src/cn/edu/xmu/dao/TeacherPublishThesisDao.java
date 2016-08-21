package cn.edu.xmu.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.TeacherPublishThesis;


/**
 * 表3-6-4  教师发表论文数 (自然年)
 * @author chunfeng
 *
 */
public interface TeacherPublishThesisDao {
	public List<TeacherPublishThesis> getTeacherPublishThesis(int start, int end,
			String sortStr, String orderStr,Map<String, String> params, Date deadline);
	
	public int getTeacherPublishThesisCount(Map queryParams);
	
	boolean batchDelete(String[] tptids) throws SQLException;
	
	public int addTeacherPublishThesis(TeacherPublishThesis teacherPublishThesis);
	
	public int alterTeacherPublishThesis(Map<String, String> valueMap, String id);
	
	public List<TeacherPublishThesis> getAllTeacherPublishThesis();
	
	public void deleteAll();
	public boolean deleteByCollegeandDeadline(String college, Date deadline) throws SQLException;

}

