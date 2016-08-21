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

import cn.edu.xmu.dao.NewBooksthatYearDao;
import cn.edu.xmu.daoimpl.NewBooksthatYearDaoImpl;
import cn.edu.xmu.table.NewBooksthatYearTable;

/**
 * Servlet implementation class Sec_UpdateNewBooksthatYear
 */
@WebServlet("/UpdateNewBooksthatYear")
public class Sec_UpdateNewBooksthatYear extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_UpdateNewBooksthatYear() {
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
		//String college = request.getParameter(NewBooksthatYearTable.NBY_COLLEGE);
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
					String nby_id = jsons.getJSONObject(i).getString(NewBooksthatYearTable.NBY_ID);
					String nby_comments = jsons.getJSONObject(i).getString(NewBooksthatYearTable.NBY_COMMENTS);
				
					Map<String,String> params= new HashMap<String, String>();
					params.put(NewBooksthatYearTable.NBY_ID, nby_id);
					params.put(NewBooksthatYearTable.NBY_COMMENTS, nby_comments);
				
					NewBooksthatYearDao nbyDao = new NewBooksthatYearDaoImpl();
					int result = nbyDao.alterNewBooksthatYear(params, nby_id);
				
					out.print(result);
				}
			}
			else {
				JSONObject json = new JSONObject(data);
			
				String nby_id = json.getString(NewBooksthatYearTable.NBY_ID);
				String nby_paperbooksnumber = json.getString(NewBooksthatYearTable.NBY_PAPERBOOKSNUMBER);
				if(nby_paperbooksnumber.equals(""))
					nby_paperbooksnumber = "-999";
				String nby_ebooksnumber = json.getString(NewBooksthatYearTable.NBY_EBOOKSNUMBER);
				if(nby_ebooksnumber.equals(""))
					nby_ebooksnumber="-999";
				String nby_documentacquisitioncost = json.getString(NewBooksthatYearTable.NBY_DOCUMENTACQUISITIONCOST);
				if(nby_documentacquisitioncost.equals(""))
					nby_documentacquisitioncost="-999";
				String nby_bookcirculation = json.getString(NewBooksthatYearTable.NBY_BOOKCIRCULATION);
				if(nby_bookcirculation.equals(""))
					nby_bookcirculation="-999";
				String nby_electronicresourceaccess = json.getString(NewBooksthatYearTable.NBY_ELECTRONICRESOURCEACCESS);
				if(nby_electronicresourceaccess.equals(""))
					nby_electronicresourceaccess="-999";
			
				//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
				String isnull = "0";
				if(nby_paperbooksnumber.equals("-999") || nby_ebooksnumber.equals("-999") || nby_documentacquisitioncost.equals("-999") ||
						nby_bookcirculation.equals("-999") || nby_electronicresourceaccess.equals("-999"))
					isnull = "1";
				
				Map<String,String> params= new HashMap<String, String>();
				params.put(NewBooksthatYearTable.NBY_ID, nby_id);
				params.put(NewBooksthatYearTable.NBY_PAPERBOOKSNUMBER, nby_paperbooksnumber);
				params.put(NewBooksthatYearTable.NBY_EBOOKSNUMBER, nby_ebooksnumber);
				params.put(NewBooksthatYearTable.NBY_DOCUMENTACQUISITIONCOST, nby_documentacquisitioncost);
				params.put(NewBooksthatYearTable.NBY_BOOKCIRCULATION, nby_bookcirculation);
				params.put(NewBooksthatYearTable.NBY_ELECTRONICRESOURCEACCESS, nby_electronicresourceaccess);
				params.put(NewBooksthatYearTable.ISNULL, isnull);
			
				NewBooksthatYearDao nbyDao = new NewBooksthatYearDaoImpl();
				int result = nbyDao.alterNewBooksthatYear(params, nby_id);
			
				out.print(result);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
	}

}
