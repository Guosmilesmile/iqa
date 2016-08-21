package cn.edu.xmu.service;

import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.DegreeSpot;
import cn.edu.xmu.entity.GraduatesEmploymentsDistribution;
import cn.edu.xmu.entity.TeacherStructure;
/**
 * 
 * @author zsj
 *
 */
public interface GraduatesEmploymentsDistributionService{

	/**
	 * 获得 毕业生就业去向分布情况
	 * @param params
	 * @return
	 */
	public List<GraduatesEmploymentsDistribution> getGraduatesEmploymentsDistributions(Map params);
}
