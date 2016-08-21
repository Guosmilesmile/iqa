package cn.edu.xmu.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.FullTimeTeacherInfo;
import cn.edu.xmu.entity.TeachResearchReform;

/**
 * 
 * @author xiaoping 专任教师基本信息 实体类功能接口 date 2015-06-29
 */
public interface FullTimeTeacherInfoDao extends BaseDao<FullTimeTeacherInfo>
{
	/**
	 * 获取专任教师基本信息
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
	 * @return 专任教师基本信息列表
	 */
	public List<FullTimeTeacherInfo> getFullTimeTeachers(int start, int end, String sortStr, String orderStr,
			Map queryParams, Date deadline);
	
	public List<FullTimeTeacherInfo> getFullTimeTeachersByDepartNum(String departNum);
	

	/**
	 * 根据查询条件获取相应专任教师的个数
	 * 
	 * @param queryParams
	 *            查询参数
	 * @return 查询到的个数
	 */
	public int getFullTimeTeacherCount(Map queryParams);
	
	/**
	 * 根据查询条件获取相应的作为专业带头人的专任教师的个数
	 * @param queryParams
	 * @param basicMap
	 * @return
	 */
	public int getFullTimeTeacherCountInMajorHeader(Map queryParams,Map basicMap);

	/**
	 * 获取最大序列号
	 * 
	 * @return
	 */
	public int getMaxSerialNum();

	/**
	 * 根据id批量删除专任教师信息
	 * 
	 * @param smids
	 *            专任教师信息id字符串数组
	 * @return 批量删除成功与否
	 */
	public boolean batchDelete(String[] fttiids) throws SQLException;

	/**
	 * 插入专任教师信息
	 * 
	 * @param fullTimeTeacherInfo
	 *            专任教师信息实体类
	 * @return 操作成功的个数
	 */
	public int addFullTimeTeacher(FullTimeTeacherInfo fullTimeTeacherInfo);


	/**
	 * 更改专任教师信息
	 * 
	 * @param valueMap
	 *            要更改的字段及其更改后的值
	 * @param id
	 * @return 操作成功的个数
	 */
	public int alterFullTimeTeacher(Map<String, String> valueMap, String id);
	
	/**
	 * 根据教工号获取id不同的专任教师
	 * @param workNumber 教工号
	 * @param id 
	 * @return
	 */
	public int getCountByWorkNumber(String workNumber, String id);
	
	
	/**
	 * 根据字段是date型的日期获取某一个范围内的个数
	 * @param param 字段名
	 * @param start 起始日期
	 * @param end 终止日期
	 * @param queryParams 所要查询的字段
	 * @return
	 */
	public int getCountByRange(String param, Date start, Date end, Map queryParams);
	
	/**
	 * 根据字段是date型的日期获取某一个范围内的个数(作为专业带头人)
	 * @param param
	 * @param start
	 * @param end
	 * @param queryParams
	 * @return
	 */
	public int getCountByRangeInMajorHeader(String param, Date start, Date end, Map queryParams,Map basicMap);
	/**
	 * 获取所有专任教师信息
	 * @return
	 */
	public List<FullTimeTeacherInfo> getAll();
	/**
	 * 根绝所属学院和填报期限删除记录
	 * @param college
	 * @param deadline
	 * @return
	 * @throws SQLException
	 */
	public boolean deleteByCollegeandDeadline(String college, Date deadline) throws SQLException;
	
	public List<TeachResearchReform> getReformAndFulltime(Map queryParamsforReform,Map queryParamsforFulltime);
	
	public List<TeachResearchReform> getAwardAndFulltime(Map queryParamsforAward,Map queryParamsforFulltime);
	
	
}
