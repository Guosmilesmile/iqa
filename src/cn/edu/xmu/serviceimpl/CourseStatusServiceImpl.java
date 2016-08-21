package cn.edu.xmu.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.dao.StartClassDao;
import cn.edu.xmu.daoimpl.StartClassDaoImpl;
import cn.edu.xmu.entity.CourseStatus;
import cn.edu.xmu.entity.StartClass;
import cn.edu.xmu.service.CourseStatusService;
import cn.edu.xmu.table.PlayGroundTable;
import cn.edu.xmu.table.RoomAreaofTeachingAdministrationTable;
import cn.edu.xmu.table.SchActUseClassroomTable;
import cn.edu.xmu.table.StartClassTable;
import cn.edu.xmu.table.StudentCenterTable;
import cn.edu.xmu.table.StudentNumberInfoTable;

public class CourseStatusServiceImpl implements CourseStatusService{

	@Override
	public List<CourseStatus> get(Map<String, String> filter){
		
		List<CourseStatus> content = new ArrayList<>();
		
		StartClassDao scd = new StartClassDaoImpl();
		
		Map<String, String> scFilter = new HashMap<>();
		
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
					scFilter.put(StartClassTable.SC_COLLEGE, college);

				}
				if (key.equals("deadline")) {
					filter.remove("deadline");
					scFilter.put(StartClassTable.SC_DEADLINE, value);
				}
			}
		}
		
		double[] pro = new double[5];
		double[] must = new double[5];
		double[] choose = new double[5];
		
		scFilter.put(StartClassTable.SC_COURSECATEGORY, "专业");
		pro[0] = scd.getClassCountDistinct(scFilter);
		pro[1] = scd.getClassTeacherCount(scFilter);
		scFilter.put(StartClassTable.SC_ISENGLISH, "双语授课");
		pro[2] = scd.getClassCountDistinct(scFilter);
		scFilter.remove(StartClassTable.SC_ISENGLISH);
		List<StartClass> scList = scd.getStartClass(0, 0, "nope", "nope", scFilter,null);
		for (int i = 0; i < scList.size(); i++) {
			pro[3] += scList.get(i).getSc_totalhour();
			pro[4] += scList.get(i).getSc_studentnum();
		}
		pro[3] /= pro[1];
		pro[4] /= pro[1];
		scFilter.remove(StartClassTable.SC_COURSECATEGORY);
		CourseStatus scPro = new CourseStatus();
		scPro.getDataFromArray(pro);
		scPro.setRowTitle("专业课");
		content.add(scPro);
		
		scFilter.put(StartClassTable.SC_COURSECATEGORY, "公共必修");
		must[0] = scd.getClassCountDistinct(scFilter);
		must[1] = scd.getClassTeacherCount(scFilter);
		scFilter.put(StartClassTable.SC_ISENGLISH, "双语授课");
		must[2] = scd.getClassCountDistinct(scFilter);
		scFilter.remove(StartClassTable.SC_ISENGLISH);
		List<StartClass> mustList = scd.getStartClass(0, 0, "nope", "nope", scFilter,null);
		for (int i = 0; i < scList.size(); i++) {
			must[3] += scList.get(i).getSc_totalhour();
			must[4] += scList.get(i).getSc_studentnum();
		}
		must[3] /= must[1];
		must[4] /= must[1];
		scFilter.remove(StartClassTable.SC_COURSECATEGORY);
		CourseStatus scMust = new CourseStatus();
		scMust.getDataFromArray(must);
		scMust.setRowTitle("公共必修");
		content.add(scMust);
		
		scFilter.put(StartClassTable.SC_COURSECATEGORY, "公共选修");
		choose[0] = scd.getClassCountDistinct(scFilter);
		choose[1] = scd.getClassTeacherCount(scFilter);
		scFilter.put(StartClassTable.SC_ISENGLISH, "双语授课");
		choose[2] = scd.getClassCountDistinct(scFilter);
		scFilter.remove(StartClassTable.SC_ISENGLISH);
		List<StartClass> chooseList = scd.getStartClass(0, 0, "nope", "nope", scFilter,null);
		for (int i = 0; i < scList.size(); i++) {
			choose[3] += scList.get(i).getSc_totalhour();
			choose[4] += scList.get(i).getSc_studentnum();
		}
		choose[3] /= choose[1];
		choose[4] /= choose[1];
		scFilter.remove(StartClassTable.SC_COURSECATEGORY);
		CourseStatus scChoose = new CourseStatus();
		scChoose.getDataFromArray(choose);
		scChoose.setRowTitle("公共选修课");
		content.add(scChoose);
		
		return content;
		
		
		
		
		
		
	}
}
