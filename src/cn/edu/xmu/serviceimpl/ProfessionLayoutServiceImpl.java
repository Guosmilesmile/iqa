package cn.edu.xmu.serviceimpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.dao.GraduateAndDoctoralDao;
import cn.edu.xmu.dao.ImportantStudyDao;
import cn.edu.xmu.dao.MajorInfoDao;
import cn.edu.xmu.daoimpl.GraduateAndDoctoralDaoImpl;
import cn.edu.xmu.daoimpl.ImportantStudyDaoImpl;
import cn.edu.xmu.daoimpl.MajorInfoDaoImpl;
import cn.edu.xmu.entity.GraduateAndDoctoral;
import cn.edu.xmu.entity.MajorInfo;
import cn.edu.xmu.entity.ProfessionLayout;
import cn.edu.xmu.service.ProfessionLayoutService;
import cn.edu.xmu.table.GraduateAndDoctoralTable;
import cn.edu.xmu.table.ImportantStudyTable;
import cn.edu.xmu.table.MajorInfoTable;

/**
 * 
 * @author xiaoping 1.5 专业布局概览 date 2015-8-5
 *
 */
public class ProfessionLayoutServiceImpl implements ProfessionLayoutService
{
	private MajorInfoDao mid = new MajorInfoDaoImpl();
	private ImportantStudyDao isd = new ImportantStudyDaoImpl();
	private GraduateAndDoctoralDao gdd = new GraduateAndDoctoralDaoImpl();

