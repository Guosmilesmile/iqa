package cn.edu.xmu.dao;

import java.util.List;

import cn.edu.xmu.entity.Role;

public interface RoleDao extends BaseDao<Role> {
	public int AddRole(String rolename);

	public List<Role> getallrole();

	public int deleterole(String roleid);
	
	public String GetRoleNameByRoleId(int roleid);
}
