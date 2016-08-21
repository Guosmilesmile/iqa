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
import cn.edu.xmu.dao.TeachingAwardDao;
import cn.edu.xmu.daoimpl.TableListDaoImpl;
import cn.edu.xmu.daoimpl.TeachingAwardDaoImpl;
import cn.edu.xmu.entity.TeachingAward;
import cn.edu.xmu.table.TeachingAwardTable;
import net.sf.excelutils.ExcelException;
import net.sf.excelutils.ExcelUtils;

/**
 * 表7-3-2教学成果奖（时点）
 * @author chunfeng
 * 
 */
@WebServlet("/Sec_ExportTeachingAwardServlet")
public class Sec_ExportTeachingAwardServlet extends HttpServlet
{

	private static final long serialVersionUID = 1L;

	public Sec_ExportTeachingAwardServlet()
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
			//queryParams.put(TeachingAwardTable.TA_DEADLINE, deadLine);
		if (college != null && !"".equals(college))
		{
			if (college.contains("学院"))
			{// 具体的学院才过滤
				queryParams.put(TeachingAwardTable.TA_COLLEGE, college);
			}
		}
		if (tablename.endsWith("表7-3-2教学成果奖（时点）"))
		{
			TeachingAwardDao dao = new TeachingAwardDaoImpl();
			List<TeachingAward> feList = dao.getTeachingAward(0, dao.getTeachingAwardCount(queryParams),
					TeachingAwardTable.TA_ID, "asc", queryParams,deadLine);
			/*-------------- 1.准备数据--------------*/
			List<Object> feResultList = new ArrayList<Object>();
			for (int i = 0; i < feList.size(); i++)
			{
				List<Object> feCountList = new ArrayList<Object>();// 行数据
				feCountList.add(feList.get(i).getTa_projectname());
				feCountList.add(feList.get(i).getTa_compere());
				feCountList.add(feList.get(i).getTa_comperenumber());
				feCountList.add(feList.get(i).getTa_level());
				feCountList.add(feList.get(i).getTa_windate());
				feCountList.add(feList.get(i).getTa_grantunit());
				
				feResultList.add(feCountList);
			}
			ExcelUtils.addValue("typename", tablename);// typename是用在表格模板里面的表名
			ExcelUtils.addValue("resultList", feResultList);// resultList是用在表格模板里面用于显示数据的
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
