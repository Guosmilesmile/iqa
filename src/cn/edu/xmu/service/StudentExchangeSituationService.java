package cn.edu.xmu.service;

import java.util.Map;

import cn.edu.xmu.entity.StudentExchangeSituation;

public interface StudentExchangeSituationService {
	/**
	 * 学生国际交流情况
	 * @param params
	 * @return
	 */
	public StudentExchangeSituation getStudentExchangeSituation(Map params); 

}
