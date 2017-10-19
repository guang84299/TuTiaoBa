package com.guang.web.tools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import net.sf.json.JSONObject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.guang.web.mode.GArticle;
import com.guang.web.mode.GTag;
import com.guang.web.service.GArticleService;
import com.guang.web.service.GTagService;


public class GAutoTool {

	public static JSONObject autoAdd(String url,String type,String channel)
	{
		JSONObject obj = null;
		if(url != null && type != null && channel != null)
		{
			if(channel.equals("jianshu"))
			{
				obj = autoJianShu(url,type);
			}
			else if(channel.equals("jokeji"))
			{
				obj = autoJokeji(url,type);
			}
			else if(channel.equals("swmt"))
			{
				obj = autoSwmt(url, type);
			}
		}
		return obj;
	}
	
	public static void test(String url)
	{
		Document document = null;
		//获取指定网址的页面内容
		try {
			document = Jsoup.connect(url).timeout(50000).get();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String nowTime = formatter.format(new Date().getTime()-3*24*60*60*1000);
			System.out.println(nowTime);
			Elements elements = document.select("ul.single-list");
			if(elements.size()>0)
			{
				elements = elements.get(0).select("li");
			}
			System.out.println(elements.size());
			for(Element e : elements)
			{
				Elements eltimes = e.select("em");
				if(eltimes.size()>0)
				{
					String dt = eltimes.get(0).text();
					if(dt.contains(nowTime))
					{
						
					}
				}
			}
//			elements = elements.get(0).select("a");
//			for(Element e : elements)
//			{
//				String u = e.attr("href");
//				if(u.contains(nowTime))
//				System.out.println(u);
//			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void update()
	{
		//段子
		List<String> list = getDuanziList();
		for(String url : list)
		{
			url = GTools.encode(url);
			JSONObject obj = autoJokeji(url,2+"");
			autoAddArticle(obj);
		}
		//MM
		list = getMMList();
		for(String url : list)
		{
			url = GTools.encode(url);
			JSONObject obj = autoSwmt(url,3+"");
			autoAddArticle(obj);
		}
	}
	
	public static void autoAddArticle(JSONObject obj)
	{		
		if(obj != null)
		{
			GArticleService articleService = BeanUtils.getBean("GArticleServiceImpl");
			GTagService tagService = BeanUtils.getBean("GTagServiceImpl");
			
			String type = obj.getString("type");
			String title = obj.getString("title");
			String content = obj.getString("content");
			String summary = obj.getString("summary");
			String tag = obj.getString("tag");
			String keywords = obj.getString("keywords");
			String showNum = obj.getString("showNum");
			String author = obj.getString("author");
			String headPath = null;
			if(obj.containsKey("headPath"))
				headPath = obj.getString("headPath");
			
			if(!StringTools.isEmpty(title) && !StringTools.isEmpty(type))
			{
				long tagId = 0;
				GTag gtag = tagService.find(tag);
				if(gtag != null)
					tagId = gtag.getId();
				else
				{
					tagService.add(new GTag(tag));
					gtag = tagService.find(tag);
					tagId = gtag.getId();
				}
				GArticle article = new GArticle(Integer.parseInt(type),title,content,summary,tagId,keywords,
						Long.parseLong(showNum),headPath,author);
				article.setGrelease(true);
				articleService.add(article);
			}
		}
	}
	
	public static List<String> getDuanziList()
	{
		List<String> list = new ArrayList<String>();
		
		Document document = null;
		//获取指定网址的页面内容
		try {
			document = Jsoup.connect("http://www.jokeji.cn/list.htm").timeout(50000).get();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
			String nowTime = formatter.format(new Date().getTime()-24*60*60*1000);
			
			Elements elements = document.select("div.list_title");
			elements = elements.get(0).select("a");
			for(Element e : elements)
			{
				String u = e.attr("href");
				if(u.contains(nowTime))
				{
					u = "http://www.jokeji.cn"+u;
					list.add(u);
				}
			}
			
			
			document = Jsoup.connect("http://gaoxiao.jokeji.cn").timeout(50000).get();
			elements = document.select("div.newpic");
			elements = elements.get(0).select("a");
			for(Element e : elements)
			{
				String u = e.attr("href");
				if(u.contains(nowTime))
				{
					boolean b = false;
					for(String u2 : list)
					{
						if(u.equals(u2))
						{
							b = true;
							break;
						}
					}
					if(!b)
						list.add(u);
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	public static List<String> getMMList()
	{
		List<String> list = new ArrayList<String>();
		
		Document document = null;
		//获取指定网址的页面内容
		try {
			document = Jsoup.connect("http://www.swmt.cc/new.html").timeout(50000).get();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String nowTime = formatter.format(new Date().getTime()-3*24*60*60*1000);
			
			Elements elements = document.select("ul.single-list");
			if(elements.size()>0)
			{
				elements = elements.get(0).select("li");
			}
			for(Element e : elements)
			{
				Elements eltimes = e.select("em");
				if(eltimes.size()>0)
				{
					String dt = eltimes.get(0).text();
					if(dt.contains(nowTime))
					{
						Elements elas = e.select("a");
						if(elas.size()>0)
						{
							String url = "http://www.swmt.cc"+elas.get(0).attr("href");
							list.add(url);
						}
					}
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	public static JSONObject autoSwmt(String url,String type)
	{
		JSONObject obj = null;
		Document document = null;
    	
		try {
			 obj = new JSONObject();
	    	 obj.put("type", type);
	    	//获取指定网址的页面内容
			document = Jsoup.connect(url).timeout(50000).get();
						
			//获取标题
			Elements elements = document.select("title");
			String title = "";
			if(elements != null && elements.size()>0)
			{
				title = elements.get(0).text();
				obj.put("title", title.split("_")[0]);
			}
			
			//获取简介
			obj.put("summary", title.split("_")[0]);
			
			//标签，设置默认
			elements = document.select("a.tags");
			String tag = "";
			if(elements != null && elements.size()>0)
			{
				tag = elements.get(0).text();
				obj.put("tag", tag);
			}
			
			//获取关键词
			obj.put("keywords", title);
			
			//显示次数
			obj.put("showNum", "0");
			
			//作者
			String author = tag;
			obj.put("author", author);
			
			//获取内容
			elements = document.select("div.content");
			if(elements != null && elements.size()>0)
			{
				elements = elements.get(0).select("center");
			}
			if(elements != null && elements.size()>0)
			{
				Element ele = elements.get(0);
				//找到所有img标签
				Elements imgs = ele.select("img");
				if(imgs != null && imgs.size()>0)
				{
					//遍历img下载图片并替换img
					int i = 0;
					for(Element e : imgs)
					{
						e.removeClass("center-block");
				  		e.addClass("center-block");
				  		e.removeClass("img-responsive");
				  		e.addClass("img-responsive");
  		
						String imgsrc = e.attr("src");
						if(imgsrc != null)
						{							
							Date currentTime = new Date();
							SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
							SimpleDateFormat formatter2 = new SimpleDateFormat("HH-mm-ss-SSS");
							String dateString = formatter.format(currentTime);
							String dateString2 = formatter2.format(currentTime);
							
							String extension = imgsrc.substring(imgsrc.lastIndexOf('.'), imgsrc.length()).toLowerCase();
							String picPath = "img/tutiao/"+ dateString + "/" + dateString2 + extension;
							String pic_relpath = SpringTools.getAbsolutePath(picPath);
							String toPicPath = picPath;
							if(downloadPic(imgsrc,pic_relpath))
							{
								//下载完成压缩
								if(!".gif".equals(extension))
								{
									toPicPath = "img/tutiao/"+ dateString + "/" + dateString2 + "1.jpg";
									String topic_relpath =  SpringTools.getAbsolutePath(toPicPath);
									GTools.tozipPic(pic_relpath, topic_relpath, false);
								}
								e.attr("src",toPicPath);
								e.parent().attr("href",toPicPath);
								
								
								//设置头像
								if(i == 0)
								{
									String headPicPath = "img/tutiao/"+ dateString + "/" + dateString2 + "0.jpg";
									String headpic_relpath = SpringTools.getAbsolutePath(headPicPath);
									GTools.tozipPic(pic_relpath, headpic_relpath, true);
									
									obj.put("headPath", headPicPath);
								}
								i++;
							}							
						}
					}					
				}
				
				obj.put("content", ele.toString());
			}			
		} catch (IOException e) {
			e.printStackTrace();
			obj = null;
		}
		
		return obj;
	}
	
	public static JSONObject autoJokeji(String url,String type)
	{
		JSONObject obj = null;
		Document document = null;
    	
		try {
			 obj = new JSONObject();
	    	 obj.put("type", type);
	    	//获取指定网址的页面内容
			document = Jsoup.connect(url).timeout(50000).get();
			
			boolean isPic = false;
			if(url.contains("GrapHtml"))
				isPic = true;
			
			//获取标题
			Elements elements = document.select("title");
			String title = "";
			if(elements != null && elements.size()>0)
			{
				title = elements.get(0).text();
				obj.put("title", title.split("_")[0]);
			}
			
			//获取简介
			elements = document.select("meta[name=description]");
			if(elements != null && elements.size()>0)
			{
				String summary = elements.get(0).attr("content");
				obj.put("summary", summary);
			}
			
			//标签，设置默认
			if(isPic)
				obj.put("tag", "搞笑图片");
			else
				obj.put("tag", title.split("_")[1]+"笑话");
			
			//获取关键词
			elements = document.select("meta[name=keywords]");
			if(elements != null && elements.size()>0)
			{
				String keywords = elements.get(0).attr("content");
				obj.put("keywords", keywords);
			}
			
			//显示次数
			obj.put("showNum", "0");
			
			//作者
			String author = "搞笑图片";
			if(!isPic)
				author = title.split("_")[1]+"笑话";
			obj.put("author", author);
			
			//获取内容
			elements = document.select("span#text110");
			if(elements != null && elements.size()>0)
			{
				Element ele = elements.get(0);
				obj.put("content", ele.toString());
			}
			//搞笑图片
			else
			{
				elements = document.select("div.pic_pview");
				if(elements != null && elements.size()>0)
					elements = elements.get(0).select("ul");
				if(elements != null && elements.size()>0)
				{
					Element ele = elements.get(0);
					//找到所有img标签
					Elements imgs = ele.select("img");
					if(imgs != null && imgs.size()>0)
					{
						//遍历img下载图片并替换img
						int i = 0;
						for(Element e : imgs)
						{
							e.removeClass("center-block");
					  		e.addClass("center-block");
					  		e.removeClass("img-responsive");
					  		e.addClass("img-responsive");
					  		
							String imgsrc = e.attr("src");
							if(imgsrc != null)
							{
								imgsrc = "http://gaoxiao.jokeji.cn"+imgsrc;
								
								Date currentTime = new Date();
								SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
								SimpleDateFormat formatter2 = new SimpleDateFormat("HH-mm-ss-SSS");
								String dateString = formatter.format(currentTime);
								String dateString2 = formatter2.format(currentTime);
								
								String extension = imgsrc.substring(imgsrc.lastIndexOf('.'), imgsrc.length()).toLowerCase();
								String picPath = "img/tutiao/"+ dateString + "/" + dateString2 + extension;
								String pic_relpath = SpringTools.getAbsolutePath(picPath);
								String toPicPath = picPath;
								if(downloadPic(imgsrc,pic_relpath))
								{
									//下载完成压缩
									if(!".gif".equals(extension))
									{
										toPicPath = "img/tutiao/"+ dateString + "/" + dateString2 + "1.jpg";
										String topic_relpath =  SpringTools.getAbsolutePath(toPicPath);
										GTools.tozipPic(pic_relpath, topic_relpath, false);
									}
									e.attr("src",toPicPath);
									
									//设置头像
									if(i == 0)
									{
										String headPicPath = "img/tutiao/"+ dateString + "/" + dateString2 + "0.jpg";
										String headpic_relpath = SpringTools.getAbsolutePath(headPicPath);
										GTools.tozipPic(pic_relpath, headpic_relpath, true);
										
										obj.put("headPath", headPicPath);
									}
									i++;
								}							
							}
						}
						
						
					}
					
					obj.put("content", ele.toString());
				}
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			obj = null;
		}
		
		return obj;
	}
	
	public static JSONObject autoJianShu(String url,String type)
	{
		JSONObject obj = null;
		Document document = null;
		
	     try {
	    	 obj = new JSONObject();
	    	 obj.put("type", type);
	    	 
	    	//获取指定网址的页面内容
			document = Jsoup.connect(url).timeout(50000).get();
			
			//获取标题
			Elements elements = document.select("h1.title");
			String title = "";
			if(elements != null && elements.size()>0)
			{
				title = elements.get(0).text();
				obj.put("title", title);
			}
			
			//获取简介
			elements = document.select("meta[name=description]");
			if(elements != null && elements.size()>0)
			{
				String summary = elements.get(0).attr("content");
				obj.put("summary", summary);
			}
			
			//无法抓取标签，设置默认
			obj.put("tag", "生活");
			
			//获取关键词
			obj.put("keywords", title);
			
			//显示次数
			obj.put("showNum", "0");
			
			//作者
			elements = document.select("div[class=name]");
			if(elements != null && elements.size()>0)
			{
				String author = elements.get(0).text();
				obj.put("author", author);
			}
			
			//获取内容
			elements = document.select("div.show-content");
			if(elements.size() == 0)
			{
				elements = document.select("div.content");
			}
			if(elements != null && elements.size()>0)
			{
				Element ele = elements.get(0);
				//找到所有img标签
				Elements imgs = ele.select("img");
				if(imgs != null && imgs.size()>0)
				{
					//遍历img下载图片并替换img
					int i = 0;
					for(Element e : imgs)
					{
						e.removeClass("center-block");
				  		e.addClass("center-block");
				  		e.removeClass("img-responsive");
				  		e.addClass("img-responsive");
				  		
						String imgsrc = e.attr("src");
						if(imgsrc != null)
						{
							if(imgsrc.startsWith("//"))
								imgsrc = "http:"+imgsrc;
							e.attr("src",imgsrc);
							
							Date currentTime = new Date();
							SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
							SimpleDateFormat formatter2 = new SimpleDateFormat("HH-mm-ss-SSS");
							String dateString = formatter.format(currentTime);
							String dateString2 = formatter2.format(currentTime);
							
							String picPath = "img/tutiao/"+ dateString + "/" + dateString2 + ".jpg";
							String pic_relpath = SpringTools.getAbsolutePath(picPath);
							String toPicPath = picPath;
							if(downloadPic(imgsrc,pic_relpath))
							{
								//下载完成压缩
								toPicPath = "img/tutiao/"+ dateString + "/" + dateString2 + "1.jpg";
								String topic_relpath = SpringTools.getAbsolutePath(toPicPath);
								GTools.tozipPic(pic_relpath, topic_relpath, false);
								e.attr("src",toPicPath);
								e.attr("data-original-src",toPicPath);
								
								//设置头像
								if(i == 0)
								{
									String headPicPath = "img/tutiao/"+ dateString + "/" + dateString2 + "0.jpg";
									String headpic_relpath = SpringTools.getAbsolutePath(headPicPath);
									GTools.tozipPic(pic_relpath, headpic_relpath, true);
									
									obj.put("headPath", headPicPath);
								}
								i++;
							}							
						}
					}
					
					
				}
				
				obj.put("content", ele.toString());
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			obj = null;
		}
		return obj;
	}
	
	/**
	   * 下载文件到本地
	   *
	   * @param urlString
	   *          被下载的文件地址
	   * @param filename
	   *          本地文件名
	   */
	public static boolean downloadPic(String urlString, String filename) {
		InputStream is = null;
		OutputStream os = null;
		boolean result = false;
		try {
			File file = new File(filename);
			if (!file.getParentFile().exists())
				file.getParentFile().mkdirs();
			
			HostnameVerifier hv = new HostnameVerifier() {  
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}  
		    };  
			trustAllHttpsCertificates();
			HttpsURLConnection.setDefaultHostnameVerifier(hv);  
			
			// 构造URL
			URL url = new URL(urlString);
			 // 打开连接
		    URLConnection con = url.openConnection();
		    con.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36");
		    if(urlString.contains("gaoxiao.jokeji.cn"))
		    	con.setRequestProperty("Referer","http://gaoxiao.jokeji.cn/"); 
		    // 输入流
		    is = con.getInputStream();
		    // 1K的数据缓冲
		    byte[] bs = new byte[1024];
		    // 读取到的数据长度
		    int len;
		    // 输出的文件流
		    os = new FileOutputStream(file);
		    // 开始读取
		    while ((len = is.read(bs)) != -1) {
		      os.write(bs, 0, len);
		    }
		    result = true;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			if(os != null)
			   {
				   try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			   }
			   if(is != null)
			   {
				   try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			   }
		}
	    return result;
	}   
	
	private static void trustAllHttpsCertificates() {  
        javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[1];  
        javax.net.ssl.TrustManager tm = new miTM();  
        trustAllCerts[0] = tm;  
        javax.net.ssl.SSLContext sc;
		try {
			sc = javax.net.ssl.SSLContext  
			        .getInstance("SSL");
			sc.init(null, trustAllCerts, null);
			javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(sc  
	                .getSocketFactory());  
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}       
    }  
	
	static class miTM implements javax.net.ssl.TrustManager,javax.net.ssl.X509TrustManager {  
		public java.security.cert.X509Certificate[] getAcceptedIssuers() {
			return null;
		}

		public boolean isServerTrusted(
				java.security.cert.X509Certificate[] certs) {
			return true;
		}

		public boolean isClientTrusted(
				java.security.cert.X509Certificate[] certs) {
			return true;
		}

		public void checkServerTrusted(
				java.security.cert.X509Certificate[] certs, String authType)
				throws java.security.cert.CertificateException {
			return;
		}

		public void checkClientTrusted(
				java.security.cert.X509Certificate[] certs, String authType)
				throws java.security.cert.CertificateException {
			return;
		}
	}
	
	static class TouTiaoElement
	{
		private String con;
		private boolean pic;
		public TouTiaoElement(boolean pic,String con)
		{
			this.pic = pic;
			this.con = con;
		}
		public String getCon() {
			return con;
		}
		public void setCon(String con) {
			this.con = con;
		}
		public boolean isPic() {
			return pic;
		}
		public void setPic(boolean pic) {
			this.pic = pic;
		}
		
	}
}
