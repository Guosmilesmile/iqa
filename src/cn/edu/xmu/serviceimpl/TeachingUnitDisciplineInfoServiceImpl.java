package cn.edu.xmu.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.dao.ClassCultureDao;
import cn.edu.xmu.dao.DisciplineConstructionDao;
import cn.edu.xmu.dao.GraduateAndDoctoralDao;
import cn.edu.xmu.dao.ImportantStudyDao;
import cn.edu.xmu.dao.MajorInfoDao;
import cn.edu.xmu.dao.PostdoctoralMobileStationDao;
import cn.edu.xmu.dao.TeachScientificDao;
import cn.edu.xmu.daoimpl.ClassCultureDaoImpl;
import cn.edu.xmu.daoimpl.DisciplineConstructionDaoImpl;
import cn.edu.xmu.daoimpl.GraduateAndDoctoralDaoImpl;
import cn.edu.xmu.daoimpl.ImportantStudyDaoImpl;
import cn.edu.xmu.daoimpl.MajorInfoDaoImpl;
import cn.edu.xmu.daoimpl.PostdoctoralMobileStationDaoImpl;
import cn.edu.xmu.daoimpl.TeachScientificDaoImpl;
import cn.edu.xmu.entity.DegreeSpot;
import cn.edu.xmu.entity.DisciplineConstruction;
import cn.edu.xmu.entity.ImportantStudy;
import cn.edu.xmu.entity.TeachingUnitDisciplineInfo;
import cn.edu.xmu.service.DegreeSpotService;
import cn.edu.xmu.service.TeachingUnitDisciplineInfoService;
import cn.edu.xmu.table.ClassCultureTable;
import cn.edu.xmu.table.DegreeSpotTable;
import cn.edu.xmu.table.DisciplineConstructionTable;
import cn.edu.xmu.table.FullTimeTeacherInfoTable;
import cn.edu.xmu.table.GraduateAndDoctoralTable;
import cn.edu.xmu.table.ImportantStudyTable;
import cn.edu.xmu.table.MajorInfoTable;
import cn.edu.xmu.table.PostdoctoralMobileStationTable;
import cn.edu.xmu.table.TeachScientificTable;
import cn.edu.xmu.table.TeachersScientificAchievementsTable;

public class TeachingUnitDisciplineInfoServiceImpl implements TeachingUnitDisciplineInfoService{

