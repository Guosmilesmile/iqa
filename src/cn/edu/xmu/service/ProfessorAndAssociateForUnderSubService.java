package cn.edu.xmu.service;

import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.DegreeSpot;
import cn.edu.xmu.entity.ProfessorAndAssociateForUnderSub;
import cn.edu.xmu.entity.TeachingUnitDisciplineInfo;

public interface ProfessorAndAssociateForUnderSubService{

	/**
	 * 获得开课统计信息(数量和比例)
	 * @param params
	 * @return
	 */
	public List<ProfessorAndAssociateForUnderSub> getProfessorAndAssociateForUnderSub(Map params);
}
