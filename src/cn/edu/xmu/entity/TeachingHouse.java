package cn.edu.xmu.entity;

/**
 * 
 * @author xiaoping 3.2.1 教学行政用房情况 date 2015-8-3
 */

public class TeachingHouse
{
	private String rowTitle;

	// 教学行政用房
	private String areaTotal; // 总面积（平方米）;
	private String asistHouse; // 教学科研辅助用房（平方米）
	private String classroomArea; // 教学行政用房 教室（平方米）
	private String libraryArea; // 图书馆（平方米）
	private String labArea; // 实验室
	private String researchArea;// 专业科研用房
	private String gymArea;// 体育馆（平方米）
	private String hallArea;// 会堂（平方米）
	private String adminArea;// 行政用房（平方米）
	private String avgArea; // 生均教学行政用房面积（平方米/生）

	// 教室
	private String classroomAmount;// 数量（间）
	private String englishComputerAmount;// 其中：外语教学计算机机房（含语音室）（间）
	private String multimediaAmount;// 多媒体教室（间）
	private String seatNumber;// 座位数（个）
	private String englishComputerNumber;// 其中：外语教学计算机机房（含语音室）（个）
	private String multimediaNumber;// 多媒体教室（个）
	private String perHundredNumber;// 百名学生配多媒体教室和语音实验室座位数（个）

	// 体育馆
	private String sportsArea;// 面积（平方米）
	private String sportsNumber;// 体育馆数量（个）

	// 学生活动中心
	private String studentsActivityArea;// 面积（平方米）
	private String studentsActitvityAmount;// 数量（间）

	private String college;

	public TeachingHouse()
	{
		super();
		this.rowTitle = "";
		this.areaTotal = "";
		this.asistHouse = "";
		this.classroomArea = "";
		this.libraryArea = "";
		this.labArea = "";
		this.researchArea = "";
		this.gymArea = "";
		this.hallArea = "";
		this.adminArea = "";
		this.avgArea = "";
		this.classroomAmount = "";
		this.englishComputerAmount = "";
		this.multimediaAmount = "";
		this.seatNumber = "";
		this.englishComputerNumber = "";
		this.multimediaNumber = "";
		this.perHundredNumber = "";
		this.sportsArea = "";
		this.sportsNumber = "";
		this.studentsActivityArea = "";
		this.studentsActitvityAmount = "";
		this.college = "";
	}

	public TeachingHouse(String rowTitle, String areaTotal, String asistHouse, String classroomArea, String libraryArea,
			String labArea, String researchArea, String gymArea, String hallArea, String adminArea, String avgArea,
			String classroomAmount, String englishComputerAmount, String multimediaAmount, String seatNumber,
			String englishComputerNumber, String multimediaNumber, String perHundredNumber, String sportsArea,
			String sportsNumber, String studentsActivityArea, String studentsActitvityAmount, String college)
	{
		super();
		this.rowTitle = rowTitle;
		this.areaTotal = areaTotal;
		this.asistHouse = asistHouse;
		this.classroomArea = classroomArea;
		this.libraryArea = libraryArea;
		this.labArea = labArea;
		this.researchArea = researchArea;
		this.gymArea = gymArea;
		this.hallArea = hallArea;
		this.adminArea = adminArea;
		this.avgArea = avgArea;
		this.classroomAmount = classroomAmount;
		this.englishComputerAmount = englishComputerAmount;
		this.multimediaAmount = multimediaAmount;
		this.seatNumber = seatNumber;
		this.englishComputerNumber = englishComputerNumber;
		this.multimediaNumber = multimediaNumber;
		this.perHundredNumber = perHundredNumber;
		this.sportsArea = sportsArea;
		this.sportsNumber = sportsNumber;
		this.studentsActivityArea = studentsActivityArea;
		this.studentsActitvityAmount = studentsActitvityAmount;
		this.college = college;
	}

	// public void getDataFromArray(double[] a)
	// {
	// this.areaTotal = a[0];
	// this.asistHouse = a[1];
	// this.classroomArea = a[2];
	// this.libraryArea = a[3];
	// this.labArea = a[4];
	// this.researchArea = a[5];
	// this.gymArea = a[6];
	// this.hallArea = a[7];
	// this.adminArea = a[8];
	// this.avgArea = a[9];
	//
	// this.classroomAmount = (int) a[10];
	// this.englishComputerAmount = (int) a[11];
	// this.multimediaAmount = (int) a[12];
	// this.seatNumber = (int) a[13];
	// this.englishComputerNumber = (int) a[14];
	// this.multimediaNumber = (int) a[15];
	// this.eAndMNumberPerHundredStudent = a[16];
	// this.sportsArea = (int) a[17];
	// this.sportsNumber = (int) a[18];
	// this.studentsActivityArea = a[19];
	// this.studentsActitvityAmount = (int) a[20];
	// }

