package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
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
@WebServlet("/Sec_UpdateSchActUseClassroom")
public class Sec_UpdateSchActUseClassroom extends HttpServlet
{

	private static final long serialVersionUID = 1L;

	public Sec_UpdateSchActUseClassroom()
	{
		super();
		// TODO Auto-generated constructor stub
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
		String patter = request.getParameter("patter");
		// 解码
		data = URLDecoder.decode(data, "UTF-8");
		data = data.substring(1, data.length() - 1);
		try
		{
			SchActUseClassroomDao schActUseClassroomDao = new SchActUseClassroomDaoImpl();
			if (patter != null && "batch".equals(patter))
			{
				System.out.println("批量更新");
				// 审核部分更新，可以批量
				data = "[" + data + "]";
				JSONArray jsons = new JSONArray(data);
				for (int i = 0; i < jsons.length(); i++)
				{
					JSONObject json = jsons.getJSONObject(i);
					String sauc_id = json.getString(SchActUseClassroomTable.SAUC_ID);
					String sauc_comments = json.getString(SchActUseClassroomTable.SAUC_COMMENTS);

					Map<String, String> params = new HashMap<String, String>();
					params.put(SchActUseClassroomTable.SAUC_ID, sauc_id);
					params.put(SchActUseClassroomTable.SAUC_COMMENTS, sauc_comments);
					int result = schActUseClassroomDao.alterSchActUseClassroom(params, sauc_id);

					out.print(result);
				}
			} else
			{
				JSONObject json = new JSONObject(data);
				String sauc_id = json.getString(SchActUseClassroomTable.SAUC_ID);
				String sauc_region = json.getString(SchActUseClassroomTable.SAUC_REGION);
				String sauc_site = json.getString(SchActUseClassroomTable.SAUC_SITE);
				String sauc_multiroom = json.getString(SchActUseClassroomTable.SAUC_MULTIROOM);
				String sauc_multiseat = json.getString(SchActUseClassroomTable.SAUC_MULTISEAT);
				String sauc_regularroom = json.getString(SchActUseClassroomTable.SAUC_REGULARROOM);
				String sauc_regularseat = json.getString(SchActUseClassroomTable.SAUC_REGULARSEAT);
				String sauc_computerroom = json.getString(SchActUseClassroomTable.SAUC_COMPUTERROOM);
				String sauc_computerseat = json.getString(SchActUseClassroomTable.SAUC_COMPUTERSEAT);
				String sauc_voiceroom = json.getString(SchActUseClassroomTable.SAUC_VOICEROOM);
				String sauc_voiceseat = json.getString(SchActUseClassroomTable.SAUC_VOICESEAT);
				String sauc_otherroom = json.getString(SchActUseClassroomTable.SAUC_OTHERROOM);
				String sauc_otherseat = json.getString(SchActUseClassroomTable.SAUC_OTHERSEAT);
				int sauc_subtotalroom = 0;
				int sauc_subtotalseat = 0;
				String sauc_comments = json.getString(SchActUseClassroomTable.SAUC_COMMENTS);
				String sauc_isnull = "0";
				if ("".equals(sauc_region) || "".equals(sauc_site) || "".equals(sauc_multiroom)
						|| "".equals(sauc_multiseat) || "".equals(sauc_regularroom) || "".equals(sauc_regularseat)
						|| "".equals(sauc_computerroom) || "".equals(sauc_computerseat) || "".equals(sauc_voiceroom)
						|| "".equals(sauc_voiceseat) || "".equals(sauc_otherroom) || "".equals(sauc_otherseat))
					sauc_isnull = "1";
				if ("".equals(sauc_multiroom))
					sauc_multiroom = "-9";
				else
					sauc_subtotalroom += Integer.parseInt(sauc_multiroom);
				if ("".equals(sauc_multiseat))
					sauc_multiseat = "-9";
				else
					sauc_subtotalseat += Integer.parseInt(sauc_multiseat);
				if ("".equals(sauc_regularroom))
					sauc_regularroom = "-9";
				else
					sauc_subtotalroom += Integer.parseInt(sauc_regularroom);
				if ("".equals(sauc_regularseat))
					sauc_regularseat = "-9";
				else
					sauc_subtotalseat += Integer.parseInt(sauc_regularseat);
				if ("".equals(sauc_computerroom))
					sauc_computerroom = "-9";
				else
					sauc_subtotalroom += Integer.parseInt(sauc_computerroom);
				if ("".equals(sauc_computerseat))
					sauc_computerseat = "-9";
				else
					sauc_subtotalseat += Integer.parseInt(sauc_computerseat);
				if ("".equals(sauc_voiceroom))
					sauc_voiceroom = "-9";
				else
					sauc_subtotalroom += Integer.parseInt(sauc_voiceroom);
				if ("".equals(sauc_voiceseat))
					sauc_voiceseat = "-9";
				else
					sauc_subtotalseat += Integer.parseInt(sauc_voiceseat);
				if ("".equals(sauc_otherroom))
					sauc_otherroom = "-9";
				else
					sauc_subtotalroom += Integer.parseInt(sauc_otherroom);
				if ("".equals(sauc_otherseat))
					sauc_otherseat = "-9";
				else
					sauc_subtotalseat += Integer.parseInt(sauc_otherseat);

				Map<String, String> params = new HashMap<String, String>();
				params.put(SchActUseClassroomTable.SAUC_ID, sauc_id);
				params.put(SchActUseClassroomTable.SAUC_REGION, sauc_region);
				params.put(SchActUseClassroomTable.SAUC_SITE, sauc_site);
				params.put(SchActUseClassroomTable.SAUC_SUBTOTALROOM, sauc_subtotalroom + "");
				params.put(SchActUseClassroomTable.SAUC_SUBTOTALSEAT, sauc_subtotalseat + "");
				params.put(SchActUseClassroomTable.SAUC_MULTIROOM, sauc_multiroom);
				params.put(SchActUseClassroomTable.SAUC_MULTISEAT, sauc_multiseat);
				params.put(SchActUseClassroomTable.SAUC_REGULARROOM, sauc_regularroom);
				params.put(SchActUseClassroomTable.SAUC_REGULARSEAT, sauc_regularseat);
				params.put(SchActUseClassroomTable.SAUC_COMPUTERROOM, sauc_computerroom);
				params.put(SchActUseClassroomTable.SAUC_COMPUTERSEAT, sauc_computerseat);
				params.put(SchActUseClassroomTable.SAUC_VOICEROOM, sauc_voiceroom);
				params.put(SchActUseClassroomTable.SAUC_VOICESEAT, sauc_voiceseat);
				params.put(SchActUseClassroomTable.SAUC_OTHERROOM, sauc_otherroom);
				params.put(SchActUseClassroomTable.SAUC_OTHERSEAT, sauc_otherseat);
				params.put(SchActUseClassroomTable.SAUC_COMMENTS, sauc_comments);
				params.put(SchActUseClassroomTable.SAUC_ISNULL, sauc_isnull);
				int result = schActUseClassroomDao.alterSchActUseClassroom(params, sauc_id);
				if (result == 1)
				{
					// 解码
					String college = request.getParameter(SchActUseClassroomTable.SAUC_COLLEGE);
					college = URLDecoder.decode(college, "UTF-8");
					int sauc_serialnumber = Integer.parseInt(request.getParameter("serialnumber"));
					String sauc_college = college;
					Map equalParams = new HashMap();
					Map notEqualParams = new HashMap<>();
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
						result = schActUseClassroomDao.alterSchActUseClassroom(equalParams,
								Integer.toString((subtotal.getSauc_id())));
					} else
					{
						SchActUseClassroom subtotal = new SchActUseClassroom(0, sauc_region, "小计", subtotalRoom,
								subtotalSeat, multiRoom, multiSeat, regularRoom, regularSeat, computerRoom,
								computerSeat, voiceRoom, voiceSeat, otherRoom, otherSeat, sauc_serialnumber, null,
								sauc_college, sauc_comments,0);
						result = schActUseClassroomDao.addSchActUseClassroom(subtotal);
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
						result = schActUseClassroomDao.alterSchActUseClassroom(equalParams,
								Integer.toString((total.getSauc_id())));
					} else
					{
						SchActUseClassroom total = new SchActUseClassroom(0, "合计", "", subtotalRoom, subtotalSeat,
								multiRoom, multiSeat, regularRoom, regularSeat, computerRoom, computerSeat, voiceRoom,
								voiceSeat, otherRoom, otherSeat, sauc_serialnumber, null, sauc_college, sauc_comments,0);
						result = schActUseClassroomDao.addSchActUseClassroom(total);
					}
				}
				out.print(result);
			}
		} catch (JSONException e)
		{
			e.printStackTrace();
		} finally
		{
			out.close();
		}
	}

}
