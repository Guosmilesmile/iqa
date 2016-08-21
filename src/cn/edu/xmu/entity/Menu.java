package cn.edu.xmu.entity;

import java.util.List;

public class Menu {
	private int mb_id;
	private String mb_ch_name;
	private String mb_en_name;
	private String mb_show;
	private List<SubMenu> subMenus;

	public List<SubMenu> getSubMenus() {
		return subMenus;
	}

	public void setSubMenus(List<SubMenu> subMenus) {
		this.subMenus = subMenus;
	}

	public Menu(int mb_id, String mb_ch_name, String mb_en_name) {
		super();
		this.mb_id = mb_id;
		this.mb_ch_name = mb_ch_name;
		this.mb_en_name = mb_en_name;
	}

	public Menu(String mb_ch_name, String mb_en_name) {
		super();
		this.mb_ch_name = mb_ch_name;
		this.mb_en_name = mb_en_name;
	}

	public Menu(Integer mb_id, String mb_ch_name, String mb_en_name) {
		super();
		this.mb_id = mb_id;
		this.mb_ch_name = mb_ch_name;
		this.mb_en_name = mb_en_name;
	}

	public int getMb_id() {
		return mb_id;
	}

	public void setMb_id(int mb_id) {
		this.mb_id = mb_id;
	}

	public String getMb_ch_name() {
		return mb_ch_name;
	}

	public void setMb_ch_name(String mb_ch_name) {
		this.mb_ch_name = mb_ch_name;
	}

	public String getMb_en_name() {
		return mb_en_name;
	}

	public void setMb_en_name(String mb_en_name) {
		this.mb_en_name = mb_en_name;
	}

	public String getMb_show() {
		return mb_show;
	}

	public void setMb_show(String mb_show) {
		this.mb_show = mb_show;
	}

}
