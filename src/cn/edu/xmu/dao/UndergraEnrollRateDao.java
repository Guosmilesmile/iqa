package cn.edu.xmu.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.UndergraEnrollRate;

/**
 * 附表6-1-5-3本科生招生志愿满足率（时点）
 * @author yue
 *
 */
public interface UndergraEnrollRateDao extends BaseDao<UndergraEnrollRate>{

		//添加记录
		public int addUndergraEnrollRateRecord(UndergraEnrollRate uer);
		//批量删除
		boolean batchDelete(String[] uerids) throws SQLException;
		//修改
		public int alterUndergraEnrollRate(Map<String, String> valueMap, String id);
		//总数量 
		public int getUndergraEnrollRateCount(Map queryParams);
		//获得
		public List<UndergraEnrollRate> getUndergraEnrollRate(int start, int end,
				String sortStr, String orderStr,Map<String, String> params
				,Date deadline);
		void deleteByCollegeandDeadline(String college, Date deadline)
				throws SQLException;

		List<UndergraEnrollRate> getAllUndergraEnrollRate();
		

}
