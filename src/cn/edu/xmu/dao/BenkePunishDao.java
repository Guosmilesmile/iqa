package cn.edu.xmu.dao;


import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.BenkePunish;
import cn.edu.xmu.entity.PlayGround;



/**
 * 
 * @author Lee
 * 附表6-2-1-10 本科生受处分情况(按处分等级）  实体类功能接口
 * date 2015-06-29
 */
public interface BenkePunishDao {

	
	/**
	 * 插入数据
	 * 
	 * @param bp
	 * 		本科生受处分情况实体
	 * @return 
	 * 		插入数据结果成功与否
	 */
	public int addRecord(BenkePunish bp);
	
	
	/**
	 * 
	 * @param bpids
	 * @return
	 * @throws SQLException
	 */
	boolean batchDelete(String[] bpids) throws SQLException;
	
	/**
	 * 
	 * @param valueMap
	 * @param id
	 * @return
	 */
	public int alterBenkePunish(Map<String, String> valueMap, String id);
	
	/**
	 * 
	 * @param queryParams
	 * @return 
	 * 		根据查询条件返回符合条件的条数
	 */
	public int getBenkePunishCount(Map queryParams);
	
	
	/**
	 * 
	 * @param start  开始的标记
	 * @param end    结束的标记
	 * @param sortStr 排序的字段
	 * @param orderStr 升降选项
	 * @param queryParams 查询参数
	 * @return
	 */
	 public List<BenkePunish> getAllBenkePunish(int start, int end,
				String sortStr, String orderStr, Map queryParams);
	 
	 
	 public void deleteByCollegeandDeadline(String college)
				throws SQLException;
	 public List<BenkePunish> getAllBenkePunish();
	 
}
