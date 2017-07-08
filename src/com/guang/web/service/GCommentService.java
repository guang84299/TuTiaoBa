package com.guang.web.service;

import org.springframework.stereotype.Service;

import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GComment;

@Service
public interface GCommentService {
	void add(GComment comment);
	void delete(long id);
	void update(GComment comment);
	GComment find(long id);
	QueryResult<GComment> find(long articleId,String ip);
	QueryResult<GComment> findAll();
	QueryResult<GComment> findAll(long articleId,int firstindex,int maxNum);
	QueryResult<GComment> findByHot(long articleId,int firstindex,int maxNum);
	long findFloor(long articleId);
	long findNum(long articleId);
}
