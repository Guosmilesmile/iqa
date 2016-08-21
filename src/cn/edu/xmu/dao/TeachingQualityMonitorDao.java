package cn.edu.xmu.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.TeachingQualityMonitor;

/**
 * 表7-4  教学质量管理与监控（时点）
 * @author chunfeng
 *
 */
public interface TeachingQualityMonitorDao extends BaseDao<TeachingQualityMonitor>{
	public List<TeachingQualityMonitor> getTeachingQualityMonitor(int start, int end,
			String sortStr, String orderStr,Map<String, String> params,Date deadLine);
	
	public int getTeachingQualityMonitorCount(Map queryParams);
	
	boolean batchDelete(String[] tqmids) throws SQLException;
	
	public int addTeachingQualityMonitor(TeachingQualityMonitor teachingQualityMonitor);
	
	public int alterTeachingQualityMonitor(Map<String, String> valueMap, String id);
	
	public List<TeachingQualityMonitor> getAllTeachingQualityMonitor();
	
	public void deleteAll();
	
	public void deleteByCollegeandDeadline(String college,Date deadline) throws SQLException;

}
