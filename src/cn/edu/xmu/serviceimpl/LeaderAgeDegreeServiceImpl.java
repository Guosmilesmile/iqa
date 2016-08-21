package cn.edu.xmu.serviceimpl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.dao.SchoolLeaderInfoDao;
import cn.edu.xmu.daoimpl.SchoolLeaderInfoDaoImpl;
import cn.edu.xmu.entity.LeaderAgeDegree;
import cn.edu.xmu.entity.SchoolLeaderInfo;
import cn.edu.xmu.service.LeaderAgeDegreeService;
import cn.edu.xmu.table.LeaderAgeDegreeTable;
import cn.edu.xmu.table.ManagerInfoTable;
import cn.edu.xmu.table.SchoolLeaderInfoTable;

public class LeaderAgeDegreeServiceImpl implements LeaderAgeDegreeService{
	
	@Override
	public List<LeaderAgeDegree> get(Map<String, String> filter){
		
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
					filter.put(SchoolLeaderInfoTable.SLI_COLLEGE, college);
					
				}
				if (key.equals("deadline")) {
					filter.remove("deadline");
					filter.put(SchoolLeaderInfoTable.SLI_DEADLINE, value);
				}
			}
		}
		
		List<LeaderAgeDegree> content = new ArrayList<>();
		SchoolLeaderInfoDao slid = new SchoolLeaderInfoDaoImpl();
		Date now = new Date(System.currentTimeMillis());
		Date hajimari = new Date(1000, 1, 1);
		Date owari = new Date(2015, 1, 1);
		int nowyear = Integer.parseInt(now.toString().substring(0, 4));
		Date young = new Date(nowyear-35, 1, 1);
		Date midyoung = new Date(nowyear-45,1,1);
		Date midold = new Date(nowyear-55, 1, 1);
		
		
		LeaderAgeDegree amount = new LeaderAgeDegree();
		double[] valueArrayAmount = new double[8];
		
		Map<String, String> phd_a = new HashMap<>();
		phd_a.put(SchoolLeaderInfoTable.SLI_DEGREE, "博士");
		valueArrayAmount[0] = slid.getSchoolLeaderInfoCount(phd_a);
		
		Map<String, String> master_a = new HashMap<>();
		master_a.put(SchoolLeaderInfoTable.SLI_DEGREE, "硕士");
		valueArrayAmount[1] = slid.getSchoolLeaderInfoCount(master_a);
		
		Map<String, String> bachelor_a = new HashMap<>();
		bachelor_a.put(SchoolLeaderInfoTable.SLI_DEGREE, "学士");
		valueArrayAmount[2] = slid.getSchoolLeaderInfoCount(bachelor_a);
		
		Map<String, String> sb_a = new HashMap<>();
		sb_a.put(SchoolLeaderInfoTable.SLI_DEGREE, "无学位");
		valueArrayAmount[3] = slid.getSchoolLeaderInfoCount(sb_a);
		
		valueArrayAmount[4] = slid.getCountByRange(SchoolLeaderInfoTable.SLI_BIRTHDAY, hajimari, young, null);
		valueArrayAmount[5] =  slid.getCountByRange(SchoolLeaderInfoTable.SLI_BIRTHDAY, young, midyoung, null);
		valueArrayAmount[6] = slid.getCountByRange(SchoolLeaderInfoTable.SLI_BIRTHDAY, midyoung, midold, null);
		valueArrayAmount[7] =  slid.getCountByRange(SchoolLeaderInfoTable.SLI_BIRTHDAY, midold, owari, null);
		
		amount.getDataFromArray(valueArrayAmount);
		amount.setRowTitle("数量");
		content.add(amount);
		
		LeaderAgeDegree rate = new LeaderAgeDegree();
		double[] valueArrayRate = new double[8];
		for (int i = 0; i < valueArrayRate.length; i++) {
			valueArrayRate[i] = valueArrayAmount[i] / amount.getTotal();
		}
		rate.getDataFromArray(valueArrayRate);
		rate.setRowTitle("比例（%）");
		content.add(rate);
		
		return content;
		
		
	}

}
