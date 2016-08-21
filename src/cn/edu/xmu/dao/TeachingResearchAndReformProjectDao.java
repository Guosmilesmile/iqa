package cn.edu.xmu.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.TeachingResearchAndReformProject;

/**
 * 7-3-1  教育教学研究与改革项目
 * @author chunfeng
 *
 */
public interface TeachingResearchAndReformProjectDao extends BaseDao<TeachingResearchAndReformProject>{
	public List<TeachingResearchAndReformProject> getTeachingResearchAndReformProject(int start, int end,
			String sortStr, String orderStr,Map<String, String> params,Date deadline);
	
	/*相关管理人员相关的 zsj*/
	public List<TeachingResearchAndReformProject> getTeachingResearchAndReformProjectInManagerInfo(int start, int end,
			String sortStr, String orderStr,Map<String, String> params);
	
	public int getTeachingResearchAndReformProjectCount(Map queryParams);
	
	/*相关管理人员相关的 zsj*/
	public int getTeachingResearchAndReformProjectCountInManagerInfo(Map queryParams);
	
	boolean batchDelete(String[] trarpids) throws SQLException;
	
	public int addTeachingResearchAndReformProject(TeachingResearchAndReformProject teachingResearchAndReformProject);
	
	public int alterTeachingResearchAndReformProject(Map<String, String> valueMap, String id);
	
	public List<TeachingResearchAndReformProject> getAllTeachingResearchAndReformProject();
	
	public void deleteAll();
	
	public void deleteByCollegeandDeadline(String college,Date deadline) throws SQLException;
	
	public float getSumByParam(String param);

}
