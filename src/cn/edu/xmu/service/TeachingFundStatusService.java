package cn.edu.xmu.service;

import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.TeachingFundStatus;

public interface TeachingFundStatusService {
	public List<TeachingFundStatus> get(Map<String, String> filter);
}
