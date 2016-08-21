package cn.edu.xmu.table;

/**
 * 表2-5-2 图书当年新增情况(自然年)
 * @author zhantu
 * date 2015-06-30
 */
public class NewBooksthatYearTable {
	//表名
	public static final String TABLE_NAME = "nby_newbooksthatyear";
	//ID
	public static final String NBY_ID = "nby_id";
	//当年新增纸质图书（册）
	public static final String NBY_PAPERBOOKSNUMBER = "nby_paperbooksnumber";
	//当年新增电子图书（种)
	public static final String NBY_EBOOKSNUMBER = "nby_ebooksnumber";
	//当年文献购置费（万元）
	public static final String NBY_DOCUMENTACQUISITIONCOST = "nby_documentacquisitioncost";
	//当年图书流通量（本次）
	public static final String NBY_BOOKCIRCULATION = "nby_bookcirculation";
	//当年电子资源访问量（次）
	public static final String NBY_ELECTRONICRESOURCEACCESS = "nby_electronicresourceaccess";
	//序列号
	public static final String NBY_SERIALNUMBER = "nby_serialnumber";
	//截止日期
	public static final String NBY_DEADLINE = "nby_deadline";
	//所属学院
	public static final String NBY_COLLEGE = "nby_college";
	//审核意见
	public static final String NBY_COMMENTS = "nby_comments";
	//记录是否存在空值
	public static final String ISNULL = "isnull";
}
