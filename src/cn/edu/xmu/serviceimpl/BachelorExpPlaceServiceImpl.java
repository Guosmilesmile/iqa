package cn.edu.xmu.serviceimpl;

/**
 * 
 * @author xiaoping 3.2.3本科实验、实习、实训场所情况 date 2015-8-4
 *
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.dao.OutsidePracticePlaceDao;
import cn.edu.xmu.dao.PracticePlaceDao;
import cn.edu.xmu.daoimpl.OutsidePracticePlaceDaoImpl;
import cn.edu.xmu.daoimpl.PracticePlaceDaoImpl;
import cn.edu.xmu.entity.BachelorExpPlace;
import cn.edu.xmu.entity.OutsidePracticePlace;
import cn.edu.xmu.entity.PracticePlace;
import cn.edu.xmu.service.BachelorExpPlaceService;
import cn.edu.xmu.table.OutsidePracticePlaceTable;
import cn.edu.xmu.table.PracticePlaceTable;

public class BachelorExpPlaceServiceImpl implements BachelorExpPlaceService
{

	@Override
	public List<BachelorExpPlace> get(Map<String, String> filter)
	{

		List<BachelorExpPlace> content = new ArrayList<>();
		PracticePlaceDao ppd = new PracticePlaceDaoImpl();
		OutsidePracticePlaceDao oppd = new OutsidePracticePlaceDaoImpl();

		Map<String, String> ppFilter = new HashMap<>();
		Map<String, String> oppFilter = new HashMap<>();
		// 本科实验、实习、实训场所
		float area = 0; // 面积（平方米）
		int exphour = 0; // 学年度承担的实验教学人时数（人时）
		int expcount = 0; // 学年度承担的实验教学人次数（人次）
		int projectnum = 0; // 本科生实验、实习、实训项目数（个）
		int capacity = 0; // 每次可容纳的学生数（人）

		// 校外实习、实训基地
		int outschoolnum = 0; // 校外基地个数
		int outcapacity = 0; // 校外基地平均每次可接纳学生数（个）
		String college = "";

		if (filter == null)
		{
			filter = new HashMap<>();
		} else if (filter.keySet().size() != 0)
		{
			for (Object object : filter.keySet())
			{
				String key = object.toString();
				String value = (String) filter.get(key);

				if (key.contains("college"))
				{
					college = value;
					filter.remove("college");
					ppFilter.put(PracticePlaceTable.PP_COLLEGE, college);
					oppFilter.put(OutsidePracticePlaceTable.OPP_COLLEGE, college);

				}

				if (key.contains("deadline"))
				{
					filter.remove("deadline");
					ppFilter.put(PracticePlaceTable.PP_DEADLINE, value);
					oppFilter.put(OutsidePracticePlaceTable.OPP_DEADLINE, value);
				}
			}
		}

		List<PracticePlace> ppList = ppd.getPracticePlace(0, ppd.getPracticePlaceCount(ppFilter),
				PracticePlaceTable.PP_COLLEGENAME, "asc", ppFilter, null);
		if (ppList != null && ppList.size() > 0)
		{
			for (int j = 0; j < ppList.size(); j++)
			{
				area += ppList.get(j).getPp_area();
				exphour += ppList.get(j).getPp_hours();
				expcount += ppList.get(j).getPp_times();
				projectnum += ppList.get(j).getPp_projectnum();
				capacity += ppList.get(j).getPp_largeststudents();
			}
		}

		List<OutsidePracticePlace> oppList = oppd.getOutsidePracticePlace(0,
				oppd.getOutsidePracticePlaceCount(oppFilter), OutsidePracticePlaceTable.OPP_COLLEGE, "asc", oppFilter,
				null);
		if (oppList != null && oppList.size() > 0)
		{
			outschoolnum = oppd.getOutsidePracticePlaceCount(oppFilter);
			for (int j = 0; j < oppList.size(); j++)
			{
				outcapacity += oppList.get(j).getOpp_studentspertime();
			}
		}
		// valueArray[6] /= valueArray[5];
		BachelorExpPlace school = new BachelorExpPlace(area + "", exphour + "", expcount + "", projectnum + "",
				capacity + "", outschoolnum + "", outcapacity + "", "学校情况", college);
		BachelorExpPlace standard = new BachelorExpPlace();
		standard.setRowTitle("办学条件指标/合格标准");
		BachelorExpPlace changmo = new BachelorExpPlace();
		changmo.setRowTitle("常模值");

		content.add(school);
		content.add(standard);
		content.add(changmo);

		return content;

	}
}
