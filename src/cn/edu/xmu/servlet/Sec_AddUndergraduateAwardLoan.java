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

import cn.edu.xmu.dao.UndergraduateAwardLoanDao;
import cn.edu.xmu.daoimpl.UndergraduateAwardLoanDaoImpl;
import cn.edu.xmu.entity.UndergraduateAwardLoan;
import cn.edu.xmu.table.UndergraduateAwardLoanTable;

/**
 * Servlet implementation class Sec_AddUndergraduateAwardLoan
 */
@WebServlet("/AddUndergraduateAwardLoan")
public class Sec_AddUndergraduateAwardLoan extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_AddUndergraduateAwardLoan() {
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
		String college = request.getParameter(UndergraduateAwardLoanTable.UAL_COLLEGE);
		college = URLDecoder.decode(college,"UTF-8");
		
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		String temp = "";
		try {
			JSONObject json = new JSONObject(data);

			temp = json.getString(UndergraduateAwardLoanTable.UAL_GOVCOST);
			float ual_govcost = -999;
			if(!temp.equals(""))
				ual_govcost = Float.valueOf(temp);
			
			temp = json.getString(UndergraduateAwardLoanTable.UAL_SOCIETYCOST);
			float ual_societycost = -999;
			if(!temp.equals(""))
				ual_societycost = Float.valueOf(temp);
			
			temp = json.getString(UndergraduateAwardLoanTable.UAL_SCHOOLCOST);
			float ual_schoolcost = -999;
			if(!temp.equals(""))
				ual_schoolcost = Float.valueOf(temp);
			
			temp = json.getString(UndergraduateAwardLoanTable.UAL_COUNTRYCOST);
			float ual_countrycost = -999;
			if(!temp.equals(""))
				ual_countrycost = Float.valueOf(temp);
			
			temp = json.getString(UndergraduateAwardLoanTable.UAL_WORKSTUDYCOST);
			float ual_workstudycost = -999;
			if(!temp.equals(""))
				ual_workstudycost = Float.valueOf(temp);
			
			temp = json.getString(UndergraduateAwardLoanTable.UAL_DERATECOST);
			float ual_deratecost = -999;
			if(!temp.equals(""))
				ual_deratecost = Float.valueOf(temp);
			
			temp = json.getString(UndergraduateAwardLoanTable.UAL_TROUBLEAIDCOST);
			float ual_troubleaidcost = -999;
			if(!temp.equals(""))
				ual_troubleaidcost = Float.valueOf(temp);
			float ual_sumcost = -999;
			if(ual_govcost!=-999 && ual_societycost!=-999 && ual_schoolcost!=-999 && 
					ual_countrycost!=-999 && ual_workstudycost!=-999 && ual_deratecost!=-999 && ual_troubleaidcost!=-999)
				ual_sumcost = ual_govcost+ual_societycost+ual_schoolcost+
					ual_countrycost+ual_workstudycost+ual_deratecost+ual_troubleaidcost;
			
			temp = json.getString(UndergraduateAwardLoanTable.UAL_GOVCOUNT);
			int ual_govcount = -999;
			if(!temp.equals(""))
				ual_govcount = Integer.valueOf(temp);
			
			temp = json.getString(UndergraduateAwardLoanTable.UAL_SOCIETYCOUNT);
			int ual_societycount = -999;
			if(!temp.equals(""))
				ual_societycount = Integer.valueOf(temp);
			
			temp = json.getString(UndergraduateAwardLoanTable.UAL_SCHOOLCOUNT);
			int ual_schoolcount = -999;
			if(!temp.equals(""))
				ual_schoolcount = Integer.valueOf(temp);
			
			temp = json.getString(UndergraduateAwardLoanTable.UAL_COUNTRYCOUNT);
			int ual_countrycount = -999;
			if(!temp.equals(""))
				ual_countrycount = Integer.valueOf(temp);
			
			temp = json.getString(UndergraduateAwardLoanTable.UAL_WORKSTUDYCOUNT);
			int ual_workstudycount = -999;
			if(!temp.equals(""))
				ual_workstudycount = Integer.valueOf(temp);
			
			temp = json.getString(UndergraduateAwardLoanTable.UAL_DERATECOUNT);
			int ual_deratecount = -999;
			if(!temp.equals(""))
				ual_deratecount = Integer.valueOf(temp);
			
			temp = json.getString(UndergraduateAwardLoanTable.UAL_TROUBLEAIDCOUNT);
			int ual_troubleaidcount = -999;
			if(!temp.equals(""))
				ual_troubleaidcount = Integer.valueOf(temp);
			
			int ual_sumcount = -999;
			if(ual_govcount!=-999 && ual_societycount!=-999 && ual_schoolcount!=-999 && ual_countrycount!=-999 && 
					ual_workstudycount!=-999 && ual_deratecount!=-999 && ual_troubleaidcount!=-999)
				ual_sumcount = ual_govcount+ual_societycount+ual_schoolcount+ual_countrycount+
					ual_workstudycount+ual_deratecount+ual_troubleaidcount;
			
			int ual_serialnumber = serialnumber;
			String ual_college = college;
			String ual_comments = "";
			
			//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
			int isnull = 0;
			if(ual_govcost==-999 || ual_societycost==-999 || ual_schoolcost==-999 || ual_troubleaidcost==-999 ||
					ual_countrycost==-999 || ual_workstudycost==-999 || ual_deratecost==-999 ||
					ual_govcount==-999 || ual_societycount==-999 || ual_schoolcount==-999 || ual_troubleaidcount==-999 ||
					ual_countrycount==-999 || ual_workstudycount==-999 || ual_deratecount==-999)
				isnull = 1;
			
			if(ual_govcost==-999 && ual_societycost==-999 && ual_schoolcost==-999 && ual_troubleaidcost==-999 &&
					ual_countrycost==-999 && ual_workstudycost==-999 && ual_deratecost==-999 &&
					ual_govcount==-999 && ual_societycount==-999 && ual_schoolcount==-999 && ual_troubleaidcount==-999 &&
					ual_countrycount==-999 && ual_workstudycount==-999 && ual_deratecount==-999)
				return;
			
			UndergraduateAwardLoan ual = new UndergraduateAwardLoan(ual_sumcost,
					ual_govcost, ual_societycost, ual_schoolcost,
					ual_countrycost, ual_workstudycost,
					ual_deratecost, ual_troubleaidcost, ual_sumcount,
					ual_govcount, ual_societycount, ual_schoolcount,
					ual_countrycount, ual_workstudycount, ual_deratecount,
					ual_troubleaidcount, ual_serialnumber,
					ual_college, ual_comments, isnull);			
			
			UndergraduateAwardLoanDao ualDao = new UndergraduateAwardLoanDaoImpl();
			ualDao.addRecord(ual);
			
			out.print(true);
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
		
	}

}
