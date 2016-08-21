package cn.edu.xmu.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.GraduateAndDoctoral;

/**
 * 表4-1-3  博士点、硕士点 (时点)
 * @author yue
 *
 */
public interface GraduateAndDoctoralDao extends BaseDao<GraduateAndDoctoral>{
	
	//添加记录
	public int addGraduateAndDoctoralRecord(GraduateAndDoctoral gd);
	//批量删除
	boolean batchDelete(String[] gdids) throws SQLException;
	//修改
	public int alterGraduateAndDoctoral(Map<String, String> valueMap, String id);
	//总数量 
	public int getGraduateAndDoctoralCount(Map queryParams);
	//获得
	public List<GraduateAndDoctoral> getGraduateAndDoctoral(int start, int end,
			String sortStr, String orderStr,Map<String, String> params
			,Date deadline);
	void deleteByCollegeandDeadline(String college, Date deadline)
			throws SQLException;

	List<GraduateAndDoctoral> getAllGraduateAndDoctoral();
	



}
