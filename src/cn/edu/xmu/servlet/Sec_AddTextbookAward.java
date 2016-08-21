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

import cn.edu.xmu.dao.TextbookAwardDao;
import cn.edu.xmu.daoimpl.TextbookAwardDaoImpl;
import cn.edu.xmu.entity.TextbookAward;
import cn.edu.xmu.table.TextbookAwardTable;

/**
 * Servlet implementation class Sec_AddTextbookAward
 */
@WebServlet("/AddTextbookAward")
public class Sec_AddTextbookAward extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_AddTextbookAward() {
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
		//解码
		String college = request.getParameter(TextbookAwardTable.TA_COLLEGE);
		college = URLDecoder.decode(college,"UTF-8");
		
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		try {
			JSONObject json = new JSONObject(data);

			String ta_collegename= json.getString(TextbookAwardTable.TA_COLLEGENAME);
			String ta_name= json.getString(TextbookAwardTable.TA_NAME);
			String ta_author= json.getString(TextbookAwardTable.TA_AUTHOR);
			String ta_publisher= json.getString(TextbookAwardTable.TA_PUBLISHER);
			String ta_prizename= json.getString(TextbookAwardTable.TA_PRIZENAME);
			String ta_grade= json.getString(TextbookAwardTable.TA_GRADE);
			String ta_type= json.getString(TextbookAwardTable.TA_TYPE);
			
			//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
			int isnull = 0;
			if(ta_collegename.equals("") || ta_name.equals("") || ta_author.equals("")||ta_publisher.equals("")||
					ta_prizename.equals("") || ta_grade.equals("") || ta_type.equals(""))
				isnull = 1;
			
			if(ta_collegename.equals("") && ta_name.equals("") && ta_author.equals("")&&ta_publisher.equals("")&&
					ta_prizename.equals("") && ta_grade.equals("") && ta_type.equals(""))
				return;
			
			int ta_serialnumber = serialnumber;
			String ta_college = college;
			String ta_comments = "";
			TextbookAward ta = new TextbookAward(ta_collegename, ta_name,
					ta_author, ta_publisher, ta_prizename,
					ta_grade, ta_type, ta_serialnumber, ta_college, ta_comments, isnull);			
			
			TextbookAwardDao taDao = new TextbookAwardDaoImpl();
			taDao.addRecord(ta);
			
			out.print(true);
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
		
	}

}
