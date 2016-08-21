package cn.edu.xmu.serviceimpl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import cn.edu.xmu.dao.FullTimeTeacherInfoDao;
import cn.edu.xmu.daoimpl.FullTimeTeacherInfoDaoImpl;
import cn.edu.xmu.entity.FullTimeTeacher;
import cn.edu.xmu.entity.FullTimeTeacherInfo;
import cn.edu.xmu.service.FullTimeTeacherService;
import cn.edu.xmu.table.FullTimeTeacherInfoTable;

/**
 * 附表3 各教学单位专任教师结构
 * @author yue
 *
 */
public class FullTimeTeacherServiceImpl implements FullTimeTeacherService{

	FullTimeTeacherInfoDao fttiDao = new FullTimeTeacherInfoDaoImpl();
	@SuppressWarnings({ "unchecked", "deprecation", "rawtypes" })
	@Override
	public List<FullTimeTeacher> getFullTimeTeacher(Map params) {
		// TODO Auto-generated method stub
		int serialnumber = 0;
		String college = "";
		Map paramsFullTimeTeacherInfo = new HashMap<>();
		
		 if(params == null)
		 {
			 params = new HashMap<>();
		 }else if (params.keySet().size() != 0) {
			 for(Object object:params.keySet()){
				 String key = object.toString();
				 String value = (String) params.get(key);
					if (key.equals("college")) {
						college = value;
						params.remove("college");
						paramsFullTimeTeacherInfo.put(FullTimeTeacherInfoTable.FTTI_COLLEGE, value);
					}
					if (key.equals("deadline")) {
						params.remove("deadline");
						paramsFullTimeTeacherInfo.put(FullTimeTeacherInfoTable.FTTI_DEADLINE, value);
					}
			 }
			
		}
		 
		List<FullTimeTeacherInfo> fttis = new ArrayList<FullTimeTeacherInfo>();
		fttis = fttiDao.getFullTimeTeachers(0, fttiDao.getFullTimeTeacherCount(paramsFullTimeTeacherInfo), FullTimeTeacherInfoTable.FTTI_ID, "asc", paramsFullTimeTeacherInfo, null);
		Map<String, FullTimeTeacher> resultmap = new HashMap<>();
		
		for(int i = 0; i<fttis.size(); i++)
		{
			if(fttis.get(i).getFtti_departmentname() != null || !"".equals(fttis.get(i).getFtti_departmentname()))
			{
				if(resultmap.containsKey(fttis.get(i).getFtti_departmentname()))
				{
					FullTimeTeacher fullTimeTeacher = resultmap.get(fttis.get(i).getFtti_departmentname());
					fullTimeTeacher.setFullTimeTeacherCount(fullTimeTeacher.getFullTimeTeacherCount() + 1);
					if(fttis.get(i).getFtti_professionaltitle() != null || !"".equals(fttis.get(i).getFtti_professionaltitle()))
					{
						if(fttis.get(i).getFtti_professionaltitle().equals("教授"))
							fullTimeTeacher.setProfessor(fullTimeTeacher.getProfessor() + 1);
						else if(fttis.get(i).getFtti_professionaltitle().equals("副教授"))
							fullTimeTeacher.setAssociateProfessor(fullTimeTeacher.getAssociateProfessor() + 1);
						else
						{
							fullTimeTeacher.setOtherProfessionalTitles(fullTimeTeacher.getOtherProfessionalTitles() + 1);
						}
					}
					if(fttis.get(i).getFtti_degree() != null || !"".equals(fttis.get(i).getFtti_degree()))
					{
						if(fttis.get(i).getFtti_degree().equals("博士"))
							fullTimeTeacher.setDoctor(fullTimeTeacher.getDoctor() + 1);
						else if(fttis.get(i).getFtti_degree().equals("硕士"))
							fullTimeTeacher.setMaster(fullTimeTeacher.getMaster() + 1);
						else {
							fullTimeTeacher.setOtherDegrees(fullTimeTeacher.getOtherDegrees()+ 1);
						}
					}
					if(fttis.get(i).getFtti_educationsource() != null || !"".equals(fttis.get(i).getFtti_educationsource()))
					{
						if(fttis.get(i).getFtti_educationsource().equals("本校"))
							fullTimeTeacher.setInSchool(fullTimeTeacher.getInSchool() + 1);
						else if(fttis.get(i).getFtti_educationsource().equals("外校（境内）"))
							fullTimeTeacher.setTerritory(fullTimeTeacher.getTerritory() + 1);
						else if(fttis.get(i).getFtti_educationsource().equals("外校（境外）"))
							fullTimeTeacher.setAbroad(fullTimeTeacher.getAbroad() + 1);
					}
					
				}
				else{
					String department = fttis.get(i).getFtti_departmentname();
					int fullTimeTeacherCount = 1;
					int professor = 0;
					int associateProfessor = 0;
					int otherProfessionalTitles = 0;
					if(fttis.get(i).getFtti_professionaltitle() != null || !"".equals(fttis.get(i).getFtti_professionaltitle()))
					{
						if(fttis.get(i).getFtti_professionaltitle().equals("教授"))
							professor += 1;
						else if(fttis.get(i).getFtti_professionaltitle().equals("副教授"))
							associateProfessor += 1;
						else {
							otherProfessionalTitles += 1;
						}
					}
					int doctor = 0;
					int master = 0;
					int otherDegrees = 0;
					if(fttis.get(i).getFtti_degree() != null || !"".equals(fttis.get(i).getFtti_degree()))
					{
						if(fttis.get(i).getFtti_degree().equals("博士"))
							doctor += 1;
						else if(fttis.get(i).getFtti_degree().equals("硕士"))
							master += 1;
						else {
							otherDegrees += 1;
						}
					}
					paramsFullTimeTeacherInfo.put(FullTimeTeacherInfoTable.FTTI_DEPARTMENTNAME, department);
					
					int under35 = 0;
					java.util.Date now = new java.util.Date();
					Date nowsql = new Date(now.getTime());
					java.util.Date year35 = new java.util.Date();
					year35.setYear(year35.getYear()-35);
					Date year35sql = new Date(year35.getTime());
					under35 = fttiDao.getCountByRange(FullTimeTeacherInfoTable.FTTI_BIRTHDAY, year35sql, nowsql, paramsFullTimeTeacherInfo);
					int between36_45 = 0;
					java.util.Date year45 = new java.util.Date();
					year45.setYear(year45.getYear()-45);
					Date year45sql = new Date(year45.getTime());
					between36_45 = fttiDao.getCountByRange(FullTimeTeacherInfoTable.FTTI_BIRTHDAY, year45sql, year35sql, paramsFullTimeTeacherInfo);
					int between46_55 = 0;
					java.util.Date year55 = new java.util.Date();
					year55.setYear(year55.getYear()-55);
					Date year55sql = new Date(year55.getTime());
					between46_55 = fttiDao.getCountByRange(FullTimeTeacherInfoTable.FTTI_BIRTHDAY, year55sql, year45sql, paramsFullTimeTeacherInfo);
					int above56 = 0;
					java.util.Date year110 = new java.util.Date();
					year110.setYear(year110.getYear()-110);
					Date year110sql = new Date(year110.getTime());
					above56 = fttiDao.getCountByRange(FullTimeTeacherInfoTable.FTTI_BIRTHDAY, year110sql, year55sql, paramsFullTimeTeacherInfo);
					int inSchool = 0;
					int abroad = 0;
					int territory = 0;
					if(fttis.get(i).getFtti_educationsource() != null || !"".equals(fttis.get(i).getFtti_educationsource()))
					{
						if(fttis.get(i).getFtti_educationsource().equals("本校"))
							inSchool += 1;
						else if(fttis.get(i).getFtti_educationsource().equals("外校（境内）"))
							territory += 1;
						else if(fttis.get(i).getFtti_educationsource().equals("外校（境外）"))
							abroad += 1;
					}
					paramsFullTimeTeacherInfo.remove(FullTimeTeacherInfoTable.FTTI_DEPARTMENTNAME);
					resultmap.put(fttis.get(i).getFtti_departmentname(), new FullTimeTeacher(++serialnumber, department, fullTimeTeacherCount, professor, associateProfessor, otherProfessionalTitles, doctor, master, otherDegrees, under35, between36_45, between46_55, above56, inSchool, abroad, territory, college));  
				}
			}
		}
		List<FullTimeTeacher> ftts = new ArrayList<FullTimeTeacher>();
		Iterator iterator = resultmap.entrySet().iterator();
		int i = 1;
		while (iterator.hasNext()) {
			Map.Entry entry = (Map.Entry) iterator.next();
			FullTimeTeacher val = (FullTimeTeacher)entry.getValue();
			val.setSerialnumber(i);
			i++;
			ftts.add(val);
		}
		return ftts;
	}

}
