package cn.edu.xmu.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.dao.AlumnusAndSocialCoopDao;
import cn.edu.xmu.daoimpl.AlumnusAndSocialCoopDaoImpl;
import cn.edu.xmu.entity.AlumnusAndSocialCoop;
import cn.edu.xmu.entity.CoopEducationInfo;
import cn.edu.xmu.service.CoopEducationInfoService;
import cn.edu.xmu.table.AlumnusAndSocialCoppTable;

/**
 * 3.10 合作办学情况
 * @author yue
 *
 */
public class CoopEducationInfoServiceImpl implements CoopEducationInfoService{

	AlumnusAndSocialCoopDao asDao = new AlumnusAndSocialCoopDaoImpl();
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public CoopEducationInfo getCoopEducationInfo(Map params) {
		// TODO Auto-generated method stub
		
		int agencyAmount = 0;//掐你当合作协议机构总数
		int academic = 0;//其中：学术机构
		int industry = 0;//行业机构和企业
		int government = 0;//地方政府
		String college = "";//学院
		
		
		Map paramsAlumnusAndSocialCoop = new HashMap<>();
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
					paramsAlumnusAndSocialCoop.put(AlumnusAndSocialCoppTable.AS_COLLEGE, value);
				}
				if(key.equals("deadline"))
				{
					params.remove("deadline");
					paramsAlumnusAndSocialCoop.put(AlumnusAndSocialCoppTable.AS_DEADLINE, value);
				}
			}
		}
		List<AlumnusAndSocialCoop> ass = asDao.getAlumnusAndSocialCoop(0, asDao.getAlumnusAndSocialCoopCount(paramsAlumnusAndSocialCoop), AlumnusAndSocialCoppTable.AS_ID, "asc", paramsAlumnusAndSocialCoop, null);
		
		for(AlumnusAndSocialCoop as:ass)
		{
			if(as.getAs_agencyamount()!=null || !"".equals(as.getAs_agencyamount()))
				agencyAmount += as.getAs_agencyamount();
			if(as.getAs_academic() != null || !"".equals(as.getAs_academic()))
				academic += as.getAs_academic();
			if(as.getAs_industry() != null || !"".equals(as.getAs_industry()))
				industry += as.getAs_industry();
			if(as.getAs_government() != null || !"".equals(as.getAs_government()))
				government += as.getAs_government();
		}
		
		return new CoopEducationInfo(agencyAmount, academic, industry, government, college);
	}


}
