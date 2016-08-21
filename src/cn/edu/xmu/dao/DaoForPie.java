package cn.edu.xmu.dao;

import java.util.Map;

public interface DaoForPie {

	//作饼图用
	public Map<String, Integer> getNumByAttribute(String tableName,String attribute,Map<String, String> params);
}
