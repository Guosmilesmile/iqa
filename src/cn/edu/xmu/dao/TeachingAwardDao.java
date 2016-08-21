package cn.edu.xmu.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.TeachingAward;

/**
 * 7-3-2  教学成果奖
 * @author chunfeng
 *
 */

public interface TeachingAwardDao extends BaseDao<TeachingAward>{
	public List<TeachingAward> getTeachingAward(int start, int end,
			String sortStr, String orderStr,Map<String, String> params, Date deadline);
	
	public int getTeachingAwardCount(Map queryParams);
	
	/*相关管理人员相关的 zsj*/
	public int getTeachingAwardCountInManagerInfo(Map queryParams);
	
	boolean batchDelete(String[] taids) throws SQLException;
	
	public int addTeachingAward(TeachingAward teachingAward);
	
	public int alterTeachingAward(Map<String, String> valueMap, String id);
	
	public List<TeachingAward> getAllTeachingAward();
	
	public void deleteAll();
	
	public void deleteByCollegeandDeadline(String college,Date deadline) throws SQLException;

}
