package cn.edu.xmu.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.dao.MenuDao;
import cn.edu.xmu.dao.SubMenuDao;
import cn.edu.xmu.entity.Menu;
import cn.edu.xmu.entity.SubMenu;
import cn.edu.xmu.table.MenuTable;
import cn.edu.xmu.table.SubMenuTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;

public class MenuDaoImpl extends BaseDaoImpl<Menu> implements MenuDao {
	private SubMenuDao subMenuDao = new SubMenuDaoImpl();

	@Override
	public int addMenuRecord(Menu menu) {
		// TODO Auto-generated method stub
		int result = 0;
		String sql = "insert into " + MenuTable.TABLE_NAME + "("
				+ MenuTable.MB_CH_NAME + "," + MenuTable.MB_EN_NAME
				+ ")values(?,?)";

		System.out.println("新增栏目" + sql);
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PreparedStatement pstmt = null;

		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, menu.getMb_ch_name());
			pstmt.setString(2, menu.getMb_en_name());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			result = -1;
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, null);
		}
		return result;
	}

	/***
	 * @param 需要修改的键值对
	 * @param 需要修改的栏目的id
	 */
	@Override
	public int alterMenuRecord(Map<String, String> valueMap, int id) {
		Map condition = new HashMap();
		condition.put(MenuTable.MB_ID, id);
		int result = updateRecord(MenuTable.TABLE_NAME, valueMap, condition);
		return result;
	}

	@Override
	public int deleteMenuById(int id) {
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(MenuTable.MB_ID, id + "");
		int result = deleteRecord(MenuTable.TABLE_NAME, condition);
		return result;
	}

	@Override
	public int getMenuCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Menu> getAllMenu(Map queryParams) {

		String sql = " select * from " + MenuTable.TABLE_NAME + " where 1=1 ";
		System.out.println(sql);
		for (Object object : queryParams.keySet()) {
			String key = object.toString();
			String value = queryParams.get(key).toString();
			sql += String.format(" and %s = '%s'", key, value);
		}
		sql += " order by " + MenuTable.MB_ID;
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		List<Menu> resultList = new ArrayList<Menu>();
		try {
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				int mb_id = resultSet.getInt(MenuTable.MB_ID);
				String mb_ch_name = resultSet.getString(MenuTable.MB_CH_NAME);
				String mb_en_name = resultSet.getString(MenuTable.MB_EN_NAME);
				Menu menu = new Menu(mb_id, mb_ch_name, mb_en_name);
				resultList.add(menu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return resultList;

	}

	@Override
	public int getIdMenuByName(String name) {
		Map<String, String> params = new HashMap<String, String>();
		params.put(MenuTable.MB_CH_NAME, name);
		int id = (Integer) getData(MenuTable.TABLE_NAME, MenuTable.MB_ID,
				params);
		return id;
	}

	@Override
	public Menu getMenuById(int id) {

		String sql = "select * from " + MenuTable.TABLE_NAME + " where "
				+ MenuTable.MB_ID + "=" + id;
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		try {
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				int mb_id = resultSet.getInt(MenuTable.MB_ID);
				String mb_ch_name = resultSet.getString(MenuTable.MB_CH_NAME);
				String mb_en_name = resultSet.getString(MenuTable.MB_EN_NAME);
				List<SubMenu> subMenus = null;
				subMenus = subMenuDao.getAllByField(SubMenuTable.SMB_MBID,
						mb_id + "");
				Menu menu = new Menu(mb_id, mb_ch_name, mb_en_name);
				menu.setSubMenus(subMenus);
				return menu;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return null;
	}
}
