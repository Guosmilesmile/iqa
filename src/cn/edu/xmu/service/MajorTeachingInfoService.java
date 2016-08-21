package cn.edu.xmu.service;

import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.MajorTeachingInfo;

public interface MajorTeachingInfoService {

	/**
	 * 获得各专业教学情况
	 * @param params
	 * @return
	 */
	public List<MajorTeachingInfo> getMajorTeachingInfo(Map params);
}
