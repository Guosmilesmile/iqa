package cn.edu.xmu.daoimpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.dao.GeneralHighEnrollmentfileLineDao;
import cn.edu.xmu.entity.GeneralHighEnrollmentfileLine;
import cn.edu.xmu.table.GeneralHighEnrollmentfileLineTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;


/**
 * @author zhantu
 * 厦门大学普高招生各省份出档线高出本一线分值  实体类功能 ——接口实现
 * date 2015-07-13
 */

public class GeneralHighEnrollmentfileLineDaoImpl extends BaseDaoImpl<GeneralHighEnrollmentfileLine>implements GeneralHighEnrollmentfileLineDao{

	@Override
	public int addRecord(GeneralHighEnrollmentfileLine ghel) {
		
		int result = 0;

		String t_sql = "update " + GeneralHighEnrollmentfileLineTable.TABLE_NAME + " set "
				+ GeneralHighEnrollmentfileLineTable.GHEL_SERIALNUMBER + " = "
				+ GeneralHighEnrollmentfileLineTable.GHEL_SERIALNUMBER + " +1 where "
				+ GeneralHighEnrollmentfileLineTable.GHEL_SERIALNUMBER + ">=?";

		
		Connection connection2 = null;
		try {
			//连接池取得对象连接
			connection2 = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
			return 0;
		}
		PreparedStatement t_pstmt = null;
		try {
			//获取操作对象
			t_pstmt = connection2.prepareStatement(t_sql);
			t_pstmt.setInt(1, ghel.getGhel_serialnumber());
			
			//执行插入操作并更新
			result = t_pstmt.executeUpdate();
			
		} catch (SQLException e) {
			//事务回滚，不做插入操作
			e.printStackTrace();
			return 0;
		} finally {
			JdbcUtils_DBCP.release(connection2, t_pstmt, null);
		}

		//执行插入操作的语句
		String sql = "insert into " + GeneralHighEnrollmentfileLineTable.TABLE_NAME + "("
				+ GeneralHighEnrollmentfileLineTable.GHEL_TYPE + ","
				
				+ GeneralHighEnrollmentfileLineTable.GHEL_HAINAN + ","
				+ GeneralHighEnrollmentfileLineTable.GHEL_XINJIANG + ","
				+ GeneralHighEnrollmentfileLineTable.GHEL_XIZANGSHAO + ","
				+ GeneralHighEnrollmentfileLineTable.GHEL_YUNNAN + ","
				
				+ GeneralHighEnrollmentfileLineTable.GHEL_SHANXISHAN + ","
				+ GeneralHighEnrollmentfileLineTable.GHEL_TIANJIN + ","
				+ GeneralHighEnrollmentfileLineTable.GHEL_NINGXIA + ","
				+ GeneralHighEnrollmentfileLineTable.GHEL_GUIZHOU + ","
				
				+ GeneralHighEnrollmentfileLineTable.GHEL_LIAONING + ","
				+ GeneralHighEnrollmentfileLineTable.GHEL_XIZANGHAN + ","
				+ GeneralHighEnrollmentfileLineTable.GHEL_JILIN + ","
				+ GeneralHighEnrollmentfileLineTable.GHEL_GUANGXI + ","
				
				+ GeneralHighEnrollmentfileLineTable.GHEL_ZHEJIANG + ","
				+ GeneralHighEnrollmentfileLineTable.GHEL_CHONGQING + ","
				+ GeneralHighEnrollmentfileLineTable.GHEL_ANHUI + ","
				+ GeneralHighEnrollmentfileLineTable.GHEL_HEILONGJIANG + ","
				
				+ GeneralHighEnrollmentfileLineTable.GHEL_JIANGXI + ","
				+ GeneralHighEnrollmentfileLineTable.GHEL_SICHUAN + ","
				+ GeneralHighEnrollmentfileLineTable.GHEL_BEIJING + ","
				+ GeneralHighEnrollmentfileLineTable.GHEL_HENAN + ","
				
				+ GeneralHighEnrollmentfileLineTable.GHEL_HUNAN + ","
				+ GeneralHighEnrollmentfileLineTable.GHEL_SHANGHAI + ","
				+ GeneralHighEnrollmentfileLineTable.GHEL_FUJIAN + ","
				+ GeneralHighEnrollmentfileLineTable.GHEL_SHANDONG + ","
				
				+ GeneralHighEnrollmentfileLineTable.GHEL_HEBEI + ","
				+ GeneralHighEnrollmentfileLineTable.GHEL_HUBEI + ","
				+ GeneralHighEnrollmentfileLineTable.GHEL_GUANGDONG + ","
				+ GeneralHighEnrollmentfileLineTable.GHEL_JIANGSU + ","
				
				+ GeneralHighEnrollmentfileLineTable.GHEL_SHANXIJIN + ","
				+ GeneralHighEnrollmentfileLineTable.GHEL_NEIMENGGU + ","
				+ GeneralHighEnrollmentfileLineTable.GHEL_QINGHAI+ ","
				+ GeneralHighEnrollmentfileLineTable.GHEL_GANSU + ","
				
				+ GeneralHighEnrollmentfileLineTable.GHEL_SERIALNUMBER + ","
				+ GeneralHighEnrollmentfileLineTable.GHEL_COLLEGE + ","
				+ GeneralHighEnrollmentfileLineTable.GHEL_COMMENTS + ","
				+ GeneralHighEnrollmentfileLineTable.ISNULL
				+ ")values(?, ?,?,?,?, ?,?,?,?, ?,?,?,?, ?,?,?,?, ?,?,?,?, ?,?,?,?"
				+ ", ?,?,?,?, ?,?,?,?, ?,?,?,? )";

		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, ghel.getGhel_type());

