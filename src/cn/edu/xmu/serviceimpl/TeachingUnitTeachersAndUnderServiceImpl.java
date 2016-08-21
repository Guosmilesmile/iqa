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
import cn.edu.xmu.dao.RegUndergraProfesStuNumDao;
import cn.edu.xmu.dao.TeachScientificDao;
import cn.edu.xmu.daoimpl.ExternalTeacherDaoImpl;
import cn.edu.xmu.daoimpl.FullTimeTeacherInfoDaoImpl;
import cn.edu.xmu.daoimpl.RegUndergraProfesStuNumDaoImpl;
import cn.edu.xmu.daoimpl.TeachScientificDaoImpl;
import cn.edu.xmu.entity.RegUndergraProfesStuNum;
import cn.edu.xmu.entity.TeachingUnitTeachersAndUnder;
import cn.edu.xmu.service.TeachingUnitTeachersAndUnderService;
import cn.edu.xmu.table.ExternalTeacherTable;
import cn.edu.xmu.table.FullTimeTeacherInfoTable;
import cn.edu.xmu.table.RegUndergraProfesStuNumTable;
import cn.edu.xmu.table.TeachScientificTable;

public class TeachingUnitTeachersAndUnderServiceImpl implements TeachingUnitTeachersAndUnderService{

	
	private TeachScientificDao teachScientificDao = new TeachScientificDaoImpl();

