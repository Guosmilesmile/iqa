package cn.edu.xmu.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.MajorTeacherStructureTemp;
import cn.edu.xmu.entity.StartClass;

/**
 * 5-1-1-1 开课情况表
 * @author chunfeng
 *
 */
public interface StartClassDao extends BaseDao<StartClass>{
	public List<StartClass> getStartClass(int start, int end,
			String sortStr, String orderStr,Map<String, String> params,Date deadline);
	
	
	public List<StartClass> getStartClassByTeacherNum(String teacherNum);
	
	
	public int getStartClassCount(Map queryParams);
	
	/**
	 * @author zshbleaker
	 */
	
	public int getClassCountDistinct(Map queryParams);
	
	/**
	 * zsj
	 * 获得开课的教师人数（去掉重复的）
	 * @param queryParams
	 * @return
	 */
	public int getClassTeacherCount(Map queryParams);
	
	boolean batchDelete(String[] scids) throws SQLException;
	
	public int addStartClass(StartClass startClass);
	
	public int alterStartClass(Map<String, String> valueMap, String id);
	
	public List<StartClass> getAllStartClasss();
	
	public void deleteAll();
	
	public void deleteByCollegeandDeadline(String college,Date deadline) throws SQLException;


	List<MajorTeacherStructureTemp> getMajorTeacherStructureFormFTTIAll(
			Map queryParamsforMT, Map queryParamsforSC, Map queryParamsforFTTI);

	Map getMajorLowCountFormFTTI(Map queryParamsforMT, Map queryParamsforSC,
			Map queryParamsforFTTI);

	List<MajorTeacherStructureTemp> getMajorTeacherStructureFormETAll(
			Map queryParamsforMT, Map queryParamsforSC, Map queryParamsforET);

	Map getMajorLowCountFormET(Map queryParamsforMT, Map queryParamsforSC,
			Map queryParamsforET);

	List<MajorTeacherStructureTemp> getMajorTeacherStructureFormOTIAll(
			Map queryParamsforMT, Map queryParamsforSC, Map queryParamsforOTI);

	Map getMajorLowCountFormOTI(Map queryParamsforMT, Map queryParamsforSC,
			Map queryParamsforOTI);

}
