<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	isELIgnored="false"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<jsp:include page="/includes/head.jsp" />
	<div class="error-logo-wrap">
    <img src="<%=basePath%>images/logo.png" href="<%=basePath%>" class="img-responsive">
  </div>
  <div class="well error-content">
     <div><h3 >404错误 </h3></div> 
	<h3 >哎呀！图条君找不到您想要的界面！</h3>
 </div>
  <jsp:include page="/includes/foot.jsp" /> 
<script >
var baseUrl =  window.location.protocol + "//" + window.location.host + "/";
		if(baseUrl.indexOf("8080") == -1)
			baseUrl = "www.tutiaoba.com/"
  $(function(){

      $('#particles').particleground({
    dotColor: '#faeaea',
    lineColor: '#faeaea'
    });

	 $(".error-logo-wrap").click(function(){
		location.href = baseUrl;
	 });

  });
</script>