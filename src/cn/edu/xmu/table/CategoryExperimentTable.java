package cn.edu.xmu.table;

/**
 * 表5-3-3 2014-2015学年分专业（大类）实验情况
 * 
 * @author Gy
 * 
 */
public class CategoryExperimentTable {

	public static final String TABLE_NAME = "ce_categoryexperiment";// 表名
	public static final String CE_ID = "ce_id";// id
	public static final String CE_COLLEGES = "ce_colleges";// 学院
	public static final String CE_MAJORNAME = "ce_majorname";// 专业（大类）名称
	public static final String CE_CONTAINEXPERIMENT = "ce_containexperiment";// 有实验的课程（门）
	public static final String CE_SINLGEEXPERIMENT = "ce_singleexperiment";// 独立设置的实验课程（门）
	public static final String CE_NOSINGELEEXPERIMENT = "ce_nosingleexperiment";// 非独立设置的实验课（门）
	public static final String CE_DESIGNEXPERIMENT = "ce_designexperiment";// 其中综合性、设计性实验教学（门）
	public static final String CE_PERCENTAGE = "ce_percentage";// 实验开出率（%）
	public static final String CE_MAJORNUMBER ="ce_majornumber";//专业代码
	public static final String CE_SERIALNUMBER = "ce_serialnumber";//序列号
	public static final String CE_DEADLINE = "ce_deadline";//截止日期
	public static final String CE_COLLEGE = "ce_college";//所属学院
	public static final String CE_COMMENTS = "ce_comments";//审核意见
	
}
