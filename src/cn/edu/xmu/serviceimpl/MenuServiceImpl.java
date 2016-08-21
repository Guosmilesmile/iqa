package cn.edu.xmu.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import java.util.Map;

import cn.edu.xmu.dao.MenuDao;
import cn.edu.xmu.dao.SubMenuDao;
import cn.edu.xmu.daoimpl.MenuDaoImpl;
import cn.edu.xmu.daoimpl.SubMenuDaoImpl;
import cn.edu.xmu.entity.Menu;
import cn.edu.xmu.entity.SubMenu;
import cn.edu.xmu.service.MenuService;
import cn.edu.xmu.table.SubMenuTable;

public class MenuServiceImpl implements MenuService {
	private MenuDao menuDao = new MenuDaoImpl();
	private SubMenuDao subMenuDao = new SubMenuDaoImpl();

	@Override
	public int addMenuRecord(Menu menu) {
		// TODO Auto-generated method stub
		int result = 0;
		result = menuDao.addMenuRecord(menu);
		return result;
	}

	@Override
	public int alterMenuRecord(Map<String, String> valueMap, int id) {
		int result = menuDao.alterMenuRecord(valueMap, id);
		return result;
	}

	@Override
	public int deleteMenuById(int id) {
		int result = menuDao.deleteMenuById(id);

		List<SubMenu> subMenus = subMenuDao.getAllByField(
				SubMenuTable.SMB_MBID, id + "");
		if (subMenus.size() > 0) {
			for (SubMenu subMenu : subMenus) {
				subMenuDao.deleteSubMenuById(subMenu.getSmb_id());
			}
		}
		return result;
	}

	@Override
	public List<Menu> getAllMenu(Map queryParams) {
		List<Menu> menuList = new ArrayList<Menu>();
		menuList = menuDao.getAllMenu(queryParams);
		List<SubMenu> subMenus = null;
		for (Menu menu : menuList) {
			int menuId = menu.getMb_id();
			subMenus = subMenuDao.getAllByField(SubMenuTable.SMB_MBID, menuId
					+ "");
			menu.setSubMenus(subMenus);
		}
		return menuList;
	}

	@Override
	public int getIdMenuByName(String name) {
		return menuDao.getIdMenuByName(name);
	}

	@Override
	public int getMenuCount() {
		// TODO Auto-generated method stub
		return 0;
	}

}
