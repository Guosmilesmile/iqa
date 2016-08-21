package cn.edu.xmu.dao;

/**
 * 
 * @author Daping
 * date 2015-08-13
 */
//联合表查询的获取值，集合
public interface GetValueUtilDao {

	//根据填报人角色id获取所属院系名称
	public String getDeptxiByRoleId(String roleId);
	
	
}
