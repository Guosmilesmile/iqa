package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
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
import cn.edu.xmu.dao.SlideNewsDao;
import cn.edu.xmu.daoimpl.NewsDaoImpl;
import cn.edu.xmu.daoimpl.SlideNewsDaoImpl;
import cn.edu.xmu.entity.News;
import cn.edu.xmu.entity.SlideNews;
import cn.edu.xmu.table.NewsTable;
import cn.edu.xmu.table.SlideNewsTable;

/**
 * Servlet implementation class PublishSlideNews
 */
/**
 * 发布滑动新闻的逻辑类
 * 
 * @author Lee
 *
 */
@WebServlet("/PublishSlideNews")
public class PublishSlideNews extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PublishSlideNews() {
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
				+ Common.SLIDENEWS_IMG_URL;

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

			// 标题
			String sn_titles = su.getRequest().getParameter(
					SlideNewsTable.SN_TITLES);
			// 内容
			String sn_content = su.getRequest().getParameter(
					SlideNewsTable.SN_CONTENT);
			// 版本
			String sn_version = su.getRequest().getParameter(
					SlideNewsTable.SN_VERSION);

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
				image_url = Common.SLIDENEWS_IMG_URL + image_url;
			}

			SlideNews slidenews = new SlideNews(sn_titles, sn_version,
					sn_content, image_url);
			SlideNewsDao slideNewsDao = new SlideNewsDaoImpl();
			int result = slideNewsDao.addSlideNewsRecord(slidenews);
			if (result == 1) {
				response.sendRedirect("./admin/getallslidenews.jsp");
				System.out.println("滚动新闻单条纪录添加成功");
			}
		} catch (SmartUploadException e) {
			e.printStackTrace();
		}

	}

}
