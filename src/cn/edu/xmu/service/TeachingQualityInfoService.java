package cn.edu.xmu.service;

import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.DegreeSpot;
import cn.edu.xmu.entity.TeachingQualityInfo;

public interface TeachingQualityInfoService{

	/**
	 * @param params
	 * @return
	 */
	public List<TeachingQualityInfo> getTeachingQualityInfos(Map params);
}
