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

import cn.edu.xmu.dao.AdvantageMajorInfoDao;
import cn.edu.xmu.daoimpl.AdvantageMajorInfoDaoImpl;
import cn.edu.xmu.entity.AdvantageMajorInfo;
import cn.edu.xmu.table.AdvantageMajorInfoTable;
import cn.edu.xmu.table.RoomAreaofTeachingAdministrationTable;

/**
 * 附表4-2-3-1优势专业情况（时点）
 * @author yue
 *
 */
@WebServlet("/Sec_DeleteAdvantageMajorInfoServlet")
public class Sec_DeleteAdvantageMajorInfoServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_DeleteAdvantageMajorInfoServlet() {
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
		String ami_id = request.getParameter("amiids");
		String college = request.getParameter(AdvantageMajorInfoTable.AMI_COLLEGE);
		college = URLDecoder.decode(college,"UTF-8");
		String amiid[] = ami_id.split(",");
		
		AdvantageMajorInfoDao amiDao = new AdvantageMajorInfoDaoImpl();
		boolean result = false;
		try {
			result = amiDao.batchDelete(amiid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("删除纪录的结果"+result);
		
		out.print(result);
		
		//更新总计的记录：
		//找当前记录中college为当前用户college且deadline为空，学院名为“总计”的记录
		List<AdvantageMajorInfo> sums= amiDao.getAMISumorNoSum(college, true);
		//找当前记录中college为当前用户college且deadline为空，学院名不为“总计”的记录
		List<AdvantageMajorInfo> nosums= amiDao.getAMISumorNoSum(college, false);
		if(nosums.size()==0)
			return;
		//算出总计的各个值
		int sum_ami_nationallevel = 0,sum_ami_provinciallevel = 0;
		int sum_ami_serialnumber = nosums.get(0).getAmi_serialnumber();//显示当前这个学院数据的最上面
		for(AdvantageMajorInfo ami1:nosums)
		{
			
			if(ami1.getAmi_nationallevel() != null)
			{
				if(ami1.getAmi_nationallevel()>0)
				{
					sum_ami_nationallevel++;
				}
			}
			if(ami1.getAmi_provinciallevel() != null)
			{
				if(ami1.getAmi_provinciallevel()>0)
				{
					sum_ami_provinciallevel++;
				}
			}
			if(sum_ami_serialnumber>ami1.getAmi_serialnumber())
				sum_ami_serialnumber = ami1.getAmi_serialnumber();
		}
		sum_ami_serialnumber--;
		//记录当中还没有总计的记录需要新建一条学院叫总计的记录
		if(sums.size()==0)
		{
			AdvantageMajorInfo ami1 = new AdvantageMajorInfo( "全校", " ", " ", sum_ami_nationallevel, sum_ami_provinciallevel, " ", sum_ami_serialnumber, college, "",0);
			amiDao.addAdvantageMajorInfoRecord(ami1);
		}
		//记录当中已经存在一条学院叫总计的记录，对他做更新操作
		else
		{
			int id = sums.get(0).getAmi_id();
			Map<String,String> params= new HashMap<String, String>();
			params.put(AdvantageMajorInfoTable.AMI_ID, Integer.toString(id));
			params.put(AdvantageMajorInfoTable.AMI_INSTITUTE, "全校");
			params.put(AdvantageMajorInfoTable.AMI_NATIONALLEVEL, sum_ami_nationallevel+"");
			params.put(AdvantageMajorInfoTable.AMI_PROVINCIALLEVEL, sum_ami_provinciallevel+"");
			params.put(AdvantageMajorInfoTable.AMI_SERIALNUMBER, sum_ami_serialnumber+"");
			if(sums.get(0).getAmi_comments()==null || sums.get(0).getAmi_comments().equals(""))
				params.put(AdvantageMajorInfoTable.AMI_COMMETNS, "");
			else
				params.put(AdvantageMajorInfoTable.AMI_COMMETNS, sums.get(0).getAmi_comments());
		
			int result_sum = amiDao.alterAdvantageMajorInfo(params, Integer.toString(id));
		}
	}
}
