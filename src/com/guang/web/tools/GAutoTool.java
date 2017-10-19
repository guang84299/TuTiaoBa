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

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


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
		}
		return obj;
	}
	
	public static void test(String url)
	{
		Document document = null;
		//获取指定网址的页面内容
		try {
			document = Jsoup.connect(url).timeout(50000).get();
			Elements elements = document.select("div[class=name]");
//			if(elements.size() == 0)
//			{
//				System.out.println("div.show-content null");
//				elements = document.select("div.content");
//			}
				
			System.out.println(elements.get(0).text());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
							String pic_relpath = ServletActionContext.getServletContext().getRealPath(picPath);
							String toPicPath = picPath;
							if(downloadPic(imgsrc,pic_relpath))
							{
								//下载完成压缩
								toPicPath = "img/tutiao/"+ dateString + "/" + dateString2 + "1.jpg";
								String topic_relpath = ServletActionContext.getServletContext().getRealPath(toPicPath);
								GTools.tozipPic(pic_relpath, topic_relpath, false);
								e.attr("src",toPicPath);
								e.attr("data-original-src",toPicPath);
								
								//设置头像
								if(i == 0)
								{
									String headPicPath = "img/tutiao/"+ dateString + "/" + dateString2 + "0.jpg";
									String headpic_relpath = ServletActionContext.getServletContext().getRealPath(headPicPath);
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
