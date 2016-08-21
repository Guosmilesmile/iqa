package cn.edu.xmu.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.GovermentalUnit;
import cn.edu.xmu.entity.TeachResearchUnit;
/**
 * 
 * @author luo
 * 学校教学科研单位  实体类功能--接口
 * date 2015-06-29
 */

public interface TeachResearchUnitDao extends BaseDao<TeachResearchUnit> {
  
	//单位总数量 
	public int getTeachResearchUnitCount();
	
	//获得全部单位
	public List<TeachResearchUnit> getAllTeachResearchUnit(int start, int end,
			String sortStr, String orderStr);
	
	//添加
	public int addTeachResearchUnit(String name,String number,String responperson,int serialnumber,int isnull);
	
	//修改
	public int alterTeachResearchUnit(Map<String, String> valueMap, String id);
	
	//批量删除
	boolean batchDelete(String[] trids) throws SQLException;
	
	//FindforPage
	public List<TeachResearchUnit> findForPage(int start, int end,
			String sortStr, String orderStr, Map queryParams);
	
	//得到所有的单位
	public List<TeachResearchUnit> getAllTeachResearchUnits();
	
	//删除所有记录
	public void deleteAll();
	
	//添加一条记录
	public int addTeachResearchUnitRecord(TeachResearchUnit teachResearchUnit);
}

