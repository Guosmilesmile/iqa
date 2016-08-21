package cn.edu.xmu.service;

import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.DegreeSpot;
import cn.edu.xmu.entity.TeachingUnitDisciplineInfo;

public interface TeachingUnitDisciplineInfoService{

	/**
	 * 获得教学单位学科专业统计信息
	 * @param params
	 * @return
	 */
	public List<TeachingUnitDisciplineInfo> getTeachingUnitDisciplineInfos(Map params);
}
