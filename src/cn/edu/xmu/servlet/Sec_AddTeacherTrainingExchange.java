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

import cn.edu.xmu.dao.TeacherTrainingExchangeDao;
import cn.edu.xmu.daoimpl.TeacherTrainingExchangeDaoImpl;
import cn.edu.xmu.entity.TeacherTrainingExchange;
import cn.edu.xmu.table.TeacherTrainingExchangeTable;

/**
 * Servlet implementation class Sec_AddTeacherTrainingExchange
 */
@WebServlet("/AddTeacherTrainingExchange")
public class Sec_AddTeacherTrainingExchange extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_AddTeacherTrainingExchange() {
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
		String college = request.getParameter(TeacherTrainingExchangeTable.TTE_COLLEGE);
		college = URLDecoder.decode(college,"UTF-8");
		
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		String temp = "";
		try {
			JSONObject json = new JSONObject(data);
			
			String tte_departmentname = json.getString(TeacherTrainingExchangeTable.TTE_DEPARTMENTNAME);
			String tte_departmentnumber = json.getString(TeacherTrainingExchangeTable.TTE_DEPARTMENTNUMBER);
			temp = json.getString(TeacherTrainingExchangeTable.TTE_TRAINCHURCHYARD);
			int tte_trainchurchyard = -999;
			if(!temp.equals(""))
				tte_trainchurchyard = Integer.valueOf(temp);
			
			temp = json.getString(TeacherTrainingExchangeTable.TTE_TRAINOVERSEASSUM);
			int tte_trainoverseassum = -999;
			if(!temp.equals(""))
				tte_trainoverseassum = Integer.valueOf(temp);
			
			temp = json.getString(TeacherTrainingExchangeTable.TTE_TRAINOVERSEASOVER3);
			int tte_trainoverseasover3 = -999;
			if(!temp.equals(""))
				tte_trainoverseasover3 = Integer.valueOf(temp);
			
			temp = json.getString(TeacherTrainingExchangeTable.TTE_TRAINTRADE);
			int tte_traintrade = -999;
			if(!temp.equals(""))
				tte_traintrade = Integer.valueOf(temp);
			
			temp = json.getString(TeacherTrainingExchangeTable.TTE_TRAINFORDOCTOR);
			int tte_trainfordoctor = -999;
			if(!temp.equals(""))
				tte_trainfordoctor = Integer.valueOf(temp);
			
			temp = json.getString(TeacherTrainingExchangeTable.TTE_TRAINFORMASTER);
			int tte_trainformaster = -999;
			if(!temp.equals(""))
				tte_trainformaster = Integer.valueOf(temp);
			
			int tte_trainfordegreesum = -999;
			if(tte_trainfordoctor!=-999 && tte_trainformaster!=-999)
				tte_trainfordegreesum = tte_trainfordoctor+tte_trainformaster;
			
			temp = json.getString(TeacherTrainingExchangeTable.TTE_EXCHANGECOMECHURCHYARD);
			int tte_exchangecomechurchyard = -999;
			if(!temp.equals(""))
				tte_exchangecomechurchyard = Integer.valueOf(temp);
			
			temp = json.getString(TeacherTrainingExchangeTable.TTE_EXCHANGECOMEOVERSEA);
			int tte_exchangecomeoversea = -999;
			if(!temp.equals(""))
				tte_exchangecomeoversea = Integer.valueOf(temp);
			
			temp = json.getString(TeacherTrainingExchangeTable.TTE_EXCHANGEVISITCHURCHYARD);
			int tte_exchangevisitchurchyard = -999;
			if(!temp.equals(""))
				tte_exchangevisitchurchyard = Integer.valueOf(temp);
			
			temp = json.getString(TeacherTrainingExchangeTable.TTE_EXCHANGEVISITOVERSEA);
			int tte_exchangevisitoversea = -999;
			if(!temp.equals(""))
				tte_exchangevisitoversea = Integer.valueOf(temp);
			
			//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
			int isnull = 0;
			if(tte_departmentname.equals("") || tte_departmentnumber.equals("") || tte_trainchurchyard==-999 ||
					tte_trainoverseassum==-999 || tte_trainoverseasover3==-999 || tte_traintrade==-999 ||
					tte_trainfordoctor==-999 || tte_trainformaster==-999 || tte_exchangecomechurchyard==-999 ||
					tte_exchangecomeoversea==-999 || tte_exchangevisitchurchyard==-999 || tte_exchangevisitoversea==-999)
				isnull = 1;
			
			if(tte_departmentname.equals("") && tte_departmentnumber.equals("") && tte_trainchurchyard==-999 &&
					tte_trainoverseassum==-999 && tte_trainoverseasover3==-999 && tte_traintrade==-999 &&
					tte_trainfordoctor==-999 && tte_trainformaster==-999 && tte_exchangecomechurchyard==-999 &&
					tte_exchangecomeoversea==-999 && tte_exchangevisitchurchyard==-999 && tte_exchangevisitoversea==-999)
				return;
			
			int tte_serialnumber = serialnumber;
			String tte_college = college;
			String tte_comments = "";
			
			TeacherTrainingExchange tte = new TeacherTrainingExchange(tte_departmentname,
					tte_departmentnumber, tte_trainchurchyard,
					tte_trainoverseassum, tte_trainoverseasover3,
					tte_traintrade, tte_trainfordegreesum,
					tte_trainfordoctor, tte_trainformaster,
					tte_exchangecomechurchyard, tte_exchangecomeoversea,
					tte_exchangevisitchurchyard, tte_exchangevisitoversea,
					tte_serialnumber, tte_college, tte_comments, isnull);			
			
			TeacherTrainingExchangeDao tteDao = new TeacherTrainingExchangeDaoImpl();
			tteDao.addRecord(tte);
			
			out.print(true);
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
		
	}

}
