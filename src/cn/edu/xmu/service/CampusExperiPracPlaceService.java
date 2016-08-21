package cn.edu.xmu.service;

import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.CampusExperiPracPlace;

/**
 * 
 * @author xiaoping 附表9 校内实验实习实训场所情况 date 2015-7-20
 *
 */
public interface CampusExperiPracPlaceService
{
	/**
	 * 获取校内实验实习实训场所情况
	 * @param queryParams
	 * @return
	 */
	public List<CampusExperiPracPlace> getCampusExperiPracPlace(Map queryParams);
}
