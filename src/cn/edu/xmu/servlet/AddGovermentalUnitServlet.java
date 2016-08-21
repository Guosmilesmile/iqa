package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import cn.edu.xmu.dao.GovermentalUnitDao;
import cn.edu.xmu.daoimpl.GovermentalUnitDaoImpl;
import cn.edu.xmu.table.GovermentalUnitTable;

/*
 * 1-3 添加行政单位
 */
@WebServlet("/AddGovermentalUnitServlet")
public class AddGovermentalUnitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddGovermentalUnitServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;Charset=UTF-8");
		PrintWriter out = response.getWriter();

		String data = request.getParameter("rowdata");
		int serialnumber  = Integer.parseInt(request.getParameter("serialnumber"));
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		
		
		try {
			JSONObject json = new JSONObject(data);
			
			
			String gu_name = json.getString(GovermentalUnitTable.GU_NAME);
			String gu_number = json.getString(GovermentalUnitTable.GU_NUMBER);
			String gu_responsibility = json.getString(GovermentalUnitTable.GU_RESPONSIBILITY);
			String respon_person = json.getString(GovermentalUnitTable.GU_RESPONPERSON);
			//String college = json.getString(SuperMajorTable.COLLEGE);
			//SuperMajor sm = new SuperMajor(gu_name, gu_number, gu_responsibility, respon_person, serialnumber);			
			
			GovermentalUnitDao gud = new GovermentalUnitDaoImpl();
			gud.addGovermentalUnit(gu_name, gu_number, gu_responsibility, respon_person, serialnumber);
			
			out.print(true);
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
	}

}

