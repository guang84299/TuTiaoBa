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
  <div class="well error-content">
     <div><h3 >404错误 </h3></div> 
	<h3 >哎呀！图条君找不到您想要的界面！</h3>
 </div>
  <jsp:include page="/includes/foot.jsp" /> 
 </html>