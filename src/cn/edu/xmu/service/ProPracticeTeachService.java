package cn.edu.xmu.service;

import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.ProPracticeTeach;
/**
 * 
 * @author xiaoping 附表10 各专业实践教学情况 date 2015-8-3
 *
 */
public interface ProPracticeTeachService
{
	/**
	 * 根据条件获取
	 * @param isGetByPracticeRate 是否获取实践教学学分占总学分前五以及后五的教学情况
	 * @param queryParams 筛选条件
	 * @return
	 */
	public List<ProPracticeTeach> getProPracticeTeach(boolean isGetByPracticeRate, Map queryParams);
}
