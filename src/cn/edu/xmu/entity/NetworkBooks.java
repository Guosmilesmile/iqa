package cn.edu.xmu.entity;

/**
 * 
 * @author xiaoping 3.2.4校园网、图书情况 date 2015-8-5
 */
public class NetworkBooks
{

	private String rowTitle;
	private String mainband; // 校园网主干带宽（Mbps）
	private String outband; // 校园网出口带宽（Mbps）
	private String networkaccess; // 网络接入信息点数量（个）
	private String paperbooktotal; // 纸质图书总量（册）
	private String paperbookperstudent; // 生均纸质图书（册）
	private String ebooktotal; // 电子图书数量（种）
	private String ebookperstudent; // 生均电子图书（种）
	private String newpaperbookthatyear; // 当年新增纸质图书（册）
	private String newpaperbookthatyearperstudent; // 生均年进纸质图书（册）
	private String newebookthatyear; // 当年新增电子图书（种）
	private String bookcurrencythatyear; // 当年图书流通量（本次）
	private String paperjournaltotal; // 纸质期刊数量（份）
	private String paperjournaltype; // 纸质期刊种类数（种）
	private String ejournaltype; // 电子期刊种类（种）
	private String databasenumber; // 数据库（个）

	private String college;

	public String getRowTitle()
	{
		return rowTitle;
	}

	public void setRowTitle(String rowTitle)
	{
		this.rowTitle = rowTitle;
	}

	public String getMainband()
	{
		return mainband;
	}

	public void setMainband(String mainband)
	{
		this.mainband = mainband;
	}

	public String getOutband()
	{
		return outband;
	}

	public void setOutband(String outband)
	{
		this.outband = outband;
	}

	public String getNetworkaccess()
	{
		return networkaccess;
	}

	public void setNetworkaccess(String networkaccess)
	{
		this.networkaccess = networkaccess;
	}

	public String getPaperbooktotal()
	{
		return paperbooktotal;
	}

	public void setPaperbooktotal(String paperbooktotal)
	{
		this.paperbooktotal = paperbooktotal;
	}

	public String getPaperbookperstudent()
	{
		return paperbookperstudent;
	}

	public void setPaperbookperstudent(String paperbookperstudent)
	{
		this.paperbookperstudent = paperbookperstudent;
	}

	public String getEbooktotal()
	{
		return ebooktotal;
	}

	public void setEbooktotal(String ebooktotal)
	{
		this.ebooktotal = ebooktotal;
	}

	public String getEbookperstudent()
	{
		return ebookperstudent;
	}

	public void setEbookperstudent(String ebookperstudent)
	{
		this.ebookperstudent = ebookperstudent;
	}

	public String getNewpaperbookthatyear()
	{
		return newpaperbookthatyear;
	}

	public void setNewpaperbookthatyear(String newpaperbookthatyear)
	{
		this.newpaperbookthatyear = newpaperbookthatyear;
	}

	public String getNewpaperbookthatyearperstudent()
	{
		return newpaperbookthatyearperstudent;
	}

	public void setNewpaperbookthatyearperstudent(String newpaperbookthatyearperstudent)
	{
		this.newpaperbookthatyearperstudent = newpaperbookthatyearperstudent;
	}

	public String getNewebookthatyear()
	{
		return newebookthatyear;
	}

	public void setNewebookthatyear(String newebookthatyear)
	{
		this.newebookthatyear = newebookthatyear;
	}

	public String getBookcurrencythatyear()
	{
		return bookcurrencythatyear;
	}

	public void setBookcurrencythatyear(String bookcurrencythatyear)
	{
		this.bookcurrencythatyear = bookcurrencythatyear;
	}

	public String getPaperjournaltotal()
	{
		return paperjournaltotal;
	}

	public void setPaperjournaltotal(String paperjournaltotal)
	{
		this.paperjournaltotal = paperjournaltotal;
	}

	public String getPaperjournaltype()
	{
		return paperjournaltype;
	}

	public void setPaperjournaltype(String paperjournaltype)
	{
		this.paperjournaltype = paperjournaltype;
	}

	public String getEjournaltype()
	{
		return ejournaltype;
	}

	public void setEjournaltype(String ejournaltype)
	{
		this.ejournaltype = ejournaltype;
	}

	public String getDatabasenumber()
	{
		return databasenumber;
	}

	public void setDatabasenumber(String databasenumber)
	{
		this.databasenumber = databasenumber;
	}

	public String getCollege()
	{
		return college;
	}

	public void setCollege(String college)
	{
		this.college = college;
	}

	public NetworkBooks(String rowTitle, String mainband, String outband, String networkaccess, String paperbooktotal,
			String paperbookperstudent, String ebooktotal, String ebookperstudent, String newpaperbookthatyear,
			String newpaperbookthatyearperstudent, String newebookthatyear, String bookcurrencythatyear,
			String paperjournaltotal, String paperjournaltype, String ejournaltype, String databasenumber,
			String college)
	{
		super();
		this.rowTitle = rowTitle;
		this.mainband = mainband;
		this.outband = outband;
		this.networkaccess = networkaccess;
		this.paperbooktotal = paperbooktotal;
		this.paperbookperstudent = paperbookperstudent;
		this.ebooktotal = ebooktotal;
		this.ebookperstudent = ebookperstudent;
		this.newpaperbookthatyear = newpaperbookthatyear;
		this.newpaperbookthatyearperstudent = newpaperbookthatyearperstudent;
		this.newebookthatyear = newebookthatyear;
		this.bookcurrencythatyear = bookcurrencythatyear;
		this.paperjournaltotal = paperjournaltotal;
		this.paperjournaltype = paperjournaltype;
		this.ejournaltype = ejournaltype;
		this.databasenumber = databasenumber;
		this.college = college;
	}

	public NetworkBooks()
	{
		this.rowTitle = "";
		this.mainband = "";
		this.outband = "";
		this.networkaccess = "";
		this.paperbooktotal = "";
		this.paperbookperstudent = "";
		this.ebooktotal = "";
		this.ebookperstudent = "";
		this.newpaperbookthatyear = "";
		this.newpaperbookthatyearperstudent = "";
		this.newebookthatyear = "";
		this.bookcurrencythatyear = "";
		this.paperjournaltotal = "";
		this.paperjournaltype = "";
		this.ejournaltype = "";
		this.databasenumber = "";
		this.college = "";
	}

}
