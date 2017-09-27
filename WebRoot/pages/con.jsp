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
  <title>【程序员网】${article.title }</title>
  <meta name="keywords" content="${article.keywords },程序员网,程序员段子,程序员技术,程序员图片,春光博客">
  <meta name="description" content="${article.title }_程序员段子_程序员技术_程序员图片">
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
      <a href="<%=basePath%>" class="navbar-brand">程序员网</a>
    </div>
    <nav id="bs-navbar" class="collapse navbar-collapse">
      <ul class="nav navbar-nav">
        <li class="active">
          <a href="<%=basePath%>">技术</a>
        </li>
        <li  class="active">
          <a href="<%=basePath%>duanzi">段子</a>
        </li>
        <li  class="active">
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

<div class="g-sc-3 row">
    <h2>${article.title }</h2>
    <div class="row g-hot g-con-head">
      <div class="col-xs-4 col-sm-3 col-md-2 g-hot-head">
        <img src="<%=basePath%><s:if test="#article.headPath==null">img/head.jpg</s:if><s:else>${article.headPath}</s:else>" alt="${article.title }" class="img-circle">
      </div>
      <div class="col-xs-20 col-sm-21 col-md-22 g-hot-con">
        <strong>${article.author}</strong>
        <p class="wrapper2"><small class="text-muted"><s:date name="#article.cdate" format="yyyy.MM.dd"/> 阅读 ${article.showNum } 评论 <span class="commentNum">${article.commentNum }</span> 喜欢 <span class="loveNum">${article.loveNum }</span></small></p>
      </div>
    </div>

    <div class="g-con-con">
    ${article.content }
    </div>

    <div class="g-con-exp">
      <a href="<%=basePath%>tag/${article.tag.id}.html"><span class="glyphicon glyphicon-bookmark"></span><small> ${article.tag.name }</small></a>
      <span class="pull-right"><span class="glyphicon glyphicon-warning-sign"></span><small> 转载请联系作者，并注明出处。</small></span>
    </div>
    
    <div class="g-con-appreciate">
      <p>如果觉得我的文章对您有用，请随意赞赏。您的支持将鼓励我继续创作！</p>
      <button type="button" class="btn btn-danger g-btn-appreciate">赞赏支持</button>
      <div class="row g-con-appreciate-show">
        <div class="col-xs-12">
             <img src="<%=basePath%>img/pay_zhi.jpg" alt="春光支付宝" class="img-responsive">
             <h4>支付宝</h4>
        </div>
        <div class="col-xs-12">
             <img src="<%=basePath%>img/pay_wei.jpg" alt="春光微信" class="img-responsive">
             <h4>微信</h4>
        </div>
      </div>
    </div>
    <hr>
    <div class="g-con-share">
      <span class="g-con-share-heart <s:if test="#love == true">g-con-share-heart-love</s:if>" data-id="${article.id }">
        <span class="glyphicon glyphicon-heart-empty"></span> 喜欢  &nbsp;|&nbsp; <span class="loveNum">${article.loveNum }</span>
      </span>
      <div class="bdsharebuttonbox pull-right"><a href="#" class="bds_more" data-cmd="more"></a><a href="#" class="bds_weixin" data-cmd="weixin" title="分享到微信"></a><a href="#" class="bds_qzone" data-cmd="qzone" title="分享到QQ空间"></a><a href="#" class="bds_tsina" data-cmd="tsina" title="分享到新浪微博"></a></div>
    </div>

    <div class="g-con-prenext row">
    <s:if test="#pre != null">
    	<div class="col-xs-12 text-left notice"><a href="<%=basePath%>${pre.id}.html"><span class="glyphicon glyphicon-step-backward"></span> ${pre.title }</a></div>
    </s:if>
    <s:else>
    	<div class="col-xs-12 text-left notice"><a href="#"><span class="glyphicon glyphicon-step-backward"></span> 没有啦</a></div>
    </s:else>
    <s:if test="#next != null">
    	<div class="col-xs-12 text-right notice"><a href="<%=basePath%>${next.id}.html">${next.title }&nbsp;<span class="glyphicon glyphicon-step-forward"></span></a></div>
    </s:if>
    <s:else>
    	<div class="col-xs-12 text-right notice"><a href="#" class="pull-right"><span class="glyphicon glyphicon-step-forward"></span> 没有啦</a></div>
    </s:else>
    </div>

    <div class="g-con-pinglun">
      <textarea class="col-xs-24" id="ta_pinglun" data-id="${article.id }" rows="3" placeholder="写下您的评论..."></textarea> 
      <label>IP:${ip }</label>
      <button type="button" class="btn btn-warning pull-right g-btn-pinglun">发表</button>
    </div>

    <div class="g-con-pinglun-con">
      <h4><span class="commentNum">${article.commentNum }</span>条评论</h4>
      <hr>
	 <div id="commets">
<s:iterator value="#comments" var="val" status="sta">	 
      <div>
        <p><strong>${val.xip }</strong>&nbsp;&nbsp;&nbsp;<small class="text-muted">${val.floor }楼 · <s:date name="#val.cdate" format="yyyy.MM.dd HH:mm"/></small></p>
        <p>${val.content }</p>
        <div class="text-right g-con-pinglun-con-zan <s:if test="#val.love == true">g-con-pinglun-con-zan-love</s:if>" data-id="${val.id }"><span class="glyphicon glyphicon-thumbs-up"></span> <span class="commentloveNum">${val.loveNum }</span>人赞</div>
        <hr>
      </div>
 </s:iterator>     
	</div>
      <s:if test="#more == true"><span class="g-label"><div class="g-label-bg">更多评论</div></span></s:if>

    </div>

</div>


</div>
<script src="<%=basePath%>js/jquery.min.js"></script>
<script src="<%=basePath%>js/bootstrap.min.js"></script>
<script src="<%=basePath%>js/jquery.dotdotdot.min.js"></script>
<script src="<%=basePath%>js/common.js"></script>
<script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdMiniList":false,"bdPic":"","bdStyle":"0","bdSize":"32"},"share":{}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>
<script>
var _hmt = _hmt || [];
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
})(); 
</script>
</body>
</html>