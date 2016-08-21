package cn.edu.xmu.service;

import java.util.List;

import cn.edu.xmu.entity.NewMajorOverview;

/**
 * 3.5 新设专业概览
 * @author chunfeng
 *
 */
public interface NewMajorOverviewService {

	/*
	 * 获得所有新专业
	 */
	public List<NewMajorOverview> getNewMajorOverview();
	
}
