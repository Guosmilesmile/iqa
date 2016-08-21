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

import cn.edu.xmu.dao.RoomAreaofTeachingAdministrationDao;
import cn.edu.xmu.daoimpl.RoomAreaofTeachingAdministrationDaoImpl;
import cn.edu.xmu.entity.RoomAreaofTeachingAdministration;
import cn.edu.xmu.table.RoomAreaofTeachingAdministrationTable;

/**
 * Servlet implementation class Sec_UpdateRoomAreaofTeachingAdministration
 */
@WebServlet("/UpdateRoomAreaofTeachingAdministration")
public class Sec_UpdateRoomAreaofTeachingAdministration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_UpdateRoomAreaofTeachingAdministration() {
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
		String patter = request.getParameter("patter");
		//int serialnumber  = Integer.parseInt(request.getParameter("serialnumber"));
		//解码
		String college = request.getParameter(RoomAreaofTeachingAdministrationTable.RATA_COLLEGE);
		college = URLDecoder.decode(college,"UTF-8");
		
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		try {
			if(patter!=null && "batch".equals(patter))
			{
				System.out.println("批量更新");
				//审核部分更新，可以批量
				data="["+data+"]";
				JSONArray jsons = new JSONArray(data);
				
				for (int i = 0; i < jsons.length(); i++) {
					//无serialnumber,deadline,college
					String rata_id = jsons.getJSONObject(i).getString(RoomAreaofTeachingAdministrationTable.RATA_ID);
					
					String rata_comments = jsons.getJSONObject(i).getString(RoomAreaofTeachingAdministrationTable.RATA_COMMENTS);
							
					Map<String,String> params= new HashMap<String, String>();
					params.put(RoomAreaofTeachingAdministrationTable.RATA_ID, rata_id);
					params.put(RoomAreaofTeachingAdministrationTable.RATA_COMMENTS, rata_comments);
				
					RoomAreaofTeachingAdministrationDao rataDao = new RoomAreaofTeachingAdministrationDaoImpl();
					int result = rataDao.alterRoomAreaofTeachingAdministration(params, rata_id);
				
					out.print(result);
				}
			}
			else {
				JSONObject json = new JSONObject(data);
			
				String rata_id = json.getString(RoomAreaofTeachingAdministrationTable.RATA_ID);
				String rata_collegenameString = json.getString(RoomAreaofTeachingAdministrationTable.RATA_COLLEGENAME);
				String rata_teachresearchauxiliary = json.getString(RoomAreaofTeachingAdministrationTable.RATA_TEACHRESEARCHAUXILIARY);
				if(rata_teachresearchauxiliary.equals(""))
					rata_teachresearchauxiliary = "-999";
				String rata_classroom = json.getString(RoomAreaofTeachingAdministrationTable.RATA_CLASSROOM);
				if(rata_classroom.equals(""))
					rata_classroom = "-999";
				String rata_library = json.getString(RoomAreaofTeachingAdministrationTable.RATA_LIBRARY);
				if(rata_library.equals(""))
					rata_library = "-999";
				String rata_lab = json.getString(RoomAreaofTeachingAdministrationTable.RATA_LAB);
				if(rata_lab.equals(""))
					rata_lab = "-999";
				
				String rata_privatescienresearch = json.getString(RoomAreaofTeachingAdministrationTable.RATA_PRIVATESCIENRESEARCH);
				if(rata_privatescienresearch.equals(""))
					rata_privatescienresearch = "-999";
				String rata_gym = json.getString(RoomAreaofTeachingAdministrationTable.RATA_GYM);
				if(rata_gym.equals(""))
					rata_gym = "-999";
				String rata_hall = json.getString(RoomAreaofTeachingAdministrationTable.RATA_HALL);
				if(rata_hall.equals(""))
					rata_hall = "-999";
				String rata_administrationoffice = json.getString(RoomAreaofTeachingAdministrationTable.RATA_ADMINISTRATIONOFFICE);
				if(rata_administrationoffice.equals(""))
					rata_administrationoffice = "-999";
				
				float rata_sum = -999;
				if(!rata_teachresearchauxiliary.equals("-999") && !rata_classroom.equals("-999") && !rata_library.equals("-999") && !rata_lab.equals("-999") && 
						!rata_privatescienresearch.equals("-999") && !rata_gym.equals("-999") && !rata_hall.equals("-999") && !rata_administrationoffice.equals("-999"))
					rata_sum = Float.valueOf(rata_teachresearchauxiliary)+Float.valueOf(rata_classroom)+Float.valueOf(rata_library)+Float.valueOf(rata_lab)+
							Float.valueOf(rata_privatescienresearch)+Float.valueOf(rata_gym)+Float.valueOf(rata_hall)+Float.valueOf(rata_administrationoffice);
						
				//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
				int isnull = 0;
				if(rata_sum==-999 || rata_collegenameString.equals(""))
					isnull = 1;
				
				Map<String,String> params= new HashMap<String, String>();
				params.put(RoomAreaofTeachingAdministrationTable.RATA_ID, rata_id);
				params.put(RoomAreaofTeachingAdministrationTable.RATA_COLLEGENAME, rata_collegenameString);
				params.put(RoomAreaofTeachingAdministrationTable.RATA_SUM, rata_sum+"");
				params.put(RoomAreaofTeachingAdministrationTable.RATA_TEACHRESEARCHAUXILIARY, rata_teachresearchauxiliary);
				params.put(RoomAreaofTeachingAdministrationTable.RATA_CLASSROOM, rata_classroom);
				params.put(RoomAreaofTeachingAdministrationTable.RATA_LIBRARY, rata_library);
				params.put(RoomAreaofTeachingAdministrationTable.RATA_LAB, rata_lab);
				params.put(RoomAreaofTeachingAdministrationTable.RATA_PRIVATESCIENRESEARCH, rata_privatescienresearch);
				params.put(RoomAreaofTeachingAdministrationTable.RATA_GYM, rata_gym);
				params.put(RoomAreaofTeachingAdministrationTable.RATA_HALL, rata_hall);
				params.put(RoomAreaofTeachingAdministrationTable.RATA_ADMINISTRATIONOFFICE, rata_administrationoffice);
				params.put(RoomAreaofTeachingAdministrationTable.ISNULL, isnull+"");
			
				RoomAreaofTeachingAdministrationDao rataDao = new RoomAreaofTeachingAdministrationDaoImpl();
				int result = rataDao.alterRoomAreaofTeachingAdministration(params, rata_id);
			
				out.print(result);
				
				//更新总计的记录：
				//找当前记录中college为当前用户college且deadline为空，学院名为“总计”的记录
				List<RoomAreaofTeachingAdministration> sums= rataDao.getRATASumorNoSum(college, true);
				//找当前记录中college为当前用户college且deadline为空，学院名不为“总计”的记录
				List<RoomAreaofTeachingAdministration> nosums= rataDao.getRATASumorNoSum(college, false);
				//算出总计的各个值
				float sum_rata_sum = 0,sum_rata_teachresearchauxiliary = 0,sum_rata_classroom = 0,sum_rata_library = 0,
						sum_rata_lab = 0, sum_rata_privatescienresearch = 0, sum_rata_gym = 0, sum_rata_hall = 0, 
						sum_rata_administrationoffice = 0;
				if(nosums.size()==0)
					return;//当前没有数据
				int sum_rata_serialnumber = nosums.get(0).getRata_serialnumber();//显示当前这个学院数据的最上面
				for(RoomAreaofTeachingAdministration roomAreaofTeachingAdministration:nosums)
				{
					if(roomAreaofTeachingAdministration.getRata_sum()!=null)
						sum_rata_sum += roomAreaofTeachingAdministration.getRata_sum();
					if(roomAreaofTeachingAdministration.getRata_teachresearchauxiliary()!=null)
						sum_rata_teachresearchauxiliary += roomAreaofTeachingAdministration.getRata_teachresearchauxiliary();
					if(roomAreaofTeachingAdministration.getRata_classroom()!=null)
						sum_rata_classroom += roomAreaofTeachingAdministration.getRata_classroom();
					if(roomAreaofTeachingAdministration.getRata_library()!=null)
						sum_rata_library += roomAreaofTeachingAdministration.getRata_library();
					if(roomAreaofTeachingAdministration.getRata_lab()!=null)
						sum_rata_lab += roomAreaofTeachingAdministration.getRata_lab();
					if(roomAreaofTeachingAdministration.getRata_privatescienresearch()!=null)
						sum_rata_privatescienresearch += roomAreaofTeachingAdministration.getRata_privatescienresearch();
					if(roomAreaofTeachingAdministration.getRata_gym()!=null)
						sum_rata_gym += roomAreaofTeachingAdministration.getRata_gym();
					if(roomAreaofTeachingAdministration.getRata_hall()!=null)
						sum_rata_hall += roomAreaofTeachingAdministration.getRata_hall();
					if(roomAreaofTeachingAdministration.getRata_administrationoffice()!=null)
						sum_rata_administrationoffice += roomAreaofTeachingAdministration.getRata_administrationoffice();
					if(sum_rata_serialnumber>roomAreaofTeachingAdministration.getRata_serialnumber())
						sum_rata_serialnumber = roomAreaofTeachingAdministration.getRata_serialnumber();
				}
				sum_rata_serialnumber--;
				//记录当中还没有总计的记录需要新建一条学院叫总计的记录
				if(sums.size()==0)
				{
					RoomAreaofTeachingAdministration sumRoomAreaofTeachingAdministration = new RoomAreaofTeachingAdministration("总计", sum_rata_sum, sum_rata_teachresearchauxiliary,
							sum_rata_classroom, sum_rata_library, sum_rata_lab, sum_rata_privatescienresearch, sum_rata_gym, sum_rata_hall,
							sum_rata_administrationoffice, sum_rata_serialnumber, college, "", 0);
					rataDao.addRecord(sumRoomAreaofTeachingAdministration);
				}
				//记录当中已经存在一条学院叫总计的记录，对他做更新操作
				else
				{
					int id = sums.get(0).getRata_id();
					Map<String,String> params_sum= new HashMap<String, String>();
					params_sum.put(RoomAreaofTeachingAdministrationTable.RATA_ID, Integer.toString(id));
					params_sum.put(RoomAreaofTeachingAdministrationTable.RATA_COLLEGENAME, "总计");
					params_sum.put(RoomAreaofTeachingAdministrationTable.RATA_SUM, sum_rata_sum+"");
					params_sum.put(RoomAreaofTeachingAdministrationTable.RATA_TEACHRESEARCHAUXILIARY, sum_rata_teachresearchauxiliary+"");
					params_sum.put(RoomAreaofTeachingAdministrationTable.RATA_CLASSROOM, sum_rata_classroom+"");
					params_sum.put(RoomAreaofTeachingAdministrationTable.RATA_LIBRARY, sum_rata_library+"");
					params_sum.put(RoomAreaofTeachingAdministrationTable.RATA_LAB, sum_rata_lab+"");
					params_sum.put(RoomAreaofTeachingAdministrationTable.RATA_PRIVATESCIENRESEARCH, sum_rata_privatescienresearch+"");
					params_sum.put(RoomAreaofTeachingAdministrationTable.RATA_GYM, sum_rata_gym+"");
					params_sum.put(RoomAreaofTeachingAdministrationTable.RATA_HALL, sum_rata_hall+"");
					params_sum.put(RoomAreaofTeachingAdministrationTable.RATA_ADMINISTRATIONOFFICE, sum_rata_administrationoffice+"");
					if(sums.get(0).getRata_comments()==null || sums.get(0).getRata_comments().equals(""))
						params.put(RoomAreaofTeachingAdministrationTable.RATA_COMMENTS, "");
					else
						params.put(RoomAreaofTeachingAdministrationTable.RATA_COMMENTS, sums.get(0).getRata_comments());
					params_sum.put(RoomAreaofTeachingAdministrationTable.RATA_SERIALNUMBER, sum_rata_serialnumber+"");
					params.put(RoomAreaofTeachingAdministrationTable.ISNULL, "0");
				
					result = rataDao.alterRoomAreaofTeachingAdministration(params_sum, Integer.toString(id));
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
	}

}
