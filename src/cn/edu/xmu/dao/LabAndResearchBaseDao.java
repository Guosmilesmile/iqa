package cn.edu.xmu.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.LabAndResearchBase;

/**
 * 
 * @author zsj
 * 1-5实验室和科研基地表
 */
public interface LabAndResearchBaseDao extends BaseDao<LabAndResearchBase>{
	
	public List<LabAndResearchBase> getAllLabAndResearchBase(Map queryParams);
	public List<LabAndResearchBase> getAllLabAndResearchBase();
	public List<LabAndResearchBase> getAllLabAndResearchBase(int start, int end,
			String sortStr, String orderStr, Map queryParams);

	//添加实验室和科研基地
	public int addLabAndResearchBase(LabAndResearchBase labAndResearchBase);
	//修改实验室和科研基地信息
	public int alterLabAndResearchBase(Map<String, String> valueMap, String id);
	//删除实验室和科研基地
	public int deleteLabAndResearchBaseById(String id,String serialnumber);
	//批量删除
	boolean batchDelete(String[] smids) throws SQLException;
	//获得当前最大序号
	public int getMaxSerialNum();
	
	public int getLabAndResearchBaseCount(Map queryParams);
	
	public List<LabAndResearchBase> findForPage(int start, int end,
			String sortStr, String orderStr, Map queryParams);
	//清空记录
	public void deleteAll();
	
	//根据id获得serialnumber
	public int getSerialNumberById(String id);
	
	//导入之前的删除
	public void deleteByCollegeandDeadline(String college) throws SQLException;
}
