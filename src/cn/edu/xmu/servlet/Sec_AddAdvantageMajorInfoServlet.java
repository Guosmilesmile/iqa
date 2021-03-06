package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import cn.edu.xmu.dao.AdvantageMajorInfoDao;
import cn.edu.xmu.daoimpl.AdvantageMajorInfoDaoImpl;
import cn.edu.xmu.entity.AdvantageMajorInfo;
import cn.edu.xmu.table.AdvantageMajorInfoTable;

/**
 * 附表4-2-3-1优势专业情况（时点）
 * @author yue
 *
 */
@WebServlet("/Sec_AddAdvantageMajorInfoServlet")
public class Sec_AddAdvantageMajorInfoServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_AddAdvantageMajorInfoServlet()
    {
    	super();
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;Charset=UTF-8");
		PrintWriter out = response.getWriter();

		String data = request.getParameter("rowdata");
		int serialnumber  = Integer.parseInt(request.getParameter("serialnumber"));
		//解码
		String college = request.getParameter(AdvantageMajorInfoTable.AMI_COLLEGE);
		college = URLDecoder.decode(college,"UTF-8");
		
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		try {
			JSONObject json = new JSONObject(data);
			
			String ami_institute = json.getString(AdvantageMajorInfoTable.AMI_INSTITUTE);
			String ami_name = json.getString(AdvantageMajorInfoTable.AMI_NAME);
			String ami_type = json.getString(AdvantageMajorInfoTable.AMI_TYPE);
			String nationallevel = json.getString(AdvantageMajorInfoTable.AMI_NATIONALLEVEL);
			Integer ami_nationallevel = -999;
			if(!nationallevel.equals(""))
				ami_nationallevel = Integer.valueOf(nationallevel);
			String provinciallevel = json.getString(AdvantageMajorInfoTable.AMI_PROVINCIALLEVEL);
			Integer ami_provinciallevel = -999;
			if(!provinciallevel.equals(""))
				ami_provinciallevel = Integer.valueOf(provinciallevel);
			String ami_remark = json.getString(AdvantageMajorInfoTable.AMI_REMARK);
			
			//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
			int isnull = 0;
			if(ami_institute.equals("")||ami_name.equals("")||ami_type.equals(""))
				isnull = 1;
			if(ami_institute.equals("")&&ami_name.equals("")&&ami_type.equals(""))
			{
				out.println(false);
				return;
			}
			int ami_serialnumber = serialnumber;
			String ami_college = college;
			String ami_comments = "";
			
			AdvantageMajorInfo ami = new AdvantageMajorInfo(ami_institute, ami_name, ami_type, ami_nationallevel,
					ami_provinciallevel, ami_remark, ami_serialnumber, ami_college, ami_comments,isnull);
			AdvantageMajorInfoDao amiDao = new AdvantageMajorInfoDaoImpl();
			amiDao.addAdvantageMajorInfoRecord(ami);
			
			//更新总计的记录：
			//找当前记录中college为当前用户college且deadline为空，学院名为“总计”的记录
			List<AdvantageMajorInfo> sums= amiDao.getAMISumorNoSum(college, true);
			//找当前记录中college为当前用户college且deadline为空，学院名不为“总计”的记录
			List<AdvantageMajorInfo> nosums= amiDao.getAMISumorNoSum(college, false);
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
			
				int result = amiDao.alterAdvantageMajorInfo(params, Integer.toString(id));
			}
			out.print(true);
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
	}

}
