package cn.edu.xmu.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.ManagerInfo;


public interface ManagerInfoDao extends BaseDao<ManagerInfo>{
	
	//获得管理人员信息
		public List<ManagerInfo> getManagerInfo(int start, int end,
				String sortStr, String orderStr,Map<String, String> params,Date deadline);
	//管理人员总数量 
	public int getManagerInfoCount(Map queryParams);
	
	//管理人员总数量 (在教学科研单位)zsj
	public int getManagerInfoCountInTeachingScientific(Map queryParams);

	//管理人员总数量 (在行政职能部门)zsj
	public int getManagerInfoCountInExecutiveUnit(Map queryParams);
	
	//批量删除
	boolean batchDelete(String[] miids) throws SQLException;
	//添加记录
	public int addManagerInfoRecord(ManagerInfo managerInfo);
	//修改管理人员信息
	public int alterManagerInfo(Map<String, String> valueMap, String id);
	/**
	 * 根据字段是date型的日期获取某一个范围内的个数
	 * @param param 字段名
	 * @param start 起始日期
	 * @param end 终止日期
	 * @return
	 */
	public int getCountByRange(String param, Date start, Date end,Map params);
	
	

	//在教学科研单位zsj
	public int getCountByRangeInTeachingScientific(String param, Date start, Date end,Map params);

	void deleteByCollegeandDeadline(String college, Date deadline)
			throws SQLException;

	List<ManagerInfo> getAllManagerInfo();

	//在行政职能部门zsj
	public int getCountByRangeInExecutiveUnit(String param, Date start, Date end,Map params);
	
	/**
	 * 根据教工号获取id不同的专任教师
	 * @param workNumber 教工号
	 * @param id 
	 * @return
	 */
	public int getCountByWorkNumber(String workNumber, String id);
	

}
