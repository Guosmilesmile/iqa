package cn.edu.xmu.entity;

/**
 * 
 * @author xiaoping 附表10 各专业实践教学情况 date 2015-8-3
 *
 */
public class ProPracticeTeach implements Comparable<ProPracticeTeach>
{
	private int serialNumber;//序号
	private String major;//专业
	//实践教学
	private double practiceCredit;//学分
	private Double practiceRate;//占总学分（%）
	//实验教学
	private double expriCredit;//学分
	private double expriRate;//占总学分（%）
	private int haveExpriCourse;//有实验的课程（门）
	private int indepenExperiCourse;//独立设置的实验课程（门）
	private double percentage;//实验开出率（%）
	private int designExperiment;//综合性、设计性实验门数
	private String college;
	private String rowTitle = "实践教学学分占总学分比例最低的5个专业：";
	
	public int getSerialNumber()
	{
		return serialNumber;
	}


	public void setSerialNumber(int serialNumber)
	{
		this.serialNumber = serialNumber;
	}


	public String getMajor()
	{
		return major;
	}


	public void setMajor(String major)
	{
		this.major = major;
	}


	public double getPracticeCredit()
	{
		return practiceCredit;
	}


	public void setPracticeCredit(double practiceCredit)
	{
		this.practiceCredit = practiceCredit;
	}


	public Double getPracticeRate()
	{
		return practiceRate;
	}


	public void setPracticeRate(Double practiceRate)
	{
		this.practiceRate = practiceRate;
	}


	public double getExpriCredit()
	{
		return expriCredit;
	}


	public void setExpriCredit(double expriCredit)
	{
		this.expriCredit = expriCredit;
	}


	public double getExpriRate()
	{
		return expriRate;
	}


	public void setExpriRate(double expriRate)
	{
		this.expriRate = expriRate;
	}


	public int getHaveExpriCourse()
	{
		return haveExpriCourse;
	}


	public void setHaveExpriCourse(int haveExpriCourse)
	{
		this.haveExpriCourse = haveExpriCourse;
	}


	public int getIndepenExperiCourse()
	{
		return indepenExperiCourse;
	}


	public void setIndepenExperiCourse(int indepenExperiCourse)
	{
		this.indepenExperiCourse = indepenExperiCourse;
	}


	public double getPercentage()
	{
		return percentage;
	}


	public void setPercentage(double percentage)
	{
		this.percentage = percentage;
	}


	public int getDesignExperiment()
	{
		return designExperiment;
	}


	public void setDesignExperiment(int designExperiment)
	{
		this.designExperiment = designExperiment;
	}


	public String getCollege()
	{
		return college;
	}


	public void setCollege(String college)
	{
		this.college = college;
	}


	public String getRowTitle()
	{
		return rowTitle;
	}


	public void setRowTitle(String rowTitle)
	{
		this.rowTitle = rowTitle;
	}


	public ProPracticeTeach(int serialNumber, String major, double practiceCredit, Double practiceRate,
			double expriCredit, double expriRate, int haveExpriCourse, int indepenExperiCourse, double percentage,
			int designExperiment, String college, String rowTitle)
	{
		super();
		this.serialNumber = serialNumber;
		this.major = major;
		this.practiceCredit = practiceCredit;
		this.practiceRate = practiceRate;
		this.expriCredit = expriCredit;
		this.expriRate = expriRate;
		this.haveExpriCourse = haveExpriCourse;
		this.indepenExperiCourse = indepenExperiCourse;
		this.percentage = percentage;
		this.designExperiment = designExperiment;
		this.college = college;
		this.rowTitle = rowTitle;
	}


	@Override
	public int compareTo(ProPracticeTeach o)
	{
		return this.practiceRate.compareTo(o.practiceRate);
	}
	
}