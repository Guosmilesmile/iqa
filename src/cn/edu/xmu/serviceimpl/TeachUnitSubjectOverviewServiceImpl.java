package cn.edu.xmu.serviceimpl;

/**
 * @author zsh
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jspsmart.upload.File;

import cn.edu.xmu.dao.GraduateAndDoctoralDao;
import cn.edu.xmu.dao.ImportantStudyDao;
import cn.edu.xmu.dao.MajorInfoDao;
import cn.edu.xmu.dao.PostdoctoralMobileStationDao;
import cn.edu.xmu.dao.TeachResearchUnitDao;
import cn.edu.xmu.daoimpl.GraduateAndDoctoralDaoImpl;
import cn.edu.xmu.daoimpl.ImportantStudyDaoImpl;
import cn.edu.xmu.daoimpl.MajorInfoDaoImpl;
import cn.edu.xmu.daoimpl.PostdoctoralMobileStationDaoImpl;
import cn.edu.xmu.daoimpl.TeachResearchUnitDaoImpl;
import cn.edu.xmu.entity.TeachResearchUnit;
import cn.edu.xmu.entity.TeachUnitSubjectOverview;
import cn.edu.xmu.service.TeachUnitSubjectOverviewService;
import cn.edu.xmu.table.GraduateAndDoctoralTable;
import cn.edu.xmu.table.ImportantStudyTable;
import cn.edu.xmu.table.MajorInfoTable;
import cn.edu.xmu.table.PostdoctoralMobileStationTable;
import cn.edu.xmu.table.TeachResearchUnitTable;

public class TeachUnitSubjectOverviewServiceImpl implements TeachUnitSubjectOverviewService{
	
	@Override
	public List<TeachUnitSubjectOverview> get(Map<String, String> filter){
		
		List<TeachUnitSubjectOverview> content = new ArrayList<>();
		
		TeachResearchUnitDao tud = new TeachResearchUnitDaoImpl();
		MajorInfoDao mid = new MajorInfoDaoImpl();
		PostdoctoralMobileStationDao pmsd = new PostdoctoralMobileStationDaoImpl();
		GraduateAndDoctoralDao gadd = new GraduateAndDoctoralDaoImpl();
		ImportantStudyDao isd = new ImportantStudyDaoImpl();
		
		
		Map<String, String> tuFilter = new HashMap<>();
		Map<String, String> miFilter = new HashMap<>();
		Map<String, String> pmsFilter = new HashMap<>();
		Map<String, String> gadFilter = new HashMap<>();
		Map<String, String> isFilter = new HashMap<>();


		
	
		String college = "";
		if (filter == null) {
			filter = new HashMap<>();
		}else if (filter.keySet().size() != 0) {
			for(Object obj : filter.keySet()){
				String key = obj.toString();
				String value = filter.get(key);
				if (key.equals("college")) {
					college = value;
					filter.remove("college");
					tuFilter.put(TeachResearchUnitTable.TR_COLLEGE, college);
					pmsFilter.put(PostdoctoralMobileStationTable.PMS_COLLEGE, college);
					gadFilter.put(GraduateAndDoctoralTable.GD_COLLEGE, college);
					isFilter.put(ImportantStudyTable.IP_COLLEGE, college);
				}
				if (key.equals("deadline")) {
					filter.remove("deadline");
					tuFilter.put(TeachResearchUnitTable.TR_DEADLINE, value);
					pmsFilter.put(PostdoctoralMobileStationTable.PMS_DEADLINE, value);
					gadFilter.put(GraduateAndDoctoralTable.GD_DEADLINE, value);
					isFilter.put(ImportantStudyTable.IP_DEADLINE, value);
				}
			}
		}
		
		
		
		int xNum = 9;
		int yNum = tud.getTeachResearchUnitCount();
		
		List<TeachResearchUnit> truList = tud.getAllTeachResearchUnits();
		
		String[] valueArray = new String[xNum]; //content 里的一行
		
		for (Integer i = 0; i < yNum; i++) {
			TeachUnitSubjectOverview tso = new TeachUnitSubjectOverview();
			String departNumber = truList.get(i).getTr_number();
			 for (int j = 0; j < xNum; j++) {
				switch (j) {
				case 0:	//编号
					valueArray[j] = i.toString(); 
					break;
				case 1: //单位
					valueArray[j] = truList.get(i).getTr_name();
					break;
				case 2: //本科专业数
					Map<String, String> bachelorNumber = new HashMap<>();
					bachelorNumber.put(MajorInfoTable.MI_DEPARTMENTNUMBER, departNumber);
					valueArray[j] =  String.valueOf(mid.getCount(bachelorNumber));
					break;
				case 3: //博士后流动站
					Map<String, String> postPhdNumber = new HashMap<>();
					postPhdNumber.put(PostdoctoralMobileStationTable.PMS_DEPARTMENTNUMBER, departNumber);
					valueArray[j] = String.valueOf(pmsd.getPostdoctoralMobileStationCount(postPhdNumber));
					break;
				case 4: //博士一级
					Map<String, String> phdFir = new HashMap<>();
					phdFir.put(GraduateAndDoctoralTable.GD_DEPARTMENTNUMBER, departNumber);
					phdFir.put(GraduateAndDoctoralTable.GD_TYPE, "博士学位授权一级学科点");
					valueArray[j] = String.valueOf(gadd.getGraduateAndDoctoralCount(phdFir));
					break;
				case 5: //博士二级
					Map<String, String> phdSec = new HashMap<>();
					phdSec.put(GraduateAndDoctoralTable.GD_DEPARTMENTNUMBER, departNumber);
					phdSec.put(GraduateAndDoctoralTable.GD_TYPE, "博士学位授权二级学科点");
					valueArray[j] = String.valueOf(gadd.getGraduateAndDoctoralCount(phdSec));
					break;
				case 6: //硕士一级
					Map<String, String> masterFir = new HashMap<>();
					masterFir.put(GraduateAndDoctoralTable.GD_DEPARTMENTNUMBER, departNumber);
					masterFir.put(GraduateAndDoctoralTable.GD_TYPE, "硕士学位授权一级学科点");
					valueArray[j] = String.valueOf(gadd.getGraduateAndDoctoralCount(masterFir));
					break;
				case 7: //硕士二级
					Map<String, String> masterSec = new HashMap<>();
					masterSec.put(GraduateAndDoctoralTable.GD_DEPARTMENTNUMBER, departNumber);
					masterSec.put(GraduateAndDoctoralTable.GD_TYPE, "硕士学位授权二级学科点");
					valueArray[j] = String.valueOf(gadd.getGraduateAndDoctoralCount(masterSec));
					break;
				case 8: //重点学科数
					Map<String, String> important = new HashMap<>();
					important.put(ImportantStudyTable.IP_DEPARTMENTNUMBER, departNumber);
					valueArray[j] = String.valueOf(isd.getImportantStudyCountCount(important));
					break;
				default:
					System.out.println("循环里的 switch 出错啦！！！");
					break;
					
				}
			}
			 
			tso.getDataFromArray(valueArray);
			content.add(tso);
			
		}
		return content;
	}
	

}
