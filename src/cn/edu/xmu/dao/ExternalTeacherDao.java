package cn.edu.xmu.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.ExternalTeacher;
/**
 * 
 * @author zhantu
 * 外聘教师基本信息  实体类功能接口
 * date 2015-07-01
 */
public interface ExternalTeacherDao extends BaseDao<ExternalTeacher>{

	
	/**
	 * 插入数据
	 * 
	 * @param et
	 * 		外聘教师基本信息
	 * @return 
	 * 		插入数据结果成功与否
	 */
	public int addRecord(ExternalTeacher et);
	
	
	/**
	 * 
	 * @param etids
	 * @return
	 * @throws SQLException
	 */
	boolean batchDelete(String[] etids) throws SQLException;
	
	/**
	 * 
	 * @param valueMap
	 * @param id
	 * @return
	 */
	public int alterExternalTeacher(Map<String, String> valueMap, String id);
	
	/**
	 * 
	 * @param queryParams
	 * @return 
	 * 		根据查询条件返回符合条件的条数
	 */
	public int getExternalTeacherCount(Map queryParams);
	
	
	/**
	 * 
	 * @param start  开始的标记
	 * @param end    结束的标记
	 * @param sortStr 排序的字段
	 * @param orderStr 升降选项
	 * @param queryParams 查询参数
	 * @return
	 */
	 public List<ExternalTeacher> getAllExternalTeacher(int start, int end,
				String sortStr, String orderStr, Map queryParams);


	List<ExternalTeacher> getExternalTeacher(int start, int end,
			String sortStr, String orderStr, Map<String, String> params,
			Date deadline);
	
	/**
	 * 根据字段是date型的日期获取某一个范围内的个数
	 * @param param 字段名
	 * @param start 起始日期
	 * @param end 终止日期
	 * @return
	 */
	public int getCountByRange(String param, Date start, Date end);


	void deleteByCollegeandDeadline(String college, Date deadline)
			throws SQLException;


	List<ExternalTeacher> getAllExternalTeacher();
}
