package cn.edu.xmu.servlet;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.excelutils.ExcelException;
import net.sf.excelutils.ExcelUtils;
import cn.edu.xmu.dao.MajorInfoDao;
import cn.edu.xmu.dao.TableListDao;
import cn.edu.xmu.dao.PlayGroundDao;
import cn.edu.xmu.dao.UserDao;
import cn.edu.xmu.dao.UserRoleDao;
import cn.edu.xmu.daoimpl.MajorInfoDaoImpl;
import cn.edu.xmu.daoimpl.TableListDaoImpl;
import cn.edu.xmu.daoimpl.PlayGroundDaoImpl;
import cn.edu.xmu.daoimpl.UserDaoImpl;
import cn.edu.xmu.daoimpl.UserRoleDaoImpl;
import cn.edu.xmu.entity.MajorInfo;
import cn.edu.xmu.entity.PlayGround;
import cn.edu.xmu.table.MajorInfoTable;

/**
 * @author zsj
 */

@WebServlet("/ExportMajorInfoServlet")
public class ExportMajorInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExportMajorInfoServlet() {
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
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8"); 
		
		
		String college = request.getParameter("mi_college");
		college = URLDecoder.decode(college, "utf-8");
		 String tableid = request.getParameter("tableid");  
		 
		 TableListDao tableListDao = new TableListDaoImpl();	
		 System.out.println("tableid:"+tableid);
		 
		 Date deadLine = tableListDao.getTableDate(tableid);//tableList中应当注意发布时清空，关闭时赋值
		 System.out.println("deadline:"+deadLine);
	   
		 Map queryParams = new HashMap();
		 if (deadLine != null) {
			 queryParams.put(MajorInfoTable.MI_DEADLINE, deadLine);
		}
		 
		 
		  if(college != null && !"".equals(college)){
		    	if (college.contains("院")) {//具体的学院才过滤
		    		queryParams.put(MajorInfoTable.MI_COLLEGE, college);
				}
		    	/*else{
		    		
		    		queryParams.put(MajorInfoTable.MI_COLLEGE, "");
		    	}*/
		    }

		  // 根据tableid得到tablename
		String tablename = tableListDao.getTablename(tableid);

		if (tablename.endsWith("表4-2-2-1专业基本情况")) {
			MajorInfoDao dao = new MajorInfoDaoImpl();
			List<MajorInfo> etList = dao.getMajorInfo(0, dao.getCount(queryParams), MajorInfoTable.MI_ID, "asc",queryParams);
			/*-------------- 1.准备数据--------------*/
			List<Object> tsResultList = new ArrayList<Object>();
			for (int i = 0; i < etList.size(); i++) {
				List<Object> etCountList = new ArrayList<Object>();// 行数据
				etCountList.add(etList.get(i).getMi_majorcodeinschool());
				etCountList.add(etList.get(i).getMi_majornameinschool());
				etCountList.add(etList.get(i).getMi_codeversion());
				etCountList.add(etList.get(i).getMi_majorcode());
				etCountList.add(etList.get(i).getMi_majorname());
				etCountList.add(etList.get(i).getMi_majorfieldnum());
				etCountList.add(etList.get(i).getMi_majorfieldname());
				etCountList.add(etList.get(i).getMi_departmentnumber());
				etCountList.add(etList.get(i).getMi_departmentname());
				etCountList.add(etList.get(i).getMi_leaderid());
				etCountList.add(etList.get(i).getMi_leadername());
				etCountList.add(etList.get(i).getMi_admissionstate());
				etCountList.add(etList.get(i).getMi_majorspecialty());
				etCountList.add(etList.get(i).getMi_traininggoal());
				etCountList.add(etList.get(i).getMi_schoolingyear());
				etCountList.add(etList.get(i).getMi_setupyear());
				etCountList.add(etList.get(i).getMi_isnew());
				etCountList.add(etList.get(i).getMi_allhours());
				etCountList.add(etList.get(i).getMi_musthours());
				etCountList.add(etList.get(i).getMi_selectedhours());
				etCountList.add(etList.get(i).getMi_inclasshours());
				etCountList.add(etList.get(i).getMi_experimenthours());
				etCountList.add(etList.get(i).getMi_allcredits());
				etCountList.add(etList.get(i).getMi_mustcredits());
				etCountList.add(etList.get(i).getMi_selectedcredits());
				etCountList.add(etList.get(i).getMi_concentratedpracticecredits());
				etCountList.add(etList.get(i).getMi_inclasscredits());
				etCountList.add(etList.get(i).getMi_experimentcredits());
				etCountList.add(etList.get(i).getMi_outclassactivitycredits());
				
				tsResultList.add(etCountList);
			}
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
		}
	}

}
