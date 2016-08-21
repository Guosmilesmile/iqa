package cn.edu.xmu.servlet;

import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.edu.xmu.dao.SchActUseClassroomDao;
import cn.edu.xmu.daoimpl.SchActUseClassroomDaoImpl;
import cn.edu.xmu.entity.SchActUseClassroom;
import cn.edu.xmu.table.SchActUseClassroomTable;

/**
 * 
 * @author xiaoping 数据报表2-3-1全校性实际使用的教室（时点） date 2015-7-6
 *
 */
@WebServlet("/Sec_AddSchActUseClassroom")
public class Sec_AddSchActUseClassroom extends HttpServlet
{

	private static final long serialVersionUID = 1L;

	public Sec_AddSchActUseClassroom()
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

		String data = request.getParameter("rowdata");
		data = URLDecoder.decode(data, "UTF-8");
		data = data.substring(1, data.length() - 1);
		System.out.println(data);
		int serialnumber = Integer.parseInt(request.getParameter("serialnumber"));
		// 解码
		String college = request.getParameter(SchActUseClassroomTable.SAUC_COLLEGE);
		college = URLDecoder.decode(college, "UTF-8");
		int result = 0;
		try
		{
			// JSONArray jsonArray = new JSONArray(data);
			// int iSize = jsonArray.length();
			// System.out.println("Size:" + iSize);
			JSONObject jsonObj = new JSONObject(data);
			SchActUseClassroomDao saucDao = new SchActUseClassroomDaoImpl();
			// for (int i = 0; i < iSize; i++)
			// {
			// JSONObject jsonObj = jsonArray.getJSONObject(i);

			String sauc_region = jsonObj.getString(SchActUseClassroomTable.SAUC_REGION);
			String sauc_site = jsonObj.getString(SchActUseClassroomTable.SAUC_SITE);
			String multiroom = jsonObj.getString(SchActUseClassroomTable.SAUC_MULTIROOM);
			String multiseat = jsonObj.getString(SchActUseClassroomTable.SAUC_MULTISEAT);
			String regularroom = jsonObj.getString(SchActUseClassroomTable.SAUC_REGULARROOM);
			String regularseat = jsonObj.getString(SchActUseClassroomTable.SAUC_REGULARSEAT);
			String computerroom = jsonObj.getString(SchActUseClassroomTable.SAUC_COMPUTERROOM);
			String computerseat = jsonObj.getString(SchActUseClassroomTable.SAUC_COMPUTERSEAT);
			String voiceroom = jsonObj.getString(SchActUseClassroomTable.SAUC_VOICEROOM);
			String voiceseat = jsonObj.getString(SchActUseClassroomTable.SAUC_VOICESEAT);
			String otherroom = jsonObj.getString(SchActUseClassroomTable.SAUC_OTHERROOM);
			String otherseat = jsonObj.getString(SchActUseClassroomTable.SAUC_OTHERSEAT);
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
			int sauc_serialnumber = serialnumber;
			String sauc_college = college;
			String sauc_comments = "";
			int sauc_isnull = 0;
			if ("".equals(sauc_region) || "".equals(sauc_site) || "".equals(multiroom) || "".equals(multiseat)
					|| "".equals(regularroom) || "".equals(regularseat) || "".equals(computerroom)
					|| "".equals(computerseat) || "".equals(voiceroom) || "".equals(voiceseat) || "".equals(otherroom)
					|| "".equals(otherseat))
				sauc_isnull = 1;
			if ("".equals(sauc_region) && "".equals(sauc_site) && "".equals(multiroom) && "".equals(multiseat)
					&& "".equals(regularroom) && "".equals(regularseat) && "".equals(computerroom)
					&& "".equals(computerseat) && "".equals(voiceroom) && "".equals(voiceseat) && "".equals(otherroom)
					&& "".equals(otherseat))
			{
				out.print(-1);
				return;
			}
			if (!"".equals(multiroom))
			{
				sauc_multiroom = Integer.parseInt(multiroom);
				sauc_subtotalroom += sauc_multiroom;
			}
			if (!"".equals(multiseat))
			{
				sauc_multiseat = Integer.parseInt(multiseat);
				sauc_subtotalseat += sauc_multiseat;
			}
			if (!"".equals(regularroom))
			{
				sauc_regularroom = Integer.parseInt(regularroom);
				sauc_subtotalroom += sauc_regularroom;
			}
			if (!"".equals(regularseat))
			{
				sauc_regularseat = Integer.parseInt(regularseat);
				sauc_subtotalseat += sauc_regularseat;
			}
			if (!"".equals(computerroom))
			{
				sauc_computerroom = Integer.parseInt(computerroom);
				sauc_subtotalroom += sauc_computerroom;
			}
			if (!"".equals(computerseat))
			{
				sauc_computerseat = Integer.parseInt(computerseat);
				sauc_subtotalseat += sauc_computerseat;
			}
			if (!"".equals(voiceroom))
			{
				sauc_voiceroom = Integer.parseInt(voiceroom);
				sauc_subtotalroom += sauc_voiceroom;
			}
			if (!"".equals(voiceseat))
			{
				sauc_voiceseat = Integer.parseInt(voiceseat);
				sauc_subtotalseat += sauc_voiceseat;
			}
			if (!"".equals(otherroom))
			{
				sauc_otherroom = Integer.parseInt(otherroom);
				sauc_subtotalroom += sauc_otherroom;
			}
			if (!"".equals(otherseat))
			{
				sauc_otherseat = Integer.parseInt(otherseat);
				sauc_subtotalseat += sauc_otherseat;
			}
			SchActUseClassroom schActUseClassroom = new SchActUseClassroom(0, sauc_region, sauc_site, sauc_subtotalroom,
					sauc_subtotalseat, sauc_multiroom, sauc_multiseat, sauc_regularroom, sauc_regularseat,
					sauc_computerroom, sauc_computerseat, sauc_voiceroom, sauc_voiceseat, sauc_otherroom,
					sauc_otherseat, sauc_serialnumber, null, sauc_college, sauc_comments, sauc_isnull);

			result = saucDao.addSchActUseClassroom(schActUseClassroom);
			if (result == 1)
			{
				Map equalParams = new HashMap();
				Map notEqualParams = new HashMap<>();
				equalParams.put(SchActUseClassroomTable.SAUC_REGION, sauc_region);
				equalParams.put(SchActUseClassroomTable.SAUC_COLLEGE, sauc_college);
				notEqualParams.put(SchActUseClassroomTable.SAUC_SITE, "小计");
				// 获取该校区所有记录
				List<SchActUseClassroom> sums = saucDao.getEqualSchActUseClassroom(equalParams, notEqualParams);
				equalParams.put(SchActUseClassroomTable.SAUC_SITE, "小计");
				// 获取该校区的小计记录
				List<SchActUseClassroom> subtotals = saucDao.getEqualSchActUseClassroom(equalParams, null);
				int subtotalRoom = 0, multiRoom = 0, regularRoom = 0, computerRoom = 0, voiceRoom = 0, otherRoom = 0;
				int subtotalSeat = 0, multiSeat = 0, regularSeat = 0, computerSeat = 0, voiceSeat = 0, otherSeat = 0;
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
					result = saucDao.alterSchActUseClassroom(equalParams, Integer.toString((subtotal.getSauc_id())));
				} else
				{
					SchActUseClassroom subtotal = new SchActUseClassroom(0, sauc_region, "小计", subtotalRoom,
							subtotalSeat, multiRoom, multiSeat, regularRoom, regularSeat, computerRoom, computerSeat,
							voiceRoom, voiceSeat, otherRoom, otherSeat, sauc_serialnumber, null, sauc_college,
							sauc_comments, 0);
					result = saucDao.addSchActUseClassroom(subtotal);
				}
				// 计算合计
				equalParams.clear();
				equalParams.put(SchActUseClassroomTable.SAUC_REGION, "合计");
				equalParams.put(SchActUseClassroomTable.SAUC_SITE, "");
				equalParams.put(SchActUseClassroomTable.SAUC_COLLEGE, sauc_college);
				List<SchActUseClassroom> totals = saucDao.getEqualSchActUseClassroom(equalParams, null);
				equalParams.clear();
				equalParams.put(SchActUseClassroomTable.SAUC_SITE, "小计");
				equalParams.put(SchActUseClassroomTable.SAUC_COLLEGE, sauc_college);
				subtotals = saucDao.getEqualSchActUseClassroom(equalParams, null);
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
					equalParams.put(SchActUseClassroomTable.SAUC_SERIALNUMBER, (saucDao.getMaxSerialNum() + 1) + "");
					equalParams.put(SchActUseClassroomTable.SAUC_ISNULL, "0");
					result = saucDao.alterSchActUseClassroom(equalParams, Integer.toString((total.getSauc_id())));
				} else
				{
					SchActUseClassroom total = new SchActUseClassroom(0, "合计", "", subtotalRoom, subtotalSeat,
							multiRoom, multiSeat, regularRoom, regularSeat, computerRoom, computerSeat, voiceRoom,
							voiceSeat, otherRoom, otherSeat, sauc_serialnumber, null, sauc_college, sauc_comments, 0);
					result = saucDao.addSchActUseClassroom(total);
				}
			}
			// }
		} catch (JSONException e)
		{
			e.printStackTrace();
		}
		out.print(result);
	}
}
