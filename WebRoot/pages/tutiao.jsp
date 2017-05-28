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
  <title>图吧:${tuTiao.title}</title>
  <meta name="keywords" content="图吧，图条吧，图条，搞笑故事，美女故事，图片故事，新闻故事，图条吧官网">
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
<script>
(function(){
    var bp = document.createElement('script');
    var curProtocol = window.location.protocol.split(':')[0];
    if (curProtocol === 'https') {
        bp.src = 'https://zz.bdstatic.com/linksubmit/push.js';        
    }
    else {
        bp.src = 'http://push.zhanzhang.baidu.com/push.js';
    }
    var s = document.getElementsByTagName("script")[0];
    s.parentNode.insertBefore(bp, s);
})();
</script>
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
			<b style="font-size:2em;">${tuTiao.title }</b><br>
			<small>${tuTiao.author } <s:date name="#tuTiao.cdate" format="yyyy-MM-dd HH:mm" /></small>
			<br><br>
		</p>
		
		<s:iterator value="#tuTiao.units" var="val" status="sta">
             <p class="text-center">
            	<a href="${val.picPath }"><img src="${val.picPath }" alt="${val.tdescribe }"  class="img-responsive center-block" style="max-height:500px;"></a>
             </p>
             <p class="text-center">
				${val.tdescribe }<br><br>
			</p>
         </s:iterator>
	</div>
	<div class="col-xs-3" id="tutiao_col2">
		<div class="list-group">
		    <a href="#tuijian" class="list-group-item active">
		        <h4 class="list-group-item-heading">
		            精彩热门
		        </h4>
		    </a>
		    <s:iterator value="#hots" var="val" status="sta">
		    <a href="<%=basePath%>${val.tid}" class="list-group-item">
		        <div class="list-group-item-heading" >
		        	<div style="overflow:hidden;max-height:200px;">
		            <img class="img-responsive" style="padding:0px;width:100%;" src="<%=basePath%>${val.units[0].picPath }" alt="图吧-${val.units[0].tdescribe }">
		       		</div>
		       		<span class="pic-num pull-right"><s:property value="#val.units.size()" />图</span>
		        </div>
		        <p class="list-group-item-text">
		            ${val.title }
		        </p>
		    </a>
		    </s:iterator>
		   
		</div>
		
		<div class="list-group">
		    <a href="#" class="list-group-item active">
		        <h4 class="list-group-item-heading">
		            猜你喜欢
		        </h4>
		    </a>
		    <s:iterator value="#news" var="val" status="sta">
		    <a href="<%=basePath%>${val.tid}" class="list-group-item">
		        <div class="list-group-item-heading" >
		        	<div style="overflow:hidden;max-height:200px;">
		            <img class="img-responsive" style="padding:0px;width:100%;" src="<%=basePath%>${val.units[0].picPath }" alt="图吧-${val.units[0].tdescribe }">
		       		</div>
		       		<span class="pic-num pull-right"><s:property value="#val.units.size()" />图</span>
		        </div>
		        <p class="list-group-item-text">
		            ${val.title }
		        </p>
		    </a>
		    </s:iterator>
		   
		</div>
		
	</div>
</div>


<div class="row" id="div_xiangguan">
    <div class="col-sm-12">
        <div class="panel panel-default">
            <div class="panel-body" style="padding-left:0px;padding-right:0px;">
                <h3>相关推荐</h3>
                
                <div id="about_row">
                	 <s:iterator value="#tuijians" var="val" status="sta">
                	 	<div class="col-xs-2 item" style="padding:2px;">
                	 		<a href="<%=basePath%>${val.tid}" style="text-decoration:none ;">
					    	<div class="thumbnail" style="margin:2px;">
								<div class="thumbnail about_wrap" style="overflow:hidden;max-height:120px;margin-bottom:0px;"><img style="padding:0px;width:100%;" src="<%=basePath%>${val.units[0].picPath }" alt="图吧-${val.units[0].tdescribe }"></div>
								<span class="pic-num pull-right"><s:property value="#val.units.size()" />图</span>
								<div class="caption"><b style="font-size:12px;">  ${val.title } </b></div>
							</div>
							</a>
						</div>
                	 </s:iterator>
                </div>
                  
            </div>
        </div>
    </div>
</div>

<div class="row" id="div_pinglun">
    <div class="col-sm-12">
        <div class="panel panel-default">
            <div class="panel-body" >
                <span class="tutiao-abstract-index"><em id="tutiao_pinglun_num"><s:property value="#tuTiao.commentNum" /></em><span class="tutiao-abstract-index">条评论</span></span>
                
                <div id="pinglun_row" style="margin-top:20px;">
                	<div class="input-group col-sm-6">
                      <textarea class="form-control glyphicon" id="tutiao-pinglun-content" rows="2" placeholder="写下您的评论..."></textarea>
                      <span class="glyphicon input-group-addon" id="tutiao-btn-pinglun">评论</span>
                      </div>
                </div>
                <ul class="list-group col-sm-6" style="margin-top:20px;" id="tutiao-pinglun-ul">
                	<s:iterator value="#tuTiao.comments" var="val" status="sta">
				    <li class="list-group-item">
				    	<p class="text-left"><a>${val.userName }</a> <small class="text-muted">${val.time }</small></p>
				    	<p class="text-left">${val.content }</p>
				        <p class="pull-right text-muted tutiao-btn-zan" id="tutiao-btn-zan" data-id="${val.id }"><span style="margin-right:5px;">${val.support }</span><span class="glyphicon glyphicon-thumbs-up"></span></p><br>
				   	</li>
				   </s:iterator>
				</ul>
				<div class="col-sm-12">
					<button type="button" class="btn btn-link" id="tutiao-morepinglun">查看更多评论</button>
				</div>
				
            </div>
        </div>
    </div>
</div>

<div class="row" id="div_moile_tuijian" style="display: none;">
	<div class="col-sm-12">
	
         <div class="list-group" id="div_moile_tuijian_con">
		    <a href="#" class="list-group-item active">
		        <h4 class="list-group-item-heading">
		            猜你喜欢
		        </h4>
		    </a>
		    <s:iterator value="#hots" var="val" status="sta">
		    <a href="<%=basePath%>${val.tid}" class="list-group-item">
		        <div class="list-group-item-heading" >
		        	<div style="overflow:hidden;max-height:200px;">
		            <img class="img-responsive" style="padding:0px;width:100%;" src="<%=basePath%>${val.units[0].picPath }" alt="${val.units[0].tdescribe }">
		       		</div>
		       		<span class="pic-num pull-right"><s:property value="#val.units.size()" />图</span>
		        </div>
		        <p class="list-group-item-text">
		            ${val.title }
		        </p>
		    </a>
		    </s:iterator>
		   
		</div>
		<div class="col-sm-12">
			<button type="button" class="btn btn-link center-block" id="div_moile_tuijian_more">查看更多</button>
		</div>
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