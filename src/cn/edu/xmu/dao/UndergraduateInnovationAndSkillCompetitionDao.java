package cn.edu.xmu.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.UndergraduateInnovationAndSkillCompetition;

/**
 * 附表6-2-1-2  2014-2015学年本科生参加本科生创新活动、技能竞赛情况（省部级及以上）
 * @author chunfeng
 *
 */
public interface UndergraduateInnovationAndSkillCompetitionDao extends BaseDao<UndergraduateInnovationAndSkillCompetition>{
	public List<UndergraduateInnovationAndSkillCompetition> getUndergraduateInnovationAndSkillCompetitions(int start, int end,
			String sortStr, String orderStr,Map<String, String> params,Date deadline);
	
	public int getUndergraduateInnovationAndSkillCompetitionCount(Map queryParams);
	
	boolean batchDelete(String[] uiascids) throws SQLException;
	
	public int addUndergraduateInnovationAndSkillCompetition(UndergraduateInnovationAndSkillCompetition undergraduateInnovationAndSkillCompetition);
	
	public int alterUndergraduateInnovationAndSkillCompetition(Map<String, String> valueMap, String id);
	
	public List<UndergraduateInnovationAndSkillCompetition> getAllUndergraduateInnovationAndSkillCompetitions();
	
	public void deleteAll();
	
	public void deleteByCollegeandDeadline(String college,Date deadline) throws SQLException;
	
	

}
