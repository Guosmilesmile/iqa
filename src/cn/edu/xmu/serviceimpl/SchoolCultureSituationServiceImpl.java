package cn.edu.xmu.serviceimpl;

import java.util.HashMap;
import java.util.Map;

import cn.edu.xmu.dao.ExpertLectureDao;
import cn.edu.xmu.dao.UndergraduateActivityProjectDao;
import cn.edu.xmu.daoimpl.ExpertLectureDaoImpl;
import cn.edu.xmu.daoimpl.UndergraduateActivityProjectDaoImpl;
import cn.edu.xmu.entity.SchoolCultureSituation;
import cn.edu.xmu.service.SchoolCultureSituationService;
import cn.edu.xmu.table.ExpertLectureTable;
import cn.edu.xmu.table.TeachingAwardTable;
import cn.edu.xmu.table.TeachingResearchAndReformProjectTable;
import cn.edu.xmu.table.UndergraduateActivityProjectTable;

public class SchoolCultureSituationServiceImpl implements SchoolCultureSituationService {
	private ExpertLectureDao  elDao = new ExpertLectureDaoImpl();
	private UndergraduateActivityProjectDao uapDao = new UndergraduateActivityProjectDaoImpl();

	@Override
	public SchoolCultureSituation getSchoolCultureSituation(Map params) {
		String rowTitle = "数量";//行名
		//文化、学术讲座 总数
		int lectureTotalCount;
		//文化、学术讲座 其中：校级
		int lectureSchoolCount;
		//文化、学术讲座 院（系）级
		int lectureInstituteCount;
		
		//本科生课外科技、文化活动项目  总数
		int projectTotalCount;
		//本科生课外科技、文化活动项目 其中：国家大学生创新性试验计划项目
		int planProjectCount;
		//本科生课外科技、文化活动项目 省级项目
		int provinceProjectCount;
		//本科生课外科技、文化活动项目 学校项目
		int schoolProjectCount;
		String college = "";
		
		//附表5-4-1校内外专家开设文化、学术讲座情况（学年）
		Map paramsForExpertLectureMap = new HashMap();
		//附表5-4-2本科生课外科技、文化活动项目（不含大学生创新创业训练计划项目）
		Map paramsForUndergraduateActivityProjectMap = new HashMap();
		
		if (params == null ) {
			params = new HashMap();
		}else if (params.keySet().size() != 0) {
			for (Object object : params.keySet()) {
				String key = object.toString();
				String value = (String) params.get(key);
				if (key.equals("college")) {
					college = value;
					params.remove("college");
					paramsForExpertLectureMap.put(ExpertLectureTable.EL_COLLEGE, value);
					paramsForUndergraduateActivityProjectMap.put(UndergraduateActivityProjectTable.UAP_COLLEGE, value);
				}
				if (key.equals("deadline")) {
					params.remove("deadline");
					paramsForExpertLectureMap.put(ExpertLectureTable.EL_DEADLINE, value);
					paramsForUndergraduateActivityProjectMap.put(UndergraduateActivityProjectTable.UAP_DEADLINE, value);
				}
			}
		}
		
		lectureTotalCount = elDao.getExpertLectureCount(paramsForExpertLectureMap);
		paramsForExpertLectureMap.put(ExpertLectureTable.EL_GRADE, "校级");
		lectureSchoolCount = elDao.getExpertLectureCount(paramsForExpertLectureMap);
		
		paramsForExpertLectureMap.put(ExpertLectureTable.EL_GRADE, "院（系）级");
		lectureInstituteCount = elDao.getExpertLectureCount(paramsForExpertLectureMap);
		
		//========
		projectTotalCount = uapDao.getUndergraduateActivityProjectCount(paramsForUndergraduateActivityProjectMap);
		paramsForUndergraduateActivityProjectMap.put(UndergraduateActivityProjectTable.UAP_AWARDTYPE, "国家");
		planProjectCount = uapDao.getUndergraduateActivityProjectCount(paramsForUndergraduateActivityProjectMap);
		
		paramsForUndergraduateActivityProjectMap.put(UndergraduateActivityProjectTable.UAP_AWARDTYPE, "省");
		provinceProjectCount = uapDao.getUndergraduateActivityProjectCount(paramsForUndergraduateActivityProjectMap);
		
		paramsForUndergraduateActivityProjectMap.put(UndergraduateActivityProjectTable.UAP_AWARDTYPE, "校级");
		schoolProjectCount = uapDao.getUndergraduateActivityProjectCount(paramsForUndergraduateActivityProjectMap);
		
		SchoolCultureSituation schoolCultureSituation = new SchoolCultureSituation(rowTitle,lectureTotalCount,lectureSchoolCount,lectureInstituteCount,
				projectTotalCount,planProjectCount,provinceProjectCount,schoolProjectCount,college);
		return schoolCultureSituation;
	}

}
