package cn.edu.xmu.service;

import java.util.Map;

import cn.edu.xmu.entity.ManagementTeamResearch;

public interface ManagementTeamResearchService {
	/**
	 * 获得 教学管理队伍教学研究情况
	 * @param params
	 * @return
	 */
	public ManagementTeamResearch getManagementTeamResearch(Map params);
}