	private TeachScientificDao teachScientificDao = new TeachScientificDaoImpl();
	private PostdoctoralMobileStationDao postdoctoralMobileStationDao = new PostdoctoralMobileStationDaoImpl();
	private GraduateAndDoctoralDao graduateAndDoctoralDao = new GraduateAndDoctoralDaoImpl();
	private ImportantStudyDao importantStudyDao = new ImportantStudyDaoImpl();
	private ClassCultureDao ccd = new ClassCultureDaoImpl();
	@Override
	public List<TeachingUnitDisciplineInfo> getTeachingUnitDisciplineInfos(Map params) {
		List<TeachingUnitDisciplineInfo> teachingUnitDisciplineInfos = new ArrayList<TeachingUnitDisciplineInfo>();
		
		//根据教学单位名称获取不同的统计值
		String departmentName;//单位
		int bachelorDegree;//本科专业数
		int mobileStationForPostDoctor;//博士后流动站数
		int doctorFirstLevelDiscipline;//博士学位授权一级学科点
		int doctorSecondLevelDiscipline;//博士学位授权二级学科点（不含一级覆盖点）
		int masterFirstLevelDiscipline;//硕士学位授权一级学科点
		int masterSecondLevelDiscipline;//硕士学位授权二级学科点（不含一级覆盖点）
		int keyDiscipline;//重点学科数
		
		String college = "";//所属学院
		String deadline = "";
		
		boolean haveCollege = false;
		boolean haveDeadline = false;
		
		if (!(params == null || params.keySet().size() == 0)) {
			for (Object object : params.keySet()) {
				String key = object.toString();
				String value = (String) params.get(key);
				if (key.equals("college")) {
					haveCollege = true;
					college = value;
					params.remove("college");
					
				}
				if (key.equals("deadline")) {
					haveDeadline = true;
					deadline = value;
					params.remove("deadline");
					
				}
			}
		}else {
			params = new HashMap();
		}
		if (haveCollege) {
			params.put(TeachScientificTable.TS_COLLEGE, college);
		}
		if (haveDeadline) {
			params.put(TeachScientificTable.TS_DEADLINE, deadline);
		}
		
		System.out.println("deadline++++++++++++++++++++++++++"+deadline);
		//首先获得教学单位列表
		List<String> unitsNames = teachScientificDao.getAllUnits(params);
		
		/*删除键值对*/
		if (haveCollege) {
			params.remove(TeachScientificTable.TS_COLLEGE);
		}
		if (haveDeadline) {
			params.remove(TeachScientificTable.TS_DEADLINE);
		}
		
		
		
		
		for (String unitName : unitsNames) {
			departmentName = unitName;//教学单位名称
			
			if (haveCollege) {
				params.put(ImportantStudyTable.IP_COLLEGE, college);
			}
			if (haveDeadline) {
				params.put(ImportantStudyTable.IP_DEADLINE, deadline);
			}
			keyDiscipline = importantStudyDao.getImportantStudyCountByName(params);//重点学科
			
			if (haveCollege) {
				params.remove(ImportantStudyTable.IP_COLLEGE);
			}
			if (haveDeadline) {
				params.remove(ImportantStudyTable.IP_DEADLINE);
			}
			
			if (haveCollege) {
				params.put(ClassCultureTable.C_COLLEGE, college);
			}
			if (haveDeadline) {
				params.put(ClassCultureTable.C_DEADLINE, deadline);
			}
			bachelorDegree = ccd.getClassCultureCountByMajor(params);//本科专业数

			if (haveCollege) {
				params.remove(ClassCultureTable.C_COLLEGE);
			}
			if (haveDeadline) {
				params.remove(ClassCultureTable.C_DEADLINE);
			}
			

			if (haveCollege) {
				params.put(PostdoctoralMobileStationTable.PMS_COLLEGE, college);
			}
			if (haveDeadline) {
				params.put(PostdoctoralMobileStationTable.PMS_DEADLINE, deadline);
			}
			mobileStationForPostDoctor = postdoctoralMobileStationDao.getPostdoctoralMobileStationCountByStation(params);
			if (haveCollege) {
				params.remove(PostdoctoralMobileStationTable.PMS_COLLEGE);
			}
			if (haveDeadline) {
				params.remove(PostdoctoralMobileStationTable.PMS_DEADLINE);
			}
			
			
			
			
			
			if (haveCollege) {
				params.put(GraduateAndDoctoralTable.GD_COLLEGE, college);
			}
			if (haveDeadline) {
				params.put(GraduateAndDoctoralTable.GD_DEADLINE, deadline);
			}
			
			params.put(GraduateAndDoctoralTable.GD_TYPE, "博士学位授权一级学科点");
			doctorFirstLevelDiscipline = graduateAndDoctoralDao.getGraduateAndDoctoralCount(params);//博士学位授权一级学科点
			params.put(GraduateAndDoctoralTable.GD_TYPE, "博士学位授权二级学科点");
			doctorSecondLevelDiscipline = graduateAndDoctoralDao.getGraduateAndDoctoralCount(params);//博士学位授权二级学科点（不含一级覆盖点）
			params.put(GraduateAndDoctoralTable.GD_TYPE, "硕士学位授权一级学科点");
			masterFirstLevelDiscipline = graduateAndDoctoralDao.getGraduateAndDoctoralCount(params);//硕士学位授权一级学科点
			params.put(GraduateAndDoctoralTable.GD_TYPE, "硕士学位授权二级学科点");
			masterSecondLevelDiscipline = graduateAndDoctoralDao.getGraduateAndDoctoralCount(params);//硕士学位授权二级学科点（不含一级覆盖点）
			
			if (haveCollege) {
				params.remove(GraduateAndDoctoralTable.GD_COLLEGE);
			}
			if (haveDeadline) {
				params.remove(GraduateAndDoctoralTable.GD_DEADLINE);
			}
			
			params.remove(GraduateAndDoctoralTable.GD_TYPE);
			
			
			TeachingUnitDisciplineInfo teachingUnitDisciplineInfo = new TeachingUnitDisciplineInfo(departmentName, bachelorDegree, mobileStationForPostDoctor, doctorFirstLevelDiscipline, doctorSecondLevelDiscipline, masterFirstLevelDiscipline, masterSecondLevelDiscipline, keyDiscipline, college);
			teachingUnitDisciplineInfos.add(teachingUnitDisciplineInfo);
		}
		return teachingUnitDisciplineInfos;
	}

}
