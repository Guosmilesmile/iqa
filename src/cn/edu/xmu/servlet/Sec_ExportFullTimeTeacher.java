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
import cn.edu.xmu.dao.UserDao;
import cn.edu.xmu.dao.UserRoleDao;
import cn.edu.xmu.dao.FullTimeTeacherInfoDao;
import cn.edu.xmu.daoimpl.TableListDaoImpl;
import cn.edu.xmu.daoimpl.UserDaoImpl;
import cn.edu.xmu.daoimpl.UserRoleDaoImpl;
import cn.edu.xmu.daoimpl.FullTimeTeacherInfoDaoImpl;
import cn.edu.xmu.entity.FullTimeTeacherInfo;
import cn.edu.xmu.table.FullTimeTeacherInfoTable;
import net.sf.excelutils.ExcelException;
import net.sf.excelutils.ExcelUtils;

/**
 * 
 * @author xiaoping 数据报表3-1-1 专任教师基本信息表 date 2015-7-11
 *
 */
@WebServlet("/Sec_ExportFullTimeTeacher")
public class Sec_ExportFullTimeTeacher extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public Sec_ExportFullTimeTeacher()
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
		Date deadLine = tableListDao.getTableDate(tableid);// tableList中应当注意发布时清空，关闭时赋值
		System.out.println("deadline:" + deadLine);
		String college = request.getParameter("ftti_college");
		college = URLDecoder.decode(college, "UTF-8");
		Map queryParams = new HashMap();
		if (deadLine != null)
			queryParams.put(FullTimeTeacherInfoTable.FTTI_DEADLINE, deadLine);
		if (college != null && !"".equals(college))
		{
			if (college.contains("学院"))
			{// 具体的学院才过滤
				queryParams.put(FullTimeTeacherInfoTable.FTTI_COLLEGE, college);
			}
		}
		if (tablename.endsWith("表3-1-1专任教师基本信息"))
		{
			FullTimeTeacherInfoDao dao = new FullTimeTeacherInfoDaoImpl();
			List<FullTimeTeacherInfo> tsList = dao.getFullTimeTeachers(0, dao.getFullTimeTeacherCount(queryParams),
					FullTimeTeacherInfoTable.FTTI_ID, "asc", queryParams, null);
			/*-------------- 1.准备数据--------------*/
			List<Object> fttiResultList = new ArrayList<Object>();
			for (int i = 0; i < tsList.size(); i++)
			{
				List<Object> fttiCountList = new ArrayList<Object>();// 行数据
				fttiCountList.add(tsList.get(i).getFtti_name());
				fttiCountList.add(tsList.get(i).getFtti_worknumber());
				fttiCountList.add(tsList.get(i).getFtti_gender());
				fttiCountList.add(tsList.get(i).getFtti_birthday());
				fttiCountList.add(tsList.get(i).getFtti_inschooldate());
				fttiCountList.add(tsList.get(i).getFtti_workstate());
				fttiCountList.add(tsList.get(i).getFtti_departmentnumber());
				fttiCountList.add(tsList.get(i).getFtti_departmentname());
				fttiCountList.add(tsList.get(i).getFtti_education());
				fttiCountList.add(tsList.get(i).getFtti_degree());
				fttiCountList.add(tsList.get(i).getFtti_educationsource());
				fttiCountList.add(tsList.get(i).getFtti_professionaltitle());
				fttiCountList.add(tsList.get(i).getFtti_subjectcategory());
				fttiCountList.add(tsList.get(i).getFtti_ifdoublequalifiedteacher());
				fttiCountList.add(tsList.get(i).getFtti_ifengineeringbackground());
				fttiCountList.add(tsList.get(i).getFtti_ifindustrybackground());
				fttiCountList.add(tsList.get(i).getFtti_tutortype());
				fttiResultList.add(fttiCountList);
			}
			ExcelUtils.addValue("typename", tablename);// typename是用在表格模板里面的表名
			ExcelUtils.addValue("resultList", fttiResultList);// resultList是用在表格模板里面用于显示数据的
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
