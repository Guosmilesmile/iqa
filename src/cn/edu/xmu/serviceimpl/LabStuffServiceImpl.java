package cn.edu.xmu.serviceimpl;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.dao.OtherTeacherInfoDao;
import cn.edu.xmu.daoimpl.OtherTeacherInfoDaoImpl;
import cn.edu.xmu.entity.LabStuffInfo;
import cn.edu.xmu.service.LabStuffInfoService;
import cn.edu.xmu.table.DisciplineConstructionTable;
import cn.edu.xmu.table.OtherTeacherInfoTable;

public class LabStuffServiceImpl implements LabStuffInfoService{

	private OtherTeacherInfoDao otherTeacherInfoDao = new OtherTeacherInfoDaoImpl();
	
	@Override
	public List<LabStuffInfo> getLabStuffInfos(Map params){
		String rowName[] = {"数量","比例(%)"};//行名称
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
					params.put(OtherTeacherInfoTable.OTI_COLLEGE, college);
				}
				if (key.equals("deadline")) {
					params.remove("deadline");
					params.put(OtherTeacherInfoTable.OTI_DEADLINE, value);
				}
			}
		}
	
		int total = otherTeacherInfoDao.getOtherTeacherInfoCount(params);

		System.out.println("total-----------------"+total);
		params.put(OtherTeacherInfoTable.OTI_PROFESSIONALTITLE, "其他正高级");
		int zhenggaojiCount = otherTeacherInfoDao.getOtherTeacherInfoCount(params);
		percent = total > 0 ?(double)zhenggaojiCount / total * 100 : 0;
		double zhenggaojiPer = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  

		params.put(OtherTeacherInfoTable.OTI_PROFESSIONALTITLE, "其他副高级");
		int fugaojiCount = otherTeacherInfoDao.getOtherTeacherInfoCount(params);
		percent = total > 0 ?(double)fugaojiCount / total * 100 : 0;
		double fugaojiPer = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  

		
		params.put(OtherTeacherInfoTable.OTI_PROFESSIONALTITLE, "其他中级");
		int zhongjiCount = otherTeacherInfoDao.getOtherTeacherInfoCount(params);
		percent = total > 0 ?(double)zhongjiCount / total * 100: 0;
		double zhongjiPer = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  

		params.put(OtherTeacherInfoTable.OTI_PROFESSIONALTITLE, "其他初级");
		int chujiCount = otherTeacherInfoDao.getOtherTeacherInfoCount(params);
		percent = total > 0 ?(double)chujiCount / total * 100:0;
		double chujiPer = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  

		
		params.put(OtherTeacherInfoTable.OTI_PROFESSIONALTITLE, "未评级");//暂且认为无职称等价于未评级
		int noTitleCount = otherTeacherInfoDao.getOtherTeacherInfoCount(params);
		percent = total > 0 ?(double)noTitleCount / total * 100 : 0;
		double noTitlePer = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  

		/*删除键值对*/
		params.remove(OtherTeacherInfoTable.OTI_PROFESSIONALTITLE);
		
		params.put(OtherTeacherInfoTable.OTI_DEGREE, "博士");
		int doctorCount = otherTeacherInfoDao.getOtherTeacherInfoCount(params);
		percent = total > 0 ?(double)doctorCount / total * 100 : 0;
		double doctorPer = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  

		params.put(OtherTeacherInfoTable.OTI_DEGREE, "硕士");
		int masterCount = otherTeacherInfoDao.getOtherTeacherInfoCount(params);
		percent = total > 0 ?(double)masterCount / total * 100 : 0;
		double masterPer = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  

		params.put(OtherTeacherInfoTable.OTI_DEGREE, "学士");
		int BachelorCount = otherTeacherInfoDao.getOtherTeacherInfoCount(params);
		percent = total > 0 ?(double)BachelorCount / total * 100 : 0;
		double BachelorPer = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  

		params.put(OtherTeacherInfoTable.OTI_DEGREE, "无学位");
		int noDegreeCount = otherTeacherInfoDao.getOtherTeacherInfoCount(params);
		percent = total > 0 ?(double)noDegreeCount / total * 100 : 0;
		double noDegreePer = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  

		/*删除键值对*/
		params.remove(OtherTeacherInfoTable.OTI_DEGREE);
		
		Date now = new Date(System.currentTimeMillis());
		Date start = new Date(now.getYear()-35, now.getMonth(), now.getDay());
		Date end = now;
		int below35 = otherTeacherInfoDao.getCountByRange(OtherTeacherInfoTable.OTI_BIRTHDAY, start, end,params);
		percent = total > 0 ?(double)below35 / total * 100 : 0;
		System.out.println("-----------------------------------"+percent);
		double below35Per = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  

		start.setYear(now.getYear() - 45);
		end.setYear(now.getYear() - 36);
		int between36_45 = otherTeacherInfoDao.getCountByRange(OtherTeacherInfoTable.OTI_BIRTHDAY, start, end,params);
		percent = total > 0 ?(double)between36_45 / total * 100 : 0;
		double between36_45Per = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  

		start.setYear(now.getYear() - 55);
		end.setYear(now.getYear() - 46);
		int between46_55 = otherTeacherInfoDao.getCountByRange(OtherTeacherInfoTable.OTI_BIRTHDAY, start, end,params);
		percent = total > 0 ?(double)between46_55 / total * 100 : 0;
		double between46_55Per = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  

		start.setYear(now.getYear() - 100);
		end.setYear(now.getYear() - 56);
		int upon56 = otherTeacherInfoDao.getCountByRange(OtherTeacherInfoTable.OTI_BIRTHDAY, start, end,params);
		percent = total > 0 ?(double)upon56 / total * 100 : 0;
		double upon56Per = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  

		
		LabStuffInfo row1 = new LabStuffInfo(rowName[0], Integer.toString(total),Integer.toString(zhenggaojiCount), Integer.toString(fugaojiCount), Integer.toString(zhongjiCount), Integer.toString(chujiCount), Integer.toString(noTitleCount), Integer.toString(doctorCount), Integer.toString(masterCount), Integer.toString(BachelorCount), Integer.toString(noDegreeCount), Integer.toString(below35), Integer.toString(between36_45), Integer.toString(between46_55), Integer.toString(upon56),college);
		LabStuffInfo row2 = new LabStuffInfo(rowName[1], "/",Double.toString(zhenggaojiPer), Double.toString(fugaojiPer), Double.toString(zhongjiPer), Double.toString(chujiPer), Double.toString(noTitlePer), Double.toString(doctorPer), Double.toString(masterPer), Double.toString(BachelorPer), Double.toString(noDegreePer), Double.toString(below35Per), Double.toString(between36_45Per), Double.toString(between46_55Per), Double.toString(upon56Per), college);
		
		/*清除多余的参数*/
		params.remove(OtherTeacherInfoTable.OTI_COLLEGE);
		
		List<LabStuffInfo> labStuffInfos  = new ArrayList<LabStuffInfo>();
		labStuffInfos.add(row1);
		labStuffInfos.add(row2);
		return labStuffInfos;
	}


}
