package cn.edu.xmu.serviceimpl;

import java.util.ArrayList;

import cn.edu.xmu.dao.TableDao;
import cn.edu.xmu.dao.TableListDao;
import cn.edu.xmu.daoimpl.TableDaoImpl;
import cn.edu.xmu.daoimpl.TableListDaoImpl;
import cn.edu.xmu.service.TableService;

public class TableServiceImpl implements TableService{
	private TableDao tableDao = new TableDaoImpl();
	private TableListDao tableListDao = new TableListDaoImpl();
	
	@Override
	public ArrayList<String> getAttibutes(String tableName) {
		return tableDao.getAttibutes(tableName);
	}

	@Override
	public String getTableNameById(String tableId) {
		return tableListDao.getTableNameById(tableId);
	}

}
