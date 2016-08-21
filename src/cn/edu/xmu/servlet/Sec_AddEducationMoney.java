package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

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
import cn.edu.xmu.entity.EducationMoney;
import cn.edu.xmu.table.EducationMoneyTable;

/**
 * Servlet implementation class Sec_AddSchoolExecutiveUnit
 */
@WebServlet("/Sec_AddEducationMoney")
public class Sec_AddEducationMoney extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sec_AddEducationMoney() {
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
		int serialnumber = Integer.parseInt(request
				.getParameter("serialnumber"));
		// 解码
		String college = request.getParameter(EducationMoneyTable.EM_COLLEGE);
		college = URLDecoder.decode(college, "UTF-8");

		data = URLDecoder.decode(data, "UTF-8");
		data = data.substring(1, data.length() - 1);
		try {
			JSONObject json = new JSONObject(data);

			String em_colleges=json.getString(EducationMoneyTable.EM_COLLEGES);
			String intotal=json.getString(EducationMoneyTable.EM_INTOTAL);
			String inschoolfunds=json.getString(EducationMoneyTable.EM_INSCHOOLFUNDS);
			String inchange=json.getString(EducationMoneyTable.EM_INCHANGE);
			String instudentactivity=json.getString(EducationMoneyTable.EM_INSTUDENTACTIVITY);
			String inbuy=json.getString(EducationMoneyTable.EM_INBUY);
			String inselfraise=json.getString(EducationMoneyTable.EM_INSELFRAISE);
			String indonations=json.getString(EducationMoneyTable.EM_INDONATIONS);
			String outtotal=json.getString(EducationMoneyTable.EM_OUTTOTAL);
			String outdaily=json.getString(EducationMoneyTable.EM_OUTDAILY);
			String outchange=json.getString(EducationMoneyTable.EM_OUTCHANGE);
			String outbuild=json.getString(EducationMoneyTable.EM_OUTBUILD);
			String outpractice=json.getString(EducationMoneyTable.EM_OUTPRACTICE);
			String outpracticeexperiment=json.getString(EducationMoneyTable.EM_OUTPRACTICEEXPERIMENT);
			String outpracticeinter=json.getString(EducationMoneyTable.EM_OUTPRACTICEINTER);
			String outother=json.getString(EducationMoneyTable.EM_OUTOTHER);
			String outstudentactivity=json.getString(EducationMoneyTable.EM_OUTSTUDENTACTIVITY);
			String outteacher=json.getString(EducationMoneyTable.EM_OUTTEACHER);
			String em_comments="";
			int isnull=0;
			if("".equals(intotal)||"".equals(inschoolfunds)||"".equals(inchange)||"".equals(instudentactivity)||"".equals(inbuy)||"".equals(inselfraise)||"".equals(indonations)||"".equals(outtotal)||"".equals(outdaily)
					||"".equals(outchange)||"".equals(outbuild)||"".equals(outpractice)||"".equals(outpracticeexperiment)||"".equals(outpracticeinter)||"".equals(outother)||"".equals(outstudentactivity)||"".equals(outteacher)){
				isnull = 1;
			}
			if("".equals(intotal)&&"".equals(inschoolfunds)&&"".equals(inchange)&&"".equals(instudentactivity)&&"".equals(inbuy)&&"".equals(inselfraise)&&"".equals(indonations)&&"".equals(outtotal)&&"".equals(outdaily)
					&&"".equals(outchange)&&"".equals(outbuild)&&"".equals(outpractice)&&"".equals(outpracticeexperiment)&&"".equals(outpracticeinter)&&"".equals(outother)&&"".equals(outstudentactivity)&&"".equals(outteacher)){
				out.println(false);
				return;
			}
			float em_intotal = -999;
			if(!"".equals(intotal))
				em_intotal = Float.parseFloat(intotal);
			float em_inschoolfunds = -999;
			if(!"".equals(inschoolfunds))
				em_inschoolfunds= Float.parseFloat(inschoolfunds); 
			float em_inchange = -999;
			if(!"".equals(inchange))
				em_inchange= Float.parseFloat(inchange);
			
			float em_instudentactivity = -999;
			if(!"".equals(instudentactivity))
				em_instudentactivity =  Float.parseFloat(instudentactivity);
			
			
			float em_inbuy = -999;
			if(!"".equals(inbuy))
				em_inbuy = Float.parseFloat(inbuy);
			
			float em_inselfraise = -999;
			if(!"".equals(inselfraise))
				em_inselfraise = Float.parseFloat(inselfraise);
			
			float em_indonations =-999 ;
			if(!"".equals(indonations))
				em_indonations = Float.parseFloat(indonations);
					
			float em_outother=-999;
			if(!"".equals(outother))
				em_outother= Float.parseFloat(outother);
			
			
			float  em_outstudentactivity=-999;
			if(!"".equals(outstudentactivity))
				em_outstudentactivity= Float.parseFloat(outstudentactivity);
			
			float em_outtotal = -999;
			if(!"".equals(outtotal))
				em_outtotal = Float.parseFloat(outtotal);
			
			float em_outdaily = -999; 
			if(!"".equals(outdaily))
				em_outdaily = Float.parseFloat(outdaily);
			
			float em_outchange = -999;
			if(!"".equals(outchange))
				em_outchange =Float.parseFloat(outchange);
			
			float em_outbuild =-999;
			if(!"".equals(outbuild))
				em_outbuild = Float.parseFloat(outbuild);
			
			float em_outpractice = -999; 
			if(!"".equals(outpractice))
				em_outpractice = Float.parseFloat(outpractice);
			
			
			float em_outpracticeexperiment = -999; 
			if(!"".equals(outpracticeexperiment))
				em_outpracticeexperiment = Float.parseFloat(outpracticeexperiment);
			
			float em_outpracticeinter = -999; 
			if(!"".equals(outpracticeinter))
				em_outpracticeinter = Float.parseFloat(outpracticeinter);
			
			
			float em_outteacher=-999;
			if(!"".equals(outteacher))
			em_outteacher= Float.parseFloat(outteacher);
			EducationMoney em = new EducationMoney(em_colleges,
					em_intotal, em_inschoolfunds, em_inchange,
					em_instudentactivity, em_inbuy, em_inselfraise,
					em_indonations, em_outtotal, em_outdaily, em_outchange,
					em_outbuild, em_outpractice, em_outpracticeexperiment,
					em_outpracticeinter , em_outother, em_outstudentactivity,
					em_outteacher, serialnumber, college, em_comments,isnull);

			EducationMoneyDao emDao = new EducationMoneyDaoImpl();
			emDao.addRecord(em);

			out.print(true);
		} catch (JSONException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}

	}
}
