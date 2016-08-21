package cn.edu.xmu.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.UndergraduateAcademicCompetition;


/**
 * 附表6-2-1-1   2014-2015学年本科生参加学科竞赛情况（省部级及以上）
 * @author chunfeng 
 *
 */
public interface UndergraduateAcademicCompetitionDao extends BaseDao<UndergraduateAcademicCompetition>{
	public List<UndergraduateAcademicCompetition> getUndergraduateAcademicCompetitions(int start, int end,
			String sortStr, String orderStr,Map<String, String> params,Date deadline);
	
	public int getUndergraduateAcademicCompetitionCount(Map queryParams);
	
	boolean batchDelete(String[] uacids) throws SQLException;
	
	public int addUndergraduateAcademicCompetition(UndergraduateAcademicCompetition undergraduateAcademicCompetition);
	
	public int alterUndergraduateAcademicCompetition(Map<String, String> valueMap, String id);
	
	public List<UndergraduateAcademicCompetition> getAllUndergraduateAcademicCompetitions();
	
	public void deleteAll();
	
	public void deleteByCollegeandDeadline(String college,Date deadline) throws SQLException;
	
	

}
