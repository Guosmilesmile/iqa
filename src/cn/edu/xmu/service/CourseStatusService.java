package cn.edu.xmu.service;

import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.CourseStatus;

public interface CourseStatusService {
	public List<CourseStatus> get(Map<String, String> filter);
}
