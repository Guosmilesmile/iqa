package cn.edu.xmu.dao;

import java.util.List;

import cn.edu.xmu.entity.RolePower;

public interface RolePowerDao extends BaseDao<RolePower> {
	public List<RolePower> getRolePowers();
}
