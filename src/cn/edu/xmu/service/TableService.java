package cn.edu.xmu.service;

import java.util.ArrayList;

public interface TableService {
	
	//根据表名获取所有属性列
	public ArrayList<String> getAttibutes(String tableName);
	
	//根据id获取在数据库中表的名称
	public String getTableNameById(String tableId);
	

}
