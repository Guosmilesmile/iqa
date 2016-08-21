package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import cn.edu.xmu.dao.LibraryYearDao;
import cn.edu.xmu.daoimpl.LibraryYearDaoImpl;
import cn.edu.xmu.entity.LibraryYear;
import cn.edu.xmu.table.LibraryYearTable;
import cn.edu.xmu.util.FastJsonTool;

@WebServlet("/Sec_AddLibraryYearServlet")
public class Sec_AddLibraryYearServlet extends HttpServlet
{

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sec_AddLibraryYearServlet()
	{
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		this.doPost(request, response);
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
		PrintWriter out = response.getWriter();

		String data = request.getParameter("rowdata");
		int serialnumber = Integer.parseInt(request.getParameter("serialnumber"));
		// 解码
		String college = request.getParameter(LibraryYearTable.LY_COLLEGE);
		college = URLDecoder.decode(college, "UTF-8");

		data = URLDecoder.decode(data, "UTF-8");
		data = data.substring(1, data.length() - 1);
		try
		{
			JSONObject json = new JSONObject(data);
			String number = json.getString(LibraryYearTable.LY_NUMBER);
			String seatnumber = json.getString(LibraryYearTable.LY_SEATNUMBER);
			String paperbooknumber = json.getString(LibraryYearTable.LY_PAPERBOOKNUMBER);
			String paperjournalnumber = json.getString(LibraryYearTable.LY_PAPERJOURNALNUMBER);
			String paperjournaltype = json.getString(LibraryYearTable.LY_PAPERJOURNALTYPE);
			String ebooknumber = json.getString(LibraryYearTable.LY_EBOOKNUMBER);
			String ebookchnnumber = json.getString(LibraryYearTable.LY_EBOOKCHNNUMBER);
			String ebookforeignnumber = json.getString(LibraryYearTable.LY_EBOOKFOREIGNNUMBER);
			String ejournaltype = json.getString(LibraryYearTable.LY_EJOURNALTYPE);
			String databasenumber = json.getString(LibraryYearTable.LY_DATABASENUMBER);
			int ly_number = -9;
			int ly_seatnumber = -9;
			int ly_paperbooknumber = -9;
			int ly_paperjournalnumber = -9;
			int ly_paperjournaltype = -9;
			int ly_ebooknumber = -9;
			int ly_ebookchnnumber = -9;
			int ly_ebookforeignnumber = -9;
			int ly_ejournaltype = -9;
			int ly_databasenumber = -9;
			int ly_serialnumber = serialnumber;
			String ly_comments = "";
			String ly_college = college;
			int isnull = 0;

			if ("".equals(number) || "".equals(seatnumber) || "".equals(paperbooknumber)
					|| "".equals(paperjournalnumber) || "".equals(paperjournaltype) || "".equals(ebooknumber)
					|| "".equals(ebookchnnumber) || "".equals(ebookforeignnumber) || "".equals(ejournaltype)
					|| "".equals(databasenumber))
			{
				isnull = 1;
			}

			if ("".equals(number) && "".equals(seatnumber) && "".equals(paperbooknumber)
					&& "".equals(paperjournalnumber) && "".equals(paperjournaltype) && "".equals(ebooknumber)
					&& "".equals(ebookchnnumber) && "".equals(ebookforeignnumber) && "".equals(ejournaltype)
					&& "".equals(databasenumber))
			{

				out.print(-1);
				return;
			}
			if (number != null && !"".equals(number))
				ly_number = Integer.parseInt(number);
			if (seatnumber != null && !"".equals(seatnumber))
				ly_seatnumber = Integer.parseInt(seatnumber);
			if (paperbooknumber != null && !"".equals(paperbooknumber))
				ly_paperbooknumber = Integer.parseInt(paperbooknumber);
			if (paperjournalnumber != null && !"".equals(paperjournalnumber))
				ly_paperjournalnumber = Integer.parseInt(paperjournalnumber);
			if (paperjournaltype != null && !"".equals(paperjournaltype))
				ly_paperjournaltype = Integer.parseInt(paperjournaltype);
			if (ebooknumber != null && !"".equals(ebooknumber))
				ly_ebooknumber = Integer.parseInt(ebooknumber);
			if (ebookchnnumber != null && !"".equals(ebookchnnumber))
				ly_ebookchnnumber = Integer.parseInt(ebookchnnumber);
			if (ebookforeignnumber != null && !"".equals(ebookforeignnumber))
				ly_ebookforeignnumber = Integer.parseInt(ebookforeignnumber);
			if (ejournaltype != null && !"".equals(ejournaltype))
				ly_ejournaltype = Integer.parseInt(ejournaltype);
			if (databasenumber != null && !"".equals(databasenumber))
				ly_databasenumber = Integer.parseInt(databasenumber);
			LibraryYear ly = new LibraryYear(ly_number, ly_seatnumber, ly_paperbooknumber, ly_paperjournalnumber,
					ly_paperjournaltype, ly_ebooknumber, ly_ebookchnnumber, ly_ebookforeignnumber, ly_ejournaltype,
					ly_databasenumber, ly_serialnumber, ly_college, ly_comments, isnull);

			LibraryYearDao lyDao = new LibraryYearDaoImpl();
			lyDao.addRecord(ly);

			out.print(true);
		} catch (JSONException e)
		{
			e.printStackTrace();
		} finally
		{
			out.close();
		}

	}
}
