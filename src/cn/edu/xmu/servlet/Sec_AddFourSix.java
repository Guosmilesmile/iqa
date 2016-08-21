package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.edu.xmu.dao.FourSixDao;
import cn.edu.xmu.daoimpl.FourSixDaoImpl;
import cn.edu.xmu.entity.FourSix;
import cn.edu.xmu.table.FourSixTable;

/**
 * Servlet implementation class Sec_AddSchoolExecutiveUnit
 */
@WebServlet("/Sec_AddFourSix")
public class Sec_AddFourSix extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sec_AddFourSix() {
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
		String college = request.getParameter(FourSixTable.FX_COLLEGE);
		college = URLDecoder.decode(college, "UTF-8");

		data = URLDecoder.decode(data, "UTF-8");
		data = data.substring(1, data.length() - 1);
		try {
			JSONObject json = new JSONObject(data);
			int fx_total = 0;
			int fx_attend = 0;
			int fx_attendcount = 0;
			int fx_pass = 0;
			double fx_passpercent = 0;
			int fx_good = 0;
			double fx_goodpercent = 0;

			String fx_colleges = json.getString(FourSixTable.FX_COLLEGES);
			String fx_department = json.getString(FourSixTable.FX_DEAPERMENT);
			String fx_major = json.getString(FourSixTable.FX_MAJOR);
			String fx_grade = json.getString(FourSixTable.FX_GRADE);
			String fx_level = json.getString(FourSixTable.FX_LEVEL);
			String total = json.getString(FourSixTable.FX_TOTAL);
			if (total.equals(""))
				fx_total = 0;
			else {
				fx_total = Integer.parseInt(total);
			}

			String attend = json.getString(FourSixTable.FX_ATTEND);
			if (attend.equals(""))
				fx_attend = 0;
			else {
				fx_attend = Integer.parseInt(attend);
			}

			String attendcount = json.getString(FourSixTable.FX_ATTENDCOUNT);
			if (attendcount.equals(""))
				fx_attendcount = 0;
			else {
				fx_attendcount = Integer.parseInt(attendcount);
			}
			
			String pass = json.getString(FourSixTable.FX_PASS);
			if (pass.equals(""))
				fx_pass = 0;
			else {
				fx_pass = Integer.parseInt(pass);
			}

			String passpercent = json.getString(FourSixTable.FX_PASSPERCENT);
			if (passpercent.equals(""))
				fx_passpercent = 0;
			else {
				fx_passpercent = Double.parseDouble(passpercent);
			}
			String good = json.getString(FourSixTable.FX_GOOD);
			if (good.equals(""))
				fx_good = 0;
			else {
				fx_good = Integer.parseInt(good);
			}

			String goodpercent = json.getString(FourSixTable.FX_GOODPERCENT);
			if (goodpercent.equals(""))
				fx_goodpercent = 0;
			else {
				fx_goodpercent = Double.parseDouble(goodpercent);
			}

			int isnull = 0;
			if ("".equals(fx_colleges) || "".equals(fx_department)
					|| "".equals(fx_major) || "".equals(fx_grade)
					|| "".equals(fx_level) || "".equals(total)
					|| "".equals(attend) || "".equals(attendcount)
					|| "".equals(pass) || "".equals(passpercent)
					|| "".equals(good) || "".equals(goodpercent))
				isnull = 1;
			String fx_comments = "";

			FourSix fx = new FourSix(fx_colleges, fx_department, fx_major, fx_grade, fx_level,
					 fx_total, fx_attend, fx_attendcount, fx_pass, fx_passpercent,
					 fx_good, fx_goodpercent, serialnumber, college,
					 fx_comments,isnull);

			FourSixDao apDao = new FourSixDaoImpl();
			apDao.addRecord(fx);

			out.print(true);
		} catch (JSONException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}

	}

}
