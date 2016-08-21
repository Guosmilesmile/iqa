package cn.edu.xmu.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.UnfinishedForm;


/**
 * 附表 未填表格说明
 * @author chunfeng
 *
 */
public interface UnfinishedFormDao extends BaseDao<UnfinishedForm>{
	public List<UnfinishedForm> getUnfinishedForm(int start, int end,
			String sortStr, String orderStr,Map<String, String> params,Date deadLine);
	
	public int getUnfinishedFormCount(Map queryParams);
	
	boolean batchDelete(String[] ufids) throws SQLException;
	
	public int addUnfinishedForm(UnfinishedForm unfinishedForm);
	
	public int alterUnfinishedForm(Map<String, String> valueMap, String id);
	
	public List<UnfinishedForm> getAllUnfinishedForm();
	
	public void deleteAll();
	
	public void deleteByCollegeandDeadline(String college,Date deadline) throws SQLException;


}
