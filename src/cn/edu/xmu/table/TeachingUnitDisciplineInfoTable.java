package cn.edu.xmu.table;


/**
 * 
 * @author zsj
 * 学位点概况
 *
 */
public class TeachingUnitDisciplineInfoTable {
	public static final String TABLE_DESCIPLINECONSTRUCTION = "dc_disciplineconstruction";//学科建设表
	public static final String TABLE_IMPORTANTSTUDY = "ip_importantstudy";//重点学科表
	
	public static final String bachelorDegree = "dc_undertotal";//本科专业数
	public static final String mobileStationForPostDoctor = "dc_doctorstation";//博士后流动站
	public static final String doctorFirstLevelDiscipline = "dc_docgrantone";//博士学位授权一级学科点
	public static final String doctorSecondLevelDiscipline = "dc_docgranttwo";//博士学位授权二级学科点（不含一级覆盖点）
	public static final String masterFirstLevelDiscipline = "dc_masgrantone";//硕士学位授权一级学科点
	public static final String masterSecondLevelDiscipline = "dc_masgranttwo";//硕士学位授权二级学科点（不含一级覆盖点）
	public static final String keyDiscipline = "";//重点学科数
	
	public String college;//填报学院
	

}
