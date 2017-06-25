package com.guang.web.serviceimpl;

import java.util.LinkedHashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.guang.web.dao.DaoTools;
import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GTuTiaoUnit;
import com.guang.web.service.GTuTiaoUnitService;
@Service
public class GTuTiaoUnitServiceImpl implements GTuTiaoUnitService{
	@Resource private DaoTools daoTools;
	public void add(GTuTiaoUnit comment) {
		daoTools.add(comment);
	}

	public void delete(long id) {
		daoTools.delete(GTuTiaoUnit.class, id);
	}

	public void update(GTuTiaoUnit comment) {
		daoTools.update(comment);
	}

	public GTuTiaoUnit find(long id) {
		return daoTools.find(GTuTiaoUnit.class, id);
	}

	public QueryResult<GTuTiaoUnit> findAll(long tuTiaoId) {
		LinkedHashMap<String, String> colvals = new LinkedHashMap<String, String>();
//		LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();
//		lhm.put("id", "desc");
		colvals.put("tuTiaoId =", tuTiaoId+"");
		return daoTools.find(GTuTiaoUnit.class, colvals, 0, 10000000, null);
	}

	public QueryResult<GTuTiaoUnit> findAll(long tuTiaoId, int firstindex,int maxNum) {
		LinkedHashMap<String, String> colvals = new LinkedHashMap<String, String>();
//		LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();
//		lhm.put("id", "desc");
		colvals.put("tuTiaoId =", tuTiaoId+"");
		return daoTools.find(GTuTiaoUnit.class, colvals, firstindex, maxNum, null);
	}

	public long findNum(long tuTiaoId) {
		LinkedHashMap<String, String> colvals = new LinkedHashMap<String, String>();
		colvals.put("tuTiaoId =", tuTiaoId+"");
		return daoTools.findNum(GTuTiaoUnit.class, colvals);
	}

}
