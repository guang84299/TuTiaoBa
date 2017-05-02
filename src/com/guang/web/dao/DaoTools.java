package com.guang.web.dao;

import java.util.LinkedHashMap;
import java.util.List;


public interface DaoTools {
	public void add(Object obj);
	public <T> void delete(Class<T> entityclass,Object id);
	public void update(Object obj);
	public <T> T find(Class<T> entityclass, Object id);
	public <T> QueryResult<T> find(Class<T> entityclass, String columnName, String value ,int firstindex,int maxresult, LinkedHashMap<String, String> orderby);
	public <T> QueryResult<T> find(Class<T> entityclass, String columnName, String value ,String columnName2, String value2 ,int firstindex,int maxresult, LinkedHashMap<String, String> orderby);
	public <T> QueryResult<T> find(Class<T> entityclass, LinkedHashMap<String, String> colvals,int firstindex,int maxresult, LinkedHashMap<String, String> orderby);
	public <T> QueryResult<T> find(Class<T> entityclass, String sql,int firstindex,int maxresult, LinkedHashMap<String, String> orderby);
	public <T> QueryResult<T> find(Class<T> entityclass, List<String> fileds,LinkedHashMap<String, String> colvals);
	public <T> long findNum(Class<T> entityclass, LinkedHashMap<String, String> colvals);
	public <T> long findNum(Class<T> entityclass, List<String> fileds,LinkedHashMap<String, String> colvals);
}
