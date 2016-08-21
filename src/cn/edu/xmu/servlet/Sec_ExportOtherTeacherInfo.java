
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

import cn.edu.xmu.dao.OtherTeacherInfoDao;
import cn.edu.xmu.dao.TableListDao;
import cn.edu.xmu.daoimpl.OtherTeacherInfoDaoImpl;
import cn.edu.xmu.daoimpl.TableListDaoImpl;
import cn.edu.xmu.entity.OtherTeacherInfo;
import cn.edu.xmu.table.OtherTeacherInfoTable;
import net.sf.excelutils.ExcelException;
import net.sf.excelutils.ExcelUtils;

@WebServlet("/Sec_ExportOtherTeacherInfo")
public class Sec_ExportOtherTeacherInfo extends HttpServlet
{

	private static final long serialVersionUID = 1L;

	public Sec_ExportOtherTeacherInfo()
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
		// 根据tableid得到tablename
		Date deadLine = tableListDao.getTableDate(tableid);// tableList中应当注意发布时清空，关闭时赋值
		System.out.println("deadline:" + deadLine);
		String college = request.getParameter("oti_college");
		college = URLDecoder.decode(college, "UTF-8");
		Map queryParams = new HashMap();
		if (deadLine != null)
			queryParams.put(OtherTeacherInfoTable.OTI_DEADLINE, deadLine);
		if (college != null && !"".equals(college))
		{
			if (college.contains("学院"))
			{// 具体的学院才过滤
				queryParams.put(OtherTeacherInfoTable.OTI_COLLEGE, college);
			}
		}
		if (tablename.endsWith("表3-1-3其他师资信息"))
		{
			OtherTeacherInfoDao otid = new OtherTeacherInfoDaoImpl();
			List<OtherTeacherInfo> otiList = otid.getAllOtherTeacherInfo(0, otid.getOtherTeacherInfoCount(queryParams),
					OtherTeacherInfoTable.OTI_ID, "asc", queryParams);

			List<Object> otiResultList = new ArrayList<Object>();
			for (int i = 0; i < otiList.size(); i++)
			{
				List<Object> otiCountList = new ArrayList<Object>();
				OtherTeacherInfo oti = otiList.get(i);
				otiCountList.add(oti.getOti_name());
				otiCountList.add(oti.getOti_worknumber());
				otiCountList.add(oti.getOti_sex());
				otiCountList.add(oti.getOti_birthday());
				otiCountList.add(oti.getOti_inschooldate());
				otiCountList.add(oti.getOti_workstate());
				otiCountList.add(oti.getOti_teachertype());
				otiCountList.add(oti.getOti_departmentnumber());
				otiCountList.add(oti.getOti_departmentname());
				otiCountList.add(oti.getOti_education());
				otiCountList.add(oti.getOti_degree());
				otiCountList.add(oti.getOti_professionaltitle());
				otiCountList.add(oti.getOti_subjectcategory());
				otiCountList.add(oti.getOti_tutorcategory());

				otiResultList.add(otiCountList);
			}

			ExcelUtils.addValue("typename", tablename);// typename是用在表格模板里面的表名
			ExcelUtils.addValue("resultList", otiResultList);// resultList是用在表格模板里面用于显示数据的
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