	private FullTimeTeacherInfoDao fullTimeTeacherInfoDao = new FullTimeTeacherInfoDaoImpl();
	private ExternalTeacherDao exter = new ExternalTeacherDaoImpl();
	private RegUndergraProfesStuNumDao regUndergraProfesStuNumDao = new RegUndergraProfesStuNumDaoImpl();
	@Override
	public List<TeachingUnitTeachersAndUnder> getTeachingUnitTeachersAndUnder(Map params) {
		List<TeachingUnitTeachersAndUnder> teachingUnitTeachersAndUnders = new ArrayList<TeachingUnitTeachersAndUnder>();
		//根据教学单位名称获取不同的统计值
		String department;//单位
		int fulltimeteacherTotal;//专任教师总数
		int highTitleCount;//具有高级职称的专任教师数
		double highTitlePer;//比例
		int youngTeacherCount;//年轻的专任教师数
		double youngTeacherPer;//比例
		int newIn5yearCount;//5年内新增专任教师数
		double newIn5yearPer;//比例
		int externalTeacherCount;//外聘教师数
		int underCount;//本科生总数
		double underVsFull;//本科生比专任教师
		
		String college = "";//所属学院
		double percent = 0;
		Date deadline = null;
		String deadlinesString="";
		
		
		
		boolean haveCollege = false;
		boolean haveDeadline = false;
		
		
		if (params == null ) {
			params = new HashMap();
		}else if (params.keySet().size() != 0) {
			for (Object object : params.keySet()) {
				String key = object.toString();
				String value = (String) params.get(key);
				if (key.equals("college")) {
					haveCollege = true;
					college = value;
					params.remove("college");
					//params.put(FullTimeTeacherInfoTable.FTTI_COLLEGE, value);
				}
				if (key.equals("deadline")) {
					haveDeadline = true;
					//params.put(FullTimeTeacherInfoTable.FTTI_DEADLINE, value);
					deadlinesString = value;
					deadline = strToDate(value);
					params.remove("deadline");
					
				}
			}
		}
		if (haveCollege) {
			params.put(TeachScientificTable.TS_COLLEGE, college);
		}
		if (haveDeadline) {
			params.put(TeachScientificTable.TS_DEADLINE, deadline);
		}
		
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
			department = unitName;//教学单位名称
			
			if (haveCollege) {
				params.put(FullTimeTeacherInfoTable.FTTI_COLLEGE, college);
			}
			if (haveDeadline) {
				params.put(FullTimeTeacherInfoTable.FTTI_DEADLINE, deadlinesString);
			}
			
			params.put(FullTimeTeacherInfoTable.FTTI_DEPARTMENTNAME, department);
			fulltimeteacherTotal = fullTimeTeacherInfoDao.getFullTimeTeacherCount(params);
			
			
			
			params.put(FullTimeTeacherInfoTable.FTTI_PROFESSIONALTITLE, "教授");
			int jiaoshou = fullTimeTeacherInfoDao.getFullTimeTeacherCount(params);
			params.put(FullTimeTeacherInfoTable.FTTI_PROFESSIONALTITLE, "副教授");
			int fujiaoshou = fullTimeTeacherInfoDao.getFullTimeTeacherCount(params);
			params.put(FullTimeTeacherInfoTable.FTTI_PROFESSIONALTITLE, "其他正高级");
			int zhenggaoji = fullTimeTeacherInfoDao.getFullTimeTeacherCount(params);
			params.put(FullTimeTeacherInfoTable.FTTI_PROFESSIONALTITLE, "其他副高级");
			int fugaoji = fullTimeTeacherInfoDao.getFullTimeTeacherCount(params);
			
			highTitleCount = jiaoshou + fujiaoshou + zhenggaoji + fugaoji;
			percent = fulltimeteacherTotal > 0 ?highTitleCount / fulltimeteacherTotal * 100 : 0;
			highTitlePer = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  

			/*删除键值对*/
			params.remove(FullTimeTeacherInfoTable.FTTI_PROFESSIONALTITLE);
			
			
			Date now = new Date(System.currentTimeMillis());
			Date start = new Date(now.getYear()-35, now.getMonth(), now.getDay());
			Date end = now;
			youngTeacherCount = fullTimeTeacherInfoDao.getCountByRange(FullTimeTeacherInfoTable.FTTI_BIRTHDAY, start, end, params);
			percent = fulltimeteacherTotal > 0 ?youngTeacherCount / fulltimeteacherTotal * 100 : 0;
			System.out.println("-----------------------------------"+percent);
			youngTeacherPer = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  

			
			start = new Date(now.getYear()-5, now.getMonth(), now.getDay());
			end = now;
			newIn5yearCount = fullTimeTeacherInfoDao.getCountByRange(FullTimeTeacherInfoTable.FTTI_INSCHOOLDATE, start, end,params);
			percent = fulltimeteacherTotal > 0 ?newIn5yearCount / fulltimeteacherTotal * 100 : 0;
			newIn5yearPer = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  

			/*删除键值对*/
			if (haveCollege) {
				params.remove(FullTimeTeacherInfoTable.FTTI_COLLEGE);
			}
			if (haveDeadline) {
				params.remove(FullTimeTeacherInfoTable.FTTI_DEADLINE);
			}
			
			params.remove(FullTimeTeacherInfoTable.FTTI_DEPARTMENTNAME);
			
			
			if (haveCollege) {
				params.put(ExternalTeacherTable.ET_COLLEGE, college);
			}
			if (haveDeadline) {
				params.put(ExternalTeacherTable.ET_DEADLINE, deadlinesString);
			}
			
			params.put(ExternalTeacherTable.ET_DEPARTMENTNAME, department);
			externalTeacherCount = exter.getExternalTeacherCount(params);
			
			/*删除键值对*/
			if (haveCollege) {
				params.remove(ExternalTeacherTable.ET_COLLEGE);
			}
			if (haveDeadline) {
				params.remove(ExternalTeacherTable.ET_DEADLINE);
			}
			params.remove(ExternalTeacherTable.ET_DEPARTMENTNAME);
			
			
			params.put(RegUndergraProfesStuNumTable.RUPSN_COLLEGE, department);//具体的学院作为教学单位
			if (haveDeadline) {
				params.put(RegUndergraProfesStuNumTable.RUPSN_DEADLINE, deadlinesString);
			}
			
			
			List<RegUndergraProfesStuNum> regUndergraProfesStuNums = regUndergraProfesStuNumDao.getRegUndergraProfesStuNums(0, regUndergraProfesStuNumDao.getRegUndergraProfesStuNumCount(params), RegUndergraProfesStuNumTable.RUPSN_ID, "asc", params, deadline);
			underCount = 0;
			for (RegUndergraProfesStuNum regUndergraProfesStuNum : regUndergraProfesStuNums) {
				underCount += regUndergraProfesStuNum.getRupsn_atschtotal();
			}
			
			/*删除键值对*/
			if (haveCollege) {
				params.remove(RegUndergraProfesStuNumTable.RUPSN_COLLEGE);
			}
			if (haveDeadline) {
				params.remove(RegUndergraProfesStuNumTable.RUPSN_DEADLINE);
			}
			
			
			percent = fulltimeteacherTotal > 0 ? underCount / fulltimeteacherTotal : 0;
			underVsFull = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
			
			TeachingUnitTeachersAndUnder teachingUnitTeachersAndUnder = new TeachingUnitTeachersAndUnder(department, fulltimeteacherTotal, highTitleCount, highTitlePer, youngTeacherCount, youngTeacherPer, newIn5yearCount, newIn5yearPer, externalTeacherCount, underCount, underVsFull,college);
			teachingUnitTeachersAndUnders.add(teachingUnitTeachersAndUnder);
		}
		return teachingUnitTeachersAndUnders;
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
