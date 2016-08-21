package cn.edu.xmu.service;

import java.util.Map;

public interface ServiceForPie {
	//作饼图用
	public Map<String, Integer> getNumByAttribute(String tableName,String attribute,Map<String, String> params);
}
