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

import cn.edu.xmu.dao.NewBooksthatYearDao;
import cn.edu.xmu.daoimpl.NewBooksthatYearDaoImpl;
import cn.edu.xmu.entity.NewBooksthatYear;
import cn.edu.xmu.table.NewBooksthatYearTable;

/**
 * Servlet implementation class Sec_AddNewBooksthatYear
 */
@WebServlet("/AddNewBooksthatYear")
public class Sec_AddNewBooksthatYear extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_AddNewBooksthatYear() {
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
		String college = request.getParameter(NewBooksthatYearTable.NBY_COLLEGE);
		college = URLDecoder.decode(college,"UTF-8");
		
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		String temp = "";
		try {
			JSONObject json = new JSONObject(data);
			
			temp = json.getString(NewBooksthatYearTable.NBY_PAPERBOOKSNUMBER);
			int nby_paperbooksnumber = -999;
			if(!temp.equals(""))
				nby_paperbooksnumber = Integer.valueOf(temp);
			
			temp = json.getString(NewBooksthatYearTable.NBY_EBOOKSNUMBER);
			int nby_ebooksnumber = -999;
			if(!temp.equals(""))
				nby_ebooksnumber = Integer.valueOf(temp);
			
			temp = json.getString(NewBooksthatYearTable.NBY_DOCUMENTACQUISITIONCOST);
			float nby_documentacquisitioncost = -999;
			if(!temp.equals(""))
				nby_documentacquisitioncost = Float.valueOf(temp);
			
			temp = json.getString(NewBooksthatYearTable.NBY_BOOKCIRCULATION);
			int nby_bookcirculation = -999;
			if(!temp.equals(""))
				nby_bookcirculation = Integer.valueOf(temp);
			
			temp = json.getString(NewBooksthatYearTable.NBY_ELECTRONICRESOURCEACCESS);
			int nby_electronicresourceaccess = -999;
			if(!temp.equals(""))
				nby_electronicresourceaccess = Integer.valueOf(temp);
			
			int nby_serialnumber = serialnumber;
			String nby_college = college;
			String nby_comments = "";
			
			//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
			int isnull = 0;
			if(nby_paperbooksnumber==-999 || nby_ebooksnumber==-999 || nby_documentacquisitioncost==-999 ||
					nby_bookcirculation==-999 || nby_electronicresourceaccess==-999)
				isnull = 1;
			
			if(nby_paperbooksnumber==-999 && nby_ebooksnumber==-999 && nby_documentacquisitioncost==-999 &&
					nby_bookcirculation==-999 && nby_electronicresourceaccess==-999)
				return;
			
			NewBooksthatYear nby = new NewBooksthatYear(nby_paperbooksnumber,
					nby_ebooksnumber, nby_documentacquisitioncost,
					nby_bookcirculation, nby_electronicresourceaccess,
					nby_serialnumber, nby_college, nby_comments, isnull);			
			
			NewBooksthatYearDao nbyDao = new NewBooksthatYearDaoImpl();
			nbyDao.addRecord(nby);
			
			out.print(true);
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
		
	}

}
