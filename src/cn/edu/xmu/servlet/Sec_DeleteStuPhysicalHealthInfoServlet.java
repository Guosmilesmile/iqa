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

import cn.edu.xmu.dao.StuPhysicalHealthInfoDao;
import cn.edu.xmu.daoimpl.StuPhysicalHealthInfoDaoImpl;
import cn.edu.xmu.entity.StuPhysicalHealthInfo;
import cn.edu.xmu.table.StuPhysicalHealthInfoTable;

/**
 * 附表6-2-1-8厦门大学学生体质健康情况（学年）
 * @author yue
 *
 */
@WebServlet("/Sec_DeleteStuPhysicalHealthInfoServlet")
public class Sec_DeleteStuPhysicalHealthInfoServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_DeleteStuPhysicalHealthInfoServlet() {
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
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;Charset=UTF-8");
		PrintWriter out = response.getWriter();
		String sphi_id = request.getParameter("sphiids");
		String college = request.getParameter(StuPhysicalHealthInfoTable.SPHI_COLLEGE);
		college = URLDecoder.decode(college,"UTF-8");
		String sphiid[] = sphi_id.split(",");
		
		StuPhysicalHealthInfoDao sphiDao  = new StuPhysicalHealthInfoDaoImpl();
		boolean result = false;
		try {
			result = sphiDao.batchDelete(sphiid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("删除纪录的结果"+result);
		
		out.print(result);
		//更新总计的记录：
				//找当前记录中college为当前用户college且deadline为空，学院名为“总计”的记录
				List<StuPhysicalHealthInfo> sums= sphiDao.getSPHISumorNoSum(college, true);
				//找当前记录中college为当前用户college且deadline为空，学院名不为“总计”的记录
				List<StuPhysicalHealthInfo> nosums= sphiDao.getSPHISumorNoSum(college, false);
				
				//表为空,返回
				if(nosums.size() == 0)
					return;
				//算出总计的各个值
				int sum_sphi_totalnumber = 0,sum_sphi_freetestnumber = 0,sum_sphi_testnumber = 0,sum_sphi_passnumber = 0,
						sum_sphi_goodnumber = 0, sum_sphi_excellentnumber = 0;
				int sum_sphi_serialnumber = nosums.get(0).getSphi_serialnumber();//显示当前这个学院数据的最上面
				for(StuPhysicalHealthInfo sphi1:nosums)
				{
					
					if(sphi1.getSphi_totalnumber() != null && !"".equals(sphi1.getSphi_totalnumber()))
					{
						if(sphi1.getSphi_totalnumber()>0)
							sum_sphi_totalnumber += sphi1.getSphi_totalnumber();
					}
					if(sphi1.getSphi_freetestnumber() != null && !"".equals(sphi1.getSphi_freetestnumber()))
					{
						if(sphi1.getSphi_freetestnumber()>0)
							sum_sphi_freetestnumber += sphi1.getSphi_freetestnumber();
					}
					if(sphi1.getSphi_testnumber() != null && !"".equals(sphi1.getSphi_testnumber()))
					{
						if(sphi1.getSphi_testnumber()>0)
							sum_sphi_testnumber += sphi1.getSphi_testnumber();
					}
					if(sphi1.getSphi_passnumber() != null && !"".equals(sphi1.getSphi_passnumber()))
					{
						if(sphi1.getSphi_passnumber()>0)
							sum_sphi_passnumber += sphi1.getSphi_passnumber();
					}
					if(sphi1.getSphi_goodnumber() != null && !"".equals(sphi1.getSphi_goodnumber()))
					{
						if(sphi1.getSphi_goodnumber()>0)
							sum_sphi_goodnumber += sphi1.getSphi_goodnumber();
					}
					if(sphi1.getSphi_excellentnumber() != null && !"".equals(sphi1.getSphi_excellentnumber()))
					{
						if(sphi1.getSphi_excellentnumber()>0)
							sum_sphi_excellentnumber += sphi1.getSphi_excellentnumber();
					}
					if(sum_sphi_serialnumber>sphi1.getSphi_serialnumber())
						sum_sphi_serialnumber = sphi1.getSphi_serialnumber();
				}
				sum_sphi_serialnumber--;
				//记录当中还没有总计的记录需要新建一条学院叫总计的记录
				if(sums.size()==0)
				{
					StuPhysicalHealthInfo sphi1 = new StuPhysicalHealthInfo("合计", sum_sphi_totalnumber, 
							sum_sphi_freetestnumber, sum_sphi_testnumber, sum_sphi_passnumber, sum_sphi_goodnumber,
							sum_sphi_excellentnumber, sum_sphi_serialnumber, college, "",0);
					sphiDao.addStuPhysicalHealthInfoRecord(sphi1);
				}
				//记录当中已经存在一条学院叫总计的记录，对他做更新操作
				else
				{
					int id = sums.get(0).getSphi_id();
					Map<String,String> params= new HashMap<String, String>();
					params.put(StuPhysicalHealthInfoTable.SPHI_ID, Integer.toString(id));
					params.put(StuPhysicalHealthInfoTable.SPHI_GRADE, "合计");
					params.put(StuPhysicalHealthInfoTable.SPHI_TOTALNUMBER, sum_sphi_totalnumber+"");
					params.put(StuPhysicalHealthInfoTable.SPHI_FREETESTNUMBER, sum_sphi_freetestnumber+"");
					params.put(StuPhysicalHealthInfoTable.SPHI_TESTNUMBER, sum_sphi_testnumber+"");
					params.put(StuPhysicalHealthInfoTable.SPHI_PASSNUMBER, sum_sphi_passnumber+"");
					params.put(StuPhysicalHealthInfoTable.SPHI_GOODNUMBER, sum_sphi_goodnumber+"");
					params.put(StuPhysicalHealthInfoTable.SPHI_EXCELLENTNUMBER, sum_sphi_excellentnumber+"");
					if(sums.get(0).getSphi_comments()==null || sums.get(0).getSphi_comments().equals(""))
						params.put(StuPhysicalHealthInfoTable.SPHI_COMMENTS, "");
					else
						params.put(StuPhysicalHealthInfoTable.SPHI_COMMENTS, sums.get(0).getSphi_comments());
				
					int result_sum = sphiDao.alterStuPhysicalHealthInfoInfo(params, Integer.toString(id));
				}
	}
}
