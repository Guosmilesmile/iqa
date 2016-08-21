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

import cn.edu.xmu.dao.UndergraduateAwardLoanDao;
import cn.edu.xmu.daoimpl.UndergraduateAwardLoanDaoImpl;
import cn.edu.xmu.table.UndergraduateAwardLoanTable;

/**
 * Servlet implementation class Sec_UpdateUndergraduateAwardLoan
 */
@WebServlet("/UpdateUndergraduateAwardLoan")
public class Sec_UpdateUndergraduateAwardLoan extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_UpdateUndergraduateAwardLoan() {
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
		//String college = request.getParameter(UndergraduateAwardLoanTable.UAL_COLLEGE);
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
					String ual_id = jsons.getJSONObject(i).getString(UndergraduateAwardLoanTable.UAL_ID);
					

					String ual_comments = jsons.getJSONObject(i).getString(UndergraduateAwardLoanTable.UAL_COMMENTS);
				
					Map<String,String> params= new HashMap<String, String>();
					params.put(UndergraduateAwardLoanTable.UAL_ID, ual_id);
					
					params.put(UndergraduateAwardLoanTable.UAL_COMMENTS, ual_comments);
				
					UndergraduateAwardLoanDao ualDao = new UndergraduateAwardLoanDaoImpl();
					int result = ualDao.alterUndergraduateAwardLoan(params, ual_id);
				
