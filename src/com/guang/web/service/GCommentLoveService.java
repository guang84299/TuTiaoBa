package com.guang.web.service;

import org.springframework.stereotype.Service;

import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GCommentLove;

@Service
public interface GCommentLoveService {
	void add(GCommentLove commentLove);
	void delete(long id);
	void update(GCommentLove commentLove);
	GCommentLove find(long id);
	GCommentLove find(long commentId,String ip);
	QueryResult<GCommentLove> findAll();
	QueryResult<GCommentLove> findAll(long commentId,int firstindex,int maxNum);
}
