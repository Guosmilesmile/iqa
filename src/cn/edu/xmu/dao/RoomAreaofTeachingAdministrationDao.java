package cn.edu.xmu.dao;


import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.RoomAreaofTeachingAdministration;


/**
 * 
 * @author zhantu
 * 各单位教学行政用房面积  实体类功能接口
 * date 2015-07-07
 */
public interface RoomAreaofTeachingAdministrationDao {

	
	/**
	 * 插入数据
	 * 
	 * @param rata
	 * 		各单位教学行政用房面积实体
	 * @return 
	 * 		插入数据结果成功与否
	 */
	public int addRecord(RoomAreaofTeachingAdministration rata);
	
	
	/**
	 * 
	 * @param rataids
	 * @return
	 * @throws SQLException
	 */
	boolean batchDelete(String[] rataids) throws SQLException;
	
	/**
	 * 
	 * @param valueMap
	 * @param id
	 * @return
	 */
	public int alterRoomAreaofTeachingAdministration(Map<String, String> valueMap, String id);
	
	/**
	 * 
	 * @param queryParams
	 * @return 
	 * 		根据查询条件返回符合条件的条数
	 */
	public int getRoomAreaofTeachingAdministrationCount(Map queryParams);
	
	
	/**
	 * 
	 * @param start  开始的标记
	 * @param end    结束的标记
	 * @param sortStr 排序的字段
	 * @param orderStr 升降选项
	 * @param queryParams 查询参数
	 * @return
	 */
	 public List<RoomAreaofTeachingAdministration> getAllRoomAreaofTeachingAdministration(int start, int end,
				String sortStr, String orderStr, Map queryParams);
	 
	 public List<RoomAreaofTeachingAdministration> getRoomAreaofTeachingAdministration(int start, int end,
				String sortStr, String orderStr, Map<String, String> params,
				Date deadline);
	 
	//flag = true取总计 否则 取不是总计的
	 public List<RoomAreaofTeachingAdministration> getRATASumorNoSum(String college, boolean flag);


	void deleteByCollegeandDeadline(String college, Date deadline)
			throws SQLException;


	List<RoomAreaofTeachingAdministration> getAllRoomAreaofTeachingAdministration();
}
