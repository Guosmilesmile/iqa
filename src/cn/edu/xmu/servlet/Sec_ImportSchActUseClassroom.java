package cn.edu.xmu.servlet;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
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

import cn.edu.xmu.dao.TableListDao;
import cn.edu.xmu.dao.SchActUseClassroomDao;
import cn.edu.xmu.daoimpl.TableListDaoImpl;
import cn.edu.xmu.daoimpl.SchActUseClassroomDaoImpl;
import cn.edu.xmu.entity.SchActUseClassroom;
import jxl.Sheet;
import jxl.Workbook;

/**
 * 
 * @author xiaoping 数据报表2-3-1全校性实际使用的教室（时点） date 2015-7-12
 *
 */
@WebServlet("/Sec_ImportSchActUseClassroom")
public class Sec_ImportSchActUseClassroom extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private static String result;
	private static int errorrow = 1;
	private static String college;
	private int subtotalRoom = 0, multiRoom = 0, regularRoom = 0, computerRoom = 0, voiceRoom = 0, otherRoom = 0;
	private int subtotalSeat = 0, multiSeat = 0, regularSeat = 0, computerSeat = 0, voiceSeat = 0, otherSeat = 0;

	public Sec_ImportSchActUseClassroom()
	{
		super();
	}

	private void iniRoomSeat()
	{

		subtotalRoom = 0;
		multiRoom = 0;
		regularRoom = 0;
		computerRoom = 0;
		voiceRoom = 0;
		otherRoom = 0;
		subtotalSeat = 0;
		multiSeat = 0;
		regularSeat = 0;
		computerSeat = 0;
		voiceSeat = 0;
		otherSeat = 0;
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
		List<SchActUseClassroom> saucList = new ArrayList<SchActUseClassroom>();

		TableListDao tableListDao = new TableListDaoImpl();
		SchActUseClassroomDao saucDao = new SchActUseClassroomDaoImpl();

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
			if (tablename.equals("附表2-3-1全校性实际使用的教室（时点）"))
			{
				saucList = getAlltsByExcel(completeFilePath);
				saucDao.deleteByCollegeandDeadline(college, null);
				recordcount = saucList.size();
				for (int i = 0; i < saucList.size(); i++)
				{
					saucDao.addSchActUseClassroom(saucList.get(i));
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
	public List<SchActUseClassroom> getAlltsByExcel(String file)
	{
		errorrow = 1;
		List<SchActUseClassroom> saucList = new ArrayList<SchActUseClassroom>();
		try
		{
			Workbook rwb = Workbook.getWorkbook(new File(file));
			Sheet rs = rwb.getSheet(0);// 或者rwb.getSheet(0)
			int clos = rs.getColumns();// 得到所有的列
			int rows = getRightRows(rs);// 得到所有的行
			System.out.println(clos + " rows:" + rows);
			SchActUseClassroom total = new SchActUseClassroom(0, "合计", "", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, null,
					college, "", 0);// 总计
			saucList.add(total);
			errorrow++;
			String region = "";
			for (int i = 3; i < rows; i++)
			{
				for (int j = 0; j < clos; j++)
				{
					// 第一个是列数，第二个是行数
					String sauc_region = rs.getCell(j++, i).getContents();// 默认最左边编号也算一列,所以这里得j++
					if(sauc_region.equals("合计"))
						break;
					String sauc_site = rs.getCell(j++, i).getContents();
					j++;// 小计要另外算
					j++;
					if (sauc_site.equals("小计") || (!"".equals(region) && !region.equals(sauc_region)))
					{
						saucList.add(new SchActUseClassroom(0, region, "小计", subtotalRoom, subtotalSeat, multiRoom,
								multiSeat, regularRoom, regularSeat, computerRoom, computerSeat, voiceRoom, voiceSeat,
								otherRoom, otherSeat, 1, null, college, "", 0));
						errorrow++;
						total.setSauc_subtotalroom(total.getSauc_subtotalroom() + subtotalRoom);
						total.setSauc_multiroom(total.getSauc_multiroom() + multiRoom);
						total.setSauc_regularroom(total.getSauc_regularroom() + regularRoom);
						total.setSauc_computerroom(total.getSauc_computerroom() + computerRoom);
						total.setSauc_voiceroom(total.getSauc_voiceroom() + voiceRoom);
						total.setSauc_otherroom(total.getSauc_otherroom() + otherRoom);
						total.setSauc_subtotalseat(total.getSauc_subtotalseat() + subtotalSeat);
						total.setSauc_multiseat(total.getSauc_multiseat() + multiSeat);
						total.setSauc_regularseat(total.getSauc_regularseat() + regularSeat);
						total.setSauc_computerseat(total.getSauc_computerseat() + computerSeat);
						total.setSauc_voiceseat(total.getSauc_voiceseat() + voiceSeat);
						total.setSauc_otherseat(total.getSauc_otherseat() + otherSeat);
						iniRoomSeat();
						if (sauc_site.equals("小计"))
						{
							region = "";
							break;
						}
					}
					region = sauc_region;
					String temp_multiroom = rs.getCell(j++, i).getContents();
					String temp_multiseat = rs.getCell(j++, i).getContents();
					String temp_regularroom = rs.getCell(j++, i).getContents();
					String temp_regularseat = rs.getCell(j++, i).getContents();
					String temp_computerroom = rs.getCell(j++, i).getContents();
					String temp_computerseat = rs.getCell(j++, i).getContents();
					String temp_voiceroom = rs.getCell(j++, i).getContents();
					String temp_voiceseat = rs.getCell(j++, i).getContents();
					String temp_otherroom = rs.getCell(j++, i).getContents();
					String temp_otherseat = rs.getCell(j++, i).getContents();
					int sauc_multiroom = -9;
					int sauc_multiseat = -9;
					int sauc_regularroom = -9;
					int sauc_regularseat = -9;
					int sauc_computerroom = -9;
					int sauc_computerseat = -9;
					int sauc_voiceroom = -9;
					int sauc_voiceseat = -9;
					int sauc_otherroom = -9;
					int sauc_otherseat = -9;
					int sauc_subtotalroom = 0;
					int sauc_subtotalseat = 0;
					int sauc_isnull = 0;
					if ("".equals(sauc_region) || "".equals(sauc_site) || "".equals(temp_multiroom) || "".equals(temp_multiseat)
							|| "".equals(temp_regularroom) || "".equals(temp_regularseat) || "".equals(temp_computerroom)
							|| "".equals(temp_computerseat) || "".equals(temp_voiceroom) || "".equals(temp_voiceseat)
							|| "".equals(temp_otherroom) || "".equals(temp_otherseat))
						sauc_isnull = 1;
					if ("".equals(sauc_region) && "".equals(sauc_site) && "".equals(temp_multiroom) && "".equals(temp_multiseat)
							&& "".equals(temp_regularroom) && "".equals(temp_regularseat) && "".equals(temp_computerroom)
							&& "".equals(temp_computerseat) && "".equals(temp_voiceroom) && "".equals(temp_voiceseat) && "".equals(temp_otherroom)
							&& "".equals(temp_otherseat))
						break;
					if (!"".equals(temp_multiroom))
					{
						sauc_multiroom = Integer.parseInt(temp_multiroom);
						sauc_subtotalroom += sauc_multiroom;
					}
					if (!"".equals(temp_multiseat))
					{
						sauc_multiseat = Integer.parseInt(temp_multiseat);
						sauc_subtotalseat += sauc_multiseat;
					}
					if (!"".equals(temp_regularroom))
					{
						sauc_regularroom = Integer.parseInt(temp_regularroom);
						sauc_subtotalroom += sauc_regularroom;
					}
					if (!"".equals(temp_regularseat))
					{
						sauc_regularseat = Integer.parseInt(temp_regularseat);
						sauc_subtotalseat += sauc_regularseat;
					}
					if (!"".equals(temp_computerroom))
					{
						sauc_computerroom = Integer.parseInt(temp_computerroom);
						sauc_subtotalroom += sauc_computerroom;
					}
					if (!"".equals(temp_computerseat))
					{
						sauc_computerseat = Integer.parseInt(temp_computerseat);
						sauc_subtotalseat += sauc_computerseat;
					}
					if (!"".equals(temp_voiceroom))
					{
						sauc_voiceroom = Integer.parseInt(temp_voiceroom);
						sauc_subtotalroom += sauc_voiceroom;
					}
					if (!"".equals(temp_voiceseat))
					{
						sauc_voiceseat = Integer.parseInt(temp_voiceseat);
						sauc_subtotalseat += sauc_voiceseat;
					}
					if (!"".equals(temp_otherroom))
					{
						sauc_otherroom = Integer.parseInt(temp_otherroom);
						sauc_subtotalroom += sauc_otherroom;
					}
					if (!"".equals(temp_otherseat))
					{
						sauc_otherseat = Integer.parseInt(temp_otherseat);
						sauc_subtotalseat += sauc_otherseat;
					}
					subtotalRoom += sauc_subtotalroom;
					multiRoom += (sauc_multiroom == -9 ? 0 : sauc_multiroom);
					regularRoom += (sauc_regularroom == -9 ? 0 : sauc_regularroom);
					computerRoom += (sauc_computerroom == -9 ? 0 : sauc_computerroom);
					voiceRoom += (sauc_voiceroom == -9 ? 0 : sauc_voiceroom);
					otherRoom += (sauc_otherroom == -9 ? 0 : sauc_otherroom);
					subtotalSeat += sauc_subtotalseat;
					multiSeat += (sauc_multiseat == -9 ? 0 : sauc_multiseat);
					regularSeat += (sauc_regularseat == -9 ? 0 : sauc_regularseat);
					computerSeat += (sauc_computerseat == -9 ? 0 : sauc_computerseat);
					voiceSeat += (sauc_voiceseat == -9 ? 0 : sauc_voiceseat);
					otherSeat += (sauc_otherseat == -9 ? 0 : sauc_otherseat);
					saucList.add(new SchActUseClassroom(0, sauc_region, sauc_site, sauc_subtotalroom, sauc_subtotalseat,
							sauc_multiroom, sauc_multiseat, sauc_regularroom, sauc_regularseat, sauc_computerroom,
							sauc_computerseat, sauc_voiceroom, sauc_voiceseat, sauc_otherroom, sauc_otherseat, 1, null,
							college, "", sauc_isnull));
				}
				errorrow++;
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			result = "导入失败";
		}
		return saucList;
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
}
