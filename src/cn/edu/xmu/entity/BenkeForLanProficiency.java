package cn.edu.xmu.entity;

import java.sql.Date;

import org.apache.poi.hssf.record.formula.functions.Int;

/**
 * 
 * @author Lee
 * 6-2-1-12本科生外语水平及学习情况调查表
 * date 2015-07-13
 */

public class BenkeForLanProficiency {

		//序号
		private int bflp_id;

		//学号
		private String bflp_stunum;
		
		//学院
		private String bflp_college1;
		
		//姓名
		private String bflp_name;
		
		//专业
		private String bflp_major;
		
		//年级
		private String bflp_grade;
		
		//符合的程度
		private String bflp_degree;
		
		//成绩或等级
		private String bflp_level;
		
		//GPA
		private String bflp_gpa;
		
		//成绩排名/人数
		private String bflp_rank;

		//是否推免生
		private String bflp_ispush;
		
		//序列号
		private int bflp_serialnumber;
		
		//截止日期
		private Date bflp_deadline;
		
		//审核意见
		private String bflp_comments;
		
		//操作学院
		private String bflp_college;
		
		//记录是否存在空值
		private int isnull;

		public int getBflp_id() {
			return bflp_id;
		}

		public void setBflp_id(int bflp_id) {
			this.bflp_id = bflp_id;
		}

		public String getBflp_stunum() {
			return bflp_stunum;
		}

		public void setBflp_stunum(String bflp_stunum) {
			this.bflp_stunum = bflp_stunum;
		}

		public String getBflp_college1() {
			return bflp_college1;
		}

		public void setBflp_college1(String bflp_college1) {
			this.bflp_college1 = bflp_college1;
		}

		public String getBflp_name() {
			return bflp_name;
		}

		public void setBflp_name(String bflp_name) {
			this.bflp_name = bflp_name;
		}

		public String getBflp_major() {
			return bflp_major;
		}

		public void setBflp_major(String bflp_major) {
			this.bflp_major = bflp_major;
		}

		public String getBflp_grade() {
			return bflp_grade;
		}

		public void setBflp_grade(String bflp_grade) {
			this.bflp_grade = bflp_grade;
		}

		public String getBflp_degree() {
			return bflp_degree;
		}

		public void setBflp_degree(String bflp_degree) {
			this.bflp_degree = bflp_degree;
		}

		public String getBflp_level() {
			return bflp_level;
		}

		public void setBflp_level(String bflp_level) {
			this.bflp_level = bflp_level;
		}

		public String getBflp_gpa() {
			return bflp_gpa;
		}

		public void setBflp_gpa(String bflp_gpa) {
			this.bflp_gpa = bflp_gpa;
		}

		public String getBflp_rank() {
			return bflp_rank;
		}

		public void setBflp_rank(String bflp_rank) {
			this.bflp_rank = bflp_rank;
		}

		public String getBflp_ispush() {
			return bflp_ispush;
		}

		public void setBflp_ispush(String bflp_ispush) {
			this.bflp_ispush = bflp_ispush;
		}

		public int getBflp_serialnumber() {
			return bflp_serialnumber;
		}

		public void setBflp_serialnumber(int bflp_serialnumber) {
			this.bflp_serialnumber = bflp_serialnumber;
		}

		public Date getBflp_deadline() {
			return bflp_deadline;
		}

		public void setBflp_deadline(Date bflp_deadline) {
			this.bflp_deadline = bflp_deadline;
		}

		public String getBflp_comments() {
			return bflp_comments;
		}

		public void setBflp_comments(String bflp_comments) {
			this.bflp_comments = bflp_comments;
		}

		public String getBflp_college() {
			return bflp_college;
		}

		public void setBflp_college(String bflp_college) {
			this.bflp_college = bflp_college;
		}

		public int getIsnull() {
			return isnull;
		}

		public void setIsnull(int isnull) {
			this.isnull = isnull;
		}

