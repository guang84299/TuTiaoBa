package com.guang.web.action;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.guang.web.mode.GComment;
import com.guang.web.mode.GTuTiao;
import com.guang.web.mode.GTuTiaoUnit;
import com.guang.web.service.GCommentService;
import com.guang.web.service.GTuTiaoService;
import com.guang.web.service.GTuTiaoUnitService;
import com.guang.web.service.GUserService;
import com.guang.web.tools.GAutoTool;
import com.guang.web.tools.GTools;
import com.guang.web.tools.StringTools;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class GTuTiaoAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	@Resource private GTuTiaoService tuTiaoService;
	@Resource private GTuTiaoUnitService tiaoUnitService;
	@Resource private GCommentService commentService;
	@Resource private  GUserService userService;
	
	private File pic;
	private String picFileName;
	
	
	public String list() {
		String index = ServletActionContext.getRequest().getParameter("index");
		int page = 0;
		if(index != null)
		{
			page = Integer.parseInt(index)-1;
		}
		int num = (int) tuTiaoService.findNum();
		if(num%100 != 0)
			num = num/100 + 1;
		else
			num = num / 100;
		
		List<GTuTiao> list = tuTiaoService.findAll(page*100).getList();
		for(GTuTiao tuTiao : list)
		{
			tuTiao.setCommentNum(commentService.findNum(tuTiao.getId()));
		}
		
		ActionContext.getContext().put("list", list);
		ActionContext.getContext().put("page", page+1);
		ActionContext.getContext().put("num", num);
		
		return "index";
	}
	
	public String home()
	{
		List<GTuTiao> list = tuTiaoService.findByNew(0,24).getList();
		for(GTuTiao tuTiao : list)
		{
			tuTiao.setUnits(tiaoUnitService.findAll(tuTiao.getId()).getList());
			tuTiao.setContent("");
		}
		ActionContext.getContext().put("tuTiaos", list);
		ActionContext.getContext().put("type", "1");
		return "home";
	}
	
	public String hot()
	{
		List<GTuTiao> list = tuTiaoService.findByHot(0,24).getList();
		for(GTuTiao tuTiao : list)
		{
			tuTiao.setUnits(tiaoUnitService.findAll(tuTiao.getId()).getList());
			tuTiao.setContent("");
		}
		ActionContext.getContext().put("tuTiaos", list);
		ActionContext.getContext().put("type", "2");
		return "home";
	}
	
	public void getTuTiaos()
	{
		String index = ServletActionContext.getRequest().getParameter("index");
		String type = ServletActionContext.getRequest().getParameter("type");
		int num = 0;
		if(index != null)
		{
			num = Integer.parseInt(index);
		}
		List<GTuTiao> list = null;
		if("1".equals(type))
		{
			list = tuTiaoService.findByNew(num,12).getList();
		}
		else
		{
			list = tuTiaoService.findByHot(num,12).getList();
		}
		for(GTuTiao tuTiao : list)
		{
			tuTiao.setUnits(tiaoUnitService.findAll(tuTiao.getId()).getList());
			tuTiao.setContent("");
		}
		print(JSONArray.fromObject(list).toString());
	}
	
	public String findTuTiao()
	{
		String tid = ServletActionContext.getRequest().getParameter("tid");
		if(StringTools.isEmpty(tid))
			return "error";
		ActionContext.getContext().put("tid", tid);
		GTuTiao tuTiao = tuTiaoService.findByTid(tid);
		if(tuTiao != null)
		{
			tuTiao.setShowNum(tuTiao.getShowNum()+1);
			tuTiaoService.update(tuTiao);
			tuTiao.setUnits(tiaoUnitService.findAll(tuTiao.getId()).getList());
			tuTiao.setContent("");
			List<GComment> comments = commentService.findBySuport(tuTiao.getId(), 0, 5).getList();
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
			tuTiao.setComments(comments);
			tuTiao.setCommentNum(commentService.findNum(tuTiao.getId()));
			
			// 带广告
			List<GTuTiao> hots = tuTiaoService.findByHot(0,5).getList();
			for(GTuTiao tuTiao2 : hots)
			{
				tuTiao2.setUnits(tiaoUnitService.findAll(tuTiao2.getId()).getList());
				tuTiao.setContent("");
			}
			//
			List<GTuTiao> news = tuTiaoService.findByNew(0,5).getList();
			for(GTuTiao tuTiao3 : news)
			{
				tuTiao3.setUnits(tiaoUnitService.findAll(tuTiao3.getId()).getList());
				tuTiao.setContent("");
			}
			//推荐 带广告
			List<GTuTiao> tuijians = tuTiaoService.findByHot(0,6).getList();
			for(GTuTiao tuTiao4 : tuijians)
			{
				tuTiao4.setUnits(tiaoUnitService.findAll(tuTiao4.getId()).getList());
				tuTiao.setContent("");
			}
			
			ActionContext.getContext().put("tuTiao", tuTiao);
			ActionContext.getContext().put("hots", hots);
			ActionContext.getContext().put("news", news);
			ActionContext.getContext().put("tuijians", tuijians);
		}
		else
		{
			return "error";
		}
		return "tutiao";
	}
	
	public String preTuTiao()
	{
		String tid = ServletActionContext.getRequest().getParameter("tid");
		if(StringTools.isEmpty(tid))
			return "error";
		ActionContext.getContext().put("tid", tid);
		GTuTiao tuTiao = tuTiaoService.findByTid(tid);
		if(tuTiao != null)
		{
			ActionContext.getContext().put("tuTiao", tuTiao);
		}
		else
		{
			return "error";
		}
		return "pretutiao";
	}
	
	public void getTuTiaoShow()
	{
		String tid = ServletActionContext.getRequest().getParameter("tid");
		GTuTiao tuTiao = tuTiaoService.findByTid(tid);
		if(tuTiao != null)
		{
			tuTiao.setShowNum(tuTiao.getShowNum()+1);
			tuTiaoService.update(tuTiao);
			tuTiao.setUnits(tiaoUnitService.findAll(tuTiao.getId()).getList());
			tuTiao.setContent("");
			List<GComment> comments = commentService.findBySuport(tuTiao.getId(), 0, 10).getList();
			for(GComment comment : comments)
			{
				comment.setUserName(userService.find(comment.getUserId()).getName());
			}
			tuTiao.setComments(comments);
			tuTiao.setCommentNum(commentService.findNum(tuTiao.getId()));
			print(JSONObject.fromObject(tuTiao).toString());
		}
		else
		{
			print("");
		}
	}
	
	//获取展示页面的推荐
	public void getShowTuiJian()
	{
		List<GTuTiao> list = tuTiaoService.findByHot(0,4).getList();
		for(GTuTiao tuTiao : list)
		{
			tuTiao.setUnits(tiaoUnitService.findAll(tuTiao.getId()).getList());
			tuTiao.setContent("");
		}
		print(JSONArray.fromObject(list).toString());
	}
	
	//获取展示页面相关的
	public void getShowXiangGuan()
	{
		List<GTuTiao> list = tuTiaoService.findByHot(0,6).getList();
		for(GTuTiao tuTiao : list)
		{
			tuTiao.setUnits(tiaoUnitService.findAll(tuTiao.getId()).getList());
			tuTiao.setContent("");
		}
		print(JSONArray.fromObject(list).toString());
	}
	
	public void getSearch()
	{
		String val = ServletActionContext.getRequest().getParameter("val");
		String index = ServletActionContext.getRequest().getParameter("index");
		int num = 0;
		if(index != null)
		{
			num = Integer.parseInt(index);
		}
		if(StringTools.isEmpty(val))
		{
			return;
		}
		try {
			val = URLDecoder.decode(val, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
			
		List<GTuTiao> list = tuTiaoService.findSearch(val, num, 24).getList();
		for(GTuTiao tuTiao : list)
		{
			tuTiao.setUnits(tiaoUnitService.findAll(tuTiao.getId()).getList());
			tuTiao.setContent("");
		}
		print(JSONArray.fromObject(list).toString());
	}
	
	public String add() {
		return "add";
	}
	public String add2() {
		return "add2";
	}
	
	public String update() {
		String id = ServletActionContext.getRequest().getParameter("id");
		if(StringTools.isEmpty(id))
		{
			return list();
		}
		
		GTuTiao tuTiao = tuTiaoService.find(Long.parseLong(id));
		tuTiao.setUnits(tiaoUnitService.findAll(tuTiao.getId()).getList());
		tuTiao.setContent("");
		ActionContext.getContext().put("tuTiao", tuTiao);
		return "update";
	}
	
	public String update2() {
		String id = ServletActionContext.getRequest().getParameter("id");
		if(StringTools.isEmpty(id))
		{
			return list();
		}
		
		GTuTiao tuTiao = tuTiaoService.find(Long.parseLong(id));
		ActionContext.getContext().put("tuTiao", tuTiao);
		return "update2";
	}
	
	public void addTuTiao()
	{
		String tutiao = ServletActionContext.getRequest().getParameter("tutiao");
		if(StringTools.isEmpty(tutiao))
		{
			return;
		}
		JSONObject obj = JSONObject.fromObject(tutiao);
		String title = obj.getString("title");
		String author = obj.getString("author");
		
		JSONArray junits = obj.getJSONArray("units");
		if(!StringTools.isEmpty(title) && junits != null && junits.size() > 0)
		{
			GTuTiao tuTiao = new GTuTiao(GTools.getRandomTid(), title, author, 0l);
			//生成头图片
			JSONObject ob = junits.getJSONObject(0);
			String opicPath = ob.getString("picPath");
			String hp =  opicPath.substring(0,opicPath.lastIndexOf('.'));
			String toPicPath = hp + "0.jpg";
			String topic_relpath = ServletActionContext.getServletContext().getRealPath(toPicPath);
			String pic_relpath = ServletActionContext.getServletContext().getRealPath(opicPath);
			String waterPicPath = ServletActionContext.getServletContext().getRealPath("images/water.png");
			GTools.tozipPic(pic_relpath, topic_relpath, waterPicPath, true);
			
			tuTiao.setHeadPath(toPicPath);
			tuTiaoService.add(tuTiao);
			for(int i=0;i<junits.size();i++)
			{
				JSONObject o = junits.getJSONObject(i);
				String tdescribe = o.getString("tdescribe");
				String picPath = o.getString("picPath");
				
				GTuTiaoUnit unit = new GTuTiaoUnit(tuTiao.getId(), tdescribe, picPath);
				tiaoUnitService.add(unit);
			}
		}
	}
	
	public void addTuTiao2()
	{
		String tutiao = ServletActionContext.getRequest().getParameter("tutiao");
		if(StringTools.isEmpty(tutiao))
		{
			print(false);
			return;
		}
		JSONObject obj = JSONObject.fromObject(tutiao);
		String title = obj.getString("title");
		String author = obj.getString("author");
		String showNum = obj.getString("showNum");
		String description = obj.getString("description");
		String content = obj.getString("content");
		String headPath = obj.getString("headPath");
		String picNum = obj.getString("picNum");
		boolean showed = obj.getBoolean("showed");
				
		if(!StringTools.isEmpty(title))
		{
			GTuTiao tuTiao = new GTuTiao(GTools.getRandomTid(),title,author,0);
			tuTiao.setDescription(description);
			tuTiao.setContent(content);
			tuTiao.setChecked(false);
			tuTiao.setShowed(showed);
			if(!StringTools.isEmpty(showNum))
				tuTiao.setShowNum(Long.parseLong(showNum));
			if(!StringTools.isEmpty(picNum))
				tuTiao.setPicNum(Integer.parseInt(picNum));
			//判断是否更改头图片
			String sourcePath = headPath.substring(headPath.indexOf("images"), headPath.length());
			headPath = headPath.substring(headPath.indexOf("images"), headPath.lastIndexOf(".")-1)+"0.jpg";
			if(!headPath.equals(tuTiao.getHeadPath()))
			{
				String pic_relpath = ServletActionContext.getServletContext().getRealPath(sourcePath);
				String topic_relpath = ServletActionContext.getServletContext().getRealPath(headPath);
				String waterPicPath = ServletActionContext.getServletContext().getRealPath("images/water.png");
				GTools.tozipPic(pic_relpath, topic_relpath, waterPicPath, true);
				
				tuTiao.setHeadPath(headPath);
			}
			
			tuTiaoService.add(tuTiao);
			print(tuTiao.getId());
		}
	}
	
	public void autoAddTuTiao()
	{
		String url = ServletActionContext.getRequest().getParameter("url");
		String type = ServletActionContext.getRequest().getParameter("type");
		if(StringTools.isEmpty(url) || StringTools.isEmpty(type))
		{
			return;
		}
		GTuTiao tuTiao = null;
		if("1".equals(type))
		{
			tuTiao = GAutoTool.getTouTiaoPageContent(url);
		}
		else if("2".equals(type))
		{
			tuTiao = GAutoTool.getTouTiaoPageContent2(url);
		}
		else if("3".equals(type))
		{
			tuTiao = GAutoTool.getTouTiaoPageContent3(url);
		}
		else if("4".equals(type))
		{
			tuTiao = GAutoTool.getTouTiaoPageContent4(url);
		}
		
		if(tuTiao != null && tuTiao.getUnits().size() > 0)
		{
			//生成头图片
			tuTiaoService.add(tuTiao);
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat formatter2 = new SimpleDateFormat("HH-mm-ss-SSS");
			
			List<GTuTiaoUnit> units = tuTiao.getUnits();
			for(GTuTiaoUnit unit : units)
			{
				Date currentTime = new Date();
				String dateString = formatter.format(currentTime);
				String dateString2 = formatter2.format(currentTime);
				
				String extension = unit.getPicPath().substring(unit.getPicPath().lastIndexOf('.'), unit.getPicPath().length()).toLowerCase();
				String picPath = "images/tutiao/"+ dateString + "/" + dateString2 + ".jpg";
				if(".gif".equals(extension))
					picPath = "images/tutiao/"+ dateString + "/" + dateString2 + ".gif";
				String toPicPath = picPath;
				String pic_relpath = ServletActionContext.getServletContext().getRealPath(picPath);
				GAutoTool.downloadPic(unit.getPicPath(), pic_relpath);
				
				if(!".gif".equals(extension))
				{
					toPicPath = "images/tutiao/"+ dateString + "/" + dateString2 + "1.jpg";
					String topic_relpath = ServletActionContext.getServletContext().getRealPath(toPicPath);
					String waterPicPath = ServletActionContext.getServletContext().getRealPath("images/water.png");
					GTools.tozipPic(pic_relpath, topic_relpath, waterPicPath, false);
				}
				
				if(tuTiao.getHeadPath() == null)
				{
					String headPicPath = "images/tutiao/"+ dateString + "/" + dateString2 + "0.jpg";
					String topic_relpath = ServletActionContext.getServletContext().getRealPath(headPicPath);
					String waterPicPath = ServletActionContext.getServletContext().getRealPath("images/water.png");
					GTools.tozipPic(pic_relpath, topic_relpath, waterPicPath, true);
					
					tuTiao.setHeadPath(headPicPath);
					tuTiaoService.update(tuTiao);
				}
				
				unit.setPicPath(toPicPath);
				unit.setTuTiaoId(tuTiao.getId());
				tiaoUnitService.add(unit);
			}
		}
		
	}
	
	public void updateTuTiao()
	{
		String tutiao = ServletActionContext.getRequest().getParameter("tutiao");
		if(StringTools.isEmpty(tutiao))
		{
			return;
		}
		JSONObject obj = JSONObject.fromObject(tutiao);
		String title = obj.getString("title");
		String tid = obj.getString("id");
		String author = obj.getString("author");
		String showNum = obj.getString("showNum");
		
		JSONArray junits = obj.getJSONArray("units");
		
		if(!StringTools.isEmpty(tid) && !StringTools.isEmpty(title) && junits != null)
		{
			GTuTiao tuTiao = tuTiaoService.find(Long.parseLong(tid));
			tuTiao.setTitle(title);
			tuTiao.setAuthor(author);
			if(!StringTools.isEmpty(showNum))
				tuTiao.setShowNum(Long.parseLong(showNum));
			List<GTuTiaoUnit> units = tiaoUnitService.findAll(tuTiao.getId()).getList();
			if(junits.size() > 0)
			{
				for(int i=0;i<junits.size();i++)
				{
					JSONObject o = junits.getJSONObject(i);
					String picPath = o.getString("picPath");
					String tdescribe = o.getString("tdescribe");
					String id = o.getString("id");
					
					if(!StringTools.isEmpty(id) && (long)(Long.parseLong(id)) > 0)
					{
						for(GTuTiaoUnit unit : units)
						{
							if((long)(Long.parseLong(id)) == unit.getId())
							{
								unit.setPicPath(picPath);
								unit.setTdescribe(tdescribe);
								tiaoUnitService.update(unit);
								units.remove(unit);
								break;
							}
						}
					}
					else
					{
						GTuTiaoUnit unit = new GTuTiaoUnit(tuTiao.getId(), tdescribe, picPath);
						tiaoUnitService.add(unit);
					}
				}
				for(GTuTiaoUnit unit : units)
				{
					deletePic(unit.getPicPath());
					tiaoUnitService.delete(unit.getId());
				}
				tuTiaoService.update(tuTiao);				
			}
			else
			{
				for(GTuTiaoUnit unit : units)
				{
					deletePic(unit.getPicPath());
					tiaoUnitService.delete(unit.getId());
				}
				tuTiaoService.delete(Long.parseLong(tid));
			}
		}
	}
	
	public void updateTuTiao2()
	{
		String tutiao = ServletActionContext.getRequest().getParameter("tutiao");
		if(StringTools.isEmpty(tutiao))
		{
			print(false);
			return;
		}
		JSONObject obj = JSONObject.fromObject(tutiao);
		String title = obj.getString("title");
		String id = obj.getString("id");
		String author = obj.getString("author");
		String showNum = obj.getString("showNum");
		String description = obj.getString("description");
		String content = obj.getString("content");
		String headPath = obj.getString("headPath");
		String picNum = obj.getString("picNum");
		boolean showed = obj.getBoolean("showed");
				
		if(!StringTools.isEmpty(id) && !StringTools.isEmpty(title))
		{
			GTuTiao tuTiao = tuTiaoService.find(Long.parseLong(id));
			tuTiao.setTitle(title);
			tuTiao.setAuthor(author);
			tuTiao.setDescription(description);
			tuTiao.setContent(content);
			tuTiao.setChecked(false);
			tuTiao.setShowed(showed);
			if(!StringTools.isEmpty(showNum))
				tuTiao.setShowNum(Long.parseLong(showNum));
			if(!StringTools.isEmpty(picNum))
				tuTiao.setPicNum(Integer.parseInt(picNum));
			
			//判断是否更改头图片
			String sourcePath = headPath.substring(headPath.indexOf("images"), headPath.length());
			headPath = headPath.substring(headPath.indexOf("images"), headPath.lastIndexOf(".")-1)+"0.jpg";
			if(!headPath.equals(tuTiao.getHeadPath()))
			{
				String pic_relpath = ServletActionContext.getServletContext().getRealPath(sourcePath);
				String topic_relpath = ServletActionContext.getServletContext().getRealPath(headPath);
				String waterPicPath = ServletActionContext.getServletContext().getRealPath("images/water.png");
				GTools.tozipPic(pic_relpath, topic_relpath, waterPicPath, true);
				
				tuTiao.setHeadPath(headPath);
			}
			
			tuTiaoService.update(tuTiao);
			print(true);
		}
	}
	
	public String deleteTuTiao()
	{
		String id = ServletActionContext.getRequest().getParameter("id");
		if(StringTools.isEmpty(id))
		{
			return list();
		}
		GTuTiao tuTiao = tuTiaoService.find(Long.parseLong(id));
		if(tuTiao != null)
		{
			List<GTuTiaoUnit> units = tiaoUnitService.findAll(tuTiao.getId()).getList();
			for(GTuTiaoUnit unit : units)
			{
				deletePic(unit.getPicPath());
				tiaoUnitService.delete(unit.getId());
			}
			List<GComment> comments = commentService.findAll(tuTiao.getId()).getList();
			for(GComment comment : comments)
			{
				commentService.delete(comment.getId());
			}
			tuTiaoService.delete(tuTiao.getId());
		}
		return list();
	}

	public void deleteTuTiaoPic()
	{
		String imgUrl = ServletActionContext.getRequest().getParameter("imgUrl");
		if(StringTools.isEmpty(imgUrl))
		{
			return;
		}
		deletePic(imgUrl);
	}
	
	public void deletePic(String url)
	{
		String pic_relpath = ServletActionContext.getServletContext().getRealPath(url);
		File file = new File(pic_relpath);
		if(file.exists())
			file.delete();
	}
	
	public void upload()
	{
		String title = ServletActionContext.getRequest().getParameter("title");
		String fileToDel = ServletActionContext.getRequest().getParameter("fileToDel");
		if(StringTools.isEmpty(title) || pic == null)
		{
			return;
		}
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat formatter2 = new SimpleDateFormat("HH-mm-ss-SSS");
		String dateString = formatter.format(currentTime);
		String dateString2 = formatter2.format(currentTime);
		
		String extension = picFileName.substring(picFileName.lastIndexOf('.'), picFileName.length()).toLowerCase();
		String picPath = "images/tutiao/"+ dateString + "/" + dateString2 + extension;
		String pic_relpath = ServletActionContext.getServletContext().getRealPath(picPath);
		String toPicPath = picPath;
		try {
			//上传apk		
			File file = new File(pic_relpath);
			if (!file.getParentFile().exists())
				file.getParentFile().mkdirs();
			FileUtils.copyFile(pic, file);
			
			if(!".gif".equals(extension))
			{
				toPicPath = "images/tutiao/"+ dateString + "/" + dateString2 + "1.jpg";
				String topic_relpath = ServletActionContext.getServletContext().getRealPath(toPicPath);
				String waterPicPath = ServletActionContext.getServletContext().getRealPath("images/water.png");
				GTools.tozipPic(pic_relpath, topic_relpath, waterPicPath, false);
			}
			
			if(!StringTools.isEmpty(fileToDel))
			{
				file = new File(ServletActionContext.getServletContext().getRealPath(fileToDel));
				if(file.exists())
					file.delete();
			}
			
			print(toPicPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void uppic()
	{
		List<GTuTiao> list = tuTiaoService.findAll().getList();
		for(GTuTiao tuTiao : list)
		{
			if(tuTiao.getHeadPath() == null)
			{
				List<GTuTiaoUnit> units = tiaoUnitService.findAll(tuTiao.getId()).getList();
				for(GTuTiaoUnit unit : units)
				{
					String picPath = unit.getPicPath();
					String extension = picPath.substring(picPath.lastIndexOf('.'), picPath.length()).toLowerCase();
					String toPicPath = picPath;
					String pic_relpath = ServletActionContext.getServletContext().getRealPath(picPath);
					
					if(!".gif".equals(extension) && !".t".equals(extension))
					{
						toPicPath = picPath.substring(0,picPath.lastIndexOf('.')) + "1.jpg";
						String topic_relpath = ServletActionContext.getServletContext().getRealPath(toPicPath);
						String waterPicPath = ServletActionContext.getServletContext().getRealPath("images/water.png");
						GTools.tozipPic(pic_relpath, topic_relpath, waterPicPath, false);
						
						unit.setPicPath(toPicPath);
						tiaoUnitService.update(unit);
					}
					
					if(tuTiao.getHeadPath() == null)
					{
						String headPicPath = picPath.substring(0,picPath.lastIndexOf('.')) + "0.jpg";
						String topic_relpath = ServletActionContext.getServletContext().getRealPath(headPicPath);
						String waterPicPath = ServletActionContext.getServletContext().getRealPath("images/water.png");
						GTools.tozipPic(pic_relpath, topic_relpath, waterPicPath, true);
						
						tuTiao.setHeadPath(headPicPath);
						tuTiaoService.update(tuTiao);
					}
				}
			}
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
