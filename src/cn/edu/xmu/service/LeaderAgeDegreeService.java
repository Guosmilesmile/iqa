package cn.edu.xmu.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.record.formula.functions.Int;
import org.w3c.dom.views.DocumentView;

import cn.edu.xmu.entity.LeaderAgeDegree;

public interface LeaderAgeDegreeService {

	//Get all data
	public List<LeaderAgeDegree> get(Map<String, String> filter);
	
	
}
