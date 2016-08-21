package cn.edu.xmu.service;

import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.MajorOverview;

/**
 * 3.3 & 附表8  各专业教师、学生情况概览
 * @author chunfeng 
 *
 */
public interface MajorOverviewService {
	/*
	 * 获得统计数据
	 */
	public List<MajorOverview> getMajorOverview();
	
	/*
	 * 获取降序排列后的从0-10个（即排在最前的size个）
	 */
	public List<MajorOverview> getMaxTenMajorOverview(int size, boolean isSortByRate, List<MajorOverview> moList);
	
}
