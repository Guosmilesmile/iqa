package cn.edu.xmu.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.dao.FullTimeTeacherInfoDao;
import cn.edu.xmu.dao.MajorInfoDao;
import cn.edu.xmu.dao.TeachScientificDao;
import cn.edu.xmu.daoimpl.FullTimeTeacherInfoDaoImpl;
import cn.edu.xmu.daoimpl.MajorInfoDaoImpl;
import cn.edu.xmu.daoimpl.TeachScientificDaoImpl;
import cn.edu.xmu.entity.FullTimeTeacherInfo;
import cn.edu.xmu.entity.MajorInfo;
import cn.edu.xmu.entity.TeachScientific;
import cn.edu.xmu.entity.TeachingUnitsLeader;
import cn.edu.xmu.service.TeachingUnitsLeaderService;
import cn.edu.xmu.table.FullTimeTeacherInfoTable;
import cn.edu.xmu.table.MajorInfoTable;
import cn.edu.xmu.table.TeachScientificTable;

public class TeachingUnitsLeaderServiceImpl implements TeachingUnitsLeaderService{
	private TeachScientificDao teachScientificDao = new TeachScientificDaoImpl();
	private FullTimeTeacherInfoDao fullTimeTeacherInfoDao = new FullTimeTeacherInfoDaoImpl();
	private MajorInfoDao majorInfoDao = new MajorInfoDaoImpl();
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<TeachingUnitsLeader> getTeachingUnitsLeader(Map params)
	{
		Map queryParamsforTS = new HashMap(), queryParamsforFTTI = new HashMap(), queryParamsforMI = new HashMap();
		if (params == null ) {
			params = new HashMap();
		}else if (params.keySet().size() != 0) {
			for (Object object : params.keySet()) {
				String key = object.toString();
				String value = (String) params.get(key);
				if (key.equals("college")) {
					params.remove("college");
					queryParamsforTS.put(TeachScientificTable.TS_COLLEGE, value);
					queryParamsforMI.put(MajorInfoTable.MI_COLLEGE, value);
					queryParamsforFTTI.put(FullTimeTeacherInfoTable.FTTI_COLLEGE, value);
				}
				if (key.equals("deadline")) {
					params.remove("deadline");
					queryParamsforTS.put(TeachScientificTable.TS_COLLEGE, value);
					queryParamsforMI.put(MajorInfoTable.MI_COLLEGE, value);
					queryParamsforFTTI.put(FullTimeTeacherInfoTable.FTTI_COLLEGE, value);
				}
			}
		}
		
		List<TeachScientific> teachScientifics = teachScientificDao.getAllTeachScientific(0, teachScientificDao.getTeachScientificCount(queryParamsforTS), TeachScientificTable.TS_ID, "asc", queryParamsforTS);
		List<FullTimeTeacherInfo> fullTimeTeacherInfos = fullTimeTeacherInfoDao.getFullTimeTeachers(0, fullTimeTeacherInfoDao.getFullTimeTeacherCount(queryParamsforFTTI), FullTimeTeacherInfoTable.FTTI_ID, "asc", queryParamsforFTTI, null);
		List<MajorInfo> majorInfos = majorInfoDao.getAllMajorInfo(0, majorInfoDao.getCount(queryParamsforMI), MajorInfoTable.MI_ID, "asc");
		int serialnumber = 1;
		List<TeachingUnitsLeader> resultList = new ArrayList<>();
		for(int x = 0; x<teachScientifics.size(); x++)
		{
			String departmentNumber = teachScientifics.get(x).getTs_number();
			String departmentName = teachScientifics.get(x).getTs_name();
			//高级职称 人数
			int highTitleNulmer = 0;
			//获得博士学位 人数
			int doctorNumber = 0;
			//学缘为外校 人数
			int sourceOutsideNumber = 0;
			List<String> majorList = new ArrayList<>();//存储当前单位的教师教授的专业的专业代码
			List<String> teacherList = new ArrayList<>();//存储当前单位的教师的工号
			List<String> leaderList = new ArrayList<>();//存储当前单位的专业带头人的工号
			for(int y = 0; y<fullTimeTeacherInfos.size(); y++)
			{
				//遍历教师,把属于该单位的加入到teacherList中(注意不一定是专业带头人!)
				if(departmentNumber.equals(fullTimeTeacherInfos.get(y).getFtti_departmentnumber()) && !teacherList.contains(fullTimeTeacherInfos.get(y).getFtti_worknumber()))
				{
					teacherList.add(fullTimeTeacherInfos.get(y).getFtti_worknumber());
					for(int z=0; z<majorInfos.size(); z++)
					{
						//如果当前教师是专业带头人的话(注意一个教师可能是多个专业的带头人!)
						if(fullTimeTeacherInfos.get(y).getFtti_worknumber().equals(majorInfos.get(z).getMi_leaderid()) && !majorList.contains(majorInfos.get(z).getMi_majorcode()))
						{
							majorList.add(majorInfos.get(z).getMi_majorcode());
							//确保是一个不在leaderList里面的新的专业带头人
							if(!leaderList.contains(fullTimeTeacherInfos.get(y).getFtti_worknumber()))
							{
								leaderList.add(fullTimeTeacherInfos.get(y).getFtti_worknumber());
								if(fullTimeTeacherInfos.get(y).getFtti_professionaltitle().equals("教授")||fullTimeTeacherInfos.get(y).getFtti_professionaltitle().equals("副教授")||fullTimeTeacherInfos.get(y).getFtti_professionaltitle().equals("其他正高级")||fullTimeTeacherInfos.get(y).getFtti_professionaltitle().equals("其他副高级"))
									highTitleNulmer++;
								if(fullTimeTeacherInfos.get(y).getFtti_degree().equals("博士"))
									doctorNumber++;
								if(!fullTimeTeacherInfos.get(y).getFtti_educationsource().equals("本校"))
									sourceOutsideNumber++;
							}
						}
					}
				}
			}
			//一个单位判断完了
			TeachingUnitsLeader teachingUnitsLeader = new TeachingUnitsLeader(serialnumber++, departmentName,
					majorList.size(), leaderList.size(), highTitleNulmer,
					(leaderList.size()==0)?0:((highTitleNulmer*1.0)/leaderList.size()*100.0), doctorNumber,
					(leaderList.size()==0)?0:((doctorNumber*1.0)/leaderList.size()*100.0), sourceOutsideNumber,
					(leaderList.size()==0)?0:((sourceOutsideNumber*1.0)/leaderList.size()*100.0) );
			resultList.add(teachingUnitsLeader);
		}
		return resultList;
	}
}
