package cn.edu.xmu.serviceimpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.dao.StartClassDao;
import cn.edu.xmu.daoimpl.StartClassDaoImpl;
import cn.edu.xmu.entity.MajorTeacherStructure;
import cn.edu.xmu.entity.MajorTeacherStructureTemp;
import cn.edu.xmu.service.MajorTeacherStructureService;
import cn.edu.xmu.table.ExternalTeacherTable;
import cn.edu.xmu.table.FullTimeTeacherInfoTable;
import cn.edu.xmu.table.MajorTeachTable;
import cn.edu.xmu.table.OtherTeacherInfoTable;
import cn.edu.xmu.table.StartClassTable;

public class MajorTeacherStructureServiceImpl implements MajorTeacherStructureService{
	private StartClassDao startClassDao = new StartClassDaoImpl();
	
	@SuppressWarnings({ "rawtypes", "unchecked", "deprecation" })
	@Override
	public List<MajorTeacherStructure> getMajorTeacherStructureFormFTTI(Map params){
		Map queryParamsforMT = new HashMap(), queryParamsforSC = new HashMap(), queryParamsforFTTI = new HashMap();
		if (params == null ) {
			params = new HashMap();
		}else if (params.keySet().size() != 0) {
			for (Object object : params.keySet()) {
				String key = object.toString();
				String value = (String) params.get(key);
				if (key.equals("college")) {
					params.remove("college");
					queryParamsforMT.put(MajorTeachTable.MT_COLLEGE, value);
					queryParamsforSC.put(StartClassTable.SC_COLLEGE, value);
					queryParamsforFTTI.put(FullTimeTeacherInfoTable.FTTI_COLLEGE, value);
				}
				if (key.equals("deadline")) {
					params.remove("deadline");
					queryParamsforMT.put(MajorTeachTable.MT_COLLEGE, value);
					queryParamsforSC.put(StartClassTable.SC_DEADLINE, value);
					queryParamsforFTTI.put(FullTimeTeacherInfoTable.FTTI_COLLEGE, value);
				}
			}
		}
		List<MajorTeacherStructureTemp> majorTeacherStructureTemps = startClassDao.getMajorTeacherStructureFormFTTIAll(queryParamsforMT, queryParamsforSC, queryParamsforFTTI);
		
		//++++++++++++++=要放低年级
		Date nowDate = new Date();
		if((nowDate.getMonth()+1)>8)//月份从0开始,9月份以后算一个新的学年
		{
			queryParamsforSC.put("1stdegree", (nowDate.getYear()+1900)+"");
			queryParamsforSC.put("2nddegree", (nowDate.getYear()+1900-1)+"");
		}
		else {
			queryParamsforSC.put("1stdegree", (nowDate.getYear()+1900-1)+"");
			queryParamsforSC.put("2nddegree", (nowDate.getYear()+1900-2)+"");
		}
		Map lowperfessorMap = startClassDao.getMajorLowCountFormFTTI(queryParamsforMT, queryParamsforSC, queryParamsforFTTI);
		
		int Serialnumber = 1;
		Map<String, MajorTeacherStructure> resultMap = new HashMap<>();
		for(int i = 0; i<majorTeacherStructureTemps.size();i++)
		{
			if(!resultMap.containsKey(majorTeacherStructureTemps.get(i).getSubject()))
			{
				MajorTeacherStructure majorTeacherStructure = new MajorTeacherStructure();
				majorTeacherStructure.setMajor(majorTeacherStructureTemps.get(i).getSubject());
				majorTeacherStructure.setSerialNumber(Serialnumber++);
				majorTeacherStructure.setTeacherNumber(majorTeacherStructureTemps.get(i).getCount());
				if(majorTeacherStructureTemps.get(i).getTitle().equals("教授"))
				{
					majorTeacherStructure.setProfessorNumber(1);
					majorTeacherStructure.setPosiViceProfessorNumber(1);
				}
				else if (majorTeacherStructureTemps.get(i).getTitle().equals("副教授"))
				{
					majorTeacherStructure.setPosiViceProfessorNumber(1);
				}
				if(majorTeacherStructureTemps.get(i).getDegree().equals("博士") || majorTeacherStructureTemps.get(i).getDegree().equals("博士"))
					majorTeacherStructure.setMasterDoctorNumber(1);
				resultMap.put(majorTeacherStructureTemps.get(i).getSubject(), majorTeacherStructure);
			}
			else {
				MajorTeacherStructure majorTeacherStructure = resultMap.get(majorTeacherStructureTemps.get(i).getSubject());
				majorTeacherStructure.setTeacherNumber(majorTeacherStructure.getTeacherNumber()+majorTeacherStructureTemps.get(i).getCount());
				if(majorTeacherStructureTemps.get(i).getTitle().equals("教授"))
				{
					majorTeacherStructure.setProfessorNumber(majorTeacherStructure.getProfessorNumber()+1);
					majorTeacherStructure.setPosiViceProfessorNumber(majorTeacherStructure.getPosiViceProfessorNumber()+1);
				}
				else if (majorTeacherStructureTemps.get(i).getTitle().equals("副教授"))
				{
					majorTeacherStructure.setPosiViceProfessorNumber(majorTeacherStructure.getPosiViceProfessorNumber()+1);
				}
				if(majorTeacherStructureTemps.get(i).getDegree().equals("博士") || majorTeacherStructureTemps.get(i).getDegree().equals("硕士"))
					majorTeacherStructure.setMasterDoctorNumber(majorTeacherStructure.getMasterDoctorNumber()+1);
				//要不要重新放进map?
			}
		}
		//遍历添加低年级教授数目
		Iterator iterator = lowperfessorMap.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry entry = (Map.Entry) iterator.next();
			resultMap.get(entry.getKey()).setLowProfessorNumber((int)entry.getValue());
		}
		
