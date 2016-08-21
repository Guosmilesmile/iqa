package cn.edu.xmu.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.ForeignStudentInfo;

/**
 * 表6-1-4  国外及港澳台学生情况（时点）
 * @author yue
 *
 */
public interface ForeignStudentInfoDao extends BaseDao<ForeignStudentInfo>{
			
	
		//添加记录
		public int addForeignStudentInfoRecord(ForeignStudentInfo fsi);
		//批量删除
		boolean batchDelete(String[] fsiids) throws SQLException;
		//修改
		public int alterForeignStudentInfo(Map<String, String> valueMap, String id);
		//总数量 
		public int getForeignStudentInfoCount(Map queryParams);
		//获得
		public List<ForeignStudentInfo> getForeignStudentInfo(int start, int end,
				String sortStr, String orderStr,Map<String, String> params
				,Date deadline);
		
		void deleteByCollegeandDeadline(String college, Date deadline)
				throws SQLException;
		List<ForeignStudentInfo> getAllForeignStudentInfo();
	
}
