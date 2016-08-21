package cn.edu.xmu.dao;

import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.Menu;

public interface MenuDao extends BaseDao<Menu> {
	// 添加栏目
	public int addMenuRecord(Menu menu);

	// 修改栏目
	public int alterMenuRecord(Map<String, String> valueMap, int id);

	// 删除栏目
	public int deleteMenuById(int id);

	// 获取栏目数量
	public int getMenuCount();

	// 获取栏目列表
	public List<Menu> getAllMenu(Map queryParams);

	/**
	 * 根据名字获取id
	 * 
	 * @param name
	 * @return
	 */
	public int getIdMenuByName(String name);

	public Menu getMenuById(int id);
}
