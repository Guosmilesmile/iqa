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

import cn.edu.xmu.dao.UndergraduateActivityProjectDao;
import cn.edu.xmu.daoimpl.UndergraduateActivityProjectDaoImpl;
import cn.edu.xmu.table.UndergraduateActivityProjectTable;

/**
 * Servlet implementation class Sec_UpdateUndergraduateActivityProject
 */
@WebServlet("/UpdateUndergraduateActivityProject")
public class Sec_UpdateUndergraduateActivityProject extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_UpdateUndergraduateActivityProject() {
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
		//String college = request.getParameter(UndergraduateActivityProjectTable.UAP_COLLEGE);
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
					String uap_id = jsons.getJSONObject(i).getString(UndergraduateActivityProjectTable.UAP_ID);
					String uap_comments = jsons.getJSONObject(i).getString(UndergraduateActivityProjectTable.UAP_COMMENTS);
				
					Map<String,String> params= new HashMap<String, String>();
					params.put(UndergraduateActivityProjectTable.UAP_ID, uap_id);
					params.put(UndergraduateActivityProjectTable.UAP_COMMENTS, uap_comments);
				
					UndergraduateActivityProjectDao uapDao = new UndergraduateActivityProjectDaoImpl();
					int result = uapDao.alterUndergraduateActivityProject(params, uap_id);
				
					out.print(result);
				}
			}
			else {
				JSONObject json = new JSONObject(data);
			
				String uap_id = json.getString(UndergraduateActivityProjectTable.UAP_ID);
				
				String uap_projectname = json.getString(UndergraduateActivityProjectTable.UAP_PROJECTNAME);
				String uap_unit = json.getString(UndergraduateActivityProjectTable.UAP_UNIT);
				String uap_projecttype = json.getString(UndergraduateActivityProjectTable.UAP_PROJECTTYPE);
				String uap_personnumber = json.getString(UndergraduateActivityProjectTable.UAP_PERSONNUMBER);
				if(uap_personnumber.equals(""))
					uap_personnumber = "-999";
				String uap_majornumber = json.getString(UndergraduateActivityProjectTable.UAP_MAJORNUMBER);
				if(uap_majornumber.equals(""))
					uap_majornumber = "-999";
				

				String uap_projectnumber = json.getString(UndergraduateActivityProjectTable.UAP_PROJECTNUMBER);
				if(uap_projectnumber.equals(""))
					uap_projectnumber = "-999";
				String uap_awardgrade = json.getString(UndergraduateActivityProjectTable.UAP_AWARDGRADE);
				String uap_awardtype = json.getString(UndergraduateActivityProjectTable.UAP_AWARDTYPE);
				String uap_awarddate = json.getString(UndergraduateActivityProjectTable.UAP_AWARDDATE);
				if(uap_awarddate.equals(""))
					uap_awarddate = "1800-01-01";
				
				//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
				int isnull = 0;
				if(uap_projectname.equals("") || uap_unit.equals("") || uap_projecttype.equals("") ||
						uap_personnumber.equals("-999") || uap_majornumber.equals("-999") || uap_projectnumber.equals("-999") ||
						uap_awardgrade.equals("") || uap_awardtype.equals("") || uap_awarddate.equals("1800-01-01"))
					isnull = 1;
				
				Map<String,String> params= new HashMap<String, String>();
				params.put(UndergraduateActivityProjectTable.UAP_ID, uap_id);
				
				params.put(UndergraduateActivityProjectTable.UAP_PROJECTNAME, uap_projectname);
				params.put(UndergraduateActivityProjectTable.UAP_UNIT, uap_unit);
				params.put(UndergraduateActivityProjectTable.UAP_PROJECTTYPE, uap_projecttype);
				params.put(UndergraduateActivityProjectTable.UAP_PERSONNUMBER, uap_personnumber);
				params.put(UndergraduateActivityProjectTable.UAP_MAJORNUMBER, uap_majornumber);

				params.put(UndergraduateActivityProjectTable.UAP_PROJECTNUMBER, uap_projectnumber);
				params.put(UndergraduateActivityProjectTable.UAP_AWARDGRADE, uap_awardgrade);
				params.put(UndergraduateActivityProjectTable.UAP_AWARDTYPE, uap_awardtype);
				params.put(UndergraduateActivityProjectTable.UAP_AWARDDATE, uap_awarddate);
				params.put(UndergraduateActivityProjectTable.ISNULL, isnull+"");
			
				UndergraduateActivityProjectDao uapDao = new UndergraduateActivityProjectDaoImpl();
				int result = uapDao.alterUndergraduateActivityProject(params, uap_id);
			
				out.print(result);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
	}

}
