<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	isELIgnored="false"%><%@taglib uri="/struts-tags" prefix="s"%><%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() +  path + "/";
	int port = request.getServerPort();
	if(port == 8080)
	{
		basePath = request.getScheme() + "://"
			+ request.getServerName() + ":"+ port + path + "/";
	}
%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1,user-scalable=no">
  <title>图条吧_美女图片大全_美好生活图片_mm图片网_人体艺术写真</title>
  <meta name="keywords" content="美女图片大全,美好生活图片,mm图片网,人体艺术写真,图条吧">
  <meta name="description" content="《图条吧》(www.tutiaoba.com)收集最新最美的美女图片，生活图片，热点图片，包括明星美女写真图片专辑，大学校花美女贴图，性感车模写真等各类最新最好看的性感美女图片。">
  <link rel="icon" type="image/png" href="<%=basePath%>favicon.png" sizes="48x48" />
  <link rel="bookmark" type="image/x-icon" href="<%=basePath%>favicon.ico" />
  <link rel="shortcut icon" type="image/x-icon" href="<%=basePath%>favicon.ico"/>
  <link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
  <link href="<%=basePath%>css/docs.min.css" rel="stylesheet">
  <link href="<%=basePath%>css/common.css" rel="stylesheet">
  <meta property="og:type" content="image"/>
  <meta property="og:image" content="<%=basePath%>img/360.jpg"/>
  <style type="text/css">
      .notice{overflow: hidden;text-overflow: ellipsis;white-space: nowrap;}
      .g-sc{overflow:auto;overflow-x: hidden;}
  </style>
 </head>
 <body>
<div class="g-sc">
   <!-- Docs master nav -->
<header class="navbar navbar-static-top bs-docs-nav" id="top">
  <div class="container">
    <div class="navbar-header">
      <button class="navbar-toggle collapsed" type="button" data-toggle="collapse" data-target="#bs-navbar" aria-controls="bs-navbar" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a href="<%=basePath%>" class="navbar-brand">图条吧</a>
    </div>
    <nav id="bs-navbar" class="collapse navbar-collapse">
      <ul class="nav navbar-nav">
        <li>
          <a href="<%=basePath%>mm">美女图条</a>
        </li>
        <li>
          <a href="<%=basePath%>life">生活图条</a>
        </li>
      </ul>
      <div class="nav navbar-nav navbar-form navbar-right">
        <div class="input-group">
          <input type="text" class="form-control glyphicon glyphicon-search" placeholder="搜索">
          <span class="glyphicon glyphicon-search input-group-addon"></span>
        </div>
      </div>
    </nav>
  </div>
</header>