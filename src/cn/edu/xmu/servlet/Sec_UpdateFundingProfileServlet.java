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

import cn.edu.xmu.dao.FundingProfileDao;
import cn.edu.xmu.daoimpl.FundingProfileDaoimpl;
import cn.edu.xmu.table.FundingProfileTable;

/**
 * Servlet implementation class Sec_UpdateFundingProfileServlet
 */
@WebServlet("/Sec_UpdateFundingProfileServlet")
public class Sec_UpdateFundingProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sec_UpdateFundingProfileServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
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
		data = URLDecoder.decode(data, "UTF-8");
		data = data.substring(1, data.length() - 1);
		try {
			if (patter != null && "batch".equals(patter)) {
				System.out.println("批量更新");
				// 审核部分更新，可以批量
				data = "[" + data + "]";
				JSONArray json = new JSONArray(data);
				for (int i = 0; i < json.length(); i++) {
					String fp_id = json.getJSONObject(i).getString(
							FundingProfileTable.FP_ID);
					
					String fp_comments = json.getJSONObject(i).getString(
							FundingProfileTable.FP_COMMENTS);
					Map<String, String> params = new HashMap<String, String>();
					
					params.put(FundingProfileTable.FP_COMMENTS, fp_comments);

					FundingProfileDao fpDao = new FundingProfileDaoimpl();
					int result = fpDao.alterFundingProfile(params, fp_id);

					out.print(result);
				}
			} else {
				JSONObject json = new JSONObject(data);
				System.out.println(json);
					String fp_id = json.getString(FundingProfileTable.FP_ID);
					String fp_educationfund = json
							.getString(FundingProfileTable.FP_EDUCATIONFUND);
					if(fp_educationfund.equals(""))
						fp_educationfund = "-999";
					
					String fp_teachfund = json
							.getString(FundingProfileTable.FP_TEACHFUND);
					if(fp_teachfund.equals(""))
						fp_teachfund = "-999";
					
					String fp_reformfund = json
							.getString(FundingProfileTable.FP_REFORMFUND);
					if(fp_reformfund.equals(""))
						fp_reformfund = "-999";
					//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
					int isnull = 0;
					if(fp_educationfund.equals("-999") || fp_teachfund.equals("-999") || fp_reformfund.equals("-999")  )
						isnull = 1;
					
					Map<String, String> params = new HashMap<String, String>();
					params.put(FundingProfileTable.FP_EDUCATIONFUND,
							fp_educationfund);
					params.put(FundingProfileTable.FP_TEACHFUND,
							fp_teachfund);
					params.put(FundingProfileTable.FP_REFORMFUND,
							fp_reformfund);
					params.put(FundingProfileTable.ISNULL,
							isnull+"");
					FundingProfileDao fpDao = new FundingProfileDaoimpl();
					int result = fpDao.alterFundingProfile(params, fp_id);
					out.print(result);
				
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

}
