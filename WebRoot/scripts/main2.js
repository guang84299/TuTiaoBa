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
	
	function judgmentClient(){
	    var UserClient = navigator.userAgent.toLowerCase();
	    var IsIPad = UserClient.match(/ipad/i) == "ipad";
	    var IsIphoneOs = UserClient.match(/iphone os/i) == "iphone os";
	    var IsMidp = UserClient.match(/midp/i) == "midp";
	    var IsUc7 = UserClient.match(/rv:1.2.3.4/i) == "rv:1.2.3.4";
	    var IsUc = UserClient.match(/ucweb/i) == "ucweb";
	    var IsAndroid = UserClient.match(/android/i) == "android";
	    var IsCE = UserClient.match(/windows ce/i) == "windows ce";
	    var IsWM = UserClient.match(/windows mobile/i) == "windows mobile";
	    if(IsIPad || IsIphoneOs || IsMidp || IsUc7 || IsUc || IsAndroid || IsCE || IsWM){
	        return true;
	    }else{
	    	return false;
	    }
	}
	
	var isMobile = judgmentClient();
	
	function getDate(strDate) {
        var date = new Date(strDate.time);
        return date.format('yyyy-MM-dd');

    }
	
	$('.masonry').masonry({
		itemSelector: '.item',
		transitionDuration: '0.0s',
		stagger: 0
	});

	
	//显示界面
	var commentNum = 0;//评论数量
	
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
	
	var addMobileHot = function(val)
	{
		var s = '<a href="'+baseUrl+val.tid+'" class="list-group-item">' +
			'<div class="list-group-item-heading" >' +
			'<div style="overflow:hidden;max-height:200px;">'+
			'<img class="img-responsive" style="padding:0px;width:100%;" src="'+baseUrl+val.units[0].picPath+ '" alt="'+val.units[0].tdescribe+'">'+
			'</div>'+
			'<span class="pic-num pull-right">'+val.units.length+'图</span>'+
			'</div>'+
			'<p class="list-group-item-text">'+val.title+'</p>'+
			'</a>';
		$("#div_moile_tuijian_con").append(s);
	}
		
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
			var objs = JSON.parse(results);
			var s ='';
			var col_num = "col-xs-3";
			if(isMobile)
				col_num = "col-xs-6";
			for(var i=0;i<objs.length;i++)
			{
				var obj = objs[i];
				
				s += '<div class="'+col_num+' item"  style="padding:1px;">' 
					+ '<a href="'+ baseUrl+ obj.tid +'" style="text-decoration:none ;">'
					+ '<div class="thumbnail" style="margin:2px;">'
					+ '<div style="overflow:hidden;"><img class="img-thumbnail" style="padding:0px;width:100%;" src="' + baseUrl+obj.units[0].picPath +'" alt=" '+ obj.title + ' "></div>'
					+ '<span class="pic-num pull-right">' + obj.units.length + '图</span>'
					+ '<div class="caption"><b>' + obj.title + '</b>'
					+ '<div class="tutiao-fontBottom">'+ obj.author + ' ⋅ ' + obj.showNum + '浏览 ⋅ '+ getDate(obj.cdate)
					+ '</div></div></div></a></div>';
				
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
	var hideMobelNav = function()
	{
		$("#my-navbar-collapse").removeClass("in");
	}
	$("#nav_search").hide();
	$("#div_search").hide();
	var search = function()
	{
		hideMobelNav();
		
		$('#nav_search').show();
		$('#div_search').show();
		$("#nav_kan").hide();
		$("#tutiao_row").hide();
		$("#div_moile_tuijian").hide();
		$("#div_xiangguan").hide();
		$("#div_pinglun").hide();
		$("#search_row").html("");
		$('.masonry').masonry('reloadItems');
		
		tu_index = 0;
		getTuTiao(3,tu_index);
	}
	$(".glyphicon-search").keydown(function() {
        if (event.keyCode == "13") {//keyCode=13是回车键
        	search();
        }
    });
	$(".glyphicon-search:last").click(function(){
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
		datas.tid = $("#tutiao_col").attr("data-tid");
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
				var comment = JSON.parse(result);
				addPingLun(comment);
			}
			
		});
	});
	
	$("#tutiao-morepinglun").click(function(){
		var datas = {};
		datas.tid = $("#tutiao_col").attr("data-tid");
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
				var comments = JSON.parse(result);
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
	
	$("#div_moile_tuijian_more").click(function(){
		var pdata = {};
		pdata.index = $("#div_moile_tuijian_con .list-group-item").length-1;
		pdata.type = 2;
		$.ajax({
			type: "post",
			data : pdata,
			url: baseUrl + "tutiao_getTuTiaos"
		}).done(function(results) {
			var objs =  JSON.parse(results);
			for(var i=0;i<objs.length;i++)
			{
				var obj = objs[i];
				addMobileHot(obj);
			}
			if(objs.length == 0)
			{
				$("#div_moile_tuijian_more").text("没有啦");
				$("#div_moile_tuijian_more").addClass("disabled");
			}
		});
	});
	
	 $(".index-nav-btn").on({
	        mousedown:function(){
	            $(this).css("background-color","white");
	        },
	        mouseup:function(){
	            $(this).css("background-color","white");
	        }
	    });
	 
	 if(isMobile)
	 {
		 $(".tutiao-content").addClass("index-mcontent");
			
		 $("#tutiao_col").removeClass("col-xs-8");
		 $("#tutiao_col").addClass("col-xs-12");
		 $("#tutiao_col2").removeClass("col-xs-3");
		 $("#tutiao_col2").addClass("col-xs-12");
		 
		 $("#tutiao_col2").hide();
		 $("#div_xiangguan").hide();
		 $("#div_moile_tuijian").show();
	 }
});
