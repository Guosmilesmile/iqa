package cn.edu.xmu.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.ForeignExchange;

public interface ForeignExchangeDao extends BaseDao<ForeignExchange>{
	public List<ForeignExchange> getForeignExchanges(int start, int end,
			String sortStr, String orderStr,Map<String, String> params,Date deadline);
	public int getForeignExchangeCount(Map queryParams);
	
	boolean batchDelete(String[] smids) throws SQLException;
	
	public int addForeignExchange(ForeignExchange foreignExchange);
	
	public int alterForeignExchange(Map<String, String> valueMap, String id);
	
	public List<ForeignExchange> getAllForeignExchanges();
	
	public void deleteAll();
	
	//public int addForeignExchangeRecord(ForeignExchange foreignExchange);
	
	public void deleteByCollegeandDeadline(String college,Date deadline) throws SQLException;
	
	//作饼图用
	public Map<String, Integer> getNumByAttribute(String attribute,Map<String, String> params);
		
}
