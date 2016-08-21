package cn.edu.xmu.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.dao.CourseBuildInfoDao;
import cn.edu.xmu.daoimpl.CourseBuildInfoDaoImpl;
import cn.edu.xmu.entity.CourseBuildInfo;
import cn.edu.xmu.entity.ExcellentCourseBuild;
import cn.edu.xmu.service.ExcellentCourseBuildService;
import cn.edu.xmu.table.CourseBuildInfoTable;

public class ExcellentCourseBuildServiceImpl implements ExcellentCourseBuildService{

	CourseBuildInfoDao cbiDao = new CourseBuildInfoDaoImpl();
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public ExcellentCourseBuild getExcellentCourseBuild(Map params) {
		// TODO Auto-generated method stub
		int nationalLevel = 0;//国家级
		int provicalLevel = 0;//省部级
		String college = "";//学院
		
		Map paramsCourseBuildInfo = new HashMap<>();
		
		if(params == null)
		{
			params = new HashMap<>();
		}
		else if(params.keySet().size() != 0){
			for(Object object:params.keySet()){
				String key = object.toString();
				String value = (String) params.get(key);
				if(key.equals("college"))
				{
					college = value;
					params.remove("college");
					paramsCourseBuildInfo.put(CourseBuildInfoTable.CBI_COLLEGE, value);
				}
				if(key.equals("deadline"))
				{
					params.remove("deadline");
					paramsCourseBuildInfo.put(CourseBuildInfoTable.CBI_DEADLINE, value);
				}
			}
		}
		paramsCourseBuildInfo.put(CourseBuildInfoTable.CBI_TYPE, "精品（优秀）课程（群）");
		List<CourseBuildInfo> cbis = cbiDao.getCourseBuildInfo(0, cbiDao.getCourseBuildInfoCount(paramsCourseBuildInfo), CourseBuildInfoTable.CBI_ID, "asc", paramsCourseBuildInfo, null);
		
		for(CourseBuildInfo cbi:cbis)
		{
			if(cbi.getCbi_grade().equals("国家级"))
				nationalLevel ++;
			else if(cbi.getCbi_grade().equals("省部级"))
				provicalLevel++;
		}
		
		return new ExcellentCourseBuild(nationalLevel, provicalLevel, college);
	}

}
