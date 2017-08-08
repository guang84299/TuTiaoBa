package com.guang.web.action;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.guang.web.mode.GArticle;
import com.guang.web.mode.GComment;
import com.guang.web.mode.GCommentLove;
import com.guang.web.mode.GLove;
import com.guang.web.mode.GTag;
import com.guang.web.service.GArticleService;
import com.guang.web.service.GCommentLoveService;
import com.guang.web.service.GCommentService;
import com.guang.web.service.GLoveService;
import com.guang.web.service.GTagService;
import com.guang.web.tools.GTools;
import com.guang.web.tools.StringTools;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class GArticleAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	@Resource private GArticleService articleService;
	@Resource private GCommentService commentService;
	@Resource private GCommentLoveService commentLoveService;
	@Resource private GLoveService loveService;
	@Resource private GTagService tagService;
	
	private File pic;
	private String picFileName;
	
	
	public String list() {
		if(ActionContext.getContext().getSession().get("user")==null)
		{
			return "toLogin";
		}
		String index = ServletActionContext.getRequest().getParameter("index");
		int page = 0;
		if(index != null)
		{
			page = Integer.parseInt(index)-1;
		}
		int num = (int) articleService.findNum();
		if(num%100 != 0)
			num = num/100 + 1;
		else
			num = num / 100;
		
		List<GArticle> list = articleService.findAll(page*100,100).getList();
		
		ActionContext.getContext().put("list", list);
		ActionContext.getContext().put("page", page+1);
		ActionContext.getContext().put("num", num);
		
		return "index";
	}
	
	public String home()
	{
		String index = ServletActionContext.getRequest().getParameter("index");
		int page = 0;
		if(index != null)
		{
			page = Integer.parseInt(index);
		}
		
		//最新文章
		List<GArticle>  articles = articleService.findByNew(1, page, 20).getList();
		for(GArticle article : articles)
		{
			article.setTag(tagService.find(article.getTagId()));
		}
		//热点推荐
		List<GArticle>  articlehots = articleService.findByHot(1, 0, 6).getList();
		for(GArticle article : articlehots)
		{
			article.setTag(tagService.find(article.getTagId()));
		}
		//热门标签
		List<GTag> tags = tagService.findByHot(0, 20).getList();
		
		boolean more = (articles.size() == 20);
		
		ActionContext.getContext().put("articles", articles);
		ActionContext.getContext().put("articlehots", articlehots);
		ActionContext.getContext().put("tags", tags);
		ActionContext.getContext().put("type", "home");
		ActionContext.getContext().put("more", more);
		ActionContext.getContext().put("index", articles.size()+page);
		return "home";
	}
	
	public String duanzi()
	{
		String index = ServletActionContext.getRequest().getParameter("index");
		int page = 0;
		if(index != null)
		{
			page = Integer.parseInt(index);
		}
		
		//最新文章
		List<GArticle>  articles = articleService.findByNew(2, page, 20).getList();
		for(GArticle article : articles)
		{
			article.setTag(tagService.find(article.getTagId()));
		}
		//热点推荐
		List<GArticle>  articlehots = articleService.findByHot(2, 0, 6).getList();
		for(GArticle article : articlehots)
		{
			article.setTag(tagService.find(article.getTagId()));
		}
		//热门标签
		List<GTag> tags = tagService.findByHot(0, 20).getList();
		
		boolean more = (articles.size() == 20);
		
		ActionContext.getContext().put("articles", articles);
		ActionContext.getContext().put("articlehots", articlehots);
		ActionContext.getContext().put("tags", tags);
		ActionContext.getContext().put("type", "duanzi");
		ActionContext.getContext().put("more", more);
		ActionContext.getContext().put("index", articles.size()+page);
		return "duanzi";
	}
	
	public String mm()
	{
		String index = ServletActionContext.getRequest().getParameter("index");
		int page = 0;
		if(index != null)
		{
			page = Integer.parseInt(index);
		}
		
		//最新文章
		List<GArticle>  articles = articleService.findByNew(3, page, 20).getList();
		for(GArticle article : articles)
		{
			article.setTag(tagService.find(article.getTagId()));
		}
		//热点推荐
		List<GArticle>  articlehots = articleService.findByHot(3, 0, 6).getList();
		for(GArticle article : articlehots)
		{
			article.setTag(tagService.find(article.getTagId()));
		}
		//热门标签
		List<GTag> tags = tagService.findByHot(0, 20).getList();
		
		boolean more = (articles.size() == 20);
		
		ActionContext.getContext().put("articles", articles);
		ActionContext.getContext().put("articlehots", articlehots);
		ActionContext.getContext().put("tags", tags);
		ActionContext.getContext().put("type", "mm");
		ActionContext.getContext().put("more", more);
		ActionContext.getContext().put("index", articles.size()+page);
		return "mm";
	}
	
	public String tag()
	{
		String id = ServletActionContext.getRequest().getParameter("id");
		String index = ServletActionContext.getRequest().getParameter("index");
		long tagId = 0;
		if(StringTools.isEmpty(id))
		{
			return home();
		}
		tagId = Long.parseLong(id);
		int page = 0;
		if(index != null)
		{
			page = Integer.parseInt(index);
		}
		
		GTag tag = tagService.find(tagId);
		if(tag != null)
		{
			tag.setShowNum(tag.getShowNum()+1);
			tagService.update(tag);
		}
		
		//最新文章
		List<GArticle>  articles = articleService.findByNew(tagId, page, 20).getList();
		for(GArticle article : articles)
		{
			article.setTag(tagService.find(article.getTagId()));
		}
		//热点推荐
		List<GArticle>  articlehots = articleService.findByHot(tagId, 0, 6).getList();
		for(GArticle article : articlehots)
		{
			article.setTag(tagService.find(article.getTagId()));
		}
		//热门标签
		List<GTag> tags = tagService.findByHot(0, 20).getList();
		
		boolean more = (articles.size() == 20);
		
		ActionContext.getContext().put("articles", articles);
		ActionContext.getContext().put("articlehots", articlehots);
		ActionContext.getContext().put("tags", tags);
		ActionContext.getContext().put("type", "tag");
		ActionContext.getContext().put("more", more);
		ActionContext.getContext().put("index", articles.size()+page);
		ActionContext.getContext().put("tagId", tagId);
		return "tag";
	}
	
	public String show()
	{
		String id = ServletActionContext.getRequest().getParameter("id");
		if(StringTools.isEmpty(id))
			return home();
		long tid = Long.parseLong(id);
		GArticle article = articleService.find(tid);
		if(article == null)
			return home();
		article.setShowNum(article.getShowNum()+1);
		articleService.update(article);
		
		article.setTag(tagService.find(article.getTagId()));
		
		GArticle pre = articleService.findPre(tid);
		GArticle next = articleService.findNext(tid);
		
		//获取ip
		String ip = ServletActionContext.getRequest().getRemoteAddr();
		//评论
		List<GComment> comments = commentService.findByHot(tid, 0, 15).getList();
		for(GComment comment : comments)
		{
			comment.initXip();
			comment.setLove(commentLoveService.find(comment.getId(), ip) != null);
		}
		//是否喜欢
		boolean love = (loveService.find(tid, ip) != null);
		
		ActionContext.getContext().put("article", article);
		ActionContext.getContext().put("pre", pre);
		ActionContext.getContext().put("next", next);
		ActionContext.getContext().put("comments", comments);
		ActionContext.getContext().put("ip", ip);
		ActionContext.getContext().put("love", love);
		ActionContext.getContext().put("more", (comments.size() == 15));
		return "show";
	}
	
	public void love()
	{
		String id = ServletActionContext.getRequest().getParameter("id");
		if(StringTools.isEmpty(id))
		{
			print(false);
		}
		else
		{
			//获取ip
			String ip = ServletActionContext.getRequest().getRemoteAddr();
			
			long tid = Long.parseLong(id);
			GArticle article = articleService.find(tid);
			if(article != null)
			{
				GLove love = loveService.find(tid, ip);
				if(love == null)
				{
					loveService.add(new GLove(tid, ip));
					article.setLoveNum(article.getLoveNum()+1);
					articleService.update(article);
				}
				else
				{
					loveService.delete(love.getId());
					article.setLoveNum(article.getLoveNum()-1);
					articleService.update(article);
				}
				print(true);
			}
			else
			{
				print(false);
			}
		}
	}
	
	public void comment()
	{
		String id = ServletActionContext.getRequest().getParameter("id");
		String content = ServletActionContext.getRequest().getParameter("content");
		if(StringTools.isEmpty(id) || StringTools.isEmpty(content))
		{
			print(false);
		}
		else
		{
			//获取ip
			String ip = ServletActionContext.getRequest().getRemoteAddr();
			
			long tid = Long.parseLong(id);
			GArticle article = articleService.find(tid);
			if(article != null)
			{
				long floor = commentService.findFloor(tid);
				GComment comment = new GComment(tid, ip, content, floor);
				commentService.add(comment);
				
				article.setCommentNum(article.getCommentNum()+1);
				articleService.update(article);
				
				comment.initXip();
				List<GComment> comments = new ArrayList<GComment>();
				comments.add(comment);
				print(JSONArray.fromObject(comments).toString());
			}
			else
			{
				print(false);
			}
		}
	}
	
	public void morecomment()
	{
		String id = ServletActionContext.getRequest().getParameter("id");
		String index = ServletActionContext.getRequest().getParameter("index");
		if(StringTools.isEmpty(id) || StringTools.isEmpty(index))
		{
			print(false);
		}
		else
		{
			int page = 0;
			if(index != null)
			{
				page = Integer.parseInt(index);
			}
			long tid = Long.parseLong(id);
			List<GComment> comments = commentService.findByHot(tid, page, 10).getList();
			//获取ip
			String ip = ServletActionContext.getRequest().getRemoteAddr();
			for(GComment comment : comments)
			{
				comment.initXip();
				comment.setLove(commentLoveService.find(comment.getId(), ip) != null);
			}
			print(JSONArray.fromObject(comments).toString());
		}
		
	}
	
	public void commentlove()
	{
		String id = ServletActionContext.getRequest().getParameter("id");
		if(StringTools.isEmpty(id))
		{
			print(false);
		}
		else
		{
			//获取ip
			String ip = ServletActionContext.getRequest().getRemoteAddr();
			
			long tid = Long.parseLong(id);
			GComment comment = commentService.find(tid);
			if(comment != null)
			{
				GCommentLove commentLove = commentLoveService.find(tid,ip);
				if(commentLove == null)
				{
					commentLoveService.add(new GCommentLove(tid, ip));
					comment.setLoveNum(comment.getLoveNum()+1);
					commentService.update(comment);
				}
				else
				{
					commentLoveService.delete(commentLove.getId());
					comment.setLoveNum(comment.getLoveNum()-1);
					commentService.update(comment);
				}
				print(true);
			}
			else
			{
				print(false);
			}
		}
	}
	
	public String search()
	{
		String index = ServletActionContext.getRequest().getParameter("index");
		String val = ServletActionContext.getRequest().getParameter("val");		
		if(StringTools.isEmpty(val))
		{
			return home();
		}
		int page = 0;
		if(index != null)
		{
			page = Integer.parseInt(index);
		}
		try {
//			val = URLDecoder.decode(val, "utf-8");
			val = new String(val.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if(GTools.sqlValidate(val))
			return home();
		String valdata = val;
		List<String> vals = GTools.searchKeywords(val);
		List<GArticle>  articles = articleService.findSearch(vals, page, 20).getList();
		for(GArticle article : articles)
		{
			article.setTag(tagService.find(article.getTagId()));
		}
		//热点推荐
		List<GArticle>  articlehots = articleService.findByHot(1, 0, 6).getList();
		for(GArticle article : articlehots)
		{
			article.setTag(tagService.find(article.getTagId()));
		}
		//热门标签
		List<GTag> tags = tagService.findByHot(0, 20).getList();
		
		boolean more = (articles.size() == 20);
		
		ActionContext.getContext().put("articles", articles);
		ActionContext.getContext().put("articlehots", articlehots);
		ActionContext.getContext().put("tags", tags);
		ActionContext.getContext().put("vals", valdata);
		ActionContext.getContext().put("type", "search");
		ActionContext.getContext().put("more", more);
		ActionContext.getContext().put("index", articles.size()+page);
		return "search";
	}
	
	public String add() {
		if(ActionContext.getContext().getSession().get("user")==null)
		{
			return "toLogin";
		}
		List<GTag> tags = tagService.findAll(0, 100).getList();
		ActionContext.getContext().put("tags", tags);
		return "add";
	}
	
	public String update() {
		if(ActionContext.getContext().getSession().get("user")==null)
		{
			return "toLogin";
		}
		String id = ServletActionContext.getRequest().getParameter("id");
		if(StringTools.isEmpty(id))
		{
			return list();
		}
		
		GArticle article = articleService.find(Long.parseLong(id));
		article.setTag(tagService.find(article.getTagId()));
		
		List<GTag> tags = tagService.findAll(0, 100).getList();
		ActionContext.getContext().put("tags", tags);
		ActionContext.getContext().put("article", article);
		return "update";
	}
	
	public void addArticle()
	{
		String articles = ServletActionContext.getRequest().getParameter("articles");
		if(StringTools.isEmpty(articles))
		{
			print(false);
			return;
		}
		JSONObject obj = JSONObject.fromObject(articles);
		String type = obj.getString("type");
		String title = obj.getString("title");
		String content = obj.getString("content");
		String summary = obj.getString("summary");
		String tag = obj.getString("tag");
		String keywords = obj.getString("keywords");
		String showNum = obj.getString("showNum");
		String headPath = obj.getString("headPath");
				
		if(!StringTools.isEmpty(title) && !StringTools.isEmpty(type) && !StringTools.isEmpty(headPath))
		{
			String sourcePath = headPath.substring(headPath.indexOf("img"), headPath.length());
			headPath = headPath.substring(headPath.indexOf("img"), headPath.lastIndexOf(".")-1)+"0.jpg";
			String pic_relpath = ServletActionContext.getServletContext().getRealPath(sourcePath);
			String topic_relpath = ServletActionContext.getServletContext().getRealPath(headPath);
			GTools.tozipPic(pic_relpath, topic_relpath, true);
			
			long tagId = 0;
			GTag gtag = tagService.find(tag);
			if(gtag != null)
				tagId = gtag.getId();
			
			GArticle article = new GArticle(Integer.parseInt(type),title,content,summary,tagId,keywords,
					Long.parseLong(showNum),headPath);
			articleService.add(article);
			
			print(article.getId());
		}
	}
	
	public void updateArticle()
	{
		String articles = ServletActionContext.getRequest().getParameter("articles");
		if(StringTools.isEmpty(articles))
		{
			print(false);
			return;
		}
		JSONObject obj = JSONObject.fromObject(articles);
		String id = obj.getString("id");
		String type = obj.getString("type");
		String title = obj.getString("title");
		String content = obj.getString("content");
		String summary = obj.getString("summary");
		String tag = obj.getString("tag");
		String keywords = obj.getString("keywords");
		String showNum = obj.getString("showNum");
		String headPath = obj.getString("headPath");
				
		if(!StringTools.isEmpty(id) && !StringTools.isEmpty(title))
		{
			GArticle article = articleService.find(Long.parseLong(id));
			article.setTitle(title);
			article.setContent(content);
			article.setSummary(summary);
			article.setKeywords(keywords);
			article.setGrelease(false);
			
			if(!StringTools.isEmpty(type))
				article.setType(Integer.parseInt(type));
			if(!StringTools.isEmpty(showNum))
				article.setShowNum(Long.parseLong(showNum));
			
			//判断是否更改头图片
			String sourcePath = headPath.substring(headPath.indexOf("img"), headPath.length());
			headPath = headPath.substring(headPath.indexOf("img"), headPath.lastIndexOf(".")-1)+"0.jpg";
			if(!headPath.equals(article.getHeadPath()))
			{
				String pic_relpath = ServletActionContext.getServletContext().getRealPath(sourcePath);
				String topic_relpath = ServletActionContext.getServletContext().getRealPath(headPath);
				GTools.tozipPic(pic_relpath, topic_relpath, true);
				
				article.setHeadPath(headPath);
			}
			
			long tagId = 0;
			GTag gtag = tagService.find(tag);
			if(gtag != null)
				tagId = gtag.getId();
			article.setTagId(tagId);
			
			articleService.update(article);
			print(true);
		}
	}
	
	public String deleteArticle()
	{
		if(ActionContext.getContext().getSession().get("user")==null)
		{
			return "toLogin";
		}
		
		String id = ServletActionContext.getRequest().getParameter("id");
		if(StringTools.isEmpty(id))
		{
			return list();
		}
		GArticle article = articleService.find(Long.parseLong(id));
		if(article != null)
		{
			List<GComment> comments = commentService.findAll(article.getId(),0,1000000).getList();
			for(GComment comment : comments)
			{
				List<GCommentLove> commentLoves = commentLoveService.findAll(comment.getId(), 0, 1000000).getList();
				for(GCommentLove commentLove : commentLoves)
				{
					commentLoveService.delete(commentLove.getId());
				}
				commentService.delete(comment.getId());
			}
			List<GLove> loves = loveService.findAll(article.getId(),0,1000000).getList();
			for(GLove love : loves)
			{
				loveService.delete(love.getId());
			}
			articleService.delete(article.getId());
		}
		return list();
	}
	
	public String releaseArticle()
	{
		if(ActionContext.getContext().getSession().get("user")==null)
		{
			return "toLogin";
		}
		String id = ServletActionContext.getRequest().getParameter("id");
		if(StringTools.isEmpty(id))
		{
			return list();
		}
		GArticle article = articleService.find(Long.parseLong(id));
		if(article != null)
		{
			article.setGrelease(true);
			articleService.update(article);
		}
		return list();
	}
	
	public void addTag()
	{
		String tagName = ServletActionContext.getRequest().getParameter("tagName");
		if(!StringTools.isEmpty(tagName))
		{
			GTag tag = tagService.find(tagName);
			if(tag == null)
			{
				tagService.add(new GTag(tagName));
				print(true);
			}
			else
			{
				print(false);
			}
		}
		else
		{
			print(false);
		}
	}
	
	public void upload()
	{
		if(pic == null)
		{
			return;
		}
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat formatter2 = new SimpleDateFormat("HH-mm-ss-SSS");
		String dateString = formatter.format(currentTime);
		String dateString2 = formatter2.format(currentTime);
		
		String extension = picFileName.substring(picFileName.lastIndexOf('.'), picFileName.length()).toLowerCase();
		String picPath = "img/tutiao/"+ dateString + "/" + dateString2 + extension;
		String pic_relpath = ServletActionContext.getServletContext().getRealPath(picPath);
		String toPicPath = picPath;
		try {
			//上传图片		
			File file = new File(pic_relpath);
			if (!file.getParentFile().exists())
				file.getParentFile().mkdirs();
			FileUtils.copyFile(pic, file);
			
			if(!".gif".equals(extension))
			{
				toPicPath = "img/tutiao/"+ dateString + "/" + dateString2 + "1.jpg";
				String topic_relpath = ServletActionContext.getServletContext().getRealPath(toPicPath);
				GTools.tozipPic(pic_relpath, topic_relpath, false);
			}
			
			print(toPicPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public File getPic() {
		return pic;
	}
	public void setPic(File pic) {
		this.pic = pic;
	}
	public String getPicFileName() {
		return picFileName;
	}
	public void setPicFileName(String picFileName) {
		this.picFileName = picFileName;
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
