/**
 * Created by Guang on 2017/4/22.
 */
var baseUrl =  window.location.protocol + "//" + window.location.host + "/";
//js时间格式化;
Date.prototype.format = function(format) {
    var o = {
            "M+" : this.getMonth() + 1, //month
            "d+" : this.getDate(), //day
            "h+" : this.getHours(), //hour
            "m+" : this.getMinutes(), //minute
            "s+" : this.getSeconds(), //second
            "q+" : Math.floor((this.getMonth()+3)/3), //quarter
            "S"  : this.getMilliseconds() //millisecond
    }
    if (/(y+)/.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        }
    for ( var k in o) if (new RegExp("(" + k + ")").test(format)) {
        format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]: ("00" + o[k]).substr(("" + o[k]).length));
    }
        return format;
}
$(function() {
	
	function getDate(strDate) {
        var date = new Date(strDate.time);
        return date.format('yyyy-MM-dd hh:mm:ss');

    }
	$('.masonry').masonry({
		itemSelector: '.item',
		transitionDuration: '0.0s',
		stagger: 0
	});
	
	var tuijian_next_index=1;
	var getShowTuiJian = function()
	{
		$('#tuijian_data .thumbnail img').each(function(){
		     var src = $(this).attr("src");
		     var img = $(this);
		     imgReady(src, function () {
					var p = this.width/this.height;
					var sc = 1.5-p+1;
					img.height(img.height()*sc);	
				});
		  });	
	};
//	getShowTuiJian();
	
	
	var getShowXiangGuan = function()
	{
		$('#about_row .thumbnail img').each(function(){
		     var src = $(this).attr("src");
		     var img = $(this);
		     imgReady(src, function () {
					var p = this.width/this.height;
					var sc = 1.5-p+1;
					img.height(img.height()*sc);	
				});
		  });
	};
//	getShowXiangGuan();
	
	//窗口变化
	var winReSize = function()
	{
		var w = $("#tutiao_show").width()-6;
		var h = w*(450.0/750.0);
		$("#tutiao_show p").width(w);
		$("#tutiao_show p").height(h);
		$("#tutiao_show img").height(h);
		
		var roww = $("#tuijian_data .item:eq(0)").width()-8;
		var rowh = roww*(150.0/250.0);
		$(".tuijian_div_wrap").width(roww);
		$(".tuijian_div_wrap").height(rowh);
		$(".tuijian_div_wrap img").height(rowh);
		
		var roww2 = $("#about_row .item:eq(0)").width()-8;
		var rowh2 = roww2*(150.0/250.0);
		$(".about_wrap").width(roww2);
		$(".about_wrap").height(rowh2);
		$(".about_wrap img").height(rowh2);		
		
	}
	$(window).resize(function() {
			winReSize();
		});
	//显示界面
	var tutiao_show_num = 0;
	var tutiao_show_index = 0;
	var commentNum = 0;//评论数量
	var updateShow = function()
	{
		tutiao_show_num = $("#tutiao_show .thumbnail").length;
		tutiao_show_index = 0;
		$("#tutiao_show .thumbnail").hide();
		$(".tutiao_show_des").hide();
		$("#tutiao_show .thumbnail:eq(0)").show();
		$(".tutiao_show_des:eq(0)").show();	
		
		winReSize();
	}
	var updatePingLun = function(comments)
	{
		var s = '';
		for(var i=0;i<comments.length;i++)
		{
			var comment = comments[i];
			
			s += '<li class="list-group-item">' +
	    			'<p class="text-left"><a>'+comment.userName+'</a> <small class="text-muted">'+comment.time+'</small></p>'+
	    			'<p class="text-left">'+comment.content+'</p>'+
	    			'<p class="pull-right text-muted tutiao-btn-zan" data-id="'+comment.id+'"><span style="margin-right:5px;">'+comment.support+'</span><span class="glyphicon glyphicon-thumbs-up"></span></p><br>'+
					'</li>';
		}
		$("#tutiao-pinglun-ul").append(s);
		$("#tutiao_pinglun_num").text(commentNum);
		if(commentNum == $("#tutiao-pinglun-ul li").length)
		{
			$("#tutiao-morepinglun").hide();
		}
		$(".tutiao-btn-zan").unbind("click");
		$(".tutiao-btn-zan").click(function(){
			var datas = {};
			datas.id = $(this).attr("data-id");
			var t = $(this);
			$.ajax({
				type: "post",
				data : datas,
				url: baseUrl + "comment_support"
			}).done(function(result) {
				if(result == 'false')
				{
					alert("点赞失败！");
				}
				else
				{
					t.find("span:eq(0)").text(result);
				}
				
			});
		});
	};
	var addPingLun = function(comment)
	{
		var s = '<li class="list-group-item">' +
		'<p class="text-left"><a>'+comment.userName+'</a> <small class="text-muted">'+comment.time+'</small></p>'+
		'<p class="text-left">'+comment.content+'</p>'+
		'<p class="pull-right text-muted tutiao-btn-zan" data-id="'+ comment.id +'"><span style="margin-right:5px;">'+comment.support+'</span><span class="glyphicon glyphicon-thumbs-up"></span></p><br>'+
		'</li>';
		commentNum+=1;
		$("#tutiao-pinglun-ul").prepend(s);
		$("#tutiao_pinglun_num").text(commentNum);
		$(".tutiao-btn-zan").unbind("click");
		$(".tutiao-btn-zan").click(function(){
			var datas = {};
			datas.id = $(this).attr("data-id");
			var t = $(this);
			$.ajax({
				type: "post",
				data : datas,
				url: baseUrl + "comment_support"
			}).done(function(result) {
				if(result == 'false')
				{
					alert("点赞失败！");
				}
				else
				{
					t.find("span:eq(0)").text(result);
				}
				
			});
		});
	};
		
	
	var picLeft = function()
	{
		if(tutiao_show_num > 0)
		{
			tutiao_show_index--;
			if(tutiao_show_index < 0)
				tutiao_show_index = tutiao_show_num-1;
			$("#tutiao_show .thumbnail").hide();
			$(".tutiao_show_des").hide();
			$("#tutiao_show .thumbnail:eq("+tutiao_show_index+")").show();
			$(".tutiao_show_des:eq("+tutiao_show_index+")").show();			
		}
	};
	
	var picRight = function()
	{
		if(tutiao_show_num>0 && tutiao_show_index<tutiao_show_num)
		{
			tutiao_show_index++;
			if(tutiao_show_index>=tutiao_show_num)
			{
				$("#tutiao_show_row").hide();
				$("#tuijian_row").show();
//				$('.masonry').imagesLoaded(function() {
//					$('.masonry').masonry({
//						itemSelector: '.item'
//					});
//				});
				return;
			}
			$("#tutiao_show .thumbnail").hide();
			$(".tutiao_show_des").hide();
			$("#tutiao_show .thumbnail:eq("+tutiao_show_index+")").show();
			$(".tutiao_show_des:eq("+tutiao_show_index+")").show();		
		}
	};
	
	$('#tutiao_show').hover(function(){
	},function(){
		$(this).removeClass("tutiao-cursor-right"); 
		$(this).removeClass("tutiao-cursor-left"); 
	});
	var posLeft = true;
	$('#tutiao_show').mousemove(function(e) {  
		var xx=e.pageX-$('#tutiao_show').offset().left;
		var width = $(this).width();
		if(xx>width/2)
		{
			$(this).removeClass("tutiao-cursor-left"); 
			$(this).addClass("tutiao-cursor-right");
			posLeft = false;
		}
		else
		{
			$(this).removeClass("tutiao-cursor-right"); 
			$(this).addClass("tutiao-cursor-left");
			posLeft = true;
		}
	}); 
	$("#tutiao_show p").mousedown(function(e){
		if(e.which != 1)
			return;
		 if(posLeft)
		 {
			 picLeft();
		 }
		 else
		 {
			 picRight();
		 }
	});
	
	$('#tutiao-pic-left').click(function(event) {
		picLeft();
	})
	$('#tutiao-pic-right').click(function(event) {
		 picRight();
	})
	
	$("#img_large_show").hide();
	$("#img_large_show").click(function(){
		$("#img_large_show").hide();
		$(".tutiao-content").show();
	});
	$('#pic_lager').click(function(event) {
		var url = $("#tutiao_show img:visible:eq(0)").attr("src");
		$(".tutiao-content").hide();
		$("#img_large_show").show();
		$("#img_large_show img:first").attr("src",url);
	})
	
	$("#tuijian_refresh").click(function(){
		$("#tuijian_row").hide();
		$("#tutiao_show_row").show();
		updateShow();
	});
	$("#tuijian_next").click(function(){
		$("#tuijian_row").hide();
		location.href = $("#tuijian_data .item:eq(0)").attr("data-tid");
	});
	
	updateShow();
	$("#tuijian_row").hide();
	
	//搜索
	var loading = 0;
	var curr_type = 3;
	var tu_index = 0;
	var getTuTiao = function(type,index)
	{
		loading = 1;
		
		var pdata = {};
		pdata.index = index;
		pdata.type = type;
		
		var link = baseUrl + "tutiao_getTuTiaos";
		if(type == 3)
		{ 
			var val = $(".glyphicon-search:first").val();
			pdata.val = encodeURI(val);
			link = baseUrl + "tutiao_getSearch";
		}
		
		$.ajax({
			type: "post",
			url: link,
			data: pdata
		}).done(function(results) {
			var objs = eval('(' + results + ')'); 
			var s ='';
			for(var i=0;i<objs.length;i++)
			{
				var obj = objs[i];
				
				s += '<div class="col-sm-6 col-md-3 item">' 
					+ '<div onclick=window.open("' + baseUrl+ obj.tid + '") class="thumbnail">'
					+ '<div style="overflow:hidden;"><img class="img-thumbnail" style="padding:0px;" src="' + baseUrl+obj.units[0].picPath +'" alt=" '+ obj.title + ' "></div>'
					+ '<span class="pic-num pull-right">' + obj.units.length + '图</span>'
					+ '<div class="caption"><b>' + obj.title + '</b>'
					+ '<div class="tutiao-fontBottom">'+ obj.author + ' ⋅ ' + obj.showNum + '浏览 ⋅ '+ getDate(obj.cdate)
					+ '</div></div></div></div>';
				
				imgReady(baseUrl+obj.units[0].picPath, function () {
					$('.masonry').masonry();
				});
			}
			
			var items = $(s);
			$("#search_row").append(items).masonry( 'appended',items ).masonry();
//			$('.masonry').imagesLoaded(function() {
//				$('.masonry').masonry();
//			});
			
			loading = 0;
			tu_index += objs.length;
			if(objs.length == 0)
			{
				tu_index = 0;
			}
				
		})		
	};
	
	$("#nav_search").hide();
	$("#div_search").hide();
	var search = function()
	{
		$('#nav_search').show();
		$('#div_search').show();
		$("#nav_kan").hide();
		$("#tutiao_show_row").hide();
		$("#tuijian_row").hide();
		$("#div_xiangguan").hide();
		$("#div_pinglun").hide();
		$("#search_row").html("");
		$('.masonry').masonry('reloadItems');
		
		tu_index = 0;
		getTuTiao(3,tu_index);
	}
	$(".index-user-nav .glyphicon-search").keydown(function() {
        if (event.keyCode == "13") {//keyCode=13是回车键
        	search();
        }
    });
	$(".index-user-nav .glyphicon-search:last").click(function(){
		search();
	});
	
	$('.navbar-brand').click(function(){
		location.href = baseUrl;
	});
	
	$("#btn-login").click(function(){
		location.href = baseUrl + "user_login";
	});
	
	$('#particles').particleground({
        dotColor: '#faeaea',
        lineColor: '#faeaea'
      });
	
	$("#tutiao-btn-pinglun").click(function(){
		if(document.getElementById("btn-login") != null)
		{
			location.href = baseUrl + "user_login";
			return;
		}
		var datas = {};
		datas.tid = $("#tutiao_show").attr("data-tid");
		datas.content = $("#tutiao-pinglun-content").val();
		if(datas.content == null || datas.content.length < 1|| datas.content.length > 500)
		{
			alert("评论内容不能为空且不能超过500字！");
			return;
		}
		$.ajax({
			type: "post",
			data : datas,
			url: baseUrl + "comment_addComment"
		}).done(function(result) {
			if(result == 'false')
			{
				alert("评论失败！");
			}
			else
			{
				var comment = eval('(' + result + ')'); 
				addPingLun(comment);
			}
			
		});
	});
	
	$("#tutiao-morepinglun").click(function(){
		var datas = {};
		datas.tid = $("#tutiao_show").attr("data-tid");
		datas.index = $("#tutiao-pinglun-ul li").length;
		$.ajax({
			type: "post",
			data : datas,
			url: baseUrl + "comment_getComment"
		}).done(function(result) {
			if(result == 'false')
			{
				alert("获取评论失败！");
			}
			else
			{
				var comments = eval('(' + result + ')'); 
				updatePingLun(comments);
			}
			
		});
	});
	commentNum = parseInt($("#tutiao_pinglun_num").text());
	updatePingLun([]);
	$(".tutiao-btn-zan").click(function(){
		var datas = {};
		datas.id = $(this).attr("data-id");
		var t = $(this);
		$.ajax({
			type: "post",
			data : datas,
			url: baseUrl + "comment_support"
		}).done(function(result) {
			if(result == 'false')
			{
				alert("点赞失败！");
			}
			else
			{
				t.find("span:eq(0)").text(result);
			}
			
		});
	});
});
