package cn.edu.xmu.serviceimpl;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.omg.CORBA.DATA_CONVERSION;

import cn.edu.xmu.dao.DisciplineConstructionDao;
import cn.edu.xmu.dao.ExternalTeacherDao;
import cn.edu.xmu.dao.FullTimeTeacherInfoDao;
import cn.edu.xmu.dao.ImportantStudyDao;
import cn.edu.xmu.dao.MajorInfoDao;
import cn.edu.xmu.daoimpl.DisciplineConstructionDaoImpl;
import cn.edu.xmu.daoimpl.ExternalTeacherDaoImpl;
import cn.edu.xmu.daoimpl.FullTimeTeacherInfoDaoImpl;
import cn.edu.xmu.daoimpl.ImportantStudyDaoImpl;
import cn.edu.xmu.daoimpl.MajorInfoDaoImpl;
import cn.edu.xmu.entity.DegreeSpot;
import cn.edu.xmu.entity.DisciplineConstruction;
import cn.edu.xmu.entity.ImportantStudy;
import cn.edu.xmu.entity.TeacherStructure;
import cn.edu.xmu.service.DegreeSpotService;
import cn.edu.xmu.service.TeacherStructureService;
import cn.edu.xmu.table.DegreeSpotTable;
import cn.edu.xmu.table.DisciplineConstructionTable;
import cn.edu.xmu.table.ExternalTeacherTable;
import cn.edu.xmu.table.FullTimeTeacherInfoTable;
import cn.edu.xmu.table.ImportantStudyTable;
import cn.edu.xmu.table.MajorInfoTable;
import cn.edu.xmu.table.StartClassTable;

public class TeacherStructureServiceImpl implements TeacherStructureService{

