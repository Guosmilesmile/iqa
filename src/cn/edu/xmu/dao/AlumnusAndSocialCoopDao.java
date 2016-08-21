package cn.edu.xmu.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.AlumnusAndSocialCoop;

public interface AlumnusAndSocialCoopDao extends BaseDao<AlumnusAndSocialCoop>{
			
	
	
			//添加记录
			public int addAlumnusAndSocialCoopRecord(AlumnusAndSocialCoop as);
			//批量删除
			boolean batchDelete(String[] asids) throws SQLException;
			//修改
			public int alterAlumnusAndSocialCoop(Map<String, String> valueMap, String id);
			//总数量 
			public int getAlumnusAndSocialCoopCount(Map queryParams);
			//获得
			public List<AlumnusAndSocialCoop> getAlumnusAndSocialCoop(int start, int end,
					String sortStr, String orderStr,Map<String, String> params
					,Date deadline);
			
			void deleteByCollegeandDeadline(String college, Date deadline)
					throws SQLException;


			List<AlumnusAndSocialCoop> getAllAlumnusAndSocialCoop();
		
		
	

}
