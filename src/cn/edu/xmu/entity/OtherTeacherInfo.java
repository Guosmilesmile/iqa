package cn.edu.xmu.entity;

import java.sql.Date;

import cn.edu.xmu.enums.DaoShiLeiBie;
import cn.edu.xmu.enums.ShiZiLeiBie;
import cn.edu.xmu.enums.WorkingState;
import cn.edu.xmu.enums.XingBie;
import cn.edu.xmu.enums.XueKeLeiBie;
import cn.edu.xmu.enums.XueLi;
import cn.edu.xmu.enums.ZhuanYeJiShuZhiCheng;
import cn.edu.xmu.enums.ZuiGaoXueWei;

/**
 * 
 * @author Sihan
 * 其他教师信息
 * 全参构造函数、空构造函数
 * date 2015年06月29日
 */

public class OtherTeacherInfo {

	private int oti_id;				
	private String oti_name;								//姓名
	private String oti_worknumber;							//工号
	private String oti_sex;									//性别
	private Date oti_birthday;								//出生年月
	private Date oti_inschooldate;							//入校时间
	private String oti_workstate;							//任职状态
	private String oti_teachertype;							//师资类别
	private String oti_departmentnumber;					//单位号	
	private String oti_departmentname;						//单位名称	
	private String oti_education;							//学历
	private String oti_degree;								//最高学位
	private String oti_professionaltitle;					//专业技术职称
	private String oti_subjectcategory;						//学科类别
	private String oti_tutorcategory;						//导师类别	
	private String oti_college;								//填报学院
	private Date oti_deadline;								//截止时间
	private int oti_serialnumber;							//序列号
	private String oti_comments;							//审核意见
	private int oti_isnull;
	
