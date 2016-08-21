package cn.edu.xmu.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.SchoolAddress;


/**
 * 
 * @author Gy
 * 校区地址  实体类功能接口
 * date 2015-06-29
 */
public interface SchoolAddressDao {

	/**
	 * 插入数据
	 * 
	 * @param sa
	 * 		校区地址实体
	 * @return 
	 * 		插入数据结果成功与否
	 */
	public int addRecord(SchoolAddress sa);
	
	
	/**
	 * 
	 * @param seuids
	 * @return
	 * @throws SQLException
	 */
	boolean batchDelete(String[] seuids) throws SQLException;
	
	/**
	 * 
	 * @param valueMap
	 * @param id
	 * @return
	 */
	public int alterSchoolAddress(Map<String, String> valueMap, String id);
	
	/**
	 * 
	 * @param queryParams
	 * @return 
	 * 		根据查询条件返回符合条件的条数
	 */
	public int getSchoolAddressCount(Map queryParams);
	
	
	/**
	 * 
	 * @param start  开始的标记
	 * @param end    结束的标记
	 * @param sortStr 排序的字段
	 * @param orderStr 升降选项
	 * @param queryParams 查询参数
	 * @return
	 */
	 public List<SchoolAddress> getAllSchoolAddress(int start, int end,
				String sortStr, String orderStr, Map queryParams);
	 
	 
	 public List<SchoolAddress> getAllSchoolAddress();


	void deleteByCollegeandDeadline(String college, Date deadline)
			throws SQLException;
	
}
