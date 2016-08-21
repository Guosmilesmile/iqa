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

import cn.edu.xmu.dao.LibraryYearDao;
import cn.edu.xmu.dao.TableListDao;
import cn.edu.xmu.daoimpl.LibraryYearDaoImpl;
import cn.edu.xmu.daoimpl.TableListDaoImpl;
import cn.edu.xmu.entity.LibraryYear;
import cn.edu.xmu.table.LibraryYearTable;
import net.sf.excelutils.ExcelException;
import net.sf.excelutils.ExcelUtils;

@WebServlet("/Sec_ExportLibraryYear")
public class Sec_ExportLibraryYear extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public Sec_ExportLibraryYear()
	{
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String tableid = request.getParameter("tableid");
		System.out.println("tableid===========" + tableid);
		// 根据tableid得到tablename
		TableListDao tableListDao = new TableListDaoImpl();
		String tablename = tableListDao.getTablename(tableid);
		Date deadLine = tableListDao.getTableDate(tableid);// tableList中应当注意发布时清空，关闭时赋值
		System.out.println("deadline:" + deadLine);
		String college = request.getParameter("ly_college");
		college = URLDecoder.decode(college, "UTF-8");
		Map queryParams = new HashMap();
		if (deadLine != null)
			queryParams.put(LibraryYearTable.LY_DEADLINE, deadLine);
		if (college != null && !"".equals(college))
		{
			if (college.contains("学院"))
			{// 具体的学院才过滤
				queryParams.put(LibraryYearTable.LY_COLLEGE, college);
			}
		}
		if (tablename.endsWith("表2-5-1图书馆（自然年）"))
		{
			LibraryYearDao lyd = new LibraryYearDaoImpl();
			List<LibraryYear> lyList = lyd.getLibraryYear(0, lyd.getLibraryYearCount(queryParams),
					LibraryYearTable.LY_ID, "asc", queryParams);

			List<Object> lyResultList = new ArrayList<>();
			for (int i = 0; i < lyList.size(); i++)
			{
				List<Object> lyCountList = new ArrayList<>();
				LibraryYear ly = lyList.get(i);
				lyCountList.add(ly.getLy_number());
				lyCountList.add(ly.getLy_seatnumber());
				lyCountList.add(ly.getLy_paperbooknumber());
				lyCountList.add(ly.getLy_paperjournalnumber());
				lyCountList.add(ly.getLy_paperjournaltype());
				lyCountList.add(ly.getLy_ebooknumber());
				lyCountList.add(ly.getLy_ebookchnnumber());
				lyCountList.add(ly.getLy_ebookforeignnumber());
				lyCountList.add(ly.getLy_ejournaltype());
				lyCountList.add(ly.getLy_databasenumber());

				lyResultList.add(lyCountList);

			}

			ExcelUtils.addValue("typename", tablename);// typename是用在表格模板里面的表名
			ExcelUtils.addValue("resultList", lyResultList);// resultList是用在表格模板里面用于显示数据的
			/*-------------- 2.写出excel文件--------------*/
			String dirs = request.getSession().getServletContext().getRealPath("") + "/template/";
			String templateFileName = tablename;// 模版名称（不含扩展名），用于导出的模板
			String templateFilePath = dirs + templateFileName + ".xls";// 导出的模板路径
			String destFilePath = dirs + templateFileName + "-out.xls";// 导出的文件路径

			try
			{
				System.out.println("templateFilePath=" + templateFilePath);
				OutputStream out = new FileOutputStream(destFilePath);
				ExcelUtils.export(templateFilePath, out);
				response.setContentType("application/x-download");
				System.out.println("destFilePath=" + destFilePath);
				String filenamedisplay = tablename + "-out.xls";
				filenamedisplay = URLEncoder.encode(filenamedisplay, "UTF-8");
				response.addHeader("Content-Disposition", "attachment;filename=" + filenamedisplay);
			} catch (ExcelException e)
			{
				e.printStackTrace();
			} catch (FileNotFoundException e)
			{
				e.printStackTrace();
			}
			try
			{
				java.io.OutputStream os = response.getOutputStream();
				java.io.FileInputStream fis = new java.io.FileInputStream(destFilePath);
				byte[] b = new byte[1024];
				int i = 0;
				while ((i = fis.read(b)) > 0)
				{
					os.write(b, 0, i);
				}
				fis.close();
				os.flush();
				os.close();
			} catch (Exception e)
			{
				System.out.println("error");
			}

		}
	}
}
