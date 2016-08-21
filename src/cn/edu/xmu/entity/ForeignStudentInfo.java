package cn.edu.xmu.entity;

import java.sql.Date;


/**
 * 表6-1-4  国外及港澳台学生情况（时点）
 * @author yue
 *
 */
public class ForeignStudentInfo {
	private int fsi_id;
	private Integer fsi_allgraduatenumber;//毕（结）业生人数（人） 本科生（境外）
	private Integer fsi_alldegreenumber;//授予学位数（人）本科生（境外）
	private Integer fsi_allenrollnumber;//招生数（人）本科生（境外）
	private Integer fsi_allcurrentstudentnumber;//在校生数（人）本科生（境外）
	private Integer fsi_foreigngraduatenumber;//毕（结）业生人数（人） 国外（按地域分）
	private Integer fsi_foreigndegreenumber;//授予学位数（人） 国外（按地域分）
	private Integer fsi_foreignenrollnumber;//招生数（人） 国外（按地域分）
	private Integer fsi_foreigncurrentstudentnumber;//在校生数（人） 国外（按地域分）
	private Integer fsi_hkgraduatenumber;//毕（结）业生人数（人）香港（按地域分）
	private Integer fsi_hkdegreenumber;//授予学位数（人） 香港（按地域分）
	private Integer fsi_hkenrollnumber;//招生数（人） 香港（按地域分）
	private Integer fsi_hkcurrentstudentnumber;//在校生数（人） 香港（按地域分）
	private Integer fsi_macgraduatenumber;//毕（结）业生人数（人） 澳门（按地域分）
	private Integer fsi_macdegreenumber;//授予学位数（人） 澳门（按地域分）
	private Integer fsi_macenrollnumber;//招生数（人） 澳门（按地域分）
	private Integer fsi_maccurrentstudentnumber;//在校生数（人） 澳门（按地域分）
	private Integer fsi_twngraduatenumber;//毕（结）业生人数（人） 台湾（按地域分）
	private Integer fsi_twndegreenumber;//授予学位数（人）台湾（按地域分）
	private Integer fsi_twnenrollnumber;//招生数（人）台湾（按地域分）
	private Integer fsi_twncurrentstudentnumber;//在校生数（人）台湾（按地域分）
	private int fsi_serialnumber;//序列号
	private Date fsi_deadline;//截止日期
	private String fsi_college;//所属学院
	private String fsi_comments;//审核意见
	private int isnull;//记录是否存在空值
	public ForeignStudentInfo(int fsi_id, Integer fsi_allgraduatenumber, Integer fsi_alldegreenumber,
			Integer fsi_allenrollnumber, Integer fsi_allcurrentstudentnumber, Integer fsi_foreigngraduatenumber,
			Integer fsi_foreigndegreenumber, Integer fsi_foreignenrollnumber, Integer fsi_foreigncurrentstudentnumber,
			Integer fsi_hkgraduatenumber, Integer fsi_hkdegreenumber, Integer fsi_hkenrollnumber,
			Integer fsi_hkcurrentstudentnumber, Integer fsi_macgraduatenumber, Integer fsi_macdegreenumber,
			Integer fsi_macenrollnumber, Integer fsi_maccurrentstudentnumber, Integer fsi_twngraduatenumber,
			Integer fsi_twndegreenumber, Integer fsi_twnenrollnumber, Integer fsi_twncurrentstudentnumber,
			int fsi_serialnumber, Date fsi_deadline, String fsi_college, String fsi_comments, int isnull) {
		super();
		this.fsi_id = fsi_id;
		this.fsi_allgraduatenumber = fsi_allgraduatenumber;
		this.fsi_alldegreenumber = fsi_alldegreenumber;
		this.fsi_allenrollnumber = fsi_allenrollnumber;
		this.fsi_allcurrentstudentnumber = fsi_allcurrentstudentnumber;
		this.fsi_foreigngraduatenumber = fsi_foreigngraduatenumber;
		this.fsi_foreigndegreenumber = fsi_foreigndegreenumber;
		this.fsi_foreignenrollnumber = fsi_foreignenrollnumber;
		this.fsi_foreigncurrentstudentnumber = fsi_foreigncurrentstudentnumber;
		this.fsi_hkgraduatenumber = fsi_hkgraduatenumber;
		this.fsi_hkdegreenumber = fsi_hkdegreenumber;
		this.fsi_hkenrollnumber = fsi_hkenrollnumber;
		this.fsi_hkcurrentstudentnumber = fsi_hkcurrentstudentnumber;
		this.fsi_macgraduatenumber = fsi_macgraduatenumber;
		this.fsi_macdegreenumber = fsi_macdegreenumber;
		this.fsi_macenrollnumber = fsi_macenrollnumber;
		this.fsi_maccurrentstudentnumber = fsi_maccurrentstudentnumber;
		this.fsi_twngraduatenumber = fsi_twngraduatenumber;
		this.fsi_twndegreenumber = fsi_twndegreenumber;
		this.fsi_twnenrollnumber = fsi_twnenrollnumber;
		this.fsi_twncurrentstudentnumber = fsi_twncurrentstudentnumber;
		this.fsi_serialnumber = fsi_serialnumber;
		this.fsi_deadline = fsi_deadline;
		this.fsi_college = fsi_college;
		this.fsi_comments = fsi_comments;
		this.isnull = isnull;
	}
	public ForeignStudentInfo(Integer fsi_allgraduatenumber, Integer fsi_alldegreenumber, Integer fsi_allenrollnumber,
			Integer fsi_allcurrentstudentnumber, Integer fsi_foreigngraduatenumber, Integer fsi_foreigndegreenumber,
			Integer fsi_foreignenrollnumber, Integer fsi_foreigncurrentstudentnumber, Integer fsi_hkgraduatenumber,
			Integer fsi_hkdegreenumber, Integer fsi_hkenrollnumber, Integer fsi_hkcurrentstudentnumber,
			Integer fsi_macgraduatenumber, Integer fsi_macdegreenumber, Integer fsi_macenrollnumber,
			Integer fsi_maccurrentstudentnumber, Integer fsi_twngraduatenumber, Integer fsi_twndegreenumber,
			Integer fsi_twnenrollnumber, Integer fsi_twncurrentstudentnumber, int fsi_serialnumber, String fsi_college,
			String fsi_comments, int isnull) {
		super();
		this.fsi_allgraduatenumber = fsi_allgraduatenumber;
		this.fsi_alldegreenumber = fsi_alldegreenumber;
		this.fsi_allenrollnumber = fsi_allenrollnumber;
		this.fsi_allcurrentstudentnumber = fsi_allcurrentstudentnumber;
		this.fsi_foreigngraduatenumber = fsi_foreigngraduatenumber;
		this.fsi_foreigndegreenumber = fsi_foreigndegreenumber;
		this.fsi_foreignenrollnumber = fsi_foreignenrollnumber;
		this.fsi_foreigncurrentstudentnumber = fsi_foreigncurrentstudentnumber;
		this.fsi_hkgraduatenumber = fsi_hkgraduatenumber;
		this.fsi_hkdegreenumber = fsi_hkdegreenumber;
		this.fsi_hkenrollnumber = fsi_hkenrollnumber;
		this.fsi_hkcurrentstudentnumber = fsi_hkcurrentstudentnumber;
		this.fsi_macgraduatenumber = fsi_macgraduatenumber;
		this.fsi_macdegreenumber = fsi_macdegreenumber;
		this.fsi_macenrollnumber = fsi_macenrollnumber;
		this.fsi_maccurrentstudentnumber = fsi_maccurrentstudentnumber;
		this.fsi_twngraduatenumber = fsi_twngraduatenumber;
		this.fsi_twndegreenumber = fsi_twndegreenumber;
		this.fsi_twnenrollnumber = fsi_twnenrollnumber;
		this.fsi_twncurrentstudentnumber = fsi_twncurrentstudentnumber;
		this.fsi_serialnumber = fsi_serialnumber;
		this.fsi_college = fsi_college;
		this.fsi_comments = fsi_comments;
		this.isnull = isnull;
	}
	public ForeignStudentInfo(Integer fsi_allgraduatenumber, Integer fsi_alldegreenumber, Integer fsi_allenrollnumber,
			Integer fsi_allcurrentstudentnumber, Integer fsi_foreigngraduatenumber, Integer fsi_foreigndegreenumber,
			Integer fsi_foreignenrollnumber, Integer fsi_foreigncurrentstudentnumber, Integer fsi_hkgraduatenumber,
			Integer fsi_hkdegreenumber, Integer fsi_hkenrollnumber, Integer fsi_hkcurrentstudentnumber,
			Integer fsi_macgraduatenumber, Integer fsi_macdegreenumber, Integer fsi_macenrollnumber,
			Integer fsi_maccurrentstudentnumber, Integer fsi_twngraduatenumber, Integer fsi_twndegreenumber,
			Integer fsi_twnenrollnumber, Integer fsi_twncurrentstudentnumber, String fsi_college, int isnull) {
		super();
		this.fsi_allgraduatenumber = fsi_allgraduatenumber;
		this.fsi_alldegreenumber = fsi_alldegreenumber;
		this.fsi_allenrollnumber = fsi_allenrollnumber;
		this.fsi_allcurrentstudentnumber = fsi_allcurrentstudentnumber;
		this.fsi_foreigngraduatenumber = fsi_foreigngraduatenumber;
		this.fsi_foreigndegreenumber = fsi_foreigndegreenumber;
		this.fsi_foreignenrollnumber = fsi_foreignenrollnumber;
		this.fsi_foreigncurrentstudentnumber = fsi_foreigncurrentstudentnumber;
		this.fsi_hkgraduatenumber = fsi_hkgraduatenumber;
		this.fsi_hkdegreenumber = fsi_hkdegreenumber;
		this.fsi_hkenrollnumber = fsi_hkenrollnumber;
		this.fsi_hkcurrentstudentnumber = fsi_hkcurrentstudentnumber;
		this.fsi_macgraduatenumber = fsi_macgraduatenumber;
		this.fsi_macdegreenumber = fsi_macdegreenumber;
		this.fsi_macenrollnumber = fsi_macenrollnumber;
		this.fsi_maccurrentstudentnumber = fsi_maccurrentstudentnumber;
		this.fsi_twngraduatenumber = fsi_twngraduatenumber;
		this.fsi_twndegreenumber = fsi_twndegreenumber;
		this.fsi_twnenrollnumber = fsi_twnenrollnumber;
		this.fsi_twncurrentstudentnumber = fsi_twncurrentstudentnumber;
		this.fsi_college = fsi_college;
		this.fsi_comments = "";
		this.isnull = isnull;
	}
	public int getFsi_id() {
		return fsi_id;
	}
	public void setFsi_id(int fsi_id) {
		this.fsi_id = fsi_id;
	}
	public Integer getFsi_allgraduatenumber() {
		return fsi_allgraduatenumber;
	}
	public void setFsi_allgraduatenumber(Integer fsi_allgraduatenumber) {
		this.fsi_allgraduatenumber = fsi_allgraduatenumber;
	}
	public Integer getFsi_alldegreenumber() {
		return fsi_alldegreenumber;
	}
	public void setFsi_alldegreenumber(Integer fsi_alldegreenumber) {
		this.fsi_alldegreenumber = fsi_alldegreenumber;
	}
	public Integer getFsi_allenrollnumber() {
		return fsi_allenrollnumber;
	}
	public void setFsi_allenrollnumber(Integer fsi_allenrollnumber) {
		this.fsi_allenrollnumber = fsi_allenrollnumber;
	}
	public Integer getFsi_allcurrentstudentnumber() {
		return fsi_allcurrentstudentnumber;
	}
	public void setFsi_allcurrentstudentnumber(Integer fsi_allcurrentstudentnumber) {
		this.fsi_allcurrentstudentnumber = fsi_allcurrentstudentnumber;
	}
	public Integer getFsi_foreigngraduatenumber() {
		return fsi_foreigngraduatenumber;
	}
	public void setFsi_foreigngraduatenumber(Integer fsi_foreigngraduatenumber) {
		this.fsi_foreigngraduatenumber = fsi_foreigngraduatenumber;
	}
	public Integer getFsi_foreigndegreenumber() {
		return fsi_foreigndegreenumber;
	}
	public void setFsi_foreigndegreenumber(Integer fsi_foreigndegreenumber) {
		this.fsi_foreigndegreenumber = fsi_foreigndegreenumber;
	}
	public Integer getFsi_foreignenrollnumber() {
		return fsi_foreignenrollnumber;
	}
	public void setFsi_foreignenrollnumber(Integer fsi_foreignenrollnumber) {
		this.fsi_foreignenrollnumber = fsi_foreignenrollnumber;
	}
	public Integer getFsi_foreigncurrentstudentnumber() {
		return fsi_foreigncurrentstudentnumber;
	}
	public void setFsi_foreigncurrentstudentnumber(Integer fsi_foreigncurrentstudentnumber) {
		this.fsi_foreigncurrentstudentnumber = fsi_foreigncurrentstudentnumber;
	}
	public Integer getFsi_hkgraduatenumber() {
		return fsi_hkgraduatenumber;
	}
	public void setFsi_hkgraduatenumber(Integer fsi_hkgraduatenumber) {
		this.fsi_hkgraduatenumber = fsi_hkgraduatenumber;
	}
	public Integer getFsi_hkdegreenumber() {
		return fsi_hkdegreenumber;
	}
	public void setFsi_hkdegreenumber(Integer fsi_hkdegreenumber) {
		this.fsi_hkdegreenumber = fsi_hkdegreenumber;
	}
	public Integer getFsi_hkenrollnumber() {
		return fsi_hkenrollnumber;
	}
	public void setFsi_hkenrollnumber(Integer fsi_hkenrollnumber) {
		this.fsi_hkenrollnumber = fsi_hkenrollnumber;
	}
	public Integer getFsi_hkcurrentstudentnumber() {
		return fsi_hkcurrentstudentnumber;
	}
	public void setFsi_hkcurrentstudentnumber(Integer fsi_hkcurrentstudentnumber) {
		this.fsi_hkcurrentstudentnumber = fsi_hkcurrentstudentnumber;
	}
	public Integer getFsi_macgraduatenumber() {
		return fsi_macgraduatenumber;
	}
	public void setFsi_macgraduatenumber(Integer fsi_macgraduatenumber) {
		this.fsi_macgraduatenumber = fsi_macgraduatenumber;
	}
	public Integer getFsi_macdegreenumber() {
		return fsi_macdegreenumber;
	}
	public void setFsi_macdegreenumber(Integer fsi_macdegreenumber) {
		this.fsi_macdegreenumber = fsi_macdegreenumber;
	}
	public Integer getFsi_macenrollnumber() {
		return fsi_macenrollnumber;
	}
	public void setFsi_macenrollnumber(Integer fsi_macenrollnumber) {
		this.fsi_macenrollnumber = fsi_macenrollnumber;
	}
	public Integer getFsi_maccurrentstudentnumber() {
		return fsi_maccurrentstudentnumber;
	}
	public void setFsi_maccurrentstudentnumber(Integer fsi_maccurrentstudentnumber) {
		this.fsi_maccurrentstudentnumber = fsi_maccurrentstudentnumber;
	}
	public Integer getFsi_twngraduatenumber() {
		return fsi_twngraduatenumber;
	}
	public void setFsi_twngraduatenumber(Integer fsi_twngraduatenumber) {
		this.fsi_twngraduatenumber = fsi_twngraduatenumber;
	}
	public Integer getFsi_twndegreenumber() {
		return fsi_twndegreenumber;
	}
	public void setFsi_twndegreenumber(Integer fsi_twndegreenumber) {
		this.fsi_twndegreenumber = fsi_twndegreenumber;
	}
	public Integer getFsi_twnenrollnumber() {
		return fsi_twnenrollnumber;
	}
	public void setFsi_twnenrollnumber(Integer fsi_twnenrollnumber) {
		this.fsi_twnenrollnumber = fsi_twnenrollnumber;
	}
	public Integer getFsi_twncurrentstudentnumber() {
		return fsi_twncurrentstudentnumber;
	}
	public void setFsi_twncurrentstudentnumber(Integer fsi_twncurrentstudentnumber) {
		this.fsi_twncurrentstudentnumber = fsi_twncurrentstudentnumber;
	}
	public int getFsi_serialnumber() {
		return fsi_serialnumber;
	}
	public void setFsi_serialnumber(int fsi_serialnumber) {
		this.fsi_serialnumber = fsi_serialnumber;
	}
	public Date getFsi_deadline() {
		return fsi_deadline;
	}
	public void setFsi_deadline(Date fsi_deadline) {
		this.fsi_deadline = fsi_deadline;
	}
	public String getFsi_college() {
		return fsi_college;
	}
	public void setFsi_college(String fsi_college) {
		this.fsi_college = fsi_college;
	}
	public String getFsi_comments() {
		return fsi_comments;
	}
	public void setFsi_comments(String fsi_comments) {
		this.fsi_comments = fsi_comments;
	}
	public int getIsnull() {
		return isnull;
	}
	public void setIsnull(int isnull) {
		this.isnull = isnull;
	}
	
	
	
}
