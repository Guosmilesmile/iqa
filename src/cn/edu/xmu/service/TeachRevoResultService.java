package cn.edu.xmu.service;

import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.TeachRevoResult;

public interface TeachRevoResultService {
	public List<TeachRevoResult> get(Map<String, String> filter);
}
