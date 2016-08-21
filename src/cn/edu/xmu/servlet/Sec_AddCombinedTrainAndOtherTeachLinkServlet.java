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

import cn.edu.xmu.dao.CombinedTrainAndOtherTeachLinkDao;
import cn.edu.xmu.daoimpl.CombinedTrainAndOtherTeachLinkDaoImpl;
import cn.edu.xmu.entity.CombinedTrainAndOtherTeachLink;
import cn.edu.xmu.table.CombinedTrainAndOtherTeachLinkTable;

/**
 * Servlet implementation class AddCombinedTrainAndOtherTeachLinkServlet
 */
@WebServlet("/Sec_AddCombinedTrainAndOtherTeachLinkServlet")
public class Sec_AddCombinedTrainAndOtherTeachLinkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_AddCombinedTrainAndOtherTeachLinkServlet() {
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
			
			String ctaotl_collegename = json.getString(CombinedTrainAndOtherTeachLinkTable.CTAOTL_COLLEGENAME);  //学院
			
		    String ctaotl_major = json.getString(CombinedTrainAndOtherTeachLinkTable.CTAOTL_MAJOR); //专业
		    String ctaotl_majornumber = json.getString(CombinedTrainAndOtherTeachLinkTable.CTAOTL_MAJORNUMBER);  //专业代码
		    
		    String graduatestudent = json.getString(CombinedTrainAndOtherTeachLinkTable.CTAOTL_GRADUATESTUDENT); //毕业班人数
		    Integer ctaotl_graduatestudent = -1;
		    if(!"".equals(graduatestudent)) ctaotl_graduatestudent = Integer.parseInt(graduatestudent);
		    String ct_t_total = json.getString(CombinedTrainAndOtherTeachLinkTable.CTAOTL_CT_T_TOTAL); //分专业毕业综合训练/指导毕业综合训练教师数/总数
		    Integer ctaotl_ct_t_total = -1;
		    if(!"".equals(ct_t_total)) ctaotl_ct_t_total = Integer.parseInt(ct_t_total);
		    
		    String ct_t_fullteacher = json.getString(CombinedTrainAndOtherTeachLinkTable.CTAOTL_CT_T_FULLTEACHER);//分专业毕业综合训练/指导毕业综合训练教师数/专任教师
		    Integer ctaotl_ct_t_fullteacher = -1;
		    if(!"".equals(ct_t_fullteacher)) ctaotl_ct_t_fullteacher = Integer.parseInt(ct_t_fullteacher);
		    String ct_t_partteacher = json.getString(CombinedTrainAndOtherTeachLinkTable.CTAOTL_CT_T_PARTTEACHER); //分专业毕业综合训练/指导毕业综合训练教师数/外聘教师
		    Integer ctaotl_ct_t_partteacher = -1;
		    if(!"".equals(ct_t_partteacher)) ctaotl_ct_t_partteacher = Integer.parseInt(ct_t_partteacher);
		    
		    String ct_t_senior = json.getString(CombinedTrainAndOtherTeachLinkTable.CTAOTL_CT_T_SENIOR); //分专业毕业综合训练/指导毕业综合训练教师数/正高级
		    Integer ctaotl_ct_t_senior = -1;
		    if(!"".equals(ct_t_senior)) ctaotl_ct_t_senior = Integer.parseInt(ct_t_senior);
		    String ct_t_subsenior = json.getString(CombinedTrainAndOtherTeachLinkTable.CTAOTL_CT_T_SUBSENIOR); //分专业毕业综合训练/指导毕业综合训练教师数/副高级
		    Integer ctaotl_ct_t_subsenior = -1;
		    if(!"".equals(ct_t_subsenior)) ctaotl_ct_t_subsenior = Integer.parseInt(ct_t_subsenior);
		    
		    String ct_p_total = json.getString(CombinedTrainAndOtherTeachLinkTable.CTAOTL_CT_P_TOTAL);  //分专业毕业综合训练/毕业综合训练课题/总数
		    Integer ctaotl_ct_p_total = -1;
		    if(!"".equals(ct_p_total)) ctaotl_ct_p_total = Integer.parseInt(ct_p_total);
		    String ct_p_socialfinish = json.getString(CombinedTrainAndOtherTeachLinkTable.CTAOTL_CT_P_SOCIALFINISH);//分专业毕业综合训练/毕业综合训练课题/在实验、实习、工程实践和社会调查等社会实践中完成数
		    Integer ctaotl_ct_p_socialfinish = -1;
		    if(!"".equals(ct_p_socialfinish)) ctaotl_ct_p_socialfinish = Integer.parseInt(ct_p_socialfinish);
		    
