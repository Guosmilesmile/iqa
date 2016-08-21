package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lxh.smart.File;
import org.lxh.smart.SmartUpload;
import org.lxh.smart.SmartUploadException;

import cn.edu.xmu.common.Common;
import cn.edu.xmu.dao.NewsDao;
import cn.edu.xmu.daoimpl.NewsDaoImpl;
import cn.edu.xmu.entity.News;
import cn.edu.xmu.table.NewsTable;

/**
 * Servlet implementation class UpdateNews
 */
@WebServlet("/UpdateNews")
public class UpdateNews extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateNews() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		String imagepath = this.getServletContext().getRealPath("/")
				+ Common.NEWS_IMG_URL;

		System.out.println(imagepath);
		java.io.File fpath = new java.io.File(imagepath);
		if (!fpath.exists()) {
			fpath.mkdirs();
		}
		SmartUpload su = new SmartUpload();
		su.initialize(this.getServletConfig(), request, response);
		su.setMaxFileSize(10 * 1024 * 1024);
		su.setAllowedFilesList("gif,png,jpg,jpeg,JPEG");
		try {
			su.upload();
			int ss = su.getFiles().getCount();
			System.out.println(ss);

			String n_id = su.getRequest().getParameter(NewsTable.N_ID);
			// 标题
			String n_titles = su.getRequest().getParameter(NewsTable.N_TITLES);
			// 作者
			String n_author = su.getRequest().getParameter(NewsTable.N_AUTHOR);
			// 概要
			String n_summary = su.getRequest()
					.getParameter(NewsTable.N_SUMMARY);
			// 内容
			String n_content = su.getRequest()
					.getParameter(NewsTable.N_CONTENT);
			// 大栏目类别
			String sclass = su.getRequest().getParameter("maintype");
			// 小栏目类别
			String ssubclass = su.getRequest().getParameter("subtype");
			// 时间
			String publishtime = su.getRequest().getParameter(
					NewsTable.N_PUBLISHTIME);

			System.out.println("大类" + sclass + "子类别" + ssubclass);
			// 中英文
			String n_version = su.getRequest()
					.getParameter(NewsTable.N_VERSION);

			String image_url = "";
			for (int i = 0; i < su.getFiles().getCount(); i++) {
				if (su.getFiles().getFile(i).getFileExt().equals("jpg")
						|| su.getFiles().getFile(i).getFileExt().equals("jpeg")
						|| su.getFiles().getFile(i).getFileExt().equals("png")) {
					File image_file = su.getFiles().getFile(i);
					if (image_file.isMissing()) {
						System.out.println("图片丢失");
						out.print("error");
					} else {
						image_url = image_url + new Date().getTime()
								+ (new Random().nextInt(900) + 100) + "."
								+ image_file.getFileExt();
						image_file.saveAs(imagepath + image_url);
						System.out.println("存储成功");
					}
				}
			}
			if (!"".equals(image_url)) {
				image_url = Common.NEWS_IMG_URL + image_url;
			}

			Map<String, String> param1 = new HashMap<String, String>();
			param1.put(NewsTable.N_TITLES, n_titles);
			param1.put(NewsTable.N_AUTHOR, n_author);
			param1.put(NewsTable.N_SUMMARY, n_summary);
			param1.put(NewsTable.N_CONTENT, n_content);
			param1.put(NewsTable.N_CLASS, sclass);
			param1.put(NewsTable.N_SUB_CLASS, ssubclass);
			param1.put(NewsTable.N_VERSION, n_version);
			param1.put(NewsTable.N_IMGURL, image_url);
			if (!publishtime.equals("")) {
				param1.put(NewsTable.N_PUBLISHTIME, publishtime);
			}

			Map<String, String> param2 = new HashMap<String, String>();
			param2.put(NewsTable.N_ID, n_id);

			// 修改的操作
			NewsDao newsDao = new NewsDaoImpl();

			int result = newsDao.updateRecord(NewsTable.TABLE_NAME, param1,
					param2);
			if (result == 1) {
				response.sendRedirect("./admin/getallnews.jsp");
			}
		} catch (SmartUploadException e) {
			e.printStackTrace();
		} finally {
			try {
			} catch (Exception e2) {
			}
		}

	}

}
