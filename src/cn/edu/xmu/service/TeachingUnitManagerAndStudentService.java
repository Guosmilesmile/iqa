package cn.edu.xmu.service;

import java.util.List;
import java.util.Map;
/**
 * 
 * @author xiaoping 5.4 各教学单位学生管理人员与学生情况 date 2015-7-17
 *
 */
import cn.edu.xmu.entity.TeachingUnitManagerAndStudent;

public interface TeachingUnitManagerAndStudentService
{
	/**
	 * 获取各教学单位学生管理人员与学生情况
	 * @param queryParams
	 * @return
	 */
	public List<TeachingUnitManagerAndStudent> getTeachingUnitManagerAndStudent(Map queryParams);
}
