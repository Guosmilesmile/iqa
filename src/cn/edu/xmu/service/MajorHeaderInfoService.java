package cn.edu.xmu.service;

import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.DegreeSpot;
import cn.edu.xmu.entity.MajorHeaderInfo;
import cn.edu.xmu.entity.ProfessorAndAssociateForUnderSub;
import cn.edu.xmu.entity.TeachersReformAndAchieve;
import cn.edu.xmu.entity.TeachersTrainingInfo;
import cn.edu.xmu.entity.TeachingUnitDisciplineInfo;

public interface MajorHeaderInfoService{

	/**
	 * 获得专业带头人信息
	 * @param params
	 * @return
	 */
	public List<MajorHeaderInfo> getMajorHeaderInfos(Map params);
}
