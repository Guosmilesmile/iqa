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

import cn.edu.xmu.dao.MajorEnrollInfoDao;
import cn.edu.xmu.daoimpl.MajorEnrollInfoDaoImpl;
import cn.edu.xmu.entity.MajorEnrollInfo;
import cn.edu.xmu.table.MajorEnrollInfoTable;

/**
 * 各专业（大类）招生情况（时点）
 * @author yue
 *
 */
@WebServlet("/Sec_AddMajorEnrollInfoServlet")
public class Sec_AddMajorEnrollInfoServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_AddMajorEnrollInfoServlet()
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
		String college = request.getParameter(MajorEnrollInfoTable.MEI_COLLEGE);
		college = URLDecoder.decode(college,"UTF-8");
		
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		try {
			JSONObject json = new JSONObject(data);
			
			String mei_majorcode = json.getString(MajorEnrollInfoTable.MEI_MAJORCODE);
			String mei_majorname = json.getString(MajorEnrollInfoTable.MEI_MAJORNAME);
			String plannumber = json.getString(MajorEnrollInfoTable.MEI_PLANNUMBER);
			Integer mei_plannumber = -999;
			if(!plannumber.equals(""))
				mei_plannumber = Integer.valueOf(plannumber);
			String enrollnumber = json.getString(MajorEnrollInfoTable.MEI_ENROLLNUMBER);
			int mei_enrollnumber = -999;
			if(!enrollnumber.equals(""))
				mei_enrollnumber = Integer.valueOf(enrollnumber);
			
			//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
			int isnull = 0;
			if(mei_majorcode.equals("")||mei_majorname.equals("")||
					plannumber.equals("")||enrollnumber.equals(""))
				isnull = 1;
			if(mei_majorcode.equals("")&&mei_majorname.equals("")&&
					plannumber.equals("")&&enrollnumber.equals(""))
			{
				out.println(false);
				return ;
			}
			int mei_serialnumber = serialnumber;
			String mei_college = college;
			String mei_comments = "";
			
			MajorEnrollInfo mei = new MajorEnrollInfo(mei_majorcode, mei_majorname, mei_plannumber,
					mei_enrollnumber, mei_serialnumber, mei_college, mei_comments,isnull);
			MajorEnrollInfoDao meiDao = new MajorEnrollInfoDaoImpl();
			meiDao.addMajorEnrollInfoRecord(mei);
			out.print(true);
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
	}
}
