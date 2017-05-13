<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	isELIgnored="false"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1,user-scalable=no">
  <title>后台管理页</title>
  <link rel="icon" type="image/png" href="favicon.png" sizes="32x32" />
  <link rel="bookmark" type="image/x-icon" href="favicon.ico" />
  <link rel="shortcut icon" type="image/x-icon" href="favicon.ico"/>
  <link href="<%=basePath%>styles/bootstrap.min.css" rel="stylesheet">
  <link href="<%=basePath%>styles/admin.css" rel="stylesheet">
 </head>
 <body>
  <div class="container-fluid">
   <h3 class="col-sm-offset-1">后台管理页</h3>
   
   <hr/>
   
   
   <table class="table table-hover">
  <thead>
    <tr>
      <th>内容</th>
      <th>点赞量</th>
      <th>时间</th>
      <th>操作</th>
    </tr>
  </thead>
  <tbody>
  <s:iterator value="#list" var="val" status="sta">
    <tr>
      <td>${val.content }</td>
      <td>${val.support }</td>
      <td><s:date name="#val.cdate" format="yyyy-MM-dd HH:mm:ss" /></td>
      <td>
        <button class="btn btn-primary btn-xs pinglun_delete_btn" title="${val.id }" data-tid="${tid }">删除</button>
      </td>
    </tr>
	</s:iterator>
    
    
  </tbody>
</table>
<hr/>
<div class="text-center">
  <p id="ye2" align="center" data-tid="${tid }">
    <a href="" id="a_first2" class="btn btn-small btn-success" title="First Page">&laquo; 首页</a>
    <a href="" id="a_pre2" class="btn btn-small btn-success" title="Previous Page">&laquo;上一页</a>
    <a href="" id="a_curr2" class="btn btn-small btn-warning ye1" title="${page}">${page}</a>
    <a href="" id="a_next2" class="btn btn-small btn-success" title="Next Page">下一页 &raquo;</a>
    <a href="" id="a_end2" class="btn btn-small btn-success" title="Last Page">尾页&raquo;</a>
    <a id="a_num2" class="btn btn-small btn-warning yel" title="${num}">共${num} 页</a>
  </p>
</div>

   
   
  </div>
  <script src="<%=basePath%>scripts/jquery.min.js"></script>
  <script src="<%=basePath%>scripts/bootstrap.min.js"></script>
  <script src="<%=basePath%>scripts/admin.js"></script>

</body></html>