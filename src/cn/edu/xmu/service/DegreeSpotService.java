package cn.edu.xmu.service;

import java.util.Map;

import cn.edu.xmu.entity.DegreeSpot;

public interface DegreeSpotService{

	/**
	 * 获得学位点概况信息
	 * @param params
	 * @return
	 */
	public DegreeSpot getDegreeSpot(Map params);
}
