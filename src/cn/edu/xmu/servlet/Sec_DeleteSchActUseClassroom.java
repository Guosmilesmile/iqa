package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.xmu.dao.SchActUseClassroomDao;
import cn.edu.xmu.daoimpl.SchActUseClassroomDaoImpl;
import cn.edu.xmu.entity.SchActUseClassroom;
import cn.edu.xmu.table.SchActUseClassroomTable;

/**
 * 
 * @author xiaoping 数据报表2-3-1全校性实际使用的教室（时点） date 2015-7-6
 *
 */
@WebServlet("/Sec_DeleteSchActUseClassroom")
public class Sec_DeleteSchActUseClassroom extends HttpServlet
{

	private static final long serialVersionUID = 1L;

	public Sec_DeleteSchActUseClassroom()
	{
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;Charset=UTF-8");
		PrintWriter out = response.getWriter();
		String sauc_id = request.getParameter("saucids");

		String saucid[] = sauc_id.split(",");

		SchActUseClassroomDao schActUseClassroomDao = new SchActUseClassroomDaoImpl();
		boolean result = false;
		try
		{
			result = schActUseClassroomDao.batchDelete(saucid);
			if (result)
			{
				// 解码
				String college = request.getParameter(SchActUseClassroomTable.SAUC_COLLEGE);
				college = URLDecoder.decode(college, "UTF-8");
				String sauc_college = college;
				Map equalParams = new HashMap();
				Map notEqualParams = new HashMap<>();
				String sauc_regions = request.getParameter("saucregions");
				String saucregion[] = sauc_regions.split(",");
				for (int i = 0; i < saucregion.length; i++)
				{
					String sauc_region = saucregion[i];
					if (sauc_region == null || sauc_region.equals(""))
						continue;
					result = false;
					equalParams.put(SchActUseClassroomTable.SAUC_REGION, sauc_region);
					equalParams.put(SchActUseClassroomTable.SAUC_COLLEGE, sauc_college);
					notEqualParams.put(SchActUseClassroomTable.SAUC_SITE, "小计");
					// 获取该校区所有记录
					List<SchActUseClassroom> sums = schActUseClassroomDao.getEqualSchActUseClassroom(equalParams,
							notEqualParams);
					equalParams.put(SchActUseClassroomTable.SAUC_SITE, "小计");
					// 获取该校区的小计记录
					List<SchActUseClassroom> subtotals = schActUseClassroomDao.getEqualSchActUseClassroom(equalParams,
							null);
					int subtotalRoom = 0, multiRoom = 0, regularRoom = 0, computerRoom = 0, voiceRoom = 0,
							otherRoom = 0;
					int subtotalSeat = 0, multiSeat = 0, regularSeat = 0, computerSeat = 0, voiceSeat = 0,
							otherSeat = 0;
					if (sums != null && sums.size() > 0)
					{
						for (SchActUseClassroom sauc : sums)
						{
							subtotalRoom += (sauc.getSauc_subtotalroom() < 0 ? 0 : sauc.getSauc_subtotalroom());
							subtotalSeat += (sauc.getSauc_subtotalseat() < 0 ? 0 : sauc.getSauc_subtotalseat());
							multiRoom += (sauc.getSauc_multiroom() < 0 ? 0 : sauc.getSauc_multiroom());
							multiSeat += (sauc.getSauc_multiseat() < 0 ? 0 : sauc.getSauc_multiseat());
							regularRoom += (sauc.getSauc_regularroom() < 0 ? 0 : sauc.getSauc_regularroom());
							regularSeat += (sauc.getSauc_regularseat() < 0 ? 0 : sauc.getSauc_regularseat());
							computerRoom += (sauc.getSauc_computerroom() < 0 ? 0 : sauc.getSauc_computerroom());
							computerSeat += (sauc.getSauc_computerseat() < 0 ? 0 : sauc.getSauc_computerseat());
							voiceRoom += (sauc.getSauc_voiceroom() < 0 ? 0 : sauc.getSauc_voiceroom());
							voiceSeat += (sauc.getSauc_voiceseat() < 0 ? 0 : sauc.getSauc_voiceseat());
							otherRoom += (sauc.getSauc_otherroom() < 0 ? 0 : sauc.getSauc_otherroom());
							otherSeat += (sauc.getSauc_otherseat() < 0 ? 0 : sauc.getSauc_otherseat());
						}
					}
					// 修改小计
					if (subtotals != null && subtotals.size() > 0)
					{
						SchActUseClassroom subtotal = subtotals.get(0);
						if (subtotalRoom == 0 && subtotalSeat == 0) // 如果小计结果为空，删除该条小计记录
						{
							result = schActUseClassroomDao.batchDelete(new String[]
							{ subtotal.getSauc_id() + "" });
						} else
						{
							equalParams.clear();
							equalParams.put(SchActUseClassroomTable.SAUC_SUBTOTALROOM, subtotalRoom + "");
							equalParams.put(SchActUseClassroomTable.SAUC_SUBTOTALSEAT, subtotalSeat + "");
							equalParams.put(SchActUseClassroomTable.SAUC_MULTIROOM, multiRoom + "");
							equalParams.put(SchActUseClassroomTable.SAUC_MULTISEAT, multiSeat + "");
							equalParams.put(SchActUseClassroomTable.SAUC_REGULARROOM, regularRoom + "");
							equalParams.put(SchActUseClassroomTable.SAUC_REGULARSEAT, regularSeat + "");
							equalParams.put(SchActUseClassroomTable.SAUC_COMPUTERROOM, computerRoom + "");
							equalParams.put(SchActUseClassroomTable.SAUC_COMPUTERSEAT, computerSeat + "");
							equalParams.put(SchActUseClassroomTable.SAUC_VOICEROOM, voiceRoom + "");
							equalParams.put(SchActUseClassroomTable.SAUC_VOICESEAT, voiceSeat + "");
							equalParams.put(SchActUseClassroomTable.SAUC_OTHERROOM, otherRoom + "");
							equalParams.put(SchActUseClassroomTable.SAUC_OTHERSEAT, otherSeat + "");
							equalParams.put(SchActUseClassroomTable.SAUC_ISNULL, "0");
							if (schActUseClassroomDao.alterSchActUseClassroom(equalParams,
									Integer.toString((subtotal.getSauc_id()))) == 1)
								result = true;
						}
					} else
					{
						if (subtotalRoom != 0 && subtotalSeat != 0) // 如果小计结果不为空，添加小计记录
						{
							SchActUseClassroom subtotal = new SchActUseClassroom(0, sauc_region, "小计", subtotalRoom,
									subtotalSeat, multiRoom, multiSeat, regularRoom, regularSeat, computerRoom,
									computerSeat, voiceRoom, voiceSeat, otherRoom, otherSeat, 1, null, sauc_college,
									"",0);
							if (schActUseClassroomDao.addSchActUseClassroom(subtotal) == 1)
								result = true;
						}
					}
					equalParams.clear();
					equalParams.put(SchActUseClassroomTable.SAUC_REGION, "合计");
					equalParams.put(SchActUseClassroomTable.SAUC_SITE, "");
					equalParams.put(SchActUseClassroomTable.SAUC_COLLEGE, sauc_college);
					List<SchActUseClassroom> totals = schActUseClassroomDao.getEqualSchActUseClassroom(equalParams,
							null);
					equalParams.clear();
					equalParams.put(SchActUseClassroomTable.SAUC_SITE, "小计");
					equalParams.put(SchActUseClassroomTable.SAUC_COLLEGE, sauc_college);
					subtotals = schActUseClassroomDao.getEqualSchActUseClassroom(equalParams, null);
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
					if (subtotals != null && subtotals.size() > 0)
					{
						for (SchActUseClassroom sauc : subtotals)
						{
							subtotalRoom += sauc.getSauc_subtotalroom();
							subtotalSeat += sauc.getSauc_subtotalseat();
							multiRoom += sauc.getSauc_multiroom();
							multiSeat += sauc.getSauc_multiseat();
							regularRoom += sauc.getSauc_regularroom();
							regularSeat += sauc.getSauc_regularseat();
							computerRoom += sauc.getSauc_computerroom();
							computerSeat += sauc.getSauc_computerseat();
							voiceRoom += sauc.getSauc_voiceroom();
							voiceSeat += sauc.getSauc_voiceseat();
							otherRoom += sauc.getSauc_otherroom();
							otherSeat += sauc.getSauc_otherseat();
						}
					}
					if (totals != null && totals.size() > 0)
					{
						SchActUseClassroom total = totals.get(0);
						equalParams.clear();
						equalParams.put(SchActUseClassroomTable.SAUC_SUBTOTALROOM, subtotalRoom + "");
						equalParams.put(SchActUseClassroomTable.SAUC_SUBTOTALSEAT, subtotalSeat + "");
						equalParams.put(SchActUseClassroomTable.SAUC_MULTIROOM, multiRoom + "");
						equalParams.put(SchActUseClassroomTable.SAUC_MULTISEAT, multiSeat + "");
						equalParams.put(SchActUseClassroomTable.SAUC_REGULARROOM, regularRoom + "");
						equalParams.put(SchActUseClassroomTable.SAUC_REGULARSEAT, regularSeat + "");
						equalParams.put(SchActUseClassroomTable.SAUC_COMPUTERROOM, computerRoom + "");
						equalParams.put(SchActUseClassroomTable.SAUC_COMPUTERSEAT, computerSeat + "");
						equalParams.put(SchActUseClassroomTable.SAUC_VOICEROOM, voiceRoom + "");
						equalParams.put(SchActUseClassroomTable.SAUC_VOICESEAT, voiceSeat + "");
						equalParams.put(SchActUseClassroomTable.SAUC_OTHERROOM, otherRoom + "");
						equalParams.put(SchActUseClassroomTable.SAUC_OTHERSEAT, otherSeat + "");
						equalParams.put(SchActUseClassroomTable.SAUC_SERIALNUMBER,
								(schActUseClassroomDao.getMaxSerialNum() + 1) + "");
						equalParams.put(SchActUseClassroomTable.SAUC_ISNULL, "0");
						if (schActUseClassroomDao.alterSchActUseClassroom(equalParams,
								Integer.toString((total.getSauc_id()))) == 1)
							result = true;
					} else
					{
						SchActUseClassroom total = new SchActUseClassroom(0, "合计", "", subtotalRoom, subtotalSeat,
								multiRoom, multiSeat, regularRoom, regularSeat, computerRoom, computerSeat, voiceRoom,
								voiceSeat, otherRoom, otherSeat, 1, null, sauc_college, "",0);
						if (schActUseClassroomDao.addSchActUseClassroom(total) == 1)
							result = true;
					}
					equalParams.clear();
					notEqualParams.clear();
				}
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}

		System.out.println("删除纪录的结果" + result);

		out.print(result);
	}
}
