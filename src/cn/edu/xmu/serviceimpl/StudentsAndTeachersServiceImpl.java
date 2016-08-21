package cn.edu.xmu.serviceimpl;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.dao.ExternalTeacherDao;
import cn.edu.xmu.dao.FullTimeTeacherInfoDao;
import cn.edu.xmu.dao.StartClassDao;
import cn.edu.xmu.dao.StudentNumberInfoDao;
import cn.edu.xmu.dao.TeachingAwardDao;
import cn.edu.xmu.dao.TeachingResearchAndReformProjectDao;
import cn.edu.xmu.daoimpl.ExternalTeacherDaoImpl;
import cn.edu.xmu.daoimpl.FullTimeTeacherInfoDaoImpl;
import cn.edu.xmu.daoimpl.StartClassDaoImpl;
import cn.edu.xmu.daoimpl.StudentNumberInfoDaoImpl;
import cn.edu.xmu.daoimpl.TeachingAwardDaoImpl;
import cn.edu.xmu.daoimpl.TeachingResearchAndReformProjectDaoImpl;
import cn.edu.xmu.entity.StudentNumberInfo;
import cn.edu.xmu.entity.StudentsAndTeachers;
import cn.edu.xmu.entity.TeachersReformAndAchieve;
import cn.edu.xmu.entity.TeachingResearchAndReformProject;
import cn.edu.xmu.service.StudentsAndTeachersService;
import cn.edu.xmu.service.TeachersReformAndAchieveService;
import cn.edu.xmu.table.ExternalTeacherTable;
import cn.edu.xmu.table.FullTimeTeacherInfoTable;
import cn.edu.xmu.table.StartClassTable;
import cn.edu.xmu.table.StudentNumberInfoTable;
import cn.edu.xmu.table.TeachingAwardTable;
import cn.edu.xmu.table.TeachingResearchAndReformProjectTable;

public class StudentsAndTeachersServiceImpl implements StudentsAndTeachersService{

	private FullTimeTeacherInfoDao fullTimeTeacherInfoDao = new FullTimeTeacherInfoDaoImpl();
	private ExternalTeacherDao externalTeacherDao = new ExternalTeacherDaoImpl();
	
