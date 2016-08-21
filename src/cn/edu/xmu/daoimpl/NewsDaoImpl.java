package cn.edu.xmu.daoimpl;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.dao.NewsDao;
import cn.edu.xmu.entity.News;
import cn.edu.xmu.enums.NewsType;
import cn.edu.xmu.table.NewsTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;

public class NewsDaoImpl extends BaseDaoImpl<News> implements NewsDao {

	public int addNewsRecord(News news) {
		int result = 0;

		String sql = "insert into " + NewsTable.TABLE_NAME + "("
				+ NewsTable.N_TITLES + "," + NewsTable.N_AUTHOR + ","
				+ NewsTable.N_SUMMARY + "," + NewsTable.N_CONTENT + ","
				+ NewsTable.N_IMGURL + "," + NewsTable.N_PUBLISHTIME + ","
				+ NewsTable.N_CLASS + "," + NewsTable.N_SUB_CLASS + ","
				+ NewsTable.N_VERSION + ")values(?,?,?,?,?,?,?,?,?)";

		System.out.println("添加新闻纪录" + sql);
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
			pstmt.setString(1, news.getN_titles());
			pstmt.setString(2, news.getN_author());
			pstmt.setString(3, news.getN_summary());
			pstmt.setString(4, news.getN_content());
			pstmt.setString(5, news.getN_imgurl());
			pstmt.setTimestamp(
					6,
					new Timestamp(null == news.getN_publishtime() ? System
							.currentTimeMillis() : news.getN_publishtime()
							.getTime()));
			pstmt.setString(7, news.getN_class());
			pstmt.setString(8, news.getN_subclass());
			pstmt.setString(9, news.getN_version());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			result = -1;
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, null);
		}

		return result;
	}

	@Override
	public int getNewsCount(Map queryParams) {
		int count = 0;

		String sql = "select count(*) from " + NewsTable.TABLE_NAME
				+ " where 1 = 1";
		for (Object object : queryParams.keySet()) {
			String key = object.toString();
			String value = queryParams.get(key).toString();
			sql += String.format(" and  %s = '%s' ", key, value);
		}
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
				count = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			count = -1;
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}

		System.out.println(count);
		return count;
	}

	@Override
	public List<News> findForPage(int start, int end, String sort,
			String order, Map queryParams) {
		String sql = " select tmp.* from ( " + " select * from "
				+ NewsTable.TABLE_NAME + " where 1=1 ";
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
		List<News> resultList = new ArrayList<News>();
		try {
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				int n_id = resultSet.getInt(NewsTable.N_ID);
				String n_titles = resultSet.getString(NewsTable.N_TITLES);
				String n_author = resultSet.getString(NewsTable.N_AUTHOR);
				String n_summary = resultSet.getString(NewsTable.N_SUMMARY);
				String n_content = resultSet.getString(NewsTable.N_CONTENT);
				String n_imgurl = resultSet.getString(NewsTable.N_IMGURL);

				Date n_publishtime = resultSet.getDate(NewsTable.N_PUBLISHTIME);
				String n_class = resultSet.getString(NewsTable.N_CLASS);
				String n_subclass = resultSet.getString(NewsTable.N_SUB_CLASS);
				String n_version = resultSet.getString(NewsTable.N_VERSION);
				News news = new News(n_id, n_titles, n_author, n_summary,
						n_content, n_imgurl, n_publishtime, n_class,
						n_subclass, n_version);
				resultList.add(news);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return resultList;
	}

	@Override
	public int getNewsCountCH2Query(Map queryParams) {
		int count = 0;

		String sql = "select count(*) from " + NewsTable.TABLE_NAME
				+ " where 1 = 1";
		for (Object object : queryParams.keySet()) {
			String key = object.toString();
			String value = queryParams.get(key).toString();
			sql += String.format(" and  %s like '%%%s%%' ", key, value);
		}
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
				count = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			count = -1;
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}

		System.out.println(count);
		return count;
	}

	@Override
	public List<News> findForPageCH2Query(int start, int end, String sort,
			String order, Map queryParams) {
		String sql = " select tmp.* from ( " + " select * from "
				+ NewsTable.TABLE_NAME + " where 1=1 ";
		for (Object object : queryParams.keySet()) {
			String key = object.toString();
			String value = queryParams.get(key).toString();
			sql += String.format(" and %s like '%%%s%%'", key, value);
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
		List<News> resultList = new ArrayList<News>();
		try {
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				int n_id = resultSet.getInt(NewsTable.N_ID);
				String n_titles = resultSet.getString(NewsTable.N_TITLES);
				String n_author = resultSet.getString(NewsTable.N_AUTHOR);
				String n_summary = resultSet.getString(NewsTable.N_SUMMARY);
				String n_content = resultSet.getString(NewsTable.N_CONTENT);
				String n_imgurl = resultSet.getString(NewsTable.N_IMGURL);

				Date n_publishtime = resultSet.getDate(NewsTable.N_PUBLISHTIME);
				String n_class = resultSet.getString(NewsTable.N_CLASS);
				String n_subclass = resultSet.getString(NewsTable.N_SUB_CLASS);
				String n_version = resultSet.getString(NewsTable.N_VERSION);
				News news = new News(n_id, n_titles, n_author, n_summary,
						n_content, n_imgurl, n_publishtime, n_class,
						n_subclass, n_version);
				resultList.add(news);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return resultList;
	}
	
	@Override
	public News getNewsRecordById(String value) {

		String sql = "select * from " + NewsTable.TABLE_NAME + " where "
				+ NewsTable.N_ID + "=" + value;

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

				int n_id = resultSet.getInt(NewsTable.N_ID);
				String n_titles = resultSet.getString(NewsTable.N_TITLES);
				String n_author = resultSet.getString(NewsTable.N_AUTHOR);
				String n_summary = resultSet.getString(NewsTable.N_SUMMARY);
				String n_content = resultSet.getString(NewsTable.N_CONTENT);
				String n_imgurl = resultSet.getString(NewsTable.N_IMGURL);

				Timestamp n_time = resultSet
						.getTimestamp(NewsTable.N_PUBLISHTIME);
				String n_class = resultSet.getString(NewsTable.N_CLASS);
				String n_subclass = resultSet.getString(NewsTable.N_SUB_CLASS);
				String n_version = resultSet.getString(NewsTable.N_VERSION);

				News news = new News(n_id, n_titles, n_author, n_summary,
						n_content, n_imgurl, n_time, n_class, n_subclass,
						n_version);
				return news;
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
	public Map<NewsType, Integer> getNumOfEachType() {
		Map<NewsType, Integer> numOfEachTypeMap = new HashMap<NewsType, Integer>();
		List<NewsType> typeList = new ArrayList<NewsType>();
		typeList.add(NewsType.学习调查);
		typeList.add(NewsType.教学状态);
		typeList.add(NewsType.教学研究);
		typeList.add(NewsType.新闻);
		typeList.add(NewsType.本科评估);
		typeList.add(NewsType.简介);
		typeList.add(NewsType.课堂听课);
		typeList.add(NewsType.课程评价);

		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		String sql = null;
		//int total = 0;

		for (int i = 0; i < typeList.size(); i++) {
			try {
				sql = "select * from "
						+ NewsTable.TABLE_NAME
						+ " where "
						+ NewsTable.N_CLASS
						+ "='"
						+ new String(typeList.get(i).toString()
								.getBytes(), "utf-8")+"'";
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("SQL:" + sql);
			try {
				pstmt = connection.prepareStatement(sql);
				resultSet = pstmt.executeQuery();
				resultSet.last();
				numOfEachTypeMap.put(typeList.get(i),resultSet.getRow());
				//total += resultSet.getRow();
				System.out.println(resultSet.getRow());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				JdbcUtils_DBCP.release(connection, pstmt, resultSet);
			}
		}
		return numOfEachTypeMap;
	}
}
