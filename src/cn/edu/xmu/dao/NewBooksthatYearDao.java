package cn.edu.xmu.dao;


import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.NewBooksthatYear;


/**
 * 
 * @author zhantu
 * 图书当年新增情况  实体类功能接口
 * date 2015-06-30
 */
public interface NewBooksthatYearDao {

	
	/**
	 * 插入数据
	 * 
	 * @param nby
	 * 		图书当年新增情况实体
	 * @return 
	 * 		插入数据结果成功与否
	 */
	public int addRecord(NewBooksthatYear nby);
	
	
	/**
	 * 
	 * @param nbyids
	 * @return
	 * @throws SQLException
	 */
	boolean batchDelete(String[] nbyids) throws SQLException;
	
	/**
	 * 
	 * @param valueMap
	 * @param id
	 * @return
	 */
	public int alterNewBooksthatYear(Map<String, String> valueMap, String id);
	
	/**
	 * 
	 * @param queryParams
	 * @return 
	 * 		根据查询条件返回符合条件的条数
	 */
	public int getNewBooksthatYearCount(Map queryParams);
	
	
	/**
	 * 
	 * @param start  开始的标记
	 * @param end    结束的标记
	 * @param sortStr 排序的字段
	 * @param orderStr 升降选项
	 * @param queryParams 查询参数
	 * @return
	 */
	 public List<NewBooksthatYear> getAllNewBooksthatYear(int start, int end,
				String sortStr, String orderStr, Map queryParams);
	 
	 public List<NewBooksthatYear> getNewBooksthatYear(int start, int end,
				String sortStr, String orderStr, Map<String, String> params,
				Date deadline);


	void deleteByCollegeandDeadline(String college, Date deadline)
			throws SQLException;


	List<NewBooksthatYear> getAllNewBooksthatYear();
}
