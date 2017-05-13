<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	isELIgnored="false"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<jsp:include page="/includes/head.jsp" />
	<div class="regist-logo-wrap">
    <img src="<%=basePath%>images/logo.png" href="<%=basePath%>" class="img-responsive">
  </div>
  <div class="well regist-content">
      <form class="form-horizontal" role="form">
        <div class="form-group">
          <label for="firstname" class="col-sm-3 control-label">用户名</label>
          <div class="col-sm-8">
            <input type="text" class="form-control" id="username" placeholder="请输入用户名">
          </div>
        </div>
        <div class="form-group">
          <label for="lastname" class="col-sm-3 control-label">邮箱</label>
          <div class="col-sm-8">
            <input type="text" class="form-control" id="email" placeholder="请输入邮箱">
          </div>
        </div>
        <div class="form-group">
          <label for="inputPassword" class="col-sm-3 control-label">密码</label>
          <div class="col-sm-8">
            <input type="password" class="form-control" id="password" placeholder="请输入密码">
          </div>
        </div>
        <div class="form-group">
          <label for="inputPassword" class="col-sm-3 control-label">确认密码</label>
          <div class="col-sm-8">
            <input type="password" class="form-control" id="password2" placeholder="请确认密码">
          </div>
        </div>
        <div id="regist-btn-reg">
          注 册
        </div>
        <div class="form-group" id="regist-pro" style="display:none;">
        	<label class="col-sm-5 control-label"></label>
        	<img class="col-sm-2" src="<%=basePath%>images/pro.gif">
        </div>
      </form>
	<h5 id="regist-result" style="display:none;">恭喜注册成功！我们已经为您发送一封电子邮件，请您登陆邮箱，验证激活帐号！</h5>
	</div>
  <jsp:include page="/includes/foot.jsp" /> 
<script >
var baseUrl =  window.location.protocol + "//" + window.location.host + "/";
		if(baseUrl.indexOf("8080") == -1)
			baseUrl = "www.tutiaoba.com/"
  $(function(){

      $('#particles').particleground({
    dotColor: '#faeaea',
    lineColor: '#faeaea'
    });

      function validateEmail(emailValue){  
          var reg = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;  
          if(reg.test(emailValue))
          {
            return true;
          }  
          else
          {
            alert("邮箱格式不正确！");
            return false;
          }
      };

      function validateName(name){  
          if(name == null)
          {
            alert("用户名不能为空！");
            return false;
          }
          if(name.trim().length < 2 || name.trim().length > 12)
          {
            alert("用户名长度在2-12之间！");
            return false;
          }
          return true;
      };

      function validatePassword(password){  
          if(password == null)
          {
            alert("密码不能为空！");
            return false;
          }
          if(password.trim().length < 6 || password.trim().length > 16)
          {
            alert("密码长度在6-16之间！");
            return false;
          }
          return true;
      };

      function validatePassword2(password,password2){  
          if(password != password2)
          {
            alert("两次密码不一致！");
            return false;
          }
         
          return true;
      };
      var nameuse = false;
      $("#username").blur(function(){
          nameuse = false;
          var name = $("#username").val();
          $.ajax({
            type: "post",
            data: {username: name},
            url: baseUrl + "user_checkName"
          }).done(function(result) {
            nameuse = result;
          });
      });
      var emailuse = false;
      $("#email").blur(function(){
          emailuse = false;
          var mail = $("#email").val();
          $.ajax({
            type: "post",
            data: {email: mail},
            url: baseUrl + "user_checkMail"
          }).done(function(result) {
            emailuse = result;
          });
      });
      
      var regist = function()
      {
      	var mail = $("#email").val();
        var pass = $("#password").val();
        var username = $("#username").val();
        if(validateName(username))
        {
          if(nameuse == 'false')
          {
            alert("用户名已存在！");
            return;
          }
          if(validateEmail(mail))
          {
          	  if(emailuse == 'false')
	          {
	            alert("此邮箱已被占用！");
	            return;
	          }
            if(validatePassword(pass))
            {
                if(validatePassword2(pass,$("#password2").val()))
                {
                  	$("#regist-btn-reg").hide(); 
                  	$("#regist-pro").show();
                  	
                  	$.ajax({
			            type: "post",
			            data: {name:username,password:pass,email: mail},
			            url: baseUrl + "user_register"
			          }).done(function(result) {
			            	if(result == 'true')
			            	{
			            		$("#regist-result").show();
			            		$(".form-horizontal").hide();
			            	}
			            	else
			            	{
			            		alert("注册失败！请重试");
			            		location.href = baseUrl + "user_toRegister";
			            	}
			          });
                }
            }
          }
        }
      };

      $("#regist-btn-reg").click(function(){
      		regist();
      });
      
      
	 $(".regist-logo-wrap").click(function(){
		location.href = baseUrl;
	 });

  });
</script>