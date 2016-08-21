package cn.edu.xmu.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.dao.DisciplineConstructionDao;
import cn.edu.xmu.dao.ImportantStudyDao;
import cn.edu.xmu.dao.MajorInfoDao;
import cn.edu.xmu.daoimpl.DisciplineConstructionDaoImpl;
import cn.edu.xmu.daoimpl.ImportantStudyDaoImpl;
import cn.edu.xmu.daoimpl.MajorInfoDaoImpl;
import cn.edu.xmu.entity.DegreeSpot;
import cn.edu.xmu.entity.DisciplineConstruction;
import cn.edu.xmu.entity.ImportantStudy;
import cn.edu.xmu.service.DegreeSpotService;
import cn.edu.xmu.table.DegreeSpotTable;
import cn.edu.xmu.table.DisciplineConstructionTable;
import cn.edu.xmu.table.ImportantStudyTable;
import cn.edu.xmu.table.MajorInfoTable;

public class DegreeSpotServiceImpl implements DegreeSpotService{

	private DisciplineConstructionDao dcd = new DisciplineConstructionDaoImpl();
	private ImportantStudyDao importantStudyDao = new ImportantStudyDaoImpl();
	@Override
	public DegreeSpot getDegreeSpot(Map params) {
		//来自表4-1-1  学科建设
		int mobileStationForPostDoctor = 0;//博士后流动站
		int doctorFirstLevelDiscipline = 0;//博士学位授权一级学科点
		int doctorSecondLevelDiscipline = 0;//博士学位授权二级学科点（不含一级覆盖点）
		int masterFirstLevelDiscipline = 0;//硕士学位授权一级学科点
		int masterSecondLevelDiscipline = 0;//硕士学位授权二级学科点（不含一级覆盖点）
		int bachelorDegreeTotal = 0;//本科专业总数
		int bachelorDegreeNew = 0;//本科专业中的新专业
		//来自表4-1-4  重点学科
		int keyDiscipline = 0;//重点学科数
		
		String college = "";
		
		//学科建设
		Map param = new HashMap();
		if (!(params == null || params.keySet().size() == 0)) {
			for (Object object : params.keySet()) {
				String key = object.toString();
				String value = (String) params.get(key);
				if (key.equals("college")) {
					param.put(DisciplineConstructionTable.DC_COLLEGE, value);
					college = value;
				}
				if (key.equals("deadline")) {
					params.remove("deadline");
					param.put(DisciplineConstructionTable.DC_DEADLINE, value);
				}
			}
		}
		List<DisciplineConstruction> dConstructions  = dcd.getAllDisciplineConstruction(0, dcd.getDisciplineConstructionCount(param), DisciplineConstructionTable.DC_ID, "asc", param);
		for (DisciplineConstruction disciplineConstruction : dConstructions) {
			mobileStationForPostDoctor += disciplineConstruction.getDc_doctorstation();//博士后流动站
			doctorFirstLevelDiscipline += disciplineConstruction.getDc_docgrantone();//博士学位授权一级学科点
			doctorSecondLevelDiscipline += disciplineConstruction.getDc_docgranttwo();//博士学位授权二级学科点（不含一级覆盖点）
			masterFirstLevelDiscipline += disciplineConstruction.getDc_masgrantone();//硕士学位授权一级学科点
			masterSecondLevelDiscipline += disciplineConstruction.getDc_masgranttwo();//硕士学位授权二级学科点（不含一级覆盖点）
			bachelorDegreeTotal += disciplineConstruction.getDc_undertotal();//本科专业总数
			bachelorDegreeNew += disciplineConstruction.getDc_undernew();//本科专业中的新专业
		}
		//重点学科
		param = new HashMap();
		
		if (!(params == null || params.keySet().size() == 0)) {
			for (Object object : params.keySet()) {
				String key = object.toString();
				String value = params.get(key).toString();
				if (key.equals("college")) {
					param.put(ImportantStudyTable.IP_COLLEGE, value);
				}
				if (key.equals("deadline")) {
					param.put(ImportantStudyTable.IP_DEADLINE, value);
				}
			}
		}
		keyDiscipline = importantStudyDao.getImportantStudyCountCount(param);
		
		
		DegreeSpot degreeSpot = new DegreeSpot(mobileStationForPostDoctor, doctorFirstLevelDiscipline, doctorSecondLevelDiscipline, masterFirstLevelDiscipline, masterSecondLevelDiscipline, bachelorDegreeTotal, bachelorDegreeNew, keyDiscipline, college);
		return degreeSpot;
	}

}
