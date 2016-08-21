package cn.edu.xmu.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.PostdoctoralMobileStation;

/**
 * 表4-1-2  博士后流动站   (时点)
 * @author yue
 *
 */
public interface PostdoctoralMobileStationDao extends BaseDao<PostdoctoralMobileStation>{

	//添加记录
	public int addPostdoctoralMobileStationRecord(PostdoctoralMobileStation pms);
	//批量删除
	boolean batchDelete(String[] pmsids) throws SQLException;
	//修改
	public int alterPostdoctoralMobileStation(Map<String, String> valueMap, String id);
	//总数量 
	public int getPostdoctoralMobileStationCount(Map queryParams);
	//总数量 ,stationname列不为空
	public int getPostdoctoralMobileStationCountByStation(Map queryParams);
	//获得
	public List<PostdoctoralMobileStation> getPostdoctoralMobileStation(int start, int end,
			String sortStr, String orderStr,Map<String, String> params
			,Date deadline);
	void deleteByCollegeandDeadline(String college, Date deadline)
			throws SQLException;

	List<PostdoctoralMobileStation> getAllPostdoctoralMobileStation();
}
