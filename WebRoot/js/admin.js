/**
 * Created by Steve on 2016/3/22.
 */
var baseUrl =  window.location.protocol + "//" + window.location.host + "/";
String.prototype.trim = function () {
	return this.replace(/(^\s*)|(\s*$)/g, '');
};


$(function() {

	var versionSelect = $(".versionSelect").find("select");

	if (versionSelect.length > 0) {
		versionSelect
				.find('option[value="' + location.pathname.slice(location.pathname.lastIndexOf('/') + 1) + '"]')
				.attr('selected', true);
	}

	versionSelect.change(function() {
		location.pathname = "/admin/" + $(this).find(":selected").val();
	});

	$('.versionChangeable').click(function(event){
		event.preventDefault();
		$(this).parent().find("[name='title']").removeAttr("disabled");
	});

	
	$('.changeIcon').click(function(event) {
		event.preventDefault();

		$('<input class="form-control" type="file" name="fileUpload" value=""/>' +
				'<button class="btn btn-primary change">上传图片</button>' +
				'<div class="spinner">' +
				'<div class="spinner-container container1">' +
				'<div class="circle1"></div>' +
				'<div class="circle2"></div>' +
				'<div class="circle3"></div>' +
				'<div class="circle4"></div>' +
				'</div>' +
				'<div class="spinner-container container2">' +
				'<div class="circle1"></div>' +
				'<div class="circle2"></div>' +
				'<div class="circle3"></div>' +
				'<div class="circle4"></div>' +
				'</div>' +
				'<div class="spinner-container container3">' +
				'<div class="circle1"></div>' +
				'<div class="circle2"></div>' +
				'<div class="circle3"></div>' +
				'<div class="circle4"></div>' +
				'</div>' +
				'</div>')
				.insertBefore($(this));

		$(this).siblings('.change').click(function(event) {
			event.preventDefault();
			changeIcon(this);
		});

		$(this).css('display', 'none');
	});



	$('.sub_btn').click(function() {
		if (isTitle()) {
			var tutiaos = {};
			tutiaos.title = $("[name='title']").val();
			tutiaos.author = $("[name='author']").val();
			tutiaos.units = getTuTiaoData();
			var data = JSON.stringify(tutiaos);
			$.ajax({
				type: "post",
				data: {tutiao: data},
				url: baseUrl + "tutiao_addTuTiao"
			}).done(function(results) {
				location.href = baseUrl + "tutiao_list";
			})
		}
	})
	
	$('.sub_btn2').click(function() {
		if (isTitle()) {
			var tutiaos = {};
			tutiaos.title = $("[name='title']").val();
			tutiaos.author = $("[name='author']").val();
			tutiaos.showNum = $("[name='showNum']").val();
			tutiaos.id = $("[name='title']")[0].title;
			tutiaos.units = getTuTiaoData();
			var data = JSON.stringify(tutiaos);
			$.ajax({
				type: "post",
				data: {tutiao: data},
				url: baseUrl + "tutiao_updateTuTiao"
			}).done(function(results) {
				location.href = baseUrl + "tutiao_list";
			})
		}
	})


	$('.add_hot').click(function(event) {
		event.preventDefault();
		addOneHot(this);
	})

	$('.hotGames').delegate('.del-btn', 'click', function(event) {
		event.preventDefault();
		delOneGame(this);
	})

	$('.hotGames').delegate('.uploadBtn', 'click', function(event) {
		event.preventDefault();
		uploadFile(this);
	})

	$('.add_btn').click(function(event) {
		event.preventDefault();
		location.href = baseUrl + "tutiao_add";
	})
	
	
	$('.update_btn').click(function(event) {
		event.preventDefault();
		location.href = baseUrl + "tutiao_update?id="+$(this).attr("title");
	})
	
	
	$('.delete_btn').click(function(event) {
		event.preventDefault();
		location.href = baseUrl + "tutiao_deleteTuTiao?id="+$(this).attr("title");
	})
	
	$('.pinglun_btn').click(function(event) {
		event.preventDefault();
		location.href = baseUrl + "comment_list?tid="+$(this).attr("title");
	})
	
	$('.pinglun_delete_btn').click(function(event) {
		event.preventDefault();
		location.href = baseUrl + "comment_deleteComment?id="+$(this).attr("title") + "&tid=" + $(this).attr("data-tid");
	})
	
	$('.check_btn').click(function(){
		var check = $(this).attr("data-check");
		var title = $(this).attr("title");
		var btn = $(this);
		$.ajax({
			type: "post",
			data: {id:title,checked: check},
			url: baseUrl + "tutiao_checkTuTiao"
		}).done(function(results) {
			if(results == "true")
			{
				if(check == "false")
				{
					btn.removeClass("btn-warning");
					btn.text("已审核");
					btn.attr("data-check","true");
				}
				else
				{
					btn.addClass("btn-warning");
					btn.text("审核");
					btn.attr("data-check","false");
				}
			}
		})
	});
	
	$("#auto-caiji").keydown(function() {
        if (event.keyCode == "13") {//keyCode=13是回车键
        	var dtype = 1;//mm131
        	var durl = $("#auto-caiji").val();
        	if(durl == null || durl == '' || !durl)
        		return;
        	
        	if(durl.indexOf("#p") != -1)
        		dtype = 2;
        	
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


	var curr_index = $("#a_curr").attr("title");
    var c_index = parseInt(curr_index);
    var p_index = c_index - 1;
    if(p_index < 1)
    	p_index = 1;
    
    var a_num = $("#a_num").attr("title");
    var num = parseInt(a_num);
    var n_index = c_index + 1;
    if(n_index > num)
    	n_index = num;
    
    $("#a_first").attr("href","tutiao_list?index="+1);
    $("#a_pre").attr("href","tutiao_list?index="+p_index);
    $("#a_next").attr("href","tutiao_list?index="+n_index);
    $("#a_end").attr("href","tutiao_list?index="+num);
    
    
    var curr_index2 = $("#a_curr2").attr("title");
    var c_index2 = parseInt(curr_index2);
    var p_index2 = c_index2 - 1;
    if(p_index2 < 1)
    	p_index2 = 1;
    
    var a_num2 = $("#a_num2").attr("title");
    var num2 = parseInt(a_num2);
    var n_index2 = c_index2 + 1;
    if(n_index2 > num2)
    	n_index2 = num2;
    var tid = $("#ye2").attr("data-tid");
    $("#a_first2").attr("href","comment_list?index="+1+"&tid="+tid);
    $("#a_pre2").attr("href","comment_list?index="+p_index2+"&tid="+tid);
    $("#a_next2").attr("href","comment_list?index="+n_index2+"&tid="+tid);
    $("#a_end2").attr("href","comment_list?index="+num2+"&tid="+tid);
});



function getTuTiaoData() {
	var objArr = [];
	var tdescribe = $("[name='tdescribe']");
	var picPath = $("[name='picPath']");
	
	if(tdescribe.length) {
		for (var i = 0; i < tdescribe.length; i++) {
			var obj = {};
			obj.id = tdescribe[i].title;
			obj.tdescribe = tdescribe[i].value;
			obj.picPath = picPath[i].value;
			objArr.push(obj);
		}
	}
	
	return objArr;
}


//添加一个热门游戏
function addOneHot(btn){
	var gameNum = $(btn).siblings(".game-group").length + 1;
	var inputGroup =
			'<div class="game-group col-sm-offset-1">' +
			'<div class="form-group">' +
			'<label class="control-label col-sm-3 col-sm-offset-1">' + '图'+ gameNum + '</label>' +
			'<div class="col-sm-5">' +
			'<button class="btn btn-danger del-btn">删除</button>' +
			'</div>' +
			'</div>' +
			'<div class="form-group">' +
			'<label class="control-label col-sm-3 col-sm-offset-1">图片说明</label>' +
			'<div class="col-sm-8">' +
			'<textarea  name="tdescribe" value="" title="0" class="form-control"></textarea>' +
			'</div>' +
			'</div>' +
			'<div class="form-group">' +
			'<label class="control-label col-sm-3 col-sm-offset-1">图片路径</label>' +
			'<div class="col-sm-5">' +
			'<input type="text" class="form-control gameIcon" name="picPath" disabled="disabled"/>' +
			'</div>' +
			'</div>' +
			'<div class="form-group">' +
			'<label class="control-label col-sm-3 col-sm-offset-1">图片</label>' +
			'<div class="col-sm-5">' +
			'<input type="file" class="form-control fileUpload" name="fileUpload"/>' +
			'<button class="btn btn-primary uploadBtn" >上传图片</button>' +
			'<div class="spinner">' +
			'<div class="spinner-container container1">' +
			'<div class="circle1"></div>' +
			'<div class="circle2"></div>' +
			'<div class="circle3"></div>' +
			'<div class="circle4"></div>' +
			'</div>' +
			'<div class="spinner-container container2">' +
			'<div class="circle1"></div>' +
			'<div class="circle2"></div>' +
			'<div class="circle3"></div>' +
			'<div class="circle4"></div>' +
			'</div>' +
			'<div class="spinner-container container3">' +
			'<div class="circle1"></div>' +
			'<div class="circle2"></div>' +
			'<div class="circle3"></div>' +
			'<div class="circle4"></div>' +
			'</div>' +
			'</div>' +
			'</div>' +
			'</div>' +
			'</div>';

	inputGroup = $(inputGroup);
	inputGroup.insertBefore($(btn));
}


function delOneGame(btn) {

	var imgUrl = $(btn).parent().parent().siblings().find('.gameIcon').val().trim();

	if (imgUrl) {
		$.ajax({
			type:'POST',
			url: baseUrl + 'tutiao_deleteTuTiaoPic',
			data: {imgUrl: imgUrl},
			success: function (data) {
				$(btn).parents(".game-group").remove();
			},
			error: function() {
				console.log('error');
			}
		})
	}
	else {
		$(btn).parents(".game-group").remove();
	}
}




function isTitle() {

	var versionInput = $("[name='title']");
	var warning = versionInput.siblings('.warning');

	var dirName = versionInput.val().trim();
	if (dirName != "" && dirName != null && dirName != undefined) {
		warning.html('');
		return true;
	}
	else {
		versionInput.focus();
		warning.html('未填写标题！');
		return false;
	}

}


//上传图片
function uploadFile(btn) {

	var spiner = $(btn).siblings('.spinner');
	var fileToUpload = $(btn).siblings('input');

	if (isTitle()) {

		if (fileToUpload.val().length) {

			spiner.css('display', 'inline-block');

			var fileName = fileToUpload.val();
			var title = $("[name='title']").val().trim();
			var extension = fileName.slice(fileName.lastIndexOf('.'), fileName.length).toLowerCase();

			if (extension == ".jpg" || extension == ".png" || extension == ".jpeg" || extension == ".gif") {

				var data = new FormData();
				data.append('pic', fileToUpload[0].files[0]);
				data.append('fileName', fileName);
				var type = $(btn).attr("title");
				
				$.ajax({
					url: baseUrl+'tutiao_upload?title='+title,
					type: 'POST',
					data: data,
					cache: false,
					contentType: false, //不可缺参数
					processData: false, //不可缺参数
					success: function(url) {
						var displayUrl = url;

						spiner.css('display', 'none');
						$(btn).parent().parent().siblings().find('.gameIcon').val(displayUrl);
						$(btn).parent().html('<img class="img-responsive" src=' + displayUrl + ' />');
					},
					error: function() {
						console.log('error');
					}
				})
			}
		}
	}
}



//更改游戏Icon
function changeIcon(btn) {

	var spiner = $(btn).siblings('.spinner');
	var fileToUpload = $(btn).siblings('input');

	if(isTitle()) {

		if (fileToUpload.val().length) {

			spiner.css('display', 'inline-block');

			var fileName = fileToUpload.val();
			var title = $("[name='title']").val().trim();
			var extension = fileName.slice(fileName.lastIndexOf('.'), fileName.length).toLowerCase();

			if (extension == ".jpg" || extension == ".png" || extension == ".jpeg" || extension == ".gif") {

				var data = new FormData();
				var fileToDel = $(btn).parent().parent().siblings().find('.gameIcon').val().trim();

				data.append('pic', fileToUpload[0].files[0]);
				data.append('fileName', fileName);
				var type = $(btn).attr("title");
				$.ajax({
					url: baseUrl+'tutiao_upload?title='+title+'&fileToDel='+fileToDel,
					type: 'POST',
					data: data,
					cache: false,
					contentType: false, //不可缺参数
					processData: false, //不可缺参数
					success: function(url) {

						var displayUrl = url;

						spiner.css('display', 'none');
						$(btn).parent().parent().siblings().find('.gameIcon').val(displayUrl);
						$(btn).parent().html('<img class="img-responsive" src=' + displayUrl + ' />');
						
					},
					error: function() {
						console.log('error');
					}
				})
			}
		}
	}
}