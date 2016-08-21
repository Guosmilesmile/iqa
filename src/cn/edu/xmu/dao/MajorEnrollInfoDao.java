package cn.edu.xmu.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.MajorEnrollInfo;
import cn.edu.xmu.entity.ManagerInfo;

/**
 * 附表6-1-6-1  各专业（大类）招生情况（时点）
 * @author yue
 *
 */
public interface MajorEnrollInfoDao extends BaseDao<MajorEnrollInfo>{
	//添加记录
	public int addMajorEnrollInfoRecord(MajorEnrollInfo mei);
	//批量删除
	boolean batchDelete(String[] meiids) throws SQLException;
	//修改
	public int alterMajorEnrollInfo(Map<String, String> valueMap, String id);
	//总数量 
	public int getMajorEnrollInfoCount(Map queryParams);
	//获得
	public List<MajorEnrollInfo> getMajorEnrollInfo(int start, int end,
			String sortStr, String orderStr,Map<String, String> params
			,Date deadline);
	void deleteByCollegeandDeadline(String college, Date deadline)
			throws SQLException;

	List<MajorEnrollInfo> getAllMajorEnrollInfo();
	
}
