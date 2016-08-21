package cn.edu.xmu.dao;

import java.sql.Date;
import java.util.List;

import cn.edu.xmu.entity.TableList;
import cn.edu.xmu.entity.TableName;

public interface TableListDao extends BaseDao<TableList>{
	public List<TableList> getTableLists();
	public List<TableList> getWatchTable(String userid);
	public Date getTableDate(String tableid);
	
	//根据id获取在数据库中表的名称
	public String getTableNameById(String tableId);
	public List<TableList> getTableListsLike(String like);
	public boolean changePublish(String flag,String tableid,Date datetime);
	/*public boolean changePublishAll(String flag);*/
	public boolean changePublishPage(String flag,String tableid);
	public boolean changePublishAll(String flag,String tableid,Date datetime);
	public String getTablename(String tableid);
	

}
