package cn.edu.xmu.serviceimpl;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.dao.AcademicPaperDao;
import cn.edu.xmu.dao.FourSixDao;
import cn.edu.xmu.dao.GoInterConferDao;
import cn.edu.xmu.dao.PublishProductDao;
import cn.edu.xmu.dao.StuPhysicalHealthInfoDao;
import cn.edu.xmu.dao.StudentPatentDao;
import cn.edu.xmu.dao.UndergraduateAcademicCompetitionDao;
import cn.edu.xmu.dao.UndergraduateArtAndSportCompetitionDao;
import cn.edu.xmu.dao.UndergraduateInnovationAndSkillCompetitionDao;
import cn.edu.xmu.daoimpl.AcademicPaperDaoImpl;
import cn.edu.xmu.daoimpl.FourSixDaoImpl;
import cn.edu.xmu.daoimpl.GoInterConferDaoImpl;
import cn.edu.xmu.daoimpl.PublishProductDaoImpl;
import cn.edu.xmu.daoimpl.StuPhysicalHealthInfoDaoImpl;
import cn.edu.xmu.daoimpl.StudentPatentDaoImpl;
import cn.edu.xmu.daoimpl.UndergraduateAcademicCompetitionDaoImpl;
import cn.edu.xmu.daoimpl.UndergraduateArtAndSportCompetitionDaoImpl;
import cn.edu.xmu.daoimpl.UndergraduateInnovationAndSkillCompetitionDaoImpl;
import cn.edu.xmu.entity.FourSix;
import cn.edu.xmu.entity.StuDevelopInfo;
import cn.edu.xmu.entity.StuPhysicalHealthInfo;
import cn.edu.xmu.service.StuDevelopInfoService;
import cn.edu.xmu.table.AcademicPaperTable;
import cn.edu.xmu.table.FourSixTable;
import cn.edu.xmu.table.GoInterConferTable;
import cn.edu.xmu.table.PublishProductTable;
import cn.edu.xmu.table.StuPhysicalHealthInfoTable;
import cn.edu.xmu.table.StudentPatentTable;
import cn.edu.xmu.table.UndergraduateAcademicCompetitionTable;
import cn.edu.xmu.table.UndergraduateArtAndSportCompetitionTable;
import cn.edu.xmu.table.UndergraduateInnovationAndSkillCompetitionTable;

