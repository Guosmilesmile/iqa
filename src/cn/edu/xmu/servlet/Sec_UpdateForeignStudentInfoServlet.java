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

import cn.edu.xmu.dao.ForeignStudentInfoDao;
import cn.edu.xmu.daoimpl.ForeignStudentInfoDaoImpl;
import cn.edu.xmu.table.ForeignStudentInfoTable;

/**
 * 表6-1-4  国外及港澳台学生情况（时点）
 * @author yue
 *
 */
@WebServlet("/Sec_UpdateForeignStudentInfoServlet")
public class Sec_UpdateForeignStudentInfoServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_UpdateForeignStudentInfoServlet() {
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
		//String serialnumber  = request.getParameter("serialnumber");
		
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
					String fsi_id = jsons.getJSONObject(i).getString(ForeignStudentInfoTable.FSI_ID);
					String fsi_comments = jsons.getJSONObject(i).getString(ForeignStudentInfoTable.FSI_COMMENTS);
					params.put(ForeignStudentInfoTable.FSI_COMMENTS, fsi_comments);
					
					ForeignStudentInfoDao fsiDao = new ForeignStudentInfoDaoImpl();
					int result = fsiDao.alterForeignStudentInfo(params, fsi_id);
			
			out.print(result);
				}
			}
			else{
				JSONObject json = new JSONObject(data);
				Map<String,String> params= new HashMap<String, String>();
				
				String fsi_id = json.getString(ForeignStudentInfoTable.FSI_ID);
				String fsi_allgraduatenumber = json.getString(ForeignStudentInfoTable.FSI_ALLGRADUATENUMBER);
				String fsi_alldegreenumber = json.getString(ForeignStudentInfoTable.FSI_ALLDEGREENUMBER);
				String fsi_allenrollnumber = json.getString(ForeignStudentInfoTable.FSI_ALLENROLLNUMBER);
				String fsi_allcurrentstudentnumber = json.getString(ForeignStudentInfoTable.FSI_ALLCURRENTSTUDENTNUMBER);
				String fsi_foreigngraduatenumber = json.getString(ForeignStudentInfoTable.FSI_FOREIGNGRADUATENUMBER);
				String fsi_foreigndegreenumber = json.getString(ForeignStudentInfoTable.FSI_FOREIGNDEGREENUMBER);
				String fsi_foreignenrollnumber = json.getString(ForeignStudentInfoTable.FSI_FOREIGNENROLLNUMBER);
				String fsi_foreigncurrentstudentnumber = json.getString(ForeignStudentInfoTable.FSI_FOREIGNCURRENTSTUDENTNUMBER);
				String fsi_hkgraduatenumber = json.getString(ForeignStudentInfoTable.FSI_HKGRADUATENUMBER);
				String fsi_hkdegreenumber = json.getString(ForeignStudentInfoTable.FSI_HKDEGREENUMBER);
				String fsi_hkenrollnumber = json.getString(ForeignStudentInfoTable.FSI_HKENROLLNUMBER);
				String fsi_hkcurrentstudentnumber = json.getString(ForeignStudentInfoTable.FSI_HKCURRENTSTUDENTNUMBER);
				String fsi_macgraduatenumber = json.getString(ForeignStudentInfoTable.FSI_MACGRADUATENUMBER);
				String fsi_macdegreenumber = json.getString(ForeignStudentInfoTable.FSI_MACDEGREENUMBER);
				String fsi_macenrollnumber = json.getString(ForeignStudentInfoTable.FSI_MACENROLLNUMBER);
				String fsi_maccurrentstudentnumber = json.getString(ForeignStudentInfoTable.FSI_MACCURRENTSTUDENTNUMBER);
				String fsi_twngraduatenumber = json.getString(ForeignStudentInfoTable.FSI_TWNGRADUATENUMBER);
				String fsi_twndegreenumber = json.getString(ForeignStudentInfoTable.FSI_TWNDEGREENUMBER);
				String fsi_twnenrollnumber = json.getString(ForeignStudentInfoTable.FSI_TWNENROLLNUMBER);
				String fsi_twncurrentstudentnumber = json.getString(ForeignStudentInfoTable.FSI_TWNCURRENTSTUDENTNUMBER);
				String fsi_comments = json.getString(ForeignStudentInfoTable.FSI_COMMENTS);
				
				//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
				int isnull = 0;
				if(fsi_allgraduatenumber.equals("")||fsi_alldegreenumber.equals("")||fsi_allenrollnumber.equals("")||fsi_allcurrentstudentnumber.equals("")||
						fsi_foreigngraduatenumber.equals("")||fsi_foreigndegreenumber.equals("")||fsi_foreignenrollnumber.equals("")||
						fsi_foreigncurrentstudentnumber.equals("")||fsi_hkgraduatenumber.equals("")||fsi_hkdegreenumber.equals("")||
						fsi_hkenrollnumber.equals("")||fsi_hkcurrentstudentnumber.equals("")||fsi_macgraduatenumber.equals("")||
						fsi_macdegreenumber.equals("")||fsi_macenrollnumber.equals("")||fsi_maccurrentstudentnumber.equals("")||
						fsi_twngraduatenumber.equals("")||fsi_twndegreenumber.equals("")||fsi_twnenrollnumber.equals("")||
						fsi_twncurrentstudentnumber.equals(""))
					isnull = 1;
				
				if(fsi_allgraduatenumber.equals(""))
					fsi_allgraduatenumber = "-999";
				if(fsi_alldegreenumber.equals(""))
					fsi_alldegreenumber = "-999";
				if(fsi_allenrollnumber.equals(""))
					fsi_allenrollnumber = "-999";
				if(fsi_allcurrentstudentnumber.equals(""))
					fsi_allcurrentstudentnumber = "-999";
				if(fsi_foreigngraduatenumber.equals(""))
					fsi_foreigngraduatenumber = "-999";
				if(fsi_foreigndegreenumber.equals(""))
					fsi_foreigndegreenumber = "-999";
				if(fsi_foreignenrollnumber.equals(""))
					fsi_foreignenrollnumber = "-999";
				if(fsi_foreigncurrentstudentnumber.equals(""))
					fsi_foreigncurrentstudentnumber = "-999";
				if(fsi_hkgraduatenumber.equals(""))
					fsi_hkgraduatenumber = "-999";
				if(fsi_hkdegreenumber.equals(""))
					fsi_hkdegreenumber = "-999";
				if(fsi_hkenrollnumber.equals(""))
					fsi_hkenrollnumber = "-999";
				if(fsi_hkcurrentstudentnumber.equals(""))
					fsi_hkcurrentstudentnumber = "-999";
				if(fsi_macgraduatenumber.equals(""))
					fsi_macgraduatenumber = "-999";
				if(fsi_macdegreenumber.equals(""))
					fsi_macdegreenumber = "-999";
				if(fsi_macenrollnumber.equals(""))
					fsi_macenrollnumber = "-999";
				if(fsi_maccurrentstudentnumber.equals(""))
					fsi_maccurrentstudentnumber = "-999";
				if(fsi_twngraduatenumber.equals(""))
					fsi_twngraduatenumber = "-999";
				if(fsi_twndegreenumber.equals(""))
					fsi_twndegreenumber = "-999";
				if(fsi_twnenrollnumber.equals(""))
					fsi_twnenrollnumber = "-999";
				if(fsi_twncurrentstudentnumber.equals(""))
					fsi_twncurrentstudentnumber = "-999";
				
				
				params.put(ForeignStudentInfoTable.FSI_ALLGRADUATENUMBER, fsi_allgraduatenumber);
				params.put(ForeignStudentInfoTable.FSI_ALLDEGREENUMBER, fsi_alldegreenumber);
				params.put(ForeignStudentInfoTable.FSI_ALLENROLLNUMBER,fsi_allenrollnumber);
				params.put(ForeignStudentInfoTable.FSI_ALLCURRENTSTUDENTNUMBER, fsi_allcurrentstudentnumber);
				params.put(ForeignStudentInfoTable.FSI_FOREIGNGRADUATENUMBER, fsi_foreigngraduatenumber);
				params.put(ForeignStudentInfoTable.FSI_FOREIGNDEGREENUMBER, fsi_foreigndegreenumber);
				params.put(ForeignStudentInfoTable.FSI_FOREIGNENROLLNUMBER,fsi_foreignenrollnumber);
				params.put(ForeignStudentInfoTable.FSI_FOREIGNCURRENTSTUDENTNUMBER, fsi_foreigncurrentstudentnumber);
				params.put(ForeignStudentInfoTable.FSI_HKGRADUATENUMBER, fsi_hkgraduatenumber);
				params.put(ForeignStudentInfoTable.FSI_HKDEGREENUMBER, fsi_hkdegreenumber);
				params.put(ForeignStudentInfoTable.FSI_HKENROLLNUMBER,fsi_hkenrollnumber);
				params.put(ForeignStudentInfoTable.FSI_HKCURRENTSTUDENTNUMBER, fsi_hkcurrentstudentnumber);
				params.put(ForeignStudentInfoTable.FSI_MACGRADUATENUMBER, fsi_macgraduatenumber);
				params.put(ForeignStudentInfoTable.FSI_MACDEGREENUMBER, fsi_macdegreenumber);
				params.put(ForeignStudentInfoTable.FSI_MACENROLLNUMBER,fsi_macenrollnumber);
				params.put(ForeignStudentInfoTable.FSI_MACCURRENTSTUDENTNUMBER, fsi_maccurrentstudentnumber);
				params.put(ForeignStudentInfoTable.FSI_TWNGRADUATENUMBER, fsi_twngraduatenumber);
				params.put(ForeignStudentInfoTable.FSI_TWNDEGREENUMBER, fsi_twndegreenumber);
				params.put(ForeignStudentInfoTable.FSI_TWNENROLLNUMBER,fsi_twnenrollnumber);
				params.put(ForeignStudentInfoTable.FSI_TWNCURRENTSTUDENTNUMBER, fsi_twncurrentstudentnumber);
				params.put(ForeignStudentInfoTable.FSI_COMMENTS, fsi_comments);
				params.put(ForeignStudentInfoTable.ISNULL, isnull+"");
				ForeignStudentInfoDao fsiDao = new ForeignStudentInfoDaoImpl();
				int result = fsiDao.alterForeignStudentInfo(params, fsi_id);
				out.print(result);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
		
	}
}
