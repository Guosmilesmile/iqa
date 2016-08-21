package cn.edu.xmu.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.dao.DaoForPie;
import cn.edu.xmu.dao.FullTimeTeacherInfoDao;
import cn.edu.xmu.dao.TeachingAwardDao;
import cn.edu.xmu.dao.TeachingResearchAndReformProjectDao;
import cn.edu.xmu.daoimpl.FullTimeTeacherInfoDaoImpl;
import cn.edu.xmu.daoimpl.TeachingAwardDaoImpl;
import cn.edu.xmu.daoimpl.TeachingResearchAndReformProjectDaoImpl;
import cn.edu.xmu.entity.TeachResearchReform;
import cn.edu.xmu.entity.TeachingAward;
import cn.edu.xmu.entity.TeachingResearchAndReformProject;
import cn.edu.xmu.service.TeachResearchReformService;
import cn.edu.xmu.table.FullTimeTeacherInfoTable;
import cn.edu.xmu.table.TeachingAwardTable;
import cn.edu.xmu.table.TeachingResearchAndReformProjectTable;

public class TeachResearchReformServiceImpl implements
		TeachResearchReformService {

	private FullTimeTeacherInfoDao fullTimeTeacherInfoDao = new FullTimeTeacherInfoDaoImpl();

	@Override
	public List<TeachResearchReform> getTeachResearchReforms(Map params) {
		// 单位
		String unitName = "";
		// 项目总数
		int projectTotalCount = 0;
		// 国家级
		int countryCount = 0;
		// 省部级
		int provinceCount = 0;
		// 经费(万元)
		double funding = 0;
		// 当年成果奖总数
		int priceCount = 0;
		// 国家级
		int countryPriceCount = 0;
		// 省部级
		int provincePriceCount = 0;
		String college = "";

		Map queryParamsforReform = new HashMap();
		Map queryParamsforAward = new HashMap();
		Map queryParamsforFulltime = new HashMap();

		if (params == null) {
			params = new HashMap();
		} else if (params.keySet().size() != 0) {
			for (Object object : params.keySet()) {
				String key = object.toString();
				String value = (String) params.get(key);
				if (key.equals("college")) {
					college = value;
					params.remove("college");
					queryParamsforReform
							.put(TeachingResearchAndReformProjectTable.TRARP_COLLEGE,
									value);
					queryParamsforAward.put(TeachingAwardTable.TA_COLLEGE,
							value);
					queryParamsforFulltime.put(FullTimeTeacherInfoTable.FTTI_COLLEGE, value);
				}
				if (key.equals("deadline")) {
					params.remove("deadline");
					queryParamsforReform
							.put(TeachingResearchAndReformProjectTable.TRARP_DEADLINE,
									value);
					queryParamsforAward.put(TeachingAwardTable.TA_DEADLINE,
							value);
					queryParamsforFulltime.put(FullTimeTeacherInfoTable.FTTI_DEADLINE, value);
				}
			}
		}
		List<TeachResearchReform> l1 = fullTimeTeacherInfoDao
				.getAwardAndFulltime(queryParamsforAward,
						queryParamsforFulltime);
		
		List<TeachResearchReform> l2 = fullTimeTeacherInfoDao.getReformAndFulltime(queryParamsforReform, queryParamsforFulltime);
		
		//存储单位号和最终结果
		Map<Integer, TeachResearchReform> resultMap = new HashMap<Integer, TeachResearchReform>();
		//l1中的数据departmentnumber, unitName, 0, 0,0,0,priceCount, countryPriceCount,provincePriceCount
		for(int i=0;i<l1.size();i++)
		{
			//如果不存在当前单位号的记录，put
			if(!resultMap.containsKey(l1.get(i).getDepartmentnumber()))
			{
				resultMap.put(l1.get(i).getDepartmentnumber(), l1.get(i));
			}
			//存在当前单位号的记录，取出并+=
			else {
				TeachResearchReform teachResearchReform = resultMap.get(l1.get(i).getDepartmentnumber());
				teachResearchReform.setPriceCount(teachResearchReform.getPriceCount()+l1.get(i).getPriceCount());
				teachResearchReform.setCountryPriceCount(teachResearchReform.getCountryPriceCount()+l1.get(i).getCountryPriceCount());
				teachResearchReform.setProvincePriceCount(teachResearchReform.getProvincePriceCount()+l1.get(i).getProvincePriceCount());
			}		
		}
		
		//l2中包含的数据departmentnumber, unitName, projectTotalCount, countryCount,provinceCount,funding,0,0,0
		for(int i=0; i<l2.size(); i++)
		{
			if(!resultMap.containsKey(l2.get(i).getDepartmentnumber()))
			{
				resultMap.put(l2.get(i).getDepartmentnumber(), l2.get(i));
			}
			else {
				TeachResearchReform teachResearchReform = resultMap.get(l2.get(i).getDepartmentnumber());
				teachResearchReform.setProjectTotalCount(l2.get(i).getProjectTotalCount()+teachResearchReform.getProjectTotalCount());
				teachResearchReform.setCountryCount(l2.get(i).getCountryCount()+teachResearchReform.getCountryCount());
				teachResearchReform.setProvinceCount(l2.get(i).getProvinceCount()+teachResearchReform.getProvinceCount());
				teachResearchReform.setFunding(l2.get(i).getFunding()+teachResearchReform.getFunding());
			}
		}
		
		List<TeachResearchReform> resultList = new ArrayList<TeachResearchReform>();
		Iterator iterator = resultMap.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry entry = (Map.Entry) iterator.next();
			TeachResearchReform teachResearchReform = (TeachResearchReform)entry.getValue();
			resultList.add(teachResearchReform);
		}
		return resultList;
	}

}
