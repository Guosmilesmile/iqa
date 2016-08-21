package cn.edu.xmu.dao;

import java.util.List;

import cn.edu.xmu.entity.RoleExamine;
import cn.edu.xmu.entity.RoleTablePowerOne;

public interface RoleExamineDao extends BaseDao<RoleExamine>{
	public List<RoleExamine> getRoleExamines();
}
