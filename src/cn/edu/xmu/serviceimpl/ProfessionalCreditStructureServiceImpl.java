package cn.edu.xmu.serviceimpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.dao.MajorInfoDao;
import cn.edu.xmu.daoimpl.MajorInfoDaoImpl;
import cn.edu.xmu.entity.MajorInfo;
import cn.edu.xmu.entity.ProfessionLayout;
import cn.edu.xmu.service.ProfessionalCreditStructureService;
import cn.edu.xmu.table.MajorInfoTable;

public class ProfessionalCreditStructureServiceImpl implements ProfessionalCreditStructureService{
	private MajorInfoDao majorDao = new MajorInfoDaoImpl();
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ProfessionLayout> getProfessionalCreditStructure(Map params)
	{
		String college = "";
		Map paramsForMajorInfo = new HashMap();
		if (params == null ) {
			params = new HashMap();
		}else if (params.keySet().size() != 0) {
			for (Object object : params.keySet()) {
				String key = object.toString();
				String value = (String) params.get(key);
				if (key.equals("college")) {
					params.remove("college");
					college = value;
					paramsForMajorInfo.put(MajorInfoTable.MI_COLLEGE, value);
				}
				if (key.equals("deadline")) {
					params.remove("deadline");
					paramsForMajorInfo.put(MajorInfoTable.MI_DEADLINE, value);
				}
			}
		}
		
		List<MajorInfo> majorInfos = majorDao.getMajorInfo(0, majorDao.getCount(paramsForMajorInfo), MajorInfoTable.MI_ID, "asc", paramsForMajorInfo);
		ProfessionLayout [] results = new ProfessionLayout[3];
		results[0] = new ProfessionLayout("所含专业数", college);
		results[1] = new ProfessionLayout("专业平均总学分", college);
		results[2] = new ProfessionLayout("专业平均实践教学环节学分比例（%）", college);
		//01哲学、02经济学、03法学、04教育学、05文学、06历史学、07理学、08工学、09农学、10医学、11军事学、12管理学、13艺术学。
		for(int i = 0; i<majorInfos.size(); i++)
		{
			if(majorInfos.get(i).getMi_majorcode().startsWith("01"))//01哲学
			{
				//平均总学分 = ((之前的总分 = 之前的平均总学分*专业数)+当前专业的学分数)/(之前的专业数+1)
				//!!if(majorInfos.get(i).getMi_allcredits()!=null)
					results[1].setPhilosophy((results[1].getPhilosophy()*results[0].getPhilosophy()+majorInfos.get(i).getMi_allcredits())/(results[0].getPhilosophy()+1));
				//专业平均实践教学环节学分比例（%）同上
				if(majorInfos.get(i).getMi_allcredits()!=0)
					results[2].setPhilosophy((results[2].getPhilosophy()*results[0].getPhilosophy()+majorInfos.get(i).getMi_concentratedpracticecredits()/majorInfos.get(i).getMi_allcredits())/(results[0].getPhilosophy()+1));
				results[0].setPhilosophy(results[0].getPhilosophy()+1);
			}
			else if(majorInfos.get(i).getMi_majorcode().startsWith("02"))//02经济学
			{
				results[1].setEconomic((results[1].getEconomic()*results[0].getEconomic()+majorInfos.get(i).getMi_allcredits())/(results[0].getEconomic()+1));
				if(majorInfos.get(i).getMi_allcredits()!=0)
					results[2].setEconomic((results[2].getEconomic()*results[0].getEconomic()+majorInfos.get(i).getMi_concentratedpracticecredits()/majorInfos.get(i).getMi_allcredits())/(results[0].getEconomic()+1));
				results[0].setEconomic(results[0].getEconomic()+1);
			}
			else if(majorInfos.get(i).getMi_majorcode().startsWith("03"))//03法学
			{
				results[1].setLaw((results[1].getLaw()*results[0].getLaw()+majorInfos.get(i).getMi_allcredits())/(results[0].getLaw()+1));
				if(majorInfos.get(i).getMi_allcredits()!=0)	
					results[2].setLaw((results[2].getLaw()*results[0].getLaw()+majorInfos.get(i).getMi_concentratedpracticecredits()/majorInfos.get(i).getMi_allcredits())/(results[0].getLaw()+1));
				results[0].setLaw(results[0].getLaw()+1);
			}
			else if(majorInfos.get(i).getMi_majorcode().startsWith("04"))//04教育学
			{
				results[1].setEdu((results[1].getEdu()*results[0].getEdu()+majorInfos.get(i).getMi_allcredits())/(results[0].getEdu()+1));
				if(majorInfos.get(i).getMi_allcredits()!=0)	
					results[2].setEdu((results[2].getEdu()*results[0].getEdu()+majorInfos.get(i).getMi_concentratedpracticecredits()/majorInfos.get(i).getMi_allcredits())/(results[0].getEdu()+1));
				results[0].setEdu(results[0].getEdu()+1);
			}
			else if(majorInfos.get(i).getMi_majorcode().startsWith("05"))//05文学
			{
				results[1].setLiter((results[1].getLiter()*results[0].getLiter()+majorInfos.get(i).getMi_allcredits())/(results[0].getLiter()+1));
				if(majorInfos.get(i).getMi_allcredits()!=0)	
					results[2].setLiter((results[2].getLiter()*results[0].getLiter()+majorInfos.get(i).getMi_concentratedpracticecredits()/majorInfos.get(i).getMi_allcredits())/(results[0].getLiter()+1));
				results[0].setLiter(results[0].getLiter()+1);
			}
			else if(majorInfos.get(i).getMi_majorcode().startsWith("06"))//06历史学
			{
				results[1].setHistory((results[1].getHistory()*results[0].getHistory()+majorInfos.get(i).getMi_allcredits())/(results[0].getHistory()+1));
				if(majorInfos.get(i).getMi_allcredits()!=0)	
					results[2].setHistory((results[2].getHistory()*results[0].getHistory()+majorInfos.get(i).getMi_concentratedpracticecredits()/majorInfos.get(i).getMi_allcredits())/(results[0].getHistory()+1));
				results[0].setHistory(results[0].getHistory()+1);
			}
			else if(majorInfos.get(i).getMi_majorcode().startsWith("07"))//07理学
			{
				results[1].setScience((results[1].getScience()*results[0].getScience()+majorInfos.get(i).getMi_allcredits())/(results[0].getScience()+1));
				if(majorInfos.get(i).getMi_allcredits()!=0)	
					results[2].setScience((results[2].getScience()*results[0].getScience()+majorInfos.get(i).getMi_concentratedpracticecredits()/majorInfos.get(i).getMi_allcredits())/(results[0].getScience()+1));
				results[0].setScience(results[0].getScience()+1);
			}
			else if(majorInfos.get(i).getMi_majorcode().startsWith("08"))//08工学
			{
				results[1].setFactory((results[1].getFactory()*results[0].getFactory()+majorInfos.get(i).getMi_allcredits())/(results[0].getFactory()+1));
				if(majorInfos.get(i).getMi_allcredits()!=0)	
					results[2].setFactory((results[2].getFactory()*results[0].getFactory()+majorInfos.get(i).getMi_concentratedpracticecredits()/majorInfos.get(i).getMi_allcredits())/(results[0].getFactory()+1));
				results[0].setFactory(results[0].getFactory()+1);
			}
			else if(majorInfos.get(i).getMi_majorcode().startsWith("09"))//09农学
			{
				results[1].setFarm((results[1].getFarm()*results[0].getFarm()+majorInfos.get(i).getMi_allcredits())/(results[0].getFarm()+1));
				if(majorInfos.get(i).getMi_allcredits()!=0)	
					results[2].setFarm((results[2].getFarm()*results[0].getFarm()+majorInfos.get(i).getMi_concentratedpracticecredits()/majorInfos.get(i).getMi_allcredits())/(results[0].getFarm()+1));
				results[0].setFarm(results[0].getFarm()+1);
			}
			else if(majorInfos.get(i).getMi_majorcode().startsWith("10"))//10医学
			{
				results[1].setDoctor((results[1].getDoctor()*results[0].getDoctor()+majorInfos.get(i).getMi_allcredits())/(results[0].getDoctor()+1));
				if(majorInfos.get(i).getMi_allcredits()!=0)	
					results[2].setDoctor((results[2].getDoctor()*results[0].getDoctor()+majorInfos.get(i).getMi_concentratedpracticecredits()/majorInfos.get(i).getMi_allcredits())/(results[0].getDoctor()+1));
				results[0].setDoctor(results[0].getDoctor()+1);
			}
			else if(majorInfos.get(i).getMi_majorcode().startsWith("11"))//11军事学
			{
				results[1].setMilitary((results[1].getMilitary()*results[0].getMilitary()+majorInfos.get(i).getMi_allcredits())/(results[0].getMilitary()+1));
				if(majorInfos.get(i).getMi_allcredits()!=0)	
					results[2].setMilitary((results[2].getMilitary()*results[0].getMilitary()+majorInfos.get(i).getMi_concentratedpracticecredits()/majorInfos.get(i).getMi_allcredits())/(results[0].getMilitary()+1));
				results[0].setMilitary(results[0].getMilitary()+1);
			}
			else if(majorInfos.get(i).getMi_majorcode().startsWith("12"))//12管理学
			{
				results[1].setManage((results[1].getManage()*results[0].getManage()+majorInfos.get(i).getMi_allcredits())/(results[0].getManage()+1));
				if(majorInfos.get(i).getMi_allcredits()!=0)	
					results[2].setManage((results[2].getManage()*results[0].getManage()+majorInfos.get(i).getMi_concentratedpracticecredits()/majorInfos.get(i).getMi_allcredits())/(results[0].getManage()+1));
				results[0].setManage(results[0].getManage()+1);
			}
			else if(majorInfos.get(i).getMi_majorcode().startsWith("13"))//13艺术学
			{
				results[1].setArt((results[1].getArt()*results[0].getArt()+majorInfos.get(i).getMi_allcredits())/(results[0].getArt()+1));
				if(majorInfos.get(i).getMi_allcredits()!=0)	
					results[2].setArt((results[2].getArt()*results[0].getArt()+majorInfos.get(i).getMi_concentratedpracticecredits()/majorInfos.get(i).getMi_allcredits())/(results[0].getArt()+1));
				results[0].setArt(results[0].getArt()+1);
			}
		}
		
		BigDecimal b;// 用于四舍五入
		
		{
			b = new BigDecimal(results[1].getPhilosophy());
			results[1].setPhilosophy(b.setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue());
			b = new BigDecimal(results[1].getEconomic());
			results[1].setEconomic(b.setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue());
			b = new BigDecimal(results[1].getLaw());
			results[1].setLaw(b.setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue());
			b = new BigDecimal(results[1].getEdu());
			results[1].setEdu(b.setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue());
			b = new BigDecimal(results[1].getLiter());
			results[1].setLiter(b.setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue());
			b = new BigDecimal(results[1].getHistory());
			results[1].setHistory(b.setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue());
			b = new BigDecimal(results[1].getScience());
			results[1].setScience(b.setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue());
			b = new BigDecimal(results[1].getFactory());
			results[1].setFactory(b.setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue());
			b = new BigDecimal(results[1].getFarm());
			results[1].setFarm(b.setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue());
			b = new BigDecimal(results[1].getDoctor());
			results[1].setDoctor(b.setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue());
			b = new BigDecimal(results[1].getMilitary());
			results[1].setMilitary(b.setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue());
			b = new BigDecimal(results[1].getManage());
			results[1].setManage(b.setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue());
			b = new BigDecimal(results[1].getArt());
			results[1].setArt(b.setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue());
		}
		
		for(int i = 1; i<3; i++)
		{
			b = new BigDecimal(results[i].getPhilosophy());
			results[i].setPhilosophy(b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			b = new BigDecimal(results[i].getEconomic());
			results[i].setEconomic(b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			b = new BigDecimal(results[i].getLaw());
			results[i].setLaw(b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			b = new BigDecimal(results[i].getEdu());
			results[i].setEdu(b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			b = new BigDecimal(results[i].getLiter());
			results[i].setLiter(b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			b = new BigDecimal(results[i].getHistory());
			results[i].setHistory(b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			b = new BigDecimal(results[i].getScience());
			results[i].setScience(b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			b = new BigDecimal(results[i].getFactory());
			results[i].setFactory(b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			b = new BigDecimal(results[i].getFarm());
			results[i].setFarm(b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			b = new BigDecimal(results[i].getDoctor());
			results[i].setDoctor(b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			b = new BigDecimal(results[i].getMilitary());
			results[i].setMilitary(b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			b = new BigDecimal(results[i].getManage());
			results[i].setManage(b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			b = new BigDecimal(results[i].getArt());
			results[i].setArt(b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		}
		
		List<ProfessionLayout>resultList = new ArrayList<>();
		resultList.add(results[0]);
		resultList.add(results[1]);
		resultList.add(results[2]);
		
		return resultList;
	}
}
