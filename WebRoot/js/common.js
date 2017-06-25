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
	if(location.href.indexOf("mm") != -1)
	{
		$(".navbar-nav li:eq(0)").addClass("active");
	}
	if(location.href.indexOf("life") != -1)
	{
		$(".navbar-nav li:eq(1)").addClass("active");
	}
	
	var search = function()
	{
		var link = baseUrl + "tutiao_search";
		var val = $(".glyphicon-search:first").val();
		if(val == null || val == '' || val == undefined)
		{
			alert("不能为空！");
			return;
		}
		var link = baseUrl + "tutiao_search?val="+val+"&index=0";
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
});
