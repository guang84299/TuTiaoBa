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
	GTuTiao findByTid(String tid);
	void deleteByTid(String tid);//最好用delete(long id);
	QueryResult<GTuTiao> find(String title);
	QueryResult<GTuTiao> findAll();
	QueryResult<GTuTiao> findAll(int firstindex);
	QueryResult<GTuTiao> findByNew(int firstindex,int maxNum);
	QueryResult<GTuTiao> findByHot(int firstindex,int maxNum);
	QueryResult<GTuTiao> findSearch(String val,int firstindex,int maxNum);
	long findNum();
}
