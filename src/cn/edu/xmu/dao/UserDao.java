package cn.edu.xmu.dao;

import java.util.List;

import cn.edu.xmu.entity.User;


public interface UserDao extends BaseDao<User> {
	public int[] getAllPower(String userid);

	
	/**
	 * 
	 * @param userid
	 * @param password
	 * @return
	 */
	public int isLogin(String userid,String password);
	
	
	/**
	 * 
	 * @param userid
	 * @return
	 */
	public User getAllInfo(String userid);

	public int getUser();

	public List<User> getallUsers();
	
	public String getDepxiByUserid(String userid);


	int getHighestPower(String userid);
}
