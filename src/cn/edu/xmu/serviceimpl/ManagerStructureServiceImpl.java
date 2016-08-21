package cn.edu.xmu.serviceimpl;

import java.nio.DoubleBuffer;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.dao.ManagerInfoDao;
import cn.edu.xmu.dao.SchoolExecutiveUnitDao;
import cn.edu.xmu.dao.SchoolLeaderInfoDao;
import cn.edu.xmu.daoimpl.ManagerInfoDaoImpl;
import cn.edu.xmu.daoimpl.SchoolExecutiveUnitDaoImpl;
import cn.edu.xmu.daoimpl.SchoolLeaderInfoDaoImpl;
import cn.edu.xmu.entity.ManagerStrcture;
import cn.edu.xmu.service.ManagerStructureService;
import cn.edu.xmu.table.ManagerStructureTable;
import cn.edu.xmu.table.TeachingResearchAndReformProjectTable;
import cn.edu.xmu.table.ManagerInfoTable;
import cn.edu.xmu.table.LeaderAgeDegreeTable;
import cn.edu.xmu.table.ManagerInfoTable;
import cn.edu.xmu.table.ManagerStructureTable;
import cn.edu.xmu.table.ManagerInfoTable;

public class ManagerStructureServiceImpl  implements ManagerStructureService{
	
	public List<ManagerStrcture> get(Map<String, String> filter){
		
		String college = "";
		if (filter == null) {
			filter = new HashMap<>();
		}else if (filter.keySet().size() != 0) {
			for(Object obj : filter.keySet()){
				String key = obj.toString();
				String value = filter.get(key);
				if (key.equals("college")) {
					college = value;
					filter.remove("college");
					filter.put(ManagerInfoTable.MI_COLLEGE, college);
					
				}
				if (key.equals("deadline")) {
					filter.remove("deadline");
					filter.put(ManagerInfoTable.MI_DEADLINE, value);
				}
			}
		}
		
		List<ManagerStrcture> content = new ArrayList<>();
		ManagerInfoDao mid = new ManagerInfoDaoImpl();
		
		Date now = new Date(System.currentTimeMillis());
		Date hajimari = new Date(1000, 1, 1);						//more than 56 years old
		Date owari = new Date(2015, 1, 1);							//young than 35 years old
		int nowyear = Integer.parseInt(now.toString().substring(0, 4));
		Date young = new Date(nowyear-35, 1, 1);
		Date midyoung = new Date(nowyear-45,1,1);
		Date midold = new Date(nowyear-55, 1, 1);
		
		double[] valueArrayAmount = new double[13];
		
		ManagerStrcture amount = new ManagerStrcture();
		
		Map<String, String> title_h_p = new HashMap<>();		//h for high, p for professor
		title_h_p.put(ManagerInfoTable.MI_PROFESSIONALTITLE, "教授");
		Map<String, String> title_h_o = new HashMap<>();		//o for other
		title_h_o.put(ManagerInfoTable.MI_PROFESSIONALTITLE, "其他正高级");
		valueArrayAmount[0] = mid.getManagerInfoCount(title_h_p) + mid.getManagerInfoCount(title_h_o);
		
		Map< String, String>title_mh_mp = new HashMap<>();
		title_mh_mp.put(ManagerInfoTable.MI_PROFESSIONALTITLE, "副教授");
		Map<String, String>title_mh_mo = new HashMap<>();
		title_mh_mo.put(ManagerInfoTable.MI_PROFESSIONALTITLE, "其他副高级");
		valueArrayAmount[1] = mid.getManagerInfoCount(title_mh_mo) + mid.getManagerInfoCount(title_mh_mp);
		
		Map<String, String>title_m_t = new HashMap<>();
		title_m_t.put(ManagerInfoTable.MI_PROFESSIONALTITLE, "讲师");
		Map<String, String> title_m_o = new HashMap<>();
		title_m_o.put(ManagerInfoTable.MI_PROFESSIONALTITLE, "其他中级");
		valueArrayAmount[2] = mid.getManagerInfoCount(title_m_o)+mid.getManagerInfoCount(title_m_t);
		
		
		Map<String, String> title_l_a = new HashMap<>();
		title_l_a.put(ManagerInfoTable.MI_PROFESSIONALTITLE, "助教");
		Map<String, String> title_l_o = new HashMap<>();
		title_l_o.put(ManagerInfoTable.MI_PROFESSIONALTITLE, "其他初级");
		valueArrayAmount[3] = mid.getManagerInfoCount(title_l_a)+mid.getManagerInfoCount(title_l_o);
		
		Map<String, String> title_c = new HashMap<>();
		title_c.put(ManagerInfoTable.MI_PROFESSIONALTITLE, "未评级");
		valueArrayAmount[4] = mid.getManagerInfoCount(title_c);
		
		Map<String, String> phd_a = new HashMap<>();
		phd_a.put(ManagerInfoTable.MI_DEGREES, "博士");
		valueArrayAmount[5] = mid.getManagerInfoCount(phd_a);
		
		Map<String, String> master_a = new HashMap<>();
		master_a.put(ManagerInfoTable.MI_DEGREES, "硕士");
		valueArrayAmount[6] = mid.getManagerInfoCount(master_a);
		
		Map<String, String> bachelor_a = new HashMap<>();
		bachelor_a.put(ManagerInfoTable.MI_DEGREES, "学士");
		valueArrayAmount[7] = mid.getManagerInfoCount(bachelor_a);
		
		Map<String, String> sb_a = new HashMap<>();
		sb_a.put(ManagerInfoTable.MI_DEGREES, "无学位");
		valueArrayAmount[8] = mid.getManagerInfoCount(sb_a);
		
		valueArrayAmount[9] = mid.getCountByRange(ManagerInfoTable.MI_BIRTHDAY, hajimari, young,phd_a);
		valueArrayAmount[10] = mid.getCountByRange(ManagerInfoTable.MI_BIRTHDAY, young, midyoung,phd_a);
		valueArrayAmount[11] = mid.getCountByRange(ManagerInfoTable.MI_BIRTHDAY, midyoung, midold,phd_a);
		valueArrayAmount[12] = mid.getCountByRange(ManagerInfoTable.MI_BIRTHDAY, midold, owari,phd_a);
		
		amount.getDataFromArray(valueArrayAmount);
		amount.setRowTitle("数量");
		content.add(amount);
		
		double[] valueArrayRate = new double[13];

		ManagerStrcture rate = new ManagerStrcture();
		for (int i = 0; i < valueArrayRate.length; i++) {
			valueArrayRate[i] = valueArrayAmount[i] / amount.getTotal();
		}
		rate.getDataFromArray(valueArrayRate);
		rate.setRowTitle("比例（%）");
		content.add(rate);
		
		return content;
		
	}

}
