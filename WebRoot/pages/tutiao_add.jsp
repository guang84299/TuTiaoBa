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
   <div class="pull-right" style="margin-top:-40px;margin-right:100px">
   		<input type="text" class="form-control" id="auto-caiji" placeholder="自动采集">
   </div>
   <hr/>
   <div class="row">
    <div class="col-sm-6">
     <div class="row">
  
      <form class="form-horizontal">
       <div class="form-group">
        <label class="control-label col-sm-2 col-sm-offset-1">标题</label>
        <div class="col-sm-8">
         <input type="text" name="title" value="" class="form-control">
         <p class="bg-warning warning"></p>
        </div>
        <label class="control-label col-sm-2 col-sm-offset-1">作者</label>
        <div class="col-sm-8">
         <input type="text" name="author" value="图条吧" class="form-control">
        </div>
       </div>

        <hr/>

       <div class="hotGames">
        <div class="game-group col-sm-offset-1">
         <div class="form-group">
          <label class="control-label col-sm-3 col-sm-offset-1">图1</label>
          <div class="col-sm-5">
           <button class="btn btn-danger del-btn">删除</button>
          </div>
         </div>
         <div class="form-group">
          <label class="control-label col-sm-3 col-sm-offset-1">图片说明</label>
          <div class="col-sm-8">
           <textarea  name="tdescribe" value=""  title="0" class="form-control"></textarea>
          </div>
         </div>

         <div class="form-group">
            <label class="control-label col-sm-3 col-sm-offset-1">图片路径</label>
            <div class="col-sm-5">
            <input type="text" class="form-control gameIcon" name="picPath" disabled="disabled"/>
            </div>
         </div>
         
         <div class="form-group">
          <label class="control-label col-sm-3 col-sm-offset-1">图片</label>
          <div class="col-sm-5">
           <input type="file" name="fileUpload" value="" class="form-control fileUpload">
           <button class="btn btn-primary uploadBtn" >上传图片</button>
           <div class="spinner">
            <div class="spinner-container container1">
             <div class="circle1"></div>
             <div class="circle2"></div>
             <div class="circle3"></div>
             <div class="circle4"></div>
            </div>
            <div class="spinner-container container2">
             <div class="circle1"></div>
             <div class="circle2"></div>
             <div class="circle3"></div>
             <div class="circle4"></div>
            </div>
            <div class="spinner-container container3">
             <div class="circle1"></div>
             <div class="circle2"></div>
             <div class="circle3"></div>
             <div class="circle4"></div>
            </div>
           </div>
          </div>
         </div>
        </div>
        <button class="btn btn-primary col-sm-offset-1 add_hot">新增图片</button>
       </div>

      </form>
      <button class="btn btn-warning col-sm-offset-1 sub_btn">添加</button>
     </div>
    </div>
   </div>
  </div>
  <script src="<%=basePath%>scripts/jquery.min.js"></script>
  <script src="<%=basePath%>scripts/bootstrap.min.js"></script>
  <script src="<%=basePath%>scripts/admin.js"></script>

<!--
<script>
function watermark(str){
   var img = document.getElementById("img");
   var canvas=document.getElementById("cvs");
   canvas.width = img.width;
   canvas.height = img.height;
   var ctx=canvas.getContext("2d");
   ctx.drawImage(img,0,0);
   ctx.font="18px microsoft yahei";
   ctx.fillStyle = "rgba(255,255,255,1.0)";
   ctx.fillText(str,img.width-70,img.height-10);
}
</script>
<canvas id="cvs" width="1000" height="500" >
Your browser does not support the HTML5 canvas tag.
</canvas>
<image id="img" src="./admin_index_files/zb13.jpg" > </image>
<button onclick="watermark('图条吧')">add watermark</button>
 -->
</body></html>