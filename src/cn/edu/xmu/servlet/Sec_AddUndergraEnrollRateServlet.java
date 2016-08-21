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

import cn.edu.xmu.dao.UndergraEnrollRateDao;
import cn.edu.xmu.daoimpl.UndergraEnrollRateDaoImpl;
import cn.edu.xmu.entity.UndergraEnrollRate;
import cn.edu.xmu.table.UndergraEnrollRateTable;

/**
 * 附表6-1-5-3本科生招生志愿满足率（时点）
 * @author yue
 *
 */
@WebServlet("/Sec_AddUndergraEnrollRateServlet")
public class Sec_AddUndergraEnrollRateServlet extends HttpServlet{
private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_AddUndergraEnrollRateServlet()
    {
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
		String college = request.getParameter(UndergraEnrollRateTable.UER_COLLEGEG);
		college = URLDecoder.decode(college,"UTF-8");
		
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		try {
			JSONObject json = new JSONObject(data);
			
			String uer_institute = json.getString(UndergraEnrollRateTable.UER_INSTITUTE);
			String uer_admission = json.getString(UndergraEnrollRateTable.UER_ADMISSION);
			String firstmajorrate = json.getString(UndergraEnrollRateTable.UER_FIRSTMAJORRATE);
			Double uer_firstmajorrate = Double.valueOf("-999");
			if(!firstmajorrate.equals(""))
				uer_firstmajorrate = Double.valueOf(firstmajorrate);
			String unfirstmajorrate = json.getString(UndergraEnrollRateTable.UER_UNFIRSTMAJORRATE);
			double uer_unfirstmajorrate = Double.valueOf("-999");
			if(!unfirstmajorrate.equals(""))
				uer_unfirstmajorrate =  Double.valueOf(unfirstmajorrate);
			String adjustrate = json.getString(UndergraEnrollRateTable.UER_ADJUSTRATE);
			double uer_adjustrate = Double.valueOf("-999");
			if(!adjustrate.equals(""))
				uer_adjustrate =  Double.valueOf(adjustrate);
			
			//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
			int isnull = 0;
			if(uer_institute.equals("")||uer_admission.equals("")||firstmajorrate.equals("")||
					unfirstmajorrate.equals("")||adjustrate.equals(""))
				isnull = 1;
			
			if(uer_institute.equals("")&&uer_admission.equals("")&&firstmajorrate.equals("")&&
					unfirstmajorrate.equals("")&&adjustrate.equals(""))
			{
				out.println(false);
				return;
			}
			int uer_serialnumber = serialnumber;
			String uer_college = college;
			String uer_comments = "";
			
			UndergraEnrollRate uer = new UndergraEnrollRate(uer_institute, uer_admission, uer_firstmajorrate,
					uer_unfirstmajorrate, uer_adjustrate, uer_serialnumber, uer_college, uer_comments,isnull);
			UndergraEnrollRateDao uerDao = new UndergraEnrollRateDaoImpl();
			uerDao.addUndergraEnrollRateRecord(uer);
			out.print(true);
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
	}
}
