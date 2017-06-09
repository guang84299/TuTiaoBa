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
%><jsp:include page="/includes/head.jsp" />
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
                <div><a href="<%=basePath%>"><img class="navbar-brand"  src="<%=basePath%>images/logo.png" class="img-rounded"></a></div>
            </div>  
            <div class="collapse navbar-collapse" id="my-navbar-collapse">                  
                <div class="nav navbar-nav btn-group">
                		<a  href="<%=basePath%>" class="btn btn-default index-nav-btn <s:if test="#type == 1">index-active</s:if>" id="nav_new">最新</a>
                    	<a  href="<%=basePath%>tutiao_hot" class="btn btn-default index-nav-btn <s:if test="#type == 2">index-active</s:if>" id="nav_hot">最热</a>
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

<div class="index-content">

	<div class="row index-content2 masonry" id="tutiaos" data-type="${type }" style="margin-left:0px;margin-right:0px;">
		
		<s:iterator value="#tuTiaos" var="val" status="sta">
		<div class="col-xs-3 item"  style="padding:1px;">
			<a href="<%=basePath%>${val.tid}" style="text-decoration:none ;">
			<div class="thumbnail" style="margin:2px;">
				<div style="overflow:hidden;"><img class="img-thumbnail" style="padding:0px;width:100%;" src="<%=basePath%>${val.headPath}" alt="图吧-${val.units[0].tdescribe}"></div>
					<span class="pic-num pull-right"><s:property value="#val.units.size()" />图</span>
					<div class="caption">
						<b> ${val.title} </b>
						<div class="index-fontBottom">${val.author} ⋅ ${val.showNum}浏览 ⋅ <s:date name="#val.cdate" format="yyyy-MM-dd" /></div>
					</div>
			</div>
			</a>
		</div>
		</s:iterator>
						
	</div>
</div>

<div id="imloading" style="width: 250px; height: 30px; line-height: 30px; font-size: 16px; text-align: center; border-radius: 3px; background: rgb(160, 160, 160); cursor:hand; margin: 10px auto 10px; color: rgb(255, 255, 255);">
		查看更多
</div>
		
<div class="index-bottom" > 
	<a href= target=_blank> 设为首页 </a> <span><a href="http://www.miitbeian.gov.cn/">豫ICP备17017459号</a> </span>
</div>

<jsp:include page="/includes/foot.jsp" />
<script src="<%=basePath%>scripts/main.js"></script>
</html>