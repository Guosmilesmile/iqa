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

import cn.edu.xmu.dao.DevAgencyTrainInfoDao;
import cn.edu.xmu.daoimpl.DevAgencyTrainInfoDaoImpl;
import cn.edu.xmu.entity.DevAgencyTrainInfo;
import cn.edu.xmu.table.DevAgencyTrainInfoTable;

/**
 * 附表3-5-1-1教师教学发展机构培训情况（学年）
 * @author yue
 *
 */
@WebServlet("/Sec_AddDevAgencyTrainInfoServlet")
public class Sec_AddDevAgencyTrainInfoServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_AddDevAgencyTrainInfoServlet()
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
		String college = request.getParameter(DevAgencyTrainInfoTable.DATI_COLLEGE);
		college = URLDecoder.decode(college,"UTF-8");
		
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		try {
			JSONObject json = new JSONObject(data);
			
			String dati_name = json.getString(DevAgencyTrainInfoTable.DATI_NAME);
			String dati_departmentname = json.getString(DevAgencyTrainInfoTable.DATI_DEPARTMENTNAME);
			String dati_departmentnumber = json.getString(DevAgencyTrainInfoTable.DATI_DEPARTMENTNUMBER);
			String dati_workplan = json.getString(DevAgencyTrainInfoTable.DATI_WORKPLAN);
			String traintimes = json.getString(DevAgencyTrainInfoTable.DATI_TRAINTIMES);
			Integer dati_traintimes = -999;
			if(!traintimes.equals(""))
				dati_traintimes = Integer.valueOf(traintimes);
			String traintrips = json.getString(DevAgencyTrainInfoTable.DATI_TRAINTRIPS);
			int dati_traintrips = -999;
			if(!traintrips.equals(""))
				dati_traintrips = Integer.valueOf(traintrips);
			int dati_serialnumber = serialnumber;
			String dati_college = college;
			String dati_comments = "";
			
			//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
			int isnull = 0;
			if(dati_name.equals("")||dati_departmentname.equals("")||dati_departmentnumber.equals("")||dati_workplan.equals("")||traintimes.equals("")||
					traintrips.equals(""))
				isnull = 1;
			if(dati_name.equals("")&&dati_departmentname.equals("")&&dati_departmentnumber.equals("")&&dati_workplan.equals("")&&traintimes.equals("")&&
					traintrips.equals(""))
			{
				out.println(false);
				return ;
			}
			DevAgencyTrainInfo dati = new DevAgencyTrainInfo(dati_name, dati_departmentname, dati_departmentnumber, dati_workplan,
					dati_traintimes, dati_traintrips, dati_serialnumber, dati_college, dati_comments,isnull);
			DevAgencyTrainInfoDao datiDao = new DevAgencyTrainInfoDaoImpl();
			datiDao.addDevAgencyTrainInfoRecord(dati);
			out.print(true);
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
	}

}