		List resultList = new ArrayList<MajorTeacherStructure>();
		iterator = resultMap.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry entry = (Map.Entry) iterator.next();
			MajorTeacherStructure majorTeacherStructure = (MajorTeacherStructure)entry.getValue();
			majorTeacherStructure.setProfessorProportion(majorTeacherStructure.getProfessorNumber()*1.0/majorTeacherStructure.getTeacherNumber()*100);
			majorTeacherStructure.setLowProfessorProportion(majorTeacherStructure.getLowProfessorNumber()*1.0/majorTeacherStructure.getTeacherNumber()*100);
			majorTeacherStructure.setPosiViceProfessorProportion(majorTeacherStructure.getPosiViceProfessorNumber()*1.0/majorTeacherStructure.getTeacherNumber()*100);
			majorTeacherStructure.setMasterDoctorProportion(majorTeacherStructure.getMasterDoctorNumber()*1.0/majorTeacherStructure.getTeacherNumber()*100);
			resultList.add(majorTeacherStructure);
		}
		return resultList;
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked", "deprecation" })
	@Override
	public List<MajorTeacherStructure> getMajorTeacherStructure(Map params){
		Map queryParamsforMT = new HashMap(), queryParamsforSC = new HashMap(), queryParamsforFTTI = new HashMap(), queryParamsforET = new HashMap(), queryParamsforOTI = new HashMap();
		if (params == null ) {
			params = new HashMap();
		}else if (params.keySet().size() != 0) {
			for (Object object : params.keySet()) {
				String key = object.toString();
				String value = (String) params.get(key);
				if (key.equals("college")) {
					params.remove("college");
					queryParamsforMT.put(MajorTeachTable.MT_COLLEGE, value);
					queryParamsforSC.put(StartClassTable.SC_COLLEGE, value);
					queryParamsforFTTI.put(FullTimeTeacherInfoTable.FTTI_COLLEGE, value);
					queryParamsforET.put(ExternalTeacherTable.ET_COLLEGE, value);
					queryParamsforOTI.put(OtherTeacherInfoTable.OTI_COLLEGE, value);
				}
				if (key.equals("deadline")) {
					params.remove("deadline");
					queryParamsforMT.put(MajorTeachTable.MT_COLLEGE, value);
					queryParamsforSC.put(StartClassTable.SC_DEADLINE, value);
					queryParamsforFTTI.put(FullTimeTeacherInfoTable.FTTI_COLLEGE, value);
					queryParamsforET.put(ExternalTeacherTable.ET_COLLEGE, value);
					queryParamsforOTI.put(OtherTeacherInfoTable.OTI_COLLEGE, value);
				}
			}
		}
		List<MajorTeacherStructureTemp> majorTeacherStructureTempsFTTI = startClassDao.getMajorTeacherStructureFormFTTIAll(queryParamsforMT, queryParamsforSC, queryParamsforFTTI);
		List<MajorTeacherStructureTemp> majorTeacherStructureTempsET = startClassDao.getMajorTeacherStructureFormETAll(queryParamsforMT, queryParamsforSC, queryParamsforET);
		List<MajorTeacherStructureTemp> majorTeacherStructureTempsOTI = startClassDao.getMajorTeacherStructureFormOTIAll(queryParamsforMT, queryParamsforSC, queryParamsforOTI);
		
		//++++++++++++++=要放低年级
		Date nowDate = new Date();
		if((nowDate.getMonth()+1)>8)//月份从0开始,9月份以后算一个新的学年
		{
			queryParamsforSC.put("1stdegree", (nowDate.getYear()+1900)+"");
			queryParamsforSC.put("2nddegree", (nowDate.getYear()+1900-1)+"");
		}
		else {
			queryParamsforSC.put("1stdegree", (nowDate.getYear()+1900-1)+"");
			queryParamsforSC.put("2nddegree", (nowDate.getYear()+1900-2)+"");
		}
		Map lowperfessorMapFTTI = startClassDao.getMajorLowCountFormFTTI(queryParamsforMT, queryParamsforSC, queryParamsforFTTI);
		Map lowperfessorMapET = startClassDao.getMajorLowCountFormET(queryParamsforMT, queryParamsforSC, queryParamsforET);
		Map lowperfessorMapOTI = startClassDao.getMajorLowCountFormOTI(queryParamsforMT, queryParamsforSC, queryParamsforOTI);
		
		int Serialnumber = 1;
		Map<String, MajorTeacherStructure> resultMap = new HashMap<>();
		for(int i = 0; i<majorTeacherStructureTempsFTTI.size();i++)
		{
			if(!resultMap.containsKey(majorTeacherStructureTempsFTTI.get(i).getSubject()))
			{
				MajorTeacherStructure majorTeacherStructure = new MajorTeacherStructure();
				majorTeacherStructure.setMajor(majorTeacherStructureTempsFTTI.get(i).getSubject());
				majorTeacherStructure.setSerialNumber(Serialnumber++);
				majorTeacherStructure.setTeacherNumber(majorTeacherStructureTempsFTTI.get(i).getCount());
				if(majorTeacherStructureTempsFTTI.get(i).getTitle().equals("教授"))
				{
					majorTeacherStructure.setProfessorNumber(1);
					majorTeacherStructure.setPosiViceProfessorNumber(1);
				}
				else if (majorTeacherStructureTempsFTTI.get(i).getTitle().equals("副教授"))
				{
					majorTeacherStructure.setPosiViceProfessorNumber(1);
				}
				if(majorTeacherStructureTempsFTTI.get(i).getDegree().equals("博士") || majorTeacherStructureTempsFTTI.get(i).getDegree().equals("博士"))
					majorTeacherStructure.setMasterDoctorNumber(1);
				resultMap.put(majorTeacherStructureTempsFTTI.get(i).getSubject(), majorTeacherStructure);
			}
			else {
				MajorTeacherStructure majorTeacherStructure = resultMap.get(majorTeacherStructureTempsFTTI.get(i).getSubject());
				majorTeacherStructure.setTeacherNumber(majorTeacherStructure.getTeacherNumber()+majorTeacherStructureTempsFTTI.get(i).getCount());
				if(majorTeacherStructureTempsFTTI.get(i).getTitle().equals("教授"))
				{
					majorTeacherStructure.setProfessorNumber(majorTeacherStructure.getProfessorNumber()+1);
					majorTeacherStructure.setPosiViceProfessorNumber(majorTeacherStructure.getPosiViceProfessorNumber()+1);
				}
				else if (majorTeacherStructureTempsFTTI.get(i).getTitle().equals("副教授"))
				{
					majorTeacherStructure.setPosiViceProfessorNumber(majorTeacherStructure.getPosiViceProfessorNumber()+1);
				}
				if(majorTeacherStructureTempsFTTI.get(i).getDegree().equals("博士") || majorTeacherStructureTempsFTTI.get(i).getDegree().equals("硕士"))
					majorTeacherStructure.setMasterDoctorNumber(majorTeacherStructure.getMasterDoctorNumber()+1);
				//要不要重新放进map?
			}
		}
		for(int i = 0; i<majorTeacherStructureTempsET.size();i++)
		{
			if(!resultMap.containsKey(majorTeacherStructureTempsET.get(i).getSubject()))
			{
				MajorTeacherStructure majorTeacherStructure = new MajorTeacherStructure();
				majorTeacherStructure.setMajor(majorTeacherStructureTempsET.get(i).getSubject());
				majorTeacherStructure.setSerialNumber(Serialnumber++);
				majorTeacherStructure.setTeacherNumber(majorTeacherStructureTempsET.get(i).getCount());
				if(majorTeacherStructureTempsET.get(i).getTitle().equals("教授"))
				{
					majorTeacherStructure.setProfessorNumber(1);
					majorTeacherStructure.setPosiViceProfessorNumber(1);
				}
				else if (majorTeacherStructureTempsET.get(i).getTitle().equals("副教授"))
				{
					majorTeacherStructure.setPosiViceProfessorNumber(1);
				}
				if(majorTeacherStructureTempsET.get(i).getDegree().equals("博士") || majorTeacherStructureTempsET.get(i).getDegree().equals("博士"))
					majorTeacherStructure.setMasterDoctorNumber(1);
				resultMap.put(majorTeacherStructureTempsET.get(i).getSubject(), majorTeacherStructure);
			}
			else {
				MajorTeacherStructure majorTeacherStructure = resultMap.get(majorTeacherStructureTempsET.get(i).getSubject());
				majorTeacherStructure.setTeacherNumber(majorTeacherStructure.getTeacherNumber()+majorTeacherStructureTempsET.get(i).getCount());
				if(majorTeacherStructureTempsET.get(i).getTitle().equals("教授"))
				{
					majorTeacherStructure.setProfessorNumber(majorTeacherStructure.getProfessorNumber()+1);
					majorTeacherStructure.setPosiViceProfessorNumber(majorTeacherStructure.getPosiViceProfessorNumber()+1);
				}
				else if (majorTeacherStructureTempsET.get(i).getTitle().equals("副教授"))
				{
					majorTeacherStructure.setPosiViceProfessorNumber(majorTeacherStructure.getPosiViceProfessorNumber()+1);
				}
				if(majorTeacherStructureTempsET.get(i).getDegree().equals("博士") || majorTeacherStructureTempsET.get(i).getDegree().equals("硕士"))
					majorTeacherStructure.setMasterDoctorNumber(majorTeacherStructure.getMasterDoctorNumber()+1);
				//要不要重新放进map?
			}
		}
		for(int i = 0; i<majorTeacherStructureTempsOTI.size();i++)
		{
			if(!resultMap.containsKey(majorTeacherStructureTempsOTI.get(i).getSubject()))
			{
				MajorTeacherStructure majorTeacherStructure = new MajorTeacherStructure();
				majorTeacherStructure.setMajor(majorTeacherStructureTempsOTI.get(i).getSubject());
				majorTeacherStructure.setSerialNumber(Serialnumber++);
				majorTeacherStructure.setTeacherNumber(majorTeacherStructureTempsOTI.get(i).getCount());
				if(majorTeacherStructureTempsOTI.get(i).getTitle().equals("教授"))
				{
					majorTeacherStructure.setProfessorNumber(1);
					majorTeacherStructure.setPosiViceProfessorNumber(1);
				}
				else if (majorTeacherStructureTempsOTI.get(i).getTitle().equals("副教授"))
				{
					majorTeacherStructure.setPosiViceProfessorNumber(1);
				}
				if(majorTeacherStructureTempsOTI.get(i).getDegree().equals("博士") || majorTeacherStructureTempsOTI.get(i).getDegree().equals("博士"))
					majorTeacherStructure.setMasterDoctorNumber(1);
				resultMap.put(majorTeacherStructureTempsOTI.get(i).getSubject(), majorTeacherStructure);
			}
			else {
				MajorTeacherStructure majorTeacherStructure = resultMap.get(majorTeacherStructureTempsOTI.get(i).getSubject());
				majorTeacherStructure.setTeacherNumber(majorTeacherStructure.getTeacherNumber()+majorTeacherStructureTempsOTI.get(i).getCount());
				if(majorTeacherStructureTempsOTI.get(i).getTitle().equals("教授"))
				{
					majorTeacherStructure.setProfessorNumber(majorTeacherStructure.getProfessorNumber()+1);
					majorTeacherStructure.setPosiViceProfessorNumber(majorTeacherStructure.getPosiViceProfessorNumber()+1);
				}
				else if (majorTeacherStructureTempsOTI.get(i).getTitle().equals("副教授"))
				{
					majorTeacherStructure.setPosiViceProfessorNumber(majorTeacherStructure.getPosiViceProfessorNumber()+1);
				}
				if(majorTeacherStructureTempsOTI.get(i).getDegree().equals("博士") || majorTeacherStructureTempsOTI.get(i).getDegree().equals("硕士"))
					majorTeacherStructure.setMasterDoctorNumber(majorTeacherStructure.getMasterDoctorNumber()+1);
				//要不要重新放进map?
			}
		}
		//遍历添加低年级教授数目
		Iterator iterator = lowperfessorMapFTTI.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry entry = (Map.Entry) iterator.next();
			resultMap.get(entry.getKey()).setLowProfessorNumber((int)entry.getValue());
		}
		iterator = lowperfessorMapET.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry entry = (Map.Entry) iterator.next();
			resultMap.get(entry.getKey()).setLowProfessorNumber((int)entry.getValue());
		}
		iterator = lowperfessorMapOTI.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry entry = (Map.Entry) iterator.next();
			resultMap.get(entry.getKey()).setLowProfessorNumber((int)entry.getValue());
		}
		
		List resultList = new ArrayList<MajorTeacherStructure>();
		iterator = resultMap.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry entry = (Map.Entry) iterator.next();
			MajorTeacherStructure majorTeacherStructure = (MajorTeacherStructure)entry.getValue();
			majorTeacherStructure.setProfessorProportion(majorTeacherStructure.getProfessorNumber()*1.0/majorTeacherStructure.getTeacherNumber()*100);
			majorTeacherStructure.setLowProfessorProportion(majorTeacherStructure.getLowProfessorNumber()*1.0/majorTeacherStructure.getTeacherNumber()*100);
			majorTeacherStructure.setPosiViceProfessorProportion(majorTeacherStructure.getPosiViceProfessorNumber()*1.0/majorTeacherStructure.getTeacherNumber()*100);
			majorTeacherStructure.setMasterDoctorProportion(majorTeacherStructure.getMasterDoctorNumber()*1.0/majorTeacherStructure.getTeacherNumber()*100);
			resultList.add(majorTeacherStructure);
		}
		return resultList;
	}
	
	@SuppressWarnings({ "unused", "rawtypes" })
	@Override
	public List<MajorTeacherStructure> getMajorTeacherStructureTOP5LOW5(Map params){
		List<MajorTeacherStructure> tempList = getMajorTeacherStructure(params);
		List<MajorTeacherStructure> resultList = new ArrayList<MajorTeacherStructure>();
		Collections.sort(tempList);
		for(int i = 0; i<5 && i<tempList.size(); i++)
		{
			MajorTeacherStructure majorTeacherStructure = new MajorTeacherStructure(i+1, tempList.get(i).getMajor(),
					tempList.get(i).getTeacherNumber(), tempList.get(i).getProfessorNumber(), tempList.get(i).getProfessorProportion(),
					tempList.get(i).getLowProfessorNumber(), tempList.get(i).getLowProfessorProportion(),
					tempList.get(i).getPosiViceProfessorNumber(), tempList.get(i).getPosiViceProfessorProportion(),
					tempList.get(i).getMasterDoctorNumber(), tempList.get(i).getMasterDoctorProportion(), "本科授课主讲教师中，教授和副教授比例最高的5个专业：");
			resultList.add(majorTeacherStructure);
		}
		for(int i = tempList.size()-1; i>=0 && i>=tempList.size()-5; i--)
		{
			MajorTeacherStructure majorTeacherStructure = new MajorTeacherStructure(tempList.size()-i, tempList.get(i).getMajor(),
					tempList.get(i).getTeacherNumber(), tempList.get(i).getProfessorNumber(), tempList.get(i).getProfessorProportion(),
					tempList.get(i).getLowProfessorNumber(), tempList.get(i).getLowProfessorProportion(),
					tempList.get(i).getPosiViceProfessorNumber(), tempList.get(i).getPosiViceProfessorProportion(),
					tempList.get(i).getMasterDoctorNumber(), tempList.get(i).getMasterDoctorProportion(), "本科授课主讲教师中，教授和副教授比例最低的5个专业：");
			resultList.add(majorTeacherStructure);
		}
		return resultList;
	}
}
