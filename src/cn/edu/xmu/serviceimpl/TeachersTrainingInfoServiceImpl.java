package cn.edu.xmu.serviceimpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.dao.StartClassDao;
import cn.edu.xmu.dao.TeachScientificDao;
import cn.edu.xmu.dao.TeacherTrainingExchangeDao;
import cn.edu.xmu.dao.TeachingResearchAndReformProjectDao;
import cn.edu.xmu.daoimpl.StartClassDaoImpl;
import cn.edu.xmu.daoimpl.TeachScientificDaoImpl;
import cn.edu.xmu.daoimpl.TeacherTrainingExchangeDaoImpl;
import cn.edu.xmu.daoimpl.TeachingResearchAndReformProjectDaoImpl;
import cn.edu.xmu.entity.ProfessorAndAssociateForUnderSub;
import cn.edu.xmu.entity.TeacherTrainingExchange;
import cn.edu.xmu.entity.TeachersTrainingInfo;
import cn.edu.xmu.entity.TeachingResearchAndReformProject;
import cn.edu.xmu.service.ProfessorAndAssociateForUnderSubService;
import cn.edu.xmu.service.TeachersTrainingInfoService;
import cn.edu.xmu.table.OtherTeacherInfoTable;
import cn.edu.xmu.table.StartClassTable;
import cn.edu.xmu.table.TeacherTrainingExchangeTable;
import cn.edu.xmu.table.TeachingResearchAndReformProjectTable;

public class TeachersTrainingInfoServiceImpl implements TeachersTrainingInfoService{

	private TeacherTrainingExchangeDao teacherTrainingExchangeDao = new TeacherTrainingExchangeDaoImpl();
	private TeachingResearchAndReformProjectDao teachingResearchAndReformProjectDao = new TeachingResearchAndReformProjectDaoImpl();
	
	
	@Override
	public TeachersTrainingInfo gettTeachersTrainingInfo(Map params){
		
		 String rowTitle = "数量";//行名
		 
		 int total = 0;//总计
		 int docterStudyCount = 0;//攻读博士学位
		 double docterStudyPer = 0; // 攻读博士学位占当年培养培训教师的比例（%）
		 int inside = 0;// 境内进修人次数（人次）
		 int outside = 0;// 境外进修人次数（人次）
		 int project = 0;//参与教改立项课题（校级以上）人次数（人次）
		 

		double percent = 0;
		String college = "";
		
		//表3-5-2  教师培训进修、交流情况
		Map paramsForTeacherTraining ;
		//表7-3-1 教育教学研究与改革项目
		Map paramsForTeachingResearch ;
		
		//if (params != null) {
			//paramsForTeacherTraining = new HashMap(params);
			//paramsForTeachingResearch = new HashMap(params);
		//}else {
			paramsForTeacherTraining = new HashMap();
			paramsForTeachingResearch = new HashMap();
		//}
		
		
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
					paramsForTeacherTraining.put(TeacherTrainingExchangeTable.TTE_COLLEGE, value);
				}
				if (key.equals("deadline")) {
					params.remove("deadline");
					paramsForTeachingResearch.put(TeachingResearchAndReformProjectTable.TRARP_DEADLINE, value);
					paramsForTeacherTraining.put(TeacherTrainingExchangeTable.TTE_DEADLINE, value);
				}
			}
		}
	
		List<TeacherTrainingExchange> teacherTrainingExchanges = teacherTrainingExchangeDao.getAllTeacherTrainingExchange(0, teacherTrainingExchangeDao.getTeacherTrainingExchangeCount(paramsForTeacherTraining), TeacherTrainingExchangeTable.TTE_ID, "asc", paramsForTeacherTraining);
		
		
		for (TeacherTrainingExchange teacherTrainingExchange : teacherTrainingExchanges) {
			total += (teacherTrainingExchange.getTte_trainchurchyard() + teacherTrainingExchange.getTte_trainoverseassum());//暂时是计算境内境外的总和
			docterStudyCount += teacherTrainingExchange.getTte_trainfordoctor();
			inside += teacherTrainingExchange.getTte_trainchurchyard();
			outside +=teacherTrainingExchange.getTte_trainoverseassum();
		}
				
		percent = total > 0 ?(double)docterStudyCount / total * 100 : 0;
		docterStudyPer = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  

		System.out.println(docterStudyPer);
		paramsForTeachingResearch.put(TeachingResearchAndReformProjectTable.TRARP_LEVEL, "省部级");
		int provinceProject = teachingResearchAndReformProjectDao.getTeachingResearchAndReformProjectCount(paramsForTeachingResearch);
		paramsForTeachingResearch.put(TeachingResearchAndReformProjectTable.TRARP_LEVEL, "国家级");
		int nativeProject = teachingResearchAndReformProjectDao.getTeachingResearchAndReformProjectCount(paramsForTeachingResearch);
		
		project = provinceProject + nativeProject;

		System.out.println("total-----"+total);
		TeachersTrainingInfo teachersTrainingInfo = new TeachersTrainingInfo(rowTitle, total, docterStudyCount, docterStudyPer, inside, outside, nativeProject, college);
		return teachersTrainingInfo;
	}

}
