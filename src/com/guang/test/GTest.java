package com.guang.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.hibernate.mapping.Array;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.guang.web.mode.GTuTiao;
import com.guang.web.mode.GTuTiaoUnit;
import com.guang.web.mode.GUser;
import com.guang.web.tools.ApkTools;
import com.guang.web.tools.GTools;
import com.guang.web.tools.StringTools;

public class GTest {

	public static void main(String[] args)
	{
		GTuTiao tuTiao = getPageContent("http://www.toutiao.com/a6415291820209455361/");
		
		System.out.println(parseTuTiao(tuTiao).toString());
	}
	
	public static GTuTiao parseTuTiao(GTuTiao tuTiao)
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat formatter2 = new SimpleDateFormat("HH-mm-ss-SSS");
		
		List<GTuTiaoUnit> units = tuTiao.getUnits();
		for(GTuTiaoUnit unit : units)
		{
			Date currentTime = new Date();
			String dateString = formatter.format(currentTime);
			String dateString2 = formatter2.format(currentTime);
			String picPath = "/Users/guang/Documents/images/tutiao/"+ dateString + "/" + dateString2;
			downloadPic(unit.getPicPath(), picPath);
			unit.setPicPath(picPath);
			unit.setTuTiaoId(2);
		}
		return tuTiao;
	}
	
	public static GTuTiao getPageContent(String strUrl) {
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
		      
		      tuTiao = new GTuTiao(title, author, 0);
		      
		      List<GTuTiaoUnit> units = new ArrayList<GTuTiaoUnit>();
		      //内容
		      elements = document.select(".article-content p");
		      for(int i=0;i<elements.size();i++)
		      {
		    	  Element ele = elements.get(i);
		    	  String text = ele.text();
		    	  Elements eles = ele.select("img");
		    	  if(eles.size()>0)
		    	  {
		    		  String src = eles.get(0).attr("src");
		    		  if(StringTools.isEmpty(text))
		    		  {
		    			  if(i>0)
		    				  text = elements.get(i-1).text();
		    			  else
		    				  continue;  
		    		  }
			    	  
			    	  GTuTiaoUnit unit = new GTuTiaoUnit(0, text, src);
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
	
}
