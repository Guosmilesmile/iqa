package cn.edu.xmu.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.OutsidePracticePlace;

/**
 * 2-6-2校外实习、实践基地
 * @author chunfeng
 *
 */
public interface OutsidePracticePlaceDao  extends BaseDao<OutsidePracticePlace>{
	public List<OutsidePracticePlace> getOutsidePracticePlace(int start, int end,
			String sortStr, String orderStr,Map<String, String> params, Date deadline);
	
	public int getOutsidePracticePlaceCount(Map queryParams);
	
	boolean batchDelete(String[] oppids) throws SQLException;
	
	public int addOutsidePracticePlace(OutsidePracticePlace outsidePracticePlace);
	
	public int alterOutsidePracticePlace(Map<String, String> valueMap, String id);
	
	public List<OutsidePracticePlace> getAllOutsidePracticePlace();
	
	public void deleteAll();
	
	public void deleteByCollegeandDeadline(String college,Date deadline) throws SQLException;


}
