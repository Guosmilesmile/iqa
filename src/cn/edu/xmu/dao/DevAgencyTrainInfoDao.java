package cn.edu.xmu.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.DevAgencyTrainInfo;

/**
 * 附表3-5-1-1教师教学发展机构培训情况（学年）
 * @author yue
 *
 */
public interface DevAgencyTrainInfoDao extends BaseDao<DevAgencyTrainInfo>{
	//添加记录
	public int addDevAgencyTrainInfoRecord(DevAgencyTrainInfo dati);
	//批量删除
	boolean batchDelete(String[] datiids) throws SQLException;
	//修改
	public int alterDevAgencyTrainInfo(Map<String, String> valueMap, String id);
	//总数量 
	public int getDevAgencyTrainInfoCount(Map queryParams);
	//获得
	public List<DevAgencyTrainInfo> getDevAgencyTrainInfo(int start, int end,
			String sortStr, String orderStr,Map<String, String> params
			,Date deadline);
	void deleteByCollegeandDeadline(String college, Date deadline)
			throws SQLException;

	List<DevAgencyTrainInfo> getAllDevAgencyTrainInfo();
	

}
