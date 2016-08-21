package cn.edu.xmu.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.dao.PracticePlaceDao;
import cn.edu.xmu.daoimpl.PracticePlaceDaoImpl;
import cn.edu.xmu.entity.CampusExperiPracPlace;
import cn.edu.xmu.entity.PracticePlace;
import cn.edu.xmu.service.CampusExperiPracPlaceService;
import cn.edu.xmu.table.PracticePlaceTable;

/**
 * 
 * @author xiaoping 附表9 校内实验实习实训场所情况 date 2015-7-20
 *
 */
public class CampusExperiPracPlaceServiceImpl implements CampusExperiPracPlaceService
{
	private PracticePlaceDao ppDao = new PracticePlaceDaoImpl();

	@Override
	public List<CampusExperiPracPlace> getCampusExperiPracPlace(Map queryParams)
	{
		List<CampusExperiPracPlace> ceppList = new ArrayList<CampusExperiPracPlace>();
		int serialNumber = 0;// 序号
		String placeName;// 名称
		float area;// 面积（平方米）
		String majors;// 面向专业
		int projectNum;// 项目数
		int receiveStuNum;// 接待学生数
		int hours;// 年度承担的实验教学人时数（人时）
		String college = "";
		if (queryParams == null)
		{
			queryParams = new HashMap();
		} else if (queryParams.keySet().size() != 0)
		{
			for (Object object : queryParams.keySet())
			{
				String key = object.toString();
				String value = (String) queryParams.get(key);
				if (key.equals("college"))
				{
					college = value;
					queryParams.remove(key);
					queryParams.put(PracticePlaceTable.PP_COLLEGE, value);
				}
				if (key.equals("deadline"))
				{
					queryParams.remove(key);
					queryParams.put(PracticePlaceTable.PP_DEADLINE, value);
				}
			}
		}

		List<PracticePlace> practicePlaces = ppDao.getPracticePlace(0, ppDao.getPracticePlaceCount(queryParams),
				PracticePlaceTable.PP_ID, "asc", queryParams, null);
		for (PracticePlace practicePlace : practicePlaces)
		{
			placeName = practicePlace.getPp_placename();
			area = practicePlace.getPp_area() == null ? 0 : practicePlace.getPp_area();
			majors = practicePlace.getPp_majors();
			projectNum = practicePlace.getPp_projectnum() == null ? 0 : practicePlace.getPp_projectnum();
			receiveStuNum = practicePlace.getPp_times() == null ? 0 : practicePlace.getPp_times();
			hours = practicePlace.getPp_hours() == null ? 0 : practicePlace.getPp_hours();
			ceppList.add(new CampusExperiPracPlace(++serialNumber, placeName, area, majors, projectNum, receiveStuNum,
					hours, college));
		}

		return ceppList;
	}
}
