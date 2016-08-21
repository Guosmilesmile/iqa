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

import cn.edu.xmu.dao.TeacherTrainingExchangeDao;
import cn.edu.xmu.daoimpl.TeacherTrainingExchangeDaoImpl;
import cn.edu.xmu.table.TeacherTrainingExchangeTable;

/**
 * Servlet implementation class Sec_UpdateTeacherTrainingExchange
 */
@WebServlet("/UpdateTeacherTrainingExchange")
public class Sec_UpdateTeacherTrainingExchange extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_UpdateTeacherTrainingExchange() {
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
		//解码
		//String college = request.getParameter(TeacherTrainingExchangeTable.TTE_COLLEGE);
		//college = URLDecoder.decode(college,"UTF-8");
		
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		try {
			if(patter!=null && "batch".equals(patter))
			{
				System.out.println("批量更新");
				//审核部分更新，可以批量
				data="["+data+"]";
				JSONArray jsons = new JSONArray(data);
				
				for (int i = 0; i < jsons.length(); i++) {
					//无serialnumber,deadline,college
					String tte_id = jsons.getJSONObject(i).getString(TeacherTrainingExchangeTable.TTE_ID);
					String tte_comments = jsons.getJSONObject(i).getString(TeacherTrainingExchangeTable.TTE_COMMENTS);;
					
					Map<String,String> params= new HashMap<String, String>();
					params.put(TeacherTrainingExchangeTable.TTE_ID, tte_id);
					params.put(TeacherTrainingExchangeTable.TTE_COMMENTS, tte_comments);
				
					TeacherTrainingExchangeDao tteDao = new TeacherTrainingExchangeDaoImpl();
					int result = tteDao.alterTeacherTrainingExchange(params, tte_id);
				
					out.print(result);
				}
			}
			else {
				JSONObject json = new JSONObject(data);
			
				String tte_id = json.getString(TeacherTrainingExchangeTable.TTE_ID);
				String tte_departmentname = json.getString(TeacherTrainingExchangeTable.TTE_DEPARTMENTNAME);
				String tte_departmentnumber = json.getString(TeacherTrainingExchangeTable.TTE_DEPARTMENTNUMBER);
				String tte_trainchurchyard = json.getString(TeacherTrainingExchangeTable.TTE_TRAINCHURCHYARD);
				if(tte_trainchurchyard.equals(""))
					tte_trainchurchyard = "-999";
				String tte_trainoverseassum = json.getString(TeacherTrainingExchangeTable.TTE_TRAINOVERSEASSUM);
				if(tte_trainoverseassum.equals(""))
					tte_trainoverseassum = "-999";
				String tte_trainoverseasover3 = json.getString(TeacherTrainingExchangeTable.TTE_TRAINOVERSEASOVER3);
				if(tte_trainoverseasover3.equals(""))
					tte_trainoverseasover3 = "-999";
				String tte_traintrade = json.getString(TeacherTrainingExchangeTable.TTE_TRAINTRADE);
				if(tte_traintrade.equals(""))
					tte_traintrade = "-999";
				
				String tte_trainfordoctor = json.getString(TeacherTrainingExchangeTable.TTE_TRAINFORDOCTOR);
				if(tte_trainfordoctor.equals(""))
					tte_trainfordoctor = "-999";
				String tte_trainformaster = json.getString(TeacherTrainingExchangeTable.TTE_TRAINFORMASTER);
				if(tte_trainformaster.equals(""))
					tte_trainformaster = "-999";
				int tte_trainfordegreesum = -999;
				if(!tte_trainfordoctor.equals("-999") && !tte_trainformaster.equals("-999"))
					tte_trainfordegreesum = Integer.valueOf(tte_trainfordoctor)+Integer.valueOf(tte_trainformaster);
				
				String tte_exchangecomechurchyard = json.getString(TeacherTrainingExchangeTable.TTE_EXCHANGECOMECHURCHYARD);
				if(tte_exchangecomechurchyard.equals(""))
					tte_exchangecomechurchyard = "-999";
				String tte_exchangecomeoversea = json.getString(TeacherTrainingExchangeTable.TTE_EXCHANGECOMEOVERSEA);
				if(tte_exchangecomeoversea.equals(""))
					tte_exchangecomeoversea = "-999";
				String tte_exchangevisitchurchyard = json.getString(TeacherTrainingExchangeTable.TTE_EXCHANGEVISITCHURCHYARD);
				if(tte_exchangevisitchurchyard.equals(""))
					tte_exchangevisitchurchyard = "-999";
				String tte_exchangevisitoversea = json.getString(TeacherTrainingExchangeTable.TTE_EXCHANGEVISITOVERSEA);
				if(tte_exchangevisitoversea.equals(""))
					tte_exchangevisitoversea = "-999";
				
				//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
				int isnull = 0;
				if(tte_departmentname.equals("") || tte_departmentnumber.equals("") || tte_trainchurchyard.equals("-999") ||
						tte_trainoverseassum.equals("-999") || tte_trainoverseasover3.equals("-999") || tte_traintrade.equals("-999") ||
						tte_trainfordoctor.equals("-999") || tte_trainformaster.equals("-999") || tte_exchangecomechurchyard.equals("-999") ||
						tte_exchangecomeoversea.equals("-999") || tte_exchangevisitchurchyard.equals("-999") || tte_exchangevisitoversea.equals("-999"))
					isnull = 1;
				
				Map<String,String> params= new HashMap<String, String>();
				params.put(TeacherTrainingExchangeTable.TTE_ID, tte_id);
				params.put(TeacherTrainingExchangeTable.TTE_DEPARTMENTNAME, tte_departmentname);
				params.put(TeacherTrainingExchangeTable.TTE_DEPARTMENTNUMBER, tte_departmentnumber);
				params.put(TeacherTrainingExchangeTable.TTE_TRAINCHURCHYARD, tte_trainchurchyard);
				params.put(TeacherTrainingExchangeTable.TTE_TRAINOVERSEASSUM, tte_trainoverseassum);
				params.put(TeacherTrainingExchangeTable.TTE_TRAINOVERSEASOVER3, tte_trainoverseasover3);
				params.put(TeacherTrainingExchangeTable.TTE_TRAINTRADE, tte_traintrade);
				params.put(TeacherTrainingExchangeTable.TTE_TRAINFORDEGREESUM, tte_trainfordegreesum+"");
				params.put(TeacherTrainingExchangeTable.TTE_TRAINFORDOCTOR, tte_trainfordoctor);
				params.put(TeacherTrainingExchangeTable.TTE_TRAINFORMASTER, tte_trainformaster);
				params.put(TeacherTrainingExchangeTable.TTE_EXCHANGECOMECHURCHYARD, tte_exchangecomechurchyard);
				params.put(TeacherTrainingExchangeTable.TTE_EXCHANGECOMEOVERSEA, tte_exchangecomeoversea);
				params.put(TeacherTrainingExchangeTable.TTE_EXCHANGEVISITCHURCHYARD, tte_exchangevisitchurchyard);
				params.put(TeacherTrainingExchangeTable.TTE_EXCHANGEVISITOVERSEA, tte_exchangevisitoversea);
				params.put(TeacherTrainingExchangeTable.ISNULL, isnull+"");
			
				TeacherTrainingExchangeDao tteDao = new TeacherTrainingExchangeDaoImpl();
				int result = tteDao.alterTeacherTrainingExchange(params, tte_id);
			
				out.print(result);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
	}

}
