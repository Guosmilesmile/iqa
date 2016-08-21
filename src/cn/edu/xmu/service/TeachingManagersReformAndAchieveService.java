package cn.edu.xmu.service;

import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.DegreeSpot;
import cn.edu.xmu.entity.ProfessorAndAssociateForUnderSub;
import cn.edu.xmu.entity.TeachersReformAndAchieve;
import cn.edu.xmu.entity.TeachersTrainingInfo;
import cn.edu.xmu.entity.TeachingManagersReformAndAchieve;
import cn.edu.xmu.entity.TeachingUnitDisciplineInfo;

public interface TeachingManagersReformAndAchieveService{

	/**
	 * 获得教师教育教学改革与成果情况
	 * @param params
	 * @return
	 */
	public TeachingManagersReformAndAchieve getTeachingManagersReformAndAchieve(Map params);
}
