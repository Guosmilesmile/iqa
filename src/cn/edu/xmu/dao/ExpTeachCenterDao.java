package cn.edu.xmu.dao;


import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.ExpTeachCenter;



/**
 * 
 * @author Lee
 * 实验教学示范中心  实体类功能接口
 * date 2015-06-29
 */
public interface ExpTeachCenterDao {

	
	/**
	 * 插入数据
	 * 
	 * @param etc
	 * 		实验教学示范中心实体
	 * @return 
	 * 		插入数据结果成功与否
	 */
	public int addRecord(ExpTeachCenter etc);
	
	
	/**
	 * 
	 * @param etcids
	 * @return
	 * @throws SQLException
	 */
	boolean batchDelete(String[] etcids) throws SQLException;
	
	/**
	 * 
	 * @param valueMap
	 * @param id
	 * @return
	 */
	public int alterExpTeachCenter(Map<String, String> valueMap, String id);
	
	/**
	 * 
	 * @param queryParams
	 * @return 
	 * 		根据查询条件返回符合条件的条数
	 */
	public int getExpTeachCenterCount(Map queryParams);
	
	
	/**
	 * 
	 * @param start  开始的标记
	 * @param end    结束的标记
	 * @param sortStr 排序的字段
	 * @param orderStr 升降选项
	 * @param queryParams 查询参数
	 * @return
	 */
	 public List<ExpTeachCenter> getAllExpTeachCenter(int start, int end,
				String sortStr, String orderStr, Map queryParams);
	 
	 public List<ExpTeachCenter> getExpTeachCenter(Map queryParams);
	 
	 public void deleteByCollegeandDeadline(String college)
				throws SQLException;
	 public List<ExpTeachCenter> getAllExpTeachCenter();
}
