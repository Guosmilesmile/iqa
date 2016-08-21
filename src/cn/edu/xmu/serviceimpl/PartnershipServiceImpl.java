package cn.edu.xmu.serviceimpl;

/**
 * @author zshbleaker
 * 3.10 合作办学情况
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import cn.edu.xmu.dao.AlumnusAndSocialCoopDao;
import cn.edu.xmu.daoimpl.AlumnusAndSocialCoopDaoImpl;
import cn.edu.xmu.entity.AlumnusAndSocialCoop;
import cn.edu.xmu.entity.Partnership;
import cn.edu.xmu.service.PartnershipService;
import cn.edu.xmu.table.AlumnusAndSocialCoppTable;


public class PartnershipServiceImpl implements PartnershipService{

	@Override
	public List<Partnership> get(Map<String, String> filter){
		
		List<Partnership> content = new ArrayList<>();
		
		AlumnusAndSocialCoopDao aascd = new AlumnusAndSocialCoopDaoImpl();
		
		Map<String, String> aascFilter=  new HashMap<>();
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
					aascFilter.put(AlumnusAndSocialCoppTable.AS_COLLEGE, college);

				}
				if (key.equals("deadline")) {
					filter.remove("deadline");
					aascFilter.put(AlumnusAndSocialCoppTable.AS_DEADLINE, value);

				}
			}
		}
		
		int[] arr = new int[4];
		List<AlumnusAndSocialCoop> aascList = aascd.getAlumnusAndSocialCoop(0, 0, "nope", "nope", aascFilter, null);
		
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < aascList.size(); j++) {
				switch (i) {
				case 0:
					arr[i] += aascList.get(j).getAs_agencyamount();
					break;
				case 1:
					arr[i] += aascList.get(j).getAs_academic();
					break;
				case 2:
					arr[i] += aascList.get(j).getAs_industry();
					break;
				case 3:
					arr[i] += aascList.get(j).getAs_government();
				default:
					break;
				}
			}
		}
		
		Partnership p = new Partnership();
		p.getDataFromArray(arr);
		p.setRowTitle("数量");
		content.add(p);
		
		return content;
		
	}
}
