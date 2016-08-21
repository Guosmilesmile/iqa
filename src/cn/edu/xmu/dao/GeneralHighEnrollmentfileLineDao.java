package cn.edu.xmu.dao;


import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.GeneralHighEnrollmentfileLine;


/**
 * 
 * @author zhantu
 * 厦门大学普高招生各省份出档线高出本一线分值  实体类功能接口
 * date 2015-07-13
 */
public interface GeneralHighEnrollmentfileLineDao {

	
	/**
	 * 插入数据
	 * 
	 * @param ghel
	 * 		厦门大学普高招生各省份出档线高出本一线分值实体
	 * @return 
	 * 		插入数据结果成功与否
	 */
	public int addRecord(GeneralHighEnrollmentfileLine ghel);
	
	
	/**
	 * 
	 * @param ghelids
	 * @return
	 * @throws SQLException
	 */
	boolean batchDelete(String[] ghelids) throws SQLException;
	
	/**
	 * 
	 * @param valueMap
	 * @param id
	 * @return
	 */
	public int alterGeneralHighEnrollmentfileLine(Map<String, String> valueMap, String id);
	
	/**
	 * 
	 * @param queryParams
	 * @return 
	 * 		根据查询条件返回符合条件的条数
	 */
	public int getGeneralHighEnrollmentfileLineCount(Map queryParams);
	
	
	/**
	 * 
	 * @param start  开始的标记
	 * @param end    结束的标记
	 * @param sortStr 排序的字段
	 * @param orderStr 升降选项
	 * @param queryParams 查询参数
	 * @return
	 */
	 public List<GeneralHighEnrollmentfileLine> getAllGeneralHighEnrollmentfileLine(int start, int end,
				String sortStr, String orderStr, Map queryParams);
	 
	 public List<GeneralHighEnrollmentfileLine> getGeneralHighEnrollmentfileLine(int start, int end,
				String sortStr, String orderStr, Map<String, String> params,
				Date deadline);


	void deleteByCollegeandDeadline(String college, Date deadline)
			throws SQLException;


	List<GeneralHighEnrollmentfileLine> getAllGeneralHighEnrollmentfileLine();
}
