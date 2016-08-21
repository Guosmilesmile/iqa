package cn.edu.xmu.dao;

import java.util.List;

import cn.edu.xmu.entity.RoleFill;

public interface RoleFillDao extends BaseDao<RoleFill>{
	public List<RoleFill> getRoleFills();
}
