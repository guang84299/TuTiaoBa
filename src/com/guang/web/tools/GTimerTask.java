package com.guang.web.tools;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;






public class GTimerTask {
	private final static Logger logger = LoggerFactory.getLogger(GTimerTask.class);
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);  
	private ScheduledFuture<?> taskHandle;  
    

	private  void init()
	{
		start();
	}
	
	private void update()
	{
		GAutoTool.update();
	}
	
	public void start()
	{
		 final Runnable task = new Runnable() {  
	            public void run() {  
	            	logger.info("开始抓取文章数据！");
	            	update();
	            	logger.info("抓取文章数据完成！");
	            }  
	     };  
	     
	     long oneDay = 24 * 60 * 60 * 1000;  
	     long initDelay  = getTimeMillis("00:05:00") - System.currentTimeMillis();  
	     initDelay = initDelay > 0 ? initDelay : oneDay + initDelay;  
//	     long delay = 18*60*1000;
	     taskHandle = scheduler.scheduleAtFixedRate(task, initDelay, oneDay, TimeUnit.MILLISECONDS);  
	}
	
	public void stop()
	{
		if(taskHandle != null && !taskHandle.isCancelled())
		{
			taskHandle.cancel(true);
		}
		
	}
	
	/** 
	 * 获取指定时间对应的毫秒数 
	 * @param time "HH:mm:ss" 
	 * @return 
	 */  
	private static long getTimeMillis(String time) {  
	    try {  
	        DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");  
	        DateFormat dayFormat = new SimpleDateFormat("yy-MM-dd");  
	        Date curDate = dateFormat.parse(dayFormat.format(new Date()) + " " + time);  
	        return curDate.getTime();  
	    } catch (ParseException e) {  
	        e.printStackTrace();  
	    }  
	    return 0;  
	}  
	
}
