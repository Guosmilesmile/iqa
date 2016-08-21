package cn.edu.xmu.serviceimpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.dao.MajorEnrollInfoDao;
import cn.edu.xmu.dao.MajorRegisterInfoDao;
import cn.edu.xmu.daoimpl.MajorEnrollInfoDaoImpl;
import cn.edu.xmu.daoimpl.MajorRegisterInfoDaoImpl;
import cn.edu.xmu.entity.MajorEnrollInfo;
import cn.edu.xmu.entity.MajorRegisterInfo;
import cn.edu.xmu.entity.UndergraduateAdmission;
import cn.edu.xmu.service.UndergraduateAdmissionService;
import cn.edu.xmu.table.MajorEnrollInfoTable;
import cn.edu.xmu.table.MajorRegisterInfoTable;

public class UndergraduateAdmissionServiceImpl implements UndergraduateAdmissionService
{
	private MajorEnrollInfoDao meiDao = new MajorEnrollInfoDaoImpl();
	private MajorRegisterInfoDao mriDao = new MajorRegisterInfoDaoImpl();

	@Override
	public List<UndergraduateAdmission> getUndergraduateAdmission(int count, boolean isSortByRate, Map queryParams)
	{
		List<UndergraduateAdmission> undergraduateAdmissions = new ArrayList<UndergraduateAdmission>();
		int serialNumber = 0;
		String major;
		int recruitPlan;
		int admissionNumber;
		int registerNumber;
		double registerRate;
		String college = "";
		Map meiParams = new HashMap();
		Map mriParams = new HashMap();
		if (queryParams == null)
			queryParams = new HashMap<>();
		else if (queryParams.keySet().size() != 0)
		{
			for (Object object : queryParams.keySet())
			{
				String key = object.toString();
				String value = (String) queryParams.get(key);
				if (key.equals("college"))
				{
					college = value;
					queryParams.remove("college");
					meiParams.put(MajorEnrollInfoTable.MEI_COLLEGE, value);
					mriParams.put(MajorRegisterInfoTable.MRI_COLLEGE, value);
				}
				if (key.equals("deadline"))
				{
					queryParams.remove("deadline");
					meiParams.put(MajorEnrollInfoTable.MEI_DEADLINE, value);
					mriParams.put(MajorRegisterInfoTable.MRI_DEADLINE, value);
				}
			}
		}
		List<MajorEnrollInfo> meis = meiDao.getMajorEnrollInfo(0, meiDao.getMajorEnrollInfoCount(meiParams),
				MajorEnrollInfoTable.MEI_MAJORNAME, "asc", meiParams, null);
		List<MajorRegisterInfo> mris = mriDao.getMajorRegisterInfo(0, mriDao.getMajorRegisterInfoCount(mriParams),
				MajorRegisterInfoTable.MRI_MAJORNAME, "asc", mriParams, null);
		BigDecimal b;// 用于报到率的四舍五入
		for (MajorEnrollInfo majorEnrollInfo : meis)
		{
			for (MajorRegisterInfo majorRegisterInfo : mris)
			{
				if (majorEnrollInfo.getMei_majorname().equals(majorRegisterInfo.getMri_majorname()))
				{
					major = majorEnrollInfo.getMei_majorname();
					recruitPlan = majorEnrollInfo.getMei_plannumber();
					admissionNumber = majorEnrollInfo.getMei_enrollnumber();
					registerNumber = majorRegisterInfo.getMri_registernumber();
					registerRate = (double) registerNumber / (double) admissionNumber * 100.0;
					// 将报到率四舍五入
					b = new BigDecimal(registerRate);
					registerRate = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
					undergraduateAdmissions.add(new UndergraduateAdmission(++serialNumber, major, recruitPlan,
							admissionNumber, registerNumber, registerRate, college));
					break;
				}
			}
		}
		// 按照报到率从小到大排序
		if (isSortByRate)
		{
			Collections.sort(undergraduateAdmissions);
			for (int i = 0; i < undergraduateAdmissions.size(); i++)
				undergraduateAdmissions.get(i).setSerialNumber(i + 1);// 重置序号
		}
		// 获取相应的个数
		if (count > 0)
		{
			if (count > undergraduateAdmissions.size())
				undergraduateAdmissions = undergraduateAdmissions.subList(0, undergraduateAdmissions.size());
			else
				undergraduateAdmissions = undergraduateAdmissions.subList(0, count);
		}
		return undergraduateAdmissions;
	}
}
