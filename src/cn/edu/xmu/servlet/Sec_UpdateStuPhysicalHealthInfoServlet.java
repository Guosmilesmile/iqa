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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.edu.xmu.dao.StuPhysicalHealthInfoDao;
import cn.edu.xmu.daoimpl.StuPhysicalHealthInfoDaoImpl;
import cn.edu.xmu.entity.StuPhysicalHealthInfo;
import cn.edu.xmu.table.StuPhysicalHealthInfoTable;

/**
 * 附表6-2-1-8厦门大学学生体质健康情况（学年）
 * @author yue
 *
 */
@WebServlet("/Sec_UpdateStuPhysicalHealthInfoServlet")
public class Sec_UpdateStuPhysicalHealthInfoServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_UpdateStuPhysicalHealthInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
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
		String patter = request.getParameter("patter");
		String college = request.getParameter(StuPhysicalHealthInfoTable.SPHI_COLLEGE);
		college = URLDecoder.decode(college,"UTF-8");
		
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		
		try {
			if(patter!= null && "batch".equals(patter)){
				System.out.println("批量更新");
				//审核部分更新，可以批量
				data="["+data+"]";	
				JSONArray jsons = new JSONArray(data);
				for (int i = 0; i < jsons.length(); i++) {
					Map<String,String> params= new HashMap<String, String>();
					String sphi_id = jsons.getJSONObject(i).getString(StuPhysicalHealthInfoTable.SPHI_ID);
					String sphi_comments = jsons.getJSONObject(i).getString(StuPhysicalHealthInfoTable.SPHI_COMMENTS);
					params.put(StuPhysicalHealthInfoTable.SPHI_COMMENTS, sphi_comments);
					
					StuPhysicalHealthInfoDao sphiDao = new StuPhysicalHealthInfoDaoImpl();
					int result  =sphiDao.alterStuPhysicalHealthInfoInfo(params, sphi_id);
					out.print(result);
				} 
			}
			else{
				JSONObject json = new JSONObject(data);				
				System.out.println(json);
				Map<String,String> params= new HashMap<String, String>();

				String sphi_id = json.getString(StuPhysicalHealthInfoTable.SPHI_ID);
				String sphi_grade = json.getString(StuPhysicalHealthInfoTable.SPHI_GRADE);
				String sphi_freetestnumber = json.getString(StuPhysicalHealthInfoTable.SPHI_FREETESTNUMBER);
				String sphi_testnumber = json.getString(StuPhysicalHealthInfoTable.SPHI_TESTNUMBER);
				String sphi_passnumber = json.getString(StuPhysicalHealthInfoTable.SPHI_PASSNUMBER);
				String sphi_goodnumber = json.getString(StuPhysicalHealthInfoTable.SPHI_GOODNUMBER);
				String sphi_excellentnumber = json.getString(StuPhysicalHealthInfoTable.SPHI_EXCELLENTNUMBER);
				String sphi_totalnumber = sphi_freetestnumber + sphi_testnumber;
				String sphi_comments = json.getString(StuPhysicalHealthInfoTable.SPHI_COMMENTS);
				
				//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
				int isnull = 0;
				if(sphi_grade.equals("")||sphi_freetestnumber.equals("")||sphi_testnumber.equals("")||sphi_passnumber.equals("")||
						sphi_goodnumber.equals("")||sphi_excellentnumber.equals("")||sphi_totalnumber.equals(""))

					isnull = 1;
				
				if(sphi_freetestnumber.equals(""))
					sphi_freetestnumber = "-999";
				if(sphi_testnumber.equals(""))
					sphi_testnumber = "-999";
				if(sphi_passnumber.equals(""))
					sphi_passnumber = "-999";
				if(sphi_goodnumber.equals(""))
					sphi_goodnumber = "-999";
				if(sphi_excellentnumber.equals(""))
					sphi_excellentnumber = "-999";
				if(sphi_totalnumber.equals(""))
					sphi_totalnumber = "-999";
				
				
				params.put(StuPhysicalHealthInfoTable.SPHI_GRADE, sphi_grade);
				params.put(StuPhysicalHealthInfoTable.SPHI_TOTALNUMBER, sphi_totalnumber);
				params.put(StuPhysicalHealthInfoTable.SPHI_FREETESTNUMBER, sphi_freetestnumber);
				params.put(StuPhysicalHealthInfoTable.SPHI_TESTNUMBER,sphi_testnumber);
				params.put(StuPhysicalHealthInfoTable.SPHI_PASSNUMBER, sphi_passnumber);
				params.put(StuPhysicalHealthInfoTable.SPHI_GOODNUMBER, sphi_goodnumber);
				params.put(StuPhysicalHealthInfoTable.SPHI_EXCELLENTNUMBER, sphi_excellentnumber);
				params.put(StuPhysicalHealthInfoTable.SPHI_COMMENTS, sphi_comments);
				params.put(StuPhysicalHealthInfoTable.ISNULL, isnull+"");
				
				StuPhysicalHealthInfoDao sphiDao = new StuPhysicalHealthInfoDaoImpl();
				int result  = sphiDao.alterStuPhysicalHealthInfoInfo(params, sphi_id);
				out.print(result);
				
				
				//更新总计的记录：
				//找当前记录中college为当前用户college且deadline为空，学院名为“合计”的记录
				List<StuPhysicalHealthInfo> sums= sphiDao.getSPHISumorNoSum(college, true);
				//找当前记录中college为当前用户college且deadline为空，学院名不为“合计”的记录
				List<StuPhysicalHealthInfo> nosums= sphiDao.getSPHISumorNoSum(college, false);
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
							sum_sphi_excellentnumber, sum_sphi_serialnumber,college, "",0);
					sphiDao.addStuPhysicalHealthInfoRecord(sphi1);
				}
				//记录当中已经存在一条学院叫总计的记录，对他做更新操作
				else
				{
					int id = sums.get(0).getSphi_id();
					Map<String,String> params1= new HashMap<String, String>();
					params1.put(StuPhysicalHealthInfoTable.SPHI_ID, Integer.toString(id));
					params1.put(StuPhysicalHealthInfoTable.SPHI_GRADE, "合计");
					params1.put(StuPhysicalHealthInfoTable.SPHI_TOTALNUMBER, sum_sphi_totalnumber+"");
					params1.put(StuPhysicalHealthInfoTable.SPHI_FREETESTNUMBER, sum_sphi_freetestnumber+"");
					params1.put(StuPhysicalHealthInfoTable.SPHI_TESTNUMBER, sum_sphi_testnumber+"");
					params1.put(StuPhysicalHealthInfoTable.SPHI_PASSNUMBER, sum_sphi_passnumber+"");
					params1.put(StuPhysicalHealthInfoTable.SPHI_GOODNUMBER, sum_sphi_goodnumber+"");
					params1.put(StuPhysicalHealthInfoTable.SPHI_EXCELLENTNUMBER, sum_sphi_excellentnumber+"");
					if(sums.get(0).getSphi_comments()==null || sums.get(0).getSphi_comments().equals(""))
						params1.put(StuPhysicalHealthInfoTable.SPHI_COMMENTS, "");
					else
						params1.put(StuPhysicalHealthInfoTable.SPHI_COMMENTS, sums.get(0).getSphi_comments());
				
					result = sphiDao.alterStuPhysicalHealthInfoInfo(params1, Integer.toString(id));
				}
			}
		
	}catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
	}
}
