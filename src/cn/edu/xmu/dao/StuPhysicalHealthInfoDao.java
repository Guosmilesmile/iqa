package cn.edu.xmu.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.StuPhysicalHealthInfo;

/**
 * 附表6-2-1-8厦门大学学生体质健康情况（学年）
 * @author yue
 *
 */
public interface StuPhysicalHealthInfoDao extends BaseDao<StuPhysicalHealthInfo>{
	//添加记录
		public int addStuPhysicalHealthInfoRecord(StuPhysicalHealthInfo sphi);
		//批量删除
		boolean batchDelete(String[] sphiids) throws SQLException;
		//修改
		public int alterStuPhysicalHealthInfoInfo(Map<String, String> valueMap, String id);
		//总数量 
		public int getStuPhysicalHealthInfoCount(Map queryParams);
		//获得
		public List<StuPhysicalHealthInfo> getStuPhysicalHealthInfo(int start, int end,
				String sortStr, String orderStr,Map<String, String> params
				,Date deadline);
		 
		//flag = true取总计 否则 取不是总计的
		 public List<StuPhysicalHealthInfo> getSPHISumorNoSum(String college, boolean flag);


		void deleteByCollegeandDeadline(String college, Date deadline)
				throws SQLException;


		List<StuPhysicalHealthInfo> getAllStuPhysicalHealthInfo();
}
