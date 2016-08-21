package cn.edu.xmu.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.CoverBuildingArea;

/**
 * 
 * @author xiaoping 数据报表2-1 占地与建筑面积(时点) date 2015-6-30
 *
 */
public interface CoverBuildingAreaDao extends BaseDao<CoverBuildingArea>
{
	/**
	 * 获取占地与建筑面积列表
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
	 * @param deadline 填报期限
	 * @return 占地与建筑面积列表
	 */
	public List<CoverBuildingArea> getCoverBuildingAreaList(int start, int end, String sortStr, String orderStr,
			Map queryParams,Date deadline);

	/**
	 * 根据查询条件获取占地与建筑面积个数
	 * 
	 * @param queryParams
	 *            查询参数
	 * @return 查询到的个数
	 */
	public int getCoverBuildingAreaCount(Map queryParams);

	/**
	 * 获取最大序列号
	 * 
	 * @return
	 */
	public int getMaxSerialNum();

	/**
	 * 根据id批量删除占地与建筑面积
	 * 
	 * @param cbaids
	 *            占地与建筑面积id字符串数组
	 * @return 批量删除成功与否
	 */
	public boolean batchDelete(String[] cbaids) throws SQLException;

	/**
	 * 插入占地与建筑面积
	 * 
	 * @param CoverBuildingArea
	 *            占地与建筑面积实体类
	 * @return 操作成功的个数
	 */
	public int addCoverBuildingArea(CoverBuildingArea CoverBuildingArea);

	/**
	 * 更改占地与建筑面积
	 * 
	 * @param valueMap
	 *            要更改的字段及其更改后的值
	 * @param id
	 * @return 操作成功的个数
	 */
	public int alterCoverBuildingArea(Map<String, String> valueMap, String id);
	
	/**
	 * 获取所有占地与建筑面积
	 * @return
	 */
	public List<CoverBuildingArea> getAll();
	
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
