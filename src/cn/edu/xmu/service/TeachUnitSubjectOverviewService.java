package cn.edu.xmu.service;

import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.TeachUnitSubjectOverview;

public interface TeachUnitSubjectOverviewService {

	public List<TeachUnitSubjectOverview> get(Map<String, String> filter);
	
}
