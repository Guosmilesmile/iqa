package cn.edu.xmu.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.CourseBuildInfo;

/**
 * 附表5-2-3课程建设情况（时点）
 * @author yue
 *
 */
public interface CourseBuildInfoDao extends BaseDao<CourseBuildInfo>{
	public int addCourseBuildInfoRecord(CourseBuildInfo cbi);
	//批量删除
	boolean batchDelete(String[] cbiids) throws SQLException;
	//修改
	public int alterCourseBuildInfo(Map<String, String> valueMap, String id);
	//总数量 
	public int getCourseBuildInfoCount(Map queryParams);
	//获得
	public List<CourseBuildInfo> getCourseBuildInfo(int start, int end,
			String sortStr, String orderStr,Map<String, String> params
			,Date deadline);
	void deleteByCollegeandDeadline(String college, Date deadline)
			throws SQLException;

	List<CourseBuildInfo> getAllCourseBuildInfo();
	

}
