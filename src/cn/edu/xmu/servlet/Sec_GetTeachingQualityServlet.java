package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.xmu.dao.TeachingQualityDao;
import cn.edu.xmu.daoimpl.TeachingQualityDaoImpl;
import cn.edu.xmu.entity.TeachingQuality;
import cn.edu.xmu.table.TeachingQualityTable;
import cn.edu.xmu.util.FastJsonTool;
import cn.edu.xmu.util.GridDataModel;

/**
 * Servlet implementation class Sec_GetTeachingQualityServlet
 */
@WebServlet("/Sec_GetTeachingQualityServlet")
public class Sec_GetTeachingQualityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_GetTeachingQualityServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	         	// 编码统一
				request.setCharacterEncoding("UTF-8");
				response.setCharacterEncoding("UTF-8");
				response.setContentType("text/html; charset=UTF-8");
				// 输入流
				PrintWriter writer = response.getWriter();
				TeachingQualityDao tqdao = new TeachingQualityDaoImpl();
				GridDataModel<TeachingQuality> model = new GridDataModel<TeachingQuality>();

				String pageStr = request.getParameter("page");
				String rowsStr = request.getParameter("rows");
				String sortStr = request.getParameter("sort");
				String orderStr = request.getParameter("order");

				String college = request.getParameter("tq_college");
				 String isnull = request.getParameter("isnull");

				Map queryParams = new HashMap();
				if (college != null && !"".equals(college)) {
					queryParams.put(TeachingQualityTable.TQ_COLLEGE, college);
				}
				if(isnull != null && !"".equals(isnull)){
			    	queryParams.put(TeachingQualityTable.ISNULL, isnull);
			    }
				int total = tqdao.getTeachingQualityCount(queryParams);

				if (null == pageStr) {
					pageStr = "1";
				}
				if (null == rowsStr) {
					rowsStr = total + "";
				}

				int page = Integer.parseInt(pageStr);
				int rows = Integer.parseInt(rowsStr);
				int start = (page - 1) * rows;

				System.out.println("当前第" + page + "页" + rows + "rows");
				int end = rows;
				end = end > total ? total : end;
				model.setTotal(total);
				model.setRows(tqdao.getAllTeachingQuality(start, end, sortStr, orderStr,
						queryParams));
				System.out.println(FastJsonTool.createJsonString(model));
				writer.write(FastJsonTool.createJsonString(model));
	}

}
