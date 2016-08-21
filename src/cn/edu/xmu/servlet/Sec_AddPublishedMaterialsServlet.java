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

import cn.edu.xmu.dao.PublishedMaterialsDao;
import cn.edu.xmu.daoimpl.PublishedMaterialsDaoImpl;
import cn.edu.xmu.entity.PublishedMaterials;
import cn.edu.xmu.table.PublishedMaterialsTable;

/**
 * Servlet implementation class Sec_AddPublishedMaterialsServlet
 */
@WebServlet("/Sec_AddPublishedMaterialsServlet")
public class Sec_AddPublishedMaterialsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_AddPublishedMaterialsServlet() {
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
		//解码
		String college = request.getParameter(PublishedMaterialsTable.PM_COLLEGE);
		college = URLDecoder.decode(college,"UTF-8");
		
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		try {
			JSONObject json = new JSONObject(data);
			
			String pm_importcollege = json.getString(PublishedMaterialsTable.PM_IMPORTCOLLEGE);
			String pm_materialsname = json.getString(PublishedMaterialsTable.PM_MATERIALSNAME);
			String pm_author = json.getString(PublishedMaterialsTable.PM_AUTHOR);
			String pm_type = json.getString(PublishedMaterialsTable.PM_TYPE);
			String pm_publisher = json.getString(PublishedMaterialsTable.PM_PUBLISHER);
			String pm_publishyear = json.getString(PublishedMaterialsTable.PM_PUBLISHYEAR);
			String pm_engineeringmaterials = json.getString(PublishedMaterialsTable.PM_ENGINEERINGMATERIALS);
			String pm_forteaching = json.getString(PublishedMaterialsTable.PM_FORTEACHING);
			
			//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
			int isnull = 0;
			if(pm_importcollege.equals("") || pm_materialsname.equals("") || pm_author.equals("") || 
					pm_type.equals("") || pm_publisher.equals("") || pm_publishyear.equals("") ||
					pm_engineeringmaterials.equals("") || pm_forteaching.equals("")  )
				isnull = 1;
			
			int pm_serialnumber = serialnumber;
			String pm_college = college;
			String pm_comments = "";
			
			if(pm_importcollege.equals("") && pm_materialsname.equals("") && pm_author.equals("") && 
					pm_type.equals("") && pm_publisher.equals("") && pm_publishyear.equals("") &&
					pm_engineeringmaterials.equals("") && pm_forteaching.equals("")  )
				return;
			PublishedMaterials pm = new PublishedMaterials(pm_importcollege,
					pm_materialsname, pm_author, pm_type,pm_publisher,pm_publishyear,pm_engineeringmaterials,pm_forteaching,pm_serialnumber,pm_college,pm_comments,isnull);			
			PublishedMaterialsDao pmDao = new PublishedMaterialsDaoImpl();
			pmDao.addRecord(pm);
			out.print(true);
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
	}

}
