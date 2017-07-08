package com.guang.web.serviceimpl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.guang.web.dao.DaoTools;
import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GCommentLove;
import com.guang.web.service.GCommentLoveService;

@Service
public class GCommentLoveServiceImpl implements GCommentLoveService{
	@Resource private DaoTools daoTools;
	public void add(GCommentLove commentLove) {
		daoTools.add(commentLove);
	}

	public void delete(long id) {
		daoTools.delete(GCommentLove.class, id);
	}

	public void update(GCommentLove commentLove) {
		daoTools.update(commentLove);
	}

	public GCommentLove find(long id) {
		return daoTools.find(GCommentLove.class, id);
	}

	public GCommentLove find(long commentId, String ip) {
		LinkedHashMap<String, String> colvals = new LinkedHashMap<String, String>();
		colvals.put("commentId =", commentId+"");
		colvals.put("ip =", "'"+ip+"'");
		List<GCommentLove> list = daoTools.find(GCommentLove.class, colvals, 0, 1, null).getList();
		if(list != null && list.size() > 0)
			return list.get(0);
		return null;
	}

	public QueryResult<GCommentLove> findAll() {
		LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();
		lhm.put("id", "desc");
		return daoTools.find(GCommentLove.class, null, null, 0, 1000000, null);
	}

	public QueryResult<GCommentLove> findAll(long commentId, int firstindex,
			int maxNum) {
		LinkedHashMap<String, String> colvals = new LinkedHashMap<String, String>();
		colvals.put("commentId =", commentId+"");
		LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();
		lhm.put("id", "desc");
		return daoTools.find(GCommentLove.class, colvals, firstindex, maxNum, lhm);
	}

}
