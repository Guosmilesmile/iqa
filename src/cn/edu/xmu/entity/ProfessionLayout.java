package cn.edu.xmu.entity;

/**
 * 
 * @author xiaoping 1.5 专业布局概览 date 2015-8-5
 *
 */

public class ProfessionLayout
{
	private double philosophy;// 哲学
	private double economic;// 经济学
	private double law;// 法学
	private double edu;// 教育学
	private double liter;// 文学
	private double history;// 历史学
	private double science;// 理学
	private double factory;// 工学
	private double farm;// 农学
	private double doctor;// 医学
	private double military;//军事学
	private double manage;// 管理学
	private double art;// 艺术学
	private double all;// 总计
	private String rowTitle;

	private String college;

	public double getPhilosophy()
	{
		return philosophy;
	}

	public void setPhilosophy(double philosophy)
	{
		this.philosophy = philosophy;
	}

	public double getEconomic()
	{
		return economic;
	}

	public void setEconomic(double economic)
	{
		this.economic = economic;
	}

	public double getLaw()
	{
		return law;
	}

	public void setLaw(double law)
	{
		this.law = law;
	}

	public double getEdu()
	{
		return edu;
	}

	public void setEdu(double edu)
	{
		this.edu = edu;
	}

	public double getLiter()
	{
		return liter;
	}

	public void setLiter(double liter)
	{
		this.liter = liter;
	}

	public double getHistory()
	{
		return history;
	}

	public void setHistory(double history)
	{
		this.history = history;
	}

	public double getScience()
	{
		return science;
	}

	public void setScience(double science)
	{
		this.science = science;
	}

	public double getFactory()
	{
		return factory;
	}

	public void setFactory(double factory)
	{
		this.factory = factory;
	}

	public double getFarm()
	{
		return farm;
	}

	public void setFarm(double farm)
	{
		this.farm = farm;
	}

	public double getDoctor()
	{
		return doctor;
	}

	public void setDoctor(double doctor)
	{
		this.doctor = doctor;
	}

	public double getMilitary()
	{
		return military;
	}

	public void setMilitary(double military)
	{
		this.military = military;
	}

	public double getManage()
	{
		return manage;
	}

	public void setManage(double manage)
	{
		this.manage = manage;
	}

	public double getArt()
	{
		return art;
	}

	public void setArt(double art)
	{
		this.art = art;
	}

	public double getAll()
	{
		return all;
	}

	public void setAll(double all)
	{
		this.all = all;
	}

	public String getRowTitle()
	{
		return rowTitle;
	}

	public void setRowTitle(String rowTitle)
	{
		this.rowTitle = rowTitle;
	}

	public String getCollege()
	{
		return college;
	}

	public void setCollege(String college)
	{
		this.college = college;
	}

	public ProfessionLayout(double philosophy, double economic, double law, double edu, double liter, double history,
			double science, double factory, double farm, double doctor, double military, double manage, double art,
			double all, String rowTitle, String college)
	{
		super();
		this.philosophy = philosophy;
		this.economic = economic;
		this.law = law;
		this.edu = edu;
		this.liter = liter;
		this.history = history;
		this.science = science;
		this.factory = factory;
		this.farm = farm;
		this.doctor = doctor;
		this.military = military;
		this.manage = manage;
		this.art = art;
		this.all = all;
		this.rowTitle = rowTitle;
		this.college = college;
	}
	
	public ProfessionLayout(String rowTitle, String college)
	{
		super();
		this.philosophy = 0;
		this.economic = 0;
		this.law = 0;
		this.edu = 0;
		this.liter = 0;
		this.history = 0;
		this.science = 0;
		this.factory = 0;
		this.farm = 0;
		this.doctor = 0;
		this.military = 0;
		this.manage = 0;
		this.art = 0;
		this.all = 0;
		this.rowTitle = rowTitle;
		this.college = college;
	}

}
