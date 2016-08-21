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

import cn.edu.xmu.dao.MajorRegisterInfoDao;
import cn.edu.xmu.daoimpl.MajorRegisterInfoDaoImpl;
import cn.edu.xmu.table.MajorRegisterInfoTable;

/**
 * 表6-1-6-2 各专业（大类）报到情况（时点）
 * @author yue
 *
 */
@WebServlet("/Sec_UpdateMajorRegisterInfoServlet")
public class Sec_UpdateMajorRegisterInfoServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_UpdateMajorRegisterInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
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

		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		
		try {
			if(patter!= null && "batch".equals(patter)){
				System.out.println("批量更新");
				//审核部分更新，可以批量
				data="["+data+"]";	
				JSONArray jsons = new JSONArray(data);
				for (int i = 0; i < jsons.length(); i++) {
					Map<String,String> params= new HashMap<String, String>();
					String mri_id = jsons.getJSONObject(i).getString(MajorRegisterInfoTable.MRI_ID);
					String mri_comments = jsons.getJSONObject(i).getString(MajorRegisterInfoTable.MRI_COMMETNS);
					params.put(MajorRegisterInfoTable.MRI_COMMETNS, mri_comments);
					
					MajorRegisterInfoDao mriDao = new MajorRegisterInfoDaoImpl();
					int result  =mriDao.alterMajorRegisterInfo(params, mri_id);
					out.print(result);
				} 
			}
			else{
				JSONObject json = new JSONObject(data);				
				System.out.println(json);
				Map<String,String> params= new HashMap<String, String>();

				String mri_id = json.getString(MajorRegisterInfoTable.MRI_ID);
				String mri_majorcode = json.getString(MajorRegisterInfoTable.MRI_MAJORCODE);
				String mri_majorname = json.getString(MajorRegisterInfoTable.MRI_MAJORNAME);
				String mri_registernumber = json.getString(MajorRegisterInfoTable.MRI_REGISTERNUMBER);
				String mri_comments = json.getString(MajorRegisterInfoTable.MRI_COMMETNS);
				//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
				int isnull = 0;
				if(mri_majorcode.equals("")||mri_majorname.equals("")||	mri_registernumber.equals(""))
					isnull = 1;
				
				if(mri_registernumber.equals(""))
					mri_registernumber = "-999";
				
				params.put(MajorRegisterInfoTable.MRI_MAJORCODE, mri_majorcode);
				params.put(MajorRegisterInfoTable.MRI_MAJORNAME, mri_majorname);
				params.put(MajorRegisterInfoTable.MRI_REGISTERNUMBER, mri_registernumber);
				params.put(MajorRegisterInfoTable.MRI_COMMETNS,mri_comments);
				params.put(MajorRegisterInfoTable.ISNULL, isnull+"");
				MajorRegisterInfoDao mriDao = new MajorRegisterInfoDaoImpl();
				int result = mriDao.alterMajorRegisterInfo(params, mri_id);
				out.print(result);
			}
		
	}catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
	}
}
