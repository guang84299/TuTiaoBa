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
import com.guang.web.tools.GAutoTool;
import com.guang.web.tools.GTools;
import com.guang.web.tools.StringTools;

public class GTest {

	public static void main(String[] args)
	{
		GTuTiao tuTiao = GAutoTool.toutiao3("https://mini.eastday.com/pictures/20170510/170510074837290312789.html?qid=null&idx=1&recommendtype=-1&ishot=0&fr=pgaoxiao&pgnum=1");
		
		System.out.println(tuTiao.toString());
	}
	
	
	
}
