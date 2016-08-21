package cn.edu.xmu.service;

import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.FullTimeTeacher;

/**
 * 附表3 各教学单位专任教师结构
 * @author yue
 *
 */
public interface FullTimeTeacherService {

	/**
	 * 获得各教学单位专任教师结构
	 * @param params
	 * @return
	 */
	public List<FullTimeTeacher> getFullTimeTeacher(Map params);
}
