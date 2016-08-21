package cn.edu.xmu.dao;

import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.RoleTablePower;

public interface RoleTablePowerDao extends BaseDao<RoleTablePower>{
	public void AddRoleTablePowers(int roleid,String watchs,String fills,String examones,String examtwos);
	
	//根据roleid和tableid获取权限
	public int getPoweridByRoleidAndTableid(int roleid,int tableid);
	
	
	//根据id获取相应的权限
	public int getPowerByUserid(String userid,String tableid);
	
	/**
	 * 根据学院获取相应的填报权限roleid
	 * @param colleges 学院数组
	 * @return roleid数组
	 */
	public List<Integer> getFillRoleIdByColleges(String[] colleges);
}
