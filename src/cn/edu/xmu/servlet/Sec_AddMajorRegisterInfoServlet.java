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

import cn.edu.xmu.dao.MajorRegisterInfoDao;
import cn.edu.xmu.daoimpl.MajorRegisterInfoDaoImpl;
import cn.edu.xmu.entity.MajorRegisterInfo;
import cn.edu.xmu.table.MajorRegisterInfoTable;

/**
 * 表6-1-6-2 各专业（大类）报到情况（时点）
 * @author yue
 *
 */
@WebServlet("/Sec_AddMajorRegisterInfoServlet")
public class Sec_AddMajorRegisterInfoServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_AddMajorRegisterInfoServlet()
    {
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
		int serialnumber  = Integer.parseInt(request.getParameter("serialnumber"));
		//解码
		String college = request.getParameter(MajorRegisterInfoTable.MRI_COLLEGE);
		college = URLDecoder.decode(college,"UTF-8");
		
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		try {
			JSONObject json = new JSONObject(data);
			
			String mri_majorcode = json.getString(MajorRegisterInfoTable.MRI_MAJORCODE);
			String mri_majorname = json.getString(MajorRegisterInfoTable.MRI_MAJORNAME);
			String registernumber = json.getString(MajorRegisterInfoTable.MRI_REGISTERNUMBER);
			Integer mri_registernumber = -999;
			if(!registernumber.equals(""))
				mri_registernumber = Integer.valueOf(registernumber);
			
			//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
			int isnull = 0;
			if(mri_majorcode.equals("")||mri_majorname.equals("")||	registernumber.equals(""))
				isnull = 1;
			if(mri_majorcode.equals("")&&mri_majorname.equals("")&&	registernumber.equals(""))
			{
				out.println(false);
				return;
			}
			int mri_serialnumber = serialnumber;
			String mri_college = college;
			String mri_comments = "";
			
			MajorRegisterInfo mri = new MajorRegisterInfo(mri_majorcode, mri_majorname,
					mri_registernumber, mri_serialnumber, mri_college, mri_comments,isnull);
			MajorRegisterInfoDao mriDao = new MajorRegisterInfoDaoImpl();
			mriDao.addMajorRegisterInfoRecord(mri);
			out.print(true);
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
	}

}
