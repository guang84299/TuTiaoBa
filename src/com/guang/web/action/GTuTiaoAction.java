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

import com.guang.web.mode.GTuTiaoUnit;
import com.guang.web.mode.GTuTiao;
import com.guang.web.service.GTuTiaoUnitService;
import com.guang.web.service.GTuTiaoService;
import com.guang.web.service.GUserService;
import com.guang.web.tools.GAutoTool;
import com.guang.web.tools.GTools;
import com.guang.web.tools.StringTools;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class GTuTiaoAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	@Resource private GTuTiaoService tuTiaoService;
	@Resource private GTuTiaoUnitService unitService;
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
		
		List<GTuTiao> list = tuTiaoService.findAll(page*100,100).getList();
		
		ActionContext.getContext().put("list", list);
		ActionContext.getContext().put("page", page+1);
		ActionContext.getContext().put("num", num);
		
		return "index";
	}
	
	public String home()
	{
		//轮播
		List<GTuTiao> list_lunbo = tuTiaoService.findByHot(1,0,3).getList();
		//主条
		List<GTuTiao> list_zhu = tuTiaoService.findByHot(1,3,1).getList();
		//美女排行
		List<GTuTiao> list_rank_mm = tuTiaoService.findByHot(1,4,14).getList();
		//副条left
		List<GTuTiao> list_fu_left = tuTiaoService.findByHot(1,18,14).getList();
		//精彩美女推荐
		List<GTuTiao> list_tuijian_mm = tuTiaoService.findByHot(1,32,35).getList();
		//美女图条
		List<GTuTiao> list_tutiao_mm = tuTiaoService.findByNew(1,0,12).getList();
		
		//生活排行
		List<GTuTiao> list_rank_life = tuTiaoService.findByHot(2,0,14).getList();
		//副条right
		List<GTuTiao> list_fu_right = tuTiaoService.findByHot(2,14,14).getList();
		//精彩生活推荐
		List<GTuTiao> list_tuijian_life = tuTiaoService.findByHot(2,28,35).getList();
		//生活图条
		List<GTuTiao> list_tutiao_life = tuTiaoService.findByNew(2,0,12).getList();
		
		ActionContext.getContext().put("lunbos", list_lunbo);
		ActionContext.getContext().put("zhu", list_zhu);
		ActionContext.getContext().put("rank_mm", list_rank_mm);
		ActionContext.getContext().put("fu_left", list_fu_left);
		ActionContext.getContext().put("tuijian_mm", list_tuijian_mm);
		ActionContext.getContext().put("tutiao_mm", list_tutiao_mm);
		ActionContext.getContext().put("rank_life", list_rank_life);
		ActionContext.getContext().put("fu_right", list_fu_right);
		ActionContext.getContext().put("tuijian_life", list_tuijian_life);
		ActionContext.getContext().put("tutiao_life", list_tutiao_life);
		
		return "home";
	}
	
	public String mm()
	{
		String id = ServletActionContext.getRequest().getParameter("id");
		int page = 0;
		if(!StringTools.isEmpty(id))
			page = Integer.parseInt(id);
		//浏览排行/生活排行
		List<GTuTiao> list_rank_mm = tuTiaoService.findByHot(1,0,14).getList();
		//最近更新
		List<GTuTiao> list_new_mm = tuTiaoService.findByNew(1,0,25).getList();
		//生活图条
		List<GTuTiao> list_tutiao_mm = tuTiaoService.findByNew(1,25+page,12).getList();
		
		page += list_tutiao_mm.size();
		if(list_tutiao_mm.size() < 12)
		{
			page = 0;
		}
		ActionContext.getContext().put("rank_mm", list_rank_mm);
		ActionContext.getContext().put("new_mm", list_new_mm);
		ActionContext.getContext().put("tutiao_mm", list_tutiao_mm);
		ActionContext.getContext().put("page", page);
		return "mm";
	}
	
	public String life()
	{
		String id = ServletActionContext.getRequest().getParameter("id");
		int page = 0;
		if(!StringTools.isEmpty(id))
			page = Integer.parseInt(id);
		//浏览排行/排行
		List<GTuTiao> list_rank_life = tuTiaoService.findByHot(2,0,14).getList();
		//最近更新
		List<GTuTiao> list_new_life = tuTiaoService.findByNew(2,0,25).getList();
		//美女图条
		List<GTuTiao> list_tutiao_life = tuTiaoService.findByNew(2,25+page,12).getList();
		
		page += list_tutiao_life.size();
		if(list_tutiao_life.size() < 12)
		{
			page = 0;
		}
		ActionContext.getContext().put("rank_life", list_rank_life);
		ActionContext.getContext().put("new_life", list_new_life);
		ActionContext.getContext().put("tutiao_life", list_tutiao_life);
		ActionContext.getContext().put("page", page);
		return "life";
	}
	
	public String showmm()
	{
		String id = ServletActionContext.getRequest().getParameter("id");
		String page = ServletActionContext.getRequest().getParameter("page");
		if(StringTools.isEmpty(id))
			return home();
		
		int currPage = 1;
		if(!StringTools.isEmpty(page))
			currPage = Integer.parseInt(page);
		
		long tid = Long.parseLong(id);
		GTuTiao tuTiao = tuTiaoService.find(tid);
		if(tuTiao == null)
			return home();
		tuTiao.setShowNum(tuTiao.getShowNum()+1);
		tuTiaoService.update(tuTiao);
		
		List<GTuTiaoUnit> units = unitService.findAll(tuTiao.getId()).getList();
		tuTiao.setUnit(units.get(currPage-1));
		
		//上一个
		GTuTiao pre = tuTiaoService.findPre(1, tid);
		//下一个
		GTuTiao next = tuTiaoService.findNext(1, tid);
		
		//推荐
		int n = (int) tuTiaoService.findNum();
		int r = (int) (Math.random()*100%(n-6));
		List<GTuTiao> list_tuijian = tuTiaoService.findAll(r, 6).getList();
		
		ActionContext.getContext().put("tuTiao", tuTiao);
		ActionContext.getContext().put("pre", pre);
		ActionContext.getContext().put("next", next);
		ActionContext.getContext().put("tuijian", list_tuijian);
		ActionContext.getContext().put("page", currPage);
		ActionContext.getContext().put("count", units.size());
		ActionContext.getContext().put("url", "mm/"+id+"_");
		return "con";
	}
	
	public String showlife()
	{
		String id = ServletActionContext.getRequest().getParameter("id");
		String page = ServletActionContext.getRequest().getParameter("page");
		if(StringTools.isEmpty(id))
			return home();
		
		int currPage = 1;
		if(!StringTools.isEmpty(page))
			currPage = Integer.parseInt(page);
		
		long tid = Long.parseLong(id);
		GTuTiao tuTiao = tuTiaoService.find(tid);
		if(tuTiao == null)
			return home();
		tuTiao.setShowNum(tuTiao.getShowNum()+1);
		tuTiaoService.update(tuTiao);
		
		List<GTuTiaoUnit> units = unitService.findAll(tuTiao.getId()).getList();
		tuTiao.setUnit(units.get(currPage-1));
		
		//上一个
		GTuTiao pre = tuTiaoService.findPre(2, tid);
		//下一个
		GTuTiao next = tuTiaoService.findNext(2, tid);
		
		//推荐
		int n = (int) tuTiaoService.findNum();
		int r = (int) (Math.random()*100%(n-6));
		List<GTuTiao> list_tuijian = tuTiaoService.findAll(r, 6).getList();
		
		ActionContext.getContext().put("tuTiao", tuTiao);
		ActionContext.getContext().put("pre", pre);
		ActionContext.getContext().put("next", next);
		ActionContext.getContext().put("tuijian", list_tuijian);
		ActionContext.getContext().put("page", currPage);
		ActionContext.getContext().put("count", units.size());
		ActionContext.getContext().put("url", "life/"+id+"_");
		return "con";
	}
	
	
	
	public String search()
	{
		String val = ServletActionContext.getRequest().getParameter("val");
		String index = ServletActionContext.getRequest().getParameter("index");
		String vals = val;
		
		int num = 0;
		if(index != null)
		{
			num = Integer.parseInt(index);
		}
		if(StringTools.isEmpty(val))
		{
			return home();
		}
		try {
//			val = URLDecoder.decode(val, "utf-8");
			val = new String(val.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		List<GTuTiao> list = tuTiaoService.findSearch(val, num, 12).getList();
		//浏览排行/排行
		List<GTuTiao> list_rank = tuTiaoService.findByHot(1,0,14).getList();
		//最近更新
		List<GTuTiao> list_new = tuTiaoService.findByNew(2,0,25).getList();
		
		num = num+list.size();
		if(list.size() < 12)
			num = 0;
		ActionContext.getContext().put("list", list);
		ActionContext.getContext().put("rank", list_rank);
		ActionContext.getContext().put("list_new", list_new);
		ActionContext.getContext().put("vals", val);
		ActionContext.getContext().put("page", num);
		return "search";
	}
	
	public String add() {
		return "add";
	}
	
	
	public String update() {
		String id = ServletActionContext.getRequest().getParameter("id");
		if(StringTools.isEmpty(id))
		{
			return list();
		}
		
		GTuTiao tuTiao = tuTiaoService.find(Long.parseLong(id));
		List<GTuTiaoUnit> units = unitService.findAll(tuTiao.getId()).getList();
		ActionContext.getContext().put("tuTiao", tuTiao);
		ActionContext.getContext().put("units", JSONArray.fromObject(units).toString());
		return "update";
	}
	
	
	public void addTuTiao()
	{
		String tutiao = ServletActionContext.getRequest().getParameter("tutiao");
		if(StringTools.isEmpty(tutiao))
		{
			print(false);
			return;
		}
		JSONObject obj = JSONObject.fromObject(tutiao);
		String title = obj.getString("title");
		String type = obj.getString("type");
		String showNum = obj.getString("showNum");
		String headPath = obj.getString("headPath");
		String picNum = obj.getString("picNum");
		String keywords = obj.getString("keywords");
		JSONArray units = obj.getJSONArray("units");
				
		if(!StringTools.isEmpty(title) && !StringTools.isEmpty(type) && !StringTools.isEmpty(headPath) && units != null && units.size() > 0)
		{
			String sourcePath = headPath.substring(headPath.indexOf("img"), headPath.length());
			headPath = headPath.substring(headPath.indexOf("img"), headPath.lastIndexOf(".")-1)+"0.jpg";
			String pic_relpath = ServletActionContext.getServletContext().getRealPath(sourcePath);
			String topic_relpath = ServletActionContext.getServletContext().getRealPath(headPath);
			GTools.tozipPic(pic_relpath, topic_relpath, true);
			
			GTuTiao tuTiao = new GTuTiao(Integer.parseInt(type),title,Long.parseLong(showNum),
					Integer.parseInt(picNum),headPath,keywords);
			tuTiaoService.add(tuTiao);
			
			for(int i=0;i<units.size();i++)
			{
				JSONObject o = units.getJSONObject(i);
				String pic = o.getString("pic");
				pic = pic.substring(pic.indexOf("img"), pic.length());
				unitService.add(new GTuTiaoUnit(tuTiao.getId(), pic));
			}
			
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
			List<GTuTiaoUnit> units = tuTiao.getUnits();
			//下载所有图片
			for(int i=0;i<units.size();i++)
			{
				GTuTiaoUnit unit = units.get(i);
				
				Date currentTime = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat formatter2 = new SimpleDateFormat("HH-mm-ss-SSS");
				String dateString = formatter.format(currentTime);
				String dateString2 = formatter2.format(currentTime);
				
				String downPicPath = "img/tutiao/"+ dateString + "/" + dateString2 + ".jpg";
				downPicPath = ServletActionContext.getServletContext().getRealPath(downPicPath);
				GAutoTool.downloadPic(unit.getPicPath(), downPicPath);
				
				String toPicPath = "img/tutiao/"+ dateString + "/" + dateString2 + "1.jpg";
				String topic_relpath = ServletActionContext.getServletContext().getRealPath(toPicPath);
				GTools.tozipPic(downPicPath, topic_relpath, false);
				
				unit.setPicPath(toPicPath);
				if(i == 0)
				{
					String headPicPath = "img/tutiao/"+ dateString + "/" + dateString2 + "0.jpg";
					String headpic_relpath = ServletActionContext.getServletContext().getRealPath(headPicPath);
					GTools.tozipPic(downPicPath, headpic_relpath, true);
					
					tuTiao.setHeadPath(headPicPath);
				}
			}
			
			tuTiaoService.add(tuTiao);
			
			for(int i=0;i<units.size();i++)
			{
				GTuTiaoUnit unit = units.get(i);
				unit.setTuTiaoId(tuTiao.getId());
				unitService.add(unit);
			}
		}
		
	}
	
	
	public void updateTuTiao()
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
		String type = obj.getString("type");
		String showNum = obj.getString("showNum");
		String headPath = obj.getString("headPath");
		String picNum = obj.getString("picNum");
		JSONArray units = obj.getJSONArray("units");
		String keywords = obj.getString("keywords");
				
		if(!StringTools.isEmpty(id) && !StringTools.isEmpty(title))
		{
			GTuTiao tuTiao = tuTiaoService.find(Long.parseLong(id));
			tuTiao.setTitle(title);
			tuTiao.setKeywords(keywords);
			
			if(!StringTools.isEmpty(type))
				tuTiao.setType(Integer.parseInt(type));
			if(!StringTools.isEmpty(showNum))
				tuTiao.setShowNum(Long.parseLong(showNum));
			if(!StringTools.isEmpty(picNum))
				tuTiao.setPicNum(Integer.parseInt(picNum));
			
			//判断是否更改头图片
			String sourcePath = headPath.substring(headPath.indexOf("img"), headPath.length());
			headPath = headPath.substring(headPath.indexOf("img"), headPath.lastIndexOf(".")-1)+"0.jpg";
			if(!headPath.equals(tuTiao.getHeadPath()))
			{
				String pic_relpath = ServletActionContext.getServletContext().getRealPath(sourcePath);
				String topic_relpath = ServletActionContext.getServletContext().getRealPath(headPath);
				GTools.tozipPic(pic_relpath, topic_relpath, true);
				
				tuTiao.setHeadPath(headPath);
			}
			
			List<GTuTiaoUnit> units_l = unitService.findAll(tuTiao.getId()).getList();
			int num = 0;
			for(int i=0;i<units.size();i++)
			{
				JSONObject o = units.getJSONObject(i);
				String pic = o.getString("pic");
				pic = pic.substring(pic.indexOf("img"), pic.length());
				if(i<units_l.size())
				{
					GTuTiaoUnit unit = units_l.get(i);
					if(!pic.equals(unit.getPicPath()))
					{
						unitService.update(unit);
					}
				}
				else
				{
					unitService.add(new GTuTiaoUnit(tuTiao.getId(), pic));
				}
				num++;
			}
			while(units_l.size() > num)
			{
				unitService.delete(units_l.get(units_l.size()-1).getId());
				units_l.remove(units_l.size()-1);
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
			List<GTuTiaoUnit> units = unitService.findAll(tuTiao.getId()).getList();
			for(GTuTiaoUnit unit : units)
			{
				unitService.delete(unit.getId());
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
