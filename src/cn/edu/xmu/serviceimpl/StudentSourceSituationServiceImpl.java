package cn.edu.xmu.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.dao.AdmissionCriteriaAndNumberDao;
import cn.edu.xmu.daoimpl.AdmissionCriteriaAndNumberDaoImpl;
import cn.edu.xmu.entity.AdmissionCriteriaAndNumber;
import cn.edu.xmu.service.StudentSourceSituationService;
import cn.edu.xmu.table.AdmissionCriteriaAndNumberTable;

/**
 * 统计表5.1 生源情况
 * @author zhantu
 *
 */
public class StudentSourceSituationServiceImpl implements StudentSourceSituationService{
	private AdmissionCriteriaAndNumberDao admissionCriteriaAndNumberDao = new AdmissionCriteriaAndNumberDaoImpl();
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<AdmissionCriteriaAndNumber> getStudentSourceSituation(Map params)
	{
		Map paramsForAdmissionCriteriaAndNumber = new HashMap();
		if (params == null ) {
			params = new HashMap();
		}else if (params.keySet().size() != 0) {
			for (Object object : params.keySet()) {
				String key = object.toString();
				String value = (String) params.get(key);
				if (key.equals("college")) {
					params.remove("college");
					paramsForAdmissionCriteriaAndNumber.put(AdmissionCriteriaAndNumberTable.ACN_COLLEGE, value);
				}
				if (key.equals("deadline")) {
					params.remove("deadline");
					paramsForAdmissionCriteriaAndNumber.put(AdmissionCriteriaAndNumberTable.ACN_DEADLINE, value);
				}
			}
		}
		List<AdmissionCriteriaAndNumber> studentSourceSituationList = admissionCriteriaAndNumberDao.getAdmissionCriteriaAndNumber(0, admissionCriteriaAndNumberDao.getAdmissionCriteriaAndNumberCount(paramsForAdmissionCriteriaAndNumber), AdmissionCriteriaAndNumberTable.ACN_ID, "asc", paramsForAdmissionCriteriaAndNumber, null);
		
		for(int i = 0; i<studentSourceSituationList.size(); i++)
		{
			//此处统计时，提前批次没有最低控制线，因此差值即为录取分数线。
			//批次：选择提前批招生、第一批次招生、第二批次招生A、第二批次招生B、第三批次招生A、第三批次招生B。
			if(!studentSourceSituationList.get(i).getAcn_batch().equals("提前批招生"))
			{
				studentSourceSituationList.get(i).setAcn_artsavgscore(studentSourceSituationList.get(i).getAcn_artsavgscore()-studentSourceSituationList.get(i).getAcn_artsminctrline());
				studentSourceSituationList.get(i).setAcn_scienceavgscore(studentSourceSituationList.get(i).getAcn_scienceavgscore()-studentSourceSituationList.get(i).getAcn_scienceminctrline());
			}
		}
		return studentSourceSituationList;
	}
}
