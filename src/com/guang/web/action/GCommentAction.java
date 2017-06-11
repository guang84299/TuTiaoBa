package com.guang.web.action;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.guang.web.mode.GComment;
import com.guang.web.mode.GTuTiao;
import com.guang.web.mode.GUser;
import com.guang.web.service.GCommentService;
import com.guang.web.service.GTuTiaoService;
import com.guang.web.service.GUserService;
import com.guang.web.tools.StringTools;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class GCommentAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	@Resource private GTuTiaoService tuTiaoService;
	@Resource private GCommentService commentService;
	@Resource private  GUserService userService;
	
	public String list() {
		String index = ServletActionContext.getRequest().getParameter("index");
		String tid = ServletActionContext.getRequest().getParameter("tid");
		int page = 0;
		if(index != null)
		{
			page = Integer.parseInt(index)-1;
		}
		int num = (int) commentService.findNum(Long.parseLong(tid));
		if(num%100 != 0)
			num = num/100 + 1;
		else
			num = num / 100;
		
		List<GComment> list = commentService.findBySuport(Long.parseLong(tid),page*100,100).getList();
		
		ActionContext.getContext().put("list", list);
		ActionContext.getContext().put("page", page+1);
		ActionContext.getContext().put("num", num);
		ActionContext.getContext().put("tid", tid);
		
		return "index";
	}
	
	public String deleteComment()
	{
		String id = ServletActionContext.getRequest().getParameter("id");
		String tid = ServletActionContext.getRequest().getParameter("tid");
		if(StringTools.isEmpty(id) || StringTools.isEmpty(tid))
		{
			return "error";
		}
		commentService.delete(Long.parseLong(id));
		return list();
	}
	
	public void addComment()
	{
		String tid = ServletActionContext.getRequest().getParameter("tid");
		String content = ServletActionContext.getRequest().getParameter("content");
		GUser user = (GUser) ActionContext.getContext().getSession().get("user");
		if(StringTools.isEmpty(content) || StringTools.isEmpty(tid) || user == null)
		{
			print(false);
		}
		else
		{
			GTuTiao tuTiao = tuTiaoService.findByTid(tid);
			GComment comment = new GComment(user.getId(), tuTiao.getId(), content, 0);
			comment.setUserName(user.getName());
			comment.setTime("刚刚");
			commentService.add(comment);
			print(JSONObject.fromObject(comment).toString());
		}
	}
	
	public void getComment()
	{
		String tid = ServletActionContext.getRequest().getParameter("tid");
		String index = ServletActionContext.getRequest().getParameter("index");
		if(StringTools.isEmpty(index) || StringTools.isEmpty(tid))
		{
			print(false);
		}
		else
		{
			GTuTiao tuTiao = tuTiaoService.findByTid(tid);
			List<GComment> comments = commentService.findBySuport(tuTiao.getId(), Integer.parseInt(index), 10).getList();
			for(GComment comment : comments)
			{
				comment.setUserName(userService.find(comment.getUserId()).getName());
				long t2 = new Date().getTime() - comment.getCdate().getTime();
				String t3 = "刚刚";
				if(t2 > 1000*60 && t2 <= 1000*60*60)
					t3 = (t2/1000/60) + "分钟前";
				else if(t2 > 1000*60*60 && t2 <= 1000*60*60*24)
					t3 = (t2/1000/60/60) + "小时前";
				else if(t2 > 1000*60*60*24)
					t3 = (t2/1000/60/60/24) + "天前";
				comment.setTime(t3);
			}
			print(JSONArray.fromObject(comments).toString());
		}
	}
	
	public void support()
	{
		String id = ServletActionContext.getRequest().getParameter("id");
		if(StringTools.isEmpty(id))
		{
			print(false);
		}
		else
		{
			GComment comment = commentService.find(Long.parseLong(id));
			if(comment!=null)
			{
				comment.setSupport(comment.getSupport()+1);
				commentService.update(comment);
				print(comment.getSupport());
			}
			else
			{
				print(false);
			}
		}
	}
	
	public void print(Object obj)
	{
		try {
			ServletActionContext.getResponse().getWriter().print(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
