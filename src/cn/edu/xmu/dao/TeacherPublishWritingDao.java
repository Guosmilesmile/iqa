package cn.edu.xmu.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.TeacherPublishWriting;


/**
 * 表3-6-5  教师出版著作（自然年）
 * @author chunfeng
 *
 */
public interface TeacherPublishWritingDao {
	public List<TeacherPublishWriting> getTeacherPublishWriting(int start, int end,
			String sortStr, String orderStr,Map<String, String> params,Date deadline);
	
	public int getTeacherPublishWritingCount(Map queryParams);
	
	boolean batchDelete(String[] tpwids) throws SQLException;
	
	public int addTeacherPublishWriting(TeacherPublishWriting teacherPublishWriting);
	
	public int alterTeacherPublishWriting(Map<String, String> valueMap, String id);
	
	public List<TeacherPublishWriting> getAllTeacherPublishWriting();
	
	public void deleteAll();
	public boolean deleteByCollegeandDeadline(String college, Date deadline) throws SQLException;

}