public class StuDevelopInfoServiceImpl implements StuDevelopInfoService{

	
	private UndergraduateAcademicCompetitionDao uacDao = new UndergraduateAcademicCompetitionDaoImpl();
	private UndergraduateInnovationAndSkillCompetitionDao uiacDao = new UndergraduateInnovationAndSkillCompetitionDaoImpl();
	private UndergraduateArtAndSportCompetitionDao uascDao = new UndergraduateArtAndSportCompetitionDaoImpl();
	private AcademicPaperDao apDao = new AcademicPaperDaoImpl();
	private PublishProductDao ppDao = new PublishProductDaoImpl();
	private StudentPatentDao spDao = new StudentPatentDaoImpl();
	private FourSixDao fsDao = new FourSixDaoImpl();
	private StuPhysicalHealthInfoDao sphiDao = new StuPhysicalHealthInfoDaoImpl();
	private GoInterConferDao gicDao = new GoInterConferDaoImpl();
 	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public StuDevelopInfo getStuDevelopInfo(Map params) {
		/*1.学科竞赛获奖（项）*/
		//总数
		int  academicCompetitionSum = 0;
		//其中：国家级
		int academicCompetitionNationLevel = 0;
		//省部级
		int academicCompetitionProvincialLevel = 0;
		
		
		/*2.本科生创新活动、技能竞赛获奖*/
		//总数
		int innovationAndSkillCompetitionSum = 0;
		//其中：国家级
		int innovationAndSkillCompetitionNationLevel = 0;
		//省部级
		int innovationAndSkillCompetitionProvincialLevel = 0;
		
		/*3.文艺、体育竞赛获奖*/
		//总数
		int artAndSportCompetitionSum = 0;
		//其中：国家级
		int artAndSportCompetitionNationLevel = 0;
		//省部级
		int artAndSportCompetitionProvincialLevel = 0;
		
		
		//4.学生发表学术论文（篇）
		int academicPaper = 0;
		
		//5.学生发表作品数（篇、册）
		int publishProduct = 0;
		
		//6.学生获准专利数
		int studentPatent = 0;
		
		
		/*7.英语等级考试*/
		//英语四级考试累计通过率（%）
		double englishiLevelFourPassRate = 0;
		//英语六级考试累计通过率（%）
		double englishiLevelSixPassRate = 0;
		
		//8.体质合格率（%）
		double stuPhysicalHealPassRate = 0;
		
		//9.参加国际会议（人次）
		int goInterConferCount = 0;
		
		String college = "";
		Date deadline = null;
		
		//附表6-2-1-1本科生参加学科竞赛情况
		Map paramsAcademicCompetition = new HashMap<>();
		//附表6-2-1-2本科生参加本科生创新活动、技能竞赛情况
		Map paramsInnovationAndSkillCompetition = new HashMap<>();
		//附表6-2-1-3本科生参加文艺、体育竞赛情况
		Map paramsArtAndSportCompetition = new HashMap<>();
		//附表6-2-1-4本科生发表学术论文情况
		Map paramsAcademicPaper = new HashMap<>();
		//附表6-2-1-5本科生发表作品情况
		Map paramsPublishProduct = new HashMap<>();
		//附表6-2-1-6本科生获得专利情况
		Map paramsStudentPatent = new HashMap<>();
		//附表6-2-1-7 本科毕业生大学英语四六级考试累计通过率（学年）
		Map paramsFourSix = new HashMap<>();
		//附表6-2-1-8厦门大学学生体质健康情况（学年）
		Map paramsStuPhysicalHeal = new HashMap<>();
		//附表6-2-1-9学生参加国际会议情况
		Map paramsGoInterConfer = new HashMap<>();
		
		if(params == null){
			params = new HashMap<>();
		}else if (params.keySet().size()!= 0) {
			for(Object object:params.keySet())
			{
				String key = object.toString();
				String value = (String)params.get(key);
				if(key.equals("college")){
					college = value;
					params.remove("college");
					paramsAcademicCompetition.put(UndergraduateAcademicCompetitionTable.UAC_COLLEGE, college);
					paramsInnovationAndSkillCompetition.put(UndergraduateInnovationAndSkillCompetitionTable.UIASC_COLLEGE,college);
					paramsArtAndSportCompetition.put(UndergraduateArtAndSportCompetitionTable.UAASC_COLLEGE, college);
					paramsAcademicPaper.put(AcademicPaperTable.AP_COLLEGE, college);
					paramsPublishProduct.put(PublishProductTable.PP_COLLEGE, college);
					paramsStudentPatent.put(StudentPatentTable.SP_COLLEGE, college);
					paramsFourSix.put(FourSixTable.FX_COLLEGE, college);
					paramsStuPhysicalHeal.put(StuPhysicalHealthInfoTable.SPHI_COLLEGE, college);
					paramsGoInterConfer.put(GoInterConferTable.GIC_COLLEGE, college);
				}
				if(key.equals("deadline")){
					deadline = Date.valueOf(value);
					params.remove("deadline");
					paramsAcademicCompetition.put(UndergraduateAcademicCompetitionTable.UAC_DEADLINE, deadline);
					paramsInnovationAndSkillCompetition.put(UndergraduateInnovationAndSkillCompetitionTable.UIASC_DEADLINE,deadline);
					paramsArtAndSportCompetition.put(UndergraduateArtAndSportCompetitionTable.UAASC_DEADLINE, deadline);
					paramsAcademicPaper.put(AcademicPaperTable.AP_DEADLINE, deadline);
					paramsPublishProduct.put(PublishProductTable.PP_DEADLINE, deadline);
					paramsStudentPatent.put(StudentPatentTable.SP_DEADLINE, deadline);
					paramsFourSix.put(FourSixTable.FX_DEADLINE, deadline);
					paramsStuPhysicalHeal.put(StuPhysicalHealthInfoTable.SPHI_DEADLINE, deadline);
					paramsGoInterConfer.put(GoInterConferTable.GIC_DEADLINE, deadline);
				}
			}
			
		}
		academicCompetitionSum = uacDao.getUndergraduateAcademicCompetitionCount(paramsAcademicCompetition);
		paramsAcademicCompetition.put(UndergraduateAcademicCompetitionTable.UAC_PRIZELEVEL, "国家级");
		academicCompetitionNationLevel = uacDao.getUndergraduateAcademicCompetitionCount(paramsAcademicCompetition);
		params.remove(UndergraduateAcademicCompetitionTable.UAC_PRIZELEVEL);
		paramsAcademicCompetition.put(UndergraduateAcademicCompetitionTable.UAC_PRIZELEVEL, "省部级");
		academicCompetitionProvincialLevel = uacDao.getUndergraduateAcademicCompetitionCount(paramsAcademicCompetition);
		
		innovationAndSkillCompetitionSum = uiacDao.getUndergraduateInnovationAndSkillCompetitionCount(paramsInnovationAndSkillCompetition);
		paramsInnovationAndSkillCompetition.put(UndergraduateInnovationAndSkillCompetitionTable.UIASC_PRIZELEVEL, "国家级");
		innovationAndSkillCompetitionNationLevel = uiacDao.getUndergraduateInnovationAndSkillCompetitionCount(paramsInnovationAndSkillCompetition);
		params.remove(UndergraduateInnovationAndSkillCompetitionTable.UIASC_PRIZELEVEL);
		paramsInnovationAndSkillCompetition.put(UndergraduateInnovationAndSkillCompetitionTable.UIASC_PRIZELEVEL, "省部级");
		innovationAndSkillCompetitionProvincialLevel = uiacDao.getUndergraduateInnovationAndSkillCompetitionCount(paramsInnovationAndSkillCompetition);
		
		artAndSportCompetitionSum = uascDao.getUndergraduateArtAndSportCompetitionCount(paramsArtAndSportCompetition);
		paramsArtAndSportCompetition.put(UndergraduateArtAndSportCompetitionTable.UAASC_PRIZELEVEL, "国家级");
		artAndSportCompetitionNationLevel = uascDao.getUndergraduateArtAndSportCompetitionCount(paramsArtAndSportCompetition);
		paramsArtAndSportCompetition.remove(UndergraduateArtAndSportCompetitionTable.UAASC_PRIZELEVEL);
		paramsArtAndSportCompetition.put(UndergraduateArtAndSportCompetitionTable.UAASC_PRIZELEVEL, "省部级");
		artAndSportCompetitionProvincialLevel = uascDao.getUndergraduateArtAndSportCompetitionCount(paramsArtAndSportCompetition);
		
		academicPaper = apDao.getAcademicPaperCount(paramsAcademicPaper);
		publishProduct = ppDao.getPublishProductCount(paramsPublishProduct);
		studentPatent = spDao.getStudentPatentCount(paramsStudentPatent);
		
		List<FourSix> fss = fsDao.getAllFourSix(0, fsDao.getFourSixCount(paramsFourSix), FourSixTable.FX_ID, "asc", paramsFourSix);
		for(FourSix fs:fss){
			
			if("四级".equals(fs.getFx_level()))
			{
				englishiLevelFourPassRate = fs.getFx_passpercent();
			}
			if("六级".equals(fs.getFx_level()))
			{
				englishiLevelSixPassRate = fs.getFx_passpercent();
			}
		}
		
		List<StuPhysicalHealthInfo> sphis = sphiDao.getStuPhysicalHealthInfo(0, sphiDao.getStuPhysicalHealthInfoCount(paramsStuPhysicalHeal), StuPhysicalHealthInfoTable.SPHI_ID, "asc", paramsStuPhysicalHeal, deadline);
		int passnumber = 0;
		int testnumber = 0;
		for(StuPhysicalHealthInfo sphi:sphis)
		{
			if("合计".equals(sphi.getSphi_grade()))
			{
				if(sphi.getSphi_passnumber() != null || !"".equals(sphi.getSphi_passnumber()))
					passnumber += sphi.getSphi_passnumber();
				if(sphi.getSphi_testnumber() != null || !"".equals(sphi.getSphi_testnumber()))
					testnumber += sphi.getSphi_testnumber();
			}
		}
		if(testnumber != 0)
		{
			stuPhysicalHealPassRate = (double)passnumber/testnumber * 100.0;
		}
		goInterConferCount = gicDao.getGoInterConferCount(paramsGoInterConfer);
		
		StuDevelopInfo sdi = new StuDevelopInfo(academicCompetitionSum, academicCompetitionNationLevel, academicCompetitionProvincialLevel, innovationAndSkillCompetitionSum, innovationAndSkillCompetitionNationLevel, innovationAndSkillCompetitionProvincialLevel, artAndSportCompetitionSum, artAndSportCompetitionNationLevel, artAndSportCompetitionProvincialLevel, academicPaper, publishProduct, studentPatent, englishiLevelFourPassRate, englishiLevelSixPassRate, stuPhysicalHealPassRate, goInterConferCount, college);
		return sdi;
		
		
	}

}
