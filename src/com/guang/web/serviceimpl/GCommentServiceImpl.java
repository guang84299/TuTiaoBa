package com.guang.web.serviceimpl;

import java.util.LinkedHashMap;

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

	public QueryResult<GComment> findAll(long tuTiaoId) {
		LinkedHashMap<String, String> colvals = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();
		lhm.put("id", "desc");
		colvals.put("tuTiaoId =", tuTiaoId+"");
		return daoTools.find(GComment.class, colvals, 0, 10000000, lhm);
	}

	public QueryResult<GComment> findAll(long tuTiaoId, int firstindex,int maxNum) {
		LinkedHashMap<String, String> colvals = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();
		lhm.put("id", "desc");
		colvals.put("tuTiaoId =", tuTiaoId+"");
		return daoTools.find(GComment.class, colvals, firstindex, maxNum, lhm);
	}

	public QueryResult<GComment> findByNew(long tuTiaoId, int firstindex,
			int maxNum) {
		LinkedHashMap<String, String> colvals = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();
		lhm.put("id", "desc");
		colvals.put("tuTiaoId =", tuTiaoId+"");
		return daoTools.find(GComment.class, colvals, firstindex, maxNum, lhm);
	}

	public QueryResult<GComment> findBySuport(long tuTiaoId, int firstindex,
			int maxNum) {
		LinkedHashMap<String, String> colvals = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();
		lhm.put("support", "desc");
		lhm.put("id", "desc");
		colvals.put("tuTiaoId =", tuTiaoId+"");
		return daoTools.find(GComment.class, colvals, firstindex, maxNum, lhm);
	}

	public long findNum(long tuTiaoId) {
		LinkedHashMap<String, String> colvals = new LinkedHashMap<String, String>();
		colvals.put("tuTiaoId =", tuTiaoId+"");
		return daoTools.findNum(GComment.class, colvals);
	}

}
