package cn.edu.xmu.dao;

import java.util.List;

import cn.edu.xmu.entity.UserExam;

public interface UserExamDao extends BaseDao<UserExam>{
	public List<UserExam> getUserExams(String userid);
	public boolean ChangeExamSituation(int id,int flag,String userid);
}
