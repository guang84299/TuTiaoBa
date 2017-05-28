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
	

	$('.masonry').masonry({
		itemSelector: '.item',
		transitionDuration: '0.0s',
		stagger: 0
	});
	
	
	function getDate(strDate) {
        var date = new Date(strDate.time);
//        return date.format('yyyy-MM-dd hh:mm:ss');
        return date.format('yyyy-MM-dd');
    }
	
	var curr_type = $("#tutiaos").attr("data-type");
	var tu_index = $("#tutiaos .item").length;
	var first_time = 0;
	var getTuTiao = function(type,index)
	{		
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
			if(objs.length == 0)
			{
				$("#imloading").text("没有啦.....");
			}
			else
			{	
				$("#imloading").text("加载中.....");
			}
			var col_num = "col-xs-3";
			if(isMobile)
				col_num = "col-xs-6";
			$("#imloading").fadeTo(first_time,0.01,function(){
				
				for(var i=0;i<objs.length;i++)
				{
					var obj = objs[i];
					
					s += '<div class="'+col_num+' item"  style="padding:1px;">' 
						+ '<a href="'+ baseUrl+ obj.tid +'" style="text-decoration:none ;">'
						+ '<div class="thumbnail" style="margin:2px;">'
						+ '<div style="overflow:hidden;"><img class="img-thumbnail" style="padding:0px;width:100%;" src="' + baseUrl+obj.units[0].picPath + '" alt=" '+ obj.units[0].tdescribe + ' "></div>'
						+ '<span class="pic-num pull-right">' + obj.units.length + '图</span>'
						+ '<div class="caption"><b>' + obj.title + '</b>'
						+ '<div class="index-fontBottom">'+ obj.author + ' ⋅ ' + obj.showNum + '浏览 ⋅ '+ getDate(obj.cdate)
						+ '</div></div></div></a></div>';
					
					imgReady(baseUrl+obj.units[0].picPath, function () {
//						$(".item img:eq(3)").height = this.height;
						$('.masonry').masonry();
						
					});
				}
				var items = $(s);
				$("#tutiaos").append(items).masonry( 'appended',items ).masonry();
//				$('.masonry').imagesLoaded(function() {
//					$('.masonry').masonry();
//				});
				
				tu_index += objs.length;
			});	
			$(".index-bottom").show();
			
			first_time = 500;
		})		
	};
	
	var initPic = function()
	{
		$("#tutiaos .item img").each(function(){
		     var src = $(this).attr("src");
		     imgReady(src, function () {
		    	 	$('.masonry').masonry();	
				});
		  });	
		$('#imloading').css('opacity',0.01);
	}
	
	initPic();

	var winH = $(window).height();
	$(window).scroll(function () {   
        if( $(document).scrollTop() + winH > $(document).height()-winH/5)
    	{
        	var opa = $('#imloading').css('opacity');
        	if(opa <= 0.01)
    		{
        		$("#imloading").fadeTo(500,0.7,function(){
            		getTuTiao(curr_type,tu_index);
            	});
    		}
    	}
     });
	var hideMobelNav = function()
	{
		$("#my-navbar-collapse").removeClass("in");
	}

	var search = function()
	{
		hideMobelNav();
		$('#nav_search').show();
		$("#nav_hot").hide();
		$("#nav_new").hide();
		$("#tutiaos").html("");
		$('.masonry').masonry('reloadItems');
		curr_type = 3;
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
		$("#tutiaos").html("");
		$('.masonry').masonry('reloadItems');
		location.href = baseUrl;
	});
	
	$('#particles').particleground({
        dotColor: '#faeaea',
        lineColor: '#faeaea'
      });
	
	$("#btn-login").click(function(){
		location.href = baseUrl + "user_login";
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
		$(".index-content").addClass("index-mcontent");
		$(".index-content2").addClass("index-mcontent");
		$(".index-content3").addClass("index-mcontent");
		$(".item").removeClass("col-xs-3");
		$(".item").addClass("col-xs-6");
		$('.masonry').masonry();
	}
	
	
});
