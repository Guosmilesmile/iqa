package cn.edu.xmu.service;

import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.StartClassInfo;

/**
 * 3.8 全校课程开设情况
 * @author yue
 *
 */
public interface StartClassInfoService {
	/**
	 * 获得全校课程开设情况
	 * @param params
	 * @return
	 */
	public List<StartClassInfo> getStartClassInfo(Map params);

}
