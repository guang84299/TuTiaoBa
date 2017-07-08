package com.guang.web.serviceimpl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.guang.web.dao.DaoTools;
import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GComment;
import com.guang.web.service.GCommentService;
@Service
public class GCommentServiceImpl implements GCommentService{
	@Resource private DaoTools daoTools;
	public void add(GComment comment) {
		daoTools.add(comment);
	}

	public void delete(long id) {
		daoTools.delete(GComment.class, id);
	}

	public void update(GComment comment) {
		daoTools.update(comment);
	}

	public GComment find(long id) {
		return daoTools.find(GComment.class, id);
	}

	public QueryResult<GComment> find(long articleId,String ip) {
		LinkedHashMap<String, String> colvals = new LinkedHashMap<String, String>();
		colvals.put("articleId =", articleId+"");
		colvals.put("ip =", "'"+ip+"'");
		return daoTools.find(GComment.class, colvals, 0, 100000, null);
	}

	public QueryResult<GComment> findAll() {
		LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();
		lhm.put("id", "desc");
		return daoTools.find(GComment.class, null, null, 0, 1000000, null);
	}

	public QueryResult<GComment> findAll(long articleId,int firstindex, int maxNum) {
		LinkedHashMap<String, String> colvals = new LinkedHashMap<String, String>();
		colvals.put("articleId =", articleId+"");
		LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();
		lhm.put("id", "desc");
		return daoTools.find(GComment.class, colvals, firstindex, maxNum, lhm);
	}

	public QueryResult<GComment> findByHot(long articleId,int firstindex, int maxNum) {
		LinkedHashMap<String, String> colvals = new LinkedHashMap<String, String>();
		colvals.put("articleId =", articleId+"");
		LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();
		lhm.put("loveNum", "desc");
		return daoTools.find(GComment.class, colvals, firstindex, maxNum, lhm);
	}

	public long findFloor(long articleId) {
		LinkedHashMap<String, String> colvals = new LinkedHashMap<String, String>();
		colvals.put("articleId =", articleId+"");
		LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();
		lhm.put("floor", "desc");
		List<GComment> list = daoTools.find(GComment.class, colvals, 0, 1, lhm).getList();
		if(list != null && list.size() > 0)
		{
			return list.get(0).getFloor()+1;
		}
		return 1;
	}

}
