package cn.edu.xmu.servlet;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;






import cn.edu.xmu.dao.StartClassDao;
import cn.edu.xmu.dao.TableListDao;
import cn.edu.xmu.daoimpl.StartClassDaoImpl;
import cn.edu.xmu.daoimpl.TableListDaoImpl;
import cn.edu.xmu.entity.StartClass;
import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;

/**
 * 附表5-1-1-1开课情况表
 * @author chunfeng
 *
 */
@WebServlet("/Sec_ImportStartClassServlet")
public class Sec_ImportStartClassServlet extends HttpServlet
{

	private static final long serialVersionUID = 1L;
	private static String result;
	private static int errorrow = 1;
	private static String college;

	public Sec_ImportStartClassServlet()
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
		response.setContentType("text/html;Charset=UTF-8");
		String tableid = request.getParameter("tableid");
		college = request.getParameter("college");
		college = URLDecoder.decode(college, "utf-8");
		int recordcount = 0;
		List<StartClass> scList = new ArrayList<StartClass>();

		TableListDao tableListDao = new TableListDaoImpl();
		StartClassDao scDao = new StartClassDaoImpl();

		String tablename = tableListDao.getTablename(tableid);
		System.out.println(tablename);
		String filePath = getServletContext().getRealPath("/") + "/uploadModelTable/";
		String completeFilePath;// excel文件的完整路径
		File file = new File(filePath);
		if (!file.exists())
		{
			file.mkdir();
		}
		result = "导入成功";
		SmartUpload smartUpload = new SmartUpload();
		smartUpload.initialize(getServletConfig(), request, response);
		smartUpload.setMaxFileSize(1024 * 1024 * 10);
		smartUpload.setTotalMaxFileSize(1024 * 1024 * 100);
		smartUpload.setAllowedFilesList("txt,jpg,png,gif,doc,xlsx,xls");
		try
		{
			smartUpload.setDeniedFilesList("rar,jsp,html");
		} catch (SQLException e)
		{
			e.printStackTrace();
			result = "上传失败";
		}
		try
		{
			smartUpload.upload();
			int count = 0;
			count = smartUpload.save(filePath);
			com.jspsmart.upload.File myFile = smartUpload.getFiles().getFile(0);
			completeFilePath = filePath + "\\" + myFile.getFileName();
			System.out.println(completeFilePath);
			if (tablename.equals("附表5-1-1-1开课情况表"))
			{
				scList = getAlltsByExcel(completeFilePath);
				scDao.deleteByCollegeandDeadline(college, null);
				recordcount = scList.size();
				for (int i = 0; i < scList.size(); i++)
				{
					scDao.addStartClass(scList.get(i));
				}
			}
		} catch (SmartUploadException e)
		{
			e.printStackTrace();
			result = "上传失败";
		} catch (SQLException e)
		{
			e.printStackTrace();
			System.out.println("上传失败");
		}

