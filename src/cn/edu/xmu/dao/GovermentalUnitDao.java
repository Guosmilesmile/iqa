package cn.edu.xmu.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.ForeignExchange;
import cn.edu.xmu.entity.GovermentalUnit;
import cn.edu.xmu.entity.SuperMajor;

/*
 * 1-3
 */
public interface GovermentalUnitDao extends BaseDao<GovermentalUnit> {
    //行政单位总数量 
	public int getGovermentalUnitCount();
	
	//获得全部行政单位
	public List<GovermentalUnit> getAllGovermentalUnit(int start, int end,
			String sortStr, String orderStr);
	
	//添加
	public int addGovermentalUnit(String name,String number,String responsibility,String responperson,int serialnumber);
	
	//修改
	public int alterGovermentalUnit(Map<String, String> valueMap, String id);
	
	//批量删除
	boolean batchDelete(String[] guids) throws SQLException;
	
	//FindforPage
	public List<GovermentalUnit> findForPage(int start, int end,
			String sortStr, String orderStr, Map queryParams);
	
	public List<GovermentalUnit> getAllGovermentalUnit();
	
	public void deleteAll();
	
	public int addGovermentalUnitRecord(GovermentalUnit govermentalUnit);
}
