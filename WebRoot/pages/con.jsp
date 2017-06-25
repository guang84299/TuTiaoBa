<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	isELIgnored="false"%><%@taglib uri="/struts-tags" prefix="s"%><%@ page trimDirectiveWhitespaces="true" %><%
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
  <title>${tuTiao.title } 图条吧</title>
  <meta name="keywords" content="${tuTiao.keywords },图条吧">
  <meta name="description" content="${tuTiao.title } 图条吧www.tutiaoba.com">
  <link rel="icon" type="image/png" href="<%=basePath%>favicon.png" sizes="48x48" />
  <link rel="bookmark" type="image/x-icon" href="<%=basePath%>favicon.ico" />
  <link rel="shortcut icon" type="image/x-icon" href="<%=basePath%>favicon.ico"/>
  <link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
  <link href="<%=basePath%>css/docs.min.css" rel="stylesheet">
  <link href="<%=basePath%>css/common.css" rel="stylesheet">
  <style type="text/css">
      .notice{overflow: hidden;text-overflow: ellipsis;white-space: nowrap;}
      .g-sc{overflow:auto;overflow-x: hidden;}
  </style>
 <script>
/* var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "https://hm.baidu.com/hm.js?71032e5a3071011e7d6b356b5fdfa901";
  var s = document.getElementsByTagName("script")[0]; 
  s.parentNode.insertBefore(hm, s);
})();
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
})(); */
</script>
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

<div class="row">
  <div class="visible-md-block visible-lg-block col-md-2 col-xs-2"></div> 

  <div class="content-2 col-md-20 col-xs-24">

  <div class="row">

  <div class="col-md-24 col-xs-24">

  <div class="row g-thumbnail g-box-4">
    <div class="g-title g-title5">
          <h4>${tuTiao.title }<s:if test="#page > 1">(${page })</s:if></h4>
          <p><small>更新时间：<s:date name="#tuTiao.cdate" format="yyyy-MM-dd HH:mm:ss" /> <a href="<%=basePath%>">图条吧图片</a></small></p>
    </div>
    <div class="col-xs-24">
        <a href="<%=basePath%>${tuTiao.unit.picPath }"><img class="img-responsive center-block" src="<%=basePath%>${tuTiao.unit.picPath }" alt="${tuTiao.title }"></a>
    </div>
    
    <div class="g-title col-xs-24">
          <nav aria-label="Page navigation">
            <ul class="pagination pagination-sm">
            <s:if test="#page > 1">
              <li>
                <a href="<%=basePath%>${url }<s:property value="#page-1" />.html" aria-label="Previous">
                  <span aria-hidden="true">上一页</span>
                </a>
              </li>
              </s:if>
              <s:bean name="org.apache.struts2.util.Counter" id="counter" >       
				  <s:param name="first"  value="1"  />       
				  <s:param name="last"  value="5"  />       
				  <s:iterator>  
				  	<s:if test="current-1+#page-1 <= #count">
				    <li class="<s:if test="current-1+#page-1 == #page">active</s:if>">
				    <a href="<%=basePath%>${url }<s:property value="current-1+#page-1"/>.html"><s:property value="current-1+#page-1"/></a>
				    </li>
				    </s:if>    
				  </s:iterator>       
				</s:bean>     
              <s:if test="#page < #count">
              <li>
                <a href="<%=basePath%>${url }<s:property value="#page+1" />.html" aria-label="Next">
                  <span aria-hidden="true">下一页</span>
                </a>
              </li>
              </s:if>
            </ul>
          </nav>
    </div>


  </div>

  <div class="row g-box-7">
    <div class="g-title6 col-xs-12">
      <h5>
      <s:if test="#pre==null">
      <a href="#" class="btn btn-info">上一组</a>
      <a class="g-title6-a-1 hidden-xs" href="#">没有了</a>
      </s:if>
      <s:else>
      	<a href="<%=basePath%><s:if test="#pre.type==1">mm</s:if><s:else>life</s:else>/${pre.id}.html" target="_blank" class="btn btn-info">上一组</a>
      	<a class="g-title6-a-1 hidden-xs" href="<%=basePath%><s:if test="#pre.type==1">mm</s:if><s:else>life</s:else>/${pre.id}.html" target="_blank">${pre.title}</a>
      </s:else>
      </h5>
    </div>
    <div class="g-title6 col-xs-12">
      <h5 class="text-right">
      <s:if test="#next==null">
      <a class="g-title6-a-2 hidden-xs" href="#">没有了</a>
      <a class="btn btn-info" href="#">下一组</a>
      </s:if>
      <s:else>
      	<a class="g-title6-a-2 hidden-xs" href="<%=basePath%><s:if test="#next.type==1">mm</s:if><s:else>life</s:else>/${next.id}.html" target="_blank">${next.title}</a>
      	<a class="btn btn-info" href="<%=basePath%><s:if test="#next.type==1">mm</s:if><s:else>life</s:else>/${next.id}.html" target="_blank">下一组</a>
      </s:else>
      </h5>
    </div>
  </div>

  <div class="row g-box-7">
    
    <div class="row g-thumbnail-2 g-box-4">
 	<s:iterator value="#tuijian" var="val" status="sta">
    <div class="col-sm-8 col-md-4 col-xs-12">
      <div class="thumbnail">
        <a href="<%=basePath%><s:if test="#val.type==1">mm</s:if><s:else>life</s:else>/${val.id}.html" target="_blank"><img src="<%=basePath%>${val.headPath }" alt="${val.title }"></a>
        <div class="caption notice">
          <a href="<%=basePath%><s:if test="#val.type==1">mm</s:if><s:else>life</s:else>/${val.id}.html" target="_blank">${val.title }</a>
        </div>
      </div>
    </div>
    </s:iterator>
    </div>

  </div>

  </div>


  </div>

  </div> 
<!-- row end -->
  </div>

  <div class="visible-md-block visible-lg-block col-md-2 col-xs-2"></div> 
</div>

<jsp:include page="/includes/foot.jsp" />
</html>