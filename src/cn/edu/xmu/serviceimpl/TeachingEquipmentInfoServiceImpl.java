package cn.edu.xmu.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import cn.edu.xmu.dao.FixedAssetsDao;
import cn.edu.xmu.dao.StudentNumberInfoDao;
import cn.edu.xmu.daoimpl.FixedAssetsDaoImpl;
import cn.edu.xmu.daoimpl.StudentNumberInfoDaoImpl;
import cn.edu.xmu.entity.FixedAssets;
import cn.edu.xmu.entity.StudentNumberInfo;
import cn.edu.xmu.entity.TeachingEquipmentInfo;
import cn.edu.xmu.service.TeachingEquipmentInfoService;
import cn.edu.xmu.table.FixedAssetsTable;
import cn.edu.xmu.table.StudentNumberInfoTable;

/**
 * 3.2.2教学、科研仪器设备情况
 * @author yue
 *
 */
public class TeachingEquipmentInfoServiceImpl implements TeachingEquipmentInfoService{

	StudentNumberInfoDao sniDao = new StudentNumberInfoDaoImpl();
	FixedAssetsDao faDao = new FixedAssetsDaoImpl();
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<TeachingEquipmentInfo> getTeachingEquipmentInfo(Map params) {
		// TODO Auto-generated method stub
		String title = "";
		Float equipmentAssetsSum = (float) 0;//教学、科研仪器设备 资产总值（万元）
		Float equipmentAssetsStuAvg = (float) 0;//生均（万元）
		Float newAssets = (float) 0;//当年新增（万元）
		Float newAssetsPercent = (float) 0;//当年新增所占比例（%）
		String college = "";//学院
		
		Map paramsStudentNumberInfo = new HashMap<>();
		Map paramsFixedAssets = new HashMap<>();
		if(params == null)
		{
			params = new HashMap<>();
		}
		else if(params.keySet().size() != 0){
			for(Object object:params.keySet()){
				String key = object.toString();
				String value = (String) params.get(key);
				if(key.equals("college"))
				{
					college = value;
					params.remove("college");
					paramsStudentNumberInfo.put(StudentNumberInfoTable.SNI_COLLEGE, value);
					paramsFixedAssets.put(FixedAssetsTable.FA_COLLEGE, value);
				}
				if(key.equals("deadline"))
				{
					params.remove("deadline");
					paramsStudentNumberInfo.put(StudentNumberInfoTable.SNI_DEADLINE, value);
					paramsFixedAssets.put(FixedAssetsTable.FA_DEADLINE, value);
				}
			}
		}
		List<TeachingEquipmentInfo> teis = new ArrayList<TeachingEquipmentInfo>();
		List<FixedAssets> fas = new ArrayList<FixedAssets>();
		List<StudentNumberInfo> snis = new ArrayList<StudentNumberInfo>();
		
		title = "学校情况";
		fas=faDao.getAllFixedAssets(0, faDao.getFixedAssetsCount(paramsFixedAssets), FixedAssetsTable.FA_ID, "asc", paramsFixedAssets);
		for(FixedAssets fa:fas)
		{
			if(fa.getFa_equipmentassetssum() != null || !"".equals(fa.getFa_equipmentassetssum()))
				equipmentAssetsSum += fa.getFa_equipmentassetssum();
			if(fa.getFa_newassets() != null || "".equals(fa.getFa_newassets()))
				newAssets += fa.getFa_newassets();
		}
		int studentNumber = 0;//普通本科学生数
		snis = sniDao.getStudentNumberInfos(0, sniDao.getStudentNumberInfoCount(paramsStudentNumberInfo), StudentNumberInfoTable.SNI_ID, "asc", paramsStudentNumberInfo, null);
		for(StudentNumberInfo sni:snis)
		{
			if(sni.getSni_ordiundergrastu() != null || !"".equals(sni.getSni_ordiundergrastu()))
				studentNumber+= sni.getSni_ordiundergrastu();
		}
		if(studentNumber != 0)
		{
			equipmentAssetsStuAvg = equipmentAssetsSum/studentNumber;
		}
		if(equipmentAssetsSum != 0)
		{
			newAssetsPercent = newAssets/equipmentAssetsSum * 100;
		}
		teis.add(new TeachingEquipmentInfo(title,equipmentAssetsSum, equipmentAssetsStuAvg, newAssets, newAssetsPercent, college));
		
		
		title = "办学条件指标|合格标准";
		equipmentAssetsSum = null;
		equipmentAssetsStuAvg = null;
		newAssets = null;
		newAssetsPercent = null;
		teis.add(new TeachingEquipmentInfo(title, equipmentAssetsSum, equipmentAssetsStuAvg, newAssets, newAssetsPercent, college));
		
		
		title = "常模值";
		equipmentAssetsSum = null;
		equipmentAssetsStuAvg = null;
		newAssets = null;
		newAssetsPercent = null;
		teis.add(new TeachingEquipmentInfo(title, equipmentAssetsSum, equipmentAssetsStuAvg, newAssets, newAssetsPercent, college));
		
		return teis;
	}

}
