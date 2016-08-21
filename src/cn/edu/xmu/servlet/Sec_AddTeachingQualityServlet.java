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

import cn.edu.xmu.dao.SchoolNetDao;
import cn.edu.xmu.dao.TeachingQualityDao;
import cn.edu.xmu.daoimpl.SchoolNetDaoimpl;
import cn.edu.xmu.daoimpl.TeachingQualityDaoImpl;
import cn.edu.xmu.entity.SchoolNet;
import cn.edu.xmu.entity.TeachingQuality;
import cn.edu.xmu.table.SchoolNetTable;
import cn.edu.xmu.table.TeachingQualityTable;

/**
 * Servlet implementation class Sec_AddTeachingQualityServlet
 */
@WebServlet("/Sec_AddTeachingQualityServlet")
public class Sec_AddTeachingQualityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_AddTeachingQualityServlet() {
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
		String college = request.getParameter(TeachingQualityTable.TQ_COLLEGE);
		college = URLDecoder.decode(college,"UTF-8");
		
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		try {
			JSONObject json = new JSONObject(data);
			
			String tq_project = json.getString(TeachingQualityTable.TQ_PROJECT);
			String coverpercent = json.getString(TeachingQualityTable.TQ_COVERPERCENT);
			float tq_coverpercent = -999;
			if (coverpercent!=null && !"".equals(coverpercent)) {
				tq_coverpercent = Float.parseFloat(coverpercent);
			}
			String excellent = json.getString(TeachingQualityTable.TQ_EXCELLENT);
			float tq_excellent = -999;
			if (excellent!=null && !"".equals(excellent)) {
				tq_excellent = Float.parseFloat(excellent);
			}
			String good = json.getString(TeachingQualityTable.TQ_GOOD);
			float tq_good = -999;
			if (good!=null && !"".equals(good)) {
				tq_good = Float.parseFloat(good);
			}
			String medium = json.getString(TeachingQualityTable.TQ_MEDIUN);
			float tq_medium = -999;
			if (medium!=null && !"".equals(medium)) {
				tq_medium = Float.parseFloat(medium);
			}
			String poor = json.getString(TeachingQualityTable.TQ_POOR);
			float tq_poor = -999;
			if (poor!=null && !"".equals(poor)) {
				tq_poor = Float.parseFloat(poor);
			}
			
			//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
			int isnull = 0;
			if(tq_project.equals("") || coverpercent.equals("") || excellent.equals("") || 
					good.equals("") || medium.equals("") || poor.equals("") )
				isnull = 1;
			
			int tq_serialnumber = serialnumber;
			String tq_college = college;
			String tp_comments = "";
			
			if(tq_project.equals("") && coverpercent.equals("") && excellent.equals("")  && 
					good.equals("")  && medium.equals("")  && poor.equals("") )
			return;
			TeachingQuality tq = new TeachingQuality(tq_project,
					tq_coverpercent, tq_excellent,tq_good,tq_medium,tq_poor, tq_serialnumber,tq_college,tp_comments,isnull);
			
			TeachingQualityDao tqDao = new TeachingQualityDaoImpl();
			tqDao.addRecord(tq);
			out.print(true);
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
	}

}
