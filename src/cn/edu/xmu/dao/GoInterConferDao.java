package cn.edu.xmu.dao;


import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.BenkePunish;
import cn.edu.xmu.entity.GoInterConfer;



/**
 * 
 * @author Lee
 * 学生参加国际会议情况  实体类功能接口
 * date 2015-06-29
 */
public interface GoInterConferDao {

	
	/**
	 * 插入数据
	 * 
	 * @param gic
	 * 		学生参加国际会议情况实体
	 * @return 
	 * 		插入数据结果成功与否
	 */
	public int addRecord(GoInterConfer gic);
	
	
	/**
	 * 
	 * @param gicids
	 * @return
	 * @throws SQLException
	 */
	boolean batchDelete(String[] gicids) throws SQLException;
	
	/**
	 * 
	 * @param valueMap
	 * @param id
	 * @return
	 */
	public int alterGoInterConfer(Map<String, String> valueMap, String id);
	
	/**
	 * 
	 * @param queryParams
	 * @return 
	 * 		根据查询条件返回符合条件的条数
	 */
	public int getGoInterConferCount(Map queryParams);
	
	
	/**
	 * 
	 * @param start  开始的标记
	 * @param end    结束的标记
	 * @param sortStr 排序的字段
	 * @param orderStr 升降选项
	 * @param queryParams 查询参数
	 * @return
	 */
	 public List<GoInterConfer> getAllGoInterConfer(int start, int end,
				String sortStr, String orderStr, Map queryParams);
	 
	 
	 public void deleteByCollegeandDeadline(String college)
				throws SQLException;
	 public List<GoInterConfer> getAllGoInterConfer();
	 
}
