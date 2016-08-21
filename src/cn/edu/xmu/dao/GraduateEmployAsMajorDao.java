package cn.edu.xmu.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.GraduateEmployAsMajor;

/**
 * 表6-1-9-3 应届本科毕业生分专业就业情况（时点）
 * @author chunfeng
 *
 */
public interface GraduateEmployAsMajorDao extends BaseDao<GraduateEmployAsMajor>{
	public List<GraduateEmployAsMajor> getGraduateEmployAsMajors(int start, int end,
			String sortStr, String orderStr,Map<String, String> params, Date deadline);
	
	public GraduateEmployAsMajor getGraduateEmployAsMajorsByMajor(String majorCode);
	
	public int getGraduateEmployAsMajorCount(Map queryParams);
	
	boolean batchDelete(String[] geamids) throws SQLException;
	
	public int addGraduateEmployAsMajor(GraduateEmployAsMajor graduateEmployAsMajor);
	
	public int alterGraduateEmployAsMajor(Map<String, String> valueMap, String id);
	
	public List<GraduateEmployAsMajor> getAllGraduateEmployAsMajors();
	
	public void deleteAll();
	
	public void deleteByCollegeandDeadline(String college,Date deadline) throws SQLException;

}
