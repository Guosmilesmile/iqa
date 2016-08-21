package cn.edu.xmu.dao;

import java.util.List;

import cn.edu.xmu.entity.UserFill;

public interface UserFillDao extends BaseDao<UserFill>{
	public List<UserFill> getUserFills(String userid);
	public List<UserFill> getUserExam(String userid, String college);
	public int UpdateReview(String userid,int tableid,int flag,int roleid,int reviewsituation);
	/**
	 * 修改审核状态
	 * @param userid 用户Id
	 * @param tableid 
	 * @param flag
	 * @param roleid
	 * @param reviewsituation
	 * @return
	 */
	public int UpdateReview(String userid, int tableid, int flag, List<Integer> roleid, int reviewsituation);
	List<UserFill> getTableDetail(String tid);
}
