package cn.edu.xmu.service;

import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.DegreeSpot;
import cn.edu.xmu.entity.StudentsAndTeachers;

public interface StudentsAndTeachersService{

	/**
	 *
	 * @param params
	 * @return
	 */
	public List<StudentsAndTeachers> getStudentsAndTeachers(Map params);
}
