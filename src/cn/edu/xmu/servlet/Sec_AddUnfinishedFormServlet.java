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

import cn.edu.xmu.dao.UnfinishedFormDao;
import cn.edu.xmu.daoimpl.UnfinishedFormDaoImpl;
import cn.edu.xmu.entity.UnfinishedForm;
import cn.edu.xmu.table.UnfinishedFormTable;

/**
 * 未填表格说明
 * @author chunfeng
 *
 */
@WebServlet("/Sec_AddUnfinishedFormServlet")
public class Sec_AddUnfinishedFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_AddUnfinishedFormServlet() {
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
					 
			String uf_formname = json.getString(UnfinishedFormTable.UF_FORMNAME);   
			String uf_accountfor  = json.getString(UnfinishedFormTable.UF_ACCOUNTFOR); 					
			
			int uf_isnull = 0;
			if("".equals(uf_formname)||"".equals(uf_accountfor))
			{
				uf_isnull = 1;
			}
			if ("".equals(uf_formname)&&"".equals(uf_accountfor))
			{
				result = -1;
				out.println(result);
				return;
			}
					
			String uf_college  = request.getParameter(UnfinishedFormTable.UF_COLLEGE); //院系名称			
			uf_college = URLDecoder.decode(uf_college,"UTF-8");
			
			UnfinishedForm unfinishedForm = new UnfinishedForm(uf_formname,
					uf_accountfor, uf_college, serialnumber,uf_isnull);
			UnfinishedFormDao unfinishedFormDao = new UnfinishedFormDaoImpl();
			result = unfinishedFormDao.addUnfinishedForm(unfinishedForm);
			out.print(result);
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
	}

}
