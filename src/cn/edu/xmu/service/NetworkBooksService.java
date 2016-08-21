package cn.edu.xmu.service;

import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.NetworkBooks;
/**
 * 
 * @author xiaoping 3.2.4校园网、图书情况 date 2015-8-5
 */
public interface NetworkBooksService {
	public List<NetworkBooks> get(Map<String, String> filter);
}
