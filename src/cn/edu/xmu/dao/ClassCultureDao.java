package cn.edu.xmu.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.ClassCulture;

/**
 * 
 * @author Gy
 * 大类培养基本情况  实体类功能接口
 * date 2015-06-29
 */
public interface ClassCultureDao{
	/**
	 * 分页得到实体
	 * @param start
	 * 			开始
	 * @param end
	 * 			结束
	 * @param sortStr
	 * 				
	 * @param orderStr
	 * @return  list实体
	 */
	public List<ClassCulture> getAllClassCultures(int start, int end,
			String sortStr, String orderStr);
	
	public List<ClassCulture> getAllClassCultures(int start, int end,
			String sortStr, String orderStr,Map queryParams);
	
	/**
	 * 
	 * @return
	 */
	
	/**
	 * 专业名不为空
	 * @param params
	 * @return
	 */
	public int getClassCultureCountByMajor(Map params);
	
	/**
	 * 批量删除
	 * @param smids id数组
	 * @return true为成功  false为失败
	 * @throws SQLException
	 */
	boolean batchDelete(String[] smids) throws SQLException;
	
	/**
	 * 在数据行中增加数据
	 * @param classCulture 
	 * @return
	 */
	public int addClassCulture(ClassCulture classCulture);
	
	/**
	 *  修改数据
	 * @param valueMap
	 * 			map  键值对。 
	 * @param id
	 * 			
	 * @return asd 
	 */
	public int alterClassCulture(Map<String, String> valueMap, String id);
	
	/**
	 * 得到所有的数据
	 * @return
	 */
	public List<ClassCulture> getAllClassCultures();
	
	public void deleteAll();
	
	/**
	 * 在最后一行插入数据
	 * @param classCulture
	 * @return
	 */
	public int addClassCultureDaoRecord(ClassCulture classCulture);
	
	
	public void deleteByCollegeandDeadline(String college, Date deadline) throws SQLException;

	int getClassCultureCounts(Map queryParams);
	
}
