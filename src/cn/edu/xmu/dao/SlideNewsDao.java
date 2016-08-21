package cn.edu.xmu.dao;

import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.SlideNews;

public interface SlideNewsDao extends BaseDao<SlideNews> {

	// 添加滑动新闻纪录
	public int addSlideNewsRecord(SlideNews slidenews);

	// 获取单条纪录
	public SlideNews getSlideNewsRecordById(String value);

	// 获取滑动新闻条数
	public int getSlideNewsCount(Map queryParams);

	// 获取滑动新闻列表
	public List<SlideNews> findForPage(int start, int end, String sort,
			String order, Map queryParams);
}
