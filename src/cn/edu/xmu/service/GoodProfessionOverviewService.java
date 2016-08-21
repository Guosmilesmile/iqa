package cn.edu.xmu.service;

import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.GoodProfessionOverview;

public interface GoodProfessionOverviewService {

	public List<GoodProfessionOverview> get(Map<String, String> filter);
}
