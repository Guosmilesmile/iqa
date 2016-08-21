package cn.edu.xmu.entity;

/**
 * 
 * @author zshbleaker
 *
 */

public class LibraryYear {

	private int ly_id;
	private Integer ly_number;//数量（个）
	private Integer ly_seatnumber;//阅览室座位数（个）
	private Integer ly_paperbooknumber;//纸质图书总量（册）
	//纸质期刊
	private Integer ly_paperjournalnumber;//数量（份）
	private Integer ly_paperjournaltype;//种类（种）
	//电子图书
	private Integer ly_ebooknumber;//数量（种）
	private Integer ly_ebookchnnumber;//其中：中文数量（种）
	private Integer ly_ebookforeignnumber;//外文数量（种）
	
	private Integer ly_ejournaltype;//电子期刊种类（种）
	private Integer ly_databasenumber;//数据库数量（个）
	private int ly_serialnumber;
	private String ly_college;
	private String ly_deadline;
	private String ly_comments;
	
	private int isnull;

	public LibraryYear(int ly_id, Integer ly_number, Integer ly_seatnumber, Integer ly_paperbooknumber, Integer ly_paperjournalnumber,
			Integer ly_paperjournaltype, Integer ly_ebooknumber, Integer ly_ebookchnnumber, Integer ly_ebookforeignnumber,
			Integer ly_ejournaltype, Integer ly_databasenumber, int ly_serialnumber, String ly_college, String ly_deadline,
			String ly_comments, int isnull) {
		super();
		this.ly_id = ly_id;
		this.ly_number = ly_number;
		this.ly_seatnumber = ly_seatnumber;
		this.ly_paperbooknumber = ly_paperbooknumber;
		this.ly_paperjournalnumber = ly_paperjournalnumber;
		this.ly_paperjournaltype = ly_paperjournaltype;
		this.ly_ebooknumber = ly_ebooknumber;
		this.ly_ebookchnnumber = ly_ebookchnnumber;
		this.ly_ebookforeignnumber = ly_ebookforeignnumber;
		this.ly_ejournaltype = ly_ejournaltype;
		this.ly_databasenumber = ly_databasenumber;
		this.ly_serialnumber = ly_serialnumber;
		this.ly_college = ly_college;
		this.ly_deadline = ly_deadline;
		this.ly_comments = ly_comments;
		this.isnull = isnull;
	}

	
	//no id and deadline
	public LibraryYear(Integer ly_number, Integer ly_seatnumber, Integer ly_paperbooknumber, Integer ly_paperjournalnumber,
			Integer ly_paperjournaltype, Integer ly_ebooknumber, Integer ly_ebookchnnumber, Integer ly_ebookforeignnumber,
			Integer ly_ejournaltype, Integer ly_databasenumber, int ly_serialnumber, String ly_college, String ly_comments, int isnull) {
		super();
		this.ly_number = ly_number;
		this.ly_seatnumber = ly_seatnumber;
		this.ly_paperbooknumber = ly_paperbooknumber;
		this.ly_paperjournalnumber = ly_paperjournalnumber;
		this.ly_paperjournaltype = ly_paperjournaltype;
		this.ly_ebooknumber = ly_ebooknumber;
		this.ly_ebookchnnumber = ly_ebookchnnumber;
		this.ly_ebookforeignnumber = ly_ebookforeignnumber;
		this.ly_ejournaltype = ly_ejournaltype;
		this.ly_databasenumber = ly_databasenumber;
		this.ly_serialnumber = ly_serialnumber;
		this.ly_college = ly_college;
		this.ly_comments = ly_comments;
		this.isnull = isnull;
	}

	//no deadline and college
	public LibraryYear(int ly_id, Integer ly_number, Integer ly_seatnumber, Integer ly_paperbooknumber, Integer ly_paperjournalnumber,
			Integer ly_paperjournaltype, Integer ly_ebooknumber, Integer ly_ebookchnnumber, Integer ly_ebookforeignnumber,
			Integer ly_ejournaltype, Integer ly_databasenumber, int ly_serialnumber, String ly_comments, int ly_isnull) {
		super();
		this.ly_id = ly_id;
		this.ly_number = ly_number;
		this.ly_seatnumber = ly_seatnumber;
		this.ly_paperbooknumber = ly_paperbooknumber;
		this.ly_paperjournalnumber = ly_paperjournalnumber;
		this.ly_paperjournaltype = ly_paperjournaltype;
		this.ly_ebooknumber = ly_ebooknumber;
		this.ly_ebookchnnumber = ly_ebookchnnumber;
		this.ly_ebookforeignnumber = ly_ebookforeignnumber;
		this.ly_ejournaltype = ly_ejournaltype;
		this.ly_databasenumber = ly_databasenumber;
		this.ly_serialnumber = ly_serialnumber;
		this.ly_comments = ly_comments;
		this.isnull = ly_isnull;
	}

