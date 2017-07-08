package com.guang.web.service;

import org.springframework.stereotype.Service;

import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GTag;

@Service
public interface GTagService {
	void add(GTag tag);
	void delete(long id);
	void update(GTag tag);
	GTag find(long id);
	GTag find(String name);
	QueryResult<GTag> findAll();
	QueryResult<GTag> findAll(int firstindex,int maxNum);
	QueryResult<GTag> findByHot(int firstindex,int maxNum);
}
