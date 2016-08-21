package cn.edu.xmu.table;

import org.eclipse.jdt.internal.compiler.classfmt.FieldInfoWithAnnotation;

/**
 * 
 * @author xiaoping 附表6-2-2-3本科生境内交流情况（学年） date 2015-7-9
 *
 */
public class UndergraStuInlandCommuTable
{
	public static final String TABLE_NAME = "usic_undergrastuinlandcommu";//表名
	public static final String USIC_ID = "usic_id";//主键
	public static final String USIC_INSTITUTE = "usic_institute";//学院
	public static final String USIC_OUTNUMBER = "usic_outnumber";//本校到外校学生数
	public static final String USIC_INNUMBER = "usic_innumber";//外校到本校学生数
	public static final String USIC_SERIALNUMBER = "usic_serialnumber";//序列号
	public static final String USIC_DEADLINE = "usic_deadline";//截止日期
	public static final String USIC_COLLEGE = "usic_college";//所属学院
	public static final String USIC_COMMENTS = "usic_comments";//审核意见
	public static final String USIC_ISNULL = "usic_isnull";//记录中是否存在空字段，0为完整   1为存在空字段
}
