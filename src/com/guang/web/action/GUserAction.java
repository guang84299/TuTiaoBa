package com.guang.web.action;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;












import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GUser;
import com.guang.web.service.GUserService;
import com.guang.web.tools.GZipTool;
import com.guang.web.tools.StringTools;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


public class GUserAction extends ActionSupport{

	private static final long serialVersionUID = -6570772391551890119L;
	private final static Logger logger = LoggerFactory.getLogger(GUserAction.class);
	@Resource private  GUserService userService;
	
	private File source;
	private String sourceFileName;
	
	public String list()
	{
		QueryResult<GUser>  qr = userService.findAlls(0);
		
		String sindex = ServletActionContext.getRequest().getParameter("index");
		int index = 0;
		if (sindex != null && !"".equals(sindex))
			index = Integer.parseInt(sindex);
		Long num = qr.getNum();
		int start = index * 100;
		if (start > num) {
			start = 0;
		}
		
		List<GUser> userList = userService.findAlls(start).getList();
		if(userList == null)
		{
			userList = new ArrayList<GUser>();
		}
		
		
		ActionContext.getContext().put("maxNum", num);
		ActionContext.getContext().put("userList", userList);
		ActionContext.getContext().put("pages", "user");
		
		return "index";
	}
	
	
	
	//删除user
	public void deleteUser()
	{
		String id = ServletActionContext.getRequest().getParameter("data");
		if(id != null && !"".equals(id))
		{
			long ids = Long.parseLong(id);
			userService.delete(ids);
			
			
		}
	}
	
	//初始化数据
	public void init()
	{
//		GSysVal sysVal = new GSysVal(0, false, 2, "", "", 0, 1.0f);
//		sysValService.save(sysVal);
		
	}

	
	public void print(Object data) {
		try {
			ServletActionContext.getResponse().getWriter().print(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 查询用户
	 */
	public String findUser(){
		String regFrom = ServletActionContext.getRequest().getParameter("regDate_from");
		String loginFrom = ServletActionContext.getRequest().getParameter("loginDate_from");
		String regTo = ServletActionContext.getRequest().getParameter("regDate_to");
		String loginTo = ServletActionContext.getRequest().getParameter("loginDate_to");
		
		List<GUser> gUser = null ; 
		//注册时间
		if (null!=regFrom&&!"".equals(regFrom) && null!=regTo&&!"".equals(regTo)) {
			LinkedHashMap<String, String> colvals = new LinkedHashMap<String, String>();			
			colvals.put("createdDate >=", "'"+regFrom+"'");
			colvals.put("createdDate <", "'"+regTo+"'");
			gUser = userService.find(colvals).getList();
			long m = userService.find(colvals).getNum();
			ActionContext.getContext().put("maxNum", m);
			ActionContext.getContext().put("userList", gUser);
			ActionContext.getContext().put("pages", "user");
			return "index";	 
		}
		if (null!=loginFrom&&!"".equals(loginFrom) && null!=loginTo&&!"".equals(loginTo)) {
			//登录时间
			LinkedHashMap<String, String> colvals2 = new LinkedHashMap<String, String>();			
			colvals2.put("updatedDate >=", "'"+loginFrom+"'");
			colvals2.put("updatedDate <", "'"+loginTo+"'");
			long n = userService.find(colvals2).getNum();
			gUser = userService.find(colvals2).getList();
			ActionContext.getContext().put("maxNum", n);
			ActionContext.getContext().put("userList", gUser);
			ActionContext.getContext().put("pages", "user");
			
			return "index";
		}
		return "index";
	}
	
	//验证是否已经注册
	public void validates()
	{
		String data = ServletActionContext.getRequest().getParameter("data");
		JSONObject obj = JSONObject.fromObject(data);
		
		String name = obj.getString("name");
		String password = obj.getString("password");
		GUser user = userService.find(name,password);
		JSONObject result = new JSONObject();
		if(user != null)
		{
			result.put("result", true);			

			
			userService.update(user);
			
			loginSuccess(user.getName());
		}
		else
		{
			result.put("result", false);
		}
		print(result.toString());
	}
	
	//登录
	public void login()
	{
		String data = ServletActionContext.getRequest().getParameter("data");
		JSONObject obj = JSONObject.fromObject(data);
		String name = obj.getString("name");
		String password = obj.getString("password");
		String networkType = obj.getString("networkType");
		
		GUser user = userService.find(name,password);
		obj = new JSONObject();
		if(user != null)
		{			
			obj.put("result", true);
			
			userService.update(user);
			
			loginSuccess(user.getName());
		}
		else
		{
			obj.put("result", false);
		}
		print(obj.toString());
	}
	
	//注册
	public void register()
	{
		String data = ServletActionContext.getRequest().getParameter("data");
		GUser user = (GUser) JSONObject.toBean(JSONObject.fromObject(data),GUser.class);
		if(user == null)
		{
			return;
		}
		
		
		userService.add(user);
		
		
		
		logger.info(user.getName()+" 注册成功！");
		loginSuccess(user.getName());
		print("1");	
	}
	//登录成功
	public void loginSuccess(String name)
	{
		logger.info(name+" 登录成功！");
		
	}
	
	//退出登录
	public void loginOut(String name,String password)
	{
		GUser user = userService.find(name,password);
		if(user != null)
		{
			
        	
        	userService.update(user);        	
		}
	}
	
	
	
	
	
	public String debug()
	{
		ActionContext.getContext().put("pages", "debug");
		return "index";
	}
	
	public String uploadSource()
	{
		if(source == null)
		{
			ActionContext.getContext().put("uploadSource", "上传失败！");
			ActionContext.getContext().put("pages", "debug");
			return "index";
		}
		
		URL url = GUserAction.class.getClassLoader().getResource("log4j.properties");
		String relpath = url.getPath().replace("classes/log4j.properties", "");
		//上传
		File file = new File(new File(relpath), sourceFileName);
		if (!file.getParentFile().exists())
			file.getParentFile().mkdirs();
		try {
			FileUtils.copyFile(source, file);
			//解压
			GZipTool.unzip(file.getAbsolutePath());
			file.delete();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ActionContext.getContext().put("uploadSource", "上传成功！");
		ActionContext.getContext().put("pages", "debug");
		return "index";
	}

	public File getSource() {
		return source;
	}

	public void setSource(File source) {
		this.source = source;
	}

	public String getSourceFileName() {
		return sourceFileName;
	}

	public void setSourceFileName(String sourceFileName) {
		this.sourceFileName = sourceFileName;
	}
	
	
}
