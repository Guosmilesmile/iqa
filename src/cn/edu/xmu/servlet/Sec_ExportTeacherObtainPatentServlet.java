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
import cn.edu.xmu.dao.TeacherObtainPatentDao;
import cn.edu.xmu.daoimpl.TableListDaoImpl;
import cn.edu.xmu.daoimpl.TeacherObtainPatentDaoImpl;
import cn.edu.xmu.entity.TeacherObtainPatent;
import cn.edu.xmu.table.TeacherObtainPatentTable;
import net.sf.excelutils.ExcelException;
import net.sf.excelutils.ExcelUtils;
/**
 * 3-6-6  教师获准专利数（自然年）
 * @author chunfeng
 *
 */
@WebServlet("/Sec_ExportTeacherObtainPatentServlet")
public class Sec_ExportTeacherObtainPatentServlet extends HttpServlet
{

	private static final long serialVersionUID = 1L;

	public Sec_ExportTeacherObtainPatentServlet()
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
		String college = request.getParameter("tsa_college");
		college = URLDecoder.decode(college, "UTF-8");
		Date deadLine = tableListDao.getTableDate(tableid);
		Map queryParams = new HashMap();
		//if (deadLine != null)
			//queryParams.put(TeacherObtainPatentTable.TOP_DEADLINE, deadLine);
		if (college != null && !"".equals(college))
		{
			if (college.contains("学院"))
			{// 具体的学院才过滤
				queryParams.put(TeacherObtainPatentTable.TOP_COLLEGE, college);
			}
		}
		if (tablename.endsWith("表3-6-6教师获准专利数（自然年）"))
		{
			TeacherObtainPatentDao dao = new TeacherObtainPatentDaoImpl();
			List<TeacherObtainPatent> tsList = dao.getTeacherObtainPatent(0, dao.getTeacherObtainPatentCount(queryParams),
					TeacherObtainPatentTable.TOP_ID, "asc", queryParams,deadLine);
			/*-------------- 1.准备数据--------------*/
			List<Object> topResultList = new ArrayList<Object>();
			for (int i = 0; i < tsList.size(); i++)
			{
				List<Object> tsaCountList = new ArrayList<Object>();// 行数据
				tsaCountList.add(tsList.get(i).getTop_total());
				tsaCountList.add(tsList.get(i).getTop_invention());
				tsaCountList.add(tsList.get(i).getTop_utilitymodel());
				topResultList.add(tsaCountList);
			}
			ExcelUtils.addValue("typename", tablename);// typename是用在表格模板里面的表名
			ExcelUtils.addValue("resultList", topResultList);// resultList是用在表格模板里面用于显示数据的
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
