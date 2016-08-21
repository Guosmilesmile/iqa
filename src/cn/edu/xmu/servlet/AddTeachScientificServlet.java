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
import cn.edu.xmu.dao.TeachScientificDao;
import cn.edu.xmu.daoimpl.TeachResearchUnitDaoImpl;
import cn.edu.xmu.daoimpl.TeachScientificDaoImpl;
import cn.edu.xmu.entity.TeachScientific;
import cn.edu.xmu.table.TeachResearchUnitTable;
import cn.edu.xmu.table.TeachScientificTable;

/**
 * 
 * @author luo
 * 添加学校教学科研单位
 * date 2015-06-29
 */
@WebServlet("/AddTeachScientificServlet")
public class AddTeachScientificServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTeachScientificServlet() {
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
		
		String college = request.getParameter(TeachScientificTable.TS_COLLEGE);
		college = URLDecoder.decode(college,"UTF-8");
		
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		
		System.out.println(data);
		System.out.println(serialnumber);
		
		try {
			JSONObject json = new JSONObject(data);
			String ts_name = json.getString(TeachScientificTable.TS_NAME);
			String ts_number = json.getString(TeachScientificTable.TS_NUMBER);
			String ts_head = json.getString(TeachScientificTable.TS_HEAD);
			
			//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
			int isnull = 0;
			if(ts_name.equals("") || ts_number.equals("") || ts_head.equals("")  )
				isnull = 1;
			
			int ts_serialnumber = serialnumber;
			String ts_college = college;
			String ts_comments = "";
			if(ts_name.equals("") && ts_number.equals("") && ts_head.equals("")  )
				return;
			TeachScientific ts = new TeachScientific(ts_name, ts_number, ts_head, ts_serialnumber,ts_college,ts_comments, isnull);
			TeachScientificDao trd = new TeachScientificDaoImpl();
			trd.addTeachScientificRecord(ts);
			out.print(true);
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
	}

}
