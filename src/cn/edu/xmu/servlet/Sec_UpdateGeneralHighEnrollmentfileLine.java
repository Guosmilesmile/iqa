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

import cn.edu.xmu.dao.GeneralHighEnrollmentfileLineDao;
import cn.edu.xmu.daoimpl.GeneralHighEnrollmentfileLineDaoImpl;
import cn.edu.xmu.table.GeneralHighEnrollmentfileLineTable;

/**
 * Servlet implementation class Sec_UpdateGeneralHighEnrollmentfileLine
 */
@WebServlet("/UpdateGeneralHighEnrollmentfileLine")
public class Sec_UpdateGeneralHighEnrollmentfileLine extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_UpdateGeneralHighEnrollmentfileLine() {
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
		//String college = request.getParameter(GeneralHighEnrollmentfileLineTable.GHEL_COLLEGE);
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
					String ghel_id = jsons.getJSONObject(i).getString(GeneralHighEnrollmentfileLineTable.GHEL_ID);
					String ghel_comments = jsons.getJSONObject(i).getString(GeneralHighEnrollmentfileLineTable.GHEL_COMMENTS);
				
					Map<String,String> params= new HashMap<String, String>();
					params.put(GeneralHighEnrollmentfileLineTable.GHEL_ID, ghel_id);
					params.put(GeneralHighEnrollmentfileLineTable.GHEL_COMMENTS, ghel_comments);
				
					GeneralHighEnrollmentfileLineDao ghelDao = new GeneralHighEnrollmentfileLineDaoImpl();
					int result = ghelDao.alterGeneralHighEnrollmentfileLine(params, ghel_id);
				
