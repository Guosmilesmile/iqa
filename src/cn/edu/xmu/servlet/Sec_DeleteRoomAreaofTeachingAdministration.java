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

import cn.edu.xmu.dao.RoomAreaofTeachingAdministrationDao;
import cn.edu.xmu.daoimpl.RoomAreaofTeachingAdministrationDaoImpl;
import cn.edu.xmu.entity.RoomAreaofTeachingAdministration;
import cn.edu.xmu.table.RoomAreaofTeachingAdministrationTable;

/**
 * Servlet implementation class DeleteRoomAreaofTeachingAdministration
 */
@WebServlet("/DeleteRoomAreaofTeachingAdministration")
public class Sec_DeleteRoomAreaofTeachingAdministration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_DeleteRoomAreaofTeachingAdministration() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;Charset=UTF-8");
		PrintWriter out = response.getWriter();
		String rata_id = request.getParameter("rataids");
		//解码
		String college = request.getParameter(RoomAreaofTeachingAdministrationTable.RATA_COLLEGE);
		college = URLDecoder.decode(college,"UTF-8");
		String rataid[] = rata_id.split(",");
		
		RoomAreaofTeachingAdministrationDao rataDao = new RoomAreaofTeachingAdministrationDaoImpl();
		boolean result = false;
		try {
			result = rataDao.batchDelete(rataid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("删除纪录的结果"+result);
		
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
			Map<String,String> params= new HashMap<String, String>();
			params.put(RoomAreaofTeachingAdministrationTable.RATA_ID, Integer.toString(id));
			params.put(RoomAreaofTeachingAdministrationTable.RATA_COLLEGENAME, "总计");
			params.put(RoomAreaofTeachingAdministrationTable.RATA_SUM, sum_rata_sum+"");
			params.put(RoomAreaofTeachingAdministrationTable.RATA_TEACHRESEARCHAUXILIARY, sum_rata_teachresearchauxiliary+"");
			params.put(RoomAreaofTeachingAdministrationTable.RATA_CLASSROOM, sum_rata_classroom+"");
			params.put(RoomAreaofTeachingAdministrationTable.RATA_LIBRARY, sum_rata_library+"");
			params.put(RoomAreaofTeachingAdministrationTable.RATA_LAB, sum_rata_lab+"");
			params.put(RoomAreaofTeachingAdministrationTable.RATA_PRIVATESCIENRESEARCH, sum_rata_privatescienresearch+"");
			params.put(RoomAreaofTeachingAdministrationTable.RATA_GYM, sum_rata_gym+"");
			params.put(RoomAreaofTeachingAdministrationTable.RATA_HALL, sum_rata_hall+"");
			params.put(RoomAreaofTeachingAdministrationTable.RATA_ADMINISTRATIONOFFICE, sum_rata_administrationoffice+"");
			params.put(RoomAreaofTeachingAdministrationTable.RATA_SERIALNUMBER, sum_rata_serialnumber+"");
			if(sums.get(0).getRata_comments()==null || sums.get(0).getRata_comments().equals(""))
				params.put(RoomAreaofTeachingAdministrationTable.RATA_COMMENTS, "");
			else
				params.put(RoomAreaofTeachingAdministrationTable.RATA_COMMENTS, sums.get(0).getRata_comments());
			params.put(RoomAreaofTeachingAdministrationTable.ISNULL, "0");
		
			int result_sum = rataDao.alterRoomAreaofTeachingAdministration(params, Integer.toString(id));
		}
	}

}
