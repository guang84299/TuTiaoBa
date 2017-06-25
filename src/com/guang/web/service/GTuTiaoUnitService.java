package com.guang.web.service;

import org.springframework.stereotype.Service;

import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GTuTiaoUnit;
@Service
public interface GTuTiaoUnitService {
	void add(GTuTiaoUnit unit);
	void delete(long id);
	void update(GTuTiaoUnit unit);
	GTuTiaoUnit find(long id);
	QueryResult<GTuTiaoUnit> findAll(long tuTiaoId);
	QueryResult<GTuTiaoUnit> findAll(long tuTiaoId,int firstindex,int maxNum);
	long findNum(long tuTiaoId);
}
