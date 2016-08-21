package cn.edu.xmu.service;

import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.ManagerStrcture;

public interface ManagerStructureService {
	public List<ManagerStrcture> get(Map<String, String> filter);
}
