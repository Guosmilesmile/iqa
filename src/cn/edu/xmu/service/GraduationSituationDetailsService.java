package cn.edu.xmu.service;

import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.GraduationSituationDetails;

public interface GraduationSituationDetailsService {

	/**
	 * 获得各专业毕业生情况
	 * @param params
	 * @return
	 */
	public List<GraduationSituationDetails> getGraduationSituationDetails(Map params);
}
