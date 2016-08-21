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

import cn.edu.xmu.dao.TeachResearchUnitDao;
import cn.edu.xmu.daoimpl.TeachResearchUnitDaoImpl;
import cn.edu.xmu.table.TeachResearchUnitTable;

/*
 * 1-4 学校教学科研单位
 */
@WebServlet("/AddTeachResearchUnitServlet")
public class AddTeachResearchUnitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTeachResearchUnitServlet() {
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
		
		System.out.println(data);
		System.out.println(serialnumber);
		
		try {
			JSONObject json = new JSONObject(data);
			String tr_name = json.getString(TeachResearchUnitTable.TR_NAME);
			String tr_number = json.getString(TeachResearchUnitTable.TR_NUMBER);
			String respon_person = json.getString(TeachResearchUnitTable.TR_RESPONPERSON);
			//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
			int isnull = 0;
			if("".equals(tr_name)||"".equals(tr_number)||"".equals(respon_person))
				isnull = 1;
			
			TeachResearchUnitDao trd = new TeachResearchUnitDaoImpl();
			trd.addTeachResearchUnit(tr_name, tr_number, respon_person, serialnumber,isnull);
			out.print(true);
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
	}

}

