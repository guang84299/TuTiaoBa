package com.guang.web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GArticle;

@Service
public interface GArticleService {
	void add(GArticle article);
	void delete(long id);
	void update(GArticle article);
	GArticle find(long id);
	QueryResult<GArticle> find(String title);
	QueryResult<GArticle> findAll();
	QueryResult<GArticle> findAll(int firstindex,int maxNum);
	QueryResult<GArticle> findByNew(int type,int firstindex,int maxNum);
	QueryResult<GArticle> findByHot(int type,int firstindex,int maxNum);
	QueryResult<GArticle> findByNew(long tagId,int firstindex,int maxNum);
	QueryResult<GArticle> findByHot(long tagId,int firstindex,int maxNum);
	GArticle findNext(long id);
	GArticle findPre(long id);
	QueryResult<GArticle> findSearch(List<String> vals,int firstindex,int maxNum);
	long findNum();
}
