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

@WebServlet("/ExportLeaderAgeDegreeServlet")
public class Statistic_ExportLeaderAgeDegreeSevlet {

	private static final long serialVersionUID = 1L;
	public Statistic_ExportLeaderAgeDegreeSevlet() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
			
		String tablename = "1.2校领导年龄和学位结构";
		
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
			
			List<Object> ladResults = new ArrayList<Object>();
			for (int i = 0; i < jsons.length(); i++) {
				List<Object> ladCountList = new ArrayList<Object>();
				ladCountList.add(jsons.getJSONObject(i).getString("rowTitle"));
				ladCountList.add(jsons.getJSONObject(i).getString("total"));
				ladCountList.add(jsons.getJSONObject(i).getString("phd"));
				ladCountList.add(jsons.getJSONObject(i).getString("master"));
				ladCountList.add(jsons.getJSONObject(i).getString("sb"));
				ladCountList.add(jsons.getJSONObject(i).getString("young"));
				ladCountList.add(jsons.getJSONObject(i).getString("midyoung"));
				ladCountList.add(jsons.getJSONObject(i).getString("midold"));
				ladCountList.add(jsons.getJSONObject(i).getString("old"));


				ladResults.add(ladCountList);
			}
			
			ExcelUtils.addValue("typename", tablename);//typename是用在表格模板里面的表名
			ExcelUtils.addValue("resultList", ladResults);//resultList是用在表格模板里面用于显示数据的
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
			System.out.println("error");
		}
	}
}
