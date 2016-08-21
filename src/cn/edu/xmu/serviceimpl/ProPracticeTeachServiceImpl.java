package cn.edu.xmu.serviceimpl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.ProPracticeTeach;
import cn.edu.xmu.service.ProPracticeTeachService;
import cn.edu.xmu.table.CategoryExperimentTable;
import cn.edu.xmu.table.MajorInfoTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;

/**
 * 
 * @author xiaoping 附表10 各专业实践教学情况 date 2015-8-3
 *
 */
public class ProPracticeTeachServiceImpl implements ProPracticeTeachService
{

	@Override
	public List<ProPracticeTeach> getProPracticeTeach(boolean isGetByPracticeRate, Map queryParams)
	{
		List<ProPracticeTeach> practiceTeachs = new ArrayList<ProPracticeTeach>();
		String college = "";

		Map miParams = new HashMap();
		Map ceParams = new HashMap();
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
					miParams.put(MajorInfoTable.MI_COLLEGE, value);
					ceParams.put(CategoryExperimentTable.CE_COLLEGE, value);
				}
				if (key.equals("deadline"))
				{
					queryParams.remove("deadline");
					miParams.put(MajorInfoTable.MI_DEADLINE, value);
					ceParams.put(CategoryExperimentTable.CE_DEADLINE, value);
				}
			}
		}

		practiceTeachs = getProPracticeTeach(miParams, ceParams, college);
		// 获取实践教学学分占总学分比例最高和最低的5个专业
		if (isGetByPracticeRate)
		{
			Collections.sort(practiceTeachs);
			List<ProPracticeTeach> tops = new ArrayList<ProPracticeTeach>();
			List<ProPracticeTeach> lows = new ArrayList<ProPracticeTeach>();
			int size = practiceTeachs.size();
			if (size > 5)
			{
				lows = practiceTeachs.subList(0, 5);
				for (int i = size - 1; i >= size - 5; i--)
				{
					ProPracticeTeach proPracticeTeach = practiceTeachs.get(i);
					ProPracticeTeach p = new ProPracticeTeach(proPracticeTeach.getSerialNumber(),
							proPracticeTeach.getMajor(), proPracticeTeach.getPracticeCredit(),
							proPracticeTeach.getPracticeRate(), proPracticeTeach.getExpriCredit(),
							proPracticeTeach.getExpriRate(), proPracticeTeach.getHaveExpriCourse(),
							proPracticeTeach.getIndepenExperiCourse(), proPracticeTeach.getPercentage(),
							proPracticeTeach.getDesignExperiment(), college, "实践教学学分占总学分比例最高的5个专业：");
					tops.add(p);
				}
			}
			else{
				lows = practiceTeachs.subList(0, practiceTeachs.size());
				for (int i = size - 1; i >= 0; i--)
				{
					ProPracticeTeach proPracticeTeach = practiceTeachs.get(i);
					ProPracticeTeach p = new ProPracticeTeach(proPracticeTeach.getSerialNumber(),
							proPracticeTeach.getMajor(), proPracticeTeach.getPracticeCredit(),
							proPracticeTeach.getPracticeRate(), proPracticeTeach.getExpriCredit(),
							proPracticeTeach.getExpriRate(), proPracticeTeach.getHaveExpriCourse(),
							proPracticeTeach.getIndepenExperiCourse(), proPracticeTeach.getPercentage(),
							proPracticeTeach.getDesignExperiment(), college, "实践教学学分占总学分比例最高的5个专业：");
					tops.add(p);
				}
			}
			tops.addAll(lows);
			practiceTeachs = tops;
		}

		return practiceTeachs;
	}

	/**
	 * 获取各专业实验教学情况
	 * 
	 * @param miParams
	 * @param ceParams
	 * @return
	 */
	public List<ProPracticeTeach> getProPracticeTeach(Map miParams, Map ceParams, String college)
	{
		List<ProPracticeTeach> resultList = new ArrayList<ProPracticeTeach>();
		String sql = "SELECT " + MajorInfoTable.MI_MAJORCODEINSCHOOL + "," + MajorInfoTable.MI_MAJORNAMEINSCHOOL + ","
				+ MajorInfoTable.MI_ALLCREDITS + "," + MajorInfoTable.MI_CONCENTRATEDPRACTICECREDITS + ","
				+ MajorInfoTable.MI_EXPERIMENTCREDITS + "," + CategoryExperimentTable.CE_CONTAINEXPERIMENT + ","
				+ CategoryExperimentTable.CE_SINLGEEXPERIMENT + "," + CategoryExperimentTable.CE_PERCENTAGE + ","
				+ CategoryExperimentTable.CE_DESIGNEXPERIMENT + " from " + MajorInfoTable.TABLE_NAME + ","
				+ CategoryExperimentTable.TABLE_NAME + " WHERE " + MajorInfoTable.MI_MAJORNAMEINSCHOOL + "="
				+ CategoryExperimentTable.CE_MAJORNAME;
		if (miParams != null && miParams.size() > 0)
		{
			for (Object object : miParams.keySet())
			{
				String key = object.toString();
				String value = miParams.get(key).toString();
				sql += String.format(" and %s like  '%%%s%%'", key, value);
			}
		}
		if (ceParams != null && ceParams.size() > 0)
		{
			for (Object object : ceParams.keySet())
			{
				String key = object.toString();
				String value = ceParams.get(key).toString();
				sql += String.format(" and %s like  '%%%s%%'", key, value);
			}
		}
		sql += " ORDER BY " + MajorInfoTable.MI_MAJORCODEINSCHOOL;// 根据专业代码排序
		Connection connection = null;
		try
		{
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1)
		{
			e1.printStackTrace();
			return null;
		}
		System.out.println(sql);
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try
		{
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			BigDecimal b;// 用于百分比的四舍五入
			int serialNumber = 0;
			while (resultSet.next())
			{
				String major = resultSet.getString(MajorInfoTable.MI_MAJORNAMEINSCHOOL);
				double totalCredit = resultSet.getDouble(MajorInfoTable.MI_ALLCREDITS);
				double practiceCredit = resultSet.getDouble(MajorInfoTable.MI_CONCENTRATEDPRACTICECREDITS);
				double practiceRate = 0;
				double expriCredit = resultSet.getShort(MajorInfoTable.MI_EXPERIMENTCREDITS);
				double expriRate = 0;
				if (totalCredit != 0)
				{
					practiceRate = practiceCredit / totalCredit * 100.0;
					b = new BigDecimal(practiceRate);
					practiceRate = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

					expriRate = expriCredit / totalCredit * 100.0;
					b = new BigDecimal(expriRate);
					expriRate = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				}
				int haveExpriCourse = resultSet.getInt(CategoryExperimentTable.CE_CONTAINEXPERIMENT);// 有实验的课程（门）
				int indepenExperiCourse = resultSet.getInt(CategoryExperimentTable.CE_SINLGEEXPERIMENT);// 独立设置的实验课程（门）
				double percentage = resultSet.getDouble(CategoryExperimentTable.CE_PERCENTAGE);// 实验开出率（%）
				int designExperiment = resultSet.getInt(CategoryExperimentTable.CE_DESIGNEXPERIMENT);// 综合性、设计性实验门数
				ProPracticeTeach proPracticeTeach = new ProPracticeTeach(++serialNumber, major, practiceCredit,
						practiceRate, expriCredit, expriRate, haveExpriCourse, indepenExperiCourse, percentage,
						designExperiment, college, "实践教学学分占总学分比例最低的5个专业：");
				resultList.add(proPracticeTeach);
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		} finally
		{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return resultList;
	}
}