			pstmt.setFloat(2, ghel.getGhel_hainan());
			pstmt.setFloat(3, ghel.getGhel_xinjiang());
			pstmt.setFloat(4, ghel.getGhel_xizangshao());
			pstmt.setFloat(5, ghel.getGhel_yunnan());

			pstmt.setFloat(6, ghel.getGhel_shanxishan());
			pstmt.setFloat(7, ghel.getGhel_tianjin());
			pstmt.setFloat(8, ghel.getGhel_ningxia());
			pstmt.setFloat(9, ghel.getGhel_guizhou());

			pstmt.setFloat(10, ghel.getGhel_liaoning());
			pstmt.setFloat(11, ghel.getGhel_xizanghan());
			pstmt.setFloat(12, ghel.getGhel_jilin());
			pstmt.setFloat(13, ghel.getGhel_guangxi());

			pstmt.setFloat(14, ghel.getGhel_zhejiang());
			pstmt.setFloat(15, ghel.getGhel_chongqing());
			pstmt.setFloat(16, ghel.getGhel_anhui());
			pstmt.setFloat(17, ghel.getGhel_heilongjiang());

			pstmt.setFloat(18, ghel.getGhel_jiangxi());
			pstmt.setFloat(19, ghel.getGhel_sichuan());
			pstmt.setFloat(20, ghel.getGhel_beijing());
			pstmt.setFloat(21, ghel.getGhel_henan());

			pstmt.setFloat(22, ghel.getGhel_hunan());
			pstmt.setFloat(23, ghel.getGhel_shanghai());
			pstmt.setFloat(24, ghel.getGhel_fujian());
			pstmt.setFloat(25, ghel.getGhel_shandong());

			pstmt.setFloat(26, ghel.getGhel_hebei());
			pstmt.setFloat(27, ghel.getGhel_hubei());
			pstmt.setFloat(28, ghel.getGhel_guangdong());
			pstmt.setFloat(29, ghel.getGhel_jiangsu());

