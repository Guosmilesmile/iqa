package cn.edu.xmu.dao;

import java.util.List;

import cn.edu.xmu.entity.RoleTablePowerOne;

public interface RoleTablePowerOneDao extends BaseDao<RoleTablePowerOne>{
	public List<RoleTablePowerOne> getRoleFills();
}
