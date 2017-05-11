package com.guang.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
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
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.mail.MessagingException;

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
import com.guang.web.tools.GAutoTool;
import com.guang.web.tools.GTools;
import com.guang.web.tools.StringTools;

public class GTest {

	public static void main(String[] args)
	{
//		GTuTiao tuTiao = GAutoTool.toutiao3("https://mini.eastday.com/pictures/20170510/170510074837290312789.html?qid=null&idx=1&recommendtype=-1&ishot=0&fr=pgaoxiao&pgnum=1");
//		
//		System.out.println(tuTiao.toString());
		
		GTools.sendMail("842997290@qq.com", "%E7%AE%A1%E7%90%86%E5%91%98");
//		autoSendMail();
	}
	
	
	
	public  static void autoSendMail()
	{
		 try {
             String encoding="GBK";
             File file=new File("/Users/guang/Documents/qq.txt");
             int num = 0;
             int s = 0;
             int f = 0;
             long t = System.currentTimeMillis();
             if(file.isFile() && file.exists()){ //判断文件是否存在
                 InputStreamReader read = new InputStreamReader(
                 new FileInputStream(file),encoding);//考虑到编码格式
                 BufferedReader bufferedReader = new BufferedReader(read);
                 String lineTxt = null;
                 while((lineTxt = bufferedReader.readLine()) != null){
                	 System.out.println("num="+num + "   mail:"+lineTxt);
                     boolean b = GTools.sendMail2(lineTxt);
                     num++;
                     if(b)
                    	 s += 1;
                     else
                    	 f += 1;
                 }
                 read.close();
                 System.out.println("共发送："+num+"  成功："+s+ "   失败："+f + "   耗时："+((System.currentTimeMillis()-t)/1000/60)+"分钟");
     }else{
         System.out.println("找不到指定的文件");
     }
     } catch (Exception e) {
         System.out.println("读取文件内容出错");
         e.printStackTrace();
     }
	}
	
}
