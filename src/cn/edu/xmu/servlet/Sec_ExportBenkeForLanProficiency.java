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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.excelutils.ExcelException;
import net.sf.excelutils.ExcelUtils;
import cn.edu.xmu.dao.TableListDao;
import cn.edu.xmu.dao.BenkeForLanProficiencyDao;
import cn.edu.xmu.daoimpl.TableListDaoImpl;
import cn.edu.xmu.daoimpl.BenkeForLanProficiencyDaoImpl;
import cn.edu.xmu.entity.BenkeForLanProficiency;

/**
 * @author luo Servlet implementation class Sec_ExportServlet date 2015-07-09
 */

@WebServlet("/ExportBenkeForLanProficiency")
public class Sec_ExportBenkeForLanProficiency extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sec_ExportBenkeForLanProficiency() {
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
		String tableid = request.getParameter("tableid");
		System.out.println("tableid===========" + tableid);
		// 根据tableid得到tablename
		TableListDao tableListDao = new TableListDaoImpl();
		String tablename = tableListDao.getTablename(tableid);

		if (tablename.endsWith("附表6-2-1-12本科生外语水平及学习情况调查表")) {
			BenkeForLanProficiencyDao dao = new BenkeForLanProficiencyDaoImpl();
			List<BenkeForLanProficiency> etList = dao.getAllBenkeForLanProficiency();
			/*-------------- 1.准备数据--------------*/
			List<Object> tsResultList = new ArrayList<Object>();
			for (int i = 0; i < etList.size(); i++) {
				List<Object> etCountList = new ArrayList<Object>();// 行数据
				etCountList.add(etList.get(i).getBflp_id());
				etCountList.add(etList.get(i).getBflp_stunum());
				etCountList.add(etList.get(i).getBflp_college1());
				etCountList.add(etList.get(i).getBflp_name());
				etCountList.add(etList.get(i).getBflp_major());
				etCountList.add(etList.get(i).getBflp_grade());
				etCountList.add(etList.get(i).getBflp_degree());
				etCountList.add(etList.get(i).getBflp_grade());
				etCountList.add(etList.get(i).getBflp_gpa());
				etCountList.add(etList.get(i).getBflp_rank());
				etCountList.add(etList.get(i).getBflp_ispush());
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
