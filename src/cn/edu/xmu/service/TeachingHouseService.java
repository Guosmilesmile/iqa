package cn.edu.xmu.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.dao.RoomAreaofTeachingAdministrationDao;
import cn.edu.xmu.dao.SchActUseClassroomDao;
import cn.edu.xmu.daoimpl.RoomAreaofTeachingAdministrationDaoImpl;
import cn.edu.xmu.daoimpl.SchActUseClassroomDaoImpl;
import cn.edu.xmu.entity.TeachingHouse;

public interface TeachingHouseService {

	public List<TeachingHouse> get(Map<String, String> filter);
	

}
