package com.guang.web.action;


import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
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
import com.guang.web.mode.GPermission;
import com.guang.web.mode.GUser;
import com.guang.web.service.GPermissionService;
import com.guang.web.service.GUserService;
import com.guang.web.tools.GTools;
import com.guang.web.tools.GZipTool;
import com.guang.web.tools.StringTools;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


public class GUserAction extends ActionSupport{

	private static final long serialVersionUID = 1;
	private final static Logger logger = LoggerFactory.getLogger(GUserAction.class);
	@Resource private  GUserService userService;
	@Resource private GPermissionService permissionService;
	
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
		
		GPermission permission = new GPermission();
		permission.setAdmin(true);
		permission.setActive(true);
		permissionService.add(permission);
		GUser user = new GUser(permission.getId(), "guang", "920616", "842997290@qq.com");
		userService.add(user);
	}
	
	public String toLogin()
	{
		String name = ServletActionContext.getRequest().getParameter("username");
		String password = ServletActionContext.getRequest().getParameter("password");
		
		if(StringTools.isEmpty(name) || StringTools.isEmpty(password))
		{
			return "toLogin";
		}
		else
		{
			GUser user = userService.find(name,password);
			if(user == null)
			{
				return "toLogin";
			}
			else
			{
				ActionContext.getContext().getSession().put("user", user);
				return "loginSuccess";
			}
		}
	}

	
	public void print(Object data) {
		try {
			ServletActionContext.getResponse().getWriter().print(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String login()
	{
		//生成随机验证码
		String code = GTools.getRandCode();
		ActionContext.getContext().getSession().put("code", code);
		return "login";
	}
	//登录
	public void vallogin()
	{
		String name = ServletActionContext.getRequest().getParameter("username");
		String password = ServletActionContext.getRequest().getParameter("password");
		String code = ServletActionContext.getRequest().getParameter("code");
		
		if(StringTools.isEmpty(name) || StringTools.isEmpty(password) ||StringTools.isEmpty(code))
		{
			print(1);
		}
		else
		{
			String c = (String) ActionContext.getContext().getSession().get("code");
			if(!code.equals(c))
			{
				print(2);
			}
			else
			{
				GUser user = userService.find(name,password);
				if(user == null)
				{
					print(1);
				}
				else
				{
					GPermission permission = permissionService.find(user.getPermissionId());
					if(permission.isActive())
					{
						ActionContext.getContext().getSession().put("user", user);
						print(true);
					}
					else
					{
						print(3);
					}
				}
			}
		}
	}
	
	public String toRegister()
	{
		return "register";
	}
	public void checkName()
	{
		String username = ServletActionContext.getRequest().getParameter("username");
		if(StringTools.isEmpty(username))
		{
			print(false);
			return;
		}
		GUser user = userService.findByName(username);
		if(user != null)
		{
			print(false);
		}
		else
		{
			print(true);
		}
	}
	public void checkMail()
	{
		String email = ServletActionContext.getRequest().getParameter("email");
		if(StringTools.isEmpty(email))
		{
			print(false);
			return;
		}
		GUser user = userService.findByEmail(email);
		if(user != null)
		{
			print(false);
		}
		else
		{
			print(true);
		}
	}
	//注册
	public void register()
	{
		final String name = ServletActionContext.getRequest().getParameter("name");
		String password = ServletActionContext.getRequest().getParameter("password");
		final String email = ServletActionContext.getRequest().getParameter("email");
		if(StringTools.isEmpty(name) || StringTools.isEmpty(password) || StringTools.isEmpty(email))
		{
			print(false);
		}
		else
		{
			GPermission permission = new GPermission();
			permission.setAdmin(false);
			permission.setActive(false);
			permissionService.add(permission);
			GUser user = new GUser(permission.getId(), name, password, email);
			userService.add(user);
			print(true);
			
			new Thread(){
				public void run() {
					GTools.sendMail(email, "");
				};
			}.start();
		}
		
	}
	public String active()
	{
//		String name = ServletActionContext.getRequest().getParameter("name");
		String email = ServletActionContext.getRequest().getParameter("mail");
		if(StringTools.isEmpty(email) )
		{
			return "error";
		}
//		try {
//			name = URLDecoder.decode(name, "utf-8");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
		GUser user = userService.findByEmail(email);
		if(user != null)
		{
			GPermission permission = permissionService.find(user.getPermissionId());
			permission.setActive(true);
			permissionService.update(permission);
			ActionContext.getContext().put("active", true);
			return login();
		}
		return "error";
	}

	//退出登录
	public String loginOut()
	{
		ActionContext.getContext().getSession().put("user", null);
		return "loginOut";
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
