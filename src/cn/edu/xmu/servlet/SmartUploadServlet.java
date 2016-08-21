package cn.edu.xmu.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

/**
 * Servlet implementation class SmartUploadServlet
 */

public class SmartUploadServlet extends HttpServlet {
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SmartUploadServlet() {
        super();
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
		
		String filePath = getServletContext().getRealPath("/") + "/uploadModel/";
		System.out.println(filePath);
		File file  = new File(filePath);
		if(!file.exists()){
			file.mkdir();
		}
		String result = "上传成功";
		SmartUpload smartUpload = new SmartUpload();
		smartUpload.initialize(getServletConfig(), request, response);
		smartUpload.setMaxFileSize(1024*1024*10);
		smartUpload.setTotalMaxFileSize(1024*1024*100);
		smartUpload.setAllowedFilesList("txt,jpg,png,gif,doc,xlsx,xls");
		try {
			smartUpload.setDeniedFilesList("rar,jsp,html");
		} catch (SQLException e) {
			e.printStackTrace();
			result="上传失败";
		}
		try {
			smartUpload.upload();
			int count = 0;
			count = smartUpload.save(filePath);
		} catch (SmartUploadException e) {
			e.printStackTrace();
			result="上传失败";
		}
		request.setAttribute("result", result);
		request.getRequestDispatcher("admin/uploadModel.jsp").forward(request, response);
	}

}
