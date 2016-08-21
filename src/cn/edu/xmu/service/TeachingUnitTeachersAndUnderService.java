package cn.edu.xmu.service;

import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.DegreeSpot;
import cn.edu.xmu.entity.TeachingUnitDisciplineInfo;
import cn.edu.xmu.entity.TeachingUnitTeachersAndUnder;

public interface TeachingUnitTeachersAndUnderService{

	/**
	 * 获得教学单位教师与学生情况
	 * @param params
	 * @return
	 */
	public List<TeachingUnitTeachersAndUnder> getTeachingUnitTeachersAndUnder(Map params);
}
