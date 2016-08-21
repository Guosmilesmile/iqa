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

import cn.edu.xmu.dao.CombinedTrainAndOtherTeachLinkDao;
import cn.edu.xmu.daoimpl.CombinedTrainAndOtherTeachLinkDaoImpl;
import cn.edu.xmu.table.CombinedTrainAndOtherTeachLinkTable;

/**
 * Servlet implementation class UpdateForeignExchangServlet
 */
@WebServlet("/Sec_UpdateCombinedTrainAndOtherTeachLinkServlet")
public class Sec_UpdateCombinedTrainAndOtherTeachLinkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_UpdateCombinedTrainAndOtherTeachLinkServlet() {
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
		String patter = request.getParameter("patter");
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		try {
			if (patter!=null && "batch".equals(patter)) {
				System.out.println("批量更新");
				//审核部分更新，可以批量
				data="["+data+"]";
				JSONArray jsons = new JSONArray(data);
				
				for (int i = 0; i < jsons.length(); i++) {					
					  
					   int ctaotl_id = jsons.getJSONObject(i).getInt(CombinedTrainAndOtherTeachLinkTable.CTAOTL_ID);
					   
					  	String ctaotl_comments = jsons.getJSONObject(i).getString(CombinedTrainAndOtherTeachLinkTable.CTAOTL_COMMENTS);//审核意见
									
					    Map<String,String> params= new HashMap<String, String>();
					    params.put(CombinedTrainAndOtherTeachLinkTable.CTAOTL_ID, ctaotl_id+"");
					  
					    params.put(CombinedTrainAndOtherTeachLinkTable.CTAOTL_COMMENTS, ctaotl_comments);
					    				
					CombinedTrainAndOtherTeachLinkDao combinedTrainAndOtherTeachLinkDao = new CombinedTrainAndOtherTeachLinkDaoImpl();
					int result = combinedTrainAndOtherTeachLinkDao.alterCombinedTrainAndOtherTeachLink(params, ctaotl_id+"");
					out.print(result);
				}
			}else {
				
				//普通更新
				JSONObject json = new JSONObject(data);
						    
			    int ctaotl_id = json.getInt(CombinedTrainAndOtherTeachLinkTable.CTAOTL_ID);
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
						|| "".equals(ctaotl_graduatestudent) || "".equals(ctaotl_ct_t_total) || "".equals(ctaotl_ct_t_fullteacher) || "".equals(ctaotl_ct_t_partteacher)
						|| "".equals(ctaotl_ct_t_senior) || "".equals(ctaotl_ct_t_subsenior) || "".equals(ctaotl_ct_p_total) || "".equals(ctaotl_ct_p_socialfinish) || "".equals(ctaotl_ct_p_fromscience)
						|| "".equals(ctaotl_ct_p_fromproduce) || "".equals(ctaotl_ct_p_fromsocial) || "".equals( ctaotl_ct_p_other) || "".equals(ctaotl_ot_pro_credit) || "".equals( ctaotl_ot_pro_days)
						|| "".equals(ctaotl_ot_pra_credit) || "".equals(ctaotl_ot_pra_days) || "".equals(ctaotl_ot_ter_credit) || "".equals(ctaotl_ot_ter_days) || "".equals(ctaotl_ot_the_credit)
						|| "".equals(ctaotl_ot_the_days) || "".equals(ctaotl_ot_oth_credit) || "".equals(ctaotl_ot_oth_days) || "".equals(ctaotl_ot_tot_credit) || "".equals(ctaotl_ot_tot_days))
				{
					ctaotl_isnull = 1;
				}
			  	String ctaotl_comments = json.getString(CombinedTrainAndOtherTeachLinkTable.CTAOTL_COMMENTS);//审核意见
										
			    Map<String,String> params= new HashMap<String, String>();
			    params.put(CombinedTrainAndOtherTeachLinkTable.CTAOTL_ID, ctaotl_id+"");
			    params.put(CombinedTrainAndOtherTeachLinkTable.CTAOTL_COLLEGENAME, ctaotl_collegename);
			    params.put(CombinedTrainAndOtherTeachLinkTable.CTAOTL_MAJOR, ctaotl_major);
			    params.put(CombinedTrainAndOtherTeachLinkTable.CTAOTL_MAJORNUMBER, ctaotl_majornumber);
			    params.put(CombinedTrainAndOtherTeachLinkTable.CTAOTL_GRADUATESTUDENT, ctaotl_graduatestudent+"");
			    params.put(CombinedTrainAndOtherTeachLinkTable.CTAOTL_CT_T_TOTAL, ctaotl_ct_t_total+"");
			    params.put(CombinedTrainAndOtherTeachLinkTable.CTAOTL_CT_T_FULLTEACHER, ctaotl_ct_t_fullteacher+"");
			    params.put(CombinedTrainAndOtherTeachLinkTable.CTAOTL_CT_T_PARTTEACHER, ctaotl_ct_t_partteacher+"");
			    params.put(CombinedTrainAndOtherTeachLinkTable.CTAOTL_CT_T_SENIOR, ctaotl_ct_t_senior+"");
			    params.put(CombinedTrainAndOtherTeachLinkTable.CTAOTL_CT_T_SUBSENIOR, ctaotl_ct_t_subsenior+"");
			    params.put(CombinedTrainAndOtherTeachLinkTable.CTAOTL_CT_P_TOTAL, ctaotl_ct_p_total+"");
			    params.put(CombinedTrainAndOtherTeachLinkTable.CTAOTL_CT_P_SOCIALFINISH, ctaotl_ct_p_socialfinish+"");
			    params.put(CombinedTrainAndOtherTeachLinkTable.CTAOTL_CT_P_FROMSCIENCE, ctaotl_ct_p_fromscience+"");
			    params.put(CombinedTrainAndOtherTeachLinkTable.CTAOTL_CT_P_FROMPRODUCE, ctaotl_ct_p_fromproduce+"");
			    params.put(CombinedTrainAndOtherTeachLinkTable.CTAOTL_CT_P_FROMSOCIAL, ctaotl_ct_p_fromsocial+"");
			    params.put(CombinedTrainAndOtherTeachLinkTable.CTAOTL_CT_P_OTHER, ctaotl_ct_p_other+"");
			    
			    params.put(CombinedTrainAndOtherTeachLinkTable.CTAOTL_OT_PRO_CREDIT, ctaotl_ot_pro_credit+"");
			    params.put(CombinedTrainAndOtherTeachLinkTable.CTAOTL_OT_PRO_DAYS, ctaotl_ot_pro_days+"");
			    params.put(CombinedTrainAndOtherTeachLinkTable.CTAOTL_OT_PRA_CREDIT, ctaotl_ot_pra_credit+"");
			    params.put(CombinedTrainAndOtherTeachLinkTable.CTAOTL_OT_PRA_DAYS, ctaotl_ot_pra_days+"");
			    params.put(CombinedTrainAndOtherTeachLinkTable.CTAOTL_OT_TER_CREDIT, ctaotl_ot_ter_credit+"");
			    params.put(CombinedTrainAndOtherTeachLinkTable.CTAOTL_OT_TER_DAYS, ctaotl_ot_ter_days+"");					
			    params.put(CombinedTrainAndOtherTeachLinkTable.CTAOTL_OT_THE_CREDIT, ctaotl_ot_the_credit+"");
			    params.put(CombinedTrainAndOtherTeachLinkTable.CTAOTL_OT_THE_DAYS, ctaotl_ot_the_days+"");
			    params.put(CombinedTrainAndOtherTeachLinkTable.CTAOTL_OT_OTH_CREDIT, ctaotl_ot_oth_credit+"");
			    params.put(CombinedTrainAndOtherTeachLinkTable.CTAOTL_OT_OTH_DAYS, ctaotl_ot_oth_days+"");
			    params.put(CombinedTrainAndOtherTeachLinkTable.CTAOTL_OT_TOT_CREDIT, ctaotl_ot_tot_credit+"");
			    params.put(CombinedTrainAndOtherTeachLinkTable.CTAOTL_OT_TOT_DAYS, ctaotl_ot_tot_days+"");
			    params.put(CombinedTrainAndOtherTeachLinkTable.CTAOTL_COMMENTS, ctaotl_comments);
			    params.put(CombinedTrainAndOtherTeachLinkTable.CTAOTL_ISNULL, ctaotl_isnull+"");
			
			    CombinedTrainAndOtherTeachLinkDao combinedTrainAndOtherTeachLinkDao = new CombinedTrainAndOtherTeachLinkDaoImpl();
			    int result = combinedTrainAndOtherTeachLinkDao.alterCombinedTrainAndOtherTeachLink(params, ctaotl_id+"");
			    out.print(result);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
		
	}

}