		    String ct_p_fromscience = json.getString(CombinedTrainAndOtherTeachLinkTable.CTAOTL_CT_P_FROMSCIENCE); //分专业毕业综合训练/毕业综合训练课题/来自教师科研课题
		    Integer ctaotl_ct_p_fromscience = -1;
		    if(!"".equals(ct_p_fromscience)) ctaotl_ct_p_fromscience = Integer.parseInt(ct_p_fromscience);
		    String ct_p_fromproduce = json.getString(CombinedTrainAndOtherTeachLinkTable.CTAOTL_CT_P_FROMPRODUCE); //分专业毕业综合训练/毕业综合训练课题/来自生产管理一线
		    Integer ctaotl_ct_p_fromproduce = -1;
		    if(!"".equals(ct_p_fromproduce)) ctaotl_ct_p_fromproduce = Integer.parseInt(ct_p_fromproduce);
		    
		    String ct_p_fromsocial = json.getString(CombinedTrainAndOtherTeachLinkTable.CTAOTL_CT_P_FROMSOCIAL); //分专业毕业综合训练/毕业综合训练课题/来自社会实践
		    Integer ctaotl_ct_p_fromsocial = -1;
		    if(!"".equals(ct_p_fromsocial)) ctaotl_ct_p_fromsocial = Integer.parseInt(ct_p_fromsocial);
		    String ct_p_other = json.getString(CombinedTrainAndOtherTeachLinkTable.CTAOTL_CT_P_OTHER); //分专业毕业综合训练/毕业综合训练课题/其他
		    Integer ctaotl_ct_p_other = -1;
		    if(!"".equals(ct_p_other)) ctaotl_ct_p_other = Integer.parseInt(ct_p_other);
		    
		    String ot_pro_credit = json.getString(CombinedTrainAndOtherTeachLinkTable.CTAOTL_OT_PRO_CREDIT); //其他教学环节/见习或认识实习/学分
		    Float ctaotl_ot_pro_credit = (float) -1.0;
		    if(!"".equals(ot_pro_credit)) ctaotl_ot_pro_credit = Float.parseFloat(ot_pro_credit);
		    String ot_pro_days = json.getString(CombinedTrainAndOtherTeachLinkTable.CTAOTL_OT_PRO_DAYS); //其他教学环节/见习或认识实习/天数
		    Integer ctaotl_ot_pro_days = -1;
		    if(!"".equals(ot_pro_days)) ctaotl_ot_pro_days = Integer.parseInt(ot_pro_days);
		    
		    String ot_pra_credit = json.getString(CombinedTrainAndOtherTeachLinkTable.CTAOTL_OT_PRA_CREDIT); //其他教学环节/毕业实习/学分
		    Float ctaotl_ot_pra_credit = (float) -1.0;
		    if(!"".equals(ot_pra_credit)) ctaotl_ot_pra_credit = Float.parseFloat(ot_pra_credit);
		    String ot_pra_days = json.getString(CombinedTrainAndOtherTeachLinkTable.CTAOTL_OT_PRA_DAYS); //其他教学环节/毕业实习/天数
		    Integer ctaotl_ot_pra_days = -1;
		    if(!"".equals(ot_pra_days)) ctaotl_ot_pra_days = Integer.parseInt(ot_pra_days);
		    
		    String ot_ter_credit = json.getString(CombinedTrainAndOtherTeachLinkTable.CTAOTL_OT_TER_CREDIT); //其他教学环节/学年论文/学分 
		    Float ctaotl_ot_ter_credit = (float) -1.0;
		    if(!"".equals(ot_ter_credit)) ctaotl_ot_ter_credit = Float.parseFloat(ot_ter_credit);
		    String ot_ter_days = json.getString(CombinedTrainAndOtherTeachLinkTable.CTAOTL_OT_TER_DAYS); //其他教学环节/学年论文/天数
		    Integer ctaotl_ot_ter_days = -1;
		    if(!"".equals(ot_ter_days)) ctaotl_ot_ter_days = Integer.parseInt(ot_ter_days);
		    
		    String ot_the_credit = json.getString(CombinedTrainAndOtherTeachLinkTable.CTAOTL_OT_THE_CREDIT); //其他教学环节/毕业论文/学分
		    Float ctaotl_ot_the_credit = (float) -1.0;
		    if(!"".equals(ot_the_credit)) ctaotl_ot_the_credit = Float.parseFloat(ot_the_credit);
		    String ot_the_days = json.getString(CombinedTrainAndOtherTeachLinkTable.CTAOTL_OT_THE_DAYS); //其他教学环节/毕业论文/天数
		    Integer ctaotl_ot_the_days = -1;
		    if(!"".equals(ot_the_days)) ctaotl_ot_the_days = Integer.parseInt(ot_the_days);
		    
		    String ot_oth_credit = json.getString(CombinedTrainAndOtherTeachLinkTable.CTAOTL_OT_OTH_CREDIT); //其他教学环节/其他/学分
		    Float ctaotl_ot_oth_credit = (float) -1.0;
		    if(!"".equals(ot_oth_credit)) ctaotl_ot_oth_credit = Float.parseFloat(ot_oth_credit);
		    String ot_oth_days = json.getString(CombinedTrainAndOtherTeachLinkTable.CTAOTL_OT_OTH_DAYS); //其他教学环节/其他/天数
		    Integer ctaotl_ot_oth_days = -1;
		    if(!"".equals(ot_oth_days)) ctaotl_ot_oth_days = Integer.parseInt(ot_oth_days);
		    
