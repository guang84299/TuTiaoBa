package com.guang.web.serviceimpl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.guang.web.dao.DaoTools;
import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GTag;
import com.guang.web.service.GTagService;
@Service
public class GTagServiceImpl implements GTagService{
	@Resource private DaoTools daoTools;
	public void add(GTag tag) {
		daoTools.add(tag);
	}

	public void delete(long id) {
		daoTools.delete(GTag.class, id);
	}

	public void update(GTag tag) {
		daoTools.update(tag);
	}

	public GTag find(long id) {
		return daoTools.find(GTag.class, id);
	}

	public GTag find(String name) {
		List<GTag> list = daoTools.find(GTag.class, "name", name, 0, 1, null).getList();
		if(list != null && list.size() > 0)
			return list.get(0);
		return null;
	}

	public QueryResult<GTag> findAll() {
		LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();
		lhm.put("id", "desc");
		return daoTools.find(GTag.class, null, null, 0, 1000000, null);
	}

	public QueryResult<GTag> findAll(int firstindex, int maxNum) {
		LinkedHashMap<String, String> colvals = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();
		lhm.put("id", "desc");
		return daoTools.find(GTag.class, colvals, firstindex, maxNum, lhm);
	}

	public QueryResult<GTag> findByHot(int firstindex, int maxNum) {
		LinkedHashMap<String, String> colvals = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();
		lhm.put("showNum", "desc");
		return daoTools.find(GTag.class, colvals, firstindex, maxNum, lhm);
	}

}
