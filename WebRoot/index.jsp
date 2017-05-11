<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	isELIgnored="false"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<jsp:include page="/includes/head.jsp" />
<div>
   <nav class="navbar navbar-fixed-top my-navbar" role="navigation">  
        <div class="container-fluid index-content3">  
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
                    <li ><a class="active index-active" href="#" id="nav_new">最新</a></li>  
                    <li ><a class="" href="#" id="nav_hot">最热</a></li>  
                    <li ><a class="" href="#" id="nav_search" style="display: none;">搜索结果</a></li>  
                </ul>  

                <div class="navbar-form navbar-right" role="search">
                  <div class="form-group">
                  		
                       <div class="input-group" id="btn-login" style="width:50px;height:30px;margin-right:10px;background:#ED4040;line-height:30px;text-align: center;color:#fff;cursor:hand;">
                       	登录
                       </div>
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

<div class="index-content container">

	<div class="row index-content2 masonry" id="tutiaos">

	</div>
</div>

<div id="imloading" style="width: 100px; height: 20px; line-height: 20px; font-size: 12px; text-align: center; border-radius: 3px; opacity: 0.0; background: rgb(0, 0, 0); margin: 10px auto 5px; color: rgb(255, 255, 255);">
		加载中.....
</div>
		
<div class="index-bottom" > 
	<a href= target=_blank> 设为首页 </a> <span><a href="http://www.miitbeian.gov.cn/">豫ICP备17017459号</a>&nbsp;&nbsp;友情链接&nbsp;<a href="http://www.hao123.com/">hao123</a></span>
</div>

<jsp:include page="/includes/foot.jsp" />
<script src="<%=basePath%>scripts/main.js"></script>