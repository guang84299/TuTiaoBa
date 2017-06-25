package com.guang.web.serviceimpl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.guang.web.dao.DaoTools;
import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GTuTiao;
import com.guang.web.service.GTuTiaoService;
@Service
public class GTuTiaoServiceImpl implements GTuTiaoService{
	@Resource private DaoTools daoTools;
	public void add(GTuTiao tuTiao) {
		daoTools.add(tuTiao);
	}

	public void delete(long id) {
		daoTools.delete(GTuTiao.class, id);
	}

	public void update(GTuTiao tuTiao) {
		daoTools.update(tuTiao);
	}

	public GTuTiao find(long id) {
		return daoTools.find(GTuTiao.class, id);
	}

	public QueryResult<GTuTiao> find(String title) {
		return daoTools.find(GTuTiao.class, "title", title, 0, 10, null);
	}

	public QueryResult<GTuTiao> findAll() {
		LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();
		lhm.put("id", "desc");
		return daoTools.find(GTuTiao.class, null, null, 0, 1000000, null);
	}

	public long findNum() {
		return daoTools.findNum(GTuTiao.class, null);
	}

	public QueryResult<GTuTiao> findAll(int firstindex,int maxNum) {
		LinkedHashMap<String, String> colvals = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();
		lhm.put("id", "desc");
		return daoTools.find(GTuTiao.class, colvals, firstindex, maxNum, lhm);
	}

	public QueryResult<GTuTiao> findByNew(int type,int firstindex,int maxNum) {
		LinkedHashMap<String, String> colvals = new LinkedHashMap<String, String>();
		colvals.put("type =", type+"");
		LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();
		lhm.put("id", "desc");
		return daoTools.find(GTuTiao.class, colvals, firstindex, maxNum, lhm);
	}

	public QueryResult<GTuTiao> findByHot(int type,int firstindex,int maxNum) {
		LinkedHashMap<String, String> colvals = new LinkedHashMap<String, String>();
		colvals.put("type =", type+"");
		LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();
		lhm.put("showNum", "desc");
		return daoTools.find(GTuTiao.class, colvals, firstindex, maxNum, lhm);
	}

	public QueryResult<GTuTiao> findSearch(String val, int firstindex,
			int maxNum) {
		LinkedHashMap<String, String> colvals = new LinkedHashMap<String, String>();
		colvals.put("title like", "'%"+val+"%'");
		return daoTools.find(GTuTiao.class, colvals, firstindex, maxNum, null);
	}

	public GTuTiao findNext(int type, long id) {
		LinkedHashMap<String, String> colvals = new LinkedHashMap<String, String>();
		colvals.put("type =", type+"");
		colvals.put("id >", id+"");
		List<GTuTiao> list = daoTools.find(GTuTiao.class, colvals, 0, 1, null).getList();
		if(list.size() > 0)
			return list.get(0);
		return null;
	}

	public GTuTiao findPre(int type, long id) {
		LinkedHashMap<String, String> colvals = new LinkedHashMap<String, String>();
		colvals.put("type =", type+"");
		colvals.put("id <", id+"");
		List<GTuTiao> list = daoTools.find(GTuTiao.class, colvals, 0, 1, null).getList();
		if(list.size() > 0)
			return list.get(0);
		return null;
	}

	

}
