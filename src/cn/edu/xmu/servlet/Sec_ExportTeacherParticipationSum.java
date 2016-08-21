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
import cn.edu.xmu.dao.TeacherParticipationSumDao;
import cn.edu.xmu.daoimpl.TableListDaoImpl;
import cn.edu.xmu.daoimpl.TeacherParticipationSumDaoImpl;
import cn.edu.xmu.entity.TeacherParticipationSum;
import cn.edu.xmu.table.TeacherParticipationSumTable;
import net.sf.excelutils.ExcelException;
import net.sf.excelutils.ExcelUtils;

/**
 * 
 * @author xiaoping 数据报表 附表3-5-1-3 教师参加院级及以上教学竞赛情况汇总表（自然年） date 2015-7-12
 *
 */
@WebServlet("/Sec_ExportTeacherParticipationSum")
public class Sec_ExportTeacherParticipationSum extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public Sec_ExportTeacherParticipationSum()
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
		String college = request.getParameter(TeacherParticipationSumTable.TPS_COLLEGE);
		college = URLDecoder.decode(college, "UTF-8");
		Date deadLine = tableListDao.getTableDate(tableid);
		Map queryParams = new HashMap();
		if (deadLine != null)
			queryParams.put(TeacherParticipationSumTable.TPS_DEADLINE, deadLine);
		if (college != null && !"".equals(college))
		{
			if (college.contains("学院"))
			{// 具体的学院才过滤
				queryParams.put(TeacherParticipationSumTable.TPS_COLLEGE, college);
			}
		}
		if (tablename.endsWith("附表3-5-1-3教师参加院级及以上教学竞赛情况"))
		{
			TeacherParticipationSumDao dao = new TeacherParticipationSumDaoImpl();
			List<TeacherParticipationSum> tsList = dao.getTeacherParticipationSums(0,
					dao.getTeacherParticipationSumCount(queryParams), TeacherParticipationSumTable.TPS_ID, "asc",
					queryParams, null);
			/*-------------- 1.准备数据--------------*/
			List<Object> tpsResultList = new ArrayList<Object>();
			for (int i = 0; i < tsList.size(); i++)
			{
				List<Object> tpsCountList = new ArrayList<Object>();// 行数据
				tpsCountList.add(tsList.get(i).getTps_particollege());
				tpsCountList.add(tsList.get(i).getTps_schskillcompecourtyardnum());
				tpsCountList.add(tsList.get(i).getTps_schskillcompeschoolnum());
				tpsCountList.add(tsList.get(i).getTps_schskillcompespecialnum());
				tpsCountList.add(tsList.get(i).getTps_schskillcompefirstnum());
				tpsCountList.add(tsList.get(i).getTps_schskillcompesecnum());
				tpsCountList.add(tsList.get(i).getTps_provinskillcompepartinum());
				tpsCountList.add(tsList.get(i).getTps_provinskillcompespecialnum());
				tpsCountList.add(tsList.get(i).getTps_provinskillcompefirstnum());
				tpsCountList.add(tsList.get(i).getTps_provinskillcompesecnum());
				tpsCountList.add(tsList.get(i).getTps_countryskillcompepartinum());
				tpsCountList.add(tsList.get(i).getTps_countryskillcompespecialnum());
				tpsCountList.add(tsList.get(i).getTps_countryskillcompefirstnum());
				tpsCountList.add(tsList.get(i).getTps_countryskillcompesecnum());
				tpsCountList.add(tsList.get(i).getTps_countrymicrocompepartinum());
				tpsCountList.add(tsList.get(i).getTps_countrymicrocompespecialnum());
				tpsCountList.add(tsList.get(i).getTps_countrymicrocompefirstnum());
				tpsCountList.add(tsList.get(i).getTps_countrymicrocompesecnum());
				// tpsCountList.add(tsList.get(i).getTps_preparer());
				tpsResultList.add(tpsCountList);
			}
			ExcelUtils.addValue("typename", tablename);// typename是用在表格模板里面的表名
			ExcelUtils.addValue("resultList", tpsResultList);// resultList是用在表格模板里面用于显示数据的
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
