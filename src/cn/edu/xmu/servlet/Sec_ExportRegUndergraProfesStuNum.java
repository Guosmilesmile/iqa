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
import cn.edu.xmu.dao.RegUndergraProfesStuNumDao;
import cn.edu.xmu.daoimpl.TableListDaoImpl;
import cn.edu.xmu.daoimpl.RegUndergraProfesStuNumDaoImpl;
import cn.edu.xmu.entity.RegUndergraProfesStuNum;
import cn.edu.xmu.table.RegUndergraProfesStuNumTable;
import net.sf.excelutils.ExcelException;
import net.sf.excelutils.ExcelUtils;

/**
 * 
 * @author xiaoping 数据报表6-1-2 普通本科分专业（大类）学生数（时点） date 2015-7-12
 *
 */
@WebServlet("/Sec_ExportRegUndergraProfesStuNum")
public class Sec_ExportRegUndergraProfesStuNum extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public Sec_ExportRegUndergraProfesStuNum()
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

		String college = request.getParameter(RegUndergraProfesStuNumTable.RUPSN_COLLEGE);
		college = URLDecoder.decode(college, "UTF-8");
		Date deadLine = tableListDao.getTableDate(tableid);
		Map queryParams = new HashMap();
		if (deadLine != null)
			queryParams.put(RegUndergraProfesStuNumTable.RUPSN_DEADLINE, deadLine);
		if (college != null && !"".equals(college))
		{
			if (college.contains("学院"))
			{// 具体的学院才过滤
				queryParams.put(RegUndergraProfesStuNumTable.RUPSN_COLLEGE, college);
			}
		}
		if (tablename.endsWith("表6-1-2普通本科分专业（大类）学生数（时点）"))
		{
			RegUndergraProfesStuNumDao dao = new RegUndergraProfesStuNumDaoImpl();
			List<RegUndergraProfesStuNum> tsList = dao.getRegUndergraProfesStuNums(0,
					dao.getRegUndergraProfesStuNumCount(queryParams), RegUndergraProfesStuNumTable.RUPSN_ID, "asc",
					queryParams, null);
			/*-------------- 1.准备数据--------------*/
			List<Object> rupsnResultList = new ArrayList<Object>();
			for (int i = 0; i < tsList.size(); i++)
			{
				List<Object> rupsnCountList = new ArrayList<Object>();// 行数据
				rupsnCountList.add(tsList.get(i).getRupsn_schprofescode());
				rupsnCountList.add(tsList.get(i).getRupsn_schprofesname());
				rupsnCountList.add(tsList.get(i).getRupsn_edusystem());
				rupsnCountList.add(tsList.get(i).getRupsn_atschtotal());
				rupsnCountList.add(tsList.get(i).getRupsn_gradeone());
				rupsnCountList.add(tsList.get(i).getRupsn_gradetwo());
				rupsnCountList.add(tsList.get(i).getRupsn_gradethree());
				rupsnCountList.add(tsList.get(i).getRupsn_gradefour());
				rupsnCountList.add(tsList.get(i).getRupsn_abgradefive());
				rupsnCountList.add(tsList.get(i).getRupsn_minor());
				rupsnCountList.add(tsList.get(i).getRupsn_doubledegree());
				rupsnCountList.add(tsList.get(i).getRupsn_intonumber());
				rupsnCountList.add(tsList.get(i).getRupsn_outnumber());
				rupsnResultList.add(rupsnCountList);
			}
			ExcelUtils.addValue("typename", tablename);// typename是用在表格模板里面的表名
			ExcelUtils.addValue("resultList", rupsnResultList);// resultList是用在表格模板里面用于显示数据的
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
