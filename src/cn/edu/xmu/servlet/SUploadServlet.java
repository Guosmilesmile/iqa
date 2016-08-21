package cn.edu.xmu.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Sheet;
import jxl.Workbook;
import cn.edu.xmu.dao.SuperMajorDao;
import cn.edu.xmu.daoimpl.SuperMajorDaoImpl;
import cn.edu.xmu.entity.SuperMajor;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

/**
 * Servlet implementation class SmartUploadServlet
 */

public class SUploadServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SUploadServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("进入SUploadServlet");
		List<SuperMajor> list=new ArrayList<SuperMajor>();
		SuperMajorDao superMajorDao = new SuperMajorDaoImpl();
		String filePath = getServletContext().getRealPath("/") + "upload";
		String completeFilePath;
		//System.out.println(filePath);
		File file = new File(filePath);
		if (!file.exists()) {
			file.mkdir();
		}
		String result = "导入成功";
		SmartUpload smartUpload = new SmartUpload();
		smartUpload.initialize(getServletConfig(), request, response);
		smartUpload.setMaxFileSize(1024 * 1024 * 10);
		smartUpload.setTotalMaxFileSize(1024 * 1024 * 100);
		smartUpload.setAllowedFilesList("txt,jpg,png,gif,doc,xlsx,xls");
		try {
			smartUpload.setDeniedFilesList("rar,jsp,html");
		} catch (SQLException e) {
			e.printStackTrace();
			result = "上传失败";
		}
		try {
			smartUpload.upload();
			int count = 0;
			count = smartUpload.save(filePath);
			com.jspsmart.upload.File myFile = smartUpload.getFiles().getFile(0);
			completeFilePath = filePath +"\\"+ myFile.getFileName();
			System.out.println(completeFilePath);
			list = getAllByExcel(completeFilePath);
			for(int i=0;i<list.size();i++)
			{
				superMajorDao.addSuperMajorRecord(list.get(i));
			}
		} catch (SmartUploadException e) {
			e.printStackTrace();
			result = "上传失败";
		}
		System.out.println(list.size());
		request.setAttribute("result", result);
		request.setAttribute("count", list.size());
		request.getRequestDispatcher("upTest/uploadtest.jsp").forward(request,
				response);
	}
	
    /**
     * 查询指定目录中电子表格中所有的数据
     * @param file 文件完整路径
     * @return
     */
    public static List<SuperMajor> getAllByExcel(String file){
        List<SuperMajor> list=new ArrayList<SuperMajor>();
        try {
            Workbook rwb=Workbook.getWorkbook(new File(file));
            Sheet rs=rwb.getSheet(0);//或者rwb.getSheet(0)
            int clos=rs.getColumns();//得到所有的列
            int rows=rs.getRows();//得到所有的行
            
            System.out.println(clos+" rows:"+rows);
            for (int i = 2; i < rows; i++) {
                for (int j = 1; j < clos; j++) {
                    //第一个是列数，第二个是行数
                    String sm_name=rs.getCell(j++, i).getContents();//默认最左边编号也算一列 所以这里得j++
                    String sm_number=rs.getCell(j++, i).getContents();
                    String sm_class=rs.getCell(j++, i).getContents();
                    String c_startyear=rs.getCell(j++, i).getContents();
                    String p_startyear=rs.getCell(j++, i).getContents();
                    String s_startyear=rs.getCell(j++, i).getContents();
                    String respon_person=rs.getCell(j++, i).getContents();
                    String college=rs.getCell(j++, i).getContents();
                    String sm_serialnumber=rs.getCell(j++, i).getContents();
                    System.out.println("sm_name:"+sm_name+" sm_number:"+sm_number+" sm_class:"+Integer.parseInt(sm_class)+" c_startyear:"+c_startyear+" p_startyear:"+p_startyear+" s_startyear:"+s_startyear+" respon_person:"+respon_person+" college:"+college+" sm_serialnumber:"+Integer.parseInt(sm_serialnumber));
                    list.add(new SuperMajor(sm_name, sm_number, sm_class, c_startyear,p_startyear,s_startyear,respon_person,college,Integer.parseInt(sm_serialnumber)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return list;
    }
    

}
