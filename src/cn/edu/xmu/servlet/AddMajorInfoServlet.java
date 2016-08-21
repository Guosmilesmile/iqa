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

import cn.edu.xmu.dao.MajorInfoDao;
import cn.edu.xmu.daoimpl.MajorInfoDaoImpl;
import cn.edu.xmu.entity.MajorInfo;
import cn.edu.xmu.table.MajorInfoTable;

/**
 * 
 * @author zsj
 * 4-2-2-1 专业基本情况
 */
@WebServlet("/AddMajorInfoServlet")
public class AddMajorInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MajorInfoDao majorInfoDao = new MajorInfoDaoImpl();
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddMajorInfoServlet() {
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
		
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		try {
			JSONObject json = new JSONObject(data);
			
			String mi_majorcodeinschool = json.getString(MajorInfoTable.MI_MAJORCODEINSCHOOL);
			String mi_majornameinschool = json.getString(MajorInfoTable.MI_MAJORNAMEINSCHOOL);
			String mi_majorname = json.getString(MajorInfoTable.MI_MAJORNAME);
			String mi_majorcode = json.getString(MajorInfoTable.MI_MAJORCODE);
			String mi_codeversion = json.getString(MajorInfoTable.MI_CODEVERSION);
			String mi_majorfieldnum = json.getString(MajorInfoTable.MI_MAJORFIELDNUM);
			String mi_majorfieldname = json.getString(MajorInfoTable.MI_MAJORFIELDNAME);
			String mi_departmentnumber = json.getString(MajorInfoTable.MI_DEPARTMENTNUMBER);
			String mi_departmentname = json.getString(MajorInfoTable.MI_DEPARTMENTNAME);
			String mi_leaderid = json.getString(MajorInfoTable.MI_LEADERID);
			String mi_leadername = json.getString(MajorInfoTable.MI_LEADERNAME);
			String mi_admissionstate = json.getString(MajorInfoTable.MI_ADMISSIONSTATE);
			String mi_majorspecialty = json.getString(MajorInfoTable.MI_MAJORSPECIALTY);
			String mi_traininggoal = json.getString(MajorInfoTable.MI_TRAININGGOAL);
			
			String schoolingyear = json.getString(MajorInfoTable.MI_SCHOOLINGYEAR);
			int mi_schoolingyear = -999;
			if (schoolingyear!=null && !"".equals(schoolingyear)) {
				mi_schoolingyear = json.getInt(MajorInfoTable.MI_SCHOOLINGYEAR);
			}
			
			String mi_setupyear = json.getString(MajorInfoTable.MI_SETUPYEAR);
			
			String mi_isnew = json.getString(MajorInfoTable.MI_ISNEW);

			String allhours = json.getString(MajorInfoTable.MI_ALLHOURS);
			int mi_allhours = -999;
			if (allhours!=null && !"".equals(allhours)) {
				mi_allhours = Integer.parseInt(allhours);
			}
			
			String musthours = json.getString(MajorInfoTable.MI_MUSTHOURS);
			int mi_musthours = -999;
			if (musthours!=null && !"".equals(musthours)) {
				mi_musthours = Integer.parseInt(musthours);
			}
			
			String selectedhours = json.getString(MajorInfoTable.MI_SELECTEDHOURS);
			int mi_selectedhours = -999;
			if (selectedhours!=null && !"".equals(selectedhours)) {
				mi_selectedhours = Integer.parseInt(selectedhours);
			}
			
			String inclasshours = json.getString(MajorInfoTable.MI_INCLASSHOURS);
			int mi_inclasshours = -999;
			if (inclasshours!=null && !"".equals(inclasshours)) {
				mi_inclasshours = Integer.parseInt(inclasshours);
			}
			
			String experimenthours = json.getString(MajorInfoTable.MI_EXPERIMENTHOURS);
			int mi_experimenthours = -999;
			if (experimenthours!=null && !"".equals(experimenthours)) {
				mi_experimenthours = Integer.parseInt(experimenthours);
			}
			
			String allcredits = json.getString(MajorInfoTable.MI_ALLCREDITS);
			float mi_allcredits = -999;
			if (allcredits!=null && !"".equals(allcredits)) {
				mi_allcredits = Float.parseFloat(allcredits);
			}
			
			String mustcredits = json.getString(MajorInfoTable.MI_MUSTCREDITS);
			float mi_mustcredits = -999;
			if (mustcredits!=null && !"".equals(mustcredits)) {
				mi_mustcredits = Float.parseFloat(mustcredits);
			}
			
			String selectedcredits = json.getString(MajorInfoTable.MI_SELECTEDCREDITS);
			float mi_selectedcredits = -999;
			if (selectedcredits!=null && !"".equals(selectedcredits)) {
				mi_selectedcredits = Float.parseFloat(selectedcredits);
			}
			
			String concentratedpracticecredits = json.getString(MajorInfoTable.MI_CONCENTRATEDPRACTICECREDITS);
			float mi_concentratedpracticecredits = -999;
			if (concentratedpracticecredits!=null && !"".equals(concentratedpracticecredits)) {
				mi_concentratedpracticecredits = Float.parseFloat(concentratedpracticecredits);
			}
			
			String inclasscredits = json.getString(MajorInfoTable.MI_INCLASSCREDITS);
			float mi_inclasscredits = -999;
			if (inclasscredits!=null && !"".equals(inclasscredits)) {
				mi_inclasscredits = Float.parseFloat(inclasscredits);
			}
			
			String experimentcredits = json.getString(MajorInfoTable.MI_EXPERIMENTCREDITS);
			float mi_experimentcredits = 0;
			if (experimentcredits!=null && !"".equals(experimentcredits)) {
				mi_experimentcredits = Float.parseFloat(experimentcredits);
			}
			
			String outclassactivitycredits = json.getString(MajorInfoTable.MI_OUTCLASSACTIVITYCREDITS);
			float mi_outclassactivitycredits = -999;
			if (outclassactivitycredits!=null && !"".equals(outclassactivitycredits)) {
				mi_outclassactivitycredits = Float.parseFloat(outclassactivitycredits);
			}
			
			int serialnumber  = Integer.parseInt(request.getParameter("serialnumber"));
			int mi_serialnumber = serialnumber;
			//Date mi_deadline = new Date(json.getLong(MajorInfoTable.MI_DEADLINE));
			
			String college = request.getParameter("mi_college");
			college = URLDecoder.decode(college,"UTF-8");
			String mi_college = college;
			//String mi_comments = json.getString(MajorInfoTable.MI_COMMENTS);
			
			//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
			int isnull = 0;
			if(mi_majorcodeinschool.equals("") || mi_majornameinschool.equals("") || mi_majorname.equals("") || 
					mi_majorcode.equals("") || mi_codeversion.equals("") || mi_majorfieldnum.equals("") ||
					mi_majorfieldname.equals("") || mi_departmentnumber.equals("") || mi_departmentname.equals("") || mi_leaderid.equals("") ||
					mi_leadername.equals("") ||mi_admissionstate.equals("") || mi_majorspecialty.equals("") || mi_traininggoal.equals("") ||
					schoolingyear.equals("") || mi_setupyear.equals("") || mi_isnew.equals("") ||
					allhours.equals("") || musthours.equals("")||selectedhours.equals("") || inclasshours.equals("") || 
					experimenthours.equals("") || allcredits.equals("") || mustcredits.equals("") || selectedcredits.equals("") ||
					concentratedpracticecredits.equals("") || inclasscredits.equals("") || experimentcredits.equals("") ||
					outclassactivitycredits.equals(""))
				isnull = 1;
			
			
			if(mi_majorcodeinschool.equals("") && mi_majornameinschool.equals("") && mi_majorname.equals("") && 
					mi_majorcode.equals("") && mi_codeversion.equals("") && mi_majorfieldnum.equals("") &&
					mi_majorfieldname.equals("") && mi_departmentnumber.equals("") && mi_departmentname.equals("") && mi_leaderid.equals("") &&
					mi_leadername.equals("") && mi_admissionstate.equals("") && mi_majorspecialty.equals("") && mi_traininggoal.equals("") &&
					schoolingyear.equals("") && mi_setupyear.equals("") && mi_isnew.equals("") &&
					allhours.equals("") && musthours.equals("")&&selectedhours.equals("") && inclasshours.equals("") && 
					experimenthours.equals("") && allcredits.equals("") && mustcredits.equals("") && selectedcredits.equals("") &&
					concentratedpracticecredits.equals("") && inclasscredits.equals("") && experimentcredits.equals("") &&
					outclassactivitycredits.equals(""))
				return;

			
			
			MajorInfo majorInfo = new MajorInfo(mi_majorcodeinschool, mi_majornameinschool, mi_codeversion, mi_majorcode, mi_majorname, mi_majorfieldnum, mi_majorfieldname, mi_departmentnumber, mi_departmentname, mi_leaderid, mi_leadername, mi_admissionstate, mi_majorspecialty, mi_traininggoal, mi_schoolingyear, mi_setupyear, mi_isnew, mi_allhours, mi_musthours, mi_selectedhours, mi_inclasshours, mi_experimenthours, mi_allcredits, mi_mustcredits, mi_selectedcredits, mi_concentratedpracticecredits, mi_inclasscredits, mi_experimentcredits, mi_outclassactivitycredits, mi_serialnumber, mi_college, "", isnull);
			
			majorInfoDao.addMajorInfo(majorInfo);
			
			out.print(true);
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
	}
}
