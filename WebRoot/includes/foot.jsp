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
<div class="g-bottom">
  <p class="text-center"><small>本站纯属免费图片欣赏网站,所有图片均收集于互联网,如有侵犯版权请来信告知,我们将立即更正。</small></p>
  <p class="text-center"><small>© 2017 (<a href="www.tutiaoba.com">www.tutiaoba.com</a>) 图条吧 版权所有 <a href="http://www.miitbeian.gov.cn/">豫ICP备17017459号</a></small></p>
</div>

</div>
</body>
<script src="<%=basePath%>js/jquery.min.js"></script>
<script src="<%=basePath%>js/bootstrap.min.js"></script>
<script src="<%=basePath%>js/common.js"></script>