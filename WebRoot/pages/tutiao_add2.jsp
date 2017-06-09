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
  <link href="<%=basePath%>styles/summernote.css" rel="stylesheet">
 </head>
 <body>
  <div class="container-fluid">
   <h5 class="col-sm-offset-1">后台管理页</h5>
   <div class="pull-right" style="margin-top:-30px;margin-right:100px">
   		<input type="text" class="form-control" id="auto-caiji" placeholder="自动采集">
   </div>
   <hr/>
   
   <div class="row">
   
   <div class="col-sm-1">
   </div>
   
    <div class="col-sm-10">
     <div class="row">
      <form class="form-horizontal">
  		<div class="form-group">
        <label class="control-label col-sm-1">标题</label>
        <div class="col-sm-5">
         <input type="text" name="title" value="" class="form-control">
         <p class="bg-warning warning"></p>
        </div>
        <label class="control-label col-sm-1">作者</label>
        <div class="col-sm-3">
         <input type="text" name="author" value="图条吧" class="form-control">
        </div>
       </div>
       </form>
  	  
  	  <div id="summernote"></div>  
     
      
       <div class="form-horizontal">
  		<div class="form-group">
        <label class="control-label col-sm-1">描述</label>
        <div class="col-sm-7">
         <textarea name="description" value="" rows="1" class="form-control"></textarea>
         <p class="bg-warning warning"></p>
        </div>
        <div class="col-sm-4">
         <div class="btn-group pull-right">
		     <button class="btn btn-warning save">保存</button>
		     <button class="btn btn-warning">添加</button>
	      </div>
        </div>
       </div>
       </div>
   
     </div>
    </div>
    
    <div class="col-sm-1">
   </div>
    
   </div>
  </div>
  
  <script src="<%=basePath%>scripts/jquery.min.js"></script>
  <script src="<%=basePath%>scripts/bootstrap.min.js"></script>
  <script src="<%=basePath%>scripts/summernote.min.js"></script>
  <script src="<%=basePath%>scripts/lang/summernote-zh-CN.min.js"></script>

<script type="text/javascript">  
var baseUrl =  window.location.protocol + "//" + window.location.host + "/";
//加载编辑器  
$(document).ready(function() {  
    $('#summernote').summernote({  
    	toolbar: [
		    // [groupName, [list of button]]
		    ['fontstyle', ['bold', 'italic', 'underline', 'fontsize','color','fontname','clear','strikethrough', 'superscript', 'subscript']],
		    ['para', ['style','ul', 'ol', 'paragraph','height']],
		    ['insert', ['link', 'picture','video','table','hr']],
		    ['misc', ['fullscreen', 'codeview','undo','redo','help']]
		  ],
		  
        minHeight: 300,             
        maxHeight: 400,        
        focus: true,   
        lang:'zh-CN',
        // 重写图片上传  
    	callbacks: {  
            onImageUpload: function(files) 
            { //the onImageUpload API  
                sendFile(files[0]);  
        	}  
        }
  });  
  
  $(".save").click(function(){
  	var str = $('#summernote').summernote('code');
  	var code = $(str);
  	//清除带外链的图片
  	var imgs = $("img",code);
  	for(var i=0;i< imgs.length;i++)
  	{
  		var img = $(imgs[i]);
  		var src = img.attr("src");
  		if(src.indexOf(baseUrl) == -1)
  		{
  			var p = baseUrl+"images/water.png";
  			img.after('<img src="'+p+'">')
  			img.remove();
  		}
  	}
  	//清除带外链a标签
  	var as = $("[href]",code);
  	for(var i=0;i< as.length;i++)
  	{
  		var a = $(as[i]);
  		var href = a.attr("href");
  		if(href.indexOf(baseUrl) == -1 && href.length > 5)
  		{
  			a.attr("href","#");
  		}
  	}
  	//清除带背景图片和外链的标签
  	var bs = $("[style]",code);
  	for(var i=0;i< bs.length;i++)
  	{
  		var b = $(bs[i]);
  		var style = b.attr("style");
  		if(style.indexOf("image") != -1 || style.indexOf("url") != -1)
  		{
  			b.attr("style","");
  		}
  	}
  	//为图片添加a标签
  	var imgs = $("img",code);
  	for(var i=0;i< imgs.length;i++)
  	{
  		var img = $(imgs[i]);
  		var a = img.parent();
  		if(a.get(0).tagName.toLocaleLowerCase() == 'a')
  		{
  			if(href.indexOf(baseUrl) == -1)
  				a.attr("href",img.attr("src"));
  		}
  		else
  		{
  			img.wrap('<a href="'+img.attr("src")+'"></a>');
  		}
  	}
  	$('#summernote').summernote('code',code);
  });
});  
//上传图片
function sendFile(file) {  
    var data = new FormData();  
    data.append("pic", file);
   
    $.ajax({  
        data: data,  
        type: "POST",  
        url: baseUrl+'tutiao_upload?title=111',
        cache: false,  
        contentType: false,  
        processData: false,  
        success: function(url) {  
              $("#summernote").summernote('insertImage', baseUrl+url); // the insertImage API  
        }  
    });  
}  

  
</script>  
</body></html>