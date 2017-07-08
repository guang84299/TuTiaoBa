package com.guang.web.serviceimpl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.guang.web.dao.DaoTools;
import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GArticle;
import com.guang.web.service.GArticleService;
@Service
public class GArticleServiceImpl implements GArticleService{
	@Resource private DaoTools daoTools;
	public void add(GArticle article) {
		daoTools.add(article);
	}

	public void delete(long id) {
		daoTools.delete(GArticle.class, id);
	}

	public void update(GArticle article) {
		daoTools.update(article);
	}

	public GArticle find(long id) {
		return daoTools.find(GArticle.class, id);
	}

	public QueryResult<GArticle> find(String title) {
		return daoTools.find(GArticle.class, "title", title, 0, 10, null);
	}

	public QueryResult<GArticle> findAll() {
		LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();
		lhm.put("id", "desc");
		return daoTools.find(GArticle.class, null, null, 0, 1000000, null);
	}

	public QueryResult<GArticle> findAll(int firstindex, int maxNum) {
		LinkedHashMap<String, String> colvals = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();
		lhm.put("id", "desc");
		return daoTools.find(GArticle.class, colvals, firstindex, maxNum, lhm);
	}

	public QueryResult<GArticle> findByNew(int type, int firstindex, int maxNum) {
		LinkedHashMap<String, String> colvals = new LinkedHashMap<String, String>();
		colvals.put("type =", type+"");
		LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();
		lhm.put("id", "desc");
		return daoTools.find(GArticle.class, colvals, firstindex, maxNum, lhm);
	}

	public QueryResult<GArticle> findByHot(int type, int firstindex, int maxNum) {
		LinkedHashMap<String, String> colvals = new LinkedHashMap<String, String>();
		colvals.put("type =", type+"");
		LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();
		lhm.put("showNum", "desc");
		return daoTools.find(GArticle.class, colvals, firstindex, maxNum, lhm);
	}

	public QueryResult<GArticle> findByNew(long tagId, int firstindex,
			int maxNum) {
		LinkedHashMap<String, String> colvals = new LinkedHashMap<String, String>();
		colvals.put("tagId =", tagId+"");
		LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();
		lhm.put("id", "desc");
		return daoTools.find(GArticle.class, colvals, firstindex, maxNum, lhm);
	}

	public QueryResult<GArticle> findByHot(long tagId, int firstindex,
			int maxNum) {
		LinkedHashMap<String, String> colvals = new LinkedHashMap<String, String>();
		colvals.put("tagId =", tagId+"");
		LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();
		lhm.put("showNum", "desc");
		return daoTools.find(GArticle.class, colvals, firstindex, maxNum, lhm);
	}

	public GArticle findNext(long id) {
		LinkedHashMap<String, String> colvals = new LinkedHashMap<String, String>();
		colvals.put("id >", id+"");
		List<GArticle> list = daoTools.find(GArticle.class, colvals, 0, 1, null).getList();
		if(list.size() > 0)
			return list.get(0);
		return null;
	}

	public GArticle findPre(long id) {
		LinkedHashMap<String, String> colvals = new LinkedHashMap<String, String>();
		colvals.put("id <", id+"");
		LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();
		lhm.put("id", "desc");
		List<GArticle> list = daoTools.find(GArticle.class, colvals, 0, 1, lhm).getList();
		if(list.size() > 0)
			return list.get(0);
		return null;
	}

	public QueryResult<GArticle> findSearch(List<String> vals, int firstindex,
			int maxNum) {
		String sql = "";
		for(String val : vals)
		{
			sql += ("title like " + "'%"+val+"%' or ");
		}
		sql = sql.substring(0,sql.length() - 4);
		return daoTools.find(GArticle.class, sql, firstindex, maxNum, null);
	}

	public long findNum() {
		return daoTools.findNum(GArticle.class, null);
	}

}
