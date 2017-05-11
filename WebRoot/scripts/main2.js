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
		itemSelector: '.item'
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
				s += '<div class="col-sm-6 col-md-3 item">' 
					+ '<div onclick=window.open("' + baseUrl+ obj.tid + '") class="thumbnail">'
					+ '<img src="' +baseUrl+ obj.units[0].picPath + '" alt=" '+ obj.title + ' ">'
					+ '<span class="pic-num pull-right">' + obj.units.length + '图</span>'
					+ '<div class="caption"><b>' + obj.title + '</b>'
					+ '</div></div></div>';
				imgReady(baseUrl+obj.units[0].picPath, function () {
					$('.masonry').masonry();
				});
			}
			var items = $(s);
			$("#tuijian_data").append(items).masonry( 'appended',items ).masonry();
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
				s += '<div class="col-sm-2 col-md-2 item">' 
					+ '<div onclick=window.open("' + baseUrl+ obj.tid + '") class="thumbnail">'
					+ '<img src="' + baseUrl+obj.units[0].picPath + '" alt=" '+ obj.title + ' ">'
					+ '<span class="pic-num pull-right">' + obj.units.length + '图</span>'
					+ '<div class="caption"><b>' + obj.title + '</b>'
					+ '</div></div></div>';
				imgReady(baseUrl+obj.units[0].picPath, function () {
					$('.masonry').masonry();
				});
			}
			var items = $(s);
			$("#about_row").append(items).masonry( 'appended',items ).masonry();
			
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
	var updateShow = function()
	{
		var id = $("#tutiao_show").attr("data-id");
		var datas = {};
		datas.id = id;
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
			resetPicWidth();
		});
		
	}
	$("#tuijian_row").hide();
	$('.pic_left').hide();
	var resetPicWidth = function()
	{
		var width = $("#tutiao_show").width();
		var img = $("#tutiao_show img:first").attr("src");
		imgReady(img, function () {
			
			if(this.width / this.height < 1.5)
			{
				var w = this.width/this.height/1.5*width;
				$("#tutiao_show img:first").attr("width",parseInt(w)+"px");
			}
			else
			{
				$("#tutiao_show img:first").attr("width",width+"px");
			}
				
		});
	};
	
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
			
			resetPicWidth();
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
				$('.masonry').imagesLoaded(function() {
					$('.masonry').masonry({
						itemSelector: '.item'
					});
				});
				return;
			}
			if(tutiao_show_index > 0)
				$('.pic_left').show();
			$("#tutiao_show img:first").attr("src",baseUrl+tutiao_show_data.units[tutiao_show_index].picPath);
			$("#tutiao_show_curr_page").text(tutiao_show_index+1);
			$("#tutiao_show_des").html(tutiao_show_data.units[tutiao_show_index].tdescribe);
			
			resetPicWidth();
		}
	};
	
	$('#tutiao_show').hover(function(){
//		$(this).addClass("cursor_right"); 
	},function(){
		$(this).removeClass("cursor_right"); 
		$(this).removeClass("cursor_left"); 
	});
	var posLeft = true;
	$('#tutiao_show').mousemove(function(e) {  
		var xx=e.pageX-$('#tutiao_show').offset().left;
		var width = $(this).width();
		if(xx>width/2)
		{
			$(this).removeClass("cursor_left"); 
			$(this).addClass("cursor_right");
			posLeft = false;
		}
		else
		{
			$(this).removeClass("cursor_right"); 
			$(this).addClass("cursor_left");
			posLeft = true;
		}
	}); 
	$("#tutiao_show").mousedown(function(e){
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
	
	$('#pic_left').click(function(event) {
		picLeft();
	})
	$('#pic_right').click(function(event) {
		 picRight();
	})
	
	$("#img_large_show").hide();
	$("#img_large_show").click(function(){
		$("#img_large_show").hide();
		$(".content").show();
	});
	$('#pic_lager').click(function(event) {
		var url = $("#tutiao_show img:first").attr("src");
		$(".content").hide();
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
					+ '<img src="' + baseUrl+obj.units[0].picPath +'" alt=" '+ obj.title + ' ">'
					+ '<span class="pic-num pull-right">' + obj.units.length + '图</span>'
					+ '<div class="caption"><b>' + obj.title + '</b>'
					+ '<div class="fontBottom">'+ obj.author + ' ⋅ ' + obj.showNum + '浏览 ⋅ '+ getDate(obj.cdate)
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
});
