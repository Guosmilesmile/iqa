package cn.edu.xmu.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.MinorDegree;

/**
 * 
 * @author Luo 附表6-1-9-2XXXX年全校获得辅修学位、获得辅修本科证书人数统计表 实体类功能接口 date 2015-07-10
 */
public interface MinorDegreeDao {
	/**
	 * 插入数据
	 * 
	 * @param md
	 *            学校概况实体
	 * @return 插入数据结果成功与否
	 */
	public int addRecord(MinorDegree md);

	/**
	 * 
	 * @param mdids
	 * @return
	 * @throws SQLException
	 */
	boolean batchDelete(String[] mdids) throws SQLException;

	/**
	 * 
	 * @param valueMap
	 * @param id
	 * @return
	 */
	public int alterMinorDegree(Map<String, String> valueMap, String id);

	/**
	 * 
	 * @param queryParams
	 * @return 根据查询条件返回符合条件的条数
	 */
	public int getMinorDegreeCount(Map queryParams);

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
	public List<MinorDegree> getAllMinorDegree(int start, int end,
			String sortStr, String orderStr, Map queryParams);

	public List<MinorDegree> getAllMinorDegree();

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
