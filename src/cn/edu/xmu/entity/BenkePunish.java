package cn.edu.xmu.entity;

import java.sql.Date;

/**
 * 
 * @author Lee
 * 附表6-2-1-10 本科生受处分情况(按处分等级）（学年）
 * date 2015/7/13
 */

public class BenkePunish {

		//序号
		private int bp_id;

		//学院
		private String bp_college1;
		
		//严重警告
		private String bp_warning;
		
		//记过处分
		private String bp_demerit;
		
		//留校察看处分
		private String bp_probation;
		
		//开除学籍处分
		private String bp_expulsion;
		
		//累计人数
		private int bp_totalmount;
		
		//序列号
		private int bp_serialnumber;
		
		//截止日期
		private Date bp_deadline;
		
		//审核意见
		private String bp_comments;
		
		//操作学院
		private String bp_college;
		
		//记录是否存在空值
		private int isnull;

		public int getBp_id() {
			return bp_id;
		}

		public void setBp_id(int bp_id) {
			this.bp_id = bp_id;
		}

		public String getBp_college1() {
			return bp_college1;
		}

		public void setBp_college1(String bp_college1) {
			this.bp_college1 = bp_college1;
		}

		public String getBp_warning() {
			return bp_warning;
		}

		public void setBp_warning(String bp_warning) {
			this.bp_warning = bp_warning;
		}

		public String getBp_demerit() {
			return bp_demerit;
		}

		public void setBp_demerit(String bp_demerit) {
			this.bp_demerit = bp_demerit;
		}

		public String getBp_probation() {
			return bp_probation;
		}

		public void setBp_probation(String bp_probation) {
			this.bp_probation = bp_probation;
		}

		public String getBp_expulsion() {
			return bp_expulsion;
		}

		public void setBp_expulsion(String bp_expulsion) {
			this.bp_expulsion = bp_expulsion;
		}

		public int getBp_totalmount() {
			return bp_totalmount;
		}

		public void setBp_totalmount(int bp_totalmount) {
			this.bp_totalmount = bp_totalmount;
		}

		public int getBp_serialnumber() {
			return bp_serialnumber;
		}

		public void setBp_serialnumber(int bp_serialnumber) {
			this.bp_serialnumber = bp_serialnumber;
		}

		public Date getBp_deadline() {
			return bp_deadline;
		}

		public void setBp_deadline(Date bp_deadline) {
			this.bp_deadline = bp_deadline;
		}

		public String getBp_comments() {
			return bp_comments;
		}

		public void setBp_comments(String bp_comments) {
			this.bp_comments = bp_comments;
		}

		public String getBp_college() {
			return bp_college;
		}

		public void setBp_college(String bp_college) {
			this.bp_college = bp_college;
		}

		public int getIsnull() {
			return isnull;
		}

		public void setIsnull(int isnull) {
			this.isnull = isnull;
		}

		public BenkePunish() {
			super();
		}

		public BenkePunish(int bp_id, String bp_college1, String bp_warning,
				String bp_demerit, String bp_probation, String bp_expulsion,
				int bp_totalmount, int bp_serialnumber, String bp_comments,
				String bp_college, int isnull) {
			super();
			this.bp_id = bp_id;
			this.bp_college1 = bp_college1;
			this.bp_warning = bp_warning;
			this.bp_demerit = bp_demerit;
			this.bp_probation = bp_probation;
			this.bp_expulsion = bp_expulsion;
			this.bp_totalmount = bp_totalmount;
			this.bp_serialnumber = bp_serialnumber;
			this.bp_comments = bp_comments;
			this.bp_college = bp_college;
			this.isnull = isnull;
		}

		public BenkePunish(int bp_id, String bp_college1, String bp_warning,
				String bp_demerit, String bp_probation, String bp_expulsion,
				int bp_totalmount, int bp_serialnumber, Date bp_deadline,
				String bp_comments, String bp_college, int isnull) {
			super();
			this.bp_id = bp_id;
			this.bp_college1 = bp_college1;
			this.bp_warning = bp_warning;
			this.bp_demerit = bp_demerit;
			this.bp_probation = bp_probation;
			this.bp_expulsion = bp_expulsion;
			this.bp_totalmount = bp_totalmount;
			this.bp_serialnumber = bp_serialnumber;
			this.bp_deadline = bp_deadline;
			this.bp_comments = bp_comments;
			this.bp_college = bp_college;
			this.isnull = isnull;
		}

		public BenkePunish(String bp_college1, String bp_warning,
				String bp_demerit, String bp_probation, String bp_expulsion,
				int bp_totalmount, int bp_serialnumber, String bp_college,
				int isnull) {
			super();
			this.bp_college1 = bp_college1;
			this.bp_warning = bp_warning;
			this.bp_demerit = bp_demerit;
			this.bp_probation = bp_probation;
			this.bp_expulsion = bp_expulsion;
			this.bp_totalmount = bp_totalmount;
			this.bp_serialnumber = bp_serialnumber;
			this.bp_college = bp_college;
			this.isnull = isnull;
		}

		
		/**
		 * 导出使用
		 * @param bp_college1
		 * @param bp_warning
		 * @param bp_demerit
		 * @param bp_probation
		 * @param bp_expulsion
		 * @param bp_totalmount
		 * @param bp_college
		 * @param isnull
		 */
		public BenkePunish(String bp_college1, String bp_warning,
				String bp_demerit, String bp_probation, String bp_expulsion,
				int bp_totalmount, String bp_college, int isnull) {
			super();
			this.bp_college1 = bp_college1;
			this.bp_warning = bp_warning;
			this.bp_demerit = bp_demerit;
			this.bp_probation = bp_probation;
			this.bp_expulsion = bp_expulsion;
			this.bp_totalmount = bp_totalmount;
			this.bp_college = bp_college;
			this.isnull = isnull;
		}
		
		
		
	
}
