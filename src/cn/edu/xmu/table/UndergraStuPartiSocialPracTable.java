package cn.edu.xmu.table;

import org.apache.poi.hssf.record.formula.functions.FinanceFunction;

/**
 * 
 * @author xiaoping 附表5-4-3 本科生参与暑期社会实践情况  date 2015-7-10
 *
 */
public class UndergraStuPartiSocialPracTable
{
	public static final String TABLE_NAME = "uspsp_undergrastupartisocialprac";//表名
	public static final String USPSP_ID = "uspsp_id";//主键
	public static final String USPSP_DEPARTMENT = "uspsp_department";//单位
	public static final String USPSP_FOCUSPRACNUM= "uspsp_focuspracnum";//参加集中社会实践人数
	public static final String USPSP_SCATTERPRACNUM = "uspsp_scatterpracnum";//分散社会实践人数
	public static final String USPSP_SUBTOTAL = "uspsp_subtotal";//小计
	public static final String USPSP_SERIALNUMBER = "uspsp_serialnumber";//序列号
	public static final String USPSP_DEADLINE = "uspsp_deadline";//截止日期
	public static final String USPSP_COLLEGE = "uspsp_college";//所属学院
	public static final String USPSP_COMMENTS = "uspsp_comments";//审核意见
	public static final String USPSP_ISNULL = "USPSP_ISNULL";//记录中是否存在空字段，0为完整   1为存在空字段
}
