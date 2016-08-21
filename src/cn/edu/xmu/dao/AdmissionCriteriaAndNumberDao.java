package cn.edu.xmu.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.AdmissionCriteriaAndNumber;

/**
 * 附表6-1-5-4  近一届本科生录取标准及人数（时点）
 * @author yue
 *
 */
		
public interface AdmissionCriteriaAndNumberDao extends BaseDao<AdmissionCriteriaAndNumber>{

		//添加记录
		public int addAdmissionCriteriaAndNumberRecord(AdmissionCriteriaAndNumber acn);
		//批量删除
		boolean batchDelete(String[] acnids) throws SQLException;
		//修改
		public int alterAdmissionCriteriaAndNumber(Map<String, String> valueMap, String id);
		//总数量 
		public int getAdmissionCriteriaAndNumberCount(Map queryParams);
		//获得
		public List<AdmissionCriteriaAndNumber> getAdmissionCriteriaAndNumber(int start, int end,
				String sortStr, String orderStr,Map<String, String> params
				,Date deadline);
		void deleteByCollegeandDeadline(String college, Date deadline)
				throws SQLException;

		List<AdmissionCriteriaAndNumber> getAllAdmissionCriteriaAndNumber();
		
}
