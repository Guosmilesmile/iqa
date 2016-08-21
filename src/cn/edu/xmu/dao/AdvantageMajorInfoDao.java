package cn.edu.xmu.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.AdvantageMajorInfo;

/**
 * 附表4-2-3-1优势专业情况（时点）
 * @author yue
 *
 */
public interface AdvantageMajorInfoDao extends BaseDao<AdvantageMajorInfo>{
		//添加记录
		public int addAdvantageMajorInfoRecord(AdvantageMajorInfo ami);
		//批量删除
		boolean batchDelete(String[] amiids) throws SQLException;
		//修改
		public int alterAdvantageMajorInfo(Map<String, String> valueMap, String id);
		//总数量 
		public int getAdvantageMajorInfoCount(Map queryParams);
		//获得
		public List<AdvantageMajorInfo> getAdvantageMajorInfo(int start, int end,
				String sortStr, String orderStr,Map<String, String> params
				,Date deadline);
		//flag = true取总计 否则 取不是总计的
		 public List<AdvantageMajorInfo> getAMISumorNoSum(String college, boolean flag);


		void deleteByCollegeandDeadline(String college, Date deadline)
				throws SQLException;


		List<AdvantageMajorInfo> getAllAdvantageMajorInfo();
}
