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

import cn.edu.xmu.dao.MajorTeachDao;
import cn.edu.xmu.daoimpl.MajorTeachDaoImpl;
import cn.edu.xmu.entity.MajorTeach;
import cn.edu.xmu.table.MajorTeachTable;

/**
 * Servlet implementation class Sec_AddMajorTeachServlet
 */
@WebServlet("/Sec_AddMajorTeachServlet")
public class Sec_AddMajorTeachServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_AddMajorTeachServlet() {
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
		String college = request.getParameter(MajorTeachTable.MT_COLLEGE);
		college = URLDecoder.decode(college,"UTF-8");
		
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		try {
			JSONObject json = new JSONObject(data);
			
			String mt_majornameinschool = json.getString(MajorTeachTable.MT_MAJORNAMEINSCHOOL);
			String mt_majorcodeinschool = json.getString(MajorTeachTable.MT_MAJORCODEINSCHOOL);
			String mt_coursecode = json.getString(MajorTeachTable.MT_COURSECODE);
			String mt_coursenature = json.getString(MajorTeachTable.MT_COURSENATURE);
			String credits = json.getString(MajorTeachTable.MT_CREDITS);
			float mt_credits = -999;
			if (credits!=null && !"".equals(credits)) {
				mt_credits = Float.parseFloat(credits);
			}
			
			String mt_grade = json.getString(MajorTeachTable.MT_GRADE);
			
			//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
			int isnull = 0;
			if(mt_majornameinschool.equals("") || mt_majorcodeinschool.equals("") || mt_coursecode.equals("") || 
					mt_coursenature.equals("") || credits.equals("") || mt_grade.equals("")  )
				isnull = 1;
			
			int mt_serialnumber = serialnumber;
			String mt_college = college;
			String mt_comments = "";
			
			if(mt_majornameinschool.equals("") && mt_majorcodeinschool.equals("") && mt_coursecode.equals("") || 
					mt_coursenature.equals("") && credits.equals("") && mt_grade.equals("")  )
				return;
			MajorTeach mt = new MajorTeach(mt_majornameinschool,
					mt_majorcodeinschool, mt_coursecode, mt_coursenature,mt_credits,mt_grade,mt_serialnumber,mt_college,mt_comments,isnull);			
			MajorTeachDao mtDao = new MajorTeachDaoImpl();
			mtDao.addRecord(mt);
			out.print(true);
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
	}

}