	@Override
	public List<ProfessionLayout> get(Map<String, String> filter)
	{

		List<ProfessionLayout> content = new ArrayList<>();
		
		double all = 0;// 总计
		
		Map<String, String> isFilter = new HashMap<>();
		Map<String, String> gdFilter = new HashMap<>();
		Map<String, String> miFilter = new HashMap<>();

		String college = "";
		if (filter == null)
		{
			filter = new HashMap<>();
		} else if (filter.keySet().size() != 0)
		{
			for (Object obj : filter.keySet())
			{
				String key = obj.toString();
				String value = filter.get(key);
				if (key.equals("college"))
				{
					college = value;
					filter.remove("college");
					miFilter.put(MajorInfoTable.MI_COLLEGE, college);
					isFilter.put(ImportantStudyTable.IP_COLLEGE, college);
					gdFilter.put(GraduateAndDoctoralTable.GD_COLLEGE, college);
				}
				if (key.equals("deadline"))
				{
					filter.remove("deadline");
					miFilter.put(MajorInfoTable.MI_DEADLINE, value);
					isFilter.put(ImportantStudyTable.IP_DEADLINE, value);
					gdFilter.put(GraduateAndDoctoralTable.GD_DEADLINE, value);
				}
			}
		}

		int allSubject = mid.getCount(miFilter);
		List<MajorInfo> miList = mid.getMajorInfo(0, allSubject, MajorInfoTable.MI_MAJORCODE, "asc", miFilter);

		String[] categoryArray =
		{ "哲学", "经济学", "法学", "教育学", "文学", "历史学", "理学", "工学", "农学", "医学", "军事学", "管理学", "艺术学" };
		String[] categoryCodeArray =
		{ "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13" };

		double[] categoryValueArray = new double[categoryArray.length]; // content
																		// 里的一行
		for (int i = 0; i < categoryValueArray.length; i++)
		{
			categoryValueArray[i] = 0;
		}

		// 获取各个学科门类所含本科专业数
		if (miList.size() > 0)
		{
			for (int i = 0; i < categoryCodeArray.length; i++)
				for (int j = 0; j < miList.size(); j++)
					if (miList.get(j).getMi_majorcode().substring(0, 2).equals(categoryCodeArray[i]))
						categoryValueArray[i]++;
		}
		for (int i = 0; i < categoryValueArray.length; i++)// 计算总计
			all += categoryValueArray[i];
		content.add(new ProfessionLayout(categoryValueArray[0], categoryValueArray[1], categoryValueArray[2],
				categoryValueArray[3], categoryValueArray[4], categoryValueArray[5], categoryValueArray[6],
				categoryValueArray[7], categoryValueArray[8], categoryValueArray[9], categoryValueArray[10],
				categoryValueArray[11], categoryValueArray[12], all, "所含本科专业数", college));

		BigDecimal b;// 用于报到率的四舍五入
		// 计算比例（%）
		for (int i = 0; i < categoryValueArray.length; i++)
		{
			categoryValueArray[i] = categoryValueArray[i] / allSubject * 100.0;
			b = new BigDecimal(categoryValueArray[i]);
			categoryValueArray[i] = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		}
		all = all / allSubject * 100.0;
		b = new BigDecimal(all);
		all = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		content.add(new ProfessionLayout(categoryValueArray[0], categoryValueArray[1], categoryValueArray[2],
				categoryValueArray[3], categoryValueArray[4], categoryValueArray[5], categoryValueArray[6],
				categoryValueArray[7], categoryValueArray[8], categoryValueArray[9], categoryValueArray[10],
				categoryValueArray[11], categoryValueArray[12], all, "比例（%）", college));

		all = getGraduateAndDoctoralContent("博士一级学科授权点", allSubject, categoryValueArray, gdFilter, categoryCodeArray);
		content.add(new ProfessionLayout(categoryValueArray[0], categoryValueArray[1], categoryValueArray[2],
				categoryValueArray[3], categoryValueArray[4], categoryValueArray[5], categoryValueArray[6],
				categoryValueArray[7], categoryValueArray[8], categoryValueArray[9], categoryValueArray[10],
				categoryValueArray[11], categoryValueArray[12], all, "博士学位授权一级学科点数", college));
		all = getGraduateAndDoctoralContent("博士二级学科授权点", allSubject, categoryValueArray, gdFilter, categoryCodeArray);
		content.add(new ProfessionLayout(categoryValueArray[0], categoryValueArray[1], categoryValueArray[2],
				categoryValueArray[3], categoryValueArray[4], categoryValueArray[5], categoryValueArray[6],
				categoryValueArray[7], categoryValueArray[8], categoryValueArray[9], categoryValueArray[10],
				categoryValueArray[11], categoryValueArray[12], all, "博士学位授权二级学科点数（不含一级学科覆盖点）", college));
		all = getGraduateAndDoctoralContent("硕士一级学科授权点", allSubject, categoryValueArray, gdFilter, categoryCodeArray);
		content.add(new ProfessionLayout(categoryValueArray[0], categoryValueArray[1], categoryValueArray[2],
				categoryValueArray[3], categoryValueArray[4], categoryValueArray[5], categoryValueArray[6],
				categoryValueArray[7], categoryValueArray[8], categoryValueArray[9], categoryValueArray[10],
				categoryValueArray[11], categoryValueArray[12], all, "硕士学位授权一级学科点数", college));
		all = getGraduateAndDoctoralContent("硕士二级学科授权点", allSubject, categoryValueArray, gdFilter, categoryCodeArray);
		content.add(new ProfessionLayout(categoryValueArray[0], categoryValueArray[1], categoryValueArray[2],
				categoryValueArray[3], categoryValueArray[4], categoryValueArray[5], categoryValueArray[6],
				categoryValueArray[7], categoryValueArray[8], categoryValueArray[9], categoryValueArray[10],
				categoryValueArray[11], categoryValueArray[12], all, "硕士学位授权二级学科点数（不含一级学科覆盖点）", college));
		
		for (int i = 0; i < categoryArray.length; i++)
		{
			isFilter.put(ImportantStudyTable.IP_CATEGORY, categoryArray[i]);
			categoryValueArray[i] = isd.getImportantStudyCountCount(isFilter);
		}
		
		all = 0;
		for (int i = 0; i < categoryValueArray.length; i++)
			all+=categoryValueArray[i];
		
		content.add(new ProfessionLayout(categoryValueArray[0], categoryValueArray[1], categoryValueArray[2],
				categoryValueArray[3], categoryValueArray[4], categoryValueArray[5], categoryValueArray[6],
				categoryValueArray[7], categoryValueArray[8], categoryValueArray[9], categoryValueArray[10],
				categoryValueArray[11], categoryValueArray[12], all, "重点学科数", college));
		return content;

	}

	// 获取相应博士硕士点类型的总计
	public double getGraduateAndDoctoralContent(String type, int allSubject, double[] categoryValueArray,
			Map<String, String> gdFilter, String[] categoryCodeArray)
	{
		gdFilter.put(GraduateAndDoctoralTable.GD_TYPE, type);
		List<GraduateAndDoctoral> gadList = gdd.getGraduateAndDoctoral(0, allSubject, GraduateAndDoctoralTable.GD_CODE,
				"asc", gdFilter, null);
		for (int i = 0; i < categoryValueArray.length; i++)
		{
			categoryValueArray[i] = 0;
		}
		double all = 0;
		if (gadList != null && gadList.size() > 0)
		{
			for (int i = 0; i < categoryValueArray.length; i++)
			{
				for (int j = 0; j < gadList.size(); j++)
				{
					if (gadList.get(j).getGd_code().substring(0, 2).equals(categoryCodeArray[i]))
						categoryValueArray[i]++;
				}
			}
		}
		for (int i = 0; i < categoryValueArray.length; i++)
			all += categoryValueArray[i];
		return all;
	}

}
