/**
 * Created by Guang on 2017/4/22.
 */
var baseUrl =  window.location.protocol + "//" + window.location.host + "/";
if(baseUrl.indexOf("8080") == -1)
	baseUrl = "www.tutiaoba.com/"
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
		$.ajax({
			type: "post",
			url: baseUrl + "tutiao_getShowTuiJian"
		}).done(function(results) {
			var objs = eval('(' + results + ')'); 
			if(objs.length > 0)
				tuijian_next_index = objs[0].tid;
			var s = '';
			for(var i=0;i<objs.length;i++)
			{
				var obj = objs[i];
				s = '<div class="col-sm-6 col-md-3 item">' 
					+ '<div onclick=window.open("' + baseUrl+ obj.tid + '") class="thumbnail">'
					+ '<div style="overflow:hidden;height:150px;"><img style="height:220px;" src="' +baseUrl+ obj.units[0].picPath + '" alt=" '+ obj.title + ' "></div>'
					+ '<span class="pic-num pull-right">' + obj.units.length + '图</span>'
					+ '<div class="caption"><b>' + obj.title + '</b>'
					+ '</div></div></div>';
				$("#tuijian_data").append(s);
				imgReady(baseUrl+obj.units[0].picPath, function () {
					var p = this.width/this.height;
					var pic = this.src;
			    	$('#tuijian_data .thumbnail img').each(function(){
		    		     var src = $(this).attr("src");
		    		     if(src == pic)
	    		    	 {
		    		    	 var sc = 1.5-p+1;
		    		    	 $(this).height($(this).height()*sc);			    		    	 
	    		    	 }
		    		  });
				});
			}
//			var items = $(s);
//			$("#tuijian_data").append(items).masonry( 'appended',items ).masonry();
//			$('.masonry').imagesLoaded(function() {
//				$('.masonry').masonry({
//					itemSelector: '.item'
//				});
//			});
		})		
	};
	getShowTuiJian();
	
	
	var getShowXiangGuan = function()
	{
		$.ajax({
			type: "post",
			url: baseUrl + "tutiao_getShowXiangGuan"
		}).done(function(results) {
			var objs = eval('(' + results + ')'); 
			if(objs.length > 0)
				tuijian_next_index = objs[0].tid;
			var s ='';
			for(var i=0;i<objs.length;i++)
			{
				var obj = objs[i];
				s = '<div class="col-sm-2 col-md-2 item">' 
					+ '<div onclick=window.open("' + baseUrl+ obj.tid + '") class="thumbnail">'
					+ '<div style="overflow:hidden;height:100px;"><img style="height:120px;" src="' + baseUrl+obj.units[0].picPath + '" alt=" '+ obj.title + ' "></div>'
					+ '<span class="pic-num pull-right">' + obj.units.length + '图</span>'
					+ '<div class="caption"><b>' + obj.title + '</b>'
					+ '</div></div></div>';
				$("#about_row").append(s);
				imgReady(baseUrl+obj.units[0].picPath, function () {
					var p = this.width/this.height;
					var pic = this.src;
			    	$('#about_row .thumbnail img').each(function(){
		    		     var src = $(this).attr("src");
		    		     if(src == pic)
	    		    	 {
		    		    	 var sc = 1.5-p+1;
		    		    	 $(this).height($(this).height()*sc);			    		    	 
	    		    	 }
		    		  });
				});
			}
//			var items = $(s);
//			$("#about_row").append(items).masonry( 'appended',items ).masonry();
			
//			$('.masonry').imagesLoaded(function() {
//				$('.masonry').masonry({
//					itemSelector: '.item'
//				});
//			});
		})		
	};
	getShowXiangGuan();
	
	
	
	//显示界面
	var tutiao_show_data = null;
	var tutiao_show_index = 0;
	var commentNum = 0;//评论数量
	var updateShow = function()
	{
		var tid = $("#tutiao_show").attr("data-tid");
		var datas = {};
		datas.tid = tid;
		$.ajax({
			type: "post",
			data : datas,
			url: baseUrl + "tutiao_getTuTiaoShow"
		}).done(function(results) {
			tutiao_show_data = eval('(' + results + ')'); 
			tutiao_show_index =0;
			$("#tutiao_show img:first").attr("src",baseUrl+tutiao_show_data.units[0].picPath);
			$("#tutiao_show_title").text(tutiao_show_data.title);
			$("#tutiao_show_curr_page").text("1");
			$("#tutiao_show_curr_page").next().text("/"+tutiao_show_data.units.length);
			$("#tutiao_show_des").html(tutiao_show_data.units[tutiao_show_index].tdescribe);
			$("#tuijian_title").text(tutiao_show_data.title);
			
			$("#tutiao_show_row").show();
			commentNum = tutiao_show_data.commentNum;
			updatePingLun(tutiao_show_data.comments);
		});
		
	}
	var updatePingLun = function(comments)
	{
		var s = '';
		for(var i=0;i<comments.length;i++)
		{
			var comment = comments[i];
			var t1 = new Date(comment.cdate.time);
			var t2 = new Date().getTime() - t1.getTime();
			var t3 = '刚刚';
			if(t2 > 1000*60 && t2 <= 1000*60*60)
				t3 = parseInt(t2/1000/60) + "分钟前";
			else if(t2 > 1000*60*60 && t2 <= 1000*60*60*24)
				t3 = parseInt(t2/1000/60/60) + "小时前";
			else if(t2 > 1000*60*60*24)
				t3 = parseInt(t2/1000/60/60/24) + "天前";
			
			s += '<li class="list-group-item">' +
	    			'<p class="text-left"><a>'+comment.userName+'</a> <small class="text-muted">'+t3+'</small></p>'+
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
		var t1 = new Date(comment.cdate.time);
		var t2 = new Date().getTime() - t1.getTime();
		var t3 = '刚刚';
		if(t2 > 1000*60 && t2 <= 1000*60*60)
			t3 = parseInt(t2/1000/60) + "分钟前";
		else if(t2 > 1000*60*60 && t2 <= 1000*60*60*24)
			t3 = parseInt(t2/1000/60/60) + "小时前";
		else if(t2 > 1000*60*60*24)
			t3 = parseInt(t2/1000/60/60/24) + "天前";
		var s = '<li class="list-group-item">' +
		'<p class="text-left"><a>'+comment.userName+'</a> <small class="text-muted">'+t3+'</small></p>'+
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
	$("#tuijian_row").hide();
	$('.tutiao-pic-left').hide();
	
	
	var picLeft = function()
	{
		if(tutiao_show_data)
		{
			tutiao_show_index--;
			if(tutiao_show_index < 0)
				tutiao_show_index = tutiao_show_data.units.length-1;
			$("#tutiao_show img:first").attr("src",baseUrl+tutiao_show_data.units[tutiao_show_index].picPath);
			$("#tutiao_show_curr_page").text(tutiao_show_index+1);
			$("#tutiao_show_des").html(tutiao_show_data.units[tutiao_show_index].tdescribe);
			
		}
	};
	
	var picRight = function()
	{
		if(tutiao_show_data && tutiao_show_index<tutiao_show_data.units.length)
		{
			tutiao_show_index++;
			if(tutiao_show_index>=tutiao_show_data.units.length)
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
			if(tutiao_show_index > 0)
				$('.tutiao-pic-left').show();
			$("#tutiao_show img:first").attr("src",baseUrl+tutiao_show_data.units[tutiao_show_index].picPath);
			$("#tutiao_show_curr_page").text(tutiao_show_index+1);
			$("#tutiao_show_des").html(tutiao_show_data.units[tutiao_show_index].tdescribe);
			
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
		var url = $("#tutiao_show img:first").attr("src");
		$(".tutiao-content").hide();
		$("#img_large_show").show();
		$("#img_large_show img:first").attr("src",url);
	})
	
	$("#tuijian_refresh").click(function(){
		$("#tuijian_row").hide();
		updateShow();
	});
	$("#tuijian_next").click(function(){
		$("#tuijian_row").hide();
		location.href = baseUrl + tuijian_next_index
	});
	
	updateShow();
	
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
	
	
});
