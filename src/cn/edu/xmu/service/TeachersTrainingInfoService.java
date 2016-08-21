package cn.edu.xmu.service;

import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.DegreeSpot;
import cn.edu.xmu.entity.ProfessorAndAssociateForUnderSub;
import cn.edu.xmu.entity.TeachersTrainingInfo;
import cn.edu.xmu.entity.TeachingUnitDisciplineInfo;

public interface TeachersTrainingInfoService{

	/**
	 * 获得教师进修情况
	 * @param params
	 * @return
	 */
	public TeachersTrainingInfo gettTeachersTrainingInfo(Map params);
}
