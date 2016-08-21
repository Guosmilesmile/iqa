package cn.edu.xmu.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.ForeignExchange;
import cn.edu.xmu.entity.ImportantStudy;
import cn.edu.xmu.entity.MajorInfo;

/**
 * 
 * @author Gy
 * 表4-1-4  重点学科  (时点)
 */
public interface ImportantStudyDao {
	   	/**
	   	 * 总数
	   	 * @return
	   	 */
		public int getImportantStudyCount();
		
		/**
		 * 
		 * @param queryParams
		 * @return 
		 * 		根据查询条件返回符合条件的条数
		 */
		public int getImportantStudyCountCount(Map queryParams);
		
		/**
		 * 重点学科名不为空
		 * @param queryParams
		 * @return
		 */
		public int getImportantStudyCountByName(Map queryParams);
		
		
		//获得全部专业信息
		public List<ImportantStudy> getAllImportantStudy(int start, int end,
				String sortStr, String orderStr,Map queryParams);
		
		
		/**
		 * @return
		 */
		public int addImportantStudy(ImportantStudy importantStudy);
		
		//修改
		public int alterImportantStudy(Map<String, String> valueMap, String id);
		
		//批量删除
		boolean batchDelete(String[] miids) throws SQLException;

		public List<ImportantStudy> getAllImportantStudy();


		void deleteByCollegeandDeadline(String college, Date deadline)
				throws SQLException;

}
