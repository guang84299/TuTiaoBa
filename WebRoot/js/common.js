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
        return date.format('yyyy.MM.dd hh:mm');
    }
	
	var search = function()
	{
		var link = baseUrl + "article_search";
		var val = $(".glyphicon-search:first").val();
		if(val == null || val == '' || val == undefined)
		{
			alert("不能为空！");
			return;
		}
		var link = baseUrl + "article_search?val="+val+"&index=0";
		window.open(link);
//		pdata.val = encodeURI(val);
	}
	$(".glyphicon-search").keydown(function() {
        if (event.keyCode == "13") {//keyCode=13是回车键
        	search();
        }
    });
	$(".glyphicon-search:last").click(function(){
		search();
	});
	
	 $(".g-btn-appreciate").click(function(){
         var dis = $(".g-con-appreciate-show").css("display");
         if(dis == "none")
         {
           $(".g-con-appreciate-show").show();
         }
         else
         {
           $(".g-con-appreciate-show").hide();
         }
     });
	//love
	$(".g-con-share-heart").click(function(){
		var aid = $(this).attr("data-id");
		$.ajax({
		type: "post",
		data: {id:aid},
		url: baseUrl + "article_love"
		}).done(function(res) {
			if(res == 'true')
			{
				if($(".g-con-share-heart").hasClass("g-con-share-heart-love"))
				{
					$(".g-con-share-heart").removeClass("g-con-share-heart-love");
					$(".loveNum").text(parseInt($(".loveNum:eq(0)").text())-1);
				}
				else
				{
					$(".g-con-share-heart").addClass("g-con-share-heart-love");
					$(".loveNum").text(parseInt($(".loveNum:eq(0)").text())+1);
				}
			}
		});
	});
	//评论
	$(".g-btn-pinglun").click(function(){
		var text = $("#ta_pinglun").val();
		if(text == null || text == '' || text == undefined)
		{
			alert("评论内容不能为空！");
			return;
		}
		var aid = $("#ta_pinglun").attr("data-id");
		$.ajax({
		type: "post",
		data: {id:aid,content:text},
		url: baseUrl + "article_comment"
		}).done(function(res) {
			if(res != 'false')
			{
				var objs = JSON.parse(res);
				addCommet(objs,1);
				$(".commentNum").text(parseInt($(".commentNum:eq(0)").text())+1);
				$("#ta_pinglun").val("");
			}
		});
	});
	//更多评论
	$(".g-label").click(function(){
		var aid = $("#ta_pinglun").attr("data-id");
		var count = $("#commets").children().length;
		$.ajax({
		type: "post",
		data: {id:aid,index:count},
		url: baseUrl + "article_morecomment"
		}).done(function(res) {
			if(res != 'false')
			{
				var objs = JSON.parse(res);
				addCommet(objs,2);
				if(objs.length < 10)
				{
					$(".g-label").hide();
				}
			}
		});
	});
	
	 var addCommet = function(commets,type)
     {
     	for(var i=0;i<commets.length;i++)
     	{
     		var commet = commets[i];
     		var love = "";
     		if(commet.love == true)
     		{
     			love = "g-con-pinglun-con-zan-love";
     		}
     			
     		var s = '<div>'
     		+ '<p><strong>'+commet.xip+'</strong>&nbsp;&nbsp;&nbsp;<small class="text-muted">'+commet.floor+'楼 · '+getDate(commet.cdate)+'</small></p>'
     		+ '<p>'+commet.content+'</p>'
     		+ '<div class="text-right g-con-pinglun-con-zan '+ love +'" data-id="'+ commet.id +'"><span class="glyphicon glyphicon-thumbs-up"></span> <span class="commentloveNum">'+commet.loveNum + '</span>人赞</div>'
     		+ '<hr></div>';
     		if(type == 1)
     			$("#commets").prepend(s);
     		else
     			$("#commets").append(s);
     	}
     	commentLove();
     }
     
     //评论点赞
     var commentLove = function()
     {
     	$(".g-con-pinglun-con-zan").unbind("click");
     	$(".g-con-pinglun-con-zan").click(function(){
			var aid = $(this).attr("data-id");
			var zan = $(this);
			$.ajax({
			type: "post",
			data: {id:aid},
			url: baseUrl + "article_commentlove"
			}).done(function(res) {
				if(res == 'true')
				{
					if(zan.hasClass("g-con-pinglun-con-zan-love"))
					{
						zan.removeClass("g-con-pinglun-con-zan-love");
						$(".commentloveNum",zan).text(parseInt($(".commentloveNum:eq(0)",zan).text())-1);
					}
					else
					{
						zan.addClass("g-con-pinglun-con-zan-love");
						$(".commentloveNum",zan).text(parseInt($(".commentloveNum:eq(0)",zan).text())+1);
					}
				}
			});
		});
     }
     
   //赞评论
 	commentLove();
});
