package cn.edu.xmu.dao;

import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.Menu;
import cn.edu.xmu.entity.SubMenu;

public interface SubMenuDao extends BaseDao<SubMenu> {

	public List<SubMenu> getAllByField(String field, String value);

	// 添加子栏目
	public int addSubMenu(SubMenu subMenu);

	// 删除子栏目
	public int deleteSubMenuById(int id);

	// 修改子栏目
	public int alterSubMenuRecord(Map<String, String> valueMap, int id);

}
