package com.guang.web.service;

import org.springframework.stereotype.Service;

import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GTuTiao;

@Service
public interface GTuTiaoService {
	void add(GTuTiao tuTiao);
	void delete(long id);
	void update(GTuTiao tuTiao);
	GTuTiao find(long id);
	QueryResult<GTuTiao> find(String title);
	QueryResult<GTuTiao> findAll();
	QueryResult<GTuTiao> findAll(int firstindex,int maxNum);
	QueryResult<GTuTiao> findByNew(int type,int firstindex,int maxNum);
	QueryResult<GTuTiao> findByHot(int type,int firstindex,int maxNum);
	GTuTiao findNext(int type,long id);
	GTuTiao findPre(int type,long id);
	QueryResult<GTuTiao> findSearch(String val,int firstindex,int maxNum);
	long findNum();
}
