package cn.edu.xmu.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.TeacherParticipationSum;
import cn.edu.xmu.entity.TeachingPlanImpl;

/**
 * 
 * @author xiaoping 数据报表 附表4-2-2-2教学计划执行情况  date 2015-7-8
 *
 */
public interface TeachingPlanImplDao extends BaseDao<TeachingPlanImpl>
{
	/**
	 * 获取教学计划执行情况
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
	 * @return 教学计划执行情况列表
	 */
	public List<TeachingPlanImpl> getTeachingPlanImpls(int start, int end, String sortStr, String orderStr,
			Map queryParams, Date deadline);

	/**
	 * 根据查询条件获取相应教学计划执行情况的个数
	 * 
	 * @param queryParams
	 *            查询参数
	 * @return 查询到的个数
	 */
	public int getTeachingPlanImplCount(Map queryParams);

	/**
	 * 获取最大序列号
	 * 
	 * @return
	 */
	public int getMaxSerialNum();

	/**
	 * 根据id批量删除教学计划执行情况
	 * 
	 * @param tpiids
	 *            id字符串数组
	 * @return 批量删除成功与否
	 */
	public boolean batchDelete(String[] tpiids) throws SQLException;

	/**
	 * 插入教学计划执行情况
	 * 
	 * @param teachingPlanImpl
	 *            教学计划执行情况实体类
	 * @return 操作成功的个数
	 */
	public int addTeachingPlanImpl(TeachingPlanImpl teachingPlanImpl);
	
	/**
	 * 更改教学计划执行情况
	 * 
	 * @param valueMap
	 *            要更改的字段及其更改后的值
	 * @param id
	 * @return 操作成功的个数
	 */
	public int alterTeachingPlanImpl(Map<String, String> valueMap, String id);
	
	/**
	 * 获取所有数据
	 * @return
	 */
	public List<TeachingPlanImpl> getAll();
	
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