	private StartClassDao startClassDao = new StartClassDaoImpl();
	private StudentNumberInfoDao studentNumberInfoDao = new StudentNumberInfoDaoImpl();
	
	
	@Override
	public List<StudentsAndTeachers> getStudentsAndTeachers(Map params){
		
		String rowNames[] = {"数量","百分比(%)"};//行名称
		/*专任教师*/
		int fullTotal;
		int master;
		int doctor;
		int doubleType;
		int engineeringBack;
		int industryBack;
		/*外聘教师*/
		int foreignTotal;
		int outside;
		/*折合在校生*/
		double studentsNum;
		/*师生比*/
		double proportion;
		/*本科课程授课教师数*/
		int underLessonTeacherNum;
		String college = "";
		double percent = 0;
		Date deadline = null;
		//专任教师
		Map paramsForFull;
		//外聘教师
		Map paramsForForeign;
		//学生数量
		Map paramsForStuNum;
		//开课情况
		Map paramsForOpenClass;
		
		paramsForFull = new HashMap();
		paramsForForeign = new HashMap();
		paramsForStuNum = new HashMap();
		paramsForOpenClass = new HashMap();

		
		if (params == null ) {
			params = new HashMap();
		}else if (params.keySet().size() != 0) {
			for (Object object : params.keySet()) {
				String key = object.toString();
				String value = (String) params.get(key);
				if (key.equals("college")) {
					college = value;
					params.remove("college");
					paramsForFull.put(FullTimeTeacherInfoTable.FTTI_COLLEGE, value);
					paramsForForeign.put(ExternalTeacherTable.ET_COLLEGE, value);
					paramsForStuNum.put(StudentNumberInfoTable.SNI_COLLEGE, value);
					paramsForOpenClass.put(StartClassTable.SC_COLLEGE, value);
				}
				if (key.equals("deadline")) {
					deadline = strToDate(value);
					params.remove("deadline");
					paramsForFull.put(FullTimeTeacherInfoTable.FTTI_DEADLINE, value);
					paramsForForeign.put(ExternalTeacherTable.ET_DEADLINE, value);
					paramsForStuNum.put(StudentNumberInfoTable.SNI_DEADLINE, value);
					paramsForOpenClass.put(StartClassTable.SC_DEADLINE, value);
				}
			}
		}
	
		 fullTotal = fullTimeTeacherInfoDao.getFullTimeTeacherCount(paramsForFull);
		 paramsForFull.put(FullTimeTeacherInfoTable.FTTI_DEGREE, "硕士");
		 master = fullTimeTeacherInfoDao.getFullTimeTeacherCount(paramsForFull);
		 percent = fullTotal > 0 ?(double)master / fullTotal * 100 : 0;
		 double masterPer = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  

		 paramsForFull.put(FullTimeTeacherInfoTable.FTTI_DEGREE, "博士");
		 doctor = fullTimeTeacherInfoDao.getFullTimeTeacherCount(paramsForFull);
		 percent = fullTotal > 0 ?(double)doctor / fullTotal * 100 : 0;
		 double doctorPer = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  

		 /*删除键值对*/
		 paramsForFull.remove(FullTimeTeacherInfoTable.FTTI_DEGREE);
		 
		 paramsForFull.put(FullTimeTeacherInfoTable.FTTI_IFDOUBLEQUALIFIEDTEACHER, "是");
		 doubleType = fullTimeTeacherInfoDao.getFullTimeTeacherCount(paramsForFull);
		 percent = fullTotal > 0 ?(double)doubleType / fullTotal * 100 : 0;
		 double doubleTypePer = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  

		 /*删除键值对*/
		 paramsForFull.remove(FullTimeTeacherInfoTable.FTTI_IFDOUBLEQUALIFIEDTEACHER);
		 
		 paramsForFull.put(FullTimeTeacherInfoTable.FTTI_IFENGINEERINBACKGROUND, "是");
		 engineeringBack = fullTimeTeacherInfoDao.getFullTimeTeacherCount(paramsForFull);
		 percent = fullTotal > 0 ?(double)engineeringBack / fullTotal * 100 : 0;
		 double engineeringBackPer = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  

		 /*删除键值对*/
		 paramsForFull.remove(FullTimeTeacherInfoTable.FTTI_IFENGINEERINBACKGROUND);
		 
		 paramsForFull.put(FullTimeTeacherInfoTable.FTTI_IFINDUSTRYBACKGROUND, "是");
		 industryBack = fullTimeTeacherInfoDao.getFullTimeTeacherCount(paramsForFull);
		 percent = fullTotal > 0 ?(double)industryBack / fullTotal * 100 : 0;
		 double industryBackPer = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  

		 /*删除键值对*/
		 paramsForFull.remove(FullTimeTeacherInfoTable.FTTI_IFINDUSTRYBACKGROUND);
		 
		 
		 foreignTotal = externalTeacherDao.getExternalTeacherCount(paramsForForeign);
		 paramsForForeign.put(ExternalTeacherTable.ET_AREA, "境外");
		 outside = externalTeacherDao.getExternalTeacherCount(paramsForForeign);
		 percent = foreignTotal > 0 ?(double)outside / foreignTotal * 100 : 0;
		 double outsidePer = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  

		 /*删除键值对*/
		 paramsForForeign.remove(ExternalTeacherTable.ET_AREA);
		 int sni_ordiundergrastu = 0;// 普通本科生人数
		int sni_highervocationstu = 0;// 普通高职（含专科）学生数（人）
		int sni_fulltimepost = 0;// 硕士研究生中：全日制硕士研究生
		int sni_fulltimedoct = 0;// 博士研究生中：全日制博士研究生
		int sni_abroadstu = 0;// 留学生数（人）
		int sni_ordipreppie = 0;// 普通预科生数（人）
		int sni_advancedstu = 0;// 进修生数（人）
		int sni_adultfulltimestu = 0;// 成人脱产学生数（人）
		int sni_parttimestu = 0;// 夜大（业余）学生数（人）
		int sni_correspondencestu = 0;// 函授学生数（人）
		 List<StudentNumberInfo> studentNumberInfos = studentNumberInfoDao.getStudentNumberInfos(0, studentNumberInfoDao.getStudentNumberInfoCount(paramsForStuNum), StudentNumberInfoTable.SNI_ID, "asc", paramsForStuNum, deadline);
		 for (StudentNumberInfo studentNumberInfo : studentNumberInfos) {
			 sni_ordiundergrastu += studentNumberInfo.getSni_ordiundergrastu();
			 sni_highervocationstu += studentNumberInfo.getSni_highervocationstu();
			 sni_fulltimepost += studentNumberInfo.getSni_fulltimepost();
			 sni_fulltimedoct += studentNumberInfo.getSni_fulltimedoct();
			 sni_abroadstu += studentNumberInfo.getSni_abroadstu();
			 sni_ordipreppie += studentNumberInfo.getSni_ordipreppie();
			 sni_advancedstu += studentNumberInfo.getSni_advancedstu();
			 sni_adultfulltimestu += studentNumberInfo.getSni_adultfulltimestu();
			 sni_parttimestu += studentNumberInfo.getSni_parttimestu();
			 sni_correspondencestu += studentNumberInfo.getSni_correspondencestu();
		}
		// 折合在校生数=普通本、专科（高职)生数+全日制硕士生数×1.5+全日制博士生数×2+留学生数×3+预科生数+进修生数+成人脱产班学生数+夜大（业余)学生数×0.3+函授生数×0.1（不包括自考生）。
		 studentsNum = sni_ordiundergrastu + sni_highervocationstu +sni_fulltimepost*1.5+sni_fulltimedoct*2 +sni_abroadstu*3 + 
				 sni_ordipreppie+sni_advancedstu + sni_adultfulltimestu+sni_parttimestu*0.3+sni_correspondencestu*0.1;
		
		 proportion = studentsNum / (foreignTotal*0.5 + fullTotal);
				 
		 studentsNum = new BigDecimal(studentsNum).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  //四舍五入保留两位小数
		 proportion = new BigDecimal(proportion).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  //四舍五入保留两位小数

		 underLessonTeacherNum = startClassDao.getClassTeacherCount(paramsForOpenClass);
		 
		 StudentsAndTeachers row1 = new StudentsAndTeachers(rowNames[0], Integer.toString(fullTotal), Integer.toString(master), Integer.toString(doctor), Integer.toString(doubleType), Integer.toString(engineeringBack), Integer.toString(industryBack), Integer.toString(foreignTotal), Integer.toString(outside), Double.toString(studentsNum), Double.toString(proportion), Integer.toString(underLessonTeacherNum), college);
		 StudentsAndTeachers row2 = new StudentsAndTeachers(rowNames[1], "/", Double.toString(masterPer), Double.toString(doctorPer), Double.toString(doubleTypePer), Double.toString(engineeringBackPer), Double.toString(industryBackPer), "/", Double.toString(outsidePer), "-", "-", "-", college);
		 
		 List<StudentsAndTeachers> studentsAndTeacherss = new ArrayList<StudentsAndTeachers>();
		 studentsAndTeacherss.add(row1);
		 studentsAndTeacherss.add(row2);
		 
		return studentsAndTeacherss;
		 
		
	}

	 public static Date strToDate(String strDate) {
	        String str = strDate;
	        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
	        java.util.Date d = null;
	        try {
	            d = format.parse(str);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        java.sql.Date date = new java.sql.Date(d.getTime());
	        return date;
	    }
}
