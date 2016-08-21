package cn.edu.xmu.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.StaffingSituation;

public interface StaffingSituationDao {
	/**
	 * 插入数据
	 * 
	 * @param ss
	 * 		学校概况实体
	 * @return 
	 * 		插入数据结果成功与否
	 */
	public int addRecord(StaffingSituation ss);
	
	/**
	 * 
	 * @param ssids
	 * @return
	 * @throws SQLException
	 */
	boolean batchDelete(String[] ssids) throws SQLException;
	
	/**
	 * 
	 * @param valueMap
	 * @param id
	 * @return
	 */
	public int alterStaffingSituation(Map<String, String> valueMap, String id);
	
	/**
	 * 
	 * @param queryParams
	 * @return 
	 * 		根据查询条件返回符合条件的条数
	 */
	public int getStaffingSituationCount(Map queryParams);
	
	/**
	 * 
	 * @param start  开始的标记
	 * @param end    结束的标记
	 * @param sortStr 排序的字段
	 * @param orderStr 升降选项
	 * @param queryParams 查询参数
	 * @return
	 */
	 public List<StaffingSituation> getAllStaffingSituation(int start, int end,
				String sortStr, String orderStr, Map queryParams);
	 
	 public List<StaffingSituation> getAllStaffingSituation();
		/**
		 * 根据学院和截止日期来覆盖当期的数据
		 * @param college
		 * @param deadline
		 * @throws SQLException
		 */
		public void deleteByCollegeandDeadline(String college,Date deadline) throws SQLException;
	 

}
