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

import cn.edu.xmu.dao.AdvancedIndividualDao;
import cn.edu.xmu.dao.SchoolNetDao;
import cn.edu.xmu.daoimpl.AdvancedIndividualDaoImpl;
import cn.edu.xmu.daoimpl.SchoolNetDaoimpl;
import cn.edu.xmu.entity.AdvancedIndividual;
import cn.edu.xmu.entity.SchoolNet;
import cn.edu.xmu.table.AdvancedIndividualTable;
import cn.edu.xmu.table.SchoolNetTable;

/**
 * Servlet implementation class Sec_AddAdvancedIndividualServlet
 */
@WebServlet("/Sec_AddAdvancedIndividualServlet")
public class Sec_AddAdvancedIndividualServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_AddAdvancedIndividualServlet() {
        super();
        // TODO Auto-generated constructor stub
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
		//解码
		String college = request.getParameter(AdvancedIndividualTable.AI_COLLEGE);
		college = URLDecoder.decode(college,"UTF-8");
		
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		try {
			JSONObject json = new JSONObject(data);
			
			String order = json.getString(AdvancedIndividualTable.AI_ORDER);
			int ai_order = -999;
			if(!order.equals(""))
				ai_order = Integer.valueOf(order);
			
			String ai_importcollege = json.getString(AdvancedIndividualTable.AI_IMPORTCOLLEGE);
			String ai_name = json.getString(AdvancedIndividualTable.AI_NAME);
			String ai_honoryear = json.getString(AdvancedIndividualTable.AI_HONORYEAR);
			
			//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
			int isnull = 0;
			if(order.equals("") || ai_importcollege.equals("") || ai_name.equals("") || 
					ai_honoryear.equals("")  )
				isnull = 1;
			
			int ai_serialnumber = serialnumber;
			String ai_college = college;
			String ai_comments = "";
			if(order.equals("") && ai_importcollege.equals("") && ai_name.equals("") && 
					ai_honoryear.equals("")  )
				return;
			AdvancedIndividual ai = new AdvancedIndividual(ai_order,
					ai_importcollege, ai_name,ai_honoryear, ai_serialnumber,ai_college,ai_comments,isnull);			
			AdvancedIndividualDao aiDao = new AdvancedIndividualDaoImpl();
			aiDao.addRecord(ai);
			out.print(true);
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
	}

}
