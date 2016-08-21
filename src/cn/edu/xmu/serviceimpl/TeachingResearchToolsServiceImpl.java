package cn.edu.xmu.serviceimpl;
/**
 * @author zshbleaker
 *  3.2.2 教学科研仪器设备情况
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.dao.FixedAssetsDao;
import cn.edu.xmu.dao.StudentNumberInfoDao;
import cn.edu.xmu.daoimpl.FixedAssetsDaoImpl;
import cn.edu.xmu.daoimpl.StudentNumberInfoDaoImpl;
import cn.edu.xmu.entity.FixedAssets;
import cn.edu.xmu.entity.StudentNumberInfo;
import cn.edu.xmu.entity.TeachingResearchTools;
import cn.edu.xmu.service.TeachingHouseService;
import cn.edu.xmu.service.TeachingResearchToolsService;
import cn.edu.xmu.table.FixedAssetsTable;
import cn.edu.xmu.table.PlayGroundTable;
import cn.edu.xmu.table.RoomAreaofTeachingAdministrationTable;
import cn.edu.xmu.table.SchActUseClassroomTable;
import cn.edu.xmu.table.StudentCenterTable;
import cn.edu.xmu.table.StudentNumberInfoTable;

public class TeachingResearchToolsServiceImpl implements TeachingResearchToolsService{

	@Override
	public List<TeachingResearchTools> get(Map<String, String> filter){
		
		List<TeachingResearchTools> content = new ArrayList<>();
		FixedAssetsDao fad = new FixedAssetsDaoImpl();
		StudentNumberInfoDao snid = new StudentNumberInfoDaoImpl();

		Map<String, String> faFilter = new HashMap<>();
		Map<String, String> sniFilter = new HashMap<>();
		
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
					faFilter.put(FixedAssetsTable.FA_COLLEGE, college);
					sniFilter.put(StudentNumberInfoTable.SNI_COLLEGE, college);

				}
				if (key.equals("deadline")) {
					filter.remove("deadline");
					faFilter.put(FixedAssetsTable.FA_DEADLINE, value);

					sniFilter.put(StudentNumberInfoTable.SNI_DEADLINE, value);

				}
			}
		}

		int  people  =0;
		List<StudentNumberInfo> sniList = snid.getStudentNumberInfos(0, 0, "nope", "nope", 
				sniFilter, null);
		for (int i = 0; i < sniList.size(); i++) {
			people = sniList.get(i).getSni_ordiundergrastu();

		}
		
		double[] arr = new double[4];
		
		List<FixedAssets> faList = fad.getAllFixedAssets(0, 0, "nope", "nope", faFilter);
		for (int i = 0; i < faList.size(); i++) {
			FixedAssets tempFa = faList.get(i);
			arr[0] += tempFa.getFa_fixedassetssum();
			arr[2] += tempFa.getFa_newassets();
		}
		
		arr[1] = arr[0] / people;
		arr[3] = arr[2] / arr[0];
		
		TeachingResearchTools school = new TeachingResearchTools();
		school.getDataFromArray(arr);
		school.setRowTitle("学校情况");
		
		TeachingResearchTools standard = new TeachingResearchTools();
		standard.setRowTitle("办学条件指标/合格标准");
		TeachingResearchTools changmo = new TeachingResearchTools();
		changmo.setRowTitle("常模值");
		
		content.add(school);
		content.add(standard);
		content.add(changmo);
		
		return content;
		
	}
}
