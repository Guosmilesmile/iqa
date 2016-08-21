package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import cn.edu.xmu.dao.FixedAssetsDao;
import cn.edu.xmu.daoimpl.FixedAssetsDaoImpl;
import cn.edu.xmu.entity.FixedAssets;
import cn.edu.xmu.table.FixedAssetsTable;

/**
 * Servlet implementation class Sec_AddFixedAssetsServlet
 */
@WebServlet("/Sec_AddFixedAssetsServlet")
public class Sec_AddFixedAssetsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_AddFixedAssetsServlet() {
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
		int serialnumber  = Integer.parseInt(request.getParameter("serialnumber"));
		//解码
		String college = request.getParameter(FixedAssetsTable.FA_COLLEGE);
		college = URLDecoder.decode(college,"UTF-8");
		
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		try {
			JSONObject json = new JSONObject(data);
			
			String fa_importcollege = json.getString(FixedAssetsTable.FA_IMPORTCOLLEGE);
			String fixedassetssum = json.getString(FixedAssetsTable.FA_FIXEDASSETSSUM);
			float fa_fixedassetssum = -999;
			if (fixedassetssum!=null && !"".equals(fixedassetssum)) {
				fa_fixedassetssum = Float.parseFloat(fixedassetssum);
			}
			String equipmentassetssum = json.getString(FixedAssetsTable.FA_EQUIPMENTASSETSSUM);
			float fa_equipmentassetssum = -999;
			if (equipmentassetssum!=null && !"".equals(equipmentassetssum)) {
				fa_equipmentassetssum = Float.parseFloat(equipmentassetssum);
			}
			String newassets = json.getString(FixedAssetsTable.FA_NEWASSETS);
			float fa_newassets = -999;
			if (newassets!=null && !"".equals(newassets)) {
				fa_newassets = Float.parseFloat(newassets);
			}
			
			//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
			int isnull = 0;
			if(fa_importcollege.equals("") || fixedassetssum.equals("") || equipmentassetssum.equals("") || 
					newassets.equals("")  )
				isnull = 1;
			
			int fa_serialnumber = serialnumber;
			String fa_college = college;
			String fa_comments = "";
			
			if(fa_importcollege.equals("") && fixedassetssum.equals("") && equipmentassetssum.equals("") && 
					newassets.equals("")  )
				return;
			FixedAssets fa = new FixedAssets(fa_importcollege,
					fa_fixedassetssum, fa_equipmentassetssum, fa_newassets,fa_serialnumber,fa_college,fa_comments,isnull);			
			FixedAssetsDao faDao = new FixedAssetsDaoImpl();
			faDao.addRecord(fa);
			out.print(true);
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
	}

}
