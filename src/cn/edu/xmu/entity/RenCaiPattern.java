package cn.edu.xmu.entity;

import java.sql.Date;


/**
 * 
 * @author Lee
 * 人才培养模式创新实验项目 实体类
 * 全参构造函数
 * date 2015-07-08
 *
 */
public class RenCaiPattern {
		//序号
		private int rcp_id;
		//学院
		private String rcp_college1;
		
		//项目
		private String rcp_project;
		
		//负责人
		private String rcp_head;
		
		//类型
		private String rcp_type;
		
		//级别
		private String rcp_level;
		
		//设立时间
		private Date rcp_starttime;
		
		//序列号
		private int rcp_serialnumber;
		
		//截止日期
		private Date rcp_deadline;
		
		//审核意见
		private String rcp_comments;
		
		//操作学院
		private String rcp_college;
		
		//记录是否存在空值
		private int isnull;
		
		public int getRcp_id() {
			return rcp_id;
		}

		public void setRcp_id(int rcp_id) {
			this.rcp_id = rcp_id;
		}

		public String getRcp_college1() {
			return rcp_college1;
		}

		public void setRcp_college1(String rcp_college1) {
			this.rcp_college1 = rcp_college1;
		}

		public String getRcp_project() {
			return rcp_project;
		}

		public void setRcp_project(String rcp_project) {
			this.rcp_project = rcp_project;
		}

		public String getRcp_head() {
			return rcp_head;
		}

		public void setRcp_head(String rcp_head) {
			this.rcp_head = rcp_head;
		}

		public String getRcp_type() {
			return rcp_type;
		}

		public void setRcp_type(String rcp_type) {
			this.rcp_type = rcp_type;
		}

		public String getRcp_level() {
			return rcp_level;
		}

		public void setRcp_level(String rcp_level) {
			this.rcp_level = rcp_level;
		}

		public Date getRcp_starttime() {
			return rcp_starttime;
		}

		public void setRcp_starttime(Date rcp_starttime) {
			this.rcp_starttime = rcp_starttime;
		}

		public int getRcp_serialnumber() {
			return rcp_serialnumber;
		}

		public void setRcp_serialnumber(int rcp_serialnumber) {
			this.rcp_serialnumber = rcp_serialnumber;
		}

		public Date getRcp_deadline() {
			return rcp_deadline;
		}

		public void setRcp_deadline(Date rcp_deadline) {
			this.rcp_deadline = rcp_deadline;
		}

		public String getRcp_comments() {
			return rcp_comments;
		}

		public void setRcp_comments(String rcp_comments) {
			this.rcp_comments = rcp_comments;
		}

		public String getRcp_college() {
			return rcp_college;
		}

		public void setRcp_college(String rcp_college) {
			this.rcp_college = rcp_college;
		}

		public int getRcp_isnull() {
			return isnull;
		}

		public void setRcp_isnull(int isnull) {
			this.isnull = isnull;
		}

		/**
		 * 无参构造函数
		 */
		public RenCaiPattern() {
			super();
		}

		public RenCaiPattern(int rcp_id, String rcp_college1,
				String rcp_project, String rcp_head, String rcp_type,
				String rcp_level, Date rcp_starttime, int rcp_serialnumber,
				Date rcp_deadline, String rcp_comments, String rcp_college,
				int isnull) {
			super();
			this.rcp_id = rcp_id;
			this.rcp_college1 = rcp_college1;
			this.rcp_project = rcp_project;
			this.rcp_head = rcp_head;
			this.rcp_type = rcp_type;
			this.rcp_level = rcp_level;
			this.rcp_starttime = rcp_starttime;
			this.rcp_serialnumber = rcp_serialnumber;
			this.rcp_deadline = rcp_deadline;
			this.rcp_comments = rcp_comments;
			this.rcp_college = rcp_college;
			this.isnull = isnull;
		}

		public RenCaiPattern(String rcp_college1,
				String rcp_project, String rcp_head, String rcp_type,
				String rcp_level, Date rcp_starttime, int rcp_serialnumber,
				String rcp_college, int isnull) {
			super();
			this.rcp_college1 = rcp_college1;
			this.rcp_project = rcp_project;
			this.rcp_head = rcp_head;
			this.rcp_type = rcp_type;
			this.rcp_level = rcp_level;
			this.rcp_starttime = rcp_starttime;
			this.rcp_serialnumber = rcp_serialnumber;
			this.rcp_college = rcp_college;
			this.isnull = isnull;
		}
		
	
	
}
