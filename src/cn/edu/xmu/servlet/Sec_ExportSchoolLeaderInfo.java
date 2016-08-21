package cn.edu.xmu.servlet;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.net.URLDecoder;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.xmu.dao.TableListDao;
import cn.edu.xmu.dao.SchoolLeaderInfoDao;
import cn.edu.xmu.daoimpl.TableListDaoImpl;
import cn.edu.xmu.daoimpl.SchoolLeaderInfoDaoImpl;
import cn.edu.xmu.entity.SchoolLeaderInfo;
import cn.edu.xmu.table.SchoolLeaderInfoTable;
import net.sf.excelutils.ExcelException;
import net.sf.excelutils.ExcelUtils;

/**
 * 
 * @author xiaoping 表3-2 校领导基本信息(时点) date 2015-7-11
 *
 */
@WebServlet("/Sec_ExportSchoolLeaderInfo")
public class Sec_ExportSchoolLeaderInfo extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public Sec_ExportSchoolLeaderInfo()
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
		String tableid = request.getParameter("tableid");
		System.out.println("tableid===========" + tableid);
		// 根据tableid得到tablename
		TableListDao tableListDao = new TableListDaoImpl();
		String tablename = tableListDao.getTablename(tableid);
		String college = request.getParameter(SchoolLeaderInfoTable.SLI_COLLEGE);
		college = URLDecoder.decode(college, "UTF-8");
		Date deadLine = tableListDao.getTableDate(tableid);
		Map queryParams = new HashMap();
		if (deadLine != null)
			queryParams.put(SchoolLeaderInfoTable.SLI_DEADLINE, deadLine);
		if (college != null && !"".equals(college))
		{
			if (college.contains("学院"))
			{// 具体的学院才过滤
				queryParams.put(SchoolLeaderInfoTable.SLI_COLLEGE, college);
			}
		}
		if (tablename.endsWith("表3-2校领导基本信息（时点）"))
		{
			SchoolLeaderInfoDao dao = new SchoolLeaderInfoDaoImpl();
			List<SchoolLeaderInfo> tsList = dao.getSchoolLeaderInfos(0, dao.getSchoolLeaderInfoCount(queryParams),
					SchoolLeaderInfoTable.SLI_ID, "asc", queryParams, null);
			/*-------------- 1.准备数据--------------*/
			List<Object> sliResultList = new ArrayList<Object>();
			for (int i = 0; i < tsList.size(); i++)
			{
				List<Object> sliCountList = new ArrayList<Object>();// 行数据
				sliCountList.add(tsList.get(i).getSli_sequencenumber());
				sliCountList.add(tsList.get(i).getSli_name());
				sliCountList.add(tsList.get(i).getSli_worknumber());
				sliCountList.add(tsList.get(i).getSli_position());
				sliCountList.add(tsList.get(i).getSli_gender());
				sliCountList.add(tsList.get(i).getSli_birthday());
				sliCountList.add(tsList.get(i).getSli_inschooldate());
				sliCountList.add(tsList.get(i).getSli_education());
				sliCountList.add(tsList.get(i).getSli_degree());
				sliCountList.add(tsList.get(i).getSli_professionaltitle());
				sliCountList.add(tsList.get(i).getSli_responsibility());
				sliCountList.add(tsList.get(i).getSli_studyworkresume());
				sliResultList.add(sliCountList);
			}
			ExcelUtils.addValue("typename", tablename);// typename是用在表格模板里面的表名
			ExcelUtils.addValue("resultList", sliResultList);// resultList是用在表格模板里面用于显示数据的
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
