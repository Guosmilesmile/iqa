package cn.edu.xmu.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.PracticePlace;

/**
 * 2-6-1 本科实验实习实训场所
 * @author chunfeng
 *
 */
public interface PracticePlaceDao extends BaseDao<PracticePlace>{
	public List<PracticePlace> getPracticePlace(int start, int end,
			String sortStr, String orderStr,Map<String, String> params,Date deadLine);
	
	public int getPracticePlaceCount(Map queryParams);
	
	boolean batchDelete(String[] ppids) throws SQLException;
	
	public int addPracticePlace(PracticePlace practicePlace);
	
	public int alterPracticePlace(Map<String, String> valueMap, String id);
	
	public List<PracticePlace> getAllPracticePlace();
	
	public void deleteAll();
	
	public void deleteByCollegeandDeadline(String college,Date deadline) throws SQLException;

}
