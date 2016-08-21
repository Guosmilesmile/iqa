package cn.edu.xmu.service;

import cn.edu.xmu.entity.ProfessionLayout;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author xiaoping 1.5 专业布局概览 date 2015-8-5
 *
 */
public interface ProfessionLayoutService {

	public List<ProfessionLayout> get(Map<String, String> filter);
	
	
}
