package cn.edu.xmu.serviceimpl;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.dao.ManagerInfoDao;
import cn.edu.xmu.daoimpl.ManagerInfoDaoImpl;
import cn.edu.xmu.entity.StudentManagerStructure;
import cn.edu.xmu.service.StudentManagerStructureService;
import cn.edu.xmu.table.ManagerInfoTable;

/**
 * 
 * @author xiaoping 5.3 学生管理人员结构 date 2015-7-18
 *
 */
public class StudentManagerStructureServiceImpl implements StudentManagerStructureService
{
	private ManagerInfoDao managerInfoDao = new ManagerInfoDaoImpl();

	@Override
	public List<StudentManagerStructure> getSchoolStuManagerStructures(Map params)
	{
		String rowNames[] =
		{ "校级学生管理人员数量", "比例（%）" };// 行名称

		int total = 0;
		/* 职称 */
		int zhenggaoji = 0;// 正高级
		int fugaoji = 0;// 副高级
		int zhongji = 0;// 中级
		int chuji = 0;// 初级
		int noTitle = 0;// 无职称

		/* 最高学位 */
		int doctor = 0;// 博士
		int master = 0;// 硕士
		int bachelor = 0;// 学士
		int noDegree = 0;// 无学位

		/* 年龄 */
		int below35 = 0;// 35以下
		int between36_45 = 0;
		int between46_55 = 0;
		int upon56 = 0;

		double zhenggaojiPer = 0;// 正高级
		double fugaojiPer = 0;// 副高级
		double zhongjiPer = 0;// 中级
		double chujiPer = 0;// 初级
		double noTitlePer = 0;// 无职称

		/* 最高学位 */
		double doctorPer = 0;// 博士
		double masterPer = 0;// 硕士
		double bachelorPer = 0;// 学士
		double noDegreePer = 0;// 无学位

		/* 年龄 */
		double below35Per = 0;// 35以下
		double between36_45Per = 0;
		double between46_55Per = 0;
		double upon56Per = 0;

		Date now = new Date(System.currentTimeMillis());
		Date start = null;
		Date end = null;

		double percent = 0;
		String college = "";

		if (params == null)
		{
			params = new HashMap();
		} else if (params.keySet().size() != 0)
		{
			for (Object object : params.keySet())
			{
				String key = object.toString();
				String value = (String) params.get(key);
				if (key.equals("college"))
				{
					college = value;
					params.remove("college");
					params.put(ManagerInfoTable.MI_COLLEGE, college);
				}
				if (key.equals("deadline"))
				{
					params.remove("deadline");
					params.put(ManagerInfoTable.MI_DEADLINE, value);
				}
			}
		}

		params.put(ManagerInfoTable.MI_MANAGERTYPE, "学生管理人员");
		total = managerInfoDao.getManagerInfoCountInExecutiveUnit(params);

		params.put(ManagerInfoTable.MI_PROFESSIONALTITLE, "正高级");
		zhenggaoji = managerInfoDao.getManagerInfoCountInExecutiveUnit(params);
		percent = total > 0 ? (double) zhenggaoji / total * 100 : 0;
		zhenggaojiPer = new BigDecimal(percent).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

		params.put(ManagerInfoTable.MI_PROFESSIONALTITLE, "副高级");
		fugaoji = managerInfoDao.getManagerInfoCountInExecutiveUnit(params);
		percent = total > 0 ? (double) fugaoji / total * 100 : 0;
		fugaojiPer = new BigDecimal(percent).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

		params.put(ManagerInfoTable.MI_PROFESSIONALTITLE, "中级");
		zhongji = managerInfoDao.getManagerInfoCountInExecutiveUnit(params);
		percent = total > 0 ? (double) zhongji / total * 100 : 0;
		zhongjiPer = new BigDecimal(percent).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

		params.put(ManagerInfoTable.MI_PROFESSIONALTITLE, "初级");
		chuji = managerInfoDao.getManagerInfoCountInExecutiveUnit(params);
		percent = total > 0 ? (double) chuji / total * 100 : 0;
		chujiPer = new BigDecimal(percent).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

		params.put(ManagerInfoTable.MI_PROFESSIONALTITLE, "未评级");
		noTitle = managerInfoDao.getManagerInfoCountInExecutiveUnit(params);
		percent = total > 0 ? (double) noTitle / total * 100 : 0;
		noTitlePer = new BigDecimal(percent).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

		/* 删除键值对 */
		params.remove(ManagerInfoTable.MI_PROFESSIONALTITLE);

		params.put(ManagerInfoTable.MI_DEGREES, "博士");
		doctor = managerInfoDao.getManagerInfoCountInExecutiveUnit(params);
		percent = total > 0 ? (double) doctor / total * 100 : 0;
		doctorPer = new BigDecimal(percent).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

		params.put(ManagerInfoTable.MI_DEGREES, "硕士");
		master = managerInfoDao.getManagerInfoCountInExecutiveUnit(params);
		percent = total > 0 ? (double) master / total * 100 : 0;
		masterPer = new BigDecimal(percent).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

		params.put(ManagerInfoTable.MI_DEGREES, "学士");
		bachelor = managerInfoDao.getManagerInfoCountInExecutiveUnit(params);
		percent = total > 0 ? (double) bachelor / total * 100 : 0;
		bachelorPer = new BigDecimal(percent).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

		params.put(ManagerInfoTable.MI_DEGREES, "无学位");
		noDegree = managerInfoDao.getManagerInfoCountInExecutiveUnit(params);
		percent = total > 0 ? (double) noDegree / total * 100 : 0;
		noDegreePer = new BigDecimal(percent).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

		/* 删除键值对 */
		params.remove(ManagerInfoTable.MI_DEGREES);

		start = new Date(now.getYear() - 35, now.getMonth(), now.getDay());
		end = now;
		below35 = managerInfoDao.getCountByRangeInExecutiveUnit(ManagerInfoTable.MI_BIRTHDAY, start, end, params);
		percent = total > 0 ? (double) below35 / total * 100 : 0;
		below35Per = new BigDecimal(percent).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

		start.setYear(now.getYear() - 45);
		end.setYear(now.getYear() - 36);
		between36_45 = managerInfoDao.getCountByRangeInExecutiveUnit(ManagerInfoTable.MI_BIRTHDAY, start, end, params);
		percent = total > 0 ? (double) between36_45 / total * 100 : 0;
		between36_45Per = new BigDecimal(percent).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

		start.setYear(now.getYear() - 55);
		end.setYear(now.getYear() - 46);
		between46_55 = managerInfoDao.getCountByRangeInExecutiveUnit(ManagerInfoTable.MI_BIRTHDAY, start, end, params);
		percent = total > 0 ? (double) between46_55 / total * 100 : 0;
		between46_55Per = new BigDecimal(percent).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

		start.setYear(now.getYear() - 100);
		end.setYear(now.getYear() - 56);
		upon56 = managerInfoDao.getCountByRangeInExecutiveUnit(ManagerInfoTable.MI_BIRTHDAY, start, end, params);
		percent = total > 0 ? (double) upon56 / total * 100 : 0;
		upon56Per = new BigDecimal(percent).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

		StudentManagerStructure row1 = new StudentManagerStructure(rowNames[0], Integer.toString(total),
				Integer.toString(zhenggaoji), Integer.toString(fugaoji), Integer.toString(zhongji),
				Integer.toString(chuji), Integer.toString(noTitle), Integer.toString(doctor), Integer.toString(master),
				Integer.toString(bachelor), Integer.toString(noDegree), Integer.toString(below35),
				Integer.toString(between36_45), Integer.toString(between46_55), Integer.toString(upon56), college);
		StudentManagerStructure row2 = new StudentManagerStructure(rowNames[1], "/", Double.toString(zhenggaojiPer),
				Double.toString(fugaojiPer), Double.toString(zhongjiPer), Double.toString(chujiPer),
				Double.toString(noTitlePer), Double.toString(doctorPer), Double.toString(masterPer),
				Double.toString(bachelorPer), Double.toString(noDegreePer), Double.toString(below35Per),
				Double.toString(between36_45Per), Double.toString(between46_55Per), Double.toString(upon56Per),
				college);

		List<StudentManagerStructure> studentManagerStructures = new ArrayList<StudentManagerStructure>();
		studentManagerStructures.add(row1);
		studentManagerStructures.add(row2);
		return studentManagerStructures;
	}

