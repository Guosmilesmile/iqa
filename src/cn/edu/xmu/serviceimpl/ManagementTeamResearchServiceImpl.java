package cn.edu.xmu.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.dao.ManagerInfoDao;
import cn.edu.xmu.dao.TeachingAwardDao;
import cn.edu.xmu.dao.TeachingResearchAndReformProjectDao;
import cn.edu.xmu.daoimpl.ManagerInfoDaoImpl;
import cn.edu.xmu.daoimpl.TeachingAwardDaoImpl;
import cn.edu.xmu.daoimpl.TeachingResearchAndReformProjectDaoImpl;
import cn.edu.xmu.entity.ManagementTeamResearch;
import cn.edu.xmu.entity.ManagerInfo;
import cn.edu.xmu.entity.TeachingAward;
import cn.edu.xmu.entity.TeachingResearchAndReformProject;
import cn.edu.xmu.service.ManagementTeamResearchService;
import cn.edu.xmu.table.ManagerInfoTable;
import cn.edu.xmu.table.TeachingAwardTable;
import cn.edu.xmu.table.TeachingResearchAndReformProjectTable;
/**
 * @author zhantu
 * 6.3 教学管理队伍教学研究情况
 */
public class ManagementTeamResearchServiceImpl implements ManagementTeamResearchService{

	private TeachingAwardDao teachingAwardDao = new TeachingAwardDaoImpl();
	private TeachingResearchAndReformProjectDao teachingResearchAndReformProjectDao = new TeachingResearchAndReformProjectDaoImpl();
	private ManagerInfoDao managerInfoDao = new ManagerInfoDaoImpl();
	
	
	@Override
	public ManagementTeamResearch getManagementTeamResearch(Map params){
		String rowTitle = "数量";//行名
		 
		int achieveTotal = 0;//记录教学成果的总数,用于查询
		/*教学管理人员主持教学成果奖（项）*/
		int managerachieveTotal = 0;//教学管理人员参与总数
		int nationAchieve = 0;//国家级
		int provinceAchieve = 0;//省部级
		
		int projectTotal = 0;//记录项目总数,用于查询
		/*教学管理人员主持教育教学研究与改革项目*/
		int managerprojectTotalCount = 0;//项目总数
		int nationProjectCount = 0;//国家级
		int provinceProjectCount = 0;//省部级
		int schoolProjectCount = 0;//校级
		
		/*教学管理人员主持教育教学研究与改革项目(经费)*/
		float managerprojectTotalFee = 0;//经费总数
		float nationProjectFee = 0;//国家级
		float provinceProjectFee = 0;//省部级
		float schoolProjectFee = 0;//校级
		
		String college = "";
		
		//表3-3 相关管理人员基本信息
		Map paramsForManagerInfo;
		//表7-3-2 教学成果奖
		Map paramsForTeacherAchieve;
		//表7-3-1 教育教学研究与改革项目
		Map paramsForTeachingResearch ;
		
		if (params != null) {
			paramsForManagerInfo = params;
			paramsForTeacherAchieve = params;
			paramsForTeachingResearch = params;
		}else {
			paramsForManagerInfo = new HashMap();
			paramsForTeacherAchieve = new HashMap();
			paramsForTeachingResearch = new HashMap();
		}
		if (params == null ) {
			params = new HashMap();
		}else if (params.keySet().size() != 0) {
			for (Object object : params.keySet()) {
				String key = object.toString();
				String value = (String) params.get(key);
				if (key.equals("college")) {
					college = value;
					params.remove("college");
					paramsForManagerInfo.put(ManagerInfoTable.MI_COLLEGE, value);
					paramsForTeachingResearch.put(TeachingResearchAndReformProjectTable.TRARP_COLLEGE, value);
					paramsForTeacherAchieve.put(TeachingAwardTable.TA_COLLEGE, value);
				}
				if (key.equals("deadline")) {
					params.remove("deadline");
					paramsForManagerInfo.put(ManagerInfoTable.MI_DEADLINE, value);
					paramsForTeachingResearch.put(TeachingResearchAndReformProjectTable.TRARP_DEADLINE, value);
					paramsForTeacherAchieve.put(TeachingAwardTable.TA_DEADLINE, value);
				}
			}
		}
		
		int managertotal = managerInfoDao.getManagerInfoCount(paramsForManagerInfo);//获取总数
		paramsForManagerInfo.put(ManagerInfoTable.MI_MANAGERTYPE, "教学管理人员");
		List<ManagerInfo> managerInfolist = managerInfoDao.getManagerInfo(0, managertotal, ManagerInfoTable.MI_ID, "asc", paramsForManagerInfo, null);//获取所有管理人员list
		List<Integer> managerworknumberlist = new ArrayList<Integer>();
		for(ManagerInfo managerInfo:managerInfolist)
			managerworknumberlist.add(Integer.valueOf(managerInfo.getMi_worknumber()));//将所有教学管理人员的工号存到list中
		//教学管理人员主持教学成果奖（项）
		achieveTotal = teachingAwardDao.getTeachingAwardCount(paramsForTeacherAchieve);
		List<TeachingAward> achieveList = teachingAwardDao.getTeachingAward(0, achieveTotal, TeachingAwardTable.TA_ID, "asc", paramsForTeachingResearch,null);
		
		for(TeachingAward teachingAward:achieveList)
			if(managerworknumberlist.contains(Integer.valueOf(teachingAward.getTa_comperenumber())))
			{
				managerachieveTotal++;
				if(teachingAward.getTa_level().equals("国家级"))
					nationAchieve++;
				else if (teachingAward.getTa_level().equals("省部级"))
					provinceAchieve++;
			}
		//教学管理人员主持教育教学研究与改革项目
		projectTotal = teachingResearchAndReformProjectDao.getTeachingResearchAndReformProjectCount(paramsForTeachingResearch);
		List<TeachingResearchAndReformProject> projectList = teachingResearchAndReformProjectDao.getTeachingResearchAndReformProject(0, projectTotal, TeachingResearchAndReformProjectTable.TRARP_ID, "asc", paramsForTeachingResearch,null);
		float fee = 0;
		for(TeachingResearchAndReformProject teachingResearchAndReformProject:projectList)
			if(managerworknumberlist.contains(Integer.valueOf(teachingResearchAndReformProject.getTrarp_comperenumber())))
			{
				managerprojectTotalCount++;
				fee = teachingResearchAndReformProject.getTrarp_funds();
				managerprojectTotalFee+=fee;
				if(teachingResearchAndReformProject.getTrarp_level().equals("国家级"))
				{
					nationProjectCount++;
					nationProjectFee+=fee;
				}
				else if (teachingResearchAndReformProject.getTrarp_level().equals("省部级"))
				{
					provinceProjectCount++;
					provinceProjectFee+=fee;
				}
				else if (teachingResearchAndReformProject.getTrarp_level().equals("其他"))
				{
					schoolProjectCount++;
					schoolProjectFee+=fee;
				}
			}
		ManagementTeamResearch managementTeamResearch = new ManagementTeamResearch(rowTitle, managerachieveTotal, nationAchieve, provinceAchieve, managerprojectTotalCount, nationProjectCount, provinceProjectCount, schoolProjectCount, managerprojectTotalFee, nationProjectFee, provinceProjectFee, schoolProjectFee, college);
		return managementTeamResearch;
	}

}
