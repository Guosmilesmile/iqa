package cn.edu.xmu.service;

import java.util.Map;

import cn.edu.xmu.entity.UndergraduateAwardLoanInfo;

public interface UndergraduateAwardLoanInfoService {
	/**
	 * 本科生奖贷补情况
	 * @param params
	 * @return
	 */
	public UndergraduateAwardLoanInfo getUndergraduateAwardLoanInfo(Map params);
}