		    String ot_tot_credit = json.getString(CombinedTrainAndOtherTeachLinkTable.CTAOTL_OT_TOT_CREDIT); //其他教学环节/合计/学分
		    Float ctaotl_ot_tot_credit = (float) -1.0;
		    if(!"".equals(ot_tot_credit)) ctaotl_ot_tot_credit = Float.parseFloat(ot_tot_credit);
		    String ot_tot_days = json.getString(CombinedTrainAndOtherTeachLinkTable.CTAOTL_OT_TOT_DAYS);  //其他教学环节/合计/天数
		    Integer ctaotl_ot_tot_days = -1;
		    if(!"".equals(ot_tot_days)) ctaotl_ot_tot_days = Integer.parseInt(ot_tot_days);
		    
		    int ctaotl_isnull = 0;
			if("".equals(ctaotl_collegename) || "".equals(ctaotl_major) || "".equals(ctaotl_majornumber)
					|| "".equals(graduatestudent) || "".equals(ct_t_total) || "".equals(ct_t_fullteacher) || "".equals(ct_t_partteacher)
					|| "".equals(ct_t_senior) || "".equals(ct_t_subsenior) || "".equals(ct_p_total) || "".equals(ct_p_socialfinish) || "".equals(ct_p_fromscience)
					|| "".equals(ct_p_fromproduce) || "".equals(ct_p_fromsocial) || "".equals( ct_p_other) || "".equals(ot_pro_credit) || "".equals( ot_pro_days)
					|| "".equals(ot_pra_credit) || "".equals(ot_pra_days) || "".equals(ot_ter_credit) || "".equals(ot_ter_days) || "".equals(ot_the_credit)
					|| "".equals(ot_the_days) || "".equals(ot_oth_credit) || "".equals(ot_oth_days) || "".equals(ot_tot_credit) || "".equals(ot_tot_days))
			{
				ctaotl_isnull = 1;
			}
			if ("".equals(ctaotl_collegename) && "".equals(ctaotl_major) && "".equals(ctaotl_majornumber)
					&& "".equals(graduatestudent) && "".equals(ct_t_total) && "".equals(ct_t_fullteacher) && "".equals(ct_t_partteacher)
					&& "".equals(ct_t_senior) && "".equals(ct_t_subsenior) && "".equals(ct_p_total) && "".equals(ct_p_socialfinish) && "".equals(ct_p_fromscience)
					&& "".equals(ct_p_fromproduce) && "".equals(ct_p_fromsocial) && "".equals(ct_p_other) && "".equals(ot_pro_credit) && "".equals( ot_pro_days)
					&& "".equals(ot_pra_credit) && "".equals(ot_pra_days) && "".equals(ot_ter_credit) && "".equals(ot_ter_days) && "".equals(ot_the_credit)
					&& "".equals(ot_the_days) && "".equals(ot_oth_credit) && "".equals(ot_oth_days) && "".equals(ot_tot_credit) && "".equals(ot_tot_days))
			{
				result = -1;
				out.println(result);
				return;
			}	  	
			String ctaotl_college = request.getParameter(CombinedTrainAndOtherTeachLinkTable.CTAOTL_COLLEGE);  //学院
			ctaotl_college = URLDecoder.decode(ctaotl_college,"UTF-8");
			
			CombinedTrainAndOtherTeachLink combinedTrainAndOtherTeachLink = new CombinedTrainAndOtherTeachLink(
					ctaotl_collegename,ctaotl_major,ctaotl_majornumber,
		            ctaotl_graduatestudent,ctaotl_ct_t_total,ctaotl_ct_t_fullteacher,ctaotl_ct_t_partteacher,
		            ctaotl_ct_t_senior,ctaotl_ct_t_subsenior,ctaotl_ct_p_total,ctaotl_ct_p_socialfinish,ctaotl_ct_p_fromscience,
		            ctaotl_ct_p_fromproduce,ctaotl_ct_p_fromsocial, ctaotl_ct_p_other, ctaotl_ot_pro_credit, ctaotl_ot_pro_days,
		            ctaotl_ot_pra_credit, ctaotl_ot_pra_days, ctaotl_ot_ter_credit, ctaotl_ot_ter_days, ctaotl_ot_the_credit,
		            ctaotl_ot_the_days, ctaotl_ot_oth_credit, ctaotl_ot_oth_days, ctaotl_ot_tot_credit, ctaotl_ot_tot_days,
		            ctaotl_college, serialnumber, ctaotl_isnull);

			CombinedTrainAndOtherTeachLinkDao combinedTrainAndOtherTeachLinkDao = new CombinedTrainAndOtherTeachLinkDaoImpl();
			result = combinedTrainAndOtherTeachLinkDao.addCombinedTrainAndOtherTeachLink(combinedTrainAndOtherTeachLink);
			out.print(result);
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
	}

}
