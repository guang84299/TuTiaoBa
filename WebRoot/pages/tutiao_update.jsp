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
  <link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
  <link href="<%=basePath%>css/summernote.css" rel="stylesheet">
  <style type="text/css">
  .fengmian
  {
  	width:70px;
  	height:40px;
  	padding:0px;
  	cursor:hand;
  	text-align:center
  }
  .fengmian span
  {
  	line-height:40px;
  }
  .fengmian-sel-bg
  {
  	position:absolute;
  	width:100%;
  	height:100%;
  	background:rgba(0,0,0,0.6);
  	top:0%; left:0%;
  	display:none;
  }
  .fengmian-sel
  {
  	width:50%;
  	height:60%;
  	position:absolute;
  	top:10%; left:25%;
  }
  .fengmian-sel img
  {
  	cursor:hand;
  }
  .warning
  {
  	display: none;
  }
  </style>
 </head>
 <body>
  <div class="container-fluid">
   <h5 class="col-sm-offset-2">后台管理页</h5>
  
   <hr/>
   
   <div class="row">
   
   <div class="col-sm-2">
   </div>
   
    <div class="col-sm-20">
     <div class="row">
      <div class="form-horizontal">
  		<div class="form-group">
        <label class="control-label col-sm-1">标题</label>
        <div class="col-sm-7">
         <input type="text" name="title" value="${article.title }" title="${article.id }" class="form-control">
         <p class="bg-warning warning" id="title-warn">标题不能为空！</p>
        </div>
        <label class="control-label col-sm-1">类型</label>
        <div class="col-sm-2">
         <input type="text" name="type" value="${article.type }" class="form-control">
         <p class="bg-warning warning" id="type-warn">类型不能为空！</p>
        </div>
        <label class="control-label col-sm-2">关键词</label>
        <div class="col-sm-5">
         <input type="text" name="keywords" value="${article.keywords }" title="" class="form-control">
         <p class="bg-warning warning" id="keywords-warn">关键词不能为空！</p>
        </div>
        
        <label class="control-label col-sm-1">标签</label>
        <div class="col-sm-3">
		    <select name="tag" value="" class="form-control">
		    <s:iterator value="#tags" var="val" status="sta">
		      <option <s:if test="#article.tag.id == #val.id">selected="selected"</s:if>>${val.name }</option>
		     </s:iterator>
		    </select>
         <p class="bg-warning warning" id="tag-warn">标签不能为空！</p>
        </div>
        
        <div class="col-sm-2">
	        <div class="well well-lg fengmian" data-headPath="${article.headPath }"><span class="glyphicon glyphicon-plus">封面</span></div>
	       	<p class="bg-warning warning" id="headPath-warn">请选择一个封面！</p>  
       	</div>
        
        <label class="control-label col-sm-2">阅读量</label>
        <div class="col-sm-2">
         <input type="text" name="showNum" value="${article.showNum }" class="form-control">
        </div>
        
        
       	
        <div class="col-sm-3">
		     <button class="btn btn-warning save">保存</button>
		     <p class="bg-warning warning" id="btn-tishi"></p> 
        </div>
        
       </div>
       </div>
  	  
  	  <div id="summernote">${article.content }</div>  
	      
     </div>
    </div>
    
    <div class="col-sm-2">
   </div>
    
   </div>
  </div>
  
  <div class="fengmian-sel-bg">
  		<div class="well fengmian-sel">
  
  		</div>
  </div>
  
  <script src="<%=basePath%>js/jquery.min.js"></script>
  <script src="<%=basePath%>js/bootstrap.min.js"></script>
  <script src="<%=basePath%>js/summernote.min.js"></script>
  <script src="<%=basePath%>js/lang/summernote-zh-CN.min.js"></script>

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
		 height:500,
        minHeight: 400,             
        maxHeight: 800,        
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
	
  	//为图片添加a标签和响应式
  	var imgs = $("img",code);
  	for(var i=0;i< imgs.length;i++)
  	{
  		var img = $(imgs[i]);
  		img.removeClass("center-block");
  		img.addClass("center-block");
  		img.removeClass("img-responsive");
  		img.addClass("img-responsive");
  	}  	
  	$('#summernote').summernote('code',code); 
  	var summary = code.text();
  	if(summary.length > 150)
  		summary = summary.substr(0,150);
  	
  	var articles = {};
  	articles.id = $("[name='title']")[0].title;
    articles.type = $("[name='type']").val();
	articles.title = $("[name='title']").val();
	articles.content = $('#summernote').summernote('code');
	articles.summary = summary;
	articles.tag = $("[name='tag']").val();
	articles.keywords = $("[name='keywords']").val();
	articles.showNum = $("[name='showNum']").val();; 
	articles.headPath = $("#headPath").attr("src");
	
	if(articles.title == "" || articles.title == null || articles.title == undefined)
	{
		$("#title-warn").show();
		$("[name='title']").focus();
		return;
	}
	if(articles.type == "" || articles.type == null || articles.type == undefined)
	{
		$("#type-warn").show();
		$("[name='type']").focus();
		return;
	}
	if(articles.keywords == "" || articles.keywords == null || articles.keywords == undefined)
	{
		$("#keywords-warn").show();
		$("[name='keywords']").focus();
		return;
	}
	if(articles.headPath == "" || articles.headPath == null || articles.headPath == undefined)
	{
		$("#headPath-warn").show();
		return;
	}
	
    var data = JSON.stringify(articles);
    $.ajax({
			type: "post",
			data: {articles: data},
			url: baseUrl + "article_updateArticle"
			}).done(function(res) {
				if(res == 'true')
				{
					$("#btn-tishi").text("保存成功！");
					$("#btn-tishi").show();
					setTimeout(function(){
						$("#btn-tishi").hide();
					},2000);
				}
				else
				{
					$("#btn-tishi").text("保存失败！");
					$("#btn-tishi").show();
				}
	});
  });
  
  $("[name='title']").blur(function(){
  	$("#title-warn").hide();
  });
  $("[name='type']").blur(function(){
  	$("#type-warn").hide();
  });
 $("[name='keywords']").blur(function(){
  	$("#keywords-warn").hide();
  });


  
  var headPath = $(".fengmian").attr("data-headPath");
  if(headPath != "" && headPath != null && headPath != undefined)
  {
  		$(".fengmian").html("");
  		var s = '<img id="headPath" width=50 height=50 src="'+ headPath +'">'
  		$(".fengmian").append(s);
  }
  
  $(".fengmian").click(function(){
  	$(".fengmian-sel-bg").show();
  	$(".fengmian-sel").html("");
  	var str = $('#summernote').summernote('code');
  	var code = $(str);
  	var imgs = $("img",code);
  	for(var i=0;i< imgs.length;i++)
  	{
  		var img = $(imgs[i]);
  		var s = '<img width=50 height=50 style="margin:10px;" src="'+ img.attr("src") +'">'
  		$(".fengmian-sel").append(s);
  	}
  	$(".fengmian-sel img").unbind("click");
  	$(".fengmian-sel img").click(function(){
  		$(".fengmian span").hide();
  		$(".fengmian").html("");
  		var s = '<img id="headPath" width=50 height=50 src="'+ $(this).attr("src") +'">'
  		$(".fengmian").append(s);
  		$("#headPath-warn").hide();
  	});
  });
  
  $(".fengmian-sel-bg").click(function(){
  	$(this).hide();
  });
  
  
});  
//上传图片
function sendFile(file) {  
    var data = new FormData();  
    data.append("pic", file);
   
    $.ajax({  
        data: data,  
        type: "POST",  
        url: baseUrl+'article_upload',
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