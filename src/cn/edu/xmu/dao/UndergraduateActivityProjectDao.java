package cn.edu.xmu.dao;


import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.UndergraduateActivityProject;


/**
 * 
 * @author zhantu
 * 本科生课外科技、文化活动项目（学年）  实体类功能接口
 * date 2015-07-09
 */
public interface UndergraduateActivityProjectDao {

	
	/**
	 * 插入数据
	 * 
	 * @param uap
	 * 		本科生课外科技、文化活动项目（学年）实体
	 * @return 
	 * 		插入数据结果成功与否
	 */
	public int addRecord(UndergraduateActivityProject uap);
	
	
	/**
	 * 
	 * @param uapids
	 * @return
	 * @throws SQLException
	 */
	boolean batchDelete(String[] uapids) throws SQLException;
	
	/**
	 * 
	 * @param valueMap
	 * @param id
	 * @return
	 */
	public int alterUndergraduateActivityProject(Map<String, String> valueMap, String id);
	
	/**
	 * 
	 * @param queryParams
	 * @return 
	 * 		根据查询条件返回符合条件的条数
	 */
	public int getUndergraduateActivityProjectCount(Map queryParams);
	
	
	/**
	 * 
	 * @param start  开始的标记
	 * @param end    结束的标记
	 * @param sortStr 排序的字段
	 * @param orderStr 升降选项
	 * @param queryParams 查询参数
	 * @return
	 */
	 public List<UndergraduateActivityProject> getAllUndergraduateActivityProject(int start, int end,
				String sortStr, String orderStr, Map queryParams);
	 
	 public List<UndergraduateActivityProject> getUndergraduateActivityProject(int start, int end,
				String sortStr, String orderStr, Map<String, String> params,
				Date deadline);


	void deleteByCollegeandDeadline(String college, Date deadline)
			throws SQLException;


	List<UndergraduateActivityProject> getAllUndergraduateActivityProject();
}
