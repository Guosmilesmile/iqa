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
import cn.edu.xmu.dao.SuperMajorDao;
import cn.edu.xmu.dao.TableListDao;
import cn.edu.xmu.dao.UserFillDao;
import cn.edu.xmu.daoimpl.SuperMajorDaoImpl;
import cn.edu.xmu.daoimpl.TableListDaoImpl;
import cn.edu.xmu.daoimpl.UserFillDaoImpl;
import cn.edu.xmu.entity.SuperMajor;
import cn.edu.xmu.entity.UserFill;

/**
 * Servlet implementation class ExportDetailServlet
 */
@WebServlet("/ExportDetailServlet")
public class ExportDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExportDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String userid = request.getParameter("userid");
		UserFillDao userFillDao = new UserFillDaoImpl();
		List<UserFill> list = userFillDao.getUserExam(userid,"");
		System.out.println(list.size());
		/*-------------- 1.准备数据--------------*/
		List<Object> resultList = new ArrayList<Object>();
		for (int i = 0; i < list.size(); i++) {
			List<Object> countList = new ArrayList<Object>();// 行数据
			countList.add(list.get(i).getTablename());
			countList.add(list.get(i).getRolename());
			if(list.get(i).getRf_fillsituation()==0)
			{
				countList.add("未提交");
			}
			else 
			{
				countList.add("已提交");
			}
			if(list.get(i).getRf_reviewsituation()==0)
			{
				countList.add("未审核");
			}
			else if(list.get(i).getRf_reviewsituation()==1)
			{
				countList.add("不通过");
			}
			else if(list.get(i).getRf_reviewsituation()==2)
			{
				countList.add("一级审核通过");
			}
			else
			{
				countList.add("二级审核通过");
			}
			resultList.add(countList);
		}
		ExcelUtils.addValue("typename", "审核明细表");
		ExcelUtils.addValue("resultList", resultList);

		/*-------------- 2.写出excel文件--------------*/
		String dirs = request.getSession().getServletContext()
				.getRealPath("")
				+ "/template/";
		String templateFileName = "审核明细表";// 模版名称（不含扩张名） 
		String templateFilePath = dirs + templateFileName + ".xls";
		String destFilePath = dirs + templateFileName + "-out.xls";
		try {
			System.out.println("templateFilePath=" + templateFilePath);
			OutputStream out = new FileOutputStream(destFilePath);
			ExcelUtils.export(templateFilePath, out);
			response.setContentType("application/x-download");
			System.out.println("destFilePath=" + destFilePath);
			String filenamedisplay = templateFileName + "-out.xls";
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

