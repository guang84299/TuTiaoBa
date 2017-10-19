package com.guang.web.tools;

import java.io.IOException;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service
public class SpringTools implements ApplicationContextAware {
	private static ApplicationContext applicationContext;
	
	public static  ApplicationContext getApplicationContext()
	{
		return applicationContext;
	}
	
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		applicationContext = arg0;
	}
	
	public static String getAbsolutePath(String file)
	{
		String s = null;
		 try {
			s = SpringTools.getApplicationContext().getResource(file).getFile().getAbsolutePath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return s;
	}
}
