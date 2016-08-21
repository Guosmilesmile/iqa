package cn.edu.xmu.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.ForeignExchange;
import cn.edu.xmu.entity.MajorInfo;

/**
 * 
 * @author zsj
 * 4-2-2-1 专业基本情况
 */
public interface MajorInfoDao extends BaseDao<MajorInfo>{
	   	/**
	   	 * 专业总数
	   	 * @return
	   	 */
		public int getMajorInfoCount();
		
	
		/**
		 * 根据属性条件获取专业总数
		 * @param params
		 * @return
		 */
		public int getCount(Map params);
		
		//获得全部专业信息
		public List<MajorInfo> getAllMajorInfo(int start, int end,
				String sortStr, String orderStr);
		
		public List<MajorInfo> getMajorInfo(int start, int end,
				String sortStr, String orderStr,Map<String, String> params);
		
		public List<MajorInfo> getMajorInfoByYear();
		
		public MajorInfo getMajorInfoByName(String majorName);
		
		public List<MajorInfo> getNewMajor();
		/**
		 * 添加专业信息
		 * @param majorInfo
		 * @return
		 */
		public int addMajorInfo(MajorInfo majorInfo);
		
		//修改
		public int alterMajorInfo(Map<String, String> valueMap, String id);
		
		//批量删除
		boolean batchDelete(String[] miids) throws SQLException;
		
		//导入之前的删除
		public void deleteByCollegeandDeadline(String college) throws SQLException;
}
