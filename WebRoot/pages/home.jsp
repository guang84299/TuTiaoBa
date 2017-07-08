<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	isELIgnored="false"%><%@taglib uri="/struts-tags" prefix="s"%><%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + path + "/";
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
  <title>春光博客_技术分享_程序员最喜欢的图条吧</title>
  <meta name="keywords" content="春光博客,技术分享,精美段子,女神图片,图条吧">
  <meta name="description" content="《春光博客》(www.tutiaoba.com)程序员的福利，分享日常开发的技术难题，提供精美段子和女神美女图片。">
  <link rel="icon" type="image/png" href="<%=basePath%>favicon.png" sizes="48x48" />
  <link rel="bookmark" type="image/x-icon" href="<%=basePath%>favicon.ico" />
  <link rel="shortcut icon" type="image/x-icon" href="<%=basePath%>favicon.ico"/>
  <link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
  <link href="<%=basePath%>css/common.css" rel="stylesheet">
 </head>
<body>
<div class="g-sc">
   <!-- Docs master nav -->
<header class="navbar navbar-static-top" id="top">
  <div class="container">
    <div class="navbar-header">
      <button class="navbar-toggle collapsed" type="button" data-toggle="collapse" data-target="#bs-navbar" aria-controls="bs-navbar" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a href="<%=basePath%>" class="navbar-brand">春光博客</a>
    </div>
    <nav id="bs-navbar" class="collapse navbar-collapse">
      <ul class="nav navbar-nav">
        <li class="active <s:if test="#type == 'home'">g-active</s:if>">
          <a href="<%=basePath%>">技术</a>
        </li>
        <li  class="active <s:if test="#type == 'duanzi'">g-active</s:if>">
          <a href="<%=basePath%>duanzi">段子</a>
        </li>
        <li  class="active <s:if test="#type == 'mm'">g-active</s:if>">
          <a href="<%=basePath%>mm">美女</a>
        </li>
      </ul>
      <form class="nav navbar-nav navbar-form navbar-right">
        <div class="input-group">
          <input type="text" class="form-control glyphicon glyphicon-search" placeholder="搜索">
          <span class="glyphicon glyphicon-search input-group-addon"></span>
        </div>
      </form>
    </nav>
  </div>
</header>

<div class="g-sc-2 row">
  <div class="col-xs-24 col-sm-16 col-md-16 col-lg-16">
    <strong class="text-muted">
    <s:if test="#type == 'search'">搜索结果</s:if><s:else>最新文章</s:else>
     </strong>
    <hr>
<s:iterator value="#articles" var="val" status="sta">
    <div>
      <div class="g-head">
        <img src="<%=basePath%>img/head.jpg" alt="${val.title}" class="img-circle">
        <small>春光</small>
        <small class="text-muted"><s:date name="#val.cdate" format="yyyy-MM-dd HH:mm" /></small>
      </div>
      <div class="row">
        <div class="col-xs-14 col-sm-18">
          <h4><a href="<%=basePath%>${val.id}.html" class="g-con-a">${val.title}</a></h4>
          <div class="wrapper"><p class="small">${val.summary}</p></div>
        </div>
        <div class="col-xs-10 col-sm-6">
          <a href="<%=basePath%>${val.id}.html"><img src="<%=basePath%>${val.headPath}" alt="${val.title}" class="img-responsive img-rounded g-con-img"></a>
        </div>
      </div>
      <div>
        <a class="btn btn-xs g-btn" href="<%=basePath%>tag/${val.tag.id}.html">${val.tag.name}</a>
        <strong class="g-bottom">
        <a href="<%=basePath%>${val.id}.html"><span class="glyphicon glyphicon-eye-open"></span><small>${val.showNum}</small></a>
        <a><span class="glyphicon glyphicon-comment"></span><small>${val.commentNum}</small></a>
        <a><span class="glyphicon glyphicon-heart"></span><small>${val.loveNum}</small></a>
        </strong>
      </div>
      <hr>
    </div>
</s:iterator>
    <s:if test="#more==true">
    	<s:if test="#type == 'tag'">
    		<a href="<%=basePath%>${type}/${tagId}_${index}.html" class="g-label"><div class="g-label-bg">阅读更多</div></a>
    	</s:if>
    	<s:elseif test="#type == 'search'">
    		<a href="<%=basePath%>article_search?val=${vals}&index=${index}" class="g-label"><div class="g-label-bg">阅读更多</div></a>
    	</s:elseif>
    	<s:else>
    		<a href="<%=basePath%>${type}/${index}" class="g-label"><div class="g-label-bg">阅读更多</div></a>
    	</s:else>
	</s:if>
  </div>
  <div class="col-xs-24 col-sm-1 col-md-1 col-lg-1 hidden-xs"></div>
  <div class="col-xs-24 col-sm-7 col-md-7 col-lg-7 hidden-xs">
    <strong class="text-muted">热点推荐</strong>
    <hr>
<s:iterator value="#articlehots" var="val" status="sta">    
    <div class="row g-hot">
      <div class="col-xs-5 g-hot-head">
        <img src="<%=basePath%>img/head.jpg" alt="${val.title}" class="img-circle">
      </div>
      <div class="col-xs-19 g-hot-con">
        <strong>春光</strong>
        <p class="wrapper2"><small><a href="<%=basePath%>${val.id}.html">${val.title}</a></small></p>
      </div>
    </div>
</s:iterator>    
    <br>
    <strong class="text-muted">热门标签</strong>
    <hr>
    <div>
<s:iterator value="#tags" var="val" status="sta">
    <a href="<%=basePath%>tag/${val.id}.html" class="label label-default">${val.name }</a>
</s:iterator>
    </div>

  </div>

<div class="col-xs-24 col-sm-16 col-md-16 col-lg-16 g-foot">
  <p class="text-center"><small>本站内容多为原创。</small></p>
  <p class="text-center"><small>© 2017 (<a href="www.tutiaoba.com">www.tutiaoba.com</a>) 图条吧 版权所有 <a href="http://www.miitbeian.gov.cn/">豫ICP备17017459号</a></small></p>
</div>

</div>

</div>
    <script src="<%=basePath%>js/jquery.min.js"></script>
    <script src="<%=basePath%>js/bootstrap.min.js"></script>
    <script src="<%=basePath%>js/jquery.dotdotdot.min.js"></script>
    <script src="<%=basePath%>js/common.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
          $(".wrapper").dotdotdot({
            ellipsis  : '... ',
            height    : 60
          });
          $(".wrapper2").dotdotdot({
            ellipsis  : '... ',
            height    : 20
          });
          
          
        });
    </script>
  </body>
</html>