package cn.edu.xmu.entity;

import java.sql.Date;

/**
 * 附表5-3-4分专业毕业综合训练、其他教学环节安排情况（学年）
 * @author chunfeng
 *
 */
public class CombinedTrainAndOtherTeachLink {
      private int ctaotl_id;
      private String ctaotl_collegename;  //学院
      private String ctaotl_major; //专业
      private String ctaotl_majornumber;  //专业代码
      private Integer ctaotl_graduatestudent; //毕业班人数
      private Integer ctaotl_ct_t_total; //分专业毕业综合训练/指导毕业综合训练教师数/总数
      private Integer ctaotl_ct_t_fullteacher;//分专业毕业综合训练/指导毕业综合训练教师数/专任教师
      private Integer ctaotl_ct_t_partteacher; //分专业毕业综合训练/指导毕业综合训练教师数/外聘教师
      private Integer ctaotl_ct_t_senior; //分专业毕业综合训练/指导毕业综合训练教师数/正高级
      private Integer ctaotl_ct_t_subsenior; //分专业毕业综合训练/指导毕业综合训练教师数/副高级
      private Integer ctaotl_ct_p_total;  //分专业毕业综合训练/毕业综合训练课题/总数
      private Integer ctaotl_ct_p_socialfinish;//分专业毕业综合训练/毕业综合训练课题/在实验、实习、工程实践和社会调查等社会实践中完成数
      private Integer ctaotl_ct_p_fromscience; //分专业毕业综合训练/毕业综合训练课题/来自教师科研课题
      private Integer ctaotl_ct_p_fromproduce; //分专业毕业综合训练/毕业综合训练课题/来自生产管理一线
      private Integer ctaotl_ct_p_fromsocial; //分专业毕业综合训练/毕业综合训练课题/来自社会实践
      private Integer ctaotl_ct_p_other; //分专业毕业综合训练/毕业综合训练课题/其他
      private Float ctaotl_ot_pro_credit; //其他教学环节/见习或认识实习/学分
      private Integer ctaotl_ot_pro_days; //其他教学环节/见习或认识实习/天数
      private Float ctaotl_ot_pra_credit; //其他教学环节/毕业实习/学分
      private Integer ctaotl_ot_pra_days; //其他教学环节/毕业实习/天数
      private Float ctaotl_ot_ter_credit; //其他教学环节/学年论文/学分 
      private Integer ctaotl_ot_ter_days; //其他教学环节/学年论文/天数
      private Float ctaotl_ot_the_credit; //其他教学环节/毕业论文/学分
      private Integer ctaotl_ot_the_days; //其他教学环节/毕业论文/天数
      private Float ctaotl_ot_oth_credit; //其他教学环节/其他/学分
      private Integer ctaotl_ot_oth_days; //其他教学环节/其他/天数
      private Float ctaotl_ot_tot_credit; //其他教学环节/合计/学分
      private Integer ctaotl_ot_tot_days;  //其他教学环节/合计/天数
      
      private String ctaotl_college;
      private int ctaotl_serialnumber;//序列号
  	  private Date ctaotl_deadline;//截止日期
  	  private String ctaotl_comments;//审核意见
      private int ctaotl_isnull;
  	  
