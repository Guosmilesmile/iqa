package cn.edu.xmu.dao;

import java.util.ArrayList;

public interface TableDao {
	
	//根据表名获取所有属性列
	ArrayList<String> getAttibutes(String tableName);
}
