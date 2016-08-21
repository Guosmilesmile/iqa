package cn.edu.xmu.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.TeachScientific;

public interface TeachScientificDao extends BaseDao<TeachScientific> {
	

		/**
		 * 获取所有的单位
		 * @param params
		 * @return
		 */
		public List<String> getAllUnits(Map params);
		
	    //单位总数量 
		public int getTeachScientificCount(Map queryParams);
		
		//获得全部单位
		public List<TeachScientific> getAllTeachScientific(int start, int end,
				String sortStr, String orderStr,Map queryParams);
		
		//添加
		public int addTeachScientific(String name,String number,String responperson,int serialnumber);
		
		//修改
		public int alterTeachScientific(Map<String, String> valueMap, String id);
		
		//批量删除
		boolean batchDelete(String[] trids) throws SQLException;
		
		//FindforPage
		public List<TeachScientific> findForPage(int start, int end,
				String sortStr, String orderStr, Map queryParams);
		
		//得到所有的单位
		public List<TeachScientific> getAllTeachScientific();
		
		//删除所有记录
		public void deleteAll();
		
		//添加一条记录
		public int addTeachScientificRecord(TeachScientific teachScientific);
		
		/**
		 * 返回所有的单位号给Sec_GetAllDepartmentNumber制作1-3+1-4+"000"的下拉框
		 * @return
		 */
		public int[] getDepartmentNumber();
		/**
		 * 根据学院和截止日期来覆盖当期的数据
		 * @param college
		 * @param deadline
		 * @throws SQLException
		 */
		public void deleteByCollegeandDeadline(String college,Date deadline) throws SQLException;
}
