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

import cn.edu.xmu.dao.TableListDao;
import cn.edu.xmu.dao.CoverBuildingAreaDao;
import cn.edu.xmu.daoimpl.TableListDaoImpl;
import cn.edu.xmu.daoimpl.CoverBuildingAreaDaoImpl;
import cn.edu.xmu.entity.CoverBuildingArea;
import cn.edu.xmu.table.CoverBuildingAreaTable;
import net.sf.excelutils.ExcelException;
import net.sf.excelutils.ExcelUtils;

/**
 * 
 * @author xiaoping 数据报表2-1 占地与建筑面积(时点) date 2015-7-11
 *
 */
@WebServlet("/Sec_ExportCoverBuildingArea")
public class Sec_ExportCoverBuildingArea extends HttpServlet
{

	private static final long serialVersionUID = 1L;

	public Sec_ExportCoverBuildingArea()
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
		String college = request.getParameter("cba_college");
		college = URLDecoder.decode(college, "UTF-8");
		Date deadLine = tableListDao.getTableDate(tableid);
		Map queryParams = new HashMap();
		if (deadLine != null)
			queryParams.put(CoverBuildingAreaTable.CBA_DEADLINE, deadLine);
		if (college != null && !"".equals(college))
		{
			if (college.contains("学院"))
			{// 具体的学院才过滤
				queryParams.put(CoverBuildingAreaTable.CBA_COLLEGE, college);
			}
		}
		if (tablename.endsWith("表2-1占地与建筑面积（时点）"))
		{
			CoverBuildingAreaDao dao = new CoverBuildingAreaDaoImpl();
			List<CoverBuildingArea> tsList = dao.getCoverBuildingAreaList(0, dao.getCoverBuildingAreaCount(queryParams),
					CoverBuildingAreaTable.CBA_ID, "asc", queryParams, null);
			/*-------------- 1.准备数据--------------*/
			List<Object> cbaResultList = new ArrayList<Object>();
			for (int i = 0; i < tsList.size(); i++)
			{
				List<Object> cbaCountList = new ArrayList<Object>();// 行数据
				cbaCountList.add(tsList.get(i).getCba_statisticaltime());
				cbaCountList.add(tsList.get(i).getCba_fillschool());
				cbaCountList.add(tsList.get(i).getCba_totalcoverarea());
				cbaCountList.add(tsList.get(i).getCba_propertycov());
				cbaCountList.add(tsList.get(i).getCba_propertyafforestcov());
				cbaCountList.add(tsList.get(i).getCba_nonpropertycov());
				cbaCountList.add(tsList.get(i).getCba_nonpropertyafforestcov());
				cbaCountList.add(tsList.get(i).getCba_nonproindepusecov());
				cbaCountList.add(tsList.get(i).getCba_nonprocommonusecov());
				cbaCountList.add(tsList.get(i).getCba_totalbuildingarea());
				cbaCountList.add(tsList.get(i).getCba_propertybui());
				cbaCountList.add(tsList.get(i).getCba_nonpropertybui());
				cbaCountList.add(tsList.get(i).getCba_nonproindepusebui());
				cbaCountList.add(tsList.get(i).getCba_nonprocommonusebui());
				cbaResultList.add(cbaCountList);
			}
			ExcelUtils.addValue("typename", tablename);// typename是用在表格模板里面的表名
			ExcelUtils.addValue("resultList", cbaResultList);// resultList是用在表格模板里面用于显示数据的
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
