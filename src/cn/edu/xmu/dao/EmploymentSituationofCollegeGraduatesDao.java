package cn.edu.xmu.dao;


import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.EmploymentSituationofCollegeGraduates;


/**
 * 
 * @author zhantu
 * 应届本科毕业生就业情况（时点）  实体类功能接口
 * date 2015-07-05
 */
public interface EmploymentSituationofCollegeGraduatesDao {

	
	/**
	 * 插入数据
	 * 
	 * @param escg
	 * 		应届本科毕业生就业情况（时点）实体
	 * @return 
	 * 		插入数据结果成功与否
	 */
	public int addRecord(EmploymentSituationofCollegeGraduates escg);
	
	
	/**
	 * 
	 * @param escgids
	 * @return
	 * @throws SQLException
	 */
	boolean batchDelete(String[] escgids) throws SQLException;
	
	/**
	 * 
	 * @param valueMap
	 * @param id
	 * @return
	 */
	public int alterEmploymentSituationofCollegeGraduates(Map<String, String> valueMap, String id);
	
	/**
	 * 
	 * @param queryParams
	 * @return 
	 * 		根据查询条件返回符合条件的条数
	 */
	public int getEmploymentSituationofCollegeGraduatesCount(Map queryParams);
	
	
	/**
	 * 
	 * @param start  开始的标记
	 * @param end    结束的标记
	 * @param sortStr 排序的字段
	 * @param orderStr 升降选项
	 * @param queryParams 查询参数
	 * @return
	 */
	 public List<EmploymentSituationofCollegeGraduates> getAllEmploymentSituationofCollegeGraduates(int start, int end,
				String sortStr, String orderStr, Map queryParams);
	 
	 public List<EmploymentSituationofCollegeGraduates> getEmploymentSituationofCollegeGraduates(int start, int end,
				String sortStr, String orderStr, Map<String, String> params,
				Date deadline);


	void deleteByCollegeandDeadline(String college, Date deadline)
			throws SQLException;


	List<EmploymentSituationofCollegeGraduates> getAllEmploymentSituationofCollegeGraduates();

}
