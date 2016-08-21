package cn.edu.xmu.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.UndergraInResearchLab;

/**
 * 
 * @author xiaoping 附表5-4-4 本科生进入科研实验室情况 date 2015-7-11
 *
 */
public interface UndergraInResearchLabDao extends BaseDao<UndergraInResearchLab>
{
	/**
	 * 本科生进入科研实验室情况
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
	 * @return 本科生进入科研实验室情况列表
	 */
	public List<UndergraInResearchLab> getUndergraInResearchLabs(int start, int end, String sortStr, String orderStr,
			Map queryParams, Date deadline);

	/**
	 * 根据查询条件获取相应本科生进入科研实验室情况的个数
	 * 
	 * @param queryParams
	 *            查询参数
	 * @return 查询到的个数
	 */
	public int getUndergraInResearchLabCount(Map queryParams);

	/**
	 * 获取最大序列号
	 * 
	 * @return
	 */
	public int getMaxSerialNum();

	/**
	 * 根据id批量删除本科生进入科研实验室情况
	 * 
	 * @param uirlids
	 *            id字符串数组
	 * @return 批量删除成功与否
	 */
	public boolean batchDelete(String[] uirlids) throws SQLException;

	/**
	 * 插入本科生进入科研实验室情况
	 * 
	 * @param undergraInResearchLab
	 *            实体类
	 * @return 操作成功的个数
	 */
	public int addUndergraInResearchLab(UndergraInResearchLab undergraInResearchLab);
	
	/**
	 * 更改本科生进入科研实验室情况
	 * 
	 * @param valueMap
	 *            要更改的字段及其更改后的值
	 * @param id
	 * @return 操作成功的个数
	 */
	public int alterUndergraInResearchLab(Map<String, String> valueMap, String id);
	
	/**
	 * 获取所有本科生进入科研实验室情况
	 * @return
	 */
	public List<UndergraInResearchLab> getAll();
	
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
