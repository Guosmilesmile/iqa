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

import cn.edu.xmu.dao.CombinedTrainAndOtherTeachLinkDao;
import cn.edu.xmu.dao.TableListDao;
import cn.edu.xmu.daoimpl.CombinedTrainAndOtherTeachLinkDaoImpl;
import cn.edu.xmu.daoimpl.TableListDaoImpl;
import cn.edu.xmu.entity.CombinedTrainAndOtherTeachLink;
import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;

/**
 * 附表5-3-4分专业毕业综合训练、其他教学环节安排情况（学年）
 * @author chunfeng
 *
 */
@WebServlet("/Sec_ImportCombinedTrainAndOtherTeachLinkServlet")
public class Sec_ImportCombinedTrainAndOtherTeachLinkServlet extends HttpServlet
{

	private static final long serialVersionUID = 1L;
	private static String result;
	private static int errorrow = 1;
	private static String college;

	public Sec_ImportCombinedTrainAndOtherTeachLinkServlet()
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
		List<CombinedTrainAndOtherTeachLink> feList = new ArrayList<CombinedTrainAndOtherTeachLink>();

		TableListDao tableListDao = new TableListDaoImpl();
		CombinedTrainAndOtherTeachLinkDao feDao = new CombinedTrainAndOtherTeachLinkDaoImpl();

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
			if (tablename.equals("附表5-3-4分专业毕业综合训练、其他教学环节安排情况（学年）"))
			{
				feList = getAlltsByExcel(completeFilePath);
				feDao.deleteByCollegeandDeadline(college, null);
				recordcount = feList.size();
				for (int i = 0; i < feList.size(); i++)
				{
					feDao.addCombinedTrainAndOtherTeachLink(feList.get(i));
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
	public static List<CombinedTrainAndOtherTeachLink> getAlltsByExcel(String file)
	{
		errorrow = 1;
		List<CombinedTrainAndOtherTeachLink> feList = new ArrayList<CombinedTrainAndOtherTeachLink>();
		try
		{
			Workbook rwb = Workbook.getWorkbook(new File(file));
			Sheet rs = rwb.getSheet(0);// 或者rwb.getSheet(0)
			int clos = rs.getColumns();// 得到所有的列
			int rows = getRightRows(rs);// 得到所有的行
			System.out.println(clos + " rows:" + rows);
			for (int i = 4; i < rows; i++)
			{
				for (int j = 0; j < clos; j++)
				{
					// 第一个是列数，第二个是行数
					String ctaotl_collegename = rs.getCell(j++, i).getContents();  //学院
					
				    String ctaotl_major = rs.getCell(j++, i).getContents(); //专业
				    String ctaotl_majornumber = rs.getCell(j++, i).getContents();  //专业代码
				    
				    String graduatestudent = rs.getCell(j++, i).getContents(); //毕业班人数
				    Integer ctaotl_graduatestudent = -1;
				    if(!"".equals(graduatestudent)) ctaotl_graduatestudent = Integer.parseInt(graduatestudent);
				    String ct_t_total = rs.getCell(j++, i).getContents(); //分专业毕业综合训练/指导毕业综合训练教师数/总数
				    Integer ctaotl_ct_t_total = -1;
				    if(!"".equals(ct_t_total)) ctaotl_ct_t_total = Integer.parseInt(ct_t_total);
				    
				    String ct_t_fullteacher = rs.getCell(j++, i).getContents();//分专业毕业综合训练/指导毕业综合训练教师数/专任教师
				    Integer ctaotl_ct_t_fullteacher = -1;
				    if(!"".equals(ct_t_fullteacher)) ctaotl_ct_t_fullteacher = Integer.parseInt(ct_t_fullteacher);
				    String ct_t_partteacher = rs.getCell(j++, i).getContents(); //分专业毕业综合训练/指导毕业综合训练教师数/外聘教师
				    Integer ctaotl_ct_t_partteacher = -1;
				    if(!"".equals(ct_t_partteacher)) ctaotl_ct_t_partteacher = Integer.parseInt(ct_t_partteacher);
				    
				    String ct_t_senior = rs.getCell(j++, i).getContents();//分专业毕业综合训练/指导毕业综合训练教师数/正高级
				    Integer ctaotl_ct_t_senior = -1;
				    if(!"".equals(ct_t_senior)) ctaotl_ct_t_senior = Integer.parseInt(ct_t_senior);
				    String ct_t_subsenior = rs.getCell(j++, i).getContents(); //分专业毕业综合训练/指导毕业综合训练教师数/副高级
				    Integer ctaotl_ct_t_subsenior = -1;
				    if(!"".equals(ct_t_subsenior)) ctaotl_ct_t_subsenior = Integer.parseInt(ct_t_subsenior);
				    
				    String ct_p_total = rs.getCell(j++, i).getContents();//分专业毕业综合训练/毕业综合训练课题/总数
				    Integer ctaotl_ct_p_total = -1;
				    if(!"".equals(ct_p_total)) ctaotl_ct_p_total = Integer.parseInt(ct_p_total);
				    String ct_p_socialfinish = rs.getCell(j++, i).getContents();//分专业毕业综合训练/毕业综合训练课题/在实验、实习、工程实践和社会调查等社会实践中完成数
				    Integer ctaotl_ct_p_socialfinish = -1;
				    if(!"".equals(ct_p_socialfinish)) ctaotl_ct_p_socialfinish = Integer.parseInt(ct_p_socialfinish);
				    
				    String ct_p_fromscience = rs.getCell(j++, i).getContents(); //分专业毕业综合训练/毕业综合训练课题/来自教师科研课题
				    Integer ctaotl_ct_p_fromscience = -1;
				    if(!"".equals(ct_p_fromscience)) ctaotl_ct_t_subsenior = Integer.parseInt(ct_p_fromscience);
				    String ct_p_fromproduce = rs.getCell(j++, i).getContents(); //分专业毕业综合训练/毕业综合训练课题/来自生产管理一线
				    Integer ctaotl_ct_p_fromproduce = -1;
				    if(!"".equals(ct_p_fromproduce)) ctaotl_ct_p_fromproduce = Integer.parseInt(ct_p_fromproduce);
				    
				    String ct_p_fromsocial = rs.getCell(j++, i).getContents(); //分专业毕业综合训练/毕业综合训练课题/来自社会实践
				    Integer ctaotl_ct_p_fromsocial = -1;
				    if(!"".equals(ct_p_fromsocial)) ctaotl_ct_p_fromsocial = Integer.parseInt(ct_p_fromsocial);
				    String ct_p_other = rs.getCell(j++, i).getContents(); //分专业毕业综合训练/毕业综合训练课题/其他
				    Integer ctaotl_ct_p_other = -1;
				    if(!"".equals(ct_p_other)) ctaotl_ct_p_other = Integer.parseInt(ct_p_other);
				    
				    String ot_pro_credit = rs.getCell(j++, i).getContents(); //其他教学环节/见习或认识实习/学分
				    Float ctaotl_ot_pro_credit = (float) -1.0;
				    if(!"".equals(ot_pro_credit)) ctaotl_ot_pro_credit = Float.parseFloat(ot_pro_credit);
				    String ot_pro_days = rs.getCell(j++, i).getContents(); //其他教学环节/见习或认识实习/天数
				    Integer ctaotl_ot_pro_days = -1;
				    if(!"".equals(ot_pro_days)) ctaotl_ot_pro_days = Integer.parseInt(ot_pro_days);
				    
				    String ot_pra_credit = rs.getCell(j++, i).getContents();//其他教学环节/毕业实习/学分
				    Float ctaotl_ot_pra_credit = (float) -1.0;
				    if(!"".equals(ot_pra_credit)) ctaotl_ot_pra_credit = Float.parseFloat(ot_pra_credit);
				    String ot_pra_days = rs.getCell(j++, i).getContents(); //其他教学环节/毕业实习/天数
				    Integer ctaotl_ot_pra_days = -1;
				    if(!"".equals(ot_pra_days)) ctaotl_ot_pra_days = Integer.parseInt(ot_pra_days);
				    
				    String ot_ter_credit = rs.getCell(j++, i).getContents(); //其他教学环节/学年论文/学分 
				    Float ctaotl_ot_ter_credit = (float) -1.0;
				    if(!"".equals(ot_ter_credit)) ctaotl_ot_ter_credit = Float.parseFloat(ot_ter_credit);
				    String ot_ter_days = rs.getCell(j++, i).getContents(); //其他教学环节/学年论文/天数
				    Integer ctaotl_ot_ter_days = -1;
				    if(!"".equals(ot_ter_days)) ctaotl_ot_ter_days = Integer.parseInt(ot_ter_days);
				    
				    String ot_the_credit = rs.getCell(j++, i).getContents(); //其他教学环节/毕业论文/学分
				    Float ctaotl_ot_the_credit = (float) -1.0;
				    if(!"".equals(ot_the_credit)) ctaotl_ot_the_credit = Float.parseFloat(ot_the_credit);
				    String ot_the_days = rs.getCell(j++, i).getContents(); //其他教学环节/毕业论文/天数
				    Integer ctaotl_ot_the_days = -1;
				    if(!"".equals(ot_the_days)) ctaotl_ot_the_days = Integer.parseInt(ot_the_days);
				    
				    String ot_oth_credit = rs.getCell(j++, i).getContents();//其他教学环节/其他/学分
				    Float ctaotl_ot_oth_credit = (float) -1.0;
				    if(!"".equals(ot_oth_credit)) ctaotl_ot_oth_credit = Float.parseFloat(ot_oth_credit);
				    String ot_oth_days = rs.getCell(j++, i).getContents();//其他教学环节/其他/天数
				    Integer ctaotl_ot_oth_days = -1;
				    if(!"".equals(ot_oth_days)) ctaotl_ot_oth_days = Integer.parseInt(ot_oth_days);
				    
				    String ot_tot_credit = rs.getCell(j++, i).getContents(); //其他教学环节/合计/学分
				    Float ctaotl_ot_tot_credit = (float) -1.0;
				    if(!"".equals(ot_tot_credit)) ctaotl_ot_tot_credit = Float.parseFloat(ot_tot_credit);
				    String ot_tot_days = rs.getCell(j++, i).getContents();  //其他教学环节/合计/天数
				    Integer ctaotl_ot_tot_days = -1;
				    if(!"".equals(ot_tot_days)) ctaotl_ot_tot_days = Integer.parseInt(ot_tot_days);
				    
				    int ctaotl_isnull = 0;
					if("".equals(ctaotl_collegename) || "".equals(ctaotl_major) || "".equals(ctaotl_majornumber)
							|| "".equals(ctaotl_graduatestudent) || "".equals(ctaotl_ct_t_total) || "".equals(ctaotl_ct_t_fullteacher) || "".equals(ctaotl_ct_t_partteacher)
							|| "".equals(ctaotl_ct_t_senior) || "".equals(ctaotl_ct_t_subsenior) || "".equals(ctaotl_ct_p_total) || "".equals(ctaotl_ct_p_socialfinish) || "".equals(ctaotl_ct_p_fromscience)
							|| "".equals(ctaotl_ct_p_fromproduce) || "".equals(ctaotl_ct_p_fromsocial) || "".equals( ctaotl_ct_p_other) || "".equals(ctaotl_ot_pro_credit) || "".equals( ctaotl_ot_pro_days)
							|| "".equals(ctaotl_ot_pra_credit) || "".equals(ctaotl_ot_pra_days) || "".equals(ctaotl_ot_ter_credit) || "".equals(ctaotl_ot_ter_days) || "".equals(ctaotl_ot_the_credit)
							|| "".equals(ctaotl_ot_the_days) || "".equals(ctaotl_ot_oth_credit) || "".equals(ctaotl_ot_oth_days) || "".equals(ctaotl_ot_tot_credit) || "".equals(ctaotl_ot_tot_days))
					{
						ctaotl_isnull = 1;
					}
					if ("".equals(ctaotl_collegename) && "".equals(ctaotl_major) && "".equals(ctaotl_majornumber)
							&& "".equals(ctaotl_graduatestudent) && "".equals(ctaotl_ct_t_total) && "".equals(ctaotl_ct_t_fullteacher) && "".equals(ctaotl_ct_t_partteacher)
							&& "".equals(ctaotl_ct_t_senior) && "".equals(ctaotl_ct_t_subsenior) && "".equals(ctaotl_ct_p_total) && "".equals(ctaotl_ct_p_socialfinish) && "".equals(ctaotl_ct_p_fromscience)
							&& "".equals(ctaotl_ct_p_fromproduce) && "".equals(ctaotl_ct_p_fromsocial) && "".equals( ctaotl_ct_p_other) && "".equals(ctaotl_ot_pro_credit) && "".equals( ctaotl_ot_pro_days)
							&& "".equals(ctaotl_ot_pra_credit) && "".equals(ctaotl_ot_pra_days) && "".equals(ctaotl_ot_ter_credit) && "".equals(ctaotl_ot_ter_days) && "".equals(ctaotl_ot_the_credit)
							&& "".equals(ctaotl_ot_the_days) && "".equals(ctaotl_ot_oth_credit) && "".equals(ctaotl_ot_oth_days) && "".equals(ctaotl_ot_tot_credit) && "".equals(ctaotl_ot_tot_days))
					{
						break;
					}	  	
					
					CombinedTrainAndOtherTeachLink combinedTrainAndOtherTeachLink = new CombinedTrainAndOtherTeachLink(
							ctaotl_collegename,ctaotl_major,ctaotl_majornumber,
				            ctaotl_graduatestudent,ctaotl_ct_t_total,ctaotl_ct_t_fullteacher,ctaotl_ct_t_partteacher,
				            ctaotl_ct_t_senior,ctaotl_ct_t_subsenior,ctaotl_ct_p_total,ctaotl_ct_p_socialfinish,ctaotl_ct_p_fromscience,
				            ctaotl_ct_p_fromproduce,ctaotl_ct_p_fromsocial, ctaotl_ct_p_other, ctaotl_ot_pro_credit, ctaotl_ot_pro_days,
				            ctaotl_ot_pra_credit, ctaotl_ot_pra_days, ctaotl_ot_ter_credit, ctaotl_ot_ter_days, ctaotl_ot_the_credit,
				            ctaotl_ot_the_days, ctaotl_ot_oth_credit, ctaotl_ot_oth_days, ctaotl_ot_tot_credit, ctaotl_ot_tot_days,
				            college, 1, ctaotl_isnull);

					feList.add(combinedTrainAndOtherTeachLink);
					
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
