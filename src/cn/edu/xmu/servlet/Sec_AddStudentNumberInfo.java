package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.Date;

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
import cn.edu.xmu.entity.StudentNumberInfo;
import cn.edu.xmu.table.StudentNumberInfoTable;

/**
 * 
 * @author xiaoping 数据报表6-1-1 学生数量基本情况（时点）date 2015-7-5
 *
 */
@WebServlet("/Sec_AddStudentNumberInfo")
public class Sec_AddStudentNumberInfo extends HttpServlet
{

	private static final long serialVersionUID = 1L;

	public Sec_AddStudentNumberInfo()
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
		String college = request.getParameter(StudentNumberInfoTable.SNI_COLLEGE);
		college = URLDecoder.decode(college, "UTF-8");
		int result = 0;
		try
		{
			// JSONArray jsonArray = new JSONArray(data);
			// int iSize = jsonArray.length();
			// System.out.println("Size:" + iSize);
			JSONObject jsonObj = new JSONObject(data);
			StudentNumberInfoDao sniDao = new StudentNumberInfoDaoImpl();
			// for (int i = 0; i < iSize; i++)
			// {
			// JSONObject jsonObj = jsonArray.getJSONObject(i);

			String sni_stuinfobaselink = jsonObj.getString(StudentNumberInfoTable.SNI_STUINFOBASELINK);
			String ordiundergrastu = jsonObj.getString(StudentNumberInfoTable.SNI_ORDIUNDERGRASTU);
			String countryoverseastu = jsonObj.getString(StudentNumberInfoTable.SNI_COUNTRYOVERSEASTU);
			String highervocationstu = jsonObj.getString(StudentNumberInfoTable.SNI_HIGHERVOCATIONSTU);
			String postgraduate = jsonObj.getString(StudentNumberInfoTable.SNI_POSTGRADUATE);
			String fulltimepost = jsonObj.getString(StudentNumberInfoTable.SNI_FULLTIMEPOST);
			String nofulltimepost = jsonObj.getString(StudentNumberInfoTable.SNI_NOFULLTIMEPOST);
			String doctorcandidate = jsonObj.getString(StudentNumberInfoTable.SNI_DOCTORCANDIDATE);
			String fulltimedoct = jsonObj.getString(StudentNumberInfoTable.SNI_FULLTIMEDOCT);
			String nofulltimedoct = jsonObj.getString(StudentNumberInfoTable.SNI_NOFULLTIMEDOCT);
			String abroadstu = jsonObj.getString(StudentNumberInfoTable.SNI_ABROADSTU);
			String ordipreppie = jsonObj.getString(StudentNumberInfoTable.SNI_ORDIPREPPIE);
			String advancedstu = jsonObj.getString(StudentNumberInfoTable.SNI_ADVANCEDSTU);
			String adultfulltimestu = jsonObj.getString(StudentNumberInfoTable.SNI_ADULTFULLTIMESTU);
			String parttimestu = jsonObj.getString(StudentNumberInfoTable.SNI_PARTTIMESTU);
			String correspondencestu = jsonObj.getString(StudentNumberInfoTable.SNI_CORRESPONDENCESTU);
			String networkstu = jsonObj.getString(StudentNumberInfoTable.SNI_NETWORKSTU);
			String selftaughtstu = jsonObj.getString(StudentNumberInfoTable.SNI_SELFTAUGHTSTU);
			int sni_ordiundergrastu = -9;
			int sni_countryoverseastu = -9;
			int sni_highervocationstu = -9;
			int sni_postgraduate = -9;
			int sni_fulltimepost = -9;
			int sni_nofulltimepost = -9;
			int sni_doctorcandidate = -9;
			int sni_fulltimedoct = -9;
			int sni_nofulltimedoct = -9;
			int sni_abroadstu = -9;
			int sni_ordipreppie = -9;
			int sni_advancedstu = -9;
			int sni_adultfulltimestu = -9;
			int sni_parttimestu = -9;
			int sni_correspondencestu = -9;
			int sni_networkstu = -9;
			int sni_selftaughtstu = -9;
			int sni_serialnumber = serialnumber;
			String sni_college = college;
			String sni_comments = "";
			int sni_isnull = 0;
			if ("".equals(sni_stuinfobaselink) || "".equals(ordiundergrastu) || "".equals(countryoverseastu)
					|| "".equals(highervocationstu) || "".equals(postgraduate)
					|| "".equals(fulltimepost) || "".equals(nofulltimepost)
					|| "".equals(doctorcandidate) || "".equals(fulltimedoct)
					|| "".equals(nofulltimedoct) || "".equals(abroadstu)
					|| "".equals(ordipreppie) || "".equals(advancedstu)
					|| "".equals(adultfulltimestu) || "".equals(parttimestu)
					|| "".equals(correspondencestu) || "".equals(networkstu)
					|| "".equals(selftaughtstu))
				sni_isnull = 1;
			if ("".equals(sni_stuinfobaselink) && "".equals(ordiundergrastu) && "".equals(countryoverseastu)
					&& "".equals(highervocationstu) && "".equals(postgraduate)
					&& "".equals(fulltimepost) && "".equals(nofulltimepost)
					&& "".equals(doctorcandidate) && "".equals(fulltimedoct)
					&& "".equals(nofulltimedoct) && "".equals(abroadstu)
					&& "".equals(ordipreppie) && "".equals(advancedstu)
					&& "".equals(adultfulltimestu) && "".equals(parttimestu)
					&& "".equals(correspondencestu) && "".equals(networkstu)
					&& "".equals(selftaughtstu))
			{
				out.print(-1);
				return;
			}
			if(ordiundergrastu!=null && !"".equals(ordiundergrastu))
				sni_ordiundergrastu = Integer.parseInt(ordiundergrastu);
			if(countryoverseastu!=null && !"".equals(countryoverseastu))
				sni_countryoverseastu = Integer.parseInt(countryoverseastu);
			if(highervocationstu!=null && !"".equals(highervocationstu))
				sni_highervocationstu = Integer.parseInt(highervocationstu);
			if(postgraduate!=null && !"".equals(postgraduate))
				sni_postgraduate = Integer.parseInt(postgraduate);
			if(fulltimepost!=null && !"".equals(fulltimepost))
				sni_fulltimepost = Integer.parseInt(fulltimepost);
			if(nofulltimepost!=null && !"".equals(nofulltimepost))
				sni_nofulltimepost = Integer.parseInt(nofulltimepost);
			if(doctorcandidate!=null && !"".equals(doctorcandidate))
				sni_doctorcandidate = Integer.parseInt(doctorcandidate);
			if(fulltimedoct!=null && !"".equals(fulltimedoct))
				sni_fulltimedoct = Integer.parseInt(fulltimedoct);
			if(nofulltimedoct!=null && !"".equals(nofulltimedoct))
				sni_nofulltimedoct = Integer.parseInt(nofulltimedoct);
			if(abroadstu!=null && !"".equals(abroadstu))
				sni_abroadstu = Integer.parseInt(abroadstu);
			if(ordipreppie!=null && !"".equals(ordipreppie))
				sni_ordipreppie = Integer.parseInt(ordipreppie);
			if(advancedstu!=null && !"".equals(advancedstu))
				sni_advancedstu = Integer.parseInt(advancedstu);
			if(adultfulltimestu!=null && !"".equals(adultfulltimestu))
				sni_adultfulltimestu = Integer.parseInt(adultfulltimestu);
			if(parttimestu!=null && !"".equals(parttimestu))
				sni_parttimestu = Integer.parseInt(parttimestu);
			if(correspondencestu!=null && !"".equals(correspondencestu))
				sni_correspondencestu = Integer.parseInt(correspondencestu);
			if(networkstu!=null && !"".equals(networkstu))
				sni_networkstu = Integer.parseInt(networkstu);
			if(selftaughtstu!=null && !"".equals(selftaughtstu))
				sni_selftaughtstu = Integer.parseInt(selftaughtstu);
			StudentNumberInfo studentNumberInfo = new StudentNumberInfo(0, sni_stuinfobaselink, sni_ordiundergrastu,
					sni_countryoverseastu, sni_highervocationstu, sni_postgraduate, sni_fulltimepost,
					sni_nofulltimepost, sni_doctorcandidate, sni_fulltimedoct, sni_nofulltimedoct, sni_abroadstu,
					sni_ordipreppie, sni_advancedstu, sni_adultfulltimestu, sni_parttimestu, sni_correspondencestu,
					sni_networkstu, sni_selftaughtstu, sni_serialnumber, null, sni_college, sni_comments, sni_isnull);

			result = sniDao.addStudentNumberInfo(studentNumberInfo);
			// }
		} catch (JSONException e)
		{
			e.printStackTrace();
		}

		out.print(result);
	}

}
