package cn.edu.xmu.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.HighLevelResearchTeam;
import cn.edu.xmu.entity.TeachingPlanImpl;

/**
 * 
 * @author xiaoping 表3-4-2  高层次研究团队 (时点)  date 2015-7-9
 *
 */
public interface HighLevelResearchTeamDao extends BaseDao<HighLevelResearchTeam>
{
	/**
	 * 获取高层次研究团队 (时点)
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
	 * @return 高层次研究团队列表
	 */
	public List<HighLevelResearchTeam> getHighLevelResearchTeams(int start, int end, String sortStr, String orderStr,
			Map queryParams, Date deadline);

	/**
	 * 根据查询条件获取相应高层次研究团队的个数
	 * 
	 * @param queryParams
	 *            查询参数
	 * @return 查询到的个数
	 */
	public int getHighLevelResearchTeamCount(Map queryParams);

	/**
	 * 获取最大序列号
	 * 
	 * @return
	 */
	public int getMaxSerialNum();

	/**
	 * 根据id批量删除高层次研究团队
	 * 
	 * @param hlrtids
	 *            高层次研究团队id字符串数组
	 * @return 批量删除成功与否
	 */
	public boolean batchDelete(String[] hlrtids) throws SQLException;

	/**
	 * 插入高层次研究团队
	 * 
	 * @param highLevelResearchTeam
	 *           高层次研究团队实体类
	 * @return 操作成功的个数
	 */
	public int addHighLevelResearchTeam(HighLevelResearchTeam highLevelResearchTeam);
	
	/**
	 * 更改高层次研究团队
	 * 
	 * @param valueMap
	 *            要更改的字段及其更改后的值
	 * @param id
	 * @return 操作成功的个数
	 */
	public int alterHighLevelResearchTeam(Map<String, String> valueMap, String id);
	/**
	 * 获取所有数据
	 * @return
	 */
	public List<HighLevelResearchTeam> getAll();
	
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
