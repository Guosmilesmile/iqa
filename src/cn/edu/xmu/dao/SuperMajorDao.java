package cn.edu.xmu.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.News;
import cn.edu.xmu.entity.SuperMajor;

public interface SuperMajorDao extends BaseDao<SuperMajor>{
	       //获得全部优势学科
	public List<SuperMajor> getAllSuperMajor(Map queryParams);
			public List<SuperMajor> getAllSuperMajor();
			public List<SuperMajor> getAllSuperMajor(int start, int end,
					String sortStr, String orderStr, Map queryParams);
    
			//添加优势学科
			public int addSuperMajor(String name,String number,String m_class,String c_start,String p_start,String s_start,String respon_p,String pid,int sm_serialnumber);
			//修改优势学科信息
			public int alterSuperMajor(Map<String, String> valueMap, String id);
			//删除优势学科
			public int deleteSMajorById(String id,String serialnumber);
			//批量删除
			boolean batchDelete(String[] smids) throws SQLException;
			//获得当前最大序号
			public int getMaxSerialNum();
			
			public int getSuperMajorCount(Map queryParams);
			
			public List<SuperMajor> findForPage(int start, int end,
					String sortStr, String orderStr, Map queryParams);
			//添加记录
			public int addSuperMajorRecord(SuperMajor superMajor);
			//清空记录
			public void deleteAll();
}
