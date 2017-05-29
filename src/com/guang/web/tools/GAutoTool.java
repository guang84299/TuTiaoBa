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
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.guang.web.mode.GTuTiao;
import com.guang.web.mode.GTuTiaoUnit;

public class GAutoTool {

	public static GTuTiao toutiao(String strUrl)
	{
		return getTouTiaoPageContent(strUrl);
	}
	//今日头条 图片专栏
	public static GTuTiao toutiao2(String strUrl)
	{
		return getTouTiaoPageContent2(strUrl);
	}
	//头条新闻 图片专栏
	public static GTuTiao toutiao3(String strUrl)
	{
		return getTouTiaoPageContent3(strUrl);
	}
	
	//头条新闻 mobile专栏
	public static GTuTiao toutiao4(String strUrl)
	{
		return getTouTiaoPageContent4(strUrl);
	}
	
	public static GTuTiao getTouTiaoPageContent4(String strUrl) {
		Document document = null;
		GTuTiao tuTiao = null;
		try {
			//获取指定网址的页面内容
		      document = Jsoup.connect(strUrl).timeout(50000).get();
		      
		      Elements elements = document.select("title");
		      String title = elements.get(0).text();
		      String author = "图条吧";
		      
		      tuTiao = new GTuTiao(GTools.getRandomTid(), title, author, 0);
    		  List<GTuTiaoUnit> units = new ArrayList<GTuTiaoUnit>();
    		  
		      elements = document.select("#content figure,#content p");
		      String text = null;
		      List<String> pics = new ArrayList<String>();
		      List<String> texts = new ArrayList<String>();
		      for(Element ele : elements)
		      {
		    	  String nodeName = ele.nodeName();
		    	  if("figure".equals(nodeName))
		    	  {
		    		  String url = ele.select("a").get(0).attr("href");
		    		  pics.add(url);
		    		  if(text != null)
		    			  texts.add(text);
		    		  text = null;
		    	  }
		    	  else if("p".equals(nodeName))
		    	  {
		    		  if(text == null)
		    			  text = ele.text();
		    		  else
		    			  text = text + "<br><br>" + ele.text();
		    	  }	  
		      }
		      if(text != null)
		      {
		    	  if(pics.size() == texts.size())
		    	  {
		    		  String s = texts.get(texts.size()-1);
			    	  s = s + "<br><br>" + text;
			    	  texts.remove(texts.size()-1);
			    	  texts.add(s);
		    	  }
		    	  else
		    	  {
		    		  texts.add(text);
		    	  }
		      }
		      for(int i=0;i<pics.size();i++)
		      {
		    	  units.add(new GTuTiaoUnit(0, texts.get(i),pics.get(i)));
		      }
			tuTiao.setUnits(units);
		 } catch (IOException e) {
		      e.printStackTrace();
		  }
		
		return tuTiao;
	}
	
	public static GTuTiao getTouTiaoPageContent3(String strUrl) {
		Document document = null;
		GTuTiao tuTiao = null;
		try {
			//获取指定网址的页面内容
		      document = Jsoup.connect(strUrl).timeout(50000).get();
		      
		      Elements elements = document.select("title");
		      String title = elements.get(0).text();
		      String author = "图条吧";
		      
		      tuTiao = new GTuTiao(GTools.getRandomTid(), title, author, 0);
    		  List<GTuTiaoUnit> units = new ArrayList<GTuTiaoUnit>();
    		  
		      elements = document.select("#content figure");
		      for(Element ele : elements)
		      {
		    	  String text = ele.select("figcaption").get(0).text();
		    	  String url = ele.select("a").get(0).attr("href");
		    		  
		    	  units.add(new GTuTiaoUnit(0, text,url));
		      }
			tuTiao.setUnits(units);
		 } catch (IOException e) {
		      e.printStackTrace();
		  }
		
		return tuTiao;
	}
	
