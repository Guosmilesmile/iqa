package cn.edu.xmu.service;

import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.SubjectsEduPractice;

public interface SubjectEduPracticeService {

	public List<SubjectsEduPractice> get(Map<String, String> filter);
}
