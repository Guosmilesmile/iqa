package cn.edu.xmu.dao;


import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.SchoolExecutiveUnit;


/**
 * 
 * @author Lee
 * 学校相关行政单位  实体类功能接口
 * date 2015-06-29
 */
public interface SchoolExecutiveUnitDao {

	
	/**
	 * 插入数据
	 * 
	 * @param seu
	 * 		学校相关行政单位实体
	 * @return 
	 * 		插入数据结果成功与否
	 */
	public int addRecord(SchoolExecutiveUnit seu);
	
	
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
	public int alterSchoolExecutiveUnit(Map<String, String> valueMap, String id);
	
	/**
	 * 
	 * @param queryParams
	 * @return 
	 * 		根据查询条件返回符合条件的条数
	 */
	public int getSchoolExecutiveUnitCount(Map queryParams);
	
	
	/**
	 * 
	 * @param start  开始的标记
	 * @param end    结束的标记
	 * @param sortStr 排序的字段
	 * @param orderStr 升降选项
	 * @param queryParams 查询参数
	 * @return
	 */
	 public List<SchoolExecutiveUnit> getAllSchoolExecutiveUnit(int start, int end,
				String sortStr, String orderStr, Map queryParams);
	 
	 public void deleteByCollegeandDeadline(String college)
				throws SQLException;
	 public List<SchoolExecutiveUnit> getAllSchoolExecutiveUnit();
	 
}
