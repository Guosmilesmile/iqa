package cn.edu.xmu.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.PublishedMaterials;

/**
 * 
 * @author Luo 附表5-2-1出版教材情况（自然年） 实体类功能接口 date 2015-07-08
 */
public interface PublishedMaterialsDao {
	/**
	 * 插入数据
	 * 
	 * @param pm
	 *            学校概况实体
	 * @return 插入数据结果成功与否
	 */
	public int addRecord(PublishedMaterials pm);

	/**
	 * 
	 * @param pmids
	 * @return
	 * @throws SQLException
	 */
	boolean batchDelete(String[] pmids) throws SQLException;

	/**
	 * 
	 * @param valueMap
	 * @param id
	 * @return
	 */
	public int alterPublishedMaterials(Map<String, String> valueMap, String id);

	/**
	 * 
	 * @param queryParams
	 * @return 根据查询条件返回符合条件的条数
	 */
	public int getPublishedMaterialsCount(Map queryParams);

	/**
	 * 
	 * @param start
	 *            开始的标记
	 * @param end
	 *            结束的标记
	 * @param sortStr
	 *            排序的字段
	 * @param orderStr
	 *            升降选项
	 * @param queryParams
	 *            查询参数
	 * @return
	 */
	public List<PublishedMaterials> getAllPublishedMaterials(int start,
			int end, String sortStr, String orderStr, Map queryParams);

	public List<PublishedMaterials> getAllPublishedMaterials();

	/**
	 * 根据学院和截止日期来覆盖当期的数据
	 * 
	 * @param college
	 * @param deadline
	 * @throws SQLException
	 */
	public void deleteByCollegeandDeadline(String college, Date deadline)
			throws SQLException;

}
