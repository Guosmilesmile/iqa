package cn.edu.xmu.entity;

import java.util.Map;

public class RoleTablePower {
	private int roleid;
	private String powers;
	public int getRoleid() {
		return roleid;
	}
	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}
	public String getPowers() {
		return powers;
	}
	public void setPowers(String powers) {
		this.powers = powers;
	}
	public RoleTablePower(int roleid, String powers) {
		super();
		this.roleid = roleid;
		this.powers = powers;
	}
	
	
	
}
