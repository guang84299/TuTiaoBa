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
	QueryResult<GComment> findAll(long tuTiaoId);
	QueryResult<GComment> findAll(long tuTiaoId,int firstindex,int maxNum);
	QueryResult<GComment> findByNew(long tuTiaoId,int firstindex,int maxNum);
	QueryResult<GComment> findBySuport(long tuTiaoId,int firstindex,int maxNum);
	long findNum(long tuTiaoId);
}
