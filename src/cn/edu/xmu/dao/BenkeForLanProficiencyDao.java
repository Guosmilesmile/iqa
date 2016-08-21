package cn.edu.xmu.dao;


import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.BenkeForLanProficiency;
import cn.edu.xmu.entity.BenkeMentalHealth;




/**
 * 
 * @author Lee
 * 6-2-1-12本科生外语水平及学习情况调查表
 * date 2015-07-13
 */
public interface BenkeForLanProficiencyDao {

	
	/**
	 * 插入数据
	 * 
	 * @param bflp
	 * 		本科生外语水平及学习情况 实体
	 * @return 
	 * 		插入数据结果成功与否
	 */
	public int addRecord(BenkeForLanProficiency bflp);
	
	
	/**
	 * 
	 * @param bflpids
	 * @return
	 * @throws SQLException
	 */
	boolean batchDelete(String[] bflpids) throws SQLException;
	
	/**
	 * 
	 * @param valueMap
	 * @param id
	 * @return
	 */
	public int alterBenkeForLanProficiency(Map<String, String> valueMap, String id);
	
	/**
	 * 
	 * @param queryParams
	 * @return 
	 * 		根据查询条件返回符合条件的条数
	 */
	public int getBenkeForLanProficiencyCount(Map queryParams);
	
	
	/**
	 * 
	 * @param start  开始的标记
	 * @param end    结束的标记
	 * @param sortStr 排序的字段
	 * @param orderStr 升降选项
	 * @param queryParams 查询参数
	 * @return
	 */
	 public List<BenkeForLanProficiency> getAllBenkeForLanProficiency(int start, int end,
				String sortStr, String orderStr, Map queryParams);
	 
	 
	 public void deleteByCollegeandDeadline(String college)
				throws SQLException;
	 public List<BenkeForLanProficiency> getAllBenkeForLanProficiency();
}
