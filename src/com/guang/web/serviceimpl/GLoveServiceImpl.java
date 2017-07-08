package com.guang.web.serviceimpl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.guang.web.dao.DaoTools;
import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GLove;
import com.guang.web.service.GLoveService;
@Service
public class GLoveServiceImpl implements GLoveService{
	@Resource private DaoTools daoTools;
	public void add(GLove love) {
		daoTools.add(love);
	}

	public void delete(long id) {
		daoTools.delete(GLove.class, id);
	}

	public void update(GLove love) {
		daoTools.update(love);
	}

	public GLove find(long id) {
		return daoTools.find(GLove.class, id);
	}

	public GLove find(long articleId, String ip) {
		LinkedHashMap<String, String> colvals = new LinkedHashMap<String, String>();
		colvals.put("articleId =", articleId+"");
		colvals.put("ip =", "'"+ip+"'");
		List<GLove> list = daoTools.find(GLove.class, colvals, 0, 1, null).getList();
		if(list != null && list.size() > 0)
			return list.get(0);
		return null;
	}

	public QueryResult<GLove> findAll() {
		LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();
		lhm.put("id", "desc");
		return daoTools.find(GLove.class, null, null, 0, 1000000, null);
	}

	public QueryResult<GLove> findAll(long articleId, int firstindex, int maxNum) {
		LinkedHashMap<String, String> colvals = new LinkedHashMap<String, String>();
		colvals.put("articleId =", articleId+"");
		LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();
		lhm.put("id", "desc");
		return daoTools.find(GLove.class, colvals, firstindex, maxNum, lhm);
	}

}
