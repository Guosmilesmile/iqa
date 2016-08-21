package cn.edu.xmu.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.EnrollCategoryInfo;

/**
 * 表6-1-3  近一届本科生招生类别情况（时点）
 * @author yue
 *
 */
public interface EnrollCategoryInfoDao extends BaseDao<EnrollCategoryInfo> {
		//获得
		public List<EnrollCategoryInfo> getEnrollCategoryInfo(int start, int end,
					String sortStr, String orderStr,Map<String, String> params,Date deadline);
		//管理 
		public int getEnrollCategoryInfoCount(Map queryParams);
		//批量删除
		boolean batchDelete(String[] eciids) throws SQLException;
		//添加记录
		public int addEnrollCategoryInfoRecord(EnrollCategoryInfo eci);
		//修改
		public int alterEnrollCategoryInfo(Map<String, String> valueMap, String id);

		void deleteByCollegeandDeadline(String college, Date deadline)
				throws SQLException;

		List<EnrollCategoryInfo> getAllEnrollCategoryInfo();
		
}