		public BenkeForLanProficiency(int bflp_id, String bflp_stunum,
				String bflp_college1, String bflp_name, String bflp_major,
				String bflp_grade, String bflp_degree, String bflp_level,
				String bflp_gpa, String bflp_rank, String bflp_ispush,
				int bflp_serialnumber, String bflp_comments,
				String bflp_college, int isnull) {
			super();
			this.bflp_id = bflp_id;
			this.bflp_stunum = bflp_stunum;
			this.bflp_college1 = bflp_college1;
			this.bflp_name = bflp_name;
			this.bflp_major = bflp_major;
			this.bflp_grade = bflp_grade;
			this.bflp_degree = bflp_degree;
			this.bflp_level = bflp_level;
			this.bflp_gpa = bflp_gpa;
			this.bflp_rank = bflp_rank;
			this.bflp_ispush = bflp_ispush;
			this.bflp_serialnumber = bflp_serialnumber;
			this.bflp_comments = bflp_comments;
			this.bflp_college = bflp_college;
			this.isnull = isnull;
		}

		public BenkeForLanProficiency(int bflp_id, String bflp_stunum,
				String bflp_college1, String bflp_name, String bflp_major,
				String bflp_grade, String bflp_degree, String bflp_level,
				String bflp_gpa, String bflp_rank, String bflp_ispush,
				int bflp_serialnumber, Date bflp_deadline,
				String bflp_comments, String bflp_college, int isnull) {
			super();
			this.bflp_id = bflp_id;
			this.bflp_stunum = bflp_stunum;
			this.bflp_college1 = bflp_college1;
			this.bflp_name = bflp_name;
			this.bflp_major = bflp_major;
			this.bflp_grade = bflp_grade;
			this.bflp_degree = bflp_degree;
			this.bflp_level = bflp_level;
			this.bflp_gpa = bflp_gpa;
			this.bflp_rank = bflp_rank;
			this.bflp_ispush = bflp_ispush;
			this.bflp_serialnumber = bflp_serialnumber;
			this.bflp_deadline = bflp_deadline;
			this.bflp_comments = bflp_comments;
			this.bflp_college = bflp_college;
			this.isnull = isnull;
		}

		public BenkeForLanProficiency(String bflp_stunum, String bflp_college1,
				String bflp_name, String bflp_major, String bflp_grade,
				String bflp_degree, String bflp_level, String bflp_gpa,
				String bflp_rank, String bflp_ispush, int bflp_serialnumber,
				String bflp_college, int isnull) {
			super();
			this.bflp_stunum = bflp_stunum;
			this.bflp_college1 = bflp_college1;
			this.bflp_name = bflp_name;
			this.bflp_major = bflp_major;
			this.bflp_grade = bflp_grade;
			this.bflp_degree = bflp_degree;
			this.bflp_level = bflp_level;
			this.bflp_gpa = bflp_gpa;
			this.bflp_rank = bflp_rank;
			this.bflp_ispush = bflp_ispush;
			this.bflp_serialnumber = bflp_serialnumber;
			this.bflp_college = bflp_college;
			this.isnull = isnull;
		}

		/**
		 * 导出使用
		 * @param bflp_stunum
		 * @param bflp_college1
		 * @param bflp_name
		 * @param bflp_major
		 * @param bflp_grade
		 * @param bflp_degree
		 * @param bflp_level
		 * @param bflp_gpa
		 * @param bflp_rank
		 * @param bflp_ispush
		 * @param bflp_college
		 * @param isnull
		 */
		public BenkeForLanProficiency(String bflp_stunum, String bflp_college1,
				String bflp_name, String bflp_major, String bflp_grade,
				String bflp_degree, String bflp_level, String bflp_gpa,
				String bflp_rank, String bflp_ispush, String bflp_college,
				int isnull) {
			super();
			this.bflp_stunum = bflp_stunum;
			this.bflp_college1 = bflp_college1;
			this.bflp_name = bflp_name;
			this.bflp_major = bflp_major;
			this.bflp_grade = bflp_grade;
			this.bflp_degree = bflp_degree;
			this.bflp_level = bflp_level;
			this.bflp_gpa = bflp_gpa;
			this.bflp_rank = bflp_rank;
			this.bflp_ispush = bflp_ispush;
			this.bflp_college = bflp_college;
			this.isnull = isnull;
		}
		
		
}
