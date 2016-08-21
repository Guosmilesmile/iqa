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

import cn.edu.xmu.dao.ForeignStudentInfoDao;
import cn.edu.xmu.daoimpl.ForeignStudentInfoDaoImpl;
import cn.edu.xmu.entity.ForeignStudentInfo;
import cn.edu.xmu.table.ForeignStudentInfoTable;

/**
 * 表6-1-4  国外及港澳台学生情况（时点）
 * @author yue
 *
 */
@WebServlet("/Sec_AddForeignStudentInfoServlet")
public class Sec_AddForeignStudentInfoServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_AddForeignStudentInfoServlet() {
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
		String college = request.getParameter("fsi_college");
		//解码
		college = URLDecoder.decode(college,"UTF-8");
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
			
		try {
			JSONObject json = new JSONObject(data);
			
			String allgraduatenumber = json.getString(ForeignStudentInfoTable.FSI_ALLGRADUATENUMBER);
			Integer fsi_allgraduatenumber = -999;
			if(!allgraduatenumber.equals(""))
				fsi_allgraduatenumber = Integer.valueOf(allgraduatenumber);
			String alldegreenumber = json.getString( ForeignStudentInfoTable.FSI_ALLDEGREENUMBER);
			Integer fsi_alldegreenumber = -999;
			if(!alldegreenumber.equals(""))
				fsi_alldegreenumber = Integer.valueOf(alldegreenumber);
			String allenrollnumber = json.getString( ForeignStudentInfoTable.FSI_ALLENROLLNUMBER);
			Integer fsi_allenrollnumber = -999;
			if(!allenrollnumber.equals(""))
				fsi_allenrollnumber = Integer.valueOf(allenrollnumber);
			String allcurrentstudentnumber = json.getString( ForeignStudentInfoTable.FSI_ALLCURRENTSTUDENTNUMBER);
			Integer fsi_allcurrentstudentnumber = -999;
			if(!allcurrentstudentnumber.equals(""))
				fsi_allcurrentstudentnumber = Integer.valueOf(allcurrentstudentnumber);
			String foreigngraduatenumber  = json.getString( ForeignStudentInfoTable.FSI_FOREIGNGRADUATENUMBER);
			Integer fsi_foreigngraduatenumber = -999;
			if(!foreigngraduatenumber.equals(""))
				fsi_foreigngraduatenumber = Integer.valueOf(foreigngraduatenumber);
			String foreigndegreenumber = json.getString( ForeignStudentInfoTable.FSI_FOREIGNDEGREENUMBER);
			Integer fsi_foreigndegreenumber = -999;
			if(!foreigndegreenumber.equals(""))
				fsi_foreigndegreenumber = Integer.valueOf(foreigndegreenumber);
			String foreignenrollnumber = json.getString(  ForeignStudentInfoTable.FSI_FOREIGNENROLLNUMBER);
			Integer fsi_foreignenrollnumber = -999;
			if(!foreignenrollnumber.equals(""))
				fsi_foreignenrollnumber = Integer.valueOf(foreignenrollnumber);
			String foreigncurrentstudentnumber = json.getString(  ForeignStudentInfoTable.FSI_FOREIGNCURRENTSTUDENTNUMBER);
			Integer fsi_foreigncurrentstudentnumber = -999;
			if(!foreigncurrentstudentnumber.equals(""))
				fsi_foreigncurrentstudentnumber = Integer.valueOf(foreigncurrentstudentnumber);
			String hkgraduatenumber = json.getString(  ForeignStudentInfoTable.FSI_HKGRADUATENUMBER);
			Integer fsi_hkgraduatenumber = -999;
			if(!hkgraduatenumber.equals(""))
				fsi_hkgraduatenumber = Integer.valueOf(hkgraduatenumber);
			String hkdegreenumber = json.getString(  ForeignStudentInfoTable.FSI_HKDEGREENUMBER);
			Integer fsi_hkdegreenumber = -999;
			if(!hkdegreenumber.equals(""))
				fsi_hkdegreenumber = Integer.valueOf(hkdegreenumber);
			String hkenrollnumber = json.getString(  ForeignStudentInfoTable.FSI_HKENROLLNUMBER);
			Integer fsi_hkenrollnumber = -999;
			if(!hkenrollnumber.equals(""))
				fsi_hkenrollnumber = Integer.valueOf(hkenrollnumber);
			String hkcurrentstudentnumber = json.getString(  ForeignStudentInfoTable.FSI_HKCURRENTSTUDENTNUMBER);
			Integer fsi_hkcurrentstudentnumber = -999;
			if(!hkcurrentstudentnumber.equals(""))
				fsi_hkcurrentstudentnumber = Integer.valueOf(hkcurrentstudentnumber);
			String macgraduatenumber = json.getString(  ForeignStudentInfoTable.FSI_MACGRADUATENUMBER);
			Integer fsi_macgraduatenumber = -999;
			if(!macgraduatenumber.equals(""))
				fsi_macgraduatenumber = Integer.valueOf(macgraduatenumber);
			String macdegreenumber = json.getString(  ForeignStudentInfoTable.FSI_MACDEGREENUMBER);
			Integer fsi_macdegreenumber = -999;
			if(!macdegreenumber.equals(""))
				fsi_macdegreenumber = Integer.valueOf(macdegreenumber);
			String macenrollnumber = json.getString(  ForeignStudentInfoTable.FSI_MACENROLLNUMBER);
			Integer fsi_macenrollnumber = -999;
			if(!macenrollnumber.equals(""))
				fsi_macenrollnumber = Integer.valueOf(macenrollnumber);
			String maccurrentstudentnumber = json.getString(  ForeignStudentInfoTable.FSI_MACCURRENTSTUDENTNUMBER);
			Integer fsi_maccurrentstudentnumber = -999;
			if(!maccurrentstudentnumber.equals(""))
				fsi_maccurrentstudentnumber = Integer.valueOf(maccurrentstudentnumber);
			String twngraduatenumber = json.getString(  ForeignStudentInfoTable.FSI_TWNGRADUATENUMBER);
			Integer fsi_twngraduatenumber = -999;
			if(!twngraduatenumber.equals(""))
				fsi_twngraduatenumber = Integer.valueOf(twngraduatenumber);
			String twndegreenumber = json.getString(  ForeignStudentInfoTable.FSI_TWNDEGREENUMBER);
			Integer fsi_twndegreenumber = -999;
			if(!twndegreenumber.equals(""))
				fsi_twndegreenumber = Integer.valueOf(twndegreenumber);
			String twnenrollnumber = json.getString(  ForeignStudentInfoTable.FSI_TWNENROLLNUMBER);
			Integer fsi_twnenrollnumber = -999;
			if(!twnenrollnumber.equals(""))
				fsi_twnenrollnumber = Integer.valueOf(twnenrollnumber);
			String twncurrentstudentnumber = json.getString(  ForeignStudentInfoTable.FSI_TWNCURRENTSTUDENTNUMBER);
			Integer fsi_twncurrentstudentnumber = -999;
			if(!twncurrentstudentnumber.equals(""))
				fsi_twncurrentstudentnumber = Integer.valueOf(twncurrentstudentnumber);
			
			//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
			int isnull = 0;
			if(allgraduatenumber.equals("")||alldegreenumber.equals("")||allenrollnumber.equals("")||allcurrentstudentnumber.equals("")||
					foreigngraduatenumber.equals("")||foreigndegreenumber.equals("")||foreignenrollnumber.equals("")||
					foreigncurrentstudentnumber.equals("")||hkgraduatenumber.equals("")||hkdegreenumber.equals("")||
					hkenrollnumber.equals("")||hkcurrentstudentnumber.equals("")||macgraduatenumber.equals("")||
					macdegreenumber.equals("")||macenrollnumber.equals("")||maccurrentstudentnumber.equals("")||
					twngraduatenumber.equals("")||twndegreenumber.equals("")||twnenrollnumber.equals("")||
					twncurrentstudentnumber.equals(""))
				isnull = 1;
			if(allgraduatenumber.equals("")&&alldegreenumber.equals("")&&allenrollnumber.equals("")&&allcurrentstudentnumber.equals("")&&
					foreigngraduatenumber.equals("")&&foreigndegreenumber.equals("")&&foreignenrollnumber.equals("")&&
					foreigncurrentstudentnumber.equals("")&&hkgraduatenumber.equals("")&&hkdegreenumber.equals("")&&
					hkenrollnumber.equals("")&&hkcurrentstudentnumber.equals("")&&macgraduatenumber.equals("")&&
					macdegreenumber.equals("")&&macenrollnumber.equals("")&&maccurrentstudentnumber.equals("")&&
					twngraduatenumber.equals("")&&twndegreenumber.equals("")&&twnenrollnumber.equals("")&&
					twncurrentstudentnumber.equals(""))
			{
				out.println(false);
				return ;
			}
			int fsi_serialnumber = serialnumber;
			String fsi_college = college;
			String fsi_comments = "";
			ForeignStudentInfo fsi = new ForeignStudentInfo(fsi_allgraduatenumber, fsi_alldegreenumber, 
					fsi_allenrollnumber, fsi_allcurrentstudentnumber, fsi_foreigngraduatenumber,
					fsi_foreigndegreenumber, fsi_foreignenrollnumber, fsi_foreigncurrentstudentnumber, 
					fsi_hkgraduatenumber, fsi_hkdegreenumber, fsi_hkenrollnumber, 
					fsi_hkcurrentstudentnumber, fsi_macgraduatenumber, fsi_macdegreenumber,
					fsi_macenrollnumber, fsi_maccurrentstudentnumber, fsi_twngraduatenumber, 
					fsi_twndegreenumber, fsi_twnenrollnumber, fsi_twncurrentstudentnumber, 
					fsi_serialnumber, fsi_college, fsi_comments,isnull);
			ForeignStudentInfoDao fsiDao = new ForeignStudentInfoDaoImpl();
			fsiDao.addForeignStudentInfoRecord(fsi);
			out.print(true);
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
	}
}
