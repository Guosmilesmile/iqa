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

import cn.edu.xmu.entity.UnitPersonnelStructure;
import cn.edu.xmu.service.UnitPersonnelStructureService;
import cn.edu.xmu.serviceimpl.UnitPersonnelStructureServiceImpl;
import net.sf.excelutils.ExcelException;
import net.sf.excelutils.ExcelUtils;

/**
 * @author zhantu
 */

@WebServlet("/ExportUnitPersonnelStructure")
public class Statistic_ExportUnitPersonnelStructure extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UnitPersonnelStructureService unitPersonnelStructureService = new UnitPersonnelStructureServiceImpl();
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Statistic_ExportUnitPersonnelStructure() {
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
		String tablename = "附表5各教学单位实验系列职称人员结构";
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String college = request.getParameter("college");
	    String deadline = request.getParameter("deadline");
		if(college!=null)
			college = new String(college.getBytes("ISO-8859-1"),"UTF-8"); 
		if(deadline!=null)
			deadline = new String(deadline.getBytes("ISO-8859-1"),"UTF-8"); 
		try {
			Map queryParams = new HashMap();
		    if (deadline != null) {
		    	queryParams.put("deadline", deadline);
			}
		    
		    
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
		    
		    List<UnitPersonnelStructure> unitPersonnelStructures = unitPersonnelStructureService.getUnitPersonnelStructure(queryParams);
			/*-------------- 1.准备数据--------------*/
			List<Object> tsResultList = new ArrayList<Object>();
			for (int i = 0; i < unitPersonnelStructures.size(); i++) {
				List<Object> tsCountList = new ArrayList<Object>();// 行数据
				//tsCountList.add(unitPersonnelStructures.getString("rowTitle"));
				tsCountList.add(unitPersonnelStructures.get(i).getSerialnumber());
				tsCountList.add(unitPersonnelStructures.get(i).getDepartmentname());
				tsCountList.add(unitPersonnelStructures.get(i).getSum());
				
				tsCountList.add(unitPersonnelStructures.get(i).getTitlePositiveSenior());
				tsCountList.add(unitPersonnelStructures.get(i).getTitleViceSenior());
				tsCountList.add(unitPersonnelStructures.get(i).getTitleIntermediate());
				tsCountList.add(unitPersonnelStructures.get(i).getTitlePrimary());
				tsCountList.add(unitPersonnelStructures.get(i).getTitlenone());

				tsCountList.add(unitPersonnelStructures.get(i).getDoctor());
				tsCountList.add(unitPersonnelStructures.get(i).getMaster());
				tsCountList.add(unitPersonnelStructures.get(i).getDegree());
				tsCountList.add(unitPersonnelStructures.get(i).getNonedegree());

				tsCountList.add(unitPersonnelStructures.get(i).getUnder35());
				tsCountList.add(unitPersonnelStructures.get(i).getBetween36and45());
				tsCountList.add(unitPersonnelStructures.get(i).getBetween46and55());
				tsCountList.add(unitPersonnelStructures.get(i).getOver55());
				
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
