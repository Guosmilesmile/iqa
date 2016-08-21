package cn.edu.xmu.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.CombinedTrainAndOtherTeachLink;

/**
 * 附表5-3-4 2014-2015学年分专业毕业综合训练、其他教学环节安排情况
 * @author chunfeng
 *
 */
public interface CombinedTrainAndOtherTeachLinkDao extends BaseDao<CombinedTrainAndOtherTeachLink>{
	public List<CombinedTrainAndOtherTeachLink> getCombinedTrainAndOtherTeachLink(int start, int end,
			String sortStr, String orderStr,Map<String, String> params, Date deadline);
	
	public int getCombinedTrainAndOtherTeachLinkCount(Map queryParams);
	
	boolean batchDelete(String[] ctaotlids) throws SQLException;
	
	public int addCombinedTrainAndOtherTeachLink(CombinedTrainAndOtherTeachLink combinedTrainAndOtherTeachLink);
	
	public int alterCombinedTrainAndOtherTeachLink(Map<String, String> valueMap, String id);
	
	public List<CombinedTrainAndOtherTeachLink> getAllCombinedTrainAndOtherTeachLinks();
	
	public void deleteAll();
	
	public void deleteByCollegeandDeadline(String college,Date deadline) throws SQLException;
	

}
