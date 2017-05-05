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
  <link rel="icon" type="image/png" href="favicon.png" sizes="32x32" />
  <link rel="bookmark" type="image/x-icon" href="favicon.ico" />
  <link rel="shortcut icon" type="image/x-icon" href="favicon.ico"/>
  <link href="<%=basePath%>styles/bootstrap.min.css" rel="stylesheet">
  <link href="<%=basePath%>styles/main.css" rel="stylesheet">
  <script src="<%=basePath%>scripts/jquery.min.js"></script>
  <script src="<%=basePath%>scripts/bootstrap.min.js"></script>
  <script src="<%=basePath%>scripts/masonry.pkgd.min.js"></script>
  <script src="<%=basePath%>scripts/imagesloaded.pkgd.min.js"></script>
  <script src="<%=basePath%>scripts/main2.js"></script>
  <script src="<%=basePath%>scripts/imgh.js"></script>
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
    <div class="col-sm-8">
         <div class="thumbnail picShow" id="tutiao_show" title="${id}">
            <img src="" alt="">
            <span class="label label-default pic_left"><span class="glyphicon glyphicon-chevron-left"></span></span>
            <span class="label label-default pic_right"><span class="glyphicon glyphicon-chevron-right"></span></span>
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
                                  <span class="glyphicon glyphicon-step-forward"></span> 下一个
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



</body>
</html>