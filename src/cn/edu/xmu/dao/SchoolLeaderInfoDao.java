package cn.edu.xmu.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.SchoolLeaderInfo;

/**
 * 
 * @author xiaoping 表3-2 校领导基本信息(时点) date 2015-7-3
 *
 */
public interface SchoolLeaderInfoDao extends BaseDao<SchoolLeaderInfo>
{
	/**
	 * 校领导基本信息(时点)
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
	 * @return 校领导基本信息(时点)列表
	 */
	public List<SchoolLeaderInfo> getSchoolLeaderInfos(int start, int end, String sortStr, String orderStr,
			Map queryParams, Date deadline);

	/**
	 * 根据查询条件获取校领导基本信息的个数
	 * 
	 * @param queryParams
	 *            查询参数
	 * @return 查询到的个数
	 */
	public int getSchoolLeaderInfoCount(Map queryParams);

	/**
	 * 获取最大序列号
	 * 
	 * @return
	 */
	public int getMaxSerialNum();

	/**
	 * 根据id批量删除校领导基本信息
	 * 
	 * @param sliids
	 *            校领导基本信息id字符串数组
	 * @return 批量删除成功与否
	 */
	public boolean batchDelete(String[] sliids) throws SQLException;

	/**
	 * 插入校领导基本信息
	 * 
	 * @param highLevelTalent
	 *            校领导基本信息实体类
	 * @return 操作成功的个数
	 */
	public int addSchoolLeaderInfo(SchoolLeaderInfo schoolLeaderInfo);
	
	/**
	 * 更改校领导基本信息
	 * 
	 * @param valueMap
	 *            要更改的字段及其更改后的值
	 * @param id
	 * @return 操作成功的个数
	 */
	public int alterSchoolLeaderInfo(Map<String, String> valueMap, String id);
	
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
	 * 获取所有校领导基本信息
	 * @return
	 */
	public List<SchoolLeaderInfo> getAll();
	
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
