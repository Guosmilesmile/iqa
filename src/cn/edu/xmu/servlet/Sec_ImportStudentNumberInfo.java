package cn.edu.xmu.servlet;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

import cn.edu.xmu.dao.TableListDao;
import cn.edu.xmu.dao.StudentNumberInfoDao;
import cn.edu.xmu.daoimpl.TableListDaoImpl;
import cn.edu.xmu.daoimpl.StudentNumberInfoDaoImpl;
import cn.edu.xmu.entity.StudentNumberInfo;
import jxl.Sheet;
import jxl.Workbook;
/**
 * 
 * @author xiaoping 数据报表6-1-1 学生数量基本情况（时点）date 2015-7-12
 *
 */
@WebServlet("/Sec_ImportStudentNumberInfo")
public class Sec_ImportStudentNumberInfo extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private static String result;
	private static int errorrow = 1;
	private static String college;

	public Sec_ImportStudentNumberInfo()
	{
		super();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;Charset=UTF-8");
		String tableid = request.getParameter("tableid");
		college = request.getParameter("college");
		college = URLDecoder.decode(college, "utf-8");
		int recordcount = 0;
		List<StudentNumberInfo> sniList = new ArrayList<StudentNumberInfo>();

		TableListDao tableListDao = new TableListDaoImpl();
		StudentNumberInfoDao sniDao = new StudentNumberInfoDaoImpl();

		String tablename = tableListDao.getTablename(tableid);
		System.out.println(tablename);
		String filePath = getServletContext().getRealPath("/") + "/uploadModelTable/";
		String completeFilePath;// excel文件的完整路径
		File file = new File(filePath);
		if (!file.exists())
		{
			file.mkdir();
		}
		result = "导入成功";
		SmartUpload smartUpload = new SmartUpload();
		smartUpload.initialize(getServletConfig(), request, response);
		smartUpload.setMaxFileSize(1024 * 1024 * 10);
		smartUpload.setTotalMaxFileSize(1024 * 1024 * 100);
		smartUpload.setAllowedFilesList("txt,jpg,png,gif,doc,xlsx,xls");
		try
		{
			smartUpload.setDeniedFilesList("rar,jsp,html");
		} catch (SQLException e)
		{
			e.printStackTrace();
			result = "上传失败";
		}
		try
		{
			smartUpload.upload();
			int count = 0;
			count = smartUpload.save(filePath);
			com.jspsmart.upload.File myFile = smartUpload.getFiles().getFile(0);
			completeFilePath = filePath + "\\" + myFile.getFileName();
			System.out.println(completeFilePath);
			if (tablename.equals("表6-1-1学生数量基本情况（时点）"))
			{
				sniList = getAlltsByExcel(completeFilePath);
				sniDao.deleteByCollegeandDeadline(college, null);
				recordcount = sniList.size();
				for (int i = 0; i < sniList.size(); i++)
				{
					sniDao.addStudentNumberInfo(sniList.get(i));
				}
			}
		} catch (SmartUploadException e)
		{
			e.printStackTrace();
			result = "上传失败";
		} catch (SQLException e)
		{
			e.printStackTrace();
			System.out.println("上传失败");
		}

		if (result.equals("导入成功"))
		{
			request.setAttribute("result", result);
			request.setAttribute("count", recordcount);
			request.getRequestDispatcher("upTest/uploadtest.jsp").forward(request, response);
		} else
		{
			request.setAttribute("result", result);
			request.setAttribute("errorrow", errorrow);
			request.getRequestDispatcher("upTest/error.jsp").forward(request, response);
		}
	}

	/**
	 * 得到Excel表格里面的数据
	 * 
	 * @param file
	 * @return
	 */
	public static List<StudentNumberInfo> getAlltsByExcel(String file)
	{
		errorrow = 1;
		List<StudentNumberInfo> sniList = new ArrayList<StudentNumberInfo>();
		try
		{
			Workbook rwb = Workbook.getWorkbook(new File(file));
			Sheet rs = rwb.getSheet(0);// 或者rwb.getSheet(0)
			int clos = rs.getColumns();// 得到所有的列
			int rows = getRightRows(rs);// 得到所有的行
			System.out.println(clos + " rows:" + rows);
				for(int i = 1; i < rows;)
				{
					int j = 1;
					String sni_stuinfobaselink = rs.getCell(j, i++).getContents();// 默认最左边编号也算一列,所以这里得j++
					i++;//该表为竖着的表，第2行（以0开始计算）没有数据
					String ordiundergrastu = rs.getCell(j, i++).getContents();
					String countryoverseastu = rs.getCell(j, i++).getContents();
					String highervocationstu = rs.getCell(j, i++).getContents();
					String postgraduate = rs.getCell(j, i++).getContents();
					String fulltimepost = rs.getCell(j, i++).getContents();
					String nofulltimepost = rs.getCell(j, i++).getContents();
					String doctorcandidate = rs.getCell(j, i++).getContents();
					String fulltimedoct = rs.getCell(j, i++).getContents();
					String nofulltimedoct = rs.getCell(j, i++).getContents();
					String abroadstu = rs.getCell(j, i++).getContents();
					String ordipreppie = rs.getCell(j, i++).getContents();
					String advancedstu = rs.getCell(j, i++).getContents();
					String adultfulltimestu = rs.getCell(j, i++).getContents();
					String parttimestu = rs.getCell(j, i++).getContents();
					String correspondencestu = rs.getCell(j, i++).getContents();
					String networkstu = rs.getCell(j, i++).getContents();
					String selftaughtstu = rs.getCell(j, i++).getContents();
					int sni_ordiundergrastu = -9;
					int sni_countryoverseastu = -9;
					int sni_highervocationstu = -9;
					int sni_postgraduate = -9;
					int sni_fulltimepost = -9;
					int sni_nofulltimepost = -9;
					int sni_doctorcandidate = -9;
					int sni_fulltimedoct = -9;
					int sni_nofulltimedoct = -9;
					int sni_abroadstu = -9;
					int sni_ordipreppie = -9;
					int sni_advancedstu = -9;
					int sni_adultfulltimestu = -9;
					int sni_parttimestu = -9;
					int sni_correspondencestu = -9;
					int sni_networkstu = -9;
					int sni_selftaughtstu = -9;
					int sni_isnull = 0;
					if ("".equals(sni_stuinfobaselink) || "".equals(ordiundergrastu) || "".equals(countryoverseastu)
							|| "".equals(highervocationstu) || "".equals(postgraduate)
							|| "".equals(fulltimepost) || "".equals(nofulltimepost)
							|| "".equals(doctorcandidate) || "".equals(fulltimedoct)
							|| "".equals(nofulltimedoct) || "".equals(abroadstu)
							|| "".equals(ordipreppie) || "".equals(advancedstu)
							|| "".equals(adultfulltimestu) || "".equals(parttimestu)
							|| "".equals(correspondencestu) || "".equals(networkstu)
							|| "".equals(selftaughtstu))
						sni_isnull = 1;
					if ("".equals(sni_stuinfobaselink) && "".equals(ordiundergrastu) && "".equals(countryoverseastu)
							&& "".equals(highervocationstu) && "".equals(postgraduate)
							&& "".equals(fulltimepost) && "".equals(nofulltimepost)
							&& "".equals(doctorcandidate) && "".equals(fulltimedoct)
							&& "".equals(nofulltimedoct) && "".equals(abroadstu)
							&& "".equals(ordipreppie) && "".equals(advancedstu)
							&& "".equals(adultfulltimestu) && "".equals(parttimestu)
							&& "".equals(correspondencestu) && "".equals(networkstu)
							&& "".equals(selftaughtstu))
						break;
					if(ordiundergrastu!=null && !"".equals(ordiundergrastu))
						sni_ordiundergrastu = Integer.parseInt(ordiundergrastu);
					if(countryoverseastu!=null && !"".equals(countryoverseastu))
						sni_countryoverseastu = Integer.parseInt(countryoverseastu);
					if(highervocationstu!=null && !"".equals(highervocationstu))
						sni_highervocationstu = Integer.parseInt(highervocationstu);
					if(postgraduate!=null && !"".equals(postgraduate))
						sni_postgraduate = Integer.parseInt(postgraduate);
					if(fulltimepost!=null && !"".equals(fulltimepost))
						sni_fulltimepost = Integer.parseInt(fulltimepost);
					if(nofulltimepost!=null && !"".equals(nofulltimepost))
						sni_nofulltimepost = Integer.parseInt(nofulltimepost);
					if(doctorcandidate!=null && !"".equals(doctorcandidate))
						sni_doctorcandidate = Integer.parseInt(doctorcandidate);
					if(fulltimedoct!=null && !"".equals(fulltimedoct))
						sni_fulltimedoct = Integer.parseInt(fulltimedoct);
					if(nofulltimedoct!=null && !"".equals(nofulltimedoct))
						sni_nofulltimedoct = Integer.parseInt(nofulltimedoct);
					if(abroadstu!=null && !"".equals(abroadstu))
						sni_abroadstu = Integer.parseInt(abroadstu);
					if(ordipreppie!=null && !"".equals(ordipreppie))
						sni_ordipreppie = Integer.parseInt(ordipreppie);
					if(advancedstu!=null && !"".equals(advancedstu))
						sni_advancedstu = Integer.parseInt(advancedstu);
					if(adultfulltimestu!=null && !"".equals(adultfulltimestu))
						sni_adultfulltimestu = Integer.parseInt(adultfulltimestu);
					if(parttimestu!=null && !"".equals(parttimestu))
						sni_parttimestu = Integer.parseInt(parttimestu);
					if(correspondencestu!=null && !"".equals(correspondencestu))
						sni_correspondencestu = Integer.parseInt(correspondencestu);
					if(networkstu!=null && !"".equals(networkstu))
						sni_networkstu = Integer.parseInt(networkstu);
					if(selftaughtstu!=null && !"".equals(selftaughtstu))
						sni_selftaughtstu = Integer.parseInt(selftaughtstu);
					sniList.add(new StudentNumberInfo(0, sni_stuinfobaselink, sni_ordiundergrastu, sni_countryoverseastu, sni_highervocationstu, sni_postgraduate, sni_fulltimepost, sni_nofulltimepost, sni_doctorcandidate, sni_fulltimedoct, sni_nofulltimedoct, sni_abroadstu, sni_ordipreppie, sni_advancedstu, sni_adultfulltimestu, sni_parttimestu, sni_correspondencestu, sni_networkstu, sni_selftaughtstu, 1, null, college, "", sni_isnull));
				errorrow++;
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			result = "导入失败";
		}
		return sniList;
	}

	// 返回去掉空行的记录数
	private static int getRightRows(Sheet sheet)
	{
		int rsCols = sheet.getColumns(); // 列数
		int rsRows = sheet.getRows(); // 行数
		int nullCellNum;
		int afterRows = rsRows;
		for (int i = 1; i < rsRows; i++)
		{ // 统计行中为空的单元格数
			nullCellNum = 0;
			for (int j = 0; j < rsCols; j++)
			{
				String val = sheet.getCell(j, i).getContents();
				val = StringUtils.trimToEmpty(val);
				if (StringUtils.isBlank(val))
					nullCellNum++;
			}
			if (nullCellNum >= rsCols)
			{ // 如果nullCellNum大于或等于总的列数
				afterRows--; // 行数减一
			}
		}
		return afterRows;
	}
}
