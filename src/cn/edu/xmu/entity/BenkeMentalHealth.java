package cn.edu.xmu.entity;

import java.sql.Date;

/**
 * 
 * @author Lee
 * 附表6-2-1-11本科生心理健康状况分析（学年）
 * date 2015-07-13
 */
public class BenkeMentalHealth {

		//序号
		private int bmh_id;

		//学院
		private String bmh_college1;
		
		//在校生人数
		private int bmh_inschoolamount;
		
		//心理健康状况
		private String bmh_health;
		
		//序列号
		private int bmh_serialnumber;
		
		//截止日期
		private Date bmh_deadline;
		
		//审核意见
		private String bmh_comments;
		
		//操作学院
		private String bmh_college;
		
		//记录是否存在空值
		private int isnull;

		public int getBmh_id() {
			return bmh_id;
		}

		public void setBmh_id(int bmh_id) {
			this.bmh_id = bmh_id;
		}

		public String getBmh_college1() {
			return bmh_college1;
		}

		public void setBmh_college1(String bmh_college1) {
			this.bmh_college1 = bmh_college1;
		}

		public int getBmh_inschoolamount() {
			return bmh_inschoolamount;
		}

		public void setBmh_inschoolamount(int bmh_inschoolamount) {
			this.bmh_inschoolamount = bmh_inschoolamount;
		}

		public String getBmh_health() {
			return bmh_health;
		}

		public void setBmh_health(String bmh_health) {
			this.bmh_health = bmh_health;
		}

		public int getBmh_serialnumber() {
			return bmh_serialnumber;
		}

		public void setBmh_serialnumber(int bmh_serialnumber) {
			this.bmh_serialnumber = bmh_serialnumber;
		}

		public Date getBmh_deadline() {
			return bmh_deadline;
		}

		public void setBmh_deadline(Date bmh_deadline) {
			this.bmh_deadline = bmh_deadline;
		}

		public String getBmh_comments() {
			return bmh_comments;
		}

		public void setBmh_comments(String bmh_comments) {
			this.bmh_comments = bmh_comments;
		}

		public String getBmh_college() {
			return bmh_college;
		}

		public void setBmh_college(String bmh_college) {
			this.bmh_college = bmh_college;
		}

		public int getIsnull() {
			return isnull;
		}

		public void setIsnull(int isnull) {
			this.isnull = isnull;
		}

		public BenkeMentalHealth() {
			super();
		}

		public BenkeMentalHealth(int bmh_id, String bmh_college1,
				int bmh_inschoolamount, String bmh_health,
				int bmh_serialnumber, String bmh_comments, String bmh_college,
				int isnull) {
			super();
			this.bmh_id = bmh_id;
			this.bmh_college1 = bmh_college1;
			this.bmh_inschoolamount = bmh_inschoolamount;
			this.bmh_health = bmh_health;
			this.bmh_serialnumber = bmh_serialnumber;
			this.bmh_comments = bmh_comments;
			this.bmh_college = bmh_college;
			this.isnull = isnull;
		}

		public BenkeMentalHealth(int bmh_id, String bmh_college1,
				int bmh_inschoolamount, String bmh_health,
				int bmh_serialnumber, Date bmh_deadline, String bmh_comments,
				String bmh_college, int isnull) {
			super();
			this.bmh_id = bmh_id;
			this.bmh_college1 = bmh_college1;
			this.bmh_inschoolamount = bmh_inschoolamount;
			this.bmh_health = bmh_health;
			this.bmh_serialnumber = bmh_serialnumber;
			this.bmh_deadline = bmh_deadline;
			this.bmh_comments = bmh_comments;
			this.bmh_college = bmh_college;
			this.isnull = isnull;
		}

		public BenkeMentalHealth(String bmh_college1, int bmh_inschoolamount,
				String bmh_health, int bmh_serialnumber, String bmh_college,
				int isnull) {
			super();
			this.bmh_college1 = bmh_college1;
			this.bmh_inschoolamount = bmh_inschoolamount;
			this.bmh_health = bmh_health;
			this.bmh_serialnumber = bmh_serialnumber;
			this.bmh_college = bmh_college;
			this.isnull = isnull;
		}

		/**
		 * 导出使用
		 * @param bmh_college1
		 * @param bmh_inschoolamount
		 * @param bmh_health
		 * @param bmh_college
		 * @param isnull
		 */
		public BenkeMentalHealth(String bmh_college1, int bmh_inschoolamount,
				String bmh_health, String bmh_college, int isnull) {
			super();
			this.bmh_college1 = bmh_college1;
			this.bmh_inschoolamount = bmh_inschoolamount;
			this.bmh_health = bmh_health;
			this.bmh_college = bmh_college;
			this.isnull = isnull;
		}
		
		
}
