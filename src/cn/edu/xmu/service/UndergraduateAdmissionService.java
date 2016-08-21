package cn.edu.xmu.service;

import java.util.List;
import java.util.Map;
import cn.edu.xmu.entity.UndergraduateAdmission;

/**
 * 
 * @author xiaoping 附表13 各专业（大类）本科生招生情况 date 2015-7-17
 *
 */
public interface UndergraduateAdmissionService
{
	/**
	 * 获取各专业（大类）本科生招生情况
	 * 
	 * @param size
	 *            要获取的个数，若小于0则获取全部
	 * @param isSortByrate
	 *            是否按照报到率来排序，true为按照报到率排序
	 * @param queryParams
	 *            查询键值对
	 * @return
	 */
	public List<UndergraduateAdmission> getUndergraduateAdmission(int size, boolean isSortByRate, Map queryParams);
}
