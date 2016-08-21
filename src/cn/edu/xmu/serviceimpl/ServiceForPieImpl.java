package cn.edu.xmu.serviceimpl;

import java.util.Map;

import cn.edu.xmu.dao.DaoForPie;
import cn.edu.xmu.daoimpl.DaoForPieImpl;
import cn.edu.xmu.service.ServiceForPie;

public class ServiceForPieImpl implements ServiceForPie{

	private DaoForPie daoForPie = new DaoForPieImpl(); 
	@Override
	public Map<String, Integer> getNumByAttribute(String tableName,
			String attribute, Map<String, String> params) {
		return daoForPie.getNumByAttribute(tableName, attribute, params);
	}

}
