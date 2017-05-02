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
	public void delete(long id) {
		daoTools.delete(GTuTiaoUnit.class, id);
	}
	
	public void add(GTuTiaoUnit unit) {
		daoTools.add(unit);
	}
	public void update(GTuTiaoUnit unit) {
		daoTools.update(unit);
	}
	public GTuTiaoUnit find(long id) {
		return daoTools.find(GTuTiaoUnit.class, id);
	}
	public QueryResult<GTuTiaoUnit> findAll(long tuTiaoId) {
		return daoTools.find(GTuTiaoUnit.class, "tuTiaoId", tuTiaoId+"", 0, 100, null);
	}
	public QueryResult<GTuTiaoUnit> findAll() {
		LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();
		lhm.put("id", "desc");
		return daoTools.find(GTuTiaoUnit.class, null, null, 0, 1000000, null);
	}

	public QueryResult<GTuTiaoUnit> findOne(long tuTiaoId) {
		return daoTools.find(GTuTiaoUnit.class, "tuTiaoId", tuTiaoId+"", 0, 1, null);
	}

}
