package cn.edu.xmu.dao;

import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.News;
import cn.edu.xmu.enums.NewsType;

public interface NewsDao extends BaseDao<News> {

	// 添加新闻纪录
	public int addNewsRecord(News news);

	// 获取单条纪录
	public News getNewsRecordById(String value);

	// 获取新闻条数
	public int getNewsCount(Map queryParams);

	// 获取新闻列表
	public List<News> findForPage(int start, int end, String sort,
			String order, Map queryParams);

	// 获取新闻分类的条数，用于测试饼图
	public Map<NewsType, Integer> getNumOfEachType();

	//用于ch2的查询功能
	List<News> findForPageCH2Query(int start, int end, String sort,
			String order, Map queryParams);

	//用于ch2的查询功能
	int getNewsCountCH2Query(Map queryParams);
}
