package cn.edu.xmu.serviceimpl;

import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.dao.CombinedTrainAndOtherTeachLinkDao;
import cn.edu.xmu.daoimpl.CombinedTrainAndOtherTeachLinkDaoImpl;
import cn.edu.xmu.entity.CombinedTrainAndOtherTeachLink;
import cn.edu.xmu.entity.GraduateCombinedTrain;
import cn.edu.xmu.service.GraduateCombinedTrainService;
import cn.edu.xmu.table.CombinedTrainAndOtherTeachLinkTable;

/**
 *  毕业综合训练情况
 * @author chunfeng 
 *
 */
public class GraduateCombinedTrainServiceImpl implements GraduateCombinedTrainService{

	CombinedTrainAndOtherTeachLinkDao ctaotlDao = new CombinedTrainAndOtherTeachLinkDaoImpl();
	@Override
	public List<GraduateCombinedTrain> getGraduateCombinedTrain( Map queryParams) {
		// TODO Auto-generated method stub
		List<GraduateCombinedTrain> gctList = new ArrayList<GraduateCombinedTrain>();
		int serialNumber = 0; //序号
		String major = "";  //专业
		Integer projectNum = null; //课题数
		Integer socialFinish = null; //在实验、实习、工程实践和社会调查等社会实践中完成数
		Float rate = null; //比例（%）
		Integer fullTeacher = null; //专任教师
		Integer partTeacher = null;  //外聘教师
		Float stuPerTeacher = null;  //每名教师平均指导毕业生数
		String college = "";
		
		Date deadline = null;
		Map ctaotlParams = new HashMap();
		if (queryParams == null)
			queryParams = new HashMap<>();		
		else if (queryParams.keySet().size() != 0)
		{
			for (Object object : queryParams.keySet())
			{
				String key = object.toString();
				String value = (String) queryParams.get(key);
				if (key.equals("college"))
				{
					college = value;
					queryParams.remove("college");
					ctaotlParams.put(CombinedTrainAndOtherTeachLinkTable.CTAOTL_COLLEGE, value);					
				}
				if (key.equals("deadline"))
				{
					queryParams.remove("deadline");
					deadline = Date.valueOf(value);					
				}			
			}
		}
		
		
		List<CombinedTrainAndOtherTeachLink> ctaotls = ctaotlDao.getCombinedTrainAndOtherTeachLink(0, 
				ctaotlDao.getCombinedTrainAndOtherTeachLinkCount(ctaotlParams), 
				CombinedTrainAndOtherTeachLinkTable.CTAOTL_MAJORNUMBER, "desc", ctaotlParams,deadline);
		
		for (CombinedTrainAndOtherTeachLink ctaotl : ctaotls)
		{			
				major = ctaotl.getCtaotl_major();
				
				//课题数
				if( ctaotl.getCtaotl_ct_p_total() != null ) projectNum = ctaotl.getCtaotl_ct_p_total();
				
				//在实验、实习、工程实践和社会调查等社会实践中完成数
				if(ctaotl.getCtaotl_ct_p_socialfinish() != null) socialFinish = ctaotl.getCtaotl_ct_p_socialfinish();
				
				if( projectNum != null && socialFinish != null)
				{
					rate = (float) ((float)socialFinish / (float)projectNum)*100 ; //比例（%）
					DecimalFormat df = new DecimalFormat("#.0000");
				    rate = Float.parseFloat(df.format(rate));
				}
				else
					rate = null;
				
				//专任教师
				if(ctaotl.getCtaotl_ct_t_fullteacher() != null) fullTeacher = ctaotl.getCtaotl_ct_t_fullteacher();
				
				//外聘教师
				if(ctaotl.getCtaotl_ct_t_partteacher() != null) partTeacher = ctaotl.getCtaotl_ct_t_partteacher();
				
				if( ctaotl.getCtaotl_ct_t_total() != null && ctaotl.getCtaotl_graduatestudent()!=null)
				  stuPerTeacher = (float)ctaotl.getCtaotl_graduatestudent() / (float)ctaotl.getCtaotl_ct_t_total() ;  
				else
					stuPerTeacher = null;
				
				gctList.add(new GraduateCombinedTrain(++serialNumber, major, projectNum,
						socialFinish, rate, fullTeacher, partTeacher, stuPerTeacher, college,"每名教师指导毕业生数最高的五个专业："));							
		}
		
		//降序排序
		Collections.sort(gctList);
		for (int i = 0; i < gctList.size(); i++)
			gctList.get(i).setSerialNumber(i + 1);// 重置序号
		
		return gctList;		
	}
	
	/*
	 * 获取降序排列后的从0-SIZE个（即排在最前的size个）
	 */
	
	@Override
	public List<GraduateCombinedTrain> getFrontGraduateCombinedTrain(int size, boolean isSortByRate,
			List<GraduateCombinedTrain> gctList) {
		// TODO Auto-generated method stub
		// 按照每名教师平均指导毕业生数降序排列
		/*if (isSortByRate)
		{
			Collections.sort(gctList);
			for (int i = 0; i < gctList.size(); i++)
				gctList.get(i).setSerialNumber(i + 1);// 重置序号
		}*/
		// 获取相应的个数
		List<GraduateCombinedTrain> resultList = null;
		if (size > 0)
		{
			if (size > gctList.size())
				resultList = gctList.subList(0, gctList.size());
			else
				resultList = gctList.subList(0, size);
		}
		
		return resultList;
	}
	
	/*
	 * 获取降序排列后的从 length-SIZE 到  length个 （即排在最后的size个）
	 */
	@Override
	public List<GraduateCombinedTrain> getBottomGraduateCombinedTrain(int size, boolean isSortByRate,
			List<GraduateCombinedTrain> gctList) {
		// TODO Auto-generated method stub
		// 按照每名教师平均指导毕业生数降序排列
				/*if (isSortByRate)
				{
					Collections.sort(gctList);
					for (int i = 0; i < gctList.size(); i++)
						gctList.get(i).setSerialNumber(i + 1);// 重置序号
				}*/
				// 获取相应的个数
				List<GraduateCombinedTrain> resultList = new ArrayList<GraduateCombinedTrain>();
				if (size > 0)
				{
					if (size > gctList.size())
						for (GraduateCombinedTrain g : gctList)
							resultList.add(new GraduateCombinedTrain(g.getSerialNumber(), g.getMajor(), g.getProjectNum(), g.getSocialFinish(), g.getRate(), g.getFullTeacher(), g.getPartTeacher(), g.getStuPerTeacher(), g.getCollege(), "每名教师指导毕业生数最低的五个专业："));
					else
						for (int i = gctList.size()-size+1; i < gctList.size(); i++) {
							GraduateCombinedTrain g = gctList.get(i);
							resultList.add(new GraduateCombinedTrain(g.getSerialNumber(), g.getMajor(), g.getProjectNum(), g.getSocialFinish(), g.getRate(), g.getFullTeacher(), g.getPartTeacher(), g.getStuPerTeacher(), g.getCollege(), "每名教师指导毕业生数最低的五个专业："));
						}
				}
				
				return resultList;
	}
	
	
}
