package cn.edu.xmu.service;

import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.GraduationSituationInfo;

public interface GraduationSituationInfoService {
	/**
	 * 获得各专业毕业生情况
	 * @param params
	 * @return
	 */
	public List<GraduationSituationInfo> getGraduationSituationInfo(Map params);

}
