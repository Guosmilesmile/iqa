package cn.edu.xmu.serviceimpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.dao.StartClassDao;
import cn.edu.xmu.daoimpl.StartClassDaoImpl;
import cn.edu.xmu.entity.ProfessorAndAssociateForUnderSub;
import cn.edu.xmu.service.ProfessorAndAssociateForUnderSubService;
import cn.edu.xmu.table.OtherTeacherInfoTable;
import cn.edu.xmu.table.StartClassTable;

public class ProfessorAndAssociateForUnderSubServiceImpl implements ProfessorAndAssociateForUnderSubService{

	private StartClassDao startClassDao = new StartClassDaoImpl();
	
	
	@Override
	public List<ProfessorAndAssociateForUnderSub> getProfessorAndAssociateForUnderSub(Map params){
		
		String rowName[] = {"数量","百分比（%）"};//行名称
		double percent = 0;
		String college = "";
		
		if (params == null ) {
			params = new HashMap();
		}else if (params.keySet().size() != 0) {
			for (Object object : params.keySet()) {
				String key = object.toString();
				String value = (String) params.get(key);
				if (key.equals("college")) {
					college = value;
					params.remove("college");
					params.put(StartClassTable.SC_COLLEGE, college);
				}
				if (key.equals("deadline")) {
					params.remove("deadline");
					params.put(StartClassTable.SC_DEADLINE, value);
				}
			}
		}
	
		int total = startClassDao.getStartClassCount(params);
		System.out.println("total------------------"+total);
		
		params.put(StartClassTable.SC_ISOUTSIDETEACHER, "否");//不包含外聘教师
		
		params.put(StartClassTable.SC_TEACHERTITLE, "副教授");
		int assoLessonCount = startClassDao.getStartClassCount(params);
		percent = total > 0 ?assoLessonCount / total * 100 : 0;
		double assoLessonPer = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  

		params.put(StartClassTable.SC_TEACHERTITLE, "教授");
		int proLessonCount = startClassDao.getStartClassCount(params);
		percent = total > 0 ?proLessonCount / total * 100 : 0;
		double proLessonPer = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  

		//一下都是教授
		params.put(StartClassTable.SC_COURSECATEGORY, "专业");
		int proproLessonCount = startClassDao.getStartClassCount(params);
		percent = total > 0 ?proproLessonCount / total * 100 : 0;
		double proproLessonPer = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  

		params.put(StartClassTable.SC_COURSECATEGORY, "公共必修课");
		int proCommLessonCount = startClassDao.getStartClassCount(params);
		percent = total > 0 ?proCommLessonCount / total * 100 : 0;
		double proCommLessonPer = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  

		
		ProfessorAndAssociateForUnderSub row1 = new ProfessorAndAssociateForUnderSub(rowName[0], Integer.toString(total),Integer.toString(proLessonCount), Integer.toString(assoLessonCount), Integer.toString(proproLessonCount), Integer.toString(proCommLessonCount),college);
		ProfessorAndAssociateForUnderSub row2 = new ProfessorAndAssociateForUnderSub(rowName[1],"/", Double.toString(proLessonPer), Double.toString(assoLessonPer), Double.toString(proproLessonPer), Double.toString(proCommLessonPer), college);

		List<ProfessorAndAssociateForUnderSub> professorAndAssociateForUnderSubs  = new ArrayList<ProfessorAndAssociateForUnderSub>();
		professorAndAssociateForUnderSubs.add(row1);
		professorAndAssociateForUnderSubs.add(row2);
		return professorAndAssociateForUnderSubs;
	}

}
