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
<div class="g-sc-2">
<div class="row">
  <div class="content-2 col-md-24 col-xs-24">

  <div class="row">

  <div class="col-md-18 col-xs-24">

  <div class="row g-thumbnail g-box-4">
    <div class="g-title">
          <h4>搜索结果</h4>
    </div>
    <s:iterator value="#list" var="val" status="sta">
    <div class="col-sm-8 col-md-6 col-xs-12">
      <div class="thumbnail">
        <a href="<%=basePath%><s:if test="#pre.type==1">mm</s:if><s:else>life</s:else>/${val.id}.html" target="_blank"><img src="<%=basePath%>${val.headPath }" alt="${val.title }"></a>
        <div class="caption notice">
          <a href="<%=basePath%><s:if test="#pre.type==1">mm</s:if><s:else>life</s:else>/${val.id}.html" target="_blank">${val.title }</a>
        </div>
      </div>
    </div>
    </s:iterator>
    <div class="g-title col-xs-24">
          <h5><a href="<%=basePath%>tutiao_search?val=${vals}&index=${page}">查看更多...</a></h5>
    </div>

  </div>


  </div>

  <div class="visible-md-block visible-lg-block col-md-6 col-xs-6">

  <div class="g-box-5-1">
        <div class="g-title">
          <h4>浏览排行</h4>
        </div>
        <div class="list-group">
		<s:iterator value="#rank" var="val" status="sta">
          <div class="list-group-item notice">
            <span class="badge pull-left"><s:property value="#sta.index+1" /></span>
            <a href="<%=basePath%>mm/${val.id }.html" target="_blank">${val.title }</a>
          </div>
         </s:iterator> 

      </div>
  </div>

  <div class="g-box-5-2">
        <div class="g-title">
          <h4>最近更新</h4>
        </div>
        <div class="list-group">
		<s:iterator value="#list_new" var="val" status="sta">
          <div class="list-group-item notice">
            <span class="glyphicon glyphicon-heart pull-left"></span>
            <a href="<%=basePath%>life/${val.id }.html" target="_blank">${val.title }</a>
          </div>
         </s:iterator> 
      </div>
  </div>

  </div>
  

  </div> 
<!-- row end -->
  </div>
</div>
<div class="g-sc-2">
<jsp:include page="/includes/foot.jsp" />
</html>