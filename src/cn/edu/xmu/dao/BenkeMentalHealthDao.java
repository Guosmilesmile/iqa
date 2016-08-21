package cn.edu.xmu.dao;


import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.BenkeMentalHealth;




/**
 * 
 * @author Lee
 * 附表6-2-1-11本科生心理健康状况分析（学年）
 * date 2015-07-13
 */
public interface BenkeMentalHealthDao {

	
	/**
	 * 插入数据
	 * 
	 * @param bmh
	 * 		本科生心理健康状况分析 实体
	 * @return 
	 * 		插入数据结果成功与否
	 */
	public int addRecord(BenkeMentalHealth bmh);
	
	
	/**
	 * 
	 * @param bmhids
	 * @return
	 * @throws SQLException
	 */
	boolean batchDelete(String[] bmhids) throws SQLException;
	
	/**
	 * 
	 * @param valueMap
	 * @param id
	 * @return
	 */
	public int alterBenkeMentalHealth(Map<String, String> valueMap, String id);
	
	/**
	 * 
	 * @param queryParams
	 * @return 
	 * 		根据查询条件返回符合条件的条数
	 */
	public int getBenkeMentalHealthCount(Map queryParams);
	
	
	/**
	 * 
	 * @param start  开始的标记
	 * @param end    结束的标记
	 * @param sortStr 排序的字段
	 * @param orderStr 升降选项
	 * @param queryParams 查询参数
	 * @return
	 */
	 public List<BenkeMentalHealth> getAllBenkeMentalHealth(int start, int end,
				String sortStr, String orderStr, Map queryParams);
	 
	 
	 public void deleteByCollegeandDeadline(String college)
				throws SQLException;
	 public List<BenkeMentalHealth> getAllBenkeMentalHealth();
	 
}
