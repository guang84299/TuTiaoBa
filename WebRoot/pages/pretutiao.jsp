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
  <title>${tuTiao.title}</title>
  <meta name="keywords" content="图吧，图条吧，图条，搞笑故事，美女故事，图片故事，新闻故事，图条吧官网">
  <meta name="description" content="《图条吧》(www.tutiaoba.com)每天提供最新最好看的图片故事，搞笑故事，美女故事，新闻故事，有你好看！">
  <link rel="icon" type="image/png" href="favicon.png" sizes="32x32" />
  <link rel="bookmark" type="image/x-icon" href="favicon.ico" />
  <link rel="shortcut icon" type="image/x-icon" href="favicon.ico"/>
  <link href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
  <link href="<%=basePath%>styles/main.css" rel="stylesheet">
 </head>
 <body>
 <div id="particles">
 <div id="intro">
<div>
   <nav class="navbar navbar-fixed-top my-navbar" role="navigation">  
        <div class="container-fluid tutiao-content3">  
            <div class="navbar-header">  
                <button type="button" class="navbar-toggle" data-toggle="collapse"  
                        data-target="#my-navbar-collapse">  
                    <span class="sr-only">切换导航</span>  
                    <span class="icon-bar"></span>  
                    <span class="icon-bar"></span>  
                    <span class="icon-bar"></span>  
                </button>  
                <div><a href="<%=basePath%>"><img class="navbar-brand" href="/" src="<%=basePath%>images/logo.png" class="img-rounded"></a></div>
            </div>  
            <div class="collapse navbar-collapse" id="my-navbar-collapse">  
                <!-- <ul class="nav navbar-nav">  
                    <li ><a class="ac" href="#" id="">看图条</a></li>  
                    <li ><a class="ac" href="#" id="nav_search" style="display: none;">搜索结果</a></li>
                </ul>   -->
                <div class="nav navbar-nav btn-group">
                		<a  class="btn btn-default index-nav-btn index-active" id="nav_kan">看图条</a>
                    	<a  class="btn btn-default index-nav-btn index-active" id="nav_search" style="display: none;">搜索结果</a>
                </div>

                <div class="navbar-form navbar-right" role="search">
                
                	<div class="btn-group">
					  	<s:if test="#session.user != null">
					  		<a  class="btn btn-default index-nav-btn3" ><s:property value="#session.user.name" /></a>
					  		<a  href="<%=basePath%>user_loginOut" class="btn btn-default index-nav-btn4">退出</a>
					  	</s:if>
					  	<s:else>
                       		<a  class="btn btn-default index-nav-btn2" id="btn-login">登录</a>
                       	</s:else>
                       	<div class="input-group">
                        <input type="text" class="form-control glyphicon glyphicon-search" placeholder="Search">
                     			 <span class="glyphicon glyphicon-search input-group-addon"></span>
                      	</div>
                      </div>
                </div> 

            </div> 

        </div>  
    </nav>  

</div>

<div class="tutiao-content container">


<div class="row" id="tutiao_row">
	<div class="col-xs-1" ></div>
	<div class="col-xs-8" id="tutiao_col" data-tid="${tuTiao.tid }">
		<p>
			<b style="font-size:2em;">${tuTiao.title }</b><br><br>
			<small><b>${tuTiao.author } <s:date name="#tuTiao.cdate" format="yyyy-MM-dd HH:mm" /></b></small>
			<hr>
		</p>
		${tuTiao.content }
		
	</div>
	<div class="col-xs-3" id="tutiao_col2">
		
		
	</div>
</div>



<div class="row" id="div_search" >
    <div class="col-sm-12" style="padding:2px;">
         <div id="search_row" class="masonry">
                
         </div>
    </div>
</div>

</div>


<jsp:include page="/includes/foot.jsp" />
<script src="<%=basePath%>scripts/main2.js"></script>
</html>