	//no serialnumber deadline college
	public OtherTeacherInfo(int oti_id, String oti_name, String oti_worknumber, String oti_sex, Date oti_birthday,
			Date oti_inschooldate, String oti_workstate, String oti_teachertype, String oti_departmentnumber,
			String oti_departmentname, String oti_education, String oti_degree, String oti_professionaltitle,
			String oti_subjectcategory, String oti_tutorcategory, String oti_comments) {
		super();
		this.oti_id = oti_id;
		this.oti_name = oti_name;
		this.oti_worknumber = oti_worknumber;
		this.oti_sex = oti_sex;
		this.oti_birthday = oti_birthday;
		this.oti_inschooldate = oti_inschooldate;
		this.oti_workstate = oti_workstate;
		this.oti_teachertype = oti_teachertype;
		this.oti_departmentnumber = oti_departmentnumber;
		this.oti_departmentname = oti_departmentname;
		this.oti_education = oti_education;
		this.oti_degree = oti_degree;
		this.oti_professionaltitle = oti_professionaltitle;
		this.oti_subjectcategory = oti_subjectcategory;
		this.oti_tutorcategory = oti_tutorcategory;
		this.oti_comments = oti_comments;
	}
	//no deadline college
	public OtherTeacherInfo(int oti_id, String oti_name, String oti_worknumber, String oti_sex, Date oti_birthday,
			Date oti_inschooldate, String oti_workstate, String oti_teachertype, String oti_departmentnumber,
			String oti_departmentname, String oti_education, String oti_degree, String oti_professionaltitle,
			String oti_subjectcategory, String oti_tutorcategory, int oti_serialnumber, String oti_comments) {
		super();
		this.oti_id = oti_id;
		this.oti_name = oti_name;
		this.oti_worknumber = oti_worknumber;
		this.oti_sex = oti_sex;
		this.oti_birthday = oti_birthday;
		this.oti_inschooldate = oti_inschooldate;
		this.oti_workstate = oti_workstate;
		this.oti_teachertype = oti_teachertype;
		this.oti_departmentnumber = oti_departmentnumber;
		this.oti_departmentname = oti_departmentname;
		this.oti_education = oti_education;
		this.oti_degree = oti_degree;
		this.oti_professionaltitle = oti_professionaltitle;
		this.oti_subjectcategory = oti_subjectcategory;
		this.oti_tutorcategory = oti_tutorcategory;
		this.oti_serialnumber = oti_serialnumber;
		this.oti_comments = oti_comments;
	}
	//full argus
	public OtherTeacherInfo(int oti_id, String oti_name, String oti_worknumber, String oti_sex, Date oti_birthday,
			Date oti_inschooldate, String oti_workstate, String oti_teachertype, String oti_departmentnumber,
			String oti_departmentname, String oti_education, String oti_degree, String oti_professionaltitle,
			String oti_subjectcategory, String oti_tutorcategory, String oti_college, Date oti_deadline,
			int oti_serialnumber, String oti_comments, int isnull) {
		super();
		this.oti_id = oti_id;
		this.oti_name = oti_name;
		this.oti_worknumber = oti_worknumber;
		this.oti_sex = oti_sex;
		this.oti_birthday = oti_birthday;
		this.oti_inschooldate = oti_inschooldate;
		this.oti_workstate = oti_workstate;
		this.oti_teachertype = oti_teachertype;
		this.oti_departmentnumber = oti_departmentnumber;
		this.oti_departmentname = oti_departmentname;
		this.oti_education = oti_education;
		this.oti_degree = oti_degree;
		this.oti_professionaltitle = oti_professionaltitle;
		this.oti_subjectcategory = oti_subjectcategory;
		this.oti_tutorcategory = oti_tutorcategory;
		this.oti_college = oti_college;
		this.oti_deadline = oti_deadline;
		this.oti_serialnumber = oti_serialnumber;
		this.oti_comments = oti_comments;
		this.oti_isnull = isnull;
	}
	//no id deadline
	public OtherTeacherInfo(String oti_name, String oti_worknumber, String oti_sex, Date oti_birthday,
			Date oti_inschooldate, String oti_workstate, String oti_teachertype, String oti_departmentnumber,
			String oti_departmentname, String oti_education, String oti_degree, String oti_professionaltitle,
			String oti_subjectcategory, String oti_tutorcategory, String oti_college, int oti_serialnumber,
			String oti_comments, int oti_isnull) {
		super();
		this.oti_name = oti_name;
		this.oti_worknumber = oti_worknumber;
		this.oti_sex = oti_sex;
		this.oti_birthday = oti_birthday;
		this.oti_inschooldate = oti_inschooldate;
		this.oti_workstate = oti_workstate;
		this.oti_teachertype = oti_teachertype;
		this.oti_departmentnumber = oti_departmentnumber;
		this.oti_departmentname = oti_departmentname;
		this.oti_education = oti_education;
		this.oti_degree = oti_degree;
		this.oti_professionaltitle = oti_professionaltitle;
		this.oti_subjectcategory = oti_subjectcategory;
		this.oti_tutorcategory = oti_tutorcategory;
		this.oti_college = oti_college;
		this.oti_serialnumber = oti_serialnumber;
		this.oti_comments = oti_comments;
		this.oti_isnull = oti_isnull;
	}
	public int getOti_id() {
		return oti_id;
	}
	public void setOti_id(int oti_id) {
		this.oti_id = oti_id;
	}
	public String getOti_name() {
		return oti_name;
	}
	public void setOti_name(String oti_name) {
		this.oti_name = oti_name;
	}
	public String getOti_worknumber() {
		return oti_worknumber;
	}
	public void setOti_worknumber(String oti_worknumber) {
		this.oti_worknumber = oti_worknumber;
	}
	public String getOti_sex() {
		return oti_sex;
	}
	public void setOti_sex(String oti_sex) {
		this.oti_sex = oti_sex;
	}
	public Date getOti_birthday() {
		return oti_birthday;
	}
	public void setOti_birthday(Date oti_birthday) {
		this.oti_birthday = oti_birthday;
	}
	public Date getOti_inschooldate() {
		return oti_inschooldate;
	}
	public void setOti_inschooldate(Date oti_inschooldate) {
		this.oti_inschooldate = oti_inschooldate;
	}
	public String getOti_workstate() {
		return oti_workstate;
	}
	public void setOti_workstate(String oti_workstate) {
		this.oti_workstate = oti_workstate;
	}
	public String getOti_teachertype() {
		return oti_teachertype;
	}
	public void setOti_teachertype(String oti_teachertype) {
		this.oti_teachertype = oti_teachertype;
	}
	public String getOti_departmentnumber() {
		return oti_departmentnumber;
	}
	public void setOti_departmentnumber(String oti_departmentnumber) {
		this.oti_departmentnumber = oti_departmentnumber;
	}
	public String getOti_departmentname() {
		return oti_departmentname;
	}
	public void setOti_departmentname(String oti_departmentname) {
		this.oti_departmentname = oti_departmentname;
	}
	public String getOti_education() {
		return oti_education;
	}
	public void setOti_education(String oti_education) {
		this.oti_education = oti_education;
	}
	public String getOti_degree() {
		return oti_degree;
	}
	public void setOti_degree(String oti_degree) {
		this.oti_degree = oti_degree;
	}
	public String getOti_professionaltitle() {
		return oti_professionaltitle;
	}
	public void setOti_professionaltitle(String oti_professionaltitle) {
		this.oti_professionaltitle = oti_professionaltitle;
	}
	public String getOti_subjectcategory() {
		return oti_subjectcategory;
	}
	public void setOti_subjectcategory(String oti_subjectcategory) {
		this.oti_subjectcategory = oti_subjectcategory;
	}
	public String getOti_tutorcategory() {
		return oti_tutorcategory;
	}
	public void setOti_tutorcategory(String oti_tutorcategory) {
		this.oti_tutorcategory = oti_tutorcategory;
	}
	public String getOti_college() {
		return oti_college;
	}
	public void setOti_college(String oti_college) {
		this.oti_college = oti_college;
	}
	public Date getOti_deadline() {
		return oti_deadline;
	}
	public void setOti_deadline(Date oti_deadline) {
		this.oti_deadline = oti_deadline;
	}
	public int getOti_serialnumber() {
		return oti_serialnumber;
	}
	public void setOti_serialnumber(int oti_serialnumber) {
		this.oti_serialnumber = oti_serialnumber;
	}
	public String getOti_comments() {
		return oti_comments;
	}
	public void setOti_comments(String oti_comments) {
		this.oti_comments = oti_comments;
	}
	public int getOti_isnull() {
		return oti_isnull;
	}
	public void setOti_isnull(int oti_isnull) {
		this.oti_isnull = oti_isnull;
	}
	
	
	
