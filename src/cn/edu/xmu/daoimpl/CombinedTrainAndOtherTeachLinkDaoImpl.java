package cn.edu.xmu.daoimpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.dao.CombinedTrainAndOtherTeachLinkDao;
import cn.edu.xmu.entity.CombinedTrainAndOtherTeachLink;
import cn.edu.xmu.table.CombinedTrainAndOtherTeachLinkTable;
import cn.edu.xmu.table.ForeignExchangeTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;

/**
 * 附表5-3-4 2014-2015学年分专业毕业综合训练、其他教学环节安排情况
 * @author chunfeng
 *
 */
public class CombinedTrainAndOtherTeachLinkDaoImpl extends BaseDaoImpl<CombinedTrainAndOtherTeachLink>
		implements CombinedTrainAndOtherTeachLinkDao {

	@Override
	public List<CombinedTrainAndOtherTeachLink> getCombinedTrainAndOtherTeachLink(int start, int end,
			String sortStr, String orderStr, Map<String, String> params, Date deadline) {

		String sql = " select tmp.* from ( " + " select * from "
				+ CombinedTrainAndOtherTeachLinkTable.TABLE_NAME + " where 1=1 ";
		if (deadline != null) {
			sql += String.format("and "+CombinedTrainAndOtherTeachLinkTable.CTAOTL_DEADLINE+" like  '%s%%' ", deadline);
		}
		if (!(params == null || params.keySet().size() == 0)) {
			for (Object object : params.keySet()) {

				String key = object.toString();
				String value = params.get(key).toString();
				sql += String.format("and %s like  '%%%s%%' ", key, value);
			}
		}

		sql += " order by " + sortStr + " " + orderStr + " ) tmp limit "
				+ start + " ," + end;

		System.out.println(sql);

		List<CombinedTrainAndOtherTeachLink> combinedTrainAndOtherTeachLinks = new ArrayList<CombinedTrainAndOtherTeachLink>();
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		try {
			//System.out.println(sql);
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				int ctaotl_id = resultSet.getInt(CombinedTrainAndOtherTeachLinkTable.CTAOTL_ID);
			    String ctaotl_collegename = resultSet.getString(CombinedTrainAndOtherTeachLinkTable.CTAOTL_COLLEGENAME);  //学院
			    String ctaotl_major = resultSet.getString(CombinedTrainAndOtherTeachLinkTable.CTAOTL_MAJOR); //专业
			    String ctaotl_majornumber = resultSet.getString(CombinedTrainAndOtherTeachLinkTable.CTAOTL_MAJORNUMBER);  //专业代码
			    
			    Integer ctaotl_graduatestudent = resultSet.getInt(CombinedTrainAndOtherTeachLinkTable.CTAOTL_GRADUATESTUDENT); //毕业班人数
			    if(ctaotl_graduatestudent == -1) ctaotl_graduatestudent = null;
			    Integer ctaotl_ct_t_total = resultSet.getInt(CombinedTrainAndOtherTeachLinkTable.CTAOTL_CT_T_TOTAL); //分专业毕业综合训练/指导毕业综合训练教师数/总数
			    if(ctaotl_ct_t_total == -1) ctaotl_ct_t_total = null;
			    
			    Integer ctaotl_ct_t_fullteacher = resultSet.getInt(CombinedTrainAndOtherTeachLinkTable.CTAOTL_CT_T_FULLTEACHER);//分专业毕业综合训练/指导毕业综合训练教师数/专任教师
			    if(ctaotl_ct_t_fullteacher == -1) ctaotl_ct_t_fullteacher = null;
			    Integer ctaotl_ct_t_partteacher = resultSet.getInt(CombinedTrainAndOtherTeachLinkTable.CTAOTL_CT_T_PARTTEACHER); //分专业毕业综合训练/指导毕业综合训练教师数/外聘教师
			    if(ctaotl_ct_t_partteacher == -1) ctaotl_ct_t_partteacher = null;
			    
			    Integer ctaotl_ct_t_senior = resultSet.getInt(CombinedTrainAndOtherTeachLinkTable.CTAOTL_CT_T_SENIOR); //分专业毕业综合训练/指导毕业综合训练教师数/正高级
			    if(ctaotl_ct_t_senior == -1) ctaotl_ct_t_senior = null;
			    Integer ctaotl_ct_t_subsenior = resultSet.getInt(CombinedTrainAndOtherTeachLinkTable.CTAOTL_CT_T_SUBSENIOR); //分专业毕业综合训练/指导毕业综合训练教师数/副高级
			    if(ctaotl_ct_t_subsenior == -1) ctaotl_ct_t_subsenior = null;
			    
			    Integer ctaotl_ct_p_total = resultSet.getInt(CombinedTrainAndOtherTeachLinkTable.CTAOTL_CT_P_TOTAL);  //分专业毕业综合训练/毕业综合训练课题/总数
			    if(ctaotl_ct_p_total == -1) ctaotl_ct_p_total = null;
			    Integer ctaotl_ct_p_socialfinish = resultSet.getInt(CombinedTrainAndOtherTeachLinkTable.CTAOTL_CT_P_SOCIALFINISH);//分专业毕业综合训练/毕业综合训练课题/在实验、实习、工程实践和社会调查等社会实践中完成数
			    if(ctaotl_ct_p_socialfinish == -1) ctaotl_ct_p_socialfinish = null;
			    
			    Integer ctaotl_ct_p_fromscience = resultSet.getInt(CombinedTrainAndOtherTeachLinkTable.CTAOTL_CT_P_FROMSCIENCE); //分专业毕业综合训练/毕业综合训练课题/来自教师科研课题
			    if(ctaotl_ct_p_fromscience == -1) ctaotl_ct_p_fromscience = null;
			    Integer ctaotl_ct_p_fromproduce = resultSet.getInt(CombinedTrainAndOtherTeachLinkTable.CTAOTL_CT_P_FROMPRODUCE); //分专业毕业综合训练/毕业综合训练课题/来自生产管理一线
			    if(ctaotl_ct_p_fromproduce == -1) ctaotl_ct_p_fromproduce = null;
			    
			    Integer ctaotl_ct_p_fromsocial = resultSet.getInt(CombinedTrainAndOtherTeachLinkTable.CTAOTL_CT_P_FROMSOCIAL); //分专业毕业综合训练/毕业综合训练课题/来自社会实践
			    if(ctaotl_ct_p_fromsocial == -1) ctaotl_ct_p_fromsocial = null;			    
			    Integer ctaotl_ct_p_other = resultSet.getInt(CombinedTrainAndOtherTeachLinkTable.CTAOTL_CT_P_OTHER); //分专业毕业综合训练/毕业综合训练课题/其他
			    if(ctaotl_ct_p_other == -1) ctaotl_ct_p_other = null;
			    
			    Float ctaotl_ot_pro_credit = resultSet.getFloat(CombinedTrainAndOtherTeachLinkTable.CTAOTL_OT_PRO_CREDIT); //其他教学环节/见习或认识实习/学分
			    if(ctaotl_ot_pro_credit == -1.0) ctaotl_ot_pro_credit = null;
			    Integer ctaotl_ot_pro_days = resultSet.getInt(CombinedTrainAndOtherTeachLinkTable.CTAOTL_OT_PRO_DAYS); //其他教学环节/见习或认识实习/天数
			    if(ctaotl_ot_pro_days == -1) ctaotl_ot_pro_days = null;
			    
			    Float ctaotl_ot_pra_credit = resultSet.getFloat(CombinedTrainAndOtherTeachLinkTable.CTAOTL_OT_PRA_CREDIT); //其他教学环节/毕业实习/学分
			    if(ctaotl_ot_pra_credit == -1.0) ctaotl_ot_pra_credit = null;
			    Integer ctaotl_ot_pra_days = resultSet.getInt(CombinedTrainAndOtherTeachLinkTable.CTAOTL_OT_PRA_DAYS); //其他教学环节/毕业实习/天数
			    if(ctaotl_ot_pra_days == -1) ctaotl_ot_pra_days = null;
			    
			    Float ctaotl_ot_ter_credit = resultSet.getFloat(CombinedTrainAndOtherTeachLinkTable.CTAOTL_OT_TER_CREDIT); //其他教学环节/学年论文/学分 
			    if(ctaotl_ot_ter_credit == -1.0) ctaotl_ot_ter_credit = null;
			    Integer ctaotl_ot_ter_days = resultSet.getInt(CombinedTrainAndOtherTeachLinkTable.CTAOTL_OT_TER_DAYS); //其他教学环节/学年论文/天数
			    if(ctaotl_ot_ter_days == -1) ctaotl_ot_ter_days = null;
			    
			    Float ctaotl_ot_the_credit = resultSet.getFloat(CombinedTrainAndOtherTeachLinkTable.CTAOTL_OT_THE_CREDIT); //其他教学环节/毕业论文/学分
			    if(ctaotl_ot_the_credit == -1.0) ctaotl_ot_the_credit = null;
			    Integer ctaotl_ot_the_days = resultSet.getInt(CombinedTrainAndOtherTeachLinkTable.CTAOTL_OT_THE_DAYS); //其他教学环节/毕业论文/天数
			    if(ctaotl_ot_the_days == -1) ctaotl_ot_the_days = null;
			    
			    Float ctaotl_ot_oth_credit = resultSet.getFloat(CombinedTrainAndOtherTeachLinkTable.CTAOTL_OT_OTH_CREDIT); //其他教学环节/其他/学分
			    if(ctaotl_ot_oth_credit == -1.0) ctaotl_ot_oth_credit = null;
			    Integer ctaotl_ot_oth_days = resultSet.getInt(CombinedTrainAndOtherTeachLinkTable.CTAOTL_OT_OTH_DAYS); //其他教学环节/其他/天数
			    if(ctaotl_ot_oth_days == -1) ctaotl_ot_oth_days = null;
			    
			    Float ctaotl_ot_tot_credit = resultSet.getFloat(CombinedTrainAndOtherTeachLinkTable.CTAOTL_OT_TOT_CREDIT); //其他教学环节/合计/学分
			    if(ctaotl_ot_tot_credit == -1.0) ctaotl_ot_tot_credit = null;
			    Integer ctaotl_ot_tot_days = resultSet.getInt(CombinedTrainAndOtherTeachLinkTable.CTAOTL_OT_TOT_DAYS);  //其他教学环节/合计/天数
			    if(ctaotl_ot_tot_days == -1) ctaotl_ot_tot_days = null;  
			    
			    String ctaotl_college = resultSet.getString(CombinedTrainAndOtherTeachLinkTable.CTAOTL_COLLEGE);
			    int ctaotl_serialnumber = resultSet.getInt(CombinedTrainAndOtherTeachLinkTable.CTAOTL_SERIALNUMBER);//序列号
			  	Date ctaotl_deadline = resultSet.getDate(CombinedTrainAndOtherTeachLinkTable.CTAOTL_DEADLINE);//截止日期
			  	String ctaotl_comments = resultSet.getString(CombinedTrainAndOtherTeachLinkTable.CTAOTL_COMMENTS);//审核意见
				if(null == ctaotl_comments){
					ctaotl_comments = "";
				}
				int ctaotl_isnull = resultSet.getInt(CombinedTrainAndOtherTeachLinkTable.CTAOTL_ISNULL);//序列号
				
				CombinedTrainAndOtherTeachLink combinedTrainAndOtherTeachLink = new CombinedTrainAndOtherTeachLink(
						ctaotl_id,ctaotl_collegename,ctaotl_major,ctaotl_majornumber,
			            ctaotl_graduatestudent,ctaotl_ct_t_total,ctaotl_ct_t_fullteacher,ctaotl_ct_t_partteacher,
			            ctaotl_ct_t_senior,ctaotl_ct_t_subsenior,ctaotl_ct_p_total,ctaotl_ct_p_socialfinish,ctaotl_ct_p_fromscience,
			            ctaotl_ct_p_fromproduce,ctaotl_ct_p_fromsocial, ctaotl_ct_p_other, ctaotl_ot_pro_credit, ctaotl_ot_pro_days,
			            ctaotl_ot_pra_credit, ctaotl_ot_pra_days, ctaotl_ot_ter_credit, ctaotl_ot_ter_days, ctaotl_ot_the_credit,
			            ctaotl_ot_the_days, ctaotl_ot_oth_credit, ctaotl_ot_oth_days, ctaotl_ot_tot_credit, ctaotl_ot_tot_days,
			            ctaotl_college,ctaotl_serialnumber, ctaotl_deadline, ctaotl_comments, ctaotl_isnull);

				combinedTrainAndOtherTeachLinks.add(combinedTrainAndOtherTeachLink);
			}
			return combinedTrainAndOtherTeachLinks;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}

	}

	@Override
	public int getCombinedTrainAndOtherTeachLinkCount(Map queryParams) {
		int count = 0;
		String sql = "select count(*) from " + CombinedTrainAndOtherTeachLinkTable.TABLE_NAME
				+ " where 1 = 1";
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		if (queryParams != null)
		{
			for (Object object : queryParams.keySet())
			{
				String key = object.toString();
				String value = queryParams.get(key).toString();
				sql += String.format(" and  %s like  '%%%s%%' ", key, value);
			}
			// sql += String.format(" or %s ='%s'", "college","");
		}
		
		try {
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			resultSet.next();
			count = resultSet.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} finally{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}

		System.out.println(count);
		return count;
	}

	@Override
	public boolean batchDelete(String[] ctaotlids) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();

		for (String ctaotlid : ctaotlids) {
			String sql = "delete from " + CombinedTrainAndOtherTeachLinkTable.TABLE_NAME
					+ " where " + CombinedTrainAndOtherTeachLinkTable.CTAOTL_ID + " = '" + ctaotlid
					+ "'";
			System.out.println(sql);
			try {
				stmt.executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		JdbcUtils_DBCP.release(connection, stmt, null);

		return true;
	}

	@Override
	public int addCombinedTrainAndOtherTeachLink(CombinedTrainAndOtherTeachLink CombinedTrainAndOtherTeachLink) {
		int result = 0;

		String sql2 = "update " + CombinedTrainAndOtherTeachLinkTable.TABLE_NAME + " set "
				+ CombinedTrainAndOtherTeachLinkTable.CTAOTL_SERIALNUMBER + " = "
				+ CombinedTrainAndOtherTeachLinkTable.CTAOTL_SERIALNUMBER + " +1 where "
				+ CombinedTrainAndOtherTeachLinkTable.CTAOTL_SERIALNUMBER + ">="
				+ CombinedTrainAndOtherTeachLink.getCtaotl_serialnumber();
		Connection connection2 = null;
		try {
			connection2 = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		PreparedStatement pstmt2 = null;
		try {
			pstmt2 = connection2.prepareStatement(sql2);
			result = pstmt2.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			try {
				JdbcUtils_DBCP.release(connection2, pstmt2, null);
			} catch (Exception e2) {
				return 0;
			}
		}

		String sql = "insert into " + CombinedTrainAndOtherTeachLinkTable.TABLE_NAME + "("
				+ CombinedTrainAndOtherTeachLinkTable.CTAOTL_COLLEGENAME + ","
				+ CombinedTrainAndOtherTeachLinkTable.CTAOTL_MAJOR + ","
				+ CombinedTrainAndOtherTeachLinkTable.CTAOTL_MAJORNUMBER + ","
				+ CombinedTrainAndOtherTeachLinkTable.CTAOTL_GRADUATESTUDENT + ","
				+ CombinedTrainAndOtherTeachLinkTable.CTAOTL_CT_T_TOTAL + ","
				+ CombinedTrainAndOtherTeachLinkTable.CTAOTL_CT_T_FULLTEACHER + ","
				+ CombinedTrainAndOtherTeachLinkTable.CTAOTL_CT_T_PARTTEACHER + ","
				+ CombinedTrainAndOtherTeachLinkTable.CTAOTL_CT_T_SENIOR + ","
				+ CombinedTrainAndOtherTeachLinkTable.CTAOTL_CT_T_SUBSENIOR + ","
				+ CombinedTrainAndOtherTeachLinkTable.CTAOTL_CT_P_TOTAL + ","
				+ CombinedTrainAndOtherTeachLinkTable.CTAOTL_CT_P_SOCIALFINISH + ","
				+ CombinedTrainAndOtherTeachLinkTable.CTAOTL_CT_P_FROMSCIENCE + ","
				+ CombinedTrainAndOtherTeachLinkTable.CTAOTL_CT_P_FROMPRODUCE + ","
				+ CombinedTrainAndOtherTeachLinkTable.CTAOTL_CT_P_FROMSOCIAL + ","
				+ CombinedTrainAndOtherTeachLinkTable.CTAOTL_CT_P_OTHER + ","
				+ CombinedTrainAndOtherTeachLinkTable.CTAOTL_OT_PRO_CREDIT + ","
				+ CombinedTrainAndOtherTeachLinkTable.CTAOTL_OT_PRO_DAYS + ","
				+ CombinedTrainAndOtherTeachLinkTable.CTAOTL_OT_PRA_CREDIT + ","
				+ CombinedTrainAndOtherTeachLinkTable.CTAOTL_OT_PRA_DAYS + ","
				+ CombinedTrainAndOtherTeachLinkTable.CTAOTL_OT_TER_CREDIT + ","
				+ CombinedTrainAndOtherTeachLinkTable.CTAOTL_OT_TER_DAYS + ","
				+ CombinedTrainAndOtherTeachLinkTable.CTAOTL_OT_THE_CREDIT + ","
				+ CombinedTrainAndOtherTeachLinkTable.CTAOTL_OT_THE_DAYS + ","
				+ CombinedTrainAndOtherTeachLinkTable.CTAOTL_OT_OTH_CREDIT + ","
				+ CombinedTrainAndOtherTeachLinkTable.CTAOTL_OT_OTH_DAYS + ","
				+ CombinedTrainAndOtherTeachLinkTable.CTAOTL_OT_TOT_CREDIT + ","
				+ CombinedTrainAndOtherTeachLinkTable.CTAOTL_OT_TOT_DAYS + ","	
				+ CombinedTrainAndOtherTeachLinkTable.CTAOTL_COLLEGE + ","
				+ CombinedTrainAndOtherTeachLinkTable.CTAOTL_SERIALNUMBER+ ","
				+ CombinedTrainAndOtherTeachLinkTable.CTAOTL_ISNULL + ")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PreparedStatement pstmt = null;

		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, CombinedTrainAndOtherTeachLink.getCtaotl_collegename());
			pstmt.setString(2, CombinedTrainAndOtherTeachLink.getCtaotl_major());
			pstmt.setString(3, CombinedTrainAndOtherTeachLink.getCtaotl_majornumber());
			pstmt.setInt(4, CombinedTrainAndOtherTeachLink.getCtaotl_graduatestudent());
			pstmt.setInt(5, CombinedTrainAndOtherTeachLink.getCtaotl_ct_t_total());
			pstmt.setInt(6, CombinedTrainAndOtherTeachLink.getCtaotl_ct_t_fullteacher());
			pstmt.setInt(7, CombinedTrainAndOtherTeachLink.getCtaotl_ct_t_partteacher());
			pstmt.setInt(8, CombinedTrainAndOtherTeachLink.getCtaotl_ct_t_senior());
			pstmt.setInt(9, CombinedTrainAndOtherTeachLink.getCtaotl_ct_t_subsenior());
			pstmt.setInt(10, CombinedTrainAndOtherTeachLink.getCtaotl_ct_p_total());
			pstmt.setInt(11, CombinedTrainAndOtherTeachLink.getCtaotl_ct_p_socialfinish());
			pstmt.setInt(12, CombinedTrainAndOtherTeachLink.getCtaotl_ct_p_fromscience());
			pstmt.setInt(13, CombinedTrainAndOtherTeachLink.getCtaotl_ct_p_fromproduce());
			pstmt.setInt(14, CombinedTrainAndOtherTeachLink.getCtaotl_ct_p_fromsocial());
			pstmt.setInt(15, CombinedTrainAndOtherTeachLink.getCtaotl_ct_p_other());
			pstmt.setFloat(16, CombinedTrainAndOtherTeachLink.getCtaotl_ot_pro_credit());
			pstmt.setInt(17, CombinedTrainAndOtherTeachLink.getCtaotl_ot_pro_days());
			pstmt.setFloat(18, CombinedTrainAndOtherTeachLink.getCtaotl_ot_pra_credit());
			pstmt.setInt(19, CombinedTrainAndOtherTeachLink.getCtaotl_ot_pra_days());
			pstmt.setFloat(20, CombinedTrainAndOtherTeachLink.getCtaotl_ot_ter_credit());
			pstmt.setInt(21, CombinedTrainAndOtherTeachLink.getCtaotl_ot_ter_days());
			pstmt.setFloat(22, CombinedTrainAndOtherTeachLink.getCtaotl_ot_the_credit());
			pstmt.setInt(23, CombinedTrainAndOtherTeachLink.getCtaotl_ot_the_days());
			pstmt.setFloat(24, CombinedTrainAndOtherTeachLink.getCtaotl_ot_oth_credit());
			pstmt.setInt(25, CombinedTrainAndOtherTeachLink.getCtaotl_ot_oth_days());
			pstmt.setFloat(26, CombinedTrainAndOtherTeachLink.getCtaotl_ot_tot_credit());
			pstmt.setInt(27, CombinedTrainAndOtherTeachLink.getCtaotl_ot_tot_days());
			pstmt.setString(28, CombinedTrainAndOtherTeachLink.getCtaotl_college());
			pstmt.setInt(29, CombinedTrainAndOtherTeachLink.getCtaotl_serialnumber());
			pstmt.setInt(30, CombinedTrainAndOtherTeachLink.getCtaotl_isnull());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			result = -1;
		} finally{
			JdbcUtils_DBCP.release(connection, pstmt, null);
		}


		return result;
	}

	@Override
	public int alterCombinedTrainAndOtherTeachLink(Map<String, String> valueMap, String id) {
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(CombinedTrainAndOtherTeachLinkTable.CTAOTL_ID, id);
		int result = updateRecord(CombinedTrainAndOtherTeachLinkTable.TABLE_NAME, valueMap,
				condition);
		return result;
	}

	@Override
	public List<CombinedTrainAndOtherTeachLink> getAllCombinedTrainAndOtherTeachLinks() {
		String sql = " select * from " + CombinedTrainAndOtherTeachLinkTable.TABLE_NAME
				+ " where 1=1 " + " order by " + CombinedTrainAndOtherTeachLinkTable.CTAOTL_ID;
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			System.out.println(sql);
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			List<CombinedTrainAndOtherTeachLink> combinedTrainAndOtherTeachLinkList = new ArrayList<CombinedTrainAndOtherTeachLink>();
			while (resultSet.next()) {
				int ctaotl_id = resultSet.getInt(CombinedTrainAndOtherTeachLinkTable.CTAOTL_ID);
				String ctaotl_collegename = resultSet.getString(CombinedTrainAndOtherTeachLinkTable.CTAOTL_COLLEGENAME);  //学院
			    String ctaotl_major = resultSet.getString(CombinedTrainAndOtherTeachLinkTable.CTAOTL_MAJOR); //专业
			    String ctaotl_majornumber = resultSet.getString(CombinedTrainAndOtherTeachLinkTable.CTAOTL_MAJORNUMBER);  //专业代码
			    
			    Integer ctaotl_graduatestudent = resultSet.getInt(CombinedTrainAndOtherTeachLinkTable.CTAOTL_GRADUATESTUDENT); //毕业班人数
			    if(ctaotl_graduatestudent == -1) ctaotl_graduatestudent = null;
			    Integer ctaotl_ct_t_total = resultSet.getInt(CombinedTrainAndOtherTeachLinkTable.CTAOTL_CT_T_TOTAL); //分专业毕业综合训练/指导毕业综合训练教师数/总数
			    if(ctaotl_ct_t_total == -1) ctaotl_ct_t_total = null;
			    
			    Integer ctaotl_ct_t_fullteacher = resultSet.getInt(CombinedTrainAndOtherTeachLinkTable.CTAOTL_CT_T_FULLTEACHER);//分专业毕业综合训练/指导毕业综合训练教师数/专任教师
			    if(ctaotl_ct_t_fullteacher == -1) ctaotl_ct_t_fullteacher = null;
			    Integer ctaotl_ct_t_partteacher = resultSet.getInt(CombinedTrainAndOtherTeachLinkTable.CTAOTL_CT_T_PARTTEACHER); //分专业毕业综合训练/指导毕业综合训练教师数/外聘教师
			    if(ctaotl_ct_t_partteacher == -1) ctaotl_ct_t_partteacher = null;
			    
			    Integer ctaotl_ct_t_senior = resultSet.getInt(CombinedTrainAndOtherTeachLinkTable.CTAOTL_CT_T_SENIOR); //分专业毕业综合训练/指导毕业综合训练教师数/正高级
			    if(ctaotl_ct_t_senior == -1) ctaotl_ct_t_senior = null;
			    Integer ctaotl_ct_t_subsenior = resultSet.getInt(CombinedTrainAndOtherTeachLinkTable.CTAOTL_CT_T_SUBSENIOR); //分专业毕业综合训练/指导毕业综合训练教师数/副高级
			    if(ctaotl_ct_t_subsenior == -1) ctaotl_ct_t_subsenior = null;
			    
			    Integer ctaotl_ct_p_total = resultSet.getInt(CombinedTrainAndOtherTeachLinkTable.CTAOTL_CT_P_TOTAL);  //分专业毕业综合训练/毕业综合训练课题/总数
			    if(ctaotl_ct_p_total == -1) ctaotl_ct_p_total = null;
			    Integer ctaotl_ct_p_socialfinish = resultSet.getInt(CombinedTrainAndOtherTeachLinkTable.CTAOTL_CT_P_SOCIALFINISH);//分专业毕业综合训练/毕业综合训练课题/在实验、实习、工程实践和社会调查等社会实践中完成数
			    if(ctaotl_ct_p_socialfinish == -1) ctaotl_ct_p_socialfinish = null;
			    
			    Integer ctaotl_ct_p_fromscience = resultSet.getInt(CombinedTrainAndOtherTeachLinkTable.CTAOTL_CT_P_FROMSCIENCE); //分专业毕业综合训练/毕业综合训练课题/来自教师科研课题
			    if(ctaotl_ct_p_fromscience == -1) ctaotl_ct_p_fromscience = null;
			    Integer ctaotl_ct_p_fromproduce = resultSet.getInt(CombinedTrainAndOtherTeachLinkTable.CTAOTL_CT_P_FROMPRODUCE); //分专业毕业综合训练/毕业综合训练课题/来自生产管理一线
			    if(ctaotl_ct_p_fromproduce == -1) ctaotl_ct_p_fromproduce = null;
			    
			    Integer ctaotl_ct_p_fromsocial = resultSet.getInt(CombinedTrainAndOtherTeachLinkTable.CTAOTL_CT_P_FROMSOCIAL); //分专业毕业综合训练/毕业综合训练课题/来自社会实践
			    if(ctaotl_ct_p_fromsocial == -1) ctaotl_ct_p_fromsocial = null;			    
			    Integer ctaotl_ct_p_other = resultSet.getInt(CombinedTrainAndOtherTeachLinkTable.CTAOTL_CT_P_OTHER); //分专业毕业综合训练/毕业综合训练课题/其他
			    if(ctaotl_ct_p_other == -1) ctaotl_ct_p_other = null;
			    
			    Float ctaotl_ot_pro_credit = resultSet.getFloat(CombinedTrainAndOtherTeachLinkTable.CTAOTL_OT_PRO_CREDIT); //其他教学环节/见习或认识实习/学分
			    if(ctaotl_ot_pro_credit == -1.0) ctaotl_ot_pro_credit = null;
			    Integer ctaotl_ot_pro_days = resultSet.getInt(CombinedTrainAndOtherTeachLinkTable.CTAOTL_OT_PRO_DAYS); //其他教学环节/见习或认识实习/天数
			    if(ctaotl_ot_pro_days == -1) ctaotl_ot_pro_days = null;
			    
			    Float ctaotl_ot_pra_credit = resultSet.getFloat(CombinedTrainAndOtherTeachLinkTable.CTAOTL_OT_PRA_CREDIT); //其他教学环节/毕业实习/学分
			    if(ctaotl_ot_pra_credit == -1.0) ctaotl_ot_pra_credit = null;
			    Integer ctaotl_ot_pra_days = resultSet.getInt(CombinedTrainAndOtherTeachLinkTable.CTAOTL_OT_PRA_DAYS); //其他教学环节/毕业实习/天数
			    if(ctaotl_ot_pra_days == -1) ctaotl_ot_pra_days = null;
			    
			    Float ctaotl_ot_ter_credit = resultSet.getFloat(CombinedTrainAndOtherTeachLinkTable.CTAOTL_OT_TER_CREDIT); //其他教学环节/学年论文/学分 
			    if(ctaotl_ot_ter_credit == -1.0) ctaotl_ot_ter_credit = null;
			    Integer ctaotl_ot_ter_days = resultSet.getInt(CombinedTrainAndOtherTeachLinkTable.CTAOTL_OT_TER_DAYS); //其他教学环节/学年论文/天数
			    if(ctaotl_ot_ter_days == -1) ctaotl_ot_ter_days = null;
			    
			    Float ctaotl_ot_the_credit = resultSet.getFloat(CombinedTrainAndOtherTeachLinkTable.CTAOTL_OT_THE_CREDIT); //其他教学环节/毕业论文/学分
			    if(ctaotl_ot_the_credit == -1.0) ctaotl_ot_the_credit = null;
			    Integer ctaotl_ot_the_days = resultSet.getInt(CombinedTrainAndOtherTeachLinkTable.CTAOTL_OT_THE_DAYS); //其他教学环节/毕业论文/天数
			    if(ctaotl_ot_the_days == -1) ctaotl_ot_the_days = null;
			    
			    Float ctaotl_ot_oth_credit = resultSet.getFloat(CombinedTrainAndOtherTeachLinkTable.CTAOTL_OT_OTH_CREDIT); //其他教学环节/其他/学分
			    if(ctaotl_ot_oth_credit == -1.0) ctaotl_ot_oth_credit = null;
			    Integer ctaotl_ot_oth_days = resultSet.getInt(CombinedTrainAndOtherTeachLinkTable.CTAOTL_OT_OTH_DAYS); //其他教学环节/其他/天数
			    if(ctaotl_ot_oth_days == -1) ctaotl_ot_oth_days = null;
			    
			    Float ctaotl_ot_tot_credit = resultSet.getFloat(CombinedTrainAndOtherTeachLinkTable.CTAOTL_OT_TOT_CREDIT); //其他教学环节/合计/学分
			    if(ctaotl_ot_tot_credit == -1.0) ctaotl_ot_tot_credit = null;
			    Integer ctaotl_ot_tot_days = resultSet.getInt(CombinedTrainAndOtherTeachLinkTable.CTAOTL_OT_TOT_DAYS);  //其他教学环节/合计/天数
			    if(ctaotl_ot_tot_days == -1) ctaotl_ot_tot_days = null;  
			    
			    String ctaotl_college = resultSet.getString(CombinedTrainAndOtherTeachLinkTable.CTAOTL_COLLEGE);
			    int ctaotl_serialnumber = resultSet.getInt(CombinedTrainAndOtherTeachLinkTable.CTAOTL_SERIALNUMBER);//序列号
			  	Date ctaotl_deadline = resultSet.getDate(CombinedTrainAndOtherTeachLinkTable.CTAOTL_DEADLINE);//截止日期
			  	String ctaotl_comments = resultSet.getString(CombinedTrainAndOtherTeachLinkTable.CTAOTL_COMMENTS);//审核意见
				if(null == ctaotl_comments){
					ctaotl_comments = "";
				}
				int ctaotl_isnull = resultSet.getInt(CombinedTrainAndOtherTeachLinkTable.CTAOTL_ISNULL);//序列号
				
				CombinedTrainAndOtherTeachLink combinedTrainAndOtherTeachLink = new CombinedTrainAndOtherTeachLink(
						ctaotl_id,ctaotl_collegename,ctaotl_major,ctaotl_majornumber,
			            ctaotl_graduatestudent,ctaotl_ct_t_total,ctaotl_ct_t_fullteacher,ctaotl_ct_t_partteacher,
			            ctaotl_ct_t_senior,ctaotl_ct_t_subsenior,ctaotl_ct_p_total,ctaotl_ct_p_socialfinish,ctaotl_ct_p_fromscience,
			            ctaotl_ct_p_fromproduce,ctaotl_ct_p_fromsocial, ctaotl_ct_p_other, ctaotl_ot_pro_credit, ctaotl_ot_pro_days,
			            ctaotl_ot_pra_credit, ctaotl_ot_pra_days, ctaotl_ot_ter_credit, ctaotl_ot_ter_days, ctaotl_ot_the_credit,
			            ctaotl_ot_the_days, ctaotl_ot_oth_credit, ctaotl_ot_oth_days, ctaotl_ot_tot_credit, ctaotl_ot_tot_days,
			            ctaotl_college,ctaotl_serialnumber, ctaotl_deadline, ctaotl_comments, ctaotl_isnull);


				combinedTrainAndOtherTeachLinkList.add(combinedTrainAndOtherTeachLink);
			}
			return combinedTrainAndOtherTeachLinkList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally{
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}

	}

	@Override
	public void deleteAll() {
		Connection connection = null;
		try {
			connection = JdbcUtils_DBCP.getConnection();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		Statement stmt = null;
		try {
			stmt = connection.createStatement();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		String sql = "delete from " + CombinedTrainAndOtherTeachLinkTable.TABLE_NAME;
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils_DBCP.release(connection, stmt, null);
		}

	}


	@Override
	public void deleteByCollegeandDeadline(String college, Date deadline) throws SQLException {
		Connection connection = JdbcUtils_DBCP.getConnection();
		Statement stmt = connection.createStatement();
		String sql = "delete from " + CombinedTrainAndOtherTeachLinkTable.TABLE_NAME
				+ " where " + CombinedTrainAndOtherTeachLinkTable.CTAOTL_COLLEGE + " = '" + college + "'" +" and ctaotl_deadline is null ";
	//	sql += String.format(" and fe_deadline = ", null);
		System.out.println(sql);
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils_DBCP.release(connection, stmt, null);
		}

	}
}
