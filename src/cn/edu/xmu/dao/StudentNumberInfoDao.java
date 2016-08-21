package cn.edu.xmu.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.StudentNumberInfo;
/**
 * 
 * @author xiaoping 数据报表6-1-1 学生数量基本情况（时点）date 2015-7-5
 *
 */
public interface StudentNumberInfoDao extends BaseDao<StudentNumberInfo>
{
	/**
	 * 获取学生数量基本情况(时点)
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
	 * @return 学生数量基本情况列表
	 */
	public List<StudentNumberInfo> getStudentNumberInfos(int start, int end, String sortStr, String orderStr,
			Map queryParams, Date deadline);

	/**
	 * 根据查询条件获取相应学生数量基本情况的个数
	 * 
	 * @param queryParams
	 *            查询参数
	 * @return 查询到的个数
	 */
	public int getStudentNumberInfoCount(Map queryParams);

	/**
	 * 获取最大序列号
	 * 
	 * @return
	 */
	public int getMaxSerialNum();

	/**
	 * 根据id批量删除学生数量基本情况
	 * 
	 * @param hltids
	 *            高层次人才id字符串数组
	 * @return 批量删除成功与否
	 */
	public boolean batchDelete(String[] sniids) throws SQLException;

	/**
	 * 添加学生数量基本情况
	 * 
	 * @param highLevelTalent
	 *            学生数量基本情况实体类
	 * @return 操作成功的个数
	 */
	public int addStudentNumberInfo(StudentNumberInfo studentNumberInfo);
	
	/**
	 * 更改学生数量基本情况
	 * 
	 * @param valueMap
	 *            要更改的字段及其更改后的值
	 * @param id
	 * @return 操作成功的个数
	 */
	public int alterStudentNumberInfo(Map<String, String> valueMap, String id);
	/**
	 * 获取所有学生数量基本情况
	 * @return
	 */
	public List<StudentNumberInfo> getAll();
	
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