	private FullTimeTeacherInfoDao fullTimeTeacherInfoDao = new FullTimeTeacherInfoDaoImpl();
	private ExternalTeacherDao externalTeacherDao = new ExternalTeacherDaoImpl();
	
	
	@Override
	public List<TeacherStructure> getTeacherStructureForFull(Map params){
		
		String rowName[] = {"专任教师","比例（%）"};//行名称
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
					params.put(FullTimeTeacherInfoTable.FTTI_COLLEGE, college);
				}
				if (key.equals("deadline")) {
					params.remove("deadline");
					params.put(FullTimeTeacherInfoTable.FTTI_DEADLINE, value);
				}
			}
		}
	
		int total = fullTimeTeacherInfoDao.getFullTimeTeacherCount(params);
		System.out.println("total------------------"+total);
		
		params.put(FullTimeTeacherInfoTable.FTTI_PROFESSIONALTITLE, "教授");
		int professorCount = fullTimeTeacherInfoDao.getFullTimeTeacherCount(params);
		percent = total > 0 ?(double)professorCount / total * 100 : 0;
		double professorPer = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  

		params.put(FullTimeTeacherInfoTable.FTTI_PROFESSIONALTITLE, "副教授");
		int associateProfessorCount = fullTimeTeacherInfoDao.getFullTimeTeacherCount(params);
		percent = total > 0 ?(double)associateProfessorCount / total * 100 : 0;
		double associateProfessorPer = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  

		
		params.put(FullTimeTeacherInfoTable.FTTI_PROFESSIONALTITLE, "讲师");
		int lecturerCount = fullTimeTeacherInfoDao.getFullTimeTeacherCount(params);
		percent = total > 0 ?(double)lecturerCount / total * 100 : 0;
		double lecturerPer = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  

		params.put(FullTimeTeacherInfoTable.FTTI_PROFESSIONALTITLE, "助教");
		int assistantCount = fullTimeTeacherInfoDao.getFullTimeTeacherCount(params);
		percent = total > 0 ?(double)assistantCount / total * 100 : 0;
		double assistantPer = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  

		
		params.put(FullTimeTeacherInfoTable.FTTI_PROFESSIONALTITLE, "未评级");//暂且认为无职称等价于未评级
		int noTitleCount = fullTimeTeacherInfoDao.getFullTimeTeacherCount(params);
		percent = total > 0 ?(double)noTitleCount / total * 100 : 0;
		double noTitlePer = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  

		/*删除键值对*/
		params.remove(FullTimeTeacherInfoTable.FTTI_PROFESSIONALTITLE);
		
		params.put(FullTimeTeacherInfoTable.FTTI_DEGREE, "博士");
		int doctorCount = fullTimeTeacherInfoDao.getFullTimeTeacherCount(params);
		percent = total > 0 ?(double)doctorCount / total * 100 : 0;
		double doctorPer = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  

		params.put(FullTimeTeacherInfoTable.FTTI_DEGREE, "硕士");
		int masterCount = fullTimeTeacherInfoDao.getFullTimeTeacherCount(params);
		percent = total > 0 ?(double)masterCount / total * 100 : 0;
		double masterPer = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  

		params.put(FullTimeTeacherInfoTable.FTTI_DEGREE, "学士");
		int BachelorCount = fullTimeTeacherInfoDao.getFullTimeTeacherCount(params);
		percent = total > 0 ?(double)BachelorCount / total * 100 : 0;
		double BachelorPer = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  

		params.put(FullTimeTeacherInfoTable.FTTI_DEGREE, "无学位");
		int noDegreeCount = fullTimeTeacherInfoDao.getFullTimeTeacherCount(params);
		percent = total > 0 ?(double)noDegreeCount / total * 100 : 0;
		double noDegreePer = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  

		/*删除键值对*/
		params.remove(FullTimeTeacherInfoTable.FTTI_DEGREE);
		
		Date now = new Date(System.currentTimeMillis());
		Date start = new Date(now.getYear()-35, now.getMonth(), now.getDay());
		Date end = now;
		int below35 = fullTimeTeacherInfoDao.getCountByRange(FullTimeTeacherInfoTable.FTTI_BIRTHDAY, start, end,params);
		percent = total > 0 ?(double)below35 / total * 100 : 0;
		System.out.println("-----------------------------------"+percent);
		double below35Per = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  

		start.setYear(now.getYear() - 45);
		end.setYear(now.getYear() - 36);
		int between36_45 = fullTimeTeacherInfoDao.getCountByRange(FullTimeTeacherInfoTable.FTTI_BIRTHDAY, start, end,params);
		percent = total > 0 ?(double)between36_45 / total * 100 : 0;
		double between36_45Per = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  

		start.setYear(now.getYear() - 55);
		end.setYear(now.getYear() - 46);
		int between46_55 = fullTimeTeacherInfoDao.getCountByRange(FullTimeTeacherInfoTable.FTTI_BIRTHDAY, start, end,params);
		percent = total > 0 ?(double)between46_55 / total * 100 : 0;
		double between46_55Per = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  

		start.setYear(now.getYear() - 100);
		end.setYear(now.getYear() - 56);
		int upon56 = fullTimeTeacherInfoDao.getCountByRange(FullTimeTeacherInfoTable.FTTI_BIRTHDAY, start, end,params);
		percent = total > 0 ?(double)upon56 / total * 100 : 0;
		double upon56Per = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  

		params.put(FullTimeTeacherInfoTable.FTTI_EDUCATIONSOURCE, "本校");
		int selfSchoolCount = fullTimeTeacherInfoDao.getFullTimeTeacherCount(params);
		percent = total > 0 ?(double)selfSchoolCount / total * 100 : 0;
		double selfSchoolPer = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  

		params.put(FullTimeTeacherInfoTable.FTTI_EDUCATIONSOURCE, "境内外校");
		int insideCount = fullTimeTeacherInfoDao.getFullTimeTeacherCount(params);
		percent = total > 0 ?(double)insideCount / total * 100 : 0;
		double insidePer = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  

		params.put(FullTimeTeacherInfoTable.FTTI_EDUCATIONSOURCE, "境外外校");
		int outsideCount = fullTimeTeacherInfoDao.getFullTimeTeacherCount(params);
		percent = total > 0 ?(double)outsideCount / total * 100 : 0;
		double outsidePer = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  

		/*删除键值对*/
		params.remove(FullTimeTeacherInfoTable.FTTI_EDUCATIONSOURCE);
		
		TeacherStructure row1 = new TeacherStructure(rowName[0], Integer.toString(professorCount), Integer.toString(associateProfessorCount), Integer.toString(lecturerCount), Integer.toString(assistantCount), Integer.toString(noTitleCount), Integer.toString(doctorCount), Integer.toString(masterCount), Integer.toString(BachelorCount), Integer.toString(noDegreeCount), Integer.toString(below35), Integer.toString(between36_45), Integer.toString(between46_55), Integer.toString(upon56), Integer.toString(selfSchoolCount), Integer.toString(insideCount), Integer.toString(outsideCount), college);
		TeacherStructure row2 = new TeacherStructure(rowName[1], Double.toString(professorPer), Double.toString(associateProfessorPer), Double.toString(lecturerPer), Double.toString(assistantPer), Double.toString(noTitlePer), Double.toString(doctorPer), Double.toString(masterPer), Double.toString(BachelorPer), Double.toString(noDegreePer), Double.toString(below35Per), Double.toString(between36_45Per), Double.toString(between46_55Per), Double.toString(upon56Per), Double.toString(selfSchoolPer), Double.toString(insidePer), Double.toString(outsidePer), college);
		
		/*清除多余的参数*/
		params.remove(FullTimeTeacherInfoTable.FTTI_COLLEGE);
		
		List<TeacherStructure> teacherStructures  = new ArrayList<TeacherStructure>();
		teacherStructures.add(row1);
		teacherStructures.add(row2);
		return teacherStructures;
	}


	@Override
	public List<TeacherStructure> getTeacherStructureForExternal(Map params) {
		String rowName[] = {"外聘教师","比例（%）"};//行名称
		
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
					params.put(ExternalTeacherTable.ET_COLLEGE, college);
				}
			}
		}
	
		int total = externalTeacherDao.getExternalTeacherCount(params);
		
		
		params.put(ExternalTeacherTable.ET_PROFESSIONAL, "教授");
		int professorCount = externalTeacherDao.getExternalTeacherCount(params);
		percent = total > 0 ?(double)professorCount / total * 100 : 0;
		double professorPer = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  

		params.put(ExternalTeacherTable.ET_PROFESSIONAL, "副教授");
		int associateProfessorCount = externalTeacherDao.getExternalTeacherCount(params);
		percent = total > 0 ?(double)associateProfessorCount / total * 100 : 0;
		double associateProfessorPer = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  
		
		params.put(ExternalTeacherTable.ET_PROFESSIONAL, "讲师");
		int lecturerCount = externalTeacherDao.getExternalTeacherCount(params);
		percent = total > 0 ?(double)lecturerCount / total * 100 : 0;
		double lecturerPer = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  

		params.put(ExternalTeacherTable.ET_PROFESSIONAL, "助教");
		int assistantCount = externalTeacherDao.getExternalTeacherCount(params);
		percent = total > 0 ?(double)assistantCount / total * 100 : 0;
		double assistantPer = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  

		
		params.put(ExternalTeacherTable.ET_PROFESSIONAL, "未评级");//暂且认为无职称等价于未评级
		int noTitleCount = externalTeacherDao.getExternalTeacherCount(params);
		percent = total > 0 ?(double)noTitleCount / total * 100 : 0;
		double noTitlePer = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  

		/*删除键值对*/
		params.remove(ExternalTeacherTable.ET_PROFESSIONAL);
		
		
		params.put(ExternalTeacherTable.ET_TOPEDUCATION, "博士");
		int doctorCount = externalTeacherDao.getExternalTeacherCount(params);
		percent = total > 0 ?(double)doctorCount / total * 100 : 0;
		double doctorPer = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  

		params.put(ExternalTeacherTable.ET_TOPEDUCATION, "硕士");
		int masterCount = externalTeacherDao.getExternalTeacherCount(params);
		percent = total > 0 ?(double)masterCount / total * 100 : 0;
		double masterPer = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  

		params.put(ExternalTeacherTable.ET_TOPEDUCATION, "学士");
		int BachelorCount = externalTeacherDao.getExternalTeacherCount(params);
		percent = total > 0 ?(double)BachelorCount / total * 100 : 0;
		double BachelorPer = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  

		params.put(ExternalTeacherTable.ET_TOPEDUCATION, "无学位");
		int noDegreeCount = externalTeacherDao.getExternalTeacherCount(params);
		percent = total > 0 ?(double)noDegreeCount / total * 100 : 0;
		double noDegreePer = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  

		/*删除键值对*/
		params.remove(ExternalTeacherTable.ET_TOPEDUCATION);
		
		Date now = new Date(System.currentTimeMillis());
		Date start = new Date(now.getYear()-35, now.getMonth(), now.getDay());
		Date end = now;
		int below35 = externalTeacherDao.getCountByRange(ExternalTeacherTable.ET_BIRTH, start, end);
		percent = total > 0 ?(double)below35 / total * 100 : 0;
		double below35Per = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  

		start.setYear(now.getYear() - 45);
		end.setYear(now.getYear() - 36);
		int between36_45 = externalTeacherDao.getCountByRange(ExternalTeacherTable.ET_BIRTH, start, end);
		percent = total > 0 ?(double)between36_45 / total * 100 : 0;
		double between36_45Per = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  

		start.setYear(now.getYear() - 55);
		end.setYear(now.getYear() - 46);
		int between46_55 = externalTeacherDao.getCountByRange(ExternalTeacherTable.ET_BIRTH, start, end);
		percent = total > 0 ?(double)between46_55 / total * 100 : 0;
		double between46_55Per = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  

		start.setYear(now.getYear() - 100);
		end.setYear(now.getYear() - 56);
		int upon56 = externalTeacherDao.getCountByRange(ExternalTeacherTable.ET_BIRTH, start, end);
		percent = total > 0 ?(double)upon56 / total * 100 : 0;
		double upon56Per = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  

		params.put(ExternalTeacherTable.ET_AREA, "本校");
		int selfSchoolCount = externalTeacherDao.getExternalTeacherCount(params);
		percent = total > 0 ?(double)selfSchoolCount / total * 100 : 0;
		double selfSchoolPer = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  

		params.put(ExternalTeacherTable.ET_AREA, "境内");
		int insideCount = externalTeacherDao.getExternalTeacherCount(params);
		percent = total > 0 ?(double)insideCount / total * 100 : 0;
		double insidePer = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  

		params.put(ExternalTeacherTable.ET_AREA, "境外_国外及港澳台");
		int outsideCount = externalTeacherDao.getExternalTeacherCount(params);
		percent = total > 0 ?(double)outsideCount / total * 100 : 0;
		double outsidePer = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  

		/*删除键值对*/
		params.remove(ExternalTeacherTable.ET_AREA);
		
		/*清除多余的参数*/
		params.remove(ExternalTeacherTable.ET_COLLEGE);
		
		TeacherStructure row1 = new TeacherStructure(rowName[0], Integer.toString(professorCount), Integer.toString(associateProfessorCount), Integer.toString(lecturerCount), Integer.toString(assistantCount), Integer.toString(noTitleCount), Integer.toString(doctorCount), Integer.toString(masterCount), Integer.toString(BachelorCount), Integer.toString(noDegreeCount), Integer.toString(below35), Integer.toString(between36_45), Integer.toString(between46_55), Integer.toString(upon56), Integer.toString(selfSchoolCount), Integer.toString(insideCount), Integer.toString(outsideCount), college);
		TeacherStructure row2 = new TeacherStructure(rowName[1], Double.toString(professorPer), Double.toString(associateProfessorPer), Double.toString(lecturerPer), Double.toString(assistantPer), Double.toString(noTitlePer), Double.toString(doctorPer), Double.toString(masterPer), Double.toString(BachelorPer), Double.toString(noDegreePer), Double.toString(below35Per), Double.toString(between36_45Per), Double.toString(between46_55Per), Double.toString(upon56Per), Double.toString(selfSchoolPer), Double.toString(insidePer), Double.toString(outsidePer), college);
		
		List<TeacherStructure> teacherStructures  = new ArrayList<TeacherStructure>();
		teacherStructures.add(row1);
		teacherStructures.add(row2);
		return teacherStructures;
	}

}
