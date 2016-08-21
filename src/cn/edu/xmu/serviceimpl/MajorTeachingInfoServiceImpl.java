package cn.edu.xmu.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.dao.AdvantageMajorInfoDao;
import cn.edu.xmu.dao.MajorInfoDao;
import cn.edu.xmu.daoimpl.AdvantageMajorInfoDaoImpl;
import cn.edu.xmu.daoimpl.MajorInfoDaoImpl;
import cn.edu.xmu.entity.AdvantageMajorInfo;
import cn.edu.xmu.entity.MajorInfo;
import cn.edu.xmu.entity.MajorTeachingInfo;
import cn.edu.xmu.service.MajorTeachingInfoService;
import cn.edu.xmu.table.AdvantageMajorInfoTable;
import cn.edu.xmu.table.MajorInfoTable;

public class MajorTeachingInfoServiceImpl implements MajorTeachingInfoService{

	private AdvantageMajorInfoDao amiDao = new AdvantageMajorInfoDaoImpl();
	private MajorInfoDao miDao = new MajorInfoDaoImpl();
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<MajorTeachingInfo> getMajorTeachingInfo(Map params) {
		int serialNumber = 0;//序号
		Map paramsAdvantageMajorInfo = new HashMap<>();
		Map paramsMajorInfo = new HashMap<>();
		if(params == null)
		{
			params = new HashMap<>();
		}
		else if(params.keySet().size() != 0){
			for(Object object:params.keySet()){
				String key = object.toString();
				String value = (String) params.get(key);
				if(key.equals("college"))
				{
					params.remove("college");
					paramsAdvantageMajorInfo.put(AdvantageMajorInfoTable.AMI_COLLEGE, value);
					paramsMajorInfo.put(MajorInfoTable.MI_COLLEGE, value);
				}
				if(key.equals("deadline"))
				{
					params.remove("deadline");
					paramsAdvantageMajorInfo.put(AdvantageMajorInfoTable.AMI_DEADLINE, value);
					paramsMajorInfo.put(MajorInfoTable.MI_DEADLINE, value);
				}
			}
		}
		List<MajorTeachingInfo> mtis = new ArrayList<MajorTeachingInfo>();
		List<MajorInfo> mis = new ArrayList<MajorInfo>();
		List<AdvantageMajorInfo> amis= new ArrayList<AdvantageMajorInfo>();
		mis = miDao.getAllMajorInfo(0, miDao.getCount(paramsMajorInfo), MajorInfoTable.MI_ID, "asc");
		amis = amiDao.getAdvantageMajorInfo(0, amiDao.getAdvantageMajorInfoCount(paramsAdvantageMajorInfo), AdvantageMajorInfoTable.AMI_ID, "asc", paramsAdvantageMajorInfo, null);
		for(MajorInfo mi:mis)
		{
			String majorName = mi.getMi_majornameinschool();
			String advantageMajor = "";
			for(AdvantageMajorInfo ami:amis)
			{
				if(ami.getAmi_name().equals(majorName))
					advantageMajor = ami.getAmi_type();
			}
			String setupYear = mi.getMi_setupyear();
			int allHours = mi.getMi_allhours();
			float allCredits = mi.getMi_allcredits();
			float mustCredits = mi.getMi_mustcredits();
			float selectedCredits = mi.getMi_selectedcredits();
			float concentratedPracticeCredits = mi.getMi_concentratedpracticecredits();
			float inclassCredits = mi.getMi_inclasscredits();
			float experimentCredits = mi.getMi_experimentcredits();
			float outclassActivityCredits = mi.getMi_outclassactivitycredits();
			float practiceCreditsPercent = 0;
			if(allCredits!=0)		
				practiceCreditsPercent = concentratedPracticeCredits/allCredits * 100;
			String college = mi.getMi_college();
			MajorTeachingInfo mti = new MajorTeachingInfo(++serialNumber, majorName,
					advantageMajor, setupYear, allHours, allCredits, mustCredits,
					selectedCredits, concentratedPracticeCredits, inclassCredits,
					experimentCredits, outclassActivityCredits, practiceCreditsPercent,
					college);
			mtis.add(mti);
		}
		return mtis;
	}

}
