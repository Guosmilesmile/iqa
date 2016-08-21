package cn.edu.xmu.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.dao.CourseBuildInfoDao;
import cn.edu.xmu.daoimpl.CourseBuildInfoDaoImpl;
import cn.edu.xmu.entity.GoodCourseBuild;

public interface GoodCourseBuildService {
	public List<GoodCourseBuild> get(Map<String, String> filter);
	
	
	
}