  	  public CombinedTrainAndOtherTeachLink(int ctaotl_id,String ctaotl_collegename,String ctaotl_major,String ctaotl_majornumber,
             Integer ctaotl_graduatestudent,Integer ctaotl_ct_t_total,Integer ctaotl_ct_t_fullteacher,Integer ctaotl_ct_t_partteacher,
             Integer ctaotl_ct_t_senior,Integer ctaotl_ct_t_subsenior,Integer ctaotl_ct_p_total,Integer ctaotl_ct_p_socialfinish,Integer ctaotl_ct_p_fromscience,
             Integer ctaotl_ct_p_fromproduce,Integer ctaotl_ct_p_fromsocial,Integer ctaotl_ct_p_other,Float ctaotl_ot_pro_credit,Integer ctaotl_ot_pro_days,
             Float ctaotl_ot_pra_credit,Integer ctaotl_ot_pra_days,Float ctaotl_ot_ter_credit,Integer ctaotl_ot_ter_days,Float ctaotl_ot_the_credit,
             Integer ctaotl_ot_the_days,Float ctaotl_ot_oth_credit,Integer ctaotl_ot_oth_days,Float ctaotl_ot_tot_credit,Integer ctaotl_ot_tot_days,
             String ctaotl_college, int ctaotl_serialnumber,Date ctaotl_deadline,String ctaotl_comments, int ctaotl_isnull){
  		 this.ctaotl_id = ctaotl_id;
  	     this.ctaotl_collegename = ctaotl_collegename;  //学院
  	     this.ctaotl_major = ctaotl_major; //专业
  	     this.ctaotl_majornumber = ctaotl_majornumber;  //专业代码
  	     this.ctaotl_graduatestudent = ctaotl_graduatestudent; //毕业班人数
  	     this.ctaotl_ct_t_total = ctaotl_ct_t_total; //分专业毕业综合训练/指导毕业综合训练教师数/总数
  	     this.ctaotl_ct_t_fullteacher = ctaotl_ct_t_fullteacher;//分专业毕业综合训练/指导毕业综合训练教师数/专任教师
  	     this.ctaotl_ct_t_partteacher = ctaotl_ct_t_partteacher; //分专业毕业综合训练/指导毕业综合训练教师数/外聘教师
  	     this.ctaotl_ct_t_senior = ctaotl_ct_t_senior; //分专业毕业综合训练/指导毕业综合训练教师数/正高级
  	     this.ctaotl_ct_t_subsenior = ctaotl_ct_t_subsenior; //分专业毕业综合训练/指导毕业综合训练教师数/副高级
  	     this.ctaotl_ct_p_total = ctaotl_ct_p_total;  //分专业毕业综合训练/毕业综合训练课题/总数
  	     this.ctaotl_ct_p_socialfinish = ctaotl_ct_p_socialfinish;//分专业毕业综合训练/毕业综合训练课题/在实验、实习、工程实践和社会调查等社会实践中完成数
  	     this.ctaotl_ct_p_fromscience = ctaotl_ct_p_fromscience; //分专业毕业综合训练/毕业综合训练课题/来自教师科研课题
  	     this.ctaotl_ct_p_fromproduce = ctaotl_ct_p_fromproduce; //分专业毕业综合训练/毕业综合训练课题/来自生产管理一线
  	     this.ctaotl_ct_p_fromsocial = ctaotl_ct_p_fromsocial; //分专业毕业综合训练/毕业综合训练课题/来自社会实践
  	     this.ctaotl_ct_p_other = ctaotl_ct_p_other; //分专业毕业综合训练/毕业综合训练课题/其他
  	     this.ctaotl_ot_pro_credit = ctaotl_ot_pro_credit; //其他教学环节/见习或认识实习/学分
  	     this.ctaotl_ot_pro_days = ctaotl_ot_pro_days; //其他教学环节/见习或认识实习/天数
  	     this.ctaotl_ot_pra_credit = ctaotl_ot_pra_credit; //其他教学环节/毕业实习/学分
  	     this.ctaotl_ot_pra_days = ctaotl_ot_pra_days; //其他教学环节/毕业实习/天数
  	     this.ctaotl_ot_ter_credit = ctaotl_ot_ter_credit; //其他教学环节/学年论文/学分 
  	     this.ctaotl_ot_ter_days = ctaotl_ot_ter_days; //其他教学环节/学年论文/天数
  	     this.ctaotl_ot_the_credit = ctaotl_ot_the_credit; //其他教学环节/毕业论文/学分
  	     this.ctaotl_ot_the_days = ctaotl_ot_the_days; //其他教学环节/毕业论文/天数
  	     this.ctaotl_ot_oth_credit = ctaotl_ot_oth_credit; //其他教学环节/其他/学分
  	     this.ctaotl_ot_oth_days = ctaotl_ot_oth_days; //其他教学环节/其他/天数
  	     this.ctaotl_ot_tot_credit = ctaotl_ot_tot_credit; //其他教学环节/合计/学分
  	     this.ctaotl_ot_tot_days = ctaotl_ot_tot_days;  //其他教学环节/合计/天数
  	      
  	     this.ctaotl_college = ctaotl_college; 
  	     this.ctaotl_serialnumber = ctaotl_serialnumber;//序列号
  	     this.ctaotl_deadline = ctaotl_deadline;//截止日期
  	     this.ctaotl_comments = ctaotl_comments;//审核意见
  	     this.ctaotl_isnull = ctaotl_isnull;
  	  }
  	public CombinedTrainAndOtherTeachLink(String ctaotl_collegename,String ctaotl_major,String ctaotl_majornumber,
            Integer ctaotl_graduatestudent,Integer ctaotl_ct_t_total,Integer ctaotl_ct_t_fullteacher,Integer ctaotl_ct_t_partteacher,
            Integer ctaotl_ct_t_senior,Integer ctaotl_ct_t_subsenior,Integer ctaotl_ct_p_total,Integer ctaotl_ct_p_socialfinish,Integer ctaotl_ct_p_fromscience,
            Integer ctaotl_ct_p_fromproduce,Integer ctaotl_ct_p_fromsocial,Integer ctaotl_ct_p_other,Float ctaotl_ot_pro_credit,Integer ctaotl_ot_pro_days,
            Float ctaotl_ot_pra_credit,Integer ctaotl_ot_pra_days,Float ctaotl_ot_ter_credit,Integer ctaotl_ot_ter_days,Float ctaotl_ot_the_credit,
            Integer ctaotl_ot_the_days,Float ctaotl_ot_oth_credit,Integer ctaotl_ot_oth_days,Float ctaotl_ot_tot_credit,Integer ctaotl_ot_tot_days,
            String ctaotl_college, int ctaotl_serialnumber, int ctaotl_isnull){
 		
 	     this.ctaotl_collegename = ctaotl_collegename;  //学院
 	     this.ctaotl_major = ctaotl_major; //专业
 	     this.ctaotl_majornumber = ctaotl_majornumber;  //专业代码
 	     this.ctaotl_graduatestudent = ctaotl_graduatestudent; //毕业班人数
 	     this.ctaotl_ct_t_total = ctaotl_ct_t_total; //分专业毕业综合训练/指导毕业综合训练教师数/总数
 	     this.ctaotl_ct_t_fullteacher = ctaotl_ct_t_fullteacher;//分专业毕业综合训练/指导毕业综合训练教师数/专任教师
 	     this.ctaotl_ct_t_partteacher = ctaotl_ct_t_partteacher; //分专业毕业综合训练/指导毕业综合训练教师数/外聘教师
 	     this.ctaotl_ct_t_senior = ctaotl_ct_t_senior; //分专业毕业综合训练/指导毕业综合训练教师数/正高级
 	     this.ctaotl_ct_t_subsenior = ctaotl_ct_t_subsenior; //分专业毕业综合训练/指导毕业综合训练教师数/副高级
 	     this.ctaotl_ct_p_total = ctaotl_ct_p_total;  //分专业毕业综合训练/毕业综合训练课题/总数
 	     this.ctaotl_ct_p_socialfinish = ctaotl_ct_p_socialfinish;//分专业毕业综合训练/毕业综合训练课题/在实验、实习、工程实践和社会调查等社会实践中完成数
 	     this.ctaotl_ct_p_fromscience = ctaotl_ct_p_fromscience; //分专业毕业综合训练/毕业综合训练课题/来自教师科研课题
 	     this.ctaotl_ct_p_fromproduce = ctaotl_ct_p_fromproduce; //分专业毕业综合训练/毕业综合训练课题/来自生产管理一线
 	     this.ctaotl_ct_p_fromsocial = ctaotl_ct_p_fromsocial; //分专业毕业综合训练/毕业综合训练课题/来自社会实践
 	     this.ctaotl_ct_p_other = ctaotl_ct_p_other; //分专业毕业综合训练/毕业综合训练课题/其他
 	     this.ctaotl_ot_pro_credit = ctaotl_ot_pro_credit; //其他教学环节/见习或认识实习/学分
 	     this.ctaotl_ot_pro_days = ctaotl_ot_pro_days; //其他教学环节/见习或认识实习/天数
 	     this.ctaotl_ot_pra_credit = ctaotl_ot_pra_credit; //其他教学环节/毕业实习/学分
 	     this.ctaotl_ot_pra_days = ctaotl_ot_pra_days; //其他教学环节/毕业实习/天数
 	     this.ctaotl_ot_ter_credit = ctaotl_ot_ter_credit; //其他教学环节/学年论文/学分 
 	     this.ctaotl_ot_ter_days = ctaotl_ot_ter_days; //其他教学环节/学年论文/天数
 	     this.ctaotl_ot_the_credit = ctaotl_ot_the_credit; //其他教学环节/毕业论文/学分
 	     this.ctaotl_ot_the_days = ctaotl_ot_the_days; //其他教学环节/毕业论文/天数
 	     this.ctaotl_ot_oth_credit = ctaotl_ot_oth_credit; //其他教学环节/其他/学分
 	     this.ctaotl_ot_oth_days = ctaotl_ot_oth_days; //其他教学环节/其他/天数
 	     this.ctaotl_ot_tot_credit = ctaotl_ot_tot_credit; //其他教学环节/合计/学分
 	     this.ctaotl_ot_tot_days = ctaotl_ot_tot_days;  //其他教学环节/合计/天数
 	      
 	     this.ctaotl_college = ctaotl_college;
 	     this.ctaotl_serialnumber = ctaotl_serialnumber;//序列号 	  
 	    this.ctaotl_isnull = ctaotl_isnull;
 	  }
  	
      
	public int getCtaotl_id() {
		return ctaotl_id;
	}
	public void setCtaotl_id(int ctaotl_id) {
		this.ctaotl_id = ctaotl_id;
	}
	public String getCtaotl_college() {
		return ctaotl_college;
	}
	public void setCtaotl_college(String ctaotl_college) {
		this.ctaotl_college = ctaotl_college;
	}
	public String getCtaotl_major() {
		return ctaotl_major;
	}
	public void setCtaotl_major(String ctaotl_major) {
		this.ctaotl_major = ctaotl_major;
	}
	public String getCtaotl_majornumber() {
		return ctaotl_majornumber;
	}
	public void setCtaotl_majornumber(String ctaotl_majornumber) {
		this.ctaotl_majornumber = ctaotl_majornumber;
	}
	public Integer getCtaotl_graduatestudent() {
		return ctaotl_graduatestudent;
	}
	public void setCtaotl_graduatestudent(Integer ctaotl_graduatestudent) {
		this.ctaotl_graduatestudent = ctaotl_graduatestudent;
	}
	public Integer getCtaotl_ct_t_total() {
		return ctaotl_ct_t_total;
	}
	public void setCtaotl_ct_t_total(Integer ctaotl_ct_t_total) {
		this.ctaotl_ct_t_total = ctaotl_ct_t_total;
	}
	public Integer getCtaotl_ct_t_fullteacher() {
		return ctaotl_ct_t_fullteacher;
	}
	public void setCtaotl_ct_t_fullteacher(Integer ctaotl_ct_t_fullteacher) {
		this.ctaotl_ct_t_fullteacher = ctaotl_ct_t_fullteacher;
	}
	public Integer getCtaotl_ct_t_partteacher() {
		return ctaotl_ct_t_partteacher;
	}
	public void setCtaotl_ct_t_partteacher(Integer ctaotl_ct_t_partteacher) {
		this.ctaotl_ct_t_partteacher = ctaotl_ct_t_partteacher;
	}
	public Integer getCtaotl_ct_t_senior() {
		return ctaotl_ct_t_senior;
	}
	public void setCtaotl_ct_t_senior(Integer ctaotl_ct_t_senior) {
		this.ctaotl_ct_t_senior = ctaotl_ct_t_senior;
	}
	public Integer getCtaotl_ct_t_subsenior() {
		return ctaotl_ct_t_subsenior;
	}
	public void setCtaotl_ct_t_subsenior(Integer ctaotl_ct_t_subsenior) {
		this.ctaotl_ct_t_subsenior = ctaotl_ct_t_subsenior;
	}
	public Integer getCtaotl_ct_p_total() {
		return ctaotl_ct_p_total;
	}
	public void setCtaotl_ct_p_total(Integer ctaotl_ct_p_total) {
		this.ctaotl_ct_p_total = ctaotl_ct_p_total;
	}
	public Integer getCtaotl_ct_p_socialfinish() {
		return ctaotl_ct_p_socialfinish;
	}
	public void setCtaotl_ct_p_socialfinish(Integer ctaotl_ct_p_socialfinish) {
		this.ctaotl_ct_p_socialfinish = ctaotl_ct_p_socialfinish;
	}
	public Integer getCtaotl_ct_p_fromscience() {
		return ctaotl_ct_p_fromscience;
	}
	public void setCtaotl_ct_p_fromscience(Integer ctaotl_ct_p_fromscience) {
		this.ctaotl_ct_p_fromscience = ctaotl_ct_p_fromscience;
	}
	public Integer getCtaotl_ct_p_fromproduce() {
		return ctaotl_ct_p_fromproduce;
	}
	public void setCtaotl_ct_p_fromproduce(Integer ctaotl_ct_p_fromproduce) {
		this.ctaotl_ct_p_fromproduce = ctaotl_ct_p_fromproduce;
	}
	public Integer getCtaotl_ct_p_fromsocial() {
		return ctaotl_ct_p_fromsocial;
	}
	public void setCtaotl_ct_p_fromsocial(Integer ctaotl_ct_p_fromsocial) {
		this.ctaotl_ct_p_fromsocial = ctaotl_ct_p_fromsocial;
	}
	public Integer getCtaotl_ct_p_other() {
		return ctaotl_ct_p_other;
	}
	public void setCtaotl_ct_p_other(Integer ctaotl_ct_p_other) {
		this.ctaotl_ct_p_other = ctaotl_ct_p_other;
	}
	public Float getCtaotl_ot_pro_credit() {
		return ctaotl_ot_pro_credit;
	}
	public void setCtaotl_ot_pro_credit(Float ctaotl_ot_pro_credit) {
		this.ctaotl_ot_pro_credit = ctaotl_ot_pro_credit;
	}
	public Integer getCtaotl_ot_pro_days() {
		return ctaotl_ot_pro_days;
	}
	public void setCtaotl_ot_pro_days(Integer ctaotl_ot_pro_days) {
		this.ctaotl_ot_pro_days = ctaotl_ot_pro_days;
	}
	public Float getCtaotl_ot_pra_credit() {
		return ctaotl_ot_pra_credit;
	}
	public void setCtaotl_ot_pra_credit(Float ctaotl_ot_pra_credit) {
		this.ctaotl_ot_pra_credit = ctaotl_ot_pra_credit;
	}
	public Integer getCtaotl_ot_pra_days() {
		return ctaotl_ot_pra_days;
	}
	public void setCtaotl_ot_pra_days(Integer ctaotl_ot_pra_days) {
		this.ctaotl_ot_pra_days = ctaotl_ot_pra_days;
	}
	public Float getCtaotl_ot_ter_credit() {
		return ctaotl_ot_ter_credit;
	}
	public void setCtaotl_ot_ter_credit(Float ctaotl_ot_ter_credit) {
		this.ctaotl_ot_ter_credit = ctaotl_ot_ter_credit;
	}
	public Integer getCtaotl_ot_ter_days() {
		return ctaotl_ot_ter_days;
	}
	public void setCtaotl_ot_ter_days(Integer ctaotl_ot_ter_days) {
		this.ctaotl_ot_ter_days = ctaotl_ot_ter_days;
	}
	public Float getCtaotl_ot_the_credit() {
		return ctaotl_ot_the_credit;
	}
	public void setCtaotl_ot_the_credit(Float ctaotl_ot_the_credit) {
		this.ctaotl_ot_the_credit = ctaotl_ot_the_credit;
	}
	public Integer getCtaotl_ot_the_days() {
		return ctaotl_ot_the_days;
	}
	public void setCtaotl_ot_the_days(Integer ctaotl_ot_the_days) {
		this.ctaotl_ot_the_days = ctaotl_ot_the_days;
	}
	public Float getCtaotl_ot_oth_credit() {
		return ctaotl_ot_oth_credit;
	}
	public void setCtaotl_ot_oth_credit(Float ctaotl_ot_oth_credit) {
		this.ctaotl_ot_oth_credit = ctaotl_ot_oth_credit;
	}
	public Integer getCtaotl_ot_oth_days() {
		return ctaotl_ot_oth_days;
	}
	public void setCtaotl_ot_oth_days(Integer ctaotl_ot_oth_days) {
		this.ctaotl_ot_oth_days = ctaotl_ot_oth_days;
	}
	public Float getCtaotl_ot_tot_credit() {
		return ctaotl_ot_tot_credit;
	}
	public void setCtaotl_ot_tot_credit(Float ctaotl_ot_tot_credit) {
		this.ctaotl_ot_tot_credit = ctaotl_ot_tot_credit;
	}
	public Integer getCtaotl_ot_tot_days() {
		return ctaotl_ot_tot_days;
	}
	public void setCtaotl_ot_tot_days(Integer ctaotl_ot_tot_days) {
		this.ctaotl_ot_tot_days = ctaotl_ot_tot_days;
	}
	public int getCtaotl_serialnumber() {
		return ctaotl_serialnumber;
	}
	public void setCtaotl_serialnumber(int ctaotl_serialnumber) {
		this.ctaotl_serialnumber = ctaotl_serialnumber;
	}
	public Date getCtaotl_deadline() {
		return ctaotl_deadline;
	}
	public void setCtaotl_deadline(Date ctaotl_deadline) {
		this.ctaotl_deadline = ctaotl_deadline;
	}
	public String getCtaotl_comments() {
		return ctaotl_comments;
	}
	public void setCtaotl_comments(String ctaotl_comments) {
		this.ctaotl_comments = ctaotl_comments;
	}
	public int getCtaotl_isnull() {
		return ctaotl_isnull;
	}
	public void setCtaotl_isnull(int ctaotl_isnull) {
		this.ctaotl_isnull = ctaotl_isnull;
	}
	public String getCtaotl_collegename() {
		return ctaotl_collegename;
	}
	public void setCtaotl_collegename(String ctaotl_collegename) {
		this.ctaotl_collegename = ctaotl_collegename;
	}
	
}
