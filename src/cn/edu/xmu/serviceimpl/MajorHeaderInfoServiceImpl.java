package cn.edu.xmu.serviceimpl;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.dao.FullTimeTeacherInfoDao;
import cn.edu.xmu.dao.OtherTeacherInfoDao;
import cn.edu.xmu.daoimpl.FullTimeTeacherInfoDaoImpl;
import cn.edu.xmu.daoimpl.OtherTeacherInfoDaoImpl;
import cn.edu.xmu.entity.MajorHeaderInfo;
import cn.edu.xmu.service.MajorHeaderInfoService;
import cn.edu.xmu.table.FullTimeTeacherInfoTable;
import cn.edu.xmu.table.OtherTeacherInfoTable;

public class MajorHeaderInfoServiceImpl implements MajorHeaderInfoService{

	private FullTimeTeacherInfoDao fullTimeTeacherInfoDao = new FullTimeTeacherInfoDaoImpl();
	private OtherTeacherInfoDao otherTeacherInfoDao = new OtherTeacherInfoDaoImpl();
	
	
	@Override
	public List<MajorHeaderInfo> getMajorHeaderInfos(Map params){
		
		String rowTitles[] = {"数量","比例(%)"};//行名
		 
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
				}
				if (key.equals("deadline")) {
					params.remove("deadline");
				}
			}
		}
		
		//附表4-2-2-1 专业基本情况表
		//Map paramsForMajorInfo;
		//表3-1-1 专任教师
		Map paramsForFullTimeTeacher;
		//表3-1-3 其他师资
		Map paramsForOtherTeacher;
		
		Map basicParams = new HashMap(params);
		
		if (params != null) {
			//paramsForMajorInfo = params;
			paramsForFullTimeTeacher = new HashMap(params);
			paramsForOtherTeacher = new HashMap(params);
		}else {
			//paramsForMajorInfo = new HashMap();
			paramsForFullTimeTeacher = new HashMap();
			paramsForOtherTeacher = new HashMap();
		}
		
		
		int fullTotal = fullTimeTeacherInfoDao.getFullTimeTeacherCountInMajorHeader(paramsForFullTimeTeacher,basicParams);
		System.out.println("fullTotal"+fullTotal);
		paramsForFullTimeTeacher.put(FullTimeTeacherInfoTable.FTTI_PROFESSIONALTITLE, "正高级");
		int fullZhenggaoji = fullTimeTeacherInfoDao.getFullTimeTeacherCountInMajorHeader(paramsForFullTimeTeacher,basicParams);
		paramsForFullTimeTeacher.put(FullTimeTeacherInfoTable.FTTI_PROFESSIONALTITLE, "副高级");
		int fullFugaoji = fullTimeTeacherInfoDao.getFullTimeTeacherCountInMajorHeader(paramsForFullTimeTeacher,basicParams);
		int fullOtherTitle = fullTotal - fullFugaoji -fullZhenggaoji;
		
		/*删除键值对*/
		paramsForFullTimeTeacher.remove(FullTimeTeacherInfoTable.FTTI_PROFESSIONALTITLE);
		
		paramsForFullTimeTeacher.put(FullTimeTeacherInfoTable.FTTI_DEGREE, "博士");
		int fullDoctor = fullTimeTeacherInfoDao.getFullTimeTeacherCountInMajorHeader(paramsForFullTimeTeacher,basicParams);
		paramsForFullTimeTeacher.put(FullTimeTeacherInfoTable.FTTI_DEGREE, "硕士");
		int fullMaster = fullTimeTeacherInfoDao.getFullTimeTeacherCountInMajorHeader(paramsForFullTimeTeacher,basicParams);
		int fullOtherDegree = fullTotal - fullDoctor -fullMaster;
		
		/*删除键值对*/
		paramsForFullTimeTeacher.remove(FullTimeTeacherInfoTable.FTTI_DEGREE);
		
		Date now = new Date(System.currentTimeMillis());
		Date start = new Date(now.getYear()-35, now.getMonth(), now.getDay());
		Date end = now;
		int fullBelow35 = fullTimeTeacherInfoDao.getCountByRangeInMajorHeader(FullTimeTeacherInfoTable.FTTI_BIRTHDAY, start, end, paramsForFullTimeTeacher,basicParams);
		
		start = new Date(now.getYear()-45, now.getMonth(), now.getDay());
		end = new Date(now.getYear()-36, now.getMonth(), now.getDay());
		int fullBetween36_45 = fullTimeTeacherInfoDao.getCountByRangeInMajorHeader(FullTimeTeacherInfoTable.FTTI_BIRTHDAY, start, end, paramsForFullTimeTeacher,basicParams);
		
		start = new Date(now.getYear()-55, now.getMonth(), now.getDay());
		end = new Date(now.getYear()-46, now.getMonth(), now.getDay());
		int fullBetween46_55 = fullTimeTeacherInfoDao.getCountByRangeInMajorHeader(FullTimeTeacherInfoTable.FTTI_BIRTHDAY, start, end, paramsForFullTimeTeacher,basicParams);
		
		start = new Date(now.getYear()-100, now.getMonth(), now.getDay());
		end = new Date(now.getYear()-56, now.getMonth(), now.getDay());
		int fullUpon56 = fullTimeTeacherInfoDao.getCountByRangeInMajorHeader(FullTimeTeacherInfoTable.FTTI_BIRTHDAY, start, end, paramsForFullTimeTeacher,basicParams);
		
		paramsForFullTimeTeacher.put(FullTimeTeacherInfoTable.FTTI_EDUCATIONSOURCE, "本校");
		int fullSelfSchool = fullTimeTeacherInfoDao.getFullTimeTeacherCountInMajorHeader(paramsForFullTimeTeacher,basicParams);
		paramsForFullTimeTeacher.put(FullTimeTeacherInfoTable.FTTI_EDUCATIONSOURCE, "境内");
		int fullInside = fullTimeTeacherInfoDao.getFullTimeTeacherCountInMajorHeader(paramsForFullTimeTeacher,basicParams);
		paramsForFullTimeTeacher.put(FullTimeTeacherInfoTable.FTTI_EDUCATIONSOURCE, "境外");
		int fullOutside = fullTimeTeacherInfoDao.getFullTimeTeacherCountInMajorHeader(paramsForFullTimeTeacher,basicParams);
		/*删除键值对*/
		paramsForFullTimeTeacher.remove(FullTimeTeacherInfoTable.FTTI_EDUCATIONSOURCE);
		
		int otherTotal = otherTeacherInfoDao.getOtherTeacherCountInMajorHeader(paramsForOtherTeacher,basicParams);
		paramsForOtherTeacher.put(OtherTeacherInfoTable.OTI_PROFESSIONALTITLE, "正高级");
		int otherZhenggaoji = otherTeacherInfoDao.getOtherTeacherCountInMajorHeader(paramsForOtherTeacher,basicParams);
		paramsForOtherTeacher.put(OtherTeacherInfoTable.OTI_PROFESSIONALTITLE, "副高级");
		int otherFugaoji = otherTeacherInfoDao.getOtherTeacherCountInMajorHeader(paramsForOtherTeacher,basicParams);
		int otherOtherTitle = otherTotal - otherFugaoji -otherZhenggaoji;
		
		/*删除键值对*/
		paramsForOtherTeacher.remove(OtherTeacherInfoTable.OTI_PROFESSIONALTITLE);
		
		paramsForOtherTeacher.put(OtherTeacherInfoTable.OTI_DEGREE, "博士");
		int otherDoctor = otherTeacherInfoDao.getOtherTeacherCountInMajorHeader(paramsForOtherTeacher,basicParams);
		paramsForOtherTeacher.put(OtherTeacherInfoTable.OTI_DEGREE, "硕士");
		int otherMaster = otherTeacherInfoDao.getOtherTeacherCountInMajorHeader(paramsForOtherTeacher,basicParams);
		int otherOtherDegree = otherTotal - otherDoctor -otherMaster;
		
		/*删除键值对*/
		paramsForOtherTeacher.remove(OtherTeacherInfoTable.OTI_DEGREE);
		
		start = new Date(now.getYear()-35, now.getMonth(), now.getDay());
		end = now;
		int otherBelow35 = otherTeacherInfoDao.getCountByRangeInMajorHeader(OtherTeacherInfoTable.OTI_BIRTHDAY, start, end, paramsForOtherTeacher,basicParams);
		
		start = new Date(now.getYear()-45, now.getMonth(), now.getDay());
		end = new Date(now.getYear()-36, now.getMonth(), now.getDay());
		int otherBetween36_45 = otherTeacherInfoDao.getCountByRangeInMajorHeader(OtherTeacherInfoTable.OTI_BIRTHDAY, start, end, paramsForOtherTeacher,basicParams);
		
		start = new Date(now.getYear()-55, now.getMonth(), now.getDay());
		end = new Date(now.getYear()-46, now.getMonth(), now.getDay());
		int otherBetween46_55 = otherTeacherInfoDao.getCountByRangeInMajorHeader(OtherTeacherInfoTable.OTI_BIRTHDAY, start, end, paramsForOtherTeacher,basicParams);
		
		start = new Date(now.getYear()-100, now.getMonth(), now.getDay());
		end = new Date(now.getYear()-56, now.getMonth(), now.getDay());
		int otherUpon56 = otherTeacherInfoDao.getCountByRangeInMajorHeader(OtherTeacherInfoTable.OTI_BIRTHDAY, start, end, paramsForOtherTeacher,basicParams);
		
		int total = fullTotal + otherTotal;
		
		int zhenggaoji = fullZhenggaoji + otherZhenggaoji;
		percent = total > 0 ?(double)zhenggaoji / total * 100 : 0;
		double zhenggaojiPer = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  

		int fugaoji = fullFugaoji + otherFugaoji;
		percent = total > 0 ?(double)fugaoji / total * 100 : 0;
		double fugaojiPer = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  

		int otherTitle = fullOtherTitle + otherOtherTitle;
		percent = total > 0 ?(double)otherTitle / total * 100 : 0;
		double otherTitlePer = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  

		int doctor = fullDoctor + otherDoctor;
		percent = total > 0 ?(double)doctor / total * 100 : 0;
		double doctorPer = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  

		int master = fullMaster + otherMaster;
		percent = total > 0 ?(double)master / total * 100 : 0;
		double masterPer = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  

		int otherDegree = fullOtherDegree + otherOtherDegree;
		percent = total > 0 ?(double)otherDegree / total * 100 : 0;
		double otherDegreePer = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  

		int below35 = fullBelow35 + otherBelow35;
		percent = total > 0 ?(double)below35 / total * 100 : 0;
		double below35Per = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  

		int between36_45 = fullBetween36_45 + otherBetween36_45;
		percent = total > 0 ?(double)between36_45 / total * 100 : 0;
		double between36_45Per = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  

		int between46_55 = fullBetween46_55 + otherBetween46_55;
		percent = total > 0 ?(double)between46_55 / total * 100 : 0;
		double between46_55Per = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  

		int upon56 = fullUpon56 + otherUpon56;
		percent = total > 0 ?(double)upon56 / total * 100 : 0;
		double upon56Per = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  

		int selfSchool = fullSelfSchool;
		percent = total > 0 ?(double)selfSchool / total * 100 : 0;
		double selfSchoolPer = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  

		int inside = fullInside;
		percent = total > 0 ?(double)inside / total * 100 : 0;
		double insidePer = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  

		int outside = fullOutside;
		percent = total > 0 ?(double)outside / total * 100 : 0;
		double outsidePer = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  

		
		MajorHeaderInfo row1 = new MajorHeaderInfo(rowTitles[0], Integer.toString(total), Integer.toString(zhenggaoji), Integer.toString(fugaoji), Integer.toString(otherTitle), Integer.toString(doctor), Integer.toString(master), Integer.toString(otherDegree), Integer.toString(below35), Integer.toString(between36_45), Integer.toString(between46_55), Integer.toString(upon56), Integer.toString(selfSchool), Integer.toString(inside), Integer.toString(outside), college);
		MajorHeaderInfo row2 = new MajorHeaderInfo(rowTitles[1], "/", Double.toString(zhenggaojiPer), Double.toString(fugaojiPer), Double.toString(otherTitlePer), Double.toString(doctorPer), Double.toString(masterPer), Double.toString(otherDegreePer), Double.toString(below35Per), Double.toString(between36_45Per), Double.toString(between46_55Per), Double.toString(upon56Per), Double.toString(selfSchoolPer), Double.toString(insidePer), Double.toString(outsidePer), college);
		
		List<MajorHeaderInfo> majorHeaderInfos = new ArrayList<MajorHeaderInfo>();
		majorHeaderInfos.add(row1);
		majorHeaderInfos.add(row2);
		
		return majorHeaderInfos;
	}

}
