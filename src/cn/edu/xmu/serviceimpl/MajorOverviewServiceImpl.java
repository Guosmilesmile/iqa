package cn.edu.xmu.serviceimpl;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.dao.FullTimeTeacherInfoDao;
import cn.edu.xmu.dao.GraduateEmployAsMajorDao;
import cn.edu.xmu.dao.GraduationSituationDao;
import cn.edu.xmu.dao.MajorInfoDao;
import cn.edu.xmu.dao.OtherTeacherInfoDao;
import cn.edu.xmu.dao.RegUndergraProfesStuNumDao;
import cn.edu.xmu.dao.TeacherInfoDao;
import cn.edu.xmu.daoimpl.FullTimeTeacherInfoDaoImpl;
import cn.edu.xmu.daoimpl.GraduateEmployAsMajorDaoImpl;
import cn.edu.xmu.daoimpl.GraduationSituationDaoImpl;
import cn.edu.xmu.daoimpl.MajorInfoDaoImpl;
import cn.edu.xmu.daoimpl.OtherTeacherInfoDaoImpl;
import cn.edu.xmu.daoimpl.RegUndergraProfesStuNumDaoImpl;
import cn.edu.xmu.daoimpl.TeacherInfoDaoImpl;
import cn.edu.xmu.entity.GraduateEmployAsMajor;
import cn.edu.xmu.entity.GraduationSituation;
import cn.edu.xmu.entity.MajorInfo;
import cn.edu.xmu.entity.MajorOverview;
import cn.edu.xmu.entity.RegUndergraProfesStuNum;
import cn.edu.xmu.entity.TeacherInfo;
import cn.edu.xmu.service.MajorOverviewService;
import cn.edu.xmu.table.FullTimeTeacherInfoTable;
import cn.edu.xmu.table.OtherTeacherInfoTable;

/**
 * 3.3 & 附表8  各专业教师、学生情况概览
 * @author chunfeng 
 *
 */
public class MajorOverviewServiceImpl implements MajorOverviewService{
	private MajorInfoDao miDao = new MajorInfoDaoImpl(); //4-2-2-1 专业基本情况
	private TeacherInfoDao tiDao = new TeacherInfoDaoImpl();  //5-1-1-1教师资源表
	private FullTimeTeacherInfoDao fttiDao = new FullTimeTeacherInfoDaoImpl(); //3-1-1 专任教师基本信息表
	private OtherTeacherInfoDao otiDao = new OtherTeacherInfoDaoImpl(); //其他师资
	private RegUndergraProfesStuNumDao rupsnDao = new RegUndergraProfesStuNumDaoImpl(); //6-1-2 普通本科分专业（大类）学生数
	private GraduationSituationDao gsDao = new GraduationSituationDaoImpl();
	private GraduateEmployAsMajorDao geamDao = new GraduateEmployAsMajorDaoImpl();
	
	@Override
	public List<MajorOverview> getMajorOverview() {
		// TODO Auto-generated method stub
		List<MajorOverview> moList = new ArrayList<MajorOverview>();
		int serialNumber = 0;
		
		List<MajorInfo> miList = miDao.getMajorInfoByYear();
		
		for (MajorInfo mi : miList)
		{		
			    String majorName = "";
			    String majorSetYear = "";
			    Integer selfTeacher = null; //本学院授课教师数
			    Integer otherTeacher = null; //外学院授课教师数
			    Integer highTeacher = null; //具有高级职称的教师数
			    Integer undergraduates = null; //本科学生数
			    Float stuVsTeacher = null; //学生与教师比
			    Integer stuFlow = null; //学生流动净值
			    Integer graduates = null; //应届毕业生数
			    Float firstWork = null; //初次就业率
			    
			    
			    String majorCode = mi.getMi_majorcode();
			    majorName = mi.getMi_majorname();
				
			    majorSetYear = mi.getMi_setupyear();
			    
			    selfTeacher = 0;
			    otherTeacher = 0;
			    highTeacher = 0;
			    
			    List<TeacherInfo> tiList = tiDao.getTeacherInfoByCollege(majorName);
			    for(int i=0; i<tiList.size(); i++){
			    	
			    	String workNum = tiList.get(i).getTi_number();
			    	
			    	Map<String, String> queryParams1 = new HashMap<String, String>();
			    	queryParams1.put(FullTimeTeacherInfoTable.FTTI_WORKNUMBER, workNum);
			    	Map<String, String> queryParams2 = new HashMap<String, String>();
			    	queryParams2.put(OtherTeacherInfoTable.OTI_WORKNUMBER, workNum);
			    	if( fttiDao.getFullTimeTeacherCount(queryParams1) > 0 || otiDao.getOtherTeacherInfoCount(queryParams2) > 0){
			    		selfTeacher ++ ;
			    	}
			    	else{
			    		otherTeacher ++;
			    	}
			    	
			    	if(tiList.get(i).getTi_professiontitle().equals("教授") || 
			    			tiList.get(i).getTi_professiontitle().equals("其他正高级")){
			    		highTeacher ++;
			    	}
			    }
			    
				RegUndergraProfesStuNum rupsn = rupsnDao.getByMajorCode(majorCode); //本科学生数
				if(rupsn!=null){
				   undergraduates = rupsn.getRupsn_atschtotal();
				}
				
				if(tiList.size() > 0)
				    stuVsTeacher = (float) ((float)undergraduates/(float)tiList.size() *100.0); //学生与教师比
				
				if(rupsn!=null){
				    stuFlow = rupsn.getRupsn_intonumber() - rupsn.getRupsn_outnumber(); //学生流动净值
				}
				
				GraduationSituation gs = gsDao.getGraduationSituationByMajor(majorCode);
				if(gs != null){
				    graduates = gs.getGs_graduationnumber(); //应届毕业生数
				}
				
				firstWork = (float) 0; //初次就业率
				if(graduates != null ) {
					GraduateEmployAsMajor geam = geamDao.getGraduateEmployAsMajorsByMajor(majorCode);
					if(geam != null )
					    firstWork = (float)((float)geam.getGeam_graduatenum()/(float)graduates * 100.0); 				
				}
				moList.add(new MajorOverview(++serialNumber, majorName, majorSetYear,
						selfTeacher, otherTeacher, highTeacher, undergraduates, stuVsTeacher, stuFlow, graduates, firstWork));							
		}
		
		//降序排序
		Collections.sort(moList);
		for (int i = 0; i < moList.size(); i++)
			moList.get(i).setSerialNumber(i + 1);// 重置序号
		
		return moList;		
	}
	
	/*
	 * 获取降序排列后的从0-SIZE个（即排在最前的size个）
	 */
	
	@Override
	public List<MajorOverview> getMaxTenMajorOverview(int size, boolean isSortByRate, List<MajorOverview> moList) {
		// TODO Auto-generated method stub
		
		// 获取相应的个数
		List<MajorOverview> resultList = null;
		if(size > 0){
		if (size > moList.size())
				resultList = moList.subList(0, moList.size());
			else
				resultList = moList.subList(0, size);
		}
		
		return resultList;
	}
}
