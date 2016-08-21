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
import cn.edu.xmu.dao.ForeignExchangeDao;
import cn.edu.xmu.daoimpl.ForeignExchangeDaoImpl;
import cn.edu.xmu.entity.ForeignExchange;
import cn.edu.xmu.table.ForeignExchangeTable;

/**
 * Servlet implementation class AddForeignExchangeServlet
 */
@WebServlet("/Sec_AddForeignExchangeServlet")
public class Sec_AddForeignExchangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_AddForeignExchangeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
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
		int result = 0;
		try {
			JSONObject json = new JSONObject(data);
			
			String fe_collegename = json.getString(ForeignExchangeTable.FE_COLLEGENAME);
		
			String fe_projectname = json.getString(ForeignExchangeTable.FE_PROJECTNAME);			
			String fe_iscsc = json.getString(ForeignExchangeTable.FE_ISCSC);
			String fe_country  = json.getString(ForeignExchangeTable.FE_COUNTRY);
			String fe_school = json.getString(ForeignExchangeTable.FE_SCHOOL);
			String fe_level = json.getString(ForeignExchangeTable.FE_LEVEL);
			String fe_time = json.getString(ForeignExchangeTable.FE_TIME);			
			String selftoforeign = json.getString(ForeignExchangeTable.FE_SELFTOFOREIGN);
			Integer fe_selftoforeign = -1;
			if(!selftoforeign.equals("")) fe_selftoforeign = Integer.parseInt(selftoforeign);
			String foreigntoself = json.getString(ForeignExchangeTable.FE_FOREIGNTOSELF);
			Integer fe_foreigntoself = -1;
			if(!foreigntoself.equals("")) fe_foreigntoself = Integer.parseInt(foreigntoself);
			
			int fe_isnull = 0;
			if("".equals(fe_collegename)||"".equals(fe_projectname)||"".equals(fe_iscsc)||"".equals(fe_country)
					|| "".equals(fe_school) || "".equals(fe_level) || "".equals(fe_time) || "".equals(selftoforeign) ||"".equals(foreigntoself))
			{
				fe_isnull = 1;
			}
			if ("".equals(fe_collegename) && "".equals(fe_projectname) && "".equals(fe_iscsc) && "".equals(fe_country)
					&&  "".equals(fe_school) && "".equals(fe_level) && "".equals(fe_time) && "".equals(selftoforeign) && "".equals(foreigntoself))
			{
				result = -1;
				out.println(result);
				return;
			}
			
			String fe_college = request.getParameter(ForeignExchangeTable.FE_COLLEGE);
			fe_college = URLDecoder.decode(fe_college,"UTF-8");
			
			ForeignExchange foreignExchange = new ForeignExchange(fe_collegename,fe_projectname, fe_iscsc, fe_country, fe_school, fe_level, fe_time,fe_selftoforeign,fe_foreigntoself,fe_college,serialnumber,fe_isnull);
			ForeignExchangeDao foreignEchangeDao = new ForeignExchangeDaoImpl();
			result = foreignEchangeDao.addForeignExchange(foreignExchange);
			out.print(result);
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
	}

}
