package cn.edu.xmu.serviceimpl;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import cn.edu.xmu.dao.UndergraduateAwardLoanDao;
import cn.edu.xmu.daoimpl.UndergraduateAwardLoanDaoImpl;
import cn.edu.xmu.entity.UndergraduateAwardLoan;
import cn.edu.xmu.entity.UndergraduateAwardLoanInfo;
import cn.edu.xmu.service.UndergraduateAwardLoanInfoService;
import cn.edu.xmu.table.UndergraduateAwardLoanTable;

public class UndergraduateAwardLoanInfoServiceImpl implements UndergraduateAwardLoanInfoService{

	private UndergraduateAwardLoanDao ualDao = new UndergraduateAwardLoanDaoImpl();
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public UndergraduateAwardLoanInfo getUndergraduateAwardLoanInfo(Map params) {
		//资助金额（万元）
		//政府奖、助学金
		float govcost = 0;
		//社会奖、助学金
		float societycost = 0;
		//学校奖学金
		float schoolcost = 0;
		//国家助学贷款
		float countrycost = 0;
		//勤工助学金
		float workstudycost = 0;
		//减免学费
		float deratecost  = 0;
		//临时困难补助
		float troubleaidcost = 0;
		
		//资助学生数（人次）
		//政府奖、助学金
		int govcount = 0;
		//社会奖、助学金
		int societycount  = 0;
		//学校奖学金
		int schoolcount = 0;
		//国家助学贷款
		int countrycount = 0;
		//勤工助学金
		int workstudycount = 0;
		//减免学费
		int deratecount = 0;
		//临时困难补助
		int troubleaidcount = 0;
		
		String college = "";
		Date deadline = null;
		Map paramsUndergraduateAwardLoan = new HashMap<>();
		if(params == null)
			params = new HashMap<>();
		else if(params.keySet().size() != 0)
		{
			for(Object object:params.keySet()){
				String key  = object.toString();
				String value = (String)params.get(key);
				if(key.equals("college")){
					college = value;
					params.remove("college");
					paramsUndergraduateAwardLoan.put(UndergraduateAwardLoanTable.UAL_COLLEGE, college);
				}
				if(key.equals("deadline")){
					deadline = Date.valueOf(value);
					params.remove("deadline");
					paramsUndergraduateAwardLoan.put(UndergraduateAwardLoanTable.UAL_DEADLINE, value);
				}
			}
		}
		int total = ualDao.getUndergraduateAwardLoanCount(paramsUndergraduateAwardLoan);
		List<UndergraduateAwardLoan> uals = ualDao.getUndergraduateAwardLoan(0, total, UndergraduateAwardLoanTable.UAL_ID, "asc", paramsUndergraduateAwardLoan,deadline);
		for(UndergraduateAwardLoan ual:uals)
		{
			if(ual.getUal_govcost() != null || !"".equals(ual.getUal_govcost()))
				govcost += ual.getUal_govcost();
			if(ual.getUal_societycost() != null || !"".equals(ual.getUal_societycost()))
				societycost += ual.getUal_societycost();
			if(ual.getUal_societycost() != null || !"".equals(ual.getUal_societycost()))
				schoolcost += ual.getUal_societycost();
			if(ual.getUal_countrycost() != null || !"".equals( ual.getUal_countrycost()))
				countrycost += ual.getUal_countrycost();
			if(ual.getUal_workstudycost() != null || !"".equals(ual.getUal_workstudycost()))
				workstudycost += ual.getUal_workstudycost();
			if(ual.getUal_deratecost() != null || !"".equals(ual.getUal_deratecost()))
				deratecost  += ual.getUal_deratecost();
			if(ual.getUal_troubleaidcost() != null || !"".equals(ual.getUal_troubleaidcost()))
				troubleaidcost += ual.getUal_troubleaidcost();
			if(ual.getUal_govcount() != null || !"".equals(ual.getUal_govcount()))
				govcount += ual.getUal_govcount();
			if(ual.getUal_societycount() != null || !"".equals(ual.getUal_societycount()))
				societycount  += ual.getUal_societycount();
			if(ual.getUal_schoolcount() != null || !"".equals(ual.getUal_schoolcount()))
				schoolcount = ual.getUal_schoolcount();
			if(ual.getUal_countrycount() != null || !"".equals(ual.getUal_countrycount()))
				countrycount += ual.getUal_countrycount();
			if(ual.getUal_workstudycount() != null || !"".equals(ual.getUal_workstudycount()))
				workstudycount += ual.getUal_workstudycount();
			if(ual.getUal_deratecount() != null || !"".equals(ual.getUal_deratecount()))
				deratecount += ual.getUal_deratecount();
			if( ual.getUal_troubleaidcount() != null || !"".equals( ual.getUal_troubleaidcount()))
				troubleaidcount += ual.getUal_troubleaidcount();
		}
		UndergraduateAwardLoanInfo uali = new UndergraduateAwardLoanInfo(govcost, societycost, schoolcost, countrycost, workstudycost, deratecost, troubleaidcost, govcount, societycount, schoolcount, countrycount, workstudycount, deratecount, troubleaidcount,college);
		return uali;
	}

}
