package cn.edu.xmu.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.RecruitExceedScore;

/**
 * 
 * @author xiaoping 附表6-1-5-1厦门大学在全国各省（市、自治区）招生出档分数情况（时点） date 2015-7-11
 *
 */
public interface RecruitExceedScoreDao extends BaseDao<RecruitExceedScore>
{
	/**
	 * 厦门大学在全国各省（市、自治区）招生出档分数情况
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
	 * @return 厦门大学在全国各省（市、自治区）招生出档分数情况列表
	 */
	public List<RecruitExceedScore> getRecruitExceedScores(int start, int end, String sortStr, String orderStr,
			Map queryParams, Date deadline);

	/**
	 * 根据查询条件获取相应厦门大学在全国各省（市、自治区）招生出档分数情况的个数
	 * 
	 * @param queryParams
	 *            查询参数
	 * @return 查询到的个数
	 */
	public int getRecruitExceedScoreCount(Map queryParams);

	/**
	 * 获取最大序列号
	 * 
	 * @return
	 */
	public int getMaxSerialNum();

	/**
	 * 根据id批量删除厦门大学在全国各省（市、自治区）招生出档分数情况
	 * 
	 * @param resids
	 *            id字符串数组
	 * @return 批量删除成功与否
	 */
	public boolean batchDelete(String[] resids) throws SQLException;

	/**
	 * 插入厦门大学在全国各省（市、自治区）招生出档分数情况
	 * 
	 * @param recruitExceedScore
	 *            实体类
	 * @return 操作成功的个数
	 */
	public int addRecruitExceedScore(RecruitExceedScore recruitExceedScore);
	
	/**
	 * 更改厦门大学在全国各省（市、自治区）招生出档分数情况
	 * 
	 * @param valueMap
	 *            要更改的字段及其更改后的值
	 * @param id
	 * @return 操作成功的个数
	 */
	public int alterRecruitExceedScore(Map<String, String> valueMap, String id);
	
	/**
	 * 获取所有厦门大学在全国各省（市、自治区）招生出档分数情况
	 * @return
	 */
	public List<RecruitExceedScore> getAll();
	
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
