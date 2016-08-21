package cn.edu.xmu.serviceimpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.dao.ClassCultureDao;
import cn.edu.xmu.dao.DisciplineConstructionDao;
import cn.edu.xmu.dao.EmploymentSituationofCollegeGraduatesDao;
import cn.edu.xmu.dao.GraduateAndDoctoralDao;
import cn.edu.xmu.dao.ImportantStudyDao;
import cn.edu.xmu.dao.MajorInfoDao;
import cn.edu.xmu.dao.PostdoctoralMobileStationDao;
import cn.edu.xmu.dao.TeachScientificDao;
import cn.edu.xmu.daoimpl.ClassCultureDaoImpl;
import cn.edu.xmu.daoimpl.DisciplineConstructionDaoImpl;
import cn.edu.xmu.daoimpl.EmploymentSituationofCollegeGraduatesDaoImpl;
import cn.edu.xmu.daoimpl.GraduateAndDoctoralDaoImpl;
import cn.edu.xmu.daoimpl.ImportantStudyDaoImpl;
import cn.edu.xmu.daoimpl.MajorInfoDaoImpl;
import cn.edu.xmu.daoimpl.PostdoctoralMobileStationDaoImpl;
import cn.edu.xmu.daoimpl.TeachScientificDaoImpl;
import cn.edu.xmu.entity.DegreeSpot;
import cn.edu.xmu.entity.DisciplineConstruction;
import cn.edu.xmu.entity.EmploymentSituationofCollegeGraduates;
import cn.edu.xmu.entity.GraduatesEmploymentsDistribution;
import cn.edu.xmu.entity.ImportantStudy;
import cn.edu.xmu.entity.TeachingUnitDisciplineInfo;
import cn.edu.xmu.service.DegreeSpotService;
import cn.edu.xmu.service.GraduatesEmploymentsDistributionService;
import cn.edu.xmu.service.TeachingUnitDisciplineInfoService;
import cn.edu.xmu.table.ClassCultureTable;
import cn.edu.xmu.table.DegreeSpotTable;
import cn.edu.xmu.table.DisciplineConstructionTable;
import cn.edu.xmu.table.EmploymentSituationofCollegeGraduatesTable;
import cn.edu.xmu.table.FullTimeTeacherInfoTable;
import cn.edu.xmu.table.GraduateAndDoctoralTable;
import cn.edu.xmu.table.ImportantStudyTable;
import cn.edu.xmu.table.MajorInfoTable;
import cn.edu.xmu.table.PostdoctoralMobileStationTable;
import cn.edu.xmu.table.TeachScientificTable;
import cn.edu.xmu.table.TeachersScientificAchievementsTable;

public class GraduatesEmploymentsDistributionServiceImpl implements GraduatesEmploymentsDistributionService{

	private EmploymentSituationofCollegeGraduatesDao emdao = new EmploymentSituationofCollegeGraduatesDaoImpl();
	@Override
	public List<GraduatesEmploymentsDistribution> getGraduatesEmploymentsDistributions(Map params) {
		
		
		String rowTitles[] ={"数量","比例(%)"};
		int total = 0;
		//政府机构
		int  gov = 0;
		//事业单位
		int  institution = 0;
		//企业
		int  enterprise = 0;
		//部队
		int  troops = 0;
		//灵活就业
		int  flexibleemployment = 0;
		//升学
		int  entrance = 0;
		//参加国家地方项目就业
		int  nationallocalprojectemployment = 0;
		//其他
		int  others = 0;
		
		String college = "";//所属学院
		double percent = 0;
		
		if (!(params == null || params.keySet().size() == 0)) {
			for (Object object : params.keySet()) {
				String key = object.toString();
				String value = (String) params.get(key);
				if (key.equals("college")) {
					college = value;
					params.remove("college");
					params.put(EmploymentSituationofCollegeGraduatesTable.ESCG_COLLEGE, value);
					
				}
				if (key.equals("deadline")) {
					params.remove("deadline");
					params.put(EmploymentSituationofCollegeGraduatesTable.ESCG_DEADLINE, value);
					
				}
			}

		}	
			
		List<EmploymentSituationofCollegeGraduates> employmentSituationofCollegeGraduates = emdao.getAllEmploymentSituationofCollegeGraduates(0, emdao.getEmploymentSituationofCollegeGraduatesCount(params), EmploymentSituationofCollegeGraduatesTable.ESCG_ID, "asc", params);
		for (EmploymentSituationofCollegeGraduates em : employmentSituationofCollegeGraduates) {
			total += em.getEscg_employmentsum();
			//政府机构
			gov += em.getEscg_gov();
			//事业单位
			institution += em.getEscg_institution();
			//企业
			enterprise += em.getEscg_enterprise();
			//部队
			troops += em.getEscg_troops();
			//灵活就业
			flexibleemployment += em.getEscg_flexibleemployment();
			//升学
			entrance += em.getEsce_entrance();
			//参加国家地方项目就业
			nationallocalprojectemployment += em.getEscg_nationallocalprojectemployment();
			//其他
			others += em.getEscg_others();
		}
		percent = total > 0 ?(double)gov / total * 100 : 0;
		double govPer = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  
		
		percent = total > 0 ?(double)institution / total * 100 : 0;
		double institutionPer = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  
		
		percent = total > 0 ?(double)enterprise / total * 100 : 0;
		double enterprisePer = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  
		
		percent = total > 0 ?(double)troops / total * 100 : 0;
		double troopsPer = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  
		
		percent = total > 0 ?(double)flexibleemployment / total * 100 : 0;
		double flexibleemploymentPer = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  

		percent = total > 0 ?(double)entrance / total * 100 : 0;
		double entrancePer = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  
		
		percent = total > 0 ?(double)nationallocalprojectemployment / total * 100 : 0;
		double nationallocalprojectemploymentPer = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  
		
		double othersPer = 100 - govPer - institutionPer - enterprisePer - troopsPer - flexibleemploymentPer - entrancePer - nationallocalprojectemploymentPer;

		othersPer = new BigDecimal(othersPer).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  
		
		
		
		GraduatesEmploymentsDistribution row1 = new GraduatesEmploymentsDistribution(rowTitles[0], Integer.toString(total), Integer.toString(gov), Integer.toString(institution), Integer.toString(enterprise), Integer.toString(troops), Integer.toString(flexibleemployment), Integer.toString(entrance), Integer.toString(nationallocalprojectemployment), Integer.toString(others), college);
		GraduatesEmploymentsDistribution row2 = new GraduatesEmploymentsDistribution(rowTitles[1], "/", Double.toString(govPer), Double.toString(institutionPer), Double.toString(enterprisePer), Double.toString(troopsPer), Double.toString(flexibleemploymentPer), Double.toString(entrancePer), Double.toString(nationallocalprojectemploymentPer), Double.toString(othersPer), college);
		
		List<GraduatesEmploymentsDistribution> geds = new ArrayList<GraduatesEmploymentsDistribution>();
		geds.add(row1);
		geds.add(row2);
		
		return geds;
	}
}
