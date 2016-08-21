package cn.edu.xmu.service;
/**
 * @author zshbleaker
 *  3.2.2 教学科研仪器设备情况
 */

import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.TeachingResearchTools;

public interface TeachingResearchToolsService {

	public List<TeachingResearchTools> get(Map<String, String> filter);
}
