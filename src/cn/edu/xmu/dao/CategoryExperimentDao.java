package cn.edu.xmu.dao;


import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.CategoryExperiment;
import cn.edu.xmu.entity.SchoolExecutiveUnit;
import cn.edu.xmu.entity.SuperMajor;


/**
 * 
 * @author Gy
 * 表5-3-3 2014-2015学年分专业（大类）实验情况 
 * date 2015-07-05
 */
public interface CategoryExperimentDao {

	
	/**
	 * 
	 * @param ce
	 * @return
	 */
	public int addRecord(CategoryExperiment ce);
	
	
	/**
	 * 
	 * @param 
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
	public int alterCategoryExperiment(Map<String, String> valueMap, String id);
	
	/**
	 * 
	 * @param queryParams
	 * @return 
	 * 		根据查询条件返回符合条件的条数
	 */
	public int getCategoryExperimentCount(Map queryParams);
	
	
	/**
	 * 
	 * @param start  开始的标记
	 * @param end    结束的标记
	 * @param sortStr 排序的字段
	 * @param orderStr 升降选项
	 * @param queryParams 查询参数
	 * @return
	 */
	 public List<CategoryExperiment> getAllCategoryExperiment(int start, int end,
				String sortStr, String orderStr, Map queryParams);


	List<CategoryExperiment> getAllCategoryExperiment();


	void deleteByCollegeandDeadline(String college, Date deadline)
			throws SQLException;
	
	 
	 
}
