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
     
    <div class="pull-right" style="margin-top:-30px;margin-right:100px;width:50px;">
   		<input type="text" class="form-control" id="auto-caiji-type" value="1">
   </div>
   <div class="pull-right" style="margin-top:-30px;margin-right:10px">
   		<input type="text" class="form-control" id="auto-caiji" placeholder="自动采集">
   </div>
   
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
         <input type="text" name="title" value="" title="" class="form-control">
         <p class="bg-warning warning" id="title-warn">标题不能为空！</p>
        </div>
        
        <label class="control-label col-sm-1">类型</label>
        <div class="col-sm-2">
         <input type="text" name="type" value="" title="" class="form-control">
         <p class="bg-warning warning" id="type-warn">类型不能为空！</p>
        </div>
        
        <label class="control-label col-sm-2">关键词</label>
        <div class="col-sm-5">
         <input type="text" name="keywords" value="" title="" class="form-control">
         <p class="bg-warning warning" id="keywords-warn">关键词不能为空！</p>
        </div>
        
        <label class="control-label col-sm-1">标签</label>
        <div class="col-sm-3">
		    <select name="tag" value="" class="form-control">
		    <s:iterator value="#tags" var="val" status="sta">
		      <option>${val.name }</option>
		     </s:iterator>
		    </select>
         <p class="bg-warning warning" id="tag-warn">标签不能为空！</p>
        </div>
        
        <div class="col-sm-2">
	        <div class="well well-lg fengmian"><span class="glyphicon glyphicon-plus">封面</span></div>
	       	<p class="bg-warning warning" id="headPath-warn">请选择一个封面！</p>  
       	</div>
       	 
	    
	    <label class="control-label col-sm-2">新增标签</label>
        <div class="col-sm-3">
         <input type="text" name="addTag" value="" title="" class="form-control">
         <p class="bg-warning warning" id="addTag-warn">添加成功！</p>
        </div>
        <div class="col-sm-2">
		     <button class="btn btn-warning addTag">添加</button>
	    </div>
        
        <label class="control-label col-sm-2">作者</label>
        <div class="col-sm-3">
         <input type="text" name="author" value="程序员.春光" class="form-control">
         <p class="bg-warning warning" id="author-warn">作者不能为空！</p>
        </div>
        
        <div class="col-sm-4 pull-right">
		     <button class="btn btn-warning save">保存</button>
		     <p class="bg-warning warning" id="btn-tishi"></p> 
	    </div>
	      
       </div>
       </div>
       
  	  
  	  <div id="summernote"></div>  
	      
   
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
  	var alt = $("[name='title']").val();
  	//得到所有图片
  	var imgs = $("img",code);
  	for(var i=0;i< imgs.length;i++)
  	{
  		var img = $(imgs[i]);
  		img.attr("alt",alt);
  		img.removeClass("center-block");
  		img.addClass("center-block");
  		img.removeClass("img-responsive");
  		img.addClass("img-responsive");
  	}  	
  	$('#summernote').summernote('code',code); 
  	var summary = code.text();
  	if(summary.length > 150)
  		summary = summary.substr(0,150);
  	summary = summary.replace(/<[^>]+>/g,"").replace(/\s/g,"");
  	
    var articles = {};
    articles.type = $("[name='type']").val();
	articles.title = $("[name='title']").val();
	articles.content = $('#summernote').summernote('code');
	articles.summary = summary;
	articles.tag = $("[name='tag']").val();
	articles.keywords = $("[name='keywords']").val();
	articles.showNum = 0; 
	articles.author = $("[name='author']").val();
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
	if(articles.author == "" || articles.author == null || articles.author == undefined)
	{
		$("#author-warn").show();
		$("[name='author']").focus();
		return;
	}
	/* if(articles.headPath == "" || articles.headPath == null || articles.headPath == undefined)
	{
		$("#headPath-warn").show();
		return;
	} */
	
    var data = JSON.stringify(articles);
    $.ajax({
			type: "post",
			data: {articles: data},
			url: baseUrl + "article_addArticle"
			}).done(function(res) {
				if(res == 'false')
				{
					$("#btn-tishi").text("保存失败！");
					$("#btn-tishi").show();
					setTimeout(function(){
						$("#btn-tishi").hide();
					},2000);
				}
				else
				{
					location.href = baseUrl + "article_update?id=" + res;
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
   $("[name='author']").blur(function(){
  	$("#author-warn").hide();
  });
  
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
  
  
  //添加标签
  $(".addTag").click(function(){
  		var name = $("[name='addTag']").val();
  		$.ajax({
				type: "post",
				data: {tagName: name},
				url: baseUrl + "article_addTag"
			}).done(function(results) {
				if(results == "true")
				{
					var str = '<option>'+name+'</option>';
					$("[name='tag']").append(str);
					$("#addTag-warn").text("添加成功！");
					$("#addTag-warn").show();
					setTimeout(function(){
						$("#addTag-warn").hide();
					},2000);
				}
				else
				{
					$("#addTag-warn").text("添加失败！");
					$("#addTag-warn").show();
					setTimeout(function(){
						$("#addTag-warn").hide();
					},2000);
				}
			});
  });
  
  //自动采集
   	$("#auto-caiji").keydown(function() {
       if (event.keyCode == "13") {//keyCode=13是回车键
       	var dchannel = 'jianshu';//jianshu
       	var durl = $("#auto-caiji").val();
       	var dtype = $("#auto-caiji-type").val();
       	if(durl == null || durl == '' || !durl)
       		return;
       		
       	if(durl.indexOf("jokeji") != -1)
       		dchannel = 'jokeji';
       	else if(durl.indexOf("www.mmonly.cc") != -1) 
       		dchannel = 'mmonly';
       	
       	$("#auto-caiji").attr("disabled","disabled");
       	$("#auto-caiji").val("信息采集中...");
       	
       	$.ajax({
			type: "post",
			data: {type: dtype,url: durl,channel: dchannel},
			url: baseUrl + "article_autoAddArticle"
		}).done(function(results) {
			location.href = baseUrl + "article_list";
		})
       }
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