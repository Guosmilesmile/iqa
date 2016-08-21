package cn.edu.xmu.service;

import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.TeachingUnitsLeader;

public interface TeachingUnitsLeaderService {
	@SuppressWarnings("rawtypes")
	public List<TeachingUnitsLeader> getTeachingUnitsLeader(Map params);
}
