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

import cn.edu.xmu.dao.EducationMoneyDao;
import cn.edu.xmu.daoimpl.EducationMoneyDaoImpl;
import cn.edu.xmu.table.EducationMoneyTable;

/*
 */
@WebServlet("/Sec_UpdateEducationMoney")
public class Sec_UpdateEducationMoney extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sec_UpdateEducationMoney() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
			EducationMoneyDao emdao = new EducationMoneyDaoImpl();
			if (patter != null && "batch".equals(patter)) {
				System.out.println("批量更新");
				// 审核部分更新，可以批量
				data = "[" + data + "]";
				JSONArray jsons = new JSONArray(data);

				for (int i = 0; i < jsons.length(); i++) {
					Map<String, String> params = new HashMap<String, String>();
					JSONObject json = jsons.getJSONObject(i);

					String em_id = json.getString(EducationMoneyTable.EM_ID);
					String em_comments = json.getString(EducationMoneyTable.EM_COMMENTS);
					params.put(EducationMoneyTable.EM_COMMENTS, em_comments);

					int result = emdao.alterEducationMoney(params, em_id);

					out.print(result);
				}
			} else {
				JSONObject json = new JSONObject(data);
				System.out.println(json);
				Map<String, String> params = new HashMap<String, String>();

				String em_id = json.getString(EducationMoneyTable.EM_ID);

				String em_colleges = json.getString(EducationMoneyTable.EM_COLLEGES);
				String em_intotal = json.getString(EducationMoneyTable.EM_INTOTAL);
				String em_inschoolfunds = json.getString(EducationMoneyTable.EM_INSCHOOLFUNDS);
				String em_inchange = json.getString(EducationMoneyTable.EM_INCHANGE);
				String em_instudentactivity = json
						.getString(EducationMoneyTable.EM_INSTUDENTACTIVITY);
				String em_inbuy = json.getString(EducationMoneyTable.EM_INBUY);
				String em_inselfraise = json.getString(EducationMoneyTable.EM_INSELFRAISE);
				String em_indonations = json.getString(EducationMoneyTable.EM_INDONATIONS);
				String em_outtotal = json.getString(EducationMoneyTable.EM_OUTTOTAL);
				String em_outdaily = json.getString(EducationMoneyTable.EM_OUTDAILY);
				String em_outchange = json.getString(EducationMoneyTable.EM_OUTCHANGE);
				String em_outbuild = json.getString(EducationMoneyTable.EM_OUTBUILD);
				String em_outpractice = json.getString(EducationMoneyTable.EM_OUTPRACTICE);
				String em_outpracticeexperiment = json
						.getString(EducationMoneyTable.EM_OUTPRACTICEEXPERIMENT);
				String em_outpracticeinter = json
						.getString(EducationMoneyTable.EM_OUTPRACTICEINTER);
				String em_outother = json.getString(EducationMoneyTable.EM_OUTOTHER);
				String em_outstudentactivity = json
						.getString(EducationMoneyTable.EM_OUTSTUDENTACTIVITY);
				String em_outteacher = json.getString(EducationMoneyTable.EM_OUTTEACHER);
				int isnull = 0;
				if ("".equals(em_intotal) || "".equals(em_inschoolfunds) || "".equals(em_inchange)
						|| "".equals(em_instudentactivity) || "".equals(em_inbuy)
						|| "".equals(em_inselfraise) || "".equals(em_indonations)
						|| "".equals(em_outtotal) || "".equals(em_outdaily)
						|| "".equals(em_outchange) || "".equals(em_outbuild)
						|| "".equals(em_outpractice) || "".equals(em_outpracticeexperiment)
						|| "".equals(em_outpracticeinter) || "".equals(em_outother)
						|| "".equals(em_outstudentactivity) || "".equals(em_outteacher)) {
					isnull = 1;
				}

				if ("".equals(em_intotal))
					em_intotal = "-999";
				if ("".equals(em_inschoolfunds))
					em_inschoolfunds = "-999";
				if ("".equals(em_inchange))
					em_inchange = "-999";
				if ("".equals(em_instudentactivity))
					em_instudentactivity = "-999";
				if ("".equals(em_inbuy))
					em_inbuy = "-999";
				if ("".equals(em_inselfraise))
					em_inselfraise = "-999";
				if ("".equals(em_indonations))
					em_indonations = "-999";
				em_outother = "-999";
				if ("".equals(em_outstudentactivity))
					em_outstudentactivity = "-999";
				if ("".equals(em_outtotal))
					em_outtotal = "-999";
				if ("".equals(em_outdaily))
					em_outdaily = "-999";
				if ("".equals(em_outchange))
					em_outchange = "-999";
				if ("".equals(em_outbuild))
					em_outbuild = "-999";
				if ("".equals(em_outpractice))
					em_outpractice = "-999";
				if ("".equals(em_outpracticeexperiment))
					em_outpracticeexperiment = "-999";
				if ("".equals(em_outpracticeinter))
					em_outpracticeinter = "-999";
				if ("".equals(em_outteacher))
					em_outteacher = "-999";
				params.put(EducationMoneyTable.EM_COLLEGES, em_colleges);
				params.put(EducationMoneyTable.EM_INTOTAL, em_intotal);
				params.put(EducationMoneyTable.EM_INSCHOOLFUNDS, em_inschoolfunds);
				params.put(EducationMoneyTable.EM_INCHANGE, em_inchange);
				params.put(EducationMoneyTable.EM_INSTUDENTACTIVITY, em_instudentactivity);
				params.put(EducationMoneyTable.EM_INBUY, em_inbuy);
				params.put(EducationMoneyTable.EM_INSELFRAISE, em_inselfraise);
				params.put(EducationMoneyTable.EM_INDONATIONS, em_indonations);
				params.put(EducationMoneyTable.EM_OUTTOTAL, em_outtotal);
				params.put(EducationMoneyTable.EM_OUTDAILY, em_outdaily);
				params.put(EducationMoneyTable.EM_OUTCHANGE, em_outchange);
				params.put(EducationMoneyTable.EM_OUTBUILD, em_outbuild);
				params.put(EducationMoneyTable.EM_OUTPRACTICE, em_outpractice);
				params.put(EducationMoneyTable.EM_OUTPRACTICEEXPERIMENT, em_outpracticeexperiment);
				params.put(EducationMoneyTable.EM_OUTPRACTICEINTER, em_outpracticeinter);
				params.put(EducationMoneyTable.EM_OUTOTHER, em_outother);
				params.put(EducationMoneyTable.EM_OUTSTUDENTACTIVITY, em_outstudentactivity);
				params.put(EducationMoneyTable.EM_OUTTEACHER, em_outteacher);
				params.put("isnull", isnull + "");

				int result = emdao.alterEducationMoney(params, em_id);

				out.print(result);
			}

		} catch (JSONException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}

	}

}
