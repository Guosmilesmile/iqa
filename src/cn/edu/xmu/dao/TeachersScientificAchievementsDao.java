package cn.edu.xmu.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.TeachersScientificAchievements;


/**
 * 3-6-3  教师最近一届科研成果奖数
 * @author chunfeng
 *
 */
public interface TeachersScientificAchievementsDao {
	public List<TeachersScientificAchievements> getTeachersScientificAchievements(int start, int end,
			String sortStr, String orderStr,Map<String, String> params,Date deadline);
	
	public int getTeachersScientificAchievementsCount(Map queryParams);
	
	boolean batchDelete(String[] tsaids) throws SQLException;
	
	public int addTeachersScientificAchievements(TeachersScientificAchievements teachersScientificAchievements);
	
	public int alterTeachersScientificAchievements(Map<String, String> valueMap, String id);
	
	public List<TeachersScientificAchievements> getAllTeachersScientificAchievements();
	
	public void deleteAll();
	/**
	 * 根据填报学院和填报期限删除数据
	 * @param college 学院
	 * @param deadline 填报期限
	 * @return
	 * @throws SQLException
	 */
	public boolean deleteByCollegeandDeadline(String college, Date deadline) throws SQLException;
	//public void deleteByCollegeandDeadline(String college,Date deadline) throws SQLException;

}
