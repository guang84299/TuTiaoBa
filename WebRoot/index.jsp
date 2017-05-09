<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	isELIgnored="false"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- saved from url=(0045)http://media.qiqiup.com/QianQi/tuijian_addapk -->
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1,user-scalable=no">
  <title>图条吧</title>
  <meta name="keywords" content="图条吧，图条，图吧，图片，条吧，图片故事，头条网，头条新闻，图条吧官网">
  <meta name="description" content="《图条吧》(www.tutiaoba.com)每天提供最新最好看的图片故事，美女故事，新闻故事，有你好看！">
  <link rel="icon" type="image/png" href="favicon.png" sizes="32x32" />
  <link rel="bookmark" type="image/x-icon" href="favicon.ico" />
  <link rel="shortcut icon" type="image/x-icon" href="favicon.ico"/>
  <link href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
  <link href="<%=basePath%>styles/main.css" rel="stylesheet">
  
  
  <script>
var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "https://hm.baidu.com/hm.js?71032e5a3071011e7d6b356b5fdfa901";
  var s = document.getElementsByTagName("script")[0]; 
  s.parentNode.insertBefore(hm, s);
})();
</script>
  
 </head>
 <body>
<div>

   <nav class="navbar navbar-fixed-top my-navbar" role="navigation">  
        <div class="container-fluid content3">  
            <div class="navbar-header">  
                <button type="button" class="navbar-toggle" data-toggle="collapse"  
                        data-target="#my-navbar-collapse">  
                    <span class="sr-only">切换导航</span>  
                    <span class="icon-bar"></span>  
                    <span class="icon-bar"></span>  
                    <span class="icon-bar"></span>  
                </button>  
                <div><img class="navbar-brand" href="<%=basePath%>" src="<%=basePath%>images/logo.png" class="img-rounded"></div>
            </div>  
            <div class="collapse navbar-collapse" id="my-navbar-collapse">  
                <ul class="nav navbar-nav">  
                    <li ><a class="active myactive" href="#" id="nav_new">最新</a></li>  
                    <li ><a class="" href="#" id="nav_hot">最热</a></li>  
                    <li ><a class="" href="#" id="nav_search">搜索结果</a></li>  
                </ul>  

                <div class="navbar-form navbar-right" role="search">
                  <div class="form-group">
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

<div class="content container">

	<div class="row content2 masonry" id="tutiaos">

	</div>
</div>

<div id="imloading" style="width: 100px; height: 20px; line-height: 20px; font-size: 12px; text-align: center; border-radius: 3px; opacity: 0.0; background: rgb(0, 0, 0); margin: 10px auto 5px; color: rgb(255, 255, 255);">
		加载中.....
</div>
		
<div class="bottom" > 
	<a href= target=_blank> 设为首页 </a> <span><a href="http://www.miitbeian.gov.cn/">豫ICP备17017459号</a>&nbsp;</span>
</div>

</body>
<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://unpkg.com/masonry-layout@4/dist/masonry.pkgd.min.js"></script>
<script src="https://unpkg.com/imagesloaded@4/imagesloaded.pkgd.min.js"></script>
<script src="<%=basePath%>scripts/main.js"></script>
<script src="<%=basePath%>scripts/imgh.js"></script>
</html>