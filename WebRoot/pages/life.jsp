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

<div class="row">
  <div class="visible-md-block visible-lg-block col-md-2 col-xs-2"></div> 

  <div class="content-2 col-md-20 col-xs-24">

  <div class="row">

  <div class="col-md-18 col-xs-24">

  <div class="row g-thumbnail g-box-4">
    <div class="g-title">
          <h4>生活图条</h4>
    </div>
    <s:iterator value="#tutiao_life" var="val" status="sta">
    <div class="col-sm-8 col-md-6 col-xs-12">
      <div class="thumbnail">
        <a href="<%=basePath%>life/${val.id }.html" target="_blank"><img src="<%=basePath%>${val.headPath }" alt="${val.title }"></a>
        <div class="caption notice">
          <a href="<%=basePath%>life/${val.id }.html" target="_blank">${val.title }</a>
        </div>
      </div>
    </div>
    </s:iterator>
    <div class="g-title col-xs-24">
          <h5><a href="<%=basePath%>life/${page}">查看更多...</a></h5>
    </div>

  </div>


  </div>

  <div class="visible-md-block visible-lg-block col-md-6 col-xs-6">

  <div class="g-box-5-1">
        <div class="g-title">
          <h4>浏览排行</h4>
        </div>
        <div class="list-group">
		<s:iterator value="#rank_life" var="val" status="sta">
          <div class="list-group-item notice">
            <span class="badge pull-left"><s:property value="#sta.index+1" /></span>
            <a href="<%=basePath%>life/${val.id }.html" target="_blank">${val.title }</a>
          </div>
         </s:iterator> 

      </div>
  </div>

  <div class="g-box-5-2">
        <div class="g-title">
          <h4>最近更新</h4>
        </div>
        <div class="list-group">
		<s:iterator value="#new_life" var="val" status="sta">
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

  <div class="visible-md-block visible-lg-block col-md-2 col-xs-2"></div> 
</div>

<jsp:include page="/includes/foot.jsp" />
</html>