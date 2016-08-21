package cn.edu.xmu.servlet;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import net.sf.excelutils.ExcelException;
import net.sf.excelutils.ExcelUtils;

/**
 * 
 * @author zshbleaker
 *
 */
@WebServlet("/ExportTeachUnitSubjectOverviewServlet")
public class Statistic_ExportTeachUnitSubjectOverviewServlet {
	
	private static final long serialVersionUID = 1L;
	public Statistic_ExportTeachUnitSubjectOverviewServlet() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
			
		String tablename = "1.6 教学单位学科专业概览";
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String data = request.getParameter("rowdata");
		
		
		data = new String(data.getBytes("ISO-8859-1"),"UTF-8"); 
		//data = URLDecoder.decode(data.replaceAll("%", "%25"),"UTF-8");
		data = data.substring(1, data.length()-1);
		
		
		data = "["+data+"]";
		System.out.println(data);
		
		try {
			JSONArray jsons = new JSONArray(data);
			System.out.println(jsons);
			
			List<Object> tusoResults = new ArrayList<Object>();
			for (int i = 0; i < jsons.length(); i++) {
				List<Object> tusoCountList = new ArrayList<Object>();
				tusoCountList.add(jsons.getJSONObject(i).getString("number"));
				tusoCountList.add(jsons.getJSONObject(i).getString("unit"));
				tusoCountList.add(jsons.getJSONObject(i).getString("bachelorSubjectNumber"));
				tusoCountList.add(jsons.getJSONObject(i).getString("postPhdFlowSpotNumber"));
				tusoCountList.add(jsons.getJSONObject(i).getString("phdAuthFir"));
				tusoCountList.add(jsons.getJSONObject(i).getString("phdAuthSec"));
				tusoCountList.add(jsons.getJSONObject(i).getString("masterAuthFir"));
				tusoCountList.add(jsons.getJSONObject(i).getString("masterAuthSec"));
				tusoCountList.add(jsons.getJSONObject(i).getString("importantSubject"));



				tusoResults.add(tusoCountList);
			}
			
			ExcelUtils.addValue("typename", tablename);//typename是用在表格模板里面的表名
			ExcelUtils.addValue("resultList", tusoResults);//resultList是用在表格模板里面用于显示数据的
			/*-------------- 2.写出excel文件--------------*/
			String dirs = request.getSession().getServletContext()
					.getRealPath("")
					+ "/template/";
			String templateFileName = tablename;// 模版名称（不含扩展名），用于导出的模板
			String templateFilePath = dirs + templateFileName + ".xls";//导出的模板路径
			String destFilePath = dirs + templateFileName + "-out.xls";//导出的文件路径
			try {
				System.out.println("templateFilePath=" + templateFilePath);
				OutputStream out = new FileOutputStream(destFilePath);
				ExcelUtils.export(templateFilePath, out);
				response.setContentType("application/x-download");
				System.out.println("destFilePath=" + destFilePath);
				String filenamedisplay = tablename + "-out.xls";
				filenamedisplay = URLEncoder.encode(filenamedisplay, "UTF-8");
				response.addHeader("Content-Disposition",
						"attachment;filename=" + filenamedisplay);
			} catch (ExcelException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				java.io.OutputStream os = response.getOutputStream();
				java.io.FileInputStream fis = new java.io.FileInputStream(
						destFilePath);
				byte[] b = new byte[1024];
				int i = 0;
				while ((i = fis.read(b)) > 0) {
					os.write(b, 0, i);
				}
				fis.close();
				os.flush();
				os.close();
			} catch (Exception e) {
				System.out.println("error");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
