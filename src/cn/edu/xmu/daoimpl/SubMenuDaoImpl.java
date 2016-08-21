package cn.edu.xmu.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.dao.SubMenuDao;
import cn.edu.xmu.entity.SubMenu;
import cn.edu.xmu.table.SubMenuTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;

public class SubMenuDaoImpl extends BaseDaoImpl<SubMenu> implements SubMenuDao {

	@Override
	public List<SubMenu> getAllByField(String field, String value) {

		List<SubMenu> list = new ArrayList<SubMenu>();

		String sql = "select * from " + SubMenuTable.TABLE_NAME + " where 1=1 "
				+ "and " + field + "='" + value + "'";

		System.out.println(sql);
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

				int smb_id = resultSet.getInt(SubMenuTable.SMB_ID);
				int smb_mbid = resultSet.getInt(SubMenuTable.SMB_MBID);
				String smb_ch_name = resultSet
						.getString(SubMenuTable.SMB_CH_NAME);
				String smb_en_name = resultSet
						.getString(SubMenuTable.SMB_EN_NAME);
				SubMenu sm = new SubMenu(smb_id, smb_mbid, smb_ch_name,
						smb_en_name);
				list.add(sm);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}

		return list;
	}

	@Override
	public int addSubMenu(SubMenu subMenu) {
		Map<String, String> subMenuAtrrs = new HashMap<String, String>();
		subMenuAtrrs.put(SubMenuTable.SMB_MBID, subMenu.getSmb_mbid() + "");
		subMenuAtrrs.put(SubMenuTable.SMB_CH_NAME, subMenu.getSmb_ch_name());
		subMenuAtrrs.put(SubMenuTable.SMB_EN_NAME, subMenu.getSmb_en_name());
		addRecord(SubMenuTable.TABLE_NAME, subMenuAtrrs);
		return 0;
	}

	@Override
	public int deleteSubMenuById(int id) {
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(SubMenuTable.SMB_ID, id + "");
		int result = deleteRecord(SubMenuTable.TABLE_NAME, condition);
		return result;
	}

	@Override
	public int alterSubMenuRecord(Map<String, String> valueMap, int id) {
		Map condition = new HashMap();
		condition.put(SubMenuTable.SMB_ID, id);
		int result = updateRecord(SubMenuTable.TABLE_NAME, valueMap, condition);
		return result;
	}

}
