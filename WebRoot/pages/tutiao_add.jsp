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
  	width:50px;
  	height:50px;
  	padding:0px;
  	cursor:hand;
  	text-align:center
  }
  .fengmian span
  {
  	line-height:50px;
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
     <div class="pull-right" style="margin-top:-30px;margin-right:100px">
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
        <label class="control-label col-sm-2">标题</label>
        <div class="col-sm-6">
         <input type="text" name="title" value="" title="" class="form-control">
         <p class="bg-warning warning" id="title-warn">标题不能为空！</p>
        </div>
        
        <label class="control-label col-sm-2">类型</label>
        <div class="col-sm-2">
         <input type="text" name="type" value="" title="" class="form-control">
         <p class="bg-warning warning" id="type-warn">类型不能为空！</p>
        </div>
        
        <label class="control-label col-sm-2">关键词</label>
        <div class="col-sm-2">
         <input type="text" name="keywords" value="" title="" class="form-control">
         <p class="bg-warning warning" id="keywords-warn">关键词不能为空！</p>
        </div>
        
        <div class="col-sm-2">
	        <div class="well well-lg fengmian"><span class="glyphicon glyphicon-plus">封面</span></div>
	       	<p class="bg-warning warning" id="headPath-warn">请选择一个封面！</p>  
       	</div>
        
        <div class="col-sm-3 pull-right">
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
		  //  ['fontstyle', ['bold', 'italic', 'underline', 'fontsize','color','fontname','clear','strikethrough', 'superscript', 'subscript']],
		 //   ['para', ['style','ul', 'ol', 'paragraph','height']],
		   // ['insert', ['link', 'picture','video','table','hr']],
		   // ['misc', ['fullscreen', 'codeview','undo','redo','help']]
		   ['insert', ['picture']],
		   ['misc', ['codeview']]
		  ],
		 height:500,
        minHeight: 400,             
        maxHeight: 500,        
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
  	  	
  	//得到所有图片
  	var imgs = $("img",code);
  	var picNum = imgs.length;
  	var units = [];
  	for(var i=0;i< imgs.length;i++)
  	{
  		var img = $(imgs[i]);
  		var src = img.attr("src");
  		units[i] = {"pic":src};
  	}  	
    var tutiaos = {};
	tutiaos.title = $("[name='title']").val();
	tutiaos.type = $("[name='type']").val();
	tutiaos.keywords = $("[name='keywords']").val();
	tutiaos.showNum = 0;
	tutiaos.headPath = $("#headPath").attr("src");
	tutiaos.picNum = picNum; 
	tutiaos.units = units;
	
	if(tutiaos.title == "" || tutiaos.title == null || tutiaos.title == undefined)
	{
		$("#title-warn").show();
		$("[name='title']").focus();
		return;
	}
	if(tutiaos.type == "" || tutiaos.type == null || tutiaos.type == undefined)
	{
		$("#type-warn").show();
		$("[name='type']").focus();
		return;
	}
	if(tutiaos.keywords == "" || tutiaos.keywords == null || tutiaos.keywords == undefined)
	{
		$("#keywords-warn").show();
		$("[name='keywords']").focus();
		return;
	}
	if(tutiaos.headPath == "" || tutiaos.headPath == null || tutiaos.headPath == undefined)
	{
		$("#headPath-warn").show();
		return;
	}
	
    var data = JSON.stringify(tutiaos);
    $.ajax({
			type: "post",
			data: {tutiao: data},
			url: baseUrl + "tutiao_addTuTiao"
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
					location.href = baseUrl + "tutiao_update?id=" + res;
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
  
  
  $("#auto-caiji").keydown(function() {
        if (event.keyCode == "13") {//keyCode=13是回车键
        	var dtype = 1;//mm131
        	var durl = $("#auto-caiji").val();
        	if(durl == null || durl == '' || !durl)
        		return;
        	
        	if(durl.indexOf("chemo") != -1)
        		dtype = 2;//mm131 chemo
        	
        	if(durl.indexOf("eastday.com/pictures") != -1)
        		dtype = 3;//头条新闻 图片
        	
        	if(durl.indexOf("eastday.com/mobile") != -1)
        		dtype = 4;//头条新闻 图文
        	
        	$("#auto-caiji").attr("disabled","disabled");
        	$("#auto-caiji").val("信息采集中...");
        	
        	$.ajax({
				type: "post",
				data: {type: dtype,url: durl},
				url: baseUrl + "tutiao_autoAddTuTiao"
			}).done(function(results) {
				location.href = baseUrl + "tutiao_list";
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
        url: baseUrl+'tutiao_upload',
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