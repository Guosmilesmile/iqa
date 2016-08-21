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

import cn.edu.xmu.dao.CategoryExperimentDao;
import cn.edu.xmu.daoimpl.CategoryExperimentDaoImpl;
import cn.edu.xmu.table.CategoryExperimentTable;



@WebServlet("/UpdateCategoryExperiment")
public class Sec_UpdateCategoryExperiment extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_UpdateCategoryExperiment() {
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
					JSONObject json = jsons.getJSONObject(i);
					String ce_id = json.getString(CategoryExperimentTable.CE_ID);
					String ce_colleges = json.getString(CategoryExperimentTable.CE_COLLEGES);
					String ce_majorname = json.getString(CategoryExperimentTable.CE_MAJORNAME);
					String ce_containexperiment = json.getString(CategoryExperimentTable.CE_CONTAINEXPERIMENT);
					String ce_singleexperiment = json.getString(CategoryExperimentTable.CE_SINLGEEXPERIMENT);
					String ce_nosingleexperiment = json.getString(CategoryExperimentTable.CE_NOSINGELEEXPERIMENT);
					String ce_designexperiment = json.getString(CategoryExperimentTable.CE_DESIGNEXPERIMENT);
					String ce_percentage = json.getString(CategoryExperimentTable.CE_PERCENTAGE);
					String ce_serialnumber = json.getString(CategoryExperimentTable.CE_SERIALNUMBER);
					String ce_comments = json.getString(CategoryExperimentTable.CE_COMMENTS);
					String ce_majornumebr = json.getString(CategoryExperimentTable.CE_MAJORNUMBER);
					if ("".equals(ce_containexperiment)) {
						ce_containexperiment = "";
					}
					if ("".equals(ce_percentage)) {
						ce_percentage = "";
					}
					if ("".equals(ce_singleexperiment)) {
						ce_singleexperiment = "";
					}
					if ("".equals(ce_nosingleexperiment)) {
						ce_nosingleexperiment = "";
					}
					if ("".equals(ce_designexperiment)) {
						ce_designexperiment = "";
					}
					params.put(CategoryExperimentTable.CE_ID, ce_id);
					params.put(CategoryExperimentTable.CE_COLLEGES,ce_colleges );
					params.put(CategoryExperimentTable.CE_MAJORNAME,ce_majorname );
					params.put(CategoryExperimentTable.CE_CONTAINEXPERIMENT,ce_containexperiment );
					params.put(CategoryExperimentTable.CE_SINLGEEXPERIMENT,ce_singleexperiment );
					params.put(CategoryExperimentTable.CE_NOSINGELEEXPERIMENT,ce_nosingleexperiment);
					params.put(CategoryExperimentTable.CE_DESIGNEXPERIMENT,ce_designexperiment );
					params.put(CategoryExperimentTable.CE_PERCENTAGE,ce_percentage );
					params.put(CategoryExperimentTable.CE_SERIALNUMBER,ce_serialnumber );
					params.put(CategoryExperimentTable.CE_COMMENTS,ce_comments );
					params.put(CategoryExperimentTable.CE_MAJORNUMBER,ce_majornumebr);
					CategoryExperimentDao ceDao  = new CategoryExperimentDaoImpl();
					int result = ceDao.alterCategoryExperiment(params, ce_id);
					
					out.print(result);
				}
			}
			else{
				JSONObject json = new JSONObject(data);
				System.out.println(json);
				Map<String,String> params= new HashMap<String, String>();
				
				String ce_id = json.getString(CategoryExperimentTable.CE_ID);
				String ce_colleges = json.getString(CategoryExperimentTable.CE_COLLEGES);
				String ce_majorname = json.getString(CategoryExperimentTable.CE_MAJORNAME);
				String ce_containexperiment = json.getString(CategoryExperimentTable.CE_CONTAINEXPERIMENT);
				String ce_singleexperiment = json.getString(CategoryExperimentTable.CE_SINLGEEXPERIMENT);
				String ce_nosingleexperiment = json.getString(CategoryExperimentTable.CE_NOSINGELEEXPERIMENT);
				String ce_designexperiment = json.getString(CategoryExperimentTable.CE_DESIGNEXPERIMENT);
				String ce_percentage = json.getString(CategoryExperimentTable.CE_PERCENTAGE);
				String ce_majornumebr = json.getString(CategoryExperimentTable.CE_MAJORNUMBER);
				String ce_serialnumber = json.getString(CategoryExperimentTable.CE_SERIALNUMBER);
				String ce_comments = json.getString(CategoryExperimentTable.CE_COMMENTS);
				
				
				int isnull = 0;
				if ("".equals(ce_colleges) || "".equals(ce_majorname)
						|| "".equals(ce_containexperiment)
						|| "".equals(ce_singleexperiment)
						|| "".equals(ce_nosingleexperiment)
						|| "".equals(ce_designexperiment) || "".equals(ce_percentage)||"".equals(ce_majornumebr)
						) {
					isnull = 1;
				}
				
				params.put(CategoryExperimentTable.CE_ID, ce_id);
				params.put(CategoryExperimentTable.CE_COLLEGES,ce_colleges );
				params.put(CategoryExperimentTable.CE_MAJORNAME,ce_majorname );
				params.put(CategoryExperimentTable.CE_CONTAINEXPERIMENT,ce_containexperiment );
				params.put(CategoryExperimentTable.CE_SINLGEEXPERIMENT,ce_singleexperiment );
				params.put(CategoryExperimentTable.CE_NOSINGELEEXPERIMENT,ce_nosingleexperiment);
				params.put(CategoryExperimentTable.CE_DESIGNEXPERIMENT,ce_designexperiment );
				params.put(CategoryExperimentTable.CE_PERCENTAGE,ce_percentage );
				params.put(CategoryExperimentTable.CE_SERIALNUMBER,ce_serialnumber );
				params.put(CategoryExperimentTable.CE_COMMENTS,ce_comments );
				params.put(CategoryExperimentTable.CE_MAJORNUMBER,ce_majornumebr);
				params.put("isnull", isnull+"");
				CategoryExperimentDao ceDao  = new CategoryExperimentDaoImpl();
				int result = ceDao.alterCategoryExperiment(params, ce_id);
				
				out.print(result);
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
		
	}


}

