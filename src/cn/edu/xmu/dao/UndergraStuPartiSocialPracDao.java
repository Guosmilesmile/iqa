package cn.edu.xmu.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.HighLevelResearchTeam;
import cn.edu.xmu.entity.UndergraStuPartiSocialPrac;
/**
 * 
 * @author xiaoping 附表5-4-3 本科生参与暑期社会实践情况  date 2015-7-10
 *
 */
public interface UndergraStuPartiSocialPracDao extends BaseDao<UndergraStuPartiSocialPrac>
{
	/**
	 * 本科生参与暑期社会实践情况
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
	 * @return 本科生参与暑期社会实践情况列表
	 */
	public List<UndergraStuPartiSocialPrac> getUndergraStuPartiSocialPracs(int start, int end, String sortStr, String orderStr,
			Map queryParams, Date deadline);

	/**
	 * 根据查询条件获取相应本科生参与暑期社会实践情况的个数
	 * 
	 * @param queryParams
	 *            查询参数
	 * @return 查询到的个数
	 */
	public int getUndergraStuPartiSocialPracCount(Map queryParams);

	/**
	 * 获取最大序列号
	 * 
	 * @return
	 */
	public int getMaxSerialNum();

	/**
	 * 根据id批量删除本科生参与暑期社会实践情况
	 * 
	 * @param uspspids
	 *            id字符串数组
	 * @return 批量删除成功与否
	 */
	public boolean batchDelete(String[] uspspids) throws SQLException;

	/**
	 * 插入本科生参与暑期社会实践情况
	 * 
	 * @param undergraStuPartiSocialPrac
	 *            本科生参与暑期社会实践情况实体类
	 * @return 操作成功的个数
	 */
	public int addUndergraStuPartiSocialPrac(UndergraStuPartiSocialPrac undergraStuPartiSocialPrac);
	
	/**
	 * 更改本科生参与暑期社会实践情况
	 * 
	 * @param valueMap
	 *            要更改的字段及其更改后的值
	 * @param id
	 * @return 操作成功的个数
	 */
	public int alterUndergraStuPartiSocialPrac(Map<String, String> valueMap, String id);
	/**
	 * 获取所有数据
	 * @return
	 */
	public List<UndergraStuPartiSocialPrac> getAll();
	
	/**
	 * 根据所属学院和填报日期删除数据
	 * @param college
	 * @param deadline
	 * @return
	 * @throws SQLException
	 */
	public boolean deleteByCollegeandDeadline(String college, Date deadline)
			throws SQLException;
	/**
	 * 根据参数获取相匹配值，主要用来获取‘合计’
	 * @param equalParams 相等参数对
	 *  @param equalParams 不相等参数对
	 * @return 匹配的值
	 */
	public List<UndergraStuPartiSocialPrac> getEqualUndergraStuPartiSocialPrac(Map<String, String> equalParams, Map<String, String> notEqualParams);
}
