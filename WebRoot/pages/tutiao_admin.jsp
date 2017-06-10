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
   
   <div class="pull-right" style="margin-top:-40px;">
        <button class="btn btn-primary add_btn">添加</button>
        <button class="btn btn-primary add_btn2">添加2</button>
    </div>
   <hr/>
   
   
   <table class="table table-hover">
  <thead>
    <tr>
      <th>标题</th>
      <th>作者</th>
      <th>浏览量</th>
      <th>评论量</th>
      <th>时间</th>
      <th>操作</th>
    </tr>
  </thead>
  <tbody>
  <s:iterator value="#list" var="val" status="sta">
    <tr>
      <td>${val.title }</td>
      <td>${val.author }</td>
      <td>${val.showNum }</td>
      <td>${val.commentNum }</td>
      <td><s:date name="#val.cdate" format="yyyy-MM-dd HH:mm:ss" /></td>
      <td>
      	<button class="btn btn-primary btn-xs pinglun_btn" title="${val.id }">评论管理</button>
        <button class="btn btn-primary btn-xs update_btn" title="${val.id }">更改</button>
        <button class="btn btn-primary btn-xs update_btn2" title="${val.id }">更改2</button>
        <button class="btn btn-primary btn-xs delete_btn" title="${val.id }">删除</button>
      </td>
    </tr>
	</s:iterator>
    
    
  </tbody>
</table>
<hr/>
<div class="text-center">
  <p id="ye1" align="center">
    <a href="" id="a_first" class="btn btn-small btn-success" title="First Page">&laquo; 首页</a>
    <a href="" id="a_pre" class="btn btn-small btn-success" title="Previous Page">&laquo;上一页</a>
    <a href="" id="a_curr" class="btn btn-small btn-warning ye1" title="${page}">${page}</a>
    <a href="" id="a_next" class="btn btn-small btn-success" title="Next Page">下一页 &raquo;</a>
    <a href="" id="a_end" class="btn btn-small btn-success" title="Last Page">尾页&raquo;</a>
    <a id="a_num" class="btn btn-small btn-warning yel" title="${num}">共${num} 页</a>
  </p>
</div>

   
   
  </div>
  <script src="<%=basePath%>scripts/jquery.min.js"></script>
  <script src="<%=basePath%>scripts/bootstrap.min.js"></script>
  <script src="<%=basePath%>scripts/admin.js"></script>

</body></html>