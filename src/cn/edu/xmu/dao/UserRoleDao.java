package cn.edu.xmu.dao;

import java.util.List;

import cn.edu.xmu.entity.UserRole;

public interface UserRoleDao extends BaseDao<UserRole> {
	public List<UserRole> getalluserrole();
	
	public int getRoleidByUserid(String userid);
	
	public List<Integer> getUseridsByRoleid(String roleid);
	
	public String getUseridByRoleid(String roleid);
}
