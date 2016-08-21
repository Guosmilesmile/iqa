package cn.edu.xmu.dao;


import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.EducationMoney;



/**
 * 
 * @author Gy
 * 附表2-10-2-1教育经费收支情况
 */
public interface EducationMoneyDao {

	
	/**
	 * 插入数据
	 * 
	 * @param seu
	 * 		学校相关行政单位实体
	 * @return 
	 * 		插入数据结果成功与否
	 */
	public int addRecord(EducationMoney em);
	
	
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
	public int alterEducationMoney(Map<String, String> valueMap, String id);
	
	/**
	 * 
	 * @param queryParams
	 * @return 
	 * 		根据查询条件返回符合条件的条数
	 */
	public int getEducationMoneyCount(Map queryParams);
	
	
	/**
	 * 
	 * @param start  开始的标记
	 * @param end    结束的标记
	 * @param sortStr 排序的字段
	 * @param orderStr 升降选项
	 * @param queryParams 查询参数
	 * @return
	 */
	 public List<EducationMoney> getAllEducationMoney(int start, int end,
				String sortStr, String orderStr, Map queryParams);


	public List<EducationMoney> getAllEducationMoney();


	void deleteByCollegeandDeadline(String college, Date deadline)
			throws SQLException;
	
	 
	 
}