			pstmt.setFloat(30, ghel.getGhel_shanxijin());
			pstmt.setFloat(31, ghel.getGhel_neimenggu());
			pstmt.setFloat(32, ghel.getGhel_qinghai());
			pstmt.setFloat(33, ghel.getGhel_gansu());
			
			pstmt.setInt(34, ghel.getGhel_serialnumber());
			pstmt.setString(35, ghel.getGhel_college());
			pstmt.setString(36, ghel.getGhel_comments());
			pstmt.setInt(37, ghel.getIsnull());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			//事务回滚。不做插入操作
			e.printStackTrace();
			result = -1;
			
		} finally {
			JdbcUtils_DBCP.release(connection,pstmt,null);
		}
		return result;
		
	}

	@Override
	public int getGeneralHighEnrollmentfileLineCount(Map queryParams) {
		
		int count = 0;
		//获取条件的语句
		String sql = "select count(*) from " + GeneralHighEnrollmentfileLineTable.TABLE_NAME
				+ " where 1 = 1";
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		for (Object object : queryParams.keySet()) {
			String key = object.toString();
			String value = queryParams.get(key).toString();
			sql += String.format(" and  %s like  '%%%s%%' ", key, value);
		}
		sql += String.format(" or %s ='%s'", GeneralHighEnrollmentfileLineTable.GHEL_COLLEGE, "");
		
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		try {
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			resultSet.next();
			count = resultSet.getInt(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		
		return count;
	}

	@Override
	public List<GeneralHighEnrollmentfileLine> getAllGeneralHighEnrollmentfileLine(int start, int end,
			String sortStr, String orderStr, Map queryParams) {
		
		String sql = " select tmp.* from ( " + " select * from "
				+ GeneralHighEnrollmentfileLineTable.TABLE_NAME + " where 1=1 ";
		
		for (Object object : queryParams.keySet()) {
			String key = object.toString();
			String value = queryParams.get(key).toString();
			sql += String.format(" and %s like  '%%%s%%'", key, value);
		}
		sql += " order by " + sortStr + " " + orderStr + " ) tmp limit "
				+ start + " ," + end;

		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		List<GeneralHighEnrollmentfileLine> ghels = new ArrayList<GeneralHighEnrollmentfileLine>();
		try {
			
			connection = JdbcUtils_DBCP.getConnection();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int ghel_id = resultSet.getInt(GeneralHighEnrollmentfileLineTable.GHEL_ID);
				String ghel_type = resultSet.getString(GeneralHighEnrollmentfileLineTable.GHEL_TYPE);
				
				Float ghel_hainan = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_HAINAN);
				if(ghel_hainan==-999)
					ghel_hainan = null;
				Float ghel_xinjiang = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_XINJIANG);
				if(ghel_xinjiang==-999)
					ghel_xinjiang = null;
				Float ghel_xizangshao = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_XIZANGSHAO);
				if(ghel_xizangshao==-999)
					ghel_xizangshao = null;
				Float ghel_yunnan = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_YUNNAN);
				if(ghel_yunnan==-999)
					ghel_yunnan = null;
				Float ghel_shanxishan = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_SHANXISHAN);
				if(ghel_shanxishan==-999)
					ghel_shanxishan = null;
				Float ghel_tianjin = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_TIANJIN);
				if(ghel_tianjin==-999)
					ghel_tianjin = null;
				Float ghel_ningxia = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_NINGXIA);
				if(ghel_ningxia==-999)
					ghel_ningxia = null;
				Float ghel_guizhou = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_GUIZHOU);
				if(ghel_guizhou==-999)
					ghel_guizhou = null;
				Float ghel_liaoning = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_LIAONING);
				if(ghel_liaoning==-999)
					ghel_liaoning = null;
				Float ghel_xizanghan = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_XIZANGHAN);
				if(ghel_xizanghan==-999)
					ghel_xizanghan = null;
				Float ghel_jilin = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_JILIN);
				if(ghel_jilin==-999)
					ghel_jilin = null;
				Float ghel_guangxi = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_GUANGXI);
				if(ghel_guangxi==-999)
					ghel_guangxi = null;
				Float ghel_zhejiang = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_ZHEJIANG);
				if(ghel_zhejiang==-999)
					ghel_zhejiang = null;
				Float ghel_chongqing = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_CHONGQING);
				if(ghel_chongqing==-999)
					ghel_chongqing = null;
				Float ghel_anhui = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_ANHUI);
				if(ghel_anhui==-999)
					ghel_anhui = null;
				Float ghel_heilongjiang = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_HEILONGJIANG);
				if(ghel_heilongjiang==-999)
					ghel_heilongjiang = null;
				Float ghel_jiangxi = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_JIANGXI);
				if(ghel_jiangxi==-999)
					ghel_jiangxi = null;
				Float ghel_sichuan = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_SICHUAN);
				if(ghel_sichuan==-999)
					ghel_sichuan = null;
				Float ghel_beijing = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_BEIJING);
				if(ghel_beijing==-999)
					ghel_beijing = null;
				Float ghel_henan = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_HENAN);
				if(ghel_henan==-999)
					ghel_henan = null;
				Float ghel_hunan = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_HUNAN);
				if(ghel_hunan==-999)
					ghel_hunan = null;
				Float ghel_shanghai = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_SHANGHAI);
				if(ghel_shanghai==-999)
					ghel_shanghai = null;
				Float ghel_fujian = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_FUJIAN);
				if(ghel_fujian==-999)
					ghel_fujian = null;
				Float ghel_shandong = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_SHANDONG);
				if(ghel_shandong==-999)
					ghel_shandong = null;
				Float ghel_hebei = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_HEBEI);
				if(ghel_hebei==-999)
					ghel_hebei = null;
				Float ghel_hubei = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_HUBEI);
				if(ghel_hubei==-999)
					ghel_hubei = null;
				Float ghel_guangdong = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_GUANGDONG);
				if(ghel_guangdong==-999)
					ghel_guangdong = null;
				Float ghel_jiangsu = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_JIANGSU);
				if(ghel_jiangsu==-999)
					ghel_jiangsu = null;
				Float ghel_shanxijin = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_SHANXIJIN);
				if(ghel_shanxijin==-999)
					ghel_shanxijin = null;
				Float ghel_neimenggu = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_NEIMENGGU);
				if(ghel_neimenggu==-999)
					ghel_neimenggu = null;
				Float ghel_qinghai = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_QINGHAI);
				if(ghel_qinghai==-999)
					ghel_qinghai = null;
				Float ghel_gansu = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_GANSU);
				if(ghel_gansu==-999)
					ghel_gansu = null;

				
				int ghel_serialnumber = resultSet.getInt(GeneralHighEnrollmentfileLineTable.GHEL_SERIALNUMBER);
				Date ghel_deadline = resultSet.getDate(GeneralHighEnrollmentfileLineTable.GHEL_DEADLINE);
				String ghel_comments = resultSet.getString(GeneralHighEnrollmentfileLineTable.GHEL_COMMENTS);
				String ghel_college = resultSet.getString(GeneralHighEnrollmentfileLineTable.GHEL_COLLEGE);
				int isnull = resultSet.getInt(GeneralHighEnrollmentfileLineTable.ISNULL);
				if(ghel_comments == null)
					ghel_comments = "";
				
				GeneralHighEnrollmentfileLine ghel = new GeneralHighEnrollmentfileLine(ghel_id, ghel_type,
						ghel_hainan, ghel_xinjiang, ghel_xizangshao,
						ghel_yunnan, ghel_shanxishan, ghel_tianjin,
						ghel_ningxia, ghel_guizhou, ghel_liaoning,
						ghel_xizanghan, ghel_jilin, ghel_guangxi,
						ghel_zhejiang, ghel_chongqing, ghel_anhui,
						ghel_heilongjiang, ghel_jiangxi, ghel_sichuan,
						ghel_beijing, ghel_henan, ghel_hunan,
						ghel_shanghai, ghel_fujian, ghel_shandong,
						ghel_hebei, ghel_hubei, ghel_guangdong,
						ghel_jiangsu, ghel_shanxijin, ghel_neimenggu,
						ghel_qinghai, ghel_gansu, ghel_serialnumber,
						ghel_deadline, ghel_college, ghel_comments, isnull);
				
				ghels.add(ghel);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return ghels;
	}

	@Override
	public int alterGeneralHighEnrollmentfileLine(Map<String, String> valueMap, String id) {
		
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(GeneralHighEnrollmentfileLineTable.GHEL_ID, id);
		int result = updateRecord(GeneralHighEnrollmentfileLineTable.TABLE_NAME, valueMap,
				condition);
		return result;
	}

	@Override
	public boolean batchDelete(String[] ghelids) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();

		for (String ghelid : ghelids) {
			String sql = "delete from " + GeneralHighEnrollmentfileLineTable.TABLE_NAME
					+ " where " + GeneralHighEnrollmentfileLineTable.GHEL_ID + " = '" + ghelid + "'";
			try {
				stmt.executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		JdbcUtils_DBCP.release(connection, stmt, null);
		return true;
	}

	@Override
	public List<GeneralHighEnrollmentfileLine> getGeneralHighEnrollmentfileLine(int start, int end,
			String sortStr, String orderStr, Map<String, String> params,
			Date deadline) {
		String sql = " select tmp.* from ( " + " select * from "
				+ GeneralHighEnrollmentfileLineTable.TABLE_NAME + " where 1=1 ";
		if (deadline != null) {

			sql += String.format("and "+GeneralHighEnrollmentfileLineTable.GHEL_DEADLINE+" like  '%s%%' ", deadline);
		}
		if (!(params == null || params.keySet().size() == 0)) {
			for (Object object : params.keySet()) {

				String key = object.toString();
				String value = params.get(key).toString();
				sql += String.format("and %s like  '%%%s%%' ", key, value);
			}
		}

		sql += " order by " + sortStr + " " + orderStr + " ) tmp limit "
				+ start + " ," + end;

		System.out.println(sql);

		List<GeneralHighEnrollmentfileLine> newBooksthatYears = new ArrayList<GeneralHighEnrollmentfileLine>();
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
			System.out.println(sql);
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				int ghel_id = resultSet.getInt(GeneralHighEnrollmentfileLineTable.GHEL_ID);
				String ghel_type = resultSet.getString(GeneralHighEnrollmentfileLineTable.GHEL_TYPE);
				
				Float ghel_hainan = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_HAINAN);
				if(ghel_hainan==-999)
					ghel_hainan = null;
				Float ghel_xinjiang = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_XINJIANG);
				if(ghel_xinjiang==-999)
					ghel_xinjiang = null;
				Float ghel_xizangshao = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_XIZANGSHAO);
				if(ghel_xizangshao==-999)
					ghel_xizangshao = null;
				Float ghel_yunnan = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_YUNNAN);
				if(ghel_yunnan==-999)
					ghel_yunnan = null;
				Float ghel_shanxishan = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_SHANXISHAN);
				if(ghel_shanxishan==-999)
					ghel_shanxishan = null;
				Float ghel_tianjin = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_TIANJIN);
				if(ghel_tianjin==-999)
					ghel_tianjin = null;
				Float ghel_ningxia = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_NINGXIA);
				if(ghel_ningxia==-999)
					ghel_ningxia = null;
				Float ghel_guizhou = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_GUIZHOU);
				if(ghel_guizhou==-999)
					ghel_guizhou = null;
				Float ghel_liaoning = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_LIAONING);
				if(ghel_liaoning==-999)
					ghel_liaoning = null;
				Float ghel_xizanghan = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_XIZANGHAN);
				if(ghel_xizanghan==-999)
					ghel_xizanghan = null;
				Float ghel_jilin = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_JILIN);
				if(ghel_jilin==-999)
					ghel_jilin = null;
				Float ghel_guangxi = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_GUANGXI);
				if(ghel_guangxi==-999)
					ghel_guangxi = null;
				Float ghel_zhejiang = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_ZHEJIANG);
				if(ghel_zhejiang==-999)
					ghel_zhejiang = null;
				Float ghel_chongqing = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_CHONGQING);
				if(ghel_chongqing==-999)
					ghel_chongqing = null;
				Float ghel_anhui = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_ANHUI);
				if(ghel_anhui==-999)
					ghel_anhui = null;
				Float ghel_heilongjiang = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_HEILONGJIANG);
				if(ghel_heilongjiang==-999)
					ghel_heilongjiang = null;
				Float ghel_jiangxi = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_JIANGXI);
				if(ghel_jiangxi==-999)
					ghel_jiangxi = null;
				Float ghel_sichuan = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_SICHUAN);
				if(ghel_sichuan==-999)
					ghel_sichuan = null;
				Float ghel_beijing = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_BEIJING);
				if(ghel_beijing==-999)
					ghel_beijing = null;
				Float ghel_henan = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_HENAN);
				if(ghel_henan==-999)
					ghel_henan = null;
				Float ghel_hunan = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_HUNAN);
				if(ghel_hunan==-999)
					ghel_hunan = null;
				Float ghel_shanghai = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_SHANGHAI);
				if(ghel_shanghai==-999)
					ghel_shanghai = null;
				Float ghel_fujian = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_FUJIAN);
				if(ghel_fujian==-999)
					ghel_fujian = null;
				Float ghel_shandong = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_SHANDONG);
				if(ghel_shandong==-999)
					ghel_shandong = null;
				Float ghel_hebei = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_HEBEI);
				if(ghel_hebei==-999)
					ghel_hebei = null;
				Float ghel_hubei = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_HUBEI);
				if(ghel_hubei==-999)
					ghel_hubei = null;
				Float ghel_guangdong = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_GUANGDONG);
				if(ghel_guangdong==-999)
					ghel_guangdong = null;
				Float ghel_jiangsu = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_JIANGSU);
				if(ghel_jiangsu==-999)
					ghel_jiangsu = null;
				Float ghel_shanxijin = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_SHANXIJIN);
				if(ghel_shanxijin==-999)
					ghel_shanxijin = null;
				Float ghel_neimenggu = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_NEIMENGGU);
				if(ghel_neimenggu==-999)
					ghel_neimenggu = null;
				Float ghel_qinghai = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_QINGHAI);
				if(ghel_qinghai==-999)
					ghel_qinghai = null;
				Float ghel_gansu = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_GANSU);
				if(ghel_gansu==-999)
					ghel_gansu = null;

				
				int ghel_serialnumber = resultSet.getInt(GeneralHighEnrollmentfileLineTable.GHEL_SERIALNUMBER);
				Date ghel_deadline = resultSet.getDate(GeneralHighEnrollmentfileLineTable.GHEL_DEADLINE);
				String ghel_comments = resultSet.getString(GeneralHighEnrollmentfileLineTable.GHEL_COMMENTS);
				String ghel_college = resultSet.getString(GeneralHighEnrollmentfileLineTable.GHEL_COLLEGE);
				int isnull = resultSet.getInt(GeneralHighEnrollmentfileLineTable.ISNULL);
				if(ghel_comments == null)
					ghel_comments = "";
				
				GeneralHighEnrollmentfileLine ghel = new GeneralHighEnrollmentfileLine(ghel_id, ghel_type,
						ghel_hainan, ghel_xinjiang, ghel_xizangshao,
						ghel_yunnan, ghel_shanxishan, ghel_tianjin,
						ghel_ningxia, ghel_guizhou, ghel_liaoning,
						ghel_xizanghan, ghel_jilin, ghel_guangxi,
						ghel_zhejiang, ghel_chongqing, ghel_anhui,
						ghel_heilongjiang, ghel_jiangxi, ghel_sichuan,
						ghel_beijing, ghel_henan, ghel_hunan,
						ghel_shanghai, ghel_fujian, ghel_shandong,
						ghel_hebei, ghel_hubei, ghel_guangdong,
						ghel_jiangsu, ghel_shanxijin, ghel_neimenggu,
						ghel_qinghai, ghel_gansu, ghel_serialnumber,
						ghel_deadline, ghel_college, ghel_comments, isnull);

				newBooksthatYears.add(ghel);
			}
			return newBooksthatYears;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
	}
	
	@Override
	public void deleteByCollegeandDeadline(String college, Date deadline)
			throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();
		String sql = "delete from " + GeneralHighEnrollmentfileLineTable.TABLE_NAME
				+ " where " + GeneralHighEnrollmentfileLineTable.GHEL_COLLEGE + " = '" + college + "'" +" and ghel_deadline is null ";
		System.out.println(sql);
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils_DBCP.release(connection, stmt, null);
		}
		
	}

	@Override
	public List<GeneralHighEnrollmentfileLine> getAllGeneralHighEnrollmentfileLine() {
		String sql = " select * from " + GeneralHighEnrollmentfileLineTable.TABLE_NAME
				+ " where 1=1 " + " order by " + GeneralHighEnrollmentfileLineTable.GHEL_ID;
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			System.out.println(sql);
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			List<GeneralHighEnrollmentfileLine> newBooksthatYearList = new ArrayList<GeneralHighEnrollmentfileLine>();
			while (resultSet.next()) {
				int ghel_id = resultSet.getInt(GeneralHighEnrollmentfileLineTable.GHEL_ID);
				String ghel_type = resultSet.getString(GeneralHighEnrollmentfileLineTable.GHEL_TYPE);
				
				Float ghel_hainan = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_HAINAN);
				if(ghel_hainan==-999)
					ghel_hainan = null;
				Float ghel_xinjiang = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_XINJIANG);
				if(ghel_xinjiang==-999)
					ghel_xinjiang = null;
				Float ghel_xizangshao = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_XIZANGSHAO);
				if(ghel_xizangshao==-999)
					ghel_xizangshao = null;
				Float ghel_yunnan = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_YUNNAN);
				if(ghel_yunnan==-999)
					ghel_yunnan = null;
				Float ghel_shanxishan = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_SHANXISHAN);
				if(ghel_shanxishan==-999)
					ghel_shanxishan = null;
				Float ghel_tianjin = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_TIANJIN);
				if(ghel_tianjin==-999)
					ghel_tianjin = null;
				Float ghel_ningxia = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_NINGXIA);
				if(ghel_ningxia==-999)
					ghel_ningxia = null;
				Float ghel_guizhou = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_GUIZHOU);
				if(ghel_guizhou==-999)
					ghel_guizhou = null;
				Float ghel_liaoning = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_LIAONING);
				if(ghel_liaoning==-999)
					ghel_liaoning = null;
				Float ghel_xizanghan = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_XIZANGHAN);
				if(ghel_xizanghan==-999)
					ghel_xizanghan = null;
				Float ghel_jilin = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_JILIN);
				if(ghel_jilin==-999)
					ghel_jilin = null;
				Float ghel_guangxi = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_GUANGXI);
				if(ghel_guangxi==-999)
					ghel_guangxi = null;
				Float ghel_zhejiang = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_ZHEJIANG);
				if(ghel_zhejiang==-999)
					ghel_zhejiang = null;
				Float ghel_chongqing = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_CHONGQING);
				if(ghel_chongqing==-999)
					ghel_chongqing = null;
				Float ghel_anhui = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_ANHUI);
				if(ghel_anhui==-999)
					ghel_anhui = null;
				Float ghel_heilongjiang = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_HEILONGJIANG);
				if(ghel_heilongjiang==-999)
					ghel_heilongjiang = null;
				Float ghel_jiangxi = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_JIANGXI);
				if(ghel_jiangxi==-999)
					ghel_jiangxi = null;
				Float ghel_sichuan = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_SICHUAN);
				if(ghel_sichuan==-999)
					ghel_sichuan = null;
				Float ghel_beijing = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_BEIJING);
				if(ghel_beijing==-999)
					ghel_beijing = null;
				Float ghel_henan = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_HENAN);
				if(ghel_henan==-999)
					ghel_henan = null;
				Float ghel_hunan = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_HUNAN);
				if(ghel_hunan==-999)
					ghel_hunan = null;
				Float ghel_shanghai = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_SHANGHAI);
				if(ghel_shanghai==-999)
					ghel_shanghai = null;
				Float ghel_fujian = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_FUJIAN);
				if(ghel_fujian==-999)
					ghel_fujian = null;
				Float ghel_shandong = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_SHANDONG);
				if(ghel_shandong==-999)
					ghel_shandong = null;
				Float ghel_hebei = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_HEBEI);
				if(ghel_hebei==-999)
					ghel_hebei = null;
				Float ghel_hubei = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_HUBEI);
				if(ghel_hubei==-999)
					ghel_hubei = null;
				Float ghel_guangdong = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_GUANGDONG);
				if(ghel_guangdong==-999)
					ghel_guangdong = null;
				Float ghel_jiangsu = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_JIANGSU);
				if(ghel_jiangsu==-999)
					ghel_jiangsu = null;
				Float ghel_shanxijin = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_SHANXIJIN);
				if(ghel_shanxijin==-999)
					ghel_shanxijin = null;
				Float ghel_neimenggu = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_NEIMENGGU);
				if(ghel_neimenggu==-999)
					ghel_neimenggu = null;
				Float ghel_qinghai = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_QINGHAI);
				if(ghel_qinghai==-999)
					ghel_qinghai = null;
				Float ghel_gansu = resultSet.getFloat(GeneralHighEnrollmentfileLineTable.GHEL_GANSU);
				if(ghel_gansu==-999)
					ghel_gansu = null;

				
				int ghel_serialnumber = resultSet.getInt(GeneralHighEnrollmentfileLineTable.GHEL_SERIALNUMBER);
				Date ghel_deadline = resultSet.getDate(GeneralHighEnrollmentfileLineTable.GHEL_DEADLINE);
				String ghel_comments = resultSet.getString(GeneralHighEnrollmentfileLineTable.GHEL_COMMENTS);
				String ghel_college = resultSet.getString(GeneralHighEnrollmentfileLineTable.GHEL_COLLEGE);
				int isnull = resultSet.getInt(GeneralHighEnrollmentfileLineTable.ISNULL);
				if(ghel_comments == null)
					ghel_comments = "";
				
				GeneralHighEnrollmentfileLine ghel = new GeneralHighEnrollmentfileLine(ghel_id, ghel_type,
						ghel_hainan, ghel_xinjiang, ghel_xizangshao,
						ghel_yunnan, ghel_shanxishan, ghel_tianjin,
						ghel_ningxia, ghel_guizhou, ghel_liaoning,
						ghel_xizanghan, ghel_jilin, ghel_guangxi,
						ghel_zhejiang, ghel_chongqing, ghel_anhui,
						ghel_heilongjiang, ghel_jiangxi, ghel_sichuan,
						ghel_beijing, ghel_henan, ghel_hunan,
						ghel_shanghai, ghel_fujian, ghel_shandong,
						ghel_hebei, ghel_hubei, ghel_guangdong,
						ghel_jiangsu, ghel_shanxijin, ghel_neimenggu,
						ghel_qinghai, ghel_gansu, ghel_serialnumber,
						ghel_deadline, ghel_college, ghel_comments, isnull);
				
				newBooksthatYearList.add(ghel);
			}
			return newBooksthatYearList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
	}
}
