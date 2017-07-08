package com.guang.web.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;

import com.guang.web.mode.GArticle;
import com.guang.web.mode.GComment;
import com.guang.web.mode.GCommentLove;
import com.guang.web.service.GArticleService;
import com.guang.web.service.GCommentLoveService;
import com.guang.web.service.GCommentService;
import com.guang.web.tools.StringTools;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class GCommentAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	@Resource private GArticleService articleService;
	@Resource private GCommentService commentService;
	@Resource private GCommentLoveService commentLoveService;
	
	public String list() {
		if(ActionContext.getContext().getSession().get("user")==null)
		{
			return "toLogin";
		}
		
		String id = ServletActionContext.getRequest().getParameter("id");
		String index = ServletActionContext.getRequest().getParameter("index");
		long tid = Long.parseLong(id);
		int page = 0;
		if(index != null)
		{
			page = Integer.parseInt(index)-1;
		}
		int num = (int) commentService.findNum(tid);
		if(num%100 != 0)
			num = num/100 + 1;
		else
			num = num / 100;
		
		List<GComment> list = commentService.findAll(tid,page*100,100).getList();
		
		ActionContext.getContext().put("list", list);
		ActionContext.getContext().put("page", page+1);
		ActionContext.getContext().put("num", num);
		ActionContext.getContext().put("aid", id);
		
		return "index";
	}
	
	public String deleteComment()
	{
		String id = ServletActionContext.getRequest().getParameter("cid");
		if(StringTools.isEmpty(id))
		{
			return list();
		}
		
		long cid = Long.parseLong(id);
		
		GComment comment = commentService.find(cid);
		if(comment != null)
		{
			long aid = comment.getArticleId();
			
			List<GCommentLove> commentLoves = commentLoveService.findAll(cid, 0, 1000000).getList();
			for(GCommentLove commentLove : commentLoves)
			{
				commentLoveService.delete(commentLove.getId());
			}
			commentService.delete(cid);
			
			GArticle article = articleService.find(aid);
			if(article != null)
			{
				article.setCommentNum(article.getCommentNum()-1);
				articleService.update(article);
			}
		}
		
		
		return list();
	}
}
