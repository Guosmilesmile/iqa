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

import cn.edu.xmu.dao.SchoolNetDao;
import cn.edu.xmu.dao.StaffingSituationDao;
import cn.edu.xmu.daoimpl.SchoolNetDaoimpl;
import cn.edu.xmu.daoimpl.StaffingSituationDaoImpl;
import cn.edu.xmu.entity.SchoolNet;
import cn.edu.xmu.entity.StaffingSituation;
import cn.edu.xmu.table.SchoolNetTable;
import cn.edu.xmu.table.StaffingSituationTable;
import cn.edu.xmu.util.FastJsonTool;
import cn.edu.xmu.util.GridDataModel;

/**
 * Servlet implementation class Sec_GetStaffingSituationServlet
 */
@WebServlet("/Sec_GetStaffingSituationServlet")
public class Sec_GetStaffingSituationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_GetStaffingSituationServlet() {
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
				StaffingSituationDao ssdao = new StaffingSituationDaoImpl();
				GridDataModel<StaffingSituation> model = new GridDataModel<StaffingSituation>();

				String pageStr = request.getParameter("page");
				String rowsStr = request.getParameter("rows");
				String sortStr = request.getParameter("sort");
				String orderStr = request.getParameter("order");

				String college = request.getParameter("ss_college");
				String isnull = request.getParameter("isnull");
				
				Map queryParams = new HashMap();
				if (college != null && !"".equals(college)) {
					queryParams.put(StaffingSituationTable.SS_COLLEGE, college);
				}

				if(isnull != null && !"".equals(isnull)){
			    	queryParams.put(StaffingSituationTable.ISNULL, isnull);
			    }
				
				int total = ssdao.getStaffingSituationCount(queryParams);

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
				model.setRows(ssdao.getAllStaffingSituation(start, end, sortStr, orderStr,
						queryParams));
				System.out.println(FastJsonTool.createJsonString(model));
				writer.write(FastJsonTool.createJsonString(model));
	}

}
