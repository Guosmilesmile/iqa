package cn.edu.xmu.servlet;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import cn.edu.xmu.entity.MajorTeachingInfo;
import cn.edu.xmu.service.MajorTeachingInfoService;
import cn.edu.xmu.serviceimpl.MajorTeachingInfoServiceImpl;
import cn.edu.xmu.util.FastJsonTool;
import net.sf.excelutils.ExcelException;
import net.sf.excelutils.ExcelUtils;

/**
 * 附表11 各专业教学情况一览表
 * @author yue
 *
 */
@WebServlet("/Statistic_ExportMajorTeachingInfoServlet")
public class Statistic_ExportMajorTeachingInfoServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private MajorTeachingInfoService majorTeachingInfoService = new MajorTeachingInfoServiceImpl();
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Statistic_ExportMajorTeachingInfoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String tablename = "附表11各专业教学情况一览表";
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String college = request.getParameter("college");
		if(college!=null)
			college = new String(college.getBytes("ISO-8859-1"),"UTF-8"); 
		Map queryParams = new HashMap();
	    
	    if(college != null && !"".equals(college)){
	    	if (college.contains("学院")) {//具体的学院才过滤
	    		queryParams.put("college", college);
			}
	    	else{
	    		/*college = null;
	    		queryParams.put("college", college);*/
	    	}
	    }
	    else {
			queryParams = null;
		}
	    List<MajorTeachingInfo> mtis = new ArrayList<MajorTeachingInfo>();
		mtis = majorTeachingInfoService.getMajorTeachingInfo(queryParams);
	    String data = FastJsonTool.createJsonString(mtis);
		System.out.println(data);
		try {
			JSONArray jsons = new JSONArray(data);
			System.out.println(jsons);
			/*-------------- 1.准备数据--------------*/
			List<Object> tsResultList = new ArrayList<Object>();
			for (int i = 0; i < jsons.length(); i++) {
				List<Object> tsCountList = new ArrayList<Object>();// 行数据
				tsCountList.add(jsons.getJSONObject(i).getInt("serialNumber"));
				tsCountList.add(jsons.getJSONObject(i).getString("majorName"));
				tsCountList.add(jsons.getJSONObject(i).getString("advantageMajor"));
				tsCountList.add(jsons.getJSONObject(i).getString("setupYear"));
				tsCountList.add(jsons.getJSONObject(i).getInt("allHours"));
				tsCountList.add(Float.parseFloat(jsons.getJSONObject(i).getString("allCredits")));
				tsCountList.add(Float.parseFloat(jsons.getJSONObject(i).getString("mustCredits")));
				tsCountList.add(Float.parseFloat(jsons.getJSONObject(i).getString("selectedCredits")));
				tsCountList.add(Float.parseFloat(jsons.getJSONObject(i).getString("concentratedPracticeCredits")));
				tsCountList.add(Float.parseFloat(jsons.getJSONObject(i).getString("inclassCredits")));
				tsCountList.add(Float.parseFloat(jsons.getJSONObject(i).getString("experimentCredits")));
				tsCountList.add(Float.parseFloat(jsons.getJSONObject(i).getString("outclassActivityCredits")));
				tsCountList.add(Float.parseFloat(jsons.getJSONObject(i).getString("practiceCreditsPercent")));

				tsResultList.add(tsCountList);
			}
			System.out.println(tsResultList);
			ExcelUtils.addValue("typename", tablename);//typename是用在表格模板里面的表名
			ExcelUtils.addValue("resultList", tsResultList);//resultList是用在表格模板里面用于显示数据的
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
			e.printStackTrace();
		}
		
	}
}