	@Override
	public List<StudentManagerStructure> getCollegeStuManagerStructures(Map params)
	{
		String rowNames[] =
		{ "院系学生管理人员数量", "比例（%）" };// 行名称

		int total = 0;
		/* 职称 */
		int zhenggaoji = 0;// 正高级
		int fugaoji = 0;// 副高级
		int zhongji = 0;// 中级
		int chuji = 0;// 初级
		int noTitle = 0;// 无职称

		/* 最高学位 */
		int doctor = 0;// 博士
		int master = 0;// 硕士
		int bachelor = 0;// 学士
		int noDegree = 0;// 无学位

		/* 年龄 */
		int below35 = 0;// 35以下
		int between36_45 = 0;
		int between46_55 = 0;
		int upon56 = 0;

		double zhenggaojiPer = 0;// 正高级
		double fugaojiPer = 0;// 副高级
		double zhongjiPer = 0;// 中级
		double chujiPer = 0;// 初级
		double noTitlePer = 0;// 无职称

		/* 最高学位 */
		double doctorPer = 0;// 博士
		double masterPer = 0;// 硕士
		double bachelorPer = 0;// 学士
		double noDegreePer = 0;// 无学位

		/* 年龄 */
		double below35Per = 0;// 35以下
		double between36_45Per = 0;
		double between46_55Per = 0;
		double upon56Per = 0;

		Date now = new Date(System.currentTimeMillis());
		Date start = null;
		Date end = null;

		double percent = 0;
		String college = "";

		if (params == null)
		{
			params = new HashMap();
		} else if (params.keySet().size() != 0)
		{
			for (Object object : params.keySet())
			{
				String key = object.toString();
				String value = (String) params.get(key);
				if (key.equals("college"))
				{
					college = value;
					params.remove("college");
					params.put(ManagerInfoTable.MI_COLLEGE, college);
				}
				if (key.equals("deadline"))
				{
					params.remove("deadline");
					params.put(ManagerInfoTable.MI_DEADLINE, value);
				}
			}
		}

		params.put(ManagerInfoTable.MI_MANAGERTYPE, "学生管理人员");
		total = managerInfoDao.getManagerInfoCountInTeachingScientific(params);

		params.put(ManagerInfoTable.MI_PROFESSIONALTITLE, "正高级");
		zhenggaoji = managerInfoDao.getManagerInfoCountInTeachingScientific(params);
		percent = total > 0 ? (double) zhenggaoji / total * 100 : 0;
		zhenggaojiPer = new BigDecimal(percent).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

		params.put(ManagerInfoTable.MI_PROFESSIONALTITLE, "副高级");
		fugaoji = managerInfoDao.getManagerInfoCountInTeachingScientific(params);
		percent = total > 0 ? (double) fugaoji / total * 100 : 0;
		fugaojiPer = new BigDecimal(percent).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

		params.put(ManagerInfoTable.MI_PROFESSIONALTITLE, "中级");
		zhongji = managerInfoDao.getManagerInfoCountInTeachingScientific(params);
		percent = total > 0 ? (double) zhongji / total * 100 : 0;
		zhongjiPer = new BigDecimal(percent).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

		params.put(ManagerInfoTable.MI_PROFESSIONALTITLE, "初级");
		chuji = managerInfoDao.getManagerInfoCountInTeachingScientific(params);
		percent = total > 0 ? (double) chuji / total * 100 : 0;
		chujiPer = new BigDecimal(percent).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

		params.put(ManagerInfoTable.MI_PROFESSIONALTITLE, "未评级");
		noTitle = managerInfoDao.getManagerInfoCountInTeachingScientific(params);
		percent = total > 0 ? (double) noTitle / total * 100 : 0;
		noTitlePer = new BigDecimal(percent).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

		/* 删除键值对 */
		params.remove(ManagerInfoTable.MI_PROFESSIONALTITLE);

		params.put(ManagerInfoTable.MI_DEGREES, "博士");
		doctor = managerInfoDao.getManagerInfoCountInTeachingScientific(params);
		percent = total > 0 ? (double) doctor / total * 100 : 0;
		doctorPer = new BigDecimal(percent).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

		params.put(ManagerInfoTable.MI_DEGREES, "硕士");
		master = managerInfoDao.getManagerInfoCountInTeachingScientific(params);
		percent = total > 0 ? (double) master / total * 100 : 0;
		masterPer = new BigDecimal(percent).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

		params.put(ManagerInfoTable.MI_DEGREES, "学士");
		bachelor = managerInfoDao.getManagerInfoCountInTeachingScientific(params);
		percent = total > 0 ? (double) bachelor / total * 100 : 0;
		bachelorPer = new BigDecimal(percent).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

		params.put(ManagerInfoTable.MI_DEGREES, "无学位");
		noDegree = managerInfoDao.getManagerInfoCountInTeachingScientific(params);
		percent = total > 0 ? (double) noDegree / total * 100 : 0;
		noDegreePer = new BigDecimal(percent).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

		/* 删除键值对 */
		params.remove(ManagerInfoTable.MI_DEGREES);

		start = new Date(now.getYear() - 35, now.getMonth(), now.getDay());
		end = now;
		below35 = managerInfoDao.getCountByRangeInTeachingScientific(ManagerInfoTable.MI_BIRTHDAY, start, end, params);
		percent = total > 0 ? (double) below35 / total * 100 : 0;
		below35Per = new BigDecimal(percent).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

		start.setYear(now.getYear() - 45);
		end.setYear(now.getYear() - 36);
		between36_45 = managerInfoDao.getCountByRangeInTeachingScientific(ManagerInfoTable.MI_BIRTHDAY, start, end,
				params);
		percent = total > 0 ? (double) between36_45 / total * 100 : 0;
		between36_45Per = new BigDecimal(percent).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

		start.setYear(now.getYear() - 55);
		end.setYear(now.getYear() - 46);
		between46_55 = managerInfoDao.getCountByRangeInTeachingScientific(ManagerInfoTable.MI_BIRTHDAY, start, end,
				params);
		percent = total > 0 ? (double) between46_55 / total * 100 : 0;
		between46_55Per = new BigDecimal(percent).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

		start.setYear(now.getYear() - 100);
		end.setYear(now.getYear() - 56);
		upon56 = managerInfoDao.getCountByRangeInTeachingScientific(ManagerInfoTable.MI_BIRTHDAY, start, end, params);
		percent = total > 0 ? (double) upon56 / total * 100 : 0;
		upon56Per = new BigDecimal(percent).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

		StudentManagerStructure row1 = new StudentManagerStructure(rowNames[0], Integer.toString(total),
				Integer.toString(zhenggaoji), Integer.toString(fugaoji), Integer.toString(zhongji),
				Integer.toString(chuji), Integer.toString(noTitle), Integer.toString(doctor), Integer.toString(master),
				Integer.toString(bachelor), Integer.toString(noDegree), Integer.toString(below35),
				Integer.toString(between36_45), Integer.toString(between46_55), Integer.toString(upon56), college);
		StudentManagerStructure row2 = new StudentManagerStructure(rowNames[1], "/", Double.toString(zhenggaojiPer),
				Double.toString(fugaojiPer), Double.toString(zhongjiPer), Double.toString(chujiPer),
				Double.toString(noTitlePer), Double.toString(doctorPer), Double.toString(masterPer),
				Double.toString(bachelorPer), Double.toString(noDegreePer), Double.toString(below35Per),
				Double.toString(between36_45Per), Double.toString(between46_55Per), Double.toString(upon56Per),
				college);

		List<StudentManagerStructure> studentManagerStructures = new ArrayList<StudentManagerStructure>();
		studentManagerStructures.add(row1);
		studentManagerStructures.add(row2);
		return studentManagerStructures;
	}

	@Override
	public List<StudentManagerStructure> getStuManagerStructure(Map params)
	{
		List<StudentManagerStructure> schoolSMS = getSchoolStuManagerStructures(params);
		List<StudentManagerStructure> collegeSMS = getCollegeStuManagerStructures(params);

		for (StudentManagerStructure studentManagerStructure : collegeSMS)
		{
			schoolSMS.add(studentManagerStructure);
		}

		return schoolSMS;
	}

}
