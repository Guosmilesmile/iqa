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
import cn.edu.xmu.dao.UnfinishedFormDao;
import cn.edu.xmu.daoimpl.TableListDaoImpl;
import cn.edu.xmu.daoimpl.UnfinishedFormDaoImpl;
import cn.edu.xmu.entity.UnfinishedForm;
import cn.edu.xmu.table.UnfinishedFormTable;
import net.sf.excelutils.ExcelException;
import net.sf.excelutils.ExcelUtils;

/**
 * 未填表格说明
 * @author chunfeng
 * 
 */
@WebServlet("/Sec_ExportUnfinishedFormServlet")
public class Sec_ExportUnfinishedFormServlet extends HttpServlet
{

	private static final long serialVersionUID = 1L;

	public Sec_ExportUnfinishedFormServlet()
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
		String college = request.getParameter("college");
		college = URLDecoder.decode(college, "UTF-8");
		Date deadLine = tableListDao.getTableDate(tableid);
		Map queryParams = new HashMap();
		//if (deadLine != null)
			//queryParams.put(PracticePlaceTable.PP_DEADLINE, deadLine);
		if (college != null && !"".equals(college))
		{
			if (college.contains("学院"))
			{// 具体的学院才过滤
				queryParams.put(UnfinishedFormTable.UF_COLLEGE, college);
			}
		}
		if (tablename.endsWith("附表未填表格情况"))
		{
			UnfinishedFormDao dao = new UnfinishedFormDaoImpl();
			List<UnfinishedForm> ufList = dao.getUnfinishedForm(0, dao.getUnfinishedFormCount(queryParams),
					UnfinishedFormTable.UF_ID, "asc", queryParams,deadLine);
			/*-------------- 1.准备数据--------------*/
			List<Object> ppResultList = new ArrayList<Object>();
			for (int i = 0; i < ufList.size(); i++)
			{
				List<Object> ppCountList = new ArrayList<Object>();// 行数据
				ppCountList.add(ufList.get(i).getUf_formname());
				ppCountList.add(ufList.get(i).getUf_accountfor());
				
				ppResultList.add(ppCountList);
			}
			ExcelUtils.addValue("typename", tablename);// typename是用在表格模板里面的表名
			ExcelUtils.addValue("resultList", ppResultList);// resultList是用在表格模板里面用于显示数据的
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
