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
                <ul class="nav navbar-nav">  
                    <li ><a class="ac" href="#" id="nav_kan">看图条</a></li>  
                    <li ><a class="ac" href="#" id="nav_search" style="display: none;">搜索结果</a></li>
                </ul>  

                <div class="navbar-form navbar-right" role="search">
                  <div class="form-group index-user-nav">
                  
                  	<s:if test="#session.user != null">
                  			<li><a href="#"><s:property value="#session.user.name" /></a>
					            <ul class="subs">
					                <li><a href="<%=basePath%>user_loginOut">退出</a></li>
					            </ul>
					        </li>
						</s:if>
						<s:else>
							<div class="input-group index-btn-login" id="btn-login">
	                       	登录
	                       </div>
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

<div class="row" id="tutiao_show_row">
    <div class="col-sm-8" >
         <div class="thumbnail" id="tutiao_show" data-tid="${tid}">
         	<s:iterator value="#tuTiao.units" var="val" status="sta">
         	<p  class="thumbnail" style="overflow:hidden;width:750px; height:450px;user-select：none;display:none;">
            	<img src="${val.picPath }" alt="${val.tdescribe }" id="tutiao_show_img" onselectstart="return false;" style="-moz-user-select:none;height:450px;">
            </p>
            </s:iterator>
             <div class="caption text-center">
                <button type="button" class="btn btn-primary btn-sm" id="tutiao-pic-left">
                  <span class="glyphicon glyphicon-step-backward"></span> 上一个
                </button>
                <small>&nbsp;&nbsp;&nbsp;</small>
                <button type="button" class="btn btn-primary btn-sm" id="pic_lager">
                  <span class="glyphicon glyphicon-search"></span> 大图
                </button>
                <small>&nbsp;&nbsp;&nbsp;</small>
                <button type="button" class="btn btn-primary btn-sm" id="tutiao-pic-right">
                  <span class="glyphicon glyphicon-step-forward"></span> 下一个
                </button>
             </div>
        </div>

    </div>
    <div class="col-sm-4">
        <div class="panel panel-default">
            <div class="panel-body" >
    
                <h4 id="tutiao_show_title">${tuTiao.title }</h4>
                <s:iterator value="#tuTiao.units" var="val" status="sta">
                <div class="tutiao_show_des" style="display:none;">
	                <span class="tutiao-abstract-index" ><em id="tutiao_show_curr_page"><s:property value="#sta.index+1" /></em><span class="tutiao-abstract-index">/<s:property value="#tuTiao.units.size()" /></span></span>
	                <p  >${val.tdescribe } </p>
                </div>
                </s:iterator>
            </div>
        </div>

    </div>
    
</div>



<div class="row" id="tuijian_row">
    <div class="col-sm-12">
        <div class="panel panel-default">
            <div class="panel-body" style="padding-left:0px;padding-right:0px;">
                
                <div class="tutiao-panel-body-d" style="padding-left:15px;padding-right:15px;">
                    <div class="row">
                        <div class="col-sm-8">
                            <h4 id="tuijian_title">${tuTiao.title }</h4>
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
					<s:iterator value="#xiangguan" var="val" status="sta">
                	 	<div class="col-sm-6 col-md-3 item" data-tid="<%=basePath%>${val.tid }">
					    	<div onclick="window.open('<%=basePath%>${val.tid }')" class="thumbnail">
								<div class="thumbnail tuijian_div_wrap" style="overflow:hidden;height:150px;width:250px;margin-bottom:0px;"><img style="height:220px;" src="<%=basePath%>${val.units[0].picPath }" alt="${val.units[0].tdescribe }"></div>
								<span class="pic-num pull-right"><s:property value="#val.units.size()" />图</span>
								<div class="caption"><b style="font-size:12px;">  ${val.title } </b></div>
							</div>
						</div>
                	 </s:iterator>
				</div>
                
                
            </div>
        </div>
    </div>
</div>



<div class="row" id="div_xiangguan">
    <div class="col-sm-12">
        <div class="panel panel-default">
            <div class="panel-body" style="padding-left:0px;padding-right:0px;">
                <h3>相关推荐</h3>
                
                <div id="about_row" class="masonry">
                	 <s:iterator value="#tuijian" var="val" status="sta">
                	 	<div class="col-sm-6 col-md-3 item">
					    	<div onclick="window.open('<%=basePath%>${val.tid }')" class="thumbnail">
								<div class="thumbnail about_wrap" style="overflow:hidden;height:150px;width:250px;margin-bottom:0px;"><img style="height:220px;" src="<%=basePath%>${val.units[0].picPath }" alt="${val.units[0].tdescribe }"></div>
								<span class="pic-num pull-right"><s:property value="#val.units.size()" />图</span>
								<div class="caption"><b style="font-size:12px;">  ${val.title } </b></div>
							</div>
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


<div class="row" id="div_search">
    <div class="col-sm-12">
         <div id="search_row" class="masonry">
                
         </div>
    </div>
</div>

</div>


<div class="tutiao-d-large-show thumbnail" id="img_large_show">
    <img src="">
</div>

<jsp:include page="/includes/foot.jsp" />
<script src="<%=basePath%>scripts/main2.js"></script>