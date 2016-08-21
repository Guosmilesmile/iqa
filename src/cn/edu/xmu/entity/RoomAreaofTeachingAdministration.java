package cn.edu.xmu.entity;

import java.sql.Date;

/**
 * @author zhantu
 * 各单位教学行政用房面积 实体类
 * date 2015-07-07
 */
public class RoomAreaofTeachingAdministration {
	//ID
	private int rata_id;
	//所属学院名
	private String rata_collegename;
	//小计
	private Float rata_sum;
	//教学科研及辅助用房
	private Float rata_teachresearchauxiliary;
	//教室
	private Float rata_classroom;
	//图书馆
	private Float rata_library;
	//实验室、实习场所
	private Float rata_lab;
	//专用科研用房
	private Float rata_privatescienresearch;
	//体育馆
	private Float rata_gym;
	//会堂
	private Float rata_hall;
	//行政办公用房
	private Float rata_administrationoffice;
	//序列号
	private int rata_serialnumber;
	//截止日期
	private Date rata_deadline;
	//所属学院
	private String rata_college;
	//审核意见
	private String rata_comments;
	//记录是否存在空值
	private int isnull;
	
	public int getIsnull() {
		return isnull;
	}
	public void setIsnull(int isnull) {
		this.isnull = isnull;
	}
	public int getRata_id() {
		return rata_id;
	}
	public void setRata_id(int rata_id) {
		this.rata_id = rata_id;
	}
	public String getRata_collegename() {
		return rata_collegename;
	}
	public void setRata_collegename(String rata_collegename) {
		this.rata_collegename = rata_collegename;
	}
	public Float getRata_sum() {
		return rata_sum;
	}
	public void setRata_sum(Float rata_sum) {
		this.rata_sum = rata_sum;
	}
	public Float getRata_teachresearchauxiliary() {
		return rata_teachresearchauxiliary;
	}
	public void setRata_teachresearchauxiliary(Float rata_teachresearchauxiliary) {
		this.rata_teachresearchauxiliary = rata_teachresearchauxiliary;
	}
	public Float getRata_classroom() {
		return rata_classroom;
	}
	public void setRata_classroom(Float rata_classroom) {
		this.rata_classroom = rata_classroom;
	}
	public Float getRata_library() {
		return rata_library;
	}
	public void setRata_library(Float rata_library) {
		this.rata_library = rata_library;
	}
	public Float getRata_lab() {
		return rata_lab;
	}
	public void setRata_lab(Float rata_lab) {
		this.rata_lab = rata_lab;
	}
	public Float getRata_privatescienresearch() {
		return rata_privatescienresearch;
	}
	public void setRata_privatescienresearch(Float rata_privatescienresearch) {
		this.rata_privatescienresearch = rata_privatescienresearch;
	}
	public Float getRata_gym() {
		return rata_gym;
	}
	public void setRata_gym(Float rata_gym) {
		this.rata_gym = rata_gym;
	}
	public Float getRata_hall() {
		return rata_hall;
	}
	public void setRata_hall(Float rata_hall) {
		this.rata_hall = rata_hall;
	}
	public Float getRata_administrationoffice() {
		return rata_administrationoffice;
	}
	public void setRata_administrationoffice(Float rata_administrationoffice) {
		this.rata_administrationoffice = rata_administrationoffice;
	}
	public int getRata_serialnumber() {
		return rata_serialnumber;
	}
	public void setRata_serialnumber(int rata_serialnumber) {
		this.rata_serialnumber = rata_serialnumber;
	}
	public Date getRata_deadline() {
		return rata_deadline;
	}
	public void setRata_deadline(Date rata_deadline) {
		this.rata_deadline = rata_deadline;
	}
	public String getRata_college() {
		return rata_college;
	}
	public void setRata_college(String rata_college) {
		this.rata_college = rata_college;
	}
	public String getRata_comments() {
		return rata_comments;
	}
	public void setRata_comments(String rata_comments) {
		this.rata_comments = rata_comments;
	}
	public RoomAreaofTeachingAdministration() {
		super();
	}
	public RoomAreaofTeachingAdministration(int rata_id,String rata_collegename, Float rata_sum,
			Float rata_teachresearchauxiliary, Float rata_classroom,
			Float rata_library, Float rata_lab,
			Float rata_privatescienresearch, Float rata_gym, Float rata_hall,
			Float rata_administrationoffice, int rata_serialnumber,
			Date rata_deadline, String rata_college, String rata_comments, int isnull) {
		super();
		this.rata_id = rata_id;
		this.rata_collegename = rata_collegename;
		this.rata_sum = rata_sum;
		this.rata_teachresearchauxiliary = rata_teachresearchauxiliary;
		this.rata_classroom = rata_classroom;
		this.rata_library = rata_library;
		this.rata_lab = rata_lab;
		this.rata_privatescienresearch = rata_privatescienresearch;
		this.rata_gym = rata_gym;
		this.rata_hall = rata_hall;
		this.rata_administrationoffice = rata_administrationoffice;
		this.rata_serialnumber = rata_serialnumber;
		this.rata_deadline = rata_deadline;
		this.rata_college = rata_college;
		this.rata_comments = rata_comments;
		this.isnull = isnull;
	}
	public RoomAreaofTeachingAdministration(String rata_collegename, Float rata_sum,
			Float rata_teachresearchauxiliary, Float rata_classroom,
			Float rata_library, Float rata_lab,
			Float rata_privatescienresearch, Float rata_gym, Float rata_hall,
			Float rata_administrationoffice, int rata_serialnumber,
			String rata_college, String rata_comments, int isnull) {
		super();
		this.rata_collegename = rata_collegename;
		this.rata_sum = rata_sum;
		this.rata_teachresearchauxiliary = rata_teachresearchauxiliary;
		this.rata_classroom = rata_classroom;
		this.rata_library = rata_library;
		this.rata_lab = rata_lab;
		this.rata_privatescienresearch = rata_privatescienresearch;
		this.rata_gym = rata_gym;
		this.rata_hall = rata_hall;
		this.rata_administrationoffice = rata_administrationoffice;
		this.rata_serialnumber = rata_serialnumber;
		this.rata_college = rata_college;
		this.rata_comments = rata_comments;
		this.isnull = isnull;
	}
	public RoomAreaofTeachingAdministration(int rata_id,String rata_collegename,Float rata_sum,
			Float rata_teachresearchauxiliary, Float rata_classroom,
			Float rata_library, Float rata_lab,
			Float rata_privatescienresearch, Float rata_gym, Float rata_hall,
			Float rata_administrationoffice, int rata_serialnumber,
			String rata_comments, int isnull) {
		super();
		this.rata_id = rata_id;
		this.rata_collegename = rata_collegename;
		this.rata_sum = rata_sum;
		this.rata_teachresearchauxiliary = rata_teachresearchauxiliary;
		this.rata_classroom = rata_classroom;
		this.rata_library = rata_library;
		this.rata_lab = rata_lab;
		this.rata_privatescienresearch = rata_privatescienresearch;
		this.rata_gym = rata_gym;
		this.rata_hall = rata_hall;
		this.rata_administrationoffice = rata_administrationoffice;
		this.rata_serialnumber = rata_serialnumber;
		this.rata_comments = rata_comments;
		this.isnull = isnull;
	}
	public RoomAreaofTeachingAdministration(int rata_id,String rata_collegename,Float rata_sum,
			Float rata_teachresearchauxiliary, Float rata_classroom,
			Float rata_library, Float rata_lab,
			Float rata_privatescienresearch, Float rata_gym, Float rata_hall,
			Float rata_administrationoffice, String rata_comments, int isnull) {
		super();
		this.rata_id = rata_id;
		this.rata_collegename = rata_collegename;
		this.rata_sum = rata_sum;
		this.rata_teachresearchauxiliary = rata_teachresearchauxiliary;
		this.rata_classroom = rata_classroom;
		this.rata_library = rata_library;
		this.rata_lab = rata_lab;
		this.rata_privatescienresearch = rata_privatescienresearch;
		this.rata_gym = rata_gym;
		this.rata_hall = rata_hall;
		this.rata_administrationoffice = rata_administrationoffice;
		this.rata_comments = rata_comments;
		this.isnull = isnull;
	}
	public RoomAreaofTeachingAdministration(String rata_collegename,
			Float rata_sum, Float rata_teachresearchauxiliary,
			Float rata_classroom, Float rata_library, Float rata_lab,
			Float rata_privatescienresearch, Float rata_gym, Float rata_hall,
			Float rata_administrationoffice, String rata_college, int isnull) {
		super();
		this.rata_collegename = rata_collegename;
		this.rata_sum = rata_sum;
		this.rata_teachresearchauxiliary = rata_teachresearchauxiliary;
		this.rata_classroom = rata_classroom;
		this.rata_library = rata_library;
		this.rata_lab = rata_lab;
		this.rata_privatescienresearch = rata_privatescienresearch;
		this.rata_gym = rata_gym;
		this.rata_hall = rata_hall;
		this.rata_administrationoffice = rata_administrationoffice;
		this.rata_college = rata_college;
		this.isnull = isnull;
	}
	
}
