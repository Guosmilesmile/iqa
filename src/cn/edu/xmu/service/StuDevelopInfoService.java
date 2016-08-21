package cn.edu.xmu.service;

import java.util.Map;

import cn.edu.xmu.entity.StuDevelopInfo;

public interface StuDevelopInfoService {

	/**
	 * 获得学生发展情况
	 * @param params
	 * @return
	 */
	public StuDevelopInfo getStuDevelopInfo(Map params);
}
