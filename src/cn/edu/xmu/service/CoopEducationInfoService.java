package cn.edu.xmu.service;

import java.util.Map;

import cn.edu.xmu.entity.CoopEducationInfo;

/**
 * 3.10 合作办学情况
 * @author yue
 *
 */
public interface CoopEducationInfoService {
	/**
	 * 获取合作办学情况
	 * @param params
	 * @return
	 */
	public CoopEducationInfo getCoopEducationInfo(Map params);
}
