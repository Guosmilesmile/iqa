package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
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

import cn.edu.xmu.dao.LibraryYearDao;
import cn.edu.xmu.daoimpl.LibraryYearDaoImpl;
import cn.edu.xmu.table.LibraryYearTable;

@WebServlet("/Sec_UpdateLibraryYearServlet")
public class Sec_UpdateLibraryYearServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public Sec_UpdateLibraryYearServlet()
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
		String patter = request.getParameter("patter");
		// int serialnumber =
		// Integer.parseInt(request.getParameter("serialnumber"));
		// 解码
		//String college = request.getParameter(LibraryYearTable.LY_COLLEGE);
		//college = URLDecoder.decode(college, "UTF-8");

		data = URLDecoder.decode(data, "UTF-8");
		data = data.substring(1, data.length() - 1);
		try
		{
			if (patter != null && "batch".equals(patter))
			{

				System.out.println("批量更新");
				data = "[" + data + "]";
				JSONArray jsons = new JSONArray(data);

				for (int i = 0; i < jsons.length(); i++)
				{
					String ly_id = jsons.getJSONObject(i).getString(LibraryYearTable.LY_ID);
					String ly_comments = jsons.getJSONObject(i).getString(LibraryYearTable.LY_COMMENTS);
					Map<String, String> params = new HashMap<String, String>();
					params.put(LibraryYearTable.LY_ID, ly_id);
					params.put(LibraryYearTable.LY_COMMENTS, ly_comments);

					LibraryYearDao lyd = new LibraryYearDaoImpl();
					int result = lyd.alterLibraryYear(params, ly_id + "");

					out.print(result);
				}
			} else
			{
				JSONObject json = new JSONObject(data);

				String ly_id = json.getString(LibraryYearTable.LY_ID);
				String ly_number = json.getString(LibraryYearTable.LY_NUMBER);
				String ly_seatnumber = json.getString(LibraryYearTable.LY_SEATNUMBER);
				String ly_paperbooknumber = json.getString(LibraryYearTable.LY_PAPERBOOKNUMBER);
				String ly_paperjournalnumber = json.getString(LibraryYearTable.LY_PAPERJOURNALNUMBER);
				String ly_paperjournaltype = json.getString(LibraryYearTable.LY_PAPERJOURNALTYPE);
				String ly_ebooknumber = json.getString(LibraryYearTable.LY_EBOOKNUMBER);
				String ly_ebookchnnumber = json.getString(LibraryYearTable.LY_EBOOKCHNNUMBER);
				String ly_ebookforeignnumber = json.getString(LibraryYearTable.LY_EBOOKFOREIGNNUMBER);
				String ly_ejournaltype = json.getString(LibraryYearTable.LY_EJOURNALTYPE);
				String ly_databasenumber = json.getString(LibraryYearTable.LY_DATABASENUMBER);
				String ly_comments = json.getString(LibraryYearTable.LY_COMMENTS);
				String isnull = "0";

				if ("".equals(ly_number) || "".equals(ly_seatnumber) || "".equals(ly_paperbooknumber)
						|| "".equals(ly_paperjournaltype) || "".equals(ly_ebooknumber) || "".equals(ly_ebookchnnumber)
						|| "".equals(ly_ebookforeignnumber) || "".equals(ly_ejournaltype)
						|| "".equals(ly_databasenumber))
				{
					isnull = "1";
				}
				if (ly_number == null || "".equals(ly_number))
					ly_number = "-9";
				if (ly_seatnumber == null || "".equals(ly_seatnumber))
					ly_seatnumber = "-9";
				if (ly_paperbooknumber == null || "".equals(ly_paperbooknumber))
					ly_paperbooknumber = "-9";
				if (ly_paperjournalnumber == null || "".equals(ly_paperjournalnumber))
					ly_paperjournalnumber = "-9";
				if (ly_paperjournaltype == null || "".equals(ly_paperjournaltype))
					ly_paperjournaltype = "-9";
				if (ly_ebooknumber == null || "".equals(ly_ebooknumber))
					ly_ebooknumber = "-9";
				if (ly_ebookchnnumber == null || "".equals(ly_ebookchnnumber))
					ly_ebookchnnumber = "-9";
				if (ly_ebookforeignnumber == null || "".equals(ly_ebookforeignnumber))
					ly_ebookforeignnumber = "-9";
				if (ly_ejournaltype == null || "".equals(ly_ejournaltype))
					ly_ejournaltype = "-9";
				if (ly_databasenumber == null || "".equals(ly_databasenumber))
					ly_databasenumber = "-9";
				Map<String, String> params = new HashMap<String, String>();
				params.put(LibraryYearTable.LY_ID, ly_id);
				params.put(LibraryYearTable.LY_NUMBER, ly_number);
				params.put(LibraryYearTable.LY_SEATNUMBER, ly_seatnumber);
				params.put(LibraryYearTable.LY_PAPERBOOKNUMBER, ly_paperbooknumber);
				params.put(LibraryYearTable.LY_PAPERJOURNALNUMBER, ly_paperjournalnumber);
				params.put(LibraryYearTable.LY_PAPERJOURNALTYPE, ly_paperjournaltype);
				params.put(LibraryYearTable.LY_EBOOKNUMBER, ly_ebooknumber);
				params.put(LibraryYearTable.LY_EBOOKCHNNUMBER, ly_ebookchnnumber);
				params.put(LibraryYearTable.LY_EBOOKFOREIGNNUMBER, ly_ebookforeignnumber);
				params.put(LibraryYearTable.LY_EJOURNALTYPE, ly_ejournaltype);
				params.put(LibraryYearTable.LY_DATABASENUMBER, ly_databasenumber);
				params.put(LibraryYearTable.LY_COMMENTS, ly_comments);
				params.put(LibraryYearTable.ISNULL, isnull);
				LibraryYearDao lyDao = new LibraryYearDaoImpl();
				int result = lyDao.alterLibraryYear(params, ly_id);

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
