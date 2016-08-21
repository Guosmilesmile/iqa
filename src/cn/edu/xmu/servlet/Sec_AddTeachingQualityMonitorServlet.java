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

import cn.edu.xmu.dao.TeachingQualityMonitorDao;
import cn.edu.xmu.daoimpl.TeachingQualityMonitorDaoImpl;
import cn.edu.xmu.entity.TeachingQualityMonitor;
import cn.edu.xmu.table.TeachingQualityMonitorTable;

/**
 * 7-4
 * @author chunfeng
 *
 */
@WebServlet("/Sec_AddTeachingQualityMonitorServlet")
public class Sec_AddTeachingQualityMonitorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_AddTeachingQualityMonitorServlet() {
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
					 
			String tqm_managesyscontent = json.getString(TeachingQualityMonitorTable.TQM_MANAGESYSCONTENT);   
			String tqm_assessexe  = json.getString(TeachingQualityMonitorTable.TQM_ASSESSEXE); 					
			String tqm_annualreport = json.getString(TeachingQualityMonitorTable.TQM_ANNUALREPORT);  
			
			int tqm_isnull = 0;
			if("".equals(tqm_managesyscontent)||"".equals(tqm_assessexe)||"".equals(tqm_annualreport))
			{
				tqm_isnull = 1;
			}
			if ("".equals(tqm_managesyscontent)&&"".equals(tqm_assessexe)&&"".equals(tqm_annualreport))
			{
				result = -1;
				out.println(result);
				return;
			}
					
			String tqm_college  = request.getParameter(TeachingQualityMonitorTable.TQM_COLLEGE); //院系名称			
			tqm_college = URLDecoder.decode(tqm_college,"UTF-8");
			
			TeachingQualityMonitor teachingQualityMonitor = new TeachingQualityMonitor(tqm_managesyscontent,
					tqm_assessexe, tqm_annualreport, tqm_college, serialnumber,tqm_isnull);
			TeachingQualityMonitorDao teachingQualityMonitorDao = new TeachingQualityMonitorDaoImpl();
			result = teachingQualityMonitorDao.addTeachingQualityMonitor(teachingQualityMonitor);
			out.print(result);
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
	}

}
