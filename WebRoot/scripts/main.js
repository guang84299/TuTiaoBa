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

	$('.masonry').masonry({
		itemSelector: '.item'
//		transitionDuration: '0.0s',
//		stagger: 0
	});
	
	
	function getDate(strDate) {
        var date = new Date(strDate.time);
        return date.format('yyyy-MM-dd hh:mm:ss');

    }
	var loading = 0;
	var curr_type = 1;
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
			$("#imloading").fadeOut(500,function(){
				
				for(var i=0;i<objs.length;i++)
				{
					var obj = objs[i];
					
					s += '<div class="col-sm-6 col-md-3 item">' 
						+ '<div onclick=window.open("' + baseUrl+'tutiao_findTuTiao?id=' + obj.id + '") class="thumbnail">'
						+ '<img height="1500px" src="' + baseUrl+obj.units[0].picPath + '">'
						+ '<span class="pic-num pull-right">' + obj.units.length + '图</span>'
						+ '<div class="caption"><b>' + obj.title + '</b>'
						+ '<div class="fontBottom">'+ obj.author + ' ⋅ ' + obj.showNum + '浏览 ⋅ '+ getDate(obj.cdate)
						+ '</div></div></div></div>';
					
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
				
				loading = 0;
				tu_index += objs.length;
				if(objs.length == 0)
				{
					tu_index = 0;
					if(type!=3)
					getTuTiao(curr_type,tu_index);
				}
				
			});	
		})		
	};
	getTuTiao(1,0);
	$("#nav_new").css("color","#f00");
	
	var hideMobelNav = function()
	{
		$("#my-navbar-collapse").removeClass("in");
	}
	
	$("#nav_new").click(function(){
		curr_type = 1;
		tu_index = 0;
		hideMobelNav();
		$("#tutiaos").html("");
		$("#nav_new").addClass("myactive");
		$("#nav_hot").removeClass("myactive");
		$('.masonry').masonry('reloadItems');
		getTuTiao(1,0);
		
	});
	$("#nav_hot").click(function(){
		curr_type = 2;
		tu_index = 0;
		hideMobelNav();
		$("#nav_new").css("color","");
		$("#tutiaos").html("");
		$("#nav_hot").addClass("myactive");
		$("#nav_new").removeClass("myactive");
		$('.masonry').masonry('reloadItems');
		getTuTiao(2,0);
	});
	var winH = $(window).height();
	$(window).scroll(function () {   
        if( $(document).scrollTop() + winH > $(document).height()-1  && loading == 0 )
    	{
        	loading = 1;
        	$("#imloading").fadeTo(500,0.7);
        	 setTimeout(function(){
        		 getTuTiao(curr_type,tu_index);
        	 }, 1000)
    	}
     });
	
	$('#nav_search').hide();
	var search = function()
	{
		hideMobelNav();
		$('#nav_search').show();
		$("#nav_hot").hide();
		$("#nav_new").hide();
		$("#tutiaos").html("");
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
