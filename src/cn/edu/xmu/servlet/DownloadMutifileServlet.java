package cn.edu.xmu.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.excelutils.ExcelException;
import net.sf.excelutils.ExcelUtils;
import cn.edu.xmu.dao.ForeignExchangeDao;
import cn.edu.xmu.dao.SuperMajorDao;
import cn.edu.xmu.daoimpl.ForeignExchangeDaoImpl;
import cn.edu.xmu.daoimpl.SuperMajorDaoImpl;
import cn.edu.xmu.entity.ForeignExchange;
import cn.edu.xmu.entity.SuperMajor;

/**
 * Servlet implementation class DownloadMutifileServlet
 */
@WebServlet("/DownloadMutifileServlet")
public class DownloadMutifileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DownloadMutifileServlet() {
		super();
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
		try {
//			=============4-2-3优势专业情况=====================//
			SuperMajorDao dao = new SuperMajorDaoImpl();
			List<SuperMajor> list = dao.getAllSuperMajor();
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i).getSm_name());
			}
			System.out.println(list.size());
			//-------------- 1.准备数据--------------
			List<Object> resultList = new ArrayList<Object>();
			for (int i = 0; i < list.size(); i++) {
				List<Object> countList = new ArrayList<Object>();// 行数据
				countList.add(list.get(i).getSm_name());
				countList.add(list.get(i).getSm_number());
				countList.add(list.get(i).getSm_class());
				countList.add(list.get(i).getC_startyear());
				countList.add(list.get(i).getP_startyear());
				countList.add(list.get(i).getS_startyear());
				countList.add(list.get(i).getRespon_person());
				countList.add(list.get(i).getSm_college());
				resultList.add(countList);
			}
			int counts = list.size();
			ExcelUtils.addValue("typename", "4-2-3优势专业情况");
			ExcelUtils.addValue("counts", counts);
			ExcelUtils.addValue("resultList", resultList);

			//-------------- 2.写出excel文件--------------
			String dirs = request.getSession().getServletContext()
					.getRealPath("")
					+ "/template/";
			String templateFileName = "AircraftType";// 模版名称（不含扩张名） AircraftType
			String templateFilePath = dirs + templateFileName + ".xls";
			String destFilePath = dirs + "4-2-3优势专业情况" + "-out.xls";
			try {
				System.out.println("templateFilePath=" + templateFilePath);
				OutputStream out = new FileOutputStream(destFilePath);

				ExcelUtils.export(templateFilePath, out);

				response.setContentType("application/x-download");
				System.out.println("destFilePath=" + destFilePath);
				String filenamedisplay = "4-2-3优势专业情况" + "-out.xls";
				filenamedisplay = URLEncoder.encode(filenamedisplay, "UTF-8");
				response.addHeader("Content-Disposition",
						"attachment;filename=" + filenamedisplay);

			} catch (ExcelException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		//	=============6-2-2-1本科生境外交流项目=====================//
			ForeignExchangeDao feDao = new ForeignExchangeDaoImpl();
			List<ForeignExchange> felist = feDao.getAllForeignExchanges();
			/*-------------- 1.准备数据--------------*/
			List<Object> feResultList = new ArrayList<Object>();
			for (int i = 0; i < felist.size(); i++) {
				List<Object> countList = new ArrayList<Object>();// 行数据
				countList.add(felist.get(i).getFe_id());
				countList.add(felist.get(i).getFe_projectname());
				countList.add(felist.get(i).getFe_country());
				countList.add(felist.get(i).getFe_school());
				countList.add(felist.get(i).getFe_level());
				countList.add(felist.get(i).getFe_time());
				feResultList.add(countList);
			}
			ExcelUtils.addValue("typename", "6-2-2-1本科生境外交流项目");
			ExcelUtils.addValue("resultList", feResultList);

			/*-------------- 2.写出excel文件--------------*/
			String feDirs = request.getSession().getServletContext()
					.getRealPath("")
					+ "/template/";
			String feTemplateFileName = "6-2-2-1本科生境外交流项目";// 模版名称（不含扩张名） AircraftType
			String feTemplateFilePath = feDirs + feTemplateFileName + ".xls";
			String feDestFilePath = feDirs + feTemplateFileName + "-out.xls";
			try {
				OutputStream out = new FileOutputStream(feDestFilePath);
				ExcelUtils.export(feTemplateFilePath, out);
				response.setContentType("application/x-download");
				String filenamedisplay = "6-2-2-1本科生境外交流项目" + "-out.xls";
				filenamedisplay = URLEncoder.encode(filenamedisplay, "UTF-8");
				response.addHeader("Content-Disposition",
						"attachment;filename=" + filenamedisplay);

			} catch (ExcelException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			/**
			 * 这个集合就是你想要打包的所有文件， 这里假设已经准备好了所要打包的文件
			 */
			String allDirs = getServletContext().getRealPath("/");
			File file1 = new File(destFilePath);
			File file2 = new File(feDestFilePath);
			List<File> files = new ArrayList<File>();
			files.add(file1);
			files.add(file2);
			/**
			 * 创建一个临时压缩文件， 我们会把文件流全部注入到这个文件中 这里的文件你可以自定义是.rar还是.zip
			 */
			System.out.println(allDirs);
			String filename = null;
			String completePath = allDirs + filename;
			// 临时的文件名字
			File file = new File(allDirs + "certpics.rar");
			if (!file.exists()) {
				file.createNewFile();
			}
			response.reset();
			// 创建文件输出流
			FileOutputStream fous = new FileOutputStream(file);
			/**
			 * 打包的方法我们会用到ZipOutputStream这样一个输出流, 所以这里我们把输出流转换一下
			 */
			ZipOutputStream zipOut = new ZipOutputStream(fous);
			/**
			 * 这个方法接受的就是一个所要打包文件的集合， 还有一个ZipOutputStream
			 */
			zipFile(files, zipOut);
			zipOut.close();
			fous.close();
			downloadZip(file, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 把接受的全部文件打成压缩包
	 * 
	 * @param List
	 *            <File>;
	 * @param org
	 *            .apache.tools.zip.ZipOutputStream
	 */
	public static void zipFile(List files, ZipOutputStream outputStream) {
		int size = files.size();
		for (int i = 0; i < size; i++) {
			File file = (File) files.get(i);
			zipFile(file, outputStream);
		}
	}

	/**
	 * 根据输入的文件与输出流对文件进行打包
	 * 
	 * @param File
	 * @param org
	 *            .apache.tools.zip.ZipOutputStream
	 */
	public static void zipFile(File inputFile, ZipOutputStream ouputStream) {
		try {
			if (inputFile.exists()) {
				/**
				 * 如果是目录的话这里是不采取操作的， 至于目录的打包正在研究中
				 */
				if (inputFile.isFile()) {
					FileInputStream IN = new FileInputStream(inputFile);
					BufferedInputStream bins = new BufferedInputStream(IN, 512);
					// org.apache.tools.zip.ZipEntry
					ZipEntry entry = new ZipEntry(inputFile.getName());
					ouputStream.putNextEntry(entry);
					// 向压缩文件中输出数据
					int nNumber;
					byte[] buffer = new byte[512];
					while ((nNumber = bins.read(buffer)) != -1) {
						ouputStream.write(buffer, 0, nNumber);
					}
					// 关闭创建的流对象
					bins.close();
					IN.close();
				} else {
					try {
						File[] files = inputFile.listFiles();
						for (int i = 0; i < files.length; i++) {
							zipFile(files[i], ouputStream);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void downloadZip(File file, HttpServletResponse response) {
		try {
			// 以流的形式下载文件。
			InputStream fis = new BufferedInputStream(new FileInputStream(
					file.getPath()));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			// 清空response
			response.reset();

			OutputStream toClient = new BufferedOutputStream(
					response.getOutputStream());
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename="
					+ file.getName());
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				File f = new File(file.getPath());
				f.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
