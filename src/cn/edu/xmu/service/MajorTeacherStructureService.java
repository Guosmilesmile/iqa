package cn.edu.xmu.service;

import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.MajorTeacherStructure;

public interface MajorTeacherStructureService {
	@SuppressWarnings("rawtypes")
	public List<MajorTeacherStructure> getMajorTeacherStructure(Map params);

	@SuppressWarnings("rawtypes")
	List<MajorTeacherStructure> getMajorTeacherStructureFormFTTI(Map params);

	@SuppressWarnings("rawtypes")
	List<MajorTeacherStructure> getMajorTeacherStructureTOP5LOW5(Map params);
}
