package cn.edu.xmu.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.RegUndergraProfesStuNum;

/**
 * 
 * @author xiaoping 数据报表6-1-2 普通本科分专业（大类）学生数（时点） date 2015-7-5
 *
 */
public interface RegUndergraProfesStuNumDao extends BaseDao<RegUndergraProfesStuNum>
{
	/**
	 * 获取普通本科分专业（大类）学生数(时点)
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
	 * @return 普通本科分专业学生数列表
	 */
	public List<RegUndergraProfesStuNum> getRegUndergraProfesStuNums(int start, int end, String sortStr, String orderStr,
			Map queryParams, Date deadline);

	/**
	 * 根据查询条件获取相应普通本科分专业学生数的个数
	 * 
	 * @param queryParams
	 *            查询参数
	 * @return 查询到的个数
	 */
	public int getRegUndergraProfesStuNumCount(Map queryParams);
	
	/**
	 * 获取最大序列号
	 * 
	 * @return
	 */
	public int getMaxSerialNum();

	/**
	 * 根据id批量删除普通本科分专业学生数
	 * 
	 * @param hltids
	 *            高层次人才id字符串数组
	 * @return 批量删除成功与否
	 */
	public boolean batchDelete(String[] rupsnids) throws SQLException;

	/**
	 * 添加普通本科分专业学生数
	 * 
	 * @param highLevelTalent
	 *            普通本科分专业学生数实体类
	 * @return 操作成功的个数
	 */
	public int addRegUndergraProfesStuNum(RegUndergraProfesStuNum regUndergraProfesStuNum);
	
	/**
	 * 更改普通本科分专业学生数
	 * 
	 * @param valueMap
	 *            要更改的字段及其更改后的值
	 * @param id
	 * @return 操作成功的个数
	 */
	public int alterRegUndergraProfesStuNum(Map<String, String> valueMap, String id);
	/**
	 * 获取所有普通本科分专业学生数
	 * @return
	 */
	public List<RegUndergraProfesStuNum> getAll();
	
	/**
	 * 根据所属学院和填报日期删除数据
	 * @param college
	 * @param deadline
	 * @return
	 * @throws SQLException
	 */
	public boolean deleteByCollegeandDeadline(String college, Date deadline)
			throws SQLException;
	
	
	public RegUndergraProfesStuNum getByMajorCode(String majorCode);
}