	public static GTuTiao getTouTiaoPageContent2(String strUrl) {
		Document document = null;
		GTuTiao tuTiao = null;
		try {
			//获取指定网址的页面内容
		      document = Jsoup.connect(strUrl).timeout(50000).get();
		      
		      Elements elements = document.select("script");
		      String author = "图条吧";
		      for(Element ele : elements)
		      {
		    	  String html = ele.html();
		    	  if(html != null && html.contains("mediaInfo ="))
		    	  {
		    		  String mediaInfo = html.split("mediaInfo =")[1].trim();
		    		  mediaInfo = mediaInfo.substring(0, mediaInfo.indexOf("};")) + "}";
		    		  JSONObject obj = JSONObject.fromObject(mediaInfo);
		    		  author = obj.getString("name");
		    	  }
		    	  if(html != null && html.contains("gallery ="))
		    	  {
		    		  html = html.split("gallery =")[1].trim();
		    		  html = html.substring(0, html.indexOf("};")) + "}";
		    		  
		    		  JSONObject obj = JSONObject.fromObject(html);
		    		  
		    		  //获取图片链接
		    		  List<TouTiaoElement> list_url = new ArrayList<GAutoTool.TouTiaoElement>();
		    		  JSONArray arr_url = obj.getJSONArray("sub_images");
		    		 
		    		  for(int i=0;i<arr_url.size();i++)
		    		  {
		    			  String url = arr_url.getJSONObject(i).getJSONArray("url_list").getJSONObject(0).getString("url");
		    			  list_url.add(new TouTiaoElement(true, url));
		    		  }
		    		  //获取图片描述
		    		  List<TouTiaoElement> list_desc = new ArrayList<GAutoTool.TouTiaoElement>();
		    		  JSONArray arr_desc = obj.getJSONArray("sub_abstracts");
		    		 
		    		  for(int i=0;i<arr_desc.size();i++)
		    		  {
		    			  String desc = arr_desc.get(i).toString();
		    			  list_desc.add(new TouTiaoElement(false, desc));
		    		  }
		    		  //获取标题
		    		  String title = obj.getJSONArray("sub_titles").get(0).toString();
		    		  
		    		  tuTiao = new GTuTiao(GTools.getRandomTid(),title, author, 0);
		    		  List<GTuTiaoUnit> units = new ArrayList<GTuTiaoUnit>();
		    		  for(int i=0;i<list_url.size();i++)
		    		  {
		    			  units.add(new GTuTiaoUnit(0, list_desc.get(i).getCon(), list_url.get(i).getCon()));
		    		  }
		    		  tuTiao.setUnits(units);
		    		  break;
		    	  }
		      }

		 } catch (IOException e) {
		      e.printStackTrace();
		  }
		
		return tuTiao;
	}
	
	public static GTuTiao getTouTiaoPageContent(String strUrl) {
		Document document = null;
		GTuTiao tuTiao = null;
		try {
			//获取指定网址的页面内容
		      document = Jsoup.connect(strUrl).timeout(50000).get();
		      
		      Elements elements = document.select(".article-title");
		    //得到结点的第一个对象
		      Element element = elements.get(0);
		    //获取想要的属性值
//		      String href = element.attr("href");
		      String title = element.text();
		      String author = document.select(".src").get(0).text();
		      
		      tuTiao = new GTuTiao(GTools.getRandomTid(),title, author, 0);
		      
		      List<GTuTiaoUnit> units = new ArrayList<GTuTiaoUnit>();
		      //内容
		      elements = document.select(".article-content p");
		      //1：搞笑类-> 说明和图片在一个p标签内 
		      //2：类似搞笑类，比较混乱，说明和图片可能不在一个p标签，但说明总是在前面
		      //3：图文类，说明和图片不在一个p标签，但说明总是在图片后面
		      
		      List<TouTiaoElement> list = new ArrayList<GAutoTool.TouTiaoElement>();
		      
		      for(int i=0;i<elements.size();i++)
		      {
		    	  Element ele = elements.get(i);
		    	  String text = ele.text();
		    	  Elements eles = ele.select("img");
		    	  if(!StringTools.isEmpty(text))
		    		  list.add(new TouTiaoElement(false, text));
		    	  if(eles.size()>0)
		    		  list.add(new TouTiaoElement(true, eles.get(0).attr("src")));
		      }
		      
		      while(list.size() > 0)
		      {
		    	  TouTiaoElement tele = list.get(0);
		    	  String text = "";
		    	  
		    	  list.remove(0);
		    	  //找到下面所有文字
	    		  while(list.size() > 0)
	    		  {
	    			  TouTiaoElement tele2 = list.get(0);
	    			  if(tele2.isPic())
	    				  break;
	    			  list.remove(0);
	    			  
	    			  text += "<br><br>"+tele2.getCon();
	    		  }
	    		  
		    	  if(tele.isPic())
		    	  {
		    		  GTuTiaoUnit unit = new GTuTiaoUnit(0, text, tele.getCon());
			    	  units.add(unit);
		    	  }
		    	  else
		    	  {
		    		  TouTiaoElement tele3 = list.get(0);
		    		  list.remove(0);
		    		  GTuTiaoUnit unit = new GTuTiaoUnit(0, tele.getCon()+text, tele3.getCon());
			    	  units.add(unit);
		    	  }
		      }
		      tuTiao.setUnits(units);
		      

		 } catch (IOException e) {
		      e.printStackTrace();
		  }
		
		return tuTiao;
	}
	
	/**
	   * 下载文件到本地
	   *
	   * @param urlString
	   *          被下载的文件地址
	   * @param filename
	   *          本地文件名
	   */
	public static void downloadPic(String urlString, String filename) {
		InputStream is = null;
		OutputStream os = null;
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
