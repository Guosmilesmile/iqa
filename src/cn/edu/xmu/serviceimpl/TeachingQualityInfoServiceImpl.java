package cn.edu.xmu.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.dao.TeachingQualityDao;
import cn.edu.xmu.daoimpl.TeachingQualityDaoImpl;
import cn.edu.xmu.entity.TeachingQuality;
import cn.edu.xmu.entity.TeachingQualityInfo;
import cn.edu.xmu.service.TeachingQualityInfoService;
import cn.edu.xmu.table.TeachingQualityTable;

public class TeachingQualityInfoServiceImpl implements TeachingQualityInfoService{

	 TeachingQualityDao teachingQualityDao = new TeachingQualityDaoImpl();
	@Override
	public List<TeachingQualityInfo> getTeachingQualityInfos(Map params) {
		
		String college = "";
		
		if (!(params == null || params.keySet().size() == 0)) {
			for (Object object : params.keySet()) {
				String key = object.toString();
				String value = (String) params.get(key);
				if (key.equals("college")) {
					params.remove("college");
					params.put(TeachingQualityTable.TQ_COLLEGE, value);
					college = value;
				}
				if (key.equals("deadline")) {
					params.remove("deadline");
					params.put(TeachingQualityTable.TQ_DEADLINE, value);
				}
			}
		}
		
		TeachingQualityInfo row1 = null;
		TeachingQualityInfo row2 = null;
		TeachingQualityInfo row3 = null;
		 /*----------------学生------------*/
		//项目
		 String stu_project = "";
		//覆盖比例
		 float stu_coverpercent = 0;
		//优秀
		 float stu_excellent = 0;
		//良好
		 float stu_good = 0;
		//中等
		 float stu_medium = 0;
		//差
		 float stu_poor = 0;
		 
		 /*----------------同行------------*/
		//项目
		 String in_project = "";
		//覆盖比例
		 float in_coverpercent = 0;
		//优秀
		 float in_excellent = 0;
		//良好
		 float in_good = 0;
		//中等
		 float in_medium = 0;
		//差
		 float in_poor = 0;
		 
		 
		 /*----------------专家------------*/
		//项目
		 String pro_project = "";
		//覆盖比例
		 float pro_coverpercent = 0;
		//优秀
		 float pro_excellent = 0;
		//良好
		 float pro_good = 0;
		//中等
		 float pro_medium = 0;
		//差
		 float pro_poor = 0;
		 
		List<TeachingQuality> teachingQualities  = teachingQualityDao.getAllTeachingQuality(0, teachingQualityDao.getTeachingQualityCount(params), TeachingQualityTable.TQ_ID, "asc", params);
		for (TeachingQuality teachingQuality : teachingQualities) {
			if (teachingQuality.getTq_project().equals("学生评教")) {
				stu_project = teachingQuality.getTq_project();
				stu_coverpercent += teachingQuality.getTq_coverpercent();
				stu_excellent += teachingQuality.getTq_excellent();
				stu_good += teachingQuality.getTq_good();
				stu_medium += teachingQuality.getTq_medium();
				stu_poor += teachingQuality.getTq_poor();
				row1 = new TeachingQualityInfo(stu_project, stu_coverpercent, stu_excellent, stu_good, stu_medium, stu_poor, college);
			}
			
			if (teachingQuality.getTq_project().equals("同行评教")) {
				in_project = teachingQuality.getTq_project();
				in_coverpercent += teachingQuality.getTq_coverpercent();
				in_excellent += teachingQuality.getTq_excellent();
				in_good += teachingQuality.getTq_good();
				in_medium += teachingQuality.getTq_medium();
				in_poor += teachingQuality.getTq_poor();
				row2 = new TeachingQualityInfo(in_project, in_coverpercent, in_excellent, in_good, in_medium, in_poor, college);
			}
			
			if (teachingQuality.getTq_project().equals("专家评教")) {
				pro_project = teachingQuality.getTq_project();
				pro_coverpercent += teachingQuality.getTq_coverpercent();
				pro_excellent += teachingQuality.getTq_excellent();
				pro_good += teachingQuality.getTq_good();
				pro_medium += teachingQuality.getTq_medium();
				pro_poor += teachingQuality.getTq_poor();
				row3 = new TeachingQualityInfo(pro_project, pro_coverpercent, pro_excellent, pro_good, pro_medium, pro_poor, college);
			}
		}

		List<TeachingQualityInfo> teachingQualityInfos = new ArrayList<TeachingQualityInfo>();
		teachingQualityInfos.add(row1);
		teachingQualityInfos.add(row2);
		teachingQualityInfos.add(row3);
		
		return teachingQualityInfos;
	}

}
