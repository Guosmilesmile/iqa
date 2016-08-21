package cn.edu.xmu.service;

import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.StudentAssociation;
import cn.edu.xmu.entity.StudentAssociationSituation;

public interface StudentAssociationService {
	
	/*
	 * 获得学生社团情况
	 * luo
	 */
	public List<StudentAssociationSituation> getStudentAssociation (Map params);

}
