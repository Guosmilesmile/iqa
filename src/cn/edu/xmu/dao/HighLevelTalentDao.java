package cn.edu.xmu.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.HighLevelTalent;

/**
 * 
 * @author xiaoping 表3-4-1 高层次人才(时点) date 2015-7-3
 *
 */
public interface HighLevelTalentDao extends BaseDao<HighLevelTalent>
{
	/**
	 * 获取高层次人才(时点)
	 * 
	 * @param start
	 *            开始位置
	 * @param end
	 *            终止位置
	 * @param sortStr
	 *            第一个排序参数
	 * @param orderStr
	 *            第二个排序参数
	 * @param queryParams
	 *            查询参数
	 * @return 高层次人才(时点)列表
	 */
	public List<HighLevelTalent> getHighLevelTalents(int start, int end, String sortStr, String orderStr,
			Map queryParams, Date deadline);

	/**
	 * 根据查询条件获取相应高层次人才的个数
	 * 
	 * @param queryParams
	 *            查询参数
	 * @return 查询到的个数
	 */
	public int getHighLevelTalentCount(Map queryParams);

	/**
	 * 获取最大序列号
	 * 
	 * @return
	 */
	public int getMaxSerialNum();

	/**
	 * 根据id批量删除高层次人才
	 * 
	 * @param hltids
	 *            高层次人才id字符串数组
	 * @return 批量删除成功与否
	 */
	public boolean batchDelete(String[] hltids) throws SQLException;

	/**
	 * 插入高层次人才
	 * 
	 * @param highLevelTalent
	 *            高层次人才实体类
	 * @return 操作成功的个数
	 */
	public int addHighLevelTalent(HighLevelTalent highLevelTalent);
	
	/**
	 * 更改高层次人才
	 * 
	 * @param valueMap
	 *            要更改的字段及其更改后的值
	 * @param id
	 * @return 操作成功的个数
	 */
	public int alterHighLevelTalent(Map<String, String> valueMap, String id);
	
	/**
	 * 获取所有高层次人才
	 * @return
	 */
	public List<HighLevelTalent> getAll();
	
	/**
	 * 根据所属学院和填报日期删除数据
	 * @param college
	 * @param deadline
	 * @return
	 * @throws SQLException
	 */
	public boolean deleteByCollegeandDeadline(String college, Date deadline)
			throws SQLException;
}
