package cn.edu.xmu.service;

import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.StudentManagerStructure;

/**
 * 
 * @author xiaoping 5.3 学生管理人员结构 date 2015-7-18
 *
 */
public interface StudentManagerStructureService
{
	/**
	 * 获得校级学生管理人员数量及比例
	 * @param params
	 * @return
	 */
	public List<StudentManagerStructure> getSchoolStuManagerStructures(Map params);
	
	/**
	 * 获得院系学生管理人员数量及比例
	 * @param params
	 * @return
	 */
	public List<StudentManagerStructure> getCollegeStuManagerStructures(Map params);
	
	/**
	 * 学生管理人员结构
	 * @param params
	 * @return
	 */
	public List<StudentManagerStructure> getStuManagerStructure(Map params);
}
