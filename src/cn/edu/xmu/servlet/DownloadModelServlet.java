package cn.edu.xmu.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.excelutils.ExcelUtils;
import cn.edu.xmu.dao.TableListDao;
import cn.edu.xmu.daoimpl.TableDaoImpl;
import cn.edu.xmu.daoimpl.TableListDaoImpl;

/**
 * Servlet implementation class DownloadModelServlet
 */
@WebServlet("/DownloadModelServlet")
public class DownloadModelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadModelServlet() {
        super();
        // TODO Auto-generated constructor stub
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
		System.out.println("进入下载模板模块");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		TableListDao tableListDao = new TableListDaoImpl();
		String path = getServletContext().getRealPath("/") + "/uploadModel/";
		String tableid = request.getParameter("tableid");
		String filename = tableListDao.getTablename(tableid);
		System.out.println(filename);
		filename=URLDecoder.decode(filename,"utf-8");
		System.out.println(filename);
		File file = new File(path + filename+ ".xls");
		String destFilePath = path + filename + ".xls";
		System.out.println(file.getAbsolutePath());
		if (file.exists()) {
			System.out.println("进入");
			response.setContentType("application/x-download");
			String filenamedisplay = filename + ".xls";
			filenamedisplay = URLEncoder.encode(filenamedisplay, "UTF-8");
			response.addHeader("Content-Disposition",
					"attachment;filename=" + filenamedisplay);
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
			System.out.println("end");

		} else {
			request.setAttribute("successResult", "删除失败");
			request.getRequestDispatcher("admin/failed.jsp").forward(
					request, response);
		}
	}

}
