package cn.edu.xmu.service;

import java.util.Map;

import cn.edu.xmu.entity.SchoolCultureSituation;

public interface SchoolCultureSituationService {
	/**
	 * 获得校园文化活动情况
	 * @param params
	 * @return
	 */
	public SchoolCultureSituation getSchoolCultureSituation(Map params);

}
