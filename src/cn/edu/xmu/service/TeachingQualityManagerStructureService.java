package cn.edu.xmu.service;

import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.DegreeSpot;
import cn.edu.xmu.entity.TeacherStructure;
import cn.edu.xmu.entity.TeachingQualityManagerStructure;
/**
 * 
 * @author zsj
 *
 */
public interface TeachingQualityManagerStructureService{

	/**
	 * 获得校级教学质量管理队伍数量及比例
	 * @param params
	 * @return
	 */
	public List<TeachingQualityManagerStructure> getteManagerStructuresOfSchoolQM(Map params);
	
	/**
	 * 获得院系教学质量管理队伍数量及比例
	 * @param params
	 * @return
	 */
	public List<TeachingQualityManagerStructure> getteManagerStructuresOfCollegeQM(Map params);
	
	/**
	 * 获得质量监控人员队伍数量及比例
	 * @param params
	 * @return
	 */
	public List<TeachingQualityManagerStructure> getteManagerStructuresOfQMor(Map params);
	
	/**
	 * 教学质量管理队伍结构
	 * @param params
	 * @return
	 */
	public List<TeachingQualityManagerStructure> getteManagerStructure(Map params);
}
