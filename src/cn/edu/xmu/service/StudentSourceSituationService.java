package cn.edu.xmu.service;

import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.AdmissionCriteriaAndNumber;

public interface StudentSourceSituationService {

	@SuppressWarnings("rawtypes")
	List<AdmissionCriteriaAndNumber> getStudentSourceSituation(Map params);

}
