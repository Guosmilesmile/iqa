package cn.edu.xmu.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.GraduationSituation;
import cn.edu.xmu.entity.SchoolNet;

/**
 * 
 * @author Luo 附表6-1-9-1XXXX届本科毕业生分专业毕业情况实体类功能接口 date 2015-07-10
 */
public interface GraduationSituationDao {
	/**
	 * 插入数据
	 * 
	 * @param gs
	 *            学校概况实体
	 * @return 插入数据结果成功与否
	 */
	public int addRecord(GraduationSituation gs);

	/**
	 * 
	 * @param gsids
	 * @return
	 * @throws SQLException
	 */
	boolean batchDelete(String[] gsids) throws SQLException;

	/**
	 * 
	 * @param valueMap
	 * @param id
	 * @return
	 */
	public int alterGraduationSituation(Map<String, String> valueMap, String id);

	/**
	 * 
	 * @param queryParams
	 * @return 根据查询条件返回符合条件的条数
	 */
	public int getGraduationSituationCount(Map queryParams);

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
	public List<GraduationSituation> getAllGraduationSituation(int start,
			int end, String sortStr, String orderStr, Map queryParams);

	public List<GraduationSituation> getAllGraduationSituation();
	
	public GraduationSituation getGraduationSituationByMajor(String majorCode);

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
