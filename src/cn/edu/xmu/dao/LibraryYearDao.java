package cn.edu.xmu.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.LibraryYear;

/**
 * 
 * @author zshbleaker
 *
 */

public interface LibraryYearDao {

	public int addRecord(LibraryYear ly);
	
	public boolean batchDelete(String[] lyids) throws SQLException;
	
	public int alterLibraryYear(Map<String, String> valueMap, String id);
	
	public int getLibraryYearCount(Map queryParams);
	
	public List<LibraryYear> getAll();
	
	public void deleteByCollegeandDeadline(String college, Date deadline)
			throws SQLException;
	
	public List<LibraryYear> getLibraryYear(int start, int end, String sortStr, String orderStr, Map queryParams);
}
