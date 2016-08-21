package cn.edu.xmu.service;

import java.util.List;
import java.util.Map;

import cn.edu.xmu.entity.GraduateCombinedTrain;

/**
 * 毕业综合训练情况
 * @author chunfeng
 *
 */
public interface GraduateCombinedTrainService {
	/*
	 * 获得统计数据
	 */
	public List<GraduateCombinedTrain> getGraduateCombinedTrain( Map queryParams);
	
	/*
	 * 获取降序排列后的从0-SIZE个（即排在最前的size个）
	 */
	public List<GraduateCombinedTrain> getFrontGraduateCombinedTrain(int size, boolean isSortByRate, List<GraduateCombinedTrain> gctList);
	
	/*
	 * 获取降序排列后的从 length-SIZE 到  length个 （即排在最后的size个）
	 */
	public List<GraduateCombinedTrain> getBottomGraduateCombinedTrain(int size, boolean isSortByRate, List<GraduateCombinedTrain> gctList);
}
