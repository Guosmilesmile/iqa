package cn.edu.xmu.service;

import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.DegreeSpot;
import cn.edu.xmu.entity.TeacherStructure;
/**
 * 
 * @author zsj
 *
 */
public interface TeacherStructureService{

	/**
	 * 获得教师队伍结构信息(专任教师部分)
	 * @param params
	 * @return
	 */
	public List<TeacherStructure> getTeacherStructureForFull(Map params);
	/**
	 * 获得教师队伍结构信息(外聘教师部分)
	 * @param params
	 * @return
	 */
	public List<TeacherStructure> getTeacherStructureForExternal(Map params);
}
