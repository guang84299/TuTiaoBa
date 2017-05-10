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
  <meta name="keywords" content="图条吧，图条，图吧，搞笑故事，美女故事，图片故事，新闻故事，图条吧官网">
  <meta name="description" content="《图条吧》(www.tutiaoba.com)每天提供最新最好看的图片故事，搞笑故事，美女故事，新闻故事，有你好看！">
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
                    <li ><a class="ac" href="#" id="nav_kan">看图条</a></li>  
                    <li ><a class="ac" href="#" id="nav_search">搜索结果</a></li>
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

<div class="row" id="tutiao_show_row">
    <div class="col-sm-8" >
         <div class="thumbnail  picShow" id="tutiao_show" data-id="${id}">
            <img src="" alt="" width="800px" id="tutiao_show_img">
             <div class="caption text-center">
                <button type="button" class="btn btn-primary btn-sm" id="pic_left">
                  <span class="glyphicon glyphicon-step-backward"></span> 上一个
                </button>
                <small>&nbsp;&nbsp;&nbsp;</small>
                <button type="button" class="btn btn-primary btn-sm" id="pic_lager">
                  <span class="glyphicon glyphicon-search"></span> 大图
                </button>
                <small>&nbsp;&nbsp;&nbsp;</small>
                <button type="button" class="btn btn-primary btn-sm" id="pic_right">
                  <span class="glyphicon glyphicon-step-forward"></span> 下一个
                </button>
             </div>
        </div>

    </div>
    <div class="col-sm-4">
        <div class="panel panel-default">
            <div class="panel-body">
    
                <h4 id="tutiao_show_title"></h4>
                <span class="abstract-index"><em id="tutiao_show_curr_page">1</em><span class="abstract-index">/2</span></span>
                
                <p id="tutiao_show_des"> </p>
                
            </div>
        </div>

    </div>
    
</div>



<div class="row" id="tuijian_row">
    <div class="col-sm-12">
        <div class="panel panel-default">
            <div class="panel-body">
                
                <div class="panel-body-d">
                    <div class="row">
                        <div class="col-sm-8">
                            <h4 id="tuijian_title"></h4>
                        </div>
                        <div class="col-sm-4">
                            <small class="pull-right">
                            <button type="button" class="btn btn-primary btn-sm" id="tuijian_refresh">
                                  <span class="glyphicon glyphicon-refresh"></span> 重新浏览
                                </button>
                                <button type="button" class="btn btn-primary btn-sm" id="tuijian_next">
                                  <span class="glyphicon glyphicon-step-forward"></span> 下一图条
                                </button>
                            </small>
                        </div>
                    </div>
    
                </div>
                
				<div id="tuijian_data" class="masonry">
				
				</div>
                
                
            </div>
        </div>
    </div>
</div>



<div class="row" id="div_xiangguan">
    <div class="col-sm-12">
        <div class="panel panel-default">
            <div class="panel-body" >
                <h3>相关推荐</h3>
                
                <div id="about_row" class="masonry">
                
                </div>
                  
            </div>
        </div>
    </div>
</div>

<div class="row" id="div_search">
    <div class="col-sm-12">
         <div id="search_row" class="masonry">
                
         </div>
    </div>
</div>

</div>


<div class="d_large_show thumbnail" id="img_large_show">
    <img src="">
</div>


</body>
<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://unpkg.com/masonry-layout@4/dist/masonry.pkgd.min.js"></script>
<script src="https://unpkg.com/imagesloaded@4/imagesloaded.pkgd.min.js"></script>
<script src="<%=basePath%>scripts/main2.js"></script>
<script src="<%=basePath%>scripts/imgh.js"></script>
</html>