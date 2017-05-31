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
   <div class="row">
    <div class="col-sm-6">
     <div class="row">
  
      <form class="form-horizontal">
       <div class="form-group">
        <label class="control-label col-sm-2 col-sm-offset-1">标题</label>
        <div class="col-sm-8">
         <input type="text" name="title" value="${requestScope.tuTiao.title }" title="${requestScope.tuTiao.id }" class="form-control">
         <p class="bg-warning warning"></p>
        </div>
        <label class="control-label col-sm-2 col-sm-offset-1">作者</label>
        <div class="col-sm-8">
         <input type="text" name="author" value="${requestScope.tuTiao.author }" class="form-control">
         <p class="bg-warning warning"></p>
        </div>
         <label class="control-label col-sm-2 col-sm-offset-1">阅读量</label>
        <div class="col-sm-8">
         <input type="text" name="showNum" value="${requestScope.tuTiao.showNum }" class="form-control">
         <p class="bg-warning warning"></p>
        </div>
       </div>

        <hr/>

       <div class="hotGames">
       
       <s:iterator value="#tuTiao.units" var="val" status="sta">
       
        <div class="game-group col-sm-offset-1">
         <div class="form-group">
          <label class="control-label col-sm-3 col-sm-offset-1">图<s:property value="#sta.index + 1" /></label>
          <div class="col-sm-5">
           <button class="btn btn-danger del-btn">删除</button>
          </div>
         </div>
         <div class="form-group">
          <label class="control-label col-sm-3 col-sm-offset-1">图片说明</label>
          <div class="col-sm-8">
           <textarea  name="tdescribe"  title="<s:property value="#val.id" />" class="form-control"><s:property value="#val.tdescribe" /></textarea>
          </div>
         </div>

         <div class="form-group">
            <label class="control-label col-sm-3 col-sm-offset-1">图片路径</label>
            <div class="col-sm-5">
            <input type="text" class="form-control gameIcon" name="picPath" value="<s:property value="#val.picPath" />" disabled="disabled"/>
            </div>
         </div>
         
         <div class="form-group">
          <label class="control-label col-sm-3 col-sm-offset-1">图片</label>
          <div class="col-sm-8">
           <img src="<%=basePath%><s:property value="#val.picPath" />" class="img-responsive" />
           <button class="btn btn-warning changeIcon" title="1">更改图片</button>
          </div>
          
          
         </div>
        </div>
        
        </s:iterator>
        
        <button class="btn btn-primary col-sm-offset-1 add_hot">新增图片</button>
       </div>

      </form>
      <button class="btn btn-warning col-sm-offset-1 sub_btn2">更改</button>
     </div>
    </div>
   </div>
  </div>
  <script src="<%=basePath%>scripts/jquery.min.js"></script>
  <script src="<%=basePath%>scripts/bootstrap.min.js"></script>
  <script src="<%=basePath%>scripts/admin.js"></script>
</body></html>