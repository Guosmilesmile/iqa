package cn.edu.xmu.dao;

/**
 * @author Sihan
 */

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.OtherTeacherInfo;
import cn.edu.xmu.service.publishNewsService;

public interface OtherTeacherInfoDao extends BaseDao<OtherTeacherInfo> {
	   	
		public int addRecord(OtherTeacherInfo oti);
		
		public boolean batchDelete(String[] otiids) throws SQLException;
		
		public int  alterOtherTeacherInfo(Map<String, String> valueMap, String id);
		
		public int  getOtherTeacherInfoCount(Map queryParams);
		
		public List<OtherTeacherInfo> getAllOtherTeacherInfo(int start, int end,
				String sortStr, String orderStr, Map queryParams); 
		
		public List<OtherTeacherInfo> getAll();
		
		//根据单位号获取教师
		public List<OtherTeacherInfo> getOtherTeacherInfoByDepartNum(String departNum);
		
		public int getOtherTeacherInfoCountByWorknumber(String worknumber, String id);
		
		/**
		 * 根据字段是date型的日期获取某一个范围内的个数
		 * @param param 字段名
		 * @param start 起始日期
		 * @param end 终止日期
		 * @return
		 */
		public int getCountByRange(String param, Date start, Date end,Map queryMap);
		
		/**
		 * 根据字段是date型的日期获取某一个范围内的个数(作为专业带头人)
		 * @param param
		 * @param start
		 * @param end
		 * @param queryParams
		 * @return
		 */
		public int getCountByRangeInMajorHeader(String param, Date start, Date end, Map queryParams,Map basicMap);
		/**
		 * 根据查询条件获取相应的作为专业带头人的其他教师的个数
		 * @param queryParams
		 * @return
		 */
		public int getOtherTeacherCountInMajorHeader(Map queryParams,Map basicMap);

		public boolean deleteByCollegeandDeadline(String college, Date deadline) throws SQLException;

		
}
