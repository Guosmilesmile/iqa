package cn.edu.xmu.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.dao.SlideNewsDao;
import cn.edu.xmu.entity.SlideNews;
import cn.edu.xmu.table.SlideNewsTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;

public class SlideNewsDaoImpl extends BaseDaoImpl<SlideNews> implements
		SlideNewsDao {

	@Override
	public int addSlideNewsRecord(SlideNews slidenews) {

		int result = 0;
		String sql = "insert into " + SlideNewsTable.TABLE_NAME + "("
				+ SlideNewsTable.SN_TITLES + "," + SlideNewsTable.SN_VERSION
				+ "," + SlideNewsTable.SN_CONTENT + ","
				+ SlideNewsTable.SN_IMGURL + ","
				+ SlideNewsTable.SN_PUBLISHTIME + ")values(?,?,?,?,?)";

		System.out.println("添加滚动新闻纪录的sql" + sql);
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
			pstmt.setString(1, slidenews.getSn_titles());
			pstmt.setString(2, slidenews.getSn_version());
			pstmt.setString(3, slidenews.getSn_content());
			pstmt.setString(4, slidenews.getSn_imgurl());
			pstmt.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			result = 0;
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, null);
		}
		return result;
	}

	/**
	 * @return 返回单条的滑动新闻
	 */
	@Override
	public SlideNews getSlideNewsRecordById(String value) {

		String sql = "select * from " + SlideNewsTable.TABLE_NAME + " where "
				+ SlideNewsTable.SN_ID + " = " + value;

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
				int sn_id = resultSet.getInt(SlideNewsTable.SN_ID);
				String sn_titles = resultSet
						.getString(SlideNewsTable.SN_TITLES);
				String sn_content = resultSet
						.getString(SlideNewsTable.SN_CONTENT);
				String sn_imgurl = resultSet
						.getString(SlideNewsTable.SN_IMGURL);
				Date sn_publishtime = resultSet
						.getTimestamp(SlideNewsTable.SN_PUBLISHTIME);
				SlideNews slideNews = new SlideNews(sn_id, sn_titles,
						sn_content, sn_imgurl, sn_publishtime);
				return slideNews;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return null;
	}

	@Override
	public int getSlideNewsCount(Map queryParams) {

		int count = 0;

		String sql = "select count(*) from " + SlideNewsTable.TABLE_NAME
				+ " where 1 = 1";
		for (Object object : queryParams.keySet()) {
			String key = object.toString();
			String value = queryParams.get(key).toString();
			sql += String.format(" and  %s = '%s' ", key, value);
		}
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
				count = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			count = -1;
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}

		return count;

	}

	@Override
	public List<SlideNews> findForPage(int start, int end, String sort,
			String order, Map queryParams) {

		String sql = " select tmp.* from ( " + " select * from "
				+ SlideNewsTable.TABLE_NAME + " where 1=1 ";
		for (Object object : queryParams.keySet()) {
			String key = object.toString();
			String value = queryParams.get(key).toString();
			sql += String.format(" and %s = '%s'", key, value);
		}
		sql += " order by " + sort + " " + order + " ) tmp limit " + start
				+ " ," + end;

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
		List<SlideNews> resultList = new ArrayList<SlideNews>();
		try {
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				int sn_id = resultSet.getInt(SlideNewsTable.SN_ID);
				String sn_titles = resultSet
						.getString(SlideNewsTable.SN_TITLES);
				String sn_content = resultSet
						.getString(SlideNewsTable.SN_CONTENT);
				String sn_imgurl = resultSet
						.getString(SlideNewsTable.SN_IMGURL);
				Date sn_publishtime = resultSet
						.getTimestamp(SlideNewsTable.SN_PUBLISHTIME);
				SlideNews slideNews = new SlideNews(sn_id, sn_titles,
						sn_content, sn_imgurl, sn_publishtime);
				resultList.add(slideNews);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return resultList;

	}

}
