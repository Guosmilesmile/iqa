package cn.edu.xmu.serviceimpl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.dao.ForeignExchangeDao;
import cn.edu.xmu.dao.ForeignStudentInfoDao;
import cn.edu.xmu.dao.StudentNumberInfoDao;
import cn.edu.xmu.dao.UndergraStuInlandCommuDao;
import cn.edu.xmu.daoimpl.ForeignExchangeDaoImpl;
import cn.edu.xmu.daoimpl.ForeignStudentInfoDaoImpl;
import cn.edu.xmu.daoimpl.StudentNumberInfoDaoImpl;
import cn.edu.xmu.daoimpl.UndergraStuInlandCommuDaoImpl;
import cn.edu.xmu.entity.ForeignExchange;
import cn.edu.xmu.entity.ForeignStudentInfo;
import cn.edu.xmu.entity.StudentExchangeSituation;
import cn.edu.xmu.entity.StudentNumberInfo;
import cn.edu.xmu.entity.UndergraStuInlandCommu;
import cn.edu.xmu.service.StudentExchangeSituationService;
import cn.edu.xmu.table.ForeignExchangeTable;
import cn.edu.xmu.table.ForeignStudentInfoTable;
import cn.edu.xmu.table.StudentNumberInfoTable;
import cn.edu.xmu.table.UndergraStuInlandCommuTable;

public class StudentExchangeSituationServiceImpl implements StudentExchangeSituationService {
	private ForeignStudentInfoDao fsiDao = new ForeignStudentInfoDaoImpl();
	private StudentNumberInfoDao sniDao = new StudentNumberInfoDaoImpl();
	private ForeignExchangeDao feDao = new ForeignExchangeDaoImpl();
	private UndergraStuInlandCommuDao usDao = new UndergraStuInlandCommuDaoImpl();

