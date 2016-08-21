package cn.edu.xmu.service;

import java.util.Map;

/**
 * 获取导航service
 */
public interface GetMenuService {
	public abstract Map<Integer, String[]> getFirstMenu(); // 获取一级菜单

	public abstract Map<Integer, String[]> getSecondMenu(Integer menu_id); // 获取二级菜单
}