	public String getRowTitle()
	{
		return rowTitle;
	}

	public void setRowTitle(String rowTitle)
	{
		this.rowTitle = rowTitle;
	}

	public String getAreaTotal()
	{
		return areaTotal;
	}

	public void setAreaTotal(String areaTotal)
	{
		this.areaTotal = areaTotal;
	}

	public String getAsistHouse()
	{
		return asistHouse;
	}

	public void setAsistHouse(String asistHouse)
	{
		this.asistHouse = asistHouse;
	}

	public String getClassroomArea()
	{
		return classroomArea;
	}

	public void setClassroomArea(String classroomArea)
	{
		this.classroomArea = classroomArea;
	}

	public String getLibraryArea()
	{
		return libraryArea;
	}

	public void setLibraryArea(String libraryArea)
	{
		this.libraryArea = libraryArea;
	}

	public String getLabArea()
	{
		return labArea;
	}

	public void setLabArea(String labArea)
	{
		this.labArea = labArea;
	}

	public String getResearchArea()
	{
		return researchArea;
	}

	public void setResearchArea(String researchArea)
	{
		this.researchArea = researchArea;
	}

	public String getGymArea()
	{
		return gymArea;
	}

	public void setGymArea(String gymArea)
	{
		this.gymArea = gymArea;
	}

	public String getHallArea()
	{
		return hallArea;
	}

	public void setHallArea(String hallArea)
	{
		this.hallArea = hallArea;
	}

	public String getAdminArea()
	{
		return adminArea;
	}

	public void setAdminArea(String adminArea)
	{
		this.adminArea = adminArea;
	}

	public String getAvgArea()
	{
		return avgArea;
	}

	public void setAvgArea(String avgArea)
	{
		this.avgArea = avgArea;
	}

	public String getClassroomAmount()
	{
		return classroomAmount;
	}

	public void setClassroomAmount(String classroomAmount)
	{
		this.classroomAmount = classroomAmount;
	}

	public String getEnglishComputerAmount()
	{
		return englishComputerAmount;
	}

	public void setEnglishComputerAmount(String englishComputerAmount)
	{
		this.englishComputerAmount = englishComputerAmount;
	}

	public String getMultimediaAmount()
	{
		return multimediaAmount;
	}

	public void setMultimediaAmount(String multimediaAmount)
	{
		this.multimediaAmount = multimediaAmount;
	}

	public String getSeatNumber()
	{
		return seatNumber;
	}

	public void setSeatNumber(String seatNumber)
	{
		this.seatNumber = seatNumber;
	}

	public String getEnglishComputerNumber()
	{
		return englishComputerNumber;
	}

	public void setEnglishComputerNumber(String englishComputerNumber)
	{
		this.englishComputerNumber = englishComputerNumber;
	}

	public String getMultimediaNumber()
	{
		return multimediaNumber;
	}

	public void setMultimediaNumber(String multimediaNumber)
	{
		this.multimediaNumber = multimediaNumber;
	}

	public String getPerHundredNumber()
	{
		return perHundredNumber;
	}

	public void setPerHundredNumber(String perHundredNumber)
	{
		this.perHundredNumber = perHundredNumber;
	}

	public String getSportsArea()
	{
		return sportsArea;
	}

	public void setSportsArea(String sportsArea)
	{
		this.sportsArea = sportsArea;
	}

	public String getSportsNumber()
	{
		return sportsNumber;
	}

	public void setSportsNumber(String sportsNumber)
	{
		this.sportsNumber = sportsNumber;
	}

	public String getStudentsActivityArea()
	{
		return studentsActivityArea;
	}

	public void setStudentsActivityArea(String studentsActivityArea)
	{
		this.studentsActivityArea = studentsActivityArea;
	}

	public String getStudentsActitvityAmount()
	{
		return studentsActitvityAmount;
	}

	public void setStudentsActitvityAmount(String studentsActitvityAmount)
	{
		this.studentsActitvityAmount = studentsActitvityAmount;
	}

	public String getCollege()
	{
		return college;
	}

	public void setCollege(String college)
	{
		this.college = college;
	}

}
