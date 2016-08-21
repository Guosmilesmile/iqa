package cn.edu.xmu.service;

/**
 * 
 * @author zshbleaker
 * 3.3 专业情况概览
 *
 */

import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.ProfessionOverview;

public interface ProfessionOverviewService {

	public List<ProfessionOverview> get(Map<String, String> filter);
}
