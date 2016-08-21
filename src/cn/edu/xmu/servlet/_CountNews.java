package cn.edu.xmu.servlet;

import java.awt.Color;
import java.awt.List;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.edu.xmu.dao.NewsDao;
import cn.edu.xmu.daoimpl.NewsDaoImpl;
import cn.edu.xmu.enums.NewsType;
import cn.edu.xmu.table.NewsTable;
import cn.edu.xmu.util.FastJsonTool;

@WebServlet("/CountNews")
public class _CountNews extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public _CountNews() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected String toBrowserHexValue(int number) {
		StringBuilder builder = new StringBuilder(
				Integer.toHexString(number & 0xff));
		while (builder.length() < 2) {
			builder.append("0");
		}
		return builder.toString().toUpperCase();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;Charset=UTF-8");
		// PrintWriter out = response.getWriter();

		String chartType = request.getParameter("chartType");
		NewsDao newsDao = new NewsDaoImpl();
		Map<NewsType, Integer> numOfEachType = new HashMap<NewsType, Integer>();
		numOfEachType = newsDao.getNumOfEachType();
		Iterator<NewsType> iter = null;
		
		if (chartType.equals("pie")) {
			JSONArray jsonArray = new JSONArray();
			int r = 0;
			int g = 51;
			int b = 180;
			double total = 0;
			//保留两位小数
			DecimalFormat df = new DecimalFormat("#0.00");
			iter = numOfEachType.keySet().iterator();

			while (iter.hasNext()) {
			    int value = numOfEachType.get(iter.next());
			    total += value;
			}
			iter = numOfEachType.keySet().iterator();
			while (iter.hasNext()) {
				r += 30;
				g += 20;
				b += 10;
				JSONObject node = new JSONObject();
				NewsType category = iter.next();
				int value = numOfEachType.get(category);
				node.put("category", category);
				node.put("value",df.format(value/total*100));
				node.put("color", "#" + toBrowserHexValue(r)
						+ toBrowserHexValue(g) + toBrowserHexValue(b));
				jsonArray.add(node);
			}
			PrintWriter out = response.getWriter();
			out.write(jsonArray.toString());
		}
		else if(chartType.equals("chart")){
			JSONArray jsonArray = new JSONArray();
			//JSONArray series = new JSONArray();
			//JSONArray categories = new JSONArray();
			
			iter = numOfEachType.keySet().iterator();
			//ArrayList<NewsType> typeList = new ArrayList<NewsType>();
			//ArrayList<Integer> numList = new ArrayList<Integer>();
			while (iter.hasNext()) {
				NewsType category = iter.next();
				int value = numOfEachType.get(category);
				JSONObject node = new JSONObject();
				node.put("category", category);
				node.put("value",value);
				//numList.add(value);
				//typeList.add(category);
				jsonArray.add(node);
			}
			/*JSONObject numNode = new JSONObject();
			numNode.put("name", "Num Of News");
			numNode.put("data", numList);
			series.add(numNode);
			
			JSONObject typeNode = new JSONObject();
			numNode.put("categories", typeList);
			categories.add(typeNode);
			
			PrintWriter out = response.getWriter();
			out.write(series.toString());
			out.write(categories.toString());*/
			PrintWriter out = response.getWriter();
			out.write(jsonArray.toString());
		}
	}
}
