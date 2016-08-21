package cn.edu.xmu.serviceimpl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.dao.OtherTeacherInfoDao;
import cn.edu.xmu.daoimpl.OtherTeacherInfoDaoImpl;
import cn.edu.xmu.entity.OtherTeacherInfo;
import cn.edu.xmu.entity.UnitPersonnelStructure;
import cn.edu.xmu.service.UnitPersonnelStructureService;
import cn.edu.xmu.table.OtherTeacherInfoTable;

public class UnitPersonnelStructureServiceImpl implements UnitPersonnelStructureService{
	private OtherTeacherInfoDao otherTeacherInfoDao = new OtherTeacherInfoDaoImpl();
	
	@SuppressWarnings({ "rawtypes", "unchecked", "deprecation" })
	public List<UnitPersonnelStructure> getUnitPersonnelStructure(Map params)
	{
		Map paramsForUnitPersonnelStructure = new HashMap();
		if (params == null ) {
			params = new HashMap();
		}else if (params.keySet().size() != 0) {
			for (Object object : params.keySet()) {
				String key = object.toString();
				String value = (String) params.get(key);
				if (key.equals("college")) {
					params.remove("college");
					paramsForUnitPersonnelStructure.put(OtherTeacherInfoTable.OTI_COLLEGE, value);
				}
				if (key.equals("deadline")) {
					params.remove("deadline");
					paramsForUnitPersonnelStructure.put(OtherTeacherInfoTable.OTI_DEADLINE, value);
				}
			}
		}
		
		List<OtherTeacherInfo> otherTeacherList = otherTeacherInfoDao.getAllOtherTeacherInfo(0, otherTeacherInfoDao.getOtherTeacherInfoCount(paramsForUnitPersonnelStructure), OtherTeacherInfoTable.OTI_ID, "asc", paramsForUnitPersonnelStructure);
		Map unitPersonnelStructureMap = new HashMap();
		java.util.Date now = new java.util.Date();
		for(int i = 0; i<otherTeacherList.size(); i++)
		{
			if(!unitPersonnelStructureMap.containsKey(otherTeacherList.get(i).getOti_departmentname())){
				UnitPersonnelStructure unitPersonnelStructure = new UnitPersonnelStructure();
				unitPersonnelStructure.setDepartmentname(otherTeacherList.get(i).getOti_departmentname());
				unitPersonnelStructure.setSum(unitPersonnelStructure.getSum()+1);
				//职称	正高级	副高级	中级		初级		无职称
				if(otherTeacherList.get(i).getOti_professionaltitle().equals("教授") || otherTeacherList.get(i).getOti_professionaltitle().equals("其他正高级"))
					unitPersonnelStructure.setTitlePositiveSenior(unitPersonnelStructure.getTitlePositiveSenior()+1);
				else if(otherTeacherList.get(i).getOti_professionaltitle().equals("副教授") || otherTeacherList.get(i).getOti_professionaltitle().equals("其他副高级"))
					unitPersonnelStructure.setTitleViceSenior(unitPersonnelStructure.getTitleViceSenior()+1);
				else if(otherTeacherList.get(i).getOti_professionaltitle().equals("讲师") || otherTeacherList.get(i).getOti_professionaltitle().equals("其他中级"))
					unitPersonnelStructure.setTitleIntermediate(unitPersonnelStructure.getTitleIntermediate()+1);
				else if(otherTeacherList.get(i).getOti_professionaltitle().equals("助教") || otherTeacherList.get(i).getOti_professionaltitle().equals("其他初级"))
					unitPersonnelStructure.setTitlePrimary(unitPersonnelStructure.getTitlePrimary()+1);
				else if(otherTeacherList.get(i).getOti_professionaltitle().equals("未评级"))
					unitPersonnelStructure.setTitlenone(unitPersonnelStructure.getTitlenone());
				//学位	博士		硕士		学位		无学位
				if(otherTeacherList.get(i).getOti_degree().equals("博士"))
					unitPersonnelStructure.setDoctor(unitPersonnelStructure.getDoctor()+1);
				else if(otherTeacherList.get(i).getOti_degree().equals("硕士"))
					unitPersonnelStructure.setMaster(unitPersonnelStructure.getMaster()+1);
				else if(otherTeacherList.get(i).getOti_degree().equals("学士"))
					unitPersonnelStructure.setDegree(unitPersonnelStructure.getDegree()+1);
				else if(otherTeacherList.get(i).getOti_degree().equals("无学位"))
					unitPersonnelStructure.setNonedegree(unitPersonnelStructure.getNonedegree()+1);
				//年龄	35岁及以下	36-45	46-55	56岁及以上
				Date oti_birthday = otherTeacherList.get(i).getOti_birthday();
				double doubleage = now.getYear() - oti_birthday.getYear() + (now.getMonth() - oti_birthday.getMonth())/12.0;
				int age = (int) doubleage;
				if(age<=35)
					unitPersonnelStructure.setUnder35(unitPersonnelStructure.getUnder35()+1);
				else if(age>=36 && age<=45)
					unitPersonnelStructure.setBetween36and45(unitPersonnelStructure.getBetween36and45()+1);
				else if(age>=46 && age<=55)
					unitPersonnelStructure.setBetween46and55(unitPersonnelStructure.getBetween46and55()+1);
				else if(age>55)
					unitPersonnelStructure.setOver55(unitPersonnelStructure.getOver55()+1);
				
				unitPersonnelStructureMap.put(unitPersonnelStructure.getDepartmentname(), unitPersonnelStructure);
			}
			else {
				UnitPersonnelStructure unitPersonnelStructure = (UnitPersonnelStructure) unitPersonnelStructureMap.get(otherTeacherList.get(i).getOti_departmentname());
				unitPersonnelStructure.setDepartmentname(otherTeacherList.get(i).getOti_departmentname());
				unitPersonnelStructure.setSum(unitPersonnelStructure.getSum()+1);
				//职称	正高级	副高级	中级		初级		无职称
				if(otherTeacherList.get(i).getOti_professionaltitle().equals("教授") || otherTeacherList.get(i).getOti_professionaltitle().equals("其他正高级"))
					unitPersonnelStructure.setTitlePositiveSenior(unitPersonnelStructure.getTitlePositiveSenior()+1);
				else if(otherTeacherList.get(i).getOti_professionaltitle().equals("副教授") || otherTeacherList.get(i).getOti_professionaltitle().equals("其他副高级"))
					unitPersonnelStructure.setTitleViceSenior(unitPersonnelStructure.getTitleViceSenior()+1);
				else if(otherTeacherList.get(i).getOti_professionaltitle().equals("讲师") || otherTeacherList.get(i).getOti_professionaltitle().equals("其他中级"))
					unitPersonnelStructure.setTitleIntermediate(unitPersonnelStructure.getTitleIntermediate()+1);
				else if(otherTeacherList.get(i).getOti_professionaltitle().equals("助教") || otherTeacherList.get(i).getOti_professionaltitle().equals("其他初级"))
					unitPersonnelStructure.setTitlePrimary(unitPersonnelStructure.getTitlePrimary()+1);
				else if(otherTeacherList.get(i).getOti_professionaltitle().equals("未评级"))
					unitPersonnelStructure.setTitlenone(unitPersonnelStructure.getTitlenone());
				//学位	博士		硕士		学位		无学位
				if(otherTeacherList.get(i).getOti_degree().equals("博士"))
					unitPersonnelStructure.setDoctor(unitPersonnelStructure.getDoctor()+1);
				else if(otherTeacherList.get(i).getOti_degree().equals("硕士"))
					unitPersonnelStructure.setMaster(unitPersonnelStructure.getMaster()+1);
				else if(otherTeacherList.get(i).getOti_degree().equals("学士"))
					unitPersonnelStructure.setDegree(unitPersonnelStructure.getDegree()+1);
				else if(otherTeacherList.get(i).getOti_degree().equals("无学位"))
					unitPersonnelStructure.setNonedegree(unitPersonnelStructure.getNonedegree()+1);
				//年龄	35岁及以下	36-45	46-55	56岁及以上
				Date oti_birthday = otherTeacherList.get(i).getOti_birthday();
				double doubleage = now.getYear() - oti_birthday.getYear() + (now.getMonth() - oti_birthday.getMonth())/12.0;
				int age = (int) doubleage;
				if(age<=35)
					unitPersonnelStructure.setUnder35(unitPersonnelStructure.getUnder35()+1);
				else if(age>=36 && age<=45)
					unitPersonnelStructure.setBetween36and45(unitPersonnelStructure.getBetween36and45()+1);
				else if(age>=46 && age<=55)
					unitPersonnelStructure.setBetween46and55(unitPersonnelStructure.getBetween46and55()+1);
				else if(age>55)
					unitPersonnelStructure.setOver55(unitPersonnelStructure.getOver55()+1);
			}
		}
		List<UnitPersonnelStructure> unitPersonnelStructures = new ArrayList<UnitPersonnelStructure>();
		Iterator iterator = unitPersonnelStructureMap.entrySet().iterator();
		int i = 1;
		while (iterator.hasNext()) {
			Map.Entry entry = (Map.Entry) iterator.next();
			UnitPersonnelStructure val = (UnitPersonnelStructure)entry.getValue();
			val.setSerialnumber(i);
			i++;
			unitPersonnelStructures.add(val);
		}
		
		return unitPersonnelStructures;
	}
}
