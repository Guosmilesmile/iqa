package cn.edu.xmu.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.dao.CourseBuildInfoDao;
import cn.edu.xmu.daoimpl.CourseBuildInfoDaoImpl;
import cn.edu.xmu.entity.CourseBuildInfo;
import cn.edu.xmu.entity.GoodCourseBuild;
import cn.edu.xmu.service.GoodCourseBuildService;
import cn.edu.xmu.table.CourseBuildInfoTable;
import cn.edu.xmu.table.PlayGroundTable;
import cn.edu.xmu.table.RoomAreaofTeachingAdministrationTable;
import cn.edu.xmu.table.SchActUseClassroomTable;
import cn.edu.xmu.table.StudentCenterTable;
import cn.edu.xmu.table.StudentNumberInfoTable;

public class GoodCourseBuildServiceImpl implements GoodCourseBuildService{

	@Override
	public List<GoodCourseBuild> get(Map<String, String> filter){
		List<GoodCourseBuild> content = new ArrayList<>();
		
		CourseBuildInfoDao cbid = new CourseBuildInfoDaoImpl();
		
		Map<String, String> cbiFilter = new HashMap<>();
		
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
					cbiFilter.put(CourseBuildInfoTable.CBI_COLLEGE, college);

				}
				if (key.equals("deadline")) {
					filter.remove("deadline");
					cbiFilter.put(CourseBuildInfoTable.CBI_DEADLINE, value);

				}
			}
		}
		
		GoodCourseBuild gcb = new GoodCourseBuild();
		cbiFilter.put(CourseBuildInfoTable.CBI_TYPE, "国家级");
		gcb.setNation(cbid.getCourseBuildInfoCount(cbiFilter));
		cbiFilter.remove(CourseBuildInfoTable.CBI_TYPE);
		
		cbiFilter.put(CourseBuildInfoTable.CBI_TYPE, "省部级");
		gcb.setProvince(cbid.getCourseBuildInfoCount(cbiFilter));
		cbiFilter.remove(CourseBuildInfoTable.CBI_TYPE);
		
		gcb.setRowTitle("数量");
		content.add(gcb);
		return content;
	}
}
