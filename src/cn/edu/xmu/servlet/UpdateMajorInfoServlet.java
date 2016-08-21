package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.edu.xmu.dao.MajorInfoDao;
import cn.edu.xmu.daoimpl.MajorInfoDaoImpl;
import cn.edu.xmu.table.MajorInfoTable;


/*
 * 4-2-2-1 专业基本情况
 */
@WebServlet("/UpdateMajorInfoServlet")
public class UpdateMajorInfoServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateMajorInfoServlet() {
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
		String patter = request.getParameter("patter");
		//int serialnumber  = Integer.parseInt(request.getParameter("serialnumber"));
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		
		
		try {
			if (patter!=null && "batch".equals(patter)) {
				System.out.println("批量更新");
				//审核部分更新，可以批量
				data="["+data+"]";
				JSONArray jsons = new JSONArray(data);
				
				for (int i = 0; i < jsons.length(); i++) {
					Map<String,String> params= new HashMap<String, String>();
					String mi_id = jsons.getJSONObject(i).getString(MajorInfoTable.MI_ID);
					
					String mi_comments = jsons.getJSONObject(i).getString(MajorInfoTable.MI_COMMENTS);		
					params.put(MajorInfoTable.MI_COMMENTS,mi_comments);
					System.out.println("mi_id:"+mi_id);
					MajorInfoDao mid = new MajorInfoDaoImpl();
					int result = mid.alterMajorInfo(params, mi_id);
					
					out.print(result);
				}
			}
			else{
				JSONObject json = new JSONObject(data);
				System.out.println(json);
				Map<String,String> params= new HashMap<String, String>();
				
				String mi_id = json.getString(MajorInfoTable.MI_ID);
				
				String mi_majorcodeinschool = json.getString(MajorInfoTable.MI_MAJORCODEINSCHOOL);
				params.put(MajorInfoTable.MI_MAJORCODEINSCHOOL,mi_majorcodeinschool);
				String mi_majornameinschool = json.getString(MajorInfoTable.MI_MAJORNAMEINSCHOOL);
				params.put(MajorInfoTable.MI_MAJORNAMEINSCHOOL,mi_majornameinschool);
				String mi_majorname = json.getString(MajorInfoTable.MI_MAJORNAME);
				params.put(MajorInfoTable.MI_MAJORNAME,mi_majorname);
				String mi_majorcode = json.getString(MajorInfoTable.MI_MAJORCODE);
				params.put(MajorInfoTable.MI_MAJORCODE,mi_majorcode);
				String mi_codeversion = json.getString(MajorInfoTable.MI_CODEVERSION);
				params.put(MajorInfoTable.MI_CODEVERSION,mi_codeversion);
				String mi_majorfieldnum = json.getString(MajorInfoTable.MI_MAJORFIELDNUM);
				params.put(MajorInfoTable.MI_MAJORFIELDNUM,mi_majorfieldnum);
				String mi_majorfieldname = json.getString(MajorInfoTable.MI_MAJORFIELDNAME);
				params.put(MajorInfoTable.MI_MAJORFIELDNAME,mi_majorfieldname);
				String mi_departmentnumber = json.getString(MajorInfoTable.MI_DEPARTMENTNUMBER);
				params.put(MajorInfoTable.MI_DEPARTMENTNUMBER,mi_departmentnumber);
				String mi_departmentname = json.getString(MajorInfoTable.MI_DEPARTMENTNAME);
				params.put(MajorInfoTable.MI_DEPARTMENTNAME,mi_departmentname);
				String mi_leaderid = json.getString(MajorInfoTable.MI_LEADERID);
				params.put(MajorInfoTable.MI_LEADERID,mi_leaderid);
				String mi_leadername = json.getString(MajorInfoTable.MI_LEADERNAME);
				params.put(MajorInfoTable.MI_LEADERNAME,mi_leadername);
				String mi_admissionstate = json.getString(MajorInfoTable.MI_ADMISSIONSTATE);
				params.put(MajorInfoTable.MI_ADMISSIONSTATE,mi_admissionstate);
				String mi_majorspecialty = json.getString(MajorInfoTable.MI_MAJORSPECIALTY);
				params.put(MajorInfoTable.MI_MAJORSPECIALTY,mi_majorspecialty);
				String mi_traininggoal = json.getString(MajorInfoTable.MI_TRAININGGOAL);
				params.put(MajorInfoTable.MI_TRAININGGOAL,mi_traininggoal);
				String mi_schoolingyear = json.getString(MajorInfoTable.MI_SCHOOLINGYEAR);
				if(mi_schoolingyear.equals(""))
					mi_schoolingyear = "-999";
				params.put(MajorInfoTable.MI_SCHOOLINGYEAR,mi_schoolingyear);
				String mi_setupyear = json.getString(MajorInfoTable.MI_SETUPYEAR);
				params.put(MajorInfoTable.MI_SETUPYEAR,mi_setupyear);
				String mi_isnew = json.getString(MajorInfoTable.MI_ISNEW);
				params.put(MajorInfoTable.MI_ISNEW,mi_isnew);
				String mi_allhours = json.getString(MajorInfoTable.MI_ALLHOURS);
				if(mi_allhours.equals(""))
					mi_allhours = "-999";
				params.put(MajorInfoTable.MI_ALLHOURS,mi_allhours);
				String mi_musthours = json.getString(MajorInfoTable.MI_MUSTHOURS);
				if(mi_musthours.equals(""))
					mi_musthours = "-999";
				params.put(MajorInfoTable.MI_MUSTHOURS,mi_musthours);
				String mi_selectedhours = json.getString(MajorInfoTable.MI_SELECTEDHOURS);
				if(mi_selectedhours.equals(""))
					mi_selectedhours = "-999";
				params.put(MajorInfoTable.MI_SELECTEDHOURS,mi_selectedhours);
				String mi_inclasshours = json.getString(MajorInfoTable.MI_INCLASSHOURS);
				if(mi_inclasshours.equals(""))
					mi_inclasshours = "-999";
				params.put(MajorInfoTable.MI_INCLASSHOURS,mi_inclasshours);
				String mi_experimenthours = json.getString(MajorInfoTable.MI_EXPERIMENTHOURS);
				if(mi_experimenthours.equals(""))
					mi_experimenthours = "-999";
				params.put(MajorInfoTable.MI_EXPERIMENTHOURS,mi_experimenthours);
				String mi_allcredits = json.getString(MajorInfoTable.MI_ALLCREDITS);
				if(mi_allcredits.equals(""))
					mi_allcredits = "-999";
				params.put(MajorInfoTable.MI_ALLCREDITS,mi_allcredits);
				String mi_mustcredits = json.getString(MajorInfoTable.MI_MUSTCREDITS);
				if(mi_mustcredits.equals(""))
					mi_mustcredits = "-999";
				params.put(MajorInfoTable.MI_MUSTCREDITS,mi_mustcredits);
				String mi_selectedcredits = json.getString(MajorInfoTable.MI_SELECTEDCREDITS);
				if(mi_selectedcredits.equals(""))
					mi_selectedcredits = "-999";
				params.put(MajorInfoTable.MI_SELECTEDCREDITS,mi_selectedcredits);
				String mi_concentratedpracticecredits = json.getString(MajorInfoTable.MI_CONCENTRATEDPRACTICECREDITS);
				if(mi_concentratedpracticecredits.equals(""))
					mi_concentratedpracticecredits = "-999";
				params.put(MajorInfoTable.MI_CONCENTRATEDPRACTICECREDITS,mi_concentratedpracticecredits);
				String mi_inclasscredits = json.getString(MajorInfoTable.MI_INCLASSCREDITS);
				if(mi_inclasscredits.equals(""))
					mi_inclasscredits = "-999";
				params.put(MajorInfoTable.MI_INCLASSCREDITS,mi_inclasscredits);
				String mi_experimentcredits = json.getString(MajorInfoTable.MI_EXPERIMENTCREDITS);
				if(mi_experimentcredits.equals(""))
					mi_experimentcredits = "-999";
				params.put(MajorInfoTable.MI_EXPERIMENTCREDITS,mi_experimentcredits);
				String mi_outclassactivitycredits = json.getString(MajorInfoTable.MI_OUTCLASSACTIVITYCREDITS);		
				if(mi_outclassactivitycredits.equals(""))
					mi_outclassactivitycredits = "-999";
				params.put(MajorInfoTable.MI_OUTCLASSACTIVITYCREDITS,mi_outclassactivitycredits);
				String mi_comments = json.getString(MajorInfoTable.MI_COMMENTS);		
				params.put(MajorInfoTable.MI_COMMENTS,mi_comments);
				System.out.println("mi_id:"+mi_id);
				
				
				
				//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
				int isnull = 0;
				if(mi_majorcodeinschool.equals("") || mi_majornameinschool.equals("") || mi_majorname.equals("") || 
						mi_majorcode.equals("") || mi_codeversion.equals("") || mi_majorfieldnum.equals("") ||
						mi_majorfieldname.equals("") || mi_departmentnumber.equals("") ||mi_departmentname.equals("") || mi_leaderid.equals("") || mi_leadername.equals("") ||
						mi_admissionstate.equals("") || mi_majorspecialty.equals("") || mi_traininggoal.equals("") ||
						mi_schoolingyear.equals("-999") || mi_setupyear.equals("") || mi_isnew.equals("") ||
						mi_allhours.equals("-999") || mi_musthours.equals("-999")||mi_selectedhours.equals("-999") || mi_inclasshours.equals("-999") || 
						mi_experimenthours.equals("-999") || mi_allcredits.equals("-999") || mi_mustcredits.equals("-999") || mi_selectedcredits.equals("-999") ||
						mi_concentratedpracticecredits.equals("-999") || mi_inclasscredits.equals("-999") || mi_experimentcredits.equals("-999") ||
						mi_outclassactivitycredits.equals("-999"))
					isnull = 1;
				
				params.put(MajorInfoTable.ISNULL, isnull+"");
				
				MajorInfoDao mid = new MajorInfoDaoImpl();
				int result = mid.alterMajorInfo(params, mi_id);
				
				out.print(result);
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
		
	}


}

