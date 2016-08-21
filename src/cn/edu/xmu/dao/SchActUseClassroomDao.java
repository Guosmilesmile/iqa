package cn.edu.xmu.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.SchActUseClassroom;
/**
 * 
 * @author xiaoping 数据报表2-3-1全校性实际使用的教室（时点） date 2015-7-6
 *
 */
public interface SchActUseClassroomDao extends BaseDao<SchActUseClassroom>
{
	/**
	 * 获取全校性实际使用的教室（时点）
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
	 * @return 全校性实际使用的教室列表
	 */
	public List<SchActUseClassroom> getSchActUseClassrooms(int start, int end, String sortStr, String orderStr,
			Map queryParams, Date deadline);

	/**
	 * 根据查询条件获取相应全校性实际使用的教室的个数
	 * 
	 * @param queryParams
	 *            查询参数
	 * @return 查询到的个数
	 */
	public int getSchActUseClassroomCount(Map queryParams);

	/**
	 * 获取最大序列号
	 * 
	 * @return
	 */
	public int getMaxSerialNum();

	/**
	 * 根据id批量删除全校性实际使用的教室
	 * 
	 * @param hltids
	 *            id字符串数组
	 * @return 批量删除成功与否
	 */
	public boolean batchDelete(String[] saucids) throws SQLException;

	/**
	 * 添加全校性实际使用的教室
	 * 
	 * @param highLevelTalent
	 *           全校性实际使用的教室实体类
	 * @return 操作成功的个数
	 */
	public int addSchActUseClassroom(SchActUseClassroom schActUseClassroom);
	
	/**
	 * 更改全校性实际使用的教室
	 * 
	 * @param valueMap
	 *            要更改的字段及其更改后的值
	 * @param id
	 * @return 操作成功的个数
	 */
	public int alterSchActUseClassroom(Map<String, String> valueMap, String id);
	
	/**
	 * 根据参数获取相匹配的第一个值，主要用来获取‘总计’和‘小计’
	 * @param equalParams 相等参数对
	 *  @param equalParams 不相等参数对
	 * @return 第一个匹配的值
	 */
	public List<SchActUseClassroom> getEqualSchActUseClassroom(Map<String, String> equalParams, Map<String, String> notEqualParams);
	
	/**
	 * 获取所有全校性实际使用的教室
	 * @return
	 */
	public List<SchActUseClassroom> getAll();
	
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
