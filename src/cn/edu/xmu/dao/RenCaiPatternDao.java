package cn.edu.xmu.dao;


import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.RenCaiPattern;


/**
 * 
 * @author Lee
 * 人才培养模式创新实验项目  实体类功能接口
 * date 2015-06-29
 */
public interface RenCaiPatternDao {

	
	/**
	 * 插入数据
	 * 
	 * @param rcp
	 * 		人才培养模式创新实验项目实体
	 * @return 
	 * 		插入数据结果成功与否
	 */
	public int addRecord(RenCaiPattern rcp);
	
	
	/**
	 * 
	 * @param rcpids
	 * @return
	 * @throws SQLException
	 */
	boolean batchDelete(String[] rcpids) throws SQLException;
	
	/**
	 * 
	 * @param valueMap
	 * @param id
	 * @return
	 */
	public int alterRenCaiPattern(Map<String, String> valueMap, String id);
	
	/**
	 * 
	 * @param queryParams
	 * @return 
	 * 		根据查询条件返回符合条件的条数
	 */
	public int getRenCaiPatternCount(Map queryParams);
	
	
	/**
	 * 
	 * @param start  开始的标记
	 * @param end    结束的标记
	 * @param sortStr 排序的字段
	 * @param orderStr 升降选项
	 * @param queryParams 查询参数
	 * @return
	 */
	 public List<RenCaiPattern> getAllRenCaiPattern(int start, int end,
				String sortStr, String orderStr, Map queryParams);
	 
	 
	 /*
	  * 统计时使用： 获取所有数据
	  */
	 public List<RenCaiPattern> getRenCaiPattern( Map queryParams);
	 
	 
}