		if (result.equals("导入成功"))
		{
			request.setAttribute("result", result);
			request.setAttribute("count", recordcount);
			request.getRequestDispatcher("upTest/uploadtest.jsp").forward(request, response);
		} else
		{
			request.setAttribute("result", result);
			request.setAttribute("errorrow", errorrow);
			request.getRequestDispatcher("upTest/error.jsp").forward(request, response);
		}
	}

	/**
	 * 得到Excel表格里面的数据
	 * 
	 * @param file
	 * @return
	 */
	public static List<StartClass> getAlltsByExcel(String file)
	{
		errorrow = 1;
		List<StartClass> feList = new ArrayList<StartClass>();
		try
		{
			Workbook rwb = Workbook.getWorkbook(new File(file));
			Sheet rs = rwb.getSheet(0);// 或者rwb.getSheet(0)
			int clos = rs.getColumns();// 得到所有的列
			int rows = getRightRows(rs);// 得到所有的行
			System.out.println(clos + " rows:" + rows);
			for (int i = 3; i < rows; i++)
			{
				for (int j = 0; j < 29; j++)
				{
					// 第一个是列数，第二个是行数
					String sc_number = rs.getCell(j++, i).getContents();	
					String sc_coursenum = rs.getCell(j++, i).getContents();	//课程号
					String sc_coursecategory = rs.getCell(j++, i).getContents();	//课程类别
					String sc_campus = rs.getCell(j++, i).getContents();	 //校区
					String totalhour = rs.getCell(j++, i).getContents();	 //总学时
					Integer sc_totalhour = -1;
					if(!"".equals(totalhour)) sc_totalhour = Integer.parseInt(totalhour);
					String totalcredit = rs.getCell(j++, i).getContents();	//学分
					Float sc_totalcredit = (float) -1.0;
					if(!"".equals(totalcredit)) sc_totalcredit = Float.parseFloat(totalcredit);
					String sc_evaluationmode = rs.getCell(j++, i).getContents();	 //考核方式
					String sc_teachobject = rs.getCell(j++, i).getContents();	 //授课对象
					String sc_arrange = rs.getCell(j++, i).getContents();	//安排情况
					String sc_yearandsemester = rs.getCell(j++, i).getContents();	//学年学期
					String sc_collegename = rs.getCell(j++, i).getContents();	 //授课院
					
					String sc_coursename = rs.getCell(j++, i).getContents();	 //课程名称
					String sc_teacher = rs.getCell(j++, i).getContents();	 //授课教师
					String sc_isoutsideteacher = rs.getCell(j++, i).getContents();	 //是否校外专家
					String sc_teachernumber = rs.getCell(j++, i).getContents();	 //授课教师工号
					String sc_teachertitle = rs.getCell(j++, i).getContents();	//职称
					String studentnum = rs.getCell(j++, i).getContents();	//本科学生数
					Integer sc_studentnum = -1;
					if(!"".equals(studentnum)) sc_studentnum = Integer.parseInt(studentnum);
					String sc_isenglish = rs.getCell(j++, i).getContents();	//英语授课情况
					String sc_website = rs.getCell(j++, i).getContents();	 //网络教学平台网站
					String sc_teachmaterial = rs.getCell(j++, i).getContents();	//教材使用情况
					String materialspecies = rs.getCell(j++, i).getContents();	 //使用教材种数
					Integer sc_materialspecies = -1;
					if(!"".equals(materialspecies)) sc_studentnum = Integer.parseInt(materialspecies);
					String sc_ismagong = rs.getCell(j++, i).getContents();	//是否使用马工教材
					String sc_isstandard = rs.getCell(j++, i).getContents();	//是否使用规划教材
					String sc_foreignmaterial = rs.getCell(j++, i).getContents();	 //境外教材使用情况
					String sc_m_name = rs.getCell(j++, i).getContents();	 //教材名称
					String sc_m_auther = rs.getCell(j++, i).getContents();	//作者
					String sc_m_publisher = rs.getCell(j++, i).getContents();	 //出版社
				    String sc_m_country = rs.getCell(j++, i).getContents();	 //所属国家
					String m_publishyear = rs.getCell(j++, i).getContents();	 //出版年
					Integer sc_m_publishyear = -1;
					if(!"".equals(m_publishyear)) sc_m_publishyear = Integer.parseInt(m_publishyear);
					
					int sc_isnull = 0;
					if("".equals(sc_number) || "".equals(sc_coursenum) || "".equals(sc_coursecategory) || "".equals(sc_campus) || "".equals(totalhour) || "".equals(totalcredit) || "".equals(sc_evaluationmode)
							 || "".equals(	sc_teachobject) || "".equals( sc_arrange) || "".equals( sc_yearandsemester) || "".equals( sc_collegename) || "".equals( sc_coursename) || "".equals( sc_teacher) || "".equals( sc_isoutsideteacher) 
							 || "".equals(sc_teachernumber) || "".equals(sc_teachertitle) || "".equals(sc_studentnum) || "".equals(sc_isenglish) || "".equals(sc_website) || "".equals( sc_teachmaterial) || "".equals(sc_materialspecies) 
							 || "".equals(sc_ismagong) || "".equals( sc_isstandard) || "".equals( sc_foreignmaterial) )
					{
						sc_isnull = 1;
					}
					if(!"".equals( sc_foreignmaterial)&&!"无".equals( sc_foreignmaterial)){
						if("".equals( sc_m_name) || "".equals(sc_m_auther) || "".equals(sc_m_publisher) || "".equals(sc_m_country) || "".equals( m_publishyear)){
							sc_isnull = 1;
						}
					}		
					if ("".equals(sc_number) && "".equals(sc_coursenum) && "".equals(sc_coursecategory) && "".equals(sc_campus) && "".equals(totalhour) && "".equals(totalcredit) && "".equals(sc_evaluationmode)
							 && "".equals(	sc_teachobject) && "".equals( sc_arrange) && "".equals( sc_yearandsemester) && "".equals( sc_collegename) && "".equals( sc_coursename) && "".equals( sc_teacher) && "".equals( sc_isoutsideteacher) 
							 && "".equals(sc_teachernumber) && "".equals(sc_teachertitle) && "".equals(sc_studentnum) && "".equals(sc_isenglish) && "".equals(sc_website) && "".equals( sc_teachmaterial) && "".equals(sc_materialspecies) 
							 && "".equals(sc_ismagong) && "".equals( sc_isstandard) && "".equals( sc_foreignmaterial) && "".equals( sc_m_name) && "".equals(sc_m_auther) && "".equals(sc_m_publisher) && "".equals(sc_m_country) && "".equals( sc_m_publishyear))
					{
						break;
					}
					
					
				StartClass startClass = new StartClass(
						sc_number, sc_coursenum, sc_coursecategory, sc_campus, sc_totalhour, sc_totalcredit, sc_evaluationmode,
						sc_teachobject, sc_arrange, sc_yearandsemester, sc_collegename, sc_coursename, sc_teacher, sc_isoutsideteacher, 
						sc_teachernumber, sc_teachertitle, sc_studentnum, sc_isenglish, sc_website, sc_teachmaterial, sc_materialspecies, 
						sc_ismagong, sc_isstandard, sc_foreignmaterial, sc_m_name, sc_m_auther, sc_m_publisher, sc_m_country, sc_m_publishyear, 					
						college,1, sc_isnull);

				feList.add(startClass);
					
					
					
					}
				errorrow++;
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			result = "导入失败";
		}
		return feList;
	}

	// 返回去掉空行的记录数
	private static int getRightRows(Sheet sheet)
	{
		int rsCols = sheet.getColumns(); // 列数
		int rsRows = sheet.getRows(); // 行数
		int nullCellNum;
		int afterRows = rsRows;
		for (int i = 1; i < rsRows; i++)
		{ // 统计行中为空的单元格数
			nullCellNum = 0;
			for (int j = 0; j < rsCols; j++)
			{
				String val = sheet.getCell(j, i).getContents();
				val = StringUtils.trimToEmpty(val);
				if (StringUtils.isBlank(val))
					nullCellNum++;
			}
			if (nullCellNum >= rsCols)
			{ // 如果nullCellNum大于或等于总的列数
				afterRows--; // 行数减一
			}
		}
		return afterRows;
	}

	/**
	 * 将excel日期型单元格转成sql.Date
	 * 
	 * @param cell
	 *            excel单元格
	 * @return
	 */
	private static Date dateCellToSql(Cell cell)
	{
		Date date = Date.valueOf("1800-1-1");
		if (cell.getType() == CellType.DATE)
		{
			DateCell dc = (DateCell) (cell);
			date = new Date(dc.getDate().getTime());
		} else
		{
			try
			{
				String[] temp = cell.getContents().split("-");
				if (temp.length == 2)
				{
					date = Date.valueOf(temp[0] + "-" + temp[1] + "-01");
				} else if (temp.length == 3)
				{
					date = Date.valueOf(temp[0] + "-" + temp[1] + "-" + temp[2]);
				}
			} catch (Exception e)
			{
				date = Date.valueOf("1800-1-1");
			}
		}
		System.out.println("date" + date);
		return date;
	}
}
