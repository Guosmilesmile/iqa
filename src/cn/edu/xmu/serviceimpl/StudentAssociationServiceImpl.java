package cn.edu.xmu.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.attribute.standard.Sides;

import cn.edu.xmu.dao.StudentAssociationDao;
import cn.edu.xmu.daoimpl.StudentAssociationDaoImpl;
import cn.edu.xmu.entity.LabStuffInfo;
import cn.edu.xmu.entity.StudentAssociation;
import cn.edu.xmu.entity.StudentAssociationSituation;
import cn.edu.xmu.entity.TeachingResearchAndReformProject;
import cn.edu.xmu.service.StudentAssociationService;
import cn.edu.xmu.table.ListenSituationTable;
import cn.edu.xmu.table.StudentAssociationTable;
import cn.edu.xmu.table.StudentHomeTable;
import cn.edu.xmu.table.TeachingAwardTable;
import cn.edu.xmu.table.TeachingResearchAndReformProjectTable;

public class StudentAssociationServiceImpl implements StudentAssociationService {
	
	private StudentAssociationDao saDao = new StudentAssociationDaoImpl();

	@Override
	public List<StudentAssociationSituation> getStudentAssociation(Map params) {
		List<StudentAssociationSituation> sas  = new ArrayList<StudentAssociationSituation>();
		StudentAssociationSituation s1 = new StudentAssociationSituation();
		StudentAssociationSituation s2 = new StudentAssociationSituation();
		String rowTitle1 = "社团个数";//第一行行名
		String rowTitle2 = "参与人次数";//第二行行名
		//社团（个）总数
		int sa_sumcount = 0;
		//社团（个）其中：科技类
		int sa_sciencecount = 0;
		//社团（个）人文社会类
		int sa_humanisticcount = 0;
		//社团（个）体育类
		int sa_sportscount = 0;
		//社团（个）文艺类
		int sa_literatureartcount = 0;
		
		String college = "";
		//表6-3 学生社团
		
		s1.setRowTitle(rowTitle1);
		s2.setRowTitle(rowTitle2);

		Map paramsForStudentAssociation;
		paramsForStudentAssociation = new HashMap();
		if (params == null ) {
			params = new HashMap();
		}else if (params.keySet().size() != 0) {
			for (Object object : params.keySet()) {
				String key = object.toString();
				String value = (String) params.get(key);
				if (key.equals("college")) {
					college = value;
					params.remove("college");
					paramsForStudentAssociation.put(StudentAssociationTable.SA_COLLEGE, value);
				}
				if (key.equals("deadline")) {
					params.remove("deadline");
					paramsForStudentAssociation.put(StudentAssociationTable.SA_DEADLINE, value);
				}
			}
		}
		
		s1.setCollege(college);
		s1.setCollege(college);
		
		int totalCount = saDao.getStudentAssociationCount(paramsForStudentAssociation);
		
		List<StudentAssociation> studentAssociations = saDao.getAllStudentAssociation(0, totalCount, StudentAssociationTable.SA_ID, "asc", paramsForStudentAssociation);
		for (StudentAssociation studentAssociation : studentAssociations) {
			//社团个数 总数
			if(studentAssociation.getSa_sciencecount()!=null && studentAssociation.getSa_humanisticcount()!=null && studentAssociation.getSa_sportscount()!=null && studentAssociation.getSa_literatureartcount()!=null)
			{
				sa_sumcount += studentAssociation.getSa_sciencecount()+studentAssociation.getSa_humanisticcount()+studentAssociation.getSa_sportscount()+studentAssociation.getSa_literatureartcount();
				s1.setTotal(s1.getTotal()+sa_sumcount);
			}
			//社团个数 科技类社团
			if(studentAssociation.getSa_sciencecount()!=null)
			{
				sa_sciencecount += studentAssociation.getSa_sciencecount();
				s1.setScience(s1.getScience()+sa_sciencecount);
			}
			//社团个数 人文社会类社团
			if(studentAssociation.getSa_humanisticcount()!=null)
			{
				sa_humanisticcount += studentAssociation.getSa_humanisticcount();
				s1.setHumanistic(s1.getHumanistic()+sa_humanisticcount);
			}
			//社团个数 体育类社团
			if(studentAssociation.getSa_sportscount()!=null)
			{
				sa_sportscount += studentAssociation.getSa_sportscount();
				s1.setSports(s1.getSports()+sa_sportscount);
			}
			//社团个数 文艺类社团
			if(studentAssociation.getSa_literatureartcount()!=null)
			{
				sa_literatureartcount += studentAssociation.getSa_literatureartcount();
				s1.setLiteratureart(s1.getLiteratureart()+sa_literatureartcount);
			}
			
			//社团（个）总数
			sa_sumcount = 0;
			//社团（个）其中：科技类
			sa_sciencecount = 0;
			//社团（个）人文社会类
			sa_humanisticcount = 0;
			//社团（个）体育类
			sa_sportscount = 0;
			//社团（个）文艺类
			sa_literatureartcount = 0;
			
			//参与人次数 总数
			if(studentAssociation.getSa_scienceperson()!=null && studentAssociation.getSa_humanisticperson()!=null && studentAssociation.getSa_sportsperson()!=null &&studentAssociation.getSa_literatureartperson()!=null)
			{
				sa_sumcount += studentAssociation.getSa_scienceperson()+studentAssociation.getSa_humanisticperson()+studentAssociation.getSa_sportsperson()+studentAssociation.getSa_literatureartperson();
				s2.setTotal(s2.getTotal()+sa_sumcount);
			}
			//参与人次数 科技类社团
			if(studentAssociation.getSa_scienceperson()!=null)
			{
				sa_sciencecount += studentAssociation.getSa_scienceperson();
				s2.setScience(s2.getScience()+sa_sciencecount);
			}
			//参与人次数 人文社会类社团
			if(studentAssociation.getSa_humanisticperson()!=null)
			{
				sa_humanisticcount += studentAssociation.getSa_humanisticperson();
				s2.setHumanistic(s2.getHumanistic()+sa_humanisticcount);
			}
			//参与人次数 体育类社团
			if(studentAssociation.getSa_sportsperson()!=null)
			{
				sa_sportscount += studentAssociation.getSa_sportsperson();
				s2.setSports(s2.getSports()+sa_sportscount);
			}
			//参与人次数  文艺类
			if(studentAssociation.getSa_literatureartperson()!=null)
			{
				sa_literatureartcount += studentAssociation.getSa_literatureartperson();
				s2.setLiteratureart(s2.getLiteratureart()+sa_literatureartcount);
			}
			//社团（个）总数
			sa_sumcount = 0;
			//社团（个）其中：科技类
			sa_sciencecount = 0;
			//社团（个）人文社会类
			sa_humanisticcount = 0;
			//社团（个）体育类
			sa_sportscount = 0;
			//社团（个）文艺类
			sa_literatureartcount = 0;
		}
		sas.add(s1);
		sas.add(s2);
		return sas;
	}

}
