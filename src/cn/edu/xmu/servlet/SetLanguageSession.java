package cn.edu.xmu.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SetLanguageSession
 */
@WebServlet("/SetLanguageSession")
public class SetLanguageSession extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SetLanguageSession() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String type = request.getParameter("type");
		if ("chinese".equals(type)) {
			request.getSession().setAttribute("language", "zh_CN");
			System.out.println(type + "==========");
		}
		if ("english".equals(type)) {
			request.getSession().setAttribute("language", "en_US");
			System.out.println(type + "==========");
		}

	}

}
