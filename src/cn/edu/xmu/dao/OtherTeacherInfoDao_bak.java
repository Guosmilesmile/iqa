package cn.edu.xmu.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import cn.edu.xmu.entity.OtherTeacherInfo;

/*
 * 3-1-3
 */

public interface OtherTeacherInfoDao_bak extends BaseDao<OtherTeacherInfo>{
	   //其他师资总数量 
		public int getOtherTeacherInfoCount(Map queryParams);
		
		//获得其他师资信息
		public List<OtherTeacherInfo> getCollegeOtherTeacherInfo(int start, int end,
				String sortStr, String orderStr,Map queryParams);//某个学院的
		public List<OtherTeacherInfo> getAllOtherTeacherInfo(); //所有的
		//添加
		public int addOtherTeacherInfo(Map<String, String> valueMap);
		
		//修改
		public int alterOtherTeacherInfo(Map<String, String> valueMap, String id);
		
		//批量删除
		boolean batchDelete(String[] tiids) throws SQLException;

}
