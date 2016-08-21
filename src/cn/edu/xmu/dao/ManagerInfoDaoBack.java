package cn.edu.xmu.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.ManagerInfo;
import cn.edu.xmu.entity.ManagerInfoBack;

//chunfeng
public interface ManagerInfoDaoBack extends BaseDao<ManagerInfoBack>{
	 //管理人员总数量 
	public int getManagerInfoCount();
	
	//获得管理人员信息
	public List<ManagerInfoBack> getManagerInfo(int start, int end,
			String sortStr, String orderStr);
	
	//添加
	public int addManagerInfo(Map<String, String> valueMap);
	
	//修改
	public int alterManagerInfo(Map<String, String> valueMap, String id);
	
	//批量删除
	boolean batchDelete(String[] miids) throws SQLException;
}
