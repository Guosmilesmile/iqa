package cn.edu.xmu.dao;


import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.TrainingInstitutions;


/**
 * 
 * @author Gy
 * 附表3-5-1-2教师教学发展机构培训情况（学年）
 */
public interface TrainingInstitutionsDao {

	
	/**
	 * 插入数据
	 * 
	 * @param seu
	 * 		学校相关行政单位实体
	 * @return 
	 * 		插入数据结果成功与否
	 */
	public int addRecord(TrainingInstitutions ti);
	
	
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
	public int alterTrainingInstitutions(Map<String, String> valueMap, String id);
	
	/**
	 * 
	 * @param queryParams
	 * @return 
	 * 		根据查询条件返回符合条件的条数
	 */
	public int getTrainingInstitutionsCount(Map queryParams);
	
	
	/**
	 * 
	 * @param start  开始的标记
	 * @param end    结束的标记
	 * @param sortStr 排序的字段
	 * @param orderStr 升降选项
	 * @param queryParams 查询参数
	 * @return
	 */
	 public List<TrainingInstitutions> getAllTrainingInstitutions(int start, int end,
				String sortStr, String orderStr, Map queryParams);


	public List<TrainingInstitutions> getAllTrainingInstitutions();


	void deleteByCollegeandDeadline(String college, Date deadline)
			throws SQLException;
	
	 
}
