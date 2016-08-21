package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.Date;
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

import cn.edu.xmu.dao.StudentNumberInfoDao;
import cn.edu.xmu.daoimpl.StudentNumberInfoDaoImpl;
import cn.edu.xmu.table.StudentNumberInfoTable;

/**
 * 
 * @author xiaoping 数据报表6-1-1 学生数量基本情况（时点）date 2015-7-5
 *
 */
@WebServlet("/Sec_UpdateStudentNumberInfo")
public class Sec_UpdateStudentNumberInfo extends HttpServlet
{

	private static final long serialVersionUID = 1L;

	public Sec_UpdateStudentNumberInfo()
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
		String patter = request.getParameter("patter");
		// 解码
		data = URLDecoder.decode(data, "UTF-8");
		data = data.substring(1, data.length() - 1);
		try
		{
			StudentNumberInfoDao studentNumberInfoDao = new StudentNumberInfoDaoImpl();
			if (patter != null && "batch".equals(patter))
			{
				System.out.println("批量更新");
				// 审核部分更新，可以批量
				data = "[" + data + "]";
				JSONArray jsons = new JSONArray(data);
				for (int i = 0; i < jsons.length(); i++)
				{
					JSONObject json = jsons.getJSONObject(i);
					String sni_id = json.getString(StudentNumberInfoTable.SNI_ID);
					String sni_comments = json.getString(StudentNumberInfoTable.SNI_COMMENTS);

					Map<String, String> params = new HashMap<String, String>();
					params.put(StudentNumberInfoTable.SNI_ID, sni_id);
					params.put(StudentNumberInfoTable.SNI_COMMENTS, sni_comments);
					int result = studentNumberInfoDao.alterStudentNumberInfo(params, sni_id);

					out.print(result);
				}
			} else
			{
				JSONObject json = new JSONObject(data);
				String sni_id = json.getString(StudentNumberInfoTable.SNI_ID);
				String sni_stuinfobaselink = json.getString(StudentNumberInfoTable.SNI_STUINFOBASELINK);
				String sni_ordiundergrastu = json.getString(StudentNumberInfoTable.SNI_ORDIUNDERGRASTU);
				String sni_countryoverseastu = json.getString(StudentNumberInfoTable.SNI_COUNTRYOVERSEASTU);
				String sni_highervocationstu = json.getString(StudentNumberInfoTable.SNI_HIGHERVOCATIONSTU);
				String sni_postgraduate = json.getString(StudentNumberInfoTable.SNI_POSTGRADUATE);
				String sni_fulltimepost = json.getString(StudentNumberInfoTable.SNI_FULLTIMEPOST);
				String sni_nofulltimepost = json.getString(StudentNumberInfoTable.SNI_NOFULLTIMEPOST);
				String sni_doctorcandidate = json.getString(StudentNumberInfoTable.SNI_DOCTORCANDIDATE);
				String sni_fulltimedoct = json.getString(StudentNumberInfoTable.SNI_FULLTIMEDOCT);
				String sni_nofulltimedoct = json.getString(StudentNumberInfoTable.SNI_NOFULLTIMEDOCT);
				String sni_abroadstu = json.getString(StudentNumberInfoTable.SNI_ABROADSTU);
				String sni_ordipreppie = json.getString(StudentNumberInfoTable.SNI_ORDIPREPPIE);
				String sni_advancedstu = json.getString(StudentNumberInfoTable.SNI_ADVANCEDSTU);
				String sni_adultfulltimestu = json.getString(StudentNumberInfoTable.SNI_ADULTFULLTIMESTU);
				String sni_parttimestu = json.getString(StudentNumberInfoTable.SNI_PARTTIMESTU);
				String sni_correspondencestu = json.getString(StudentNumberInfoTable.SNI_CORRESPONDENCESTU);
				String sni_networkstu = json.getString(StudentNumberInfoTable.SNI_NETWORKSTU);
				String sni_selftaughtstu = json.getString(StudentNumberInfoTable.SNI_SELFTAUGHTSTU);
				String sni_comments = json.getString(StudentNumberInfoTable.SNI_COMMENTS);
				String sni_isnull = "0";
				if ("".equals(sni_stuinfobaselink) || "".equals(sni_ordiundergrastu) || "".equals(sni_countryoverseastu)
						|| "".equals(sni_highervocationstu) || "".equals(sni_postgraduate)
						|| "".equals(sni_fulltimepost) || "".equals(sni_nofulltimepost)
						|| "".equals(sni_doctorcandidate) || "".equals(sni_fulltimedoct)
						|| "".equals(sni_nofulltimedoct) || "".equals(sni_abroadstu)
						|| "".equals(sni_ordipreppie) || "".equals(sni_advancedstu)
						|| "".equals(sni_adultfulltimestu) || "".equals(sni_parttimestu)
						|| "".equals(sni_correspondencestu) || "".equals(sni_networkstu)
						|| "".equals(sni_selftaughtstu))
					sni_isnull = "1";
				if(sni_ordiundergrastu==null || "".equals(sni_ordiundergrastu))
					sni_ordiundergrastu = "-9";
				if(sni_countryoverseastu==null || "".equals(sni_countryoverseastu))
					sni_countryoverseastu = "-9";
				if(sni_highervocationstu==null || "".equals(sni_highervocationstu))
					sni_highervocationstu = "-9";
				if(sni_postgraduate==null || "".equals(sni_postgraduate))
					sni_postgraduate = "-9";
				if(sni_fulltimepost==null || "".equals(sni_fulltimepost))
					sni_fulltimepost = "-9";
				if(sni_nofulltimepost==null || "".equals(sni_nofulltimepost))
					sni_nofulltimepost = "-9";
				if(sni_doctorcandidate==null || "".equals(sni_doctorcandidate))
					sni_doctorcandidate = "-9";
				if(sni_fulltimedoct==null || "".equals(sni_fulltimedoct))
					sni_fulltimedoct = "-9";
				if(sni_nofulltimedoct==null || "".equals(sni_nofulltimedoct))
					sni_nofulltimedoct = "-9";
				if(sni_abroadstu==null || "".equals(sni_abroadstu))
					sni_abroadstu = "-9";
				if(sni_ordipreppie==null || "".equals(sni_ordipreppie))
					sni_ordipreppie = "-9";
				if(sni_advancedstu==null || "".equals(sni_advancedstu))
					sni_advancedstu = "-9";
				if(sni_adultfulltimestu==null || "".equals(sni_adultfulltimestu))
					sni_adultfulltimestu = "-9";
				if(sni_parttimestu==null || "".equals(sni_parttimestu))
					sni_parttimestu = "-9";
				if(sni_correspondencestu==null || "".equals(sni_correspondencestu))
					sni_correspondencestu = "-9";
				if(sni_networkstu==null || "".equals(sni_networkstu))
					sni_networkstu = "-9";
				if(sni_selftaughtstu==null || "".equals(sni_selftaughtstu))
					sni_selftaughtstu = "-9";
				Map<String, String> params = new HashMap<String, String>();
				params.put(StudentNumberInfoTable.SNI_ID, sni_id);
				params.put(StudentNumberInfoTable.SNI_STUINFOBASELINK, sni_stuinfobaselink);
				params.put(StudentNumberInfoTable.SNI_ORDIUNDERGRASTU, sni_ordiundergrastu);
				params.put(StudentNumberInfoTable.SNI_COUNTRYOVERSEASTU, sni_countryoverseastu);
				params.put(StudentNumberInfoTable.SNI_HIGHERVOCATIONSTU, sni_highervocationstu);
				params.put(StudentNumberInfoTable.SNI_POSTGRADUATE, sni_postgraduate);
				params.put(StudentNumberInfoTable.SNI_FULLTIMEPOST, sni_fulltimepost);
				params.put(StudentNumberInfoTable.SNI_NOFULLTIMEPOST, sni_nofulltimepost);
				params.put(StudentNumberInfoTable.SNI_DOCTORCANDIDATE, sni_doctorcandidate);
				params.put(StudentNumberInfoTable.SNI_FULLTIMEDOCT, sni_fulltimedoct);
				params.put(StudentNumberInfoTable.SNI_NOFULLTIMEDOCT, sni_nofulltimedoct);
				params.put(StudentNumberInfoTable.SNI_ABROADSTU, sni_abroadstu);
				params.put(StudentNumberInfoTable.SNI_ORDIPREPPIE, sni_ordipreppie);
				params.put(StudentNumberInfoTable.SNI_ADVANCEDSTU, sni_advancedstu);
				params.put(StudentNumberInfoTable.SNI_ADULTFULLTIMESTU, sni_adultfulltimestu);
				params.put(StudentNumberInfoTable.SNI_PARTTIMESTU, sni_parttimestu);
				params.put(StudentNumberInfoTable.SNI_CORRESPONDENCESTU, sni_correspondencestu);
				params.put(StudentNumberInfoTable.SNI_NETWORKSTU, sni_networkstu);
				params.put(StudentNumberInfoTable.SNI_SELFTAUGHTSTU, sni_selftaughtstu);
				params.put(StudentNumberInfoTable.SNI_COMMENTS, sni_comments);
				params.put(StudentNumberInfoTable.SNI_ISNULL, sni_isnull);
				int result = studentNumberInfoDao.alterStudentNumberInfo(params, sni_id);

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
