package cn.edu.xmu.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.MajorRegisterInfo;
import cn.edu.xmu.entity.ManagerInfo;

/**
 * 表6-1-6-2 各专业（大类）报到情况（时点）
 * @author yue
 *
 */
public interface MajorRegisterInfoDao extends BaseDao<MajorRegisterInfo>{
		//添加记录
		public int addMajorRegisterInfoRecord(MajorRegisterInfo mri);
		//批量删除
		boolean batchDelete(String[] mriids) throws SQLException;
		//修改
		public int alterMajorRegisterInfo(Map<String, String> valueMap, String id);
		//总数量 
		public int getMajorRegisterInfoCount(Map queryParams);
		//获得
		public List<MajorRegisterInfo> getMajorRegisterInfo(int start, int end,
				String sortStr, String orderStr,Map<String, String> params
				,Date deadline);
		void deleteByCollegeandDeadline(String college, Date deadline)
				throws SQLException;

		List<MajorRegisterInfo> getAllMajorRegisterInfo();
		
}
