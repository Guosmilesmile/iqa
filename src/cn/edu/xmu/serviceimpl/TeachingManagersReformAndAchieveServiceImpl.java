package cn.edu.xmu.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.dao.TeachingAwardDao;
import cn.edu.xmu.dao.TeachingResearchAndReformProjectDao;
import cn.edu.xmu.daoimpl.TeachingAwardDaoImpl;
import cn.edu.xmu.daoimpl.TeachingResearchAndReformProjectDaoImpl;
import cn.edu.xmu.entity.TeachersReformAndAchieve;
import cn.edu.xmu.entity.TeachingManagersReformAndAchieve;
import cn.edu.xmu.entity.TeachingResearchAndReformProject;
import cn.edu.xmu.service.TeachersReformAndAchieveService;
import cn.edu.xmu.service.TeachingManagersReformAndAchieveService;
import cn.edu.xmu.table.TeachingAwardTable;
import cn.edu.xmu.table.TeachingResearchAndReformProjectTable;

public class TeachingManagersReformAndAchieveServiceImpl implements TeachingManagersReformAndAchieveService{

	private TeachingAwardDao teachingAwardDao = new TeachingAwardDaoImpl();
	private TeachingResearchAndReformProjectDao teachingResearchAndReformProjectDao = new TeachingResearchAndReformProjectDaoImpl();
	
	
	@Override
	public TeachingManagersReformAndAchieve getTeachingManagersReformAndAchieve(Map params){
		
		 String rowTitle = "数量";//行名
		 
		/*教学管理人员主持教学成果奖（项）*/
		 int achieveTotal = 0;//总数
		 int nationAchieve = 0;//国家级
		 int provinceAchieve = 0;//省部级
		
		/*教学管理人员主持教育教学研究与改革项目*/
		 int projectTotalCount = 0;//项目总数
		 int nationProjectCount = 0;//国家级
		 int provinceProjectCount = 0;//省部级
		 int schoolProjectCount = 0;//校级
		
		/*教学管理人员主持教育教学研究与改革项目(经费)*/
		 int projectTotalFee = 0;//经费总数
		 int nationProjectFee = 0;//国家级
		 int provinceProjectFee = 0;//省部级
		 int schoolProjectFee = 0;//校级
		
		String college = "";
		
		//表7-3-2 教学成果奖
		Map paramsForTeacherAchieve = new HashMap();
		//表7-3-1 教育教学研究与改革项目
		Map paramsForTeachingResearch = new HashMap();
		
		
		if (params == null ) {
			params = new HashMap();
		}else if (params.keySet().size() != 0) {
			for (Object object : params.keySet()) {
				String key = object.toString();
				String value = (String) params.get(key);
				if (key.equals("college")) {
					college = value;
					params.remove("college");
					
					paramsForTeachingResearch.put(TeachingResearchAndReformProjectTable.TRARP_COLLEGE, value);
					paramsForTeacherAchieve.put(TeachingAwardTable.TA_COLLEGE, value);
				}
				if (key.equals("deadline")) {
					params.remove("deadline");
					paramsForTeachingResearch.put(TeachingResearchAndReformProjectTable.TRARP_DEADLINE, value);
					paramsForTeacherAchieve.put(TeachingAwardTable.TA_DEADLINE, value);
				}
			}
		}
	
		 achieveTotal = teachingAwardDao.getTeachingAwardCountInManagerInfo(paramsForTeacherAchieve);
		 paramsForTeacherAchieve.put(TeachingAwardTable.TA_LEVEL, "国家级");
		 nationAchieve = teachingAwardDao.getTeachingAwardCountInManagerInfo(paramsForTeacherAchieve);
		 
		 paramsForTeacherAchieve.put(TeachingAwardTable.TA_LEVEL, "省部级");
		 provinceAchieve = teachingAwardDao.getTeachingAwardCountInManagerInfo(paramsForTeacherAchieve);
		 
		projectTotalCount = teachingResearchAndReformProjectDao.getTeachingResearchAndReformProjectCountInManagerInfo(paramsForTeachingResearch);
		List<TeachingResearchAndReformProject> teachingResearchAndReformProjects = teachingResearchAndReformProjectDao.getTeachingResearchAndReformProjectInManagerInfo(0, projectTotalCount, TeachingResearchAndReformProjectTable.TRARP_ID, "asc", paramsForTeachingResearch);
		for (TeachingResearchAndReformProject teachingResearchAndReformProject : teachingResearchAndReformProjects) {
			float fee = teachingResearchAndReformProject.getTrarp_funds();
			projectTotalFee += fee;
			if ("国家级".equals(teachingResearchAndReformProject.getTrarp_level())) {
				nationProjectFee += fee;
				nationProjectCount ++;
			}
			if ("省部级".equals(teachingResearchAndReformProject.getTrarp_level())) {
				provinceProjectFee += fee;
				provinceProjectFee ++;
			}
			if ("校级".equals(teachingResearchAndReformProject.getTrarp_level())) {
				schoolProjectFee += fee;
				schoolProjectCount ++;
			}
		}
		
		TeachingManagersReformAndAchieve teachingManagersReformAndAchieve = new TeachingManagersReformAndAchieve(rowTitle, achieveTotal, nationAchieve, provinceAchieve, projectTotalCount, nationProjectCount, provinceProjectCount, schoolProjectCount, projectTotalFee, nationProjectFee, provinceProjectFee, schoolProjectFee, college);
		return teachingManagersReformAndAchieve;
	}

}
