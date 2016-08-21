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

import cn.edu.xmu.dao.AdmissionCriteriaAndNumberDao;
import cn.edu.xmu.daoimpl.AdmissionCriteriaAndNumberDaoImpl;
import cn.edu.xmu.entity.AdmissionCriteriaAndNumber;
import cn.edu.xmu.table.AdmissionCriteriaAndNumberTable;

/**
 * 附表6-1-5-4  近一届本科生录取标准及人数（时点）
 * @author yue
 *
 */
@WebServlet("/Sec_AddAdmissionCriteriaAndNumberServlet")
public class Sec_AddAdmissionCriteriaAndNumberServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_AddAdmissionCriteriaAndNumberServlet()
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
		String college = request.getParameter(AdmissionCriteriaAndNumberTable.ACN_COLLEGE);
		college = URLDecoder.decode(college,"UTF-8");
		
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		try {
			JSONObject json = new JSONObject(data);
			
			String acn_province = json.getString(AdmissionCriteriaAndNumberTable.ACN_PROVINCE);
			String acn_batch = json.getString(AdmissionCriteriaAndNumberTable.ACN_BATCH);
			String artsadmission = json.getString(AdmissionCriteriaAndNumberTable.ACN_ARTSADMISSION);
			Integer acn_artsadmission = -999;
			if(!artsadmission.equals(""))
				acn_artsadmission = Integer.valueOf(artsadmission);
			String scienceadmission = json.getString(AdmissionCriteriaAndNumberTable.ACN_SCIENCEADMISSION);
			Integer acn_scienceadmission = -999;
			if(!scienceadmission.equals(""))
				acn_scienceadmission = Integer.valueOf(scienceadmission);
			String artsminctrline = json.getString(AdmissionCriteriaAndNumberTable.ACN_ARTSMINCTRLINE);
			Double acn_artsminctrline = Double.valueOf("-999");
			if(!artsminctrline.equals(""))
				acn_artsminctrline = Double.valueOf(artsminctrline);
			String scienceminctrline = json.getString(AdmissionCriteriaAndNumberTable.ACN_SCIENCEMINCTRLINE);
			Double acn_scienceminctrline = Double.valueOf("-999");
			if(!scienceminctrline.equals(""))
				acn_scienceminctrline = Double.valueOf(scienceminctrline);
			String artsavgscore = json.getString(AdmissionCriteriaAndNumberTable.ACN_ARTSAVGSCORE);
			Double acn_artsavgscore = Double.valueOf("-999");
			if(!artsavgscore.equals(""))
				acn_artsavgscore = Double.valueOf(artsavgscore);
			String scienceavgscore =  json.getString(AdmissionCriteriaAndNumberTable.ACN_SCIENCEAVGSCORE);
			Double acn_scienceavgscore = Double.valueOf("-999");
			if(!scienceavgscore.equals(""))
				acn_scienceavgscore = Double.valueOf(scienceavgscore);
			String acn_instruction = json.getString(AdmissionCriteriaAndNumberTable.ACN_INSTRUCTION);
			
			//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
			int isnull = 0;
			if(acn_province.equals("") || acn_batch.equals("") || artsadmission.equals("") || 
					scienceadmission.equals("") || artsminctrline.equals("") || scienceminctrline.equals("") ||
					artsavgscore.equals("") || scienceavgscore.equals("") )
				isnull = 1;
			if(acn_province.equals("") && acn_batch.equals("") && artsadmission.equals("") && 
					scienceadmission.equals("") && artsminctrline.equals("") && scienceminctrline.equals("") &&
					artsavgscore.equals("") && scienceavgscore.equals("") && acn_instruction.equals("") )
			{
				out.println(false);
				return ;
			}
			int acn_serialnumber = serialnumber;
			String acn_college = college;
			String acn_comments = "";
			
			
			AdmissionCriteriaAndNumber acn = new AdmissionCriteriaAndNumber(acn_province, acn_batch, 
					acn_artsadmission, acn_scienceadmission, acn_artsminctrline, acn_scienceminctrline,
					acn_artsavgscore, acn_scienceavgscore, acn_instruction, acn_serialnumber, acn_college,
					acn_comments,isnull);
			AdmissionCriteriaAndNumberDao acnDao = new AdmissionCriteriaAndNumberDaoImpl();
			acnDao.addAdmissionCriteriaAndNumberRecord(acn);
			out.print(true);
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
	}
}
