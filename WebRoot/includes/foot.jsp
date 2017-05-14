<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	isELIgnored="false"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + path + "/";
	int port = request.getServerPort();
	if(port == 8080)
	{
		basePath = request.getScheme() + "://"
			+ request.getServerName() + ":"+ port + path + "/";
	}
%>
</div>
</div>
</body>
<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="<%=basePath%>scripts/masonry.pkgd.min.js"></script>
<script src="<%=basePath%>scripts/imagesloaded.pkgd.min.js"></script>
<script src="<%=basePath%>scripts/jquery.particleground.min.js"></script>
<script src="<%=basePath%>scripts/imgh.js"></script>
</html>