package cn.edu.xmu.service;

import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.DegreeSpot;
import cn.edu.xmu.entity.LabStuffInfo;
import cn.edu.xmu.entity.TeacherStructure;
/**
 * 
 * @author zsj
 *
 */
public interface LabStuffInfoService{

	/**
	 * 获得学校实验系列人员结构信息
	 * @param params
	 * @return
	 */
	public List<LabStuffInfo> getLabStuffInfos(Map params);
}