					out.print(result);
				}
			}
			else {
				JSONObject json = new JSONObject(data);
			
				String ghel_id = json.getString(GeneralHighEnrollmentfileLineTable.GHEL_ID);
				String ghel_type = json.getString(GeneralHighEnrollmentfileLineTable.GHEL_TYPE);
				
				String ghel_hainan = json.getString(GeneralHighEnrollmentfileLineTable.GHEL_HAINAN);
				if(ghel_hainan.equals(""))
					ghel_hainan = "-999";
				String ghel_xinjiang = json.getString(GeneralHighEnrollmentfileLineTable.GHEL_XINJIANG);
				if(ghel_xinjiang.equals(""))
					ghel_xinjiang = "-999";
				String ghel_xizangshao = json.getString(GeneralHighEnrollmentfileLineTable.GHEL_XIZANGSHAO);
				if(ghel_xizangshao.equals(""))
					ghel_xizangshao = "-999";
				String ghel_yunnan = json.getString(GeneralHighEnrollmentfileLineTable.GHEL_YUNNAN);
				if(ghel_yunnan.equals(""))
					ghel_yunnan = "-999";
				String ghel_shanxishan = json.getString(GeneralHighEnrollmentfileLineTable.GHEL_SHANXISHAN);
				if(ghel_shanxishan.equals(""))
					ghel_shanxishan = "-999";
				String ghel_tianjin = json.getString(GeneralHighEnrollmentfileLineTable.GHEL_TIANJIN);
				if(ghel_tianjin.equals(""))
					ghel_tianjin = "-999";
				String ghel_ningxia = json.getString(GeneralHighEnrollmentfileLineTable.GHEL_NINGXIA);
				if(ghel_ningxia.equals(""))
					ghel_ningxia = "-999";
				String ghel_guizhou = json.getString(GeneralHighEnrollmentfileLineTable.GHEL_GUIZHOU);
				if(ghel_guizhou.equals(""))
					ghel_guizhou = "-999";
				String ghel_liaoning = json.getString(GeneralHighEnrollmentfileLineTable.GHEL_LIAONING);
				if(ghel_liaoning.equals(""))
					ghel_liaoning = "-999";
				String ghel_xizanghan = json.getString(GeneralHighEnrollmentfileLineTable.GHEL_XIZANGHAN);
				if(ghel_xizanghan.equals(""))
					ghel_xizanghan = "-999";
				String ghel_jilin = json.getString(GeneralHighEnrollmentfileLineTable.GHEL_JILIN);
				if(ghel_jilin.equals(""))
					ghel_jilin = "-999";
				String ghel_guangxi = json.getString(GeneralHighEnrollmentfileLineTable.GHEL_GUANGXI);
				if(ghel_guangxi.equals(""))
					ghel_guangxi = "-999";
				String ghel_zhejiang = json.getString(GeneralHighEnrollmentfileLineTable.GHEL_ZHEJIANG);
				if(ghel_zhejiang.equals(""))
					ghel_zhejiang = "-999";
				String ghel_chongqing = json.getString(GeneralHighEnrollmentfileLineTable.GHEL_CHONGQING);
				if(ghel_chongqing.equals(""))
					ghel_chongqing = "-999";
				String ghel_anhui = json.getString(GeneralHighEnrollmentfileLineTable.GHEL_ANHUI);
				if(ghel_anhui.equals(""))
					ghel_anhui = "-999";
				String ghel_heilongjiang = json.getString(GeneralHighEnrollmentfileLineTable.GHEL_HEILONGJIANG);
				if(ghel_heilongjiang.equals(""))
					ghel_heilongjiang = "-999";
				String ghel_jiangxi = json.getString(GeneralHighEnrollmentfileLineTable.GHEL_JIANGXI);
				if(ghel_jiangxi.equals(""))
					ghel_jiangxi = "-999";
				String ghel_sichuan = json.getString(GeneralHighEnrollmentfileLineTable.GHEL_SICHUAN);
				if(ghel_sichuan.equals(""))
					ghel_sichuan = "-999";
				String ghel_beijing = json.getString(GeneralHighEnrollmentfileLineTable.GHEL_BEIJING);
				if(ghel_beijing.equals(""))
					ghel_beijing = "-999";
				String ghel_henan = json.getString(GeneralHighEnrollmentfileLineTable.GHEL_HENAN);
				if(ghel_henan.equals(""))
					ghel_henan = "-999";
				String ghel_hunan = json.getString(GeneralHighEnrollmentfileLineTable.GHEL_HUNAN);
				if(ghel_hunan.equals(""))
					ghel_hunan = "-999";
				String ghel_shanghai = json.getString(GeneralHighEnrollmentfileLineTable.GHEL_SHANGHAI);
				if(ghel_shanghai.equals(""))
					ghel_shanghai = "-999";
				String ghel_fujian = json.getString(GeneralHighEnrollmentfileLineTable.GHEL_FUJIAN);
				if(ghel_fujian.equals(""))
					ghel_fujian = "-999";
				String ghel_shandong = json.getString(GeneralHighEnrollmentfileLineTable.GHEL_SHANDONG);
				if(ghel_shandong.equals(""))
					ghel_shandong = "-999";
				String ghel_hebei = json.getString(GeneralHighEnrollmentfileLineTable.GHEL_HEBEI);
				if(ghel_hebei.equals(""))
					ghel_hebei = "-999";
				String ghel_hubei = json.getString(GeneralHighEnrollmentfileLineTable.GHEL_HUBEI);
				if(ghel_hubei.equals(""))
					ghel_hubei = "-999";
				String ghel_guangdong = json.getString(GeneralHighEnrollmentfileLineTable.GHEL_GUANGDONG);
				if(ghel_guangdong.equals(""))
					ghel_guangdong = "-999";
				String ghel_jiangsu = json.getString(GeneralHighEnrollmentfileLineTable.GHEL_JIANGSU);
				if(ghel_jiangsu.equals(""))
					ghel_jiangsu = "-999";
				String ghel_shanxijin = json.getString(GeneralHighEnrollmentfileLineTable.GHEL_SHANXIJIN);
				if(ghel_shanxijin.equals(""))
					ghel_shanxijin = "-999";
				String ghel_neimenggu = json.getString(GeneralHighEnrollmentfileLineTable.GHEL_NEIMENGGU);
				if(ghel_neimenggu.equals(""))
					ghel_neimenggu = "-999";
				String ghel_qinghai = json.getString(GeneralHighEnrollmentfileLineTable.GHEL_QINGHAI);
				if(ghel_qinghai.equals(""))
					ghel_qinghai = "-999";
				String ghel_gansu = json.getString(GeneralHighEnrollmentfileLineTable.GHEL_GANSU);
				if(ghel_gansu.equals(""))
					ghel_gansu = "-999";
				
				//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
				int isnull = 0;
				if(ghel_type.equals("") || ghel_hainan.equals("-999") || ghel_xinjiang.equals("-999") || ghel_xizangshao.equals("-999") || 
						ghel_yunnan.equals("-999") || ghel_shanxishan.equals("-999") || ghel_tianjin.equals("-999") || 
						ghel_ningxia.equals("-999") || ghel_guizhou.equals("-999") || ghel_liaoning.equals("-999") || 
						ghel_xizanghan.equals("-999") || ghel_jilin.equals("-999") || ghel_guangxi.equals("-999") || 
						ghel_zhejiang.equals("-999") || ghel_chongqing.equals("-999") || ghel_anhui.equals("-999") || 
						ghel_heilongjiang.equals("-999") || ghel_jiangxi.equals("-999") || ghel_sichuan.equals("-999") || 
						ghel_beijing.equals("-999") || ghel_henan.equals("-999") || ghel_hunan.equals("-999") || 
						ghel_shanghai.equals("-999") || ghel_fujian.equals("-999") || ghel_shandong.equals("-999") || 
						ghel_hebei.equals("-999") || ghel_hubei.equals("-999") || ghel_guangdong.equals("-999") || 
						ghel_jiangsu.equals("-999") || ghel_shanxijin.equals("-999") || ghel_neimenggu.equals("-999") || 
						ghel_qinghai.equals("-999") || ghel_gansu.equals("-999"))
					isnull = 1;
			
				Map<String,String> params= new HashMap<String, String>();
				params.put(GeneralHighEnrollmentfileLineTable.GHEL_ID, ghel_id);
				params.put(GeneralHighEnrollmentfileLineTable.GHEL_TYPE, ghel_type);

				params.put(GeneralHighEnrollmentfileLineTable.GHEL_HAINAN, ghel_hainan);
				params.put(GeneralHighEnrollmentfileLineTable.GHEL_XINJIANG, ghel_xinjiang);
				params.put(GeneralHighEnrollmentfileLineTable.GHEL_XIZANGSHAO, ghel_xizangshao);
				params.put(GeneralHighEnrollmentfileLineTable.GHEL_YUNNAN, ghel_yunnan);

				params.put(GeneralHighEnrollmentfileLineTable.GHEL_SHANXISHAN, ghel_shanxishan);
				params.put(GeneralHighEnrollmentfileLineTable.GHEL_TIANJIN, ghel_tianjin);
				params.put(GeneralHighEnrollmentfileLineTable.GHEL_NINGXIA, ghel_ningxia);
				params.put(GeneralHighEnrollmentfileLineTable.GHEL_GUIZHOU, ghel_guizhou);

				params.put(GeneralHighEnrollmentfileLineTable.GHEL_LIAONING, ghel_liaoning);
				params.put(GeneralHighEnrollmentfileLineTable.GHEL_XIZANGHAN, ghel_xizanghan);
				params.put(GeneralHighEnrollmentfileLineTable.GHEL_JILIN, ghel_jilin);
				params.put(GeneralHighEnrollmentfileLineTable.GHEL_GUANGXI, ghel_guangxi);

				params.put(GeneralHighEnrollmentfileLineTable.GHEL_ZHEJIANG, ghel_zhejiang);
				params.put(GeneralHighEnrollmentfileLineTable.GHEL_CHONGQING, ghel_chongqing);
				params.put(GeneralHighEnrollmentfileLineTable.GHEL_ANHUI, ghel_anhui);
				params.put(GeneralHighEnrollmentfileLineTable.GHEL_HEILONGJIANG, ghel_heilongjiang);

				params.put(GeneralHighEnrollmentfileLineTable.GHEL_JIANGXI, ghel_jiangxi);
				params.put(GeneralHighEnrollmentfileLineTable.GHEL_SICHUAN, ghel_sichuan);
				params.put(GeneralHighEnrollmentfileLineTable.GHEL_BEIJING, ghel_beijing);
				params.put(GeneralHighEnrollmentfileLineTable.GHEL_HENAN, ghel_henan);
				
				params.put(GeneralHighEnrollmentfileLineTable.GHEL_HUNAN, ghel_hunan);
				params.put(GeneralHighEnrollmentfileLineTable.GHEL_SHANGHAI, ghel_shanghai);
				params.put(GeneralHighEnrollmentfileLineTable.GHEL_FUJIAN, ghel_fujian);
				params.put(GeneralHighEnrollmentfileLineTable.GHEL_SHANDONG, ghel_shandong);
				
				params.put(GeneralHighEnrollmentfileLineTable.GHEL_HEBEI, ghel_hebei);
				params.put(GeneralHighEnrollmentfileLineTable.GHEL_HUBEI, ghel_hubei);
				params.put(GeneralHighEnrollmentfileLineTable.GHEL_GUANGDONG, ghel_guangdong);
				params.put(GeneralHighEnrollmentfileLineTable.GHEL_JIANGSU, ghel_jiangsu);
				
				params.put(GeneralHighEnrollmentfileLineTable.GHEL_SHANXIJIN, ghel_shanxijin);
				params.put(GeneralHighEnrollmentfileLineTable.GHEL_NEIMENGGU, ghel_neimenggu);
				params.put(GeneralHighEnrollmentfileLineTable.GHEL_QINGHAI, ghel_qinghai);
				params.put(GeneralHighEnrollmentfileLineTable.GHEL_GANSU, ghel_gansu);
				
				params.put(GeneralHighEnrollmentfileLineTable.ISNULL, isnull+"");
			
				GeneralHighEnrollmentfileLineDao ghelDao = new GeneralHighEnrollmentfileLineDaoImpl();
				int result = ghelDao.alterGeneralHighEnrollmentfileLine(params, ghel_id);
			
				out.print(result);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
	}

}
