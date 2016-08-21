package cn.edu.xmu.service;

import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.Menu;
import cn.edu.xmu.entity.SubMenu;

public interface SubMenuService {
	// 添加栏目
	public int addSubMenu(SubMenu subMenu);

	// 修改栏目
	public int alterSubMenu(Map<String, String> valueMap, int id);

	// 删除栏目
	public int deleteSubMenuById(int id);

	// 获取子栏目列表
	public List<SubMenu> getAllSubMenu(int mainMenuId);

}
