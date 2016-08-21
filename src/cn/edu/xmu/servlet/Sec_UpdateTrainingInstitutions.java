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

import cn.edu.xmu.dao.TrainingInstitutionsDao;
import cn.edu.xmu.daoimpl.TrainingInstitutionsDaoImpl;
import cn.edu.xmu.table.TrainingInstitutionsTable;



/*
 * 4-2-2-1 专业基本情况
 */
@WebServlet("/Sec_UpdateTrainingInstitutions")
public class Sec_UpdateTrainingInstitutions extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_UpdateTrainingInstitutions() {
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
					JSONObject json = jsons.getJSONObject(i);
					Map<String,String> params= new HashMap<String, String>();
					
					
					String ti_id = json.getString(TrainingInstitutionsTable.TI_ID);
					String ti_name = json.getString(TrainingInstitutionsTable.TI_NAME);
					String ti_departmentnumber = json.getString(TrainingInstitutionsTable.TI_DEPAERTMENTNUMBER);
					String ti_departmentname  =json.getString(TrainingInstitutionsTable.TI_DEPAERTMENTNAME);
					String ti_projectname = json.getString(TrainingInstitutionsTable.TI_PROJECTNAME);
					String ti_object = json.getString(TrainingInstitutionsTable.TI_OBJECT);
					String ti_peoplecount = json.getString(TrainingInstitutionsTable.TI_PEOPLECOUNT);
					String ti_serialnumber = json.getString(TrainingInstitutionsTable.TI_SERIALNUMBER);
					String ti_comments = json.getString(TrainingInstitutionsTable.TI_COMMENTS);
					
				
					params.put(TrainingInstitutionsTable.TI_NAME, ti_name);
					params.put(TrainingInstitutionsTable.TI_DEPAERTMENTNUMBER, ti_departmentnumber);
					params.put(TrainingInstitutionsTable.TI_DEPAERTMENTNAME, ti_departmentname);
					params.put(TrainingInstitutionsTable.TI_PROJECTNAME,ti_projectname  );
					params.put(TrainingInstitutionsTable.TI_OBJECT,ti_object );
					params.put(TrainingInstitutionsTable.TI_PEOPLECOUNT, ti_peoplecount);
					params.put(TrainingInstitutionsTable.TI_SERIALNUMBER,ti_serialnumber );
					params.put(TrainingInstitutionsTable.TI_COMMENTS, ti_comments );
					TrainingInstitutionsDao tidao = new TrainingInstitutionsDaoImpl();
					int result = tidao.alterTrainingInstitutions(params, ti_id);
					
					out.print(result);
				}
			}
			else{
				JSONObject json = new JSONObject(data);
				Map<String,String> params= new HashMap<String, String>();
				
				
				String ti_id = json.getString(TrainingInstitutionsTable.TI_ID);
				String ti_name = json.getString(TrainingInstitutionsTable.TI_NAME);
				String ti_departmentnumber = json.getString(TrainingInstitutionsTable.TI_DEPAERTMENTNUMBER);
				String ti_departmentname  =json.getString(TrainingInstitutionsTable.TI_DEPAERTMENTNAME);
				String ti_projectname = json.getString(TrainingInstitutionsTable.TI_PROJECTNAME);
				String ti_object = json.getString(TrainingInstitutionsTable.TI_OBJECT);
				String ti_time = json.getString(TrainingInstitutionsTable.TI_TIME);
				String ti_peoplecount = json.getString(TrainingInstitutionsTable.TI_PEOPLECOUNT);
				String ti_serialnumber = json.getString(TrainingInstitutionsTable.TI_SERIALNUMBER);
				String ti_comments = json.getString(TrainingInstitutionsTable.TI_COMMENTS);
				
				int isnull = 0;
				if ("".equals(ti_name) || "".equals(ti_departmentnumber)
						|| "".equals(ti_departmentname)
						|| "".equals(ti_projectname) || "".equals(ti_time)
						|| "".equals(ti_object) || "".equals(ti_projectname))
					isnull = 1;
				if(ti_time.equals(""))
					ti_time="1800-01-01";
				
				params.put(TrainingInstitutionsTable.TI_NAME, ti_name);
				params.put(TrainingInstitutionsTable.TI_DEPAERTMENTNUMBER, ti_departmentnumber);
				params.put(TrainingInstitutionsTable.TI_DEPAERTMENTNAME, ti_departmentname);
				params.put(TrainingInstitutionsTable.TI_PROJECTNAME,ti_projectname  );
				params.put(TrainingInstitutionsTable.TI_OBJECT,ti_object );
				params.put(TrainingInstitutionsTable.TI_TIME,ti_time);
				params.put(TrainingInstitutionsTable.TI_PEOPLECOUNT, ti_peoplecount);
				params.put(TrainingInstitutionsTable.TI_SERIALNUMBER,ti_serialnumber );
				params.put(TrainingInstitutionsTable.TI_COMMENTS, ti_comments );
				params.put("isnull",isnull+"");
				TrainingInstitutionsDao tidao = new TrainingInstitutionsDaoImpl();
				int result = tidao.alterTrainingInstitutions(params, ti_id);
				
				out.print(result);
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
		
	}


}

