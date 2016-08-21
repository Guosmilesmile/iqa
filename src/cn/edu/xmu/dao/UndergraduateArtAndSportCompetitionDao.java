package cn.edu.xmu.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.UndergraduateArtAndSportCompetition;

/**
 * 附表6-2-1-3   2014-2015学年本科生参加文艺、体育竞赛情况（省部级及以上）
 * @author chunfeng
 *
 */
public interface UndergraduateArtAndSportCompetitionDao extends BaseDao<UndergraduateArtAndSportCompetition>{
	public List<UndergraduateArtAndSportCompetition> getUndergraduateArtAndSportCompetitions(int start, int end,
			String sortStr, String orderStr,Map<String, String> params,Date deadline);
	
	public int getUndergraduateArtAndSportCompetitionCount(Map queryParams);
	
	boolean batchDelete(String[] uaascids) throws SQLException;
	
	public int addUndergraduateArtAndSportCompetition(UndergraduateArtAndSportCompetition undergraduateArtAndSportCompetition);
	
	public int alterUndergraduateArtAndSportCompetition(Map<String, String> valueMap, String id);
	
	public List<UndergraduateArtAndSportCompetition> getAllUndergraduateArtAndSportCompetitions();
	
	public void deleteAll();
	
	public void deleteByCollegeandDeadline(String college,Date deadline) throws SQLException;

}
