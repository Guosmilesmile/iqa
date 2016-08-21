package cn.edu.xmu.entity;


import java.sql.Date;


/**
 * 
 * @author Lee
 * 实验教学示范中心 实体类
 * 全参构造函数
 * date 2015-07-09
 *
 */
public class ExpTeachCenter {

		//序号
		private int etc_id;

		//实验教学示范中心名称
		private String etc_expteachcentername;
		
		//学科名称
		private String etc_subjectname;
		
		//学科代码
		private String etc_subjectcode;
		
		//级别代码
		private int etc_levelnum;
		
		//级别
		private String etc_levelname;
		
		//设立时间
		private int etc_starttime;
		
		//序列号
		private int etc_serialnumber;
		
		//截止日期
		private Date etc_deadline;
		
		//审核意见
		private String etc_comments;
		
		//操作学院
		private String etc_college;
		
		//记录是否存在空值
		private int isnull;

		public int getEtc_id() {
			return etc_id;
		}

		public void setEtc_id(int etc_id) {
			this.etc_id = etc_id;
		}

		public String getEtc_expteachcentername() {
			return etc_expteachcentername;
		}

		public void setEtc_expteachcentername(String etc_expteachcentername) {
			this.etc_expteachcentername = etc_expteachcentername;
		}

		public String getEtc_subjectname() {
			return etc_subjectname;
		}

		public void setEtc_subjectname(String etc_subjectname) {
			this.etc_subjectname = etc_subjectname;
		}

		public String getEtc_subjectcode() {
			return etc_subjectcode;
		}

		public void setEtc_subjectcode(String etc_subjectcode) {
			this.etc_subjectcode = etc_subjectcode;
		}

		public int getEtc_levelnum() {
			return etc_levelnum;
		}

		public void setEtc_levelnum(int etc_levelnum) {
			this.etc_levelnum = etc_levelnum;
		}

		public String getEtc_levelname() {
			return etc_levelname;
		}

		public void setEtc_levelname(String etc_levelname) {
			this.etc_levelname = etc_levelname;
		}

		public int getEtc_starttime() {
			return etc_starttime;
		}

		public void setEtc_starttime(int etc_starttime) {
			this.etc_starttime = etc_starttime;
		}

		public int getEtc_serialnumber() {
			return etc_serialnumber;
		}

		public void setEtc_serialnumber(int etc_serialnumber) {
			this.etc_serialnumber = etc_serialnumber;
		}

		public Date getEtc_deadline() {
			return etc_deadline;
		}

		public void setEtc_deadline(Date etc_deadline) {
			this.etc_deadline = etc_deadline;
		}

		public String getEtc_comments() {
			return etc_comments;
		}

		public void setEtc_comments(String etc_comments) {
			this.etc_comments = etc_comments;
		}

		public String getEtc_college() {
			return etc_college;
		}

		public void setEtc_college(String etc_college) {
			this.etc_college = etc_college;
		}

		public int getIsnull() {
			return isnull;
		}

		public void setIsnull(int isnull) {
			this.isnull = isnull;
		}

		public ExpTeachCenter() {
			super();
		}

		public ExpTeachCenter(int etc_id, String etc_expteachcentername,
				String etc_subjectname, String etc_subjectcode,
				int etc_levelnum, String etc_levelname, int etc_starttime,
				int etc_serialnumber, Date etc_deadline, String etc_comments,
				String etc_college, int isnull) {
			super();
			this.etc_id = etc_id;
			this.etc_expteachcentername = etc_expteachcentername;
			this.etc_subjectname = etc_subjectname;
			this.etc_subjectcode = etc_subjectcode;
			this.etc_levelnum = etc_levelnum;
			this.etc_levelname = etc_levelname;
			this.etc_starttime = etc_starttime;
			this.etc_serialnumber = etc_serialnumber;
			this.etc_deadline = etc_deadline;
			this.etc_comments = etc_comments;
			this.etc_college = etc_college;
			this.isnull = isnull;
		}

		public ExpTeachCenter(int etc_id, String etc_expteachcentername,
				String etc_subjectname, String etc_subjectcode,
				int etc_levelnum, String etc_levelname, int etc_starttime,
				int etc_serialnumber, String etc_college, int isnull) {
			super();
			this.etc_id = etc_id;
			this.etc_expteachcentername = etc_expteachcentername;
			this.etc_subjectname = etc_subjectname;
			this.etc_subjectcode = etc_subjectcode;
			this.etc_levelnum = etc_levelnum;
			this.etc_levelname = etc_levelname;
			this.etc_starttime = etc_starttime;
			this.etc_serialnumber = etc_serialnumber;
			this.etc_college = etc_college;
			this.isnull = isnull;
		}

		public ExpTeachCenter(String etc_expteachcentername,
				String etc_subjectname, String etc_subjectcode,
				int etc_levelnum, String etc_levelname, int etc_starttime,
				String etc_college, int isnull) {
			super();
			this.etc_expteachcentername = etc_expteachcentername;
			this.etc_subjectname = etc_subjectname;
			this.etc_subjectcode = etc_subjectcode;
			this.etc_levelnum = etc_levelnum;
			this.etc_levelname = etc_levelname;
			this.etc_starttime = etc_starttime;
			this.etc_college = etc_college;
			this.isnull = isnull;
		}
	
	
		
	
	
	
}
