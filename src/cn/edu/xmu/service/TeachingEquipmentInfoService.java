package cn.edu.xmu.service;

import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.TeachingEquipmentInfo;

/**
 * 3.2.2教学、科研仪器设备情况
 * @author yue
 *
 */
public interface TeachingEquipmentInfoService {

	/**
	 * 获取教学、科研仪器设备情况
	 * @param params
	 * @return
	 */
	public List<TeachingEquipmentInfo> getTeachingEquipmentInfo(Map params);
}
