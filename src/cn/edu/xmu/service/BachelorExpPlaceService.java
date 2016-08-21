package cn.edu.xmu.service;

import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.BachelorExpPlace;

public interface BachelorExpPlaceService {
	public List<BachelorExpPlace> get(Map<String, String> filter);
}
