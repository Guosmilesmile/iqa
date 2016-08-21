package cn.edu.xmu.service;

import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.Partnership;

public interface PartnershipService {
	public List<Partnership> get(Map<String, String> filter);
}
