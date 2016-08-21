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

import cn.edu.xmu.dao.FundingProfileDao;
import cn.edu.xmu.daoimpl.FundingProfileDaoimpl;
import cn.edu.xmu.entity.FundingProfile;
import cn.edu.xmu.table.FundingProfileTable;

/**
 * Servlet implementation class Sec_AddFundingProfile
 */
@WebServlet("/Sec_AddFundingProfile")
public class Sec_AddFundingProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_AddFundingProfileServlet() {
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
		String college = request.getParameter(FundingProfileTable.FP_COLLEGE);
		college = URLDecoder.decode(college,"UTF-8");
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		try {
			JSONObject json = new JSONObject(data);
			
			String educationfund = json.getString(FundingProfileTable.FP_EDUCATIONFUND);
			float fp_educationfund = -999;
			if (educationfund!=null && !"".equals(educationfund)) {
				fp_educationfund = Float.parseFloat(educationfund);
			}
			String teachfund = json.getString(FundingProfileTable.FP_TEACHFUND);
			float fp_teachfund = -999;
			if (teachfund!=null && !"".equals(teachfund)) {
				fp_teachfund = Float.parseFloat(teachfund);
			}
			String reformfund = json.getString(FundingProfileTable.FP_REFORMFUND);
			float fp_reformfund = -999;
			if (reformfund!=null && !"".equals(reformfund)) {
				fp_reformfund = Float.parseFloat(reformfund);
			}
			
			//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
			int isnull = 0;
			if(educationfund.equals("") || teachfund.equals("") || reformfund.equals("")  )
				isnull = 1;
			
			int fp_serialnumber = serialnumber;
			String fp_college = college;
			String fp_comments = "";
			
			if(educationfund.equals("") && teachfund.equals("") && reformfund.equals("")  )
				return;
			FundingProfile fp = new FundingProfile(fp_educationfund,
					fp_teachfund, fp_reformfund, fp_serialnumber,fp_college,fp_comments,isnull);			
			FundingProfileDao fpDao = new FundingProfileDaoimpl();
			fpDao.addRecord(fp);
			out.print(true);
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
	}

}