	@Override
	public StudentExchangeSituation getStudentExchangeSituation(Map params) {
		StudentExchangeSituation s1 = new StudentExchangeSituation();
		
		String rowTitle = "数量";//行名称
		//国外及港澳台学生在校本科生数
		int foreignPersonCount = 0;
		//当年交流本科生数
		int exchangePersonCount = 0;
		//占全日制本科生比例（%）
		double percent = 0;
		//本校到境外
		int schoolToOverseasCount = 0;
		//本校到境内
		int schoolToTerritoryCount = 0;
		//境内到本校
		int territoryToSchoolCount = 0;
		//境外到本校
	    int overseasToSchoolCount = 0;
		
		String college = "";
		s1.setRowTitle(rowTitle);
		
		Map paramsForStudentExchangeSituationMap = new HashMap();
		Map paramsForStudentNumberInfoMap = new HashMap();
		Map paramsForForeignExchange = new HashMap();
		Map paramsForUndergraStuInlandCommu = new HashMap();
		if (params == null ) {
			params = new HashMap();
		}else if (params.keySet().size() != 0) {
			for (Object object : params.keySet()) {
				String key = object.toString();
				String value = (String) params.get(key);
				if (key.equals("college")) {
					college = value;
					params.remove("college");
					paramsForStudentExchangeSituationMap.put(ForeignStudentInfoTable.FSI_COLLEGE, value);
					paramsForStudentNumberInfoMap.put(StudentNumberInfoTable.SNI_COLLEGE, value);
					paramsForForeignExchange.put(ForeignExchangeTable.FE_COLLEGE, value);
					paramsForUndergraStuInlandCommu.put(UndergraStuInlandCommuTable.USIC_COLLEGE, value);
				}
				if (key.equals("deadline")) {
					params.remove("deadline");
					paramsForStudentExchangeSituationMap.put(ForeignStudentInfoTable.FSI_DEADLINE, value);
					paramsForStudentNumberInfoMap.put(StudentNumberInfoTable.SNI_DEADLINE,value);
					paramsForForeignExchange.put(ForeignExchangeTable.FE_DEADLINE, value);
					paramsForUndergraStuInlandCommu.put(UndergraStuInlandCommuTable.USIC_DEADLINE, value);
				}
			}
		}
		
		s1.setCollege(college);
		
		int totalcount = fsiDao.getForeignStudentInfoCount(paramsForStudentExchangeSituationMap);
		System.out.println("=====================totalcount"+totalcount);
		List<ForeignStudentInfo> foreignStudentInfos = fsiDao.getForeignStudentInfo(0, totalcount, ForeignStudentInfoTable.FSI_ID, "asc", paramsForStudentExchangeSituationMap, null);
		System.out.println("==================="+foreignStudentInfos.size());
		for (ForeignStudentInfo foreignStudentInfo : foreignStudentInfos) {
			//国外及港澳台学生在校本科生数
			if(foreignStudentInfo.getFsi_foreigncurrentstudentnumber()!=null && foreignStudentInfo.getFsi_hkcurrentstudentnumber()!=null && foreignStudentInfo.getFsi_maccurrentstudentnumber()!=null && foreignStudentInfo.getFsi_twncurrentstudentnumber()!=null)
			{
				foreignPersonCount += foreignStudentInfo.getFsi_foreigncurrentstudentnumber()+foreignStudentInfo.getFsi_hkcurrentstudentnumber()+foreignStudentInfo.getFsi_maccurrentstudentnumber()+foreignStudentInfo.getFsi_twncurrentstudentnumber();
				System.out.println("==================forrignPersonCount"+foreignPersonCount);
				s1.setForeignPersonCount(foreignPersonCount);
			}
			
			//当年交流本科生数
			if(foreignStudentInfo.getFsi_allgraduatenumber()!=null && foreignStudentInfo.getFsi_alldegreenumber()!=null && foreignStudentInfo.getFsi_allenrollnumber()!=null && foreignStudentInfo.getFsi_allcurrentstudentnumber()!=null)
			{
				exchangePersonCount += foreignStudentInfo.getFsi_allgraduatenumber()+foreignStudentInfo.getFsi_alldegreenumber()+foreignStudentInfo.getFsi_allenrollnumber()+foreignStudentInfo.getFsi_allcurrentstudentnumber();
				s1.setExchangePersonCount(exchangePersonCount);
			}
			
		}
		
		int totalcount1 = sniDao.getStudentNumberInfoCount(paramsForStudentNumberInfoMap);
		int ordiundergrastucount = 0;
		List<StudentNumberInfo> studentNumberInfos = sniDao.getStudentNumberInfos(0, totalcount1, StudentNumberInfoTable.SNI_ID, "asc", paramsForStudentNumberInfoMap,null);
		for(StudentNumberInfo studentNumberInfo:studentNumberInfos){
			//先取出全日制本科生的数量
			if(studentNumberInfo.getSni_ordiundergrastu()!=null)
			{
				ordiundergrastucount+= studentNumberInfo.getSni_ordiundergrastu();
			}
			
		}
		//percent = total > 0 ?(double)chujiCount / total * 100:0;
		//double chujiPer = new BigDecimal(percent).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
		//占全日制本科生比例（%）
		double percenttemp = ordiundergrastucount>0?(double)exchangePersonCount/ordiundergrastucount*100:0;
		percent = new BigDecimal(percenttemp).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
		s1.setPercent(percent);
		
		int totalcount2 = feDao.getForeignExchangeCount(paramsForForeignExchange);
		List<ForeignExchange> foreignExchanges = feDao.getForeignExchanges(0, totalcount2, ForeignExchangeTable.FE_ID, "asc", paramsForForeignExchange,null);
		System.out.println("====================foreignExchanges"+foreignExchanges.size());
		for(ForeignExchange foreignExchange:foreignExchanges){
			if(foreignExchange.getFe_selftoforeign()!=null)
			{
				//本校到境外
				schoolToOverseasCount+= foreignExchange.getFe_selftoforeign();
				s1.setSchoolToOverseasCount(schoolToOverseasCount);
			}
			if(foreignExchange.getFe_foreigntoself()!=null)
			{
				//境外到本校
				overseasToSchoolCount+= foreignExchange.getFe_foreigntoself();
				s1.setOverseasToSchoolCount(overseasToSchoolCount);
			}
		}
		
		int totalcount3 = usDao.getUndergraStuInlandCommuCount(paramsForUndergraStuInlandCommu);
		List<UndergraStuInlandCommu> undergraStuInlandCommus = usDao.getUndergraStuInlandCommus(0, totalcount3, UndergraStuInlandCommuTable.USIC_ID, "asc", paramsForUndergraStuInlandCommu, null);
		for(UndergraStuInlandCommu undergraStuInlandCommu:undergraStuInlandCommus)
		{
			//本校到境内
			if(undergraStuInlandCommu.getUsic_outnumber()!=null)
			{
				schoolToTerritoryCount+= undergraStuInlandCommu.getUsic_outnumber();
				s1.setSchoolToTerritoryCount(schoolToTerritoryCount);
			}
			//境内到本校
			if(undergraStuInlandCommu.getUsic_innumber()!=null)
			{
				territoryToSchoolCount+= undergraStuInlandCommu.getUsic_innumber();
				s1.setTerritoryToSchoolCount(territoryToSchoolCount);
			}
		}
		return s1;
	}



}
