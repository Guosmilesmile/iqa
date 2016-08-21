package cn.edu.xmu.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import cn.edu.xmu.dao.FullTimeTeacherInfoDao;
import cn.edu.xmu.dao.OtherTeacherInfoDao;
import cn.edu.xmu.dao.SchoolExecutiveUnitDao;
import cn.edu.xmu.dao.StartClassDao;
import cn.edu.xmu.dao.TeachScientificDao;
import cn.edu.xmu.daoimpl.FullTimeTeacherInfoDaoImpl;
import cn.edu.xmu.daoimpl.OtherTeacherInfoDaoImpl;
import cn.edu.xmu.daoimpl.SchoolExecutiveUnitDaoImpl;
import cn.edu.xmu.daoimpl.StartClassDaoImpl;
import cn.edu.xmu.daoimpl.TeachScientificDaoImpl;
import cn.edu.xmu.entity.FullTimeTeacherInfo;
import cn.edu.xmu.entity.OtherTeacherInfo;
import cn.edu.xmu.entity.SchoolExecutiveUnit;
import cn.edu.xmu.entity.StartClass;
import cn.edu.xmu.entity.StartClassOverview;
import cn.edu.xmu.entity.TeachScientific;
import cn.edu.xmu.service.StartClassOverviewService;

/**
 * 3.6 各教学单位课程开设情况
 * @author chunfeng
 *
 */
public class StartClassOverviewServiceImpl implements StartClassOverviewService{

	private SchoolExecutiveUnitDao seuDao = new SchoolExecutiveUnitDaoImpl(); //学校相关行政单位
	private TeachScientificDao tsDao = new TeachScientificDaoImpl();//学校教学科研单位
	private FullTimeTeacherInfoDao fttiDao = new FullTimeTeacherInfoDaoImpl(); //专任教师
	private OtherTeacherInfoDao otiDao = new OtherTeacherInfoDaoImpl();//其他师资
	private StartClassDao scDao = new StartClassDaoImpl();//开课情况
	
	@Override
	public List<StartClassOverview> getStartClassOverview() {
		// TODO Auto-generated method stub
		List<StartClassOverview> scoList = new ArrayList<StartClassOverview>();
		int serialNumber = 0;
	    
	    //获得所有单位的单位号和单位名称
		List<SchoolExecutiveUnit> seuList = seuDao.getAllSchoolExecutiveUnit();
		List<TeachScientific> tsList = tsDao.getAllTeachScientific();
		
		List<String> departNameList = new ArrayList<String>();
		List<String> departCodeList = new ArrayList<String>();
		
		for(SchoolExecutiveUnit seu : seuList){
			departNameList.add(seu.getSeu_departmentname());
			departCodeList.add(seu.getSeu_departmentnumber());
		}
		for(TeachScientific ts : tsList){
			departNameList.add(ts.getTs_name());
			departCodeList.add(ts.getTs_number());
		}
		
		int i = 0;
		for(;i<departCodeList.size();i++){
			String departName = departNameList.get(i);//单位
		    int shuangyuTeacher = 0; //开设双语课程教师数
		    Float stuPerMajorclass = (float) 0; //专业课平均学生数
		    Float hourPerTeacher = (float) 0; //教授、副教授人均授课学时数
		    
			String departCode = departCodeList.get(i);
			
			//根据学院，获得该学院所有教师的工号和职称
			List<FullTimeTeacherInfo> fttiList = fttiDao.getFullTimeTeachersByDepartNum(departCode);
			List<OtherTeacherInfo> otiList = otiDao.getOtherTeacherInfoByDepartNum(departCode);
			
			List<String> teacherNumberList = new ArrayList<String>();
			List<String> teacherTitleList = new ArrayList<String>();
			
			if(fttiList.size() > 0){
			for(FullTimeTeacherInfo ftti : fttiList){
				teacherNumberList.add(ftti.getFtti_worknumber());
				teacherTitleList.add(ftti.getFtti_professionaltitle());
			}}
			
			if(otiList.size() > 0){
			for(OtherTeacherInfo oti : otiList){
				teacherNumberList.add(oti.getOti_worknumber());
				teacherTitleList.add(oti.getOti_professionaltitle());								
			}}
			
			/*
			 *用于获得hourPerTeacher
			 */
			//教授和副教授人数
			int jiaoshou = 0;
			String zhicheng = null;
			
			//总学时
			int hour = 0;
			
			/*
			 * 用于获得stuPerMajorclass
			 */
			//专业课数
			int majorclass = 0;
			//专业课学生数
			int stuNum = 0;
			
			//根据该学院的每位教师，分别获取该教师的开课情况
			for(int j=0; j<teacherNumberList.size() ; j++ ){
				
				zhicheng = teacherTitleList.get(j);
				if( zhicheng.equals("教授") || zhicheng.equals("副教授") ) jiaoshou++;
				
				/*
				 * 用于获得shuangyuTeacher
				 */
				int shuangyuflag = 0;
				
				 //获得教师J的开课列表
				List<StartClass> scList = scDao.getStartClassByTeacherNum(teacherNumberList.get(j));
				for(StartClass sc : scList){
					
					
					//专业课数++  专业课学生数+
					if(sc.getSc_coursecategory().contains("专业")) { 
						majorclass++;
						stuNum += sc.getSc_studentnum();
						
						//教授和副教授授课总学时
						if( zhicheng.equals("教授") || zhicheng.equals("副教授") ){
							hour += sc.getSc_totalhour();
						}
						
						//双语授课
						if(sc.getSc_isenglish().equals("双语授课")) shuangyuflag = 1;
						
					}
					
					
				}
				
				//开设双语课程教师数++
				if(shuangyuflag == 1) shuangyuTeacher++;
				
			}
					
			if( majorclass != 0)
		    stuPerMajorclass = (float) ((float)stuNum/(float)majorclass); //专业课平均学生数
			if(jiaoshou != 0)
		    hourPerTeacher = (float)((float)hour/(float)jiaoshou); //教授、副教授人均授课学时数
		    
		    scoList.add(new StartClassOverview(++serialNumber, departName, shuangyuTeacher, stuPerMajorclass, hourPerTeacher));
			
		}
		return scoList;
	}

}
