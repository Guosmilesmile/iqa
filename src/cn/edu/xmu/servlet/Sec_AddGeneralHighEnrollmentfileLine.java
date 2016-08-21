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

import cn.edu.xmu.dao.GeneralHighEnrollmentfileLineDao;
import cn.edu.xmu.daoimpl.GeneralHighEnrollmentfileLineDaoImpl;
import cn.edu.xmu.entity.GeneralHighEnrollmentfileLine;
import cn.edu.xmu.table.GeneralHighEnrollmentfileLineTable;

/**
 * Servlet implementation class Sec_AddGeneralHighEnrollmentfileLine
 */
@WebServlet("/AddGeneralHighEnrollmentfileLine")
public class Sec_AddGeneralHighEnrollmentfileLine extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_AddGeneralHighEnrollmentfileLine() {
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
		String college = request.getParameter(GeneralHighEnrollmentfileLineTable.GHEL_COLLEGE);
		college = URLDecoder.decode(college,"UTF-8");
		
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		String tempString = "";
		try {
			JSONObject json = new JSONObject(data);
			
			String ghel_type = json.getString(GeneralHighEnrollmentfileLineTable.GHEL_TYPE);
			
			tempString = json.getString(GeneralHighEnrollmentfileLineTable.GHEL_HAINAN);
			float ghel_hainan = -999;
			if(!tempString.equals(""))
				ghel_hainan = Float.valueOf(tempString);
			
			tempString = json.getString(GeneralHighEnrollmentfileLineTable.GHEL_XINJIANG);
			float ghel_xinjiang = -999;
			if(!tempString.equals(""))
				ghel_xinjiang = Float.valueOf(tempString);
			
			tempString = json.getString(GeneralHighEnrollmentfileLineTable.GHEL_XIZANGSHAO);
			float ghel_xizangshao = -999;
			if(!tempString.equals(""))
				ghel_xizangshao = Float.valueOf(tempString);
			
			tempString = json.getString(GeneralHighEnrollmentfileLineTable.GHEL_YUNNAN);
			float ghel_yunnan = -999;
			if(!tempString.equals(""))
				ghel_yunnan = Float.valueOf(tempString);
			
			tempString = json.getString(GeneralHighEnrollmentfileLineTable.GHEL_SHANXISHAN);
			float ghel_shanxishan = -999;
			if(!tempString.equals(""))
				ghel_shanxishan = Float.valueOf(tempString);
			
			tempString = json.getString(GeneralHighEnrollmentfileLineTable.GHEL_TIANJIN);
			float ghel_tianjin = -999;
			if(!tempString.equals(""))
				ghel_tianjin = Float.valueOf(tempString);
			
			tempString = json.getString(GeneralHighEnrollmentfileLineTable.GHEL_NINGXIA);
			float ghel_ningxia = -999;
			if(!tempString.equals(""))
				ghel_ningxia = Float.valueOf(tempString);
			
			tempString = json.getString(GeneralHighEnrollmentfileLineTable.GHEL_GUIZHOU);
			float ghel_guizhou = -999;
			if(!tempString.equals(""))
				ghel_guizhou = Float.valueOf(tempString);
			
			tempString = json.getString(GeneralHighEnrollmentfileLineTable.GHEL_LIAONING);
			float ghel_liaoning = -999;
			if(!tempString.equals(""))
				ghel_liaoning = Float.valueOf(tempString);
			
			tempString = json.getString(GeneralHighEnrollmentfileLineTable.GHEL_XIZANGHAN);
			float ghel_xizanghan = -999;
			if(!tempString.equals(""))
				ghel_xizanghan = Float.valueOf(tempString);
			
			tempString = json.getString(GeneralHighEnrollmentfileLineTable.GHEL_JILIN);
			float ghel_jilin = -999;
			if(!tempString.equals(""))
				ghel_jilin = Float.valueOf(tempString);
			
			tempString = json.getString(GeneralHighEnrollmentfileLineTable.GHEL_GUANGXI);
			float ghel_guangxi = -999;
			if(!tempString.equals(""))
				ghel_guangxi = Float.valueOf(tempString);
			
			tempString = json.getString(GeneralHighEnrollmentfileLineTable.GHEL_ZHEJIANG);
			float ghel_zhejiang = -999;
			if(!tempString.equals(""))
				ghel_zhejiang = Float.valueOf(tempString);
			
			tempString = json.getString(GeneralHighEnrollmentfileLineTable.GHEL_CHONGQING);
			float ghel_chongqing = -999;
			if(!tempString.equals(""))
				ghel_chongqing = Float.valueOf(tempString);
			
			tempString = json.getString(GeneralHighEnrollmentfileLineTable.GHEL_ANHUI);
			float ghel_anhui = -999;
			if(!tempString.equals(""))
				ghel_anhui = Float.valueOf(tempString);
			
			tempString = json.getString(GeneralHighEnrollmentfileLineTable.GHEL_HEILONGJIANG);
			float ghel_heilongjiang = -999;
			if(!tempString.equals(""))
				ghel_heilongjiang = Float.valueOf(tempString);
			
			tempString = json.getString(GeneralHighEnrollmentfileLineTable.GHEL_JIANGXI);
			float ghel_jiangxi = -999;
			if(!tempString.equals(""))
				ghel_jiangxi = Float.valueOf(tempString);
			
			tempString = json.getString(GeneralHighEnrollmentfileLineTable.GHEL_SICHUAN);
			float ghel_sichuan = -999;
			if(!tempString.equals(""))
				ghel_sichuan = Float.valueOf(tempString);
			
			tempString = json.getString(GeneralHighEnrollmentfileLineTable.GHEL_BEIJING);
			float ghel_beijing = -999;
			if(!tempString.equals(""))
				ghel_beijing = Float.valueOf(tempString);
			
			tempString = json.getString(GeneralHighEnrollmentfileLineTable.GHEL_HENAN);
			float ghel_henan = -999;
			if(!tempString.equals(""))
				ghel_henan = Float.valueOf(tempString);
			
			tempString = json.getString(GeneralHighEnrollmentfileLineTable.GHEL_HUNAN);
			float ghel_hunan = -999;
			if(!tempString.equals(""))
				ghel_hunan = Float.valueOf(tempString);
			
			tempString = json.getString(GeneralHighEnrollmentfileLineTable.GHEL_SHANGHAI);
			float ghel_shanghai = -999;
			if(!tempString.equals(""))
				ghel_shanghai = Float.valueOf(tempString);
			
			tempString = json.getString(GeneralHighEnrollmentfileLineTable.GHEL_FUJIAN);
			float ghel_fujian = -999;
			if(!tempString.equals(""))
				ghel_fujian = Float.valueOf(tempString);
			
			tempString = json.getString(GeneralHighEnrollmentfileLineTable.GHEL_SHANDONG);
			float ghel_shandong = -999;
			if(!tempString.equals(""))
				ghel_shandong = Float.valueOf(tempString);
			
			tempString = json.getString(GeneralHighEnrollmentfileLineTable.GHEL_HEBEI);
			float ghel_hebei = -999;
			if(!tempString.equals(""))
				ghel_hebei = Float.valueOf(tempString);
			
			tempString = json.getString(GeneralHighEnrollmentfileLineTable.GHEL_HUBEI);
			float ghel_hubei = -999;
			if(!tempString.equals(""))
				ghel_hubei = Float.valueOf(tempString);
			
			tempString = json.getString(GeneralHighEnrollmentfileLineTable.GHEL_GUANGDONG);
			float ghel_guangdong = -999;
			if(!tempString.equals(""))
				ghel_guangdong = Float.valueOf(tempString);
			
			tempString = json.getString(GeneralHighEnrollmentfileLineTable.GHEL_JIANGSU);
			float ghel_jiangsu = -999;
			if(!tempString.equals(""))
				ghel_jiangsu = Float.valueOf(tempString);
			
			tempString = json.getString(GeneralHighEnrollmentfileLineTable.GHEL_SHANXIJIN);
			float ghel_shanxijin = -999;
			if(!tempString.equals(""))
				ghel_shanxijin = Float.valueOf(tempString);
			
			tempString = json.getString(GeneralHighEnrollmentfileLineTable.GHEL_NEIMENGGU);
			float ghel_neimenggu = -999;
			if(!tempString.equals(""))
				ghel_neimenggu = Float.valueOf(tempString);
			
			tempString = json.getString(GeneralHighEnrollmentfileLineTable.GHEL_QINGHAI);
			float ghel_qinghai = -999;
			if(!tempString.equals(""))
				ghel_qinghai = Float.valueOf(tempString);
			
			tempString = json.getString(GeneralHighEnrollmentfileLineTable.GHEL_GANSU);
			float ghel_gansu = -999;
			if(!tempString.equals(""))
				ghel_gansu = Float.valueOf(tempString);
			
			int ghel_serialnumber = serialnumber;
			String ghel_college = college;
			String ghel_comments = "";
			
			//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
			int isnull = 0;
			if(ghel_type.equals("") || ghel_hainan==-999 || ghel_xinjiang==-999 || ghel_xizangshao==-999 || 
					ghel_yunnan==-999 || ghel_shanxishan==-999 || ghel_tianjin==-999 || 
					ghel_ningxia==-999 || ghel_guizhou==-999 || ghel_liaoning==-999 || 
					ghel_xizanghan==-999 || ghel_jilin==-999 || ghel_guangxi==-999 || 
					ghel_zhejiang==-999 || ghel_chongqing==-999 || ghel_anhui==-999 || 
					ghel_heilongjiang==-999 || ghel_jiangxi==-999 || ghel_sichuan==-999 || 
					ghel_beijing==-999 || ghel_henan==-999 || ghel_hunan==-999 || 
					ghel_shanghai==-999 || ghel_fujian==-999 || ghel_shandong==-999 || 
					ghel_hebei==-999 || ghel_hubei==-999 || ghel_guangdong==-999 || 
					ghel_jiangsu==-999 || ghel_shanxijin==-999 || ghel_neimenggu==-999 || 
					ghel_qinghai==-999 || ghel_gansu==-999)
				isnull = 1;
			
			if(ghel_type.equals("") && ghel_hainan==-999 && ghel_xinjiang==-999 && ghel_xizangshao==-999 && 
					ghel_yunnan==-999 && ghel_shanxishan==-999 && ghel_tianjin==-999 && 
					ghel_ningxia==-999 && ghel_guizhou==-999 && ghel_liaoning==-999 && 
					ghel_xizanghan==-999 && ghel_jilin==-999 && ghel_guangxi==-999 && 
					ghel_zhejiang==-999 && ghel_chongqing==-999 && ghel_anhui==-999 && 
					ghel_heilongjiang==-999 && ghel_jiangxi==-999 && ghel_sichuan==-999 && 
					ghel_beijing==-999 && ghel_henan==-999 && ghel_hunan==-999 && 
					ghel_shanghai==-999 && ghel_fujian==-999 && ghel_shandong==-999 && 
					ghel_hebei==-999 && ghel_hubei==-999 && ghel_guangdong==-999 && 
					ghel_jiangsu==-999 && ghel_shanxijin==-999 && ghel_neimenggu==-999 && 
					ghel_qinghai==-999 && ghel_gansu==-999)
				return;
			
			GeneralHighEnrollmentfileLine ghel = new GeneralHighEnrollmentfileLine(ghel_type,
					ghel_hainan, ghel_xinjiang, ghel_xizangshao,
					ghel_yunnan, ghel_shanxishan, ghel_tianjin,
					ghel_ningxia, ghel_guizhou, ghel_liaoning,
					ghel_xizanghan, ghel_jilin, ghel_guangxi,
					ghel_zhejiang, ghel_chongqing, ghel_anhui,
					ghel_heilongjiang, ghel_jiangxi, ghel_sichuan,
					ghel_beijing, ghel_henan, ghel_hunan,
					ghel_shanghai, ghel_fujian, ghel_shandong,
					ghel_hebei, ghel_hubei, ghel_guangdong,
					ghel_jiangsu, ghel_shanxijin, ghel_neimenggu,
					ghel_qinghai, ghel_gansu, ghel_serialnumber,
					ghel_college, ghel_comments, isnull);			
			
			GeneralHighEnrollmentfileLineDao ghelDao = new GeneralHighEnrollmentfileLineDaoImpl();
			ghelDao.addRecord(ghel);
			
			out.print(true);
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
		
	}

}
