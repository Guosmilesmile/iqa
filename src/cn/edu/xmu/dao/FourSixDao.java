package cn.edu.xmu.dao;


import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.FourSix;




/**
 * 
 * @author Gy
 * 附表6-2-1-7 本科毕业生大学英语四六级考试累计通过率（学年）
 */
public interface FourSixDao {

	
	/**
	 * 插入数据
	 * 
	 * @return 
	 * 		插入数据结果成功与否
	 */
	public int addRecord(FourSix fx);
	
	
	/**
	 * 
	 * @param seuids
	 * @return
	 * @throws SQLException
	 */
	boolean batchDelete(String[] seuids) throws SQLException;
	
	/**
	 * 
	 * @param valueMap
	 * @param id
	 * @return
	 */
	public int alterFourSix(Map<String, String> valueMap, String id);
	
	/**
	 * 
	 * @param queryParams
	 * @return 
	 * 		根据查询条件返回符合条件的条数
	 */
	public int getFourSixCount(Map queryParams);
	
	
	/**
	 * 
	 * @param start  开始的标记
	 * @param end    结束的标记
	 * @param sortStr 排序的字段
	 * @param orderStr 升降选项
	 * @param queryParams 查询参数
	 * @return
	 */
	 public List<FourSix> getAllFourSix(int start, int end,
				String sortStr, String orderStr, Map queryParams);


	List<FourSix> getAllFourSix();


	void deleteByCollegeandDeadline(String college, Date deadline)
			throws SQLException;
	 
	 
}
