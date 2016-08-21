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

import cn.edu.xmu.dao.TeachersScientificAchievementsDao;
import cn.edu.xmu.dao.TableListDao;
import cn.edu.xmu.daoimpl.TeachersScientificAchievementsDaoImpl;
import cn.edu.xmu.daoimpl.TableListDaoImpl;
import cn.edu.xmu.entity.TeachersScientificAchievements;
import cn.edu.xmu.table.TeachersScientificAchievementsTable;
import net.sf.excelutils.ExcelException;
import net.sf.excelutils.ExcelUtils;
/**
 * 3-6-3  教师最近一届科研成果奖数
 * @author chunfeng
 *
 */
@WebServlet("/Sec_ExportTeachersScientificAchievementsServlet")
public class Sec_ExportTeachersScientificAchievementsServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public Sec_ExportTeachersScientificAchievementsServlet()
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
			//queryParams.put(TeachersScientificAchievementsTable.TSA_DEADLINE, deadLine);
		if (college != null && !"".equals(college))
		{
			if (college.contains("学院"))
			{// 具体的学院才过滤
				queryParams.put(TeachersScientificAchievementsTable.TSA_COLLEGE, college);
			}
		}
		if (tablename.endsWith("表3-6-3教师最近一届科研成果奖数（时点）"))
		{
			TeachersScientificAchievementsDao dao = new TeachersScientificAchievementsDaoImpl();
			List<TeachersScientificAchievements> tsList = dao.getTeachersScientificAchievements(0, dao.getTeachersScientificAchievementsCount(queryParams),
					TeachersScientificAchievementsTable.TSA_ID, "asc", queryParams,deadLine);
			/*-------------- 1.准备数据--------------*/
			List<Object> tsaResultList = new ArrayList<Object>();
			for (int i = 0; i < tsList.size(); i++)
			{
				List<Object> tsaCountList = new ArrayList<Object>();// 行数据
				tsaCountList.add(tsList.get(i).getTsa_total());
				tsaCountList.add(tsList.get(i).getTsa_nationallevel());
				tsaCountList.add(tsList.get(i).getTsa_provinciallevel());
				tsaResultList.add(tsaCountList);
			}
			ExcelUtils.addValue("typename", tablename);// typename是用在表格模板里面的表名
			ExcelUtils.addValue("resultList", tsaResultList);// resultList是用在表格模板里面用于显示数据的
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