	//no serialnumber, deadline and college
	public LibraryYear(int ly_id, Integer ly_number, Integer ly_seatnumber, Integer ly_paperbooknumber, Integer ly_paperjournalnumber,
			Integer ly_paperjournaltype, Integer ly_ebooknumber, Integer ly_ebookchnnumber, Integer ly_ebookforeignnumber,
			Integer ly_ejournaltype, Integer ly_databasenumber, String ly_comments, int isnull) {
		super();
		this.ly_id = ly_id;
		this.ly_number = ly_number;
		this.ly_seatnumber = ly_seatnumber;
		this.ly_paperbooknumber = ly_paperbooknumber;
		this.ly_paperjournalnumber = ly_paperjournalnumber;
		this.ly_paperjournaltype = ly_paperjournaltype;
		this.ly_ebooknumber = ly_ebooknumber;
		this.ly_ebookchnnumber = ly_ebookchnnumber;
		this.ly_ebookforeignnumber = ly_ebookforeignnumber;
		this.ly_ejournaltype = ly_ejournaltype;
		this.ly_databasenumber = ly_databasenumber;
		this.ly_comments = ly_comments;
		this.isnull = isnull;
	}


	public LibraryYear() {
		super();
	}


	

	public int getIsnull() {
		return isnull;
	}


	public void setIsnull(int isnull) {
		this.isnull = isnull;
	}


	public int getLy_id() {
		return ly_id;
	}
	public void setLy_id(int ly_id) {
		this.ly_id = ly_id;
	}
	
	public Integer getLy_number()
	{
		return ly_number;
	}


	public void setLy_number(Integer ly_number)
	{
		this.ly_number = ly_number;
	}


	public Integer getLy_seatnumber()
	{
		return ly_seatnumber;
	}


	public void setLy_seatnumber(Integer ly_seatnumber)
	{
		this.ly_seatnumber = ly_seatnumber;
	}


	public Integer getLy_paperbooknumber()
	{
		return ly_paperbooknumber;
	}


	public void setLy_paperbooknumber(Integer ly_paperbooknumber)
	{
		this.ly_paperbooknumber = ly_paperbooknumber;
	}


	public Integer getLy_paperjournalnumber()
	{
		return ly_paperjournalnumber;
	}


	public void setLy_paperjournalnumber(Integer ly_paperjournalnumber)
	{
		this.ly_paperjournalnumber = ly_paperjournalnumber;
	}


	public Integer getLy_paperjournaltype()
	{
		return ly_paperjournaltype;
	}


	public void setLy_paperjournaltype(Integer ly_paperjournaltype)
	{
		this.ly_paperjournaltype = ly_paperjournaltype;
	}


	public Integer getLy_ebooknumber()
	{
		return ly_ebooknumber;
	}


	public void setLy_ebooknumber(Integer ly_ebooknumber)
	{
		this.ly_ebooknumber = ly_ebooknumber;
	}


	public Integer getLy_ebookchnnumber()
	{
		return ly_ebookchnnumber;
	}


	public void setLy_ebookchnnumber(Integer ly_ebookchnnumber)
	{
		this.ly_ebookchnnumber = ly_ebookchnnumber;
	}


	public Integer getLy_ebookforeignnumber()
	{
		return ly_ebookforeignnumber;
	}


	public void setLy_ebookforeignnumber(Integer ly_ebookforeignnumber)
	{
		this.ly_ebookforeignnumber = ly_ebookforeignnumber;
	}


	public Integer getLy_ejournaltype()
	{
		return ly_ejournaltype;
	}


	public void setLy_ejournaltype(Integer ly_ejournaltype)
	{
		this.ly_ejournaltype = ly_ejournaltype;
	}


	public Integer getLy_databasenumber()
	{
		return ly_databasenumber;
	}


	public void setLy_databasenumber(Integer ly_databasenumber)
	{
		this.ly_databasenumber = ly_databasenumber;
	}


	public int getLy_serialnumber() {
		return ly_serialnumber;
	}
	public void setLy_serialnumber(int ly_serialnumber) {
		this.ly_serialnumber = ly_serialnumber;
	}
	public String getLy_college() {
		return ly_college;
	}
	public void setLy_college(String ly_college) {
		this.ly_college = ly_college;
	}
	public String getLy_deadline() {
		return ly_deadline;
	}
	public void setLy_deadline(String ly_deadline) {
		this.ly_deadline = ly_deadline;
	}
	public String getLy_comments() {
		return ly_comments;
	}
	public void setLy_comments(String ly_comments) {
		this.ly_comments = ly_comments;
	}

	
}