	/**
	private int oti_id;				
	private String oti_name;								//姓名
	private String oti_worknumber;							//工号
	private XingBie oti_sex;								//性别
	private Date oti_birthday;								//出生年月
	private Date oti_inschooldate;							//入校时间
	private WorkingState oti_workstate;						//任职状态
	private ShiZiLeiBie oti_teachertype;					//师资类别
	private String oti_departmentnumber;					//单位号	
	private String oti_departmentname;						//单位名称	
	private XueLi oti_education;							//学历
	private ZuiGaoXueWei oti_degree;						//最高学位
	private ZhuanYeJiShuZhiCheng oti_professionaltitle;		//专业技术职称
	private XueKeLeiBie oti_subjectcategory;				//学科类别
	private DaoShiLeiBie oti_tutorcategory;					//导师类别	
	private String oti_college;								//填报学院
	private Date oti_deadline;								//截止时间
	private int oti_serialnumber;							//序列号
	private String oti_comments;							//审核意见
	
	public OtherTeacherInfo(){
		super();
	}
	
	

	public OtherTeacherInfo(int oti_id, String oti_name, String oti_worknumber, XingBie oti_sex, Date oti_birthday,
			Date oti_inschooldate, WorkingState oti_workstate, ShiZiLeiBie oti_teachertype, String oti_departmentnumber,
			String oti_departmentname, XueLi oti_education, ZuiGaoXueWei oti_degree,
			ZhuanYeJiShuZhiCheng oti_professionaltitle, XueKeLeiBie oti_subjectcategory, DaoShiLeiBie oti_tutorcategory,
			String oti_comments) {
		super();
		this.oti_id = oti_id;
		this.oti_name = oti_name;
		this.oti_worknumber = oti_worknumber;
		this.oti_sex = oti_sex;
		this.oti_birthday = oti_birthday;
		this.oti_inschooldate = oti_inschooldate;
		this.oti_workstate = oti_workstate;
		this.oti_teachertype = oti_teachertype;
		this.oti_departmentnumber = oti_departmentnumber;
		this.oti_departmentname = oti_departmentname;
		this.oti_education = oti_education;
		this.oti_degree = oti_degree;
		this.oti_professionaltitle = oti_professionaltitle;
		this.oti_subjectcategory = oti_subjectcategory;
		this.oti_tutorcategory = oti_tutorcategory;
		this.oti_comments = oti_comments;
	}



	public OtherTeacherInfo(int oti_id, String oti_name, String oti_worknumber, XingBie oti_sex, Date oti_birthday,
			Date oti_inschooldate, WorkingState oti_workstate, ShiZiLeiBie oti_teachertype, String oti_departmentnumber,
			String oti_departmentname, XueLi oti_education, ZuiGaoXueWei oti_degree,
			ZhuanYeJiShuZhiCheng oti_professionaltitle, XueKeLeiBie oti_subjectcategory, DaoShiLeiBie oti_tutorcategory,
			int oti_serialnumber, String oti_comments) {
		super();
		this.oti_id = oti_id;
		this.oti_name = oti_name;
		this.oti_worknumber = oti_worknumber;
		this.oti_sex = oti_sex;
		this.oti_birthday = oti_birthday;
		this.oti_inschooldate = oti_inschooldate;
		this.oti_workstate = oti_workstate;
		this.oti_teachertype = oti_teachertype;
		this.oti_departmentnumber = oti_departmentnumber;
		this.oti_departmentname = oti_departmentname;
		this.oti_education = oti_education;
		this.oti_degree = oti_degree;
		this.oti_professionaltitle = oti_professionaltitle;
		this.oti_subjectcategory = oti_subjectcategory;
		this.oti_tutorcategory = oti_tutorcategory;
		this.oti_serialnumber = oti_serialnumber;
		this.oti_comments = oti_comments;
	}



	public OtherTeacherInfo(String oti_name, String oti_worknumber, XingBie oti_sex, Date oti_birthday,
			Date oti_inschooldate, WorkingState oti_workstate, ShiZiLeiBie oti_teachertype, String oti_departmentnumber,
			String oti_departmentname, XueLi oti_education, ZuiGaoXueWei oti_degree,
			ZhuanYeJiShuZhiCheng oti_professionaltitle, XueKeLeiBie oti_subjectcategory, DaoShiLeiBie oti_tutorcategory,
			String oti_college, int oti_serialnumber, String oti_comments) {
		super();
		this.oti_name = oti_name;
		this.oti_worknumber = oti_worknumber;
		this.oti_sex = oti_sex;
		this.oti_birthday = oti_birthday;
		this.oti_inschooldate = oti_inschooldate;
		this.oti_workstate = oti_workstate;
		this.oti_teachertype = oti_teachertype;
		this.oti_departmentnumber = oti_departmentnumber;
		this.oti_departmentname = oti_departmentname;
		this.oti_education = oti_education;
		this.oti_degree = oti_degree;
		this.oti_professionaltitle = oti_professionaltitle;
		this.oti_subjectcategory = oti_subjectcategory;
		this.oti_tutorcategory = oti_tutorcategory;
		this.oti_college = oti_college;
		this.oti_serialnumber = oti_serialnumber;
		this.oti_comments = oti_comments;
	}



	public OtherTeacherInfo(int oti_id, String oti_name, String oti_worknumber, XingBie oti_sex, Date oti_birthday,
			Date oti_inschooldate, WorkingState oti_workstate, ShiZiLeiBie oti_teachertype, String oti_departmentnumber,
			String oti_departmentname, XueLi oti_education, ZuiGaoXueWei oti_degree,
			ZhuanYeJiShuZhiCheng oti_professionaltitle, XueKeLeiBie oti_subjectcategory, DaoShiLeiBie oti_tutorcategory,
			String oti_college, Date oti_deadline, int oti_serialnumber, String oti_comments) {
		super();
		this.oti_id = oti_id;
		this.oti_name = oti_name;
		this.oti_worknumber = oti_worknumber;
		this.oti_sex = oti_sex;
		this.oti_birthday = oti_birthday;
		this.oti_inschooldate = oti_inschooldate;
		this.oti_workstate = oti_workstate;
		this.oti_teachertype = oti_teachertype;
		this.oti_departmentnumber = oti_departmentnumber;
		this.oti_departmentname = oti_departmentname;
		this.oti_education = oti_education;
		this.oti_degree = oti_degree;
		this.oti_professionaltitle = oti_professionaltitle;
		this.oti_subjectcategory = oti_subjectcategory;
		this.oti_tutorcategory = oti_tutorcategory;
		this.oti_college = oti_college;
		this.oti_deadline = oti_deadline;
		this.oti_serialnumber = oti_serialnumber;
		this.oti_comments = oti_comments;
	}



	public int getOti_id() {
		return oti_id;
	}

	public void setOti_id(int oti_id) {
		this.oti_id = oti_id;
	}

	public String getOti_name() {
		return oti_name;
	}

	public void setOti_name(String oti_name) {
		this.oti_name = oti_name;
	}

	public String getOti_worknumber() {
		return oti_worknumber;
	}

	public void setOti_worknumber(String oti_worknumber) {
		this.oti_worknumber = oti_worknumber;
	}

	public XingBie getOti_sex() {
		return oti_sex;
	}

	public void setOti_sex(XingBie oti_sex) {
		this.oti_sex = oti_sex;
	}

	public Date getOti_birthday() {
		return oti_birthday;
	}

	public void setOti_birthday(Date oti_birthday) {
		this.oti_birthday = oti_birthday;
	}

	public Date getOti_inschooldate() {
		return oti_inschooldate;
	}

	public void setOti_inschooldate(Date oti_inschooldate) {
		this.oti_inschooldate = oti_inschooldate;
	}

	public WorkingState getOti_workstate() {
		return oti_workstate;
	}

	public void setOti_workstate(WorkingState oti_workstate) {
		this.oti_workstate = oti_workstate;
	}

	public ShiZiLeiBie getOti_teachertype() {
		return oti_teachertype;
	}

	public void setOti_teachertype(ShiZiLeiBie oti_teachertype) {
		this.oti_teachertype = oti_teachertype;
	}

	public String getOti_departmentnumber() {
		return oti_departmentnumber;
	}

	public void setOti_departmentnumber(String oti_departmentnumber) {
		this.oti_departmentnumber = oti_departmentnumber;
	}

	public String getOti_departmentname() {
		return oti_departmentname;
	}

	public void setOti_departmentname(String oti_departmentname) {
		this.oti_departmentname = oti_departmentname;
	}

	public XueLi getOti_education() {
		return oti_education;
	}

	public void setOti_education(XueLi oti_education) {
		this.oti_education = oti_education;
	}

	public ZuiGaoXueWei getOti_degree() {
		return oti_degree;
	}

	public void setOti_degree(ZuiGaoXueWei oti_degree) {
		this.oti_degree = oti_degree;
	}

	public ZhuanYeJiShuZhiCheng getOti_professionaltitle() {
		return oti_professionaltitle;
	}

	public void setOti_professionaltitle(ZhuanYeJiShuZhiCheng oti_professionaltitle) {
		this.oti_professionaltitle = oti_professionaltitle;
	}

	public XueKeLeiBie getOti_subjectcategory() {
		return oti_subjectcategory;
	}

	public void setOti_subjectcategory(XueKeLeiBie oti_subjectcategory) {
		this.oti_subjectcategory = oti_subjectcategory;
	}

	public DaoShiLeiBie getOti_tutorcategory() {
		return oti_tutorcategory;
	}

	public void setOti_tutorcategory(DaoShiLeiBie oti_tutorcategory) {
		this.oti_tutorcategory = oti_tutorcategory;
	}

	public String getOti_college() {
		return oti_college;
	}

	public void setOti_college(String oti_college) {
		this.oti_college = oti_college;
	}

	public Date getOti_deadline() {
		return oti_deadline;
	}

	public void setOti_deadline(Date oti_deadline) {
		this.oti_deadline = oti_deadline;
	}

	public int getOti_serialnumber() {
		return oti_serialnumber;
	}

	public void setOti_serialnumber(int oti_serialnumber) {
		this.oti_serialnumber = oti_serialnumber;
	}

	public String getOti_comments() {
		return oti_comments;
	}

	public void setOti_comments(String oti_comments) {
		this.oti_comments = oti_comments;
	}**/
		
	
	
}
