package cn.edu.xmu.serviceimpl;

import java.util.List;
import java.util.Map;

import cn.edu.xmu.dao.SubMenuDao;
import cn.edu.xmu.daoimpl.SubMenuDaoImpl;
import cn.edu.xmu.entity.SubMenu;
import cn.edu.xmu.service.SubMenuService;
import cn.edu.xmu.table.SubMenuTable;

public class SubMenuServiceImpl implements SubMenuService {
	private SubMenuDao subMenuDao = new SubMenuDaoImpl();

	@Override
	public int addSubMenu(SubMenu subMenu) {
		subMenuDao.addSubMenu(subMenu);
		return 0;
	}

	@Override
	public int alterSubMenu(Map<String, String> valueMap, int id) {
		int result = subMenuDao.alterSubMenuRecord(valueMap, id);
		return result;
	}

	@Override
	public int deleteSubMenuById(int id) {
		subMenuDao.deleteSubMenuById(id);
		return 0;
	}

	@Override
	public List<SubMenu> getAllSubMenu(int mainMenuId) {
		List<SubMenu> subMenus = subMenuDao.getAllByField(SubMenuTable.SMB_ID,
				mainMenuId + "");
		return subMenus;
	}

}
