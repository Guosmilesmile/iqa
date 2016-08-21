package cn.edu.xmu.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.TeacherParticipationSum;
import cn.edu.xmu.entity.UndergraInResearchLab;

/**
 * 
 * @author xiaoping 数据报表 附表3-5-1-3 教师参加院级及以上教学竞赛情况汇总表（自然年） date 2015-7-8
 *
 */
public interface TeacherParticipationSumDao extends BaseDao<TeacherParticipationSum>
{
	/**
	 * 获取教师参加院级及以上教学竞赛情况汇总（自然年）
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
	 * @return 教师参加院级及以上教学竞赛情况汇总列表
	 */
	public List<TeacherParticipationSum> getTeacherParticipationSums(int start, int end, String sortStr, String orderStr,
			Map queryParams, Date deadline);

	/**
	 * 根据查询条件获取相应教师参加院级及以上教学竞赛情况汇总的个数
	 * 
	 * @param queryParams
	 *            查询参数
	 * @return 查询到的个数
	 */
	public int getTeacherParticipationSumCount(Map queryParams);

	/**
	 * 获取最大序列号
	 * 
	 * @return
	 */
	public int getMaxSerialNum();

	/**
	 * 根据id批量删除教师参加院级及以上教学竞赛情况汇总
	 * 
	 * @param hltids
	 *            汇总情况id字符串数组
	 * @return 批量删除成功与否
	 */
	public boolean batchDelete(String[] tpsids) throws SQLException;

	/**
	 * 添加教师参加院级及以上教学竞赛情况汇总
	 * 
	 * @param highLevelTalent
	 *           教师参加院级及以上教学竞赛情况汇总实体类
	 * @return 操作成功的个数
	 */
	public int addTeacherParticipationSum(TeacherParticipationSum teacherParticipationSum);
	
	/**
	 * 更改教师参加院级及以上教学竞赛情况汇总
	 * 
	 * @param valueMap
	 *            要更改的字段及其更改后的值
	 * @param id
	 * @return 操作成功的个数
	 */
	public int alterTeacherParticipationSum(Map<String, String> valueMap, String id);
	
	/**
	 * 获取所有数据
	 * @return
	 */
	public List<TeacherParticipationSum> getAll();
	
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
