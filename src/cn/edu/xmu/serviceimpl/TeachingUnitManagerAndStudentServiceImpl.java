package cn.edu.xmu.serviceimpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.dao.MajorInfoDao;
import cn.edu.xmu.dao.ManagerInfoDao;
import cn.edu.xmu.dao.RegUndergraProfesStuNumDao;
import cn.edu.xmu.dao.TeachScientificDao;
import cn.edu.xmu.daoimpl.MajorInfoDaoImpl;
import cn.edu.xmu.daoimpl.ManagerInfoDaoImpl;
import cn.edu.xmu.daoimpl.RegUndergraProfesStuNumDaoImpl;
import cn.edu.xmu.daoimpl.TeachScientificDaoImpl;
import cn.edu.xmu.entity.MajorInfo;
import cn.edu.xmu.entity.RegUndergraProfesStuNum;
import cn.edu.xmu.entity.TeachScientific;
import cn.edu.xmu.entity.TeachingUnitManagerAndStudent;
import cn.edu.xmu.service.TeachingUnitManagerAndStudentService;
import cn.edu.xmu.table.MajorInfoTable;
import cn.edu.xmu.table.ManagerInfoTable;
import cn.edu.xmu.table.RegUndergraProfesStuNumTable;
import cn.edu.xmu.table.TeachScientificTable;

/**
 * 
 * @author xiaoping 5.4 各教学单位学生管理人员与学生情况 date 2015-7-17
 *
 */
public class TeachingUnitManagerAndStudentServiceImpl implements TeachingUnitManagerAndStudentService
{
	private TeachScientificDao tsDao = new TeachScientificDaoImpl();// 教学科研单位
	private ManagerInfoDao managerInfoDao = new ManagerInfoDaoImpl();// 相关管理人员
	private MajorInfoDao majorInfoDao = new MajorInfoDaoImpl();// 专业基本情况
	private RegUndergraProfesStuNumDao rupsnDao = new RegUndergraProfesStuNumDaoImpl();// 普通本科分专业学生数

	@Override
	public List<TeachingUnitManagerAndStudent> getTeachingUnitManagerAndStudent(Map queryParams)
	{
		List<TeachingUnitManagerAndStudent> tumasList = new ArrayList<TeachingUnitManagerAndStudent>();
		int serialNumber = 0;
		String unit = "";
		int manager = 0;
		int undergraduate = 0;
		double undergraManagerRate = 0;
		String college = "";

		Map tsMap = new HashMap();
		Map managerInfoMap = new HashMap();
		Map majorInfoMap = new HashMap();
		Map rupsnMap = new HashMap();
		if (queryParams == null)
		{
			queryParams = new HashMap();
		} else if (queryParams.keySet().size() != 0)
		{
			for (Object object : queryParams.keySet())
			{
				String key = object.toString();
				String value = (String) queryParams.get(key);
				if (key.equals("college"))
				{
					college = value;
					queryParams.remove("college");
					tsMap.put(TeachScientificTable.TS_COLLEGE, value);
					managerInfoMap.put(ManagerInfoTable.MI_COLLEGE, value);
					majorInfoMap.put(MajorInfoTable.MI_COLLEGE, value);
					rupsnMap.put(RegUndergraProfesStuNumTable.RUPSN_COLLEGE, value);
				}
				if (key.equals("deadline"))
				{
					queryParams.remove("deadline");
					tsMap.put(TeachScientificTable.TS_DEADLINE, value);
					managerInfoMap.put(ManagerInfoTable.MI_DEADLINE, value);
					majorInfoMap.put(MajorInfoTable.MI_DEADLINE, value);
					rupsnMap.put(RegUndergraProfesStuNumTable.RUPSN_DEADLINE, value);
				}
			}
		}
		BigDecimal b;//用于报到率的四舍五入
		// 获取所有的教学科研单位
		List<TeachScientific> teachScientifics = tsDao.getAllTeachScientific(0, tsDao.getTeachScientificCount(tsMap),
				TeachScientificTable.TS_NAME, "asc", tsMap);
		for (TeachScientific teachScientific : teachScientifics)
		{
			unit = teachScientific.getTs_name();
			// 获取该单位下的学生管理人员的人数
			managerInfoMap.put(ManagerInfoTable.MI_DEPARTMENTNAME, teachScientific.getTs_name());
			managerInfoMap.put(ManagerInfoTable.MI_DEPARTMENTNUMBER, teachScientific.getTs_number());
			managerInfoMap.put(ManagerInfoTable.MI_MANAGERTYPE, "学生管理人员");
			manager = managerInfoDao.getManagerInfoCount(managerInfoMap);

			// 获取该单位下的所有专业，再通过专业获得该单位下的本科在校人数
			majorInfoMap.put(MajorInfoTable.MI_DEPARTMENTNUMBER, teachScientific.getTs_number());
			majorInfoMap.put(MajorInfoTable.MI_DEPARTMENTNAME, teachScientific.getTs_name());
			List<MajorInfo> majorInfos = majorInfoDao.getMajorInfo(0, majorInfoDao.getCount(majorInfoMap),
					MajorInfoTable.MI_DEPARTMENTNAME, "asc", majorInfoMap);
			for (MajorInfo majorInfo : majorInfos)
			{
				// 获取该专业下的所有本科在校情况
				rupsnMap.put(RegUndergraProfesStuNumTable.RUPSN_SCHPROFESCODE, majorInfo.getMi_majorcodeinschool());
				rupsnMap.put(RegUndergraProfesStuNumTable.RUPSN_SCHPROFESNAME, majorInfo.getMi_majornameinschool());
				List<RegUndergraProfesStuNum> rupsnList = rupsnDao.getRegUndergraProfesStuNums(0,
						rupsnDao.getRegUndergraProfesStuNumCount(rupsnMap),
						RegUndergraProfesStuNumTable.RUPSN_SCHPROFESNAME, "asc", rupsnMap, null);
				// 计算所有记录的总和
				for (RegUndergraProfesStuNum regUndergraProfesStuNum : rupsnList)
					undergraduate += regUndergraProfesStuNum.getRupsn_atschtotal();
			}
			
			if (manager != 0)
				undergraManagerRate = (double) undergraduate / (double) manager * 100.0;
			//将学生人数与管理员的比例四舍五入
			b = new BigDecimal(undergraManagerRate);
			undergraManagerRate = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			tumasList.add(new TeachingUnitManagerAndStudent(++serialNumber, unit, manager, undergraduate,
					undergraManagerRate, college));
			undergraduate = 0;
			undergraManagerRate = 0;
		}
		return tumasList;
	}

}
