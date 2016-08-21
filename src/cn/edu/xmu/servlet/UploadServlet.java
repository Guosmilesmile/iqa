package cn.edu.xmu.servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.io.Reader;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadServlet() {
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
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;Charset=UTF-8");
		InputStream inputStream = request.getInputStream();
		String tempfileString = "E:/tempFile";
		File tempfile = new File(tempfileString);
		FileOutputStream fileOutputStream = new FileOutputStream(tempfile);
		byte b[] = new byte[1024];
		int n;
		while ((n = inputStream.read(b)) != -1) {
			fileOutputStream.write(b, 0, n);
		}
		fileOutputStream.close();
		inputStream.close();

		RandomAccessFile randomAccessFile = new RandomAccessFile(tempfile, "r");
		randomAccessFile.readLine();
		String string = new String(randomAccessFile.readLine().getBytes(
				"ISO-8859-1"), "utf-8");
		/*
		 * FileReader fileReader = new FileReader(tempfile); BufferedReader
		 * bufferedReader = new BufferedReader(fileReader);
		 * bufferedReader.readLine(); String string1 =
		 * bufferedReader.readLine();
		 */

		String filename = string.substring(0, string.length() - 1);
		int start = filename.lastIndexOf("\"");
		filename = filename.substring(start + 1, filename.length());
		System.out.println(filename);

		// 从第四行开始读取。
		randomAccessFile.seek(0);
		long startposition = 0;
		int i = 1;
		while ((n = randomAccessFile.readByte()) != -1 && i <= 4) {
			if (n == '\n') {
				startposition = randomAccessFile.getFilePointer();
				i++;
			}
		}
		startposition = randomAccessFile.getFilePointer() - 1;

		randomAccessFile.seek(randomAccessFile.length());
		long endpositon = randomAccessFile.getFilePointer();
		int j = 1;
		while (endpositon >= 0 && j <= 2) {
			endpositon--;
			randomAccessFile.seek(endpositon);
			if (randomAccessFile.readByte() == '\n') {
				j++;
			}
		}
		endpositon = endpositon - 1;

		String realpath = getServletContext().getRealPath("/") + "upload";
		File fileload = new File(realpath);
		if (!fileload.exists()) {
			fileload.mkdir();
		}

		System.out.println(realpath);
		System.out.println(filename);

		File saveFile = new File(realpath, filename);
		RandomAccessFile randomAccessFile2 = new RandomAccessFile(saveFile,
				"rw");
		randomAccessFile.seek(startposition);
		while (startposition < endpositon) {
			randomAccessFile2.write(randomAccessFile.readByte());
			startposition = randomAccessFile.getFilePointer();
		}

		randomAccessFile.close();
		randomAccessFile2.close();
		tempfile.delete();
		System.out.println("success");
		request.setAttribute("result", "success");
		request.getRequestDispatcher("jsp/01.jsp").forward(request, response);
	}

}
