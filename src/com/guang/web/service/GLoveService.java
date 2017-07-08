package com.guang.web.service;

import org.springframework.stereotype.Service;

import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GLove;

@Service
public interface GLoveService {
	void add(GLove love);
	void delete(long id);
	void update(GLove love);
	GLove find(long id);
	GLove find(long articleId,String ip);
	QueryResult<GLove> findAll();
	QueryResult<GLove> findAll(long articleId,int firstindex,int maxNum);
}
