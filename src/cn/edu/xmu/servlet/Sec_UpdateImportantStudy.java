package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.edu.xmu.dao.ImportantStudyDao;
import cn.edu.xmu.dao.MajorInfoDao;
import cn.edu.xmu.daoimpl.ForeignExchangeDaoImpl;
import cn.edu.xmu.daoimpl.ImportantStudyDaoImpl;
import cn.edu.xmu.daoimpl.MajorInfoDaoImpl;
import cn.edu.xmu.entity.ImportantStudy;
import cn.edu.xmu.table.ForeignExchangeTable;
import cn.edu.xmu.table.ImportantStudyTable;
import cn.edu.xmu.table.MajorInfoTable;

/*
 * 4-2-2-1 专业基本情况
 */
@WebServlet("/Sec_UpdateImportantStudy")
public class Sec_UpdateImportantStudy extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sec_UpdateImportantStudy() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;Charset=UTF-8");
		PrintWriter out = response.getWriter();

		String data = request.getParameter("rowdata");
		String patter = request.getParameter("patter");
		// int serialnumber =
		// Integer.parseInt(request.getParameter("serialnumber"));
		data = URLDecoder.decode(data, "UTF-8");
		data = data.substring(1, data.length() - 1);

		try {
			if (patter != null && "batch".equals(patter)) {
				System.out.println("批量更新");
				// 审核部分更新，可以批量
				data = "[" + data + "]";
				JSONArray jsons = new JSONArray(data);

				for (int i = 0; i < jsons.length(); i++) {
					JSONObject json = jsons.getJSONObject(i);
					
					ImportantStudyDao importantStudyDao = new ImportantStudyDaoImpl();
					String ip_id = json.getString(ImportantStudyTable.IP_ID);
					int ip_studynumber = json.getInt(ImportantStudyTable.IP_STUDYNUMBER);
					String ip_studyname =json.getString(ImportantStudyTable.IP_STUDYNAME);
					String ip_departmentnumber=json.getString(ImportantStudyTable.IP_DEPARTMENTNUMBER);
					String ip_departmentname=json.getString(ImportantStudyTable.IP_DEPARTMENTNAME);
					String ip_category =json.getString(ImportantStudyTable.IP_CATEGORY);
					String ip_level=json.getString(ImportantStudyTable.IP_LEVEL);
					int ip_serialnumber =json.getInt(ImportantStudyTable.IP_SERIALNUMBER);
					String ip_comments =json.getString(ImportantStudyTable.IP_COMMENTS);
					
					Map<String, String> valueMap = new HashMap<String, String>();
					valueMap.put(ImportantStudyTable.IP_STUDYNUMBER, ip_studynumber+"");
					valueMap.put(ImportantStudyTable.IP_STUDYNAME, ip_studyname);
					valueMap.put(ImportantStudyTable.IP_DEPARTMENTNAME,ip_departmentname);
					valueMap.put(ImportantStudyTable.IP_DEPARTMENTNUMBER, ip_departmentnumber);
					valueMap.put(ImportantStudyTable.IP_CATEGORY, ip_category);
					valueMap.put(ImportantStudyTable.IP_LEVEL,ip_level);
					valueMap.put(ImportantStudyTable.IP_SERIALNUMBER,ip_serialnumber+"");
					valueMap.put(ImportantStudyTable.IP_COMMENTS, ip_comments);
					int result = importantStudyDao.alterImportantStudy(valueMap , ip_id);

					out.print(result);
				}
			} else {
				JSONObject json = new JSONObject(data);
				System.out.println(json);
				ImportantStudyDao importantStudyDao = new ImportantStudyDaoImpl();
				String ip_id = json.getString(ImportantStudyTable.IP_ID);
				String ip_studynumber = json.getString(ImportantStudyTable.IP_STUDYNUMBER);
				String ip_studyname =json.getString(ImportantStudyTable.IP_STUDYNAME);
				String ip_departmentnumber=json.getString(ImportantStudyTable.IP_DEPARTMENTNUMBER);
				String ip_departmentname=json.getString(ImportantStudyTable.IP_DEPARTMENTNAME);
				String ip_category =json.getString(ImportantStudyTable.IP_CATEGORY);
				String ip_level=json.getString(ImportantStudyTable.IP_LEVEL);
				int ip_serialnumber =json.getInt(ImportantStudyTable.IP_SERIALNUMBER);
				String ip_comments =json.getString(ImportantStudyTable.IP_COMMENTS);
				
				int isnull = 0;
				if ("".equals(ip_studynumber) || "".equals(ip_studyname)
						|| "".equals(ip_departmentnumber)
						|| "".equals(ip_departmentname) || "".equals(ip_category)
						|| "".equals(ip_level)) {
					isnull = 1;
				}
				
				
				Map<String, String> valueMap = new HashMap<String, String>();
				valueMap.put(ImportantStudyTable.IP_STUDYNUMBER, ip_studynumber+"");
				valueMap.put(ImportantStudyTable.IP_STUDYNAME, ip_studyname);
				valueMap.put(ImportantStudyTable.IP_DEPARTMENTNAME,ip_departmentname);
				valueMap.put(ImportantStudyTable.IP_DEPARTMENTNUMBER, ip_departmentnumber);
				valueMap.put(ImportantStudyTable.IP_CATEGORY, ip_category);
				valueMap.put(ImportantStudyTable.IP_LEVEL,ip_level);
				valueMap.put(ImportantStudyTable.IP_SERIALNUMBER,ip_serialnumber+"");
				valueMap.put(ImportantStudyTable.IP_COMMENTS, ip_comments);
				valueMap.put("isnull", isnull+"");
				int result = importantStudyDao.alterImportantStudy(valueMap , ip_id);

				out.print(result);
			}

		} catch (JSONException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}

	}

}
