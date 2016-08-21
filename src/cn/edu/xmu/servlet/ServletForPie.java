package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.xmu.dao.DaoForPie;
import cn.edu.xmu.daoimpl.DaoForPieImpl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@WebServlet("/servletForPie")
public class ServletForPie extends HttpServlet{
	private int r[] = {0,0,132,255,255,255,255,255,0,144};
	private int g[] = {255,255,112,255,106,165,48,0,0,238};
	private int b[] = {255,127,255,0,106,0,48,255,255,144};
	
	private static final long serialVersionUID = 1L;
	
	protected String toBrowserHexValue(int number) {
		StringBuilder builder = new StringBuilder(
				Integer.toHexString(number & 0xff));
		while (builder.length() < 2) {
			builder.append("0");
		}
		return builder.toString().toUpperCase();
	}
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;Charset=UTF-8");

		String attribute = request.getParameter("attribute");
		String tableName = request.getParameter("tableName");
		String attributeX = request.getParameter("attributeX");
		String attributeY = request.getParameter("attributeY");
		String chartType = request.getParameter("chartType");
		
		String college = request.getParameter("college");
	   
	    Map queryParams = new HashMap();
	    if(college != null && !"".equals(college)){
	    	queryParams.put("college", college);
	    }else {
			queryParams = null;
		}
		
		Iterator<String> iter = null;
		DaoForPie daoForPie = new DaoForPieImpl();
		if (chartType.equals("pie")) {
			Map<String, Integer> numByAttribute = daoForPie.getNumByAttribute(tableName,attribute,queryParams);//暂时没有其他限制条件
			JSONArray jsonArray = new JSONArray();
			/*int r = 0;
			int g = 51;
			int b = 180;*/
			double total = 0;
			//保留两位小数
			DecimalFormat df = new DecimalFormat("#0.00");
			iter = numByAttribute.keySet().iterator();

			while (iter.hasNext()) {
			    int value = numByAttribute.get(iter.next());
			    total += value;
			}
			iter = numByAttribute.keySet().iterator();
			int index = 0;
			while (iter.hasNext()) {
				/*r += 30;
				g += 20;
				b += 10;*/
				index ++;
				JSONObject node = new JSONObject();
				String attributeValue = iter.next();
				int value = numByAttribute.get(attributeValue);
				
				node.put("category", attributeValue);
				node.put("value",df.format(value/total*100));
				node.put("color", "#" + toBrowserHexValue(r[index%10])
						+ toBrowserHexValue(g[index%10]) + toBrowserHexValue(b[index%10]));
				jsonArray.add(node);
			}
			PrintWriter out = response.getWriter();
			out.write(jsonArray.toString());
		}
		else if(chartType.equals("chart")){
			/*JSONArray jsonArray = new JSONArray();
			List<String> attributes = new ArrayList<String>();
			attributes.add(attributeX);
			attributes.add(attributeY);
			
			Map<String, String> attributeValues = studentService.getAttributesValue(attributes, null);//暂时没有其他限制条件
			
			iter = attributeValues.keySet().iterator();
			//ArrayList<NewsType> typeList = new ArrayList<NewsType>();
			//ArrayList<Integer> numList = new ArrayList<Integer>();
			while (iter.hasNext()) {
				String category = iter.next();
				String value = attributeValues.get(category);
				JSONObject node = new JSONObject();
				node.put("category", category);
				node.put("value",value);
				//numList.add(value);
				//typeList.add(category);
				jsonArray.add(node);
			}
			PrintWriter out = response.getWriter();
			out.write(jsonArray.toString());*/
		}
	}


}