					out.print(result);
				}
			}
			else {
				JSONObject json = new JSONObject(data);
			
				String ual_id = json.getString(UndergraduateAwardLoanTable.UAL_ID);
				
				String ual_govcost = json.getString(UndergraduateAwardLoanTable.UAL_GOVCOST);
				if(ual_govcost.equals(""))
					ual_govcost = "-999";
				String ual_societycost = json.getString(UndergraduateAwardLoanTable.UAL_SOCIETYCOST);
				if(ual_societycost.equals(""))
					ual_societycost = "-999";
				String ual_schoolcost = json.getString(UndergraduateAwardLoanTable.UAL_SCHOOLCOST);
				if(ual_schoolcost.equals(""))
					ual_schoolcost = "-999";
				String ual_countrycost = json.getString(UndergraduateAwardLoanTable.UAL_COUNTRYCOST);
				if(ual_countrycost.equals(""))
					ual_countrycost = "-999";
				String ual_workstudycost = json.getString(UndergraduateAwardLoanTable.UAL_WORKSTUDYCOST);
				if(ual_workstudycost.equals(""))
					ual_workstudycost = "-999";
				String ual_deratecost = json.getString(UndergraduateAwardLoanTable.UAL_DERATECOST);
				if(ual_deratecost.equals(""))
					ual_deratecost = "-999";
				String ual_troubleaidcost = json.getString(UndergraduateAwardLoanTable.UAL_TROUBLEAIDCOST);
				if(ual_troubleaidcost.equals(""))
					ual_troubleaidcost = "-999";
				float ual_sumcost = -999;
				if(!ual_govcost.equals("-999") && !ual_societycost.equals("-999") && !ual_schoolcost.equals("-999") && !ual_countrycost.equals("-999") && 
						!ual_workstudycost.equals("-999") && !ual_deratecost.equals("-999") && !ual_troubleaidcost.equals("-999"))
					ual_sumcost = Float.valueOf(ual_govcost)+Float.valueOf(ual_societycost)+Float.valueOf(ual_schoolcost)+
						Float.valueOf(ual_countrycost)+Float.valueOf(ual_workstudycost)+Float.valueOf(ual_deratecost)+Float.valueOf(ual_troubleaidcost);
				
				String ual_govcount = json.getString(UndergraduateAwardLoanTable.UAL_GOVCOUNT);
				if(ual_govcount.equals(""))
					ual_govcount = "-999";
				String ual_societycount = json.getString(UndergraduateAwardLoanTable.UAL_SOCIETYCOUNT);
				if(ual_societycount.equals(""))
					ual_societycount = "-999";
				String ual_schoolcount = json.getString(UndergraduateAwardLoanTable.UAL_SCHOOLCOUNT);
				if(ual_schoolcount.equals(""))
					ual_schoolcount = "-999";
				String ual_countrycount = json.getString(UndergraduateAwardLoanTable.UAL_COUNTRYCOUNT);
				if(ual_countrycount.equals(""))
					ual_countrycount = "-999";
				String ual_workstudycount = json.getString(UndergraduateAwardLoanTable.UAL_WORKSTUDYCOUNT);
				if(ual_workstudycount.equals(""))
					ual_workstudycount = "-999";
				String ual_deratecount = json.getString(UndergraduateAwardLoanTable.UAL_DERATECOUNT);
				if(ual_deratecount.equals(""))
					ual_deratecount = "-999";
				String ual_troubleaidcount = json.getString(UndergraduateAwardLoanTable.UAL_TROUBLEAIDCOUNT);
				if(ual_troubleaidcount.equals(""))
					ual_troubleaidcount = "-999";
				int ual_sumcount = -999;
				if(!ual_govcount.equals("-999") && !ual_societycount.equals("-999") && !ual_schoolcount.equals("-999") && !ual_countrycount.equals("-999") && 
						!ual_workstudycount.equals("-999") && !ual_deratecount.equals("-999") && !ual_troubleaidcount.equals("-999"))
					ual_sumcount = Integer.valueOf(ual_govcount)+Integer.valueOf(ual_societycount)+Integer.valueOf(ual_schoolcount)+Integer.valueOf(ual_countrycount)+
						Integer.valueOf(ual_workstudycount)+Integer.valueOf(ual_deratecount)+Integer.valueOf(ual_troubleaidcount);

				//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
				int isnull = 0;
				if(ual_govcost.equals("-999") || ual_societycost.equals("-999") || ual_schoolcost.equals("-999") || ual_countrycost.equals("-999") || 
						ual_workstudycost.equals("-999") || ual_deratecost.equals("-999") || ual_troubleaidcost.equals("-999") || ual_govcount.equals("-999") || 
						ual_societycount.equals("-999") || ual_schoolcount.equals("-999") || ual_countrycount.equals("-999") || 
						ual_workstudycount.equals("-999") || ual_deratecount.equals("-999") || ual_troubleaidcount.equals("-999"))
					isnull = 1;
				
				Map<String,String> params= new HashMap<String, String>();
				params.put(UndergraduateAwardLoanTable.UAL_ID, ual_id);
				
				params.put(UndergraduateAwardLoanTable.UAL_SUMCOST, ual_sumcost+"");
				params.put(UndergraduateAwardLoanTable.UAL_GOVCOST, ual_govcost);
				params.put(UndergraduateAwardLoanTable.UAL_SOCIETYCOST, ual_societycost);
				params.put(UndergraduateAwardLoanTable.UAL_SCHOOLCOST, ual_schoolcost);
				params.put(UndergraduateAwardLoanTable.UAL_COUNTRYCOST, ual_countrycost);
				params.put(UndergraduateAwardLoanTable.UAL_WORKSTUDYCOST, ual_workstudycost);
				params.put(UndergraduateAwardLoanTable.UAL_DERATECOST, ual_deratecost);
				params.put(UndergraduateAwardLoanTable.UAL_TROUBLEAIDCOST, ual_troubleaidcost);
				
				params.put(UndergraduateAwardLoanTable.UAL_SUMCOUNT, ual_sumcount+"");
				params.put(UndergraduateAwardLoanTable.UAL_GOVCOUNT, ual_govcount);
				params.put(UndergraduateAwardLoanTable.UAL_SOCIETYCOUNT, ual_societycount);
				params.put(UndergraduateAwardLoanTable.UAL_SCHOOLCOUNT, ual_schoolcount);
				params.put(UndergraduateAwardLoanTable.UAL_COUNTRYCOUNT, ual_countrycount);
				params.put(UndergraduateAwardLoanTable.UAL_WORKSTUDYCOUNT, ual_workstudycount);
				params.put(UndergraduateAwardLoanTable.UAL_DERATECOUNT, ual_deratecount);
				params.put(UndergraduateAwardLoanTable.UAL_TROUBLEAIDCOUNT, ual_troubleaidcount);
				params.put(UndergraduateAwardLoanTable.ISNULL, isnull+"");
				
			
				UndergraduateAwardLoanDao ualDao = new UndergraduateAwardLoanDaoImpl();
				int result = ualDao.alterUndergraduateAwardLoan(params, ual_id);
			
				out.print(result);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
	}

}
